#-----------------------------------------
# Author: Eric L
# Date Created: June 29, 2017
# Last Updated:
# Updated by:
#-----------------------------------------

Feature: Verify the responsive bag features

@release_17N @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the empty bag functionality
    Given I visit the web site as a guest user
    And I navigate to responsive bag page
    Then I verify that the empty bag is displayed with expected content
  ### The expected content includes empty bag message, sign in link, and continue shopping link.



# @release_17N @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
#  Scenario: Verify the order summary
    Given I visit the web site as a guest user
    And I add a "orderable" product to my bag
    Given I navigate to core responsive bag page
    Then I verify that the core bag is displayed
  ### The expected content includes the display of bag items and order summary.


#@release_17N @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
#  Scenario: Verify the update quantity functionality
    Given I visit the web site as a guest user
    When I add a "orderable" product to my bag
    And I navigate to core responsive bag page
    And I change the quantity dropdown to 2 on bag page
    Then I verify the updated quantity is equal to 2


#  @release_17N @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
#  Scenario: Verify the remove item functionality
###    Given I visit the web site as a guest user
###    When I add a "orderable" product to my bag
###    And I navigate to core responsive bag page
###    And I click the remove button link on bag page
###    Then I verify the item is removed

 