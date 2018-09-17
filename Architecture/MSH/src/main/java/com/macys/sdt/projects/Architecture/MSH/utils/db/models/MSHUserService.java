package com.macys.sdt.projects.Architecture.MSH.utils.db.models;


    import com.macys.sdt.framework.utils.Utils;
    import org.codehaus.jackson.map.ObjectMapper;
    import com.macys.sdt.framework.utils.db.utils.DBUtils;
    import org.json.JSONObject;


    import java.sql.*;
    import java.util.ArrayList;


public class MSHUserService {
    public Statement statement;
    public Connection connection;
    public JSONObject queries;

    /*
# Columns

        # User table
        # -------
        # userId
        # roleId
        # userName
        # dfltShipMethod
        # PASSWORD
        # emailAddress
        # lastVisited
        # lastViewedCart
        # lastModified
        # created
        # disabled
        # accountLocked
        # failedLoginAttemptCounter
        # FAILED_LOGIN_ATTEMPT
        # ACCOUNT_SQA_LOCKED
        # SQA_FAILED_LOGIN_ATTEMPT_COUNTER
        # sqaFailedLoginAttempt
        # orderConfSqaCounter
        # userGuid
        # siteId

*/

    /*
     # User Contact table
        # -------
        # userId
        # contactId
        # contactSeqNbr
        # contactNickname
        # dfltBillContact
        # dfltShipContact
        # lastModified
        # created
        # shipMethodCode
        # siteId
    */

     /*
     # User profile table
        # userId
        # firstName
        # midName
        # lastName
        # gender
        # hasChildren
        # dateOfBirth
        # receiveNewsletter
        # liveNearMacys
        # buyHomeInNext12mos
        # takeVacationInNext12mos
        # haveBabyInNext12mos
        # getMarriedInNext12mos
        # likeAllCategories
        # likeAtHomeCat
        # likeWomenCat
        # likeMenCat
        # likeKidsCat
        # likeTeensCat
        # likeYoungAttitudeCat
        # likeJewelryCat
        # likeBeautyCat
        # likeMaternityCat
        # likeSpecialEventCat
        # likeGiftsCat
        # likeTrendCat
        # likeBoutiquesCat
        # age_Group
        # postalCode
        # salaryRange
        # receiveDivisionNewsletter
        # created
        # lastModified
        # registrationTypeId
        # bestContactNumber
        # registrationSource
        # addressLine1
        # addressLine2
        # city
        # stateAbbrev
        # goGreen
        # siteId
    */


    /**
     * Method to return user details from DB for a given user id
     *
     * @return orderNote
     **/
    public ArrayList getUserdata(String userId) throws Throwable {

        ArrayList returnElement = new ArrayList();
        ArrayList primaryKeysList = new ArrayList();

        setupConnection();

        queries = Utils.getSqlQueries();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queries.getJSONObject("user_service").getString("user").toString().replaceFirst("'\\?'", "'" + userId + "'"));
            PreparedStatement preparedStatementusercontact = connection.prepareStatement(queries.getJSONObject("user_service").getString("user_contact").toString().replaceFirst("'\\?'", "'" + userId + "'"));
            PreparedStatement preparedStatementprofile = connection.prepareStatement(queries.getJSONObject("user_service").getString("user_profile").toString().replaceFirst("'\\?'", "'" + userId + "'"));
           PreparedStatement preparedStatementcontact = connection.prepareStatement(queries.getJSONObject("user_service").getString("contact").toString().replaceFirst("'\\?'", "'" + userId + "'"));
            PreparedStatement preparedStatementuserpassword = connection.prepareStatement(queries.getJSONObject("user_service").getString("user_password_hint").toString().replaceFirst("'\\?'", "'" + userId + "'"));
            PreparedStatement preparedStatementaddress = connection.prepareStatement(queries.getJSONObject("user_service").getString("address").toString().replaceFirst("'\\?'", "'" + userId + "'"));

