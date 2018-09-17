package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.checkout.Repayment;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.List;

import static com.macys.sdt.projects.PurchaseAndDelivery.Common.navigation.Navigate.*;

/**
 * Created by atepliashin on 1/28/17.
 */
@FindBy(jsonPath = "order_status")
public class OrderStatus extends Page {

    public TextInput orderNumber;
    public TextInput email;
    public Button viewOrderDetails;
    public List<Button> orderDetailsButtons;
    public List<Button> updatePaymentButtons;
    public List<HtmlElement> orderNumberDetail;
    public List<HtmlElement> declinedPaymentError;

    public OrderDetails lookupByEmail(String orderNumber, String email) {
        this.orderNumber.sendKeys(orderNumber);
        Wait.until(() -> this.email.isDisplayed());
        this.email.sendKeys(email);
        Wait.until(() -> viewOrderDetails.getAttribute("disabled") == null);
        viewOrderDetails.click();
        return get(OrderDetails.class);
    }

    public OrderDetails orderDetails(int index) {
        Wait.until(() -> orderDetailsButtons.size() > 0);
        orderDetailsButtons.get(index).click();
        return get(OrderDetails.class);
    }

    public Repayment updatePayment(int index) {
        Wait.until(() -> updatePaymentButtons.size() > 0);
        updatePaymentButtons.get(index).click();
        return get(Repayment.class);
    }

    public String getOrderNumber(int index) {
        Wait.until(() -> orderNumberDetail.size() > 0);
        return orderNumberDetail.get(index).getText();
    }
}
