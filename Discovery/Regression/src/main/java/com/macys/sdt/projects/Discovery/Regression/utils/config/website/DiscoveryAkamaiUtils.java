package com.macys.sdt.projects.Discovery.Regression.utils.config.website;

import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarNameValuePair;
import net.lightbody.bmp.proxy.dns.AdvancedHostResolver;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.*;

import static com.macys.sdt.framework.utils.StepUtils.macys;
import static java.lang.String.join;
import static java.util.stream.Collectors.toList;

public class DiscoveryAkamaiUtils {

    // Cached JSON object describing expectations
    private static JSONObject akamaiJSON;

    // High-level description of the test environment
    private static String envName;

    // Store the list of known hosts for any environment
    private static final String[] declaredHosts = {
            "www.",
            "www1.",
            "www3.",
            "www1.pos.",
            "assets.",
            "images.",
            "api.",
            "m.",
            "secure-m."
    };

    // Reserve the domain under test
    private static String domainUnderTest = "";

    // Reserve the list of hosts under test
    private static List<String> testHosts = new ArrayList<>();

    // Store the list of headers required for validation
    private static final String requiredHeaders = "(x-cache|x-cache-key|x-check-cacheable)";

    // The x-cache strings which indicate the result was served from Origin
    private static final String negativeCache = "(TCP_MEM_MISS|TCP_REFRESH_MISS|TCP_REFRESH_FAIL_MISS|TCP_IMS_MISS|TCP_NEGATIVE_MISS|TCP_MISS)";

    // The x-cache strings which indicate the result was served from Akamai
    private static final String positiveCache = "(TCP_MEM_HIT|TCP_REFRESH_HIT|TCP_REFRESH_FAIL_HIT|TCP_IMS_HIT|TCP_NEGATIVE_HIT|TCP_HIT)";

    // This is my Logger.  There are many others like it, but this one is mine.
    private static final Logger logger = LoggerFactory.getLogger(DiscoveryAkamaiUtils.class);

    /**
     *
     *
     */
    public static String validate(JSONObject expectations) throws Throwable{
        akamaiJSON = expectations;
        List<HashMap<String, String>> headers = getHeaders();
        StringBuilder result = new StringBuilder();
        for (HashMap<String, String> header : headers) {
            if (hasExpectation(header)) {
                result.append(testExpectation(header));
            }
            logger.info("Validating Header:"+header);
        }
        if (result.toString().equals("")) {
            return "valid";
        } else {
            return "Error - ENV: Some requests do not meet expectations:\n" + result.toString();
        }
    }

    /**
     * Add a configuration for the Akamai Staging host mapping to the browsermob proxy
     *
     * @param browsermobServer The instance of browsermob to configure
     */
    public static void setupStaging (BrowserMobProxy browsermobServer) {
        Map<String, String> stagingHosts = new HashMap<>();
        AdvancedHostResolver advancedHostResolver = ClientUtil.createDnsJavaResolver();
        for (String host : getTestHosts()) {
            String stagingHost = getStagingHost(host);
            logger.info ("Mapping " + host + " to " + stagingHost);
            stagingHosts.put (host, stagingHost);
        }
        advancedHostResolver.remapHosts(stagingHosts);
        browsermobServer.setHostNameResolver(advancedHostResolver);
    }

    /**
     * Determine whether a given header represents a URL with known Akamai expectations
     *
     * @param header The header under test
     * @return true if the header has known expectations
     */
    private static boolean hasExpectation(HashMap<String, String> header) {
        return !(getExpectation(header).isEmpty());
    }

    /**
     * Retrieves the Akamai expectations for a given header HashMap
     *
     * @param header The hash of header values
     * @return A HashMap of expected results
     */
    private static HashMap<String, String> getExpectation (HashMap<String, String> header) {
        HashMap<String, String> expected = new HashMap<>();
        String url = header.get("url");
        String host = getDomainName(url);
        String pageClass = getPageClass(url);
        expected.put("page_class", pageClass);
        if (pageClass.length() > 0) {
            JSONObject pageClassExpectations = akamaiJSON.getJSONObject(host).getJSONObject(pageClass);
            String cpCode = pageClassExpectations.getString("cp_code");
            String ttl = pageClassExpectations.getString("ttl");
            expected.put("cp_code", cpCode);
            expected.put("ttl", ttl);
            logger.info ("URL: " + url + "\nPage class: " + pageClass + "\nCP Code: " + cpCode + "\nTTL: " + ttl);
        } else {
            logger.info ("URL: " + url + "has no expectations defined.");
        }
        return expected;
    }

