package org.whu.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whu.cs.bean.CheckDayInfo;
import org.whu.cs.repository.CheckDayInfoRepository;

import java.util.List;

@Service
public class CheckDayInfoService {
    @Autowired
    private CheckDayInfoRepository checkDayInfoRepository;

    public List<CheckDayInfo> checkDayInfos(String date) {
        return checkDayInfoRepository.findByDate(date);

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

}
