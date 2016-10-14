package com.qf.zt.testdemo2.bean;

/**
 * Created by Administrator on 2016/10/10.
 */

import org.json.JSONObject;

/**
 * {
 "id": 409126,
 "content": "",
 "postdate": "2015/4/7 16:33:33",
 "editor": "上方文Q",
 "desc": "",
 "reviewcount": 27,
 "ispass": "True",
 "isdel": "False",
 "title": "Windows 10亮眼新功能确认回归：鼓掌吧！",
 "icon": "http://news.mydrivers.com/img/topimg/20150407/214616275.jpg"
 }
 */

public class NewsBannerEntity {
    private int id;
    private String content;
    private String postdate;
    private String editor;
    private int reviewcount;
    private String ispass;
    private String isdel;
    private String title;
    private String icon;

    public NewsBannerEntity() {
    }

    public NewsBannerEntity(JSONObject jsonObject)
    {
        this.id = jsonObject.optInt("id");
        this.content = jsonObject.optString("content");
        this.postdate = jsonObject.optString("editor");
        this.reviewcount = jsonObject.optInt("reviewcount");
        this.ispass = jsonObject.optString("ispass");
        this.isdel = jsonObject.optString("isdel");
        this.title = jsonObject.optString("title");
        this.icon = jsonObject.optString("icon");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getReviewcount() {
        return reviewcount;
    }

    public void setReviewcount(int reviewcount) {
        this.reviewcount = reviewcount;
    }

    public String getIspass() {
        return ispass;
    }

    public void setIspass(String ispass) {
        this.ispass = ispass;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
