package com.alexjollands.code.facades;


import com.alexjollands.code.model.Webpage;
import com.alexjollands.code.services.PageScraperService;

public class WebpageDataFacade {

    private PageScraperService pageScraperService = new PageScraperService();

    public Webpage getWebpage(String url){
        Webpage page = new Webpage(url);
        page.setContent(pageScraperService.getHTMLContentOfWebpage(url));
        return page;
    }
}
