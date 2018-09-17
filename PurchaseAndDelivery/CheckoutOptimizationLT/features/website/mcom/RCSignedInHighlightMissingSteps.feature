Feature: RC Checkout HIghlight Missed steps for Signed In user

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @coo-ll
  Scenario: Verify 'Shipping Address' missed message and 'Scroll To Section' link for Signed in user
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    And I click "change" in shipping summary section
    Then I see 'Please review the Shipping address section. Scroll To Section' message for 'Shipping Address' section
    And I see 'Scroll To Section' link
    When I click 'Scroll To Section' link
    Then Checkout page is scrolled to 'Shipping Address' section


  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @coo-ll
  Scenario: Verify 'Pick Up In Store' missed message and 'Scroll to section' link for Signed in user
    Given I visit the web site as a registered user
    When I add an "available_bops and orderable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    And I checkout until I reach the payment page as a "bops" user
    Then I see 'Please review the Pick up in store section. Scroll To Section' message for 'Pick Up In Store' section
    And I see 'Scroll To Section' link
    When I click 'Scroll To Section' link
    Then Checkout page is scrolled to 'Pick Up In Store' section

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @coo-ll
  Scenario Outline: Verify Gift Options|Apply Gift Cards|Plenti missed message and 'Scroll To Section' link for Signed in user
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    When I click <expand_button> button
    Then I see missed field message for the <section>
    And I see 'Scroll To Section' link
    When I click 'Scroll To Section' link
    Then Checkout page is scrolled to the <section>

    Examples:
      | expand_button           | section          |
      | Expand_Gift_Options     | Gift_Options     |
      | Expand_Apply_Gift_Cards | Apply_Gift_Cards |
      | Expand_Plenti           | Plenti           |

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @coo-ll
  Scenario: Verify 'Payment Method' missed message and 'Scroll To Section' link for Signed in user
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    Then I see 'Please review the Payment section. Scroll To Section' message for 'Payment Method' section
    And I see 'Scroll To Section' link
    When I click 'Scroll To Section' link
    Then Checkout page is scrolled to 'Payment Method' section

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @coo-ll
  Scenario: Verify 'Security Code' missed message and 'Scroll To Section' link for Signed in user
    Given I visit the website as a registered user using rest services
    When I add an "available and orderable" product to my bag using rest service that is not "registrable and available_bops and BT_furniture"
    And I checkout until I reach the shipping & payment page as a "registered" user
    Then I see 'Please enter your security code in the Payment section. Scroll To Section' message for 'Security Code' section
    And I see 'Scroll To Section' link
    When I click 'Scroll To Section' link
    Then Checkout page is scrolled to 'Security Code' field

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_signedIn @coo-ll
  Scenario: Verify 'Contact Information' missed message and 'Scroll To Section' link for Signed in user
    Given I visit the web site as a registered user
    When I add a "available" product to my bag
    And I checkout until I reach the order review page as a "signed in" user
    And I click 'Edit' button in 'Contact Information' section
    Then I see 'Please review the Contact info section. Scroll To Section' message for 'Contact Information' section
    And I see 'Scroll To Section' link
    When I click 'Scroll To Section' link
    Then Checkout page is scrolled to 'Contact Information' section