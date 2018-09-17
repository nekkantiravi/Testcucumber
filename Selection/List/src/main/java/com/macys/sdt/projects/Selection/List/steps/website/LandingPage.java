package com.macys.sdt.projects.Selection.List.steps.website;

/**
 * Created by m657444 on 10/27/17.
 */

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Selection.List.actions.responsive.mcom.AddToListService;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.json.JSONObject;
import org.junit.Assert;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.TestUsers;


import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;


public class LandingPage extends StepUtils {

    private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
    SoftAssertions softly = new SoftAssertions();
    int impacespace = 6;


    @Then("^I verify the basic components of landingpage for \"([^\"]*)\" user$")
    public void landing_page_components_verification(String user_type) throws Throwable  {


        if (user_type.equals("Guest")) {
            Thread.sleep(5000);

            softly.assertThat(Elements.elementPresent("listhome.user_type")).as("Guest Info is not seen").isEqualTo(true);
            softly.assertThat(Elements.elementPresent("listhome.btn_signin")).as("Signin button is not showing up").isEqualTo(true);
            String username = Elements.findElement(Elements.element("listhome.list_title_name")).getText();
            softly.assertThat(username.equals("Guest List")).as("Guest user name" + username).isEqualTo(true);
            logger.info(String.format("List name: " + username));
            softly.assertAll();
        }
        if (user_type.equals("Signedin")) {
            Thread.sleep(5000);
            //need to add find a list verification and item container
            String firstname = TestUsers.getCustomerInformation().getUser().getProfileAddress().getFirstName();
            String username = Elements.findElement(Elements.element("listhome.list_title_username")).getText();
            String userFirstname = username.split(" ")[0];
            logger.info(String.format("List name: " + userFirstname));
            softly.assertThat(firstname.equals(userFirstname.split("'")[0])).as("signedin user name" + firstname).isEqualTo(true);
           // softly.assertThat(Elements.elementPresent("listhome.create_list_link")).as("Create List Link is not seen").isEqualTo(true);
            //String defaultlist = Elements.findElement(Elements.element("listhome.list_item_default")).getText();
            //String defaultlist = Elements.findElement(By.cssSelector(".lists--number-items")).getText();

            //String defaultlst = defaultlist.split(" ")[2];
            //logger.info(String.format("List default: " + defaultlst));
            //softly.assertThat(defaultlst.equals("Default")).as("Default List "+ defaultlst).isEqualTo(true);

            softly.assertAll();
        } else {
            logger.warning("The usertype didnot match" + user_type);
        }

    }

    //verifying the Placeholdercnt, GuestList/Signedin List, DefaultItems(or) zero items

    @Then("^I verify \"([^\"]*)\" items and \"([^\"]*)\" place holders with \"([^\"]*)\"$")
    public void verify_the_items_and_spaces_on_landingpage(String cntofitems, String cntofemptyspace, String itemexist) throws Throwable  {
        int itemcount = Integer.parseInt(cntofitems);
        int spacecnt = Integer.parseInt(cntofemptyspace);
        String itemscnt = " ";
        String noofitems = " ";
        List<WebElement> placeholdercnt;
        String listName = " ";
        int Imageitmcnt = 0;

        switch (itemexist) {
            case "NoItem":
                //Assert.assertTrue("ERROR - Desktop: The Image place holder should be six but its not matching the count",placeholdercnt.size()==spacecnt);

              //  noofitems = Elements.findElement(Elements.element("listhome.list_item_default")).getText();
                noofitems = Elements.findElement(By.cssSelector(".m-lists-number-items")).getText();
                itemscnt = noofitems.split(" ")[0];
                Imageitmcnt = Integer.parseInt(itemscnt);
                placeholdercnt = Elements.findElements(Elements.element("listhome.list_number_space"));
                softly.assertThat(itemcount == Imageitmcnt).as("The image item count is Zero" + Imageitmcnt).isEqualTo(true);
             //  softly.assertThat(placeholdercnt.size() == spacecnt).as("Desktop: The Image place holder should be six but its not matching the count").isEqualTo(true);
                listName = Elements.findElement(By.cssSelector(".m-lists-name")).getText();
                softly.assertThat(listName.equals("Guest List")).as("Guest user name" + listName).isEqualTo(true);
                softly.assertAll();
                break;

            case "Item":
                noofitems = Elements.findElement(Elements.element("listhome.list_item_default")).getText();
                itemscnt = noofitems.split(" ")[0];
                Imageitmcnt = Integer.parseInt(itemscnt);

                List<WebElement> actualimagecnt = Elements.findElements(Elements.element("listhome.list_number_image"));

                placeholdercnt = Elements.findElements(Elements.element("listhome.list_number_space"));

                softly.assertThat(itemcount == Imageitmcnt).as("The image item count is not Zero" + Imageitmcnt).isEqualTo(true);
              //  softly.assertThat(placeholdercnt.size() == spacecnt).as("Desktop: The Image place holder should be 5 but its not matching the count").isEqualTo(true);
                softly.assertThat(actualimagecnt.size() == itemcount).as("Desktop: The actual Image place holder should be 1 but its not matching the count").isEqualTo(true);

                logger.info(String.format("placeholder: " + placeholdercnt.size()));
                logger.info(String.format("itemcnte: " + itemscnt));
                listName = Elements.findElement(Elements.element("listhome.list_name_withitem")).getText();
                softly.assertThat(listName.equals("Guest List")).as("Guest user name" + listName).isEqualTo(true);

                break;


        }


    }


