# Author: Claudiu Chirila
# Story: SDE-232 - Hide Customer Prime Info
# Date Created:
# Date Signed Off:

Feature: As an associate, I no longer want to see a customer's Primary Information when viewing their client profile
  so that I am not using potentially corrupt information.

  @domain_stores @omniclient @story_SDE-232 @website @bcom
    Scenario: Section labeled 'Primary Information' within the customer's profile (Manage Client) is no longer displayed
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "KRISPEN" in the search box
    And I click on the omniclient search button
    When I click on the searched client from the customers results list
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
    And the Primary Info section section is not displayed

  @domain_stores @omniclient @story_SDE-232 @website @bcom
  Scenario: Section labeled 'Primary Information' within the customer's profile (Manage Client) is no longer displayed
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as corporate admin
    And I click on My Book radio button
    And I type the name of a customer "TESTS781" in the search box
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
    And the Primary Info section section is not displayed
