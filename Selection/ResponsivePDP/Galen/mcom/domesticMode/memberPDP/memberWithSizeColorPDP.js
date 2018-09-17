
this.ResponsivePDP = $page("ResponsivePDP",{
    addToBag:                          "css:     [data-auto='add-to-bag']",
    addToList:                         "css:     [data-auto='add-to-list']",
    continueShoppingBtn:               "css:     [data-auto='continue-shopping']"
});

var ResponsivePDP = new ResponsivePDP(driver);
Thread.sleep(1000);
ResponsivePDP.addToBag.click();
Thread.sleep(3000);
ResponsivePDP.continueShoppingBtn.click();
Thread.sleep(1000);
ResponsivePDP.addToList.click();
// ResponsivePDP.waitForIt();
// driver.findElement(By.cssSelector("[data-auto='add-to-bag']") ).click();
Thread.sleep(1000);