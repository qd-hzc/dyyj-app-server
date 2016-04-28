package com.hzc.top.model;

import com.hzc.top.util.AppDesUtil;

public class EnMaterialWithBLOBs extends EnMaterial {
    private String name;

    private String fanyi;

    public String getName() {
//        return name;
        return AppDesUtil.jiaMi(this.name);
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFanyi() {
        return fanyi;
    }

    public void setFanyi(String fanyi) {
        this.fanyi = fanyi == null ? null : fanyi.trim();
    }

    @Override
    public String toString() {
        return "EnMaterialWithBLOBs{" +
                "name='" + name + '\'' +
                ", fanyi='" + fanyi + '\'' +
                '}';
    }
}