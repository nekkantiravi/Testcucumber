DELETE from SUBS_PROD_AVAILABILITY;
DELETE from USER_ENTL_ORDER_HISTORY;
DELETE from USER_ENTITLEMENT;
DELETE from ENTITLEMENT_TYPE;

INSERT INTO ENTITLEMENT_TYPE (ENTITLEMENT_TYPE_UUID,ENTITLEMENT_TYPE,CREATED,LAST_ROW_CHG_TS,CREATED_BY,MODIFIED_BY)
VALUES
(1,'BeautyBox',now(),now(),'System','System');

INSERT INTO USER_ENTITLEMENT
(USER_ENTITLEMENT_UUID, USER_GUID, ENTITLEMENT_TYPE_UUID, USER_ENTL_UPC, USER_ENTL_PRODUCT_ID, USER_ENTL_QTY,
USER_ENTL_TYPE, USER_ENTL_DURATION, USER_ENTL_FREQUENCY, USER_ENTL_START_DT, USER_ENTL_END_DT, PAYMENT_CARD_ID, PAYMENT_TYPE_CODE, SHIPPING_CONTACT_ID, USER_ENTL_STATUS, CREATED, LAST_ROW_CHG_TS, CREATED_BY, MODIFIED_BY)
VALUES
('abc','22805','1',10,1,1,'Monthly',-1,1,now(),now(),1,'V',1,'SUBSCRIBED', now(),now(),'System','System');

INSERT INTO USER_ENTITLEMENT
(USER_ENTITLEMENT_UUID, USER_GUID, ENTITLEMENT_TYPE_UUID, USER_ENTL_UPC, USER_ENTL_PRODUCT_ID, USER_ENTL_QTY,
USER_ENTL_TYPE, USER_ENTL_DURATION, USER_ENTL_FREQUENCY, USER_ENTL_START_DT, USER_ENTL_END_DT, PAYMENT_CARD_ID, PAYMENT_TYPE_CODE, SHIPPING_CONTACT_ID, USER_ENTL_STATUS, CREATED, LAST_ROW_CHG_TS, CREATED_BY, MODIFIED_BY)
VALUES
('def','1494','1',10,1,1,'Monthly',-1,1,now(),now(),1,'V',1,'CANCELLED', now(),now(),'System','System');

INSERT INTO USER_ENTITLEMENT
(USER_ENTITLEMENT_UUID, USER_GUID, ENTITLEMENT_TYPE_UUID, USER_ENTL_UPC, USER_ENTL_PRODUCT_ID, USER_ENTL_QTY,
USER_ENTL_TYPE, USER_ENTL_DURATION, USER_ENTL_FREQUENCY, USER_ENTL_START_DT, USER_ENTL_END_DT, PAYMENT_CARD_ID, PAYMENT_TYPE_CODE, SHIPPING_CONTACT_ID, USER_ENTL_STATUS, CREATED, LAST_ROW_CHG_TS, CREATED_BY, MODIFIED_BY)
VALUES
('ghi','3306','1',10,1,1,'Monthly',-1,1,now(),now(),1,'V',1,'WAITLIST', now(),now(),'System','System');

INSERT INTO USER_ENTL_ORDER_HISTORY(ORDER_HISTORY_UUID,USER_ENTITLEMENT_UUID,ORDER_NUMBER,SUBS_ORDER_UUID,ORDER_GUID,SUBS_ORDER_STATUS,CREATED,LAST_ROW_CHG_TS,CREATED_BY,MODIFIED_BY)
VALUES('187RTWE','abc','BAC3322','BAC3322','BEC3322','DELIVERED',now(),now(),'System','System');

INSERT INTO USER_ENTL_ORDER_HISTORY(ORDER_HISTORY_UUID,USER_ENTITLEMENT_UUID,ORDER_NUMBER,SUBS_ORDER_UUID,ORDER_GUID,SUBS_ORDER_STATUS,CREATED,LAST_ROW_CHG_TS,CREATED_BY,MODIFIED_BY)
VALUES('187RTFE','abc','BAC4322','BACU322','BOC3322','DELIVERED',now(),now(),'System','System');

insert into SUBS_PROD_AVAILABILITY
(SUBS_PROD_AVAILABILITY_UUID,ENTITLEMENT_TYPE_UUID,SUBS_UPC,SUBS_PRODUCT_ID,SUBS_AVAILABLE_MAX_QTY,SUBS_AVAILABILITY_EFFECTIVE_START_DT,SUBS_AVAILABILITY_EFFECTIVE_END_DT,IS_EDITABLE,CREATED,LAST_ROW_CHG_TS,CREATED_BY,MODIFIED_BY) values
(1,1,123,345,5,'2017-01-01 00:00:00','2017-01-31 23:59:59','Y',now(),now(),'System','System');

insert into SUBS_PROD_AVAILABILITY
(SUBS_PROD_AVAILABILITY_UUID,ENTITLEMENT_TYPE_UUID,SUBS_UPC,SUBS_PRODUCT_ID,SUBS_AVAILABLE_MAX_QTY,SUBS_AVAILABILITY_EFFECTIVE_START_DT,SUBS_AVAILABILITY_EFFECTIVE_END_DT,IS_EDITABLE,CREATED,LAST_ROW_CHG_TS,CREATED_BY,MODIFIED_BY) values
(2,1,123,345,30000,'2017-02-01 00:00:00','2017-02-28 23:59:59','Y',now(),now(),'System','System');