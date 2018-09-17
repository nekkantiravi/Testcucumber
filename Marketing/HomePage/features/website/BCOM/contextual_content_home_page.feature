# Story:    19490: WDS :: BCOM :: Render Contextual Template for Homepage
# Author:   Vijay Suraneni
# Date:     2nd May, 2014
# Reviewer:
# Date Signed Off:


Feature: WDS :: BCOM :: Render Contextual Template for Homepage

######################## Context: Domestic, Desktop ############################

  @artifact_navapp @domain_marketing @release_15A @use_regression @feature_c2 @disable_defect
  Scenario: Home Page - Verify rows are contextualized on Home Page on desktop in DOMESTIC mode
    Given I visit the web site as a guest user
    When I verify I land on the Home Page
    Then I should see "JSP" on the page in "101" row
    And I should see respective media as per astra data


  ######################## Context: International, Desktop ############################

  @artifact_navapp @domain_discovery @release_15A @feature_c2 @mode_iship @disable_to @creative_merge
  Scenario Outline: Home Page - Verify rows are contextualized on Home Page on desktop in ISHIP mode
    Given I visit the web site as a guest user
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to "Home Page" category with "<media>" in "<row_type>" for context
      | DEVICE_TYPE | REGION_CODE | SHOPPING_MODE | NAVIGATION_TYPE |
      | DESKTOP     | INTL        | SITE          | BROWSE          |
    Then I should see "<media>" on the page in "<row_type>" row
    And I should see respective media as per astra data
    Examples:
      | row_type | media     |
      | 101      | JSP       |




  ###############################################################################################
  # Mingle Link       : http://mingle/projects/discovery/cards/21888
  # WDS :: MCOM:: BCOM :: ListBrands Migration from Hessian to REST
  ################################## Registry Mode ########################################################

  @artifact_navapp @domain_marketing @release_15A @use_regression @feature_c2 @mode_registry
  Scenario: Home page - Verify list brands are rendering in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    And I should see Registry Home page
    When I navigate to category page
      | BCOM | Brands |
    Then I should see list of brands
    And I click on facet

  @artifact_navapp @domain_marketing @release_15A @use_regression @feature_c2 @mode_registry @disable_env
  Scenario: Home page - Verify list brands are rendering when navigated from flyout menu in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to registry home page
    And I should see Registry Home page
    When I navigate to category page from flyout menu
      | BCOM | Brands |
    Then I should see list of brands
    And  I click on facet
