package com.alexjollands.code;

import com.alexjollands.code.model.Product;
import com.alexjollands.code.model.Webpage;
import com.alexjollands.code.facades.WebpageDataFacade;

import java.util.ArrayList;
import java.util.List;

public class WebpageToJsonApp{

    private static final String PRODUCT_PAGE_URL = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";

    private static WebpageDataFacade webpageDataFacade;

    public static void main(String[] args){
        runApplication();
    }

    private static void runApplication(){
        webpageDataFacade = new WebpageDataFacade();

        Webpage page = webpageDataFacade.getWebpage(PRODUCT_PAGE_URL);
        List<Product> products = webpageDataFacade.getProductsOnPage(page);

        for (Product p : products){
            System.out.println("Product title: " + p.getTitle());
        }
    }
}
