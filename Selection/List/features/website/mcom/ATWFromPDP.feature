#---------------------------------------------------
# Brand         : MCOM
# Author        : vijaya bharathi
# Date Created	: Feb.21,2017
#---------------------------------------------------

Feature: ADDtoWishlist from PDP Validation & Verification

  @use_regression @domain_selection @project_MCOM
  Scenario: As a user, I should add product to wishlist from PDP Page
    Given I visit the web site as a guest user
    When I navigate directly to member PDP and add a product to my list
    And I verify basic components of list page

  @use_regression @domain_selection @project_MCOM
  Scenario: As a user, I should add product to bag from wishlist Page
    Given I visit the web site as a guest user
    When I navigate directly to member PDP and add a product to my list
    And I verify basic components of list page
    And I do add to bag from List page