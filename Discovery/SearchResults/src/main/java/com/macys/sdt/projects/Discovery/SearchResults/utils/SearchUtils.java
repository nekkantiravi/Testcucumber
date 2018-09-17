package com.macys.sdt.projects.Discovery.SearchResults.utils;

import com.google.gson.Gson;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.Utils;
import org.apache.http.client.utils.URIBuilder;
import org.json.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.util.HashMap;
import java.util.Map;

public class SearchUtils {
    // Put code here that doesn't necessarily apply to just one page or step.
    // You can also use it for more general utility things, like interacting
    // with a database or talking to a service.
    private static JavascriptExecutor js;

    public static Object getItemFromLocalStorage(String key) {
        /*
        Framework team comments:
        First, use JSONObject instead of the jetty JSON utilities. They're better to work with and the jetty one is causing
        build errors (http://web-ci.devops.fds.com/jenkins/job/SDT_16T_Deploy_Trigger/465/console)

        Second, the first 3 lines of this method can be replace with:
        String localValue = (String)Navigate.execJavascript(String.format("return window.localStorage.getItem('%s');", key));

        -Taylor Hutchinson
         */

        String localValue = (String) Navigate.execJavascript(String.format("return window.localStorage.getItem('%s');", key));
//        WebDriver driver = MainRunner.getWebDriver();
//        js = ((JavascriptExecutor)driver);
//        String localValue = (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", key));
//        return JSON.parse(localValue);
        return new JSONObject(localValue);
    }
    private static String getMSPHost() {
        String platform_vip = EnvironmentDetails.otherApp("f5_vip").ipAddress;
        String SDPPort = "85";
        URIBuilder builderVal = getURI(platform_vip, SDPPort);
        return builderVal.toString();
    }

    private static URIBuilder getURI(String localhost, String wireMockPort) {
        URIBuilder builder = new URIBuilder();
        String httpProtocol = "http";
        builder.setScheme(httpProtocol);
        builder.setHost(localhost);
        builder.setPort(Integer.parseInt(wireMockPort));
        System.out.println("SDP host" + builder);
        return builder;
    }

    /*
        Returns search service response for the given keyword shopping mode, region code

        @param[String keyword] keyword used for search
        @param[String shoppingMode] current shopping mode like SITE or Wedding registry
        @param[String regionCode] region code like US or CA etc..
        @param[boolean facetGroupsSupported] for getting response with facet values grouped, mainly used for Size facet

     */
    public static Map getSearchService(String keyword, String shoppingMode, String regionCode, boolean facetGroupsSupported, String sortBy) {
        Map serviceResponse = new HashMap<>();
        String value;
        String sortByValue = (sortBy == null) ? "DEFAULT" : sortBy;
        String baseCanvasPath = "/api/kws/v4/search?searchPhrase=<KEYWORD>&_offset=1&_limit=60&_sortBy=<sortByValue>";
        String contextUrl = "&_shoppingMode=<shoppingMode>&_regionCode=<regionCode>&_application=SITE&_customerExperiment=NO_EXPERIMENT";
        contextUrl = facetGroupsSupported ? contextUrl + "&facetGroupsSupported=true" : contextUrl;
        try {
            baseCanvasPath = baseCanvasPath.replace("<KEYWORD>", keyword.toLowerCase()).replace("<sortByValue>", sortByValue);
            contextUrl = contextUrl.replace("<shoppingMode>", shoppingMode).replace("<regionCode>", regionCode);
            value = getMSPHost() + baseCanvasPath + contextUrl;
            String responseString = Utils.httpGet(value, null);
            serviceResponse = new Gson().fromJson(responseString, Map.class);
        } catch (Exception e) {
            Assert.fail("Error Fetching Service \r\n" + e.getMessage());
        }
        return serviceResponse;
    }
}
