package com.macys.sdt.projects.Discovery.SearchBrowseComponentization.steps.website;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by Discovery QE on 12/28/16.
 * This class is mapped with Product Json Data
 */

public class ProductData {

    private Map<String, Map<String, Map<String, String>>> priceTypesMap = new HashMap<>();

    public Map<String, Map<String, Map<String, String>>> getPriceTypesMap() {
        return priceTypesMap;
    }
}
