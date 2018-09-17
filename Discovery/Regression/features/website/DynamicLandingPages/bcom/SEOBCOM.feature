# Author: DISCOVERY QE
# Date Created: 06/12/2016

Feature: Verify SEO tags on Browse Page - DOMESTIC, ISHIP and REGISTRY modes

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario: DLP Page - Verify that DLP pages have a char-count > 20 but < 65 and Bloomingdale's in the page title in DOMESTIC mode
    Given I am on DynamicLandingPage in "normal" mode
    Then I verify that Page Title should have between 20 - 65 characters
    And I verify that the page title contains "Bloomingdale's"

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario: DLP Page - Verify that DLP pages have a char-count > 100 but < 155 for the meta description in DOMESTIC mode
    Given I am on DynamicLandingPage in "normal" mode
    Then I verify that Meta Description should have between 100 - 155 characters

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario: DLP Page - Verify that DLP pages have the correct canonical tag in DOMESTIC mode
    Given I am on DynamicLandingPage in "normal" mode
    Then I "should" see canonical tag pointing to the corresponding URL added in the header section



