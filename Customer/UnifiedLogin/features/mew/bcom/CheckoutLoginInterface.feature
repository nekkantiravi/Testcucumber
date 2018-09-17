#######################################################################################
# B-91691 :: BUS :: BCOM :: UI :: Create login interface for Checkout Flow
# B-95873 :: BUS/QE :: BCOM :: UI :: Create login interface for Redirection from Bag Checkout Flows
# B-95867 :: BUS/QE :: BCOM :: UI :: Create login interface for Merged Bag Checkout Flows
# Author :: QE Team
#######################################################################################

Feature:Verifying the checkout login interface flow on bcom

 #Checkout sign-in page behavior and function.

#  Background:
#    Given I set the unifiedLoginEnabled header

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify standard checkout sign in functionality.
    Given I visit the mobile web site as a guest user
    #And I add a random product to bag in mobile
    When I navigate to a product having "orderable and available" attributes
    And I click on add to bag button
    Then I click on checkout button on add to bag page
    #Then I add product to my bag from standard PDP Page using mobile site
    Then I  click on continue checkout button on shoppping bag page
    Then I should be on unified signin page
    And I should verify "pre_signin" url for "chkout_sign_in_mew" link
    When I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "chkout_sign_in_mew" link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify functionality of "checkout as a guest" button on checkout sign in page.
    Given I visit the mobile web site as a guest user
    When I navigate to a product having "orderable and available" attributes
    And I click on add to bag button
    Then I click on checkout button on add to bag page
    Then I  click on continue checkout button on shoppping bag page
    Then I should be on unified signin page
    And I should verify "pre_signin" url for "chkout_sign_in_mew" link
    When I click on checkout as guest button
    Then I should be redirected to "responsive_checkout" page
    And I should verify "post_signin" url for "checkout_sign_in_mew" link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify merge bag functionality for mobile in checkout flows.
    Given I visit the mobile web site as a registered user
    And I add a random product to bag in mobile
    And I sign out from my current mobile site profile
    And I visit the mobile web home page
    When I add a random product to bag in mobile
    And I navigate to mobile shopping bag page
    And I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "merged_bag" page
    And I should verify below fields on "merged_bag" page
      | add_to_current_bag   |
      | current_bag          |
    When I add all products to current bag
    Then I should be redirected to "merged_bag" page
    When I click on continue checkout button on shoppping bag page
    Then I should be redirected to "responsive_checkout_signed_in" page

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify checkout sign in feature through bag redirection flow from mobile home page.
    Given I visit the mobile web site as a guest user
    And I add a random product to bag in mobile
    And I visit the mobile web home page
    When I click on "quick_bag" in "home" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "chkout_sign_in_mew" link