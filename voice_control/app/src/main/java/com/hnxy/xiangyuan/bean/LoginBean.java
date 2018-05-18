package com.hnxy.xiangyuan.bean;

import java.io.Serializable;

/**
 * Created by xilinch on 2017/4/24.
 */

public class LoginBean implements Serializable {


    /**
     * code : 40001
     * message : 恭喜，成功！
     * data : {"userId":"string","userToken":"string"}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         *
         * lastLoginTime (string): 上次登录时间 ,
         loginName (string): 用户账户 ,
         userChName (string): 用户中文名 ,
         userId (integer): 用户id ,
         userLogo (string): 用户logo ,
         userToken (string): 用户token
         *
         * userId : string
         * userToken : string
         */

        private String userChName;
        private String userLogo;
        private String userId;
        private String userToken;



        public String getUserChName() {
            return userChName;
        }

        public void setUserChName(String userChName) {
            this.userChName = userChName;
        }


        public String getUserLogo() {
            return userLogo;
        }

        public void setUserLogo(String userLogo) {
            this.userLogo = userLogo;
        }


        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserToken() {
            return userToken;
        }

        public void setUserToken(String userToken) {
            this.userToken = userToken;
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
