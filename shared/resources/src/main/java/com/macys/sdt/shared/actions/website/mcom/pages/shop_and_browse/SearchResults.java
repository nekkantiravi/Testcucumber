package com.macys.sdt.shared.actions.website.mcom.pages.shop_and_browse;

import org.openqa.selenium.By;

public class SearchResults {

    public static By showItemsPerPage(String items) {
        return By.cssSelector(".perPage>li:nth-child("+String.valueOf(Integer.parseInt(items)/60)+")");
    }

    public static By selectGridColumns(String cols) {
        return cols.equalsIgnoreCase("3") ? By.className("gridCol3") : By.className("gridCol4");
    }
}
