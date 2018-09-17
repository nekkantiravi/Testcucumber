#######################################################################################
# B-90620 :: BUS :: MCOM :: UI :: Send user to correct destination if he is already signed In
# B-90622 :: BUS :: MCOM :: UI :: Set initial cookies
# Author :: QE Team
#######################################################################################

Feature: Verify Login page behavior

  Background:
    Given I set the unifiedLoginEnabled header

  @wip @domain_customer @project_unified_login
  Scenario Outline:Verify the user is redirected to the page as per forwardpage_key cookie after login from header
    Given I visit the web site as a guest user
    When I click on "<input>" in "home" page
    Then I should be on signin page
    And I verify the forwardpage key cookie is generated
    When I sign in with valid credentials
    Then I should redirect to the page corresponding to the value stored in FORWARDPAGE_KEY cookie

    Examples:
      | input                |
      | goto_my_account_link |
      | goto_sign_in_link    |

  @wip @domain_customer @project_unified_login
  Scenario Outline: Verify the user is redirected to corresponding page after signing in through My Account dropdown
    Given I visit the web site as a guest user
    When I select "<option>" link from my account dropdown
    Then I should be on signin page
    And I verify the forwardpage key cookie is generated
    When I sign in with valid credentials
    Then I should redirect to the page corresponding to the value stored in FORWARDPAGE_KEY cookie

    Examples:
      | option          |
      | goto_mywallet   |
      | goto_myplenti   |

  @wip @domain_customer @project_unified_login
  Scenario Outline: Verify the user is redirected to corresponding page after signing in through My Account dropdown
    Given I visit the web site as a guest user
    When I select "<option>" link from my account dropdown
    Then I should be redirected to "<target>" page
    When I click on "<Sign-in_link>" in "<target>" page
    Then I should be on signin page
    And I verify the forwardpage key cookie is generated
    When I sign in with valid credentials
    Then I should redirect to the page corresponding to the value stored in FORWARDPAGE_KEY cookie

    Examples:
      | option                  | Sign-in_link                 | target                      |
      | goto_order_history      | goto_order_history_sign_in   | order_status                |
      | goto_macys_credit_card  | sign_in_button               | credit_service_gateway_guest|
      | goto_mylist             | goto_signin_link             | wish_list                   |

  @wip @domain_customer @project_unified_login
  Scenario: Verify the cookies on post sign in page
    Given I navigate to the url "/account/signin" as guest user
    When I sign in with valid credentials
    Then I should be navigated to post sign in page
    And I should verify below cookies to be present
      | macys_online      |
      | macys_online_uid  |
      | macys_online_guid |
      | shippingCountry   |
      | SNSGCs            |
      | profile           |