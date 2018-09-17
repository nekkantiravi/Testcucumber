package com.macys.sdt.projects.Selection.DigitalProductXapi.steps;


import com.github.tomakehurst.wiremock.standalone.MappingsSource;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import com.github.tomakehurst.wiremock.stubbing.StubMappings;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import org.junit.Assert;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.*;

public class WiremockTestSteps extends StepUtils {

    private String webId = "";
    static JSONObject rootJson = null;
    static JSONObject fcc_rootJson=null;
    static JSONObject fcc_master_rootJson=null;
    static JSONObject fcc_mediaContent=null;
    static JSONObject bv_rootJson=null;
    static JSONObject reviews_rootJson=null;
    static JSONObject reviews_feedback_rootJson=null;
    static JSONObject bv_feedback_rootJson=null;
    private static final String wireMockDefaultPort = "8888";
    private static WireMockServer wireMockServer;
    private static String wiremockMode;



    private Configuration setUpWireMock (String product_id) throws Throwable{
        // Assign a port for Wiremock
        Configuration config = new PropertiesConfiguration("Selection/DigitalProductXapi/src/main/resources/config.properties");
        String port = config.getString("wiremock.port");

        WireMockConfiguration options = WireMockConfiguration.options();
        ConsoleNotifier notifier = new ConsoleNotifier(Boolean.valueOf(System.getProperty("verbose")));

        int wiremockPort = Integer.valueOf((port != null ? Integer.valueOf(port) : Integer.valueOf(wireMockDefaultPort)));

        // wiremock will look for __files and mappings folder inside the directory
        options.port(wiremockPort).notifier(notifier).usingFilesUnderDirectory("Selection/DigitalProductXapi/src/main/resources/"+product_id);
        wireMockServer = new WireMockServer(options);

        // Start the wiremock
        wireMockServer.start();

        return config;
    }


    @When("^I call wiremock pdp-xapi service for \"([^\"]*)\" product$")
    public void I_call_pdp_wiremock_service_for_product(String product_id) throws Throwable {
        Configuration config = setUpWireMock(product_id);
        String pdp_xapi_host= config.getString("pdp.service.host");
        String fcc_host= config.getString("fcc.service.host");

        // Get the XAPi
        rootJson = getJsonPDPXAPIWiremockService(product_id,pdp_xapi_host);

        // Get the FCC
        fcc_rootJson = getJsonFCCXAPIProductDetailsService(product_id,fcc_host);

        //Get the MediaContent
        fcc_mediaContent =  getJsonFCCXAPIMediaContent(product_id,fcc_host);
    }

    @When("^I call wiremock pdp-xapi service for Master \"([^\"]*)\" product with member \"([^\"]*)\" products$")
    public void I_call_pdp_wiremock_service_for_Master_product(String master_product_id, String product_ids) throws Throwable {
        Configuration config = setUpWireMock(master_product_id);
        String pdp_xapi_host= config.getString("pdp.service.host");
        String fcc_host= config.getString("fcc.service.host");

        // Get the XAPi
        rootJson = getJsonPDPXAPIWiremockService(master_product_id,pdp_xapi_host);

        // Get the FCC Master data
        fcc_master_rootJson = getJsonFCCMasterXAPIProductDetailsService(master_product_id, product_ids, fcc_host);

    }

    @When("^I call wiremock pdp-xapi reviews service for \"([^\"]*)\" product$")
    public void I_call_pdp_wiremock_reviews_service_for_product(String product_id) throws Throwable {
        Configuration config = setUpWireMock(product_id);
        String pdp_xapi_host= config.getString("pdp.service.host");
        String bv_host= config.getString("bv.service.host");

        // Get the XAPi
        reviews_rootJson = getJsonPDPXAPIReviewsWiremockService(product_id,pdp_xapi_host);

        // Get the bv
        bv_rootJson = getJsonBVReviewsService(product_id,bv_host);

    }

    @When("^I call wiremock pdp-xapi reviews feedback POST service for product \"([^\"]*)\" with a review content id of \"([^\"]*)\"")
    public void I_call_pdp_wiremock_reviews_feedback_POST_service_for_review(String product_id, String review_id) throws Throwable {
        Configuration config = setUpWireMock(review_id);
        String pdp_xapi_host= config.getString("pdp.service.host");
        String bv_host= config.getString("bv.service.host");

        // Get the XAPi
        reviews_feedback_rootJson = getJsonPDPXAPIReviewsFeedbackWiremockService(product_id, review_id,pdp_xapi_host);

        // Get the bv
        bv_feedback_rootJson = getJsonBVReviewsFeedbackService(review_id,bv_host);

    }

    public static JSONObject getJsonPDPXAPIWiremockService(String prodID, String pdp_xapi_host) throws IOException {
        // To use the localhost
        String url = pdp_xapi_host+"/xapi/digital/v1/product/"+prodID;
        Response response = RESTOperations.doGET(url, null);
        String finalJsonStr = response.readEntity(String.class);
        return new JSONObject(finalJsonStr);
    }

    private static JSONObject getJsonPDPXAPIReviewsWiremockService(String prodID, String pdp_xapi_host) throws IOException {
        // To use the localhost
        String url = pdp_xapi_host+"/xapi/digital/v1/product/"+prodID+"/reviews";
        Response response = RESTOperations.doGET(url, null);
        String finalJsonStr = response.readEntity(String.class);
        return new JSONObject(finalJsonStr);
    }

