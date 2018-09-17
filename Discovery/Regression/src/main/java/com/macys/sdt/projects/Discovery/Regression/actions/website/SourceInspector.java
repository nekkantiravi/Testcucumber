package com.macys.sdt.projects.Discovery.Regression.actions.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Admin on 10/4/2017.
 */
public class SourceInspector {
    String error_string;
    String fob_selector;


    private static final Logger logger = LoggerFactory.getLogger(SourceInspector.class);

    public String validate_category_by_name(String fob) throws IOException, InterruptedException {
        String fob_status = null;
        String fob_links;
        String fob_id;
        String fob_name;

        logger.info(get_time());

        HashMap<String, String> flyout_list = new HashMap<>();

        //verify home page response

        //    logger.info(WebDriverManager.getWebDriver().findElement(By.xpath("//div[contains(@class,'flyout_118')]//div[1]/ul[1]/li[2]/a")).getText());


        int response = DsvActions.getResponseCode(WebDriverManager.getCurrentUrl());
        Assert.assertTrue("ERROR:: Failed to load home page html", response == 200);
        Wait.forPageReady();

        // if (StepUtils.macys()) {
        // fob_selector = "ul#mainNavigationFobs>li";
        List<WebElement> all_fob = Elements.findElements("blank_page.fob_selector");
        if (!(all_fob.size() == 0)) {
            for (WebElement ele : all_fob) {// else condition
                fob_links = StepUtils.macys() ? ele.findElement(By.cssSelector("a")).getAttribute("href") : ele.getAttribute("href");
                fob_id = ele.getAttribute("id").split("_")[1];
                fob_name = ele.getText();
                if (fob.equalsIgnoreCase(fob_name)) {
                    logger.info("fob links::" + fob_links);
                    logger.info("fob id::" + fob_id);
                    logger.info("fob Name::" + fob_name);
                    if (!(fob_links.isEmpty())) {//else condition
                        //hover on the selected fob
                        Clicks.hoverForSelection(ele);
                        //fetching all flyout list for FOB
                        flyout_list = gather_flyout_links(fob_id, fob_name);
                        Assert.assertTrue("ERROR: Fly Out list not complete", !(flyout_list.size() < 2));
                        //Navigating to fob and verifying Page fields
                        validate_blank_page(sanitizeUrl(fob_links));
                        //validated All visible fob links on the page
                        //          validate_responsecode_of_link_onPage();
                        fob_status = "Fob Exist";
                    } else {
                        error_string += "Fob Links are empty \n";
                    }
                    break;
                } else {
                    fob_status = "NO FOB";
                }

                //else Assert.fail("ERROR - ENV Category header not found or empty");
            }
            if (!(fob_status.equalsIgnoreCase("NO FOB"))) {
                logger.info("Flyout List Count::" + flyout_list.size());
                if (!(flyout_list.size() == 0)) {
                    int count = 0;
                    for (Map.Entry<String, String> entry : flyout_list.entrySet()) {
                        count++;
                        logger.info("" + count + "_FlyOutName::" + entry.getKey() + "FlyoutLink::" + entry.getValue());
                        validate_blank_page(sanitizeUrl(entry.getValue()));
                    }
                } else
                    error_string += flyout_list.size() == 0 ? "No Facet Available \n" : "";
            } else
                Assert.fail("FOB not found in HOMEPAGE");
        } else
            Assert.fail("FOB List not found in HOMEPAGE");
        // }
        logger.info(get_time());
        return error_string;
    }

    //Return map with flyout name and flyout links


    public HashMap<String, String> gather_flyout_links(String fob_id, String fob_name) throws InterruptedException {
        String flyout_links;
        String flyout_name;
        HashMap<String, String> map = new HashMap<>();
        Thread.sleep(10000);
        String fob_flyout_selector = StepUtils.macys() ? "//div[contains(@class,'flyout_" + fob_id + "') and @aria-label='" + fob_name + "']//a" : "//div[@id='flyout_" + fob_id + "']//a";
        logger.info("xpath::" + fob_flyout_selector);
        List<WebElement> fob_flyout = Elements.findElements(By.xpath(fob_flyout_selector));
        if (!(fob_flyout.size() == 0)) {
            for (WebElement flyout_ele : fob_flyout) {
                flyout_links = flyout_ele.getAttribute("href");
                flyout_name = flyout_ele.getText();
                //logger.info("fob flyout links::" + flyout_links);
                //logger.info("fob flyout Name::" + flyout_name);
                map.put(flyout_name, flyout_links);
            }
        }

        return map;
    }

