package com.zzh.vae.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/3.
 */
public class DoctorMsg implements Serializable {
    private String specialistId; //id
    private String iconUrl; //专家头像
    private String specialistName; // 专家姓名
    private String specialistDepartment; //所在科室
    private String specialistLevel; //主任医师
    private String specialistHospital; //所在医院
    private String specialistStrongth; //擅长
    private double specialistStar; //评级
    private String specialistIntroduce; //简介
    private boolean isCollect = true; //是否收藏, 只能在我的关注中使用
    //搜索历史聊天记录的时候，高亮显示搜索关键字
    private int lightStart = -1;
    private int lightEnd = -1;

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getSpecialistDepartment() {
        return specialistDepartment;
    }

    public void setSpecialistDepartment(String specialistDepartment) {
        this.specialistDepartment = specialistDepartment;
    }

    public String getSpecialistLevel() {
        return specialistLevel;
    }

    public void setSpecialistLevel(String specialistLevel) {
        this.specialistLevel = specialistLevel;
    }

    public String getSpecialistHospital() {
        return specialistHospital;
    }

    public void setSpecialistHospital(String specialistHospital) {
        this.specialistHospital = specialistHospital;
    }

    public String getSpecialistStrongth() {
        return specialistStrongth;
    }

    public void setSpecialistStrongth(String specialistStrongth) {
        this.specialistStrongth = specialistStrongth;
    }

    public double getSpecialistStar() {
        return specialistStar;
    }

    public void setSpecialistStar(double specialistStar) {
        this.specialistStar = specialistStar;
    }

    public String getSpecialistIntroduce() {
        return specialistIntroduce;
    }

    public void setSpecialistIntroduce(String specialistIntroduce) {
        this.specialistIntroduce = specialistIntroduce;
    }

    public int getLightStart() {
        return lightStart;
    }

    public void setLightStart(int lightStart) {
        this.lightStart = lightStart;
    }

    public int getLightEnd() {
        return lightEnd;
    }

    public void setLightEnd(int lightEnd) {
        this.lightEnd = lightEnd;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }
}
