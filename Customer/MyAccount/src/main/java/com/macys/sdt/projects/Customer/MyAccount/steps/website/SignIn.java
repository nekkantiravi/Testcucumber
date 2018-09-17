package com.macys.sdt.projects.Customer.MyAccount.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.macys.sdt.framework.interactions.Navigate.switchWindow;

/**
 * Created by Manjeet Ranga on 08/09/2017.
 */
public class SignIn extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(SignIn.class);

    @And("^I select See All The Benefits Of Creating A Profile on sign in page$")
    public void iSelectSeeAllTheBenefitsOfCreatingAProfileOnSignInPage() throws Throwable {
        Clicks.click("sign_in.create_profile_benefits_new");
        switchWindow(1);
        onPage("create_profile_benefit");
    }

    @And("^I verify below \"([^\"]*)\" on create profile benefits pop up banner$")
    public void iVerifyBelowHeaderOnCreateProfileBenefitsPopUpBanner(String element, List<String> content) throws Throwable {
        String actualMsg = "";

        switch (element) {
            case "header":
                actualMsg = Elements.findElement("create_account_benefits.pop_up_banner_header").getText();
                logger.info("header text: " + actualMsg);
                Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + content.get(0), content.get(0).trim().equals(actualMsg.trim()));
                break;
            case "description":
                actualMsg = Elements.findElement("create_account_benefits.pop_up_banner_desc").getText();
                logger.info("description text: " + actualMsg);
                Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + content.get(0), content.get(0).trim().equals(actualMsg.trim()));
                break;
        }
    }

    @And("^I verify below content on create profile benefits pop up banner$")
    public void IVerifyBelowContentOnCreateProfileBenefitsPopUpBanner(DataTable data) throws Throwable {
        String errorMessage = "ERROR - APP: Content is not updated on create profile benefits pop up banner";
        String actualMsg = Elements.findElement("create_account_benefits.pop_up__banner_content").getText();
        logger.info("content text: " + actualMsg);
        List<List<String>> listData = data.raw();
        for (int i = 0; i < listData.size(); i++) {
            Assert.assertTrue("ERROR - APP: " + actualMsg + "  is not equal to " + actualMsg, actualMsg.trim().contains(listData.get(i).get(0)));
        }
    }
}
