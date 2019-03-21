package org.whu.cs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.whu.cs.bean.CheckDayInfo;
import org.whu.cs.bean.Upvote;
import org.whu.cs.repository.CheckDayInfoRepository;
import org.whu.cs.repository.UpvoteRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UpvoteService {
    @Autowired
    private UpvoteRepository upvoteRepository;

    @Autowired
    private CheckDayInfoRepository checkDayInfoRepository;

    /**
     * 根据被点赞的人的id 和 日期 和 状态 查询数据
     *
     * @param toMemberId 被点赞的人的id
     * @param date 日期
     * @param status 状态
     * @return
     */
    public List<Upvote> getUpvoteListByToAndDate(String toMemberId, String date,int status) {
        return upvoteRepository.findByToMemberIdAndDateAndStatus(toMemberId, date,status);
    }

    /**
     * 根据点赞人的id 和 日期 和 状态 查询数据
     * @param fromMemberId 点赞人Id
     * @param date   日期
     * @param status 状态
     * @return
     */
    public List<Upvote> getUpvoteListByFromAndDate(String fromMemberId,String date,int status){
        return upvoteRepository.findByFromMemberIdAndDateAndStatus(fromMemberId,date,status);
    }

    /**
     * 统计被点赞人的收到的点赞数
     * @param toMemberId 被点赞的人的id
     * @param date 日期
     * @param status 状态
     * @return
     */
    public Integer getUpvoteCountByToAndDate(String toMemberId, String date,int status) {
        return upvoteRepository.countByToMemberIdAndDateAndStatus(toMemberId, date,status);
    }

    /**
     * 点赞或者取消点赞
     * @param fromMemberId 点赞人Id
     * @param toMemberId 被点赞人Id
     * @return 更新后或者插入后的数据
     */

    public Upvote UpvoteOrUnUpvote(String fromMemberId,String toMemberId){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");  //设置日期格式
        Date today=new Date();
        String date= simpleDateFormat.format(today);
        Upvote upvote = upvoteRepository.findByFromMemberIdAndToMemberIdAndDate(fromMemberId,toMemberId,date);
        CheckDayInfo checkDayInfo= checkDayInfoRepository.findByUsernameAndDate(toMemberId,date);
        if(upvote==null){
            Upvote newUpvote=new Upvote();
            newUpvote.setFromMemberId(fromMemberId);
            newUpvote.setToMemberId(toMemberId);
            newUpvote.setGmt_create(today);
            newUpvote.setGmt_modified(today);                    //点赞
            newUpvote.setStatus(1);
            newUpvote.setDate(date);
            checkDayInfo.setUpvoteNumber(checkDayInfo.getUpvoteNumber()+1);
            checkDayInfoRepository.save(checkDayInfo);
            return upvoteRepository.save(newUpvote);
        }else{
            if (upvote.getStatus() == 1) {                        //取消点赞
                upvote.setStatus(0);
                upvote.setGmt_modified(today);
                checkDayInfo.setUpvoteNumber(checkDayInfo.getUpvoteNumber()-1);
            } else {
                upvote.setGmt_modified(today);                    //恢复点赞
                upvote.setStatus(1);
                checkDayInfo.setUpvoteNumber(checkDayInfo.getUpvoteNumber()+1);
            }
            checkDayInfoRepository.save(checkDayInfo);
            return upvoteRepository.save(upvote);
        }
    }

}
