# Author: Claudiu Chirila
# Story: SDE-171 - OmniClient :: Launch Application
# Date Created:
# Date Signed Off:

Feature: As an associate, I want the ability to add and update customer information so that I can maintain my customer's desired contact or shipping information

  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate

  @domain_stores @omniclient @story_SDE-171 @website @bcom
  Scenario: Selling Associate edits customer information for a new added customer and adds/updates data
    And I add a new BLM Client: "NewClientBLM2" "UpdateData2" "TESTADDRESS" "TESTCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    Then Existing profile information should be displayed
      | Preferred Name |
      | Address(es)    |
      | Phone(s)       |
      | Email(s)       |
    And the Preferred information radio buttons should be preselected
    And the ADD button is displayed in address, phone number sections
    And the SAVE button from Customer Info tab MACYS and BLM is displayed
    And CANCEL button from Customer Info tab MACYS and BLM is displayed
    When click on ADD button from Address section BLM
    And we add additional address "Customer Address" "random" "12345" "AL" BLM
    And we click on ADD button from Phones section BLM
    And we add additional phone number "2223334444" BLM
    And we click on ADD button from Emails section BLM
    And we add additional email "bloomingdales@blm.com" BLM
    And we click on SAVE button from Customer Info tab BLM
    Then data is displayed for the Additional Information section BLM
      | CUSTOMER ADDRESS | ADDRESS |
      | RANDOM           | CITY    |
      | 12345            | ZIP     |
      | (223)            | PHONE   |
      | bloomingdales    | EMAIL   |

  @domain_stores @omniclient @story_SDE-171 @website @mcom
  Scenario: Selling associate clicks on cancel button and information popup is displayed
    And I add a new BLM Client: "NewClientBLM2" "UpdateData2" "TESTADDRESS" "TESTCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    And I click on the CANCEL button from CUSTOMER INFO section BLM
    Then I should see the information popup from CUSTOMER INFO section BLM
    When I click on the ok button on the popup from CUSTOMER INFO section
    Then the popup from CUSTOMER INFO section is no longer displayed

  @domain_stores @omniclient @story_SDE-171 @website @mcom
  Scenario: Selling associate changes the preferred information by selecting a different radio button
    And I add a new BLM Client: "NewClientBLM2" "UpdateData2" "TESTADDRESS" "TESTCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    And click on ADD button from Address section BLM
    And we add additional address "Customer Address" "random" "12345" "AL" BLM
    And I change the preferred address by selecting the radio button from the new added address BLM
    And we click on ADD button from Phones section BLM
    And we add additional phone number "2223334444" BLM
    And I change the preferred number by selecting the radio button from the new added number BLM
    And we click on ADD button from Emails section BLM
    And we add additional email "bloomingdales@blm.com" BLM
    And I change the preferred email by selecting the radio button from the new added email BLM
    When we click on SAVE button from Customer Info tab BLM
    Then the new preferred information is displayed BLM
      | CUSTOMER ADDRESS | ADDRESS |
      | RANDOM           | CITY    |
      | 12345            | ZIP     |
      | (223)            | PHONE   |
      | bloomingdales    | EMAIL   |


