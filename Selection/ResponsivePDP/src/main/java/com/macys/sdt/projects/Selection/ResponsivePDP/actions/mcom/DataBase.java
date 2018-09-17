package com.macys.sdt.projects.Selection.ResponsivePDP.actions.mcom;


import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.db.cassandra.CassandraConnector;
import org.json.JSONObject;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class DataBase {

    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static String DbProductAttributes = null;

    public static void connect(String pId) throws Throwable {
        CassandraConnector client = new CassandraConnector();
        int port = 9042;
        String DbIP1;
        String DbIP2;

        try {
            DbIP1 = "11.168.91.3";//QA13
//            DbIP1 = EnvironmentDetails.otherApp("FCC_Cassandra").ipAddress;
            log.info("Connecting To DbIP1:  " + DbIP1);
            Thread.sleep(3000);
            client.connect(DbIP1, port, "fccuser", "xj501we");
            Thread.sleep(3000);
        }
        catch (Exception e) {
            log.warning("Error:: DbIP1 Connection Failed: " + e.getMessage());
        }
        if (client.getSession() == null) {
            try {
                DbIP2 = "11.168.91.2";//QA13
//                DbIP2 = EnvironmentDetails.otherApp("FCC_Cassandra2").ipAddress;
                log.info("Connecting To DbIP2:  " + DbIP2);
                client.connect(DbIP2, port, "fccuser", "xj501we");
            }
            catch (Exception e) {
                log.warning("Error:: DbIP2 Connection Failed: " + e.getMessage());
            }
        }
        if (client.getSession() != null) {
            try {
                DbProductAttributes = client.select("SELECT VALUE FROM catalog_v16.product WHERE PID=" + pId).get(0).get("value").toString();
                client.close();
            }
            catch (Exception e) {
                log.warning("Error:: DB Query Execution " + e.getMessage());
            }
        }
    }

    public static String getAttributeValue(String pId, String attribute) throws Throwable {

        String attrValue = null;
        connect(pId);
        if(attribute.equalsIgnoreCase("maxQuantity")) {
            JSONObject jsonObj = new JSONObject(DbProductAttributes);
            attrValue = jsonObj.optString("maxQuantity");
            Assert.assertTrue(attrValue != null);
        }
        log.info(" CassandraDB:: Value of " + attribute + " is: " + attrValue);
        return attrValue;
    }

    public static List<String> getAttributeValues(String pId) throws Throwable {

        List<String> attrValues = new ArrayList<>();
        connect(pId);
        JSONObject jsonObj = new JSONObject(DbProductAttributes);
        attrValues.add(jsonObj.optString("name"));
        attrValues.add(jsonObj.optString("brand"));
        attrValues.add(jsonObj.optString("maxQuantity"));

        if(attrValues.size() == 0) {
        log.warning("The DB Attribute List is Empty!");
        return null;
        }
        else
            return attrValues;
    }

}