    public void validate_blank_page(String link) throws IOException {
        //verify response code equals 200
       try{ int resposecode = DsvActions.getResponseCode(link);
        logger.info("Respose code::" + resposecode);
        if (resposecode != 200) {
            //update error message for code
            logger.info("ERROR:: Respose code not 200::" + resposecode);
            error_string += "Link::" + link + " ERROR:: Respose code not 200::" + resposecode + "\n";
        }//navigate to Links and verify the validation points
           Navigate.visit(link);
           Wait.forPageReady();
           String pageLink=WebDriverManager.getCurrentUrl();
           if (!(StringUtils.containsIgnoreCase(pageLink,"/ce") || pageLink.contains("/social") || pageLink.contains("/subscription") || pageLink.contains("/all-brands")|| pageLink.contains("/its-all-about-martha"))) {
                List<WebElement> cat_check = Elements.findElements(By.xpath("//*[@id= 'globalContentContainer']//div[@id='catSplash']"));
                String pageType = null;
                try {
                    pageType = get_pageType(WebDriverManager.getWebDriver().getPageSource()); //(WebDriverManager.getWebDriver().getPageSource().split("<type>")[1].split("</type>")[0];
                } catch (ArrayIndexOutOfBoundsException exp) {
                    pageType = null;
                }
                if ((cat_check.size() == 0 && pageType == null) || pageType.contains("- ?") || pageType.contains("? -")) {
                    logger.info("ERROR ::No Cat check and  no page type available on page");
                    // These are social links which does not contain page types in page source
                    if (StepUtils.macys()) {
                        if (link.matches(".*?(/social/|social\\.macys\\.com|/service/loop|/holiday-gift-guide" +
                                "|customerservice-macys\\.com|ce/splash|/custom-engagement-rings|/smart-watch-comparison-chart|" +
                                "/wedding-registry|/the-wedding-shop\\?).*?"))
                        {
                            logger.info("Link::"+ link +"linkSocial, Registry, Jewelery (ce/splash) or Customer service Page links-Verifying only Response code");
                        } else {
                            logger.info(" ERROR:: New type of Page");
                            error_string += "Link::" + link + " ERROR:: New type of Page\n";
                        }
                    } else {
                        if (link.contains("/social/") || link.contains("/lookbooks") || link.contains("/giftcardbalance") || link.contains("/2017-fall-campaign-100-percent-exclusive/")) {
                            logger.info("Social, Lookbooks,2017 fall Campaign  and giftcardbalance links-Verifying only Response code");
                        } else {
                            logger.info(" ERROR:: New type of Page");
                            error_string += "Link::" + link + " ERROR:: New type of Page\n";
                        } //Currently verifying the Response Code only
                        //Pages without any info
                    }
                } else if (pageType.contains("catSplash")) {
                    logger.info("CatSplash Page");
                    //cat Splash page validations
                    //1. verifying facet containers -D
                    //2. verifying local content container-D
                    if ((Elements.elementPresent("blank_page.facet_container") && Elements.elementPresent("blank_page.content_container")) || Elements.elementPresent("blank_page.browse_facet_container") && Elements.elementPresent("blank_page.browse_content_container")) {
                        logger.info("facet Container and local content container present");
                    } else {
                        logger.info("ERROR:: facet Container and local content container not present");
                        error_string += "Link::" + link + "ERROR:: facet Container and local content container not present \n";
                    }
                    //3. Verify feature categories text is displayed -D
                    if (StepUtils.macys() & !(link.contains("/mattress?")) & !(link.contains("/makeup-and-perfume?"))
                            & !(link.contains("/dkny-shoes?"))& !(link.contains("/modern-contemporary-clothing?"))) {
                        if (Elements.elementPresent("blank_page.feature_category")) {
                            logger.info("Featured Category Text Preset");
                        } else {
                            logger.info("ERROR:: featured Category Text not preset");
                            error_string += "Link::" + link + " ERROR:: featured Category Text not preset \n";
                        }
                    }
                } else if (pageType.contains("browse") || pageType.contains("searchResults")||
                        link.contains("/makeup-palettes?")||link.contains("/dior-fall-2017-makeup?")
                        ||link.contains("/charter-club-home?")) {
                    logger.info("Cat-Browse Page");
                    //Handling blooming dales chanel page
                    if (!(link.contains("makeup-perfume-beauty/chanel")))

                    {
                        if (StepUtils.macys() ? Elements.elementPresent("blank_page.facet_container") && Elements.elementPresent("blank_page.content_container") : Elements.elementPresent("blank_page.browse_facet_container") && Elements.elementPresent("blank_page.browse_content_container")) {
                            logger.info("facet Container and local content container present");
                        } else {
                            logger.info("ERROR:: facet Container and local content container not present");
                            error_string += "Link::" + link + "ERROR:: facet Container and local content container not present \n";
                        }


                        //1. verify Product count on Page  -D
                        logger.info("Verify Product Count on Page");
                        String product_count;
                        product_count = Elements.findElement("blank_page.product_count").getText();
                        int prod_count = Integer.parseInt(StepUtils.macys() ? product_count : product_count.split(" ")[0].trim());
                        if (prod_count > 0) {
                            logger.info("Product Available and Count Greater than Zero::" + prod_count);
               /*     //2. verify more than one array links are there with row_101 -D
                    if (!(Elements.findElements(By.xpath("/*//*[@id= 'globalContentContainer']//div[@id='catSplash']//div[contains(@id,'row_101')]")).size() > 0)) {
                        logger.info("Row Size not displayed on Page");
                    } else
                        logger.info("ERROR:: Row Size Displayed greater than zero");
               */
                        } else {
                            logger.info("ERROR:: Product count less then Zero::" + prod_count);
                            error_string += "Link::" + link + " ERROR:: Product count less then Zero::" + prod_count + "\n";
                        }
                    } else {
                        logger.info("Bloomingdales -Chanel Page");
                        if (Elements.elementPresent("blank_page.chanel_logo")) {
                            logger.info("Chanel_logo is Present");
                        } else {
                            logger.info("ERROR:: Chanel logo not Present");
                            error_string += "Link::" + link + " ERROR:: Chanel logo not Present \n";
                        }
                    }
                    //3. Verify feature categories text is displayed -D
                } else if (pageType.contains("subSplash")) {
                    logger.info("SubSplash Page");
                    //subSplash validation
                    //sub splash page
                    if (!(link.contains("/chanel") || link.contains("/its-all-about-martha"))) {
                        //1. verifying facet containers -D
                        //2. verifying local content container-D
                        if (Elements.elementPresent("blank_page.facet_container") && Elements.elementPresent("blank_page.content_container")) {
                            logger.info("facet Container and local content container present");
                        } else {
                            logger.info("ERROR:: facet Container and local content container not present");
                            error_string += "Link::" + link + " ERROR:: facet Container and local content container not present \n";
                        }
                        //3. verify Product Count on Page -D
                        logger.info("Verify Product Count on Page");
                        int prod_count = Integer.parseInt(Elements.findElement("blank_page.product_count").getText());
                        if (prod_count > 0) {
                            logger.info("Product Available and Count Greater than Zero::" + prod_count);
                        } else {
                            logger.info("ERROR:: Product count less then Zero::" + prod_count);
                            error_string += "Link::" + link + " ERROR:: Product count less then Zero::" + prod_count + "\n";
                        }
                        if (StepUtils.macys() & !(link.contains("/dkny-shoes?"))) {
                            if (Elements.elementPresent("blank_page.feature_category")) {
                                logger.info("Featured Category Text Preset");
                            } else {
                                logger.info("ERROR:: featured Category Text not preset");
                                error_string += "Link::" + link + " ERROR:: featured Category Text not preset \n";
                            }
                        }
                    } else {
                        logger.info("Macys -Chanel Page");
                        if (Elements.elementPresent("blank_page.chanel_logo")) {
                            logger.info("Chanel_logo is Present");
                        } else {
                            logger.info("ERROR:: Chanel logo not Present");
                            error_string += "Link::" + link + " ERROR:: Chanel logo not Present \n";
                        }
                    }
                } else if (pageType.contains("responsiveShopByBrandIndex") || pageType.contains("shop by brand")) {
                    if (Elements.elementPresent("blank_page.brand_page")) {
                        logger.info("Brand Text Preset");
                    } else {
                        logger.info("ERROR:: Brand Text -FEATURED BRANDS not preset");
                        error_string += "Link::" + link + " ERROR:: Brand Text- FEATURED BRANDS not Preset \n";
                    }
                } else if (pageType.contains("Offers page")) {
                    if (Elements.elementPresent("blank_page.sales_leftNav") && Elements.elementPresent("blank_page.sales_ofr_content")) {
                        logger.info("Sales & Promotion Page left Nav and Content Present");
                    } else {
                        logger.info("ERROR::Sales & Promotion Page left Nav and Content not Present");
                        error_string += "Link::" + link + " Sales & Promotion Page left Nav and Content NOT Present \n";
                    }
                } else {
                    logger.info("WARNING:: Page Type excludes all current type::" + pageType+"LINK::"+link);
                    error_string += "Link::" + link + " ERROR:: Page Type excludes all current type::" + pageType + "\n";

                }
            } else {
                logger.info("link::"+link+"\n Pagelink::"+pageLink+"\n Reason::Excluded Link containing:: /ce, /social, /subscription, /allbrands ");
            }
        } catch (AssertionError ae){
           logger.info("Exception Incountered");
           error_string += "Link::" + link + " ERROR:: Exception Incountered" + ae.getMessage() + "\n";
           ae.printStackTrace();
       }catch (Exception exp) {
            logger.info("Exception Incountered");
            error_string += "Link::" + link + " ERROR:: Exception Incountered" + exp.getMessage() + "\n";
            exp.printStackTrace();

        }
    }

