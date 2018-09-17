package com.macys.sdt.projects.Marketing.SEOImprovements.actions.website;

import com.macys.sdt.framework.interactions.Navigate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SEO {

    public List<String> getTopProductsList(){
        List<String> productsList = new ArrayList<>();
        Pattern p = Pattern.compile("(, topProducts=)(\\d+),(\\d+),(\\d+),(\\d+),(\\d+),(\\d+), ");
        Matcher m = p.matcher(Navigate.execJavascript("return MACYS.brightTag").toString());
        int index = 2;
        if (m.find()) {
            while (index < 8) {
                productsList.add(m.group(index++));
            }
        }
        return productsList;
    }
}
