package com.macys.sdt.projects.Discovery.Regression.steps.website.bcom;


import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import cucumber.api.java.en.Then;
import org.junit.Assert;
/**
 * Created by u065629 on 6/6/2017.
 */
public class IShipSteps {

    @Then("^I verify currency in category browse page and PDP page$")
    public void iVerifyCurrencyInCategoryBrowsePageAndPDPPage() throws Throwable{
        String price = (Elements.findElement("category_browse.price_normal")).getText();
        Assert.assertTrue("ERROR - APP: Expected Currency:"+ShopAndBrowse.currency+" doesn't match in Browse Page price:"+price,price.contains(ShopAndBrowse.currency));
        new ShopAndBrowse().I_select_a_random_product("member", null);
        String pdp_price = Elements.findElement("product_display.price").getText();
        Assert.assertTrue("ERROR - APP: Expected Currency:"+ShopAndBrowse.currency+" doesn't match in PDP price:"+price,pdp_price.contains(ShopAndBrowse.currency));
    }

    @Then("^I verify the international context page$")
    public void iVerifyTheInternationalContextPage() {
        Assert.assertTrue("Country dropdown is not present",Elements.anyPresent("international_shipping.country"));
        Assert.assertTrue("Currency dropdown is not present",Elements.anyPresent("international_shipping.currency"));
        Assert.assertTrue("Ship to US button is not present",Elements.anyPresent("international_shipping.ship_to_us_address"));
    }
}
