package com.macys.sdt.projects.Selection.Registry.actions.website;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.addresses.CurrentAddress;
import com.macys.sdt.framework.model.registry.Preferences;
import com.macys.sdt.framework.model.registry.Registry;
import com.macys.sdt.framework.model.user.UserProfile;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.framework.utils.rest.services.CreditCardService;
import com.macys.sdt.framework.utils.rest.services.RegistryService;
import com.macys.sdt.framework.utils.rest.services.UserProfileService;
import com.macys.sdt.shared.utils.CommonUtils;
import org.junit.Assert;
import org.omg.CORBA.UserException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.HashMap;
import com.macys.sdt.framework.model.registry.ContactInfo;
import com.macys.sdt.framework.utils.CreditCards;
import com.macys.sdt.framework.utils.TestUsers;
import org.openqa.selenium.support.ui.Select;


import java.util.List;
import java.util.Random;

import static com.macys.sdt.framework.utils.StepUtils.onPage;
import static com.macys.sdt.framework.utils.StepUtils.pausePageHangWatchDog;
import static com.macys.sdt.framework.utils.TestUsers.getRandomValidAddress;

/**
 * Created by U063689 on 7/13/2017.
 */
public class RegistryActions {

    private static final Logger logger = LoggerFactory.getLogger(Registry.class);

    private String productName;
    private String productPrice;
    private String productColor;
    private String productSize;
    private String productBrand;
    private String storeId;
    private UserProfile profile;


    public String[] searchForValidRegistry() throws Throwable {
        CommonUtils.signInOrCreateAccount();
        Clicks.clickIfPresent("my_account.add_card_overlay_no_thanks_button");
        String[] arr = new String[2];
        arr[0] = TestUsers.currentEmail = TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName();
        arr[1] = TestUsers.currentPassword = TestUsers.getCustomerInformation().getUser().getProfileAddress().getLastName();
        logger.info("Registrant's First Name IS " + arr[0]);
        logger.info("Registrant's Last Name IS " + arr[1]);
        RegistryService.createRandomRegistry(null);
        Clicks.hoverForSelection("");
        Clicks.clickIfPresent("home.goto_sign_out_link");
        return arr;
    }

    public WebElement searchForExistingRegistryFromSearchResults(String firstName, String lastName) {
        Wait.untilElementPresent("find_registry.registrant_names");
        WebElement myRegistry = null;
        List<WebElement> fNames = Elements.findElements("find_registry.registrant_names");
        for (WebElement element : fNames) {
            System.out.println(element.getText());
            if (element.getText().toLowerCase().contains(Elements.getValues(firstName).get(0)) && element.getText().toLowerCase().contains(Elements.getValues(lastName).get(0))) {
                logger.info("First Name is found in the list");
                myRegistry = element;
                break;
            }
        }
        return myRegistry;
    }

    public WebElement selectRandomElementFromRegistry(){
        WebElement selectedElem = null;
        List<WebElement> elements = Elements.findElements(By.className("gvr-card-item"));
        for (WebElement element : elements) {
            if(element.getText().toLowerCase().contains("bag")) {
                selectedElem = element;
                System.out.println(selectedElem.getText() + " element selected");
                break;
            }
            elements.listIterator().next();
        }
        return selectedElem;
    }

    public void selectRandomStoreRegistryBOPSOverlay(){
        Wait.untilElementPresent("registry_bvr.result_available_stores");
        List<WebElement> stores = Elements.findElements("registry_bvr.result_available_stores");
        if (stores.size()!=0) {
            int random = new Random().nextInt(stores.size());
            WebElement selectedStore = stores.get(random);
            logger.info("Store selected is: " + selectedStore.getText());
            setStoreId(selectedStore.getAttribute("value"));
            selectedStore.click();
        }
        else {
            Assert.fail("There are no available stores for the selected product");
        }
    }

    public void getAttributesOfSelectedRegistryItem(WebElement selectedElement){
        setProductName(selectedElement.findElement(By.className("thumbnail-product-name")).getText());
        setProductBrand(selectedElement.findElement(By.className("thumbnail-product-brand")).getText());
        if(selectedElement.getText().toLowerCase().contains("color")){
            setProductColor(selectedElement.findElement(By.cssSelector("div.thumbnail-color>h6>span")).getText());
        }
        else {
            setProductColor("no color");
        }

        if(selectedElement.getText().toLowerCase().contains("size")){
            setProductSize(selectedElement.findElement(By.cssSelector("div.thumbnail-size>h6>span")).getText());
        }
        else {
            setProductSize("no size");
        }

        if(selectedElement.getText().toLowerCase().contains("sale")||selectedElement.getText().toLowerCase().contains("now")){
            String[] priceSale = selectedElement.findElement(By.cssSelector("div.thumbnail-price>h6:nth-child(2)")).getText().split(" ");
            setProductPrice(priceSale[1]);
        }
        else {
            System.out.println(selectedElement.getText());
            String priceReg = selectedElement.findElement(By.cssSelector("div.thumbnail-price>h6")).getText();
            setProductPrice(priceReg);
        }
    }


