package com.qf.zt.testdemo2.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/11.
 */
public class DbUtils {
    private List<Map<String,String>> maps;
    private static DbUtils dbUtils;
    private DbUtils()
    {
        maps = new ArrayList<>();
    }
    private MSqliteOpenHelper sqliteOpenHelper;
    public void createTableIfNotExist(Class<?> cls)
    {
        Map<String,String> map = new HashMap<String, String>();
        map.put("table_dbutils",cls.getSimpleName().trim().toLowerCase());
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            map.put(field.getName().toLowerCase(),field.getType().getSimpleName().toLowerCase());
        }
        maps.add(map);
    }

    /**
     * 将配置信息带入初始化
     *
     * @param context
     */
    public void init(Context context)
    {
        sqliteOpenHelper = new MSqliteOpenHelper(context,maps);
        sqliteOpenHelper.getWritableDatabase();
    }


    public static DbUtils getDbUtils(){
        if(dbUtils == null)
        {
            dbUtils = new DbUtils();
        }
        return dbUtils;
    }

    /**
     * 数据的保存
     *
     * @param object
     */
    public void save(Object object)
    {
        SQLiteDatabase writableDatabase = sqliteOpenHelper.getWritableDatabase();
        Class<?> cls = object.getClass();
        String table = cls.getSimpleName().toLowerCase();
        ContentValues values = new ContentValues();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            String column = field.getName().toLowerCase();
            field.setAccessible(true);
            try {
                if (field.get(object)!=null)
                {
                    values.put(column,field.get(object).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        writableDatabase.insert(table,null, values);
    }

}
