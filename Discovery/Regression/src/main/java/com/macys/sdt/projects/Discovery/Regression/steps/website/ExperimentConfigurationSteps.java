package com.macys.sdt.projects.Discovery.Regression.steps.website;

import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Discovery.Regression.utils.config.website.DiscoveryAkamaiUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class ExperimentConfigurationSteps extends StepUtils {

    public static final Logger logger = LoggerFactory.getLogger(ExperimentConfigurationSteps.class);
    private static JSONArray experimentDetails = null;


    @Given("^I fetch all configured experiment values for (mcom|bcom) production$")
    public void iFetchAllConfiguredExperimentValuesForProduction(String brand) throws Throwable {
        if(brand.equalsIgnoreCase("mcom")){
            getExperimentConfigurationDetails("http://expcore.macys.com/campaign/all");
        }else{
            getExperimentConfigurationDetails("http://expcore.bloomingdales.com/campaign/all");
        }
    }

    @Then("^I verify below experiments details:$")
    public void iVerifyBelowExperimentsDetails(List<String> experiments) throws Throwable {
        List<HashMap> actualExperiments = experimentDetails.toList().stream().filter(f -> experiments.contains(((HashMap)f).get("name"))).map(m -> (HashMap)m).collect(Collectors.toList());
        JSONObject expectedExperiments = getExperimentsJson();
        for (String experiment : experiments) {
            HashMap actualExperiment = actualExperiments.stream().filter(f -> f.get("name").equals(experiment)).collect(Collectors.toList()).get(0);
            List actualRecipes = (ArrayList)actualExperiment.get("recipes");
            List expectedRecipes = ((JSONArray)expectedExperiments.get(experiment)).toList();
            Assert.assertTrue("ERROR - APP: Expected experiments are:'"+expectedRecipes+"' and Actual experiment are:"+actualRecipes,
                    expectedRecipes.equals(actualRecipes));
            logger.info("Verified experiment details successfully for experiment name:"+experiment);
        }
    }

    /**
     * Fills Experiments details with the test data from the JSON file
     *
     */
    private static JSONObject getExperimentsJson() {
        String domainName = DiscoveryAkamaiUtils.getDomainName(RunConfig.url);
        String jsonText = "";
        try {
            jsonText = Utils.readTextFile(getResourceFile("discovery_experiments.json"));
        } catch (java.io.IOException e) {
            logger.error("Failed to load discovery domain experiments JSON file: " + e);
        }
        JSONObject akamaiJSON = new JSONObject(jsonText);
        return akamaiJSON.getJSONObject(domainName);
    }

    private static JSONArray getExperimentConfigurationDetails(String url){
        if(experimentDetails == null){
            experimentDetails = new JSONArray(RESTOperations.doGET(url).readEntity(String.class));
        }
        return experimentDetails;
    }

}
