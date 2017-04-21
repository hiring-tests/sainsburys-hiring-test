package com.alexjollands.code;

import com.alexjollands.code.model.Webpage;

public class WebpageToJsonApp{

    private static final String PRODUCT_PAGE_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

    public static void main(String[] args){
        Webpage page = new Webpage(PRODUCT_PAGE_URL);
        String html = page.getContent();
        System.out.println("Page content: " + html);
    }
}
