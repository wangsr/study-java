package org.wsr.stu.javabeans.naming;

/**
 * javabean属性命名
 * <pre>
 * 1. javabean属性命名尽量使用常规的驼峰式命名规则
 * 2. 属性名第一个单词尽量避免使用一个字母：如eBook， eMail。
 * 3. boolean属性名避免使用 “is” 开头的名称
 * </pre>
 * Created by wangshengren on 16/10/2.
 */
public class NameDemo {
    //1、常规的驼峰命名
    private String goodBoy;

    public String getGoodBoy() {
        return goodBoy;
    }

    public void setGoodBoy(String goodBoy) {
        this.goodBoy = goodBoy;
    }

    //2、避免单字母开头
    private String eBook;

    public String geteBook() {
        return eBook;
    }

    public void seteBook(String eBook) {
        this.eBook = eBook;
    }
    //2、避免单字母开头
    private String EMail;

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    //3、boolean 不用is开始
    private boolean isGood;
    private boolean readable;

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

}
