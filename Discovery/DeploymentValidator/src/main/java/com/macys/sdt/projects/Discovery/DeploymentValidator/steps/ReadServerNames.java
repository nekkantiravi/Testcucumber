package com.macys.sdt.projects.Discovery.DeploymentValidator.steps;


import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.projects.Discovery.DeploymentValidator.util.*;
import com.macys.sdt.projects.Discovery.DeploymentValidator.util.ParseJSON;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.steps.website.PageNavigation;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by m125779 on 6/21/2017.
 */
public class ReadServerNames extends StepUtils {
    private static final Logger logger = LoggerFactory.getLogger(ReadServerNames.class);
    private static ValidatorConfigParser cParse= new ValidatorConfigParser();
    ParseJSON pJSON =new ParseJSON();
    HashMap<String, ArrayList> finalServerList = new HashMap<>();


    @When("^I hit all the instances against the api$")
    public void iHitAllTheInstancesAgainstTheApi() throws Throwable {
        String dca= RunConfig.getEnvOrExParam("dca") ;
        String cell =RunConfig.getEnvOrExParam("cell") ;
        String site =RunConfig.getEnvOrExParam("site") ;
        Iterator it=null;
        if (dca.contains("RTP")) {
            if (cell.contains("A") && site.contains("MCOM")) {
                System.out.println("RTP MCOM CELL A is being verified ");

                it = cParse.MCOMRTPCELLAServerMap.entrySet().iterator();
            } else if (cell.contains("B") && site.contains("MCOM")) {
                System.out.println("RTP MCOM CELL B is being verified ");

                it = cParse.MCOMRTPCELLBServerMap.entrySet().iterator();

            } else if (cell.contains("A") && site.contains("BCOM")) {
                System.out.println("RTP BCOM CELL A is being verified ");

                 it = cParse.BCOMRTPCELLAServerMap.entrySet().iterator();

            } else if (cell.contains("B") && site.contains("BCOM")) {
                System.out.println("RTP BCOM CELL B is being verified ");

                 it = cParse.BCOMRTPCELLBServerMap.entrySet().iterator();
            }

        }else {
            if (site.contains("MCOM")) {
                it = cParse.MCOMDALServerMap.entrySet().iterator();
            } else {
                it = cParse.BCOMDALServerMap.entrySet().iterator();
            }
        }
                List<String> serverList = new ArrayList<String>();
                while (it.hasNext()) {
                    Map.Entry pair1 = (Map.Entry) it.next();
                    if (pair1.getValue().toString().contains("FCC")) {
                        serverList.add(pair1.getKey().toString());
                        // serverList.add("macyds703");
                        // serverList.add("ma209mlvfcc050");

                        pJSON.getFCCVersion(serverList);

                    }




//        else {
//            List<String> serverList =new ArrayList<String>();
//            serverList.add("macyds703");
//            serverList.add("ma209mlvfcc050");
//            pJSON.getFCCVersion(serverList);

       }

        throw new PendingException();
    }


    @Then("^I verify that the responses are identical$")
    public void iVerifyThatTheResponsesAreIdentical() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    @Given("^I get all server instance names from IFSCAP$")
    public void iGetAllServerInstanceNamesFromIFSCAP() throws Throwable {
        logger.debug("In the method    void i_get_all_server_instance_names_from_IFSCAP  ");
//        String dca = "RTP";
//        String cell = "A";
//        String site = "MCOM";
//        pausePageHangWatchDog();
//        if(RunConfig.getEnvOrExParam("dca") != null)
//            dca = RunConfig.getEnvOrExParam("dca") ;
//        if(RunConfig.getEnvOrExParam("cell") != null)
//            cell = RunConfig.getEnvOrExParam("cell") ;
//        if(RunConfig.getEnvOrExParam("site") != null)
//            site = RunConfig.getEnvOrExParam("site") ;
//        String key = site+"_"+dca;
//        if (!finalServerList.containsKey(key)) {
//            Navigate.visit("https://ifscap.macys.net/#/app/health/infrastructure/serverlist");
//            Thread.sleep(10000);
//            shouldBeOnPage("ifs_server_list");
//            if (!site.equalsIgnoreCase("MCOM")) {
//                final String filterOption = site;
//                Clicks.click("ifs_server_list.site_button");
//                WebElement siteElement = Elements.findElements("ifs_server_list.site_dropdown_values").stream().filter(f -> f.isDisplayed() && f.getText().equalsIgnoreCase(filterOption)).collect(Collectors.toList()).get(0);
//                Clicks.click(siteElement);
//            }
//            if (!dca.equalsIgnoreCase("RTP")) {
//                final String filterOption = dca;
//                Clicks.click("ifs_server_list.dca_button");
//                WebElement dcaElement = Elements.findElements("ifs_server_list.dca_dropdown_values").stream().filter(f -> f.isDisplayed() && f.getText().equalsIgnoreCase(filterOption)).collect(Collectors.toList()).get(0);
//                Clicks.click(dcaElement);
//            }
//            Thread.sleep(10000);
//            Wait.untilElementPresent("ifs_server_list.per_page_count_buttons");
//            Clicks.click("ifs_server_list.submit_button");
//            Wait.forPageReady();
//
//            WebElement perPageCountElement = Elements.findElements("ifs_server_list.per_page_count_buttons").stream().filter(f -> f.isDisplayed() && f.getText().equalsIgnoreCase("5000")).collect(Collectors.toList()).get(0);
//            Clicks.click(perPageCountElement);
//            Thread.sleep(15000);
//
//            List<String> serverListRawDetails = Elements.findElements("ifs_server_list.server_list_table_descriptions").stream().filter(f -> f.isDisplayed()).map(m -> m.getText()).collect(Collectors.toList());
//            int noOfRows = serverListRawDetails.size() / 10;
//            ArrayList<HashMap> serversList = new ArrayList<>();
//            for (int rowCount = 1; rowCount <= noOfRows; rowCount++) {
//                HashMap<String, String> serverDetails = new HashMap<String, String>();
//                serverDetails.put("serverHost", serverListRawDetails.get(rowCount * 10 - 9));
//                serverDetails.put("serverName", serverListRawDetails.get(rowCount * 10 - 8));
//                serverDetails.put("site", serverListRawDetails.get(rowCount * 10 - 7));
//                serverDetails.put("dca", serverListRawDetails.get(rowCount * 10 - 6));
//                serverDetails.put("cell", serverListRawDetails.get(rowCount * 10 - 5));
//                serversList.add(serverDetails);
//            }
//            finalServerList.put(key,serversList);
//            resumePageHangWatchDog();
//            JSONObject.toJSONString(finalServerList);
//        }

        String resp=  ValidatorConfigParser.getIFSResponse("","");
        cParse.parseString(resp);

    }

}
