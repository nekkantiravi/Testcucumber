# Author: I Can Wait Project QE Team
# Date Created: 10/04/2016
Feature: ICW core functionality scenarios

  @project_icw @domain_purchase_and_delivery @scenario1
  Scenario: As a guest user, verify user is able to place an order with noHurry shipping method
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "guest" user
    Then I should see order confirmation section displayed with order details
    And I verify No Hurry shipping method details on confirmation page

  @project_icw @domain_purchase_and_delivery @scenario2
  Scenario: As a signedIn user, verify user is able to place an order with noHurry shipping method
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "signedIn" user
    Then I should see order confirmation section displayed with order details
    And I verify No Hurry shipping method details on confirmation page

  @project_icw @domain_purchase_and_delivery @scenario3
  Scenario: As a signedIn user, verify noHurry suggestion message displayed on shipping method section
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    Then I should see nohurry suggestion message displayed in the shipping method section:
      | You qualify for FREE No Hurry shipping. Click "change" to select offer or learn more. |

  @project_icw @domain_purchase_and_delivery @scenario4
  Scenario: As a signedIn, verify noHurry shipping option is not displayed for backorderable products
    Given I visit the web site as a registered user
    When I add an "backorderable and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signedIn" user
    Then I should not see nohurry suggestion message displayed in the shipping method section:
      | You qualify for FREE No Hurry shipping. Click "change" to select offer or learn more. |
    And I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | true  |
      | express_shipping  | true  |
      | nohurry_shipping  | false |

  @project_icw @domain_purchase_and_delivery @scenario5
  Scenario: As a guest user, verify noHurry shipping option should not be displayed for backorderable products
    Given I visit the web site as a guest user
    When I add an "backorderable and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | true  |
      | express_shipping  | true  |
      | nohurry_shipping  | false |

  @project_icw @domain_purchase_and_delivery @scenario6
  Scenario Outline: Verify noHurry shipping option should be display when bag has sdd item
    Given I visit the web site as a <user_type>
    When I add a "sdd_eligible and orderable" product to my bag
    And I checkout until I reach the <page> page as a "<user>" user
    And I enter sdd_eligible address on shipping page for <user> user
    Then I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | false |
      | express_shipping  | false |
      | sdd_shipping      | true  |
      | nohurry_shipping  | true  |

    Examples:
      | user_type                                      | page               | user      |
      | guest user                                     | shipping           | guest     |
      | registered user with checkout eligible address | shipping & payment | signed in |

  @project_icw @domain_purchase_and_delivery @scenario7
  Scenario Outline: Verify noHurry shipping method details on order details page when user place an order with nohurry shipping method
    Given I visit the web site as a <user_type>
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the <page> page as an "<user>" user
    And I select nohurry in shipping methods
    And I checkout until I reach the order confirmation page as a "<user>" user
    Then I capture order number and email address from order_confirmation page
    When I navigate to order details page from footer
    Then I verify order details and shipping details in OD page for "NO HURRY" shipping method

    Examples:
      | user_type       | page               | user      |
      | guest user      | shipping           | guest     |
      | registered user | shipping & payment | signed in |

  @project_icw @domain_purchase_and_delivery @scenario8
  Scenario: As a guest user, verify learn more information in noHurry shipping method
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I should see below shipping options:
      | standard_shipping | true |
      | premium_shipping  | true |
      | express_shipping  | true |
      | nohurry_shipping  | true |
    And I select nohurry in shipping methods
    And I select learn more link in nohurry shipping method
    Then I should see following delivery ship note for nohurry shipping method on responsive checkout page:
      | Introducing No Hurry ShippingAvailable only for a limited time. It's the smart way to ship when you don't need your order right away. Ship for FREE! Transit time 6-9 business days. |

  @project_icw @domain_purchase_and_delivery @scenario9
  Scenario: As a signedIn user, verify learn more information in noHurry shipping method
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    And I select learn more link in nohurry shipping method
    And I should see following delivery ship note for nohurry shipping method on responsive checkout page:
      | Introducing No Hurry ShippingAvailable only for a limited time. It's the smart way to ship when you don't need your order right away. Ship for FREE! Transit time 6-9 business days. |

  @project_icw @domain_purchase_and_delivery @scenario10
  Scenario: As a guest user, verify noHurry shipping fee in shipping method section and order summary section
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select nohurry in shipping methods
    Then I verify selected shipping method details
      | No Hurry                        |
      | Transit time: 6-9 business days |
      | FREE                            |
    When I select continue button on guest shipping page
    Then I verify shipping fee in order summary section
    And I verify the selected shipping method details in shipping summary section

  @project_icw @domain_purchase_and_delivery @scenario11
  Scenario: As a signedIn user, verify noHurry shipping fee in shipping method section and order summary section
    Given I visit the web site as a registered user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as an "responsive signed in" user
    And I select nohurry in shipping methods
    Then I verify selected shipping method details
      | No Hurry                        |
      | Transit time: 6-9 business days |
      | FREE                            |
    And I verify shipping fee in order summary section
    And I verify the selected shipping method details in shipping summary section

  @project_icw @domain_purchase_and_delivery @scenario12
  Scenario Outline: Verify noHurry shipping method should not be displayed on shipping page when bag has EGC item
    Given I visit the web site as a <user_type>
    When I add an "<EGC_item>" item to bag with "<amount>" using add to bag service
    And I checkout until I reach the <page> page as a "<user>" user
    And I select nohurry in shipping methods
    Then I should see below shipping options:
      | standard_shipping | true |
      | premium_shipping  | true |
      | express_shipping  | true |
      | nohurry_shipping  | true |
    Examples:
      | user_type       | page               | user      | EGC_item | amount |
      | guest user      | shipping           | guest     | 35352360 | 50     |
      | registered user | shipping & payment | signed in | 35352360 | 50     |

  @project_icw @domain_purchase_and_delivery @scenario13
  Scenario Outline: Verify noHurry shipping method should not be displayed on shipping page when bag has BOPS item
    Given I visit the web site as a <user_type>
    When I add an "available_bops" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    And I checkout until I reach the <page> page as a "<user>" user
    Then I should not see shipping methods section

    Examples:
      | user_type       | page               | user               |
      | guest user      | shipping           | bops               |
      | registered user | shipping & payment | bops and signed in |

  @project_icw @domain_purchase_and_delivery @scenario14
  Scenario: As a signedIn user, verify noHurry shipping method should be display when user moves back and forth with eligible and ineligible items
    Given I visit the web site as a registered user
    When I add an "backorderable and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | true  |
      | express_shipping  | true  |
      | nohurry_shipping  | false |
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "signed in" user
    Then I should see below shipping options:
      | standard_shipping | true |
      | premium_shipping  | true |
      | express_shipping  | true |
      | nohurry_shipping  | true |

  @project_icw @domain_purchase_and_delivery @scenario15
  Scenario: As a guest user, verify noHurry shipping method should be display when user moves back and forth with eligible and inelgible items
    Given I visit the web site as a guest user
    When I add an "backorderable and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see below shipping options:
      | standard_shipping | true  |
      | premium_shipping  | true  |
      | express_shipping  | true  |
      | nohurry_shipping  | false |
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    Then I should see below shipping options:
      | standard_shipping | true |
      | premium_shipping  | true |
      | express_shipping  | true |
      | nohurry_shipping  | true |