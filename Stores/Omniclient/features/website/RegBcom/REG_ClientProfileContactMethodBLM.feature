# Author: Ovidiu Rucoi
# Story: Contact Method - Client Profile
# Date Created: 10/11/2017
# Date Signed Off:

Feature: As an associate, I want to be able to change the Contact Method from the Client Profile

  @RegBcom @domain_stores @omniclient @website
  Scenario Outline: Check Contact Method on Client Profile, Homepage, My Clients
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I select a Bloomingdales client from OCL website Homepage
    Then I should see the Customer Profile website page
    When I navigate to Manage Customer tab
    Then the following information should be displayed in Preferred Information section BLM:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
    And the Additional Information section is displayed with the following sections BLM:
      | Address(es) |
      | Phone(s)    |
    And the Primary Info section section is not displayed
    When I select as preferred method of contact "<contact_method>" from Client Info tab
    And we click on SAVE button from Customer Info tab BLM
    Then I should see the preferred method of contact as "<contact_method>" on the Client Info page
    When I navigate to Bloomingdales Homepage
    Then I should see the preferred method of contact as "<contact_method>" on the Homepage
    When I click on My Clients from top navigation bar
    Then I should see the preferred method of contact as "<contact_method>" on the My Clients page

    Examples:
      | contact_method |
      | text           |
      | email          |
      | phone          |
