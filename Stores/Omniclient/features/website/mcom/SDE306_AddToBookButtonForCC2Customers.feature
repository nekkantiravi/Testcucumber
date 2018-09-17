# Author: Claudiu Chirila
# Story: SDE-306 - Display Add to Book button for CC2 customers on Search Results Page
# Date Created:
# Date Signed Off:

Feature: As an associate, I want to see an 'Add to Book' button for all customers I am not affiliated to when viewing
  the search results page so that I can utilize the application in a consistent manner.

  @domain_stores @omniclient @story_SDE-306 @website @mcom
  Scenario: Add To Book button is displayed for an unaffiliated client/customer in search results page
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    # we will search for an unaffiliated client/customer
    And I enter first name "PRIMARY" in the input field
    And I enter last name "CLIENT" in the input field
    And I enter zip code "44053" in the input field
    And I click on the omniclient search button
    Then the searched client is displayed in the search results page "PRIMARY CLIENT"
    And the Add to Book button is displayed on the search results page



  @domain_stores @omniclient @story_SDE-306 @website @mcom
  Scenario: Add To Book button is displayed for an CC2 client/customer in search results page
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    # we will search for CC2 client/customer
    And I enter first name "PITACCTEMV YY" in the input field
    And I enter last name "MACYS" in the input field
    And I enter zip code "44053" in the input field
    And I click on the omniclient search button
    Then the searched client is displayed in the search results page "PITACCTEMV YY MACYS"
    And the Add to Book button is displayed on the search results page


  @domain_stores @omniclient @story_SDE-306 @website @mcom
  Scenario: Add To Book button is displayed for an CC2 client/customer in search results page
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And we input the preferred number "4402339874" in the TELEPHONE field
  # we will search for CC2 client/customer
    And I click on the omniclient search button
    Then the searched client is displayed in the search results page "(440) 233-9874"
    And the Add to Book button is displayed on the search results page

  @manual @domain_stores @omniclient @story_SDE-306 @website @mcom
  Scenario: Search for a Client that is in CC2 but NOT in our DB via an Individualized Card swipe.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click the Credit Card field
    And I swipe an individualized Credit Card
    Then I should see Search Results Screen and see the following from CC2:
      | Name           |
      | Address        |
      | Phone Number   |
    And the Add to Book button is displayed on the search results page

  @manual @domain_stores @omniclient @story_SDE-306 @website @mcom
  Scenario: Search for a Client that is NOT in CC2 and is NOT in our DB via an Operational Card swipe.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click the Credit Card field
    And I swipe an operational Credit Card
    Then I should see Search Results Screen and see the following:
      | Name           |
    And the Add to Book button is displayed on the search results page