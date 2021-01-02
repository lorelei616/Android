package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LayerActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
//    private RadioButton rbFirst;
//    private RadioButton rbSecond;
//    private RadioButton rbThird;
//    private RadioButton rbForth;
    private Button btnChecked;
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layer);
        mRadioGroup =(RadioGroup) findViewById(R.id.rg_2);
        btnChecked =(Button) findViewById(R.id.btn_checked);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_1:
                        intent = new Intent(LayerActivity.this,TabMainActivity.class);
                        break;
                    case R.id.rb_2:
                       // intent = new Intent(LayerActivity.this,TabMainActivity.class);
                        break;
                    case R.id.rb_3:
                       // intent = new Intent(LayerActivity.this,TabMainActivity.class);
                        break;
                    case R.id.rb_4:
                       // intent = new Intent(LayerActivity.this,TabMainActivity.class);
                        break;
                }
            }
        });
        btnChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LayerActivity.this,TabMainActivity.class);
                startActivity(intent);
            }
        });

//        rbFirst = (RadioButton) findViewById(R.id.rb_1);
//        rbSecond = (RadioButton) findViewById(R.id.rb_2);
//        rbThird = (RadioButton) findViewById(R.id.rb_3);
//        rbForth = (RadioButton) findViewById(R.id.rb_4);
//        setListeners();
    }
//    private void setListeners(){
//        OnClick onClick = new OnClick();
//        rbFirst.setOnClickListener(onClick);
//        rbSecond.setOnClickListener(onClick);
//        rbThird.setOnClickListener(onClick);
//        rbForth.setOnClickListener(onClick);
//    }

//    private  class OnClick implements View.OnClickListener{
//
//        @Override
//        public void onClick(View view) {
//            Intent intent = null;
//            switch (view.getId()){
//                case R.id.rb_1:
//                    //跳转到TextView演示界面
//                    intent = new Intent(LayerActivity.this,TabMainActivity.class);
//                    break;
//                case R.id.rb_2:
//                    //跳转到TextView演示界面
//                    intent = new Intent(LayerActivity.this,TabMainActivity.class);
//                    break;
//                case R.id.rb_3:
//                    //跳转到TextView演示界面
//                    intent = new Intent(LayerActivity.this,TabMainActivity.class);
//                    break;
//                case R.id.rb_4:
//                    //跳转到TextView演示界面
//                    intent = new Intent(LayerActivity.this,TabMainActivity.class);
//                    break;
//            }
//            startActivity(intent);
//        }
//    }
}
