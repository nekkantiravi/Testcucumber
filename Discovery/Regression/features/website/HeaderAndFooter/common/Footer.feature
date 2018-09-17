# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verification of BCOM & MCOM footer elements in DOMESTIC, ISHIP and REGISTRY modes

  #Test Case ID: MCOM-92112 BLCOM-80274
  #Testlink-BLCOM-84273
  #vone-RT-06320
  @use_regression @priority_high @domain_discovery @mode_iship
  Scenario: Footer - International shopper should not be able to see some of the links in ISHIP mode
    Given I visit the web site as a guest user
    Then I verify the footer links in "Domestic" Mode
    When I navigate to international context page
    When I change country to "Australia"
    Then I verify the footer links in "Iship" Mode
      # Notes:
      #    BCOM Footer links in Domestic Mode:
      #    And in the footer I should see "Customerservice"
      #    And in the footer I should see "Orderstatus"
      #    And in the footer I should see "CreditServices"
      #    And in the footer I should see "PayBillOnline"
      #    And in the footer I should see "Loyallist"
      #    And in the footer I should see "AboutUs"
      #    And in the footer I should see "Careers"
      #    And in the footer I should see "Emailsignup"
      #    And in the Header I should see "Sign In" link
      #    BCOM Footer links in iship Mode:
      #    And in the footer I should see "Customerservice"
      #    And in the footer I should see "Orderstatus"
      #    And in the footer I should see "InternationalShopping"
      #    And in the footer I should see "AboutUs"
      #    And in the footer I should see "Careers"
      #    And in the Header I should not see "Sign In" link
      #    And in the Header I should not see "My Account link" link
      #    And in the footer I should not see "CreditServices"
      #    And in the footer I should not see "PayBillOnline"
      #    And in the footer I should not see "Loyallist"
      #    MCOM Footer links in Domestic Mode:
      #    And in the footer I should see "Macys Credit Card"
      #    And in the footer I should see "Pay bill Online"
      #    And in the footer I should see "apply now & Save"
      #    And in the footer I should see "Locations & hours"
      #    And in the Header I should see "sign in" link
      #    MCOM Footer links in Iship Mode:
      #    And in the footer I should not see "Macys Credit Card"
      #    And in the footer I should not see "Pay bill Online"
      #    And in the footer I should not see "apply now & Save"
      #    And in the footer I should not see "Locations & hours"
      #    And in the Header I should not see "sign in" link
      #    And in the Header I should not see "my account" link
      #    And in the footer I should see "frequently asked questions"
      #    And in the footer I should see "find a store"
      #    And in the footer I should see "visitors services"