            ResultSet user_resultSet = preparedStatement.executeQuery();
            User user = null;
            while (user_resultSet.next()) {
                user = new User();
                user.setUserId(user_resultSet.getString("USER_ID"));
                user.setRoleId(user_resultSet.getInt("ROLE_ID"));
                user.setUserName(user_resultSet.getString("USER_NAME"));
                user.setDfltShipMethod(user_resultSet.getString("DFLT_SHIP_METHOD"));
                user.setEmailAddress(user_resultSet.getString("EMAIL_ADDRESS"));
                user.setLastVisited(user_resultSet.getString("LAST_VISITED"));
                user.setLastViewedCart(user_resultSet.getString("LAST_VIEWED_CART"));
                user.setLastModified(user_resultSet.getString("LAST_MODIFIED"));
                user.setCreated(user_resultSet.getString("CREATED"));
                user.setDisabled(user_resultSet.getString("DISABLED"));
                user.setAccountLocked(user_resultSet.getString("ACCOUNT_LOCKED"));
                user.setFailedLoginAttemptCounter(user_resultSet.getInt("FAILED_LOGIN_ATTEMPT_COUNTER"));
                user.setSqaFailedLoginAttempt(user_resultSet.getInt("SQA_FAILED_LOGIN_ATTEMPT"));
                user.setOrderConfSqaCounter(user_resultSet.getInt("ORDER_CONF_SQA_COUNTER"));
                user.setUserGuid(user_resultSet.getString("USER_GUID"));
                user.setSiteId(user_resultSet.getInt("SITE_ID"));
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(user);
                System.out.println("userdata in getUserdata method is-------" + jsonInString);
            }
            if (user != null) {
                returnElement.add(user);
                primaryKeysList.add(user.getUserId());
            }


            ResultSet usercontact_resultSet = preparedStatementusercontact.executeQuery();
            UserContact usercontact = null;
            while (usercontact_resultSet.next()) {
                usercontact = new UserContact();
                usercontact.setUserId(usercontact_resultSet.getString("USER_ID"));
                usercontact.setContactId(usercontact_resultSet.getInt("CONTACT_ID"));
                usercontact.setContactSeqNbr(usercontact_resultSet.getString("CONTACT_SEQ_NBR"));
                usercontact.setContactNickname(usercontact_resultSet.getString("CONTACT_NICKNAME"));
                usercontact.setDfltBillContact(usercontact_resultSet.getString("DFLT_BILL_CONTACT"));
                usercontact.setDfltShipContact(usercontact_resultSet.getString("DFLT_SHIP_CONTACT"));
                usercontact.setLastModified(usercontact_resultSet.getString("LAST_MODIFIED"));
                usercontact.setCreated(usercontact_resultSet.getString("CREATED"));
                usercontact.setShipMethodCode(usercontact_resultSet.getString("SHIP_METHOD_CODE"));
                usercontact.setSiteId(usercontact_resultSet.getInt("SITE_ID"));
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(usercontact);
                System.out.println("usercontactdata in getUserData method is-------" + jsonInString);

            }
            if (usercontact != null) {
                returnElement.add(usercontact);
                primaryKeysList.add(usercontact.getContactId());
            }

