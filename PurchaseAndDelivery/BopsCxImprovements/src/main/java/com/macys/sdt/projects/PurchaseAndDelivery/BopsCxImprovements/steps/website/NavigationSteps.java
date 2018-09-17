package com.macys.sdt.projects.PurchaseAndDelivery.BopsCxImprovements.steps.website;

import com.macys.sdt.framework.exceptions.EnvException;
import com.macys.sdt.framework.model.Product;
import com.macys.sdt.framework.utils.TestUsers;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.ProductDisplay;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.shop_and_browse.SearchResult;
import com.macys.sdt.shared.steps.website.ShopAndBrowse;
import cucumber.api.DataTable;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;


/**
 * Created by atepliashin on 12/16/16.
 */
public class NavigationSteps {

    @When("^I navigate to product PDP page$")
    public void iNavigateToPdp(DataTable table) throws EnvException {
        Map<String, Object> criteria = new HashMap<>(table.asMap(String.class, String.class));
        Product product = TestUsers.getRandomProduct(criteria);
        assertThat("Bops product not found", product, not(nullValue()));
        Navigate.to(ProductDisplay.class, product.id);
    }

    @When("^I search for products?$")
    public void openSearchResult(DataTable table) throws EnvException {
        Map<String, Object> criteria = new HashMap<>(table.asMap(String.class, String.class));
        Product product = TestUsers.getRandomProduct(criteria);
        ShopAndBrowse.recentProduct = product;
        assertThat("Bops product not found", product, not(nullValue()));
        Navigate.to(SearchResult.class, product.categoryName);
    }
}
