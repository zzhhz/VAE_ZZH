package com.zzh.retrofit.bean;

import java.util.List;

public class HospitalAndDepartment {
    private List<List<String>> hospitals;
    private List<List<String>> departments;

    public List<List<String>> getHospitals() {
        return hospitals;
    }

    public void setHospitals(List<List<String>> hospitals) {
        this.hospitals = hospitals;
    }

    public List<List<String>> getDepartments() {
        return departments;
    }

    public void setDepartments(List<List<String>> departments) {
        this.departments = departments;
    }
}
