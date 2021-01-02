package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
//import com.example.myapplication.adapter.*;
//import com.example.myapplication.widget.*;
//import com.example.myapplication.util.*;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private Button mBtnLogIn;
    private EditText mEdittxtId;
    private EditText mEdittxtNickname;
    private EditText mEdittxtPassword;
    private TextView mtextviewForgetPassword;
    private TextView mtextviewRegister;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        mUserDao = new UserDao(this);
    }

    private void initView(){
        mBtnLogIn = (Button) findViewById(R.id.btn_login);
        mEdittxtId = (EditText) findViewById(R.id.et_id);
        mEdittxtNickname = (EditText) findViewById(R.id.et_nickname);
        mEdittxtPassword = (EditText) findViewById(R.id.et_password);
        mtextviewForgetPassword = (TextView) findViewById(R.id.tv_forget_password);
        mtextviewRegister = (TextView) findViewById(R.id.tv_register);

        setListners();
    }

    private void setListners(){
        OnClick onClick = new OnClick();
        mBtnLogIn.setOnClickListener(onClick);
        mtextviewForgetPassword.setOnClickListener(onClick);
        mtextviewRegister.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.tv_forget_password:
                    Toast.makeText(LoginActivity.this,"暂未实现",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_register:
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.btn_login:
                    String id = mEdittxtId.getText().toString().trim();
                    String nickname = mEdittxtNickname.getText().toString().trim();
                    String password = mEdittxtPassword.getText().toString().trim();
                    final User new_user = new User(id, nickname, password);

                    if(!TextUtils.isEmpty(id) && !TextUtils.isEmpty(password)){
                        ArrayList<User> data = mUserDao.getAllData();
                        boolean match = false;
                        for(int i=0; i <data.size() ; i++){
                            User user = data.get(i);
                            if(id.equals(user.getId()) && password.equals(user.getPassword())){
                                match = true;
                            }else{
                                match = false;
                            }
                        }
                        if(match){
                            Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent1);
                            finish();
                        }else {
                            // Toast.makeText(LoginActivity.this,"用户名或密码不正确",Toast.LENGTH_SHORT).show();
                            // 2020/8/14 增加的新内容（start）---- 去服务器查询以确定用户是否注册
                            new Thread(){
                                @Override
                                public void run() {
                                    try{
                                        sendUserMessage(new_user);
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                            // 2020/8/14 增加的新内容（end）
                        }
                    }else{
                        Toast.makeText(LoginActivity.this,"请输入用户名和密码",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

    private void sendUserMessage(User user) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        Log.i(TAG, json);

        // 构建RequestBody对象来存放待提交参数
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8000/api/users/register")
                .post(requestBody)
                .build();
        Log.i(TAG, request.toString());

        Response response = client.newCall(request).execute();
        int returnCode = JsonParser(response);

        if(returnCode == 0){
            Looper.prepare();
            Toast.makeText(LoginActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent1);
            finish();
            Looper.loop();
        }else if(returnCode == 1){
            Looper.prepare();
            Toast.makeText(LoginActivity.this,"用户已存在",Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    // 解析json数据
    public int JsonParser(Response response) throws IOException{
        String responseData = response.body().string();
        Log.i(TAG, responseData);
        Error error = new Gson().fromJson(responseData, Error.class);
        return error.getError();
    }

}
