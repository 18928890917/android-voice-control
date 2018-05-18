package com.hnxy.xiangyuan.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hnxy.middle.Http;
import com.hnxy.middle.Sp;
import com.hnxy.middle.base.BaseActivity;
import com.hnxy.middle.config.Config;
import com.hnxy.middle.http.json.JsonModel;
import com.hnxy.middle.http.json.JsonRespHandler;
import com.hnxy.xiangyuan.MainActivity;
import com.hnxy.xiangyuan.R;
import com.hnxy.xiangyuan.bean.Account;
import com.hnxy.xiangyuan.bean.LoginBean;
import com.jingyu.utils.encryption.md5.Md5Helper;
import com.jingyu.utils.function.Logger;
import com.jingyu.utils.http.ReqInfo;
import com.jingyu.utils.http.RespInfo;
import com.jingyu.utils.util.UtilInput;

import org.json.JSONObject;
import org.xclcharts.common.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xilinch on 2017/4/21.
 */

public class XL_loginActivity extends BaseActivity {

    /**
     * 账号
     */
    private EditText et_account;
    /**
     * 密码
     */
    private EditText et_psw;

    /**
     * 登录
     */
    private Button btn_login;
    /**
     * 关闭
     */

    /**
     * 记住密码
     */
    private CheckBox cb_remember_pwd;
    /**
     * 自动登录
     */
    private CheckBox cb_auto_login;
    /**
     * 注册
     */
    private TextView tv_register;
    /**
     * 忘记密码
     */
    private TextView tv_forget_pwd;

    /**
     * 选择框
     */
    private LinearLayout ll_select;
    /**
     * 账号框
     */
    private LinearLayout ll_account;

    private int[] bg = new int[]{R.mipmap.zhanghuxialakuang, R.mipmap.zhanghaobeijing};

    private PopupWindow popupWindow;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xl_login);
        initWidget();
        initData();
        setListener();
    }

    private void initWidget() {
        et_account = getViewById(R.id.et_account);
        et_psw = getViewById(R.id.et_psw);
        btn_login = getViewById(R.id.btn_login);
        cb_auto_login = getViewById(R.id.cb_auto_login);
        cb_remember_pwd = getViewById(R.id.cb_remember_pwd);
        tv_forget_pwd = getViewById(R.id.tv_forget_pwd);
        tv_register = getViewById(R.id.tv_register);
        ll_select = getViewById(R.id.ll_select);
        ll_account = getViewById(R.id.ll_account);
    }

    private void initData() {
        String account = Sp.getUserName();
        String psd = Sp.getPSD();
        if (!TextUtils.isEmpty(account)) {
            et_account.setText(account);
        }
        if (!TextUtils.isEmpty(psd)) {
            et_psw.setText(psd);
        }
        //是否勾选记住密码
        if (Sp.isRememberPsw()) {
            cb_remember_pwd.setChecked(true);
        } else {
            cb_remember_pwd.setChecked(false);
        }
    }

    private void setListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
                MainActivity.actionStart(XL_loginActivity.this,1);
            }
        });


        ll_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPop();
            }
        });
        //设置记住密码checkbox监听
//        cb_remember_pwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    Sp.setIsRememberPsw(isChecked);
//            }
//        });
    }

    private boolean check() {
        boolean isValid = false;
        String account = et_account.getText().toString();
        String pwd = et_psw.getText().toString();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "请输入账号！", Toast.LENGTH_SHORT).show();
//            btn_login.setEnabled(true);
        } else if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "请输入密码！", Toast.LENGTH_SHORT).show();
