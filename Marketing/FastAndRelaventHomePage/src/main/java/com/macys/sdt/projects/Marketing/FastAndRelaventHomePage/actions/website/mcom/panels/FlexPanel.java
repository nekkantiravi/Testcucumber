package com.macys.sdt.projects.Marketing.FastAndRelaventHomePage.actions.website.mcom.panels;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.actions.website.mcom.panels.shop_and_browse.FlexTemplatePanel;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

public class FlexPanel extends StepUtils{

    public List<Map<String, Object>> getAllRowMediaDetails(){
        List<Map<String, Object>> rowMediaType = new ArrayList<>();
        List<WebElement> rowElements = new FlexTemplatePanel().rowElements();
        rowElements.forEach(ele -> {
            Map<String, Object> media = new HashMap<>();
            String rowId = ele.getAttribute("class").split(" ")[0].replace("rowType", "");
            media.put("rowId", rowId);
            media.put("sequence", ele.getAttribute("id").split("_")[1]);
            if (Integer.parseInt(rowId.split("")[rowId.split("").length - 1]) > 1) {
                List<String> mediaTypes = new ArrayList<>();
                List<Map<String, Object>> data = new ArrayList<>();
                for (int index = 0; index < Integer.parseInt(rowId.split("")[rowId.split("").length - 1]); index++) {
                    String type = getZoneMediaType(ele.findElements(By.cssSelector("li>div")).get(index));
                    mediaTypes.add(type);
                    data.add(getMediaValues(ele.findElements(By.cssSelector("li>div")).get(index), type));
                }
                media.put("mediaType", mediaTypes);
                media.put("mediaData", data);
            } else {
                String type = new FlexTemplatePanel().getMediaTypeByRow(ele, true);
                if (type == null)
                    media.put("mediaType", "horizontalRule");
                else {
                    media.put("mediaType", type);
                    media.put("mediaData", getMediaValues(ele, type));
                }
            }
            rowMediaType.add(media);
        });
        return rowMediaType;
    }

    public Map<String, Object> getMediaValues(WebElement element, String type){
        Map<String, Object> data = new HashMap<>();
        Assert.assertFalse("ERROR - APP: No data present in row: "+element.getAttribute("id"), type == null);
        switch (type.toLowerCase()){
            case "ad":
                data.put("data", getAdMediaData(element));
                break;
            case "image_map":
                data.put("data", getImageMapData(element));
                break;
            case "video":
                data.put("data", getVideoData(element));
                break;
            case "flexible_pool":
                data.put("data", getFlexPoolData(element));
                break;
            case "slideshow":
                data.put("data", getSlideShowData(element));
                break;
            case "category_icon":
                data.put("data", getCategoryIconData(element));
                break;
            case "product_pool":
                data.put("data", getProductPoolData(element));
                break;
            case "banner_machine":
                data.put("data", getBannerMachineData(element));
                break;
            case "flexible_links":
                data.put("data", getFlexLinksData(element));
                break;
            case "text":
                data.put("data", getTextMediaData(element));
                break;
            default:
                Assert.fail("incorrect media type!!");
        }
        return data;
    }

    private Map<String, Object> getFlexLinksData(WebElement element){
        Map<String, Object> data = new HashMap<>();
        data.put("header", element.findElement(By.className("bgvTextHeader")).getText());
        data.put("linkHref", element.findElements(By.cssSelector(".flexLinks>ul>li>a")).stream().map(e -> e.getAttribute("href")).collect(Collectors.toList()));
        data.put("linkTexts", element.findElements(By.cssSelector(".flexLinks>ul>li>a")).stream().map(e -> e.getText()).collect(Collectors.toList()));
        return data;
    }

    private Map<String, Object> getTextMediaData(WebElement element){
        Map<String, Object> data = new HashMap<>();
        data.put("text", element.findElement(By.className("textComponent")).getText());
        return data;
    }

    private String getZoneMediaType(WebElement element){
        return new FlexTemplatePanel().getMediaTypeByRow(element, true);
    }

    private Map<String, String> getAdMediaData(WebElement element){
        Map<String, String> image = new HashMap<>();
        String name = element.findElement(By.tagName("img")).getAttribute("src");
        image.put("name", name.split("/")[name.split("/").length-1]);
        image.put("link", element.findElement(By.tagName("img")).findElement(By.xpath("..")).getAttribute("href"));
        return image;
    }

    private Map<String, Object> getImageMapData(WebElement element){
        Map<String, Object> imageMapDetails = new HashMap<>();
        if (element.findElements(By.tagName("area")).size() > 0 && element.findElement(By.tagName("area")).getAttribute("data-video-component-title") != null){
            imageMapDetails.put("videoData", getVideoData(element));
        } else {
            String name = element.findElement(By.tagName("img")).getAttribute("src");
            imageMapDetails.put("name", name.split("/")[name.split("/").length-1]);
            imageMapDetails.put("links", element.findElements(By.tagName("area"))
                    .stream().map(e -> e.getAttribute("href")).collect(Collectors.toList()));
        }
        return imageMapDetails;
    }

