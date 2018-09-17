# Author: Ovidiu Rucoi
# Story: SDE-215 - MOBILE: Client/Customer  Screen
# Date Created: 08/22/2017
# Date Signed Off:

Feature: As an associate using the mobile application, I want to view a customer's preferred information and have the
  ability to add or update customer information

  @domain_stores @omniclient @Story_SDE-215 @mcom @MEW
  Scenario: Client Screen - View Preferred Info
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search My Book button
    Then I should see the Search My Book page
    When I select By Name button from the Search My Book page
    Then I should see the By Name search page
    When I input the name "Bakerton" on the By Name search page
    And I click the Search button on the Search My Book page
    Then I should see the search results on the Search My Book
    When I click on the Client on the My Book Results page
    Then I should see the Client Profile page
    And I should see the following details on the Profile Summary
      | Spent With Me | Last Visit | Total Visits | Relationship Date | Birthday | Pref Contact Method |
    And I should see the preferred Customer Information
      | Name | Pref Name | Pref Address | Pref Phone |
    And I should see the Hints and Notes link on the Client Profile page
    And I should see the Wallet link on the Client Profile page

  @domain_stores @omniclient @Story_SDE-215 @mcom @MEW
  Scenario: Client Screen - Selecting links/buttons
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search My Book button
    Then I should see the Search My Book page
    When I select By Name button from the Search My Book page
    Then I should see the By Name search page
    When I input the name "Bakerton" on the By Name search page
    And I click the Search button on the Search My Book page
    Then I should see the search results on the Search My Book
    When I click on the Client on the My Book Results page
    Then I should see the Client Profile page
    And I should see the Hints and Notes link on the Client Profile page
    When I click the Hints and Notes link on the Client Profile page
    Then I should see the Hints and Notes page
    When I click the back button from the mobile top bar Hints and Notes
    Then I should see the Client Profile page
    And I should see the Wallet link on the Client Profile page
    When I click the Wallet link on the Client Profile page
    Then I should see the Wallet page
    When I click the back button from the mobile top bar Wallet
    Then I should see the Client Profile page
    And I should see the Create To Do button on the Client Profile page
    When I click the Create To Do button on the Client Profile page
    Then I should see the Create To Do page
    When I click the back button from the mobile top bar Create To Do
    Then I should see the Client Profile page
    And I should see the Manage Client button on the Client Profile page
    When I click the Manage Client button from Client Profile page
    Then I should see the Manage Client page

#  Marked this as manual, since there is no option to remove a client from book, so this test can run only once
#  TODO: Update the test to remove the client from book, once functionality will be in place
  @manual @domain_stores @omniclient @Story_SDE-215 @mcom @MEW
  Scenario: Add to Book - populate REQUIRED/OPTIONAL Information Fields
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select FirstLastNameZip button from the Search All Clients page
    Then I should see the FirstLastNameZip search page
    When I input first name "SDE215" on the FirstLastNameZip search page
    And I input last name "TESTCLIENT" on the FirstLastNameZip search page
    And I input the zip code "13456" on the FirstLastNameZip search page
    And I click the Search button on the Search All Clients page
    Then I should see the Customer information on the Results page:
      | Name | AddressLine1 |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Add to Book button
    And I write the preferred name "PrefName" in the Preferred Name field
    And I write the required Address "Address1"
    And I write the second address "Address2"
    And I write the city in the City field "NewCity"
    And I write the zip code "12345" in the Zip code field
    And I select "AL" state from State dropdown mobile
    And I write the phone number in the Phone number field mobile"4445556666"
    And I tap on Save button from Add Client page
    And I write a hint in the Hint field "NewHint"
    And I write a note in the Notes field "NewNote"
    And I select Preferred Contact method as phone from dropdown
    And I tap on Continue button from Add to Book page
    And I tap OK in the add confirmation popup
    And I tap Save button from Add to Book page
    And I tap OK in the Successfully Added client popup
    Then I should see the Client Profile page
    And I should see the modified Customer Information
      | Pref Name   | PrefName       |
      | Phone Nbr   | (444) 555-6666 |
      | Phone Type  | Home           |
      | Addr Line 1 | Address1       |
      | Addr Line 2 | Address2       |
      | City        | Newcity        |
      | State       | AL             |
      | Zip         | 12345          |
    And I should see the Preferred Contact method as "phone"
    And I should see the Hint field populated with "NewHint"
    And I should see the Notes field populated with "NewNote"
