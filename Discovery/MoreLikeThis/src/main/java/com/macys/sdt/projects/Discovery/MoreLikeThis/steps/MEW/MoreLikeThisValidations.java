package com.macys.sdt.projects.Discovery.MoreLikeThis.steps.MEW;

import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.macys.sdt.shared.steps.website.PageNavigation;

/**
 * Created by Davinder Singh(YC03DS3) on 8/7/2017.
 */
public class MoreLikeThisValidations extends StepUtils {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(MoreLikeThisValidations.class);
    private static final String moreLikeThisOptionTxt1 = "This one might be one-of-a-kind…";
    private static final String moreLikeThisOptionTxt2 = "This is rather unique.";
    private static final String moreLikeThisOptionTxt3 = "Check back again—we update daily!";
    private static final String moreLikeThisOptionTxt4 = "These results are the closest matches for this product";
    private static final String browseOrSearchFailureMsg1 = "Hmm...looks like we're having some technical issues.";
    private static final String browseOrSearchFailureMsg2 = "Sorry, this page is no longer available.";
    private String currentWebUrl;
    private int assumedProductListedOnPage = 48;
    private boolean onSearchOrBrowseResultsPage = true;
    private static final int minimumExpectedProductCount = 1;
    private Map<String, String> productInfo = new HashMap<>();
    private int index = 0;
    private boolean mltViaSearch = false;
    private int[] indexValuesArray;
    private int currentLevelOfMltValidation;
    private int currentPageNo;
    private boolean farResultPage = false;
    private boolean checkExistingFuncOnMltPage = false;
    private boolean iShipModeIsActive = false;
    private boolean invalidFobCategoryCombination = false;

    @And("I navigate to a far results page$")
    public void i_navigate_to_a_far_results_page() throws Throwable{
        Random r = new Random();
        int low=0;
        int totalProducts = 0;
        WebElement el = null;
        farResultPage = true;

        if(Wait.secondsUntilElementPresent("search_result.total_item_count", 30)){
            //element appeared or is present
            String str = Elements.getText("search_result.total_item_count");
            if(str.length() != 0){
                /*
                For some reason, some scenario fail at below line in spite of the above if() condition.
                adding try-catch here
                 */
                try{
                    totalProducts = Integer.parseInt(str.substring(1,str.length()-1));
                    logger.info("Total products in this category are: " + totalProducts);
                }
                catch(Exception e){
                    logger.info("Length of total item count is: " + str.length());
                    logger.info("total item count text found is: " + str);
                }
            }
            else{
                /*
                In certain cases, it is seen that although total_item_count element exists but
                its value did not get retrieved. If that is the case try again..
                 */
                logger.info("First attempt to find out total products on browse / search results page failed. Trying again.");
                Thread.sleep(2000);
                str = Elements.getText("search_result.total_item_count");
                totalProducts = Integer.parseInt(str.substring(1,str.length()-1));
                logger.info("Total products in this category are: " + totalProducts);
            }
        }else{
            logger.info("total_item_count element is not available. I do not know how many products are found for this category via browse or search.");
        }
        if(Wait.secondsUntilElementPresent("search_result.search_result.body", 30)){
            el = Elements.findElement("search_result.body");
            if(el != null){
                logger.info("<body> element is not found");
                el.sendKeys(Keys.END);
                Thread.sleep(1500);
                el.sendKeys(Keys.PAGE_UP);
            }else{
                logger.info("<body> element is not found which is needed to navigate to the end of the web page");
            }
        }
        else{
            //<search_result.body> element is not found..
                if(totalProducts == 0){
                    //<search_result.total_item_count> is also not found..
                    Assert.fail("This FOB category combination is not valid for MLT project. Aborting this scenario.");
                }
        }
        if(Wait.secondsUntilElementPresent("search_result.page_dropdown", 5)){
            el = Elements.findElement("search_result.page_dropdown");
            if(el != null){
                int totalPages = (DropDowns.getAllValues("search_result.page_dropdown").size());
                if(totalPages > 1){
                    int result = r.nextInt(totalPages-low) + low;
                    if(result > 1){
                        logger.info("Total number of result pages are: " + totalPages);
                        /*
                        If random page selected value in result is 2 then i do not need to subtract
                        -1 as 2-1 would be 1 and i am already on that page.
                         */
                        if(result == 2){
                            logger.info("I am navigating to results page number: " + (result));
                            DropDowns.selectByIndex("search_result.page_dropdown", (result));
                            currentPageNo = result;
                        }
                        else{
                            logger.info("I am navigating to results page number: " + (result-1));
                            //-1 because in drop down index starts at 0.
                            DropDowns.selectByIndex("search_result.page_dropdown", (result-1));
                            currentPageNo = (result-1);
                        }
                    }
                }
            }
        }else{
            logger.info("Total products searched are: " + totalProducts + " which explains why pagination drop down is not found.");
            el = Elements.findElement("search_result.body");
            if(el != null){
                el.sendKeys(Keys.HOME);
                Thread.sleep(1000);
            }
        }
    }

