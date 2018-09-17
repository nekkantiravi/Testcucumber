#-----------------------------------------
# Author: Eric L
# Date Created: June 29, 2017
# Last Updated:
# Updated by:
#-----------------------------------------

Feature: Verify the responsive bag features - empty bag

@release_17P @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the empty bag functionality. This includes displaying the empty bag message, sign in link, continue shopping, and deals and promotions link.
    Given I navigate to responsive empty bag page
    Then I verify that the empty bag is displayed with expected content
  ### The expected content includes empty bag message, sign in link, and continue shopping link.


@release_17P @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the sign in link
    Given I navigate to responsive empty bag page
    And I click the "signIn" button on the responsive bag page
    Then I verify the page navigation for the "SignIn" page


@release_17P @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the continue shopping link
    Given I navigate to responsive empty bag page
    And I click the "continueShopping" button on the responsive bag page
    Then I verify the page navigation for the "shopping" page

@rbag @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the bag icon is present on home page
    Given I visit the web site as a guest user
    Then I verify the bag icon is present on home page
    #once rbag page is developed then we need to verify that we navigate to rbag page after clicking on bag icon

@rbag @search @add_to_bag @navigation @release_17ZA @artifact_ui @artifact_order @priority_high @project_responsive_bag @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the checkout button is present on quick bag
    Given I visit the web site as a guest user
    When I add a "orderable and available" product to my bag
    And I clicked on Macys logo
    Then I verify the checkout button is present on quick bag
    #once rbag page is developed then we need to verify that we navigate to rbag page after clicking on checkout button from quick bag icon

@rbag @release_17ZA
  Scenario: Verify the rbag navigation from ISHIP mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "India"
    And I visit the web site as a guest user
    Then I verify the bag icon is present on home page
    #once rbag page is developed then we need to verify that we navigate to rbag page after clicking on bag icon

@rbag @release_17ZA
  Scenario: Verify the go to US site from International Context page
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "India"
    And I close the welcome mat if it's visible
    When I go to US site
    Then I verify the ability to go to US site from International Context page



