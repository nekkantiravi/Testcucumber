# Story: Create List - Pre-Generated List - Quick list titles should be auto filled into Create To Dos Page
# Date Created: 01/24/2018
# Date Signed Off:

Feature: As a Create List User, I want the title for a Quick List (Pre-Gen List) to fill in automatically,
  so I can save time and publish my list more efficiently

  Background:
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard

  @domain_stores @omniclient @story_SDE-532 @website @bcom
  Scenario: Verify Customers Who Have Not Shopped in a Store pre-populated title
    When I select the title Cust Who Have Not Shopped on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
    And I should see the following title in the title input box
      | Did Not Shop (Last 90 Days) |

  @domain_stores @omniclient @story_SDE-532 @website @bcom
  Scenario: Verify Upcoming Birthdays pre-populated title
    When I select the title Upcoming Birthdays on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
    And I should see the following title in the title input box
      | Upcoming Birthdays (Next 30 Days) |

  @domain_stores @omniclient @story_SDE-532 @website @bcom
  Scenario: Verify My Top 25 Clients pre-populated title
    When I select the title My Top 25 Clients on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
    And I should see the following title in the title input box
      | My Top 25 Customers (Last 12 Months) |

  @domain_stores @omniclient @story_SDE-532 @website @bcom
  Scenario: Verify New Accounts Opened pre-populated title
    When I select the title Opened a New Credit Account with Me on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    When I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
    And I should see the following title in the title input box
      | New Accounts Opened w/Me (Last 90 Days) |

  @domain_stores @omniclient @story_SDE-532 @website @bcom
  Scenario: Verify My Top of List Customers pre-populated title
    When I select the title My Top of List Customers on the Create List dashboard
    Then the selected pregenerated list will display on the Create List dashboard
    And I select the Create To Dos button from Create List dashboard
    Then the CREATE MY LIST Screen is displayed
    And I should see the following title in the title input box
      | My Top of List Customers |