            ResultSet profile_resultSet = preparedStatementprofile.executeQuery();
            UserProfile profile = null;
            while (profile_resultSet.next()) {
                profile =new UserProfile();
                profile.setUserId(profile_resultSet.getString("USER_ID"));
                profile.setFirstName(profile_resultSet.getString("FIRST_NAME"));
                profile.setMidName(profile_resultSet.getString("MID_NAME"));
                profile.setLastName(profile_resultSet.getString("LAST_NAME"));
                profile.setGender(profile_resultSet.getString("GENDER"));
                profile.setHasChildren(profile_resultSet.getString("HAS_CHILDREN"));
                profile.setDateOfBirth(profile_resultSet.getString("DATE_OF_BIRTH"));
                profile.setReceiveNewsletter(profile_resultSet.getString("RECEIVE_NEWSLETTER"));
                profile.setLiveNearMacys(profile_resultSet.getString("LIVE_NEAR_MACYS"));
                profile.setBuyHomeInNext12mos(profile_resultSet.getString("BUY_HOME_IN_NEXT_12_MOS"));
                profile.setTakeVacationInNext12mos(profile_resultSet.getString("TAKE_VACATION_IN_NEXT_12_MOS"));
                profile.setHaveBabyInNext12mos(profile_resultSet.getString("HAVE_BABY_IN_NEXT_12_MOS"));
                profile.setGetMarriedInNext12mos(profile_resultSet.getString("GET_MARRIED_IN_NEXT_12_MOS"));
                profile.setLikeAllCategories(profile_resultSet.getString("LIKE_ALL_CATEGORIES"));
                profile.setLikeAtHomeCat(profile_resultSet.getString("LIKE_AT_HOME_CAT"));
                profile.setLikeWomenCat(profile_resultSet.getString("LIKE_WOMEN_CAT"));
                profile.setLikeMenCat(profile_resultSet.getString("LIKE_MEN_CAT"));
                profile.setLikeKidsCat(profile_resultSet.getString("LIKE_KIDS_CAT"));
                profile.setLikeTeensCat(profile_resultSet.getString("LIKE_TEENS_CAT"));
                profile.setLikeYoungAttitudeCat(profile_resultSet.getString("LIKE_YOUNG_ATTITUDE_CAT"));
                profile.setLikeJewelryCat(profile_resultSet.getString("LIKE_JEWELRY_CAT"));
                profile.setLikeBeautyCat(profile_resultSet.getString("LIKE_BEAUTY_CAT"));
                profile.setLikeMaternityCat(profile_resultSet.getString("LIKE_MATERNITY_CAT"));
                profile.setLikeSpecialEventCat(profile_resultSet.getString("LIKE_SPECIAL_EVENTS_CAT"));
                profile.setLikeGiftsCat(profile_resultSet.getString("LIKE_GIFTS_CAT"));
                profile.setLikeTrendCat(profile_resultSet.getString("LIKE_TREND_CAT"));
                profile.setLikeBoutiquesCat(profile_resultSet.getString("LIKE_BOUTIQUES_CAT"));
                profile.setAge_Group(profile_resultSet.getString("AGE_GROUP"));
                profile.setPostalCode(profile_resultSet.getString("POSTAL_CODE"));
                profile.setSalaryRange(profile_resultSet.getString("SALARY_RANGE"));
                profile.setReceiveDivisionNewsletter(profile_resultSet.getString("RECEIVE_DIVISION_NEWSLETTER"));
                profile.setCreated(profile_resultSet.getString("CREATED"));
                profile.setLastModified(profile_resultSet.getString("LAST_MODIFIED"));
                profile.setRegistrationTypeId(profile_resultSet.getString("REGISTRATION_TYPE_ID"));
                profile.setBestContactNumber(profile_resultSet.getString("BEST_CONTACT_NUMBER"));
                profile.setRegistrationSource(profile_resultSet.getString("REGISTRATION_SOURCE"));
                profile.setAddressLine1(profile_resultSet.getString("ADDRESS_LINE_1"));
                profile.setAddressLine2(profile_resultSet.getString("ADDRESS_LINE_2"));
                profile.setCity(profile_resultSet.getString("CITY"));
                profile.setStateAbbrev(profile_resultSet.getString("STATE_ABBREV"));
                profile.setGoGreen(profile_resultSet.getString("GOGREEN"));
                profile.setSiteId(profile_resultSet.getString("SITE_ID"));
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(profile);
                System.out.println("userprofiledata in getUserData method is-------" + jsonInString);

            }
            if (profile != null) {
                returnElement.add(profile);
            }

