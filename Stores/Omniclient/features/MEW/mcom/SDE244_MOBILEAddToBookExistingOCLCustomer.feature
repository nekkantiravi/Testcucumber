# Author: Traci Morris
# Story: SDE-244 - MOBILE: Add to Book - Existing OmniClient Customer
# Date Created: 08/08/2017
# Date Signed Off:

Feature: As an associate using the mobile application, I want the ability to add an existing OCL client/customer
  to my book and modify their contact information so that I can ensure I have the best information available.

  @domain_stores @omniclient @Story_SDE-244 @mcom @MEW
  Scenario: Add an existing OCL client (no relation) to my book via a FN,LN,ZIP Search
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select FirstLastNameZip button from the Search All Clients page
    Then I should see the FirstLastNameZip search page
    When I input first name "PAULA" on the FirstLastNameZip search page
    And I input last name "SURACE" on the FirstLastNameZip search page
    And I input the zip code "06069" on the FirstLastNameZip search page
    And I click the Search button on the Search All Clients page
    Then I should see the number of search results found on the Results page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Add to Book button
    Then I should see the Add Client screen prepopulated with the clients info "PAULA" "SURACE" "06069" "4404404404"


  @domain_stores @omniclient @Story_SDE-244 @mcom @MEW
  Scenario: Add an existing OCL client (no relation) to my book via a Telephone Number Search
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "4404404404" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    Then I should see the number of search results found on the Results page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Add to Book button
    Then I should see the Add Client screen prepopulated with the clients info "PAULA" "SURACE" "06069" "4404404404"


  @manual @domain_stores @omniclient @Story_SDE-244 @mcom @MEW
  Scenario: Add an existing OCL client (no relation) to my book via a Credit Card Search
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe a credit card on the Credit Card search page
    Then I should see the number of search results found on the Results page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I select the Add to Book button
    Then I should see the Add Client screen prepopulated with the clients info