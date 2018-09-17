package com.macys.sdt.projects.Marketing.LaunchCall.utils.mcom;


import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.junit.Assert;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericUtils extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(GenericUtils.class);
    public static HashMap<String, String> failedLinks = new HashMap<String, String>();

    public static void validateLinks(List<WebElement> elems) {

        String url = null;
        Map<String, String> headers = new HashMap<>();

        for (WebElement elem : elems) {
            try {
                if (!(elem.getText() == null || elem.getAttribute("href") == null)) {

                    url = elem.getAttribute("href");
                    // creative and social pages do not work in backdoor
                    if (!(url.contains("ce") || url.contains("social") || url.contains("wedding-registry") || url.contains("javascript:void()"))) {

                        if (!url.contains("http")) {
                            url = url.contains("javascript:pop") ? url.split("'")[1] : url;
                         /*   if (url.contains("javascript:pop")) {
                                url = url.split("'")[1];
                            }*/
                            url = RunConfig.url + url;
                        }
                        System.out.print(url + " ");
                        Response response = RESTOperations.doGET(url, headers);
                        int statusCode = response.getStatus();
                        if (statusCode == 404) {
                            failedLinks.put(elem.getText(), url);
                        }
                        System.out.println(statusCode);
                    }
                }
            } catch (StaleElementReferenceException e) {
                continue;
            }
        }
    }

    public static void validateResponse(WebElement elem) {

        String url = null;
        Map<String, String> headers = new HashMap<>();
        url = elem.getAttribute("href");
        if (!url.contains("http")) {
            url = RunConfig.url + url;
        }
        System.out.print(url + " ");
        Response response = RESTOperations.doGET(url, headers);
        int statusCode = response.getStatus();
        System.out.println(statusCode);
        Assert.assertTrue(elem.getText() + " link failed",
                statusCode == 200 || statusCode == 301 || statusCode == 302);
    }
}
