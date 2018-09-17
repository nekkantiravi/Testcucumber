package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAccount;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAddressBook;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 9/19/2017.
 */
public class MyAddressBookSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(MyAddressBookSteps.class);
    static ProfileAddress profileaddressObject;

    /**
     * Verifies that user can make another address as primary
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I make second address as primary$")
    public void iMakeSecondAddressPrimary() throws Throwable {
        CommonUtils.makeSecondAddressPrimary();
    }

    @When("^I add (\\d+) addresses to my address book$")
    public void iAddAddressesToMyAddressBook(int arg1) throws Throwable {
        for (int i = 1; i <= arg1; i++) {
            iAddAnotherShippingAddress();
            Wait.forPageReady();
        }
    }

    /**
     * Verifies that user deletes 1 address
     *
     * @throws Throwable if any exception occurs
     */
    @When("^user deletes one address$")
    public void iDeleteOneAddress() throws Throwable {
        logger.info("Delete one of the address from my address book!!");
        CommonUtils.deleteOneAddress();
    }

    /**
     * Verifies that user is able to add shipping address
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I add shipping address$")
    public void iAddShippingAddress() throws Throwable {
        new MyAccount().navigateToLeftNavigationPage("my address book");
        Wait.forPageReady();
        HashMap<String, String> opts = new HashMap<>();
        opts.put("checkout_eligible", "true");
        if (MyAddressBook.isAddressAdded()) {
            new MyAddressBook().updateAddress(0, opts);
        } else {
            new MyAddressBook().addAddress(opts);
        }
        logger.info("-> Added Checkout eligible address in address book page!!");
    }

    @When("^I navigate to my address book and perform the add,edit,remove and make primary address$")
    public void iNavigateToMyAddressBookAndPerformTheAddEditRemoveAndMMakePrimaryAddress() throws Throwable {
        iAddAnotherShippingAddress();
        CommonUtils.editAddressDetails();
        CommonUtils.makeSecondAddressPrimary();
        iDeleteOneAddress();
    }

    /**
     * Verifies that user is able to add another shipping address
     *
     * @throws Throwable if any exception occurs
     */
    @When("^I add another shipping address$")
    public void iAddAnotherShippingAddress() throws Throwable {
        new MyAccount().navigateToLeftNavigationPage("my address book");
        Wait.forPageReady();
        HashMap<String, String> opts = new HashMap<>();
        opts.put("checkout_eligible", "true");
        new MyAddressBook().addAddress(opts);
        logger.info("-> Added Checkout eligible address in address book page!!");
    }

    @Then("^I can remove all of the addresses$")
    public void iCanRemoveAllOfTheAddresses() throws Throwable {
        int noOfAddress;
        if (macys())
            noOfAddress = Elements.findElements("my_address_book.first_address_removed").size();
        else
            noOfAddress = Elements.findElements("my_address_book.list_after_removed_address").size();
        for (int i = 0; i < noOfAddress; i++) {
            iDeleteOneAddress();
            Wait.forPageReady();
        }
    }

    @When("^I add an address to my address book with (missing|invalid) \"([^\"]*)\" fields?$")
    public void iAddAnAddressToMyAddressBookWithMissingFields(String option, String arg1) throws Throwable {
        new MyAccount().navigateToLeftNavigationPage("my address book");
        Wait.forPageReady();
        HashMap<String, String> opts = new HashMap<>();
        opts.put("checkout_eligible", "true");
        new MyAddressBook().addAddressWithoutFields(opts, arg1, option);
        logger.info("-> Added Checkout eligible address in address book page!!");
    }

    @Then("^I verify the error message:$")
    public void iVerifyTheErrorMessage(List<String> errorMessage) throws Throwable {
        String actualErrorMessage = Elements.findElement("my_address_book.error_message").getText();
        logger.info("Error message is: " + actualErrorMessage);
        Assert.assertTrue(actualErrorMessage.contains(errorMessage.get(0)));
    }

    @And("^I can update addresses in the address book$")
    public void iCanUpdateAddressesInTheAddressBook() throws Throwable {
        new MyAccount().navigateToLeftNavigationPage("my address book");
        HashMap<String, String> opts = new HashMap<>();
        opts.put("checkout_eligible", "true");
        new MyAddressBook().updateAddress(0, opts);
        logger.info("-> Added Checkout eligible address in address book page!!");
    }

    @Given("^I should verify the updated address in the address book page$")
    public void iShouldVerifyTheUpdatedAddressInTheAddressBookPage() throws Throwable {
        if (macys())
            Assert.assertTrue(Elements.findElement("my_address_book.status_message").getText().contains("Your entry has been successfully saved"));
        else
            Assert.assertTrue(Elements.findElement("my_address_book.status_message1").getText().contains("The entry was successfully saved"));
    }

    @Given("^I update the address in my address book with missing \"([^\"]*)\" fields$")
    public void iUpdateTheAddressInMyAddressBookWithMissingFields(String arg1) throws Throwable {
        logger.info("Edit shipping address!!");
        new MyAccount().navigateToLeftNavigationPage("my address book");
        Wait.forPageReady();
        Wait.secondsUntilElementPresent("my_address_book.edit_first_address", 30);
        Clicks.click("my_address_book.edit_first_address");
        pausePageHangWatchDog();
        TextBoxes.typeTextbox("my_address_book" + ".phone_area_code", "");
        TextBoxes.typeTextbox("my_address_book" + ".phone_exchange", "");
        TextBoxes.typeTextbox("my_address_book" + ".phone_subscriber", "");
        Clicks.click("my_address_book.update_address");
    }

    @Then("^I verify error message while adding address with invalid data or missing field on my address book page$")
    public void iVerifyErrorMessageWhileAddingAddressWithInvalidDataOrMissingFieldOnMyAddressBookPage(DataTable table) throws Throwable {
        new MyAccount().navigateToLeftNavigationPage("my address book");
        Wait.forPageReady();
        HashMap<String, String> opts = new HashMap<>();
        opts.put("checkout_eligible", "true");
        for (int i = 1; i < table.raw().size(); i++) {
            List<List<String>> dataLayer = table.raw();
            String fieldName = dataLayer.get(i).get(0);
            String value = dataLayer.get(i).get(1);
            String errorMessage = dataLayer.get(i).get(2);
            ProfileAddress addressObject = new ProfileAddress();
            TestUsers.getRandomValidAddress(opts, addressObject);
            new MyAddressBook().addressBookCommonFieldsWithInvalidOrMissingFields("my_address_book", addressObject, fieldName, value);
            String actualErrorMessage = Elements.findElement("my_address_book.error_message").getText();
            logger.info(actualErrorMessage);
            Assert.assertTrue(actualErrorMessage.contains(errorMessage));
            Wait.forPageReady();
        }
    }

    @Then("^I should see this message:$")
    public void iShouldSeeThisMessage(List<String> message) throws Throwable {
        if (macys()) {
            Assert.assertTrue(Elements.getText("my_address_book.status_message").equalsIgnoreCase(message.get(0)));
            logger.info(Elements.getText("my_address_book.status_message"));
        } else {
            Assert.assertTrue(Elements.getText("my_address_book.success_message").equalsIgnoreCase(message.get(0)));
            logger.info(Elements.getText("my_address_book.success_message"));
        }
    }

    @Then("^I verify the deleted address should not be present in the address book page$")
    public void iVerifyTheDeletedAddressShouldNotBePresentInTheAddressBookPage() throws Throwable {
        Wait.forPageReady();
        if (macys())
            Assert.assertFalse("Address not Deleted from My Address Book Page", Elements.anyPresent("my_address_book.first_address_removed"));
        else {
            Assert.assertFalse("Address not Deleted from My Address Book Page", Elements.anyPresent("my_address_book.list_after_removed_address"));
        }
    }

    @Then("^any non-primary address should have an option to make it primary$")
    public void anyNonPrimaryAddressShouldHaveAnOptionToMakeItPrimary() throws Throwable {
        List<WebElement> linkOfMakePrimary;
        if (macys())
            linkOfMakePrimary = Elements.findElements("my_address_book.all_secondary_address");
        else
            linkOfMakePrimary = Elements.findElements("my_address_book.all_secondary_address");
        for (int i = 0; i < linkOfMakePrimary.size(); i++) {
            Elements.elementShouldBePresent(linkOfMakePrimary.get(i));
        }
        logger.info("Make Primary Link is available for all the Secondary Address.");
    }

    @Then("^I should verify the primary address should not have an option to make primary$")
    public void iShouldVerifyThePrimaryAddressShouldNotHaveAnOptionToMakePrimary() throws Throwable {
        if (macys())
            Assert.assertFalse(Elements.elementPresent("my_address_book.primary_address_make_primary"));
        else
            Assert.assertFalse(Elements.elementPresent("my_address_book.primary_address_make_primary"));
        logger.info("Primary Address do not have option of Make Primary.");
    }

    @Then("^the newly added address should be displayed in my address book$")
    public void theNewlyAddedAddressShouldBeDisplayedInMyAddressBook() throws Throwable {
        if (macys())
            Assert.assertTrue(Elements.elementPresent("my_address_book.addedAddress_primary"));
        else
            Assert.assertTrue(Elements.elementPresent("my_address_book.addedAddressList"));
    }

    @Given("^I add shipping address and it should be dispalyed in my address book$")
    public void iAddShippingAddressAndItShouldBeDispalyedInMyAddressBook() throws Throwable {
        MyAddressBook myaddressbookObj = new MyAddressBook();
        new MyAccount().navigateToLeftNavigationPage("my address book");
        Wait.forPageReady();
        HashMap<String, String> opts = new HashMap<>();
        opts.put("checkout_eligible", "true");
        ProfileAddress addressObject = new ProfileAddress();
        profileaddressObject = addressObject;
        TestUsers.getRandomValidAddress(opts, addressObject);
        String pageName = "my_address_book";
        Wait.forPageReady();
        TextBoxes.typeTextbox(pageName + ".first_name", addressObject.getFirstName());
        TextBoxes.typeTextbox(pageName + ".last_name", addressObject.getLastName());
        myaddressbookObj.addressBookCommonFields(pageName, addressObject);
        Wait.forPageReady();
        logger.info("->addAddress(): Address Added!!");
        if (macys())
            Assert.assertTrue(Elements.elementPresent("my_address_book.addedAddress_primary"));
        else
            Assert.assertTrue(Elements.elementPresent("my_address_book.addedAddressList"));
    }
}
