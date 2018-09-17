package com.macys.sdt.projects.PurchaseAndDelivery.ABFreeShipping.utils.db.models;

import com.macys.sdt.framework.utils.EnvironmentDetails;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class AddToBagService {

    /**
     * Method to item to bag given userID, UPCID and amount
     **/
    public static void addToBag(String userID, String upcId, String amount) throws Throwable {
        try {
            String serviceUrl = getServiceURL();
            serviceUrl = serviceUrl + userID;
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost postRequest = new HttpPost(serviceUrl);

            String soapmessageString = "<item>\n" +
                    "<quantity>1</quantity>\n" +
                    "<source>source</source>\n" +
                    "<syndicationId>404025</syndicationId>\n" +
                    "<syndicationSource>ASI</syndicationSource>\n" +
                    "<syndicationItemId>123</syndicationItemId>\n" +
                    "<syndicationTransactionId>QH000258C</syndicationTransactionId>\n" +
                    "<syndicationItemDesc>ItemDesc</syndicationItemDesc>\n" +
                    "<giftCardAmount>" + amount + "</giftCardAmount>\n" +
                    "<upcId>" + upcId + "</upcId>\n" +
                    "<personalizationFlag>true</personalizationFlag>  \n" +
                    "</item>";
            JSONObject json = XML.toJSONObject(soapmessageString);
            StringEntity input = new StringEntity(json.toString());
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
     * Method to return add to bag service URL
     * @return add to bag service URL
     **/
    private static String getServiceURL() throws Throwable {
        return "http://" + EnvironmentDetails.otherApp("MSPORDER").ipAddress + ":8080/api/order/v1/bags?userId=";
    }
}
