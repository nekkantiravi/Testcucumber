# Author: Robert Vargas
# Story:
# Date Created: 08/30/2017
# Date Signed Off:

Feature: As an associate, I want to Search and Add/Remove a client.

  @RegBcom @domain_stores @omniclient @website
  Scenario: Launch, login and add a customer using a phone number
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I enter "4198069673" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns BLM:
      | Name                                      |
      | Address                                   |
      | Phone Number                              |
      | Affiliated with Me                        |
      | Affiliated to Other Sales Professional(s) |
    And the searched client is displayed in the search results page "ROBERT VARGAS"
    When I click the Add to Book button from search results
    Then the Create new Customer page is displayed BLM
    When I enter "TEST" in the hint Textbox
    And  I select the phone radio button as the preferred contact method
    And  I click save on the create new client page
    And I click yes button on the credit card required attention popup
    Then I click ok on the popup screen

  @RegBcom @domain_stores @omniclient @website
  Scenario: Launch, login and search for a client using a phone number
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I enter "4198069673" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns BLM:
      | Name                                      |
      | Address                                   |
      | Phone Number                              |
      | Affiliated with Me                        |
      | Affiliated to Other Sales Professional(s) |
    And the searched client is displayed in the search results page "ROBERT VARGAS"

  @RegBcom @domain_stores @omniclient @website
  Scenario: Launch, login and search my book using a phone number no results
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "8787878787" in the search box
    And I click on the omniclient search button
    Then I should see the Search Again button on the My Client Search Results page


  @RegBcom @domain_stores @omniclient @website
  Scenario: Launch, login and cancel remove a client from my book by phone number
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "4198069673" in the search box
    And I click on the omniclient search button
    And I click on the searched phone number from the customers results list
    And I click on the remove from book button
    And I click cancel on the remove from book popup
    And I should see the remove from book button on the client profile

  @RegBcom @domain_stores @omniclient @website
  Scenario: Launch, login and remove a client via first,last name and zip
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "4198069673" in the search box
    And I click on the omniclient search button
    And I click on the searched phone number from the customers results list
    And I click on the remove from book button
    And I click ok on the remove client popup screen

  @RegMcom @domain_stores @omniclient @website
  Scenario: Add a client via first, last name and zip
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter "ROBERT" in the "first name" textbox
    And I enter "VARGAS" in the "last name" textbox
    And I enter "44145" in the "zip code" textbox
    And I select the Search button in Omniclient home screen
    Then the search result page is displayed with the following columns BLM:
      | Name                                      |
      | Address                                   |
      | Phone Number                              |
      | Affiliated with Me                        |
      | Affiliated to Other Sales Professional(s) |
    And the searched client is displayed in the search results page "ROBERT VARGAS"
    When I click the Add to Book button from search results
    Then the Create new Client page is displayed
    When I enter "TEST" in the hint Textbox
    And  I select the phone radio button as the preferred contact method
    And  I click save on the create new client page
    Then I click ok on the popup screen

  @RegMcom @domain_stores @omniclient @website
  Scenario: Search for a customer via first, last name & zip
     Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter "ROBERT" in the "first name" textbox
    And I enter "VARGAS" in the "last name" textbox
    And I enter "44145" in the "zip code" textbox
    And I select the Search button in Omniclient home screen
     Then the search result page is displayed with the following columns BLM:
      | Name                                      |
      | Address                                   |
      | Phone Number                              |
      | Affiliated with Me                        |
      | Affiliated to Other Sales Professional(s) |
    And the searched client is displayed in the search results page "ROBERT VARGAS"

  @RegBcom @domain_stores @omniclient @website
  Scenario: Launch, login and verify no results from my book search via first name
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "testerman" in the search box
    And I click on the omniclient search button
    Then the No Search results screen is displayed in Omniclient search results page


  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and remove a client via first,last name and zip
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "ROBERT" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I click on the remove from book button
    And I click cancel on the remove from book popup
    And I should see the remove from book button on the client profile

  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and remove a client via first,last name and zip
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "ROBERT" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I click on the remove from book button
    And I click ok on the remove client popup screen

  @RegMcom @domain_stores @omniclient @website
  Scenario: Search for a customer via phone number (who doesn't exist in the OC or CC2 database) and add to book.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I add a new BLM Client: "Pablo" "TEST" "55 main st" "toledo" "43402" "TEST" "4755648574" "OH" "ROBERTPVARGAS@GMAIL.COM"


  @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and remove above newly added client using a phone number
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "4755648574" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I click on the remove from book button
    And I click ok on the remove client popup screen



  @RegMcom @domain_stores @omniclient @website
  Scenario: Search for a customer via first, last name and zip (who doesn't exist in the OC or CC2 database) and add to book.
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter "jimbo" in the "first name" textbox
    And I enter "johnson" in the "last name" textbox
    And I enter "44145" in the "zip code" textbox
    And I select the Search button in Omniclient home screen
    Then the No Search results screen is displayed in Omniclient search results page
    When I click here to create a new client
    Then the Create new Client page is displayed
    When I enter "4586978681" in the phone textbox field
    When I enter "TEST" in the hint Textbox
    And  I select the phone radio button as the preferred contact method
    And  I click save on the create new client page
    Then I click ok on the popup screen

    @RegMcom @domain_stores @omniclient @website
  Scenario: Launch, login and remove above newly added client via first,last name and zip
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "jimbo" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I click on the remove from book button
    And I click ok on the remove client popup screen



