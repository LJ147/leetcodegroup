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
    public Page<CheckDayInfo> checkDayInfos(
            @RequestParam(value="page")Integer page,
            @RequestParam(value="pageSize")Integer pageSize ,
            @RequestParam(value="date") String date) {
        if (date == null) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            date = df.format(new Date());
        }
        Pageable pageable=new PageRequest(page,pageSize);
        return checkDayInfoService.checkDayInfos(date,pageable);
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

}
