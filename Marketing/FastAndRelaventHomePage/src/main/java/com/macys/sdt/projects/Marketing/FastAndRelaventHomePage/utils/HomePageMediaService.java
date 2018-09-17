package com.macys.sdt.projects.Marketing.FastAndRelaventHomePage.utils;

import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageMediaService {

    public List<Map<String, Object>> getAllRowMediaData() {
        String url = RunConfig.url + "/xapi/homepage/v1/canvas";
        JSONObject jsonObject = new JSONObject(RESTOperations.doGET(url).readEntity(String.class));
        List<Map<String, Object>> data = new ArrayList<>();
        for (int index = 0; index < jsonObject.getJSONArray("rows").length(); index++) {
            JSONObject row = jsonObject.getJSONArray("rows").getJSONObject(index);
            Map<String, Object> rowData = new HashMap<>();
            String dummy = row.keys().next();
            String rowTypeId = String.valueOf(row.getJSONObject(dummy).get("rowTypeId"));
            rowData.put("rowType", rowTypeId);
            JSONArray zones = row.getJSONObject(dummy).getJSONArray("zones");
            List<Map> zoneData = new ArrayList<>();
            for (int z = 0; z < zones.length(); z++) {
                JSONObject zoneJSON = zones.getJSONObject(z);
                Map<String, Object> zone = new HashMap<>();
                zone.put("type", zoneJSON.getString("type"));
                zone.put("data", getZoneData(zoneJSON.getString("type"), zoneJSON));
                zoneData.add(zone);
            }
            rowData.put("zones", zoneData);
            data.add(rowData);
        }
        return data;
    }

    public List<Map<String, Object>> getAllRowMediaDataForSignedInUser() {
        String url = MainRunner.browsermobServer.getHar().getLog().getEntries().stream().filter(e -> e.getRequest().getUrl().contains("/xapi/homepage/v1/preferences/")).findFirst().get().getRequest().getUrl();
        JSONObject jsonObject = new JSONObject(RESTOperations.doGET(url).readEntity(String.class));
        if (!jsonObject.has("preferredMedia"))
            return getAllRowMediaData();
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> rowData = new HashMap<>();
        JSONArray preferredMedia = jsonObject.getJSONArray("preferredMedia");
        List<Map> zoneData = new ArrayList<>();
        for (int z = 0; z < preferredMedia.length(); z++) {
            JSONObject zoneJSON = preferredMedia.getJSONObject(z);
            Map<String, Object> zone = new HashMap<>();
            zone.put("type", zoneJSON.getString("type"));
            zone.put("data", getZoneData(zoneJSON.getString("type"), zoneJSON));
            zoneData.add(zone);
        }
        rowData.put("zones", zoneData);
        data.add(rowData);
        return data;
    }

    private Map<String, Object> getZoneData(String type, JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();
        switch (type) {
            case "slideshow":
                data = getSlideShowData(jsonObject.getJSONObject(type));
                break;
            case "imageMap":
                data = getImageMapData(jsonObject.getJSONObject(type));
                break;
            case "banner":
                data = getAdMediaData(jsonObject.getJSONObject(type));
                break;
            case "categoryIcon":
                data = getCategoryIconData(jsonObject.getJSONObject(type));
                break;
            case "bannerMachine":
            case "imageMachine":
                data = getBannerMachineData(jsonObject.getJSONObject(type));
                break;
            case "flexPool":
                data = getFlexPoolData(jsonObject.getJSONObject(type));
                break;
            case "flexLinks":
                data = getFlexLinks(jsonObject.getJSONObject(type));
                break;
            case "textHeader":
                data = getTextHeader(jsonObject.getJSONObject(type));
                break;
            case "horizontalRule":
                data.put("type", "horizontalRule");
                break;
            default:
                Assert.fail("incorrect media type!!"+type);
        }
        return data;
    }

    private Map<String, Object> getTextHeader(JSONObject jsonObject){
        Map<String, Object> data = new HashMap<>();
        data.put("text", jsonObject.getString("text"));
        return data;
    }

    private Map<String, Object> getBannerMachineData(JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();
        JSONObject jsonObject1 = jsonObject.getJSONArray("sections").getJSONObject(0);
        if (StepUtils.macys()) {
            data.put("urgentPreHeadline", jsonObject1.getJSONObject("urgency").getJSONObject("preHeadLine").getString("text"));
            data.put("urgentHeadline", jsonObject1.getJSONObject("urgency").getJSONObject("headLine").getString("text"));
            data.put("urgentDetail", jsonObject1.getJSONObject("urgency").getJSONObject("detail").getString("text"));
            data.put("preHeadline", jsonObject1.getJSONObject("main").getJSONObject("preHeadLine").getString("text"));
            data.put("headline", jsonObject1.getJSONObject("main").getJSONObject("headLine").getString("text"));
            data.put("subHeadline", jsonObject1.getJSONObject("main").getJSONObject("details").getJSONObject("subHeadline").getString("text"));
            data.put("promoText", jsonObject1.getJSONObject("main").getJSONObject("promos").getJSONObject("promoText").getString("text"));
            data.put("promoCode", jsonObject1.getJSONObject("main").getJSONObject("promos").getJSONObject("promoCode").getString("text"));
        } else {
            JSONArray jsonArray = jsonObject1.getJSONObject("linkList").getJSONArray("links");
            List<Map> links = new ArrayList<>();
            for (int index = 0; index < jsonArray.length(); index++) {
                Map<String, String> d = new HashMap<>();
                d.put("text", jsonArray.getJSONObject(index).getString("text"));
                d.put("link", jsonArray.getJSONObject(index).getString("href"));
                links.add(d);
            }
            data.put("links", links);
        }
        data.put("imageName", jsonObject1.getJSONObject("main").getJSONObject("imageLarge").getString("src"));
        return data;
    }

    private Map<String, Object> getAdMediaData(JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", jsonObject.getString("filePath"));
        data.put("url", (jsonObject.has("url") ? jsonObject.getString("url") : ""));
        return data;
    }

    private Map<String, Object> getFlexLinks(JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();
        data.put("header", jsonObject.getJSONObject("header").getString("text"));
        List<Map> links = new ArrayList<>();
        for(int index = 0; index<jsonObject.getJSONArray("links").length();index++){
            JSONObject jsonObject1 = jsonObject.getJSONArray("links").getJSONObject(index);
            Map<String, String> link = new HashMap<>();
            link.put("text", jsonObject1.getString("text"));
            link.put("url", jsonObject1.getString("url"));
            links.add(link);
        }
        data.put("links", links);
        return data;
    }

    private Map<String, Object> getFlexPoolData(JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();
        data.put("header", jsonObject.getJSONObject("header").getString("text"));
        data.put("shopAllUrl", jsonObject.getJSONObject("header").getString("shopAllUrl"));
        List<Map> pools = new ArrayList<>();
        for (int index = 0; index < jsonObject.getJSONArray("pool").length(); index++) {
            JSONObject poolJSON = jsonObject.getJSONArray("pool").getJSONObject(index);
            Map<String, String> mapData = new HashMap<>();
            mapData.put("name", poolJSON.getString("filePath"));
            mapData.put("link", poolJSON.getString("url"));
            mapData.put("text", poolJSON.getString("text"));
            pools.add(mapData);
        }
        data.put("data", pools);
        return data;
    }

    private Map<String, Object> getProductPoolData(JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", jsonObject.getString("title"));
        List<String> productIds = new ArrayList<>();
        for (int index = 0; index < jsonObject.getJSONArray("productIds").length(); index++) {
            productIds.add(jsonObject.getJSONArray("productIds").getString(index));
        }
        data.put("productIds", productIds);
        data.put("shopAllLink", jsonObject.getJSONObject("poolLink").getString("url"));
        data.put("header", jsonObject.getJSONObject("poolLink").getString("name"));
        return data;
    }

    private Map<String, Object> getCategoryIconData(JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", jsonObject.getJSONObject("image").getString("filePath"));
        data.put("link", jsonObject.getJSONObject("image").getString("url"));
        data.put("text", jsonObject.getJSONObject("adLink").getString("name"));
        return data;
    }

    private Map<String, Object> getSlideShowData(JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();
        List<Map> slides = new ArrayList<>();
        for (int index = 0; index < jsonObject.getJSONArray("slides").length(); index++) {
            JSONObject slide = jsonObject.getJSONArray("slides").getJSONObject(index);
            Map mapData = new HashMap();
            if (slide.has("imageMap")) {
                mapData.put("type", "imageMap");
                mapData.put("data", getImageMapData(slide.getJSONObject("imageMap")));
            }
            slides.add(mapData);
        }
        data.put("slides", slides);
        return data;
    }

    private Map<String, Object> getImageMapData(JSONObject jsonObject) {
        Map<String, Object> data = new HashMap<>();
        data.put("name", jsonObject.getString("filePath"));
        List<Map> childrens = new ArrayList<>();
        for (int index = 0; index < jsonObject.getJSONArray("children").length(); index++) {
            Map<String, Object> children = new HashMap<>();
            JSONObject link = jsonObject.getJSONArray("children").getJSONObject(index);
            children.put("linkType", (link.has("linkType") ? link.getString("linkType") : "IMAGE"));
            if (link.has("linkType") && link.getString("linkType").equals("MEDIA")) {
                children.put("data", getVideoData(link.getJSONObject("video")));
            } else {
                children.put("url", link.getString("url"));
            }
            childrens.add(children);
        }
        data.put("links", childrens);
        return data;
    }


    private Map<String, String> getVideoData(JSONObject jsonObject) {
        Map<String, String> videoData = new HashMap<>();
        videoData.put("title", jsonObject.getString("title"));
        videoData.put("img", jsonObject.getString("img"));
        return videoData;
    }
}
