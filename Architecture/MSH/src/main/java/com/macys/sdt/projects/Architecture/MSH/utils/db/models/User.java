package com.macys.sdt.projects.Architecture.MSH.utils.db.models;


public class User {


        private String userId;
        private Integer roleId;
        private String userName;
        private String dfltShipMethod;
        private String emailAddress;
        private String lastVisited;
        private String lastViewedCart;
        private String lastModified;
        private String created;
        private String disabled;
        private String accountLocked;
        private Integer failedLoginAttemptCounter;
        private Integer sqaFailedLoginAttempt;
        private Integer orderConfSqaCounter;
        private String userGuid;
        private Integer siteId;


        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDfltShipMethod() {
            return dfltShipMethod;
        }

        public void setDfltShipMethod(String dfltShipMethod) {
            this.dfltShipMethod = dfltShipMethod;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress;}


        public String getLastVisited() {
            return lastVisited;
        }

        public void setLastVisited(String lastVisited) {
            this.lastVisited = lastVisited;
        }

        public String getLastViewedCart() {
            return lastViewedCart;
        }

        public void setLastViewedCart(String lastViewedCart) {
            this.lastViewedCart = lastViewedCart;
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

        public String getDisabled() {
            return disabled;
        }

        public void setDisabled(String disabled) {
            this.disabled = disabled;
        }

        public Integer getFailedLoginAttemptCounter() {
            return failedLoginAttemptCounter;
        }

        public void setFailedLoginAttemptCounter(Integer failedLoginAttemptCounter) {
            this.failedLoginAttemptCounter = failedLoginAttemptCounter;
        }

        public Integer getSqaFailedLoginAttempt() {
            return sqaFailedLoginAttempt;
        }

        public void setSqaFailedLoginAttempt(Integer sqaFailedLoginAttempt) {
            this.sqaFailedLoginAttempt = sqaFailedLoginAttempt;
        }

        public Integer getOrderConfSqaCounter() {
            return orderConfSqaCounter;
        }

        public void setOrderConfSqaCounter(Integer orderConfSqaCounter) {
            this.orderConfSqaCounter = orderConfSqaCounter;
        }

        public String getUserGuid() {
            return userGuid;
        }

        public void setUserGuid(String userGuid) {
            this.userGuid = userGuid;
        }

        public String getAccountLocked() {
            return accountLocked;
        }

        public void setAccountLocked(String accountLocked) {
            this.accountLocked = accountLocked;
        }

        public Integer getSiteId() {
        return siteId;
    }

        public void setSiteId(Integer siteId) {
        this.siteId = siteId;
        }

        @Override
        public String toString() {
            return userId + "," + roleId.toString() + "," + userName + "," + dfltShipMethod + "," + emailAddress + "," + lastVisited + "," + lastViewedCart + "," + lastModified + "," + created + "," + disabled + "," + accountLocked + "," + failedLoginAttemptCounter.toString() + "," + sqaFailedLoginAttempt.toString() + "," + orderConfSqaCounter.toString() + "," + userGuid + "," + siteId.toString()+ ",";
        }


    }


