# Author: Claudiu Chirila
# Story: SDE-217 - MOBILE: Address Validation Checks
# Date Created: 08/08/2017
# Date Signed Off:

Feature: As an associate using the mobile application, I want the information I add/edit to be validated so that I can
  ensure I am adding real/accurate information and also not missing any required information.

  @domain_stores @omniclient @Story_SDE-217 @mcom @MEW
  Scenario: In order to save a new or edited address; I must have input an Address Line 1,
  City, State, Zip Code, and Address Type
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "5234532523" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    And I tap on Create Client button
    And I write the first name "First Name" in the First Name field
    And I write the last name "Last Name" in the Last Name Field
    And I write the preferred name "Pref Name" in the Preferred Name field
#  And I select the Phone Type from Phone Type dropdown
# bellow we are validating the required Address Line 1
    And I write the second address "Address Line 2"
    And I write the city in the City field "New City"
    And I write the zip code "12345" in the Zip code field
    And I select random state from State dropdown mobile
    And I tap on Save button from Add Client page
    Then an error popup for required address is displayed mobile
    And I click on OK button from required field error popup mobile
# bellow we are validating the required city
    When I write the required Address "Address 1"
    And I write the second address "Address Line 2"
    And I write the zip code "12345" in the Zip code field
    And I select random state from State dropdown mobile
    And I clear the city field from add client mobile page
    And I tap on Save button from Add Client page
    Then an error popup for required city is displayed mobile
    And I click on OK button from required field error popup mobile
# bellow we are validating the required zip field
    When I write the required Address "Address 1"
    And I write the second address "Address Line 2"
    And I write the city in the City field "New City"
    And I select random state from State dropdown mobile
    And I clear the zip field from add client mobile page
    And I tap on Save button from Add Client page
    Then an error popup for required zip is displayed mobile
    And I click on OK button from required field error popup mobile
    # bellow we are validating the required state
    When I write the required Address "Address 1"
    And I write the second address "Address Line 2"
    And I write the city in the City field "New City"
    And I write the zip code "12345" in the Zip code field
    And I select empty option from state dropdown mobile create client
    And I tap on Save button from Add Client page
    Then an error popup for required state is displayed mobile
    And I click on OK button from required field error popup mobile
    # bellow we are validating the zip code min characters
    And I write the zip code "1" in the Zip code field
    And I select random state from State dropdown mobile
    And I tap on Save button from Add Client page
    Then an error popup for zip code min characters is displayed mobile
    And I click on OK button from required field error popup mobile
    And I write the zip code "12" in the Zip code field
    And I tap on Save button from Add Client page
    Then an error popup for zip code min characters is displayed mobile
    And I click on OK button from required field error popup mobile
    And I write the zip code "123" in the Zip code field
    And I tap on Save button from Add Client page
    Then an error popup for zip code min characters is displayed mobile
    And I click on OK button from required field error popup mobile
    And I write the zip code "1234" in the Zip code field
    And I tap on Save button from Add Client page
    Then an error popup for zip code min characters is displayed mobile
    And I click on OK button from required field error popup mobile


  @domain_stores @omniclient @Story_SDE-217 @mcom @MEW
  Scenario: To validate address formatting, I want to ensure Address Line 1 and 2 can only contain
  numbers, letters, spaces, hash tags (#), periods ( . ), commas ( , ), semicolons ( ; ), colons ( : ),
  apostrophes ( ' ), dash ( - ), and forward slash ( / ).
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "5234532523" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    And I tap on Create Client button
    And I write the first name "First Name" in the First Name field
    And I write the last name "Last Name" in the Last Name Field
    And I write the preferred name "Pref Name" in the Preferred Name field
#   And I select the Phone Type from Phone Type dropdown
   #Address Line 1 and Address Line 2 will be added at the end and validated there
    And I write the city in the City field "New City"
    And I write the zip code "12345" in the Zip code field
    And I select random state from State dropdown mobile
    #this method will also validate the error popup that is displayed when we input invalid data
    And we add additional Address Line address line 1 and 2, remaining mandatory fields and click SAVE Mobile
      | >  |
      | `~ |
      | !@ |
      | $% |
      | ^& |
      | *( |
      | )_ |
      | +  |
      | =  |
      | [{ |
      | ]} |
      | \  |
      | <  |
      | ?  |