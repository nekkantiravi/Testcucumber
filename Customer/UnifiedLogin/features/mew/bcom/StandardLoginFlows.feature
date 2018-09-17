###############################################################################################
# B-91690 :: BUS :: BCOM :: UI :: Create login interface for Standard Sign-In Flow for mobile
# Author :: QE Team
###############################################################################################


Feature: Verify standard Sign-in flows on mobile

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior of standard login from mobile homepage
    Given I visit the mobile web site as a guest user
    When I click on signIn link in footer
    Then I should be on signin page
    And I should verify "pre_signin" url for "footer_sign_in_link_mew" footer link
    When I sign in with valid credentials
   #Then I should be redirected to mobile website as signed in user
    And I should verify "post_signin" url for the "footer_sign_in_link_mew" footer link

  @domain_customer @project_unified_login @artifact_account_ui @account_xapi
  Scenario: Verify the pre and post login behavior of Standard login from global menu signin
    Given I visit the mobile web site as a guest user
    When I navigate to the global navigation menu and click on SignIn
    Then I should be on signin page
    And I should verify "pre_signin" url for "header_sign_in_link_mew" footer link
    When I sign in with valid credentials
    #Then I should be redirected to mobile website as signed in user
    And I should verify "post_signin" url for the "header_sign_in_link_mew" footer link

  @domain_customer @project_unified_login @artifact_account_ui @account_xapi
  Scenario: Verify the pre and post login behavior of Standard login from global menu My Account
    Given I visit the mobile web site as a guest user
    When I navigate to the global navigation menu and click on My Account
    Then I should be on signin page
    And I should verify "pre_signin" url for "my_account_sign_in_link_mew" footer link
    When I sign in with valid credentials
        #Then I should see below fields on my account page
    And I should verify "post_signin" url for the "my_account_sign_in_link_mew" footer link

  @domain_customer @project_unified_login @artifact_account_ui @account_xapi
  Scenario: Verify the pre and post login behavior of Standard login from global menu My Credit Card
    Given I visit the mobile web site as a guest user
    When I navigate to the global navigation menu and click on My Credit Card
    Then I should be on signin page
    And I should verify "pre_signin" url for "cc_sign_in_link_mew" footer link
    When I sign in with valid credentials
          #Then I should see below fields on gateway page
    And I should verify "post_signin" url for the "cc_sign_in_link_mew" footer link

  @domain_customer @project_unified_login @artifact_account_ui @account_xapi
  Scenario: Verify the pre and post login behavior of Standard login from global menu My bWallet
    Given I visit the mobile web site as a guest user
    When I navigate to the global navigation menu and click on My bWallet
    Then I should be on signin page
    And I should verify "pre_signin" url for "bwallet_sign_in_link_mew" footer link
    When I sign in with valid credentials
          #Then I should see below fields on my wallet page
    And I should verify "post_signin" url for the "bwallet_sign_in_link_mew" footer link

  @domain_customer @project_unified_login @artifact_account_ui @account_xapi
  Scenario: Verify the pre and post login behavior of Standard login from global menu Wish List
    Given I visit the mobile web site as a guest user
    When I navigate to the global navigation and signin on wishlist
    Then I should be on signin page
    And I should verify "pre_signin" url for "wishlist_sign_in_link_mew" footer link
    When I sign in with valid credentials
    #Then I should see below fields on wish list page
    And I should verify "post_signin" url for the "wishlist_sign_in_link_mew" footer link

  @domain_customer @project_unified_login @artifact_account_ui @account_xapi
  Scenario: Verify standard checkout sign in functionality on mobile.
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    When I select a random member product using mobile website
    Then I should be redirected to PDP page
    And I add product to my bag from standard PDP Page using mobile site
    And I checkout until I reach the shipping page using mobile website as a "guest" user
    And I checkout until I reach the payment page using mobile website as a "guest" user
    When I checkout until I reach the order review page using mobile website as a "guest" user
    Then I should be on responsive checkout page

  @domain_customer @project_unified_login @artifact_account_ui @account_xapi
  Scenario: Signed in user checkout flow
    Given I visit the mobile web site as a registered user
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    When I select a random member product using mobile website
    Then I should be redirected to PDP page
    And I add product to my bag from standard PDP Page using mobile site
    When I checkout until I reach the shipping & payment page using mobile website as a "responsive_signed in" user
    Then I should be on responsive checkout page