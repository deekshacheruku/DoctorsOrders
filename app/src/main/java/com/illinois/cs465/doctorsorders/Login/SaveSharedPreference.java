package com.illinois.cs465.doctorsorders.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class SaveSharedPreference
{
    //assume username is for the scheduler
    static final String PREF_USER_NAME= "username";
    static final String PREF_PATIENT_NAME = "patient_name";
    static final String KEY_USER_NAME_AFTER_LOGIN = "after_login_username";

//    static final Bundle CREATE_ACCOUNT_0_TO_DASHBOARD_BUNDLE = "acct_0_bundle";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static void setPatientName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PATIENT_NAME, userName);
        editor.commit();
    }

    public static void setUserNameAfterLogin(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(KEY_USER_NAME_AFTER_LOGIN, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getPatientName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_PATIENT_NAME, "");
    }

    public static String getUserNameAfterLogin(Context ctx) { // To retrieve data
        return getSharedPreferences(ctx).getString(KEY_USER_NAME_AFTER_LOGIN, "");
    }

    public static void clearUserName(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }

    public static void clearPatientName(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }
}
