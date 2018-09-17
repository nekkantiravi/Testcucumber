###################################################################################
# B-91630 :: BUS :: MCOM :: UI :: Create login interface for Standard Sign-in flow
# Author :: QE Team
###################################################################################

Feature: Standard login interface.

  # Verify user sign in functionality with new unified login prompt.

#  Background:
#      Given I set the unifiedLoginEnabled header

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in function through mobile homepage footer from homepage.
    Given I visit the mobile web home page
    When I click on "goto_sign_in_link" in "home" mobile page
    Then I should be on signin page
    And I should verify "pre_signin" url for "goto_sign_in_link_mew" link
    When I sign in with valid credentials
    Then I should be redirected to "home" page
    And I should verify "post_signin" url for "goto_sign_in_link_mew" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in function through wallet option in global nav.
    Given I visit the mobile web home page
    When I navigate to mobile "oc_my_wallet" page from global nav
    Then I should be on signin page
    And I should verify "pre_signin" url for "oc_my_wallet_mew" link
    When I sign in with valid credentials
    Then I should be redirected to "oc_my_wallet" page
    And I should verify "post_signin" url for "oc_my_wallet_mew" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in function through my account option in gloabal nav.
    Given I visit the mobile web home page
    When I navigate to mobile "my_account" page from global nav
    Then I should be on signin page
    And I should verify "pre_signin" url for "my_account_mew" link
    When I sign in with valid credentials
    Then I should be redirected to "my_account" page
    And I should verify "post_signin" url for "my_account_mew" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in function through macys credit card option in gloabal nav.
    Given I visit the mobile web home page
    And I navigate to mobile "gateway" page from global nav
    When I click on "sign_in_button" in "credit_service_gateway_guest" mobile page
    Then I should be on signin page
    And I should verify "pre_signin" url for "gateway_signin_mew" link
    When I sign in with valid credentials
    Then I should be redirected to "credit_service_gateway_signedin" page
    And I should verify "post_signin" url for "gateway_signin_mew" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in function through Lists option in global nav.
    Given I visit the mobile web home page
    And I navigate to mobile "wishlist" page from global nav
    When I click on "sign_in_button" in "wish_list" mobile page
    Then I should be on signin page
    And I should verify "pre_signin" url for "wish_list_mew" link
    When I sign in with valid credentials
    Then I should be redirected to "wish_list" page
    And I should verify "post_signin" url for "wish_list_mew" link

#  @domain_customer @project_unified_login @artifact_account_ui
#  Scenario: Verify sign in function through create registry option on wedding registry page.
#    Given I visit the mobile web home page
#    And I navigate to mobile "registry" page from global nav
#    When I click on "goto_create_registry" in "registry_home" mobile page
#    Then I should be on signin page
#    And I should verify "pre_signin" url for "registry_sign_in_mew" link
#    When I sign in with valid credentials
#    Then I should be redirected to "registry_manager" page
#    And I should verify "post_signin" url for "registry_sign_in_mew" link
#
#  @domain_customer @project_unified_login @artifact_account_ui
#  Scenario: Verify sign in function through manage registry option on wedding registry page.
#    Given I visit the mobile web home page
#    And I navigate to mobile "registry" page from global nav
#    When I click on "goto_manage_registry" in "registry_home" mobile page
#    Then I should be on signin page
#    And I should verify "pre_signin" url for "registry_sign_in_mew" link
#    When I sign in with valid credentials
#    Then I should be redirected to "registry_manager" page
#    And I should verify "post_signin" url for "registry_sign_in_mew" link
