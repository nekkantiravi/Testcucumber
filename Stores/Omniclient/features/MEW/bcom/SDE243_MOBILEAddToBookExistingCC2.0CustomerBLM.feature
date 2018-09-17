# Author: Traci Morris
# Story: SDE-243 - MOBILE: Add to Book - Existing CC2.0 Customer
# Date Created: 08/08/2017
# Date Signed Off:

Feature: As an associate using the mobile application, I want the ability to add an existing CC2.0 client/customer
  to my book and modify their contact information so that I can ensure I have the best information available.

  @domain_stores @omniclient @Story_SDE-243 @bcom @MEW
  Scenario: Add a NEW CC2.0 client to my book via a FN,LN,ZIP Search
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select FirstLastNameZip button from the Search All Clients page
    Then I should see the FirstLastNameZip search page
    When I input first name "LOOKUP5" on the FirstLastNameZip search page
    And I input last name "TEST5" on the FirstLastNameZip search page
    And I input the zip code "30024" on the FirstLastNameZip search page
    And I click the Search button on the Search All Clients page
    Then I should see the number of search results found on the Results page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Unable to View Profile popup
    When I select the Add to Book button on Unable to View Profile popup
    Then I should see the Add Client screen prepopulated with the clients info "LOOKUP5" "TEST5" "30024" "4131015813"


  @domain_stores @omniclient @Story_SDE-243 @bcom @MEW
  Scenario: Add a NEW CC2.0 client to my book via a Telephone Number Search
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "9133279899" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    Then I should see the number of search results found on the Results page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Unable to View Profile popup
    When I select the Add to Book button on Unable to View Profile popup
    Then I should see the Add Client screen prepopulated with the clients info "LOOKUP1" "TEST1" "44236" "9133279899"


  @manual @domain_stores @omniclient @Story_SDE-243 @bcom @MEW
  Scenario: Add a NEW CC2.0 client to my book via a Credit Card Search
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe an Individualized Credit Card on the Credit Card search page
    Then I should see the number of search results found on the Results page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Add Client screen prepopulated with the clients info