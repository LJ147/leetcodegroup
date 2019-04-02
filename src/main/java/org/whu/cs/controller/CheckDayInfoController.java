package org.whu.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.whu.cs.bean.CheckDayInfo;
import org.whu.cs.service.CheckDayInfoService;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/checkDayInfo")
public class CheckDayInfoController {
    @Autowired
    private CheckDayInfoService checkDayInfoService;

    @GetMapping(value = "/day")
    @ResponseBody
    public List<CheckDayInfo> checkDayInfos(

            @RequestParam(value = "date") String date) {
        if (date == null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            date = df.format(new Date());
        }
        return checkDayInfoService.checkDayInfos(date);
    }

    @GetMapping(value = "/info")
    @ResponseBody
    public Page<CheckDayInfo> checkDayInfoPage(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "date") String date) {
        if (date == null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            date = df.format(new Date());
        }
        Pageable pageable = new PageRequest(page, pageSize);
        return checkDayInfoService.checkDayInfoPage(date, pageable);
    }


    @PostMapping(value = "/create")
    @ResponseBody
    public CheckDayInfo create(CheckDayInfo checkDayInfo) {
        checkDayInfoService.create(checkDayInfo);
        return checkDayInfo;
    }

    @GetMapping(value = "/summary")
    @ResponseBody
    public Map<Object, Object> todaySummary(String date) {
        if (date == null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            date = df.format(new Date());
        }

        Map<Object, Object> summary = new HashMap<>();

        summary.put("date", date);
        Integer totalUserCount = checkDayInfoService.getTotalUserCount(date);
        Integer checkedCount = checkDayInfoService.getCheckedCount(date);

        double ratio = totalUserCount == 0 ? 0 : (double) checkedCount / totalUserCount;

        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMaximumFractionDigits(1);//设置保留几位小数

        summary.put("totalUserCount", totalUserCount);
        summary.put("checkedCount", checkedCount);
        summary.put("checkRatio", format.format(ratio));

        return summary;
    }

    @PostMapping(value = "/submission")
    @ResponseBody
    public void updateSubmission(CheckDayInfo checkDayInfo) {
        String username = checkDayInfo.getUsername();
        String date = checkDayInfo.getDate();
        if (username != null && date != null) {
            CheckDayInfo res = checkDayInfoService.findByUsernameAndDate(username, date);

            // 已经有记录，更新数据
            if (res != null) {
                res.setChecked(checkDayInfo.getChecked());
                res.setUpdateTime(res.getUpdateTime());

                checkDayInfoService.save(res);


            } else {             // 没有记录，新建记录


                checkDayInfoService.save(checkDayInfo);
            }


        }
    }

    @PostMapping(value = "/userInfo")
    @ResponseBody
    public void updateUserInfo(CheckDayInfo checkDayInfo) throws ParseException {
        String username = checkDayInfo.getUsername();
        String date = checkDayInfo.getDate();
        if (username != null && date != null) {
            CheckDayInfo res = checkDayInfoService.findByUsernameAndDate(username, date);

            // 已经有记录，更新数据
            if (res != null) {
                res.setUpdateTime(checkDayInfo.getUpdateTime());
                res.setAcceptanceRate(checkDayInfo.getAcceptanceRate());
                res.setSolvedQuestion(checkDayInfo.getSolvedQuestion());
                res.setAcceptedSubmission(checkDayInfo.getAcceptedSubmission());
                res.setAddress(checkDayInfo.getAddress());
                res.setAvatar(checkDayInfo.getAvatar());

                if (res.getChecked() == 0) {
                    Integer solvedQuestionOfYesterday = checkDayInfoService.getSolvedProblemOfYesterday(username, date);
                    res.setSolvedProblemNumberOfToday(checkDayInfo.getSolvedQuestion() - solvedQuestionOfYesterday);
                } else {
                    res.setSolvedProblemNumberOfToday(0);
                }
                checkDayInfoService.save(res);

            }
        }
    }
}
