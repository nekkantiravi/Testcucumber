#Author: Stores Domain Checkout Team
#Story: SDU-1518- Checkout :: Populate shipping & billing address on summary page
#Date Created: 12/13/2017


@domain_stores @project_checkout @release_17 @story_SDU-1518 @wip
Feature: As an associate, I want my customer's shipping and billing address to display in the app to avoid manual data entry.

  @Macy's @Send
  Scenario: Macy's - User arrives on the Review page and his shipping and billing details are already populated
    Given On "Macy's Sales Trans" I arrive on the "Shipping Method" page after swiping a card
    When I click on the "Done" button on the overlay
    Then I arrive on the "Review" page
    And I see the "Shipping Method" information
    And I see the "Shipping Address" information
    And I see the "Billing Address" information
    And I see the navigation buttons on the review overlay
    When I click on the "X" button on the overlay
    And I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Macy's @Send
  Scenario: Macy's - User can navigate to and from the Review page
    Given On "Macy's Sales Trans" I arrive on the "Review" page after swiping a card
    When I click on the "Back" button on the overlay
    Then I arrive on the "Shipping Method" page
    When I click on the "Done" button on the overlay
    Then I arrive on the "Review" page
    When I click on the "X" button on the overlay
    Then I arrive on the "Bag" page
    When I press the checkout button
    And I click on the "Done" button on the overlay
    Then I arrive on the "Review" page
    When I click on the "Finish" button on the overlay
    Then I arrive on the "Payment" page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - User arrives on the Review page and his shipping and billing details are already populated
    Given On "Bloomingdale's Sales Trans" I arrive on the "Shipping Method" page after swiping a card
    When I arrive on the "Review" page
    Then I see the "Shipping Method" information
    And I see the "Shipping Address" information
    And I see the "Billing Address" information
    And I see the navigation buttons on the review overlay
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - User can navigate to and from the Review page
    Given On "Bloomingdale's Sales Trans" I arrive on the "Review" page after swiping a card
    When I click on the "Back" button on the overlay
    Then I arrive on the "Shipping Method" page
    When I click on the "Next" button on the overlay
    Then I arrive on the "Review" page
    When I click on the "X" button on the overlay
    Then I arrive on the "Bag" page
    When I arrive on the "Review" page
    And I click on the "Finish" button on the overlay
    Then I arrive on the "Payment" page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Macy'sDesktop @Send
  Scenario: Macy's Desktop - User arrives on the Review page and his shipping and billing details are already populated
    Given On "Macy's Desktop" I arrive on the "Shipping Method" page after swiping a card
    When I arrive on the "Review" page
    Then I see the "Shipping Method" information
    And I see the "Shipping Address" information
    And I see the "Billing Address" information
    And I see the navigation buttons on the review overlay
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page


  @Macy'sDesktop @Send
  Scenario: Macy's Desktop - User can navigate to and from the Review page
    Given On "Macy's Desktop" I arrive on the "Review" page after swiping a card
    When I click on the "Back" button on the overlay
    Then I arrive on the "Shipping Method" page
    When I click on the "Next" button on the overlay
    Then I arrive on the "Review" page
    When I click on the "X" button on the overlay
    Then I arrive on the "Bag" page
    When I arrive on the "Review" page
    And I click on the "Finish" button on the overlay
    Then I arrive on the "Payment" page
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page