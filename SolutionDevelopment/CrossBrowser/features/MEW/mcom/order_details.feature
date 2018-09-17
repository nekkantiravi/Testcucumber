Feature: Verify order history and order return details

  @scenario1 @xbrowser_tablet @domain_customer_management @product_data_dependency @xbrowser_mew
  Scenario: Order Details - cancelled as a Signed in user
    Given I visit the mobile web site as a registered user
    When I add a "available and orderable" product to my bag using mobile website that is not an "BT_furniture"
    And I checkout until I reach the order confirmation page using mobile website as a "signed in" user
    Then I place an order
    And I navigate back to "my account" page using mobile website
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page using mobile website
    Then I verify the ability to cancel the order in order history page

  @scenario2 @xbrowser_tablet @domain_customer_management @data_dependency @xbrowser_mew
  Scenario: To verify Processing items display in OH and OD Pages for the first order and first item.
    Given I visit the mobile web site as a guest user
    When I associate "processing" order in db
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page for "processing" order using mobile website
    Then I verify order details in OD page for "processing" order using mobile website

  @scenario3 @xbrowser_tablet @domain_customer_management @data_dependency @wip
  Scenario: To verify returned items display in OH and OD pages
    Given I visit the mobile web site as a guest user
    When I associate "return" order in db
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page for "return" order using mobile website
    Then I verify order details in OD page for "returned|RETURNED" order using mobile website

  @scenario4 @xbrowser_tablet @domain_customer_management @data_dependency @xbrowser_mew
  Scenario: To verify BOPS Picked up items display in OH and OD page
    Given I visit the mobile web site as a guest user
    When I associate "BOPS_PU" order in db
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page for "BOPS_PU" order using mobile website
    Then I verify order details in OD page for "Picked up" order using mobile website
