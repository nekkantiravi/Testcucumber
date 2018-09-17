#Author: Discovery QE
#Date Created: 06/12/2015

Feature: Verifying Facets in DLP

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: Category Browse page -  Verify any facets are expandable and collapsible in ALL modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    And I should see facets listed on left nav
    When I click on any "collapsed" facet
    Then I verify that the selected facet is "expanded"
    When I click on any "expanded" facet
    Then I verify that the selected facet is "collapsed"
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |


  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: Category Browse page -  Verify multiple facets selection in ALL modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode
    And I clear existing class variable data to avoid data issues
    Then I verify that the product count is displayed
    And I should see facets listed on left nav
    When I select a random facet item from each of the facets and check the breadcrumbs
    Then I verify that products are filtered as per selected facet value
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |
