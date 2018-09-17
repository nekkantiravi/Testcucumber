package com.macys.sdt.projects.Stores.Checkout.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.macys.sdt.projects.Stores.Checkout.utils.CheckoutUtils.*;


public class TenderingSteps extends StepUtils {
    public static String Store166IntegrationCID = "890e3590-562b-48b1-8843-e63936085531    ";
    public static String Store337IntegrationCID = "7b1dedb4-4191-4801-8528-929a8e4bc5f6    ";
    public static String Store55IntegrationCID = "4021cb73-f130-4635-a6c3-a2efdc48a64f    ";
    public static String Store001IntegrationCID = "a4144de6-b455-4927-b739-20d4a475feef    ";
    public static String Store572IntegrationCID = "7b1dedb4-4191-4801-8528-929a8e4bc5f6    ";


    private static final String SWIPE_CUSTOMER_CARD_TEXT = "Swipe customer's card.";

    private void goToHome() {
        Navigate.visit(RunConfig.url);
    }


    @Then("^I can see the mock tendering screen$")
    public void iCanSeeTheMockTenderingScreen() throws Throwable {
        Thread.sleep(2500);
        if (Elements.elementPresent("home.last_trans_abandoned_error")) {
            Clicks.click("home.overlay_close");
            Thread.sleep(500);
            CancelTrans();
            Thread.sleep(500);
            Assert.fail("The prepare order called came back with an error. Cancelled Transaction");
        }

        if (Elements.elementPresent("bag_screen.spinner") || Elements.elementPresent("bag_screen.checkout_button")) {
            System.out.println("I entered the spinner if statement");
            Wait.secondsUntilElementPresent("tendering.page_content", 35);

            if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                Clicks.click("home.overlay_close");
                Thread.sleep(500);
                CancelTrans();
                Thread.sleep(500);
                Assert.fail("The prepare order called came back with an error. Cancelled Transaction");
            }

            Elements.elementShouldBePresent("tendering.page_content");
            Elements.elementShouldBePresent("home.page_title");
            String tenderingScreenTitle = Elements.findElement("home.page_title").getText();
            Assert.assertEquals("You're not on the tendering screen", "Tendering", tenderingScreenTitle);
        } else {

            Wait.secondsUntilElementPresent("tendering.page_content", 20);
            Elements.elementShouldBePresent("tendering.page_content");
            Elements.elementShouldBePresent("home.page_title");
            String tenderingScreenTitle = Elements.findElement("home.page_title").getText();
            Assert.assertEquals("You're not on the tendering screen", "Tendering", tenderingScreenTitle);
        }

