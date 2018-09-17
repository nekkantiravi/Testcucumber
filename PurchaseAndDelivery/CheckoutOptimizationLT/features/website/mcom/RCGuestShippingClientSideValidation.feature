Feature: Checkout Optimization LT Checkout Guest client validation scenarios.

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_guest @checkout
  Scenario: verify client side validation tags for shipping section  as a guest user.
    Given I visit the web site as a guest user
    When I add a "chkoutlt_validation_product" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I select continue button on guest shipping page
    Then validate the shipping section for required validation messages
    Then fill the shipping section for invalid value validation messages

  @project_responsive_checkout @domain_purchase_and_delivery @mcom_rc_guest @checkout
  Scenario: verify client side validation tags for payment section  as a guest user.
    Given I visit the web site as a guest user
    When I add a "chkoutlt_validation_product" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    And I enter shipping address on guest shipping page
    And I select continue button on guest shipping page
    And I checkout until I reach the payment page as a "guest" user
    Then deselect use my shipping on payment section
    And I select continue button on guest payment page
    Then validate client side validation for the payment information
    And I fill in payment information on guest payment page
    And I select continue button on guest payment page


