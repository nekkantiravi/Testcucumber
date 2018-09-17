Feature: Verify order history and order return details


  @scenario1 @domain_customer_management @xbrowser @xbrowser_one @data_dependency @run_this
  Scenario: Verify whether guest user is navigating to order status page from return selection page through back button
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "prod_order" order as a "guest" user
    And I navigate to the "order status" page from footer
    And I lookup with order number and emailaddress in OH page
    Then I should see return status as "submitted" with updated date