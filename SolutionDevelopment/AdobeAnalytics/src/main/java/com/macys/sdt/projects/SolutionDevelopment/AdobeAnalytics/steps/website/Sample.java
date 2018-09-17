package com.macys.sdt.projects.SolutionDevelopment.AdobeAnalytics.steps.website;

import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Given;

public class Sample extends StepUtils {
    @Given("^I visit a product PDP page of Adobe Analytics Enabled$")
    public void i_visit_a_product_pdp_page_of_adobe_analytics_enabled() {
        Navigate.visit("http://digitalproductui-rpdp-17za.tbe.zeus.fds.com:8080/shop/product/seo-friendly-name?ID=1310");
    }
}
