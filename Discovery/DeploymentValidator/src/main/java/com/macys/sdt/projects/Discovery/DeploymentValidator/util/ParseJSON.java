package com.macys.sdt.projects.Discovery.DeploymentValidator.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
 * Created by m125779 on 6/19/2017.
 */
public class ParseJSON {

    static String FirstRespone ="";
    static Boolean IsFirstResponse =false;
    static Boolean Match = true;
    static HashMap<String,String> FCCInstancesMap = new HashMap<String,String>();
    static JSONObject FirstAPIRespone =null;
    static Boolean IsFirstAPIResponse =false;
    static Boolean APIRespMatch = true;
    static HashMap<String,JSONObject> FCCAPIResponsesMap = new HashMap<String,JSONObject>();

    public static void getFCCVersion(List<String> serverList) {

        for (String  fccserver : serverList) {
            System.out.println("fccserver : " + fccserver);


            String api = "/admin-service/getArtifactInfo";
            String resp = "";
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("http://" + fccserver + ":8080" + api);
            HttpResponse result = null;

            try {
                result = httpClient.execute(request);
                resp = EntityUtils.toString(result.getEntity(), "UTF-8");
                StringBuffer sb = new StringBuffer();
                String[] fccVersion = resp.split("catalog.war</td>");
                String version = fccVersion[1].substring(fccVersion[1].indexOf("<td>") + 4, fccVersion[1].indexOf("</td>"));

                System.out.println("VERSION IS::: " + version);
                matchServerResponses(fccserver, version);

            } catch (IOException E) {
                E.printStackTrace();
            }
        }
    }
    public static void  matchServerResponses(String host, String fccVersion){
        if (!IsFirstResponse){
            FirstRespone= fccVersion.trim();
            IsFirstResponse=true;
            FCCInstancesMap.put(host,fccVersion);
            System.out.println("FIRST RESPONSE");
        }
        else {
            if (fccVersion.trim().equals(FirstRespone)) {
                System.out.println("MATCHING RESPONSE");
            } else {
                Match = false;
                System.out.println("NOt Matching");
            }
            FCCInstancesMap.put(host, fccVersion);

        }

    }

    public static void getFCCAPIResponse(List<String> serverList){

        for (String  fccserver : serverList) {
            System.out.println("fccserver : " + fccserver);
            String api = "/api/catalog/v1/brands";
            String resp = "";
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet("http://" + fccserver + ":8080" + api);
            HttpResponse result = null;

            try {
                result = httpClient.execute(request);
                resp = EntityUtils.toString(result.getEntity(), "UTF-8");

                System.out.println("API Response is::: " + resp);
                Map<String, Object> myMap = new HashMap<String, Object>();
                JSONObject jsonObject = new JSONObject(resp);
                matchAPIResponses(fccserver, jsonObject);

            } catch (IOException E) {
                E.printStackTrace();
            }
        }
    }

    public static void  matchAPIResponses(String host, JSONObject JObj){
        if (!IsFirstAPIResponse){
            FirstAPIRespone= JObj;
            IsFirstAPIResponse=true;
            FCCAPIResponsesMap.put(host,JObj);
            System.out.println("FIRST API RESPONSE !");
        }
        else {
            if (areEqual(JObj,FirstAPIRespone)) {
                System.out.println("MATCHING API RESPONSE !");
            } else {
                Match = false;
                System.out.println("NOT  Matching API Response !");
            }
            FCCAPIResponsesMap.put(host, JObj);

        }

    }
    public static boolean areEqual(Object ob1, Object ob2) {
        Object obj1Converted = convertJsonElement(ob1);
        Object obj2Converted = convertJsonElement(ob2);
        return obj1Converted.equals(obj2Converted);
    }

    public static Object convertJsonElement(Object elem) {
             if (elem instanceof JSONObject) {
             JSONObject obj = (JSONObject) elem;
             Iterator<String> keys = obj.keys();
             Map<String, Object> jsonMap = new HashMap<>();
            while (keys.hasNext()) {
               // System.out.println("convertJsonElement while");
                String key = keys.next();
                jsonMap.put(key, convertJsonElement(obj.get(key)));
            }
            return jsonMap;
        } else if (elem instanceof JSONArray) {
            JSONArray arr = (JSONArray) elem;
            Set<Object> jsonSet = new HashSet<>();
            for (int i = 0; i < arr.length(); i++) {
                //System.out.println(" convertJsonElement(arr.get(i))::"+convertJsonElement(arr.get(i)));
                jsonSet.add(convertJsonElement(arr.get(i)));
            }
            return jsonSet;
        } else {
            return elem;
        }
    }


    public static void main(String args[]){
        List<String> serverList =new ArrayList<String>();
        serverList.add("macyds703");
        serverList.add("ma209mlvfcc050");
        getFCCVersion(serverList);
        getFCCAPIResponse(serverList);
    }
}
