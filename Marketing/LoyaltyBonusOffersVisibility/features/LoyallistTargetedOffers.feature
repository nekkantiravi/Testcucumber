#Author: Loyalty Bonus Offers Visibility Team
#Date Created: May 13, 2015
#Date Modified:

#VersionOne story link: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story%3A220415

Feature: As a signed in Loyallist, I want to see any targeted offers displayed that apply to me in the dashboard.

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Verify targeted offer information for a loyallist customer, on wallet dashboard
    Given I visit the web site as a loyallist
    When I have targeted offer associated to my loyalty
    Then I verify loyallist targeted offer on wallet dashboard

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Verify targeted offers expiring on same date information for a loyallist customer, on wallet dashboard
    Given I visit the web site as a loyallist
    When I have multiple targeted offers exp_same_day associated to my loyalty
    Then I verify loyallist targeted offer on wallet dashboard

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Verify targeted offers expiring on different date information for a loyallist customer, on wallet dashboard
    Given I visit the web site as a loyallist
    When I have multiple targeted offers exp_diff_day associated to my loyalty
    Then I verify loyallist targeted offer on wallet dashboard

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario Outline: Verify targeted offer information for a loyallist customer, on wallet dashboard in browse and search pages
    Given I visit the web site as a loyallist
    When I navigate to "<page_type>" page
    And I have targeted offer associated to my loyalty
    Then I verify loyallist targeted offer on wallet dashboard
    Examples:
      | page_type       |
      | browse          |
      | search          |
      | product_display |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Verify targeted offer information for a loyallist customer, on wallet dashboard in My Account page
    Given I visit the web site as a loyallist
    When I navigate to 'My Account' page
    And I have targeted offer associated to my loyalty
    Then I verify loyallist targeted offer on wallet dashboard

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Verify targeted offer information for a loyallist customer, on wallet dashboard in registry mode
    Given I visit the web site as a loyallist
    When I navigate to registry home page
    And I have targeted offer associated to my loyalty
    Then I verify loyallist targeted offer on wallet dashboard

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Verify if a user moves off of the dashboard while the info exclusions section is expanded, and then hovers over it again the info exclusions section should be collapsed
    Given I visit the web site as a loyallist
    When I have targeted offer associated to my loyalty
    And I expand info exclusions section and moves off of the dashboard
    Then I should see info exclusions section collapsed on next hover

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Verify if targeted offer has been redeemed, it will no longer display in the dashboard
    Given I visit the web site as a loyallist
    When I have targeted offer associated to my loyalty
    Then I verify loyallist targeted offer on wallet dashboard
    When I have redeemed the targeted offer
    Then I should not see loyallist targeted offer on wallet dashboard

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Verify if loyallist customer is not having any associated targeted offer, then it should not display on wallet dashboard
    Given I visit the web site as a loyallist
    Then I should not see loyallist targeted offer on wallet dashboard

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Verify if loyallist customer is having multiple targeted offers, one has been redeemed, then the second offer will display in the dashboard
    Given I visit the web site as a loyallist
    When I have multiple targeted offers associated to my loyalty
    Then I verify loyallist targeted offer on wallet dashboard
    When I have redeemed the targeted offer displayed on dashboard
    Then I verify loyallist targeted offer on wallet dashboard

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15K @artifact_shopapp @use_iteration @defect_D-18282
  Scenario Outline: Verify if loyallist customer is having targeted offer with missing required attribute, then it should not display on wallet dashboard
    Given I visit the web site as a loyallist
    When I have targeted offer without <attribute> associated to my loyalty
    Then I should not see loyallist targeted offer on wallet dashboard
    Examples:
      | attribute         |
      | offer title       |
      | offer description |
   # | info-exclusions   |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15K @artifact_shopapp @use_iteration @defect_D-18282
  Scenario Outline: Verify if loyallist customer is having multiple targeted offers, 1st offer is missing required attribute, then the second offer will display in the dashboard
    Given I visit the web site as a loyallist
    When I have targeted offer without <attribute> associated to my loyalty
    And  I have another targeted offer associated to my loyalty
    Then I verify loyallist targeted offer on wallet dashboard
    Examples:
      | attribute         |
  #    | offer title       |
      | offer description |
      | info-exclusions   |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Verify if loyallist customer is having targeted offer without loyallist flag attribute, then it should not display on wallet dashboard
    Given I visit the web site as a loyallist
    When I have targeted offer without loyallist flag associated to my loyalty
    Then I should not see loyallist targeted offer on wallet dashboard