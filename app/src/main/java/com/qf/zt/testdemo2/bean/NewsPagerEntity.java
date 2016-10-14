package com.qf.zt.testdemo2.bean;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/5.
 *
 * {
 "id": 502073,
 "Content": "0",
 "title": "传《超越善恶2》开发中",
 "postdate": "2016/10/5 11:20:16",
 "editor": "Zhengogo",
 "icon": null,
 "desc": "制作人Michel Ancel在Instagram上发了一张图并配有说明：“绝境逢生，项目前期筹备中。”尽管Ancel未能确认该项目便是雾",
 "reviewcount": 1,
 "stress": "",
 "isdel": "False",
 "ispass": "True"
 }
 */
public class NewsPagerEntity {
    private int id;
    private String icon;
    private String title;
    private int reviewCount;
    private String editor;

    /**
     *
     * 注意：对于任何一个类，必须要写一个无参数的构造方法
     *
     */
    public NewsPagerEntity() {
    }


    public NewsPagerEntity(int id, String icon, String title, int reviewCount, String editor) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.reviewCount = reviewCount;
        this.editor = editor;
    }

    public NewsPagerEntity(JSONObject jsonObject) {
        this.id = jsonObject.optInt("id");
        this.icon = jsonObject.optString("icon");
        this.title = jsonObject.optString("title");
        this.reviewCount = jsonObject.optInt("reviewcount");
        this.editor =  jsonObject.optString("editor");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
}
