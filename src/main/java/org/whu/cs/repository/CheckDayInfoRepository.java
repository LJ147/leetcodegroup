package org.whu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.whu.cs.bean.CheckDayInfo;

import java.util.List;


public interface CheckDayInfoRepository extends JpaRepository<CheckDayInfo, Long> {
    List<CheckDayInfo> findAllByDate(String date);

    @Query(value = "select * from CheckDayInfo info where info.date = ?1 ORDER by checked ASC, solvedQuestion DESC ", nativeQuery = true)
    List<CheckDayInfo> findByDate(String date);

    Integer countAllByDate(String date);

    Integer countAllByDateAndChecked(String date, int checked);

    List<CheckDayInfo> findByUsername(String userName);

    CheckDayInfo findByUsernameAndDate(String username,String data);
}
