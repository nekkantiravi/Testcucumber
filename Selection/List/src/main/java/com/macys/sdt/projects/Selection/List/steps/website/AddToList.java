package com.macys.sdt.projects.Selection.List.steps.website;

/**
 * Created by m657444 on 8/10/17.
 */

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.projects.Selection.List.actions.responsive.mcom.AddToListService;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.io.File;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Logger;


public class AddToList extends StepUtils {

    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static String productId = "";
    private static String productUpc = "";
    SoftAssertions softly = new SoftAssertions();

    // Current GC DB2 info - mcom-list-17v
    private static String dbName = "db2";
    private static String dbUrl = "jdbc:db2://172.17.1.228:50001/MGSITE";
    private static String dbUsername = "db2mcys";
    private static String dbPassword = "xj501we";



    @When("^I navigate directly to member PDP and add a product to my list$")
    public void I_navigate_directly_to_PDP_and_add_a_product_to_my_list() throws Throwable {
        String[] products = ListPIDs.memberProdID();
        int index = new Random().nextInt(products.length);
        productId = products[index];
        TextBoxes.typeTextNEnter("home.search_field", productId);
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("product_display.add_to_wishlist_image", 20);
            //softly.assertThat(Elements.elementPresent("pdp.productTitle")).as("add_to_wishlist_image button").isEqualTo(true);
        } catch (Exception e) {
            logger.warning(String.format("Product title not displayed on member PDP\n"));
            e.printStackTrace();
        }
        Clicks.click(Elements.element("product_display.add_to_wishlist_image"));
        Thread.sleep(10000);
        Wait.secondsUntilElementNotPresent("product_display.wishlist_overlay", 20);
        Clicks.click(Elements.element("product_display.wishlist_link"));
        Thread.sleep(5000);
    }

    @And("^I verify basic components of list page$")
    public void I_verify_basic_components_of_list_page() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("wish_list.wishlist_title", 20);
        } catch (Exception e) {
            logger.warning(String.format("List page is not loading properly!\n"));
            e.printStackTrace();
        }
        String listTitle = Elements.findElement(Elements.element("wish_list.wishlist_title")).getText();
        softly.assertThat(listTitle.isEmpty()).as("wishlist_title").isEqualTo(false);
    }



    @And("I do add to bag from List page$")
    public void I_do_add_to_bag_from_List_page() throws Throwable {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("wish_list.add_to_bag_btn", 20);
        } catch (Exception e) {
            logger.warning(String.format("Item is not displayed on List Page!\n"));
            e.printStackTrace();
        }
        Clicks.click(Elements.element("wish_list.add_to_bag_btn"));
        Thread.sleep(10000);
        Wait.secondsUntilElementNotPresent("wish_list.add_to_bag_dialog", 20);
        Clicks.click(Elements.element("wish_list.checkout"));
        Thread.sleep(5000);

        //String listTitle = Elements.findElement(Elements.element("wish_list.wishlist_title")).getText();
        //softly.assertThat(listTitle.isEmpty()).as("wishlist_title").isEqualTo(false);
    }




    @When("^I select a \"([^\"]*)\" product and navigate to PDP in \"([^\"]*)\" mode$")
    public void I_select_a_product_and_navigate_to_PDP(String productType, String mode) throws Throwable {
        switch (mode) {
            case "site": {
                switch (productType) {
                    case "member": {
                        String[] products = ListPIDs.memberProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                    case "master": {
                        String[] products = ListPIDs.masterProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                    case "chanel": {
                        String[] products = ListPIDs.memberProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                    case "BigTicket": {
                        String[] products = ListPIDs.bigTicketProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                    case "registry": {
                        String[] products = ListPIDs.memberProdID();
                        int index = new Random().nextInt(products.length);
                        productId = products[index];
                        break;
                    }
                    }
                }
                break;
            }


        TextBoxes.typeTextNEnter("home.search_field", productId);
        Thread.sleep(10000);
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp.productTitle", 20);
        } catch (Exception e) {
            logger.warning(String.format(productType + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!"));
            e.printStackTrace();
        }
    }

    @Then("^I click on A2L button on PDP page$")
    public void add_to_List_from_PDP() throws Throwable
    {
        try {
            Wait.forPageReady();
            Wait.secondsUntilElementPresent("pdp_list.add_to_list", 30);
            Clicks.click(Elements.element("pdp_list.add_to_list"));
            Thread.sleep(10000);
        } catch (Exception e) {
            logger.warning(String.format("Couldn't perform add to list on PDP page as the button was not displayed"));
            e.printStackTrace();
        }
    }

    @Then("^I click on ALBigTicket button on PDP page$")
    public void i_click_on_ALBigTicket_button_on_PDP_page() throws Throwable {
        try {
            Wait.forPageReady();
            Clicks.click(Elements.element("pdp_list.pdp_type"));
            TextBoxes.typeTextbox("pdp_list.pdp_zipcode","94107");
            Wait.secondsUntilElementPresent("pdp_list.add_to_list", 30);
            Clicks.click(Elements.element("pdp_list.add_to_list"));
            Thread.sleep(10000);
        } catch (Exception e) {
            logger.warning(String.format("Couldn't perform add to list on PDP page as the button was not displayed"));
            e.printStackTrace();
        }

    }

    @Then("^I verify the list overlay popup on PDP page for \"([^\"]*)\" user$")
    public void verify_list_overlay_popup_on_PDP(String user){
        if (user.equals("Guest")) {

            Wait.secondsUntilElementPresent("pdp_list.addToListOverlay", 15);
            softly.assertThat(Elements.elementPresent("pdp_list.addToListMsgPoppup")).as("addToListMsgPoppup").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp_list.addToListSigninlink")).as("addToListSigninlink").isEqualTo(true);
        }
        else if(user.equals("SignedIn")){
            Wait.secondsUntilElementPresent("pdp_list.addToListOverlay", 15);
            softly.assertThat(Elements.elementPresent("pdp_list.addToListMsgPoppup")).as("the List link on the popup").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp_list.addToListOverlay")).as("The overlay is not coming up on pdp page").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("pdp_list.addToListSigninlink")).as("addToListSigninlink").isEqualTo(false);
            logger.info(String.format("pdpupmessage"+ Elements.findElement(Elements.element("pdp_list.addToListMsgPoppup")).getText()));
        }
        else
        {
            logger.warning("The usertype didnot match" +user );
        }


    }


    @And("^I click \"([^\"]*)\" on \"([^\"]*)\" on (member|master) PDP (site) mode$")
    public void click_links_tabs_on_overlays_on_PDP(String link, String arg, String pg, String mode) throws Throwable {

        switch(mode) {
            case "site": {
                switch(pg) {
                    case "member": {
                        if(link.equalsIgnoreCase("Signin link")) {
                            if(!(arg.equalsIgnoreCase("A2L session expired popup"))){
                                Wait.secondsUntilElementPresent("pdp_list.addToListMsgPoppup", 15);
                                softly.assertThat(Elements.elementPresent("pdp_list.addToListMsgPoppup")).as("addToListMsgPoppup").isEqualTo(true);
                            }
                            softly.assertThat(Elements.elementPresent("pdp.addToListSigninLinkPoppup")).as("addToListSigninLinkPoppup").isEqualTo(true);
                            Thread.sleep(3000);
                            Clicks.click(Elements.element("pdp_list.addToListSigninLinkPoppup"));
                        }
                        else if(link.equalsIgnoreCase("List link")) {
                            Wait.secondsUntilElementPresent("pdp_list.addToListMsgPoppup", 15);
                            softly.assertThat(Elements.elementPresent("pdp_list.addToListMsgPoppup")).as("addToListMsgPoppup").isEqualTo(true);
                            softly.assertThat(Elements.elementPresent("pdp_list.addToListPopupListLink")).as("addToListPopupListLink").isEqualTo(true);
                            Thread.sleep(3000);
                            Clicks.click(Elements.element("pdp_list.addToListPopupListLink"));
                        }
                        else if(link.equalsIgnoreCase("DefaultList link")) {
                            Wait.secondsUntilElementPresent("pdp_list.addToDefaultList", 15);
                            softly.assertThat(Elements.elementPresent("pdp_list.addToDefaultList")).as("addToListPopupListLink").isEqualTo(true);
                            Clicks.click(Elements.element("pdp_list.addToDefaultList"));
                            Wait.secondsUntilElementPresent("pdp_list.addToListPopupListLink", 15);
                            Thread.sleep(5000);
                            Clicks.click(Elements.element("pdp_list.addToListPopupListLink"));
                        }
                        else if(link.equalsIgnoreCase("CreateList link")) {
                            softly.assertThat(Elements.elementPresent("pdp.createNewListLink")).as("addToListPopupListLink").isEqualTo(true);
                            Clicks.click(Elements.element("pdp.createNewListLink"));
                            Wait.secondsUntilElementPresent("pdp.createNewListName", 5);
                            Clicks.click(Elements.element("pdp.createNewListBtn"));
                            softly.assertThat(Elements.elementPresent("pdp.createNewListError")).as("createNewListError").isEqualTo(true);
                            softly.assertThat(((Elements.findElement(Elements.element("pdp.createNewListError")).getText())).equals("Please enter a name.")).isEqualTo(true);

                            Thread.sleep(3000);
                            TextBoxes.typeTextbox(Elements.element("pdp.createNewListName"), "MyList1");
                            Clicks.click(Elements.element("pdp.createNewListBtn"));

                            Wait.secondsUntilElementPresent("pdp.viewListLink", 5);
                            Clicks.click(Elements.element("pdp.viewListLink"));
                        }
                        break;
                    }
                    case "master": {
                        break;
                    }
                }
                break;
            }

        }
        logger.info(String.format("***>> " + link + " clicked on " + arg + " on " + pg + " PDP " + mode + " mode!\n"));
        softly.assertAll();
        Thread.sleep(2000);
    }


    @And("I click on default list$")
    public void click_on_listview_landingpage(){
        Wait.secondsUntilElementPresent("listhome.list_landing_view", 30);
        Clicks.click(Elements.element("listhome.list_landing_view"));
    }


    @Then("^I click on Create list \"([^\"]*)\"$")
    public void i_click_on_Create_list(String list) throws Throwable {
        Wait.secondsUntilElementPresent("listhome.create_list_link", 30);
        Clicks.click(Elements.element("listhome.create_list_link"));
        TextBoxes.typeTextbox("listhome.create_list_text", list);
        Clicks.click("listhome.create_button");
    }

    @And("^I land on List landing page$")
    public void redirect_to_listlandingpage(){
        Navigate.visit(RunConfig.url + "/wishlist/home");
    }


    @And("^sample test: I click the logo$")
    public void sample_test_I_click_the_logo() throws Throwable {
        Clicks.click("home.logo");
    }

    @And("^sample test: I navigate to sample page$")
    public void sample_test_I_navigate_to_sample_page() throws Throwable {
        Navigate.visit(RunConfig.url + "/wishlist/home");
        //Clicks.click("home.test_element");
    }

    @And("^I add a \"([^\"]*)\" product to my list through the service$")
    public void adding_product_to_list_through_service(String productType) throws Throwable {
        AddToListService add_item = new AddToListService();
        switch (productType) {
            case "member": {
                String[] products = ListPIDs.memberProdID();
                int index = new Random().nextInt(products.length);
                productId = products[index];
                break;
            }
            case "unavailable": {
                String[] products = ListPIDs.unavailProdID();
                int index = new Random().nextInt(products.length);
                productId = products[index];
                break;
            }
        }
        add_item.addPidToList(productId);
    }

    @And("^I add a \"([^\"]*)\" product to my list through the service call$")
    public void adding_product_to_list_through_service_call(String productId) throws Throwable {
        AddToListService add_item = new AddToListService();
        add_item.addPidToList(productId);
    }
    @And("^I click on email share icon and verify email list button on the email list share overlay$")
    public void i_click_on_email_share_icon_and_verify_email_list_button_on_the_email_list_share_overlay() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Elements.elementInView("listpage.lnkListEmailShare");
        Clicks.clickWhenPresent("listpage.lnkListEmailShare");
        Elements.elementInView("listpage.emailSubmit");
        Clicks.clickWhenPresent("listpage.emailSubmit");

    }

    @Then("^I verify the error message \"([^\"]*)\"$")
    public void i_verify_the_error_message(String errorMsg) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(2000);
        String errorMsgObj = Elements.findElement(Elements.element("listpage.errorMsg")).getText();
        softly.assertThat(errorMsgObj.equals(errorMsg));

    }

    @Then("^I enter to \"([^\"]*)\" and click on email list button$")
    public void i_enter_to_and_click_on_email_list_button(String emailAddress) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Thread.sleep(2000);
        TextBoxes.typeTextbox("listpage.emailToTextBox", emailAddress);


    }

    @Then("^I verify the google recaptcha error message$")
    public void i_verify_the_google_recaptcha_error_msg() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Elements.elementInView("listpage.emailShareGoogleCaptchaNotification");

    }

    @And("^I add a \"([^\"]*)\" upc to my list through the service$")
    public void adding_upc_to_list_through_service(String productType) throws Throwable {
        AddToListService add_item = new AddToListService();

        switch (productType) {
            case "available": {
                String[] products = ListUPCs.availableUpc();
                int index = new Random().nextInt(products.length);
                productUpc = products[index];
                break;
            }
            case "unavailable": {
                String[] products = ListUPCs.unavailableUpc();
                int index = new Random().nextInt(products.length);
                productUpc = products[index];
                break;
            }
        }
        add_item.addUpcToList(productUpc);
    }

    @When("^I click Add to Wish List button on PDP using desktop website$")
    public void i_click_Add_to_Wish_List_button_on_PDP_using_desktop_website() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Wait.forPageReady();
        Elements.elementInView("pdp_list.pdp_add_to_list");
        Clicks.clickWhenPresent("pdp_list.pdp_add_to_list");
        Thread.sleep(10000);

    }

    @When("^I navigate to PDP with PID \"([^\"]*)\"$")
    public void i_navigate_to_PDP_with_PID(String PID) throws Throwable {
        productId = PID;
        TextBoxes.typeTextNEnter("home.search_field", productId);
        Wait.forPageReady();
        Wait.secondsUntilElementPresent(By.cssSelector(".m-j-product-main-image"),10);

    }


    @When("^I visit postgresql database to insert or delete \"([^\"]*)\" as a precondition$")
    public void I_visit_database_to_insert_data(String dmls) throws Throwable {

        Statement stmt;

        //Below Commented code is not required as we have common code inorder to serve the purpose
    /*    String s;

        FileInputStream inputStream;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            StringBuffer sb = new StringBuffer();
            File fr = new File(classLoader.getResource(records).getFile());
            System.out.println("File Found : " + fr.exists());

            inputStream = new FileInputStream(fr);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }

            br.close();

            String[] inst = sb.toString().split(";");*/

        try {
            File fr = Utils.getResourceFile(dmls);

            String sbr = Utils.readTextFile(fr);

            String[] inst = sbr.split(";");
            //Connection connection = new DBConnection().createConnection("subscriptiondbInfo");
            Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
            System.out.println("database connection received");

            stmt = connection.createStatement();
            for (int i = 0; i < inst.length; i++) {
                if (!inst[i].trim().equals("")) {
                    stmt.executeUpdate(inst[i]);
                    System.out.println(">>" + inst[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            DBUtils.closeDBConnection();
        }
    }

    @And("^I click on the facebook share icon$")
    public void i_click_on_the_facebook_share_icon() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Elements.elementInView("listpage.facebookShare");
        Clicks.clickWhenPresent("listpage.facebookShare");
    }

    @Then("^I verify the facebook window appears$")
    public void i_verify_the_facebook_icon_appears() throws Throwable {
        for (String winHandle : WebDriverManager.getWebDriver().getWindowHandles()) {
            WebDriverManager.getWebDriver().switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }

        String URL = WebDriverManager.getCurrentUrl();
        System.out.println(URL);
        Assert.assertTrue(URL.contains("facebook.com"));
    }

    @And("^I click on the twitter share icon$")
    public void i_click_on_the_twitter_share_icon() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        Elements.elementInView("listpage.twitterShare");
        Clicks.clickWhenPresent("listpage.twitterShare");
    }

    @Then("^I verify the twitter window appears$")
    public void i_verify_the_twitter_icon_appears() throws Throwable {
        for (String winHandle : WebDriverManager.getWebDriver().getWindowHandles()) {
            WebDriverManager.getWebDriver().switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }

        String URL = WebDriverManager.getCurrentUrl();
        System.out.println(URL);
        Assert.assertTrue(URL.contains("twitter.com"));
    }
}