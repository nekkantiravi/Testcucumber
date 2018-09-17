package com.macys.sdt.projects.SolutionDevelopment.PixelsProject.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PixelsProjectSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(PixelsProjectSteps.class);

    @Then("^I verify that pixels \"([^\"]*)\" tag has fired containing with \"([^\"]*)\" attribute containing the string \"([^\"]*)\"$")
    public void i_verify_that_pixels_have_fired(String tag, String attr, String str) throws Throwable {
        str = str.toLowerCase();
        boolean pixelFired = false;
        List<WebElement> elements = Elements.findElements(By.tagName(tag));

        for (WebElement element : elements) {
            String element_attr = element.getAttribute(attr).toLowerCase();
            if (element_attr.contains(str)) {
                pixelFired = true;
                logger.info("Pixel fired contains " + attr + ": " + element_attr);
                break;
            }
        }
        Assert.assertTrue("ERROR: Pixels were not fired", pixelFired);
    }
}
