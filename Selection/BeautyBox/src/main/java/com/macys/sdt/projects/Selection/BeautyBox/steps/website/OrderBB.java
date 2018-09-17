package com.macys.sdt.projects.Selection.BeautyBox.steps.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by m940030 on 3/20/17.
 */
public class OrderBB extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(OrderBB.class);
    public int maxTries = 10;
    public JSONObject bbOrderDetails;
    String orderNum = null;



    private String currentEmail = "karrat@gmail.com";
    //private String currentEmail = "paras.dhariwal@macys.com";
    private String complexPwd = "helloworld";

    @When("^I click on SignIn button on Home page$")
    public void i_click_on_SignIn_button_on_Home_page() throws Throwable {

        if (Elements.elementPresent("my_subscription.home_signIn")) {
            Clicks.click("my_subscription.home_signIn");
            Thread.sleep(2000);

        }

    }


//    @When("^I enter an existing email id$")
//    public void iEnterExistingEmailId() throws Throwable {
//        TextBoxes.typeTextbox("create_profile.email", currentEmail);
//        /*
//        Below, I input nothing in password just to ensure that the focus moves out of the Email address
//        text box so that existing email id error msg gets displayed.
//        */
//        TextBoxes.typeTextbox("create_profile.password", complexPwd);
//        Thread.sleep(1000);
//
//        if (Elements.elementPresent("my_subscription.signIn_button")) {
//            Clicks.click("my_subscription.signIn_button");
//            Thread.sleep(2000);
//
//        }
//    }


    @Then("^I should be able to click on My Account Page$")
    public void i_should_be_able_to_click_on_My_Account_Page() throws Throwable {

        if (Elements.elementPresent("my_subscription.home_myaccount")) {
            Clicks.click("my_subscription.home_myaccount");
            Thread.sleep(4000);
            Clicks.click("my_subscription.home_order_history");
            Thread.sleep(2000);
        }

    }

    @Then("^I should be able to click on My Account on MEW Page$")
    public void i_should_be_able_to_click_on_My_Account_on_MEW_Page() throws Throwable {

        if (Elements.elementPresent("my_subscription.home_tab")) {
            Clicks.click("my_subscription.home_tab");
            Thread.sleep(1000);
            Clicks.click("my_subscription.home_account");
            Thread.sleep(2000);
        }

    }

    @Then("^I should be able to verify cancel button$")
    public void i_should_be_able_to_verify_cancel_button() throws Throwable {

        String item = Elements.findElement(Elements.element("my_subscription.order_cancel")).getText();
        Assert.assertTrue(item.contentEquals("cancel order"));
    }

    @Then("^I should be able to verify order details Page$")
    public void i_should_be_able_to_verify_order_details_Page() throws Throwable {

        String item = Elements.findElement(Elements.element("my_subscription.order_details")).getAttribute("value");
        System.out.println("gGRFHH" + item);
        Assert.assertTrue(item.contentEquals("order details"));
    }

    @Then("^I click on order details page then I should land on that page$")
    public void i_click_on_order_details_page_then_I_should_land_on_that_page() throws Throwable {
        if (Elements.elementPresent("my_subscription.order_details")) {
            Clicks.click("my_subscription.order_details");
            Thread.sleep(1000);
            String URL = WebDriverManager.getCurrentUrl();
            System.out.println("gGRFHH" + URL);
            Assert.assertTrue(URL.contains("order-details"));
        }
    }

    @Then("^I should be able to click on product image and it should take me to PDP Page$")
    public void i_should_be_able_to_click_on_product_image_and_it_should_take_me_to_PDP_Page() throws Throwable {

        if (Elements.elementPresent("orders.order_product_image")) {
            Clicks.click("orders.order_product_image");
            Thread.sleep(1000);
            for (String winHandle : WebDriverManager.getWebDriver().getWindowHandles()) {
                WebDriverManager.getWebDriver().switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            String URL = WebDriverManager.getCurrentUrl();
            System.out.println(URL);
            Assert.assertTrue(URL.contains("monthlybox"));

        }

    }

    @Then("^I should verify shipping id and cancel order button$")
    public void i_should_verify_shipping_id_and_cancel_order_button() throws Throwable {

        String item = Elements.findElement(Elements.element("my_subscription.order_details_cancel")).getAttribute("value");
        Assert.assertTrue(item.contentEquals("cancel order"));

        String shipping = Elements.findElement(Elements.element("my_subscription.orders_shipping")).getText();
        Assert.assertTrue(shipping.contentEquals("Shipping Address"));
    }


    @Then("^I should be able to click on product image and it should take me to Product Page$")
    public void i_should_be_able_to_click_on_product_image_and_it_should_take_me_to_Product_Page() throws Throwable {
        if (Elements.elementPresent("my_subscription.order_details_product_image")) {
            Clicks.click("my_subscription.order_details_product_image");
            Thread.sleep(1000);
            for (String winHandle : WebDriverManager.getWebDriver().getWindowHandles()) {
                WebDriverManager.getWebDriver().switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            String URL = WebDriverManager.getCurrentUrl();
            Assert.assertTrue(URL.contains("product"));

        }

    }


    @Then("^I should be able to verify BB Product$")
    public void i_should_be_able_to_verify_BB_Product() throws Throwable {

        String item = Elements.findElement(Elements.element("orders.beauty_order")).getText();
        Assert.assertTrue(item.contentEquals("S21345675556"));

    }

    @Then("^I should be click on order details Page$")
    public void i_should_be_click_on_order_details_Page() throws Throwable {

        if (Elements.elementPresent("orders.order_details")) {
            Clicks.click("orders.order_details");
            Thread.sleep(1000);
            String URL = WebDriverManager.getCurrentUrl();
            System.out.println("gGRFHH" + URL);
            Assert.assertTrue(URL.contains("order-details"));
        }

    }

    @Then("^I should be not able to verify cancel button and return items button$")
    public void i_should_be_not_able_to_verify_cancel_button_and_return_items_button() throws Throwable {
        boolean strBB = Elements.elementPresent("my_subscription.order_cancel");

        if (!strBB) {
            System.out.println("Beauty Box link is not present on PDP");

        } else {
            Assert.fail(" Beauty Box on pdp page");
        }

    }

    @Then("^I should be able to verify order tab with an order$")
    public void i_should_be_able_to_verify_order_tab_with_an_order() throws Throwable {
        if (Elements.elementPresent("my_subscription.order_tab")) {
            Clicks.click("my_subscription.order_tab");
            Thread.sleep(1000);
            Clicks.click("my_subscription.order_history");
            Thread.sleep(1000);
            Clicks.click("my_subscription.order_history_summary");
            Thread.sleep(1000);
        }

    }

    @Then("^I should be verify manage subscription button on order history$")
    public void i_should_be_verify_manage_subscription_button_on_order_history() throws Throwable {

        if (Elements.elementPresent("my_subscription.order_manage_button")) {
            Thread.sleep(1000);
            Clicks.click("my_subscription.order_manage_button");
            Thread.sleep(1000);
            Clicks.click("my_subscription.order_cancel_pop");

        }
    }

    @Then("^I should verify the image link taking me to path to purchase page$")
    public void i_should_verify_the_image_link_taking_me_to_path_to_purchase_page() throws Throwable {


        if (Elements.elementPresent("my_subscription.order_product_link")) {
            Clicks.click("my_subscription.order_product_link");
            Thread.sleep(1000);
        }
        String URL = WebDriverManager.getCurrentUrl();
        System.out.println(URL);
        Assert.assertTrue(URL.contains("product"));


    }

    @Then("^I should be verify see more details button and order details header$")
    public void i_should_be_verify_see_more_details_button_and_order_details_header() throws Throwable {

        if (Elements.elementPresent("my_subscription.order_see_more")) {
            Thread.sleep(1000);
            Clicks.click("my_subscription.order_see_more");

        }
        String item = Elements.findElement(Elements.element("my_subscription.order_details_header")).getText();
        System.out.println(item);
        Assert.assertTrue(item.contains("Order"));

    }

    @Then("^I should be verify manage subscription button on order details$")
    public void i_should_be_verify_manage_subscription_button_on_order_details() throws Throwable {

        if (Elements.elementPresent("my_subscription.order_detail_class")) {
            Thread.sleep(1000);
            System.out.println("passs");
        } else {
            System.out.println("fail");
        }


        if (Elements.elementPresent("my_subscription.order_detail_cancel")) {
            Thread.sleep(1000);
            Clicks.click("my_subscription.order_detail_cancel");
            Thread.sleep(1000);
            Clicks.click("my_subscription.order_detail_cancel_pop");

        }
    }

    @Then("^I should verify the image link taking me to path to purchase page on order details$")
    public void i_should_verify_the_image_link_taking_me_to_path_to_purchase_page_on_order_details() throws Throwable {


        String item = Elements.findElement(Elements.element("my_subscription.order_details_product_link")).getText();
        System.out.println("link to purchase" + item);
        Clicks.click("my_subscription.order_details_product_link");
        String URL = WebDriverManager.getCurrentUrl();
        System.out.println(URL);
        Assert.assertTrue(URL.contains("product"));

    }

    @Given("^I navigate to STAGE environment url$")
    public void i_navigate_to_STAGE_environment_url() throws Throwable {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties prop = new Properties();
        prop.load(classLoader.getResourceAsStream("config.properties"));
        String stage_url = prop.getProperty("stage_url");
        Navigate.visit(stage_url);

    }

    @Then("^I navigate to secure-m url$")
    public void i_navigate_to_secure_m_url() throws Throwable {
        ClassLoader classLoader = getClass().getClassLoader();
        Properties prop = new Properties();
        prop.load(classLoader.getResourceAsStream("config.properties"));
        String securem_url = prop.getProperty("secure-m_url");
        Navigate.visit(securem_url);
    }

    @Then("^I should be able to verify BB Subscription panel agreement details$")
    public void i_should_be_able_to_verify_BB_Subscription_panel_agreement_details() throws Throwable {

        if (Elements.elementPresent("my_subscription.chk_agree")) {
            Clicks.click("my_subscription.chk_agree");
            Thread.sleep(1000);
            Clicks.click("my_subscription.chk_agree_continue");
        }
    }

    @Then("^I should verify order \"([^\"]*)\" ,\"([^\"]*)\" and click on \"([^\"]*)\" button$")
    public void i_should_verify_order_and_click_on_subscribe_button(String sub_total, String order_total, String subscribe) throws Throwable {
        Thread.sleep(2000);

        // scrollToLazyLoadElement("my_subscription.chk_address");

        if (Elements.elementPresent("my_subscription.chk_agree")) {
            Clicks.click("my_subscription.chk_agree");
            Thread.sleep(2000);
            Clicks.click("my_subscription.chk_agree_continue");
            Thread.sleep(1000);

        } else {
            Assert.fail("Error - Unable to verify agree");
        }
        String chk_sub_total = Elements.findElement(Elements.element("my_subscription.sub_total")).getText();
        String chk_order_total = Elements.findElement(Elements.element("my_subscription.order_total")).getText();
        Thread.sleep(2000);
        if (chk_sub_total.contains(sub_total)) {
            System.out.println("sub total" + chk_sub_total);
        } else

            Assert.fail("Error - Unable to verify the sub total" + sub_total);
        if (chk_order_total.contains(order_total)) {
            System.out.println("order total" + chk_order_total);

        } else {
            Assert.fail("Error - Unable to verify the order" + order_total);
        }

        if (Elements.elementPresent("my_subscription.chk_sub_button")) {
            Clicks.click("my_subscription.chk_sub_button");
            Thread.sleep(1000);
        } else {
            Assert.fail("unable to click subscribe");
        }

        Thread.sleep(2000);
        Wait.forPageReady();
        String foo = Elements.findElement(Elements.element("my_subscription.order_sum")).getText();
        System.out.println("order confirmation page" + foo);
        //Assert.assertEquals("You are now subscribed to Macy's BeautyBox. You will recieve an order confirmation email shortly.", foo);
    }

//
//    /**
//     * Navigates to an order page using given order and user type data
//     * <p>
//     * Order details come from "return_orders.json" resource file in shared data
//     * </p>
//     *
//     * @param orderType "submitted", "intransit", or "transit"
//     * @param userType  "guest" or "signed in"
//     * @throws Throwable if any exception occurs
//     */
//    @When("^I select \"([^\"]*)\" order as a \"([^\"]*)\" user$")
//    public void I_select_order_as_as_user(String orderType, String userType) throws Throwable {
//        HashMap<String, String> order = new HashMap<>();
//        order.put("return_order", orderType);
//        bbOrderDetails = Utils.getVirtualReturns(order);
//        if (bbOrderDetails == null) {
//            Assert.fail("No order details found");
//        }
//        if (!bbOrderDetails.has("order_number")) {
//            Assert.fail("Could not find order number");
//        }
//        String orderNumber = bbOrderDetails.getString("order_number");
//
//        BeautyBoxOrderService writeareview = new BeautyBoxOrderService();
//
//     //   writeareview.deleteReturnRecord(orderNumber);
//        if (userType.equals("signed")) {
//            if (writeareview.orderExistsByOrderNumber(orderNumber)) {
//              //  Map userData = writeareview.getUserDetails(orderNumber);
//               // String password = Utils.decryptPassword(userData.get("password").toString());
//                UserProfile customer = TestUsers.getCustomer(null);
//             //   customer.getUser().getLoginCredentials().setPassword(password);
//             //   writeareview.deleteOrderRecord(orderNumber);
//            }
//            CommonUtils.signInOrCreateAccount();
//            logger.info("Email = " + TestUsers.currentEmail);
//         //   writeareview.insertOrderByOrderNumber(orderNumber, TestUsers.currentEmail);
//
//        }
//    }
//





    @Then ("^I verify write a review for beauty box order in the order \"([^\"]*)\" page$")
    public void I_verify_write_a_review_for_beauty_box_order_in_the_order_details_page(String page) throws Throwable{
        if (StringUtils.equalsIgnoreCase(page, "details")) {
            if (Elements.elementPresent("OrderBB.write_a_review")) {
                Assert.fail("Write a Review button is not  present for when it should be!!");
            }else{
                Assert.assertTrue("Write a Review button is not  being present",true);
            }

        }else if (StringUtils.equalsIgnoreCase(page, "history")) {
         //   ReturnsPage returnsPage = new ReturnsPage();
         //   returnsPage.findOrderInDateRange(returnOrderDetails.getString("order_number"));
            if (Elements.elementPresent("OrderBB.write_a_review")) {
                Assert.fail("Write a Review button is not  present for when it should be!!");
            }else{
                Assert.assertTrue("Write a Review button is not  being present",true);
            }
        }


    }




}
