package com.example.vodokanalmainactivity.data.preferences;

import android.content.SharedPreferences;

import com.example.vodokanalmainactivity.Constants;

public class DataPreferences {
    private final SharedPreferences sharedPreferences;

    public DataPreferences(SharedPreferences preferences) {
        this.sharedPreferences = preferences;

    }
   public String getPinCode(){
        return sharedPreferences.getString(Constants.PIN_CODE_VALUE,null);
    }
    public void setPinCode(String code){
        SharedPreferences.Editor editor = sharedPreferences.edit();
       editor.putString(Constants.PIN_CODE_VALUE,code);
       editor.apply();
    }
    public String getLogin(){
        return sharedPreferences.getString(Constants.LOGIN_VALUE,null);
    }
    public void setLogin(String login){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.LOGIN_VALUE,login);
        editor.apply();
    }
    public String getPassword(){
        return sharedPreferences.getString(Constants.PASS_VALUE,null);
    }
    public void setPassword(String password){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.PASS_VALUE,password);
        editor.apply();
    }
}
