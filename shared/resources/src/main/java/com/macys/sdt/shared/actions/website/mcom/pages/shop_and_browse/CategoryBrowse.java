package com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse;


import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.utils.StepUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class CategoryBrowse extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(CategoryBrowse.class);
    /**
     * Method to search for an item using search field
     *
     * @param item_name name of the item to search
     */
    public void searchForAnItem(String item_name) {
        try {
            TextBoxes.typeTextbox("home.search_field", item_name);
            Clicks.click("home.search_button");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to get the product count on browse page
     *
     * @return product count in browse page
     */
    public int getProductCount() {
        Wait.untilElementPresent("category_browse.product_count_span");
        String productCountText = Elements.getText("category_browse.product_count_span");
        Assert.assertFalse("ERROR - APP : Product count is not displayed on browse page!!", productCountText.isEmpty());
        return Integer.parseInt((macys() ? productCountText.trim() : productCountText.split(" ")[0]).replaceAll("\\n", "").replaceAll(" ", ""));
    }

    /**
     * Method to check whether pagination is present or not
     *
     * @return true if pagination is present, else returns false
     */
    public boolean paginationAvailable() {
        return Elements.elementPresent("category_browse.goto_current_page_number");
    }

    /**
     * Method to get the page count in browse/search page
     *
     * @return page count in browse page
     */
    public int getPageCount() {
        int pageCount = 0;
        if (paginationAvailable()) {
            if (macys()) {
                List<WebElement> pageLinks = Elements.findElements("category_browse.goto_each_number_link");
                for (WebElement link : pageLinks) {
                    if (link.getText().matches("[0-9]+") && Integer.parseInt(link.getText()) > pageCount) {
                        pageCount = Integer.parseInt(link.getText());
                    }
                }
            } else {
                String currentPage = (Elements.getText("category_browse.goto_current_page_number"));
                pageCount = Integer.parseInt(currentPage.split(" of ")[1]);
            }
        }
        return pageCount;
    }

    /**
     * Method to navigate to a specific page number
     *
     * @param number page number to which you want to navigate
     */
    public void gotoPageNumber(int number) {
        if (getPageCount() < number) {
            Assert.fail("ERROR APP: User con't navigate to " + number);
        }
        if (paginationAvailable()) {
            if (getCurrentPageNumber() == number) {
                logger.info("User is already on same page !!");
            } else {
                findPageNumber(number);
                logger.info("Navigated to " + number + " requested page");
            }
        }
    }

    /**
     * Method to navigate to a specific page number
     *
     * @param number page number to which you want to navigate
     */
    public void findPageNumber(int number) {
        while (number != getCurrentPageNumber()) {
            if (getCurrentPageNumber() < number) {
                Clicks.click("pagination_top.goto_next_page_via_arrow");
            } else {
                Clicks.click("pagination_top.goto_previous_page_via_arrow");
            }
            Wait.untilElementPresent("category_browse.loading_mask");
            Wait.untilElementNotPresent("category_browse.loading_mask");
        }
    }

    /**
     * Method to get the current page number
     *
     * @return page number
     */
    public int getCurrentPageNumber() {
        String currentPage = (Elements.getText("category_browse.goto_current_page_number"));
        return Integer.parseInt((macys() ? currentPage : currentPage.split(" of ")[0]));
    }

    /**
     * Method to sort the browse page results by using Sort by value option
     *
     * @param value to sort the browse page results
     */
    public void sortByValue(String value) {
        if (bloomingdales()) {
            DropDowns.selectCustomText("pagination.sort_by", "pagination.sort_by_options", value);
        } else {
            DropDowns.selectByText("category_browse.sort_by", value);
        }
        Wait.untilElementPresent("category_browse.loading_mask");
        Wait.untilElementNotPresent("category_browse.loading_mask");
    }

    /**
     * Method to check whether sort by option is present or not in browse page
     *
     * @return true if sort by option is present, else returns false
     */
    public boolean sortByAvailable() {
        return Elements.elementPresent("category_browse.sort_by");
    }
}
