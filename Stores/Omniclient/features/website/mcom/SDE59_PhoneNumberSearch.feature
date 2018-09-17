# Author: Claudiu Chirila
# Story: SDE-59 - OmniClient : Phone Search
# Date Created: 07/07/2017
# Date Signed Off:

Feature: As an associate, I want to search for customers using their phone number so that I can locate customer profiles and add customers to my book

  @domain_stores @omniclient @story_SDE-59 @website @mcom
  Scenario: Error popup is received when user performs a search without having the complete phone number added
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    #The method bellow is use for adding a complete and/or valid phone number and also a incomplete and/or invalid phone number
    And we input the preferred number "1112223" in the TELEPHONE field
    And I click on the omniclient search button
    Then error popup saying that phone number is required is displayed "Phone Number Required"

  @domain_stores @omniclient @story_SDE-59 @website @mcom
  Scenario: Error popup is received when user performs a search after searching for a phone number that starts with 0
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    #The method bellow is use for adding a complete and/or valid phone number and also a incomplete and/or invalid phone number
    And we input the preferred number "0523523523" in the TELEPHONE field
    And I click on the omniclient search button
    Then error popup saying that phone number is invalid is displayed "Invalid Phone Number"


  @domain_stores @omniclient @story_SDE-59 @website @mcom
  Scenario: Search results for a CC2 client/customer phone number is successfully done
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And we input the preferred number "4193591648" in the TELEPHONE field
    And I click on the omniclient search button
    Then the searched client is displayed in the search results page "(419) 359-1648"
    When I click on the searched client "TEST1 TEST ACCOUNT"
    Then the Create new Client page is displayed

  @domain_stores @omniclient @story_SDE-59 @website @mcom
  Scenario: Search results for a new added phone number is successfully done
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    #parameters in this order: first name, last name, address, city , zip, hint
    And I add a new Macys Client: "SEARCH" "SDE58" "TEST ADDRESS" "TEST CITY" "12345" "TEST HINT" "1111111111" "AL"
    And I navigate to Manage Client tab
    And I click on the Edit button from the CLIENT INFO section MACYS
    And we click on ADD button from Phones section
   #The method bellow is use for adding a complete and/or valid phone number and also a incomplete and/or invalid phone number
    And we add additional phone number "1122233341" MACYS
    And I change the preferred number by selecting the radio button from the new added phone
    And we click on SAVE button from Client Info tab MACYS
    And we input the preferred number "1222333411" in the TELEPHONE field
    And I click on the omniclient search button
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "SEARCH SDE58"

#  @manual @domain_stores @omniclient @story_SDE-59 @website @mcom
#  Scenario: TELEPHONE search field does not accept alpha or special characters
#    Given I launch the macy's omniclient page
#    When I sign into Omniclient application as associate
#    And I try to add special characters "@#$/*&^"in the TELEPHONE filed
#    Then the nothing is displayed in the TELEPHONE filed


