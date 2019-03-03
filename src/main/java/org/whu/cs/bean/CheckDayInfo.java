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
    private int acceptanceRate;
    // 查卡天数 格式 '2019-03-01'
    private String date;
    private int checkDaysInTheLastYear;
    // 是否打卡 0：已打卡 1： 未打卡
    private int checked;
    private String updateTime;
    private int solvedProblemNumberOfToday;
    private int submissionOfToday;
    private int submissionCount;
    // 刷过的题
    private int solvedQuestion;
    private int acceptedSubmission;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getAcceptanceRate() {
        return acceptanceRate;
    }

    public void setAcceptanceRate(int acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    public int getAcceptedSubmission() {
        return acceptedSubmission;
    }

    public void setAcceptedSubmission(int acceptedSubmission) {
        this.acceptedSubmission = acceptedSubmission;
    }

    public int getSolvedQuestion() {
        return solvedQuestion;
    }

    public void setSolvedQuestion(int solvedQuestion) {
        this.solvedQuestion = solvedQuestion;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSubmissionCount() {
        return submissionCount;
    }

    public void setSubmissionCount(int submissionCount) {
        this.submissionCount = submissionCount;
    }

    public int getSolvedProblemNumberOfToday() {
        return solvedProblemNumberOfToday;
    }

    public void setSolvedProblemNumberOfToday(int solvedProblemNumberOfToday) {
        this.solvedProblemNumberOfToday = solvedProblemNumberOfToday;
    }

    public int getSubmissionOfToday() {
        return submissionOfToday;
    }

    public void setSubmissionOfToday(int submissionOfToday) {
        this.submissionOfToday = submissionOfToday;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCheckDaysInTheLastYear() {
        return checkDaysInTheLastYear;
    }

    public void setCheckDaysInTheLastYear(int checkDaysInTheLastYear) {
        this.checkDaysInTheLastYear = checkDaysInTheLastYear;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
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
}
