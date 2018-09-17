package com.macys.sdt.projects.PurchaseAndDelivery.Common.steps.website;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.utils.ShopNServeService;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Home;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.GuestCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.SignedInCheckout;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.BWallet;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.MyAddressBookPage;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account.OCMyWallet;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout.*;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.shop_and_browse.ShoppingBag;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.macys.sdt.framework.utils.StepUtils.*;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class BATsSteps {

    @Then("^I should see payment summary section(with \"Edit\" button)?$")
    public void checkingPaymentSummarySection() {
        JSONObject paymentInfoSession = ShopNServeService.getPaymentTypesAndMethods();

        String paymentSummaryInfo = Navigate.get(PaymentGuestSection.class).getPaymentSummarySectionInfo();

        JSONObject creditCardInfo = paymentInfoSession.getJSONObject("creditCard");
        assertThat("Card Number not displayed in section",
                paymentSummaryInfo, containsString(creditCardInfo.getString("cardNumber")));
        assertThat("Card Type not displayed in section",
                paymentSummaryInfo, containsString(creditCardInfo.getString("creditCardDisplayText")));
        assertThat("Expiration Date not displayed in section",
                paymentSummaryInfo,
                containsString(String.format("%s/%s",
                        creditCardInfo.get("expMonth").toString(),
                        creditCardInfo.get("expYear").toString()))
        );

        JSONObject billingInfo = paymentInfoSession.getJSONObject("billingContact");
        assertThat("Email not displayed in section",
                paymentSummaryInfo, containsString(billingInfo.getString("email")));

        assertThat("Edit Button is not displayed in Payment Section",
                Navigate.get(PaymentGuestSection.class).editPaymentSection.isDisplayed(),
                is(TRUE));
        assertThat("Payment Summary Section is not displayed",
                Navigate.get(PaymentGuestSection.class).paymentSummary.isDisplayed(),
                is(TRUE));
    }

    @Then("^I should see payment summary section with billing address text$")
    public void billingAddressTextDisplay() {
        String paymentSummaryInfo;
        if (signedIn()) {
            paymentSummaryInfo = Navigate.get(PaymentSignedInSection.class).getPaymentSummarySectionInfo();
        } else {
            paymentSummaryInfo = Navigate.get(PaymentGuestSection.class).getPaymentSummarySectionInfo();
        }
        JSONObject billingAddressText = ShopNServeService.getOrder().getJSONObject("paymentDetails").getJSONObject("creditCard").getJSONObject("contact").getJSONObject("address");

        assertThat("Billing address text not displayed in summary", paymentSummaryInfo, containsString("Billing Address:"));
        assertThat("Address not displayed in summary", paymentSummaryInfo, containsString(billingAddressText.getString("addressLine1")));
    }

    @Then("I should see shipping summary section with \"Edit\" button")
    public void checkingShippingSummarySection() {
        //it's ok to convert step into step with checking any button if needed
        ShippingSection shippingSection = Navigate.get(GuestCheckout.class).shippingSection();
        assertThat("ERROR - Timeout - \"Edit\" is not displayed in Shipping Section",
                shippingSection.waitForEditIsDisplayed(),
                is(TRUE));
        assertThat("ERROR - Timeout - Shipping Summary Section is not displayed",
                shippingSection.shippingSummary.stream().allMatch(HtmlElement::isDisplayed),
                is(TRUE));
    }

    @Then("I should see shipping summary section with selected (premium|express|nohurry) method, name \"(.*)\" and \"Edit\" button")
    public void checkingShippingSummarySectionWithSpecificSHippingMethod(String expectedMethod, String expectedName) {
        List<HtmlElement> shippingSummary = Navigate.get(ShippingSection.class).shippingSummary;
        assertThat("ERROR: Timeout - Updated shipping methods is not displayed",
                shippingSummary.get(shippingSummary.size() - 1).getText().toLowerCase(),
                containsString(expectedMethod.toLowerCase()));
        assertThat("ERROR: Timeout - Updated name in shipping address is not displayed",
                shippingSummary.get(0).getText().toLowerCase(),
                containsString(expectedName.toLowerCase()));

        //it's ok to convert step into step with checking any button if needed
        assertThat("ERROR - Timeout - \"Edit\" is not displayed in Shipping Section",
                Navigate.get(GuestCheckout.class).shippingSection().waitForEditIsDisplayed(),
                is(TRUE));
    }

    @Then("I should see same shipping methods displayed with same order")
    public void checkingShippingMethodsDisplaying() {
        JSONArray availableShippingMethods = ShopNServeService.getShippingInfo().getJSONArray("availableShippingMethods");

        for (int i = 0; i < availableShippingMethods.length(); i++) {
            if (availableShippingMethods.getJSONObject(i).get("displayShippingMethod").equals(FALSE)) {
                availableShippingMethods.remove(i);
            }
        }

        int countAvailableShippingMethods = availableShippingMethods.length();

        List<HtmlElement> displayedShippingMethods = Navigate.get(ShippingOptions.class).shippingOptionsElements;

        assertThat("ERROR: DATA SERVICE - The Service response is not matched with displayed response",
                countAvailableShippingMethods, equalTo(displayedShippingMethods.size()));
        for (int i = 0; i < displayedShippingMethods.size(); i++) {
            assertThat(displayedShippingMethods.get(i).getAttribute("value"),
                    equalTo(availableShippingMethods.getJSONObject(i).get("methodCode")));
        }

    }

    @And("I change name for shipping address to \"(.*) (.*)\"")
    public void changeShippingAddressName(String newFirstName, String newLastName) {
        Navigate.get(ShippingSection.class).shippingOptions().updateShippingName(newFirstName, newLastName);

    }

    @Then("^I tap \"Edit\" button in \"(shipping|payment)\" section$")
    public void clickEditButtonOnCheckoutPage(String sectionName) {

        switch (sectionName) {
            case "shipping":
                Navigate.get(GuestCheckout.class).shippingSection().edit();
                break;
            case "payment":
                Navigate.get(GuestCheckout.class).paymentSection().edit();
                break;
        }
    }

    @Then("^I should see checkout summary values displayed with \"dollar\\(\\$\\)\" and \"2 decimals\" points( as guest|signed-in user)?$")
    public void checkingCheckoutSummaryDisplayedWithDollarAmountAndTwoDecimals(String userType) {
        //act as guest if user type is not specified in step
        if (userType == null)
            userType = "guest";

        Map<String, String> chkoutSummaryValuesMap = new HashMap<>();
        chkoutSummaryValuesMap.put("subtotal", "merchandiseTotalAfterDiscount");
        chkoutSummaryValuesMap.put("order total", "grandTotal");
        chkoutSummaryValuesMap.put("estimated tax", "estimatedTaxes");
        chkoutSummaryValuesMap.put("shipping", "shippingDisplayCost");
        chkoutSummaryValuesMap.put("shipping surcharges", "shippingSurcharges");
        chkoutSummaryValuesMap.put("you saved", "orderDiscountTotal");
        chkoutSummaryValuesMap.put("estimated savings", "orderDiscountTotal");
        chkoutSummaryValuesMap.put("shipping surcharge", "shippingSurcharges");
        chkoutSummaryValuesMap.put("estimated macy's money", "estimatedRewardMoney");
        chkoutSummaryValuesMap.put("in-store pickup", "shippingDisplayCost");

        JSONObject orderSummaryInfo = ShopNServeService.getOrderSummary();

        List<HtmlElement> elements = Navigate.get(OrderSummarySection.class).getRcOrderCostSummary();
        for (HtmlElement element : elements) {

            String elementText = element.getText();
            String chkoutSummaryKey = elementText.split("\\n")[0].toLowerCase();
            String chkoutSummaryValue = elementText.split("\\n")[1];

            if (chkoutSummaryKey.contains("macy's money")) {
                String estimatedMoney = orderSummaryInfo.getString(chkoutSummaryValuesMap.get(chkoutSummaryKey));
                assertThat(String.format("ERROR: SERVICE - Displayed %s amount doesn't contain \\$ sign", chkoutSummaryKey),
                        estimatedMoney, containsString("$"));
                assertThat(String.format("ERROR: SERVICE - Displayed %s amount is not matched with the ShopApp response", chkoutSummaryKey),
                        estimatedMoney, containsString(chkoutSummaryValue));
            } else if (chkoutSummaryKey.contains("shipping") || chkoutSummaryKey.contains("in-store pickup") && !chkoutSummaryKey.contains("surcharge")) {
                String actualShippingCost = null;
                if (Pattern.compile("\\((.*?)\\)").matcher(chkoutSummaryKey).matches())
                    chkoutSummaryKey = chkoutSummaryKey.contains("shipping") ? "shipping" : "in-store pickup";

                if (userType.equals("signed-in user")) {
                    JSONObject shippingInfo = ShopNServeService.getOrder().getJSONObject("shippingDetails");
                    JSONArray shippingMethods = shippingInfo.getJSONArray("availableShippingMethods");
                    for (int i = 0; i < shippingMethods.length(); i++) {
                        JSONObject shipping = shippingMethods.getJSONObject(i);
                        if (shipping.getString("methodCode").equals(shippingInfo.getString("selectedShippingMethodCode")))
                            actualShippingCost = shipping.getString("shippingDisplayCost");
                    }
                } else
                    actualShippingCost = orderSummaryInfo.getJSONObject("shippingMethod").getString("shippingDisplayCost");

                if (actualShippingCost.contains("FREE") || actualShippingCost.equals("")) {
                    assertThat(String.format("ERROR: SERVICE - Displayed shipping amount \"%s\" is not matched with the ShopAPP response \"%s\"", actualShippingCost, chkoutSummaryValue),
                            chkoutSummaryValue, equalTo("FREE"));
                } else {
                    String[] cost = chkoutSummaryValue.split("\\.");
                    assertThat(String.format("ERROR: APP - %s amount is not displaying with two decimals accuracy", chkoutSummaryKey),
                            cost[cost.length - 1].length(), equalTo(2));
                    assertThat(String.format("ERROR: SERVICE - Displayed %s amount doesn't contain \\$ sign", chkoutSummaryKey),
                            actualShippingCost, containsString("$"));
                    assertThat(String.format("ERROR: SERVICE - Displayed %s amount is not matched with the ShopAPP response", chkoutSummaryKey),
                            actualShippingCost, containsString(chkoutSummaryValue));
                }
            } else {
                String[] cost = chkoutSummaryValue.split("\\.");
                assertThat(String.format("ERROR: APP - %s amount is not displaying with two decimals accuracy", chkoutSummaryKey),
                        cost[cost.length - 1].length(), equalTo(2));
                assertThat(String.format("ERROR: SERVICE - %s amount is not displaying with two decimals accuracy", chkoutSummaryKey),
                        orderSummaryInfo.getString(chkoutSummaryValuesMap.get(chkoutSummaryKey)), containsString("$"));
                assertThat(String.format("ERROR: SERVICE - Displayed %s amount is not matched with the ShopAPP response", chkoutSummaryKey),
                        orderSummaryInfo.getString(chkoutSummaryValuesMap.get(chkoutSummaryKey)), containsString(chkoutSummaryValue));
            }
        }
    }

    @Then("^I should see shipping charge \"(.*?)\" in checkout summary with (Premium|Express|Nohurry) shipping method$")
    public void checkShippingChargeInCheckoutSummary(String shippingCost, String expectedShippingMethod) {
        JSONObject shippingMethod = ShopNServeService.getOrderSummary()
                .getJSONObject("shippingMethod");
        String shippingDisplayCost = shippingMethod.getString("shippingDisplayCost");

        Stream<HtmlElement> filteredRCOrderSummary = Navigate.get(OrderSummarySection.class).rcOrderSummary.stream().filter(
                element -> {
                    String elementText = element.getText();
                    return elementText.toLowerCase().contains("shipping")
                            && elementText.toLowerCase().contains("surcharge");
                }
        );


        if (shippingCost.contains("FREE")) {
            assertThat(String.format("ERROR -SERVICE -ShopAPP response is giving %s as shipping charges", shippingCost),
                    shippingDisplayCost, containsString(shippingCost));
            filteredRCOrderSummary.forEach(element ->
                    assertThat(String.format("ERROR - %s label is not displayed in Summary section", shippingCost),
                            element.getText(), containsString(shippingCost))
            );
        } else {
            assertThat("ERROR: SERVICE -Order Summary is not updated with shipping method",
                    shippingMethod.getString("description").toLowerCase(),
                    equalTo(expectedShippingMethod.toLowerCase()));
            filteredRCOrderSummary.forEach(element ->
                    assertThat("ERROR: Order Summary is not updated with shipping method",
                            element.getText(), containsString(shippingDisplayCost))
            );
        }
    }

    @Then("^I should see \"place order\" button in \"(enabled|disabled)\" mode$")
    public void checkPlaceOrderButtonEnabledDisabled(String mode) {
        OrderReviewSection orderReviewSection = Navigate.get(GuestCheckout.class).orderReviewSection();
        if (mode.equals("enabled")) {
            assertThat("ERROR: Timeout - Place Order Button is disabled",
                    Wait.until(orderReviewSection.placeOrder::isEnabled),
                    is(TRUE)
            );
        } else {
            assertThat("ERROR: Timeout - Place Order Button is enabled",
                    orderReviewSection.placeOrder.isEnabled(),
                    not(TRUE));
        }
    }

    @Then("^I return to bag page$")
    public void backToBag() {
        // there is no way to navigate to Shopping Bag page from bcom order confirmation page
        // so we have to use the same mechanism for this step in order to fit bcom and mcom
        Navigate.browserBack(); // going to checkout sign in/guest page
        Navigate.browserBack(); // going to Shopping Bag page

        Navigate.browserRefresh(); // refresh to get actual Shopping Bag page

        Navigate.get(ShoppingBag.class).waitForReady();
    }

    @Then("^I should see zero items on bag page$")
    public void checkBagIsEmpty() {
        ShoppingBag shoppingBag = Navigate.get(ShoppingBag.class);
        assertThat("ERROR: APP - Item count is not zero on ShoppingBag page",
                shoppingBag.itemCount(),
                equalTo(0));

        // checking error message for mcom, there is no error message for bcom
        if (macys()) {
            String expectedErrorMessage = "Your Current Shopping Bag is empty.";
            assertThat("ERROR: APP - Error message is absent on shopping bag page",
                    Navigate.get(ShoppingBag.class).errorMessage(),
                    containsString(expectedErrorMessage));
        }
    }

    @Then("^I click \"(add_new|save|cancel|change)\" in (shipping|payment)(?: summary)? section$")
    public void clickButtonInShippingOrPaymentSection(String btnTitle, String section) {
        SignedInCheckout checkoutPage = Navigate.get(SignedInCheckout.class);

        WebElement btnToClick;
        switch (btnTitle) {
            case "add_new":
                btnToClick = (section.equals("shipping")) ? checkoutPage.addShippingAddressButton : checkoutPage.paymentSection().addNewCard;
                break;
            case "save":
                btnToClick = (section.equals("shipping")) ? checkoutPage.saveShippingButton : checkoutPage.paymentSection().savePaymentButton;
                break;
            case "cancel":
                btnToClick = (section.equals("shipping")) ? checkoutPage.cancelShippingChangeButton : checkoutPage.paymentSection().cancelPaymentChangeButton;
                break;
            case "change":
                btnToClick = (section.equals("shipping")) ? checkoutPage.shippingChangeButton : checkoutPage.paymentSection().changePaymentButton;
                break;
            default:
                throw new IllegalArgumentException(String.format("ERROR: Unknown button parameter %s", btnTitle));
        }

        assertThat(String.format("ERROR: Button %s in section %s is not displayed as expected", btnTitle, section),
                Wait.until(btnToClick::isDisplayed),
                is(Boolean.TRUE));
        btnToClick.click();
    }

    @Then("I should see shipping summary section with \"(.*?)\" shipping address")
    public void checkShippingSummarySectionWithAddress(String type) throws Throwable {

        // this part is because for signed in user framework doesn't create shipping address
        if (!macys() && !prodEnv() && signedIn()) {
            MyAddressBookPage myAddressBookPage = Navigate.to(MyAddressBookPage.class);
            Map<String, String> opts = new HashMap<>();
            opts.put("checkout_eligible", "true");
            if (myAddressBookPage.isAddressAdded()) {
                myAddressBookPage.updateAddress(0, opts);
            } else {
                myAddressBookPage.addAddress(opts);
                Navigate.to(SignedInCheckout.class);
            }
        }
        JSONObject shippingContactDetails =
                ShopNServeService.getSelectedShippingAddressOrder()
                        .getJSONObject("shippingDetails")
                        .getJSONObject("contact");
        String displayedContactInfo = Navigate.get(SignedInCheckout.class).shippingAddressInfo.getText();

        assertThat("ERROR: User shipping first name is not displayed",
                displayedContactInfo,
                containsString(shippingContactDetails.getString("firstName")));
        assertThat("ERROR: User shipping last name is not displayed",
                displayedContactInfo,
                containsString(shippingContactDetails.getString("lastName")));

        JSONObject addresses = shippingContactDetails.getJSONObject("address");
        List<String> addressKeys = addresses.keySet().stream()
                .filter(key -> !key.equals("phoneNumber") && !key.equals("country"))
                .collect(Collectors.toList());

        for (String addressKey : addressKeys) {
            String addressValue = addresses.getString(addressKey);
            assertThat(String.format("ERROR: User shipping Address is not displayed - %s: %s", addressKey, addressValue),
                    displayedContactInfo,
                    containsString(addressValue));
        }
        assertThat("ERROR: Default shipping address is not displayed",
                shippingContactDetails.getBoolean("primaryFlag") && type.equals("default"),
                is(TRUE));
    }

    @And("^I enter pick up contact details in responsive shipping page$")
    public void enterRandomPickUpDetails() {
        ProfileAddress address = new ProfileAddress();
        TestUsers.getRandomValidAddress(null, address); //null for US address
        ShippingOptions shippingOptions;
        if (signedIn()) {
            shippingOptions = Navigate.get(SignedInCheckout.class).shippingSection().shippingOptions();
        } else {
            shippingOptions = Navigate.get(GuestCheckout.class).shippingSection().shippingOptions();
        }
        shippingOptions.enterPickUpInfo(address);
    }

    @Then("^I should see (shipping|payment) section in summary state$")
    public void checkShippingOrPaymentSectionInSummaryState(String sectionTitle) {
        Boolean isInSummary;
        if (signedIn()) {
            isInSummary = Wait.until(() -> sectionTitle.equals("shipping") ? Navigate.get(SignedInCheckout.class).changeShippingAddress.exists() :
                    Navigate.get(SignedInCheckout.class).changeCreditCardButton.exists(), 10);
        } else {
            isInSummary = Wait.until(() -> sectionTitle.equals("shipping") ? Navigate.get(ShippingSection.class).editShippingSection.exists() :
                    Navigate.get(PaymentGuestSection.class).editPaymentSection.exists(), 10);
        }
        assertThat(isInSummary, is(TRUE));
    }

    @And("^I select a random (credit card|shipping address)$")
    public void selectRandomCreditCard(String sectionName) {
        List<HtmlElement> creditCards;
        SignedInCheckout signedInCheckout = Navigate.get(SignedInCheckout.class);
        if (sectionName.equals("credit card")) {
            creditCards = signedInCheckout.paymentSection().creditCardSelects;
        } else {
            creditCards = signedInCheckout.shippingAddressRadioButton;
        }
        int randIndex = new Random().nextInt(creditCards.size());

        creditCards.get(randIndex).click();
    }

    @Then("^I should see payment summary section with selected credit card$")
    public void checkPaymentSummarySectionSelectedCard() {
        JSONObject orderDetails = ShopNServeService.getSelectedCreditCard();
        JSONObject creditCard = orderDetails.getJSONObject("paymentDetails").getJSONObject("creditCard");
        String paymentInfo = Navigate.get(SignedInCheckout.class).paymentSection().paymentInfo.getText();

        Assert.assertThat("ERROR: Payment Type is not displayed",
                paymentInfo,
                containsString(creditCard.getString("creditCardDisplayText")));
        String expectedCardNumber = creditCard.getString("maskedCreditCardNumber");
        if (bloomingdales()) {
            expectedCardNumber = expectedCardNumber.substring(expectedCardNumber.length() - 8);
        }
        Assert.assertThat("ERROR: Card Number is not displayed",
                paymentInfo,
                containsString(expectedCardNumber));
        if (creditCard.getBoolean("cvvExpDateRequired")) {
            Assert.assertThat(paymentInfo,
                    containsString(String.format("%s/%s",
                            creditCard.get("expMonth"),
                            creditCard.get("expYear"))));
        }
        Assert.assertThat("Credit Card Number is not masked",
                Pattern.compile("\\*+\\d{4}").matcher(creditCard.getString("maskedCreditCardNumber")).matches(),
                is(TRUE));
    }

    @Given("^I visit the web site as a registered user with credit cards$")
    public void visitSiteAsRegisteredUserWithCCards() throws Throwable {
        new PageNavigation().I_visit_the_web_site_as_a_registered_user();

        if (macys()) {
            OCMyWallet myWallet = Navigate.to(OCMyWallet.class);
            myWallet.addCard();
        } else {
            BWallet myWallet = Navigate.to(BWallet.class);
            myWallet.addCard();
        }

        Navigate.to(Home.class);
    }
}
