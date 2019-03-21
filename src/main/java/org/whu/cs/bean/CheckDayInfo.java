package org.whu.cs.bean;


import javax.persistence.*;


@Entity
@Table(name = "CheckDayInfo")

public class CheckDayInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 信息id
    private Long infoId;
    // 个人LeetCode主页地址，eg：https://leetcode.com/alexlj/
    private String address;
    // 用户名，eg： alexlj
    private String username;
    // 头像url
    private String avatar;
    // 个人主页
    private String website;
    // 提交通过率
    private Integer acceptanceRate;
    // 查卡天数 格式 '2019-03-01'
    private String date;
    private Integer checkDaysInTheLastYear;
    // 是否打卡 0：已打卡 1： 未打卡
    private Integer checked;
    private String updateTime;
    private Integer solvedProblemNumberOfToday;
    private Integer submissionOfToday;
    private Integer submissionCount;
    // 刷过的题
    private Integer solvedQuestion;
    private Integer acceptedSubmission;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }
    @Column(columnDefinition="int default 0")
    private int upvoteNumber=0;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getAcceptanceRate() {
        return acceptanceRate;
    }

    public void setAcceptanceRate(Integer acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCheckDaysInTheLastYear() {
        return checkDaysInTheLastYear   ;
    }

    public void setCheckDaysInTheLastYear(Integer checkDaysInTheLastYear) {
        this.checkDaysInTheLastYear = checkDaysInTheLastYear;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSolvedProblemNumberOfToday() {
        return solvedProblemNumberOfToday;
    }

    public void setSolvedProblemNumberOfToday(Integer solvedProblemNumberOfToday) {
        this.solvedProblemNumberOfToday = solvedProblemNumberOfToday;
    }

    public Integer getSubmissionOfToday() {
        return submissionOfToday;
    }

    public void setSubmissionOfToday(Integer submissionOfToday) {
        this.submissionOfToday = submissionOfToday;
    }

    public Integer getSubmissionCount() {
        return submissionCount;
    }

    public void setSubmissionCount(Integer submissionCount) {
        this.submissionCount = submissionCount;
    }

    public Integer getSolvedQuestion() {
        return solvedQuestion;
    }

    public void setSolvedQuestion(Integer solvedQuestion) {
        this.solvedQuestion = solvedQuestion;
    }

    public Integer getAcceptedSubmission() {
        return acceptedSubmission;
    }

    public void setAcceptedSubmission(Integer acceptedSubmission) {
        this.acceptedSubmission = acceptedSubmission;
    }

    @Override
    public String toString() {
        return "CheckDayInfo{" +
                "username='" + username + '\'' +
                ", date='" + date + '\'' +
                ", checkDaysInTheLastYear=" + checkDaysInTheLastYear +
                ", checked='" + checked + '\'' +
                '}';
    }

    public int getUpvoteNumber() {
        return upvoteNumber;
    }

    public void setUpvoteNumber(int upvoteNumber) {
        this.upvoteNumber = upvoteNumber;
    }
}
