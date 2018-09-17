package com.macys.sdt.projects.Customer.MyAccountRedesign.actions.website;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.macys.sdt.framework.model.user.UslInfo;
import com.macys.sdt.framework.utils.Utils;
import org.junit.Assert;

import java.io.File;
import java.util.List;

import static com.macys.sdt.framework.utils.Utils.getResourceFile;

/**
 * Created by yc03ds3 on 11/10/2016.
 */
public class FusionCitiPage {
    public UslInfo linkCardToProfile() throws Exception{
        /*
        Here we should get credit card details from valid_cards.json and fill on fusion page.
         */
        try {
            File uslFile = getResourceFile("enrolled_usl_id.json");
            String jsonTxt = Utils.readTextFile(uslFile);
            //JSON from file to Object
            List<UslInfo> uslInfoList = new ObjectMapper().readValue(jsonTxt,
                    TypeFactory.defaultInstance().constructCollectionType(List.class,
                            UslInfo.class));
            return uslInfoList.get(0);
        } catch (Exception e) {
            Assert.fail("Unable to parse JSON: " + e);
            return null;
        }
    }
}