    private Map<String, String> getVideoData(WebElement element){
        Map<String, String> videoDetails = new HashMap<>();
        String name = element.findElement(By.tagName("img")).getAttribute("src");
        videoDetails.put("name", name.split("/")[name.split("/").length-1]);
        videoDetails.put("title", element.findElement(By.tagName("area")).getAttribute("data-video-component-title"));
        return videoDetails;
    }

    private Map<String, Object> getSlideShowData(WebElement element){
        Map<String, Object> slideshow = new HashMap<>();
        WebElement slideshowElement = element.findElement(By.className("slideShow"));
        slideshow.put("count", slideshowElement.findElements(By.className("bannerSlides")).size());
        List<Map<String, Object>> slides = new ArrayList<>();
        slideshowElement.findElements(By.className("bannerSlides")).forEach(ele -> {
            Map<String, Object> image = new HashMap<>();
            String type = new FlexTemplatePanel().getMediaTypeByRow(ele, true);
            image.put("type", type);
            image.put("data", ((type.equals("image_map")) ? getImageMapData(ele) : getAdMediaData(ele)));
            slides.add(image);
        });
        slideshow.put("slides", slides);
        return slideshow;
    }

    private Map<String, Object> getFlexPoolData(WebElement element){
        Map<String, Object> flexData = new HashMap<>();
        WebElement flexElement = element.findElement(By.className("flexPool"));
        flexData.put("header", flexElement.findElement(By.className("flexHeader")).getText());
//        WebElement shopAllElement = flexElement.findElement(By.cssSelector(".flexHeader .shopAll>a"));
//        flexData.put("shopAllExists", shopAllElement.isDisplayed());
//        flexData.put("shopAllLink", shopAllElement.getAttribute("href"));
        List<Map<String, String>> pools = new ArrayList<>();
        flexElement.findElements(By.className("poolItem")).forEach(ele -> {
            Map<String, String> pool = new HashMap<>();
            pool.put("name", ele.findElement(By.tagName("img")).getAttribute("src"));
            pool.put("link", ele.findElement(By.tagName("a")).getAttribute("href"));
            pool.put("text", ele.findElement(By.className("itemName")).getText());
            pools.add(pool);
        });
        flexData.put("data", pools);
        return flexData;
    }

    private Map<String, Object> getCategoryIconData(WebElement element){
        Map<String, Object> catData = new HashMap<>();
        WebElement catElement = element.findElement(By.className("adCatIcon"));
        String name = catElement.findElement(By.tagName("img")).getAttribute("src");
        catData.put("name", name.split("/")[name.split("/").length-1]);
        catData.put("link", catElement.findElement(By.tagName("a")).getAttribute("href"));
        catData.put("text", catElement.findElement(By.className("adFlexText")).getText());
        return catData;
    }

    private Map<String, Object> getProductPoolData(WebElement element){
        Map<String, Object> productPool = new HashMap<>();
        WebElement carouselElement = element.findElement(By.className("componentCarousel"));
        productPool.put("header", element.findElement(By.className("adFlexText")).getText());
        WebElement shopAllElement = element.findElement(By.cssSelector(".poolHeader .shopAll>a"));
        productPool.put("shopAllExists", shopAllElement.isDisplayed());
        productPool.put("shopAllLink", shopAllElement.getAttribute("href"));
        productPool.put("productIds",
                carouselElement.findElements(By.className("productThumbnail"))
                        .stream().map(e -> e.getAttribute("id")).collect(Collectors.toList()));
        return productPool;
    }

    private Map<String, Object> getBannerMachineData(WebElement element){
        Map<String, Object> data = new HashMap<>();
        if (macys()){
            data.put("urgentPreHeadline", element.findElement(By.cssSelector(".urgent-preheadline span")).getText());
            data.put("urgentHeadline", element.findElement(By.cssSelector(".urgent-headline span")).getText());
            data.put("urgentDetail", element.findElement(By.cssSelector(".urgent-detail span")).getText());
            String name = element.findElement(By.className("bm-main-wrapper")).findElement(By.tagName("img")).getAttribute("src");
            data.put("imageName", name.split("/")[name.split("/").length-1]);
            data.put("preHeadline", element.findElement(By.cssSelector(".preheadline span")).getText());
            data.put("headline", element.findElement(By.cssSelector(".headline span")).getText());
            data.put("promoText", element.findElement(By.cssSelector(".promos .promoText")).getText());
            data.put("promoCode", element.findElement(By.cssSelector(".promos .promoCode")).getText());
            data.put("subHeadline", element.findElement(By.cssSelector(".subheadline")).getText());
            data.put("detailText", element.findElement(By.cssSelector(".detailText")).getText());
            data.put("detailTextLink", element.findElement(By.cssSelector(".detailText>a")).getAttribute("href"));
        } else {
            String banner = element.findElement(By.cssSelector(".bannerImage>img")).getAttribute("src");
            data.put("image", banner.split("/")[banner.split("/").length-1]);
            data.put("text", element.findElement(By.cssSelector(".bannerText>span")).getText());
            data.put("link", element.findElement(By.cssSelector(".bannerLinks>a")).getAttribute("href"));
            data.put("linkText", element.findElement(By.cssSelector(".bannerLinks>a")).getText());
        }
        return data;
    }
}