    private static JSONObject getJsonPDPXAPIReviewsFeedbackWiremockService(String prodID, String review_id, String pdp_xapi_host) throws IOException {
        // To use the localhost
        String url = pdp_xapi_host+"/xapi/digital/v1/product/"+prodID+"/reviews/"+review_id+"/feedback";
        Response response = RESTOperations.doPOST(url,
                MediaType.APPLICATION_JSON,
                "{\"SubmitFeedbackRequest\": {\"feedbackType\":\"inappropriate\",\"feedbackValue\":\"TEst test\",\"reviewerId\":\"7141a707147bf07140ee071494d0714bcc0714d9c071439f07147ca0\"}}");
        String finalJsonStr = response.readEntity(String.class);
        return new JSONObject(finalJsonStr);
    }

    public static JSONObject getJsonFCCXAPIProductDetailsService(String prodID, String fcc_host) throws IOException {
        String fcc_url = fcc_host+"/api/catalog/v2/products/"+prodID+
                "?_fields=id,name,brand,defaultCategoryId,upcs,shipping,price,finalPrice,giftWrappable,giftWrapId," +
                "externalHostURL,typeName,maxQuantity,comparable,productGroup,productStartDays,clearanceStartDays,isNew,active,archived," +
                "attributes,primaryImage,primaryPortraitSource,additionalImageSource,editorsPick,thumbnailCallout,domainValuesMap,additionalImages," +
                "colorwayImages,syndicateId,reviewStatistics,eligibleForPreOrder,canonicalProductUrl,rebatesAvailable,colormap,masterProductIds&sdpGrid=primary&currencyCode=USD";
        Response fcc_response = RESTOperations.doGET(fcc_url, null);
        String fcc_finalJsonStr = fcc_response.readEntity(String.class);
        return new JSONObject(fcc_finalJsonStr);
    }

    public static JSONObject getJsonFCCMasterXAPIProductDetailsService(String master_prodID, String prodIDs, String fcc_host) throws IOException {
        String fcc_url = fcc_host+"/api/catalog/v2/products/"+master_prodID+","+prodIDs+
                "?_fields=id,name,brand,defaultCategoryId,upcs,shipping,price,finalPrice,giftWrappable,giftWrapId," +
                "externalHostURL,typeName,maxQuantity,comparable,productGroup,productStartDays,clearanceStartDays," +
                "isNew,active,archived,attributes,primaryImage,primaryPortraitSource,additionalImageSource,editorsPick," +
                "thumbnailCallout,domainValuesMap,additionalImages,colorwayImages,syndicateId,reviewStatistics," +
                "eligibleForPreOrder,canonicalProductUrl,rebatesAvailable,colormap&sdpGrid=primary&currencyCode=USD&includeFinalPrice=true";
        Response fcc_response = RESTOperations.doGET(fcc_url, null);
        String fcc_finalJsonStr = fcc_response.readEntity(String.class);
        return new JSONObject(fcc_finalJsonStr);
    }

    public static JSONObject getJsonBVReviewsService(String prodID, String bv_host) throws IOException {
        String bv_url = bv_host+"/data/reviews.json?apiversion=5.4&passkey=86gu3wae8uvd6j8cnx764txc&Filter=isRatingsOnly%3Aeq%3Afalse&Stats=Reviews&sdpGrid=primary&Include=Products&Sort=ISFEATURED%3ADESC&ProductId="+prodID+"&Limit=8";
        Response bv_response = RESTOperations.doGET(bv_url, null);
        String bv_finalJsonStr = bv_response.readEntity(String.class);
        return new JSONObject(bv_finalJsonStr);
    }

    public static JSONObject getJsonBVReviewsFeedbackService(String review_id, String bv_host) throws IOException {
        String bv_url = bv_host+"/data/submitfeedback.json?apiversion=5.4&passkey=vkyvvj7xwjq8cyz5hr7zqrv4&FeedbackType=inappropriate&ContentType=review&ReasonText=TEst%20test&UserId=7141a707147bf07140ee071494d0714bcc0714d9c071439f07147ca0&sdpGrid=primary&ContentId="+review_id+"&_application=SITE&_deviceType=DESKTOP&_navigationType=BROWSE&_regionCode=US&_customerExperiment=NO_EXPERIMENT&_shoppingMode=SITE";
        Response bv_response = RESTOperations.doPOST(bv_url, MediaType.APPLICATION_JSON,null);

        String bv_finalJsonStr = bv_response.readEntity(String.class);
        return new JSONObject(bv_finalJsonStr);
    }

    public static JSONObject getJsonFCCXAPIMediaContent(String prodID, String fcc_host) throws IOException {

        String fcc_url = fcc_host+"/api/content/v2/PDP/pages/"+prodID+
                "?_expand=layout,media&sdpGrid=primary&_deviceType=DESKTOP&_application=SITE&_regionCode=US&_navigationType=BROWSE&_shoppingMode=SITE";
        Response fcc_response = RESTOperations.doGET(fcc_url, null);
        String fcc_finalJsonStr = fcc_response.readEntity(String.class);
        return new JSONObject(fcc_finalJsonStr);
    }


    @When("^I verify pdp-xapi service structure$")
    public void I_verify_pdp_xapi_service_structure() throws Throwable {
        //Validate the Product Id
        String xApiProductId = rootJson.getJSONArray("product").getJSONObject(0).get("id").toString();
        String FccProductId = fcc_rootJson.getJSONObject("product").get("id").toString();
        Assert.assertTrue(FccProductId.equals(xApiProductId));

        // Validate the Brand Name
        String xApiBrandName = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("brand").get("name").toString();
        String fccBrandName = fcc_rootJson.getJSONObject("product").getJSONObject("brand").get("brandName").toString();
        Assert.assertTrue(fccBrandName.equals(xApiBrandName));

        // Validate the Product Name
        String xApiProductName = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").get("name").toString();
        String[] fccProductName = fcc_rootJson.getJSONObject("product").get("name").toString().split(fccBrandName, 0);
        String fcc_product_name = fcc_rootJson.getJSONObject("product").get("name").toString();
        Assert.assertTrue(fcc_product_name.contains(xApiProductName));

        //Validate the Max Quantity
        String xApiMaxQuantity = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").get("maxQuantity").toString();
        String fccMaxQuantity = fcc_rootJson.getJSONObject("product").get("maxQuantity").toString();
        Assert.assertTrue(fccMaxQuantity.equals(xApiMaxQuantity));

        //Validate the Product Availability
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("availability").has("available"));
        Boolean product_availability = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("availability").getBoolean("available");
        Assert.assertTrue(product_availability ? true : false);

