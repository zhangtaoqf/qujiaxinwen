package com.qf.zt.testdemo2.config;

/**
 * Created by Administrator on 2016/10/10.
 */

public class AppConfig {
    //newspager页面有类型1 和 类型2 两种情况
    public static final int TYPE_ZX=0;
    public static final int TYPE_PT=1;


    /**
     *
     * 数据库的相关信息
     *
     */
    //数据库的名称
    public static final String DB_NAME="qujxw.db";
    //数据库的版本
    public static final int DB_VERSION=1;
    //创建数据库的表
    public static final String DB_TABLE_CTEATE="create table %s(_id integer PRIMARY KEY AUTOINCREMENT NOT NULL%s);";

}
