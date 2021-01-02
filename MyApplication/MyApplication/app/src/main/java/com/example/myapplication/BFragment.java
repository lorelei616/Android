package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BFragment extends Fragment {

    private TextView mTvTitle;

    //Fragment常用的方法
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //添加布局文件
        //inflate方法返回的是View
        View view = inflater.inflate(R.layout.fragment_a,container,false);
        return view;
    }

    /*View创建完成之后的回调方法，可以在里面做一些事情*/
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //onViewCreated相当于Activity的onCreated
        //onCreateView的作用相当于Activity中的setContentView（）
        mTvTitle = view.findViewById(R.id.tv_title);

    }
}
