#Author: Discovery SNBC QE
#Date Created: 30/08/2017

Feature: Verify SubCategory Pages - Description

  # DML script is overridden with production data for copy block media. Hence we are adding @wip tag for backup.
  @use_dsv @dsv_category_2 @use_regression @domain_discovery @priority_high @wip @use_wip @snbc_comp @discovery_daily_run
  Scenario: BrowsePage - Verify Description - HOME - Kitchen
    Given I am on Browse Page for "Kitchen" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify the Description section for "Kitchen" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  # DML script is overridden with production data for copy block media. Hence we are adding @wip tag for backup.
  @use_dsv @dsv_category_18 @use_regression @domain_discovery @priority_high @wip @use_wip @snbc_comp
  Scenario: BrowsePage - Verify Description - HOME - Dining & Entertaining
    Given I am on Browse Page for "Dining & Entertaining" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify the Description section for "Dining-Entertaining" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Description - HOME - Furniture
    Given I am on Browse Page for "Furniture" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify the Description section for "Furniture" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  # DML script is overridden with production data for copy block media. Hence we are adding @wip tag for backup.
  @use_regression @domain_discovery @priority_high @defect_ECOMSST-43875 @wip @use_wip @snbc_comp
  Scenario: BrowsePage - Verify Description - HOME - Outdoor & Patio Furniture
    Given I am on Browse Page for "Outdoor & Patio Furniture" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify the Description section for "Outdoor Furniture" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  # DML script is overridden with production data for copy block media. Hence we are adding @wip tag for backup.
  @use_dsv @dsv_category_2 @use_regression @domain_discovery @priority_high @wip @use_wip @snbc_comp
  Scenario: BrowsePage - Verify Description - HOME - Rugs
    Given I am on Browse Page for "Rugs" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify the Description section for "Rugs" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Description - HOME - Lighting & Lamps
    Given I am on Browse Page for "Lighting & Lamps" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify the Description section for "Lighting-Lamps" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_dsv @dsv_category_2 @use_regression @domain_discovery @priority_high @snbc_comp
    Scenario: BrowsePage - Verify Description - HOME - Bed & Bath
    Given I am on Browse Page for "Bed & Bath" under "HOME"
    And I modify the url to get in to snbc experiment
    Then I verify the Description section for "Bed & Bath" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars

  @use_regression @domain_discovery @priority_high @snbc_comp
  Scenario: BrowsePage - Verify Description - MEN - Boots
    Given I am on Browse Page for "Boots" under "MEN"
    And I modify the url to get in to snbc experiment
    Then I verify the Description section for "Boots" on category splash page
    # Notes:
    # Do full validation - make sure description contains the category name
    # Check description is at-least more than 50 chars
