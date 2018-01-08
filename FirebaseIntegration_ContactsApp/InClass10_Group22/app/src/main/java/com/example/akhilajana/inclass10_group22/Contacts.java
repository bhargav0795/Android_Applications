package com.example.akhilajana.inclass10_group22;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by akhilajana on 11/13/17.
 */

public class Contacts implements Serializable {


    String name;
    String email;
    String phone;
    String department;
    String image;
    String hashKey;

    public Contacts() {
    }

    public Contacts(String name, String email, String phone, String department,String image) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department='" + department + '\'' +
                ", hashKey='" + hashKey + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        result.put("phone", phone);
        result.put("department", department);
        result.put("image",image);
        result.put("hashKey", hashKey);
        return result;
    }
}
