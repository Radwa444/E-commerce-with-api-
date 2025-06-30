package com.example.e_commercewithapi.data.dataSourse.Local.SharedPreferences;

import android.content.SharedPreferences;

import com.example.e_commercewithapi.utils.Config;
public class SharedPreferencesManager {
    private final SharedPreferences sharedPreferences;
private final SharedPreferences.Editor editor ;

public SharedPreferencesManager(SharedPreferences sharedPreferences){
    this.sharedPreferences=sharedPreferences;
    this.editor=sharedPreferences.edit();
}
public void setToken(String is_token){
    editor.putString(Config.TOKEN,is_token);
    editor.apply();
}
public String getToken(){
    return sharedPreferences.getString(Config.TOKEN,null);
}
public void setRefreshToken(String refresh_token){
    editor.putString(Config.REFRESH_TOKEN,refresh_token);
    editor.apply();
}
public String getRefreshToken(){
    return sharedPreferences.getString(Config.REFRESH_TOKEN,null);
}
public boolean checkLogin(String token){
    token=getToken();
    return token != null;
}


}
