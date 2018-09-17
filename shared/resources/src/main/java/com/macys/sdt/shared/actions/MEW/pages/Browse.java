package com.macys.sdt.shared.actions.MEW.pages;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Browse extends StepUtils {

    /**
     * Apply given sort by option on browse page
     *
     * @param value to select from sort by drop down
     */
    public void sortBy(String value) {
        if (Elements.elementPresent("category_browse.sort_by")) {
            Clicks.click("category_browse.sort_by");
            DropDowns.selectByText("category_browse.sort_by_select", value);
            Clicks.click("category_browse.apply");
            Wait.forLoading(By.id("loading_mask"));
        } else {
            Assert.fail("Unable to find required brand");
        }

    }

    /**
     * Method to get the current page number
     *
     * @return current page number or 0 if pagination not available
     */
    public int getCurrentPageNumber() {
        String currentPageNumber = null;
        if (Elements.elementPresent("pagination.page_number_dropdown")) {
            currentPageNumber = Elements.getElementAttribute("pagination.page_number_dropdown", "value");
        } else if (Elements.elementPresent("pagination.current_page_number")) {
            currentPageNumber = Elements.getText("pagination.current_page_number");
        }
        return Utils.parseInt(currentPageNumber, 0);
    }

    /**
     * Method to get the category id from the url
     *
     * @return category id from the url
     */
    public String getCategoryId() {
        String  currentUrl = WebDriverManager.getCurrentUrl();
        Matcher matcher = Pattern.compile("id=(\\d+)").matcher(currentUrl);
        Assert.assertTrue("Category ID is not present in the current URL: " + currentUrl, matcher.find());
        return matcher.group(1);
    }

    /**
     * Method to get the products ids from the browse page
     *
     * @return list of products ids from the browse page
     */
    public List getProductsIds() {
        return Elements.findElements("category_browse.browse_thumbnail_wrapper").stream().map((e) -> Integer.parseInt(e.getAttribute("data-product_id"))).collect(Collectors.toList());
    }

    /**
     * Method to get products count from the browse page
     *
     * @return products count from the browse page
     */
    public Integer getProductsCount() {
        return Integer.parseInt(Elements.getText("category_browse.total_products").replaceAll("\\D+", ""));
    }

}
