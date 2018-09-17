#Author: Claudiu Chirila
#Story: SDE-210 - MOBILE:Saving Alternate Phone Number
#Date Created: 07/24/2017
#Date Signed Off:

Feature:As an associate using the mobile application, I want the phone number provided by the customer during the add
  to book process to be the preferred phone number so I know what phone number is best to contact them when viewing their profile/information.

  @domain_stores @omniclient @story_SDE-210 @mcom @MEW
  Scenario: If adding a brand new client the address is saved as the primary address and secondary (preferred) address

    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate

    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "5556667778" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    And I tap on Create Client button
    And I write the first name "First Name MCY" in the First Name field
    And I write the last name "Last Name MCY" in the Last Name Field
    And I write the preferred name "Pref Name" in the Preferred Name field
    #And I select the Phone Type from Phone Type dropdown
    And I write the required Address "Address 1 pref"
    And I write the second address "Address Line 2"
    And I write the city in the City field "New City new"
    And I write the zip code "12345" in the Zip code field
    And I select random state from State dropdown mobile
    And I tap on Save button from Add Client page
    And I write a hint in the Hint field "new hint"
    And I select Preferred Contact method as phone from dropdown

    And I tap on Continue button from Add to Book page
#    And I input the signature omniclient
#    And I click on I Agree from signature box omniclient
    And I tap OK in the add confirmation popup
    And I tap Save button from Add to Book page
    And I tap OK in the Successfully Added client popup


    Then I check that Address "Address 1 Pref" is marked as preferred
#    And I check that Address "Address 1 Pref" is marked as primary
#    And I check in DB tables that the same address is in Primary and Preferred tables -> Manual