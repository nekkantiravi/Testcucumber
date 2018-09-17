Feature: BATs for Marketing BCOM


  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_shopapp @use_iteration @use_bat @Marketing_CBT
  Scenario: Dashboard My Account Links - My account, order status, Pay My Bill
    Given I visit the web site as a registered user
    Then I should see my first name on dashboard
    And I should see "Sign out" link on dashboard
    And I should see "My Account" link on dashboard
    And I should see "Order Status & History" link on dashboard
    And I should see "Pay My Bill" link on dashboard
    When I select "My Account" link from dashboard
    Then I verify that the "My Account - Bloomingdale's" page is rendered
    When I select "Order Status & History" link from dashboard
    Then I verify that the "Check My Order Status & Order History - Bloomingdale's" page is rendered
    When I select "Pay My Bill" link from dashboard
    Then I verify that the "Credit Gateway" page is rendered

  @use_bat @artifact_shopapp @priority_high @artifact_shopapp_2 @use_regression @domain_marketing @loyalty_enroll
  Scenario: Enroll in loyalty by creating a new profile
    Given I visit the web site as a guest user
    When I enroll into the Loyalty program by creating a new profile
    And I navigate to the loyalty account summary page
    Then I verify the account summary page for the loyallist

  @14K @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @use_regression @use_bat
  Scenario: Verify that loyallist with his loyallist account associated to his profile, is able to view updated right tile with saved offers on My Account Page
    Given I visit the web site as a registered user with manually 1 stored offers in wallet
    And I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "top_tier"
    When I navigate to my account page
    Then I should see title as my bWallet
    And I should see the number of offers in my bWallet
    And I should see link 'View my bWallet' that takes me to bWallet page

  @upi_631_bcom @upi_595_mcom @artifact_shopapp @priority_high @use_regression @domain_marketing @myaccount_3
  Scenario: Verifying recently purchased items with recommendations in My Account page
    Given I visit the web site as a registered user
    When I add 2 random products to my bag and checkout
    And I checkout until I reach the "responsive order confirmation" page as a "signed in" user
    And I select continue shopping button
    And I should be on home page
    When I navigate to my account page
    Then I should see recently purchased Items with recommendations under my order history section
    When I click on "right scroll" button display under recently purchased items section
    Then I should see next recently purchased Item with recommendations

  @domain_marketing @use_bat @journey @mustpass @sv_mcom_refactored @sv_bcom_refactored @s4a_stable @ifs @Marketing_CBT
  Scenario: A registered user can add a store credit card to their wallet.
    Given I visit the web site as a guest user
    When I create a new profile
    And I add a store credit card to my wallet
    Then my credit card information should be saved successfully

  @domain_marketing @use_bat @journey @mustpass @sv_mcom_refactored @sv_bcom_refactored
  @s4a_stable @ifs
  Scenario: A registered user can add a credit card to My Wallet in their wallet.
    Given I visit the web site as a guest user
    When I create a new profile
    And I add a credit card to my wallet
    Then my credit card information should be saved successfully

  @use_regression @priority_high @artifact_navapp @domain_marketing @use_bat @iq_bat @saucelab @Marketing_CBT
  Scenario:The home page should be displayed without errors and with all relevant information present.
    Given I visit the web site as a guest user
    Then I verify basic attributes of the home page