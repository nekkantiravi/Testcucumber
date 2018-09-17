package com.macys.sdt.projects.Selection.ResponsivePDP.actions.mcom;


import com.macys.sdt.framework.runner.RunConfig;
import org.junit.Assert;
import java.util.logging.Logger;


public class Devices {

    private static Logger log = Logger.getLogger(Thread.currentThread().getClass().getName());
    private static boolean runTest;

    public static boolean runScenario(String device) {
        String deviceType = RunConfig.getEnvOrExParam("device");
        if(deviceType == null) {
            log.info("Scenario runs across all devices! Now::  " + device.toUpperCase()+
                    "\n===========================================================================\n");
            runTest = true;
        }
        else if(deviceType.equalsIgnoreCase(device)) {
            log.info("Scenario is running on " + RunConfig.getEnvVar("device").toUpperCase() +
                    "\n===========================================================================\n");
            runTest = true;
        }
        else {
            runTest = false;
            log.info("Scenario not executed on " + device.toUpperCase() +
                    "\n===========================================================================\n");
        }
        return runTest;
    }

//    public static boolean runScenario(String device) {
//        String deviceType = RunConfig.getEnvOrExParam("device");
//        if(deviceType == null) {
//            log.info("Scenario runs across all devices! Now::  " + device.toUpperCase());
//            runTest = true;
//        }
//        else if(deviceType.contains("Laptop") && device.equals("desktop")) {
//            log.info("Scenario only runs on "+device.toUpperCase()+" using simulation::  \""+RunConfig.getEnvVar("device")+"\"");
//            runTest = true;
//        }
//        else if(deviceType.contains("iPhone") && device.equals("mobile")) {
//            log.info("Scenario only runs on "+device.toUpperCase()+" using simulation::  \""+RunConfig.getEnvVar("device")+"\"");
//            runTest = true;
//        }
//        else if(deviceType.contains("iPad") && device.equals("tablet")) {
//            log.info("Scenario only runs on "+device.toUpperCase()+" using simulation::  \""+RunConfig.getEnvVar("device")+"\"");
//            runTest = true;
//        }
//        else {
//            runTest = false;
//            Assert.fail("Scenario not executed on " + device.toUpperCase() + "!");
////            log.info("Scenario not executed on " + device.toUpperCase() + "!");
//        }
//        return runTest;
//    }

}


