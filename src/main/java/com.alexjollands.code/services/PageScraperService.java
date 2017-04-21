package com.alexjollands.code.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class PageScraperService {

    public String getHTMLContentOfWebpage(String url){
        String htmlContent = null;
        try {
            Document page = Jsoup.connect(url).get();
            htmlContent = page.outerHtml();
        }
        catch (IOException e){
            // Handle exception scenarios
        }
        return htmlContent;
    }

}
