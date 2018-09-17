package com.macys.sdt.shared.actions.MEW.panels;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import gherkin.lexer.Th;
import org.openqa.selenium.By;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pagination extends StepUtils {

    public static int currentPageNumber() throws InterruptedException {
        if (macys()) {
            Thread.sleep(2000);
            if (Wait.untilElementPresent("pagination.page_number_dropdown")) {
                WebElement paginationElement = Elements.findElement("pagination.page_number_dropdown");
                Select select = new Select(paginationElement);
                return Integer.parseInt(select.getFirstSelectedOption().getAttribute("value"));
            } else {
                return 1;
            }
        } else {
            if(Elements.elementPresent(("pagination.select_page_no"))) {
                String pagination = Elements.findElement("pagination.select_page_no").findElement(By.id("b-browse-pagination-result")).getText();
                Matcher matcher = Pattern.compile("\\d+").matcher(pagination);
                matcher.find();
                return Integer.valueOf(matcher.group());
            }else{
                return 1;
            }
        }
    }

    public static void selectPagination(String page) {
        Wait.secondsUntilElementPresent("pagination.pagination", 15);
        switch (page.toLowerCase()) {
            case "next":
                if (macys()) {
                    Clicks.javascriptClick("pagination.goto_next_page");
                } else {
                    Clicks.javascriptClick("pagination.next_page");
                    Wait.untilElementNotPresent("pagination.loading_mask");
                }
                Wait.forPageReady();
                break;

            case "prev":
                if (macys()) {
                    Clicks.javascriptClick("pagination.goto_previous_page");
                } else {
                    Clicks.javascriptClick("pagination.previous_page");
                    Wait.untilElementNotPresent("pagination.loading_mask");
                }
                Wait.forPageReady();
                break;

            case "last":
                if (macys()) {
                    DropDowns.selectByIndex("pagination.page_number_dropdown", pageCount() - 1);
                } else {
                    DropDowns.selectByIndex("pagination.select_page_no", Integer.parseInt(Elements.getText("pagination.last_page_number_in_pagination")));
                    Wait.untilElementNotPresent("pagination.loading_mask");
                }
                Wait.forPageReady();
                break;

            case "random":
                if (macys()) {
                    DropDowns.selectRandomValue("pagination.page_number_dropdown");
                } else {
                    DropDowns.selectRandomValue("pagination.select_page_no");
                    Wait.untilElementNotPresent("pagination.loading_mask");
                }
                Wait.forPageReady();
                break;
            default:
                Assert.fail("Invalid option");
        }
    }

    public static void selectpageByNumber(int pageNumber) throws InterruptedException {
        if (macys()) {
            Wait.secondsUntilElementPresent("pagination.pagination", 15);
            DropDowns.selectByIndex("pagination.page_number_dropdown", pageNumber - 1);
            Wait.forPageReady();
        } else {
            Utils.threadSleep(3000,"ERROR - APP: Unable to load page while paginatio");
            Wait.secondsUntilElementPresent("pagination.select_page_no", 15);
            DropDowns.selectByIndex("pagination.select_page_no", pageNumber);
            Wait.forPageReady();
        }
    }

    public static int pageCount() {
        if (macys()) {
            Wait.secondsUntilElementPresent("pagination.pagination", 15);
            if (Elements.elementPresent("pagination.page_number_dropdown")) {
                Select select = new Select(Elements.findElement("pagination.page_number_dropdown"));
                return select.getOptions().size();
            } else {
                return 1;
            }
        } else {
            Wait.secondsUntilElementPresent("pagination.select_page_no", 15);
            if (Elements.elementPresent("pagination.select_page_no")) {
                Select select = new Select(Elements.findElement("pagination.select_page_no"));
                return select.getOptions().size() - 1;
            }else{
                return 1;
            }
        }
    }
}
