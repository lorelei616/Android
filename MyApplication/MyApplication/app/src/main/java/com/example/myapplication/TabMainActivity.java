package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

//import com.example.myapplication.adapter.*;
//import com.example.myapplication.widget.*;
//import com.example.myapplication.util.*;

public class TabMainActivity extends AppCompatActivity {
    VerticalTabLayout mTabLayout;
    List<Fragment> mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);

        mFragment = new ArrayList<>();
//        mFragment.add(new AFragment());
//        mFragment.add(new BFragment());
        mFragment.add(new Fragment_StudyArea());
        mFragment.add(new Fragment_BorrowSection());
        mFragment.add(new Fragment_exit());
        mFragment.add(new Fragment_FunctionalArea());
        mFragment.add(new Fragment_toilette());
        mFragment.add(new Fragment_rest());

        mTabLayout = (VerticalTabLayout) findViewById(R.id.tablayout2);
        mTabLayout.setTabAdapter(new MyTabAdapter());
        mTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,mFragment.get(position)).commitAllowingStateLoss();
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

    private class MyTabAdapter implements TabAdapter{
        List<String> menus;
        public MyTabAdapter(){
            menus = new ArrayList<>();
            Collections.addAll(menus,"自习区", "借阅区", "电梯等出口", "功能区", "卫生间",
                    "休息区");
        }

        @Override
        public int getCount() {
            return menus.size();
        }

        @Override
        public ITabView.TabBadge getBadge(int position) {
            return null;
        }

        @Override
        public ITabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            return new ITabView.TabTitle.Builder()
                    .setContent(menus.get(position))
                    .setTextColor(0xFFFFFFFF, 0xFF333333)//可自己设置tab字体颜色
                    .build();
        }

        @Override
        public int getBackground(int position) {
            return -1;
        }
    }
}
