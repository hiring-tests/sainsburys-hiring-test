package com.alexjollands.com.test;

import com.alexjollands.code.model.Product;
import com.alexjollands.code.services.PageScraperService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PageScraperServiceTest {

    private static final String SAINSBURYS_PLP = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
    private PageScraperService pageScraperService;
    private String productListingPage;

    @Before
    public void setup() {
        pageScraperService = new PageScraperService();
        productListingPage = pageScraperService.getHTMLContentOfWebpage(SAINSBURYS_PLP);
    }

    @Test
    public void testGetHTMLContentOfWebpageExternal(){
        String url = "http://www.google.com";
        String pageContent = pageScraperService.getHTMLContentOfWebpage(url);
        Assert.assertTrue("Title text Google not found on page", pageContent.contains("<title>Google</title>"));
    }

    @Test
    public void testGetHTMLContentOfWebpageSainsburys(){
        String url = SAINSBURYS_PLP;
        String pageContent = pageScraperService.getHTMLContentOfWebpage(url);
        Assert.assertTrue("Title text 'Sainsbury's' not found on page", pageContent.contains("Sainsbury's"));
    }

    @Test
    public void testExtractAllProductsFromWebpage(){
        ArrayList<Product> products = new ArrayList<Product>();
        products.addAll(pageScraperService.extractAllProductsFromWebpage(productListingPage));
        Assert.assertTrue("Number of products on page does not match expectations", products.size() == 7);
    }


    @Test
    public void testGetPriceValueFromFormattedPrice(){
        String formattedPrice = "£21.99";
        String priceValue = pageScraperService.getPriceValueFromFormattedPrice(formattedPrice);
        Assert.assertTrue("Price value is not as expected: priceValue= " + priceValue + ", expected= 21.99.", priceValue.equals("21.99"));

        formattedPrice = "$0.99";
        priceValue = pageScraperService.getPriceValueFromFormattedPrice(formattedPrice);
        Assert.assertTrue("Price value is not as expected: priceValue= " + priceValue + ", expected= 0.99.", priceValue.equals("0.99"));

        formattedPrice = "£1.00";
        priceValue = pageScraperService.getPriceValueFromFormattedPrice(formattedPrice);
        Assert.assertTrue("Price value is not as expected: priceValue= " + priceValue + ", expected= 1.00." , priceValue.equals("1.00"));

        formattedPrice = "abcdeghijklmnopqrstuvwxyz";
        priceValue = pageScraperService.getPriceValueFromFormattedPrice(formattedPrice);
        Assert.assertTrue("No priceValue should be found" , priceValue.equals("No price found"));
    }

}