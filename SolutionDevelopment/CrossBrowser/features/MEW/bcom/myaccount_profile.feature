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
      | My Account or SIGN IN / SIGN UP |
    Then I should be navigated to My Account Page

  @scenario3 @xbrowser_tablet @xbrowser_mew @domain_customer_management
  Scenario: Verification of My account page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | My Account or SIGN IN / SIGN UP |
    Then I verify the My Account Pages are rendered properly using mobile website
      | my profile      |
      | my address book |
      | oc my wallet    |
      | order status    |
      | wish list       |

  @scenario4 @xbrowser_tablet @domain_customer_management @xbrowser_mew
  Scenario: Verify the functionality of updating the user's profile
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | My Account or SIGN IN / SIGN UP |
    And I navigate to my profile page using mobile website
    And I update profile details on my profile
    Then I verify my profile is updated

  @scenario5 @xbrowser_tablet @domain_customer_management @xbrowser_mew
  Scenario: Verify the credit services link navigation for signed-in user from My account page
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | My Account or SIGN IN / SIGN UP |
    Then I should see below credit services links in my account page:
      | add_my_card_button    |
      | card_apply_now_button |
    And I should be navigated to below respective credit services pages using mobile website:
      | landing_page      | credit_link           |
      | fusion_add_card   | add_my_card_button    |
      | apply_credit_card | card_apply_now_button |

  @scenario6 @xbrowser_tablet @domain_customer_management @xbrowser_mew
  Scenario: Verify Create Profile page does not show any references to, add card to your profile section
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | My Account or SIGN IN / SIGN UP |
    And I navigate to the create profile page
    Then I should not see credit card section on create profile page

  # add card overlay functionality is deprecated
  @scenario7 @xbrowser_tablet @domain_customer_management @xbrowser_mew @wip
  Scenario Outline: Verify no thanks and close button functionality on add card overlay
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I create a new profile in mobile site without closing the add card overlay
    Then I should see one time add card overlay and its components using mobile website
    When I select "<field>" on add credit card overlay
    Then I should not see one time add card overlay and its components using mobile website
    And I should be redirected to "my_account" page

    Examples:
      | field                             |
      | add_card_overlay_no_thanks_button |
      | add_card_overlay_close_button     |

  @scenario8 @xbrowser_tablet @domain_marketing @xbrowser_mew
  Scenario: Verify user should be able to enroll into loyalty program as a signed user
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | My Account or SIGN IN / SIGN UP |
    And I navigate to the loyallist enrollment page as a registered user using mobile website
    Then I should be able to enroll in to the loyalty program as a "signed_in" user using mobile website

  @scenario9 @xbrowser_tablet @domain_marketing @xbrowser_mew
  Scenario: Verify user can add promo code to his wallet
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | My bWallet |
    Then I add an offer to my wallet using mobile website

  @scenario10 @xbrowser_mew @domain_marketing
  Scenario: Verify user should be able to enroll into loyalty program as a guest user
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | My Loyallist       |
      | Become a Loyallist |
    And I navigate to loyallist enrollment page from become a loyallist page
    Then I should be able to enroll in to the loyalty program as a "guest" user using mobile website
    And I navigate back to "home" page using mobile website
    And I navigate the global navigation menu as follows:
      | My Loyallist         |
      | View My Points       |
    Then I should see the "loyallist_account_summary" page
    And I navigate back to "home" page using mobile website
    And I navigate the global navigation menu as follows:
      | My Loyallist   |
      | My Perks       |
    Then I should see the "loyalty_benefits" page

  @scenario11 @xbrowser_mew @domain_marketing
  Scenario: Verify Loyalty sub nav menu items are clickable and navigate to the correct page for guest user
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | My Loyallist           |
      | Already a Loyallist    |
    Then I should see the "sign_in" page
    And I navigate back to "home" page using mobile website
    And I navigate the global navigation menu as follows:
      | My Loyallist          |
      | Become a Loyallist    |
    Then I should see the "become_loyallist" page
    And I navigate back to "home" page using mobile website
    And I navigate the global navigation menu as follows:
      | My Loyallist   |
      | My Perks       |
    Then I should see the "loyalty_benefits" page

  @scenario12 @xbrowser_mew @loaylty_data_dependency @domain_marketing
  Scenario: Verify that the loaylty dashboard page displays with points and reward card information
    Given I visit the mobile web site as a registered user without add CC
    And I navigate the global navigation menu as follows:
      | My Account or SIGN IN / SIGN UP |
    When I navigate to the loyallist account association page using mobile website
    And I should be able to associate my account by loyallist number using "top_tier" details on mobile website
    Then I verify loaylty points and reward card information displayed on loyallist dashboard

  @scenario13 @xbrowser_mew @domain_customer_management
  Scenario: Verfiy add an offer button and loyallist information displays for signed in loyalty user on Wallet Page
    Given I visit the mobile web site as a guest user without add CC
    When I navigate the global navigation menu as follows:
      | My Loyallist       |
      | Become a Loyallist |
    And I navigate to loyallist enrollment page from become a loyallist page
    Then I should be able to enroll in to the loyalty program as a "guest" user using mobile website
    And I navigate back to "home" page using mobile website
    When I navigate the global navigation menu as follows:
      | MY bWALLET |
    Then I should see following elements on "oc_my_wallet" page:
      | add_offer_btn          |
      | loyalty_points         |
      | loyallist_progress_bar |
      | loyallist_reward_text  |

  @scenario14 @domain_customer_management @xbrowser_mew
  Scenario: Verify user need to sign in on My Account while in soft sign in state
    Given I visit the mobile web site as a registered user without add CC
    And I change my state from Signed In to Soft Signed In
    When I navigate the global navigation menu as follows:
      | My Account or SIGN IN / SIGN UP |
    Then I should see following elements on "sign_in" panel:
      | verify_page   |
      | email         |
      | password      |