    /**
     * Determines whether a given header fails expectations
     *
     * @param header the header to test
     * @return empty string if the header meets expectations, otherwise a description of the failure
     */
    private static String testExpectation(HashMap<String, String> header) {
        String result = "Errors found on URL: " + header.get("url") + "\n";
        boolean pass = true;
        HashMap<String, String> expected = getExpectation(header);
        if (expected == null || expected.size() == 0) {
            return "";
        }
        if (header.get("cp_code") != null && expected.get("cp_code") != null && !(expected.get("cp_code").equalsIgnoreCase("unknown")) && !(header.get("cp_code").equals(expected.get("cp_code")))) {
            pass = false;
            result = result + "CP Code mismatch. Expected: " + expected.get("cp_code")
                    + " Actual: " + header.get("cp_code") + "\n";
        }
        if (header.get("ttl") != null && expected.get("ttl") != null && !(expected.get("ttl").equalsIgnoreCase("unknown"))) {
            if (!(header.get("ttl").equals(expected.get("ttl")))) {
                pass = false;
                result = result + "TTL mismatch. Expected: " + expected.get("ttl")
                        + " Actual: " + header.get("ttl") + "\n";
            }
            if (expected.get("ttl") != null && expected.get("ttl").equals("000") && header.get("cache_state") != null
                    && !(header.get("cache_state").matches(negativeCache))) {
                pass = false;
                result = result + "Cache state mismatch. Expected negative state. Actual: " + header.get("cache_state") + "\n";
            }
            // Omit homepage calls from cache state test because browsermob doesn't return X-Check-Cacheable accurately
            if (expected.get("ttl") != null && !(expected.get("ttl").equals("000")) && expected.get("page_class") != null
                    && !(expected.get("page_class").contains("homepage")) && header.get("cacheable") != null
                    && !(header.get("cacheable").equalsIgnoreCase("yes"))) {
                pass = false;
                result = result + "Cache state mismatch. Expected positive cacheable state. Actual: x-check-cacheable = " + header.get("cacheable") + "\n";
            }
        }
        return pass ? "" : result;
    }

    /**
     * Determines whether a given header fails expectations
     *
     * @param url the URL under test
     * @return the value of the page class, if known
     */
    private static String getPageClass(String url) {
        String pageClass = "";
        String host = getDomainName(url);
        JSONObject expectationSet;
        try {
            expectationSet = akamaiJSON.getJSONObject(host);
        } catch (JSONException e) {
            logger.debug("No expectations for host: " + host);
            return "";
        }
        if (expectationSet.length() > 0) {
            for (String key : expectationSet.keySet()) {
                String path = "" + expectationSet.getJSONObject(key).get("pattern");
                String pattern = "(.*)" + host + path;
                if (url.matches(pattern)) {
                    if (!(pageClass.equals(""))) {
                        logger.error(
                                "URL matches multiple page classes!\nURL: " + url +
                                        "\nOld class: " + pageClass + "\nNew class: " + key
                        );
                    }
                    pageClass = key;
                } else {
                    if (path.equals("Default") && pageClass.equals("")) {
                        pageClass = key;
                    }
                }
//                if(!pageClass.equals("")){
//                    break;
//                }
            }
        }
        return pageClass;
    }

    /**
     * Determines whether a given header refers to a relevant URL
     *
     * @param entry the HAR entry under test
     * @return true if the entry reflects a host under test
     */
    private static boolean isRelevant(HarEntry entry) {
        String url = entry.getRequest().getUrl();
        String hostRegex = "https?://(" + join("|", getTestHosts()) + ").*";
        return url.matches(hostRegex);
    }

