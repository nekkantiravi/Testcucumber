package com.macys.sdt.projects.Customer.MensWearHouse.utils.db;

import com.macys.sdt.framework.utils.Utils;
import com.macys.sdt.framework.utils.db.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by vjayanthi on 3/24/2017.
 */
public class TuxService {
    public static void changeEventDateToCurrentDate(String reservationId){
        Connection con = DBUtils.setupDBConnection();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate = df.format(Calendar.getInstance().getTime());
        String sql = Utils.getSqlQueries().getJSONObject("tux_service").getString("update_event_date");
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql.replaceFirst("\\?", reportDate));
            preparedStatement.setString(1, reservationId);
            preparedStatement.executeUpdate();
        }catch (Exception e){e.printStackTrace();}
    }
}
