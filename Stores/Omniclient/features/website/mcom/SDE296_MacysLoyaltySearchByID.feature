# Author: Claudiu Chirila
# Story: SDE-296 - Macy's Loyalty; Search by Loyalty ID
# Date Created:
# Date Signed Off:

Feature:As a My Client User I want to be able to search for a customer by their Loyalty ID,
  so that I can provide better customer service and help drive Loyalty and Clienteling to increase customer satisfaction and drive sales.

  @domain_stores @omniclient @story_SDE-296 @website @mcom
  Scenario: Positive Result if customer that exists in OCL database: takes user to Search Results

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the SearchByID radio button
    # ID of a customer that exists in OCL database - in this case is KONSTANCE BACKET
    And I input "L930000100120" SearchByID in the search box
    And I click on the omniclient search button
    Then the search result page is displayed with the following columns:
      | Name                           |
      | Address                        |
      | Phone Number                   |
      | My Relationship                |
      | Relationship w/other Associate |
    And the searched client is displayed in the search results page "KONSTANCE BACKET"


    #    TODO: when the A/C #5 will be implemented we need to add coverage for this(for now it was removed from the story)
#  @domain_stores @omniclient @story_SDE-296 @website @mcom
#  Scenario: Positive Result for customer that does not exist in OCL database : takes user to Customer's Loyalty page
#
#    Given I launch the macy's omniclient page
#    When I sign into Omniclient application as associate
#    And I click on the SearchByID radio button
#    # ID of a customer that doesn't exists in OCL database
#    And I input "id" SearchByID in the search box
#    And I click on the omniclient search button
#    Then the search result page is displayed with the following columns:
#      | Name                           |
#      | Address                        |
#      | Phone Number                   |
#      | My Relationship                |
#      | Relationship w/other Associate |
#    And the searched client is displayed in the search results page "NAME OF THE CLIENT that DOESNT exist in OCL db"

  @domain_stores @omniclient @story_SDE-296 @website @mcom
  Scenario: Negative Result: error messaging for wrong Loyalty ID format or for Loyalty ID that is not found

    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on the SearchByID radio button
    And I input "5643634634734" SearchByID in the search box
    And I click on the omniclient search button
    Then error messaging for wrong or non existent Loyalty ID is displayed
      | Invalid Star Rewards ID|
    And I click on OK button in omniclient attention popup
    When I input "L930000100122" SearchByID in the search box
    And I click on the omniclient search button
    Then error messaging for wrong or non existent Loyalty ID is displayed
      | Non-existent Star Rewards ID |