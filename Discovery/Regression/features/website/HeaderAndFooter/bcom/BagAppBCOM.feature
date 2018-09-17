Feature: Verification of BCOM BagApp functionalities in SITE,ISHIP,REGISTRY modes.

  #########################################################  Bloomingdales Logo link ###########################################

  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_domestic
  Scenario Outline: Verify brown bag default text as a guest and registered user in SITE, ISHIP,REGISTRY modes.
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    Then I verify quick bag text in header for "<user_type>" user
    When I hover over the quick bag
    Then I should see quick bag overlay
    And I should see "Your brown bag is empty." and X button is displayed in quick bag
    Examples:
      | user_type  |  mode     |
      | guest      |  site     |
      | guest      |  iship    |
      | guest      |  registry |
      | registered |  site     |
      | registered |  registry |


  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_domestic
  Scenario Outline: Verify quick bag items as a guest and registered user in SITE, ISHIP,REGISTRY modes.
    Given I visit the web site as a "<user_type>" user in "<mode>" mode
    When I add 2 random product to quick bag in "<mode>" mode
    When I hover over the quick bag
    Then I should see quick bag overlay
    And I should see the added products in quick bag
    Examples:
      | user_type | mode |
      | guest     | site |
#      | guest      | iship    |
#      | guest      | registry |
#      | registered | site     |
#      | registered | registry |

  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_domestic
  Scenario: Verify currency when switch from SITE mode to ISHIP mode
    Given I visit the web site as a "guest" user in "site" mode
    When I add 1 random product to quick bag in "site" mode
    And I navigate to international context page
    And I change country to "India"
    When I hover over the quick bag
    Then I should see currency changed from "site" mode to other country in quick bag


  @use_regression @priority_high @artifact_navapp @domain_discovery @mode_domestic @test
  Scenario: Verify currency when switch from ISHIP mode to SITE mode
    Given I visit the web site as a "guest" user in "iship" mode
    When I add 1 random product to quick bag in "iship" mode
    And I navigate to international context page
    And I select 'ship to US address' button on International Home Page
    When I hover over the quick bag
    Then I should see currency changed from "iship" mode to other country in quick bag