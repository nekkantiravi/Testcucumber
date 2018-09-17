package com.macys.sdt.projects.PurchaseAndDelivery.Common.panels.website.my_account;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 * Created by YH03402 on 8/7/2017.
 */
@FindBy(jsonPath = "sign_in")
public class SignIn extends Page {
    public TextInput email;
    public TextInput password;
    public Button verifyPage;

    public void login(String email,String password) {
        StepUtils.pausePageHangWatchDog();
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        Wait.until(verifyPage::isDisplayed, 20);
        Clicks.javascriptClick(verifyPage);
        StepUtils.resumePageHangWatchDog();
    }

    public boolean isReady() {
        super.isReady();
        return email.isDisplayed();
    }
}
