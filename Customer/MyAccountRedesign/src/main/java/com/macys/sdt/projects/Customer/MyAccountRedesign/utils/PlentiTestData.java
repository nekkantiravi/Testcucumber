package com.macys.sdt.projects.Customer.MyAccountRedesign.utils;

/**
 * Created by m509575 on 16/03/17.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fully_enrolled",
        "partially_enrolled"
})
public class PlentiTestData {

    @JsonProperty("fully_enrolled")
    private List<FullyEnrolled> fullyEnrolled = null;
    @JsonProperty("partially_enrolled")
    private List<PartiallyEnrolled> partiallyEnrolled = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fully_enrolled")
    public List<FullyEnrolled> getFullyEnrolled() {
        return fullyEnrolled;
    }

    @JsonProperty("fully_enrolled")
    public void setFullyEnrolled(List<FullyEnrolled> fullyEnrolled) {
        this.fullyEnrolled = fullyEnrolled;
    }

    @JsonProperty("partially_enrolled")
    public List<PartiallyEnrolled> getPartiallyEnrolled() {
        return partiallyEnrolled;
    }

    @JsonProperty("partially_enrolled")
    public void setPartiallyEnrolled(List<PartiallyEnrolled> partiallyEnrolled) {
        this.partiallyEnrolled = partiallyEnrolled;
    }

    public PartiallyEnrolled getRandomPartiallyEnrolled(){
        return this.getPartiallyEnrolled().get(ThreadLocalRandom.current().nextInt(this.getPartiallyEnrolled().size()-1));
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





