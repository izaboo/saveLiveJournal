package com.livejournal.htmlunit;

import com.gargoylesoftware.htmlunit.WebClient;

import java.util.logging.Level;

public class HtmlPage {
    String journalName;

    public HtmlPage(String journalName) {
        this.journalName = journalName;
    }

    public String getHTMLPageContent(String page) {
        WebClient webClient = new WebClient();
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);

        try {
            String fullPageAddress = "http://" + journalName + ".livejournal.com/" + page;
            com.gargoylesoftware.htmlunit.html.HtmlPage htmlPage = webClient.getPage(fullPageAddress);
            return htmlPage.asText();
        } catch (Exception e) {
                return null;
        }
    }
}
