Feature: BATs for Customer management

  @use_bat @bcom @mcom @upi_631_bcom @upi_595_mcom  @artifact_shopapp @priority_high @use_regression @myaccount_4 @use_launch_call  @s4a_stable @domain_marketing @domain_customer @migrated_to_sdt
  Scenario: Verify My Account Page section
    Given I visit the web site as a guest user
    When I create a new profile
    Then I should be able to visit all of the sections under My Account

  @use_bat @bcom @mcom @prod_pom @journey @mustpass @dsv @regression @s4a_stable @domain_customer @use_ci @ifs @migrated_to_sdt
  Scenario:A new user can create a profile that persists across visits.
    Given I visit the web site as a guest user
    When I create a new profile
    And I leave the site and return later
    Then I should be able to sign in and view my profile

  @use_bat @bcom @mcom @priority_high @artifact_shopapp @shopapp_4 @domain_customer @mode_domestic @feature_orderdetails_return @migrated_to_sdt
  Scenario: To verify that Print Shipping label link should only be available if the order status is equal to submitted as guest user
    Given I visit order history page as a guest user
    When I select "intransit" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I initiate return and navigate to order details page for guest user
    Then I see return order status as "submitted" on page
    And I should see Print Shipping label on page for submitted status

  @use_bat @bcom @mcom @use_regression @mode_domestic  @priority_low  @artifact_shopapp @shopapp_4 @domain_customer @migrated_to_sdt
  Scenario: Verify the breadcrumb on return confirmation page as signed user
    Given I visit the web site as a guest user
    When I select "intransit" order as a "signed" user
    And I navigate to order history page
    And I select return items button in "OH" page
    Then I select items and continue to submit page
    When I should navigate to return submit page
    And I select return submit from submit page
    Then I see "Return Items" is not a link in breadcrumb in "CONFIRMATION" page
    And I should see my account page using "My Account" link in breadcrumb in CONFIRMATION page for signed user

  @use_bat @mcom @use_regression @domain_customer @migrated_to_sdt
  Scenario Outline: Verify add card leads to Citi page and return
    Given I visit the web site as a registered user
    When I navigate to my account page
    And I select add card to account option
    Then I should reach on "add card fusion" page
    When I "<action>" from the add card fusion page
    Then I should go to "<landing>" page
    Examples:
      |action   | landing         |
      |Cancel   | credit gateway  |
      |Sign Off | home page       |
