package com.hnxy.xiangyuan.bean;

import com.hnxy.middle.http.IHttpRespInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Wayne on 2018/2/3.
 * @modifier Wayne 2018/2/3 11:35.
 * @description
 */
public class UpdateBean implements IHttpRespInfo,Serializable {

    /***
     *
     * JsonResultOut«VersionUpdateDTO» {
     code (string): 状态码 ,
     message (string): 返回消息 ,
     data (VersionUpdateDTO): 具体数据
     }VersionUpdateDTO {
     appType (integer): 设备类型 1-光伏Android 2-光伏iOS ,
     appVersion (string): 服务器最新版本号 ,
     forceUpdate (boolean): 是否强制更新(false：非强制更新 true：强制更新) ,
     isLatestVersion (integer): 是否是最新版本(0不是 1是) ,
     message (string): 提示语 ,
     updateDec (string): 更新说明 ,
     updatePic (Array[Map«string,string»]): 图片说明，有更新显示最新的图片介绍，没有则显示当前版本图片，返回三张即可 ,
     updateTime (string): 更新时间 ,
     url (string): 更新时间
     }Map«string,string» {}
     *
     */
    private String code;
    private String message;
    private UpdateBean.VersionUpdateDTO data;

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

    public UpdateBean.VersionUpdateDTO getData() {
        return data;
    }

    public void setData(UpdateBean.VersionUpdateDTO data) {
        this.data = data;
    }

    public static class VersionUpdateDTO  {
        /**
         *
         * VersionUpdateDTO {
         appType (integer): 设备类型 1-光伏Android 2-光伏iOS ,
         appVersion (string): 服务器最新版本号 ,
         forceUpdate (boolean): 是否强制更新(false：非强制更新 true：强制更新) ,
         isLatestVersion (integer): 是否是最新版本(0不是 1是) ,
         message (string): 提示语 ,
         updateDec (string): 更新说明 ,
         updatePic (Array[Map«string,string»]): 图片说明，有更新显示最新的图片介绍，没有则显示当前版本图片，返回三张即可 ,
         updateTime (string): 更新时间 ,
         url (string): 更新时间
         }Map«string,string» {}
         *
         */
        private int appType;
        private String appVersion;
        private boolean forceUpdate;
        private int isLatestVersion;
        private String message;
        private String updateDec;
        private String updateTime;
        private String url;
        private ArrayList<Map<String,String>> updatePic;

        public int getAppType() {
            return appType;
        }

        public void setAppType(int appType) {
            this.appType = appType;
        }
        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }
        public boolean getForceUpdate() {
            return forceUpdate;
        }

        public void setForceUpdate(boolean forceUpdate) {
            this.forceUpdate = forceUpdate;
        }

        public int getIsLatestVersion() {
            return isLatestVersion;
        }

        public void setIsLatestVersion(int isLatestVersion) {
            this.isLatestVersion = isLatestVersion;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getUpdateDec() {
            return updateDec;
        }

        public void setUpdateDec(String updateDec) {
            this.updateDec = updateDec;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public ArrayList<Map<String,String>> getUpdatePic() {
            return updatePic;
        }

        public void setUpdatePic(ArrayList<Map<String,String>> updatePic) {
            this.updatePic = updatePic;
        }

    }

}
