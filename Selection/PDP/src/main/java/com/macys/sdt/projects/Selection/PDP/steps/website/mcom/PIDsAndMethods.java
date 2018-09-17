package com.macys.sdt.projects.Selection.PDP.steps.website.mcom;

import static com.macys.sdt.framework.utils.StepUtils.macys;

/**
 * Created by m131418 on 6/6/17.
 */

public class PIDsAndMethods {

    static String[] memberProdID(){
        String[] products = {"22804", "22805", "77589", "78600"};
        return products;
    }

    static String[] masterProdID(){
        String[] products = {macys() ? "19942" : "596943"};
        return products;
    }

    static String[] iShipMemberProdID(){
        String[] products = {"22804", "22805", "78600"};
        return products;
    }

    static String[] otherMemberProdID(){
        String[] products = {"4085662", "2864777", "4085662", "4811007"};
        return products;
    }

    static String[] otherMasterProdID(){
        String[] products = {"657579", "4612085"};
        return products;
    }

    static String[] specialCharProdID(){
//        String[] products = {"2630944", "2684930", "242235", "2851448", "1474334", "605049", "2838932"};
        String[] products = {"2881071", "1898461", "1355960", "1669781", "2921180"};
        return products;
    }

    static String[] masterSpecialCharProdID(){
        String[] products = {"571822"};
        return products;
    }

    static String[] suppressedQAProdID(){
        String[] products = {"775933","4595564","4506837","4589777"};
        return products;
    }

    static String[] trueFitProdID(){
        String[] products = {"1438309","2765166","2351352","4644330","406144","3909873","984961"};
        return products;
    }

}