    @When("I click on More Like This link reached through \"([^\"]*)\"$")
    public void i_Click_On_More_Like_This_Link(String route) throws Throwable{
        if(route.equals("search")){
            Navigate.browserRefresh();
        }
        clickOnMoreLikeThisLink(false);
    }

    @Then("I should be shown matching products if any or one of the expected messages$")
    public void i_Should_be_Shown_Matching_Products_If_Any_Or_One_Of_The_Expected_Messages() throws Throwable{
        if(!invalidFobCategoryCombination)
            evaluateMoreLikeThisLinkClickResult(false);
    }

    private boolean evaluateMoreLikeThisLinkClickResult(boolean findMatchingProductsMLTLink)throws Throwable{
        String resultUrl;
        boolean matchingProductsMLTLinkFound = false;

        /*
        As discussed with developer, it might take up to 5 seconds to decide whether to display one of
        the expected text or to navigate to MLT results page.
        */
        Thread.sleep(8000);

        resultUrl = WebDriverManager.getCurrentUrl();
        //if currentUrl and resultUrl are same then navigation has not happened.
        if(currentWebUrl.contentEquals(resultUrl)){
            String mltLinkClickTxt;

            if(!mltViaSearch){
                mltLinkClickTxt = Elements.getText( Elements.paramElement("search_result.mlt_link_op1", String.valueOf(index)));
            }
            else{
                mltLinkClickTxt = Elements.getText( Elements.paramElement("search_result.mlt_link_op2", String.valueOf(index)));
            }
            if(mltLinkClickTxt.length() == 0){
                //wait for couple of seconds and give another try..
                Thread.sleep(3000);
                if(!mltViaSearch){
                    mltLinkClickTxt = Elements.getText( Elements.paramElement("search_result.mlt_link_op1", String.valueOf(index)));
                }
                else{
                    mltLinkClickTxt = Elements.getText( Elements.paramElement("search_result.mlt_link_op2", String.valueOf(index)));
                }
            }
            switch (mltLinkClickTxt){
                case moreLikeThisOptionTxt1:
                    break;
                case moreLikeThisOptionTxt2:
                    break;
                case moreLikeThisOptionTxt3:
                    break;
                case moreLikeThisOptionTxt4:
                    break;
                default:
                    Assert.fail("When clicked on More Like This link, some unexpected text is displayed which is: " + mltLinkClickTxt);
                    break;
            }//end of switch
        }
        else{
            if(Wait.secondsUntilElementPresent(Elements.paramElement("search_result.mlt_product_name", String.valueOf(index)), 20)){
                //navigation has happened.
                validateProductInfo();
                matchingProductsMLTLinkFound = true;
            }
        }
        return matchingProductsMLTLinkFound;
    }

    @Then("I look out for a More Like This link that has matching products upto \"([^\"]*)\" level and validate$")
    public void i_look_out_for_a_More_Like_This_link_that_has_matching_products_upto_level(String level) throws Throwable{
        WebDriver driver = WebDriverManager.getWebDriver();
        indexValuesArray = new int[(Integer.parseInt(level))];
        for(int i=0;i<(Integer.parseInt(level));i++){
            currentLevelOfMltValidation = i;
            clickOnMoreLikeThisLink(true);
            if(invalidFobCategoryCombination)
                break;
            WebElement e1 = driver.findElement(By.id("m-search-results"));
            assumedProductListedOnPage = e1.findElements(By.xpath("//ul[@id='m-search-results-list']/li")).size();
        }
    }

