#-----------------------------------------
# Author: Prashanth K
# Date Created: Apr 28, 2016
# Last Updated:
# Updated by:
#-----------------------------------------

Feature: Verify the cart abandonment optimization regression features in desktop

  @release_16K @feature_paypal_checkout_button_optimization @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the optimized paypal checkout button experience on the shopping bag page in desktop mode
  
  Given I visit the web site as a guest user
  When I add a "orderable and available" product to my bag
  And I select checkout with paypal
  Then I should see paypal login page
  
  @release_16K @feature_payment_badge @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the payment badges display on the shopping bag page in desktop mode
    Given I visit the web site as a guest user
    When I add a "orderable" product to my bag
    Then I should see payment badge

  @release_16K @feature_promo_inline @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the promo inline error message display on the shopping bag page in desktop mode
    Given I visit the web site as a guest user
    When I add a "orderable" product to my bag
    And I navigate to shopping bag page
    And I apply invalid promo code "asdasdasd" using website
    Then I verify the promo code validation error message appeared
    And I should see promo error message on the top of bag page
    And I should see promo inline error message
    
 