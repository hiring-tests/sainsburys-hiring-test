package com.alexjollands.code.model;

public class Webpage {

    private String url;
    private String content;

    public Webpage(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }
}
