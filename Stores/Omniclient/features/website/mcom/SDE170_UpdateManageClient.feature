# Author: Claudiu Chirila
# Story: SDE-170 - OmniClient : Launch Application
# Date Created:
# Date Signed Off:

Feature: As an associate, I want the ability to add and update customer information so that I can maintain my customer's desired contact or shipping information.

  @domain_stores @omniclient @story_SDE-170 @website @mcom
  Scenario: Selling Associate updates customer information for a new added customer
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    #parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "NewClientMACYs" "UpdateData1" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    #we navigate to Manage Customer tab/section of  the newly created client
    And I navigate to Manage Client tab
    Then the following information should be displayed in Preferred Information section:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
    And the Additional Information section is displayed with the following sections MACYs:
      | Address(es) |
      | Phone(s)    |

    When I click on the Edit button from the CLIENT INFO section MACYS
    Then the client information fields become editable MACYS
    When we update data "pref name" into Additional information fields MACYS
#    And we save the changes MACYS
    And we click on SAVE button from Client Info tab MACYS
    Then updated data "PREF NAME" is displayed for the Additional information section MACYS

  @domain_stores @omniclient @story_SDE-170 @website @mcom
  Scenario: Client information is correctly displayed after a Selling Manager navigates to Manage Client page
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as Selling Manager
    And I search for an existing client by telephone "2058372252"
    And I click on an existing client from search results page MACYS
    And I navigate to Manage Client tab
    Then the following information should be displayed in Preferred Information section:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
    And the Additional Information section is displayed with the following sections MACYs:
      | Address(es) |
      | Phone(s)    |

  @domain_stores @omniclient @story_SDE-170 @website @mcom
  Scenario: Client information is correctly displayed after a General Manager navigates to Manage Client page
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as general manager
    # parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "NewClientMACYs" "UpdateData1" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I navigate to Manage Client tab
    Then the following information should be displayed in Preferred Information section:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
    And the Additional Information section is displayed with the following sections MACYs:
      | Address(es) |
      | Phone(s)    |