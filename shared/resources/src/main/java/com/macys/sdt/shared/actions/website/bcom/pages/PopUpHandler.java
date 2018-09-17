package com.macys.sdt.shared.actions.website.bcom.pages;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.runner.WebDriverManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import static com.macys.sdt.framework.utils.StepUtils.bloomingdales;

/**
 * Created by m645990 on 5/31/17.
 */
public class PopUpHandler {
    public static void closeEmailPopup() {
        if (bloomingdales()) {
            try {
                Clicks.clickWhenPresent("header_and_footer.email_overlay");
                Actions action = new Actions(WebDriverManager.getWebDriver());
                action.sendKeys(Keys.ESCAPE).build().perform();
            } catch (Exception e) {
                System.out.println(String.format("Email modal is not present or dismissal failed.\nException handled: %s", e));
            }
        }

    }
}
