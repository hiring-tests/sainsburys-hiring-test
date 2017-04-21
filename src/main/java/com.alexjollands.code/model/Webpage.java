package com.alexjollands.code.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Webpage {

    private String url;
    private String content;


    public Webpage(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    public String getContent(){
        if (content == null){
            try {
                Document page = Jsoup.connect(this.url).get();
                this.content = page.outerHtml();
            }
            catch (IOException e){
                // Handle exception logic
            }
        }
        return content;
    }
}
