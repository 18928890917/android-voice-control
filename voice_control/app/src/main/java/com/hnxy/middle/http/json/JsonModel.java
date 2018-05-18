package com.hnxy.middle.http.json;

import com.jingyu.utils.json.JsonBean;
import com.hnxy.middle.http.IHttpRespInfo;

/**
 * @author fengjingyu@foxmail.com
 * @description
 */
public class JsonModel extends JsonBean implements IHttpRespInfo {

    @Override
    public String getCode() {
        return getString("code");
    }

    @Override
    public String getMessage() {
        return getString("message");
    }
}
