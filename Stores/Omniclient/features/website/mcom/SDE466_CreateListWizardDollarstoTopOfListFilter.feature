# Author: Claudiu Chirila
# Story: SDE-466 Create List Wizard: Dollars to Top of List Filter
# Date Created:
# Date Signed Off:

Feature: As an associate, I want to filter customers by customer/loyalty/shopping attributes,
  in order to create a customized list of my clients for personalized outreach.


  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view

  @mcom @domain_stores @omniclient @website @story_SDE-466
  Scenario: Dollars to Top of List - View
    And I should see the "Dollars to Platinum" filter criteria Header Loyalty Dollar
    And I should see the following options below Dollars to Platinum filter
      | $200 or Less |
      | $500 or Less |
    When I click the chevron of Loyalty section filter criteria
    Then I should see the Dollars to filter criteria collapsed
      | $200 or Less               |
      | $500 or Less               |
      | Points to Next Reward      |
      | Star Rewards upgrade dates |
    When I click the chevron of Loyalty section filter criteria
    Then I should see the Dollars to filter criteria expanded
      | $200 or Less               |
      | $500 or Less               |
      | Points to Next Reward      |
      | Star Rewards upgrade dates |

  @mcom @domain_stores @omniclient @website @story_SDE-466
  Scenario: Dollars to Top of List - Selecting/Deselecting filter option
    # the changing of the customer list in the preview pane based on selection may need to be tested manually
    # some investigation is needed first, the scenario will be updated accordingly after
    When I select a filter criteria from Loyalty Dollars to Top of List
      | $200 or Less |
    Then I should see the filter criteria selected from Loyalty Dollars to Top of List
      | $200 or Less |
    When I remove the filter criteria from Loyalty Dollars to Top of List
      | $200 or Less |
    Then I should no longer see the filter criteria selected from Loyalty Dollars to Top of List
      | $200 or Less |

    When I select a filter criteria from Loyalty Dollars to Top of List
      | $500 or Less |
    Then I should see the filter criteria selected from Loyalty Dollars to Top of List
      | $500 or Less |
    When I remove the filter criteria from Loyalty Dollars to Top of List
      | $500 or Less |
    Then I should no longer see the filter criteria selected from Loyalty Dollars to Top of List
      | $500 or Less |
