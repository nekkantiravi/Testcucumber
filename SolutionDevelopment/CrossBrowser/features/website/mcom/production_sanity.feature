Feature: Production sanity scenarios for my account

  @scenario1 @domain_customer_management @xbrowser @xbrowser_one
  Scenario: Verify that clicking on MyAccount and Signin links loads respective page successfully
    Given I visit the web site as a guest user
    When I click on signIn link
    Then SignIn page should get loaded
    When I sign in to my existing production profile
    When I click on "my account" link in the header
    Then I should be navigated to My Account Page

  @scenario2 @domain_customer_management @xbrowser @xbrowser_one
  Scenario: Verify that User is able to create profile and signIn and signOut with created profile and update profile
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I create a new profile
    Then I should see user logged in to account successfully
    When I sign out from my current profile
    And I sign in with created profile
    And I click on "my account" link in the header
    Then I should be navigated to My Account Page
    And I should see user logged in to account successfully
    When I navigate to my profile page
    And I update profile details on my profile
    Then I verify my profile is updated


  @scenario3 @domain_customer_management @xbrowser @xbrowser_one
  Scenario: Verify after Signin, Myaccount sections are loaded correctly
    Given I visit the production web site as a "prod_user" registered user
    And I click on "my account" link in the header
    Then I should be navigated to My Account Page
    And I should see below fields on my account page:
      | my_profile_section     |
      | verify_page           |
      | my_preferences_section    |
      | preferred_store_link      |
      | credit_card_section       |
      | csr_add_card_to_my_account_button   |
      | csr_apply_today_link     |
      | my_wallet_section    |
      | star_rewards      |
      | my_account_order_history_section |
      |order_history |
      |choose_store |
      |wish_list_section |
      |plenti |


  @scenario4 @domain_customer_management @xbrowser @xbrowser_one
  Scenario: Verify after Signin, all left navigation links are working properly
    Given I visit the production web site as a "prod_user" registered user
    And I click on "my account" link in the header
    Then I should be navigated to My Account Page
    And I verify the My Account Pages are rendered properly
      | my account                  |
      | my profile                  |
      | my preferences              |
      | my address book             |
      | my wallet                   |
      | order status                |
      | furniture & mattress status |
      | gift card balance           |
      | wish list                   |
    When I click on "my account" link in the header
    Then I verify other left navigation links on myaccounts page
    | credit service gateway signedin           |
    | credit benefits      |
    | credit service marketing       |
    | my plenti                      |

  @scenario5 @domain_customer_management @xbrowser @xbrowser_one
  Scenario:  Verify I can add address to address book, make primary, delete address,update preferences
    Given I visit the production web site as a "prod_user" registered user
    When I navigate to my account page
    And I add shipping address
    Then address should get added to profile
    When I edit address
    Then address should get updated
    When I add another shipping address
    Then another shipping address should be added to profile
    When I make second address as primary
    Then second address should be made primary
    When user deletes one address
    Then address should get deleted
    When I navigate to shipping address page
    And user deletes one address
    Then address should get deleted
    When I navigate to My Preferences page from My Account page
    And I update phone in my Preferences page
    | 5165657681 |
    Then My Preferences should be updated
      | 5165657681 |

  @scenario6 @domain_customer_management @xbrowser @xbrowser_one
  Scenario:  Check footer links for all customer domain links.
    Given I visit the web site as a guest user
    Then I should be navigated to below respective customer pages:
      | CUSTOMER SERVICE |
      | order tracking |
      | returns        |
      | shipping & delivery |
      | contact us          |
      | para ayuda          |



  @scenario7 @domain_customer_management @xbrowser @xbrowser_one
  Scenario:  Verify User is not able to create profile for simple password,phone and dob
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I create a new account with all same digits for phone
    Then I should receive "Your phone number can't be all the same number and must be entered in this format: 800-555-1212. Please try again." same phone number error message
    When I update phone number
      | 5165657681 |
    And I update password with simple password
    Then I should receive "Sorry, but your password isn't strong enough. For your security, please make it more complex." a weak pwd error message
    When I navigate to my account page
    And I navigate to create profile page
    When I create a new profile and my age is less that 13 years
    Then I should receive "Please update the boxes highlighted in red." error message