    /*
    This method fetches all product related information such as - image id, product id & product name.
    After user clicks on <More Like This> link and navigation happen to similar products page, above info will be used to
    validate if same product is displayed in header whose <More Like This> link user clicked.
     */
    private void fetchProductDetails(int index) throws Throwable{
        By selector;
        String imageId="", productId="", productName="";

        if(!mltViaSearch){
            if(Wait.secondsUntilElementPresent(Elements.paramElement("search_result.product_imgId_op1", String.valueOf(index)), 2)){
                String imgPath = Elements.getElementAttribute(Elements.paramElement("search_result.product_imgId_op1", String.valueOf(index)), "src");
                int startIndex = imgPath.lastIndexOf("/");
                int endIndex = imgPath.indexOf("_",startIndex);
                imageId = imgPath.substring(startIndex+1, endIndex);

                //fetch product id.
                selector = Elements.paramElement("search_result.product_id_op1", String.valueOf(index));
                productId = Elements.getElementAttribute(selector, "data-product_id");

                //fetch product name.
                productName = Elements.getText( Elements.paramElement("search_result.product_name_op1", String.valueOf(index)));
            }else
                mltViaSearch = true;
        }
        if(mltViaSearch){
            if(Wait.secondsUntilElementPresent(Elements.paramElement("search_result.product_imgId_op2", String.valueOf(index)), 2)){
                mltViaSearch = true;
                selector = Elements.paramElement("search_result.product_imgId_op2", String.valueOf(index));
                String imgPath = Elements.getElementAttribute(selector, "src");
                int startIndex = imgPath.lastIndexOf("/");
                int endIndex = imgPath.indexOf("_",startIndex);
                imageId = imgPath.substring(startIndex+1, endIndex);

                //fetch product id.
                selector = Elements.paramElement("search_result.product_id_op2", String.valueOf(index));
                productId = Elements.getElementAttribute(selector, "data-product-id");

                //fetch product name.
                productName = Elements.getText( Elements.paramElement("search_result.product_name_op2", String.valueOf(index)));
            }
        }
        productInfo.put("ImgId", imageId);
        productInfo.put("ProductId", productId);
        productInfo.put("ProductName", productName);
    }

    /*
    This method will validate 2 things:
        1.Original product information of parent product whose <More Like This> link was clicked:
            - Image Id
            - Product Id: This information will be validated against the current web url.
            - Product Name

        2. Count of matched products. It should show minimum 1 product. Otherwise <More Like This> link should
        not be shown for parent product in the first place.
     */
    private void validateProductInfo() throws Throwable{
        Wait.forPageReady();
        WebDriver driver = WebDriverManager.getWebDriver();
        String resultUrl = WebDriverManager.getCurrentUrl();

        String imgPath = Elements.getElementAttribute(Elements.element("search_result.mlt_parent_img_id"),"src");
        int startIndex = imgPath.lastIndexOf("/");
        int endIndex = imgPath.indexOf("_",startIndex);
        String imageId = imgPath.substring(startIndex+1, endIndex);

        Assert.assertTrue("Image mismatch. Expected Image Id: " + productInfo.get("ImgId") + ". Image Id found:  " + imageId,(productInfo.get("ImgId").equals(imageId)));

        //validate product id:
        Assert.assertTrue("Product ID mismatch - This is a different product",resultUrl.contains(productInfo.get("ProductId")));

        //validate product heading:
        String heading = Elements.getText(Elements.element("search_result.mlt_product_heading"));
        Assert.assertTrue("Item header heading mismatch",heading.equals("Items more like:"));

        //validate product name:
        String productName = Elements.getText(Elements.element("search_result.mlt_product_name"));
        //temporary comment as automation is failing at this point.....Defect logged already.
        Assert.assertTrue("Product name mismatch. Expected: " + productInfo.get("ProductName") +"\n Actual: "+ productName,productName.equals(productInfo.get("ProductName")));

        //validate count of products matched
        WebElement e1;
        try{
            e1 = driver.findElement(By.xpath("//section[@id='m-browse-results']"));
            int productCountOnPage = e1.findElements(By.xpath("//ul[@id='m-search-results-list']/li")).size();
            Assert.assertTrue("Product count should at least be 1.", productCountOnPage>=minimumExpectedProductCount);

        }catch(Exception e){
            e1 = driver.findElement(By.xpath("//section[@id='m-search-results']"));
            int productCountOnPage = e1.findElements(By.xpath("//ul[@id='m-search-results-list']/li")).size();
            Assert.assertTrue("Product count should at least be 1.", productCountOnPage>=minimumExpectedProductCount);
        }
    }

