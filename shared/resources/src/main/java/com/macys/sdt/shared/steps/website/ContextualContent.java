package com.macys.sdt.shared.steps.website;

import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.shared.actions.website.mcom.pages.home.Home;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.FlexTemplatePanel;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ContextualContent extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(ContextualContent.class);
    FlexTemplatePanel flexPanel = new FlexTemplatePanel();
    private String mainPageType, mainRowType, mainRowTypeId;
    private String[] mediaNamesArray;
    private boolean mediaAdsFlag;
    private List<String> seqNumbers;
    private List actualMediaType;
    private int sequence;

    @When("^I navigate to \"([^\"]*)\" category with \"([^\"]*)\" in \"([^\"]*)\" for context$")
    public void I_navigate_to_category_with_media_in_row_for_context(String pageType, String mediaNames, String rowType, List<Map<String, String>> context) throws Throwable {
        if (RunConfig.debugMode) {
            logger.error("Media: " + mediaNames);
        }
        String mode, regionCode;
        mode = context.stream().filter(con -> con.containsKey("SHOPPING_MODE")).map(con -> con.get("SHOPPING_MODE")).findFirst().get();
        regionCode = context.stream().filter(con -> con.containsKey("SHOPPING_MODE")).map(con -> con.get("REGION_CODE")).findFirst().get();
        if(mode.equalsIgnoreCase("SITE") && regionCode.equalsIgnoreCase("INTL")){
            mode = "ISHIP";
        }else if(mode.equalsIgnoreCase("SITE") && regionCode.equalsIgnoreCase("US")){
            mode = "SITE";
        }else if(mode.equalsIgnoreCase("WEDDING_REGISTRY") && regionCode.equalsIgnoreCase("US")){
            mode = "REGISTRY";
        }
        String creative = RunConfig.getEnvOrExParam("creative_env");
        String mediaDataFileName = (prodEnv() || (creative != null && creative.equalsIgnoreCase("true"))) ? "production_media_categories.json" : "media_categories.json";
        JSONObject json = new JSONObject((Utils.readTextFile(Utils.getResourceFile(mediaDataFileName))));
        JSONObject json_data = json.getJSONObject(pageType.toLowerCase().replace(" ","_"));
        JSONObject json_mode_data = json_data.getJSONObject(mode.toLowerCase());
        JSONObject json_media = json_mode_data.getJSONObject("OTHERS");
        JSONObject json_media_data = json_media.getJSONObject(mediaNames.replace(", ","|"));
        String json_row_data = (String)json_media_data.get(rowType);
        String[] category_list = json_row_data.split(" -> ");
        Home homePage = new Home();
        homePage.selectMainCategory(category_list[0]);
        shouldBeOnPage("category_splash");
        if(category_list.length >= 2) {
            homePage.selectSubCategory(category_list[1]);
            resumePageHangWatchDog();
        }
        if(category_list.length >= 3){
            homePage.selectSubCategory(category_list[2]);
            resumePageHangWatchDog();
            shouldBeOnPage("category_browse");
        }
        if(category_list.length == 4){
            homePage.selectSubCategory(category_list[3]);
            resumePageHangWatchDog();
            shouldBeOnPage("category_browse");
        }
        Wait.forPageReady();
        try {
            resumePageHangWatchDog();
            pageVerifications(pageType);
        } catch (Exception e) {
            logger.error(pageType + " page is not loaded " + category_list + " in context: " + context + " ");
        }
        logger.info("Navigated to category:" + category_list + " for media:" + mediaNames + " with row type id:" + rowType);
        mainPageType = pageType;
    }

    @Then("^I should see \"([^\"]*)\" on the page in \"([^\"]*)\" row$")
    public void I_should_see_on_the_page_in_row(String mediaNames, String rowType) throws Throwable {
        StepUtils.pausePageHangWatchDog();
        mediaNamesArray = mediaNames.split(", ");
        if (mediaNames.contains("MEDIA_ADS")) {
            for (int index = 0; index < mediaNamesArray.length; index++)
                if (mediaNamesArray[index].equals("MEDIA_ADS")) {
                    mediaNamesArray[index] = "THUMBNAIL_GRID";
                }
            mediaAdsFlag = true;
        }
        seqNumbers = flexPanel.getSequenceByRowTypes(rowType);
        boolean sequenceSet = false;
        for (Object seqNumber : seqNumbers) {
            actualMediaType = flexPanel.getRowMediaByRowTypeSeqNumber(rowType, seqNumber.toString(), true, false);
            // Below code is to skip empty rows.
            if (((Map) actualMediaType.get(0)).get("mediaType") == null) {
                continue;
            }
            if (Arrays.asList(mediaNamesArray).contains("SLIDESHOW") || Arrays.asList(mediaNamesArray).contains("WIDGET")) {
                for (Object anActualMediaType : actualMediaType) {
                    if (((Map) anActualMediaType).get("mediaType").toString().equals("banner_machine_slideshow")) {
                        ((Map) anActualMediaType).put("mediaType", "slideshow");
                    }
                    if (((Map) anActualMediaType).get("mediaType").toString().equals("banner_machine_widget")) {
                        ((Map) anActualMediaType).put("mediaType", "widget");
                    }
                }
            }
            String[] productPool = {"PRODUCT_PANEL_CATEGORY_FACET", "PRODUCT_PANEL_POOL", "PRODUCT_PANEL_CATEGORY", "PRODUCT_PANEL_NA", "PRODUCT_PANEL_BAZAAR"};
            for (int index = 0; index < mediaNamesArray.length; index++)
                if (Arrays.asList(productPool).contains(mediaNamesArray[index])) {
                    mediaNamesArray[index] = "PRODUCT_POOL";
                }
            if ((((Map) actualMediaType.get(0)).get("mediaType") != null) && ((Map) actualMediaType.get(0)).get("mediaType").toString().equalsIgnoreCase(mediaNamesArray[0])) {
                for (Object anActualMediaType : actualMediaType) {
                    Map type = (Map) anActualMediaType;
                    if ((!type.get("mediaType").toString().contains("widget")) && (Arrays.asList(mediaNamesArray).contains("WIDGET"))) {
                        sequence = -1;
                        sequenceSet = true;
                    } else {
                        sequence = Integer.parseInt(seqNumber.toString());
                        sequenceSet = true;
                    }
                }
            }
            if (sequenceSet) {
                break;
            }
        }
        mainRowType = rowType;
        mainRowTypeId = "row_" + rowType + "_" + String.valueOf(sequence);
        if (!sequenceSet) {
            Assert.fail("ERROR - DATA: Navigated category may have inherited canvas id, so we cannot find row type:'" + rowType + "' with media:'" + mediaNames + "' on the page");
        }
        logger.info("Required media is present in page!!");
        StepUtils.resumePageHangWatchDog();
    }

    @And("^I should see respective media as per astra data$")
    public void I_should_see_respective_media_as_per_astra_data() throws Throwable {
        StepUtils.pausePageHangWatchDog();
        if (sequence != -1) {
        List<Map> mediaDetails = new ArrayList<>();
//            if (mainRowType.equals("0") || (Arrays.asList(mediaNamesArray).contains("PRODUCT_POOL"))) {
//                for (String seq : seqNumbers) {
//                    List<Map> dummyType = flexPanel.getRowMediaByRowTypeSeqNumber(mainRowType, seq, true, false);
//                    if (dummyType.get(0).get("mediaType") != null && dummyType.get(0).get("mediaType").equals("product_pool"))
//                        mediaDetails.addAll(flexPanel.getRowMediaByRowTypeSeqNumber(mainRowType, seq, true, true).stream().collect(Collectors.toList()));
//                }
//            } else {
                mediaDetails = flexPanel.getRowMediaByRowTypeSeqNumber(mainRowType, String.valueOf(sequence), true, true).stream().collect(Collectors.toList());
//            }
            boolean isBannerMachineSlide = mediaDetails.stream().anyMatch(data -> (data.get("mediaType").toString().equals("banner_machine_slideshow") && ((Arrays.asList(mediaNamesArray).contains("BANNER_MACHINE_SLIDESHOW")) || (Arrays.asList(mediaNamesArray).contains("SLIDESHOW")))));
            verifyMediaNames(isBannerMachineSlide, mediaDetails);
            List<String> names = mediaDetails.stream().map(type -> type.get("mediaType").toString().toLowerCase().replace(" ", "_").toLowerCase()).collect(Collectors.toList());
            for (String name : names) {
                List<Map> uiData;
                mediaDetails.removeIf(type -> (!type.get("mediaType").toString().equals((isBannerMachineSlide ? "banner_machine_slideshow" : name))));
                uiData = mediaDetails;
                String errorMessage = "ERROR - APP: Expected media type: '" + name + "' is not displayed";
                switch (name) {
                    case "widget":
                    case "banner_machine_widget":
                        if ((uiData.get(0).get("mediaType").equals("banner_machine_widget") && ((List)((HashMap)uiData.get(0).get("mediaInfo")).get("bannerMachineWidgetData")).isEmpty()))
                            logger.error("Widget is collapsed due to time constraint");
                        else {
                            List<String> actualMedia = new ArrayList<>();
                            for (Map data : uiData) {
                                actualMedia.addAll(((List<Map>) data.get("mediaInfo")).stream().map(type -> type.get("panelType").toString()).collect(Collectors.toList()));
                                actualMedia.stream().filter(type -> type.equals("IMAGE")).map(type -> "AD");
                            }
                            Assert.assertFalse(errorMessage, actualMedia.isEmpty());
                        }
                        break;
                    case "category_icon":
                        String textErrorMessage = "ERROR - APP: Category icons text is not displayed with media type:'" + name + "'";
                        String imageErrorMessage = "ERROR - APP: Category icon images are not displayed with media type:'" + name + "'";
                        List<String> catIconText = new ArrayList<>();
                        List<String> uiImageNames = new ArrayList<>();
                        uiData.forEach(data -> {
                            catIconText.addAll(((List<Map>) data.get("mediaInfo")).stream()
                                    .filter(type -> type.get("text") != null)
                                    .map(type -> (type.get("text").toString().toLowerCase().replace(" and ", " ").replace(" & ", " ")))
                                    .collect(Collectors.toList()));
                            uiImageNames.addAll(((List<Map>) data.get("mediaInfo")).stream()
                                    .map(type -> type.get("image").toString().toLowerCase())
                                    .collect(Collectors.toList()));
                        });
                        Assert.assertFalse(textErrorMessage, catIconText.isEmpty());
                        Assert.assertFalse(imageErrorMessage, uiImageNames.isEmpty());
                        break;
                    case "slideshow":
                        if (isBannerMachineSlide) {
                            List<String> actualC2Slides = new ArrayList<>();
                            uiData.forEach(e -> ((List<Map>)((Map)e.get("mediaInfo")).get("bannerMachineSlideData")).stream()
                                    .filter(r -> r.containsKey("c2SlideData")).forEach(f -> actualC2Slides.add(((Map)f.get("c2SlideData")).get("imageName").toString())));
                            actualC2Slides.forEach(slide -> Assert.assertFalse("ERROR - APP: Slide Show is not displayed with valid media resource:'" + slide, slide == null));
                        } else {
                            List<String> uiInfo = new ArrayList<>();
                            uiData.forEach(data -> uiInfo.addAll((List) ((Map) data.get("mediaInfo")).get("slideshowImages")));
                            Assert.assertFalse(errorMessage, uiInfo.isEmpty());
                        }
                        break;
                    case "flexible_pool":
                        String titleErrorMessage = "ERROR - APP: Flexible title is not displayed with media type:'" + name + "'";
                        String headerErrorMessage = "ERROR - APP: Flexible header is not displayed with media type:'" + name + "'";
                        List<String> uiTitleList = new ArrayList<>();
                        List<String> uiHeaderList = new ArrayList<>();
                        uiData.forEach(data -> {
                            uiTitleList.addAll((((List<WebElement>) ((Map) data.get("mediaInfo")).get("flexTitle"))).stream()
                                    .map(WebElement::getText).map(e -> e.toLowerCase()).collect(Collectors.toList()));
                            uiHeaderList.add(((WebElement) (((Map) data.get("mediaInfo")).get("flexHeader"))).getText().split("\n")[0].toLowerCase());
                        });
                        Assert.assertFalse(titleErrorMessage, uiTitleList.isEmpty());
                        Assert.assertFalse(headerErrorMessage, uiHeaderList.isEmpty());
                        break;
                    case "ad":
                        List<String> uiMediaNamesList;
                        String adErrorMessage = "ERROR - APP: Expected media type:'ad' source is not displayed as per astra";
                        uiMediaNamesList = uiData.stream()
                                .map(data -> ((Map) data.get("mediaInfo")).get("imageName").toString())
                                .collect(Collectors.toList());
                            Assert.assertFalse(adErrorMessage, uiMediaNamesList.isEmpty());
                        break;
                    case "recently_reviewed":
                        Assert.assertFalse("ERROR - APP: Recently review data is not displayed", uiData.isEmpty());
                        break;
                    case "image_map":
                    case "custom_popup":
                        List<String> uiImageMapNames;
                        uiImageMapNames = uiData.stream().map(data -> ((Map) data.get("mediaInfo")).get("imageName").toString()).collect(Collectors.toList());
                        Assert.assertFalse(errorMessage, uiImageMapNames.isEmpty());
                        break;
                    case "video":
                        List uiVideoTitles;
                        uiVideoTitles = uiData.stream().map(data -> ((Map) data.get("mediaInfo")).get("videoTitle").toString()).collect(Collectors.toList());
                        Assert.assertFalse(errorMessage, uiVideoTitles.isEmpty());
                        break;
                    case "text":
                        List<String> uiTextData;
                        uiTextData = uiData.stream().map(data -> ((Map) data.get("mediaInfo")).get("text").toString().toLowerCase()).collect(Collectors.toList());
                        Assert.assertFalse(errorMessage, uiTextData.isEmpty());
                        break;
                    case "copy_block":
                        List<String> uiCopyData;
                        uiCopyData = uiData.stream().map(data -> ((Map) data.get("mediaInfo")).get("text").toString().toLowerCase()).collect(Collectors.toList());
                        Assert.assertFalse(errorMessage, uiCopyData.isEmpty());
                        break;
                    case "thumbnail_grid":
                        if (mediaAdsFlag) {
                            List<String> uiMediGridNames = new ArrayList<>();
                            boolean dataFound;
                            if (uiData.stream().anyMatch(data -> (((Map) data.get("mediaInfo")).get("thumbnailGridExists").getClass().equals(Boolean.class)))) {
                                dataFound = uiData.stream().map(data -> (Boolean) ((Map) data.get("mediaInfo")).get("thumbnailGridExists")).findFirst().get();
                            } else {
                                uiData.forEach(data -> uiMediGridNames.addAll((List<String>)((Map)data.get("mediaInfo")).get("thumbnailGridExists")));
                                dataFound = !uiMediGridNames.isEmpty();
                            }
                            Assert.assertTrue(errorMessage, dataFound);
                        } else {
                            List<String> uiMediaTypeDesc;
                            uiMediaTypeDesc = uiData.stream()
                                    .map(data -> data.get("mediaType").toString().toLowerCase())
                                    .collect(Collectors.toList());
                            Assert.assertFalse(errorMessage, uiMediaTypeDesc.isEmpty());
                        }
                        break;
                    case "horizontal_rule":
                        uiData.forEach(data -> Assert.assertTrue(errorMessage, ((boolean) ((Map) data.get("mediaInfo")).get("horizontalRuleExists"))));
                        break;
                    case "product_pool":
                        List<String> uiPoolData;
                        uiPoolData = uiData.stream().map(d -> ((Map)d.get("mediaInfo")).get("title").toString().toLowerCase()).collect(Collectors.toList());
                        Assert.assertFalse(errorMessage, uiPoolData.isEmpty());
                        break;
                    case "jsp":
                        uiData.forEach(data -> Assert.assertTrue(errorMessage, ((boolean) ((Map) data.get("mediaInfo")).get("jspExists"))));
                        break;
                    default:
                        Assert.fail("ERROR - ENV : Required media type data is not displayed in UI!!");
                }
            }
        } else {
            logger.error("Widget is collapsed");
        }
        logger.info("Required media data is present in page!!");
        StepUtils.resumePageHangWatchDog();
    }

    public void verifyMediaNames(boolean isBannerMachineSlide, List<Map> mediaDetails) throws Throwable {
        List<String> mediaNames = new ArrayList<>();
        mediaDetails.forEach(type -> {
            if (isBannerMachineSlide) {
                mediaNames.add(((type.get("mediaType").toString().equals("banner_machine_slideshow")) ? "slideshow" : type.get("mediaType").toString()));
            } else {
                mediaNames.add(((type.get("mediaType").toString().equals("banner_machine_widget")) ? "widget" : type.get("mediaType").toString()));
            }
        });
        Assert.assertFalse("Media are mismatch in DB and UI", mediaNames.isEmpty());
    }

    public Map<String, List<Map>> groupBy(List<Map> originalData, String key) throws Throwable {
        Map<String, List<Map>> hashMap = new HashMap<>();
        for (Map data : originalData) {
            String mainKey = data.get(key).toString();
            if (!hashMap.containsKey(mainKey)) {
                hashMap.put(mainKey, (new ArrayList<>()));
            }
            hashMap.get(mainKey).add(data);
        }
        return hashMap;
    }

    public void pageVerifications(String pageType) throws Throwable {
        String pageName = pageType.equals("Category Splash") ? "category_splash" : (pageType.equals("Sub Splash") ? "category_sub_splash" : "category_browse");
        Wait.secondsUntilElementPresent(pageName + ".verify_page", 30);
        shouldBeOnPage(pageName);
    }

    @And("^I verify media is clickable and navigating to respective pages$")
    public void iVerifyMediaIsClickableAndNavigatingToRespectivePages() throws Throwable {
        String selectedLinkURL = flexPanel.selectMediaLinksByRowTypeSeqNumber(mainRowType, String.valueOf(sequence));
        Thread.sleep(20000);
        String currentURL = WebDriverManager.getCurrentUrl();
        Assert.assertTrue("ERROR - APP: Media link selection is navigated to rspected url:"+selectedLinkURL, selectedLinkURL.equalsIgnoreCase(currentURL));
        logger.info("Required media is clickable in page& ablove to navigate to taget URL!!");
    }
}

