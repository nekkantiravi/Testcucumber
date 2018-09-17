#-----------------------------------------
# Author: Prashanth K
# Date Created: Apr 28, 2016
# Last Updated:
# Updated by:
#-----------------------------------------

Feature: Verify the cart abandonment optimization regression features in MEW

   @release_16K @feature_mew_paypal_checkout_button_optimization @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
   Scenario: Verify the optimized paypal checkout button experience on the shopping bag page in mobile site
    Given I visit the mobile web site as a guest user
    And I add an "orderable and available" product to my bag using mobile website and select checkout
    And I select checkout with paypal
    Then I should see paypal login page
    
  @release_16K @feature_mew_payment_badge @artifact_ui @artifact_order @priority_high @project_cart_abandonment_optimization @domain_purchase_and_delivery @mode_domestic
  Scenario: Verify the payment badge button display on the shopping bag page in mobile site
    Given I visit the mobile web site as a guest user
    And I add an "orderable" product to my bag using mobile website and select checkout
    Then I should see payment badge
 