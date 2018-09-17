package com.macys.sdt.projects.Architecture.MSH.utils.db.models;

import java.util.ArrayList;

/**
 * Created by yc04987 on 5/10/2017.
 */
public class ModVerification {

    public static boolean checkModValue(String sitevalue, ArrayList<String> pk)
    {
        int modvalue;
        boolean isModCompliant;
        if(sitevalue.equals("DAL"))
        {
            modvalue = 1;
            isModCompliant = parseThroughPK(modvalue, pk);
        }
        else
        {
            modvalue = 0;
            isModCompliant = parseThroughPK(modvalue, pk);
        }
        return isModCompliant;
    }

    private static boolean parseThroughPK(int mod, ArrayList<String> pk)
    {
        for (String key: pk)
        {
            if(Long.parseLong(key)%3!=mod)
                return false;
        }
        return true;
    }

}
