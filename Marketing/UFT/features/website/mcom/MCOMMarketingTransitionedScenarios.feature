# Author: Marketing Regression QE Team
# Created Date: 11/02/2017

Feature: MCOM :: Marketing scenarios transitioned from UFT

####################################### Version One: B-88998  #######################################
  @domain_marketing @artifact_navapp @project_UFT @priority_medium @release_17Q @use_regression
  Scenario Outline: Verify the url in canonical tag contains lower case letters of brand name on SLP page
    Given I visit the web site as a guest user
    When I add "<url>" to the current url
    Then I verify the url in canonical tag contains expected value "<brand_name>"
    Examples:
      | url                                 | brand_name          |
      | shop/b/Cast-Iron-Furniture?id=78473 | cast-iron-furniture |
      | shop/b/Nursing-Dresses?id=80698     | nursing-dresses     |

####################################### Version One: B-89028  #######################################
  @domain_marketing @artifact_navapp @project_UFT @priority_medium @release_17Q @use_regression
  Scenario Outline: Verify the space special character in canonical tag URL is replaced by special keyword on SLP page
    Given I visit the web site as a guest user
    When I add "<url>" to the current url
    And I select "multiple" facet value from "Brand" facet section
    Then I verify the url in canonical tag contains expected value "<special_keyword>"
    Examples:
      | url                             | special_keyword |
      | shop/b/Bath-Sets?id=79100       | %20             |
      | shop/b/Nursing-Dresses?id=80698 | %20             |

####################################### Version One: B-78529  #######################################
  @artifact_bagapp @mode_domestic @release_17I @priority_medium @domain_marketing @project_UFT @use_regression
  Scenario: Verify updated error message is displayed when user click apply button without entering a promo-code on bag page
    Given I visit the web site as a guest user
    And I add a "promo_code_eligible" product to my bag
    And I navigate to shopping bag page
    When I click apply button without entering promo code
    Then I should see the error message "You haven't entered a promo code yet. Please enter it now." on shopping bag page

####################################### Version One: B-75177  #######################################
  @artifact_shopapp @mode_domestic @release_17H @priority_low @domain_marketing @project_UFT @use_regression
  Scenario: Verify UI changes on Plenti Sign in page
    Given I visit the web site as a guest user
    When I navigate to Plenti Sign in page by using direct url
    Then I verify UI changes on Plenti Sign in page