//            btn_login.setEnabled(true);
        } else {
            isValid = true;
        }
        return isValid;

    }

    private void showPop() {
        if (popupWindow == null) {
            view = LayoutInflater.from(this).inflate(R.layout.view_popu, null);
            popupWindow = new PopupWindow(view, et_account.getWidth() + DensityUtil.dip2px(this, 34f), ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popupWindow.setTouchable(true);
            popupWindow.setContentView(view);
            ListView listView = (ListView) view.findViewById(R.id.listView);
            final MyAdapter adapter = new MyAdapter();
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //点击以后填入对应账号和密码
                    Account account = adapter.getItem(i);
                    if (et_account != null) {
                        et_account.setText(account.getAccount());
                    }
                    if (et_psw != null) {
                        et_psw.setText(account.getPwd());
                    }
                    dismissPopu();
                }
            });
            popupWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.zhanghuxialaliebiiao));
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    ll_account.setBackgroundResource(bg[1]);
                }
            });
        }

        if (popupWindow.isShowing()) {
            Logger.dShortToast("弹窗消失");
            popupWindow.dismiss();
            ll_account.setBackgroundResource(bg[1]);
        } else {
            Logger.dShortToast("弹窗提示");
            ll_account.setBackgroundResource(bg[0]);
            popupWindow.showAsDropDown(et_account, -DensityUtil.dip2px(this, 2f), 0);
        }
    }

    private void dismissPopu() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissPopu();
    }

    private void request() {
        UtilInput.hiddenInputMethod(this);
        boolean isValid = check();
        if (isValid) {
            btn_login.setEnabled(false);
            String account = et_account.getText().toString();
            String pwd = et_psw.getText().toString();
            pwd = Md5Helper.MD5Encode(pwd);
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("loginName", account);
                jsonObject.put("password", pwd);
                Http.postJsonNoCommonField(Config.getHostUrl(Config.LOGIN), jsonObject.toString(), new JsonRespHandler(getActivity()) {

                    @Override
                    public void onSuccessAll(ReqInfo reqInfo, RespInfo respInfo, JsonModel resultBean) {
                        super.onSuccessAll(reqInfo, respInfo, resultBean);
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(respInfo.getDataString(), LoginBean.class);
                        handlerResult(loginBean);
//                        MainActivity.actionStart(XL_loginActivity.this,1);
                    }

                    @Override
                    public void onEnd(ReqInfo reqInfo, RespInfo respInfo) {
                        super.onEnd(reqInfo, respInfo);
                        btn_login.setEnabled(true);

                    }
                });
            } catch (Exception exception) {
                exception.printStackTrace();
                btn_login.setEnabled(true);
            }

        }

    }

    /**
     * 处理返回结果
     */
    private void handlerResult(LoginBean resultBean) {
        String code = resultBean.getCode();
        String msg = resultBean.getMessage();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        LoginBean.DataBean dataBean = resultBean.getData();
        String userId = dataBean.getUserId();
        String userToken = dataBean.getUserToken();
        String userLogo = dataBean.getUserLogo();
        String userChName = dataBean.getUserChName();

        boolean remember_pwd = cb_remember_pwd.isChecked();
        Sp.setIsRememberPsw(remember_pwd);
        boolean auto_login = cb_auto_login.isChecked();
        Sp.setUserId(userId);
        Sp.setUserToken(userToken);
        Sp.setUserHeader(userLogo);
        Sp.setUserChName(userChName);
        String user = et_account.getText().toString();
        String psd = et_psw.getText().toString();
        Sp.setUserName(user);
        if (remember_pwd) {
            Sp.setPSD(psd);
        } else {
//            Sp.setUserName("");
            Sp.setPSD("");
        }
        //登陆成功，自动记录账号密码，密码进行加密处理
        Account account = new Account();
        account.setAccount(user);
        account.setPwd(psd);
        Account.save2LocalUserList(account);
    }

    public static void actionStart(Context activityContex) {
        Intent intent = new Intent(activityContex, XL_loginActivity.class);
        activityContex.startActivity(intent);
    }

    private class MyAdapter extends BaseAdapter {
        private List<Account> list = new ArrayList<>();

        public MyAdapter() {
            list = Account.getUserListFromLocal();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Account getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(XL_loginActivity.this).inflate(R.layout.item_textview, null);

            TextView tv = (TextView) view.findViewById(R.id.tv);
            tv.setText(list.get(i).getAccount());
            return view;
        }
    }
}
