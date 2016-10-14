package com.qf.zt.testdemo2.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.qf.zt.testdemo2.config.AppConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/10/11.
 * create table user(_id integer PRIMARY KEY AUTOINCREMENT NOT NULL,name varchar,age int)
 *
 * create table user(_id integer PRIMARY KEY AUTOINCREMENT NOT NULL,%s)
 */

public class MSqliteOpenHelper extends SQLiteOpenHelper {
    private List<Map<String,String>> maps;
    public MSqliteOpenHelper(Context context, List<Map<String,String>> maps) {
        super(context, AppConfig.DB_NAME, null, AppConfig.DB_VERSION);
        this.maps = maps;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (Map<String, String> map : maps) {
            String table_dbutils="";
            StringBuilder stringBuilder = new StringBuilder();
            Set<Map.Entry<String, String>> entrySet = map.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                if(!entry.getKey().equals("table_dbutils"))
                {
                    stringBuilder.append(",").append(entry.getKey()).append(" ");
                    if(entry.getValue().equals("string"))
                    {
                        stringBuilder.append("varchar");
                    }else
                    {
                        stringBuilder.append(entry.getValue());
                    }
                }else
                {
                    table_dbutils = entry.getValue();
                }
            }
            Log.i("info",stringBuilder.toString());
            Log.i("info",String.format(AppConfig.DB_TABLE_CTEATE,table_dbutils,stringBuilder.toString()));
            //创建数据库
            db.execSQL(String.format(AppConfig.DB_TABLE_CTEATE,table_dbutils,stringBuilder.toString()));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
