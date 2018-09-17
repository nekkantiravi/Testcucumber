package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import cucumber.api.java.en.Given;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by YH03383 on 5/25/2017.
 */
public class Iship extends StepUtils {

    public static final Logger logger = LoggerFactory.getLogger(Iship.class);

    /*
* Verifying product count on search result page on Iship mode
* */
    @Given("^I verify product count$")
    public void i_verify_product_count() throws Throwable {
        logger.info("Product Count::" + Elements.getText("search_result.product_count"));
        String productCount = Elements.getText("search_result.product_count").trim();
        productCount = productCount.contains("items") ? productCount.split(" ")[0] : productCount;
        int product_count = Integer.parseInt(productCount);
        logger.info("product count=" + product_count);
        Assert.assertTrue("Search is not working", (product_count > 1000));
    }
}
