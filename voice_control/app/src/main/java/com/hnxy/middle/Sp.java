package com.hnxy.middle;

import android.app.Application;
import android.text.TextUtils;
import android.util.Base64;

import com.jingyu.utils.function.SPHelper;



/**
 * @author fengjingyu@foxmail.com
 */
public class Sp {
    private Sp() {
    }

    public static final String SP_FILE_NAME = "sp_file";

    public static final String USER_NAME = "userName";
    public static final String USER_ID = "userId";
    public static final String USER_TOKEN = "userToken";
    public static final String IS_LOGIN = "isLogin";
    public static final String USER_HEAD = "userLogo";
    public static final String USER_ChName = "userChName";
    public static final String USER_PHONE_NUM = "userPhoneNum";
    public static final String IS_INSTALL = "isInstall";
    public static final String USER_PSD = "userPsd";
    public static final String USER_LIST = "userList";
    public static final String IS_REMEMBER_PSW = "isRememberPsw";


    private static SPHelper spHelper;

    public static void initSP(Application application) {
        spHelper = new SPHelper(application, SP_FILE_NAME);
    }

    public static String getUserId() {
        return spHelper.spGet(USER_ID, "");
    }

    public static String getUserToken() {
        return spHelper.spGet(USER_TOKEN, "");
    }

    public static String getUserName() {
        return spHelper.spGet(USER_NAME, "");
    }

    public static String getUserHead() {
        return spHelper.spGet(USER_HEAD, "");
    }

    public static String getUserPhoneNum() {
        return spHelper.spGet(USER_PHONE_NUM, "");
    }

    public static boolean isLogin() {
        return spHelper.spGet(IS_LOGIN, false);
    }

    public static boolean isRememberPsw() {
        return spHelper.spGet(IS_REMEMBER_PSW, false);
    }

    public static boolean isFirstInstallApp() {
        return spHelper.spGet(IS_INSTALL, true);
    }

    public static void setUserId(String userId) {
        spHelper.spPut(USER_ID, userId);
    }

    public static void setUserToken(String userToken) {
        spHelper.spPut(USER_TOKEN, userToken);
    }

    public static void setUserName(String userName) {
        spHelper.spPut(USER_NAME, userName);
    }

    public static void setUserHeader(String userHeader) {
        spHelper.spPut(USER_HEAD, userHeader);
    }
    public static void setUserChName(String userChName) {
        spHelper.spPut(USER_ChName, userChName);
    }

    public static void setUserPhoneNum(String userPhoneNum) {
        spHelper.spPut(USER_PHONE_NUM, userPhoneNum);
    }

    public static void setLogin(boolean isLogin) {
        spHelper.spPut(IS_LOGIN, isLogin);
    }

    public static void setIsRememberPsw(boolean isRemember) {
        spHelper.spPut(IS_REMEMBER_PSW, isRemember);
    }

    public static void setFirstInstallApp(boolean value) {
        spHelper.spPut(IS_INSTALL, value);
    }

    public static void setPSD(String psd){
        if(!TextUtils.isEmpty(psd)){
            psd = Base64.encodeToString(psd.getBytes(),Base64.NO_WRAP);
        }
        spHelper.spPut(USER_PSD, psd);
    }
    public static String getPSD(){
        String psd = spHelper.spGet(USER_PSD,"");
        if(!TextUtils.isEmpty(psd)){
            psd = new String(Base64.decode(psd.getBytes(), Base64.NO_WRAP));
        }
        return psd;
    }
    public static void setUserList(String userList){
        spHelper.spPut(USER_LIST, userList);
    }
    public static String getUserList(){
        String psd = spHelper.spGet(USER_LIST,"");
        return psd;
    }



}