    public void createRegistryDifferentEmailSubscription(boolean isSubscribedToRegistryEmail) throws Throwable{
        Navigate.visit("sign_in");
        pausePageHangWatchDog();
        TestUsers.clearCustomer();
        HashMap<String, String> options = new HashMap<>();
        options.put("checkout_eligible", "true");
        options.put("paypal_eligible", "true");
        UserProfile profile = TestUsers.getCustomer(options);
        UserProfileService.createUserProfile(profile, true);
        CurrentAddress address = new CurrentAddress();
        TestUsers.getRandomValidAddress(options, address);
        String secureToken = TestUsers.getCustomer(null).getUser().getTokenCredentials().getToken();
        TestUsers.getRandomValidAddress(options, address);
        int retryCount = 0;
        while (retryCount < 7) {
            try {
                if (Elements.findElements(By.className("container-close"), WebElement::isDisplayed).size() > 0) {
                    Clicks.click(Elements.findElements(By.className("container-close"), WebElement::isDisplayed).get(0));
                }
                Registry registry = new Registry();
                registry.addRandomData();
                registry.getContactInfo().setCurrentAddress(address);
                registry.setCurrentAddress(address);
                Preferences preferences = new Preferences();
                preferences.setSubscribeWeddingSale(isSubscribedToRegistryEmail);
                registry.setPreferences(preferences);
                registry = RegistryService.createRegistry(registry, secureToken);
                if (registry.getId() != null) {
                    break;
                }
            } catch (AssertionError e) {
                System.out.println("Retrying again...");
                retryCount++;
            }
        }
        pausePageHangWatchDog();
        Assert.assertNotNull("Failed to create registry", TestUsers.getCustomer(null).getRegistry().getId());
        CommonUtils.signInToCreatedProfile();
    }




    public void createRegistryWithPartnersMailingAddressDifferent() throws Throwable {
        Navigate.visit("sign_in");
        pausePageHangWatchDog();
        TestUsers.clearCustomer();
        HashMap<String, String> options = new HashMap<>();
        options.put("checkout_eligible", "true");
        options.put("paypal_eligible", "true");
        UserProfile profile = TestUsers.getCustomer(options);
        UserProfileService.createUserProfile(profile, true);
        CreditCardService.addCreditCardToWallet(CreditCards.getValidCard("Visa"), true);

        CurrentAddress address = new CurrentAddress();
        TestUsers.getRandomValidAddress(options, address);
        String secureToken = TestUsers.getCustomer(null).getUser().getTokenCredentials().getToken();
        TestUsers.getRandomValidAddress(options, address);
        int retryCount = 0;
        while (retryCount < 7) {
            try {
                if (Elements.findElements(By.className("container-close"), WebElement::isDisplayed).size() > 0) {
                    Clicks.click(Elements.findElements(By.className("container-close"), WebElement::isDisplayed).get(0));
                }
                Registry registry = new Registry();
                CurrentAddress coAddress = new CurrentAddress();
                ContactInfo coRegistrantContactInfo = new ContactInfo();
                registry.addRandomData();
                registry.getContactInfo().setCurrentAddress(address);
                registry.setCurrentAddress(address);
                getRandomValidAddress(null, coAddress);
                coRegistrantContactInfo.setCurrentAddress(coAddress);
                registry = RegistryService.createRegistry(registry, secureToken);
                if (registry.getId() != null) {
                    break;
                }
            } catch (AssertionError e) {
                System.out.println("Retrying again...");
                retryCount++;
            }
        }
        pausePageHangWatchDog();
        Assert.assertNotNull("Failed to create registry", TestUsers.getCustomer(null).getRegistry().getId());
        CommonUtils.signInToCreatedProfile();
    }


