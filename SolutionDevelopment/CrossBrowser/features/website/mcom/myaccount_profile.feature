Feature: User profile scenarios

  @scenario1 @domain_customer_management @xbrowser @xbrowser_one @high
  Scenario: Verify that customer is able to create a new profile
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully

  @scenario2 @domain_customer_management @xbrowser @xbrowser_one
  Scenario: Verify that customer is successfully logged in to his account
    Given I visit the web site as a guest user
    When I sign in to my existing profile
    Then I should be navigated to My Account Page

  @scenario3 @domain_customer_management @xbrowser @xbrowser_one
  Scenario: Verification of My account page
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I verify the My Account Pages are rendered properly
      | my account                  |
      | my profile                  |
      | my preferences              |
      | my address book             |
      | my wallet                   |
      | order status                |
      | furniture & mattress status |
      | gift card balance           |
      | wish list                   |

  @scenario4 @domain_customer_management @xbrowser @xbrowser_one
  Scenario: Verify the functionality of updating the user's profile
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I navigate to my profile page
    And I update profile details on my profile
    Then I verify my profile is updated

  @scenario5 @domain_marketing @xbrowser @xbrowser_one @high
  Scenario: As a fully enrolled USL user, Verify USL basic attributes on USL account summary page
    Given I visit the web site as a registered user
    When I add fully_enrolled_usl id on my account page
    And I navigate to USL account summary page
    Then I should see USL basic attributes on USL account summary page

  # unable to handle plenti authentication popup in saucelabs. working on it.
  @scenario6 @domain_marketing @xbrowser @xbrowser_one @wip
  Scenario Outline: As a guest or signed in user, user should be redirected to enrollment confirmation page after submitting additional information on USL site
    Given I visit the web site as a <user_type> user
    And I am on the USL loyalty home page
    When I enroll into the USL program from loyalty home page
    Then I should see USL enrollment confirmation page
    And I should see attributes on USL enrollment confirmation page:
      | usl_id                 |
      | shop_now_button        |
      | my_account_button      |
      | apply_now_button       |
      | confirmation_banner    |
      | congratulation_message |

    Examples:
      | user_type  |
      | guest      |
    #  | registered |

  @scenario7 @domain_customer_management @xbrowser @xbrowser_one
  Scenario:Verify Create Profile page does not show any references to, add card to your profile section
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    Then I should not see credit card section on create profile page

  @scenario8 @domain_customer_management @xbrowser @xbrowser_two @wip
  Scenario Outline: Verify no thanks and close button functionality on add card overlay
    Given I visit the web site as a guest user
    When I create a new profile without closing the add card overlay
    Then I should see one time add card overlay and its components
    When I select "<field>" on add credit card overlay
    Then I should not see one time add card overlay and its components
    And I should be redirected to "<page_name>" page

    Examples:
      | field                             | page_name  |
      | add_card_overlay_no_thanks_button | my_account |
      | add_card_overlay_close_button     | my_account |

  @scenario9 @domain_customer_management @xbrowser @xbrowser_two
  Scenario: Verify the gateway page and its components for guest user
    Given I visit the web site as a guest user
    When I navigate to the "credit services" page from footer
    Then I should see below fields on credit service gateway page:
      | credit_card_headline      |
      | sign_in_button            |
      | add_card_section          |
      | apply_now_box             |
      | activation_box            |
      | other_ways_to_pay_link    |
      | bank_privacy_notice       |
      | credit_card_agreement     |
      | amex_card_agreement       |
      | amex_card_disclaimer_text |

  @scenario10 @domain_customer_management @xbrowser @xbrowser_two
  Scenario: Verify the credit servicing footer link navigation for signed-in user from My account page
    Given I visit the web site as a registered user
    Then I should see below footer credit links:
      | link_text           | credit_link                     |
      | MACY'S CREDIT CARD  | goto_credit_services            |
      | pay bill            | goto_credit_pay_bill_online     |
      | cardholder benefits | goto_credit_cardholder_benefits |
      | apply & learn more  | goto_credit_apply_now           |
      And I should be navigated to below respective credit services pages:
      | link_text           | landing_page                    | credit_link                     |
      | MACY'S CREDIT CARD  | credit_service_gateway_signedin | goto_credit_services            |
      | cardholder benefits | credit_benefits                 | goto_credit_cardholder_benefits |
      | apply & learn more  | credit_service_marketing        | goto_credit_apply_now           |
      | pay bill            | credit_service_gateway_signedin | goto_credit_pay_bill_online    |

  @scenario11 @domain_customer_management @xbrowser @xbrowser_two
  Scenario: Verify Add Card, Activate Card\Apply Now button functionality on gateway page for Signed in user without card
    Given I visit the web site as a registered user
    When I navigate to the "credit services" page from footer
    Then I should be navigated to below citi pages from citi gateway page:
      | apply_credit_card    |
      | fusion_add_card      |
      | fusion_activate_card |

  @scenario12 @domain_marketing @xbrowser @xbrowser_two @data_dependency
  Scenario: Verify that signed in user is able to add all wallet eligible offers to wallet from deals and promotions page
    Given I visit the web site as a registered user
    When I visit the deals and promotions page
    And I add all wallet eligible offers from deals and promotions page
    Then I should see the added offers in my wallet page

  @scenario13 @domain_marketing @xbrowser @xbrowser_two @data_dependency
  Scenario: Verify in bag more than one code are displaying as comma separated in info/Exclusions overlay
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I saved omnichannel offer having more than one promo code in wallet
    And I verify the promo code added to my wallet page