###################################################################################
# B-91630 :: BUS :: MCOM :: UI :: Create login interface for Standard Sign-in flow
# B-94545 :: BUS :: MCOM :: UI :: Create Error State Messages
# Author :: QE Team
###################################################################################

Feature: Standard login interface.

  #"Common standard login interface i.e.(/account/signin) for across all the .com sign in entry points."
  Background:
    Given I set the unifiedLoginEnabled header

  ### This section covers the different entry point to sign in page for desktop,
  ##  only regular sign flows are considered here. checkout, Registry, Loyallist
  ##  will be covered in separate feature file.

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from homepage.
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
      | goto_sign_in_link           | home                            |
      | goto_credit_pay_bill_online | credit_service_gateway_signedin |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from my account dropdown options.
    Given I visit the web site as a guest user
    When I select "<input>" link from my account dropdown
    Then I should be on signin page
    And I should verify "pre_signin" url for "<input>" link
    When I sign in with valid credentials
    Then I should be redirected to "<target>" page
    And I should verify "post_signin" url for "<target>" link

    Examples:
      | input             | target            |
      | goto_star_rewards | star_rewards      |
      | goto_mywallet     | oc_my_wallet      |
      | goto_myplenti     | usl_join_for_free |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from profile under my account dropdown options.
    Given I visit the web site as a guest user
    When I select "goto_myprofile" link from my account dropdown
    And I create a new profile
    Then I should see user logged in to account successfully

  ########### List Scenarios  ####################

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from my account dropdown through wishlist page.
    Given I visit the web site as a guest user
    When I select "goto_mylist" link from my account dropdown
    And I click on "<input>" in "wish_list" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<input>" link
    When I sign in with valid credentials
    Then I should be redirected to "<target>" page
    And I should verify "post_signin" url for "<target>" link

    Examples:
      | input            | target    |
      | send_me_alerts   | wish_list |
      | goto_signin_link | wish_list |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from my account dropdown through find a list page
    Given I visit the web site as a guest user
    When I select "goto_mylist" link from my account dropdown
    And I click on "find_list_link" in "navigation" page
    And I click on "find_list_sign_in_link" in "navigation" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "find_list_signin" link
    When I sign in with valid credentials
    Then I should be redirected to "wish_list" page
    And I should verify "post_signin" url for "find_list" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from header through wishlist page.
    Given I visit the web site as a guest user
    When I click on "goto_wishlist" in "home" page
    And I click on "<input>" in "wish_list" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<input>" link
    When I sign in with valid credentials
    Then I should be redirected to "wish_list" page
    And I should verify "post_signin" url for "header_wish_list" link

    Examples:
      | input            |
      | send_me_alerts   |
      | goto_signin_link |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from header through find a list page
    Given I visit the web site as a guest user
    When I click on "goto_wishlist" in "home" page
    And I click on "find_list_link" in "navigation" page
    And I click on "find_list_sign_in_link" in "navigation" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "find_list_signin" link
    When I sign in with valid credentials
    Then I should be redirected to "wish_list" page
    And I should verify "post_signin" url for "find_list" link

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from holiday gift guide through wishlist page
    Given I visit the web site as a guest user
    When I select Holiday gift guide from Gift dropdown
    Then I should be navigated to "/social/holiday-gift-guide/" url
    When I click on "goto_my_wish_list" in "navigation" page
    And I click on "<input>" in "wish_list" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<input>" link
    When I sign in with valid credentials
    Then I should be redirected to "<target>" page
    And I should verify "post_signin" url for "<target>" link

    Examples:
      | input            | target    |
      | send_me_alerts   | wish_list |
      | goto_signin_link | wish_list |

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from holiday gift guide through find a list page
    Given I visit the web site as a guest user
    When I select Holiday gift guide from Gift dropdown
    Then I should be navigated to "/social/holiday-gift-guide/" url
    When I click on "goto_my_wish_list" in "navigation" page
    And I click on "find_list_link" in "navigation" page
    And I click on "find_list_sign_in_link" in "navigation" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "find_list_signin" link
    When I sign in with valid credentials
    Then I should be redirected to "wish_list" page
    And I should verify "post_signin" url for "find_list" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from left navigation through wishlist page.
    Given I visit the web site as a guest user
    When I click on "goto_order_status" in "home" page
    When I click on "goto_my_wish_list" in "navigation" page
    And I click on "<input>" in "wish_list" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<input>" link
    When I sign in with valid credentials
    Then I should be redirected to "wish_list" page
    And I should verify "post_signin" url for "<target>" link

    Examples:
      | input            | target           |
      | send_me_alerts   | lefnav_wish_list |
      | goto_signin_link | lefnav_wish_list |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from left navigation through find a list page.
    Given I visit the web site as a guest user
    When I click on "goto_wishlist" in "home" page
    And I click on "find_list_link" in "navigation" page
    And I click on "find_list_sign_in_link" in "navigation" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "find_list_signin" link
    When I sign in with valid credentials
    Then I should be redirected to "wish_list" page
    And I should verify "post_signin" url for "find_list" link

    ########### Sign In through Macy's Credit Card option ##################

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from my account dropdown through macys credit card page.
    Given I visit the web site as a guest user
    When I select "goto_macys_credit_card" link from my account dropdown
    And I click on "sign_in_button" in "credit_service_gateway_guest" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "creditcard_sign_in" link
    When I sign in with valid credentials
    Then I should be redirected to "credit_service_gateway_signedin" page
    And I should verify "post_signin" url for "my_credit" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from footer through macys credit card page.
    Given I visit the web site as a guest user
    When I click on "goto_credit_services" in "home" page
    And I click on "sign_in_button" in "credit_service_gateway_guest" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "creditcard_sign_in" link
    When I sign in with valid credentials
    Then I should be redirected to "credit_service_gateway_signedin" page
    And I should verify "post_signin" url for "my_credit" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from left navigation through macys credit card page.
    Given I visit the web site as a guest user
    When I click on "goto_order_status" in "home" page
    And I click on "goto_my_credit_card" in "navigation" page
    And I click on "sign_in_button" in "credit_service_gateway_guest" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "creditcard_sign_in" link
    When I sign in with valid credentials
    Then I should be redirected to "credit_service_gateway_signedin" page
    And I should verify "post_signin" url for "my_credit" link

  #################### Sign In through Order History #####################

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from my account dropdown through order history page.
    Given I visit the web site as a guest user
    When I select "goto_order_history" link from my account dropdown
    And I click on "goto_order_history_sign_in" in "order_status" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "order_history_sign_in" link
    When I sign in with valid credentials
    Then I should be redirected to "order_status" page
    And I should verify "post_signin" url for "order_status" link

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from footer through order history page.
    Given I visit the web site as a guest user
    When I click on "goto_order_status" in "home" page
    And I click on "goto_order_history_sign_in" in "order_status" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "order_history_sign_in" link
    When I sign in with valid credentials
    Then I should be redirected to "order_status" page
    And I should verify "post_signin" url for "order_status" link

    #################### Sign In through Deals & Promotions page #####################

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from find out more link through deals&promotions page.
    Given I visit the web site as a guest user
    When I click on "goto_deals_promotions" in "home" page
    And I click on "find_out_more" in "my_offers" page
    And I click on "<input>" in "my_offers" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<input>" link
    When I sign in with valid credentials
    Then I should be redirected to "<target>" page
    And I should verify "post_signin" url for "<target>" link

    Examples:
      | input               | target                |
      | wallet_sign_in_link | deals_and_promotions  |
      | use_my_wallet       | oc_my_wallet          |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface through deals&promotions page.
    Given I visit the web site as a guest user
    When I click on "goto_deals_promotions" in "home" page
    And I click on "<input>" in "my_offers" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<input>" link
    When I sign in with valid credentials
    Then I should be redirected to "<target>" page
    And I should verify "post_signin" url for "<target>" link

    Examples:
      | input         | target              |
      | get_started   | oc_my_wallet        |
      | add_to_wallet | deals_and_promotions|

    #################### Sign In through Left Navigation #####################

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from left navigation through order history page.
    Given I visit the web site as a guest user
    When I click on "goto_order_status" in "home" page
    And I click on "<leftnav>" in "navigation" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<leftnav>" link
    When I sign in with valid credentials
    Then I should be redirected to "<target>" page
    And I should verify "post_signin" url for "<target>" link

    Examples:
      | leftnav                   | target                |
      | goto_my_preferences_link  | my_preferences        |
      | goto_my_address_book_link | order_status          |
      | goto_my_wallet_link       | order_status          |
      | goto_my_plenti            | usl_join_for_free     |
      | goto_star_rewards         | star_rewards          |
      | goto_my_account           | order_status          |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from left navigation profile through order history page.
    Given I visit the web site as a guest user
    When I click on "goto_order_status" in "home" page
    And I click on "goto_my_profile" in "navigation" page
    And I create a new profile
    Then I should see user logged in to account successfully

    #################### Sign In through PDP #####################

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from wishlist overlay through pdp page.
    Given I visit the web site as a guest user
    And I navigate to a random product
    When I click on "add_to_wishlist_image" in "product_display" page
    Then I should see Wish List confirmation overlay displays
    When I select signIn link on wishlist overlay in PDP page
    Then I should be on signin page
    And I should verify "pre_signin" url for "pdp_signin" link
    When I sign in with valid credentials
    Then I should be redirected to "product_display" page
    And I should verify "post_signin" url for "product_display" link

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface from pdp page.
    Given I visit the web site as a guest user
    And I navigate to a random product
    When I click on "<input>" in "product_display" page
    Then I should be on signin page
    And I should verify "pre_signin" url for "<input>" link
    When I sign in with valid credentials
    Then I should be redirected to "<target>" page
    And I should verify "post_signin" url for "<target>" link

    Examples:
      | input                 | target       |
      | goto_ask_question     | ask_ques     |
      | write_a_review_button | pdp_reviews  |

    #################### Sign In through Plenti flow from shopping bag page #####################

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from already a member through plenti page.
    Given I navigate to the url "/ce/splash/plenti/welcome?" as guest user
    When I click on "already_a_member" in "usl_join_for_free" page
    And I click on "log_in_profile" in "usl_join_for_free" page
    Then I should be on signin page
    When I sign in with valid credentials
    Then I should be redirected to "usl_home" page

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the pre and post login behavior with standard login interface through from plenti welcome page.
    Given I navigate to the url "/ce/splash/plenti/joinfree" as guest user
    When I click on "<input>" in "usl_join_for_free" page
    Then I should be on signin page
    When I sign in with valid credentials
    Then I should be redirected to "<target>" page

    Examples:
      | input                  | target              |
      | join_for_free          | usl_enrollment      |
      | registered_plenti_card | usl_account_summary |

  ##################### Sign In through beauty box ########################

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from beauty box subscription page.
    Given I visit the web site as a guest user
    And I navigated to "beauty_subscription" page through "BEAUTY" FOB
    When I click on "beauty_subscription_button" in "beauty_subscription" page
    Then I should be on signin page
    When I sign in with valid credentials
    Then I should be redirected to "beauty_subscription" page

  ################# sign in through easy returns ########################

  @wip @defect_prod_navigation @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify the pre and post login behavior with standard login interface from easy returns page.
    Given I visit the web site as a guest user
    And I click on "goto_returns" in "home" page
    And I click on "easy_returns_link" in "easy_returns" page
    When I click on "sign_in_link" in "easy_returns" page
    Then I should be on signin page
    When I sign in with valid credentials
    Then I should be redirected to "easy_returns" page

  ##Based upon standard login url "/account/signin", it covers the UI fields and the function
  #of different links. ex- sign in button, show password, target page etc.

  @domain_customer @project_unified_login @artifact_account_ui @UL_localRun
  Scenario: Verify standard sign-in page
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    And I should verify below fields on "sign_in" page
      | email                |
      | password             |
      | verify_page          |
      | create_profile       |
      | pwd_show_button      |
      | forgot_password_link |

  @domain_customer @project_unified_login @artifact_account_ui @UL_localRun
  Scenario: Verify the forgot password link behavior on standard sign in page
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    When I click on "forgot_password_link" in "sign_in" page
    Then I should be redirected to "forgot_password" page

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify create an account button behavior on standard sign in page
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    When I click on "create_account_button" in "sign_in" page
    Then I should be redirected to "create_profile" page
    When I create a new profile
    And I should see user logged in to account successfully

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify show option button behavior in password field.
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    And I should see typed password using show button in "sign_in" page
    And I should see hide button on "sign_in" page

  ########## Error message handling scenario #########

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the validation message when no credentials is entered
    Given I navigate to the url "/account/signin" as guest user
    When I click on "sign_in_button" in "sign_in" page
    Then I should see "<expected error>" validation message on "sign_in" page

    Examples:
      | expected error                  |
      | Please enter your email address |
      | Please enter your password      |

  @domain_customer @project_unified_login @artifact_account_ui
  Scenario Outline: Verify the validation message when invalid credentials is entered
    Given I navigate to the url "/account/signin" as guest user
    When I enter invalid "<email>" and "<password>"
    Then I should see "<expected error>" validation message on "sign_in" page

    Examples:
      | email              | password  | expected error                                                                                                   |
      | testmail           | test12345 | Please enter your email address in this format: jane@company.com                                                 |
      | testmail@gmail.com | test      | Your password must be between 7-16 characters, and cannot include . , - \| \\ / = _ or spaces. Please try again. |
      | testmail@gmail.com | test12345 | That email address/password combination is not in our records. Forgot Your Password?                             |
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
    Then I should see the error messaes as below
      | Hmm...looks like we're having some technical issues. Please contact Customer Service at 1-800-BUY-MACY. |

  @wip @manual @domain_customer @project_unified_login @artifact_account_ui
  Scenario: Verify error message after user account locks down
    Given I navigate to the url "/account/signin" as guest user
    Then I should be on signin page
    When I try to login with invalid credential for more then allowed attempts
    Then I should see "<expected error>" validation message on "sign_in" page
      | We're sorry. Your account has been suspended. For more details on why your account was suspended, please contact Customer Service at 1-800-BUY-MACY(1-800-289-6229) |

