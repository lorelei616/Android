package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEdittxtUser;
    private EditText mEdittxtPassword;
    private EditText mEdittxtValidatePassword;
    private Button mBtnRegister;
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
        mUserDao = new UserDao(this);
    }

    private void initView(){
        mEdittxtUser = (EditText) findViewById(R.id.et_user_register);
        mEdittxtPassword = (EditText) findViewById(R.id.et_password_register);
        mEdittxtValidatePassword = (EditText) findViewById((R.id.et_password_register_2));
        mBtnRegister = (Button) findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String username = mEdittxtUser.getText().toString().trim();
                String password = mEdittxtPassword.getText().toString().trim();
                String validate_password = mEdittxtValidatePassword.getText().toString().trim();
                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(validate_password)){
                    if(password.equals(validate_password)){
                        mUserDao.add(username,password);
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this,"请完善信息",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
