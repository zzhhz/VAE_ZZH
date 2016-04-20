package com.zzh.vae.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by ZZH on 16/4/19.
 */
public class Users implements Parcelable {
    private String userId;
    private String userName;
    private int userAge;
    private int userSex;
    private Date userBirthday;
    public String a;


    public Users(){
    }
    public Users(String userName){
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.userName);
        dest.writeInt(this.userAge);
        dest.writeInt(this.userSex);
        dest.writeLong(userBirthday != null ? userBirthday.getTime() : -1);
    }

    protected Users(Parcel in) {
        this.userId = in.readString();
        this.userName = in.readString();
        this.userAge = in.readInt();
        this.userSex = in.readInt();
        long tmpUserBirthday = in.readLong();
        this.userBirthday = tmpUserBirthday == -1 ? null : new Date(tmpUserBirthday);
    }

    public static final Parcelable.Creator<Users> CREATOR = new Parcelable.Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel source) {
            return new Users(source);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };
}
