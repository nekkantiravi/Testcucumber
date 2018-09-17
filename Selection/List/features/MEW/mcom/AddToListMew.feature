#---------------------------------------------------
# Brand         : MCOM
# Author        : Sravanthi Poujula
# Date Created	: Nov.8,2017
#---------------------------------------------------

Feature: ADDtoWishlist on MEW Validation & Verification

##AddTOList Scenarios on pdp page -- START --
  @21 @use_regression @domain_selection @project_MCOM
  Scenario Outline: As a Guest user, I should add product to wishlist from PDP Page in site mode
    Given I visit the mobile web site as a guest user
    When I navigate to PDP with PID "<productId>" in site mode
    And I click Add to Wish List button on PDP using mobile website
    And I verify basic components on Add to Wish List overlay for "guest" user on PDP using mobile website
    And I click on view list in ATW overlay from PDP using mobile website
    And I verify the basic components on the list page for "guest" user in mobile mode
    And I verify "<product_info>" on the page
    And I should see "1" products in the responsive list page
  Examples:
    |productId |  |product_info |
    |22805   |   |Lenox Eternal Gold 4-Piece Hostess Set|
    |5238576 |   |Brittna Fabric Sofa, Created for Macy's|
    |5368775 |   |CHANEL TRAIT DE CARACTÈRE|
    |19942   |   |Lenox Eternal Gold 5-Piece Place Setting|


  @23 @use_regression @domain_selection @project_MCOM
  Scenario Outline: As a registered user, I should add product to wishlist from PDP Page in site mode
    Given I visit the mobile web site as a registered user
    When I navigate to PDP with PID "<productId>" in site mode
    And I click Add to Wish List button on PDP using mobile website
    And I verify basic components on Add to Wish List overlay for "registered" user on PDP using mobile website
    And I click on view list in ATW overlay from PDP using mobile website
    And I verify the basic components on the list page for "registered" user in mobile mode
    And I verify "<product_info>" on the page
    And I should see "1" products in the responsive list page
  Examples:
    |productId |  |product_info |
    |22805   |   |Lenox Eternal Gold 4-Piece Hostess Set|
    |5238576 |   |Brittna Fabric Sofa, Created for Macy's|
    |5368775 |   |CHANEL TRAIT DE CARACTÈRE|
    |19942   |   |Lenox Eternal Gold 5-Piece Place Setting|

  @20 @use_regression @domain_selection @project_MCOM
  Scenario Outline: As a guest user, I should add product to wishlist from PDP Page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate to PDP with PID "<productId>" in registry mode
    And I click Add to Wish List button on PDP using mobile website
    And I verify basic components on Add to Wish List overlay for "guest" user on PDP using mobile website
    And I click on view list in ATW overlay from PDP using mobile website
    And I verify the basic components on the list page for "guest" user in mobile mode
    And I verify "<product_info>" on the page
    And I should see "1" products in the responsive list page
  Examples:
    |productId |  |product_info |
    |22805   |   |Lenox Eternal Gold 4-Piece Hostess Set|
    |5238576 |   |Brittna Fabric Sofa, Created for Macy's|
    |5368775 |   |CHANEL TRAIT DE CARACTÈRE|
    |19942   |   |Lenox Eternal Gold 5-Piece Place Setting|

##AddTOList Scenarios on pdp page -- END --


##AddTOList Scenarios on bag page -- START --
  @26 @use_regression @domain_selection @project_MCOM
  Scenario Outline: As a guest user, I should add product to wishlist from bag Page
    Given I visit the mobile web site as a guest user
    When I directly add an available and orderable product "<productId>" to my bag in mobile site
    And I click Move To List button on BAG using mobile website as a "guest" user
    And I verify the basic components on the list page for "guest" user in mobile mode
    And I verify "<product_info>" on the page
    And I should see "1" products in the responsive list page
  Examples:
    |productId |  |product_info |
    |22805   |   |Lenox Eternal Gold 4-Piece Hostess Set|
    |5238576 |   |Brittna Fabric Sofa, Created for Macy's|
    |5368775 |   |CHANEL TRAIT DE CARACTÈRE|
    |19942   |   |Lenox Eternal Gold 5-Piece Place Setting|

  @25 @use_regression @domain_selection @project_MCOM
  Scenario Outline: As a registered user, I should add product to wishlist from bag Page
    Given I visit the mobile web site as a registered user
    When I directly add an available and orderable product "<productId>" to my bag in mobile site
    And I click Move To List button on BAG using mobile website as a "registered" user
    And I verify the basic components on the list page for "registered" user in mobile mode
    And I verify "<product_info>" on the page
    And I should see "1" products in the responsive list page
  Examples:
    |productId |  |product_info |
    |22805   |   |Lenox Eternal Gold 4-Piece Hostess Set|
    |5238576 |   |Brittna Fabric Sofa, Created for Macy's|
    |5368775 |   |CHANEL TRAIT DE CARACTÈRE|
    |19942   |   |Lenox Eternal Gold 5-Piece Place Setting|


  @22 @use_regression @domain_selection @project_MCOM
  Scenario Outline: As a user, I should add product to wishlist from bag page overlay
    Given I visit the mobile web site as a guest user
    When I directly add an available and orderable product "<productId>" to my bag in mobile site
    And I click Add to Wish List button on BAG overlay using mobile website
  Examples:
    |productId |  |product_info |
    |22805   |   |Lenox Eternal Gold 4-Piece Hostess Set|

##AddTOList Scenarios on bag page -- END --