        Wait.secondsUntilElementPresent("bag_screen.toast_body", 10);
        Elements.elementShouldBePresent("bag_screen.toast_body");
        String slidecardtoast = Elements.findElement("bag_screen.toast_body").getText();
        Assert.assertEquals("Slide card toast was spelled wrong", SWIPE_CUSTOMER_CARD_TEXT, slidecardtoast);

    }

    @And("^I can see the Tendering Module label$")
    public void iCanSeeTheLabel() throws Throwable {
        Wait.secondsUntilElementPresent("tendering.page_content", 10);
        Elements.elementShouldBePresent("tendering.page_content");
        Elements.elementShouldBePresent("home.page_title");

        String tenderingscreenlabel = Elements.findElement("home.page_title").getText();
        Assert.assertEquals("Tendering", tenderingscreenlabel);
    }


    @When("^I tap on Tendering icon$")
    public void iTapOnTenderingIcon() throws Throwable {
        Elements.elementShouldBePresent("sends.payment_icon");
        Clicks.click("sends.payment_icon");
    }

    @And("^I can see the Authorize button$")
    public void iCanSeeTheAuthorizeButton() throws Throwable {
        Wait.untilElementPresent("tendering.authorize_button");
        Elements.elementShouldBePresent("tendering.authorize_button");

    }


    @Then("^the printer screen displays$")
    public void thePrinterScreenDisplays() throws Throwable {
        Wait.untilElementPresent("postTender.post_tender_screen");
        Elements.elementShouldBePresent("postTender.post_tender_screen");

        String posttendertitle = Elements.findElement("home.page_title").getText();
        Assert.assertEquals("PostTender", posttendertitle);


    }

    @And("^I can see the find printer button in the button bar$")
    public void iCanSeeTheFindPrinterButtonInTheButtonBar() throws Throwable {
        Elements.elementShouldBePresent("postTender.add_printer_button");

        String FindPrintButton = Elements.findElement("postTender.add_printer_button").getText();
        Assert.assertEquals("Add Printer", FindPrintButton);
    }

    @Then("^I can see the find printer button$")
    public void iCanSeeTheFindPrinterButton() throws Throwable {
        Wait.untilElementPresent("postTender.add_printer_button");
        Elements.elementShouldBePresent("postTender.add_printer_button");

        String Addprintbtn = Elements.findElement("postTender.add_printer_button").getText();
        Assert.assertEquals("Add Printer", Addprintbtn);


    }

    @When("^I click on the find printer button$")
    public void iClickOnTheFindPrinterButton() throws Throwable {
        Wait.untilElementPresent("postTender.add_printer_button");
        Clicks.click("postTender.add_printer_button");
    }


    @And("^I can see the receipt information on a send$")
    public void iCanSeeTheReceiptInformationOnASend() throws Throwable {
        Wait.untilElementPresent("postTender.add_printer_button");
        Elements.elementShouldBePresent("postTender.sub_total_post_tender");
        Elements.elementShouldBePresent("postTender.tax_post_tender");
//        Elements.elementShouldBePresent("sends.shipping_fee_post_tender");
        Elements.elementShouldBePresent("postTender.add_printer_button");
    }

    @And("^I can see the receipt information on a take$")
    public void iCanSeeTheReceiptInformationOnATake() throws Throwable {
        Wait.untilElementPresent("postTender.add_printer_button");
        Elements.elementShouldBePresent("postTender.sub_total_post_tender");
        Elements.elementShouldBePresent("postTender.tax_post_tender");
        Elements.elementShouldBePresent("postTender.add_printer_button");
    }

    @Then("^I can see the signature view$")
    public void iCanSeeTheSignatureView() throws Throwable {
        Thread.sleep(1000);
        if (Elements.elementPresent("bag_screen.spinner")) {
            Wait.secondsUntilElementPresent("tendering.signature_view", 15);
            Elements.elementShouldBePresent("tendering.signature_view");
        } else {
            Elements.elementShouldBePresent("tendering.signature_view");
        }
    }

    @Then("^I do not see the signature view$")
    public void iDoNotSeeTheSignatureView() throws Throwable {
        Thread.sleep(2000);
        Assert.assertFalse("Signature view is still displayed", Elements.elementPresent("tendering.signature_view"));
    }

     @When("^I input my signature$")
    public void iInputMySignature() throws Throwable {
         WebElement signature = Elements.findElement("tendering.signature_view");
        WebDriver driver = WebDriverManager.getWebDriver();
        Actions action = new Actions(driver);
        action.clickAndHold(signature).perform();
        action.moveToElement(signature).build().perform();
        action.moveToElement(signature, 146,108);
        action.clickAndHold();
        action.moveByOffset(0,200);
        action.moveByOffset(-10, 0);
        action.moveByOffset(0,50);
        action.moveByOffset(50, 0);
        action.perform();
        Thread.sleep(1500);
    }

    @And("^I can see Accept signature button is not active$")
    public void iCanSeeAcceptSignatureButtonIsNotActive() throws Throwable {
        Assert.assertFalse("Button is not disabled", Elements.findElement("tendering.accept_signature_button").isEnabled());
    }

    @Then("^I can see Accept signature button is active$")
    public void iCanSeeAcceptSignatureButtonIsActive() throws Throwable {
        Assert.assertTrue("Button is not enabled", Elements.findElement("tendering.accept_signature_button").isEnabled());
    }

    @When("^I press Clear signature button$")
    public void iPressClearSignatureButton() throws Throwable {
        Clicks.click("tendering.clear_signature_button");
    }

    @And("^I press the Confirm signature button$")
    public void iPressTheConfirmSignatureButton() throws Throwable {
        Thread.sleep(500);
        Elements.elementShouldBePresent("tendering.accept_signature_button");
        Clicks.click("tendering.accept_signature_button");
    }

    @Then("^I can see the postTender screen$")
    public void iCanSeeThePostTenderScreen() throws Throwable {
        Wait.forPageReady();
        Thread.sleep(2000);
        if (Elements.findElement("home.progressBar_tendering").isEnabled() || Elements.elementPresent("bag_screen.spinner")) {
            Wait.secondsUntilElementPresent("postTender.add_printer_button", 20);
            Elements.elementShouldBePresent("postTender.print_button");
            System.out.println(Elements.findElement("home.page_title").getText());
            Assert.assertEquals(Elements.findElement("home.page_title").getText(), "PostTender");

        } else {

            Wait.secondsUntilElementPresent("postTender.add_printer_button", 15);
            Elements.elementShouldBePresent("postTender.print_button");
            Assert.assertEquals(Elements.findElement("home.page_title").getText(), "PostTender");
        }
    }


    @When("^I click on the disabled \"([^\"]*)\" icon$")
    public void iClickOnTheDisabledIcon(String disabled_icon) throws Throwable {
        switch (disabled_icon) {
            case "bag":
                Wait.untilElementPresent("home.progressBar_bag");
                Clicks.click("home.progressBar_bag");

                Assert.assertFalse("Bag icon is not disabled", !(Elements.findElement("home.progressBar_bag").getAttribute("class").contains("locked")));
                // Assert.assertFalse("Bag icon is not disabled",Elements.findElement("home.progressBar_bag").isEnabled());
                break;

            case "payment":
                Wait.untilElementPresent("home.progressBar_tendering");
                Clicks.click("home.progressBar_tendering");

                Assert.assertFalse("Payment icon is not disabled", !(Elements.findElement("home.progressBar_tendering").getAttribute("class").contains("locked")));
                // Assert.assertFalse("Payment icon is not disabled", Elements.findElement("home.progressBar_tendering").isEnabled());
                break;

            case "receipt":
                Wait.untilElementPresent("home.progressBar_postTender");
                Clicks.click("home.progressBar_postTender");

                Assert.assertFalse("Receipt icon is not disabled", !(Elements.findElement("home.progressBar_postTender").getAttribute("class").contains("locked")));
                //Assert.assertFalse("Receipt icon is not disabled", Elements.findElement("home.progressBar_postTender").isEnabled());
                break;
        }

    }

        @Then("^I am on \"([^\"]*)\" page$")
        public void iAmOnPage (String page_name) throws Throwable {
            Wait.forPageReady();
            switch (page_name) {
                case "Add Product":
                    Thread.sleep(2000);
                    Wait.forPageReady();
                    if(Elements.elementPresent("tendering.authorize_overlay")){
                        Wait.secondsUntilElementNotPresent("tendering.authorize_overlay", 10);
                    }

                    Wait.secondsUntilElementPresent("home.verify_page", 25);
                    Wait.secondsUntilElementPresent("bag_screen.upc_textbox", 20);
                    Elements.elementShouldBePresent("bag_screen.upc_textbox");
                    Elements.elementShouldBePresent("home.verify_page");
                    Assert.assertEquals("Add product page is not displayed", "Add Product", Elements.getText("home.page_title"));
                    break;

                case "Bag":
                    Wait.secondsUntilElementPresent("bag_screen.checkout_button", 20);
                    Assert.assertEquals("Bag page is not displayed", "Bag", Elements.getText("home.page_title"));
                    break;

                case "Tendering":
                    Thread.sleep(1000);
                    if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                        Clicks.click("home.overlay_close");
                        Thread.sleep(500);
                        CancelTrans();
                        Thread.sleep(500);
                        Assert.fail("The prepare order called came back with an error. Cancelled Transaction");
                    }
                    if (Elements.elementPresent("bag_screen.spinner")){
                        Wait.secondsUntilElementNotPresent("bag_screen.spinner", 25);

                        if (Elements.elementPresent("home.last_trans_abandoned_error")) {
                            Clicks.click("home.overlay_close");
                            Thread.sleep(500);
                            CancelTrans();
                            Thread.sleep(500);
                            Assert.fail("The prepare order called came back with an error. Cancelled Transaction");
                        }

                    }

                    if (Elements.elementPresent("tendering.calc_totals")) {
                        String calctotalverb = Elements.findElement("tendering.calc_totals").getText();
                        Assert.assertEquals("Calculating Totals ...", calctotalverb);
                        Wait.secondsUntilElementNotPresent("tendering.calc_totals", 10);
                        Wait.untilElementPresent("tendering.tender_first_list_item_description");
                        Assert.assertEquals("Tendering is not displayed", "Tendering", Elements.getText("home.page_title"));

                    }
                    else {

                        Wait.secondsUntilElementPresent("tendering.tender_first_list_item_description", 20);
                        Assert.assertEquals("Tendering is not displayed", "Tendering", Elements.getText("home.page_title"));
                    }
                    break;

                case "PostTender":
                    Wait.secondsUntilElementPresent("postTender.find_printer_button", 20);
                    Assert.assertEquals("PostTender page is not displayed", "Receipt", Elements.getText("home.page_title"));
                    break;

                case "Customer":
                    Wait.untilElementPresent("home.page_title");
                    Assert.assertEquals("Customer page title is not displayed", "Customer", Elements.getText("home.page_title"));
        }
    }

    @And("^I see the swipe prompt message$")
    public void iSeeTheSwipePromptMessage () throws Throwable{
        Wait.untilElementPresent("tendering.swipe_prompt.message");
        Elements.elementShouldBePresent("bag_screen.toast_body");
    }

    @And("^I do not see the swipe prompt message$")
    public void iDoNotSeeTheSwipePromptMessage () throws Throwable{
        Wait.untilElementPresent("tendering.signature_view");
        Assert.assertFalse("Swipe Prompt message is no longer displayed", Elements.elementPresent("bag_screen.toast_body"));
    }

    @And("^I can see the receipt icon$")
    public void iCanSeeTheReceiptIcon() throws Throwable {
        Wait.untilElementPresent("postTender.receipt_button");
        Elements.elementShouldBePresent("postTender.receipt_button");
    }

    @When("^I click on the receipt icon$")
    public void iClickOnTheReceiptIcon() throws Throwable {
        Wait.untilElementPresent("postTender.receipt_button");
        Clicks.click("postTender.receipt_button");

        String PostTenderscreenlabel = Elements.findElement("postTender.post_tender_screen").getText();
        Assert.assertEquals("Receipt", PostTenderscreenlabel);
    }

    @When("^I swipe the \"([^\"]*)\" at the tendering screen$")
    public void iSwipeTheAtTheTenderingScreen(String CardType) throws Throwable {
        Thread.sleep(2000);
        switch (CardType) {
            case "New Bloomies Card":
                Thread.sleep(2000);
                String NewBloomCard = "QvWBhaNzPwdEZ4Ac3KtSDmv/dnfemW66qyMy0CqiQ6UTvaiAwPCppqM7s9lJmkfXYKzV3b+I+Vf7C59mKw9LUJFOd5esCOKbNiLRFzNZF16MIET0on8FZyum5aCVnVy6kq7j1j7BrTE7k0Rzlk19sqMr/a8OUUa/+t0eHHcbFMHnhUxpsTv+L1r7PajS5y2Y";
                MsrSwipe(NewBloomCard);

            case "referred_Card_Macy's":
                String referredCardMacys = "efdTqmqh/xtmOAb20gBGv8BrBJfdXyeIUXPGMEIq1qxJjf2rZT8H3FdRfN3W8YOr+ZlaU4oI0CqSgVosstfVPrNeGaOqIOE9diizZFFqSrSPBBuzGFCCX99Xh8XaushNe8FzEO9ggjlTrS6ZWzrXMw==";
                MsrSwipe(referredCardMacys);
                break;

            case "referred_Card_Bloomingdale's":
                String refferedCardBloomingdales = "efdTqmqh/xtmOAb20gBGv8idf/uhnBuGKQG2DBw+VkGNkxsNJ5VNzJjZiWXGvzWR0FuxU2M7px7MZ1CBbvy9Ko6Ip5vt4dpSlin/ypLpfh6rb1QK9n5FJsoYC+lgF5B4AZGaOSAZ735C7DRGxuJ3mw==";
                MsrSwipe(refferedCardBloomingdales);
                break;

            case "Macy's Return Mail":
                String MacysReturnMail = "ORHCZ5BEouWrZSfsYNDB/QopdccgNd7SEa3nLZ7jL+LV4STjhLXjydX2vDBtxEufYqEUHpbDGmBo6nKmc4sPwofz1Chw1H3H588iOKvVEWTkurTfyhYhmzeRPSUN6WkPtvnOGThJmMSJLtrCcl2K1JwXdVYLxxWeqr2NaR/+VRbZvOCaduQKrLqf9HcZW9gh";
                MsrSwipe(MacysReturnMail);
                break;

            case "Macy's Check ID":
                String MacysCheckID = "ORHCZ5BEouWrZSfsYNDB/QopdccgNd7SEa3nLZ7jL+LV4STjhLXjydX2vDBtxEufYqEUHpbDGmBo6nKmc4sPwofz1Chw1H3H588iOKvVEWTkurTfyhYhmzeRPSUN6WkPtvnOGThJmMSJLtrCcl2K1JwXdVYLxxWeqr2NaR/+VRbZvOCaduQKrLqf9HcZW9gh";
                MsrSwipe(MacysCheckID);
                break;

            case "declined_Card_Macy's":
                String declinedCardMacys = "efdTqmqh/xtmOAb20gBGv8BrBJfdXyeIUXPGMEIq1qyWtrlkrte8Zk71NykOtJMSmyEeD+9BkUuZP0KTzJaKfOSYPoA2hVzRd57Mt3mAhJtgEacA9UZAtQh5g97MourQ8lk/YjSmRY6yFrTfp1lJkg==";
                MsrSwipe(declinedCardMacys);
                break;

            case "limit_exceeded_Card_Macy's":
                String limitExceededCardMacys = "2+6d0CqaJ0RCbifEfLHgz3vtIzax3fbEhKp6l1Mzg4zWqW7GYgD22C4Z9TcbB899fgjmi7EMKJoRUTOuW1i4IDU1Vxpc3A7foFZYJnBlZDaVJ1TaFogSosGVZJnqXK6BB93tFhd0aOMN3Sbys/dzQRG+HqL0c/U90zoes/qRA2Y=";
                MsrSwipe(limitExceededCardMacys);
                break;

            case "declined_Card_Bloomingdale's":
                String declinedCardBloomingdales = "efdTqmqh/xtmOAb20gBGv4YY8uysXu80qbgQ/pGwwkkB26CJKfngl1aKASbLIJrPnGzY1fbL1p3qHxO4uwKe9PRSLazFF0cb3kKM2g1QTrI5CH2vaWFsoo7P9Ijo+AgcOtbZZ5jf2VFpsewUO0TIlg==";
                MsrSwipe(declinedCardBloomingdales);
                break;

            case "AMEX":
                String AMEX = "y2BZv4Omqafj5qQxqtgGUfvCMbGIfk9GpWUhyCA2sK8KtzE5QLr1/O5is81ptrq8DoNRjNipdxVnccJtxDQntQXWn3HMpQgaeN9htt8T/i2vvB6yq4/iiSMMmyxnulopHvr/7oeel8THwUATX7OXLw==";
                MsrSwipe(AMEX);
                break;

            case "VISA":
                String Visa = "g3IqV0sgRIEg0djFUdV4ZmZkk8MqB8Dthi4zM/1CF1029nw+T0ZRp0hhO69lDUB+wHXbMgkaqmXUVPtBFeHPWx96p4ti7I81uR7ekdBRaUO0IVNAZa/+6VD7t7F5gtWP4dVPw5fWH4av+M8LESP3FS0r10Phz6VupqnytU2ppeg=";
                MsrSwipe(Visa);
                break;

            case "Discover":
                String Discover = "cYAwLElTSNtVxYVxn5djdkUhYIbC1gJ6z7rjkueoPv75AYvbOQ8Jb5xgZB7bCkJpWmAZ/Bq/HAXuw0YtlIcEcCr+0UJiHbNjY5pM2WxKdA4ni0ql5bBK01GurEDXbSKstUJhIxXGRpYIYRF4UAChud6N0fgQ66ek/LTmqjrXM9nkDvqbkMQRIBsI72P01Yf1";
                MsrSwipe(Discover);
                break;

            case "Mastercard":
                String Mastercard = "ggdVTfZvTFaeJ8LlSH4QQwj5TX40mWfkUK2+8Sw0GPbmvO8a2LSEwwVAX5A5XCk1KH2BACmjh93hUhzsrNu8a3Zy2GCmezHxzlBZwZjSDwshqA3KlozWszl3FgtnYovq9rVo91PKzEAxXynObVObLzjDoe/MnjmHuwmmkpLrlmo=";
                MsrSwipe(Mastercard);
                break;

            case "JCB":
                String JCB = "efdTqmqh/xtmOAb20gBGv3UY/OwgAVpzgtivap5ikXr+q+Y5itwD0r9bRRQaq+lDC7gQhR+6lE1jQdnQSGO0Vw+Urg9UIjAayY4FQxPFJxpfC9LlgEyEaZxrifxzm2X5bd6j60A5EVK7d37KYUtxcQ==";
                MsrSwipe(JCB);
                break;

            case "CUP":
                String CUP = "SXUsM1oFc2rFc50viMLq4y8lPKHyu8LAjW0kGJa6z6KYJ55eu9wAjBrnzoeaubnb11F+mdltNkj3IHZWqpZujW3n20J5UINyT4bdIjM481Llzov+Osh8bD6r0urGGLQl6troOzNreAzNuq+hmBJBiavoImQ3qdSG8rWRn3/3iBvl/IQCVMqc+KhMv9CLae06";
                MsrSwipe(CUP);
                break;

            case "Bloomingdale's Classic Cobrand":
                String BloomClasCobrand = "V1Hio0J0RV/A+qUHmOY46Iyk7S6kHoTfT2A+23Yp5MtBOmC9M/p7aSamvZdG5hi6dVLi8hfHCaZWbQcX14pTP9rbIvN0eV9V5JlKh2oYvqMcSfW2i/OFkD4AUFshNMc+N4UnI3+yBfFX3ZVdVCzCg11Sk/UjYdESoi8wgeya+Wg=";
                MsrSwipe(BloomClasCobrand);
                break;

            case "Bloomingdale's Prop":
                String BloomProp = "2+6d0CqaJ0RCbifEfLHgz3vtIzax3fbEhKp6l1Mzg4zWqW7GYgD22C4Z9TcbB899fgjmi7EMKJoRUTOuW1i4IDU1Vxpc3A7foFZYJnBlZDaVJ1TaFogSosGVZJnqXK6BB93tFhd0aOMN3Sbys/dzQRG+HqL0c/U90zoes/qRA2Y=";
                MsrSwipe(BloomProp);
                break;

            case "Bloomingdale's Employee Prop":
                String BloomEE = "ZcXo2uRxPfAblhZo3qVoQsVvr3hbmcv3Zlc30dlwSgTpVLhDmySWKp+9GQhET5KFkBRcH1Mswcq3gIbfKSpLv4Ux1EjjALj31M595EnfyY3mirX9njmWRPfgvLatcPGmbWfiyv1sJU43Bfa4xp5TZP4B369WSXD7yZCbsPZpiA0=";
                MsrSwipe(BloomEE);
                break;

            case "Bloomingdale's Single Line Cobrand":
                String BloomSCob = "J14WRIMc4GSu4Z4kfM3/YhFoS44flCuoPUXtX3LE4ZCTUeKusdyF2xsLMP5SVuUjd+yHOlkIAJvDmV+FmwKm3Q==";
                MsrSwipe(BloomSCob);
                break;

            case "Bloomingdale's pre-Citi Prop":
                String BloomCiti = "g54FykWno9QwYVoeatYIDn6GnfqiDYx8XgmvBG2utQoC1AsKrH/LAyz5W9Cv9Gjue68FmLWenygrQPo1F25Wt6en3tveJ7m4bXO5dbw66wrw6PW3vUq5vsho9tLMkUW4jYduIEVyhhakzuF2OBBXZA/TcfSf99muqEV3LbI1R1U=";
                MsrSwipe(BloomCiti);
                break;

            case "Macy's Prop":
                String MacysProp = "efdTqmqh/xtmOAb20gBGv8BrBJfdXyeIUXPGMEIq1qwKzMGVbD6OfiCzspdQ9etp/N68IYZYN2hzs1EVM9MM96vT2MBRnIsAhbMDQtFIxoMTQ2hlLY+w6cAHu90DEM4V7Bn+BWa2NHp6wLzwPXfp1Q==";
                MsrSwipe(MacysProp);
                break;

            case "Macy's Prop One Customer":
                String MacysPropCard = "wtY0X7wORsm1JG4yUiNwENqeaZkZKUXIdt4nh+2eWDRJ1/5FEtip5fJHnpUhlQv7mJNRRDjFPrrU+toJDhwEtRmfetFrT+SVp1pgq+Bh7+0EaIooxBNKXpYrEoEI3xS7wxW4TlCuNeAVkf9qMFf+sl1JeYTIW5GAOse4iQicw6M=";
                MsrSwipe(MacysPropCard);
                break;

            case "Macy's Classic Cobrand":
                String MacysCobrand = "HAgjmoaXIe5GNmugotTlyF/eBYbTFglFSuactyg6kLSrMzRm27iTgphIEGjd5LPRAKq1srOdk89JE1mQh+rd4XSA4wQZDfRcAkWYuL/AzY46Ch45k9HzRiJysr7jvdZKwi4drxhqoecFWet5bzWmO648iNkSXRdM2NnJVvVXnv0=";
                MsrSwipe(MacysCobrand);
                break;

            case "Macy's Single Line Cobrand":
                String MacysSLCobrand = "KqGvm2B16z6YwKzwkCAQ4sJxIaMxP4Ifweb/JEjDYDquMnH3KX4VNwoQLMXEmeo1vaaifklgUL3ShuwqGLNG5g==";
                MsrSwipe(MacysSLCobrand);
                break;

            case "Macy's Employee Prop":
                String MacysEE = "wtY0X7wORsm1JG4yUiNwENqeaZkZKUXIdt4nh+2eWDRJ1/5FEtip5fJHnpUhlQv7mJNRRDjFPrrU+toJDhwEtRmfetFrT+SVp1pgq+Bh7+0EaIooxBNKXpYrEoEI3xS7wxW4TlCuNeAVkf9qMFf+sl1JeYTIW5GAOse4iQicw6M=";
                MsrSwipe(MacysEE);
                break;

            case "Macy's pre-Citi Prop":
                String Macyspreciti = "GD+lpa/QLBY+R7mt8ZggtFrm9eE1eabp+DHJIKzjJlMW9x9UXS4TD5sdJl81v61UIkDAIzmp87MbyLZdyW2WHgvgLmmyzQ5ncdhLxJSrJbVXNdeM0ACrop/r0/iBMGrWe/195tDqyumjVqsP2CRrKw==";
                MsrSwipe(Macyspreciti);
                break;

            case "Macy's Make Magic":
                String MacysMakeMagic = "EfjVG2Mn10Bp6Mfsu9NnZDd7sEe98igdBXYzn5bJgLiJDYPZxli39+jOq2fTb5EeiUj9U1mVebtcDVmn8qFglYbMnxPRTA5yJ4pM1PA3nS1HoGNjYghs58PSh7qY+LFSL9QsyRSTxw1KFJQLg3qHOBN9u8R7Au5gb/sRUboxVw+bbWcdF/pIs9r4JCmOjmby";
                MsrSwipe(MacysMakeMagic);
                break;

            case "Bloomingdale's Citi Employee":
                String BlmCitiEmployee = "tUc+VA0GyFL4OVvTJGAvl3bG+fChdb7if/LX75U17J8CLRUlb6uV+DioUK+MA+dXqfO6VXoJtAY05aPtQUcJX8b7wI4aws26Y/elgZQIHZyhfj+YqGiyqUHuFmohlJ9E9UWbZhHozOMZLbl5Bj//YQ1T1AF/0cNz0MVaWNFXlKjp3v9Gr0+X3SXU3ekiK33v";
                MsrSwipe(BlmCitiEmployee);
                break;

            case "Bloomingdale's Return Mail":
                String BlmReturnMail = "a7GJBxvNcDGNiC+nzeOoiIY7uPML4dXm/l+DeDnMYI9k197ssWeGPLNeT50dwUMC4MbeMjAQKDn9hGjgewBHIcvWADsWfd5mL5yGLcrZKpq5nICR2BbRNMQqJ++Klvg6e1iJkYQbXUT2XaahRDncw7kr01IcowhheP6KJXBkagdNyOIIBaWzYT3642REQaOE";
                MsrSwipe(BlmReturnMail);
                break;

        }
    }

    @And("^I can see the add printer button and print button$")
    public void iCanSeeTheAddPrinterButton() throws Throwable {
        Wait.untilElementPresent("postTender.add_printer_button");
        Wait.untilElementPresent("postTender.print_button");
        Elements.elementShouldBePresent("postTender.add_printer_button");
        Elements.elementShouldBePresent("postTender.print_button");

    }

    @Then("^I see the \"([^\"]*)\" Invalid Card error message$")
    public void iSeeTheInvalidCardErrorMessage(String Division) throws Throwable {
        Thread.sleep(2000);
        switch (Division) {
            case "Macy's Send":
                Wait.untilElementPresent("bag_screen.invalidcard_error");
                Elements.elementShouldBePresent("bag_screen.invalidcard_error");
                Elements.elementShouldBePresent("bag_screen.overlay_message");


                String InvalidMacysCard = Elements.findElement("bag_screen.overlay_message").getText();
                Assert.assertEquals("This device only accepts Macy's cards. Swipe a Macy's card or suspend this transaction.", InvalidMacysCard);

                Elements.elementShouldBePresent("bag_screen.error_suspend_button");
                Elements.elementShouldBePresent("bag_screen.error_tryagain_button");
                break;

            case "Bloomingdale's Send":
                Wait.untilElementPresent("bag_screen.invalidcard_error");
                Elements.elementShouldBePresent("bag_screen.invalidcard_error");
                Elements.elementShouldBePresent("bag_screen.overlay_message");

                String InvalidBloomCard = Elements.findElement("\"bag_screen.overlay_message\"").getText();
                Assert.assertEquals("This Device only accepts Bloomingdale's\ncards. Swipe a Bloomingdale's card or suspend this transaction.", InvalidBloomCard);

                Elements.elementShouldBePresent("bag_screen.error_suspend_button");
                Elements.elementShouldBePresent("bag_screen.error_tryagain_button");
                break;
            case "Macy's Take":
                Wait.untilElementPresent("bag_screen.invalidcard_error");
                Elements.elementShouldBePresent("bag_screen.invalidcard_error");
                Elements.elementShouldBePresent("bag_screen.overlay_message");

                String InvalidMacysTakeCard = Elements.findElement("bag_screen.overlay_message").getText();
                Assert.assertEquals("This device only accepts Macy's cards. Swipe a Macy's card.", InvalidMacysTakeCard);

                Elements.elementShouldBePresent("bag_screen.error_tryagain_button");
                break;

            case "Bloomingdale's Take":
                Wait.untilElementPresent("bag_screen.invalidcard_error");
                Elements.elementShouldBePresent("bag_screen.invalidcard_error");
                Elements.elementShouldBePresent("bag_screen.overlay_message");

                String InvalidBloomTakeCard = Elements.findElement("bag_screen.overlay_message").getText();
                Assert.assertEquals("This device only accepts Bloomingdale's cards. Swipe a Bloomingdale's card.", InvalidBloomTakeCard);

                Elements.elementShouldBePresent("bag_screen.error_tryagain_button");
                break;
        }
    }

    @When("^I close the invalid swipe error message$")
    public void iCloseTheInvalidSwipeErrorMessage() throws Throwable {
        Wait.untilElementPresent("bag_screen.overlay_cancel_button");
        Elements.elementShouldBePresent("bag_screen.overlay_cancel_button");
        Clicks.click("bag_screen.overlay_cancel_button");
    }

    @When("^I press the error overlay suspend button$")
    public void iPressTheErrorOverlaySuspendButton() throws Throwable {
        Wait.untilElementPresent("bag_screen.error_suspend_button");
        Elements.elementShouldBePresent("bag_screen.error_suspend_button");
        Clicks.click("bag_screen.error_suspend_button");

    }

    @When("^I close the bag fee overlay$")
    public void iCloseTheBagFeeOverlay() throws Throwable {
        Wait.secondsUntilElementPresent("home.overlay_close", 5);
        Clicks.click("home.overlay_close");
    }


    @Then("^I can see the bag fee overlay$")
    public void iCanSeeTheBagFeeOverlay() throws Throwable {
        Wait.untilElementPresent("bag_screen.bag_fee_overlay");
        Elements.elementShouldBePresent("bag_screen.bag_fee_overlay");
        Elements.elementShouldBePresent("bag_screen.bag_fee_input");
        Elements.elementShouldBePresent("bag_screen.bag_fee_enter_button");
        Elements.elementShouldBePresent("bag_screen.bag_fee_title");
    }

    @And("^I can verify the authorize button was removed$")
    public void iCanVerifyTheAuthorizeButtonWasRemoved() throws Throwable {
        Wait.untilElementNotPresent("tendering.authorize_button");
        if (Elements.elementPresent("tendering.authorize_button")) {
            Assert.fail("Authorize button is still displaying");
        }

    }

    @Then("^I see the authorization spinner$")
    public void iSeeTheAuthorizationSpinner() throws Throwable {
        Wait.secondsUntilElementPresent("bag_screen.spinner", 10);
        Elements.elementShouldBePresent("tendering.authorize_overlay");
        Elements.elementShouldBePresent("bag_screen.spinner");
        Elements.elementShouldBePresent("tendering.authorize_msg");


        String spinner_msg = Elements.findElement("tendering.authorize_msg").getText();
        Assert.assertEquals("Authorizing...", spinner_msg);

    }

    @When("^The Authorization spinner closes$")
    public void theAuthorizationSpinnerCloses() throws Throwable {
        Wait.secondsUntilElementNotPresent("bag_screen.spinner", 20);
        if (Elements.elementPresent("bag_screen.spinner")) {
            Assert.fail("Spinner is still displaying.. Transaction took longer then 20 seconds to authorize.");
        }
        Assert.assertTrue(!Elements.elementPresent("bag_screen.spinner"));

    }

    @When("^I verify the \"([^\"]*)\" record for \"([^\"]*)\"$")
    public void iConnectToTheDatabase(String Verification, String ClientID) throws Throwable {
        switch (Verification) {
            case "Open":
                if (ClientID == Store166IntegrationCID){
                    TransHeaderVerifyOpenTransaction(Store166IntegrationCID);
                }
                if (ClientID == Store337IntegrationCID){
                    TransHeaderVerifyOpenTransaction(Store337IntegrationCID);
                }
                if (ClientID == Store55IntegrationCID){
                    TransHeaderVerifyOpenTransaction(Store55IntegrationCID);
                }
                if (ClientID == Store001IntegrationCID){
                    TransHeaderVerifyOpenTransaction(Store001IntegrationCID);
                }
                if (ClientID == Store572IntegrationCID){
                    TransHeaderVerifyOpenTransaction(Store572IntegrationCID);
                }
                break;
            case "Close":
                if (ClientID == Store166IntegrationCID){
                    TransHeaderVerifyCloseTransaction(Store166IntegrationCID);
                }
                if (ClientID == Store337IntegrationCID){
                    TransHeaderVerifyCloseTransaction(Store337IntegrationCID);
                }
                if (ClientID == Store55IntegrationCID){
                    TransHeaderVerifyCloseTransaction(Store55IntegrationCID);
                }
                if (ClientID == Store001IntegrationCID){
                    TransHeaderVerifyCloseTransaction(Store001IntegrationCID);
                }
                if (ClientID == Store572IntegrationCID){
                    TransHeaderVerifyCloseTransaction(Store572IntegrationCID);
                }
                break;

        }
    }

    @And("^Error overlay is displayed accordingly when card is declined$")
    public void errorOverlayIsDisplayedAccordingly() throws Throwable {
        Wait.untilElementPresent("tendering.declined_card_overlay");
        Assert.assertTrue("Error overlay is displayed accordingly when card is declined", Elements.findElement("tendering.declined_card_overlay").isDisplayed());
        Wait.untilElementPresent("tendering.declined_card_confirm_button");
        Assert.assertTrue("Confirm button is displayed on error overlay.", Elements.findElement("tendering.declined_card_confirm_button").isDisplayed());
        Assert.assertTrue("Confirm button is displayed on error overlay.", Elements.findElement("tendering.declined_card_confirm_button").isEnabled());

    }

    @And("^No overlay and error message are displayed$")
    public void noOverlayAndErrorMessageAreDisplayed() throws Throwable {
        Assert.assertFalse("Error overlay is displayed accordingly when card is declined", Elements.elementPresent("tendering.declined_card_overlay"));
        Assert.assertFalse("Confirm button is displayed on error overlay.", Elements.elementPresent("tendering.declined_card_confirm_button"));
    }

    @And("^Close button is properly displayed and clickable$")
    public void closeButtonIsProperlyDisplayedAndClickable() throws Throwable {
        Wait.untilElementPresent("home.overlay_close");
        Assert.assertTrue("CLose button is not displayed.", Elements.findElement("home.overlay_close").isDisplayed());
        Assert.assertTrue("CLose button is not enabled.", Elements.findElement("home.overlay_close").isEnabled());
    }

    @When("^I close the overlay$")
    public void iCloseTheOverlay() throws Throwable {
        Wait.untilElementPresent("home.overlay_close");
        Clicks.click("home.overlay_close");
    }

    @Then("^Error message is displayed accordingly and UI elements are the expected ones$")
    public void errorMessageIsDisplayedAccordinglyAndUIElementsAreTheExpectedOnes() throws Throwable {
        Wait.secondsUntilElementPresent("home.confirmation_overlay",10);
        Assert.assertTrue("Error overlay is displayed accordingly when card is declined", Elements.findElement("home.confirmation_overlay").isDisplayed());
        Wait.untilElementPresent("bag_screen.overlay_confirm_button");
        Assert.assertTrue("Confirm button is displayed on error overlay.", Elements.findElement("bag_screen.overlay_confirm_button").isDisplayed());
        String declined_card_confirm_button_text = Elements.findElement("bag_screen.overlay_confirm_button").getText();
        Assert.assertEquals("Confirm button has not the correct text.Unexpected text : " + declined_card_confirm_button_text, declined_card_confirm_button_text, "OK, I Got It");
        String declined_card_error_message = Elements.findElement("bag_screen.confirmation_overlay_message").getText();
        Assert.assertEquals("Declined card error text has not the correct text.Unexpected text : " + declined_card_error_message, declined_card_error_message, "Not Approved. You can try again or cancel the transaction");
    }

    @And("^The overlay indicating the card was referred and error message are displayed properly$")
    public void theOverlayIndicatingTheCardWasReferredAndErrorMessageAreDisplayed() throws Throwable {
        Thread.sleep(5000);
        Wait.untilElementPresent("home.confirmation_overlay");
        Assert.assertTrue("Error overlay is displayed accordingly when card is referred.", Elements.findElement("home.confirmation_overlay").isDisplayed());
        Wait.untilElementPresent("bag_screen.overlay_confirm_button");
        Assert.assertTrue("Confirm button is displayed on error overlay.", Elements.findElement("bag_screen.overlay_confirm_button").isDisplayed());
        String declined_card_confirm_button_text = Elements.findElement("tendering.declined_card_confirm_button").getText();
        Assert.assertEquals("Confirm button has not the correct text.Unexpected text : " + declined_card_confirm_button_text, declined_card_confirm_button_text, "OK, I Got It");
        String referred_card_error_message = Elements.findElement("bag_screen.confirmation_overlay_message").getText();
        Assert.assertEquals("Referred card error text has not the correct text.Unexpected text : " + referred_card_error_message, "The customer’s card has been referred. Please re-ring at the full-functionality register", referred_card_error_message);

    }

    @And("^I see the Loyallist VRC Overlay$")
    public void iSeeTheLoyallistVRCOverlay() throws Throwable {
        Wait.forPageReady();
        if (Elements.elementPresent("bag_screen.spinner")) {
            Wait.untilElementPresent("bag_screen.toast_capsule");
            Elements.elementShouldBePresent("bag_screen.toast_close_button");
            Elements.elementShouldBePresent("bag_screen.toast_bar");
            Elements.elementShouldBePresent("bag_screen.toast_body");
            Elements.elementShouldBePresent("bag_screen.toast_capsule");

        } else {
            Wait.untilElementPresent("bag_screen.toast_capsule");
            Elements.elementShouldBePresent("bag_screen.toast_close_button");
            Elements.elementShouldBePresent("bag_screen.toast_bar");
            Elements.elementShouldBePresent("bag_screen.toast_body");
            Elements.elementShouldBePresent("bag_screen.toast_capsule");

        }

        Wait.untilElementPresent("tendering.reward_toast_message");
        Elements.elementShouldBePresent("tendering.reward_toast_message");


        String rewardmessage = Elements.findElement("tendering.reward_toast_message").getText();
        Assert.assertEquals("Customer has earned a Rewards Card. It will be delivered to the customer using the email address on file.", rewardmessage);


    }

    @When("^I close the Loyallist VRC Overlay$")
    public void iCloseTheLoyallistVRCOverlay() throws Throwable {
        Thread.sleep(1000);
        Clicks.click("bag_screen.toast_close_button");
        Thread.sleep(2000);


    }

    @Then("^I do not see the VRC Overlay$")
    public void iDoNotSeeTheVRCOverlay() throws Throwable {
        Wait.secondsUntilElementNotPresent("bag_screen.toast_capsule", 3);
        if (Elements.elementPresent("bag_screen.toast_capsule")) {
            Assert.fail("The Toast message did not close");
        }
    }


    @When("^I click posttender print button$")
    public void iClickPosttenderPrintButton() throws Throwable {
        Thread.sleep(3000);
        Wait.secondsUntilElementPresent("postTender.print_button", 10);
        Elements.elementShouldBePresent("postTender.print_button");
        Clicks.click("postTender.print_button");
    }

    @Then("^I see the print confirmation toast message$")
    public void iSeeThePrintConfirmationToastMessage() throws Throwable {
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("bag_screen.toast_body", 25);
        Elements.elementShouldBePresent("bag_screen.toast_body");
        String printmessage = Elements.findElement("bag_screen.toast_body").getText();
        Assert.assertEquals("Print\nReceipt sent to Printer", printmessage);
//        Wait.secondsUntilElementPresent("bag_screen.toast_close_button", 5);
//        Elements.elementShouldBePresent("bag_screen.toast_close_button");



    }

}