        //Validate the Product Description
        String xapiDescription = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").get("description").toString();
        List<Object> bullet_text = new ArrayList<>();
        bullet_text.add(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONArray("bulletText"));

        JSONArray fccAttributes = fcc_rootJson.getJSONObject("product").getJSONArray("attributes");
        String fcc_product_description = "";
        JSONArray fcc_bullet_text = null;
        List<Object> fcc_bullet = new ArrayList<>();

        for (Object attrObj : fccAttributes) {
            JSONObject attr = (JSONObject) attrObj;
            if (attr.getString("name").equalsIgnoreCase("PRODUCT_LONG_DESCRIPTION")) {
                fcc_product_description = attr.getJSONArray("attributeValues").getJSONObject(0).getString("value");
            }
            if (attr.getString("name").equalsIgnoreCase("BULLET_TEXT")) {
                fcc_bullet_text = attr.getJSONArray("attributeValues");
                for (Object bulletObj : fcc_bullet_text){
                    JSONObject bullet = (JSONObject) bulletObj;
                    fcc_bullet.add(bullet.getString("value"));
                }
            }
            if (attr.getString("name").equalsIgnoreCase("COUNTRY_OF_ORIGIN")) {
                fcc_bullet_text = attr.getJSONArray("attributeValues");
                for (Object bulletObj : fcc_bullet_text){
                    JSONObject bullet = (JSONObject) bulletObj;
                    fcc_bullet.add(bullet.getString("value"));
                }
                break;
            }

        }

        Assert.assertEquals(xapiDescription, fcc_product_description);
        //Assert.assertSame(bullet_text, fcc_bullet);

