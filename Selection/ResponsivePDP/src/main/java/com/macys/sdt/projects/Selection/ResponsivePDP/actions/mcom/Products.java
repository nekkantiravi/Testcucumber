package com.macys.sdt.projects.Selection.ResponsivePDP.actions.mcom;


import org.junit.Assert;
import java.util.Random;
import java.util.logging.Logger;


public class Products {

    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static Random rand = new Random();
    private static int index;

    public static String ID(String productType) throws Throwable {

        String PID = null;
        if(productType.equalsIgnoreCase("member")) {
            String[] pId = {"22804","1310","22805"};
            index = rand.nextInt(pId.length);
            PID = pId[index];
        }
        else if(productType.equalsIgnoreCase("memberWithColor")) {
            String[] pId = {"77589","5747"};
            index = rand.nextInt(pId.length);
            PID = pId[index];
        }
        else if(productType.equalsIgnoreCase("memberWithSizeColor")) {
            String[] pId = {"3407910","5015175","4820073","4503088"};
            index = rand.nextInt(pId.length);
            PID = pId[index];
        }
        else if(productType.equalsIgnoreCase("memberWithSizeColorType")) {
            String[] pId = {"","","","",""};
            index = rand.nextInt(pId.length);
            PID = pId[index];
        }
        else if(productType.equalsIgnoreCase("memberWithTypeColor")) {
            String[] pId = {"1101386"};
            index = rand.nextInt(pId.length);
            PID = pId[index];
        }
        else if(productType.equalsIgnoreCase("productNotFound")) {
            String[] pId = {"1"};
            index = rand.nextInt(pId.length);
            PID = pId[index];
        }
        else if(productType.equalsIgnoreCase("productNoPromoBadge")) {
            String[] pId = {"2351345"};
            index = rand.nextInt(pId.length);
            PID = pId[index];
        }
        else if(productType.equalsIgnoreCase("productPromoBadge")) {
            String[] pId = {"1310"};
            index = rand.nextInt(pId.length);
            PID = pId[index];
        }
        else {
            log.warning("Error: Product Type Not Defined!");
            Assert.fail();
        }
        return PID;
    }

}
