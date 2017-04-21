package com.alexjollands.code.services;

import com.alexjollands.code.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageScraperService {

    public String getHTMLContentOfWebpage(String url){
        String htmlContent = null;
        try {
            Document page = Jsoup.connect(url).get();
            htmlContent = page.outerHtml();
        }
        catch (IOException e){
            System.out.println("IOException occured whilst retrieving webpage: " + url);
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
        product.setUnitPrice(getUnitPriceForProductElement(productElement));
        product.setSize(getSizeOfLinkedDetailsPageForProductElement(productElement));
        return product;
    }


    private String getTitleForProductElement(Element productElement){
        Element productInfo = productElement.getElementsByClass("productInfo").first();
        return productInfo.select("a").first().text();
    }

    private String getUnitPriceForProductElement(Element productElement){
        Element pricePerUnit = productElement.select(".pricePerUnit").first();
        String formattedPrice = pricePerUnit.text();
        return getPriceValueFromFormattedPrice(formattedPrice);
    }

    private String getSizeOfLinkedDetailsPageForProductElement(Element productElement){
        Element productInfo = productElement.getElementsByClass("productInfo").first();
        String productDetailsUrl = productInfo.select("a").first().attr("abs:href");
        String pdpContent = getHTMLContentOfWebpage(productDetailsUrl);
        if (pdpContent != null){
            Double size = Math.floor((pdpContent.length() / 1024.0) * 100) / 100;
            return String.valueOf(size) + "kb";
        }
        return "";
    }

    private String getPriceValueFromFormattedPrice(String formattedPrice){
        Pattern p = Pattern.compile("(\\d+[.]\\d\\d)");
        Matcher m = p.matcher(formattedPrice);
        if (m.find()){
            return m.group(0);
        }
        return "No price found";
    }

}
