package com.bit.sfa.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rashmi on 3/11/2019.
 */

public class User implements Serializable {
    String Name, UserName, Password, Mobile, Address, Route, Status, Code;

    public User(){

    }

    public User(String name, String username, String password, String mobile, String address, String route, String status, String code){
        this.Name = name;
        this.UserName = username;
        this.Password = password;
        this.Mobile = mobile;
        this.Address = address;
        this.Route = route;
        this.Status = status;
        this.Code = code;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public static User parseUser(JSONObject instance) throws JSONException, NumberFormatException {

        if(instance != null) {
            User user = new User();
            user.setCode(instance.getString("RepCode"));
            user.setName(instance.getString("RepName"));
            user.setUserName("UserName");
            user.setPassword("Password");
            user.setRoute(instance.getString("RouteCode"));
            user.setMobile(instance.getString("Mobile"));
            user.setAddress(instance.getString("Address"));
            user.setStatus(instance.getString("Status"));
            return user;
        }

        return null;
    }

}