    /**
     * Calculates the core hostname for the env under test
     *
     * @return the core hostname
     */
    public static String getDomain() {
        if (domainUnderTest.length() == 0) {
            URL testUrl;
            try {
                testUrl = new URL(RunConfig.url);
            } catch (MalformedURLException e) {
                logger.error("Failed to parse website URL: " + RunConfig.url + "\n" + e);
                return "null";
            }
            String[] hostComponents = testUrl.getHost().split("\\.");
            if (hostComponents.length < 3) {
                logger.error("Website host" + RunConfig.url + "has too few components for staging verification.");
                return "null";
            }
            domainUnderTest = StringUtils.join(Arrays.copyOfRange(hostComponents, 1, hostComponents.length), ".");
        }
        return domainUnderTest;
    }

    /**
     * Looks up the Akamai staging host for a given front-facing hostname
     *
     * @param hostName The front-facing hostname
     * @return the staging hostname
     */
    private static String getStagingHost(String hostName) {
        InetAddress hostAddress;
        try {
            hostAddress = InetAddress.getByName(hostName);
        } catch (UnknownHostException e) {
            logger.error("Failed to look up host: " + hostName + "\n" + e);
            return hostName;
        }
        return hostAddress.getCanonicalHostName().replace("edgekey", "edgekey-staging");
    }

    /**
     * Calculates all relevant hostnames for the env under test
     *
     * @return the list of hostnames
     */
    private static List<String> getTestHosts() {
        if (testHosts.size() == 0) {
            String domain = getDomain();
            if (macys()) {
                if (domain.equals("macys.com")) {
                    testHosts.add("assets.macysassets.com");
                } else {
                    testHosts.add("assets." + domain.split("\\.")[0] + ".fdsassets.com");
                }
            }
            for (String host : declaredHosts) {
                testHosts.add(host + domain);
            }
        }
        return testHosts;
    }

    /**
     * Retrieves the relevant headers from stored HAR files
     *
     * @return the stored headers as an array of HashMaps
     */
    public static List<HashMap<String, String>> getHeaders() {
        Har har = MainRunner.browsermobServer.newHar();
        List <HarEntry> harEntries = har.getLog().getEntries();
        List<HashMap<String, String>> allHeaders = new ArrayList<>();
        List<HarEntry> relevantEntries = harEntries.stream().filter(DiscoveryAkamaiUtils::isRelevant).collect(toList());
        for (HarEntry entry: relevantEntries) {
            HashMap<String, String> headers = new HashMap<>();
            String url = entry.getRequest().getUrl();
            headers.put("url", url);
            headers.putAll(getAkamaiParams(entry.getResponse().getHeaders()));
            allHeaders.add(headers);
        }

        return allHeaders;
    }

    /**
     * Extract Akamai params from a request's headers
     *
     * @param headers the HashMap of raw header values
     * @return a HashMap of relevant Akamai parameters
     */
    private static HashMap<String, String> getAkamaiParams(List<HarNameValuePair> headers) {
        HashMap<String, String> akamaiParams = new HashMap<>();
        for (HarNameValuePair harValue : headers) {
            String headerName = harValue.getName();
            if (headerName.equalsIgnoreCase("x-cache-key")) {
                String xCacheKey = harValue.getValue();
                if (xCacheKey != null) {
                    String[] xCacheKeyParams = xCacheKey.split("/");
                    akamaiParams.put("ttl", xCacheKeyParams[4]);
                    akamaiParams.put("cp_code", xCacheKeyParams[3]);
                }
            }
            if (headerName.equalsIgnoreCase("x-cache")) {
                String xCache =  harValue.getValue();
                if (xCache != null) {
                    akamaiParams.put("cache_state", xCache.split(" ")[0]);
                }
            }
            if (headerName.equalsIgnoreCase("x-check-cacheable")) {
                String xCacheable =  harValue.getValue();
                if (xCacheable != null) {
                    akamaiParams.put("cacheable", xCacheable);
                }
            }
        }
         return akamaiParams;
    }

    /**
     * Return the hostname from a given URL
     *
     * @param url The URL
     * @return the hostname as a string
     */
    public static String getDomainName(String url)  {
        URI uri;
        try {
            uri = new URI(url);
            return uri.getHost();
        } catch (Exception e){
            logger.error("URL is not properly formed: " + url);
            return "";
        }
    }
}
