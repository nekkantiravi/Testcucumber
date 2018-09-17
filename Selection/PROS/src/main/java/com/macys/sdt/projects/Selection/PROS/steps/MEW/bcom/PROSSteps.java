package com.macys.sdt.projects.Selection.PROS.steps.MEW.bcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import org.junit.Assert;


/**
 * Created by u065629 on 4/13/2017.
 */
public class PROSSteps extends StepUtils {

   @And("I click first recommended product from pdp page using mobile website")
   public void i_click_first_recommended_product_from_pdp_page_using_mobile_website() throws Throwable {
       Assert.assertTrue("No PROS", Wait.untilElementPresent("product_display.recommended_product"));
       String lastUrl = MainRunner.currentURL;
       System.out.println("Last PDP url:" + lastUrl);
       Clicks.click("product_display.recommended_product");
       Assert.assertTrue("Did not navigate to pdp", Wait.untilElementPresent("product_display.recommended_product"));
       System.out.println("URL after clicking PROS: " + url());
       if (url().equals(lastUrl))
           Assert.fail("User did not navigate to PROS PDP upon clicking");
   }
}
