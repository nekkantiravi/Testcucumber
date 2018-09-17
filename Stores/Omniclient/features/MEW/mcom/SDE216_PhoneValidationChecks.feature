# Author: Claudiu Chirila
# Story: SDE-216 - MOBILE: Phone Validation Checks
# Date Created: 08/08/2017
# Date Signed Off:


Feature:As an associate using the mobile application, I want the information I add/edit to be validated
  so that I can ensure I am adding real/accurate information and also not missing any required information.


  @domain_stores @omniclient @Story_SDE-216 @mcom @MEW
  Scenario: In order to save a new or edited phone number; I must have input a 10 digit number and have
  selected a Phone Type, and the Phone numbers cannot begin with a 0

    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And we add a new client on mobile
      | 7778889991        | Phone number   |
      | Test Macys Phone  | First Name     |
      | New client        | Last Name      |
      | prefName          | Preferred Name |
      | reqAddress        | Address line 1 |
      | additionalAddress | Address line 2 |
      | New City          | City           |
      | 12345             | Zip code       |
      | hint something    | Hint           |


#    #added this in order to test incomplete phone numbers
#    When I tap on Edit button from Contact Profile page
#    And I tap on Edit Phone from Edit Client page
#    And I add a new first secondary phone number "45556668"
#    And I select phone type from dropdown mobile
#    And we tap on Save button from Edit Phone page
#   Then I should see the error popup from CLIENT INFO section mobile
##   And I click on the OK button on the error popup from CLIENT INFO section mobile
#    Then I should see the error popup from CLIENT INFO section BLM mobile
#    And I click on the OK button on the error popup from CLIENT INFO section BLM mobile

    #added this in order to test phone numbers that start with 0
    When I tap on Edit button from Contact Profile page
    And I tap on Edit Phone from Edit Client page
    And I add a new first secondary phone number "0778889991"
    And I select phone type from dropdown mobile
    And we tap on Save button from Edit Phone page
    Then I should see the error popup from CLIENT INFO section Macys mobile
    And I click on the OK button on the error popup from CLIENT INFO section Macys mobile








