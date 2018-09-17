# Author: Claudiu Chirila
# Story:  BLM Loyalty; Search by Loyalty ID
# Date Created:
# Date Signed Off:

Feature:As a My Client User I want to be able to search for a customer by their Loyalty ID,
  so that I can provide better customer service and help drive Loyalty and Clienteling to increase customer satisfaction and drive sales.

  @domain_stores @omniclient @story_SDE-296 @website @mcom
  Scenario: Positive Result if customer that exists in OCL database: takes user to Search Results

    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the SearchByID radio button
    # ID of a customer that exists in OCL database - in this case is KONSTANCE BACKET
    And I input "L930000100120" SearchByID in the search box
    And I click on the omniclient search button
    Then the search result page is displayed with the following columns:
      | Name                                      |
      | Address                                   |
      | Phone Number                              |
      | Affiliated with Me                        |
      | Affiliated to Other Sales Professional(s) |
    And the searched client is displayed in the search results page "KONSTANCE BACKET"

#    TODO: when the A/C #5 will be implemented we need to add coverage for this(for now it was removed from the story)
#  @domain_stores @omniclient @story_SDE-296 @website @mcom
#  Scenario: Positive Result for customer that does not exist in OCL database : takes user to Customer's Loyalty page
#
#    Given I launch the bloomingdales's omniclient page
#    When I sign into Omniclient BLM application as associate
#    And I click on the SearchByID radio button
#    # ID of a customer that doesn't exists in OCL database
#    And I input "L920000170769" SearchByID in the search box
#    And I click on the omniclient search button
#    Then the Loyallist page is displayed
#    And the name of the customer is displayed in Loyallist page top table
#      | name of the customer |

  @domain_stores @omniclient @story_SDE-296 @website @mcom
  Scenario: Negative Result: error messaging for wrong Loyalty ID format or for Loyalty ID that is not found

    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on the SearchByID radio button
    And I input "5643634634734" SearchByID in the search box
    And I click on the omniclient search button
    Then error messaging for wrong or non existent Loyalty ID is displayed
      | Invalid Loyallist ID BLM |
    And I click on OK button in omniclient attention popup
    When I input "L930000103332" SearchByID in the search box
    And I click on the omniclient search button
    Then error messaging for wrong or non existent Loyalty ID is displayed
      | Non-existent Loyallist ID BLM |


