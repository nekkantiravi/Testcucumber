package com.macys.sdt.projects.Discovery.DeploymentValidator.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by m125779 on 6/19/2017.
 */
public class ValidatorConfigParser {

    private static final Logger logger = LoggerFactory.getLogger(ValidatorConfigParser.class);

    public static HashMap<String,String> MCOMRTPCELLAServerMap = new HashMap<String,String>();
    public static HashMap<String,String> MCOMRTPCELLBServerMap = new HashMap<String,String>();
    public static HashMap<String,String> BCOMRTPCELLAServerMap = new HashMap<String,String>();
    public static HashMap<String,String> BCOMRTPCELLBServerMap = new HashMap<String,String>();
    public static HashMap<String,String> MCOMDALServerMap = new HashMap<String,String>();
    public static HashMap<String,String> BCOMDALServerMap = new HashMap<String,String>();

    public static String getIFSResponse(String ifs_host, String ifs_port) {
        String url="https://ifsrest.macys.net/health/getServerList?allInfo=true";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("Referer", "https://ifscap.macys.net/");
        HttpResponse result = null;
        String resp_json = "";



        try {
            result = httpClient.execute(request);
            resp_json = EntityUtils.toString(result.getEntity(), "UTF-8");
            // resp_json="";
            JSONObject myObject = new JSONObject(resp_json);
            ParseJSON pJS = new ParseJSON();
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap = (Map<String, Object>) pJS.convertJsonElement(myObject);


            Iterator hashIT = jsonMap.entrySet().iterator();

            while (hashIT.hasNext()) {

                Map.Entry pair = (Map.Entry) hashIT.next();


                if (pair.getKey().toString().contains("data")) {
                    Map mp = (Map) pair.getValue();

                    Iterator it = mp.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair1 = (Map.Entry) it.next();

                        if (pair1.getKey().toString().contains("server")) {
                            HashSet mpjvm = (HashSet) pair1.getValue();
                            boolean found = false;

                            Iterator it2 = mpjvm.iterator();
                            while (it2.hasNext()) {

                                Map serverMap = (Map) it2.next();


                                //System.out.println("**************** NEXT PAIR ********************************");

                                //***********************************************

                                Iterator it5 = serverMap.entrySet().iterator();
                                String active = "";
                                String dc = "";
                                String site = "";
                                String appDesc = "";
                                String serverName = "";
                                String cell = "";

                                while (it5.hasNext()) {
                                    Map.Entry p2 = (Map.Entry) it5.next();
                                    System.out.println(p2.getKey() + " = " + p2.getValue());

                                    switch (p2.getKey().toString()) {
                                        case "site":
                                            site = p2.getValue().toString();
                                            break;
                                        case "appDesc":
                                            appDesc = p2.getValue().toString();
                                            break;
                                        case "dc":
                                            dc = p2.getValue().toString();
                                            break;
                                        case "active":
                                            active = p2.getValue().toString();
                                            break;
                                        case "server":
                                            serverName = p2.getValue().toString();
                                            break;
                                        case "cell":
                                            System.out.println("CELL :");
                                            cell = p2.getValue().toString();
                                            break;

                                    }

                                }

                                if (active.contains("1")) {

                                    switch (dc) {

                                        case "RTP":
                                            if (site.contains("MCOM") && cell.contains("A"))
                                                MCOMRTPCELLAServerMap.put(serverName, appDesc);
                                            else if (site.contains("MCOM") && cell.contains("B"))
                                                MCOMRTPCELLBServerMap.put(serverName, appDesc);
                                            else if (site.contains("BCOM") && cell.contains("A"))
                                                BCOMRTPCELLAServerMap.put(serverName, appDesc);
                                            else if (site.contains("BCOM") && cell.contains("B"))
                                                BCOMRTPCELLBServerMap.put(serverName, appDesc);

                                            break;

                                        case "DAL":
                                            if (site.contains("MCOM")) {
                                                MCOMDALServerMap.put(serverName, appDesc);
                                            } else
                                                BCOMDALServerMap.put(serverName, appDesc);

                                            break;
                                    }
                                    }
                                }

                            }
                        }
                    }

                }

            } catch(IOException e){
                e.printStackTrace();

            }


        System.out.println("\n\nRTP MCOM MAP  ");
        System.out.println("\n\n******************\n ");
        for (String key : MCOMRTPCELLAServerMap.keySet()) {
            System.out.println("key: " + key + " , value: " + MCOMRTPCELLAServerMap.get(key));

        }

        System.out.println("\n\nRTP BCOM MAP  ");
        System.out.println("\n\n******************\n ");
        for (String key : BCOMRTPCELLAServerMap.keySet()) {
            System.out.println("key: " + key + " , value: " + BCOMRTPCELLAServerMap.get(key));

        }

        System.out.println("\n\nDAL MCOM MAP  ");
        System.out.println("\n\n******************\n ");
        for (String key : MCOMDALServerMap.keySet()) {
            System.out.println("key: " + key + " , value: " + MCOMDALServerMap.get(key));

        }

        System.out.println("\n\nDAL BCOM MAP  ");
        System.out.println("\n\n******************\n ");
        for (String key : BCOMDALServerMap.keySet()) {
            System.out.println("key: " + key + " , value: " + BCOMDALServerMap.get(key));

        }

        return resp_json;
    }
    public static void parseString(String str) {
        String[] nameBits = str.split("\"JVM\":");
        for (int i = 0; i < nameBits.length;i++) {
            StringBuffer sb = new StringBuffer(nameBits[i]);
//            System.out.println("SEVER NAMES :: " +sb.substring(0, sb.charAt(sb.indexOf("},"))));
            //  System.out.println("SEVER NAMES :: " +sb.substring(0, sb.indexOf("},")));
        }
    }

    public static void main(String args[]){
        String resp=  ValidatorConfigParser.getIFSResponse("","");
        ValidatorConfigParser.parseString(resp);
    }


}
