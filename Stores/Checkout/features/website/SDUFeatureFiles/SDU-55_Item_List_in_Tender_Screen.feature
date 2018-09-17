# Author: Dorin Pop - Stores Domain Checkout Team
# Story: SDU-55 - Item List in Tender Screen - Mobile
# Date Created: 06/12/2017
# Date Signed Off:

@domain_stores @project_checkout @release_1711 @story_SDU-55
Feature: As a customer, I want to see a summary of my items,
  so that I have a clear picture of my purchase contents when tendering.

  @Macy's @Take
  Scenario: Macy's - Take Sale - After arriving on the tender screen, the associate sees the items in a summary view
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can see the item list in a summary view
    And I can see the item price
    And I can see the subtotal
    And I can see the tax amount
    And I should be able to see totals information of all items added
    But The shipping fee is missing
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Send
  Scenario: Macy's - Send Sale - Shipping method is set to standard.
            Shipping Fee 0 should still be displayed in the tender screen
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can see the item list in a summary view
    And I can see the item price
    And I can see the subtotal
    And I can see the tax amount
    And I should be able to see totals information of all items added
    And I can see the shipping fee displayed and set to "$0.00"
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Macy's @Send
  Scenario: Macy's - Send Sale - After arriving on the tender screen, the associate sees the items in a summary view
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale with a shipping fee
    Then I can see the mock tendering screen
    And I can see the item list in a summary view
    And I can see the item price
    And I can see the subtotal
    And I can see the tax amount
    And I should be able to see totals information of all items added
    And I can see the shipping fee
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Take Sale - After arriving on the tender screen, the associate sees the items in a summary view
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can see the item list in a summary view
    And I can see the item price
    And I can see the subtotal
    And I can see the tax amount
    And I should be able to see totals information of all items added
    But The shipping fee is missing
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Send Sale - Shipping method is set to standard.
            Shipping Fee 0 should still be displayed in the tender screen
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can see the item list in a summary view
    And I can see the item price
    And I can see the subtotal
    And I can see the tax amount
    And I should be able to see totals information of all items added
    And I can see the shipping fee displayed and set to "$0.00"
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Send Sale - After arriving on the tender screen, the associate sees the items in a summary view
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale with a shipping fee
    Then I can see the mock tendering screen
    And I can see the item list in a summary view
    And I can see the item price
    And I can see the subtotal
    And I can see the tax amount
    And I should be able to see totals information of all items added
    And I can see the shipping fee
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

