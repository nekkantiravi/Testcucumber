package com.macys.sdt.projects.Discovery.Regression.steps.website.bcom;

import com.macys.sdt.framework.interactions.Elements;
import cucumber.api.java.en.And;
import org.junit.Assert;
/**
 * Created by u065629 on 6/1/2017.
 */
public class PaginationSteps {
    @And("^I verify there is no (left|right) arrow in browse page$")
    public void I_verify_arrow_in_pagination(String arrow){
        switch (arrow){
            case "left":Assert.assertTrue("Left arrow exists in Pagination",!Elements.elementPresent("pagination.goto_previous_page_via_arrow")); break;
            case "right": Assert.assertTrue("Right arrow exists in Pagination", !Elements.elementPresent("pagination.goto_next_page_via_arrow")); break;
        }
    }
}
