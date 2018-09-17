# Author: Claudiu Chirila
# Story: SDE-175 - Edit Client Profile: Phone and Address Validation Checks
# Date Created: 06/30/2017
# Date Signed Off:

Feature: As an associate, I want the information I add/edit on a Client/Customer profile to be validated
  so that I can ensure I am adding real/accurate information and also not missing any required information.

  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate

  @domain_stores @omniclient @story_SDE-175 @website @bcom
  Scenario: In order to save a new or edited address; I must have entered info in all required fields:
  Address Line 1, City, State, Zip Code, Country and Address Type

    And I add a new BLM Client: "Test1 Test Account" "BLM" "TESTADDRESS" "TESTCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM



    When click on ADD button from Address section BLM
    And I add "address 1" in Street Address 1 field BLM
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM
    And I click on the OK button on the error popup from CUSTOMER INFO section BLM

#    When I add "address 1" in Street Address 1 field BLM
#    And I add city "city 1" in the City input field BLM
#    And we click on SAVE button from Customer Info tab BLM
#    Then I should see the error popup from CUSTOMER INFO section BLM
#    And I click on the OK button on the error popup from CUSTOMER INFO section BLM
#
#    When I add "address 1" in Street Address 1 field BLM
#    And I add city "city 1" in the City input field BLM
#    And I select random state from State dropdown BLM
#    And we click on SAVE button from Customer Info tab BLM
#    Then I should see the error popup from CUSTOMER INFO section BLM
#    And I click on the OK button on the error popup from CUSTOMER INFO section BLM
#
#    When I add "address 1" in Street Address 1 field BLM
#    And I add city "city 1" in the City input field BLM
#    And I select random state from State dropdown BLM
#    And I select random type from TYPE dropdown BLM
#    And we click on SAVE button from Customer Info tab BLM
#    Then I should see the error popup from CUSTOMER INFO section BLM
#    And I click on the OK button on the error popup from CUSTOMER INFO section BLM

    When I add "address 1" in Street Address 1 field BLM
    And I add city "city 1" in the City input field BLM
    And I select random state from State dropdown BLM
    And I select random type from TYPE dropdown BLM
    And I enter zip code "5" in the Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    And I click on the OK button on the error popup from CUSTOMER INFO section BLM

    And I enter zip code "52" in the Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    And I click on the OK button on the error popup from CUSTOMER INFO section BLM

    And I enter zip code "523" in the Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    And I click on the OK button on the error popup from CUSTOMER INFO section BLM

    And I enter zip code "5223" in the Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM
    And I click on the OK button on the error popup from CUSTOMER INFO section BLM

    When I enter zip code "12345" in the Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    Then data is successfully saved BLM Manage Customer


  @domain_stores @omniclient @story_SDE-175 @website @bcom
  Scenario: In order to save a new or edited phone number; I must have entered a valid 10 digit phone number
  and have selected a Phone Type.

    And I add a new BLM Client: "Test1TestAccount1" "BLM" "TESTADDRESS" "TESTCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I navigate to Manage Customer tab

    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    And we click on ADD button from Phones section
    And we add incomplete additional phone number "222333444" BLM
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM
    When I click on the OK button on the error popup from CUSTOMER INFO section BLM

   #added this in order to test phone numbers that start with 0
    And we click on ADD button from Phones section
    And we add incomplete additional phone number "00222333444" BLM
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM
    And I click on the OK button on the error popup from CUSTOMER INFO section BLM


  @domain_stores @omniclient @story_SDE-175 @website @bcom
  Scenario: In order to validate address formatting, I want to ensure Address Line 1 and 2 can only contain
  numbers, letters, spaces, hash tags (#), periods ( . ), commas ( , ), semicolons ( ; ), colons ( : ),
  apostrophes ( ' ), dash ( - ), and forward slash ( / ).
    And I click on My Book radio button
    And I type the name of a customer "Test1TestAccount" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    When click on ADD button from Address section
    And we add additional Address Line address line 1 and 2, remaining mandatory fields and click SAVE blm
#    And we add remaining address mandatory fields BLM
#    And we click on SAVE button from Customer Info tab BLM
#    Then I should see the error popup from CUSTOMER INFO section BLM
#    And I click on the OK button on the error popup from CUSTOMER INFO section BLM

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
      | >  |
      | ?/ |


#  @manual @domain_stores @omniclient @story_SDE-175 @website @bcom
#  Scenario: In order to save a preferred method of contact as 'Email' - I must have entered a valid email address
#  and have selected an email type
#    And I add a new BLM Client: "NewClientEditBLM" "UpdateEditData1"
#    And I navigate to Manage Customer tab
#    And I click on the Edit button from the CUSTOMER INFO section BLM
#    And we validate that we cannot select the Email radio button from Preferred Contact Method
#    And we click on ADD button from Emails section BLM
#    And we add additional email "<string>" BLM
#    And I change the preferred email by selecting the radio button from the new added email BLM
#    And we click on SAVE button from Customer Info tab BLM
#    Then the new preferred information is displayed BLM