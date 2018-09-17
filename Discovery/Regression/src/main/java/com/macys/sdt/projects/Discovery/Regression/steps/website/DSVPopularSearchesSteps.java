package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.shared.steps.website.CategorySplashSteps;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;
import java.util.List;

public class DSVPopularSearchesSteps {

    private static final Logger logger = LoggerFactory.getLogger(DSVPopularSearchesSteps.class);

    @Then("^I verify Popular Related Searches section and links return (\\d+) OK$")
    public void iVerifyPopularRelatedSearchesSectionAndLinksReturnOK(int expectedResponseCode) throws Throwable {
        new CategorySplashSteps().iShouldSeeThePopularSearchesAtTheBottom();
        logger.info("Verified: Tag clouds links are displayed");

        List<WebElement> seoLinks = Elements.findElements("category_browse.seo_tag_links");
        logger.info("All links count: " + seoLinks.size());
        for (WebElement link : seoLinks) {
            int responseCode = getResponseCode(link.getAttribute("href"));
            logger.info("Response code for link: " + link.getAttribute("href") + " = " + responseCode);
            Assert.assertTrue("ERROR - APP: Response code for href:"+link.getAttribute("href")+" is not 200 and Actual Value is " + responseCode,
                     responseCode == expectedResponseCode);
        }
        logger.info("Verified: Popular Related Searches section and links return 200 OK");
    }

    public static int getResponseCode(String href) {
        int responseCode = 0;
        try {
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            URL url = new URL(href);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();

            int redirectCounter = 0;
            while (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                url = new URL(connection.getHeaderField("Location"));
                connection = (HttpURLConnection) url.openConnection();
                responseCode = connection.getResponseCode();
                redirectCounter++;
                if (redirectCounter > 5) {
                    break;
                }
            }
        } catch (IOException e) {
            Assert.fail("Unable to get response for the url: " + href + "\t" + e.getMessage());
        }
        return responseCode;
    }
}