        // Validate the review statistics
        if(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("reviewStatistics").has("aggregate")) {
            String pdp_rating = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("reviewStatistics").getJSONObject("aggregate").get("rating").toString());
            String pdp_rating_count = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("reviewStatistics").getJSONObject("aggregate").get("count").toString());

            String fcc_rating = fcc_rootJson.getJSONObject("product").getJSONObject("reviewStatistics").get("averageRating").toString();
            String fcc_rating_count = fcc_rootJson.getJSONObject("product").getJSONObject("reviewStatistics").get("reviewCount").toString();

            Assert.assertTrue(pdp_rating.equals(fcc_rating));
            Assert.assertTrue(pdp_rating_count.equals(fcc_rating_count));
        }
        else {
            Assert.assertFalse(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("reviewStatistics").has("aggregate"));
        }

        // Validate the Images
        JSONObject images = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("imagery");
        JSONArray productImages = images.getJSONArray("images");

        List<Object> xapi_images = new ArrayList<>();
        JSONArray additionalImageSrcArray = null;

        for (Object imgObj : productImages) {
            JSONObject img = (JSONObject) imgObj;
            xapi_images.add(img.get("filePath").toString().substring(0, img.get("filePath").toString().indexOf(".")));
        }



            JSONObject colorMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                    .getJSONObject("colors").getJSONObject("colorMap"));
            String selectedColorId = "";
            selectedColorId = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits").getJSONObject("colors").get("selectedColor").toString();

            JSONArray colorImages = null;

            colorImages = colorMap.getJSONObject("" + selectedColorId).getJSONObject("imagery").getJSONArray("images");

            List<Object> color_images = new ArrayList<>();
            for (Object imgObj : colorImages) {
                JSONObject img = (JSONObject) imgObj;
                color_images.add(img.get("filePath").toString().substring(0, img.get("filePath").toString().indexOf(".")));
            }

            Assert.assertEquals(xapi_images, color_images);


        // Validate the default categoryId
        String xapi_default_category_id = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships").getJSONObject("taxonomy").get("defaultCategoryId").toString();
        String fcc_default_category_id = fcc_rootJson.getJSONObject("product").get("defaultCategoryId").toString();
        Assert.assertEquals(xapi_default_category_id,fcc_default_category_id);

    }

    @When("^I verify pdp-xapi reviews structure$")
    public void I_verify_pdp_xapi_reviews_structure() throws Throwable {
        //Validate the Product Id
        JSONObject review = reviews_rootJson.getJSONObject("review");
        JSONObject bv_review = bv_rootJson;
        Assert.assertEquals(review.getBoolean("hasErrors"), bv_review.getBoolean("HasErrors"));
        Assert.assertEquals(review.getJSONArray("reviews").length(), bv_review.getJSONArray("Results").length());

        // includes => products =>171
        JSONObject review_171  = review.getJSONObject("includes").getJSONObject("products").getJSONObject("171");
        JSONObject bv_review_171  = bv_review.getJSONObject("Includes").getJSONObject("Products").getJSONObject("171");
        Assert.assertEquals(review_171.getString("name"),bv_review_171.getString("Name"));
        JSONObject review_171_ratingDistribution = (JSONObject) review_171.getJSONObject("reviewStatistics").getJSONArray("ratingDistribution").get(0);
        JSONObject bv_review_171_ratingDistribution =  (JSONObject) bv_review_171.getJSONObject("ReviewStatistics").getJSONArray("RatingDistribution").get(0);
        Assert.assertEquals(review_171_ratingDistribution.getInt("ratingValue"), bv_review_171_ratingDistribution.getInt("RatingValue"));

        // reviews
        JSONObject reviews_1 = (JSONObject)review.getJSONArray("reviews").get(0);
        JSONObject bv_reviews_1 = (JSONObject)bv_review.getJSONArray("Results").get(0);
        Assert.assertEquals(reviews_1.getInt("reviewId"), bv_reviews_1.getInt("Id"));
        Assert.assertEquals(reviews_1.getJSONObject("contextDataValues").getJSONObject("gender").getString("value"), bv_reviews_1.getJSONObject("ContextDataValues").getJSONObject("gender").getString("Value"));
        Assert.assertEquals(reviews_1.getJSONObject("badges").getJSONObject("featured").getString("contentType"), bv_reviews_1.getJSONObject("Badges").getJSONObject("featured").getString("ContentType"));

    }

    @When("^I verify pdp-xapi Master product response structure$")
    public void I_verify_pdp_xapi_Master_product_response_structure() throws Throwable {
        JSONObject xApiDetail = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail");
        JSONArray fcc_master_products = fcc_master_rootJson.getJSONObject("products").getJSONArray("product");

        JSONArray orderedMasterGroupList = xApiDetail.getJSONArray("orderedMasterGroupList");
        JSONArray domainValuesMapValues = fcc_master_products.getJSONObject(0).getJSONObject("domainValuesMap").getJSONArray("entry").getJSONObject(0).getJSONArray("values");
        List<String> v1 = (ArrayList) domainValuesMapValues.toList();
        List<String> v2 = (ArrayList) orderedMasterGroupList.toList();
        Assert.assertTrue(v1.containsAll(v2));

        JSONObject memberDisplayGroupsMap = xApiDetail.getJSONObject("memberDisplayGroupsMap");
        JSONObject secondProductsFirstUPC = fcc_master_products.getJSONObject(1).getJSONArray("upcs").getJSONObject(0);

        String sizeValue = secondProductsFirstUPC.getJSONObject("size").getString("value");
        Integer productId = secondProductsFirstUPC.getInt("productId");
        boolean flag = false;
        for (Object obj: memberDisplayGroupsMap.getJSONArray(sizeValue)) {
            String id = (String) obj;
            if (id.equalsIgnoreCase(""+productId)) {
                flag = true;
                break;
            }
        }
        Assert.assertTrue(flag);

    }

    @When("^I verify pdp-xapi reviews feedback response structure$")
    public void I_verify_pdp_xapi_reviews_feedback_response_structure() throws Throwable {
        //Validate success
        Assert.assertEquals(reviews_feedback_rootJson.getJSONObject("review").getBoolean("success"), !bv_feedback_rootJson.getBoolean("HasErrors"));
    }

    @And("^I verify the color size interaction and size chart details$")
    public void I_verify_the_color_size_size_chart() throws Throwable {

        JSONObject xApiColorMap = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap");
        JSONArray fccColorMap = fcc_rootJson.getJSONObject("product").getJSONArray("colormap");

        Assert.assertTrue(xApiColorMap.length() == fccColorMap.length());
        String[] xApiKeys = xApiColorMap.keySet().toArray(new String[0]);
        String[] xApiColorNames = new String[xApiColorMap.length()];
        String[] fccColorNames = new String[fccColorMap.length()];

        for(int i=0; i < xApiColorMap.length(); i++) {
            xApiColorNames[i] = xApiColorMap.getJSONObject(xApiKeys[i]).get("name").toString();
            fccColorNames[i] = fccColorMap.getJSONObject(i).get("colorName").toString();
        }
        Arrays.sort(xApiColorNames);
        Arrays.sort(fccColorNames);

        for(int i=0; i < xApiColorNames.length; i++) {
            Assert.assertTrue(xApiColorNames[i].equals(fccColorNames[i]));

        }

        /*JSONObject xApiSizeMap = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("sizes").getJSONObject("sizeMap");
        JSONArray fccSizeMap = fcc_rootJson.getJSONObject("product")
                .getJSONArray("sizes");
        Assert.assertTrue(xApiSizeMap.length() == fccSizeMap.length());

        String[] xApiSizeKeys = xApiSizeMap.keySet().toArray(new String[0]);
        String[] xApiSizeNames = new String[xApiSizeMap.length()];
        String[] fccSizeNames = new String[fccSizeMap.length()];

        for(int i=0; i < xApiSizeMap.length(); i++) {
            xApiSizeNames[i] = xApiSizeMap.getJSONObject(xApiSizeKeys[i]).get("name").toString().trim();
            fccSizeNames[i] = fccSizeMap.getJSONObject(i).get("value").toString().trim();
        }
        Arrays.sort(xApiSizeNames);
        Arrays.sort(fccSizeNames);
        for(int i=0; i < xApiSizeNames.length; i++) {
            Assert.assertTrue(xApiSizeNames[i].equals(fccSizeNames[i]));
        }*/

        // Verify that the colorMap has the related sizes
        // Get the color map, size map and upc map
        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        JSONObject sizeMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("sizes").getJSONObject("sizeMap"));

        JSONObject colorMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap"));

        Iterator<String> keys = upcMap.keys();
        String mapId, colorId,sizeId ;

        if (keys.hasNext()) {
            mapId  = keys.next();
            String[] output = mapId.split("_");
            colorId = output[0];
            sizeId = output[1];
            if (fcc_rootJson.getJSONObject("product").has("colormap") &&
                    fcc_rootJson.getJSONObject("product").has("sizes")) {
                Assert.assertTrue(colorMap.getJSONObject("" + colorId).has("sizes"));
                Assert.assertTrue(sizeMap.getJSONObject("" + sizeId).has("colors"));
            }
        }

        // XAPI response
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("sizes").has("sizeChartId"));
        String sizeChartCanvasId = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("sizes").get("sizeChartId")).toString();
        //FCC response
        String fcc_sizeChartCanvasId = "";
        JSONArray fcc_attributes = fcc_rootJson.getJSONObject("product").getJSONArray("attributes");
        for (Object attr : fcc_attributes) {
            JSONObject aobj = (JSONObject) attr;
            if (!aobj.getString("name").equals("SIZE_CHART_CANVAS_ID")){
                continue;
            }
            fcc_sizeChartCanvasId = aobj.getJSONArray("attributeValues").getJSONObject(0).getString("value");
            //Validate xapi and fcc for the canvas id
            Assert.assertEquals(sizeChartCanvasId,fcc_sizeChartCanvasId);
        }
    }

    @And("^I verify the price details$")
    public void I_verify_the_price_details() throws Throwable {
        // Validate the Price
        String xapi_price_regular = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing").getJSONObject("price").
                getJSONArray("tieredPrice").getJSONObject(0).getJSONArray("values").getJSONObject(0).get("value").toString();
        String fcc_price_regular = fcc_rootJson.getJSONObject("product").getJSONObject("price").get("originalPrice").toString();
        Assert.assertEquals(xapi_price_regular,fcc_price_regular);

        JSONArray xApiPrices = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing")
                .getJSONObject("price").getJSONArray("tieredPrice");

        String xApiOriginalPrice;
        String xApiRetailPrice;
        String xApiWasPrice;


        JSONObject fccPrices = fcc_rootJson.getJSONObject("product").getJSONObject("price");
        String fccOriginalPrice = fccPrices.get("originalPrice").toString();
        String fccRetailPrice = fccPrices.get("retailPrice").toString();

        if(xApiPrices.length() == 1) {
            xApiOriginalPrice = xApiPrices.getJSONObject(0).getJSONArray("values").getJSONObject(0).get("value").toString();
            Assert.assertTrue(xApiOriginalPrice.equals(fccOriginalPrice));
        }
        else if(xApiPrices.length() == 2) {
            xApiOriginalPrice = xApiPrices.getJSONObject(0).getJSONArray("values").getJSONObject(0).get("value").toString();
            Assert.assertTrue(xApiOriginalPrice.equals(fccOriginalPrice));

            xApiRetailPrice = xApiPrices.getJSONObject(1).getJSONArray("values").getJSONObject(0).get("value").toString();
            Assert.assertTrue(xApiRetailPrice.equals(fccRetailPrice));

        }
        else if(xApiPrices.length() == 3) {
            xApiOriginalPrice = xApiPrices.getJSONObject(0).getJSONArray("values").getJSONObject(0).get("value").toString();
            Assert.assertTrue(xApiOriginalPrice.equals(fccOriginalPrice));

            xApiWasPrice = xApiPrices.getJSONObject(1).getJSONArray("values").getJSONObject(0).get("value").toString();
            String fccWasPrice = fccPrices.get("intermediatePrice").toString();
            Assert.assertTrue(xApiWasPrice.equals(fccWasPrice));

            xApiRetailPrice = xApiPrices.getJSONObject(2).getJSONArray("values").getJSONObject(0).get("value").toString();
            Assert.assertTrue(xApiRetailPrice.equals(fccRetailPrice));


        }

    }

    @And("^I verify the special badges$")
    public void I_verify_the_special_badges() throws Throwable {
        // XAPI response
        JSONObject badgesMap = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing").getJSONObject("badgesMap");
        JSONArray promoId = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing").getJSONArray("badgeIds");
        JSONArray fcc_promoId = null;
        JSONArray fcc_promoType = null;

        JSONArray fccAttributes = fcc_rootJson.getJSONObject("product").getJSONArray("attributes");

        for (Object attrObj : fccAttributes) {
            JSONObject attr = (JSONObject) attrObj;
            if (attr.getString("name").equalsIgnoreCase("PROMOTION_ID")) {
                fcc_promoId = attr.getJSONArray("attributeValues");
                System.out.println(fcc_promoId);
            }
            if (attr.getString("name").equalsIgnoreCase("SPECIAL_OFFERS")) {
                fcc_promoType = attr.getJSONArray("attributeValues");
                System.out.println(fcc_promoType);
                break;
            }

        }

        // Add validations for the xapi and the fcc comparison

    }

    @When("^I verify the gift card product")
    public void I_verify_the_egift_card() throws Throwable {
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail")
                .getJSONObject("flags").has("giftCard"));
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail")
                .getJSONObject("flags").getBoolean("giftCard"));
    }

    @When("^I verify the video id")
    public void I_verify_the_video_id() throws Throwable {
        JSONObject images = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("imagery");
        JSONArray fccAttributes = fcc_rootJson.getJSONObject("product").getJSONArray("attributes");

        // Validate the product video param
        for (Object attrObj : fccAttributes) {
            JSONObject attr = (JSONObject) attrObj;
            if (attr.getString("name").equalsIgnoreCase("PRODUCT_VIDEO")) {
                Assert.assertEquals((attr.getJSONArray("attributeValues").getJSONObject(0).getString("value")), String.valueOf(images.get("videoId")));
                break;
            }
        }
    }

    @When("^I verify the low availability")
    public void I_verify_the_low_availability() throws Throwable {
        Assert.assertEquals(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("availability")
                .get("lowAvailabilityMessage"),fcc_rootJson.getJSONObject("product").get("lowAvailabilityMessage"));
    }

    @And("^I verify the registry flag$")
    public void I_verify_the_registry_flag() throws Throwable {
        // When the fcc response has a attribute REGISTRABLE=WEDDING OR NOT_FOR_WC_REGISTRY=Y then the ppd-xapi should have a registrable flag set to true
        JSONArray fcc_upc_attributes = fcc_rootJson.getJSONObject("product").getJSONArray("attributes");
        Boolean registry_flag = null;
        for (Object attr : fcc_upc_attributes) {
            JSONObject aobj = (JSONObject) attr;
            if (!aobj.getString("name").equals("REGISTRABLE") && aobj.getJSONArray("attributeValues").getJSONObject(0).getString("value").equals("Wedding") ||
                    !aobj.getString("name").equals("NOT_FOR_WC_REGISTRY") && aobj.getJSONArray("attributeValues").getJSONObject(0).getString("value").equals("N")){
                continue;
            }
            Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("flags").has("registrable"));
            registry_flag = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("flags").getBoolean("registrable");
            Assert.assertTrue(registry_flag ? true : false);
        }

    }

    @And("^I verify the member flag$")
    public void I_verify_the_member_flag() throws Throwable {
        // Validate the Member Flag
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("flags").has("memberProduct"));
        Boolean member_flag = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("flags").getBoolean("memberProduct");
        System.out.println(member_flag);
        //Assert.assertTrue(member_flag ? true : false);
    }

    @And("^I verify the store product availability$")
    public void I_verify_the_store_availability_flag() throws Throwable {
        // Validate the isStoreOnlyProductOnline Flag
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("flags").has("isStoreOnlyProductOnline"));
        Boolean xapi_store_flag = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("flags").getBoolean("isStoreOnlyProductOnline");
        Assert.assertTrue(xapi_store_flag ? true : false);
    }

    @And("^I verify the color upc details$")
    public void I_verify_the_color_upc_details_availability() throws Throwable {
        JSONObject xApiColorMap = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap");
        JSONArray fccColorMap = fcc_rootJson.getJSONObject("product").getJSONArray("colormap");

        Assert.assertTrue(xApiColorMap.length() == fccColorMap.length());
        String[] xApiKeys = xApiColorMap.keySet().toArray(new String[0]);
        String[] xApiColorNames = new String[xApiColorMap.length()];
        String[] fccColorNames = new String[fccColorMap.length()];

        for(int i=0; i < xApiColorMap.length(); i++) {
            xApiColorNames[i] = xApiColorMap.getJSONObject(xApiKeys[i]).get("name").toString();
            fccColorNames[i] = fccColorMap.getJSONObject(i).get("colorName").toString();
        }
        Arrays.sort(xApiColorNames);
        Arrays.sort(fccColorNames);

        for(int i=0; i < xApiColorNames.length; i++) {
            Assert.assertTrue(xApiColorNames[i].equals(fccColorNames[i]));

        }

        //Get the uocMap and upcs from the pdp-xapi
        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        JSONObject upcs = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs"));

        //Get the upcs from fcc
        JSONArray fcc_upcs = fcc_rootJson.getJSONObject("product").getJSONArray("upcs");


        Iterator<String> keys = upcMap.keys();
        String mapId;
        int selectedColorId = -1, upcId;
        Boolean fcc_upc_availability = null, availability = null;
        JSONObject fcc_upc_colorId = null;


        if (keys.hasNext()) {
            mapId  = keys.next();
            //Get a upcId from the pdp-xapi upc map.
            upcId = upcMap.getInt(mapId);
            // Get the color id of the upcId from the pdp-xapi
            selectedColorId = upcs.getJSONObject(""+upcId).getJSONObject("traits").getJSONObject("colors").getInt("selectedColor");
            // Get the availability of the upcId from the pdp-xapi
            availability = upcs.getJSONObject(""+upcId).getJSONObject("availability").getBoolean("available");
            for (Object obj: fcc_upcs) {
                JSONObject jobj = (JSONObject)obj;
                if (jobj.getInt("id") != upcId) {
                    continue;
                }
                //Get the color id from the fcc upcId
                fcc_upc_colorId  = jobj.getJSONObject("colorway");
                //Get the availability from the fcc upcId
                fcc_upc_availability  = jobj.getJSONObject("availability").getBoolean("available");
                break;
            }
        }

        //Validate fcc and pdp-xapi parameters
        Assert.assertTrue(fcc_upc_colorId.getInt("colorwayId") == selectedColorId);
        Assert.assertFalse(fcc_upc_availability == null);
        Assert.assertFalse(availability == null);
        Assert.assertTrue(fcc_upc_availability == availability? true: false);
    }

    @And("^I verify the on sale details")
    public void I_verify_the_onsale_details() throws Throwable {
        Boolean xapi_onsalePrice = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing").getJSONObject("price").
                getJSONObject("priceType").getBoolean("onSale");
        Boolean fcc_onsalePrice = fcc_rootJson.getJSONObject("product").getJSONObject("price").getBoolean("onSale");
        Assert.assertEquals(xapi_onsalePrice,fcc_onsalePrice);
    }

    @And("^I verify the price type text")
    public void I_verify_the_priceTypeText() throws Throwable {
        String xapi_priceTypeText = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing").getJSONObject("price").
                getJSONObject("priceType").get("text").toString();
        String fcc_priceTypeText = fcc_rootJson.getJSONObject("product").getJSONObject("price").get("priceTypeText").toString();
        Assert.assertEquals(xapi_priceTypeText,fcc_priceTypeText);
    }

    @And("^I verify the bullet links")
    public void I_verify_the_bulletLinks() throws Throwable {
        JSONArray xApiBulletLinks = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail")
                .getJSONArray("bulletLinks");

        JSONArray fccContents = null;
        JSONArray fccPopUp = null;

        if(fcc_mediaContent.getJSONObject("pages").getJSONArray("page").getJSONObject(0).getJSONObject("layout").getJSONArray("rows").getJSONObject(0).getJSONArray("zones")
                .getJSONObject(0).get("name").equals("BULLET_LINK")) {
            fccContents = fcc_mediaContent.getJSONObject("pages").getJSONArray("page").getJSONObject(0).getJSONObject("layout").getJSONArray("rows").getJSONObject(0).getJSONArray("zones")
                    .getJSONObject(0).getJSONArray("contents");

            List<Object> fcc_bullets = new ArrayList<>();
            List<Object> xapi_bullets = new ArrayList<>();

            for (Object attrObj : fccContents) {
                JSONObject attr = (JSONObject) attrObj;
                if (attr.has("contentlinks")) {
                    if (attr.getJSONArray("contentlinks").getJSONObject(0).get("type").equals("STATIC")) {
                        fcc_bullets.add(attr.getJSONArray("contentlinks").getJSONObject(0).get("type"));
                        fcc_bullets.add(attr.getJSONArray("contentlinks").getJSONObject(0).get("value"));
                        fcc_bullets.add(attr.get("text"));
                    }
                    if (attr.getJSONArray("contentlinks").getJSONObject(0).get("type").equals("CATEGORY")) {
                        fcc_bullets.add(attr.getJSONArray("contentlinks").getJSONObject(0).get("type"));
                        fcc_bullets.add(attr.getJSONArray("contentlinks").getJSONObject(0).get("semanticUrl"));
                        fcc_bullets.add(attr.get("text"));
                    }
                    if (attr.getJSONArray("contentlinks").getJSONObject(0).get("type").equals("PRODUCT")) {
                        fcc_bullets.add(attr.getJSONArray("contentlinks").getJSONObject(0).get("type"));
                        fcc_bullets.add(attr.getJSONArray("contentlinks").getJSONObject(0).get("semanticUrl"));
                        fcc_bullets.add(attr.get("text"));
                    }
                }
            }

            for (Object xapiObj : xApiBulletLinks) {
                JSONObject attr_xapi = (JSONObject) xapiObj;
                if (attr_xapi.get("type").equals("STATIC")) {
                    xapi_bullets.add(attr_xapi.get("type"));
                    xapi_bullets.add(attr_xapi.get("url"));
                    xapi_bullets.add(attr_xapi.get("text"));
                }
                if (attr_xapi.get("type").equals("CATEGORY")) {
                    xapi_bullets.add(attr_xapi.get("type"));
                    xapi_bullets.add(attr_xapi.get("url"));
                    xapi_bullets.add(attr_xapi.get("text"));
                }
                if (attr_xapi.get("type").equals("PRODUCT")) {
                    xapi_bullets.add(attr_xapi.get("type"));
                    xapi_bullets.add(attr_xapi.get("url"));
                    xapi_bullets.add(attr_xapi.get("text"));
                }
            }

            System.out.println(xapi_bullets);
            System.out.println(fcc_bullets);
            Assert.assertEquals(xapi_bullets, fcc_bullets);
        }
    }

    @And("^I verify the pop up bullet links")
    public void I_verify_the_pop_up_bulletLinks() throws Throwable {
        JSONArray xApiBulletLinks = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail")
                .getJSONArray("bulletLinks");

        JSONArray fccContents = null;
        JSONArray fccPopUp = null;

        if(fcc_mediaContent.getJSONObject("pages").getJSONArray("page").getJSONObject(0).getJSONObject("layout").getJSONArray("rows").getJSONObject(0).getJSONArray("zones")
                .getJSONObject(0).get("name").equals("BULLET_LINK")) {

            fccPopUp = fcc_mediaContent.getJSONObject("pages").getJSONArray("page").getJSONObject(0).getJSONObject("layout").getJSONArray("rows").getJSONObject(0).getJSONArray("zones")
                    .getJSONObject(0).getJSONObject("nested-Content").getJSONArray("entry").getJSONObject(0).getJSONObject("value").getJSONArray("contents");

            List<Object> fcc_popUp = new ArrayList<>();
            List<Object> xapi_popUp = new ArrayList<>();

            for (Object attr1Obj : fccPopUp) {
                JSONObject attr1 = (JSONObject) attr1Obj;
                if (attr1.get("contentGroupType").equals("CUSTOM_POPUP")) {
                    fcc_popUp.add(attr1.get("contentGroupType"));
                    fcc_popUp.add(attr1.get("width"));
                    fcc_popUp.add(attr1.get("height"));
                    fcc_popUp.add(attr1.get("fileName"));
                    fcc_popUp.add(attr1.get("contentId"));
                }

                for (Object xapi1Obj : xApiBulletLinks) {
                    JSONObject attr_xapi1 = (JSONObject) xapi1Obj;
                    if (attr_xapi1.get("type").equals("CUSTOM_POPUP")) {
                        xapi_popUp.add(attr_xapi1.get("type"));
                        xapi_popUp.add(attr_xapi1.get("width"));
                        xapi_popUp.add(attr_xapi1.get("height"));
                        xapi_popUp.add(attr_xapi1.get("fileName"));
                        xapi_popUp.add(attr_xapi1.get("contentId"));
                    }
                }

                System.out.println(xapi_popUp);
                System.out.println(fcc_popUp);
                //Assert.assertEquals(xapi_popUp,fcc_popUp);
                //Assert.assertTrue(xapi_popUp.equals(fcc_popUp));
            }
        }
    }

    @And("^I verify the chanel product$")
    public void chanel_product() throws Throwable {
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("flags").has("chanel"));
        Boolean chanel_flag = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("flags").getBoolean("chanel");
        Assert.assertTrue(chanel_flag ? true : false);
    }

    @And("^I verify is master product$")
    public void is_master_product() throws Throwable{
        Assert.assertTrue(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_type").get(0).equals("master"));
    }

    @And("^I verify is member product$")
    public void is_member_product() throws Throwable{
        Assert.assertTrue(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_type").get(0).equals("member"));
    }

    @And("^I verify is single item product$")
    public void is_single_item_product() throws Throwable{
        Assert.assertTrue(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_type").get(0).equals("single item"));
    }

    @And("^I verify is not big ticket$")
    public void is_not_big_ticket() throws Throwable {
        Assert.assertTrue(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("is_big_ticket").get(0).equals("false"));
    }

    @And("^I verify is big ticket$")
    public void is_big_ticket() throws Throwable {
        Assert.assertTrue(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("is_big_ticket").get(0).equals("true"));
    }

    @And("^I verify product id is not empty")
    public void has_product_id() throws Throwable{
        String xApiProductIdAnalytics = rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_id").getString(0);
        String fccProductId = fcc_rootJson.getJSONObject("product").get("id").toString();

        Assert.assertFalse(xApiProductIdAnalytics.isEmpty());
        Assert.assertTrue(fccProductId.equals(xApiProductIdAnalytics));
    }

    @And("^I verify product name is not empty")
    public void has_product_name() throws Throwable{
        Assert.assertFalse(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_name").getString(0).isEmpty());
    }

    @And("^I verify product_original_price is not empty")
    public void has_product_original_price() throws Throwable{
        DecimalFormat decimalFormat = new DecimalFormat("0.00##");
        String xApiProductOriginalPrice = rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_original_price").getString(0);
        String fccProductOriginalPrice = decimalFormat.format(fcc_rootJson.getJSONObject("product").getJSONObject("price").get("originalPrice"));

        Assert.assertFalse(xApiProductOriginalPrice.isEmpty());
        Assert.assertTrue(fccProductOriginalPrice.equals(xApiProductOriginalPrice));
    }

    @And("^I verify product_price is not empty")
    public void has_product_price() throws Throwable{
        DecimalFormat decimalFormat = new DecimalFormat("0.00##");
        String xApiProductPrice = rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_price").getString(0);
        String fccProductPrice = decimalFormat.format(fcc_rootJson.getJSONObject("product").getJSONObject("price").get("retailPrice"));

        Assert.assertFalse(xApiProductPrice.isEmpty());
        Assert.assertTrue(fccProductPrice.equals(xApiProductPrice));
    }

    @And("^I verify product_pricing_state is not empty")
    public void has_product_pricing_state() throws Throwable{
        String xApiProductPriceState = rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_pricing_state").getString(0);
        String fccProductPriceState = fcc_rootJson.getJSONObject("product").getJSONObject("price").getString("priceTypeText");

        Assert.assertFalse(xApiProductPriceState.isEmpty());
        Assert.assertTrue(fccProductPriceState.equals(xApiProductPriceState));
    }

    @And("^I verify product_pricing_state is empty string")
    public void has_not_product_pricing_state() throws Throwable{
        Assert.assertTrue(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_pricing_state").getString(0).isEmpty());
    }

    @And("^I verify product_rating is not empty")
    public void has_product_rating() throws Throwable{
        String xApiProductRating = rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_rating").getString(0);
        String fccProductRating = fcc_rootJson.getJSONObject("product").getJSONObject("reviewStatistics").get("averageRating").toString();

        Assert.assertFalse(xApiProductRating.isEmpty());
        Assert.assertTrue(fccProductRating.equals(xApiProductRating));
    }

    @And("^I verify product_reviews is not empty")
    public void has_product_reviews() throws Throwable{
        Assert.assertFalse(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_reviews").getString(0).isEmpty());
    }

    @And("^I verify there is video")
    public void has_product_video() throws Throwable{
        String xApiProductVideo = rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_video").get(0).toString();
        JSONArray fccProducts = fcc_rootJson.getJSONObject("product").getJSONArray("attributes");
        boolean hasVideo = isAttributePresent(fccProducts, "PRODUCT_VIDEO");

        Assert.assertTrue(xApiProductVideo.equals("true"));
        Assert.assertTrue(String.valueOf(hasVideo).equals(xApiProductVideo));
    }

    @And("^I verify there is no video")
    public void has_not_product_video() throws Throwable{
        Assert.assertTrue(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_video").get(0).equals("false"));
    }

    @And("^I verify product_gwp_available is not empty")
    public void has_product_gwp_available() throws Throwable{
        Assert.assertFalse(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_gwp_available").getString(0).isEmpty());
    }

    @And("^I verify product_gwp_available is empty")
    public void has_not_product_gwp_available() throws Throwable{
        Assert.assertTrue(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_gwp_available").isNull(0));
    }

    @And("^I verify product_pwp_available is not empty")
    public void has_product_pwp_available() throws Throwable{
        Assert.assertFalse(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_pwp_available").getString(0).isEmpty());
    }

    @And("^I verify product_pwp_available is empty")
    public void has_not_product_pwp_available() throws Throwable{
        Assert.assertTrue(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("product_pwp_available").isNull(0));
    }

    @And("^I verify there is true_fit_size")
    public void has_true_fit_size() throws Throwable{
        String xApiProductTrueFit = rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("true_fit_size").get(0).toString();
        JSONArray fccProducts = fcc_rootJson.getJSONObject("product").getJSONArray("attributes");
        boolean hasTrueFit = isAttributePresent(fccProducts, "TRUEFIT");

        Assert.assertTrue(xApiProductTrueFit.equals("true"));
        Assert.assertTrue(String.valueOf(hasTrueFit).equals(xApiProductTrueFit));
    }

    @And("^I verify there is not true_fit_size")
    public void has_not_true_fit_size() throws Throwable{
        Assert.assertTrue(rootJson.getJSONObject("meta").getJSONObject("analytics").getJSONObject("data").getJSONArray("true_fit_size").get(0).equals("false"));
    }

    @And("^stop the wiremock$")
    public void I_stop_the_wiremock() throws Throwable {
        wireMockServer.stop();
    }

    private boolean isAttributePresent(JSONArray jsonArray, String attribute){
        boolean result = false;
        int i = 0;

        while(!result && i <= jsonArray.length()){
            if(((JSONObject)jsonArray.get(i)).get("name").equals(attribute))
                result = true;
            i++;
        }
        return result;
    }
}



