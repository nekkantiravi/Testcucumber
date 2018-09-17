package com.macys.sdt.projects.Discovery.DeploymentValidator.steps;

import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Discovery.DeploymentValidator.util.ValidatorConfigParser;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by m125779 on 8/2/2017.
 */
public class DataRetrievalSteps extends StepUtils {

    private static final Logger logger = LoggerFactory.getLogger(DataRetrievalSteps.class);
    private static ValidatorConfigParser cParse= new ValidatorConfigParser();
    private static String NavappVersion="";
    Boolean NavAppVersionFound=false;

    @When("^I read the version from the page source$")
    public void I_read_version_from_viewsource() throws Throwable {

    }

    @Then("^I verify that the versions on all servers are identical$")
    public void Compare_versions_on_all_server_instances() throws Throwable {

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

        while (it.hasNext()) {
            Map.Entry pair1 = (Map.Entry) it.next();
            if (pair1.getValue().toString().contains("NavApp")) {
                Navigate.visit("http://"+pair1.getKey().toString()+":8080");
                Wait.isPageLoaded();
                int vindex=WebDriverManager.getWebDriver().getPageSource().lastIndexOf("<version>");
                int lastindex=WebDriverManager.getWebDriver().getPageSource().lastIndexOf("</version>");
                String NavappInstanceVersion = WebDriverManager.getWebDriver().getPageSource().substring(vindex+9,lastindex);
                 if (NavAppVersionFound==false) {
                    NavappVersion = NavappInstanceVersion;
                    NavAppVersionFound=true;
                }
                System.out.println("NavApp version is ::"+NavappInstanceVersion);

                if (NavappInstanceVersion.equals(NavappVersion)){
                    System.out.println("MATCHING NAVAPP VERSION ");
                }
                else{
                    System.out.println(" NOT MATCHING NAVAPP VERSION ");
                }

            }





        }



    }

}

