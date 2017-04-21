package com.alexjollands.code.services;

import com.alexjollands.code.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Product> extractAllProductsFromWebpage(String content){
        ArrayList<Product> products = new ArrayList<Product>();

        Document page = Jsoup.parse(content);
        Elements productElements = page.getElementsByClass("product");

        for (Element p : productElements){
            products.add(createProductFromPageElement(p));
        }

        return products;
    }


    private Product createProductFromPageElement(Element productElement){
        Product product = new Product();
        product.setTitle(getTitleForProductElement(productElement));
        return product;
    }


    private String getTitleForProductElement(Element productElement){
        Element productInfo = productElement.getElementsByClass("productInfo").first();
        return productInfo.select("a").first().text();
    }


}
