Feature: RC Checkout Messaging Missed Fields for Signed In user

  @b-88798 @optimization_lab @domain_purchase_and_delivery
  Scenario: Verify 'Shipping Address' missed message and 'Review It Now' link for Signed in user
    Given I create a new profile
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    Then I see 'Please review the Shipping Address section. Review It Now' message for 'Shipping Address' section
    And I see 'Review It Now' link
    When I click 'Review it Now' link
    Then Checkout page is scrolled to 'Shipping Address' section

  @b-88798 @optimization_lab @domain_purchase_and_delivery
  Scenario: Verify 'Pick Up In Store' missed message and 'Review It Now' link for Signed in user
    Given I visit the web site as a registered user
    When I add an "available_bops and orderable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    And I checkout until I reach the payment page as a "bops" user
    Then I see 'Please review the Pick Up In Store section. Review It Now' message for 'Pick Up In Store' section
    And I see 'Review It Now' link
    When I click 'Review it Now' link
    Then Checkout page is scrolled to 'Pick Up In Store' section

  @b-88798 @optimization_lab @domain_purchase_and_delivery
  Scenario Outline: Verify Gift Options|Loyallist|Apply Gift Cards missed message and 'Review It Now' link for Signed in user
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    When I click <expand_button> button
    Then I see missed field message for the <section>
    And I see 'Review It Now' link
    When I click 'Review it Now' link
    Then Checkout page is scrolled to the <section>

    Examples:
      | expand_button           | section          |
      | Expand_Gift_Options     | Gift_Options     |
      | Expand_Loyallist        | Loyallist        |
      | Expand_Apply_Gift_Cards | Apply_Gift_Cards |

  @b-88798 @optimization_lab @domain_purchase_and_delivery
  Scenario: Verify 'Payment Method' missed message and 'Review It Now' link for Signed in user
    Given I create a new profile
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    And I add a valid shipping address on shipping page for signed in user
    Then I see 'Please review the Payment section. Review It Now' message for 'Payment Method' section
    And I see 'Review It Now' link
    When I click 'Review it Now' link
    Then Checkout page is scrolled to 'Payment Method' section

  @b-88798 @optimization_lab @domain_purchase_and_delivery
  Scenario: Verify 'Security Code' missed message and 'Add It Now' link for Signed in user
    Given I create a new profile
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    And I add a valid shipping address on shipping page for signed in user
    And I add a credit card during checkout
    Then I see 'Please enter your security code in the Payment section. Add It Now' message for 'Security Code' section
    And I see 'Add It Now' link
    When I click 'Add it Now' link
    Then Checkout page is scrolled to 'Security Code' field

  @b-88798 @optimization_lab @domain_purchase_and_delivery
  Scenario: Verify 'Contact Information' missed message and 'Review It Now' link for Signed in user
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    And I add a valid shipping address on shipping page for signed in user
    And I add a credit card during checkout
    And I add security code in Payment section on Checkout page
    And I click 'Edit' button in 'Contact Information' section
    Then I see 'Please review the Contact Info section. Review It Now' message for 'Contact Information' section
    And I see 'Review It Now' link
    When I click 'Review it Now' link
    Then Checkout page is scrolled to 'Contact Information' section