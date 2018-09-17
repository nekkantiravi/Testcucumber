package com.macys.sdt.projects.Architecture.MSH.utils.db.models;


public class Contact {
    private Integer contactId;
    private Integer addressId ;
    private String contactNamePfx;
    private String contactFirstName;
    private String contactMidName;
    private String contactLastName;
    private String contactNameSfx;
    private String contactEmail;
    private String dayPhone;
    private String nightPhone;
    private String mobilePhone;
    private String fax;
    private String lastModified;
    private String created;
    private String receiveNewsLetter;
    private String intlAddrId;
    private Integer siteId;

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getContactNamePfx() {
        return contactNamePfx;
    }

    public void setContactNamePfx(String contactNamePfx) {
        this.contactNamePfx = contactNamePfx;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {this.contactFirstName = contactFirstName;
    }

    public String getContactMidName() {
        return contactMidName;
    }
    public void setContactMidName(String contactMidName) {
        this.contactMidName = contactMidName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactNameSfx() {
        return contactNameSfx;
    }

    public void setContactNameSfx(String contactNameSfx) {
        this.contactNameSfx = contactNameSfx;
    }


    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDayPhone() {
        return dayPhone;
    }

    public void setDayPhone(String dayPhone) {
        this.dayPhone = dayPhone;
    }

    public String getNightPhone() {
        return nightPhone;
    }

    public void setNightPhone(String nightPhone) {
        this.nightPhone = nightPhone;
    }


    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getReceiveNewsLetter() {
        return receiveNewsLetter;
    }

    public void setReceiveNewsLetter(String receiveNewsLetter) {
        this.receiveNewsLetter = receiveNewsLetter;
    }

    public String getIntlAddrId() {
        return intlAddrId;
    }

    public void setIntlAddrId(String intlAddrId) {
        this.intlAddrId = intlAddrId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        return contactId + "," +  addressId + "," + contactNamePfx + "," + contactFirstName + "," + contactMidName + "," + contactLastName + "," + contactNameSfx+ ","+ contactEmail+ "," + dayPhone + "," + nightPhone + "," + mobilePhone + "," + fax + "," + lastModified + "," + created + "," + receiveNewsLetter + "," + intlAddrId + "," + siteId;
    }

}
