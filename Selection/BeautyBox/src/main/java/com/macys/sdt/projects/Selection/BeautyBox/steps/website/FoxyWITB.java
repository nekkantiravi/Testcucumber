package com.macys.sdt.projects.Selection.BeautyBox.steps.website;

import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;


/**
 * Created by yh00119 on 10/19/16.
 */
public class FoxyWITB extends StepUtils {

    @Then("^I see beauty box on home page$")
    public void I_see_beauty_box_on_home_page() throws Throwable {

        Navigate.visit("http://172.17.3.24:8081/subscription/monthlybox#");

        Wait.secondsUntilElementPresent("what_in_the_box.bb_home", 10);
        boolean strBB = Elements.elementPresent("what_in_the_box.bb_home");

        if (strBB) {
            Assert.assertTrue("Able to see beautybox on home page", true);
        } else {
            Assert.fail("Doesn't find Beauty Box on home page");
        }
    }

    @And("^I connect to monthlybox url$")
    public void I_connect_to_monthlybox_url() throws Throwable{

        Navigate.visit("http://172.17.3.24:8081/subscription/monthlybox#");

    }


    @And("^I see dropdown to select the monthly beauty box$")
    public void I_see_dropdown_to_select_the_monthly_beauty_box() throws Throwable {

        Thread.sleep(5000);
        String sMonth = Elements.findElement("what_in_the_box.select_bb_month").getText();

        System.out.println("Months in the monthly beauty box drop down" + sMonth);
    }

    @When("^I select a specified month from dropdown$")
    public void I_select_a_specified_month_from_dropdown() throws Throwable {

        boolean whatBox = Elements.elementPresent("what_in_the_box.select_bb_month");

        if (whatBox) {
            DropDowns.selectByText("what_in_the_box.select_bb_month", "AUGUST 2017");
            Assert.assertTrue("Selected month from dropdown to see products what in the box", true);
            Wait.secondsUntilElementPresent("what_in_the_box.bb_product_list", 10);

        } else {
            Assert.fail("Unable to select month from dropdown to see what in the box");
        }

    }


    @Then("^I should see sample products for that month$")

    public boolean I_should_see_sample_products_for_that_month() throws Throwable {

        if (Elements.elementPresent("what_in_the_box.bb_product_list")) {
            Assert.assertTrue("Able to see what in the box for selected month", true);
            return true;
        } else {
            Assert.fail("There are no list of BB products for selected month");
            return false;
        }


    }

    @Then("^I should not see header and footer on home$")
    public void I_should_not_see_header_and_footer_on_homepage() throws Throwable {

        Navigate.visit("http://172.17.3.24:8081/subscription/monthlybox?renderMode=client");

        int iLogo, iHeadSear, iFootBb;

        Elements.findElements(By.xpath("//*[@id='logo']")).size();

        iLogo = Elements.findElements(By.xpath("//*[@id='logo']")).size();

        if (iLogo == 1) {
            Navigate.visit("http://172.17.3.24:8081/subscription/monthlybox?renderMode=client&layout=min-version");
        }


        iHeadSear = Elements.findElements(By.xpath(".//*[@id='core']")).size();

        iFootBb = Elements.findElements(By.xpath("html/body/footer")).size();

        if (iHeadSear == 0 && iFootBb == 0) {
            Assert.assertTrue("WITB page doesn't have header and footer", true);

        } else {
            Assert.fail("WITB page has header and footer");
        }

    }


}
