# Author: Claudiu Chirila
# Story: SDE-171 - OmniClient :: Launch Application
# Date Created:
# Date Signed Off:

Feature: As an associate, I want the ability to add and update customer information so that I can maintain my
  customer's desired contact or shipping information

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate

  @domain_stores @omniclient @story_SDE-171 @website @mcom
  Scenario: Selling Associate edits customer information for a new added customer and addsupdates data
#parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "NewClientMACYs" "UpdateData1" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I navigate to Manage Client tab
    And I click on the Edit button from the CLIENT INFO section MACYS
    Then Existing profile information should be displayed
      | Preferred Name |
      | Address(es)    |
      | Phone(s)       |
    And the Preferred information radio buttons should be preselected
    And the ADD button is displayed in address, phone number sections
    And the SAVE button from Customer Info tab MACYS and BLM is displayed
    And CANCEL button from Customer Info tab MACYS and BLM is displayed
    When click on ADD button from Address section
    #parameters in this order: additional address, city, zip
    And we add additional address "Address 2" "City Random" "12345" "AL" MACYS
    And we click on ADD button from Phones section
    And we add additional phone number "2223334441" MACYS
    And we click on SAVE button from Client Info tab MACYS
    Then data is displayed for the Additional Information section
      | ADDRESS 2 | ADDRESS |
      | RANDOM    | CITY    |
      | 12345     | ZIP     |
      | (223)     | PHONE   |

  @domain_stores @omniclient @story_SDE-171 @website @mcom
  Scenario: Selling associate clicks on cancel button and information popup is displayed
    #parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "NewClientMACYs" "UpdateData1" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I navigate to Manage Client tab
    And I click on the Edit button from the CLIENT INFO section MACYS
    And I click on the CANCEL button from CLIENT INFO section
    Then I should see the information popup from CLIENT INFO section
    When I click on the ok button on the popup from CLIENT INFO section
    Then the popup from CLIENT INFO section is no longer displayed


  @domain_stores @omniclient @story_SDE-171 @website @mcom
  Scenario: Selling associate changes the preferred information by selecting a different radio button
    #parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "NewClientMACYs" "UpdateData1" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I navigate to Manage Client tab
    And I click on the Edit button from the CLIENT INFO section MACYS
    And click on ADD button from Address section
    And we add additional address "Address add1" "City Random" "12345" "AL" MACYS
    And I change the preferred address by selecting the radio button from the new added address
    And we click on ADD button from Phones section
    And we add additional phone number "2223334444" MACYS
    And I change the preferred number by selecting the radio button from the new added phone
    When we click on SAVE button from Client Info tab MACYS
    Then the new preferred information is displayed
      | ADDRESS ADD1 | ADDRESS |
      | CITY RANDOM  | CITY    |
      | 12345        | ZIP     |
      | (223)        | PHONE   |


