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

import com.macys.sdt.projects.Customer.OESBB.utils.db.ArchivedMessage;

/**
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 * This ArchivedMessageRepository class offers methods and REST API to query data from Archived Message table 
 */

@Repository
public class ArchivedMessageRepository {

	@Autowired
	@Qualifier("archiveDB")
	public JdbcTemplate archiveDB;

	/*
	 * Purpose: This Method allows to get particular record set for particular mailID of ArchivedMessage table
	 * Input : long mailId
	 * Output : ArchivedMessage <Object>  
	 */
	public ArchivedMessage getArchivedMessageByMailID(long mailId) {
		final String query = "SELECT * FROM AMINTM.ARCHIVED_MESSAGE where MAIL_ID=?";
		ArchivedMessage archivedMessage = archiveDB.queryForObject(query, archivedMessageMapper, mailId);
		return archivedMessage;
	}

	private static final RowMapper<ArchivedMessage> archivedMessageMapper = new RowMapper<ArchivedMessage>() {
		public ArchivedMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
			ArchivedMessage archivedMessageDAO = new ArchivedMessage(rs.getInt("MAIL_ID"),
					rs.getString("MAIL_SOURCE_ID"), rs.getString("MAIL_SOURCE_SYS_ID"), rs.getString("BRAND_ID"),
					rs.getString("MAIL_TYP"), rs.getString("MAIL_SUB_TYP"), rs.getInt("CONTACT_ID"),
					rs.getInt("ORIG_MAIL_ID"), rs.getString("RESEND_USER_ID"), rs.getString("MAIL_SUBJECT"),
					rs.getBlob("MAIL_TXT"), rs.getBlob("MAIL_HTML"), rs.getBlob("MAIL_HARDCOPY_TXT"),
					rs.getDate("MSG_RECEIVED_TS"), rs.getDate("CREATE_TS"), rs.getDate("LAST_UPD_TS"),
					rs.getString("MAIL_DELIVERY_TYP"), rs.getBlob("ENHANCE_PAYLOAD"));
			return archivedMessageDAO;
		}

	};
}
