package com.macys.sdt.projects.Customer.MensWearHouse.steps.mew;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.steps.MEW.MyAccount;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.util.List;

import org.junit.Assert;

/**
 * Created by kvenkumahanthi on 2/28/2017.
 */
public class mew extends StepUtils {
    @Then("I should see below tux items in mobile website od page$")
    public void I_should_see_below_tux_items_in_mobile_websiteod_page() throws Throwable
    {
        pausePageHangWatchDog();
        onPage("order_details");
        List<Product> productDetails = MyAccount.tux_productDetailsODPage();
        for (Product p : productDetails) {
            long reservation_id = p.reservation_id;
            System.out.println(reservation_id);
            Integer quantity = p.quantity;
            System.out.println(quantity);

        }
        resumePageHangWatchDog();
    }
    @And("^I select tuxedo \"([^\"]*)\" on od page$")
    public void I_select_tuxedo_links_on_od_page() throws Throwable
    {
        Assert.assertTrue("Tuxedo product name",Elements.elementPresent("order_details.tux_productname"));
        Clicks.click("tuxedo product name");
    }

}
