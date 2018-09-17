
DELETE from SUBS_PROD_AVAILABILITY;
DELETE from USER_ENTL_ORDER_HISTORY;
DELETE from user_entl_status_history;
DELETE from user_entl_notif_history;
DELETE from USER_ENTITLEMENT;


update entitlement_configuration set attr_value = '21' where ATTR_NAME='subscribedBy';


update entitlement_configuration set attr_value = '-1' where ATTR_NAME='waitlist.percentage';
