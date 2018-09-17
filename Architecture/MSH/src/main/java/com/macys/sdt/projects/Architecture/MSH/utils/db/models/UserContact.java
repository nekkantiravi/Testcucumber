package com.macys.sdt.projects.Architecture.MSH.utils.db.models;


public class UserContact {

        private String userId;
        private Integer contactId;
        private String contactSeqNbr;
        private String contactNickname;
        private String dfltBillContact;
        private String dfltShipContact;
        private String lastModified;
        private String created;
        private String shipMethodCode;
        private Integer siteId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Integer getContactId() {
            return contactId;
        }

        public void setContactId(Integer contactId) {
            this.contactId = contactId;
        }

        public String getContactSeqNbr() {
            return contactSeqNbr;
        }

        public void setContactSeqNbr(String contactSeqNbr) {
            this.contactSeqNbr = contactSeqNbr;
        }

        public String getContactNickname() {
            return contactNickname;
        }

        public void setContactNickname(String contactNickname) {this.contactNickname = contactNickname;
        }

        public String getDfltBillContact() {
            return dfltBillContact;
        }
        public void setDfltBillContact(String dfltBillContact) {
            this.dfltBillContact = dfltBillContact;
        }

        public String getDfltShipContact() {
            return dfltShipContact;
        }

        public void setDfltShipContact(String dfltShipContact) {
            this.dfltShipContact = dfltShipContact;
        }

        public String getLastModified() {
            return lastModified;
        }

        public void setLastModified(String lastModified) {
            this.lastModified = lastModified;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getShipMethodCode() {
            return shipMethodCode;
        }

        public void setShipMethodCode(String shipMethodCode) {
            this.shipMethodCode = shipMethodCode;
        }

        public Integer getSiteId() {
            return siteId;
        }

        public void setSiteId(Integer siteId) {
            this.siteId = siteId;
        }

        @Override
        public String toString() {
            return userId + "," + contactId + "," + contactSeqNbr + "," + contactNickname + "," + dfltBillContact + "," + dfltShipContact + "," + lastModified + "," + created + "," + shipMethodCode + "," + siteId;
        }

    }