    /*
    This method is like the main engine that drive MLT automation. In addition to doing work itself it also helper
    methods to get the work done. For ex:
    validateExistingFunctionality():    to check existing functionality on pages touched by MLT.
    fetchProductDetails():  gathers all imp information related to a product (img id, product id, product name).
    isMoreLikeThisLinkVisible():    responsible for clicking on More Like This link for a product.
    evaluateMoreLikeThisLinkClickResult():  validates whether click on More Like This link is working as expected or not.

    This method takes 1 argument: findMatchingProductsMLTLink
    If findMatchingProductsMLTLink is set to true, MLT validation will be done for multiple levels else only 1 level.
     */
    private void clickOnMoreLikeThisLink(boolean findMatchingProductsMLTLink) throws Throwable{
        WebDriver driver = WebDriverManager.getWebDriver();
        WebElement el = null;
        String identifier = "search_result.total_item_count";
        int pageCounter = 0;

        if(currentLevelOfMltValidation>0 && findMatchingProductsMLTLink)
            identifier = "search_result.mlt_product_name";

        if(Wait.secondsUntilElementPresent(identifier, 30)){
            index = 1;
            if(checkExistingFuncOnMltPage){
                /*
                First i need to verify if the following existing functionality are working or not
                1. Add to Bag,
                2. Navigation to pdp page
                3. Add to list by clicking on heart button.
                */
                validateExistingFunctionality();
                findMatchingProductsMLTLink = true;
            }
            currentWebUrl = WebDriverManager.getCurrentUrl();

            /*
            In case this automation scenario: Validation of <More Like This> on deep/far result pages,
            i do not want to assign 0 to currentPageNo rather currentPageNo should be assigned the far
            page number navigated. Thus below condition.
             */
            if(!farResultPage)
                currentPageNo = 0;

            for(; index<=assumedProductListedOnPage; index++ ){
                try{
                /*
                    Before clicking on More Like This link, i should fetch following data:
                    1. image id.
                    2. product id.
                    3. product name.
                 */
                    fetchProductDetails(index);
                    isMoreLikeThisLinkVisible(index);

                    Wait.forPageReady();
                    if(findMatchingProductsMLTLink){
                        //At this point, <More Like This> is clicked but we do not know if it has navigated to
                        //results page or shown one of the four expected messages in case there are no matching
                        //products. Let's find out
                        if(evaluateMoreLikeThisLinkClickResult(findMatchingProductsMLTLink)){
                            //if matching MLT link is found then our first level of validation is complete.
                            if(checkExistingFuncOnMltPage){
                                onSearchOrBrowseResultsPage = false;
                                validateExistingFunctionality();
                                break;
                            }
                            indexValuesArray[currentLevelOfMltValidation] = index;
                            break;
                        }
                    }
                    else
                        break;
                }catch(NoSuchElementException ex){
                    System.out.println("<More Like This> link is not available for product at (" + index + ") index");
                }
                JavascriptExecutor jse = (JavascriptExecutor)driver;
                jse.executeScript("window.scrollBy(0,110)", "");

                if(index == assumedProductListedOnPage){
                    logger.info("I am at index: " + index +". No MLT link was found on this page.");
                        //I have reached here during the execution of this scenario:
                        //Validate <More Like This> link functionality upto 3 level
                        if(currentLevelOfMltValidation == 0){
                            //I am at 1st level. I must go to next page.
                            currentPageNo++;
                            el = Elements.findElement("search_result.body");
                            if(el != null){
                                el.sendKeys(Keys.END);
                                Thread.sleep(1000);
                                el.sendKeys(Keys.PAGE_UP);
                            }
                            if(Wait.secondsUntilElementPresent("search_result.page_dropdown", 5)){
                                el = Elements.findElement("search_result.page_dropdown");
                                if(el != null){
                                    int totalPages = (DropDowns.getAllValues("search_result.page_dropdown").size());
                                    if(totalPages > 1){
                                        /*
                                        currentPageNo <= 3:         Max, i will try to find MLT link on 5 pages. 4 because currentPageNo starts at 0.
                                        currentPageNo < totalPages: In cases where totalPages is < 5. For ex - if totalPages is 4, i will search for
                                        MLT link on pages 0,1,2,3
                                         */
                                          if(currentPageNo < totalPages && pageCounter < 2){
                                            DropDowns.selectByIndex("search_result.page_dropdown", currentPageNo);
                                            index =0; //set index back to zero.
                                            pageCounter++;
                                        }
                                        else{
                                            if(pageCounter == 2){
                                                logger.info("I did not find MLT link on consecutive " + (pageCounter+1) + " pages. Thus aborting this scenario.");
                                                invalidFobCategoryCombination = true;
                                                break;
                                            }
                                            else{
                                                logger.info("I did not find MLT link and there are no more product result pages. Thus aborting this scenario.");
                                                invalidFobCategoryCombination = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            else{
                                /*
                                When control comes here, it means that on current browse / search results page, we did not
                                find MLT link for any product and there are no more pages to move to. So we have to fail the
                                scenario with proper msg.
                                 */
                                Assert.fail("There is no product on this page which has MLT link. Also there are no more pages to navigate to. Thus aborting this scenario");
                            }
                        }
                        else{
                            //I am not at 1st level. so i should go 1 level back and try next product.
                            currentLevelOfMltValidation--;
                            Navigate.browserBack();
                            index = indexValuesArray[currentLevelOfMltValidation];
                        }
                }
            }//end of for loop.
        }else{
                //sometimes Browse / Search results are not displayed and we get this error: Hmm...looks like we're having some technical issues or
                //Sorry, this page is no longer available.
                String errMsg1 = Elements.getText("search_result.err_msg1");
                String errMsg2 = Elements.getText("search_result.err_msg2");
                if(errMsg1.equalsIgnoreCase(browseOrSearchFailureMsg1)) {
                    Assert.fail("Automation can not proceed due to environment issue. Browse / Search results page showing this msg: " + browseOrSearchFailureMsg1);
                }
                else if(errMsg2.equalsIgnoreCase(browseOrSearchFailureMsg2)){
                    Assert.fail("Automation can not proceed due to environment issue. Browse / Search results page showing this msg: " + browseOrSearchFailureMsg2);
            }
                else{
                    logger.info("This FOB category combination is not valid for MLT project. Aborting this scenario.");
                    invalidFobCategoryCombination = true;
                }
        }
    }

    /*
    This method validates whether existing functionality works fine or not on pages touched by MLT project
    primarily browse/search results page and similar products page. Following items are validated:
    1. Navigation to pdp page by clicking on product img.
    2. Add to Bag overlay by clicking on Add to Bag link.
    3. Add to List (heart) functionality.
    4. Price(for ex- if INR is shown with price in case country selected is India) in case of iship mode
     */
    private void validateExistingFunctionality() throws Throwable{
        int tempIndex = 1;
        int totalProducts;
        int counter = 0;
        //I am on browse / search results page.
        WebDriver driver = WebDriverManager.getWebDriver();
        try{
            Clicks.click(Elements.findElement("search_result.product_img_op1"));
        }catch(NoSuchElementException ex) {
            Clicks.clickWhenPresent("search_result.product_img_op2");
        }
        //when control comes here, navigation to pdp page has started.
        try{
            Thread.sleep(3000);
            new PageNavigation().I_should_be_redirected_to_PDP_page();
        }catch(Exception e){
            //If control comes here, navigation to PDP has not happened.
            //Fail test here.
            Assert.fail("After clicking on the image at index 1 on browse results page, navigation to" +
                    " pdp page has not happened.");
        }
        Navigate.browserBack();
        /*
        At this point pdp validation is done. Let's validate Add to Bag overlay.
         */
        try{
            Clicks.click(Elements.paramElement("search_result.add_to_bag_op1", String.valueOf(tempIndex)));
        }catch(NoSuchElementException ex) {
            try{
                /*
                For certain products which is a combination of more than one product, <Add to Bag> is not shown
                on browse / search results page. For all such products, the control will enter the below catch
                block:      catch(NoSuchElementException ex1)
                 */
                Clicks.click(Elements.paramElement("search_result.add_to_bag_op2", String.valueOf(tempIndex)));
            }catch(NoSuchElementException ex1){
                if(onSearchOrBrowseResultsPage){
                    //We know that we tried to click on Add to Bag link for product at index 1 but failed.
                    //so we will search for Add to Bag link on index 2 onwards.
                    tempIndex++;
                    totalProducts = assumedProductListedOnPage;
                }
                else{
                    WebElement e1 = driver.findElement(By.id("m-search-results"));
                    totalProducts = e1.findElements(By.xpath("//ul[@id='m-search-results-list']/li")).size();
                }
                for(;tempIndex <= totalProducts; tempIndex++){
                    counter++;
                    try{
                        Clicks.click(Elements.paramElement("search_result.add_to_bag_op2", String.valueOf(tempIndex)));
                        break;
                    }catch(NoSuchElementException ex2){
                        JavascriptExecutor jse = (JavascriptExecutor)driver;
                        jse.executeScript("window.scrollBy(0,80)", "");
                    }
                }//end of for
                if(tempIndex == totalProducts){
                    if(onSearchOrBrowseResultsPage)
                        logger.info("All products on Browse results page are navigated. Add to Bag link is not available" +
                                " for any product.");
                    else
                        logger.info("All products on similar products page are navigated. Add to Bag link is not available" +
                                " for any product.");
                }
            }
        }
        if(Wait.secondsUntilElementPresent("search_result.quick_bag_overlay", 5)){
            Thread.sleep(1000);
            //close quick bag overlay.
            Clicks.click(Elements.findElement("search_result.quick_bag_overlay_close_btn"));

            //After closing overlay, go to top of the page.
            WebElement el = Elements.findElement("search_result.body");
            if(el != null) {
                el.sendKeys(Keys.HOME);
                Thread.sleep(500);
            }
        }
        else{
            Assert.fail("After clicking on Add to Bag at index " + tempIndex + " on browse results page, quick bag overlay" +
                    " is not seen.");
        }
        /*
        *****For Discovery Regression Team*****
        At this point quick bag validation is done. Let's validate Add to List (heart)btn functionality.
        Please note that Add to List (heart) functionality is missing from similar products page. You can also refer to
        D-61892 in V1. It will be implemented post holidays by dev team. Once that is done and we have Add to List (heart)
        functionality on similar products page, just remove the below if condition and automation will start to validate
        Add to List on similar products page as well.
         */
        if(onSearchOrBrowseResultsPage){
            try{
                Clicks.click(Elements.findElement("search_result.add_to_list_btn_empty"));
                if(!Wait.secondsUntilElementPresent("search_result.add_to_list_btn_filled", 10)) {
                    Assert.fail("Add to List heart btn at index 1 on browse results page is not working.");
                }
            }catch(Exception e){
                Assert.fail("Add to List heart btn is not available on browse results page.");
            }
        }

        if(iShipModeIsActive){
            /*
            The only extra item that i am validating in iship mode is price. The price should be displayed in
            the currency of the selected country.
             */
            String price = Elements.getText("search_result.product_price_op1");
            if(price == null)
                price = Elements.getText("search_result.product_price_op2");
            if(price != null){
                Assert.assertTrue("INR is not seen for product prices in iship mode(India selected)",price.contains("INR"));
            }
        }
    }

    /*
    This method only does the job of clicking on <More Like This> link.
     */
    private void isMoreLikeThisLinkVisible(int index){
        try{
            Clicks.click(Elements.paramElement("search_result.mlt_link_op1", String.valueOf(index)));
        }catch(NoSuchElementException ex) {
            Clicks.click(Elements.paramElement("search_result.mlt_link_op2", String.valueOf(index)));
        }
    }

    @Then("I should be able to validate existing functionality on MLT pages$")
    public void existing_functionality_should_work_fine_on_similar_products_page() throws Throwable {
        checkExistingFuncOnMltPage = true;
        clickOnMoreLikeThisLink(true);
    }

    @Then("I should be able to validate existing functionality on MLT pages in iship mode$")
    public void i_should_be_able_to_validate_existing_functionality_on_MLT_pages_in_iship_mode() throws Throwable {
        /*
        FOR REGRESSION TEAM: Please note that MLT functionality is currently disabled in iship mode.
        This implementation is to be done post holidays. That's the reason why iShipModeIsActive is set to false
        below. Once it is implemented set it to true and iship validation for MLT will happen.
         */
        iShipModeIsActive = false;
        checkExistingFuncOnMltPage = true;
        clickOnMoreLikeThisLink(true);
    }
}