    @Then("^I click on empty list$")
    public void click_on_empty_list_on_landingpage() {
        Wait.secondsUntilElementPresent("listhome.list_landing_view", 30);
        Clicks.click(Elements.element("listhome.list_landing_view"));
    }

    @Then("^I verify user landed on landing page$")
    public void verify_landing_page_url() {
        Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("/wishlist/home"));
    }

    @Then("^I verify user should land on empty \"([^\"]*)\" list page$")
    public void landon_empty_list_page(String User_type) {
        if (User_type.equals("Guest")) {
            Assert.assertTrue(WebDriverManager.getWebDriver().getCurrentUrl().contains("/wishlist/mylist"));
        }
    }

    @Then("^I verify \"([^\"]*)\" List placeholder on Landing page$")
    public void i_verify_List_placeholder_on_Landing_page(Integer itemcount) throws Throwable {
        String itemscnt = " ";
        String noofitems = " ";
        int Imageitmcnt = 0;

       // noofitems = Elements.findElement(Elements.element("listhome.list_holder_count")).getText();
        noofitems = Elements.findElement(By.xpath(".//*[@id='listsHeaderView']/section/div[1]/div[2]/div")).getText();

        itemscnt = noofitems.split(" ")[0];
        Imageitmcnt = Integer.parseInt(itemscnt);
        softly.assertThat(itemcount == Imageitmcnt).as("there are two placeholders" + Imageitmcnt).isEqualTo(true);

    }

    @Then("^Make List as searchable and verify it on settings popup$")
    public void make_List_as_searchable_and_verify_it_on_settings_popup() throws Throwable {

        Thread.sleep(1000);
        Clicks.click("listhome.settings_link");

        Assert.assertTrue(Elements.findElement(Elements.element("listhome.make_search_checkbox")).isSelected());

    }


    @Then("^I click on searchable Create list \"([^\"]*)\"$")
    public void i_click_on_Create_list(String list) throws Throwable {
        Wait.secondsUntilElementPresent("listhome.create_list_link", 30);
        Clicks.click(Elements.element("listhome.create_list_link"));
        TextBoxes.typeTextbox("listhome.create_list_text", list);
        Clicks.click("listhome.create_searchable");
        Clicks.click("listhome.create_button");
    }


    @Then("^I should be able to create new \"([^\"]*)\" and count \"([^\"]*)\" using service$")
    public void i_should_be_able_to_create_new_and_count_using_service(String list_name, int count) throws Throwable {

        AddToListService create_list = new AddToListService();
        for(int i=0;i <count;i++)
            create_list.createList(list_name+i);
    }

    @Then("^I land on MEW List landing page$")
    public void i_land_on_MEW_List_landing_page() throws Throwable {
        Navigate.visit(RunConfig.url + "/wishlist/home?experience=responsive");

    }

}
