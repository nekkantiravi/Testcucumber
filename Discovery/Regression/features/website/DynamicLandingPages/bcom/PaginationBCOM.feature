#Author: Discovery QE
#Date Created: 28/11/2016

Feature: Verifying Pagination in DLP Page

  @use_regression @domain_discovery @mode_registry @mode_iship @mode_domestic
  Scenario Outline: Search Result page - Ensure Pagination displays the product thumbnails on the dynamic landing page in ALL modes
    Given I am on DynamicLandingPage in "<shopping_mode>" mode with pagination
    When I click 2 pagination number
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I click on next pagination button
    Then I verify that navigated to page 3 on search result page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    When I click on previous pagination button
    Then I verify that navigated to page 2 on search result page
    And I verify that all the product thumbnails displayed properly on the Dynamic Landing page
    Examples:
      | shopping_mode |
      | normal        |
      | registry      |
      | iship         |