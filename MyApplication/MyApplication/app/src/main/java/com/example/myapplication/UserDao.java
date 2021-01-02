package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UserDao {
    private static final String TAG = "User Dao";
    private final DBOpenHelper mHelper;

    public UserDao(Context context){
        mHelper = new DBOpenHelper(context,Constant.DATABASE_NAME,null,Constant.VERSION_CODE);
    }
    /* 自定义增删改查
    add()
    delete()
    update()
    getAllData()
     */
//    public void add(String name, String password){
//        SQLiteDatabase db = mHelper.getWritableDatabase();
//        db.execSQL("INSERT INTO " + Constant.TABLE_NAME + " (name,password) VALUES(?,?)",new Object[]{name,password});
//    }
//    public void delete(String name, String password){
//        SQLiteDatabase db = mHelper.getWritableDatabase();
//        db.execSQL("DELETE FROM " + Constant.TABLE_NAME + " WHERE name = AND password ="+name+password);
//    }
//    public void update(String name, String password){
//        SQLiteDatabase db = mHelper.getWritableDatabase();
//        db.execSQL("UPDATE " + Constant.TABLE_NAME + " SET password = ?",new Object[]{password});
//    }
//    public ArrayList<User> getAllData(){
//        SQLiteDatabase db = mHelper.getWritableDatabase();
//        ArrayList<User> list = new ArrayList<User>();
//        Cursor cursor = db.query(Constant.TABLE_NAME,null,null,null,null,null,"name DESC");
//        while (cursor.moveToNext()){
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String password = cursor.getString(cursor.getColumnIndex("password"));
//            list.add(new User(name, password));
//        }
//        return list;
//    }

    // 增删改查（8.14 修改版）
    public void add(String id, String nickname, String password, int rate){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("INSERT INTO " + Constant.TABLE_NAME + " (name,password) VALUES(?,?,?,?)",new Object[]{id, nickname, password, rate});
    }
    public void delete(String id){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + Constant.TABLE_NAME + " WHERE id =" + id);
    }
    public void update(String id, String password){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("UPDATE " + Constant.TABLE_NAME + " SET password = ?",new Object[]{password});
    }

    public ArrayList<User> getAllData(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query(Constant.TABLE_NAME,null,null,null,null,null,"name DESC");
        while (cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String nickname = cursor.getString(cursor.getColumnIndex("nickname"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(id, nickname, password));
        }
        return list;
    }
}