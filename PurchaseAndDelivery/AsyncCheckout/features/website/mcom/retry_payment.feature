# Date Created: 01/19/2017
# Version One: B-66967, B-66968, B-66969 and B-66970
Feature: Retry payment

  @project_async_checkout @domain_purchase_and_delivery
  Scenario Outline: re-payment with different cards as a guest
    Given I visit the web site as a guest user
    And async payment experiment cookie is set
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user
    When I enter declined card info on checkout
    And I place an order
    And I save order details from confirmation page
    And I visit order history page as a guest user
    And I lookup the last placed order
    Then declined message should be present on order details page
    When I want to update payment
    Then declined message should be present on repayment page
    When I retry payment for the order by <card_type> credit card
    Then repayment successful

    Examples:
      | card_type               |
      | Visa             |
      | American Express |
      | Macy's        |
      | Macy's American Express |
      | Employee Card           |

  @project_async_checkout @domain_purchase_and_delivery
  Scenario Outline: re-payment with different cards as a registered user
    Given I visit the web site as a registered user with checkout eligible address
    And async payment experiment cookie is set
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the shipping & payment page as a "singed in" user
    When I enter declined card info on checkout
    And I enter contact info on checkout
    And I enter declined card security code on checkout
    And I place an order
    And I save order details from confirmation page
    And I visit order history page
    Then the last placed order must be present
    And declined message should be present on order history page
    When I want to update payment for the last declined order
    Then declined message should be present on repayment page
    When I retry payment for the order by <card_type> credit card
    Then repayment successful

    Examples:
      | card_type     |
      | Visa          |
      | American Express |
      | Macy's           |
      | Macy's American Express |
      | Employee Card |