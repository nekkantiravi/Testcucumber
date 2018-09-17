this.UslSignInPage = $page("USL Sign In Page", {
        get_started:     "id: createProfileContainer"
});

var uslSignInPage = new UslSignInPage(driver);
uslSignInPage.waitForIt();
uslSignInPage.get_started.click();
