package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.checkout;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 * Created by vkatru on 6/27/2017.
 */

@FindBy(jsonPath = "responsive_footer")
public class FooterSection extends Page {

    public HtmlElement footerContainer;
    public HtmlElement contactUs;
    public HtmlElement bagId;
    public HtmlElement privacyPolicy;
    public HtmlElement shippingPolicy;
    public HtmlElement returnPolicy;
    public HtmlElement copyrightCopy;

    public String getBagId() {
        return bagId.getText().replaceAll("\\D+", "");
    }

    public boolean isPolicyLinksDisplayed() {
        return Wait.until(() -> privacyPolicy.isDisplayed() && shippingPolicy.isDisplayed() && returnPolicy.isDisplayed());
    }

    @Override
    public void waitForReady() {
        super.waitForReady();
        Wait.until(() -> footerContainer.isDisplayed() && contactUs.isDisplayed() && isPolicyLinksDisplayed());
    }
}
