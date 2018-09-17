Feature: Verify order history and order return details

  @scenario1 @xbrowser_tablet @domain_customer_management @xbrowser_mew
  Scenario: Order Details - cancelled as a Signed in user
    Given I visit the mobile web site as a registered user
    When I add a "available and orderable" product to my bag using mobile website
    And I checkout until I reach the order review page using mobile website as a "signed in" user
    Then I place an order
    When I navigate back to "my account" page using mobile website
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page using mobile website
    Then I verify the ability to cancel the order in order details page using mobile website

  @scenario2 @xbrowser_tablet @domain_customer_management @product_data_dependency @xbrowser_mew
  Scenario: To verify Processing items display in OD Page
    Given I visit the mobile web site as a guest user
    When I associate "processing" order in db
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page for "processing" order using mobile website
    Then I verify order details in OD page for "PROCESSING" using mobile website

  @scenario3 @xbrowser_tablet @domain_customer_management @product_data_dependency @xbrowser_mew
  Scenario: To verify Returned items display in OD page
    Given I visit the mobile web site as a guest user
    When I associate "return" order in db
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page for "return" order using mobile website
    Then I verify order details in OD page for "returned" using mobile website

  @scenario4 @xbrowser_tablet @domain_customer_management @product_data_dependency @xbrowser_mew
  Scenario: To verify BOPS Picked up items display in OD page
    Given I visit the web site as a guest user
    When I associate "BOPS_PU" order in db
    And I navigate to "order status" page from my account page using mobile website
    And I navigate to order details page for "BOPS_PU" order using mobile website
    Then I verify order details in OD page for "IN-STORE PICK UP" using mobile website
