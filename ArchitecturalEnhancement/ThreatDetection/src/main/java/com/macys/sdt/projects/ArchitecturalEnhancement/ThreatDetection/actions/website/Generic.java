package com.macys.sdt.projects.ArchitecturalEnhancement.ThreatDetection.actions.website;


import com.macys.sdt.framework.utils.CreditCards;
import com.macys.sdt.shared.actions.website.mcom.pages.my_account.MyWallet;
import com.macys.sdt.shared.steps.website.MyAccountSteps;
import com.macys.sdt.shared.steps.website.MyAddressBookSteps;

public class Generic {

    public static MyAccountSteps myAccountSteps = new MyAccountSteps();
    public static MyAddressBookSteps myAddressBookSteps = new MyAddressBookSteps();

    public static void addCreditToWallet(String cardType) throws Throwable{
        myAccountSteps.iNavigateToMyWalletPageFromMyAccountPage();
        MyWallet.openAddCard();
        MyWallet.addCard(CreditCards.getValidCard(cardType));
    }

    public static void addAddressToProfile(int add) throws Throwable{
        myAddressBookSteps.iAddAddressesToMyAddressBook(add);
    }


}
