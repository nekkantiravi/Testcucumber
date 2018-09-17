#Author: Claudiu Chirila
#Story: SDE-212 - MOBILE:Saving Alternate Phone Number
#Date Created: 07/24/2017
#Date Signed Off:

Feature: As an associate using the mobile application, I want the ability to add or update a Client/Customer/Contact's
  phone information so that I can maintain my customer's desired contact information.

  @domain_stores @omniclient @story_SDE-212 @blm @MEW
  Scenario: We can see the existing phone information for a new added client
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And we add a new client on mobile BLM
      | 5556667778        | Phone number   |
      | Test BLM 1        | First Name     |
      | New client        | Last Name      |
      | prefName          | Preferred Name |
      | reqAddress        | Address line 1 |
      | additionalAddress | Address line 2 |
      | New City          | City           |
      | 12345             | Zip code       |
      | hint something    | Hint           |
      | testemail@blm.com | Email          |
    Then existing phone information is displayed
      | Phone number      |
      | Phone number type |
#    And the primary phone number is not displayed
    When I tap on Edit button from Contact Profile page BLM
    And I tap on Edit Phone from Edit Contact page BLM
    Then Save button is displayed in Edit Phone page
    And Cancel button is displayed in Edit Phone page
    And the preferred information radio buttons should be preselected in Edit Phone page


  @domain_stores @omniclient @story_SDE-212 @blm @MEW
  Scenario: When typing a phone number, the dash symbols ( - ) are displayed to show proper formatting: ### - ### - ####
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search My Book button
#   Then I should see the Search My Book page
    When I select By Name button from the Search My Book page
    Then I should see the By Name search page
    When I input the name "Test BLM 1" on the By Name search page
    And I click the Search button on the Search My Book page
    Then I should see the search results on the Search My Book
    When I click on the Client on the My Book Results page
    Then I should see the Contact Profile page
    When I tap on Edit button from Contact Profile page BLM
    And I tap on Edit Phone from Edit Contact page BLM
    And I add a new first secondary phone number "4445556667"
    Then the new first secondary number "444-555-6667" is displayed with dash symbols and proper formatting in edit mode
    When I select phone type from dropdown mobile
    And I select the "2" preferred radio button from edit phone page mobile
    And we tap on Save button from Edit Phone page
    And I tap OK in the add confirmation popup
    Then the new added first secondary number "(444) 555-6667 Work" is displayed with proper formatting

