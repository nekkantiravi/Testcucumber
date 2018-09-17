package com.macys.sdt.projects.Selection.List.steps.website;

import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.Utils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

public class GenericUtils extends StepUtils {

    /**
     * Method to add or update FST cookie
     **/
    public static void setSegmentFSTCookie(String FSTcookie) {
        String segmentCookie = Cookies.getCookieValue("SEGMENT");
        String cookies[] = {"1490", "1614", "1615", "1616", "1489"};
        boolean isSegmentCookiePresent = Arrays.asList(cookies).contains(segmentCookie);
        if (isSegmentCookiePresent) {
            if (!segmentCookie.contains(FSTcookie)) {
                Cookies.removeSegment(segmentCookie);
                Cookies.addSegment(FSTcookie);
                System.out.println("FST cookie " + segmentCookie + " replaced with " + FSTcookie);
            } else {
                System.out.println("No need to update FST cookie as it already exists");

            }

        } else if (!isSegmentCookiePresent && !(segmentCookie.equals("") || segmentCookie == null)) {
            Cookies.addSegment(FSTcookie);
            System.out.println(Cookies.getCookieValue("SEGMENT"));
            System.out.println("FST cookie " + FSTcookie + " is added as no FST cookie value found ");

        } else if (!isSegmentCookiePresent && (segmentCookie.equals("") || segmentCookie == null)) {
            Cookies.addCookie("SEGMENT", FSTcookie);
            System.out.println("Segment cookie with " + FSTcookie + " value is added");
        }
    }


}
