package org.whu.cs.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 用户基础信息
 */
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    // 用户名
    private String username;

    // 用户url
    private String url;

    // 用户状态
    private int status;

    // 记录创建时间
    private Date gmt_create;

    // 记录修改时间
    private Date gmt_modified;

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
     * Gets gmt modified.
     *
     * @return the gmt modified
     */
    public Date getGmt_modified() {
        return gmt_modified;
    }

    /**
     * Sets gmt modified.
     *
     * @param gmt_modified the gmt modified
     */
    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
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
     * Gets member id.
     *
     * @return the member id
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * Sets member id.
     *
     * @param memberId the member id
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", username='" + username + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", gmt_create=" + gmt_create +
                ", gmt_modified=" + gmt_modified +
                '}';
    }
}
