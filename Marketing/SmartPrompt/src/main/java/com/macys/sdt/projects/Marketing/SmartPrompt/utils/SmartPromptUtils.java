package com.macys.sdt.projects.Marketing.SmartPrompt.utils;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBConnection;
import com.macys.sdt.framework.utils.db.utils.DBUtils;
import com.macys.sdt.shared.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class SmartPromptUtils {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static void setUserAsPrompt(String user_id,boolean marketingMailsStatus){

        String sql;
        Connection con = DBUtils.setupDBConnection();
        if(marketingMailsStatus) {
            sql = Utils.getSqlQueries().getJSONObject("user_service").getString("add_smartprompt_user_yes");
        }else{
            sql = Utils.getSqlQueries().getJSONObject("user_service").getString("add_smartprompt_user_no");
        }
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {e.printStackTrace();}
    }


}
