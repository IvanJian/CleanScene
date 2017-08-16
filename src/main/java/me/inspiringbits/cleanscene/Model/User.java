package me.inspiringbits.cleanscene.Model;

import java.util.Date;
import java.util.List;

/**
 * Created by Abdulkareem on 2017/8/7.
 */
public class User {
    public Integer userId;
    public String Username;
    public String FName;
    public String LName;
    public Integer PhoneNumber;
    public Integer  Password;
    public String Address;
    public String dateOfBirth;
    public String Email;
    public String DateJoined;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }
}
