Feature: Verify order history and order return details

  @scenario1 @domain_customer_management @xbrowser @xbrowser_one @product_data_dependency
  Scenario: Order Details - cancelled as a Signed in user
    Given I visit the web site as a registered user with checkout eligible address
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "signed in" user
    And I capture order number from order_confirmation page
    And I navigate to order details page from footer
    Then I verify the ability to cancel the order in order details page

  @scenario2 @domain_customer_management @xbrowser @xbrowser_one @data_dependency @high
  Scenario: Verify the CRL number and barcode appear on return Confirmation page as guest user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "submitted" order as a "guest" user
    Then I should see barcode and crl number

  @scenario3 @domain_customer_management @xbrowser @xbrowser_one @data_dependency
  Scenario: To verify Processing items display in OH and OD Pages for the first order and first item.
    Given I visit the web site as a guest user
    When I associate "processing" order in db
    And I navigate to the "order status" page from footer
    Then I verify order details for "processing|PROCESSING" items in OH page
    And I verify order details in OD page

  @scenario4 @domain_customer_management @xbrowser @xbrowser_one @data_dependency
  Scenario: To verify returned items display in OH and OD pages
    Given I visit the web site as a guest user
    When I associate "return" order in db
    And I navigate to the "order status" page from footer
    Then I verify order details for "returned|RETURNED" items in OH page
    And I verify order details in OD page

  @scenario5 @domain_customer_management @xbrowser @xbrowser_one @data_dependency
  Scenario: To verify BOPS Picked up items display in OH and OD page
    Given I visit the web site as a guest user
    When I associate "BOPS_PU" order in db
    And I navigate to the "order status" page from footer
    Then I verify order details for "picked up|IN-STORE PICK UP Items Picked Up" items in OH page
    And I verify order details in OD page

  @scenario6 @domain_customer_management @xbrowser @xbrowser_one @data_dependency  @run_this
  Scenario: Verify Return status is "Submitted" for signed user
    Given I visit the web site as a registered user
    When I navigate to confirmation page using "submitted" order as a "signed in" user
    And I navigate to order details page as a "signed" user
    Then I should see return status as "submitted" with updated date

  @scenario7 @domain_customer_management @xbrowser @xbrowser_one @data_dependency
  Scenario: To verify the return quantity displayed in line item for return received status in order details page as guest user
    Given I visit the web site as a guest user
    When I navigate to confirmation page using "transit" order as a "guest" user
    And I navigate to the "order status" page from footer
    And I set the return "received" status for "transit" order in db
    And I lookup with order number and emailaddress in OH page
    Then I should see line item qty as "return_qty" for "received|RETURN STATUS Received" shipment

  @scenario8 @domain_customer_management @xbrowser @xbrowser_one @data_dependency
  Scenario: Verify whether guest user is navigating to order status page from return selection page through back button
    Given I visit order history page as a guest user
    When I select "intransit" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button in "OD" page
    And I select items and continue to submit page
    And I navigate back to "OH" page
    Then I should be navigated to OH page