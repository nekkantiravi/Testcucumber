# Author: Robert Vargas
# Story:
# Date Created: 08/30/2017
# Date Signed Off:

Feature: As an associate, I want to Search and Add/Remove a client.

   @RegMcom @domain_stores @omniclient @website
   Scenario: Launch, login and add a client using a phone number
     Given I launch the macy's omniclient page
     When I sign into Omniclient application as associate
     And I enter "4198069673" in the "phone" textbox
     And I select the Search button in Omniclient home screen
     Then the search result page is displayed with the following columns:
       | Name                           |
       | Address                        |
       | Phone Number                   |
       | My Relationship                |
       | Relationship w/other Associate |
     And the searched client is displayed in the search results page "ROBERT VARGAS"
     When I click the Add to Book button from search results
     Then the Create new Client page is displayed
     When I enter "TEST" in the hint Textbox
     And  I select the phone radio button as the preferred contact method
     And  I click save on the create new client page
     Then I click ok on the popup screen


   @RegMcom @domain_stores @omniclient @website
   Scenario: Launch, login and search for a client using a phone number
     Given I launch the macy's omniclient page
     When I sign into Omniclient application as associate
     And I enter "4198069673" in the "phone" textbox
     And I select the Search button in Omniclient home screen
     Then the search result page is displayed with the following columns:
       | Name                           |
       | Address                        |
       | Phone Number                   |
       | My Relationship                |
       | Relationship w/other Associate |
     And the searched client is displayed in the search results page "ROBERT VARGAS"

  @RegBcom @domain_stores @omniclient @website
  Scenario: Launch, login and verify no results my book phone number search
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on My Book radio button
    And I type the name of a customer "4758695847" in the search box
    And I select the Search button in Omniclient home screen
    Then I should see the Search Again button on the My Client Search Results page

  @RegBcom @domain_stores @omniclient @website
  Scenario: Launch, login and verify results found my book phone search
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on My Book radio button
    And I type the name of a customer "4198069673" in the search box
    And I click on the omniclient search button
   And I should see the searched client on the My Client Search Results page

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and cancel remove a client using a phone number
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I enter "4198069673" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "ROBERT VARGAS"
    When I click on the searched client "ROBERT VARGAS"
    Then I click on the remove from book button
    And I click cancel on the remove from book popup
    And I should see the remove from book button on the client profile


  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and remove a client using a phone number
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I enter "4198069673" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "ROBERT VARGAS"
    When I click on the searched client "ROBERT VARGAS"
    Then I remove the newly added Macys Client

    @RegMcom @domain_stores @omniclient @website
  Scenario: Add a client via first, last name and zip
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter "ROBERT" in the "first name" textbox
    And I enter "VARGAS" in the "last name" textbox
    And I enter "44145" in the "zip code" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "ROBERT VARGAS"
    When I click the Add to Book button from search results
    Then the Create new Client page is displayed
    When I enter "TEST" in the hint Textbox
    And  I select the phone radio button as the preferred contact method
    And  I click save on the create new client page
    Then I click ok on the popup screen


  @RegMcom @domain_stores @omniclient @website
  Scenario: Search for a customer via first, last name & zip
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter "ROBERT" in the "first name" textbox
    And I enter "VARGAS" in the "last name" textbox
    And I enter "44145" in the "zip code" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "ROBERT VARGAS"

  @RegBcom @domain_stores @omniclient @website
  Scenario: Launch, login and search for a client via first name no results
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on My Book radio button
    And I type the name of a customer "testerman" in the search box
    And I select the Search button in Omniclient home screen
    Then I should see the Search Again button on the My Client Search Results page

  @RegBcom @domain_stores @omniclient @website
  Scenario: Launch, login and search for a client via first name results
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on My Book radio button
    And I type the name of a customer "ROBERT" in the search box
    And I click on the omniclient search button
    Then I should see the searched client on the My Client Search Results page

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and cancel remove a client via first,last name and zip
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter "ROBERT" in the "first name" textbox
    And I enter "VARGAS" in the "last name" textbox
    And I enter "44145" in the "zip code" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "ROBERT VARGAS"
    When I click on the searched client "ROBERT VARGAS"
    Then I click on the remove from book button
    And I click cancel on the remove from book popup
    And I should see the remove from book button on the client profile



  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and remove a client via first,last name and zip
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter "ROBERT" in the "first name" textbox
    And I enter "VARGAS" in the "last name" textbox
    And I enter "44145" in the "zip code" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "ROBERT VARGAS"
    When I click on the searched client "ROBERT VARGAS"
    Then I remove the newly added Macys Client



  @RegMcom @domain_stores @omniclient @website
  Scenario: Search for a customer via phone number (who doesn't exist in the OC or CC2 database) and add to book.
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I add a new Macys Client: "Robert" "Test" "500 main st" "bowling green" "43402" "test" "9876598765" "OH"

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and remove a client using a phone number
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I enter "9876598765" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "ROBERT TEST"
    When I click on the searched client "ROBERT TEST"
    Then I remove the newly added Macys Client


  @RegMcom @domain_stores @omniclient @website
  Scenario: Search for a customer via first, last name and zip (who doesn't exist in the OC or CC2 database) and add to book.

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter "Pablo" in the "first name" textbox
    And I enter "Vargas" in the "last name" textbox
    And I enter "43402" in the "zip code" textbox
    And I select the Search button in Omniclient home screen
    Then the No Search results screen is displayed in Omniclient search results page
    When I click here to create a new client
    Then the Create new Client page is displayed
    And I enter "8473625273" in the phone textbox field
    And I enter "100 test st" in the Address Line 1 field
    And I enter the city of "bowling green" in the city field
    And I select "OH" from the state dropdown
    And I enter "45453" as the zip code
    And I enter "whatever" as the hint
    And I select the text radio button as the preferred contact method
    And  I click save on the create new client page
    Then I click ok on the popup screen

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and remove a client via first,last name and zip
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter "Pablo" in the "first name" textbox
    And I enter "Vargas" in the "last name" textbox
    And I enter "45453" in the "zip code" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "PABLO VARGAS"
    When I click on the searched client "PABLO VARGAS"
    Then I remove the newly added Macys Client

