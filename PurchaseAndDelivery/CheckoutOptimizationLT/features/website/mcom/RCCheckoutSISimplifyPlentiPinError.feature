Feature: Checkout Optimization Simplify Plenti PIN Error scenarios

  @project_responsive_checkout @domain_purchase_and_delivery @coo-ll
    Scenario: Verify User Plenti Pin tooltip and Pin info popup is present
    Given I visit the website as a guest user using rest services
    When I add a "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order review page as a "signed in" user
    And I lookup plenti id using valid usl phone number on payment page
    And I add usl as partial payment on payment page with redeem amount "2.04"
    And I should see redeem pin tooltip icon and popup on clicking tool tip icon

  @project_responsive_checkout @domain_purchase_and_delivery @coo-ll
    Scenario: Verify User Plenti Pin Error Scenario on Place Order
    Given I visit the website as a guest user using rest services
    When I add a "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order review page as a "signed in" user
    And I lookup plenti id using valid usl phone number on payment page
    And I add usl as partial payment on payment page with redeem amount "2.04"
    And I should see redeem pin tooltip icon and popup on clicking tool tip icon
    And I add plenti pin "3456"
    Then I should see order confirmation section displayed with order details
    And I click on place order button for plenti error
    Then I should see the plenti redeem section open with wrong pin message

  @project_responsive_checkout @domain_purchase_and_delivery @coo-ll
    Scenario: Verify User Plenti Pin Success Scenario on Place Order
    Given I visit the website as a guest user using rest services
    When I add a "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the order review page as a "signed in" user
    And I lookup plenti id using valid usl phone number on payment page
    And I add usl as partial payment on payment page with redeem amount "2.04"
    And I add plenti pin "1234"
    And I place an order
    Then I should see order confirmation section displayed with order details
