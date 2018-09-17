#Author: Stores Domain Checkout Team
#Story: SDU-1547 - Checkout :: Edit shipping & billing address from summary page
#Date Created: 12/14/2017


@domain_stores @project_checkout @release_17 @story_SDU-1547 @wip
Feature: As an associate, I want my customer's shipping and billing address to display in the app to avoid manual data entry.

  @Macy's @Send
  Scenario: Macy's - User arrives on the Review page, taps the edit shipping details button, doesn't do any changes,
            taps "Done" and sees that the same info is populated on the Review page
    Given On "Macy's Sales Trans" I arrive on the "Review" page after swiping a card
    When I tap edit next to Shipping Address
    But I close the edit page without making any changes
    Then I see the same "Shipping Address" on the Review page
    When I tap edit next to Shipping Address
    And I cancel the edit overlay
    Then I see the same "Shipping Address" on the Review page
    When I tap edit next to Billing Address
    But I close the edit page without making any changes
    Then I see the same "Billing Address" on the Review page
    When I tap edit next to Billing Address
    And I cancel the edit overlay
    Then I see the same "Billing Address" on the Review page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Macy's @Send
  Scenario: Macy's - User arrives on the Review page, edits the shipping address
            and sees the changes on the Review screen
    Given On "Macy's Sales Trans" I arrive on the "Review" page after swiping a card
    When I change the "Shipping Address"
    And I arrive on the "Review" page
    Then I see the updated "Shipping Address"
    When I tap edit next to Shipping Address
    And I close the edit page
    Then I arrive on the "Review" page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Macy's @Send
  Scenario: Macy's - User arrives on the Review page, edits the billing address
            and sees the changes on the Review screen
    Given On "Macy's Sales Trans" I arrive on the "Review" page after swiping a card
    When I change the "Billing Address"
    And I arrive on the "Review" page
    Then I see the updated "Billing Address"
    When I tap edit next to Billing Address
    And I close the edit page
    Then I arrive on the "Review" page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - User arrives on the Review page, taps the edit shipping details button, doesn't do any changes,
            taps "Done" and sees that the same info is populated on the Review page
    Given On "Bloomingdale's Sales Trans" I arrive on the "Review" page after swiping a card
    When I tap edit next to Shipping Address
    But I close the edit page without making any changes
    Then I see the same "Shipping Address" on the Review page
    When I tap edit next to Shipping Address
    And I cancel the edit overlay
    Then I see the same "Shipping Address" on the Review page
    When I tap edit next to Billing Address
    But I close the edit page without making any changes
    Then I see the same "Billing Address" on the Review page
    When I tap edit next to Billing Address
    And I cancel the edit overlay
    Then I see the same "Billing Address" on the Review page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - User arrives on the Review page, edits the shipping address
             and sees the changes on the Review screen
    Given On "Bloomingdale's Sales Trans" I arrive on the "Review" page after swiping a card
    When I change the "Shipping Address"
    And I arrive on the "Review" page
    Then I see the updated "Shipping Address"
    When I tap edit next to Shipping Address
    And I close the edit page
    Then I arrive on the "Review" page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - User arrives on the Review page, edits the billing address
            and sees the changes on the Review screen
    Given On "Bloomingdale's Sales Trans" I arrive on the "Review" page after swiping a card
    When I change the "Billing Address"
    And I arrive on the "Review" page
    Then I see the updated "Billing Address"
    When I tap edit next to Billing Address
    And I close the edit page
    Then I arrive on the "Review" page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Macy'sDesktop @Send
  Scenario: Macy's Desktop- User arrives on the Review page, taps the edit shipping details button, doesn't do any changes,
            taps "Done" and sees that the same info is populated on the Review page
    Given On "Macy's Desktop" I arrive on the "Review" page after swiping a card
    When I tap edit next to Shipping Address
    But I close the edit page without making any changes
    Then I see the same "Shipping Address" on the Review page
    When I tap edit next to Shipping Address
    And I cancel the edit overlay
    Then I see the same "Shipping Address" on the Review page
    When I tap edit next to Billing Address
    But I close the edit page without making any changes
    Then I see the same "Billing Address" on the Review page
    When I tap edit next to Billing Address
    And I cancel the edit overlay
    Then I see the same "Billing Address" on the Review page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Macy'sDesktop @Send
  Scenario: Macy's Desktop- User arrives on the Review page, edits the shipping address
            and sees the changes on the Review screen
    Given On "Macy's Desktop" I arrive on the "Review" page after swiping a card
    When I change the "Shipping Address"
    And I arrive on the "Review" page
    Then I see the updated "Shipping Address"
    When I tap edit next to Shipping Address
    And I close the edit page
    Then I arrive on the "Review" page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Macy'sDesktop @Send
  Scenario: Macy's Desktop- User arrives on the Review page, edits the billing address
            and sees the changes on the Review screen
    Given On "Macy's Desktop" I arrive on the "Review" page after swiping a card
    When I change the "Billing Address"
    And I arrive on the "Review" page
    Then I see the updated "Billing Address"
    When I tap edit next to Billing Address
    And I close the edit page
    Then I arrive on the "Review" page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page