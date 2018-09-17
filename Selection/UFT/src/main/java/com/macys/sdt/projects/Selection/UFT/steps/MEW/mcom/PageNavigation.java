package com.macys.sdt.projects.Selection.UFT.steps.MEW.mcom;

import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.MEW.panels.GlobalNav;
import cucumber.api.java.en.And;

public class PageNavigation extends StepUtils {

    /**
     * Open global navigation
     *
     * @throws Throwable if any exception occurs
     */
    @And("^I open the global navigation menu$")
    public void I_open_the_global_navigation_menu() throws Throwable {
        GlobalNav.openGlobalNav();
    }
}