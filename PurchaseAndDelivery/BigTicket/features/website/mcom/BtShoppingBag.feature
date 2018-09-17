# Author: Big Ticket Project QE Team
# Date Created: 02/21/2017
# Version One: B-69014 & B-67106

Feature: As a customer viewing my bag with big ticket items in it I don't want to see Paypal/Pick up store information since I can not tender using Paypal at checkout.

  @project_BT @domain_purchase_and_delivery @scenario1
  Scenario Outline: Verify "PayPal Checkout" button and pick-up in store section in shopping bag is suppressed, when bag has only Big ticket items in bag
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    Then I should see PayPal Checkout button "suppressed" in shopping bag
    And I should "not_see" pick up in store section in bag
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario2
  Scenario Outline: Verify "PayPal Checkout" button in shopping bag is suppressed and display the exclusion message, when bag has both Big ticket and Small ticket items in bag
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add a "available and orderable"" product to my bag
    And I navigate to shopping bag page from add to bag page
    Then I should see PayPal Checkout button "suppressed" in shopping bag
    And I should see the "Excludes furniture and mattresses" text in pick up in store section
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario3
  Scenario Outline: Verify "PayPal Checkout" button and pickup in store section is displayed in shopping bag, when bag has only Small ticket items in bag
    Given I visit the web site as a <user_type> user
    When I add a "available and orderable" product to my bag
    And I navigate to shopping bag page from add to bag page
    Then I should see PayPal Checkout button "displayed" in shopping bag
    And I should "see" pick up in store section in bag
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario4
  Scenario Outline: Verify "PayPal Checkout" button in shopping bag is displayed, when all the Big ticket items are removed from bag and left with only Small ticket items
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add a "available and orderable" product to my bag
    And I navigate to shopping bag page from add to bag page
    Then I should see PayPal Checkout button "suppressed" in shopping bag
    And I should see the "Excludes furniture and mattresses" text in pick up in store section
    When I click on "remove" link for "bigTicket" item
    Then I should see PayPal Checkout button "displayed" in shopping bag
    And I should not see the "Excludes furniture and mattresses" text in pick up in store section
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario5
  Scenario Outline: Verify “Paypal” button is suppressed, when SmallTicket and BigTicket item are added to bag and exclusion message is displayed in pick-up store section on shopping bag page
    Given I visit the web site as a <user_type> user
    When I add a "available and orderable" product to my bag
    And I navigate to shopping bag page from add to bag page
    Then I should see PayPal Checkout button "displayed" in shopping bag
    And I should not see the "Excludes furniture and mattresses" text in pick up in store section
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I navigate to shopping bag page
    Then I should see PayPal Checkout button "suppressed" in shopping bag
    And I should see the "Excludes furniture and mattresses" text in pick up in store section
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario6
  Scenario Outline: Verify “Paypal Checkout” button in shopping bag is displayed, when I have a mixed bag and Move all Big Ticket items to List and only have Small Ticket items in the bag.
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add a "available and orderable" product to my bag
    And I navigate to shopping bag page from add to bag page
    Then I should see PayPal Checkout button "suppressed" in shopping bag
    And I should see the "Excludes furniture and mattresses" text in pick up in store section
    When I click on "move to list" link for "bigTicket" item
    Then I should see PayPal Checkout button "displayed" in shopping bag
    And I should not see the "Excludes furniture and mattresses" text in pick up in store section
    Examples:
      | user_type  |
      | guest      |
      | registered |

  @project_BT @domain_purchase_and_delivery @scenario7
  Scenario Outline: Verify “Paypal Checkout” button in shopping bag is suppressed and pick up store exclusion message is displayed, when I move all small Ticket items to List and only have Big Ticket items in the bag.
    Given I visit the web site as a <user_type> user
    When I add a big ticket "BT_furniture and ONHAND" product to bag
    And I add a "available and orderable" product to my bag
    And I navigate to shopping bag page from add to bag page
    Then I should see PayPal Checkout button "suppressed" in shopping bag
    And I should see the "Excludes furniture and mattresses" text in pick up in store section
    When I click on "<linkType>" link for "normal" item
    Then I should see PayPal Checkout button "suppressed" in shopping bag
    And I should see the "Excludes furniture and mattresses" text in pick up in store section
    Examples:
      | user_type  | linkType     |
      | guest      | remove       |
      | guest      | move to list |
      | registered | move to list |
      | registered | remove       |


