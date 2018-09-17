# Author: Ovidiu Rucoi
# Story: SDE-222 - MOBILE: Remove Previous Search Results
# Date Created: 07/24/2017
# Date Signed Off:

Feature: As an associate using the mobile application, I no longer want to see previous search results after I perform
  a new search so that I can see the results of my most recent search.


  @domain_stores @omniclient @Story_SDE-222 @MEW @mcom
  Scenario: First/Last Name Zip Search - No Results Found
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select FirstLastNameZip button from the Search All Clients page
    Then I should see the FirstLastNameZip search page
    When I input first name "Hgghgh" on the FirstLastNameZip search page
    And I input last name "Hhhh" on the FirstLastNameZip search page
    And I input the zip code "12345" on the FirstLastNameZip search page
    And I click the Search button on the Search All Clients page
    Then I should see the number of search results found on the Results page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 |
    When I input first name "NORESULTS" on the FirstLastNameZip search page
    And I input last name "TEST" on the FirstLastNameZip search page
    And I input the zip code "00000" on the FirstLastNameZip search page
    And I click the Search Again button on the Search Results page
    Then I should see No Results Found on the Results page
    And I should not see the previous search results

  @domain_stores @omniclient @Story_SDE-222 @MEW @mcom
  Scenario: Telephone Search - No Results Found
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
      | Name | AddressLine1 |
    When I input telephone number "4404404401" on the Telephone Number search page
    And I click the Search Again button on the Search Results page
    Then I should see No Results Found on the Results page
    And I should not see the previous search results