package com.macys.sdt.projects.Marketing.UFT.actions.website;

import org.jsoup.nodes.Element;

/**
 * Created by m645990 on 7/27/17.
 */
public class FacetInfo {
    public final boolean isNoFollow, isNoIndex, isSelected;
    public final String dataValue, linkUrl;

    public FacetInfo(Element facet_element) {
        isNoFollow = facet_element.attr("rel").toLowerCase().contains("nofollow");
        isNoIndex = facet_element.attr("rel").toLowerCase().contains("noindex");
        isSelected = facet_element.attr("class").toLowerCase().contains("selected");
        dataValue = facet_element.attr("data-value");
        linkUrl = facet_element.attr("data-value");
    }
}
