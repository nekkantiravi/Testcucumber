package com.macys.sdt.projects.Selection.List.utils.MEW;

import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import org.json.JSONObject;
import org.junit.Assert;

import javax.ws.rs.core.Response;

public class ApiResponse {

    private ApiResponse() {
        throw new IllegalStateException("Utility class");
    }

    public static JSONObject getJsonAPIService(String prodID){
        String PATH = "/api/v4/catalog/product/";
        String resource = getServiceUrl(PATH,prodID);
        Response response = RESTOperations.doGET(resource, null);
        int status = response.getStatus();
        Assert.assertTrue(status==200);
        String finalJsonStr = response.readEntity(String.class);
        return new JSONObject(finalJsonStr);
    }

    private static String getServiceUrl(String PATH, String prodId) {
        String envPath = EnvironmentDetails.getEnvUrl().substring(EnvironmentDetails.getEnvUrl().lastIndexOf('/') + 1);
        return "http://"+envPath+PATH+prodId;
    }
}
