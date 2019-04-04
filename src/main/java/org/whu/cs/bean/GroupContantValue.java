package org.whu.cs.bean;

/**
 * 预定义的全局变量
 */
public class GroupContantValue {

    // 是否打卡
    private static final int checked = 1;
    private static final int not_checked = 0;

    // 记录开始时间（即数据库里开始有记录的时间）
    private static final String start_of_date = "2019-03-17";

    // 用户状态
    private static final int normal = 0;
    private static final int deleted = 1;
    private static final int locked = 2;


    /**
     * Gets checked.
     *
     * @return the checked
     */
    public static int getChecked() {
        return checked;
    }

    /**
     * Gets not checked.
     *
     * @return the not checked
     */
    public static int getNot_checked() {
        return not_checked;
    }

    /**
     * Gets start of date.
     *
     * @return the start of date
     */
    public static String getStart_of_date() {
        return start_of_date;
    }

    /**
     * Gets normal.
     *
     * @return the normal
     */
    public static int getNormal() {
        return normal;
    }

    /**
     * Gets deleted.
     *
     * @return the deleted
     */
    public static int getDeleted() {
        return deleted;
    }

    /**
     * Gets locked.
     *
     * @return the locked
     */
    public static int getLocked() {
        return locked;
    }
}
