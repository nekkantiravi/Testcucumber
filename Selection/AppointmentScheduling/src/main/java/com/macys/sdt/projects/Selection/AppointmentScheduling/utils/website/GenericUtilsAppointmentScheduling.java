package com.macys.sdt.projects.Selection.AppointmentScheduling.utils.website;

import com.macys.sdt.framework.utils.Cookies;
import com.macys.sdt.framework.utils.StepUtils;

/**
 * Created by yh02207 on 1/12/2017.
 */
public class GenericUtilsAppointmentScheduling extends StepUtils {

    public static void getGeoCookie() {
        String geoCookie = "USERPC1_92_945363_87_USERLL1_92_37.5710,-121.98583_87_USERST1_92_CA3_87_USERDMA1_92_8073_87_DT1_92_PC3_87_DSW1_92_2803_87_DSH1_92_1753_87_DBN1_92_Chrome3_87_DMN1_92_55";
        Cookies.addCookie("MISCGCs", geoCookie);
        Cookies.printCookie("MISCGCs");

    }
}
