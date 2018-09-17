/**
 * 
 */
package com.macys.sdt.projects.Customer.OESBB.utils.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.macys.sdt.projects.Customer.OESBB.utils.db.Message;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 * This MessageRepository class offers methods and REST API to query data from Archived Message table 
 */

@Repository
public class MessageRepository {

	/**
	 * 
	 */
	public MessageRepository() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	@Qualifier("payloadDB")
	public JdbcTemplate payloadDB;

	/*
	 * Purpose: This Method allows to get particular record set for particular mailSourceID of Message table
	 * Input : String mailSourceID
	 * Output : Message <Object>  
	 */
	@Transactional
	public Message getMessageBySourceMailID(String mailSourceID) {
	    String query = "SELECT * FROM WMINTM.MESSAGE where MAIL_SOURCE_ID=? and ROWNUM < 2";
	    Message message = payloadDB.queryForObject(query, messageMapper, mailSourceID);
	    return message;
	}

	@Transactional
	public Message getMessageBySourceMailID(String mailSourceID,String deliveryType ) {
	    String query = "SELECT * FROM WMINTM.MESSAGE where MAIL_SOURCE_ID=? and MAIL_DELIVERY_TYP=? and ROWNUM < 2";
	    Message message = payloadDB.queryForObject(query, messageMapper, mailSourceID,deliveryType);
	    return message;
	}

	private static final RowMapper<Message> messageMapper = new RowMapper<Message>() {
		public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
			Message messageDAO = new Message(rs.getLong("MAIL_ID"), rs.getString("MAIL_SOURCE_ID"),
					rs.getString("MAIL_SOURCE_SYS_ID"), rs.getString("BRAND_ID"), rs.getString("MAIL_TYP"),
					rs.getString("MAIL_SUB_TYP"), rs.getLong("CONTACT_ID"), rs.getString("ORIG_MAIL_ID"),
					rs.getString("RESEND_USER_ID"), rs.getString("MSG_STAT_CD"), rs.getBlob("ORIG_MSG_TXT"),
					rs.getDate("CREATE_TS"), rs.getDate("LAST_UPD_TS"), rs.getString("MAIL_DELIVERY_TYP"),
					rs.getLong("VERSION_NBR"), rs.getString("MSG_STAT_DETAIL"), rs.getString("MAIL_DELIVERY_TYP"),
					rs.getString("TRACKING_ID"));
			return messageDAO;
		}

	};

}
