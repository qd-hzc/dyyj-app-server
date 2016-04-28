package com.hzc.framework.ssh.controller.validate.anno;

/**
 * @author yinbin
 */
public enum RegexpType {

    ALL(".*", 0), NUMBER("^[0-9]+\\.{0,1}[0-9]{0,2}$", 1), EMAIL(
            "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", 2), HTTP_URL(
            "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$", 3), PHONE("^((\\d{0})|(\\d{3,4}(-)?)|(\\d{3,4}(-)?))?\\d{7,8}$", 4), IDCARD(
            "^\\d{15}|\\d{18}$", 5), ZH_CN("[\u4e00-\u9fa5]", 6), EMPTY("(^\\s*)|(\\s*$)", 7), US_EN("^[A-Za-z]+$", 8), USERNAME("^[\\u4e00-\\u9fa5A-Za-z0-9\\._\\-]{5,20}$", 9), PASSWORD("[^\\u4e00-\\u9fa5]{6,30}", 10), COMPANYID("^[\\u4e00-\\u9fa5A-Za-z0-9\\._\\-]{5,40}$", 11);
    // 成员变量
    private String name;// 正则
    private int index;// 下标

    // 构造方法
    private RegexpType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (RegexpType c : RegexpType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
