# Date Created: 01/25/2018

Feature: Verify SEO tags

  @use_regression @domain_marketing
  Scenario: Verify that PROPER homepage has correct canonical and alternative tags
    Given I visit the mobile web site as a guest user in domestic mode
    Then I should see canonical tag references the desktop domain
    And I should see that "cm_sp" is not appended to canonical tag url


  @use_regression @domain_marketing
  Scenario: Verify that PROPER category browse pages have correct canonical and alternative tags
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to random mobile category browse page
    Then I should see canonical tag references the desktop domain
    And I should see that "cm_sp" is not appended to canonical tag url


  @use_regression @domain_marketing
  Scenario: Verify that PROPER catsplash pages have correct canonical and alternative tags
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to random mobile category splash page
    Then I should see canonical tag references the desktop domain
    And I should see that "cm_sp" is not appended to canonical tag url


  @use_regression @domain_marketing
  Scenario: Verify that PROPER DLP pages have correct canonical and alternative tags
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to random mobile dynamic landing page
    Then I should see canonical tag references the desktop domain
    And I should see that "cm_sp" is not appended to canonical tag url


  @use_regression @domain_marketing @wip
  Scenario: Verify the DLP should canonicalize and alternate to a shorter version when navigated from the Designer Index page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to Designers index page
    And I navigate to random brand from Designer Index page
    Then I should see canonical tag is shorter version
    And I should see canonical tag references the desktop domain


  @use_regression @domain_marketing @wip
  Scenario: Verify that PDP canonical tag is shorter version and ends at product id
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to a random member product
    Then I should see the canonical tag ends at product id
    And I should see canonical tag references the desktop domain


  @use_regression @domain_marketing @wip
  Scenario Outline: Verify that only one H1 tag is present on pages in DOMESTIC MODE
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "<page>" page in "domestic" mode
    Then I should see only one H1 tag is present
    Examples:
      | page            |
      | search result   |
      | category browse |
      | category splash |
      | DLP             |
      | PDP member      |
      | PDP master      |


  @use_regression @domain_marketing @wip
  Scenario Outline: Verify that title tag is present on pages in DOMESTIC MODE and has a minimum of 10 characters
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to "<page>" page in "domestic" mode
    Then I should see title tag is present on pages in DOMESTIC MODE and has a minimum of 10 characters
    Examples:
      | page            |
      | search result   |
      | category browse |
      | category splash |
      | DLP             |
      | PDP member      |
      | PDP master      |


  @use_regression @domain_marketing
  Scenario: Verify webmaster tool Bing
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify the bing webmaster metadata is present in the head tag

  @use_regression @domain_marketing
  Scenario: Verify webmaster tool Google
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify the google metadata is present in the head tag


