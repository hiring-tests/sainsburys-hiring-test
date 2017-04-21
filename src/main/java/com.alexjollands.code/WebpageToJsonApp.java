package com.alexjollands.code;

import com.alexjollands.code.model.Product;
import com.alexjollands.code.model.Report;
import com.alexjollands.code.model.Webpage;
import com.alexjollands.code.facades.WebpageDataFacade;
import com.google.gson.Gson;


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

        Gson gson = new Gson();
        Report report = new Report();
        report.setResults(products);
        report.setTotal(calculateTotalPrice(products));
        System.out.println(gson.toJson(report));
    }

    private static String calculateTotalPrice(List<Product> products){
        Double total = 0.0;
        for(Product p : products){
            total += Double.valueOf(p.getUnitPrice());
        }
        return String.format( "%.2f", total );
    }
}
