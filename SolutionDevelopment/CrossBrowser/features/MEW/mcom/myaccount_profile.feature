Feature: User profile scenarios

  @scenario1 @xbrowser_tablet @domain_customer_management @xbrowser_mew
  Scenario: Verify that customer is able to create a new profile
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I create a new profile in mobile site
    Then I should see user logged in to account successfully

  @scenario2 @xbrowser_tablet @domain_customer_management @xbrowser_mew
  Scenario: Verify that customer is successfully logged in to his account
    Given I visit the mobile web site as a registered user without add CC
    And I sign out from my current mobile site profile
    When I sign in to my same profile using mobile website
    And I navigate the global navigation menu as follows:
      | My Account |
    Then I should be navigated to My Account Page

    # Gift card service is unavailable
  @scenario3 @xbrowser_tablet @domain_customer_management @xbrowser_mew
  Scenario: Verification of My account pages
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | My Account |
    Then I verify the My Account Pages are rendered properly using mobile website
      | my profile                  |
      | my preferences              |
      | my address book             |
      | oc my wallet                |
      | order status                |
      | furniture mattress status   |
      | wish list                   |
#      | gift card balance           |

  @scenario4 @xbrowser_tablet @domain_customer_management @xbrowser_mew
  Scenario: Verify the functionality of updating the user's profile
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | My Account |
    And I navigate to my profile page using mobile website
    And I update profile details on my profile
    Then I verify my profile is updated

  @scenario5 @xbrowser_tablet @xbrowser_mew @domain_marketing
  Scenario: As a fully enrolled USL user, Verify USL basic attributes on USL account summary page
    Given I visit the mobile web site as a registered user without add CC
    And I navigate the global navigation menu as follows:
      | My Account |
    When I add fully_enrolled_usl id on my account page using mobile website
    And I navigate to my plenti page using mobile website
    Then I should see USL basic attributes on plenti summary page

  @scenario6 @xbrowser_tablet @domain_marketing @xbrowser_mew @involves_authentication_popup
  Scenario: As a guest user, user should be redirected to enrollment confirmation page after submitting additional information on USL site
    Given I visit the mobile web site as a guest user
    When I navigate to Plenti enrollment page using mobile website
    And I enroll into the Plenti program using mobile website
    Then I should see following elements on "plenti_confirmation" page:
      | usl_id                 |
      | shop_now_button        |
      | my_account_button      |
      | congratulation_message |

  @scenario6 @xbrowser_tablet @domain_marketing @xbrowser_mew @involves_authentication_popup
  Scenario: As a signed in user, user should be redirected to enrollment confirmation page after submitting additional information on USL site
    Given I visit the mobile web site as a guest user
    And I create a new profile in mobile site
    When I navigate to Plenti enrollment page using mobile website
    And I enroll into the Plenti program using mobile website
    Then I should see following elements on "plenti_confirmation" page:
      | usl_id                 |
      | shop_now_button        |
      | my_account_button      |
      | congratulation_message |

  @scenario7 @xbrowser_tablet @domain_customer_management @xbrowser_mew
  Scenario: Verify Create Profile page does not show any references to, add card to your profile section
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | My Account |
    And I navigate to the create profile page
    Then I should not see credit card section on create profile page

  # add card overlay functionality is deprecated
  @scenario8 @xbrowser_tablet @domain_customer_management @wip
  Scenario Outline: Verify no thanks and close button functionality on add card overlay
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I create a new profile in mobile site without closing the add card overlay
    Then I should see one time add card overlay and its components
    When I select "<field>" on add credit card overlay
    Then I should not see one time add card overlay and its components
    And I should be redirected to "my_account" page

    Examples:
      | field                             |
      | add_card_overlay_no_thanks_button |
      | add_card_overlay_close_button     |

  @scenario9 @xbrowser_tablet @domain_customer_management @xbrowser_mew
  Scenario: Verify the credit services link for signed-in user from My account page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | My Account |
    Then I should see below credit services links in my account page:
      | add_my_card_button    |
      | card_apply_now_button |
    And I should be navigated to below respective credit services pages using mobile website:
      | landing_page      | credit_link           |
      | fusion_add_card   | add_my_card_button    |
      | apply_credit_card | card_apply_now_button |

  @scenario10 @xbrowser_tablet @domain_marketing @xbrowser_mew
  Scenario: Verify that signed in user is able to add all wallet eligible offers to wallet from deals and promotions page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | Deals |
    And I add all wallet eligible offers from deals and promotions page using mobile website
    And I navigate back to "home" page using mobile website
    When I navigate the global navigation menu as follows:
      | Wallet |
    Then I should see the added offers in my wallet page using mobile website

  @scenario11 @xbrowser_tablet @domain_marketing @xbrowser_mew
  Scenario: Verify user can add promo code to his wallet
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | Wallet |
    Then I add a valid offer to my wallet using mobile website
