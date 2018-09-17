package com.macys.sdt.projects.Customer.Rebuy.actions.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by YC05PG3 on 5/24/2017.
 */
public class RebuyOrderStatusPage extends StepUtils {

    private static final Logger log = LoggerFactory.getLogger(RebuyOrderStatusPage.class);

    // Returns the Total Mini bag count showing in UI top right.
    public static int getBagCount() {
        int bagCount = 0;
        try {
            if (macys()) {
                if (Elements.anyPresent("order_details.bag_count")) {
                    bagCount = Integer.parseInt(Elements.findElement("order_details.bag_count").getText().substring(1, 2));
                } else if (Elements.anyPresent("order_details.bag_count_new")) {
                    bagCount = Integer.parseInt(Elements.findElement("order_details.bag_count_new").getText());
                } else {
                    bagCount = 0;
                }
            } else if (bloomingdales()) {
                bagCount = Integer.parseInt(Elements.findElement("order_status.bag_count").getText().substring(0, 1));
            }
            return bagCount;
        } catch (Exception e) {
            log.warn("issue in retrieving bag count : " + e);
            return 0;
        }
    }

    public static WebElement getRebuyShipment(String page) {
        WebElement rebuyLn;
        switch (page) {
            case "OD":
                List<WebElement> shipments = Elements.findElements("order_details.order_details_container");
                for (WebElement e : shipments) {
                    try {
                        rebuyLn = e.findElement(Elements.element("order_details.buy_again"));
                        if (!(rebuyLn.getText().isEmpty())) {
                            return e;
                        }
                    } catch (NoSuchElementException ignored) {
                        // no action needed
                        // this is to handle cases where order container does not have buy again button
                        log.debug("element not found : " + ignored.getMessage());
                    }
                }
                break;
            case "OH":
                List<WebElement> lineItems = Elements.findElements("order_status.line_item_in_shipment");
                for (WebElement lineItem : lineItems) {
                    try {
                        rebuyLn = lineItem.findElement(Elements.element("order_status.buy_again"));
                        if (!(rebuyLn.getText().isEmpty())) {
                            return lineItem;
                        }
                    } catch (NoSuchElementException ignored) {
                        // no action needed
                        // this is to handle cases where order container does not have buy again button
                        log.debug("element not found : " + ignored.getMessage());
                    }
                }
                break;
            default:
                Assert.fail("Invalid page name: " + page);
                break;
        }
        return null;
    }

    public static void modifyProductAttributesInQuickView() {
        List<WebElement> colorSwathes = null;
        List<WebElement> sizeSwatches = null;
        try {
            DropDowns.selectByIndex("order_details.qv_size", 1);
        } catch (Exception exe) {
            log.debug("Size is not available to select");
        }
        colorSwathes = Elements.findElements("order_details.qv_colors");
        if (colorSwathes.isEmpty()) {
            log.debug("Color Swathes not available : ");
        }
        try {
            Clicks.clickRandomElement("order_details.qv_colors");
        } catch (Exception exe) {
            log.info("Unable to select color swatch");
        }

    }

    public static void openQuickView() {
        Clicks.javascriptClick("order_details.buy_again");
        do {
            try {
                Wait.secondsUntilElementPresent("order_details.quick_view_main_image", 15);
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!Elements.elementInView("order_details.quick_view_main_image"));
        log.debug("Quick Panel is loaded");
    }
}
