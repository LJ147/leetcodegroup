package org.whu.cs.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户点赞信息
 */
@Entity
public class Upvote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long upvoteID;

    // 点赞用户
    private String fromMemberId;
    // 被点赞用户
    private String toMemberId;
    // 记录创建时间
    private Date gmt_create;
    // 记录修改时间
    private Date gmt_modified;
    // 点赞状态 0-未点赞 1-已点赞
    private int status;
    // 点赞日期，格式 yyyy-MM-dd
    private String date;

    /**
     * Gets upvote id.
     *
     * @return the upvote id
     */
    public Long getUpvoteID() {
        return upvoteID;
    }

    /**
     * Sets upvote id.
     *
     * @param upvoteID the upvote id
     */
    public void setUpvoteID(Long upvoteID) {
        this.upvoteID = upvoteID;
    }

    /**
     * Gets from member id.
     *
     * @return the from member id
     */
    public String getFromMemberId() {
        return fromMemberId;
    }

    /**
     * Sets from member id.
     *
     * @param fromMemberId the from member id
     */
    public void setFromMemberId(String fromMemberId) {
        this.fromMemberId = fromMemberId;
    }

    /**
     * Gets to member id.
     *
     * @return the to member id
     */
    public String getToMemberId() {
        return toMemberId;
    }

    /**
     * Sets to member id.
     *
     * @param toMemberId the to member id
     */
    public void setToMemberId(String toMemberId) {
        this.toMemberId = toMemberId;
    }

    /**
     * Gets gmt create.
     *
     * @return the gmt create
     */
    public Date getGmt_create() {
        return gmt_create;
    }

    /**
     * Sets gmt create.
     *
     * @param gmt_create the gmt create
     */
    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets date.
     *
     * @reture date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets gmt_modified.
     *
     * @reture gmt_modified
     */

    public Date getGmt_modified() {
        return gmt_modified;
    }

    /**
     * Sets gmt_modified.
     *
     * @param gmt_modified the modifier date
     */
    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }
}
