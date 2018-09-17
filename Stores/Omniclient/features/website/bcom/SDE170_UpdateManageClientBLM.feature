# Author: Claudiu Chirila
# Story: SDE-170 - OmniClient : Launch Application
# Date Created:
# Date Signed Off:

Feature: As an associate, I want the ability to add and update customer information so that I can maintain my customer's desired contact or shipping information.

  @domain_stores @omniclient @story_SDE-170 @website @bcom
  Scenario: Selling Associate updates customer information for a new added customer
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I add a new BLM Client: "NewClientBLM" "UpdateData" "TESTADDRESS" "TESTCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    #we navigate to Manage Customer tab/section of  the newly created client
    And I navigate to Manage Customer tab
    Then the following information should be displayed in Preferred Information section BLM:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
      | Preferred Email        |
    And the Additional Information section is displayed with the following sections BLM:
      | Address(es) |
      | Phone(s)    |
      | Email(s)    |
    When I click on the Edit button from the CUSTOMER INFO section BLM
    Then the client information fields become editable BLM
    When we update data "blm name" into Additional information fields BLM
    And we save the changes BLM
    Then updated data is displayed for the Additional information section BLM

  @domain_stores @omniclient @story_SDE-170 @website @mcom
  Scenario: Customer information is correctly displayed after a Selling Manager navigates to Manage Customer page
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as Selling Manager
    And I add a new BLM Client: "NewClientBLM" "UpdateData" "TESTADDRESS" "TESTCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I click on My Book radio button
    And I type the name of a customer "NewClientBLM" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Customer tab
    Then the following information should be displayed in Preferred Information section BLM:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
      | Preferred Email        |
    And the Additional Information section is displayed with the following sections BLM:
      | Address(es) |
      | Phone(s)    |
      | Email(s)    |

  @domain_stores @omniclient @story_SDE-170 @website @mcom
  Scenario: Customer information is correctly displayed after a General Manager navigates to Manage Customer page
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as General Manager
    And I add a new BLM Client: "NewClientBLM" "UpdateData" "TESTADDRESS" "TESTCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I click on My Book radio button
    And I type the name of a customer "NewClientBLM" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I navigate to Manage Customer tab
    Then the following information should be displayed in Preferred Information section BLM:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
      | Preferred Email        |
    And the Additional Information section is displayed with the following sections BLM:
      | Address(es) |
      | Phone(s)    |
      | Email(s)    |