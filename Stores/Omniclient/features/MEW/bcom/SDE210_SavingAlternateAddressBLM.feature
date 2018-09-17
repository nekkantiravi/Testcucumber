#Author: Claudiu Chirila
#Story: SDE-210 - MOBILE:Saving Alternate Phone Number
#Date Created: 07/24/2017
#Date Signed Off:

Feature:As an associate using the mobile application, I want the phone number provided by the customer during the add
  to book process to be the preferred phone number so I know what phone number is best to contact them when viewing their profile/information.

  @domain_stores @omniclient @story_SDE-210 @blm @MEW
  Scenario: If adding a brand new client the address is saved as the primary address and secondary (preferred) address

    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "2223334445" on the Telephone Number search page
    And I click the Search button on the Search All Clients page

    And I tap on Create Client button
    And I write the first name "First Name BLM DB" in the First Name field
    And I write the last name "Last Name BLM" in the Last Name Field
    And I write the preferred name "Pref Name" in the Preferred Name field

#    And I select the Phone Type from Phone Type dropdown
    And I write the email in the Email field "bloomingdales@macys.com"
    And I write the required Address "Address 1 Pref Blm"
    And I write the second address "Address Line 2"
    And I write the city in the City field "New City"
    And I write the zip code "12345" in the Zip code field
    And I select random state from State dropdown mobile
    And I tap on Save button from Add Client page
    And I write a hint in the Hint field "new hint"
    And I select Preferred Contact method as phone from dropdown
    And I tap Save button from Add to Book page
    And I tap OK in the add confirmation popup

    Then I check that Address "Address 1 Pref Blm" is marked as preferred BLM
#    And I check that "Address 1 Pref Blm" is marked as primary address BLM
#    And I check in DB tables that the same address is in Primary and Preferred tables -> Manual

