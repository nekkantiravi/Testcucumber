# Author: Ovidiu Rucoi
# Story: SDE-181 - Edit Contact Profile: Validation Checks
# Date Created: 07/18/2017
# Date Signed Off:

Feature: As an associate, I want the information I add/edit on a Contact profile to be validated so that
  I can ensure I am adding real/accurate information and also not missing any required information.

  @domain_stores @omniclient @story_SDE-181 @website @bcom
  Scenario: To save a Contact Profile, I must have added either a phone number or email address
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I add a new BLM Client: "SDE181TEST3" "CONTACT" "TESTADDRESS" "TESTCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    And I remove the Phone Number from the CUSTOMER INFO section BLM
    And I remove the Email from the CUSTOMER INFO section BLM
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM

  @domain_stores @omniclient @story_SDE-181 @website @bcom
  Scenario: To save a preferred method of contact as 'Email', I must have email address and selected an email type
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "SDE181TEST3" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    And I select as preferred method of contact "Email"
    And we click on SAVE button from Customer Info tab BLM
    Then data is successfully saved BLM Manage Customer
    When I click on the Edit button from the CUSTOMER INFO section BLM
    And I remove the Email from the CUSTOMER INFO section BLM
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM

  @domain_stores @omniclient @story_SDE-181 @website @bcom
  Scenario: To save a new or edited phone number, I must have input a 10 digit number and have selected a Phone Type;
  Phone Numbers cannot begin with a 0
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "SDE181TEST3" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    And we click on ADD button from Phones section BLM
    And we add additional phone number "1234567890" and do not select a Phone Type BLM
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM
    When I click on the OK button on the error popup from CUSTOMER INFO section BLM
    And we add additional second phone number "00222" BLM
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM
    And I click on the OK button on the error popup from CUSTOMER INFO section BLM

  @domain_stores @omniclient @story_SDE-181 @website @bcom
  Scenario: Address Line 1 and 2 can only contain numbers, letters, spaces, hash tags (#), periods ( . ),
  commas ( , ), semicolons ( ; ), colons ( : ), apostrophes ( ' ), dash ( - ), and forward slash ( / )
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "SDE181TEST3" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    When click on ADD button from Address section
    And we add additional Address Line address line 1 and 2, remaining mandatory fields and click SAVE blm
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

  @domain_stores @omniclient @story_SDE-181 @website @bcom
  Scenario: Zip Code must be of at least 5 digits (US) or 3-11 otherwise; The state can only contain letters and spaces;
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "SDE181TEST3" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    And I select a country "US" from the Country Dropdown
    And I enter zip code "5223" in the existing Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM
    And I click on the OK button on the error popup from CUSTOMER INFO section BLM
    And I select a country "US" from the Country Dropdown
    When I enter zip code "12345" in the existing Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    Then data is successfully saved BLM Manage Customer
    When I click on the Edit button from the CUSTOMER INFO section BLM
    And I select a country "FR" from the Country Dropdown
    When I enter zip code "1" in the existing Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM
    And I click on the OK button on the error popup from CUSTOMER INFO section BLM
    And I select a country "FR" from the Country Dropdown
    When I enter zip code "111" in the existing Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    Then data is successfully saved BLM Manage Customer
    When I click on the Edit button from the CUSTOMER INFO section BLM
    And I select a country "FR" from the Country Dropdown
    When I enter zip code "11111111111" in the existing Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    Then data is successfully saved BLM Manage Customer
    When I click on the Edit button from the CUSTOMER INFO section BLM
    And I select a country "FR" from the Country Dropdown
    And I add a state "ST@TE-123" in the State input field
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the error popup from CUSTOMER INFO section BLM
    And I click on the OK button on the error popup from CUSTOMER INFO section BLM
    When I add a state "TOULOUSE LAUTREC" in the State input field
    And we click on SAVE button from Customer Info tab BLM
    Then data is successfully saved BLM Manage Customer
    When I click on the Edit button from the CUSTOMER INFO section BLM
    And I select a country "default" from the Country Dropdown
    When I enter zip code "12345" in the existing Zip Code input field BLM
    And we click on SAVE button from Customer Info tab BLM
    Then data is successfully saved BLM Manage Customer

  @domain_stores @omniclient @story_SDE-181 @website @bcom
  Scenario: Address information is optional and can be added in pieces
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "SDE181TEST3" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Customer tab
    And I successfully update the Address Information from CUSTOMER INFO:
#     | Address Line 1 | City   | State    | ZIP   | Country |
      | ADDRESS 1      | CITY 1 |          |       | US      |
      | ADDRESS 1      |        |          | 12345 | US      |
      | ADDRESS 1      |        | TOULOUSE |       | FR      |
      | ADDRESS 1      |        |          | 44044 |         |