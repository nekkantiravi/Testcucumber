#######################################################################################
# B-91632 :: BUS :: MCOM :: UI :: Create login interface for Checkout Flow
# B-95872 :: BUS/QE :: MCOM :: UI :: Create login interface for Redirection from Bag Checkout Flows
# B-95869 :: BUS/QE :: MCOM :: UI :: Create login interface for Merged Bag Checkout Flows
# Author :: QE Team
#######################################################################################

Feature:Verifying the checkout login interface flow on mcom

 #Checkout sign-in page behavior and function.
#  Background:
#    Given I set the unifiedLoginEnabled header

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify UI elements on checkout sign-in page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    Then I click on continue checkout button on shoppping bag page
    And I should verify below fields on "checkout_sign_in" page
      | email                          |
      | password                       |
      | checkout_button                |
      | continue_checkout_guest_button |
      | pwd_show_button                |
      | forgot_password_link           |
      | no_account_yet                 |

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of "Sign-In" button on checkout signin page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    Then I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I sign in with existing credentials from "checkout_sign_in" page
    And I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "responsive_checkout_signed_in" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify functionality of "checkout as a guest" button on checkout signin page.
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    Then I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I click on checkout as guest button
    And I should be redirected to "responsive_checkout" page
    And I should verify "post_signin" url for "responsive_checkout" link

  @domain_customer @project_unified_login @artifact_account_ui @wip
  Scenario: Verify the functionality of "Hide" button on checkout sign in page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    Then I click on continue checkout button on shoppping bag page
    And I should see typed password using show button in "checkout_sign_in" page
    And I should see hide button on "checkout_sign_in" page

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of forgot password link.
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    Then I click on continue checkout button on shoppping bag page
    When I click on "forgot_password_link" in "checkout_sign_in" page
    And I should be redirected to "forgot_password" page

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of place order through new sign-in page as a signed in user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    Then I click on continue checkout button on shoppping bag page
    And I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "responsive_checkout_signed_in" page
    When I checkout until I reach the shipping & payment page as a "signed in" user
    And I continue checkout until I reach the order confirmation page as an "signed in" user
    And I capture order number and email address from order_confirmation page
    And I navigate to my account page from order confirmation page
    Then I verify the order number on orders layout

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of place order through new sign-in page as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    Then I click on continue checkout button on shoppping bag page
    And I click on checkout as guest button
    And I enter shipping address on guest shipping page
    And I select continue button on guest shipping page
    And I checkout until I reach the payment page as a "guest" user
    And I fill in payment information on guest payment page
    And I select continue button on guest payment page
    And I checkout until I reach the order confirmation page as a "guest" user

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify pre and post function through signin option under eligible offers through shopping bag page
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I click on "wallet_sign_in" in "shopping_bag" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "wallet_signin" link
    When I sign in with valid credentials
    Then I should be redirected to "shopping_bag" page
    And I should verify "post_signin" url for "shopping_bag" link

    ########### Error message handling ##########################

  @wip @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the validation message when no credentials is entered and user hit sign in button
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I click on continue checkout button on shoppping bag page
    Then I should be navigated to checkout signin page
    When I click on "checkout_button" in "checkout_sign_in" page
    Then I should see "<expected error>" validation message on "checkout_sign_in" page

    Examples:
      | expected error                  |
      | Please enter your email address |
      | Please enter your password      |

  @domain_customer @project_unified_login @artifact_account_ui @wip
  Scenario Outline: Verify the validation message when invalid credentials is entered
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I click on continue checkout button on shoppping bag page
    Then I should be navigated to checkout signin page
    When I enter invalid "<email>" and "<password>"
    Then I should see "<expected error>" validation message on "sign_in" page

    Examples:
      | email              | password  | expected error                                                                                                   |
      | testmail           | test12345 | Please enter a valid email address in the following format: jane@email.com                                       |
      | testmail@gmail.com | test      | Your password must be between 5-16 characters, and cannot include . , - \| \\ / = _ or spaces. Please try again. |
      | testmail@gmail.com | test12345 | That email address/password combination is not in our records. Forgot Your Password?                             |
      |                    | test12345 | Please enter your email address                                                                                  |
      | jane@bcom.com      |           | Please enter your password                                                                                       |

  @wip @manual @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify error message if authweb otp expires
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I click on continue checkout button on shoppping bag page
    Then I should be on signin page
    When I wait enough to get otp expire
    And I try to login to my account
    Then I should verify the below error message
      | Your session has expired. Please refresh the page and sign in again. |

  @wip @manual @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify error message on sign in page due to technical issues
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I click on continue checkout button on shoppping bag page
    Then I should be on signin page
    When I try to sign in to my account
    Then I should see the error messaes as below
      | Hmm...looks like we're having some technical issues. Please contact Customer Service at 1-800-BUY-MACY. |

  @wip @manual @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify error message after user account locks down
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I click on continue checkout button on shoppping bag page
    Then I should be on signin page
    When I try to login with invalid credential for more then allowd attempts
    Then I should verify the below error message
      | We're sorry. Your account has been suspended. For more details on why your account was suspended, please contact Customer Service at 1-800-BUY-MACY(1-800-289-6229) |

    ########### Redirection from Bag Checkout Flows ##########################

  @domain_customer @artifact_shopapp @project_unified_login
  Scenario: Verify the functionality of redirection from bag checkout flow through PDP page as a signed in user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I navigate to the PDP page from shopping bag page
    And I click on "quick_bag" in "header" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "responsive_checkout_signed_in" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of redirection from bag checkout flow through PDP page as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag and select checkout
    And I navigate to the PDP page from shopping bag page
    And I click on "quick_bag" in "header" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I click on checkout as guest button
    Then I should be redirected to "responsive_checkout" page
    And I should verify "post_signin" url for "responsive_checkout" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality for redirection from bag checkout flow through browse page as a signed in user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I navigate to browse page from ATB Page
    And I click on "quick_bag" in "header" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "responsive_checkout_signed_in" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality for redirection from bag checkout flow through browse page as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I navigate to browse page from ATB Page
    And I click on "quick_bag" in "header" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I click on checkout as guest button
    Then I should be redirected to "responsive_checkout" page
    And I should verify "post_signin" url for "responsive_checkout" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of redirection from bag checkout flow through registry page as a signed in user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I navigate to registry home page
    And I click on "quick_bag" in "registry_home" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "responsive_checkout_signed_in" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of redirection from bag checkout flow through registry page as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I navigate to registry home page
    And I click on "quick_bag" in "registry_home" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I click on checkout as guest button
    Then I should be redirected to "responsive_checkout" page
    And I should verify "post_signin" url for "responsive_checkout" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of redirection from bag checkout flow through home page as a signed in user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I click on "logo" in "add_to_bag" page
    And I click on "quick_bag" in "home" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "responsive_checkout_signed_in" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of redirection from bag checkout flow through home page as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I click on "logo" in "add_to_bag" page
    And I click on "quick_bag" in "home" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I click on checkout as guest button
    Then I should be redirected to "responsive_checkout" page
    And I should verify "post_signin" url for "responsive_checkout" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of redirection from bag checkout flow through deals page as a signed in user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    When I click on "goto_deals_promotions" in "home" page
    And I click on "quick_bag" in "home" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "responsive_checkout_signed_in" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario: Verify the functionality of redirection from bag checkout flow through deals page as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    When I click on "goto_deals_promotions" in "home" page
    And I click on "quick_bag" in "home" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I click on checkout as guest button
    Then I should be redirected to "responsive_checkout" page
    And I should verify "post_signin" url for "responsive_checkout" link

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario Outline: Verify the functionality of redirection from bag checkout flow through pages under my account dropdown as a signed in user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I select "<input>" link from my account dropdown
    And I click on "quick_bag" in "header" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "responsive_checkout_signed_in" link

    Examples:
      | input                  |
      | goto_star_rewards      |
      | goto_mywallet          |
      | goto_myplenti          |
      | goto_macys_credit_card |
      | goto_mylist            |
      | goto_order_history     |
      | goto_myprofile         |

  @domain_customer @artifact_shopapp @project_unified_login @wip
  Scenario Outline: Verify the functionality of redirection from bag checkout flow through pages under my account dropdown as a guest user
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I select "<input>" link from my account dropdown
    And I click on "quick_bag" in "header" page
    Then I should be redirected to "shopping_bag" page
    When I click on continue checkout button on shoppping bag page
    And I should verify "pre_signin" url for "checkout_signin" link
    And I click on checkout as guest button
    Then I should be redirected to "responsive_checkout" page
    And I should verify "post_signin" url for "responsive_checkout" link

    Examples:
      | input                  |
      | goto_star_rewards      |
      | goto_mywallet          |
      | goto_myplenti          |
      | goto_macys_credit_card |
      | goto_mylist            |
      | goto_order_history     |
      | goto_myprofile         |

    ####################### merge bag scenario #############################

  Scenario: Verify merge bag function for signed in user.
    Given I visit the web site as a registered user
    And I add an "available and orderable" product to my bag
    And I sign out from my current profile
    And I close and reopen the browser
    When I add an "available and orderable" product to my bag and select checkout
    And I click on continue checkout button on shoppping bag page
    Then I should be navigated to checkout signin page
    And I should verify "pre_signin" url for "checkout_signin" link
    When I login with created profile through "checkout_sign_in" page
    Then I should be redirected to "merge_bag" page
    And I should verify below fields on "merge_bag" page
      | add_to_current_bag   |
      | current_bag          |
    When I add all products to current bag
    Then I should be redirected to "merge_bag" page
    When I click on continue checkout button on shoppping bag page
    Then I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "responsive_checkout_signed_in" link

  ### In Below scenario if user signs in to checkout sign page with existing profile, the landing page
  # can be either shopping bag page(if user has saved item in his profile already) or shipping page(if,
  # user has no saved item and product is only added in guest flow from current session). It will be
  # handled at code level.

  @wip @domain_customer @artifact_shopapp @project_unified_login
  Scenario: Verify product added during signed in flow exist for guest flow also in shopping bag for the same session.
    Given I visit the web site as a registered user
    And I add an "available and orderable" product to my bag
    And I sign out from my current profile
    When I add an "available and orderable" product to my bag and select checkout
    Then I verify quickbag count is updating with "2" items in bag
    When I click on continue checkout button on shoppping bag page
    Then I should be navigated to checkout signin page
    And I should verify "pre_signin" url for "checkout_signin" link
    When I sign in with existing credentials from "checkout_sign_in" page
    Then I should be redirected to "responsive_checkout_signed_in" page
    And I should verify "post_signin" url for "responsive_checkout_signed_in" link




