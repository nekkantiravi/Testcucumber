# Author: Ovidiu Rucoi
# Story: SDE-417 - Add Phone Type Selection to the Add Customer Screen
# Date Created:
# Date Signed Off:

Feature: We need to add back in a phone type selection (Home, Mobile, Work) in the Add Customer Screen.

  @domain_stores @omniclient @story_SDE-417 @website @bcom
  Scenario Outline: Check Phone Type drop down selection
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I enter "<phone_nbr>" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    And I click here to create a new client
    Then the Create new Client page is displayed
    When I enter first name "FIRSTNM" in the first name field
    And I enter last name "LASTNM" in the last name field
    And I select "<phone_type>" from the phone type dropdown on Create Client page
    And I enter "Address Line 1" in the Address Line 1 field
    And I enter the city of "Random City" in the city field
    And I select "OH" from the state dropdown
    And I enter "45453" as the zip code
    And I enter "Test Hint" as the hint
    And I select the phone radio button as the preferred contact method
    And I click save on the create new client page
    And I click yes button on the credit card required attention popup
    And I click ok on the popup screen
    And I navigate to Manage Client tab
    Then the following information should be displayed in Preferred Information section BLM:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
    And I should see "<phone_type>" as the phone type in the Preferred Information

    Examples:
      | phone_type | phone_nbr  |
      | HOME       | 9876598765 |
      | MOBILE     | 4758695847 |
      | WORK       | 4198069673 |

  @domain_stores @omniclient @story_SDE-417 @website @bcom
  Scenario Outline: If MOBILE is NOT selected then Text cannot be selectable as Preferred Method of contact
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I enter "<phone_nbr>" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns:
      | Name         |
      | Address      |
      | Phone Number |
    When I click here to create a new client
    Then the Create new Client page is displayed
    And I select "<phone_type>" from the phone type dropdown on Create Client page
    Then I should see the text radio button "<status>" on Create Client page

    Examples:
      | phone_type | phone_nbr  | status   |
      | HOME       | 9876598765 | disabled |
      | WORK       | 4198069673 | disabled |
      | MOBILE     | 4445556666 | enabled  |

  @domain_stores @omniclient @story_SDE-417 @website @bcom
  Scenario: If no phone type is selected and error message should pop up and display
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I enter "9876598765" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns:
      | Name         |
      | Address      |
      | Phone Number |
    When I click here to create a new client
    Then the Create new Client page is displayed
    When I enter first name "FIRSTNM" in the first name field
    And I enter last name "LASTNM" in the last name field
    And I enter "Address Line 1" in the Address Line 1 field
    And I enter the city of "Random City" in the city field
    And I select "OH" from the state dropdown
    And I enter "45453" as the zip code
    And I enter "Test Hint" as the hint
    And I select the phone radio button as the preferred contact method
    And I click save on the create new client page
    Then I should see the Phone Type required error popup on the Create Client page