            ResultSet contact_resultSet = preparedStatementcontact.executeQuery();
            Contact contact = null;
            while (contact_resultSet.next()) {
                contact = new Contact();
                contact.setContactId(contact_resultSet.getInt("CONTACT_ID"));
                contact.setAddressId(contact_resultSet.getInt("ADDRESS_ID"));
                contact.setContactNamePfx(contact_resultSet.getString("CONTACT_NAME_PFX"));
                contact.setContactFirstName(contact_resultSet.getString("CONTACT_FIRST_NAME"));
                contact.setContactMidName(contact_resultSet.getString("CONTACT_MID_NAME"));
                contact.setContactLastName(contact_resultSet.getString("CONTACT_LAST_NAME"));
                contact.setContactNameSfx(contact_resultSet.getString("CONTACT_NAME_SFX"));
                contact.setContactEmail(contact_resultSet.getString("CONTACT_EMAIL"));
                contact.setDayPhone(contact_resultSet.getString("DAY_PHONE"));
                contact.setNightPhone(contact_resultSet.getString("NIGHT_PHONE"));
                contact.setMobilePhone(contact_resultSet.getString("MOBILE_PHONE"));
                contact.setFax(contact_resultSet.getString("FAX"));
                contact.setLastModified(contact_resultSet.getString("LAST_MODIFIED"));
                contact.setCreated(contact_resultSet.getString("CREATED"));
                contact.setReceiveNewsLetter(contact_resultSet.getString("RECEIVE_NEWSLETTER"));
                contact.setIntlAddrId(contact_resultSet.getString("INTL_ADDR_ID"));
                contact.setSiteId(contact_resultSet.getInt("SITE_ID"));

                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(contact);
                System.out.println("contactdata in getUserData method is-------" + jsonInString);

            }
            if (contact != null) {
                returnElement.add(contact);
            }


            ResultSet user_password_hint_resultSet = preparedStatementuserpassword.executeQuery();
            UserPwd userpwd = null;
            while (user_password_hint_resultSet.next()) {
                userpwd = new UserPwd();
                userpwd.setUserId(user_password_hint_resultSet.getString("USER_ID"));
                userpwd.setHintId(user_password_hint_resultSet.getInt("HINT_ID"));
                userpwd.setHintAnswer(user_password_hint_resultSet.getString("HINT_ANSWER"));
                userpwd.setLastModified(user_password_hint_resultSet.getString("LAST_MODIFIED"));
                userpwd.setCreated(user_password_hint_resultSet.getString("CREATED"));
                userpwd.setSiteId(user_password_hint_resultSet.getInt("SITE_ID"));
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(userpwd);
                System.out.println("userpasswordhint data in getUserData method is-------" + jsonInString);


            }
            if (userpwd != null) {
                returnElement.add(userpwd);
            }
            ResultSet address_resultSet = preparedStatementaddress.executeQuery();
            Address address = null;
            while (address_resultSet.next()) {
                address = new Address();
                address.setAddressId(address_resultSet.getInt("ADDRESS_ID"));
                address.setAddressLine1(address_resultSet.getString("ADDRESS_LINE_1"));
                address.setAddressLine2(address_resultSet.getString("ADDRESS_LINE_2"));
                address.setAddressLine3(address_resultSet.getString("ADDRESS_LINE_3"));
                address.setCity(address_resultSet.getString("CITY"));
                address.setStateAbbrev(address_resultSet.getString("STATE_ABBREV"));
                address.setPostalCode(address_resultSet.getString("POSTAL_CODE"));
                address.setCountryCode(address_resultSet.getString("COUNTRY_CODE"));
                address.setLastModified(address_resultSet.getString("LAST_MODIFIED"));
                address.setCreated(address_resultSet.getString("CREATED"));
                address.setAttention(address_resultSet.getString("ATTENTION"));
                address.setSiteId(address_resultSet.getInt("SITE_ID"));ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(address);
                System.out.println("address data in getUserData method is-------" + jsonInString);

            }
            if (address != null) {
                returnElement.add(address);
                primaryKeysList.add(address.getAddressId());
            }





        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnElement;
    }

    private void setupConnection () {
        if (statement == null) {
            try {
                connection = DBUtils.setupDBConnection();
                statement = connection.createStatement();
            } catch (Exception e) {
                System.out.println("Error occurs while creating database connection" + e.getMessage());
            }
        }
    }
    }




