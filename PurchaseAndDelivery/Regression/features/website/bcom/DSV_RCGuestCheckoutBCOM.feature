Feature: Responsive Guest checkout

  @dsv_desktop_sev2 @use_regression @domain_purchase_and_delivery
  Scenario: Verify the display of shipping sections when user have only VGC item for DSV
    Given I visit the web site as a guest user
    And I change the cookie to "responsive"
    When I add a "virtual_gift_card and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should not see shipping address, shipping method and gift options sections on shipping page

  @dsv_desktop_sev2 @use_regression @domain_purchase_and_delivery
  Scenario: Verify the display of shipping sections when user have both VGC and normal item for DSV
    Given I visit the web site as a guest user
    When I add a "available and master_product" product to my bag
    And I add a "virtual_gift_card and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see shipping address, shipping method and gift options sections on shipping page

  @dsv_desktop_sev2 @use_regression @domain_purchase_and_delivery
  Scenario: Verify the VGC shipping information text when bag has only VGC item
    Given I visit the web site as a guest user
    And I add a "virtual_gift_card and orderable" product to my bag and checkout
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see VGC shipping information text in shipping page as:
      |Your E-Gift Card(s) will be emailed to the address provided.|

  @dsv_desktop_sev2 @akamai_waf
  Scenario: Verify guest checkout
    Given I visit the web site as a guest user
    When I add a "orderable and prod_available" product to my bag
    And I continue checkout until I reach the order review page as an "guest" user
    And I should see place order button in enabled state