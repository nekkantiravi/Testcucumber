package com.macys.sdt.shared.steps.MEW;

import com.macys.sdt.shared.actions.MEW.panels.FacetAccordionModel;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacetSelections {

    private static final Logger log = LoggerFactory.getLogger(FacetSelections.class);

    @When("^I select facet name \"([^\"]*)\"$")
    public void iSelectFacetName(String name) throws Throwable {
        FacetAccordionModel.selectFacetByName(name);
        log.info("selected facet with name "+name);
    }

    @And("^I select facet value \"([^\"]*)\"$")
    public void iSelectFacetValue(String value) throws Throwable {
        FacetAccordionModel.selectFacetValueByName(value);
        log.info("selected facet with value"+value);
    }

    @When("^I click on Apply button$")
    public void iClickOnApplyButton() throws Throwable {
        FacetAccordionModel.apply();
        log.info("Successfully clicked on apply button");
    }
}
