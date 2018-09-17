package com.macys.sdt.shared.actions.website.bcom.pages;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.user.LoyalistDetails;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.ObjectMapperProvider;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class LoyallistAssociation extends StepUtils {
    private static String ltyRegion="";

    public static void
    loyaltyAssociation(LoyalistDetails loyalty_customer) {
        Wait.forPageReady();
        TextBoxes.typeTextbox("loyalty_association.loyallist_number", loyalty_customer.getLoyaltyId());
        TextBoxes.typeTextbox("loyalty_association.loyallist_last_name", loyalty_customer.getLastName());
        TextBoxes.typeTextbox("loyalty_association.loyallist_zip_code", loyalty_customer.getZipCode());
        try {
            Clicks.click("loyalty_association.submit_id");
            Assert.assertFalse("ERROR-DATA: Sorry, we were unable to locate your Loyallist account. Please add valid data", Elements.elementPresent("loyalty_association.lty_error"));
        } catch (Exception e) {
            Assert.fail("Failed to validate loyallist data in the page: " + e);
        }
    }

    // 2017-11-07 Ravi: Please use LoyallistAssociation.getLoyallistDetails instead of TestUsers.getLoyallistDetails
    // as region specific data is get from LoyallistAssociation.getLoyallistDetails
    /**
     * Gets a valid USL id from "loyalty.json"
     *
     * @param loyallistType type of loyallist
     * @return JSONObject containing loyallist information
     */
    public static LoyalistDetails getLoyallistDetails(String loyallistType) {
        return getLoyallistDetails(loyallistType, null);
    }

    /**
     * Gets a random valid USL id from "loyalty.json"
     * <p>
     * example to get user with over 2500 points:
     * <br>
     * <code>LoyalistDetails details = getLoyallistDetails("base_tier", (detail) -&gt; detail.getPoints() &gt; 2500);
     * </code>
     * </p>
     *
     * @param loyallistType type of loyallist (may be null)
     * @param filterBy      Predicate to filter results by (may be null)
     * @return JSONObject containing loyallist information
     */
    public static LoyalistDetails getLoyallistDetails(String loyallistType, Predicate<LoyalistDetails> filterBy) {
        try {
            JSONArray loyallistDetails;
            File loyaltyFile = getResourceFile("loyalty.json");
            String jsonTxt = Utils.readTextFile(loyaltyFile);
//            Random rand = new Random();

            // yc03673 2017-11-03 Ravi: Modified the method to get correct loyalty data according to the lty region it belongs to.
            ltyRegion = ltyRegion.equals("")?getLoyaltyRegion():ltyRegion;
            JSONObject json = new JSONObject(jsonTxt);
            loyallistDetails = (JSONArray) json.get(ltyRegion);
            String regionLtyTxt = loyallistDetails.toString();


            //JSON from file to Object
            //  List<LoyalistDetails> loyalistDetailsList = ObjectMapperProvider.getJsonMapper().readValue(jsonTxt,
            List<LoyalistDetails> loyalistDetailsList = ObjectMapperProvider.getJsonMapper().readValue(regionLtyTxt,
                    TypeFactory
                            .defaultInstance()
                            .constructCollectionType(List.class, LoyalistDetails.class));

            if (loyallistType != null && !loyallistType.isEmpty()) {
                loyalistDetailsList = loyalistDetailsList.stream()
                        .filter(loyalistDetails -> loyalistDetails.getLoyallistType()
                                .toString()
                                .equalsIgnoreCase
                                        (loyallistType))
                        .collect(Collectors.toList());
            }
            if (filterBy != null) {
                loyalistDetailsList = loyalistDetailsList.stream()
                        .filter(filterBy)
                        .collect(Collectors.toList());
            }
//            return loyalistDetailsList.get(rand.nextInt(loyalistDetailsList.size()));
            return loyalistDetailsList.get(0);
        } catch (Exception e) {
            Assert.fail("Unable to parse JSON: " + e);
            return null;
        }
    }

    /**
     * Gets the loyalty region of the current test environment by fetching it from MSPCustomer getPropertiesInfo
     *
     * @return String region (QAT02/QAT03/UAT02) of current environment under test
     */
    public static String getLoyaltyRegion() {
        String urlCusotmerProp = "http://" + EnvironmentDetails.otherApp("mspcustomer").hostName + ":8080/sdp/sdp-admin/admin-service/getPropertiesInfo";
        WebDriver driver = WebDriverManager.getWebDriver();
        Set<String> windows = driver.getWindowHandles();
        String mainWindowHandle = driver.getWindowHandle();
        ((JavascriptExecutor) driver).executeScript("window.open();");
        Set<String> customerWindow = driver.getWindowHandles();
        customerWindow.removeAll(windows);
        String customerHandle = ((String) customerWindow.toArray()[0]);
        driver.switchTo().window(customerHandle);
        driver.get(urlCusotmerProp);

        String ltyCustomerUrl = driver.findElement(By.xpath("//tr[./td[text()='externalservice.LoyaltyServiceV11CusEnrollAdapter.CusEnroll.address']]//td[2]")).getText();
        driver.close();
        driver.switchTo().window(mainWindowHandle);
        // splitting the url to extract the port number of the service
        //ex: https://ltyservicestest:10683/Loyalty/Services/Customer/LTY_Customer_v11
        String servicePort = ltyCustomerUrl.split(":")[2].split("/")[0];
        String region = "";
        switch (servicePort) {
            case "10682":
                region = "QAT02";
                break;
            case "10683":
                region = "QAT03";
                break;
            case "10684":
                region = "QAT04";
                break;
            case "10685":
                region = "UAT02";
                break;
            default:
                // Currently there are only 3 regions QAT02/QAT03/UAT02 in use.
                // We will have to add in the list of case if any new region gets added
        }
        if (region.equals("")) {
            Assert.fail("No valid region returned that matched for the MSPCustomer Property with the loyalty customer service port: " + servicePort);
        }
        return region;
    }


}
