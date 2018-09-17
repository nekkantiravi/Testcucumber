# Author: I Can Wait Project QE Team
# Date Created: 10/10/2016
Feature: ICW Place Order Scenarios

  @project_icw @domain_purchase_and_delivery @scenario1
  Scenario Outline: Verify user is able to place an order with noHurry shipping method with normal item
    Given I visit the web site as a <user_type>
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the <page> page as a "<user>" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "<user>" user
    Then I should see order confirmation section displayed with order details
    And I verify the order details in DB for nohurry
    And I should see "1" shipments in DB
    Examples:
      | user_type       | page               | user      |
      | guest user      | shipping           | guest     |
      | registered user | shipping & payment | signed in |

  @project_icw @domain_purchase_and_delivery @scenario2
  Scenario Outline: Verify user is able to place an order with noHurry shipping method with EGC item
    Given I visit the web site as a <user_type>
    When I add an "<EGC_item>" item to bag with "<amount>" using add to bag service
    And I checkout until I reach the <page> page as a "<user>" user
    And I should see below shipping options:
      | standard_shipping | true |
      | premium_shipping  | true |
      | nohurry_shipping  | true |
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "<user>" user
    Then I verify No Hurry shipping method details on confirmation page
    And I verify the order details in DB for nohurry
    And I should see "1" shipments in DB
    Examples:
      | user_type       | page               | user      | EGC_item | amount |
      | guest user      | shipping           | guest     | 35352360 | 50     |
      | registered user | shipping & payment | signed in | 35352360 | 50     |

  @project_icw @domain_purchase_and_delivery @scenario3
  Scenario: Verify NoHurry shipping method in shipping page when bag has BVR registry item
    Given I visit the web site as a registry user
    When I add "registrable" product to my bag from BVR page
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "responsive signed in" user
    Then I verify No Hurry shipping method details on confirmation page
    And I verify the order details in DB for nohurry
    And I should see "1" shipments in DB

  @project_icw @domain_purchase_and_delivery @scenario4
  Scenario: As a guest user, verify user is able to place an order with GVR item and shipping method as nohurry
    Given I visit the web site as a registry user
    When I add "registrable" product to my bag from BVR page
    And I sign out from my current profile
    And I navigate to shopping bag page
    And I checkout until I reach the shipping page as a "guest" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "guest" user
    Then I verify No Hurry shipping method details on confirmation page
    And I verify the order details in DB for nohurry
    And I should see "1" shipments in DB

  @project_icw @domain_purchase_and_delivery @scenario5
  Scenario: As a signedIn user, verify user is able to place an order with GVR item and shipping method as nohurry
    Given I visit the web site as a registry user
    When I add "registrable" product to my bag from BVR page
    And I sign out from my current profile
    And I create a new profile
    And I navigate to shopping bag page
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "responsive signed in" user
    Then I verify No Hurry shipping method details on confirmation page
    And I verify the order details in DB for nohurry
    And I should see "1" shipments in DB

  @project_icw @domain_purchase_and_delivery @scenario6
  Scenario Outline: As a guest user, verify user is able to place an order with normal item + VGC item with noHurry shipping method
    Given I visit the web site as a <user_type>
    When I add an "available and orderable" product to my bag
    And I add "VGC_item" item with "<amount>" and "<email_ID>" to my bag
    And I checkout until I reach the <page> page as a "<user>" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "<user>" user
    Then I verify the order details in DB for nohurry
    And I should see "2" shipments in DB
    Examples:
      | user_type       | page               | user      | amount | email_ID        |
      | guest user      | shipping           | guest     | 50     | icw@testing.com |
      | registered user | shipping & payment | signed in | 50     | icw@testing.com |

  @project_icw @domain_purchase_and_delivery @scenario7
  Scenario Outline: As a guest user, verify user is able to place an order with normal item + EGC item with noHurry shipping method
    Given I visit the web site as a <user_type>
    When I add an "available and orderable" product to my bag
    When I add an "<EGC_item>" item to bag with "<amount>" using add to bag service
    And I checkout until I reach the <page> page as a "<user>" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "<user>" user
    Then I verify the order details in DB for nohurry
    And I should see "2" shipments in DB
    Examples:
      | user_type       | page               | user      | EGC_item | amount  |
      | guest user      | shipping           | guest     | 35352360 | 100     |
      | registered user | shipping & payment | signed in | 35352360 | 100     |

  @project_icw @domain_purchase_and_delivery @scenario8
  Scenario Outline: As a guest user, verify user is able to place an order with normal item + Bops item with noHurry shipping method
    Given I visit the web site as a <user_type>
    When I add an "available and orderable" product to my bag
    And I add an "available_bops" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    And I checkout until I reach the <page> page as a "<user>" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "<user>" user
    Then I verify the order details in DB for nohurry
    And I should see "2" shipments in DB
    Examples:
      | user_type       | page               | user      |
      | guest user      | shipping           | guest     |
      | registered user | shipping & payment | signed in |

  @project_icw @domain_purchase_and_delivery @scenario9
  Scenario Outline: As a guest user, verify user is able to place an order with normal + Backorderable items with noHurry shipping method
    Given I visit the web site as a <user_type>
    When I add an "available and orderable" product to my bag
    And I add an "backorderable and orderable" product to my bag
    And I checkout until I reach the <page> page as a "<user>" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "<user>" user
    Then I capture order number from order_confirmation page
    And I verify the order details in DB for nohurry
    And I should see "2" shipments in DB
    Examples:
      | user_type       | page               | user      |
      | guest user      | shipping           | guest     |
      | registered user | shipping & payment | signed in |

  @project_icw @domain_purchase_and_delivery @scenario10
  Scenario Outline: As a guest user, verify user is able to place an order with normal + EGC + VGC items with noHurry shipping method
    Given I visit the web site as a <user_type>
    When I add an "available and orderable" product to my bag
    And I add an "backorderable and orderable" product to my bag
    And I add "VGC_item" item with "<amount>" and "<email_ID>" to my bag
    And I add an "<EGC_item>" item to bag with "<amount>" using add to bag service
    And I checkout until I reach the <page> page as a "<user>" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "<user>" user
    Then I verify the order details in DB for nohurry
    And I should see "4" shipments in DB
    Examples:
      | user_type       | page               | user      | EGC_item | amount  | email_ID        |
      | guest user      | shipping           | guest     | 35352360 | 100     | icw@testing.com |
      | registered user | shipping & payment | signed in | 35352360 | 100     | icw@testing.com |

