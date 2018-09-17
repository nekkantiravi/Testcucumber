package com.macys.sdt.projects.Selection.ResponsivePDP.actions.bcom;


import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.db.cassandra.CassandraConnector;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class BCOMDatabase{
    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static String preOrder_query = "Select u.prod_id, a.upc_id, u.upc, p.prod_id, p.prod_name, p.prod_type_name, a.inv_status_code, v.attr_name From product p join upc u on u.prod_id=p.prod_id join availability a on a.upc_id=u.upc_id and a.available='Y' join unary_prd_attr_val v on v.prod_id=u.prod_id Where v.attr_name='PRE_ORDER' and v.attr_value='Y' and a.inv_status_code !='DISCONTINUED'";
    private static String onOrder_query = "Select distinct(u.prod_id), p.prod_name, p.prod_type_name, a.inv_status_code, u.SURCHARGE_FEE, a.ORDER_METHOD From product p join upc u on u.prod_id=p.prod_id join availability a on a.upc_id=u.upc_id and a.available='Y' join unary_prd_attr_val v on v.prod_id=u.prod_id Where a.inv_status_code='BACKORDERED' AND a.ORDER_METHOD = 'ORDR'";
    private static String unavailable_query = "Select u.prod_id, a.upc_id, u.upc, p.prod_id, p.prod_name, p.prod_type_name, a.inv_status_code, v.attr_name From product p join upc u on u.prod_id=p.prod_id join availability a on a.upc_id=u.upc_id and a.available='N' join unary_prd_attr_val v on v.prod_id=u.prod_id Where v.attr_name='PRE_ORDER' and v.attr_value='Y'";
    private static String directVendor = "Select distinct(u.prod_id), p.prod_name, p.prod_type_name, a.inv_status_code, u.SURCHARGE_FEE, a.ORDER_METHOD From product p join upc u on u.prod_id=p.prod_id join availability a on a.upc_id=u.upc_id and a.available='Y' join unary_prd_attr_val v on v.prod_id=u.prod_id Where a.inv_status_code='BACKORDERED' AND a.ORDER_METHOD = 'DROP'";
    private static String orderByPhone = "Select u.prod_id, a.upc_id, u.upc, p.prod_id, p.prod_name, p.prod_type_name, a.inv_status_code, v.attr_name From product p join upc u on u.prod_id=p.prod_id join availability a on a.upc_id=u.upc_id and a.available='Y' join unary_prd_attr_val v on v.prod_id=u.prod_id Where v.attr_name='PHONE_ONLY' and v.attr_value='Y' and a.inv_status_code !='DISCONTINUED'";
    private static String specialOrder = "Select distinct(u.prod_id), p.prod_name, p.prod_type_name, a.inv_status_code, u.SURCHARGE_FEE, a.ORDER_METHOD From product p join upc u on u.prod_id=p.prod_id join availability a on a.upc_id=u.upc_id and a.available='Y' join unary_prd_attr_val v on v.prod_id=u.prod_id Where a.inv_status_code='SPECIAL'";
    private static String productVideo = "Select u.prod_id, a.upc_id, u.upc, p.prod_id, p.prod_name, p.prod_type_name, a.inv_status_code, v.attr_name, v.attr_value From product p join upc u on u.prod_id=p.prod_id join availability a on a.upc_id=u.upc_id and a.available='Y' and a.WEDDING_REGISTRY_F='Y' join unary_prd_attr_val v on v.prod_id=u.prod_id Where v.attr_name='PRODUCT_VIDEO'";
    private static String lowAltImages = "Select p.prod_id, p.prod_name, p.prod_type_name, t.COUNT FROM( Select a.upc_id, count(distinct(ci.IMAGE_NAME)) as COUNT From product p join upc u on u.prod_id=p.prod_id join availability a on a.upc_id=u.upc_id and a.available='Y' join COLORWAY_IMAGE ci on u.COLORWAY_ID=ci.COLORWAY_ID Where u.COLORWAY_ID > 0 and a.inv_status_code !='DISCONTINUED' and p.prod_type_name!='ELECTRONIC_GIFT_CARD' and ci.IMAGE_ROLE='CADD' group by a.upc_id) t join upc u on t.upc_id=u.upc_id join product p on u.prod_id=p.prod_id Where COUNT <4";
    private static String available = "select prod_id, prod_name, rand() as idx from product where prod_id in( select distinct prod_id from upc where upc_id not in ( select upc_id from price_event where effective_date<=(select timestamp_value from custom_date where date_name = 'CUSTOM_DATE') and expiration_date>=(select timestamp_value from custom_date where date_name = 'CUSTOM_DATE') group by upc_id having count(distinct price_retail) >1 and count(distinct date(effective_date))>=1 ) and upc_id in ( select upc_id from price where price_type_id=1 union all select upc_id from price_event where effective_date<= (select timestamp_value from custom_date where date_name = 'CUSTOM_DATE') and expiration_date>= (select timestamp_value from custom_date where date_name = 'CUSTOM_DATE') and price_type_id in(1) ) and prod_id in ( select prod_id from product where display_stat_code=1 and product_eff_date <= (select timestamp_value from custom_date where date_name = 'CUSTOM_DATE') and product_exp_date >= (select timestamp_value from custom_date where date_name = 'CUSTOM_DATE') and prd_grp_flag='N' and prod_id in (select prod_id from upc where display_stat_code = 1 and upc_eff_date <= (select timestamp_value from custom_date where date_name = 'CUSTOM_DATE') and upc_exp_date >= (select timestamp_value from custom_date where date_name = 'CUSTOM_DATE') and upc_id in (select upc_id from availability where available='Y')) ))";
    private static String multi_tier_colors = "Select t.prod_id, pp.prod_name, pp.prod_type_name, t.COUNT FROM( Select p.prod_id, count(u.upc_id) as COUNT From product p join upc u on u.prod_id=p.prod_id join availability a on a.upc_id=u.upc_id Where a.inv_status_code !='DISCONTINUED' and p.prod_type_name!='ELECTRONIC_GIFT_CARD' and a.available='Y' group by p.prod_id ) t join product pp on t.prod_id=pp.prod_id Where t.COUNT >20 and pp.prod_type_name != 'FACE'";

    public static String getRandomPdpFromDB(Map<String, Object> options) throws Throwable {
        String dbName = "BGSITE";
        String dbUrl = "jdbc:db2://172.21.11.34:50001/BGSITE";
        String dbUsername = "db2blms";
        String dbPassword = "Zv3zsjSKpc";
        Statement stmt = null;
        ArrayList<String> products = new ArrayList<>();
        String sqlQuery = null;
        String prevProduct = "";
        ResultSet resultSet = null;

        try {
            if(options.containsKey("pre_order")) {
                sqlQuery = preOrder_query;
                Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
                log.info("database connection received");
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(sqlQuery);
                log.info("query executed");
                Thread.sleep(3000);
                JSONObject rootJson = null;

                while (resultSet.next() && products.size() < 20) {
                    if(!resultSet.getString("PROD_ID").equals(prevProduct)){
                        rootJson = BCOMxapi.getBcomJsonPDPXAPIService(
                                resultSet.getString("PROD_ID"));
                        JSONArray prodInfo = rootJson.getJSONArray("product");
                        JSONObject prodObject = prodInfo.getJSONObject(0);
                        JSONObject detail = prodObject.getJSONObject("detail");
                        JSONObject flags = detail.getJSONObject("flags");
                        if (flags.has("eligibleForPreOrder")) {
                            products.add(resultSet.getString("PROD_ID"));
                        }
                    }
                    prevProduct = resultSet.getString("PROD_ID");
                }
            }
            if(options.containsKey("available")) {
                sqlQuery = available;
                Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
                log.info("database connection received");
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(sqlQuery);
                log.info("query executed");
                Thread.sleep(3000);
                while (resultSet.next() && products.size() < 20) {
                    if (!resultSet.getString("PROD_ID").equals(prevProduct)) {
                        products.add(resultSet.getString("PROD_ID"));
                    }
                }
                prevProduct = resultSet.getString("PROD_ID");
            }
            if(options.containsKey("two_tier_colors") || options.containsKey("three_tier_colors")) {
                sqlQuery = multi_tier_colors;
                Connection connection = DBUtils.setupDBConnection(dbName, dbUrl, dbUsername, dbPassword);
                log.info("database connection received");
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(sqlQuery);
                log.info("query executed");
                Thread.sleep(3000);
                while (resultSet.next() && products.size() < 20) {
                    if (!resultSet.getString("PROD_ID").equals(prevProduct)) {
                        products.add(resultSet.getString("PROD_ID"));
                    }
                }
                prevProduct = resultSet.getString("PROD_ID");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        finally {
            resultSet.close();
            DBUtils.closeDBConnection();
        }

        Random rand = new Random();
        log.info("items found =" + products.size());
        for(String id : products){
            log.info("ids are: "+id);
        }
        return products.get(rand.nextInt(products.size()));
    }

    public static String getDBUrl(){
        return "jdbc:db2://"+ EnvironmentDetails.otherApp("SITE-ASYNC-DB2").ipAddress+":50001/BGSITE";
    }


    public static void cassandraBops() throws Throwable {
        CassandraConnector client = new CassandraConnector();
        int port = 9042;
        String DbIP1 = "172.21.11.42";
        String DbIP2 = "172.21.11.42";

        try {
            log.info("Connecting To DbIP1:  " + DbIP1);
            Thread.sleep(3000);
            client.connect(DbIP1, port, "fccuser", "xj501we");
            Thread.sleep(3000);
        }
        catch (Exception e) {
            log.info("Error:: DbIP1 Connection Failed: " + e.getMessage());
        }
        if (client.getSession() == null) {
            try {
                log.info("Connecting To DbIP2:  " + DbIP2);
                client.connect(DbIP2, port, "fccuser", "xj501we");
            }
            catch (Exception e) {
                log.info("Error:: DbIP2 Connection Failed: " + e.getMessage());
            }
        }
        if (client.getSession() != null) {
            try {
                client.select("INSERT INTO inventory_v5.STORE_BOPS_ELIGIBILITY (storenbr,bopselig,lastmodified) VALUES (343,true,1452236822000)");
                client.select("INSERT INTO inventory_v5.store_eligibility (locnbr,bopselig,lastmodified,sddelig) VALUES (343,true,1452236822000,true)");
                client.select("INSERT INTO inventory_v5.STORE_BOPS_ELIGIBILITY (storenbr,bopselig,lastmodified) VALUES (343,true,1452236822000)");

                //log.info("res is "+ res);
                client.close();
            }
            catch (Exception e) {
                log.info("Error:: DB Query Execution " + e.getMessage());
            }
        }
    }



}
