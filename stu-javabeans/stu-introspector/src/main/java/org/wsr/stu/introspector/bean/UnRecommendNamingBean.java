package org.wsr.stu.introspector.bean;

/**
 * 不推荐的命名方式(在运用"内省"的时候,容易产生错误)
 * Created by wangshengren on 16/10/2.
 */
public class UnRecommendNamingBean {
    //单个字母开头
    private String pName;
    //is开头的boolean
    private boolean isGood;
    //正常的命名
    private String age;

    public String getAge() {
        return age;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
