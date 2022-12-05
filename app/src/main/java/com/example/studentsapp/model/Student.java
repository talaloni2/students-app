package com.example.studentsapp.model;

public class Student {
    String id;
    String name;
    boolean isChecked;
    String phone;
    String address;
    boolean deleted;

    public Student(String id, String name, boolean isChecked, String phone, String address) {
        this.id = id;
        this.name = name;
        this.isChecked = isChecked;
        this.phone = phone;
        this.address = address;
        this.deleted = false;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
