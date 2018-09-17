# Author: DISCOVERY QE
# Date Created: 06/10/2015

Feature: Verify SEO tags on Home Page - DOMESTIC, ISHIP and REGISTRY modes

  #http://mingle/projects/seo/cards/4432 & SEO_4432
  #http://mingle/projects/seo/cards/4641 & SEO_4641

  @use_regression @artifact_navapp @domain_marketing @priority_high @bat_refactored @mode_domestic
  Scenario: Home Page - Verify alternative tags are added for guest user in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I "should" see the <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.bloomingdales.com/..."> tag pointing to the corresponding URL added in the header section


  @use_regression @artifact_navapp @domain_marketing @priority_high @release_13J @use_seo @mode_domestic
  Scenario: Home Page - Verify rel="canonical" tag is added on Header for guest user in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I "should" see the canonical tag pointing to the corresponding URL added in the header section

  @use_regression @artifact_navapp @domain_marketing @priority_high @use_seo @mode_domestic
  Scenario: Home Page - Verify that meta description is between 101 - 158 characters for guest user in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify that Meta Description should have between 101 - 158 characters

  @use_regression @artifact_navapp @domain_marketing @priority_high @use_seo @mode_domestic
  Scenario: Home Page - Verify title is between 21 - 90 characters for guest user in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify that Page Title should have between 21 - 90 characters

  #TBD - add scenarios for ISHIP & REGISTRY modes


    #http://mingle/projects/seo/cards/4390 & SEO_4390


  @artifact_navapp @domain_marketing @priority_high @release_13J @disable_env @use_manual @mode_domestic
  Scenario: Verify a brand suppressed in STELLA is not visible on the Brand Index Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to a brand index page in "normal" mode where a brand is suppressed
    Then I should not see suppressed brand in the Brand Index page


  @artifact_navapp @domain_marketing @priority_high @release_13J @registry @mode_registry @disable_env @use_manual
  Scenario: Verify a brand suppressed in STELLA is not visible on the Brand Index Page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to a brand index page in "registry" mode where a brand is suppressed
    Then I should not see suppressed brand in the Brand Index page


  @artifact_navapp @domain_marketing @priority_high @release_13J @mode_iship @iship_3 @use_manual
  Scenario: Verify a brand suppressed in STELLA is not visible on the Brand Index Page in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "Canada"
    And I verify the flag for "CA"
    When I navigate to a brand index page in "normal" mode where a brand is suppressed
    Then I should not see suppressed brand in the Brand Index page

  @use_regression @artifact_navapp @domain_marketing @priority_high @use_seo @mode_domestic
  Scenario: As a customer, I want to verify that the domestic homepage has the correct alternate tag in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify page url matches alternate tag url
    And I verify alternate tag url references mobile page

  @use_regression @artifact_navapp @domain_marketing @priority_high @use_seo @mode_domestic
  Scenario: As a customer, I want to verify that the homepage has the correct canonical tag in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I "should" see the canonical tag pointing to the corresponding URL added in the header section


  @use_regression @artifact_navapp @domain_marketing @priority_high @release_13J @use_manual @wip @mode_domestic
  Scenario: Suppress brands as indicated by stella on one category in Brand Index Page, should be visible on other category in Brand Index Page in DOMESTIC mode
    Given I visit the web site as a guest user
    And I navigate to Brand Index page of specific FOB category in "Dosmetic" mode
    When CAT_SUPPRESSED_BRAND attribute is set true for "<brand>" in STELLA
    And I should see "<brand>" in Brand Index page of other FOB category

  @use_regression @artifact_navapp @domain_marketing @priority_high @release_13J @use_manual @wip @mode_registry
  Scenario: Suppress brands as indicated by stella on one category in Brand Index Page, should be visible on other category in Brand Index Page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I navigate to Brand Index page of specific FOB category in "Registry" mode
    When REGISTRY_SUPPRESSED_BRAND attribute is set true for "<brand>" in SDP
    And I should see "<brand>" in Brand Index page of other FOB category

  @use_regression @artifact_navapp @domain_marketing @priority_high @release_13J @use_manual @wip @mode_domestic
  Scenario: Suppress brands as indicated by stella in iship mode on one category in Brand Index Page, should be visible on other category in Brand Index Page
    Given I visit the web site as a guest user
    And I change country to "Canada"
    And I navigate to Brand Index page of specific FOB category in "iShip" mode
    When CAT_SUPPRESSED_BRAND attribute is set true for "<brand>" in STELLA
    And I should see "<brand>" in Brand Index page of other FOB category
