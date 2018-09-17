package com.macys.sdt.projects.Marketing.UFT.steps.MEW;


import com.macys.sdt.framework.utils.StepUtils;

public class MiscellaneousSteps extends StepUtils {

    /**
     * Changes the quantity of a product in the bag page
     *
     * @throws Throwable if any exception occurs
     */
//    @When("^I update product quantity to \"([0-9]+)\"$")
//    public void i_update_product_quantity_to_number(Int quantity) throws Throwable {
//
//    }

    /**
     * Adds a random product to bag from wishlist page and selects given button
     *
     * @param action "continue shopping", "checkout" or "close"
     * @throws Throwable if any exception occurs
     */
//    @When("^I add product to my bag from wishlist page using mobile website and (continue shopping|checkout|close)$")
//    public void I_add_product_to_my_bag_from_wishlist_page_using_mobile_website_and(String action) throws Throwable {
//        Assert.assertTrue("ERROR-DATA: Unable to find available products in the wishlist", Wait.untilElementPresent("wish_list.add_to_bag_btn"));
//        Clicks.clickRandomElement("wish_list.add_to_bag_btn");
//        Wait.untilElementPresent("wish_list.add_to_bag_dialog");
//        Assert.assertTrue("ERROR-ENV: Add to bag Dialog is not presented", Elements.elementPresent("wish_list.add_to_bag_dialog"));
//        switch (action.toLowerCase()) {
//            case "continue shopping":
//                Clicks.clickIfPresent("wish_list.continue_shopping");
//                break;
//            case "checkout":
//                Clicks.clickIfPresent("wish_list.checkout");
//                break;
//            case "close":
//                Clicks.clickIfPresent("wish_list.overlay_close");
//                break;
//            default:
//                Assert.fail("Invalid option found");
//                break;
//        }
//    }

    /**
     * Selects a random product from wish list page
     *
     * @throws Throwable if any exception occurs
     */
//    @Then("^I navigate to a random product PDP from wish list page using mobile website$")
//    public void I_navigate_to_a_random_product_PDP_from_wish_list_page_using_mobile_website() throws Throwable {
//        Wait.untilElementPresent("wish_list.item_links");
//        Clicks.clickRandomElement("wish_list.item_links");
//    }
}
