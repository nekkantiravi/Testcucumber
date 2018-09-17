package com.macys.sdt.projects.Customer.MyAccountRedesign.utils;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by m509575 on 16/03/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uslid",
        "phone",
        "points"
})
public class FullyEnrolled {

    @JsonProperty("uslid")
    private String uslid;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("points")
    private String points;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("uslid")
    public String getUslid() {
        return uslid;
    }

    @JsonProperty("uslid")
    public void setUslid(String uslid) {
        this.uslid = uslid;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("points")
    public String getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(String points) {
        this.points = points;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

