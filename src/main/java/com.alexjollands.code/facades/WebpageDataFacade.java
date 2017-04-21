package com.alexjollands.code.facades;


import com.alexjollands.code.model.Product;
import com.alexjollands.code.model.Webpage;
import com.alexjollands.code.services.PageScraperService;

import java.util.List;

public class WebpageDataFacade {

    private PageScraperService pageScraperService = new PageScraperService();

    public Webpage getWebpage(String url){
        Webpage page = new Webpage(url);
        page.setContent(pageScraperService.getHTMLContentOfWebpage(url));
        return page;
    }

    public List<Product> getProductsOnPage(Webpage page){
        return pageScraperService.extractAllProductsFromWebpage(page.getContent());
    }
}
