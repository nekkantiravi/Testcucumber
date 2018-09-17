#Author: Prachi Agarwal
#Date Created:  06/20/2017
#Date Signed Off: TBD

Feature: Sign-in to Access My Account

  #Mingle: http://mingle/projects/market/cards/157
  #http://mingle/projects/market/cards/158

####################################### Regular Sign In Page #####################################

# Homepage -> MyAccount link -> enter username, pwd -> MyAccount Page
  #Test Case ID: BLCOM-80180
  @upi_157_mcom @upi_158_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_6 @use_domain_qual @cm_dsv_high_10
  @health_check @creative
  @s4a_stable @use_launch_call @domain_customer @use_browser @ifs @migrated_to_sdt
  Scenario:Verify My Account link on Home Page navigates to My Account Page after user SignsIn
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I sign out from my current profile
    And I click on "my account" link in the header
    And I sign in with created profile
    Then I should be navigated to My Account Page


  @upi_157_mcom @upi_158_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_6 @run_this
  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Verify SignIn Page links
    Given I visit the web site as a guest user
    When I click on signIn link
    Then SignIn page should get loaded
    And I verify the sign in page links:
      | forgot_password_link        |
      | looking_for_gift_registry   |
      | create_profile_benefits_new |
      | privacy_policy              |
    And I should be navigated to below respective sign in pages:
      | looking for gift registry |
      | forgot password           |
      | create profile benefits   |
      | Privacy Practices         |


  @upi_157_mcom @upi_158_bcom @artifact_shopapp @priority_high @use_regression
  @myaccount_6 @domain_customer @use_domain_qual @migrated_to_sdt
  Scenario: Verify cookies when Guest user navigates to SignIn Page
    Given I visit the web site as a guest user
    Then I verify cookies for guest user in "home" page
    When I click on signIn link
    Then I verify cookies for guest user in "sign in" page

  @upi_157_mcom @upi_158_bcom @artifact_shopapp @priority_high @use_regression @myaccount_6
  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify secure user token is deleted after SignOut
    Given I visit the web site as a guest user
    And I create a new profile
    Then I verify secure user token and cookie values after sign in
    When I sign out from my current profile
    Then I verify secure user token and signed in cookie value after signed out from profile

  @use_regression @artifact_shopapp @artifact_shopapp_2 @myaccount_2
  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:SignIn - Email Address should not pre-populated after sign-out on Regular SignIn Page
    Given I visit the web site as a guest user
    When I create a new profile
    And I leave the site and return later
    And I close and reopen the browser
    When I navigate to my account page
    Then I verify that Email Address field is not pre-populated in the Regular SignIn Page

  @use_regression @artifact_shopapp @artifact_shopapp_2 @myaccount_2
  @s4a_stable @domain_customer @priority_high  @defect_ECOMSST-42896 @migrated_to_sdt
  Scenario:SignIn - Cookies after re-opening the site in another tab as a SignedIn user
# Purpose:
# To verify that when a Signed in user closes the tab and opens the application in another tab,
# the session should not be killed and user should be able to access all user profile related information.
    Given I visit the web site as a guest user
    When I create a new profile
    Then I verify secure user token and cookie values after sign in
    When I open a new tab in the same browser
    And I close the existing tab and navigate to home page in new tab
    Then I verify secure user token and cookie values after sign in

  @domain_customer @use_regression @migrated_to_sdt
  Scenario: Sign in with valid credentials
    Given I visit the web site as a guest user
    When I create a new profile
    And I verify the basic attributes of the my profile section on My Account Page
    Then I should be redirected to home page with SIGN OUT link on header


  @upi_158_bcom @artifact_shopapp @priority_medium @use_regression @14H @myaccount_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify error messaging on SignIn Page for invalid email and password
    Given I visit the web site as a guest user
    When I click on signIn link
    And I verify the error message on sign in page
      | email            | password | error_message                                                                                 |
      |                  | 12345    | Please enter an email address.                                                                |
      | !@#$             | 12345    | Please enter a valid email address in the following format: jane@email.com                    |
      | sample@macys.com |          | Your password must be between 5-16 characters, and cannot include . , - \| \ / = _ or spaces. |
      | sample@macys.com | !@#$%    | That email address/password combination is not in our records.                                |
      | !@#$             | !@#$%    | Please enter a valid email address in the following format: jane@email.com                    |

  #functional
  @use_regression @myaccount_6 @domain_customer @migrated_to_sdt
  Scenario: SignIn - Rendering Registry SignIn Page
    Given I visit the web site as a guest user
    And I navigate to registry signin page
    Then I verify the attributes of the Registry SignIn Page

  @use_regression @artifact_shopapp  @artifact_navapp @artifact_shopapp_2 @myaccount_2 @artifact_navapp_2 @cm_dsv_high_10
  @s4a_stable @domain_customer @priority_high @defect_D-15989 @use_domain_qual @migrated_to_sdt
  Scenario:SignIn - Email Address should not be pre-populated after signing out on the checkout SignIn Page
    Given I visit the web site as a guest user
    When I create a new profile
    When I close and reopen the browser
    And I add a random product to bag
    And I navigate to checkout sign in page from shopping bag page
    Then I verify that Email Address field is not pre-populated in the checkout SignIn Page