##################################### Paypal place order scenarios ######################################3

  @project_icw @domain_purchase_and_delivery @scenario11
  Scenario: Verify user is able to place an order as guest user with noHurry shipping method using Paypal
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I select nohurry in shipping methods
    And I checkout until I reach the payment page as an "guest" user
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    And I place an order
    Then I should see order confirmation section displayed with order details
    And I should see paypal icon and email on order confirmation page
    And I verify No Hurry shipping method details on confirmation page
    And I verify the order details in DB for nohurry

  @project_icw @domain_purchase_and_delivery @scenario12
  Scenario: Verify user is able to place an order as signedIn user with noHurry shipping method using Paypal
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    And I select nohurry in shipping methods
    And I select checkout with paypal on payment page
    And I login into paypal account
    And I checkout from paypal review page
    And I place an order
    Then I should see order confirmation section displayed with order details
    And I should see paypal icon and email on order confirmation page
    And I verify No Hurry shipping method details on confirmation page
    And I verify the order details in DB for nohurry

  @project_icw @domain_purchase_and_delivery @scenario13
  Scenario: Verify user is able to place an order as guest user with noHurry shipping method using Express Paypal
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    And I edit the Shipping details
    And I select nohurry in shipping methods
    And I select continue button on guest shipping page
    And I place an order
    Then I should see order confirmation section displayed with order details
    And I should see paypal icon and email on order confirmation page
    And I verify No Hurry shipping method details on confirmation page
    And I verify the order details in DB for nohurry

  @project_icw @domain_purchase_and_delivery @scenario14
  Scenario: Verify user is able to place an order as signedIn user with noHurry shipping method using Express Paypal
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I select checkout with paypal
    And I login into paypal account
    And I checkout from paypal review page
    And I select nohurry in shipping methods
    And I place an order
    Then I should see order confirmation section displayed with order details
    And I should see paypal icon and email on order confirmation page
    And I verify No Hurry shipping method details on confirmation page
    And I verify the order details in DB for nohurry