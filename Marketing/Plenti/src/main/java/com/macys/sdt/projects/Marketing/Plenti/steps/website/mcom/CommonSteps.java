package com.macys.sdt.projects.Marketing.Plenti.steps.website.mcom;

import com.macys.sdt.framework.utils.StepUtils;

public class CommonSteps extends StepUtils {

   /* @And("^I checkout until I reach \"([^\"]*)\" page as a \"([^\"]*)\" user$")
    public void iCheckoutUntilIReachPageAsAUser(String pageName, String userType) throws Throwable {
        String country = "United States";
        userType = userType.toLowerCase();
        boolean bops = userType.contains("bops");
        boolean signIn = userType.contains("signed in") || signedIn();
        HashMap<String, String> opts = new HashMap<>();

        boolean iship = userType.contains("iship") || !country.equalsIgnoreCase("United States");
        opts.put("country", country);
        opts.put("checkout_eligible", "true");

        if (!(onPage("responsive_checkout", "checkout"))) {
            new CheckoutUtils().navigateToCheckout(signIn, iship);
        }

        // checkout can take some time, page hang watchdog can safely be paused
        pausePageHangWatchDog();
        Checkout checkout = new Checkout(opts, bops);
        if (iship) {
            checkout.ishipCheckout(pageName);
        } else {
            CheckoutUtils.RCPage page = CheckoutUtils.RCPage.fromString(pageName);
            if (signIn) {
                checkout.rcSignedIn(page, opts);
            } else {
                checkout.rcGuest(page, opts);
            }
        }
        resumePageHangWatchDog();
    } */
}
