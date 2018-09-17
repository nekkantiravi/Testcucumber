#-----------------------------------------
# Author: Eric L
# Date Created: June 29, 2017
# Last Updated:
# Updated by:
#-----------------------------------------

Feature: Verify the responsive bag features - empty bag

@release_17P @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the empty bag functionality. This includes displaying the empty bag message, sign in link, continue shopping, and deals and promotions link.
    Given I visit the web site as a guest user
    And I navigate to responsive bag page
    Then I verify that the empty bag is displayed with expected content
  ### The expected content includes empty bag message, sign in link, and continue shopping link.


@release_17P @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
Scenario: Verify the sign in link
  Given I navigate to responsive bag page
  And I click the sign in button on the bag page
  Then I verify the page navigation for the "SignIn" page


@release_17P @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
Scenario: Verify the continue shopping link
  Given I navigate to responsive bag page
  And I click the continue shopping button on the bag page
  Then I verify the page navigation for the "shopping" page

@release_17P @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
Scenario: Verify the segment cookie is present
  Given I navigate to responsive bag page
  And I check the segment cookie is correct
  Then I verify that the empty bag is displayed with expected content



