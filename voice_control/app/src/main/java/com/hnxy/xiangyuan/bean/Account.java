package com.hnxy.xiangyuan.bean;

import android.text.TextUtils;
import android.util.Base64;

import com.hnxy.middle.Sp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xilinch on 2017/5/20.
 */

public class Account implements Serializable{

    private String account;

    private String pwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account1 = (Account) o;

        if (getAccount() != null && getAccount().equals(account1.getAccount()))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = getAccount() != null ? getAccount().hashCode() : 0;
        result = 31 * result + (getPwd() != null ? getPwd().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public JSONObject toJsonObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("account",account);
            jsonObject.put("pwd",pwd);
        }catch (Exception e){

        }
        return jsonObject;
    }

    /**
     * 加密
     * @param str
     * @return
     */
    private static String encry(String str){
        String result = str;
        if(!TextUtils.isEmpty(str)){
            result = Base64.encodeToString(str.getBytes(),Base64.NO_WRAP);
        }
        return result;
    }
    /**
     * 解密
     * @param str
     * @return
     */
    private static String decry(String str){
        String result = str;
        if(!TextUtils.isEmpty(str)){
            result = new String(Base64.decode(result.getBytes(), Base64.NO_WRAP));
        }
        return result;
    }

    /**
     * 保存到本地
     * @param list
     */
    public static void save2LocalUserList(List<Account> list){
        String saveString = "";
        try{
            JSONArray jsonArray = new JSONArray();
            if(list != null){
                int size = list.size();
                for(int i = 0; i < size; i++){
                    Account account = list.get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("pwd",encry(account.getPwd()));
                    jsonObject.put("account",account.getAccount());
                    jsonArray.put(jsonObject);
                }
            }
            saveString = jsonArray.toString();
            Sp.setUserList(saveString);
        } catch (Exception exception) {
          exception.printStackTrace();
        }
    }

    /**
     * 从本地读取出来
     * @return
     */
    public static List<Account> getUserListFromLocal(){
        String saveString = Sp.getUserList();
        List arrayList = new ArrayList();
        try{
            JSONArray jsonArray = new JSONArray(saveString);
            if(jsonArray != null ){
                int size = jsonArray.length();
                for (int i = 0 ; i < size ;i++){
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Account account = new Account();
                    account.setPwd(decry(jsonObject.optString("pwd")));
                    account.setAccount(jsonObject.optString("account"));
                    arrayList.add(account);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return arrayList;
    }

    /**
     * 保存下来
     * @param account
     */
    public static void save2LocalUserList(Account account){
        List<Account> list = getUserListFromLocal();
        list.add(0 ,account);
        if(list != null){
            int size = list.size();
            for(int i = 1 ; i< size ; i++){
                Account ac = list.get(i);
                if(ac != null && account != null && ac.equals(account)){
                    list.remove(i);
                    break;
                }
            }
        }
        save2LocalUserList(list);
    }

}
