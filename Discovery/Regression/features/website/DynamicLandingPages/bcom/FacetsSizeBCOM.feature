#Author: Discovery QE
#Date Created: 10/31/2016

Feature: Verifying Size Facets in DLP

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario: Dynamic Landing Page - Verify the size facet appearance and json response in DOMESTIC mode
    Given I am on DynamicLandingPage in "normal" mode with size facet
    And I clear existing class variable data to avoid data issues
    And I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see value "MULTISELECTBUTTON" for the key "facetType" in the JSON response at top-level groups
    And I should see value "MULTISELECTDEFAULT" for the key "facetType" in the JSON response at first-level groups

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario: Dynamic Landing Page - Verify the size facet appearance and json response in ISHIP mode
    Given I am on DynamicLandingPage in "iship" mode with size facet
    And I clear existing class variable data to avoid data issues
    And I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see value "MULTISELECTBUTTON" for the key "facetType" in the JSON response at top-level groups
    And I should see value "MULTISELECTDEFAULT" for the key "facetType" in the JSON response at first-level groups

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic @wip
  Scenario: Dynamic Landing Page - Verify the size facet functionality in DOMESTIC mode
    Given I am on DynamicLandingPage in "normal" mode
    And I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see the multiple values in a single facet
    And I click on the size facet family
    When I click on any size facet item
    Then I should verify product count from JSON response against UI
    And I should see the selected facets gets highlighted
    And I should see the breadcrumb
    When I deselect a selected facet
    Then I should see the selected facet gets deselected
    When I click on multiple size facet items
    Then I should see the breadcrumb
    And I should see the clear all button
    When I deselect a selected facet
    Then I should see the selected facet gets deselected

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic @wip
  Scenario: Dynamic Landing Page - Verify the size facet functionality in ISHIP mode
    Given I visit the web site as a guest user
    When I change country to "Canada"
    And I close the welcome mat if it's visible
    And I verify the flag for "CA"
    When I navigate to DLP
    And I click the selected option in DLP
    When I click on the size facet family
    And I should see the new size facet family
    And I should see the multiple values in a single facet
    And I click on the size facet family
    When I click on any size facet item
    Then I should verify product count from JSON response against UI
    And I should see the selected facets gets highlighted
    And I should see the breadcrumb
    When I deselect a selected facet
    Then I should see the selected facet gets deselected
    When I click on multiple size facet items
    Then I should see the breadcrumb
    And I should see the clear all button
    When I deselect a selected facet
    Then I should see the selected facet gets deselected

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario: Dynamic Landing Page - Verify the node level of facet size group in page against json response in DOMESTIC mode
    Given I am on DynamicLandingPage in "normal" mode with size facet
    And I clear existing class variable data to avoid data issues
    And I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see below keys in the JSON response at top-level groups
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
      | collapsed      |
    And I should see value "MULTISELECTBUTTON" for the key "facetType" in the JSON response at top-level groups
    And I should see below keys in the JSON response at first-level groups
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
      | collapsed      |
    And I should see value "MULTISELECTDEFAULT" for the key "facetType" in the JSON response at first-level groups
    And I should see below keys in the JSON response at second-level groups
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
    And I should see value "MULTISELECTDEFAULT" for the key "facetType" in the JSON response at second-level groups

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario:Dynamic Landing Page -  Verify the node level of facet size group in the page against Json response in ISHIP mode
    Given I am on DynamicLandingPage in "iship" mode with size facet
    And I clear existing class variable data to avoid data issues
    And I click on "Size" facet header on left nav
    Then I verify that new size facet family is displayed
    And I should see below keys in the JSON response at top-level groups
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
      | collapsed      |
    And I should see value "MULTISELECTBUTTON" for the key "facetType" in the JSON response at top-level groups
    And I should see below keys in the JSON response at first-level groups
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
      | collapsed      |
    And I should see value "MULTISELECTDEFAULT" for the key "facetType" in the JSON response at first-level groups
    And I should see below keys in the JSON response at second-level groups
      | name           |
      | display name   |
      | facet type     |
      | robot readable |
    And I should see value "MULTISELECTDEFAULT" for the key "facetType" in the JSON response at second-level groups
