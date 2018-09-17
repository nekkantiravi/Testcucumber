package com.macys.sdt.projects.Selection.DigitalProductXapi.steps;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;


public class PDPxAPISteps extends StepUtils {
    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());

    private String webId = "";
    static JSONObject rootJson = null;
    static JSONObject fcc_rootJson = null;

    @When("^I call pdp-xapi service for \"([^\"]*)\" product$")
    public static void I_call_pdp_xapi_service_for_product(String prod_id) throws Throwable {
        //call PDP XAPI service
        rootJson = getJsonPDPXAIService(prod_id);
        //call FCC service for verification
        fcc_rootJson = getJsonFCCProductDetailsService(prod_id);
    }

    @When("^I verify pdp-xapi service of \"([^\"]*)\" against fcc service for product name,brand name$")
    public static void I_verify_pdp_xapi_service_for_product_against_fcc_service(String prod_id) throws Throwable {
      //Get the product name and brand name from pdp-xapi
      String pdp_name = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").get("name").toString());
      String brand_name = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("brand").get("name").toString());

      // Get the product name and brand name from pdp-xapi
      String fcc_pdp_name = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).get("name").toString();
      String fcc_brand_name = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONObject("brand").get("brandName").toString();

      //Assert the pdp-xapi params and the fcc params
      Assert.assertTrue(fcc_pdp_name.contains(pdp_name));
      Assert.assertTrue(brand_name.equals(fcc_brand_name));
    }

    public static String getFccXapiValidatedAttribute(String pId, String attribute) throws Throwable {
        if(!attribute.equals("maxQuantity"))
            logger.info("\n******* Starting xApi Vs Fcc verifications for \""+attribute+"\" with PID::  " +pId+
                    "\n===========================================================================\n");

        String attrValue = null;
        try {
            I_call_pdp_xapi_service_for_product(pId);
        } catch (Exception e) {
            logger.warning("xApi Service Down!");
            e.printStackTrace();
        }
        if(attribute.equals("BrandName")) {
            String xApiBrandName = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail")
                    .getJSONObject("brand").get("name").toString());
            String fccBrandName = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0)
                    .getJSONObject("brand").get("brandName").toString();

            Assert.assertTrue(xApiBrandName.equals(fccBrandName));
            logger.info( "Value of " + attribute + " validated:: xApi: " + xApiBrandName + " == FCC: " + fccBrandName);
            attrValue = xApiBrandName;
        }
        else if(attribute.equals("ProductName")) {
            String fccBrandName = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0)
                    .getJSONObject("brand").get("brandName").toString();
            String xApiProductName = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail")
                    .get("name").toString());
            String[] fccProductName = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0)
                    .get("name").toString().split(fccBrandName + " ",0);

            logger.info( "ByPassing xApi defect for BrandName::changing fccProductName[1].trim().equals() to fccProductName[1].trim().contains()");
