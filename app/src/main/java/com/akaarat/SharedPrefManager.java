package com.akaarat;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ahmed on 19/12/2018.
 */

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
        private static Context mCtx;
    private static final String SHARED_PREF_NAME = "LoginUser";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean saveUserToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_token", token);
        editor.apply();
        return true;
    }

    public String getUserToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString("user_token", null);
    }
    public boolean saveClintid(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("CLINTID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("clintid", token);
        editor.apply();
        return true;
    }

    public String getClintid(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("CLINTID", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("clintid", null);
    }

    public boolean saveUserType(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("TYPE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("usertype", token);
        editor.apply();
        return true;
    }

    public String getUserType(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("TYPE", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("usertype", null);
    }

    public boolean saveUserID(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("ID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userid", token);
        editor.apply();
        return true;
    }

    public String getUserID(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("ID", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("userid", null);
    }


}
