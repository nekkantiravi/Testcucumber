#######################################################################################
# B-91626 :: BUS :: MCOM :: MEW :: Create login interface for Registry Sign-In Flow
# B-96572 :: MEW:MCOM:Signed In user should be redirected to specific target registry page based on registry request URL
# Author :: QE Team
#######################################################################################


Feature:Verify the registry login interface flow

 #Pre and Post login functionality through registry links.
#  Background:
#    Given I set the unifiedLoginEnabled header

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in function through create registry option on wedding registry page.
    Given I visit the mobile web home page
    And I navigate to mobile "registry" page from global nav
    When I click on "goto_create_registry" in "registry_home" mobile page
    Then I should be on signin page
    And I should verify "pre_signin" url for "create_registry_sign_in_mew" link
    When I sign in with valid credentials
    Then I should be redirected to "new_create_registry" page
    And I should verify "post_signin" url for "create_registry_sign_in_mew" link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in function through manage registry option on wedding registry page.
    Given I visit the mobile web site as a registry user
    And I sign out from my current mobile site profile
    And I navigate to mobile "registry" page from global nav
    When I click on "goto_manage_registry" in "registry_home" mobile page
    Then I should be on signin page
    And I should verify "pre_signin" url for "manage_registry_sign_in_mew" link
    When I login with created profile through "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "manage_registry_sign_in_mew" link

  ############# Registry sign in flow through registry tools global nav option #########

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in functionality through MANAGE REGISTRY link in REGISTRY TOOLS nav
    Given I visit the mobile web site as a registry user
    And I sign out from my current mobile site profile
    And I navigate to mobile "registry" page from global nav
    When I navigate to global navigation menu as follows:
      | REGISTRY TOOLS  |
      | MANAGE REGISTRY |
    Then I should be on signin page
    And I should verify "pre_signin" url for "manage_registry_sign_in_mew" link
    When I login with created profile through "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "manage_registry_sign_in_mew" link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in functionality through VIEW REGISTRY link in REGISTRY TOOLS nav
    Given I visit the mobile web site as a registry user
    And I sign out from my current mobile site profile
    And I navigate to mobile "registry" page from global nav
    When I navigate to global navigation menu as follows:
      | REGISTRY TOOLS |
      | VIEW REGISTRY  |
    Then I should be on signin page
    And I should verify "pre_signin" url for "view_registry_sign_in_mew" link
    When I login with created profile through "new_registry_sign_in" page
    Then I should be redirected to "registry_manager" page
    And I should verify "post_signin" url for "view_registry_sign_in_mew" link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in functionality through CREATE YOUR REGISTRY AND ENROLL link on Rewards page
  through REWARDS link in REGISTRY TOOLS nav
    Given I visit the mobile web site as a guest user
    And I navigate to mobile "registry" page from global nav
    When I navigate to global navigation menu as follows:
      | REGISTRY TOOLS |
      | REWARDS        |
    And I click on "create_reg_and_enroll" in "registry_rewards" mobile page
    Then I should be on signin page
    When I login with created profile through "new_registry_sign_in" page
    Then I should be redirected to "new_create_registry" page

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in functionality through SIGN IN AND ENROLL link on Rewards page
  through REWARDS link in REGISTRY TOOLS nav
    Given I visit the mobile web site as a guest user
    And I navigate to mobile "registry" page from global nav
    When I navigate to global navigation menu as follows:
      | REGISTRY TOOLS |
      | REWARDS        |
    And I click on "sign_in_and_enroll" in "registry_rewards" mobile page
    Then I should be on signin page
    When I login with created profile through "new_registry_sign_in" page
    Then I should be redirected to "new_create_registry" page

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in functionality through REGISTRY PROFILE link in REGISTRY TOOLS nav
  through REWARDS link in REGISTRY TOOLS nav
    Given I visit the mobile web site as a registry user
    And I sign out from my current mobile site profile
    And I navigate to mobile "registry" page from global nav
    When I navigate to global navigation menu as follows:
      | REGISTRY TOOLS   |
      | REGISTRY PROFILE |
    Then I should be on signin page
    When I login with created profile through "new_registry_sign_in" page
    Then I should be redirected to "new_create_registry" page


