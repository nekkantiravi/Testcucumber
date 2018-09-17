package com.macys.sdt.projects.Marketing.UFT.steps.website;

/**
 * Created by m645990 on 7/27/17.
 */
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Marketing.UFT.actions.website.FacetInfo;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UFTSEO extends StepUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(UFTSEO.class);

    @When("^I verify SEO attributes are present in (selected|all) facets$")
    public void iVerifySeoAttributesArePresentInFacets(String facet_state) {
        boolean include_all = (facet_state.equals("all"));
        String facet_errors;
        facet_errors = getMismatchedFacetList(include_all);
        Assert.assertTrue(facet_errors, facet_errors.equals(""));
    }

    private String getMismatchedFacetList(boolean include_all) {
        String facet_errors = "";
        List<String> facet_error_list = new ArrayList<>();
        String error_string = "Facet link does not include all required attribute values.  Facet name: ";
        String currentUrl = WebDriverManager.getCurrentUrl();
        Connection connection = Jsoup.connect(currentUrl);
        Document doc;
        try {
            LOGGER.info("Attempting to retrieve URL: ", currentUrl);
            doc = connection.get();
        } catch (IOException e) {
            LOGGER.error("ERROR - ENV: Connection threw IOException: ", e);
            return "ERROR - ENV: Connection threw IOException";
        }
        List<Element> facet_list = doc.select("ul#facets>li.typbox");
        for (Element facet_element : facet_list) {
            String facet_name = facet_element.attr("aria-label");
            List<Element> facet_values = facet_element.select("a.facet_link");
            for (Element facet_value_element : facet_values) {
                FacetInfo info = new FacetInfo(facet_value_element);
                if ((include_all || info.isSelected) && !(info.isNoFollow && info.isNoIndex)) {
                    String current_error = (error_string + facet_name + "Facet Value: " + info.dataValue + " ");
                    if (!info.isNoIndex) {
                        current_error += "Missing noindex in rel attribute. ";
                    }
                    if (!info.isNoFollow) {
                        current_error += "Missing nofollow in rel attribute. ";
                    }
                    facet_error_list.add(current_error);
                }
            }
        }
        if (facet_error_list.size() > 0) {
            facet_errors = StringUtils.join(facet_errors, "\n");
        }
        return facet_errors;
    }
}