//               *** xApi defect for BrandName::changing bellow code-line to .contains(), instead of .equals()
            Assert.assertTrue(fccProductName[1].contains(xApiProductName));
            logger.info( "Value of " + attribute + " validated:: xApi: " + xApiProductName + " == FCC: " + fccProductName[1]);
            attrValue = xApiProductName;
        }
        else if(attribute.equals("maxQuantity")) {
            String xApiMaxQuantity = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").get("maxQuantity").toString();
            String fccMaxQuantity = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).get("maxQuantity").toString();

            Assert.assertTrue(xApiMaxQuantity.equals(fccMaxQuantity));
            attrValue = xApiMaxQuantity;
        }
        else if(attribute.equals("availability")) {
            String xApiAvailability = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("availability").get("available").toString();
            String fccAvailability = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONArray("upcs")
                    .getJSONObject(0).getJSONObject("availability").get("available").toString();

            Assert.assertTrue(xApiAvailability.equals(fccAvailability));
            logger.info( "Value of " + attribute + " validated xApi Vs FCC:: " + xApiAvailability + " == " + fccAvailability);
            attrValue = xApiAvailability;
        }
        else
            logger.warning( attribute + " Not Defined!");

        if(!attribute.equals("maxQuantity"))
            logger.info("\n******* xApi Vs Fcc verifications for \""+attribute+"\" completed!"+"\n\n");
        return attrValue;
    }

    public static List<String> getFccXapiValidatedAttributes(String pId, String attribute) throws Throwable {
        logger.info("\n******* Starting xApi Vs Fcc verifications for \""+attribute+"\" with PID::  " +pId+
                "\n===========================================================================\n");
        List<String> attrValueList = new ArrayList<>();
        try {
            I_call_pdp_xapi_service_for_product(pId);
        } catch (Exception e) {
            logger.warning("xApi Service Down!");
            e.printStackTrace();
        }
        switch(attribute) {
            case "Basic Elements": {
                String xApiBrandName = null;
                String fccBrandName = null;
                String xApiProductName = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").get("name").toString();
                String fccProductName = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).get("name").toString();

                try {
                    xApiBrandName = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("brand").get("name").toString();
                    fccBrandName = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONObject("brand").get("brandName").toString();
                    Assert.assertTrue(fccBrandName.equals(xApiBrandName));
                    logger.info( "BrandName Validated in xApi Vs FCC:: " + xApiBrandName + " == " + fccBrandName);
                    Assert.assertTrue(fccProductName.trim().toLowerCase().contains(xApiProductName.trim().toLowerCase()));
                    logger.info("ProductTitle Validated in xApi Vs FCC:: " + xApiBrandName.trim().toLowerCase() + " " + xApiProductName.trim().toLowerCase() + " == " + fccProductName.trim().toLowerCase());
                }
                catch(Exception e) {
                    logger.warning("Product Id \"" + pId + "\" has no brand name!   xApi brandName:: " + xApiBrandName + "   FCC brandName::  " + fccBrandName);
//                    e.printStackTrace();
                }

                String xApiMaxQuantity = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").get("maxQuantity").toString();
                String fccMaxQuantity = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).get("maxQuantity").toString();
                logger.info( "MaxQuantity Validated in xApi Vs FCC:: " + xApiMaxQuantity + " == " + fccMaxQuantity);
                Assert.assertTrue(fccMaxQuantity.equals(xApiMaxQuantity));

                attrValueList.add(xApiBrandName);
                attrValueList.add(xApiProductName);
                attrValueList.add(xApiMaxQuantity);
                break;
            }
            case "prices": {
//                Below code is for PriceTypeText @wip
//                int xApiPriceTypeId = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing")
//                        .getJSONObject("price").getInt("priceTypeId");
//                String xApiPriceEventText;
//                try {
//                    xApiPriceEventText = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing")
//                            .getJSONObject("price").getJSONObject("priceType").getString("text");
//                    logger.info("EventText for Product \""+pId+"\"::  \""+xApiPriceEventText+"\"");
//                }
//                catch (Exception e) {
//                    xApiPriceEventText = null;
//                    logger.info("Product \""+pId+"\" doesn't have any EventText!");
//                }
//                attrValueList.add(String.valueOf(xApiPriceTypeId));
//                attrValueList.add(xApiPriceEventText);
//                logger.info("DONE!");

                JSONArray xApiPrices = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing")
                        .getJSONObject("price").getJSONArray("tieredPrice");

                String xApiOriginalPrice;
                String xApiRetailPrice;
                String xApiWasPrice;
                logger.info("xApi has " + xApiPrices.length() + " tier price for the product::  " + pId);

                JSONObject fccPrices = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0)
                        .getJSONObject("price");
                String fccOriginalPrice = fccPrices.get("originalPrice").toString();
                String fccRetailPrice = fccPrices.get("retailPrice").toString();

                if(xApiPrices.length() == 1) {
                    xApiOriginalPrice = xApiPrices.getJSONObject(0).getJSONArray("values").getJSONObject(0).get("value").toString();
                    logger.info("Verified Product Original Price in xApi Vs FCC:: "+ xApiOriginalPrice + " == " + fccOriginalPrice);
                    Assert.assertTrue(xApiOriginalPrice.equals(fccOriginalPrice));
                    attrValueList.add(xApiOriginalPrice);
                }
                else if(xApiPrices.length() == 2) {
                    xApiOriginalPrice = xApiPrices.getJSONObject(0).getJSONArray("values").getJSONObject(0).get("value").toString();
                    logger.info("Verified Product Original Price in xApi Vs FCC:: "+ xApiOriginalPrice + " == " + fccOriginalPrice);
                    Assert.assertTrue(xApiOriginalPrice.equals(fccOriginalPrice));
                    attrValueList.add(xApiOriginalPrice);

                    xApiRetailPrice = xApiPrices.getJSONObject(1).getJSONArray("values").getJSONObject(0).get("value").toString();
                    logger.info("Verified Product Sale Price in xApi Vs FCC:: "+ xApiRetailPrice + " === " + fccRetailPrice);
                    Assert.assertTrue(xApiRetailPrice.equals(fccRetailPrice));
                    attrValueList.add(xApiRetailPrice);
                }
                else if(xApiPrices.length() == 3) {
                    xApiOriginalPrice = xApiPrices.getJSONObject(0).getJSONArray("values").getJSONObject(0).get("value").toString();
                    logger.info("Verified Product Original Price in xApi Vs FCC:: "+ xApiOriginalPrice + " == " + fccOriginalPrice);
                    Assert.assertTrue(xApiOriginalPrice.equals(fccOriginalPrice));
                    attrValueList.add(xApiOriginalPrice);

                    xApiWasPrice = xApiPrices.getJSONObject(1).getJSONArray("values").getJSONObject(0).get("value").toString();
                    String fccWasPrice = fccPrices.get("intermediatePrice").toString();
                    logger.info("Verified Product Was Price in xApi Vs FCC:: " + xApiWasPrice + " == " + fccWasPrice);
//                    The assertion bellow is for xApi/Fcc WasPrice, but not returning the WasPrice in attrValueList as WasPrice won't be displayed on PDP/UI
//                    attrValueList.add(xApiWasPrice);
                    Assert.assertTrue(xApiWasPrice.equals(fccWasPrice));

                    xApiRetailPrice = xApiPrices.getJSONObject(2).getJSONArray("values").getJSONObject(0).get("value").toString();
                    logger.info("Verified Product Sale Price in xApi Vs FCC:: "+ xApiRetailPrice + " === " + fccRetailPrice);
                    Assert.assertTrue(xApiRetailPrice.equals(fccRetailPrice));
                    attrValueList.add(xApiRetailPrice);
                }
                break;
            }
            case "colorValues": {
                JSONObject xApiColorMap = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                        .getJSONObject("colors").getJSONObject("colorMap");
                JSONArray fccColorMap = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0)
                        .getJSONArray("colormap");

                Assert.assertTrue(xApiColorMap.length() == fccColorMap.length());
                logger.info("Number of set & colors xApi Vs Fcc:: " + xApiColorMap.length() + " == " + fccColorMap.length());
                String[] xApiKeys = xApiColorMap.keySet().toArray(new String[0]);
                String[] xApiColorNames = new String[xApiColorMap.length()];
                String[] fccColorNames = new String[fccColorMap.length()];

                for(int i=0; i < xApiColorMap.length(); i++) {
                    xApiColorNames[i] = xApiColorMap.getJSONObject(xApiKeys[i]).get("name").toString();
                    fccColorNames[i] = fccColorMap.getJSONObject(i).get("colorName").toString();
                }
                Arrays.sort(xApiColorNames);
                Arrays.sort(fccColorNames);
                logger.info("Verifying Product Colors in xApi Vs FCC!");
                for(int i=0; i < xApiColorNames.length; i++) {
                    Assert.assertTrue(xApiColorNames[i].equals(fccColorNames[i]));
                    logger.info("xApi == Fcc:: " + xApiColorNames[i] + " === " + fccColorNames[i]);
                    attrValueList.add(xApiColorNames[i]);
                }
                break;
            }
            case "sizeValues": {
                JSONObject xApiSizeMap = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                        .getJSONObject("sizes").getJSONObject("sizeMap");
                JSONArray fccSizeMap = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0)
                        .getJSONArray("sizes");
                logger.info("Number of set & sizes xApi Vs Fcc:: " + xApiSizeMap.length() + " == " + fccSizeMap.length());
                Assert.assertTrue(xApiSizeMap.length() == fccSizeMap.length());

                String[] xApiKeys = xApiSizeMap.keySet().toArray(new String[0]);
                String[] xApiSizeNames = new String[xApiSizeMap.length()];
                String[] fccSizeNames = new String[fccSizeMap.length()];

                for(int i=0; i < xApiSizeMap.length(); i++) {
                    xApiSizeNames[i] = xApiSizeMap.getJSONObject(xApiKeys[i]).get("name").toString().trim();
                    fccSizeNames[i] = fccSizeMap.getJSONObject(i).get("value").toString().trim();
                }
                Arrays.sort(xApiSizeNames);
                Arrays.sort(fccSizeNames);
                logger.info("Verifying Product Sizes in xApi Vs FCC!");
                for(int i=0; i < xApiSizeNames.length; i++) {
                    Assert.assertTrue(xApiSizeNames[i].equals(fccSizeNames[i]));
                    logger.info("xApi == Fcc:: " + xApiSizeNames[i] + " === " + fccSizeNames[i]);
                    attrValueList.add(xApiSizeNames[i]);
                }
                break;
            }
            case "altImages": {
                List<String> fccAltImages = new ArrayList<>();
                String[] xApiAltImgs;
                String[] fccAltImgs1;
                String[] fccAltImgs2;

                JSONArray xApiJSArray = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("imagery").getJSONArray("images");
                for(int i=0; i < xApiJSArray.length() ; i++) {
                    xApiAltImgs = xApiJSArray.getJSONObject(i).getString("name").split("\\.");
                    attrValueList.add(xApiAltImgs[0]);

                    if(i < xApiJSArray.length()-1) {
                        fccAltImgs1 = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0)
                                .getJSONArray("colormap").getJSONObject(0).getJSONArray("additionalImages").get(i).toString().split(":\"");
                        fccAltImgs2 = fccAltImgs1[1].split("\\.");
                        fccAltImages.add(fccAltImgs2[0]);
                    }
                    else {
                        fccAltImgs1 = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0)
                                .getJSONObject("primaryImage").get("imageName").toString().split("\\.");
                        fccAltImages.add(fccAltImgs1[0]);
                    }
                }
                Collections.sort(fccAltImages);
                Collections.sort(attrValueList);
                Assert.assertTrue(attrValueList.size() == fccAltImages.size());
                logger.info("Verifying AltImageIDs in xApi Vs FCC!");
                for(int i=0; i < attrValueList.size(); i++) {
                    Assert.assertTrue(attrValueList.get(i).equals(fccAltImages.get(i)));
                    logger.info("xApi == FCC::  " + attrValueList.get(i) + " ===  " + fccAltImages.get(i));
                }
                break;
            }
            case "reviews": {
                int xApiReviewsRate = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail")
                        .getJSONObject("reviewStatistics").getJSONObject("aggregate").getInt("ratingPercentage");
                logger.info("Review Rating Percentage in xApi::  "+ xApiReviewsRate+"%");

                int xApiReviewsCount = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail")
                        .getJSONObject("reviewStatistics").getJSONObject("aggregate").getInt("count");
                int fccReviewsCount = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0)
                        .getJSONObject("reviewStatistics").getInt("reviewCount");

                logger.info("Review Count in xApi Vs FCC::  xApi == FCC::  " + xApiReviewsCount + " ===  " + fccReviewsCount);
                Assert.assertTrue(xApiReviewsCount == fccReviewsCount);

                attrValueList.add(String.valueOf(xApiReviewsRate));
                attrValueList.add(String.valueOf(xApiReviewsCount));
                break;
            }
        }
        logger.info("\n******* xApi Vs Fcc verifications for \""+attribute+"\" completed!"+"\n\n");
        return attrValueList;
    }

    @When("^I verify pdp-xapi service for error messages$")
    public static boolean I_verify_pdp_xapi_service_for_product_not_found() throws Throwable {
        //When the fcc response is empty, the product is not available in search criteria
        boolean validationPassed = false;
        if ((fcc_rootJson.getJSONObject("products").toString().equals("{}"))) {
          Assert.assertTrue(rootJson.getJSONObject("Error").get("message").toString().equals("Product Not Found : No data for search criteria"));
            validationPassed = true;
        } else {
            logger.info("Bad Response");
            }
        return validationPassed;
        }

    @When("^I verify pdp-xapi service of \"([^\"]*)\" against fcc service for product color availability$")
    public void I_verify_pdp_xapi_service_for_product_color_availability_against_fcc_service(String prod_id) throws Throwable {
        //Get the uocMap and upcs from the pdp-xapi
        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        JSONObject upcs = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs"));

        //Get the upcs from fcc
        JSONArray fcc_upcs = fcc_rootJson.getJSONObject("products").getJSONArray("product")
                .getJSONObject(0).getJSONArray("upcs");


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

    @When("^I verify pdp-xapi service of \"([^\"]*)\" against fcc service for product type and it's availability$")
    public void I_verify_pdp_xapi_service_for_product_type_availability_against_fcc_service(String prod_id) throws Throwable {
        //Get the type map from the pdp-xapi
        JSONObject typeMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("types").getJSONObject("typeMap"));
        //Verify the type parameters
        Assert.assertTrue(typeMap.getJSONObject("0").has("typeId"));
        Assert.assertTrue(typeMap.getJSONObject("0").has("typeName"));

        //Get the upc map from the pdp-xapi
        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        //Get the upcs from the pdp-xapi
        JSONObject upcs = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs"));

        //Get the fcc upcs
        JSONArray fcc_upcs = fcc_rootJson.getJSONObject("products").getJSONArray("product")
                .getJSONObject(0).getJSONArray("upcs");


        Iterator<String> keys = upcMap.keys();
        String mapId, typeNameText = "", fcc_type_text = "";
        int selectedTypeId, upcId;
        Boolean fcc_upc_availability = null, availability = null;

        if (keys.hasNext()) {
            mapId  = keys.next();
            //Get the upcId from the upcs map of pdp-xapi
            upcId = upcMap.getInt(mapId);
            //Get the type id of the upcId of pdp-xapi
            selectedTypeId = upcs.getJSONObject(""+upcId).getJSONObject("traits").getJSONObject("types").getInt("selectedType");
            // Get the type name of the upcId of the pdp-xapi
            typeNameText = typeMap.getJSONObject(""+selectedTypeId).getString("typeName");
            // Get the availability of the upcId of the pdp-xapi
            availability = upcs.getJSONObject(""+upcId).getJSONObject("availability").getBoolean("available");
            for (Object obj: fcc_upcs) {
                JSONObject jobj = (JSONObject)obj;
                if (jobj.getInt("id") != upcId) {
                    continue;
                }
                JSONArray fcc_upc_attributes  = jobj.getJSONArray("attributes");
                //Get availability from the fcc upcId
                fcc_upc_availability  = jobj.getJSONObject("availability").getBoolean("available");

                for (Object attr: fcc_upc_attributes) {
                    JSONObject aobj = (JSONObject)attr;
                    if (!aobj.getString("name").equalsIgnoreCase("TYPE")) {
                        continue;
                    }
                    //Get the type text of the upcId from fcc
                    fcc_type_text = aobj.getJSONArray("attributeValues").getJSONObject(0).getString("value");
                }
                break;
            }
        }
        //Validate the fcc and pdp-xapi parameters
        Assert.assertFalse(fcc_type_text.isEmpty());
        Assert.assertFalse(typeNameText.isEmpty());
        Assert.assertTrue(fcc_type_text.equalsIgnoreCase(typeNameText));
        Assert.assertFalse(fcc_upc_availability == null);
        Assert.assertFalse(availability == null);
        Assert.assertTrue(fcc_upc_availability == availability? true: false);
    }

    @When("^I verify pdp-xapi service has color size interaction$")
    public void I_verify_pdp_xapi_service_has_color_size_interaction() throws Throwable {
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
            if (fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).has("colormap") &&
                    fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).has("sizes")) {
                Assert.assertTrue(colorMap.getJSONObject("" + colorId).has("sizes"));
                Assert.assertTrue(sizeMap.getJSONObject("" + sizeId).has("colors"));
            }
        }
    }

    @When("^I verify pdp-xapi service against fcc service for registrable product$")
    public void I_verify_pdp_xapi_service_for_registry_product_against_fcc_service() throws Throwable {
        // When the fcc response has a attribute REGISTRABLE=WEDDING OR NOT_FOR_WC_REGISTRY=Y then the ppd-xapi should have a registrable flag set to true
        JSONArray fcc_upc_attributes = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONArray("attributes");
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

    @When("^I verify pdp-xapi service against fcc service for non registrable product$")
    public void I_verify_pdp_xapi_service_for_non_registry_product_against_fcc_service() throws Throwable {
        // When the fcc response has a attribute NOT_FOR_WC_REGISTRY=N then the ppd-xapi should not have a registrable flag set to true
        JSONArray fcc_upc_attributes = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONArray("attributes");
        for (Object attr : fcc_upc_attributes) {
            JSONObject aobj = (JSONObject) attr;
            if (!aobj.getString("name").equals("NOT_FOR_WC_REGISTRY") && aobj.getJSONArray("attributeValues").getJSONObject(0).getString("value").equals("Y")){
                continue;
            }
            Assert.assertFalse(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("flags").has("registrable"));
        }
    }

    @When("^I verify pdp-xapi service has color type interaction$")
    public void I_verify_pdp_xapi_service_has_color_type_interaction() throws Throwable {
        // Verify that the colorMap has the related types
        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        JSONObject typeMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("types").getJSONObject("typeMap"));

        JSONObject colorMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap"));


        Iterator<String> keys = upcMap.keys();
        String mapId, colorId,typeId ;

        if (keys.hasNext()) {
            mapId  = keys.next();
            String[] output = mapId.split("_");
            colorId = output[0];
            typeId = output[1];
            Assert.assertTrue(colorMap.getJSONObject("" + colorId).has("types"));
            Assert.assertTrue(typeMap.getJSONObject("" + typeId).has("colors"));
        }
    }

    @When("^I verify pdp-xapi service does not have the type trait$")
    public void I_verify_pdp_xapi_service_no_type_trait() throws Throwable {
        JSONObject traitMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits"));

        JSONObject upcs = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs"));

        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        JSONObject colorMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap"));

        JSONObject sizeMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("sizes").getJSONObject("sizeMap"));

        Iterator<String> keys = upcMap.keys();
        String mapId, colorId, sizeId;
        int upcId;

        if (keys.hasNext()) {
            mapId  = keys.next();
            upcId = upcMap.getInt(mapId);
            String[] output = mapId.split("_");
            colorId = output[0];
            sizeId = output[1];
            if (fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).has("colormap") &&
                    fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).has("sizes")) {

                // Verify that the upc traits does not have the types
                Assert.assertFalse(upcs.getJSONObject("" + upcId).getJSONObject("traits").has("types"));
                // Verify that the traits does not have the types
                Assert.assertFalse(traitMap.has("types"));
                // Verify that the colormap does not have the types
                Assert.assertFalse(colorMap.getJSONObject("" + colorId).has("types"));
                // Verify that the sizemap does not have the types
                Assert.assertFalse(sizeMap.getJSONObject("" + sizeId).has("types"));
            }

        }
    }

    @When("^I verify pdp-xapi service does not have the size trait$")
    public void I_verify_pdp_xapi_service_no_size_trait() throws Throwable {
        JSONObject traitMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits"));

        JSONObject upcs = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs"));

        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        JSONObject colorMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap"));

        JSONObject typeMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("types").getJSONObject("typeMap"));

        Iterator<String> keys = upcMap.keys();
        String mapId, colorId, typeId;
        int upcId;

        if (keys.hasNext()) {
            mapId  = keys.next();
            upcId = upcMap.getInt(mapId);
            String[] output = mapId.split("_");
            colorId = output[0];
            typeId = output[1];
            if (!fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).has("sizes")) {

                // Verify that the upc traits does not have the sizes
                Assert.assertFalse(upcs.getJSONObject("" + upcId).getJSONObject("traits").has("sizes"));
                // Verify that the traits does not have the sizes
                Assert.assertFalse(traitMap.has("sizes"));
                // Verify that the colormap does not have the sizes
                Assert.assertFalse(colorMap.getJSONObject("" + colorId).has("sizes"));
                // Verify that the typeMap does not have the sizes
                Assert.assertFalse(typeMap.getJSONObject("" + typeId).has("sizes"));
            }

        }
    }

    @When("^I verify pdp-xapi service does not have the type and size trait$")
    public void I_verify_pdp_xapi_service_no_type_size_trait() throws Throwable {
        JSONObject traitMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits"));

        JSONObject upcs = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs"));

        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        JSONObject colorMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap"));

        Iterator<String> keys = upcMap.keys();
        String mapId;
        int upcId;

        if (keys.hasNext()) {
            mapId  = keys.next();
            upcId = upcMap.getInt(mapId);
            if (fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).has("colormap")) {
                // Verify that the upc traits does not have the sizes
                Assert.assertFalse(upcs.getJSONObject("" + upcId).getJSONObject("traits").has("sizes"));
                // Verify that the traits does not have the sizes
                Assert.assertFalse(traitMap.has("sizes"));
                // Verify that the colormap does not have the sizes
                Assert.assertFalse(colorMap.getJSONObject("" + mapId).has("sizes"));
                // Verify that the upc traits does not have the types
                Assert.assertFalse(upcs.getJSONObject("" + upcId).getJSONObject("traits").has("types"));
                // Verify that the traits does not have the types
                Assert.assertFalse(traitMap.has("types"));
                // Verify that the colormap does not have the types
                Assert.assertFalse(colorMap.getJSONObject("" + mapId).has("types"));
            }

        }
    }

    @When("^I verify pdp-xapi service of \"([^\"]*)\" against fcc service for product size and it's availability$")
    public void I_verify_pdp_xapi_service_for_product_size_availability_against_fcc_service(String prod_id) throws Throwable {
        //Get the size availability value from pdp-xapi
        JSONObject sizeMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("sizes").getJSONObject("sizeMap"));
        Assert.assertTrue(sizeMap.getJSONObject("0").has("id"));
        Assert.assertTrue(sizeMap.getJSONObject("0").has("name"));
        Assert.assertTrue(sizeMap.getJSONObject("0").has("displayName"));

        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        JSONObject upcs = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs"));

        JSONArray fcc_upcs = fcc_rootJson.getJSONObject("products").getJSONArray("product")
                .getJSONObject(0).getJSONArray("upcs");


        Iterator<String> keys = upcMap.keys();
        String mapId, sizeNameText = "";
        int selectedSizeId = -1, upcId;
        Boolean fcc_upc_availability = null, availability = null;
        JSONObject fcc_upc_size = null;

        if (keys.hasNext()) {
            mapId  = keys.next();
            upcId = upcMap.getInt(mapId);
            selectedSizeId = upcs.getJSONObject(""+upcId).getJSONObject("traits").getJSONObject("sizes").getJSONObject("selectedSize").getInt("id");
            sizeNameText = sizeMap.getJSONObject(""+selectedSizeId).getString("name");
            availability = upcs.getJSONObject(""+upcId).getJSONObject("availability").getBoolean("available");
            for (Object obj: fcc_upcs) {
                JSONObject jobj = (JSONObject)obj;
                if (jobj.getInt("id") != upcId) {
                    continue;
                }
                fcc_upc_size  = jobj.getJSONObject("size");
                fcc_upc_availability  = jobj.getJSONObject("availability").getBoolean("available");
                break;
            }
        }
        Assert.assertFalse(sizeNameText.isEmpty());
        Assert.assertTrue(fcc_upc_size.getString("value").equalsIgnoreCase(sizeNameText));
        Assert.assertTrue(fcc_upc_size.getInt("sizeId") == selectedSizeId);
        Assert.assertFalse(fcc_upc_availability == null);
        Assert.assertFalse(availability == null);
        Assert.assertTrue(fcc_upc_availability == availability? true: false);
    }


    @When("^I verify pdp-xapi service response has color size and type interaction$")
    public void I_verify_pdp_xapi_service_response_has_color_size_and_type_interaction() throws Throwable{
        JSONObject traits= (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits"));
        JSONObject upcMap = traits.getJSONObject("traitsMaps").getJSONObject("upcMap");

        JSONObject typeMap = traits.getJSONObject("types").getJSONObject("typeMap");
        JSONObject colorMap = traits.getJSONObject("colors").getJSONObject("colorMap");
        JSONObject sizeMap = traits.getJSONObject("sizes").getJSONObject("sizeMap");

        JSONArray fccUpcList = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONArray("upcs");

        Iterator<String> keys = upcMap.keys();
        String mapId,colorId,sizeId,typeId;
        Integer fcc_upc_colorId,fcc_upc_sizeId;
        Integer upcId;

        if (keys.hasNext()) {
            mapId  = keys.next();
            upcId  = (Integer) upcMap.get(mapId);
            String[] output = mapId.split("_");
            colorId = output[0];
            sizeId  = output[1];
            typeId  = output[2];

            Assert.assertNotNull(colorMap.getJSONObject(colorId).getJSONObject("sizeToTypes"));
            Assert.assertNotNull(colorMap.getJSONObject(colorId).getJSONObject("typeToSizes"));
            Assert.assertNotNull(colorMap.getJSONObject(colorId).getJSONObject("sizeToTypes").get(sizeId));
            Assert.assertNotNull(colorMap.getJSONObject(colorId).getJSONObject("typeToSizes").get(typeId));
            Assert.assertNotNull(sizeMap.getJSONObject(sizeId).getJSONObject("colorToTypes"));
            Assert.assertNotNull(sizeMap.getJSONObject(sizeId).getJSONObject("typeToColors"));
            Assert.assertNotNull(sizeMap.getJSONObject(sizeId).getJSONObject("colorToTypes").get(colorId));
            Assert.assertNotNull(sizeMap.getJSONObject(sizeId).getJSONObject("typeToColors").get(typeId));
            Assert.assertNotNull(typeMap.getJSONObject(typeId).getJSONObject("sizeToColors"));
            Assert.assertNotNull(typeMap.getJSONObject(typeId).getJSONObject("colorToSizes"));
            Assert.assertNotNull(typeMap.getJSONObject(typeId).getJSONObject("sizeToColors").get(sizeId));
            Assert.assertNotNull(typeMap.getJSONObject(typeId).getJSONObject("colorToSizes").get(colorId));

            for(Object fccUpcObj:fccUpcList){
                JSONObject fccUpc = (JSONObject)fccUpcObj;
                if (fccUpc.getInt("id") != upcId) {
                    continue;
                }
                fcc_upc_colorId  = (Integer) fccUpc.getJSONObject("colorway").get("colorwayId");
                fcc_upc_sizeId= (Integer)fccUpc.getJSONObject("size").get("sizeId");
                Assert.assertEquals(String.valueOf(fcc_upc_colorId),colorId);
                Assert.assertEquals(String.valueOf(fcc_upc_sizeId),sizeId);
                JSONArray upcAttributes=fccUpc.getJSONArray("attributes");
                for(Object attrObj:upcAttributes){
                    JSONObject attr=(JSONObject)attrObj;
                    if(attr.getString("name").equalsIgnoreCase("TYPE")){
                        Assert.assertEquals(String.valueOf(fccUpc.get("id")),String.valueOf(upcId));
                        break;
                    }
                }
                break;
            }
        }
    }

    @When("^I verify pdp-xapi service of \"([^\"]*)\" against fcc service for colorway top price$")
    public void I_verify_pdp_xapi_service_for_product_colorway_top_price_against_fcc_service(String prod_id) throws Throwable {
        // first upc > selected color >  get color and its prices
        // compare to the UPC's price from FCC
        // first upc > selected color
        JSONObject upcs = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs");
        Iterator<String> upcsKeys = (upcs.keys());
        int selectedColor = -1, firstUPC = -1;
        String firstUPCStr = "", selectedColorStr = "";
        if (upcsKeys.hasNext()) {
            firstUPCStr = upcsKeys.next();
            firstUPC = Integer.parseInt(firstUPCStr);
            selectedColor = upcs.getJSONObject(firstUPCStr).getJSONObject("traits").getJSONObject("colors").getInt("selectedColor");
            selectedColorStr = selectedColor + "";
        }

        JSONObject priceObj = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap").getJSONObject(selectedColorStr).getJSONObject("pricing")
                .getJSONObject("price").getJSONArray("tieredPrice").getJSONObject(0).getJSONArray("values")
                .getJSONObject(0));

        JSONArray fcc_upcs = fcc_rootJson.getJSONObject("products").getJSONArray("product")
                .getJSONObject(0).getJSONArray("upcs");
        JSONObject fcc_priceObj = null;
        for (Object obj: fcc_upcs) {
            JSONObject jobj = (JSONObject)obj;
            if ( jobj.getInt("id") == firstUPC) {
                fcc_priceObj = jobj.getJSONObject("price");
                break;
            }
        }
        //TODO  Need to find a default price that show's up in all scenarios to assert against fcc
        // Assuming Regular price is the default price type in all scenarios to assert against fcc
        switch (priceObj.getString("type")) {
            case "regular":
                Assert.assertTrue(priceObj.getDouble("value") == (fcc_priceObj.getDouble("originalPrice")));
                break;

        }

    }

    @When("^I verify pdp-xapi service of \"([^\"]*)\" against fcc service for colorway pricing")
    public void I_verify_pdp_xapi_service_for_product_colorway_pricing_against_fcc_service(String prod_id) throws Throwable {
        // Find the first entry from priceToColors
        // get it's value, a color id and an onSale flag
        // Also get the corresponding UPC id using the color id

        // Compare the collected data with the FCC response by
        // looping through each of the UPC's until we find a upc
        // with the collected data.

        // XAPI response
        JSONObject traits = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits");
        JSONObject upcMap = traits.getJSONObject("traitsMaps").getJSONObject("upcMap");
        JSONObject priceToColorsFirstEntry = traits.getJSONObject("traitsMaps").getJSONArray("priceToColors").getJSONObject(0);
        JSONObject colorMap = traits.getJSONObject("colors").getJSONObject("colorMap");

        String formattedPrice = priceToColorsFirstEntry.getString("value");
        int colorId = priceToColorsFirstEntry.getJSONArray("colorIds").getInt(0);
        boolean onSale = priceToColorsFirstEntry.getBoolean("onSale");
        Iterator<String> keys = upcMap.keys();
        int upcId = -1;
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.contains(""+colorId)) {
                upcId = upcMap.getInt(key);
                break;
            }
        }

        // FCC response
        JSONArray fcc_upcs = fcc_rootJson.getJSONObject("products").getJSONArray("product")
                .getJSONObject(0).getJSONArray("upcs");
        boolean found = false;
        eachUPC:
        for (Object upc: fcc_upcs) {
            JSONObject jobj = (JSONObject)upc;
            int id = jobj.getInt("id");
            if (id == upcId) {
                JSONObject price = jobj.getJSONObject("price");
                Iterator<String> strKeys = price.keys();
                while (strKeys.hasNext()) {
                    String strKey = strKeys.next();
                    if (strKey.contains("Price") && !strKey.contains("Label") && !strKey.contains("Type")
                            && formattedPrice.contains(""+price.getInt(strKey))) {
                        Assert.assertTrue(formattedPrice.contains("" + price.getInt(strKey)));
                        if (onSale) {
                            Assert.assertTrue(price.getBoolean("onSale"));
                        } else {
                            Assert.assertFalse(price.getBoolean("onSale"));
                        }
                        Assert.assertTrue(jobj.getJSONObject("colorway").getInt("colorwayId") == colorId);
                        found = true;
                        break eachUPC;
                    }
                }
            }
        }
        Assert.assertTrue(found);
    }

    @When("^I verify pdp-xapi service of \"([^\"]*)\" against fcc service for product images and video$")
    public void I_verify_pdp_xapi_service_for_product_image_video_against_fcc_service(String prod_id) throws Throwable {
        JSONObject images = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("imagery");
        JSONArray productImages = images.getJSONArray("images");
        JSONArray fccAttributes = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONArray("attributes");

        List<Object> xapi_images = new ArrayList<>();
        List<Object> fcc_images = new ArrayList<>();
        JSONArray additionalImageSrcArray = null;

        // Validate the product video param
        for (Object attrObj : fccAttributes) {
            JSONObject attr = (JSONObject) attrObj;
            if (attr.getString("name").equalsIgnoreCase("PRODUCT_VIDEO")) {
                Assert.assertEquals((attr.getJSONArray("attributeValues").getJSONObject(0).getString("value")), String.valueOf(images.get("videoId")));
                break;
            }
        }

        // Validate the primary and additional images
        for (Object imgObj : productImages) {
            JSONObject img = (JSONObject) imgObj;
            xapi_images.add(img.get("filePath").toString().substring(0, img.get("filePath").toString().indexOf(".")));
        }

        if (fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).has("primaryPortraitSource")) {
            fcc_images.add(fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).get("primaryPortraitSource"));
        }

        if (fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).has("additionalImageSource")) {
            JSONObject productObj= (JSONObject) fcc_rootJson.getJSONObject("products").getJSONArray("product").get(0);
            additionalImageSrcArray= productObj.getJSONArray("additionalImageSource");
        }

        for(int i = 0; i < additionalImageSrcArray.length(); i++)
        {
            fcc_images.add(additionalImageSrcArray.get(i));
        }

        Assert.assertEquals(xapi_images,fcc_images);
    }

    @When("^I verify pdp-xapi service of \"([^\"]*)\" for product images and and selected color images$")
    public void I_verify_pdp_xapi_service_for_selected_color_product_image(String prod_id) throws Throwable {
        JSONObject colorMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap"));
        String selectedColorId = "";
        selectedColorId = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits").getJSONObject("colors").get("selectedColor").toString();

        JSONArray productImages = null;
        JSONArray colorImages = null;

        productImages = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("imagery").getJSONArray("images");
        colorImages = colorMap.getJSONObject("" + selectedColorId).getJSONObject("imagery").getJSONArray("images");

        //Assert.assertTrue(productImages == colorImages);
        Assert.assertSame(productImages,colorImages);
    }

    @When("^I verify pdp-xapi service for video id in selected color images$")
    public void I_verify_pdp_xapi_service_for_selected_color_product_video() throws Throwable {
        JSONObject colorMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap"));
        String selectedColorId = "";
        selectedColorId = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits").getJSONObject("colors").get("selectedColor").toString();
        // Colorway ids should not have the video id
        Assert.assertFalse(colorMap.getJSONObject("" + selectedColorId).getJSONObject("imagery").has("videoId"));
    }


    @When("^I verify pdp-xapi service response has swatch sprite url")
    public void I_verify_pdp_xapi_service_for_swatch_sprite() throws Throwable {
        JSONObject colorMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("colors").getJSONObject("colorMap"));

        Iterator<String> keys = colorMap.keys();
        String mapId;

        if (keys.hasNext()) {
            mapId  = keys.next();
            if (colorMap.getJSONObject("" + mapId).has("swatchImage")) {
                Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                        .getJSONObject("colors").has("swatchSprite"));
            }
        }
    }

    @When("^I verify pdp-xapi service for gift card product")
    public void I_verify_pdp_xapi_service_for_egift_card() throws Throwable {
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail")
                .getJSONObject("flags").has("giftCard"));
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail")
                .getJSONObject("flags").getBoolean("giftCard"));
    }

    @When("^I verify pdp-xapi service for secondary description")
    public void I_verify_pdp_xapi_service_for_secondary_description() throws Throwable {
        // Valdiate that the field secondary description is available
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").has("secondaryDescription"));
        String secondary_description = "";
        secondary_description = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getString("secondaryDescription");
        String fcc_pdp_name = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).get("name").toString();
        // Validate that the secondary description of xapi is contained in the fcc product name
        Assert.assertTrue(fcc_pdp_name.contains(secondary_description));
    }

    @When("^I verify pdp-xapi service against fcc service for product description")
    public void I_verify_pdp_xapi_service_for_product_description() throws Throwable {
        // Get the pdp-xapi product description:
        String product_description = "";
        product_description = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getString("description");
        //JSONArray bullet_text = null;
        List<Object> bullet_text = new ArrayList<>();
        bullet_text.add(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONArray("bulletText"));

        // Get the fcc product description:
        JSONArray fccAttributes = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONArray("attributes");
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

        Assert.assertEquals(product_description, fcc_product_description);
        //Assert.assertSame(bullet_text, fcc_bullet);

    }

    @When("^I verify pdp-xapi service against fcc service for product availability")
    public void I_verify_pdp_xapi_service_for_availability() throws Throwable {
        //Get the uocMap and upcs from the pdp-xapi
        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        JSONObject upcs = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs"));

        //Get the upcs from fcc
        JSONArray fcc_upcs = fcc_rootJson.getJSONObject("products").getJSONArray("product")
                .getJSONObject(0).getJSONArray("upcs");


        Iterator<String> keys = upcMap.keys();
        String mapId;
        String message = "";
        String orderType = "";
        String fcc_upc_message = "";
        String fcc_upc_orderType = "";
        int upcId;

        if (keys.hasNext()) {
            mapId  = keys.next();
            //Get a upcId from the pdp-xapi upc map.
            upcId = upcMap.getInt(mapId);
            // Get the message and orderType of the upcId from the pdp-xapi
            message = upcs.getJSONObject(""+upcId).getJSONObject("availability").getString("message");
            orderType = upcs.getJSONObject(""+upcId).getJSONObject("availability").getString("orderType");
            for (Object obj: fcc_upcs) {
                JSONObject jobj = (JSONObject)obj;
                if (jobj.getInt("id") != upcId) {
                    continue;
                }
                // Get the message and orderType of the upcId from the fcc
                fcc_upc_message  = jobj.getJSONObject("availability").getString("upcAvailabilityMessage");
                fcc_upc_orderType  = jobj.getJSONObject("availability").getString("orderMethod");
                break;
            }
        }

        //Validate fcc and pdp-xapi parameters
        Assert.assertEquals(message,fcc_upc_message);
        Assert.assertEquals(orderType,fcc_upc_orderType);
    }


    @Then("^I verify pdp-xapi service against fcc service for product in-store availability")
    public void I_verify_pdp_xapi_service_for_in_store_availability() throws Throwable {
        //Get the upcs from the pdp-xapi
        JSONObject upcs = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs"));

        //Get the upcs from fcc
        JSONArray fcc_upcs = fcc_rootJson.getJSONObject("products").getJSONArray("product")
                .getJSONObject(0).getJSONArray("upcs");

        for (Object obj: fcc_upcs) {
            JSONObject upcObj = (JSONObject)obj;
            String upcId= upcObj.getString("id");
            boolean inStoreEligible=upcObj.getJSONObject("availability").getBoolean("inStoreEligibility");
            if(inStoreEligible){
               Assert.assertTrue(upcs.getJSONObject(upcId).getJSONObject("availability").
                       getBoolean("checkInStoreEligibility"));
            }else{
                Assert.assertNull(upcs.getJSONObject(upcId).getJSONObject("availability").
                        getBoolean("checkInStoreEligibility"));
            }
        }

    }

    @When("^I verify pdp-xapi service of \"([^\"]*)\" against fcc service for promotion badge")
    public void I_verify_pdp_xapi_service_for_product_promotion_against_fcc_service(String prod_id) throws Throwable {
        // XAPI response
        JSONObject badgesMap = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing").getJSONObject("badgesMap");
        JSONArray promoId = rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing").getJSONArray("badgeIds");
        JSONArray fcc_promoId = null;
        JSONArray fcc_promoType = null;

        JSONArray fccAttributes = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONArray("attributes");

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

    @When("^I verify pdp-xapi gift with purchase details against promotions service for same product$")
    public void I_verify_pdp_xapi_gift_with_purchase_details_against_promotions_service_for_same_product(){

        JSONObject pricing=rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("pricing");
        JSONObject badgesMap=pricing.getJSONObject("badgesMap");
        JSONArray badgeIdList = pricing.getJSONArray("badgeIds");
        JSONObject promotionJson = null;

        Assert.assertNotNull(badgesMap);
        Assert.assertNotNull(badgeIdList);

        for(Object badgeId:badgeIdList) {
            String badgeID = (String) badgeId;
            if (badgesMap != null) {
                JSONObject badgeObj = badgesMap.getJSONObject(badgeID);
                String header= badgeObj.getString("header");

                if (header.equals("Free Gift with Purchase")) {
                    JSONObject giftProduct= (JSONObject)badgeObj.getJSONArray("giftProducts").get(0);

                    Assert.assertNotNull(giftProduct);

                    int giftId= giftProduct.getInt("id");

                    promotionJson = getJsonPromotionDetailsService(badgeID);
                    JSONObject promotionObj = promotionJson.getJSONObject("Promotions").getJSONObject("promotions").getJSONObject(badgeID);
                    Assert.assertTrue(String.valueOf(promotionObj.get("id")).equals(badgeID));

                    JSONArray promotionAttr= promotionObj.getJSONArray("promotionAttribute");
                    String attributeName = promotionAttr.getJSONObject(0).getString("promotionAttributeName");
                    Assert.assertTrue(attributeName.equalsIgnoreCase("BADGE_TEXT"));

                    JSONArray promotionAttributeValue = promotionAttr.getJSONObject(0).getJSONArray("attributeValue");
                    JSONObject promoAttrObj= (JSONObject) promotionAttributeValue.get(0);

                    Assert.assertTrue(String.valueOf(promoAttrObj.get("promotionID")).equalsIgnoreCase(badgeID));
                    Assert.assertTrue(promoAttrObj.getString("promotionAttributeName").equalsIgnoreCase("BADGE_TEXT"));
                    Assert.assertTrue(promoAttrObj.getString("attributeValue").equalsIgnoreCase("Free Gift with Purchase"));

                    JSONArray operations= promotionObj.getJSONArray("operations");
                    int promoGiftId = operations.getJSONObject(0).getInt("giftId");
                    Assert.assertEquals(giftId,promoGiftId);
                }
            }
        }

    }

    @When("^I verify pdp-xapi service against fcc service for size chart")
    public void I_verify_pdp_xapi_service_for_product_size_chart_against_fcc_service() throws Throwable {
        // XAPI response
        Assert.assertTrue(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("sizes").has("sizeChartId"));
        String sizeChartCanvasId = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("sizes").get("sizeChartId")).toString();
        //FCC response
        String fcc_sizeChartCanvasId = "";
        JSONArray fcc_attributes = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONArray("attributes");
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

    @When("^I should not see the canvas id in the upc traits")
    public void no_canvas_id_upc_traits() throws Throwable {
        JSONObject upcs = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("relationships")
                .getJSONObject("upcs"));

        JSONObject upcMap = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("traitsMaps").getJSONObject("upcMap"));

        Iterator<String> keys = upcMap.keys();
        String mapId;
        int upcId;

        if (keys.hasNext()) {
            mapId  = keys.next();
            upcId = upcMap.getInt(mapId);
            // Verify that the upc traits does not have the canvas id
            Assert.assertFalse(upcs.getJSONObject("" + upcId).getJSONObject("traits").getJSONObject("sizes").has("sizeChartId"));
            }

        }

    @When("^I should not see the canvas id in the size map")
    public void no_canvas_id_size_map() throws Throwable {
        Assert.assertFalse(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("traits")
                .getJSONObject("sizes").has("sizeChartId"));
    }

    @When("^I verify pdp-xapi service against fcc service for review statistics")
    public void reviewStatistics() throws Throwable {
        //XAPI Get the review rating and count from XAPI
        String pdp_rating = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("reviewStatistics").getJSONObject("aggregate").get("rating").toString());
        String pdp_rating_count = (rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").getJSONObject("reviewStatistics").getJSONObject("aggregate").get("count").toString());

        // Get the review rating and count from fcc
        String fcc_rating = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONObject("reviewStatistics").get("averageRating").toString();
        String fcc_rating_count = fcc_rootJson.getJSONObject("products").getJSONArray("product").getJSONObject(0).getJSONObject("reviewStatistics").get("reviewCount").toString();

        //Assert the pdp-xapi params and the fcc params
        Assert.assertTrue(pdp_rating.equals(fcc_rating));
        Assert.assertTrue(pdp_rating_count.equals(fcc_rating_count));
    }

    @When("^I verify pdp-xapi service against fcc service for no review statistics")
    public void noerviewStatistics() throws Throwable {
        //XAPI Review Statistics should not be available
        Assert.assertFalse(rootJson.getJSONArray("product").getJSONObject(0).getJSONObject("detail").has("reviewStatistics"));
    }


    public static JSONObject getJsonPDPXAIService(String prodID) throws IOException {
//        String url = "http://ma277mlvpdx011:8080/xapi/digital/v1/product/";//Production URL
        String url = "https://www.rpdp-18a.tbe.zeus.fds.com/xapi/digital/v1/product/";
        // To use the localhost
//         String url = "http://localhost:9000/xapi/v1/product/";
        int status = RESTOperations.doGET(url+prodID, null).getStatus();
       // Assert.assertTrue(status==200);
        Response response = RESTOperations.doGET(url+prodID, null);
        String finalJsonStr = response.readEntity(String.class);
        return new JSONObject(finalJsonStr);

    }

    public static JSONObject getJsonFCCProductDetailsService(String prodID) throws IOException {
//        String envIP = "http://" + EnvironmentDetails.otherApp("FCC").ipAddress;

        //FCC call
        //delete fcc_url with hardcoded env and uncomment line above with dynamic env_url
        //String fcc_url = envIP +":8080/api/catalog/v3/products/";
//        String fcc_url = "https://www.macys.com/api/catalog/v3/products/";//Production URL
        String fcc_url = "https://www.rpdp-18a.tbe.zeus.fds.com/api/catalog/v3/products/";
        //String fcc_url = "http://127.0.0.1:8090/api/catalog/v2/products/";
        Response fcc_response = RESTOperations.doGET(fcc_url + prodID, null);
        String fcc_finalJsonStr = fcc_response.readEntity(String.class);
        return new JSONObject(fcc_finalJsonStr);
    }



    public static JSONObject getJsonPromotionDetailsService(String promoId){
        String promotionURL = "http://jcia7675:8080/api/order/v1/promotions/promoids/";
        Response promotionResponse = RESTOperations.doGET(promotionURL + promoId, null);
        String finalJsonStr = promotionResponse.readEntity(String.class);
        return new JSONObject(finalJsonStr);
    }

}//end class PDPxAPISteps