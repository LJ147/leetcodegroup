package org.whu.cs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.whu.cs.bean.Upvote;

import java.util.List;

/**
 * 点赞接口 interface
 */
public interface UpvoteRepository extends JpaRepository<Upvote, String> {

    /**
     * 根据被点赞的人的id 和 日期 和 状态 查询数据
     * @param toMemberId 被点赞的人的id
     * @param date 日期
     * @param status 状态
     * @return
     */
    List<Upvote> findByToMemberIdAndDateAndStatus(String toMemberId, String date, int status);

    /**
     * 根据点赞人的id 和 日期 和 状态 查询数据
     * @param fromMemberId 点赞人的id
     * @param date 日期
     * @param status 状态
     * @return
     */

    List<Upvote> findByFromMemberIdAndDateAndStatus(String fromMemberId, String date, int status);

    /**
     *  统计被点赞人的收到的点赞数
     * @param toMemberId 被点赞人Id
     * @param date 日期
     * @param status 状态
     * @return
     */

    Integer countByToMemberIdAndDateAndStatus(String toMemberId, String date, int status);

    /**
     * 根据点赞人Id 被点赞人Id 日期 查询数据
     * @param fromMemberId 点赞人Id
     * @param toMemberId 被点赞人Id
     * @param date 日期
     * @return
     */

    Upvote findByFromMemberIdAndToMemberIdAndDate(String fromMemberId, String toMemberId, String date);

    /**
     * 更新或者插入一条点赞信息
     * @param upvote 点赞信息实体
     * @return 更新后或者插入后的数据
     */
    Upvote save(Upvote upvote);

}