    public void fillCreateFormUntilEventDetails(){
        Wait.forPageReady("new_create_registry");

        HashMap<String, String> options = new HashMap<>();
        options.put("checkout_eligible", "true");
        options.put("paypal_eligible", "true");
        profile = TestUsers.getCustomer(options);

        try {
                TextBoxes.typeTextbox("new_create_registry.first_name", profile.getUser().getProfileAddress().getFirstName());
                TextBoxes.typeTextbox("new_create_registry.last_name", profile.getUser().getProfileAddress().getLastName());
                TextBoxes.typeTextbox("new_create_registry.email", profile.getUser().getProfileAddress().getEmail());
                TextBoxes.typeTextbox("new_create_registry.password", profile.getUser().getLoginCredentials().getPassword());
                TextBoxes.typeTextbox("new_create_registry.verify_password", profile.getUser().getLoginCredentials().getPassword());

                if (Elements.anyPresent("new_create_registry.security_question")) {
                    DropDowns.selectByText("new_create_registry.security_question", profile.getUser().getUserPasswordHint().getQuestion());
                } else {
                    selectCustomDropDownIfPresent("new_create_registry.security_question_list", "new_create_registry.security_question_options", profile.getUser().getUserPasswordHint().getQuestion());
                }

                TextBoxes.typeTextbox("new_create_registry.security_answer", String.valueOf(profile.getUser().getUserPasswordHint().getAnswer()));
                TextBoxes.typeTextbox("new_create_registry.date_of_birth", "10/10/1980");

                if (Elements.anyPresent("new_create_registry.gender")) {
                    DropDowns.selectByText("new_create_registry.gender", profile.getUser().getGender());
                } else {
                    selectCustomDropDownIfPresent("new_create_registry.gender_list", "new_create_registry.gender_options", profile.getUser().getGender());
                }

                Clicks.click("new_create_registry.next_button");
                Wait.secondsUntilElementNotPresent("new_create_registry.section_my_account", 10);

            if(Elements.elementPresent("new_create_registry.section_my_account")){
                Assert.fail("ERROR: Create profile form was not filled with success.");
            }

        } catch (Exception e) {
            Assert.fail("ERROR: Create profile form was not filled with success.\r\n" + e.getMessage());
        }
    }

    public void continueToFillInCreateForm() throws Throwable{
        profile = TestUsers.getNewRegistryUser();

        if(Elements.elementPresent("new_create_registry.section_event_details")){
            TextBoxes.typeTextbox("new_create_registry.co_registrant_first_name", profile.getRegistry().getCoRegistrantFirstName());
            TextBoxes.typeTextbox("new_create_registry.co_registrant_last_name", profile.getRegistry().getCoRegistrantLastName());
            TextBoxes.typeTextbox("new_create_registry.event_date", "10/10/2020");
            TextBoxes.typeTextbox("new_create_registry.guests_count", profile.getRegistry().getNumberOfGuest());

            if (Elements.anyPresent("new_create_registry.store_location")) {
                DropDowns.selectByIndex("new_create_registry.store_location", 1);
            } else {
                selectCustomDropDownIfPresent("new_create_registry.store_location_list", "new_create_registry.store_location_options", profile.getRegistry().getPreferredStore());
            }

            Clicks.click("new_create_registry.next_button");
            Wait.secondsUntilElementNotPresent("new_create_registry.section_event_details", 10);

            if(Elements.elementPresent("new_create_registry.section_event_details")){
                Assert.fail("ERROR: Create profile form was not filled with success. Not able to fill the Event Details section");
            }
        }
        if (Elements.elementPresent("new_create_registry.section_address")){
            TextBoxes.typeTextbox("new_create_registry.registrant_address_line_1", profile.getRegistry().getCurrentAddress().getAddressLine1());
            TextBoxes.typeTextbox("new_create_registry.registrant_address_city", profile.getRegistry().getCurrentAddress().getCity());

            if (Elements.anyPresent("new_create_registry.registrant_address_state")) {
                DropDowns.selectByValue("new_create_registry.registrant_address_state", profile.getRegistry().getCurrentAddress().getState());
            } else {
                selectCustomDropDownIfPresent("new_create_registry.registrant_address_state_list", "new_create_registry.registrant_address_state_options",  profile.getRegistry().getCurrentAddress().getState());
            }

            TextBoxes.typeTextbox("new_create_registry.registrant_address_zip_code", profile.getRegistry().getCurrentAddress().getPostalCode());
            TextBoxes.typeTextbox("new_create_registry.registrant_address_phone", profile.getUser().getProfileAddress().getBestPhone());

            Clicks.click("new_create_registry.next_button");
            Wait.secondsUntilElementNotPresent("new_create_registry.section_address", 10);

            if(Elements.elementPresent("new_create_registry.section_address")){
                Assert.fail("ERROR: Create profile form was not filled with success. Not able to fill the Address section");
            }
        }
        if (Elements.elementPresent("new_create_registry.section_shipping")){
            Clicks.click("new_create_registry.register_button");
            Wait.forPageReady();
        }
    }

