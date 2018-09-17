# Author: Ovidiu Rucoi
# Story: SDE-223 - MOBILE: Return to Search Results Screen
# Date Created: 07/25/2017
# Date Signed Off:

Feature: As an associate using the mobile application, I want to be able to browse my search results without having to
  perform a secondary search so that I can efficiently navigate large search results and find the right customer.


  @domain_stores @omniclient @Story_SDE-223 @MEW @mcom
  Scenario: First/Last Name Zip Search - Return to Search Results Screen
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select FirstLastNameZip button from the Search All Clients page
    Then I should see the FirstLastNameZip search page
    When I input first name "Sindy" on the FirstLastNameZip search page
    And I input last name "A" on the FirstLastNameZip search page
    And I input the zip code "10022" on the FirstLastNameZip search page
    And I click the Search button on the Search All Clients page
    Then I should see the number of search results found on the Results page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I click the Back button on the Client Profile page
    Then I should see the number of search results found on the Results page
    And I should see the first name "Sindy", last name "A", zip "10022" search criteria displayed
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 |

  @domain_stores @omniclient @Story_SDE-223 @MEW @mcom
  Scenario: Telephone Search - Return to Search Results Screen
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "4405555555" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    Then I should see the number of search results found on the Results page
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 |
    When I click on the Client on the Results page
    Then I should see the Client Profile page
    When I click the Back button on the Client Profile page
    Then I should see the number of search results found on the Results page
    And I should see the telephone number "4405555555" search criteria displayed
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 |