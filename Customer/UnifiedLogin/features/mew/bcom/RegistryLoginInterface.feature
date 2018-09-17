#######################################################################################
# B-96502 :: BUS :: BCOM :: MEW :: Create login interface for Registry Flows
# Author :: QE Team
#######################################################################################


Feature:Verify the registry login interface flow for mobile

 #Pre and Post login functionality through registry links.
#  Background:
#    Given I set the unifiedLoginEnabled header

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in function through manage registry option on registry page.
    Given I visit the mobile web home page
    When I navigate to the global navigation menu and click on The Registry
    Then I should land on registry home page and click on manage registry
    Then I should be on signin page
    And I should verify "pre_signin" url for "the_registry_sign_in_link_mew" global registry link
    When I sign in with valid existing registry user credentials
    And I should verify "post_signin" url for "the_registry_sign_in_link_mew" global link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in function through create registry option on registry page.
    Given I visit the mobile web home page
    When I navigate to the global navigation menu and click on The Registry
    Then I should land on registry home page and click on create registry
    Then I should be on signin page
    And I should verify "pre_signin" url for "create_registry_sign_in_link_mew" global link
    When I sign in with valid existing registry user credentials
    And I should verify "post_signin" url for "create_registry_sign_in_link_mew" global link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in function through global registry menu via create flow
    Given I visit the mobile web home page
    When I navigate to the global navigation menu and click on The Registry
    Then I should land on registry home page
    And I select "Create Registry" tab in the left nav
    Then I should be on signin page
    And I should verify "pre_signin" url for "left_create_registry_sign_in_link_mew" global link
    When I sign in with valid existing registry user credentials
    And I should verify "post_signin" url for "left_create_registry_sign_in_link_mew" global link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in functionality through global registry menu via manage registry flow
    Given I visit the mobile web home page
    When I navigate to the global navigation menu and click on The Registry
    Then I should land on registry home page
    And I select "Manage Registry" tab in the left nav
    And I select "View Registry" tab in the left nav
    Then I should be on signin page
    And I should verify "pre_signin" url for "view_registry_sign_in_link_mew" global link
    When I sign in with valid existing registry user credentials
    And I should verify "post_signin" url for "view_registry_sign_in_link_mew" global link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify sign in functionality via add item to bag and add to registry button
    Given I visit the mobile web home page
    When I navigate to a product having "orderable and available" attributes
    Then I click on add to registry button on shopping bag page
    Then I should be on signin page
    And I should verify "pre_signin" url for "chckout_registry_sign_in_link_mew" global link
    When I sign in with valid existing registry user credentials
    And I should verify "post_signin" url for "chckout_registry_sign_in_link_mew" global link
