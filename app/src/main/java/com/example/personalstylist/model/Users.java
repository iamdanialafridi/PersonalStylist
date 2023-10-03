package com.example.personalstylist.model;

import androidx.annotation.NonNull;

public class Users {
    String Uid,Name,Email,Phone,Address,Password;

    public Users() {
    }

    public Users(@NonNull String uid, String name, String email, String phone, String address, String password) {
        Uid = uid;
        Name = name;
        Email = email;
        Phone = phone;
        Address = address;
        Password = password;
    }

    public String getCid() {
        return Uid;
    }

    public void setCid(String uid) {
        Uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
