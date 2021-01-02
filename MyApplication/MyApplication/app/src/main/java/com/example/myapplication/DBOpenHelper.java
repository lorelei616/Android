package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public DBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //第一次创建时候的回调
        //数据库已经存在时不会被调用
        Log.d(TAG,"创建数据库...");
        //创建字段,用于初始化数据表
        //sql: create table table_name(id int,...)
        String sql = "create table if not exists " + Constant.TABLE_NAME + "(id varchar primary key autoincrement, nickname varchar, password varchar, rate integer);";
        //执行SQL语句：
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG,"升级数据库、、、");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        onCreate(sqLiteDatabase);
    }
}
