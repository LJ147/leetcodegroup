package org.whu.cs.service;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.whu.cs.bean.CheckDayInfo;
import org.whu.cs.repository.CheckDayInfoRepository;

import java.net.Inet4Address;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CheckDayInfoService {
    @Autowired
    private CheckDayInfoRepository checkDayInfoRepository;

    public Page<CheckDayInfo> checkDayInfoPage(String date, Pageable pageable) {
        return checkDayInfoRepository.findAllByDate(date, pageable);

    }

    public List<CheckDayInfo> checkDayInfos(String date) {
        return checkDayInfoRepository.findByDate(date);

    }

    public CheckDayInfo findByUsernameAndDate(String username, String date) {
        return checkDayInfoRepository.findByUsernameAndDate(username, date);

    }

    public void save(CheckDayInfo checkDayInfo) {
        checkDayInfoRepository.save(checkDayInfo);
    }

    public CheckDayInfo create(CheckDayInfo checkDayInfo) {
        return checkDayInfoRepository.save(checkDayInfo);
    }

    public Integer getTotalUserCount(String date) {
        return checkDayInfoRepository.countAllByDate(date);
    }

    public Integer getCheckedCount(String date) {
        return checkDayInfoRepository.countAllByDateAndChecked(date, 0);
    }

    public Date yesterday(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        return calendar.getTime();
    }


    // 获取昨天的刷题数
    public Integer getSolvedProblemOfYesterday(String username, String today) throws ParseException {

        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = fmt.format(yesterday(fmt.parse(today)));

        CheckDayInfo checkDayInfo = checkDayInfoRepository.findByUsernameAndDate(username, yesterday);

        if (checkDayInfo != null && checkDayInfo.getSolvedQuestion() != null) {
            return checkDayInfo.getSolvedQuestion();
        }
        return 0;

    }
}
