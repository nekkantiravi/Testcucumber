#Author: Ovidiu Rucoi
#Story: SDE-213 - MOBILE: Add/Update Customer Email Information
#Date Created: 07/24/2017
#Date Signed Off:

Feature: As an associate using the mobile application, I want the ability to add or update a Client/Customer/Contact's
  email information so that I can maintain my customer's desired contact information.

  @domain_stores @omniclient @story_SDE-213 @bcom @MEW
  Scenario: We can see the existing email information for a new added client
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And we add a new client on mobile BLM
      | 5556667778        | Phone number   |
      | Iulian            | First Name     |
      | Alecsandri        | Last Name      |
      | prefName          | Preferred Name |
      | reqAddress        | Address line 1 |
      | additionalAddress | Address line 2 |
      | New City          | City           |
      | 12345             | Zip code       |
      | hint something    | Hint           |
      | iuly@blm.com      | Email          |
    Then existing email "iuly@blm.com" information is displayed
    When I tap on Edit button from Contact Profile page BLM
    And I tap on Edit Email from Edit Contact page
    Then Save button is displayed in Edit Email page
    And Cancel button is displayed in Edit Email page
    And the preferred information radio buttons should be preselected in Edit Email page

  @domain_stores @omniclient @story_SDE-213 @bcom @MEW
  Scenario: We can see the existing email information for an updated client
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search My Book button
#    Then I should see the Search My Book page
    When I select By Name button from the Search My Book page
    Then I should see the By Name search page
    When I input the name "Alecsandri" on the By Name search page
    And I click the Search button on the Search My Book page
    Then I should see the search results on the Search My Book
    When I click on the Client on the My Book Results page
    Then I should see the Contact Profile page
    When I tap on Edit button from Contact Profile page BLM
    And I tap on Edit Email from Edit Contact page
    And I update the existing email to "update@email.blm"
    And I click on the Save button in Edit Email page
    And I click OK on the save confirmation popup
    Then existing email "update@email.blm" information is displayed