#######################################################################################
# B-91632 :: BUS :: MCOM :: UI :: Create login interface for Checkout Flow
# B-95872 :: BUS/QE :: MCOM :: UI :: Create login interface for Redirection from Bag Checkout Flows
# B-95869 :: BUS/QE :: MCOM :: UI :: Create login interface for Merged Bag Checkout Flows
# Author :: QE Team
#######################################################################################

  Feature: Checkout Mobile Login interface function.

    # User must be able to login through new Unified sign in interface during checkout flows.

#    Background:
#      Given I set the unifiedLoginEnabled header

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify standard checkout sign in functionality.
      Given I visit the mobile web site as a guest user
      When I add a random product to bag in mobile
      And I navigate to mobile shopping bag page
      When I  click on continue checkout button on shoppping bag page
      Then I should be on signin page
      And I should verify "pre_signin" url for "checkout_sign_in_mew" link
      When I sign in with existing credentials from "checkout_sign_in" page
      Then I should be redirected to "responsive_checkout_signed_in" page
      And I should verify "post_signin" url for "checkout_sign_in_mew" link

    @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify functionality of "checkout as a guest" button on checkout sign in page.
      Given I visit the mobile web site as a guest user
      When I add a random product to bag in mobile
      And I navigate to mobile shopping bag page
      When I  click on continue checkout button on shoppping bag page
      Then I should be on signin page
      And I should verify "pre_signin" url for "checkout_sign_in_mew" link
      When I click on checkout as guest button
      Then I should be redirected to "responsive_checkout" page
      And I should verify "post_signin" url for "responsive_checkout" link

    @wip @domain_customer @project_unified_login @artifact_account_ui
    Scenario: Verify merge bag functionality for mobile in checkout flows.
      Given I visit the mobile website as a registered user
      And I add a random product to bag in mobile
      And I visit the mobile web home page
      And I sign out from my current mobile site profile
      When I add a random product to bag in mobile
      And I navigate to mobile shopping bag page
      When I  click on continue checkout button on shoppping bag page
      Then I should be on signin page
      When I login with created profile through "checkout_sign_in" page
      And I click on continue checkout button on shoppping bag page
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