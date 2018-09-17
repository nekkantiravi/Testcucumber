# Author: Ovidiu Rucoi
# Story: SDE-304 - Display FN LN Zip Search Results based on Search Criteria
# Date Created: 09/20/2017
# Date Signed Off:

Feature: As an associate, I want to see search results match the information I used to search so that I can avoid
  confusion and best serve my customer.

  @domain_stores @omniclient @story_SDE-304 @website @bcom
  Scenario: Add Client for Test
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
  #parameters in this order: first name, last name, address, city , zip, hint
    And I add a new BLM Client: "FIRSTNM" "LASTNM" "PRIMADDR" "PRIMCITY" "12345" "TESTHINT" "4445556666" "AL" "newemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    When click on ADD button from Address section BLM
    And we add additional address "SECADDR" "SECCITY" "54321" "WY" BLM
    And we click on SAVE button from Customer Info tab BLM
    And I click on the remove from book button
    And I click ok on the remove client popup screen

  @domain_stores @omniclient @story_SDE-304 @website @bcom
  Scenario: FN/LN Zip Search by Primary Address
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "FIRSTNM" in the input field
    And I enter last name "LASTNM" in the input field
    And I enter zip code "12345" in the input field
    And I click on the omniclient search button
    Then the searched client is displayed in the search results page "FIRSTNM LASTNM"
    And I should see the the following Address Information on the search results
      | PRIMADDR       | Address |
      | PRIMCITY       | City    |
      | AL             | State   |
      | 12345          | Zip     |
      | (444) 555-6666 | Phone   |
    When I click the Add to Book button from search results
    Then the Create new Client page is displayed
    And I should see the Create Client page prepopulated with the following Address Information
      | PRIMADDR       | Address |
      | PRIMCITY       | City    |
      | AL             | State   |
      | 12345          | Zip     |
      | (444) 555-6666 | Phone   |

  @domain_stores @omniclient @story_SDE-304 @website @bcom
  Scenario: FN/LN Zip Search by Secondary Address
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the FIRST,LAST NAME & ZIP radio button
    And I enter first name "FIRSTNM" in the input field
    And I enter last name "LASTNM" in the input field
    And I enter zip code "54321" in the input field
    And I click on the omniclient search button
    Then the searched client is displayed in the search results page "FIRSTNM LASTNM"
    And I should see the the following Address Information on the search results
      | SECADDR        | Address |
      | SECCITY        | City    |
      | WY             | State   |
      | 54321          | Zip     |
      | (444) 555-6666 | Phone   |
    When I click the Add to Book button from search results
    Then the Create new Client page is displayed
    And I should see the Create Client page prepopulated with the following Address Information
      | SECADDR        | Address |
      | SECCITY        | City    |
      | WY             | State   |
      | 54321          | Zip     |
      | (444) 555-6666 | Phone   |
