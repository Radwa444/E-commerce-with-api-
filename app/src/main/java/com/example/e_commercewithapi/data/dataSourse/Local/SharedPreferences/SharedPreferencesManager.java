package com.example.e_commercewithapi.data.dataSourse.Local.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
public static String E_COMMERCE_PREFERCES="e_commerce_preference";
public static String TOKEN="token";
public boolean is_login=false;
private final SharedPreferences sharedPreferences;
private final SharedPreferences.Editor editor ;

public SharedPreferencesManager(SharedPreferences sharedPreferences){
    this.sharedPreferences=sharedPreferences;
    this.editor=sharedPreferences.edit();
}
public void setToken(String is_token){
    editor.putString(TOKEN,is_token);
    editor.apply();
}
public String getToken(){
    return sharedPreferences.getString(TOKEN,null);
}
public boolean checkLogin(String token){
    token=getToken();
    if(token==null) {
        return false;
    }else return true;
}

}
