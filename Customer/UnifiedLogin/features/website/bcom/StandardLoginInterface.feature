#######################################################################################
# B-91690 :: BUS :: BCOM :: UI :: Create login interface for Standard Sign-In Flow
# Author :: QE Team
#######################################################################################

Feature: Verify Standard Sign-in Page

  "Common standard login interface i.e.(/account/signin) for across all the .com sign in entry points."

#  Background:
#    Given I set the unifiedLoginEnabled header

  ### This section covers the different entry point(global header, global footer, left navigation, pdp,
  ## brown bag) to sign in page for desktop, only regular sign flows are considered here.
  ## checkout, Registry, Loyallist flows will be covered in separate feature file.

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from homepage
    Given I visit the web site as a guest user
    When I click on "<input>" in "home" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<input>" link
    When I sign in with valid credentials
    Then I should be redirected to "<target>" page
    And I should verify "post_signin" url for "<target>" link
    Examples:
      | input                       | target                          |
      | goto_my_account_link        | my_account                      |
      | goto_my_account             | my_account                      |
      | goto_credit_pay_bill_online | credit_service_gateway_signedin |

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from wishlist page.
    Given I visit the web site as a guest user
    When I click on "goto_wishlist" in "home" page
    And I click on "<input>" in "wish_list" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<input>" link
    When I sign in with valid credentials
    Then I should be redirected to "<target>" page
    And I should verify "post_signin" url for "<target>" link
    Examples:
      | input                  | target    |
      | wishlist_myaccount     | wish_list |
      | myaccount_list         | wish_list |
      | goto_guest_signin_link | wish_list |

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from bloomingdales credit card page.
    Given I visit the web site as a guest user
    When I click on "goto_order_status" in "home" page
    And I click on "goto_my_credit_card" in "navigation" page
    And I click on "sign_in_button" in "credit_service_gateway_guest" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "credit_service_gateway_guest" link
    When I sign in with valid credentials
    Then I should be redirected to "credit_service_gateway_signedin" page
    And I should verify "post_signin" url for "credit_service_gateway_signedin" link

  ### Standard sign in features through the left navigation links which are common
  ### to gateway, cardholder benefits and learn more & apply page.

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline:  Verify the pre and post login behavior with standard login interface from left navigation links.
    Given I visit the web site as a guest user
    When I click on "goto_order_status" in "home" page
    And I click on "<left_nav>" in "navigation" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<left_nav>" link
    When I sign in with valid credentials
    Then I should be redirected to "<post_login>" page
    And I should verify "post_signin" url for "<post_login>" link

    Examples:
      | left_nav                  | post_login            |
      | goto_my_account           | order_status          |
      | goto_my_preferences_link  | my_preferences        |
      | goto_my_address_book_link | order_status          |
      | goto_my_wallet_link       | order_status          |
      | goto_my_points            | loyalty_association   |
      | goto_reward_card_balance  | order_status          |

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from create profile page.
    Given I visit the web site as a guest user
    When I click on "goto_order_status" in "home" page
    And I click on "goto_my_profile" in "navigation" page
    And I create a new profile
    Then I should see user logged in to account successfully

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify pre and post sign in function on order history and status page
    Given I visit the web site as a guest user
    When I click on "goto_order_status" in "home" page
    And I click on "goto_order_history_sign_in" in "order_status" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "goto_order_history_sign_in" link
    When I sign in with valid credentials
    Then I should be redirected to "order_status" page
    And I should verify "post_signin" url for "order_status" link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify pre and post sign in function on bloomingdales credit card page
    Given I visit the web site as a guest user
    When I click on "goto_credit_services" in "footer" page
    And I click on "sign_in_button" in "credit_service_gateway_guest" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "credit_service_gateway_guest" link
    When I sign in with valid credentials
    Then I should be redirected to "credit_service_gateway_signedin" page
    And I should verify "post_signin" url for "credit_service_gateway_signedin" link

   ####################pre and post sign in behavior on PDP page####################

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify pre and post function on pdp page.
    Given I visit the web site as a guest user
    And I navigate to a random product
    When I click on "add_to_list" in "product_display" page
    Then I should see Wish List confirmation overlay displays
    When I select signIn link on wishlist overlay in PDP page
    Then I should be on signin page
    And I should verify "pre_signin" url for "wishlist_signin" link
    When I sign in with valid credentials
    Then I should be redirected to "product_display" page
    And I should verify "post_signin" url for "product_display" link

  ####################pre and post sign in behavior on Brown Bag page#####################

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify pre and post function through SignIn option to see your offers and Reward Card balance on shopping page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I click on "bag_sign_in_link" in "shopping_bag" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "bag_sign_in_link" link
    When I sign in with valid credentials
    Then I should be redirected to "shopping_bag" page
    And I should verify "post_signin" url for "shopping_bag" link

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify pre and post function through signin option on Loyallist banner from shopping page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I click on "lty_sign_in_link" in "shopping_bag" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "lty_sign_in_link" link
    When I sign in with valid credentials
    Then I should be redirected to "shopping_bag" page
    And I should verify "post_signin" url for "shopping_bag" link

  Scenario: Verify the pre and post login behavior with standard login interface through write a review option from pdp page
    Given I visit the web site as a guest user
    And I navigate to a random product
    When I click on "write_a_review" in "product_display" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "write_a_review_sign_in_link" link
    When I sign in with valid credentials
    Then I should be redirected to "product_display" page
    And I should verify "post_signin" url for "product_display" link

  Scenario: Verify the pre and post login behavior with standard login interface through write a comment option from pdp page.
    Given I visit the web site as a guest user
    When I navigate to PDP Page which has reviews
    And I click on "write_a_comment" in "product_display" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "write_a_comment_sign_in_link" link
    When I sign in with valid credentials
    Then I should be redirected to "review" page
    And I should verify "post_signin" url for "product_display" link

  ##Based upon standard login url "/account/signin", it covers the UI fields and the function
  #of different links. ex- sign in button, show password, target page etc.

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify standard sign-in page
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    And I should verify below fields on "sign_in" page
      | email                |
      | password             |
      | verify_page          |
      | pwd_show_button      |
      | forgot_password_link |
      | learn_more           |
      | create_profile       |

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the different link function on standard sign in page
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    When I click on "sign_up" in "sign_in" page
    Then I should be redirected to "create_profile" page
    When I create a new profile
    Then I should see user logged in to account successfully

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the different link function on standard sign in page
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    When I click on "<input>" in "sign_in" page
    Then I should be redirected to "<target>" page

    Examples:
      | input                | target          |
      | forgot_password_link | forgot_password |
      | learn_more           | learn_more      |

  @domain_customer @project_unified_login @artifact_account_ui @wip
  Scenario: Verify the functionality of "Show" button on standard sign in page
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    And I should see typed password using show button in "sign_in" page
    And I should see hide button on "sign_in" page

  ########### Error message handling ##########################

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the validation message when no credentials is entered
    Given I navigate to the url "/account/signin" as guest user
    When I click on "sign_in_button" in "sign_in" page
    Then I should see "<expected error>" validation message on "sign_in" page

    Examples:
      | expected error                  |
      | Please enter your email address |
      | Please enter your password      |

  @domain_customer @project_unified_login @artifact_account_ui @wip
  Scenario Outline: Verify the validation message when invalid credentials is entered
    Given I navigate to the url "/account/signin" as guest user
    When I enter invalid "<email>" and "<password>"
    Then I should see "<expected error>" validation message on "sign_in" page

    Examples:
      | email              | password  | expected error                                                                                                   |
      | testmail           | test12345 | Your email address must be entered in this format: jane@company.com                                              |
      | testmail@gmail.com | test      | Your password must be between 7-16 characters, and cannot include . , - \| \\ / = _ or spaces. Please try again. |
      | testmail@gmail.com | test12345 | That email address/password combination is not in our records. Forgot Your Password? Reset it now                |
      |                    | test12345 | Please enter your email address                                                                                  |
      | jane@bcom.com      |           | Please enter your password                                                                                       |

  @wip @manual @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify error message if authweb otp expires
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    When I wait enough to get otp expire
    And I try to login to my account
    Then I should verify the below error message
      | Your session has expired. Please refresh the page and sign in again. |

  @wip @manual @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify error message on sign in page due to technical issues
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    When I try to sign in to my account
    Then I should verify the below error message
      | Hmm...looks like we're having some technical issues. Please contact Customer Service at 1-800-BUY-MACY. |

  @wip @manual @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify error message after user account locks down
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    When I try to login with invalid credential for more then allowed attempts
    Then I should verify the below error message
      | We're sorry. Your account has been suspended. For more details on why your account was suspended, please contact Customer Service at 1-800-BUY-MACY(1-800-289-6229) |
