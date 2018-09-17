#Author: Loyalty Bonus Offers Visibility Team
#Date Created: September 01, 2015
#Date Modified:

Feature: Wallet dashboard

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Dashboard Loyallist w/$0 Reward Card Balance
    Given I visit the web site as a loyallist
    Then I should see 0 reward card balance in dashboard
    And I should see "Point Balance" link on dashboard
    When I select "Point Balance" link from dashboard
    Then I verify that the "My Points" page is rendered

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Dashboard Loyallist w/$25 Reward Card Balance
    Given I visit the web site as a loyallist using "lty_with_25_reward_card_balance"
    When I navigate to 'My Account' page
    Then I should see 25 reward card balance in dashboard
    And I should see "Point Balance" link on dashboard
    When I select "Point Balance" link from dashboard
    Then I verify that the "My Points" page is rendered

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Dashboard Loyallist w/$25+ Reward Card Balance
    Given I visit the web site as a loyallist using "lty_with_100_reward_card_balance"
    When I navigate to 'My Account' page
    Then I should see 100 reward card balance in dashboard
    And I should see "Point Balance" link on dashboard
    When I select "Point Balance" link from dashboard
    Then I verify that the "My Points" page is rendered
    And I navigate to My Wallet page
    Then I verify available reward cards balance match the balance displayed in the dashboard

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration @use_bat
  Scenario: Dashboard My Account Links - My account, order status, Pay My Bill
    Given I visit the web site as a registered user
    Then I should see my first name on dashboard
    And I should see "Sign out" link on dashboard
    And I should see "My Account" link on dashboard
    And I should see "Order Status & History" link on dashboard
    And I should see "Pay My Bill" link on dashboard
    When I select "My Account" link from dashboard
    Then I verify that the "my account" page is rendered
    When I select "Order Status & History" link from dashboard
    Then I verify that the "My Order Status & History" page is rendered
    When I select "Pay My Bill" link from dashboard
    Then I verify that the "My Bloomingdale's Credit Card" page is rendered

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Dashboard - NavApp & ShopApp
    Given I visit the web site as a registered user
    When I navigate to category browse page
    And I hover over my account
    Then I should see the dashboard expand
    When I navigate to 'My Account' page
    And I hover over my account
    Then I should see the dashboard expand

  @project_lbov @mode_iship @priority_high @domain_marketing @release_15I @artifact_legacy @use_iteration
  Scenario: Dashboard On - Legacy & Iship
    Given I visit the web site as a registered user
    When I navigate to international context page
    And I hover over my account
    Then I should see the dashboard expand
    When I change country to "a random country"
    Then I should not see my account link

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario: Dashboard - Registry mode
    Given I visit the web site as a registered user
    When I navigate to registry home page
    And I hover over my account
    Then I should see the dashboard expand
    And I close the wallet dashboard
    When I navigate to category browse page
    And I hover over my account
    Then I should see the dashboard expand

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario: verify as registered user,user should see become a loyallist link on dashboard when user clicks on the link navigate to enrollment page
    Given I visit the web site as a registered user
    When I hover over my account
    Then I should see loyallist icon on dashboard
    And I should see loyallist bulleted information
    Then I should see "Sign Up" link on dashboard
    When I select "Sign Up" link from dashboard
    Then I should navigate to loyallist page

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: verify as a loyallist user, user loyalty number not updated in my account
    Given I visit the web site as a loyallist
    When I navigate to the loyalty account summary page
    And I can remove my loyallist number
    Then I should see loyallist icon on dashboard
    And I should see loyallist bulleted information
    And I should see "Sign Up" link on dashboard
    When I select "Sign Up" link from dashboard
    Then I should navigate to loyallist page

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: verify as registed user, user select sign out link on dashboard
    Given I visit the web site as a registered user
    When I hover over my account
    Then I should see my first name on dashboard
    And I should see "Sign out" link on dashboard
    When I select "Sign out" link from dashboard
    Then I should be on home page
    And I hover over my account
    Then I should not see the dashboard expand
    And I navigate to sign in page

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: verify as a registered user, user navigate to my account page after selecting my account link from header
    Given I visit the web site as a registered user
    When I hover over my account
    Then I should see the dashboard expand
    And I navigate to My Account page

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Dashboard Signed in user with current offers in wallet
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I hover over my account
    And I select "All Offers in My bWallet" link from dashboard
    Then I verify that the "My Wallet" page is rendered
    When I hover over my account
    Then I should see the most recent offers in wallet dashboard

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration
  Scenario: Dashboard Signed in user with no current or future offers in wallet
    Given I visit the web site as a registered user
    When I have zero current and future offers in wallet
    And I hover over my account
    Then I should see "MY OFFERS" wallet title
    And I should see "There are no offers saved to your bWallet." text in wallet dashboard
    And I should not see "All Offers in My bWallet" link on dashboard
    When I select "Sales & Promotions" link from dashboard
    Then I should be redirected to expected url page

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration @use_manual
  Scenario: Informational Tool tip - first time signed in
    Given I visit the web site as a guest user
    When I sign in first time
    Then I should see the tool tip in my account page
    And I should see the tool tip disappear after 3 seconds
    When I navigate away from the page
    Then I should not see the tool tip displayed on my next page
    When I sign out then sign in again
    Then I should not see the tool tip in my account page

#[session storage, if user clears cache/Cookies, tool tip will show again, if user in incognito mode, tool tip will show again]
  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration @use_manual
  Scenario: Dashboard Informational Tooltip - clear cache
    Given I visit the web site as a guest user
    When I sign in first time
    Then I should see the tool tip in my account page
    When I clear my cache and sign in again
    Then I should see the tool tip in my account page
