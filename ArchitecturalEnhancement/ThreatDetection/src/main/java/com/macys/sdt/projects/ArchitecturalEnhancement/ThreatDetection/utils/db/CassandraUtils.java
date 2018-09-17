package com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.utils.db;

import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.db.cassandra.CassandraConnector;;
import org.junit.AfterClass;
import org.slf4j.LoggerFactory;

/**
 * Created by YH03512 on 1/8/2018.
 */
public class CassandraUtils {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CassandraUtils.class);
    private static CassandraConnector client = new CassandraConnector();
    private static int port = 9042;
    private static String cassandra_host = null;

    public static void connect() throws Throwable {
        try {
            log.info("Connecting To:  " + cassandraOneHostIp());
            Thread.sleep(3000);
            con(cassandraOneHostIp());
            Thread.sleep(3000);
        } catch (Exception e) {
            log.info("Error:: Connection Failed for " + cassandraOneHostIp() + " " + e.getMessage());
        }
        if (client.getSession() == null) {
            try {
                log.info("Connecting To:  " + cassandraTwoHostIP());
                Thread.sleep(3000);
                con(cassandraTwoHostIP());
            } catch (Exception e) {
                log.info("Error:: Connection Failed for " + cassandraTwoHostIP() + " " + e.getMessage());
            }
        }
    }


    public static String cassandraOneHostIp(){
        return EnvironmentDetails.otherApp("Preferences_Cassandra1").ipAddress;
    }

    public static String cassandraTwoHostIP(){
        return EnvironmentDetails.otherApp("Preferences_Cassandra2").ipAddress;
    }


    public static void con(String cassandra_host) {
        client.connect(cassandra_host, port, "cdeploy", "cdeploy");
    }


    @AfterClass
    public static void close() {
        try{
            client.close();}
        catch (Exception e){
            log.info("Error:: Unable to close the session" + e.getMessage());
        }
    }
}
