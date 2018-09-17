# Author: Marketing Domain
# Date Created: 06/14/2017
# Date Signed Off:

Feature: My Offers - Sales and Promotions Page

  #BLCOM-80215 BLCOM-80247
  @use_regression @use_dsv @use_production @mustpass-fix @use_launch_call @use_s4a_stable @shop_browse_2 @priority_high @dsv_desktop_sev2
  Scenario:Verify Sale & Offers pages are served from navapp
    Given I visit the web site as a guest user
    When I navigate to sales and promotions page
    And I verify the sales and promotions page
    And I verify promotions for current date
    When I hover over any of the random category
    Then I should be able to see the flyout menu
    #  Notes:
    #  Description: Verify if they are fetched from navapp
    #  Steps
    #  1. Navigate to bloomingdales.com.
    #  2. hover on sale category.
    #  3. click on See All under sales and offers.
    #  Expected Results
    #  1. Bloomingdales.com homepage should display
    #  2. flyouts should come
    #  3. Sale and offers page should open

  @defect_SSTREG-50 @zat_backlog @project_oc_wallet @mode_domestic @domain_marketing @use_e2e
  Scenario: Promotions Page - Verify the print option is displayed on coupon window
    Given I visit the web site as a guest user
    When I navigate to sales and promotions page
    And I open the coupon window
    Then I should see print option in coupon window

  @use_regression @sstbacklog @shop_browse_1 @priority_high @coremetrics @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Promotions Page - Verify Shop Now link under info/exclusions level
    Given I visit the web site as a guest user
    When I navigate to sales and promotions page
    When I click on 'info/exclusions' link on offer/coupon
    Then I should be able to see shop now link

  @use_regression @use_bat_next @sstbacklog @use_launch_call @shop_browse_2 @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Promotions Page - Verify online Free Shipping link
    Given I visit the web site as a guest user
    When I navigate to sales and promotions page
    Then I verify promotions for current date
#    And I verify "Free Returns" email link
    And I verify "Free Shipping" link

  @use_regression @sstbacklog @shop_browse_1 @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Promotions Page - Verify Sign up for Email link
    Given I visit the web site as a guest user
    When I navigate to sales and promotions page
    Then I verify "Sign up for email" link

  @use_regression @sstbacklog @shop_browse_2 @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Promotions Page - Verify Get on the list link
    Given I visit the web site as a guest user
    When I navigate to sales and promotions page
    And I verify "Get on the list" link

  @use_regression @sstbacklog @shop_browse_1 @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Promotions Page - Verify Shop on the go link
    Given I visit the web site as a guest user
    When I navigate to sales and promotions page
    And I verify "Shop on the go" link
