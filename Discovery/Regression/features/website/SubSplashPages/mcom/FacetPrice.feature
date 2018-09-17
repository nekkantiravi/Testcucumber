# Author: DISCOVERY REGRESSION QE
# Date Migrated: 10/09/2017

Feature: Verifying Facets in Search Landing Page

  @domain_discovery @priority_high @mode_domestic @snbc_comp @use_regression
  Scenario: SubSplashPage - Domestic - Verify selected price facets are clearing upon selection of custom price facet value
    Given I visit the web site as a guest user
    And I clear existing class variable data to avoid data issues
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    And I verify that products are filtered as per selected facet value
    When I select minimum price as "10" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that only custom price facet is selected from price facet section
 # Notes:
 # Enter minimum abd max price range values and select 'GO' button
 # Verify all products are displayed only withing entered price range only
 # (Ex: if we enter $150-$500 price range values, then product prices should be in same range)
 # Verify entered price range value displayed in facet breadcrumb section.
 # Verify custom price range value is displayed in price facet section with 'Custom: $150 - $500' format as temporary facet.
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify 'X' icon displayed for price facet section.
 # Verify pagination updated as per current product count and defaulted to first page.
 # Verify all other pre-defined price facets are in de-selected mode.

  @domain_discovery @priority_high @mode_registry @snbc_comp @use_regression @discovery_daily_run
  Scenario: SubSplashPage - Registry - Verify selected price facets are clearing upon selection of custom price facet value
    Given I visit the web site as a guest user in "registry" mode
    And I clear existing class variable data to avoid data issues
    When I navigate to the "Anolon" sub splash page under "KITCHEN"
    When I select "multiple" facet value from "Price" facet section
    Then I verify that products are filtered with selected price facet values
    And I verify that products are filtered as per selected facet value
    When I select minimum price as "10" and maximum price as "500" range
    And I select 'GO' button from price facet
    Then I verify that products are filtered with selected price facet value
    And I verify that only custom price facet is selected from price facet section
 # Notes:
 # Enter minimum abd max price range values and select 'GO' button
 # Verify all products are displayed only withing entered price range only
 # (Ex: if we enter $150-$500 price range values, then product prices should be in same range)
 # Verify entered price range value displayed in facet breadcrumb section.
 # Verify custom price range value is displayed in price facet section with 'Custom: $150 - $500' format as temporary facet.
 # Verify 'CLEAR ALL' button displaying on top of facet section(beside 'filter by' header).
 # Verify 'X' icon displayed for price facet section.
 # Verify pagination updated as per current product count and defaulted to first page.
 # Verify all other pre-defined price facets are in de-selected mode.