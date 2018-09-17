# Author: Claudiu Chirila
# Story: SDE-175 - Edit Client Profile: Phone and Address Validation Checks
# Date Created: 06/30/2017
# Date Signed Off:

Feature: As an associate, I want the information I add/edit on a Client/Customer profile to be validated
  so that I can ensure I am adding real/accurate information and also not missing any required information.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate

  @domain_stores @omniclient @story_SDE-175 @website @mcom
  Scenario: In order to save a new or edited address; I must have entered info in all required fields:
  Address Line 1, City, State, Zip Code, Country and Address Type
        #parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "NewClientMACYsTest" "UpdateData1Test" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I navigate to Manage Client tab
    And I click on the Edit button from the CLIENT INFO section MACYS
    When click on ADD button from Address section
    And I add "address 1" in Street Address 1 field
    And we click on SAVE button from Client Info tab MACYS
    Then I should see the error popup from CLIENT INFO section MACYS
    And I click on the OK button on the error popup from CLIENT INFO section MACYS
    When I add "address 1" in Street Address 1 field
    And I add city "city 1" in the City input field
    And we click on SAVE button from Client Info tab MACYS
    Then I should see the error popup from CLIENT INFO section MACYS
    And I click on the OK button on the error popup from CLIENT INFO section MACYS
    When I add "address 1" in Street Address 1 field
    And I add city "city 1" in the City input field
    And I select random state from State dropdown
    And we click on SAVE button from Client Info tab MACYS
    Then I should see the error popup from CLIENT INFO section MACYS
    And I click on the OK button on the error popup from CLIENT INFO section MACYS
    When I add "address 1" in Street Address 1 field
    And I add city "city 1" in the City input field
    And I select random state from State dropdown
    And I select random type from TYPE dropdown
    And we click on SAVE button from Client Info tab MACYS
    Then I should see the error popup from CLIENT INFO section MACYS
    And I click on the OK button on the error popup from CLIENT INFO section MACYS
    When I add "address 1" in Street Address 1 field
    And I add city "city 1" in the City input field
    And I select random state from State dropdown
    And I select random type from TYPE dropdown
    And I enter zip code "5" in the Zip Code input field
    And we click on SAVE button from Client Info tab MACYS
    And I click on the OK button on the error popup from CLIENT INFO section MACYS
    And I enter zip code "52" in the Zip Code input field
    And we click on SAVE button from Client Info tab MACYS
    And I click on the OK button on the error popup from CLIENT INFO section MACYS
    And I enter zip code "532" in the Zip Code input field
    And we click on SAVE button from Client Info tab MACYS
    And I click on the OK button on the error popup from CLIENT INFO section MACYS
    And I enter zip code "5432" in the Zip Code input field
    And we click on SAVE button from Client Info tab MACYS
    Then I should see the error popup from CLIENT INFO section MACYS
    And I click on the OK button on the error popup from CLIENT INFO section MACYS
    When I enter zip code "12345" in the Zip Code input field
    And we click on SAVE button from Client Info tab MACYS
    Then data is successfully saved MACYS Manage Client


  @domain_stores @omniclient @story_SDE-175 @website @mcom
  Scenario: In order to save a new or edited phone number; I must have entered a valid 10 digit number
  and have selected a Phone Type.
    #parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "NewClientMACYsTest2" "UpdateData1" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I navigate to Manage Client tab
    And I click on the Edit button from the CLIENT INFO section MACYS
    And we click on ADD button from Phones section
    And we add incomplete additional phone number "222333444" MACYS
    And we click on SAVE button from Client Info tab MACYS
    Then I should see the error popup from CLIENT INFO section MACYS
    And I click on the OK button on the error popup from CLIENT INFO section MACYS
    #added this in order to test phone numbers that start with 0
#    And we click on ADD button from Phones section
    And we add incomplete additional phone number "0022333444" MACYS
    And we click on SAVE button from Client Info tab MACYS
    Then I should see the error popup from CLIENT INFO section MACYS
    And I click on the OK button on the error popup from CLIENT INFO section MACYS

  @domain_stores @omniclient @story_SDE-175 @website @mcom
  Scenario: Add new client for field validation purpose
    #parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "ValidationTest5" "Data" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"



  @domain_stores @omniclient @story_SDE-175 @website @mcom
  Scenario: In order to validate address formatting, I want to ensure Address Line 1 and 2 can only contain
  numbers, letters, spaces, hash tags (#), periods ( . ), commas ( , ), semicolons ( ; ), colons ( : ),
  apostrophes ( ' ), dash ( - ), and forward slash ( / ).
    And I click on My Book radio button
    And I type the name of a customer "ValidationTest5" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Client tab
    And I click on the Edit button from the CLIENT INFO section MACYS
    When click on ADD button from Address section
    And we add additional Address Line address line 1 and 2, remaining mandatory fields and click SAVE MACYS
#    And we add remaining address mandatory fields MACYS
#    And we click on SAVE button from Client Info tab MACYS
#    Then I should see the error popup from CLIENT INFO section MACYS
#    And I click on the OK button on the error popup from CLIENT INFO section MACYS

      | >  |
      | `~ |
      | !@ |
      | $% |
      | ^& |
      | *( |
      | )_ |
      | +  |
      | =  |
      | [{ |
      | ]} |
      | \  |
      | <  |
      | ?  |