    public void validate_responsecode_of_link_onPage() throws IOException {
        List<WebElement> page_links = Elements.findElements(By.xpath("//a"));
        StepUtils.pausePageHangWatchDog();
        validate_link_source(page_links);
        StepUtils.resumePageHangWatchDog();
    }

    public void validate_link_source(List<WebElement> page_links) throws IOException {
        int count = 0, nullcount = 0, totalcount = 0;
        String linkHref = null;
        ArrayList<String> pageUrls = new ArrayList<>();
        totalcount = page_links.size();
        logger.info("Total Links::" + totalcount);
        for (WebElement link : page_links) {
            linkHref = link.getAttribute("href");
            if (!link.getText().isEmpty() && link.isDisplayed() && !(link == null) && !(linkHref.contains("javascript:void(0)"))) {
                // || !(linkHref.contains("javascript:void(0)")) { //!link.getText().isEmpty() && link.isDisplayed()
                if (linkHref.contains("javascript:pop")) {
                    linkHref = linkHref.replace("javascript:pop", "").split(",")[0].replace("'",
                            "").replace("(", "").replace(")", "");
                    linkHref = sanitizeUrl(linkHref);
                }
                if (!linkHref.contains("http")) {
                    linkHref = sanitizeUrl(linkHref);
                }
                count++;
                //logger.info("Count::" + count + " Link::" + linkHref);
                pageUrls.add(linkHref);
                //  Assert.assertTrue("Link::"+linkHref+" linkText::"+link.getText() + " returned " + statusCode + " on GET call",
                //         statusCode == 200 || statusCode == 302 || statusCode == 301);
            } else {
                nullcount++;
                //logger.info("Not a valid Link::" + nullcount);
            }
        }
        logger.info("Total Count::" + totalcount + " Actual Count::" + count + " NullCount::" + nullcount);
        count = 0;
        for (String validLinks : pageUrls) {
            count++;
            int statusCode = 0;
            String error = "error";

            try {
                statusCode = RESTOperations.doGET(validLinks, null).getStatus();
            } catch (Exception e) {
                e.printStackTrace();
                error = e.getMessage();

            }
            String message = (statusCode == 0) ? error : String.valueOf(statusCode);
            logger.info("" + count + " Link::" + validLinks + ":: Response Code ::" + message);
            if (!(statusCode == 200 || statusCode == 302 || statusCode == 301)) {
                error_string += ("Link Response Code Error LINK::" + linkHref + " Response Code::" + message + "\n");
            }


        }

    }

    public String sanitizeUrl(String link) {
        String newLink;
        String envurl = EnvironmentDetails.getEnvUrl();
        if (!(link.contains("http") || link.contains(("https")))) {//append the evn url before it
            newLink = envurl + link;
        } else
            newLink = link;
        return newLink;
    }


    public String get_pageType(String page_source) {
        String page_type = null;
        if (page_source.contains("<type>")) {
            String[] substring = page_source.split("<type>");
            page_type = substring[substring.length - 1].split("</type>")[0];
        }
        return page_type;
    }


    public String get_time() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return (dateFormat.format(date));
    }



}
