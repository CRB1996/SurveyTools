package com.chen.member;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

class MemberDatabase extends SQLiteOpenHelper {

     private static final String DB_NAME = "member"; //数据库名字
     private static final int DB_VERSION = 1;  //数据库版本号

     MemberDatabase(Context context){
         super(context,DB_NAME,null,DB_VERSION);
     }

    @Override
    public void onCreate(SQLiteDatabase db) {
         updateMyDatabase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         updateMyDatabase(db,oldVersion,newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db,int oldVersion,int newVersion){
         if(oldVersion < 1){
             db.execSQL("CREATE TABLE MEMBER (_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"NAME TEXT,"+"STUDENT_ID TEXT);");
             insertMember(db,"陈润博","151110254");
             insertMember(db,"杨明强","151110265");
             insertMember(db,"张有林","151110274");
             insertMember(db,"黄源青","151110244");
             insertMember(db,"赖杜锋","151110242");
         }
    }

    private static void insertMember(SQLiteDatabase db,String name,String student_id){

        ContentValues memberValues = new ContentValues();
        memberValues.put("NAME",name);
        memberValues.put("STUDENT_ID",student_id);
        db.insert("MEMBER",null,memberValues);
    }
}
