package com.macys.sdt.projects.Stores.STAT.actions.app;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GlobalNavigation extends StepUtils {

    public void navigateGlobalNav(List<String> path) {
        openGlobalNav();
        path.forEach(item -> {
            for (WebElement el : Elements.findElements("global_nav.tier_2_items")) {
                if (el.getText().equals(item)) {
                    Clicks.click(el);
                    break;
                }
            }
        });
        closeGlobalNav();
    }

    public void openGlobalNav() {
        if (!Elements.elementPresent("global_nav.tier_1_categories"))
            Clicks.click("global_nav.menu_button");
    }

    public void closeGlobalNav() {
        if (Elements.elementPresent("global_nav.tier_1_categories"))
            Clicks.click("global_nav.menu_button");
    }

}
