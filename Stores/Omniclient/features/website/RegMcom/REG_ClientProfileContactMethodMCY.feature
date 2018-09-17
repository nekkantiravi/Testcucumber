# Author: Ovidiu Rucoi
# Story: Contact Method - Client Profile
# Date Created: 10/11/2017
# Date Signed Off:

Feature: As an associate, I want to be able to change the Contact Method from the Client Profile

  @RegMcom @domain_stores @omniclient @website
  Scenario Outline: Check Contact Method on Client Profile, Homepage, My Clients
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I select a Macys client from OCL website Homepage
    Then I should see the Client Profile website page
    When I navigate to Manage Client tab
    Then the following information should be displayed in Preferred Information section:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
    And the Additional Information section is displayed with the following sections MACYs:
      | Address(es) |
      | Phone(s)    |
    And the Primary Info section section is not displayed
    When I select as preferred method of contact "<contact_method>" from Client Info tab
    And we click on SAVE button from Client Info tab MACYS
    Then I should see the preferred method of contact as "<contact_method>" on the Client Info page
    When I navigate to Macys Homepage
    Then I should see the preferred method of contact as "<contact_method>" on the Homepage
    When I click on My Clients from top navigation bar
    Then I should see the preferred method of contact as "<contact_method>" on the My Clients page

    Examples:
      | contact_method |
      | text           |
      | phone          |