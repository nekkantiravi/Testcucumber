# Author: I Can Wait ABTesting Project QE Team
# Date Created: 21/04/2017
Feature: ICW ABTesting scenarios
# Pre-requisite: nhsABTestingEnabled kill-switch should be ON.

  @project_icw @domain_purchase_and_delivery @scenario1
  Scenario Outline: verify user gets 4$ shipping price for NHS
    Given I visit the web site as a guest user
    And I pass "<experimentCookie>" in segment cookie
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "guest" user
    When I enter shipping address on guest shipping page
    And I select nohurry in shipping methods
    Then I verify selected shipping method details
      | No Hurry                        |
      | Transit time: 6-9 business days |
    And I checkout until I reach the payment page as a "guest" user
    Then I verify shipping fee in order summary section is "<shippingFee>"
    Examples:
      |experimentCookie| shippingFee |
      |2339            | $4          |