package com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.website.my_account;


import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.htmlelements.annotations.FindBy;
import com.macys.sdt.projects.PurchaseAndDelivery.Common.pages.Page;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyAddressBook;

import java.util.HashMap;
import java.util.Map;


@FindBy(jsonPath = "my_address_book")
public class MyAddressBookPage extends Page {

    MyAddressBook nonCommonAddressBook;

    public MyAddressBookPage() {
        nonCommonAddressBook = new MyAddressBook();
    }

    public boolean isAddressAdded() {
        return MyAddressBook.isAddressAdded();
    }

    /**
     * Updates the address which is at the given index
     *
     * @param index index of address
     * @param opts  custom address options
     * @throws Throwable if any exception occurs
     */
    public void updateAddress(int index, Map<String, String> opts) throws Throwable {
        nonCommonAddressBook.updateAddress(index, new HashMap<>(opts));
    }

    /**
     * Adds address with the specified condition like "checkout_eligible is true"
     *
     * @param opts address options
     * @throws Throwable if any exception occurs
     */
    public void addAddress(Map<String, String> opts) throws Throwable {
        nonCommonAddressBook.addAddress(new HashMap<>(opts));
    }

    /**
     * Fills the given ProfileAddress on the given page
     *
     * @param addressObject Address to fill
     * @throws Throwable if any exception occurs
     */
    public void addressBookCommonFields(ProfileAddress addressObject) throws Throwable {
        nonCommonAddressBook.addressBookCommonFields(pageAlias(), addressObject);
    }

}
