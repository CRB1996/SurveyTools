package com.chen.member;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.surveytools.R;

public class MemberActivity extends Activity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("小组成员");
        setContentView(R.layout.activity_member);

       try {
           SQLiteOpenHelper starbuzzDatabaseHelper = new MemberDatabase(this);
           db = starbuzzDatabaseHelper.getWritableDatabase();

           cursor = db.query("member", new String[]{"_id", "name", "STUDENT_ID"},
                   null, null, null, null, null);

           while (cursor.moveToNext()) {
               int id = cursor.getInt(0);
               String name = cursor.getString(1);
               String student_id = cursor.getString(2);

               TextView all = (TextView) findViewById(R.id.membertext);
               all.setText(all.getText() + Integer.toString(id) + "\t\t\t" + name + "\t\t\t" + student_id + "\n\n");
           }
           cursor.close();
           db.close();
       }catch (SQLException e){
           Toast toast = Toast.makeText(this,"数据库读取问题",Toast.LENGTH_SHORT);
           toast.show();
       }
    }
}
