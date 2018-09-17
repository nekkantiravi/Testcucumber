# Author: Ovidiu Rucoi
# Story: SDE-521 Create List Wizard: Loyalty Level Filter
# Date Created:
# Date Signed Off:

Feature: As an associate, I want to filter customers by customer/loyalty/shopping attributes, in order
  to create a customized list of my clients for personalized outreach.

  Background:
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate on Create To Dos tab
    Then I should see the Create List dashboard
    When I click the Create Custom List button on the Create List dashboard
    Then I should see the New List view

  @mcom @domain_stores @omniclient @website @story_SDE-521
  Scenario: Star Rewards Level Filter - View
    And I should see the "Star Rewards Level" filter criteria Header Loyalty
    And I should see the following options below Loyalty Level filter
      | All Star Rewards / All Loyallists |
      | Platinum / Top of List            |
      | Gold / BCC Loyallist              |
      | Silver / Non-BCC Loyallists       |
      | Non-Star Rewards / Non-Loyallists |
    When I click the chevron of Loyalty section filter criteria
    Then I should see the Loyalty Level filter criteria collapsed
      | All Star Rewards / All Loyallists |
      | Platinum / Top of List            |
      | Gold / BCC Loyallist              |
      | Silver / Non-BCC Loyallists       |
      | Non-Star Rewards / Non-Loyallists |
    When I click the chevron of Loyalty section filter criteria
    Then I should see the Loyalty Level filter criteria expanded
      | All Star Rewards / All Loyallists |
      | Platinum / Top of List            |
      | Gold / BCC Loyallist              |
      | Silver / Non-BCC Loyallists       |
      | Non-Star Rewards / Non-Loyallists |

  @mcom @domain_stores @omniclient @website @story_SDE-521
  Scenario: Star Rewards Level Filter - Selecting/Deselecting filter option
    # the changing of the customer list in the preview pane based on selection may need to be tested manually
    # some investigation is needed first, the scenario will be updated accordingly after

    When I select a filter criteria from Loyalty Level section
      | All Star Rewards / All Loyallists |
    Then I should see the filter criteria selected from Loyalty Level section
      | All Star Rewards / All Loyallists |
    When I remove the filter criteria from Loyalty Level section
      | All Star Rewards / All Loyallists |
    Then I should no longer see the filter criteria selected from Loyalty Level section
      | All Star Rewards / All Loyallists |

    When I select a filter criteria from Loyalty Level section
      | Platinum / Top of List |
    Then I should see the filter criteria selected from Loyalty Level section
      | Platinum / Top of List |
    When I remove the filter criteria from Loyalty Level section
      | Platinum / Top of List |
    Then I should no longer see the filter criteria selected from Loyalty Level section
      | Platinum / Top of List |


    When I select a filter criteria from Loyalty Level section
      | Gold / BCC Loyallist |
    Then I should see the filter criteria selected from Loyalty Level section
      | Gold / BCC Loyallist |
    When I remove the filter criteria from Loyalty Level section
      | Gold / BCC Loyallist |
    Then I should no longer see the filter criteria selected from Loyalty Level section
      | Gold / BCC Loyallist |

    When I select a filter criteria from Loyalty Level section
      | Silver / Non-BCC Loyallists |
    Then I should see the filter criteria selected from Loyalty Level section
      | Silver / Non-BCC Loyallists |
    When I remove the filter criteria from Loyalty Level section
      | Silver / Non-BCC Loyallists |
    Then I should no longer see the filter criteria selected from Loyalty Level section
      | Silver / Non-BCC Loyallists |

    When I select a filter criteria from Loyalty Level section
      | Non-Star Rewards / Non-Loyallists |
    Then I should see the filter criteria selected from Loyalty Level section
      | Non-Star Rewards / Non-Loyallists |
    When I remove the filter criteria from Loyalty Level section
      | Non-Star Rewards / Non-Loyallists |
    Then I should no longer see the filter criteria selected from Loyalty Level section
      | Non-Star Rewards / Non-Loyallists |

