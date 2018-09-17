package com.macys.sdt.projects.Selection.List.actions.responsive.mcom;

import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.StepUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

/**
 * Created by m477277 on 10/18/17.
 */
public class AddToListService extends StepUtils {

    public static String app_name = null;

    public static String getAppName() {
        if (EnvironmentDetails.isZeus())
            app_name = "customer";
        else
            app_name = "MSPCUSTOMER";
        return app_name;
    }

    /**
     * Method to add product id to list through service
     *
     * @return list details
     */
    public static void addPidToList(String pid) throws Throwable {
        try {
            String userId = Cookies.getCookieValue(macys() ? "macys_online_uid" : "bloomingdales_online_uid");
            String url = "http://" + EnvironmentDetails.otherApp(getAppName()).ipAddress + ":8080/api/customer/v1/lists?userid=" + userId;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost postRequest = new HttpPost(url);
            JSONObject jsonObj = new JSONObject("{\"list\":{\"user\": {\"id\": " + userId + "},\"items\":[{\"product\": {\"id\": " + pid + "},\"qtyRequested\":1}]}}");
            StringEntity input = new StringEntity(jsonObj.toString());
            postRequest.addHeader("content-type", "application/json");
            postRequest.addHeader("Accept", "application/json");
            System.out.println(input);
            postRequest.setEntity(input);
            CloseableHttpResponse response = httpClient.execute(postRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            httpClient.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add upc product to list through service
     *
     * @return list details
     */
    public static void addUpcToList(String upc) throws Throwable {
        try {
            String userId = Cookies.getCookieValue(macys() ? "macys_online_uid" : "bloomingdales_online_uid");
            String url = "http://" + EnvironmentDetails.otherApp(getAppName()).ipAddress + ":8080/api/customer/v1/lists?userid=" + userId;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost postRequest = new HttpPost(url);
            JSONObject jsonObj = new JSONObject("{\"list\":{\"user\": {\"id\": " + userId + "},\"items\":[{\"upc\": {\"id\": " + upc + "},\"qtyRequested\":1}]}}");
            StringEntity input = new StringEntity(jsonObj.toString());
            postRequest.addHeader("content-type", "application/json");
            postRequest.addHeader("Accept", "application/json");
            System.out.println(input);
            postRequest.setEntity(input);
            CloseableHttpResponse response = httpClient.execute(postRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            httpClient.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to create list through service
     *
     * @return list details
     */
    public static void createList(String list) throws Throwable {
        try {
            String userId = Cookies.getCookieValue(macys() ? "macys_online_uid" : "bloomingdales_online_uid");
            String url = "http://172.21.7.185:8080/api/customer/v1/lists?userid=" + userId;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost postRequest = new HttpPost(url);
            JSONObject jsonObj = new JSONObject("{\"list\":{\"user\": {\"id\": " + userId + "},\"name\": " + list + ",\"listType\":W}}");
            StringEntity input = new StringEntity(jsonObj.toString());
            postRequest.addHeader("content-type", "application/json");
            postRequest.addHeader("Accept", "application/json");
            System.out.println(input);
            postRequest.setEntity(input);
            CloseableHttpResponse response = httpClient.execute(postRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            httpClient.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}