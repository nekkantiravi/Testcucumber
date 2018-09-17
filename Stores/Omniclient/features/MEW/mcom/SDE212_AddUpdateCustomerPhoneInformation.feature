#Author: Claudiu Chirila
#Story: SDE-212 - MOBILE:Saving Alternate Phone Number
#Date Created: 07/24/2017
#Date Signed Off:

Feature: As an associate using the mobile application, I want the ability to add or update a Client/Customer/Contact's
  phone information so that I can maintain my customer's desired contact information.

  @domain_stores @omniclient @story_SDE-212 @mcom @MEW
  Scenario: We can see the existing phone information for a new added client
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And we add a new client on mobile
      | 7778889991        | Phone number   |
      | Test Macys        | First Name     |
      | New client        | Last Name      |
      | prefName          | Preferred Name |
      | reqAddress        | Address line 1 |
      | additionalAddress | Address line 2 |
      | New City          | City           |
      | 12345             | Zip code       |
      | hint something    | Hint           |

    Then existing phone information is displayed
      | Phone number      |
      | Phone number type |
    When I tap on Edit button from Contact Profile page
    And I tap on Edit Phone from Edit Client page
    Then Save button is displayed in Edit Phone page
    And Cancel button is displayed in Edit Phone page
    And the preferred information radio buttons should be preselected in Edit Phone page
#   And the primary phone number is not displayed -> manual step


  @domain_stores @omniclient @story_SDE-212 @mcom @MEW
  Scenario: When typing a phone number, the dash symbols ( - ) are displayed to show proper formatting: ### - ### - ####
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "7778889991" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    And I should see the Customer information on the Results page:
      | Name |
    And I click on the Client on the Results page
    Then I should see the Client Profile page
    When I tap on Edit button from Contact Profile page
    And I tap on Edit Phone from Edit Client page
    And I add a new first secondary phone number "4445556668"
    Then the new first secondary number "444-555-6668" is displayed with dash symbols and proper formatting in edit mode
    When I select phone type from dropdown mobile
    And I select the "2" preferred radio button from edit phone page mobile
    And we tap on Save button from Edit Phone page
    And I tap OK in the add confirmation popup
    Then the new added first secondary number "(444) 555-6668 Work" is displayed with proper formatting


