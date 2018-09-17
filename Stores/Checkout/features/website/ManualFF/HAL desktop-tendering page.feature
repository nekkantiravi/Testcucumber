# Author: Ana Ghergut - Stores Domain Checkout Team
# Story: SDU-1235 - Tendering page on fixed register
# Date Created: 11/06/2017
# Date Signed Off:

@domain_stores @project_checkout @release_1721 @story_SDU-1235
Feature:  As an associate using a fixed register, I want an organized interface when my customer pays for her merchandise, so I have a clear view of the total amount she owes and items she is paying for.

  @manual @HALDesktop @Take
  Scenario: HALDesktop - Take Sale - After arriving on the tender screen, the associate sees the items in a summary view
    Given I am on Hal Desktop
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can see the item list in a summary view
    And I can see the item price
    And I can see the subtotal
    And I can see the tax amount
    And I should be able to see totals information of all items added
    But The shipping fee is missing

  @manual @HALDesktop @Send
  Scenario: Hal Desktop - Send Sale - Shipping method is set to standard.
  Shipping Fee 0 should still be displayed in the tender screen
    Given I am on Hal Desktop
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can see the item list in a summary view
    And I can see the item price
    And I can see the subtotal
    And I can see the tax amount
    And I should be able to see totals information of all items added
    And I can see the shipping fee displayed and set to "$0.00"


  @manual @HALDesktop @Send
  Scenario: Hal Desktop - Send Sale - After arriving on the tender screen, the associate sees the items in a summary view
    Given I am on Hal Desktop
    When I checkout an item for a send sale with a shipping fee
    Then I can see the mock tendering screen
    And I can see the item list in a summary view
    And I can see the item price
    And I can see the subtotal
    And I can see the tax amount
    And I should be able to see totals information of all items added
    And I can see the shipping feee