    public static void selectCustomDropDownIfPresent(String elementName, String elementOptions, String value) {
        if (Elements.elementPresent(elementName)) {
            DropDowns.selectCustomText(elementName, elementOptions, value);
        }
    }

    public static void selectPublicRegistryCheckbox(){
        if(onPage("edit_registry")){
            if (Elements.elementPresent("edit_registry.private_profile_checkbox")) {
                Elements.findElement("edit_registry.private_profile_checkbox").click();
            }
        }
        else {
            if (Elements.elementPresent("new_create_registry.private_profile_checkbox")) {
                //Clicks.selectCheckbox("new_create_registry.private_profile_checkbox");
                Elements.findElement("new_create_registry.private_profile_checkbox").click();
            }
        }
    }

    public static boolean isPrivateProfileChecked(){
        if(onPage("edit_registry")){
            return Elements.getElementAttribute("edit_registry.private_profile", "class").contains("checked");
        }else{
            return Elements.getElementAttribute("new_create_registry.private_profile", "class").contains("checked");
        }
    }

    public void createRegistryBVR(String registryType) throws Exception {
        Navigate.visit("sign_in");
        pausePageHangWatchDog();
        TestUsers.clearCustomer();
        HashMap<String, String> options = new HashMap<>();
        options.put("checkout_eligible", "true");
        options.put("paypal_eligible", "true");
        profile = TestUsers.getCustomer(options);
        UserProfileService.createUserProfile(profile, true);

        CurrentAddress address = new CurrentAddress();
        TestUsers.getRandomValidAddress(options, address);
        String secureToken = TestUsers.getCustomer(null).getUser().getTokenCredentials().getToken();
        TestUsers.getRandomValidAddress(options, address);
        int retryCount = 0;
        while (retryCount < 7) {
            try {
                if (Elements.findElements(By.className("container-close"), WebElement::isDisplayed).size() > 0) {
                    Clicks.click(Elements.findElements(By.className("container-close"), WebElement::isDisplayed).get(0));
                }
                Registry registry = new Registry();
                CurrentAddress coAddress = new CurrentAddress();
                ContactInfo coRegistrantContactInfo = new ContactInfo();
                registry.addRandomData();
                registry.getContactInfo().setCurrentAddress(address);
                registry.setCurrentAddress(address);
                getRandomValidAddress(null, coAddress);
                coRegistrantContactInfo.setCurrentAddress(coAddress);

                if(registryType.equals("private"))
                    registry.setPublicRegistry(false);
                else
                    registry.setPublicRegistry(true);

                registry = RegistryService.createRegistry(registry, secureToken);

                if (registry.getId() != null) {
                    break;
                }
            } catch (AssertionError e) {
                System.out.println("Retrying again...");
                retryCount++;
            }
        }
        pausePageHangWatchDog();
        Assert.assertNotNull("Failed to create registry", TestUsers.getCustomer(null).getRegistry().getId());
        CommonUtils.signInToCreatedProfile();
    }

    /*WIP - NOT WORKING YET*/
    public boolean isPublicRegistry(){
        HashMap<String, String> options = new HashMap<>();
        options.put("checkout_eligible", "true");
        options.put("paypal_eligible", "true");

        TestUsers.getCustomer(options).getRegistry().isPublicRegistry();

        return profile.getRegistry().isPublicRegistry();
    }

    private void setStoreId(String id){
        storeId = id;
    }

    public String getStoreId(){
        return storeId;
    }

    private void setProductPrice(String price){

        productPrice = price;
    }

    public String getProductPrice(){
        return productPrice;
    }

    private void setProductColor(String color){

        productColor = color;
    }

    public String getProductColor(){
        return productColor;
    }

    private void setProductSize(String size){

        productSize = size;
    }

    public String getProductSize(){
        return productSize;
    }

    private void setProductName(String name){

        productName = name;
    }

    public String getProductName(){
        return productName;
    }

    private void setProductBrand(String brand){

        productBrand = brand;
    }

    public String getProductBrand(){
        return productBrand;
    }

}
