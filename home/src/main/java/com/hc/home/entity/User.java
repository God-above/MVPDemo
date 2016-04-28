package com.hc.home.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by hc on 2016/4/26.
 */
public class User extends BmobUser {
    private double id;
    private double areaId;
    private String areaName;
    private int registerType;
    private int sex;//0 女 1男

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String nickName;


    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getAreaId() {
        return areaId;
    }

    public void setAreaId(double areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getRegisterType() {
        return registerType;
    }

    public void setRegisterType(int registerType) {
        this.registerType = registerType;
    }
}
