#Author: Stores Domain Checkout Team
           #Story: SDU-1513- Checkout :: Process Incorrectly Printed Coupons
           #Date Created: 11/15/2017
           #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-1513
Feature: As an associate, I want to be able to properly process known misprinted coupons, so that I can apply the appropriate discount to the transaction for my customer.

  @Macy's @Send @manual
  Scenario: Macy's - Misprinted Coupon should be modified to the corrected coupon for Send
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I should be able to see the bag view
    When I scan misprinted Coupon "<string>" into the bag
    Then the coupon should be modified to be the corrected coupon
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

   @Macy's @Send @manual
  Scenario: Macy's - Misprinted coupon that will fail because it is not in the coupon process as a parameter for a Send
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I should be able to see the bag view
    When I scan misprinted Coupon which is not in the coupon process as a parameter "<string>" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page

  @Macy's @Take @manual
  Scenario: Macy's - Misprinted Coupon should be modified to the corrected coupon for Take
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    And I click on the bag icon
    Then I should be able to see the bag view
    When I scan misprinted Coupon "<string>" into the bag
    Then the coupon should be modified to be the corrected coupon
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Take @manual
  Scenario: Macy's - Misprinted coupon that will fail because it is not in the coupon process as a parameter for a Take
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    And I click on the bag icon
    Then I should be able to see the bag view
    When I scan a misprinted coupon which is not in the coupon process as a parameter "<string>" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Send @manual
  Scenario: Bloomy's - Misprinted Coupon should be modified to the corrected coupon for Send
    Given I am on "Bloomy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I should be able to see the bag view
    When I scan misprinted Coupon "<string>" into the bag
    Then the coupon should be modified to be the corrected coupon
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Send @manual
  Scenario: Bloomy's - Associate should be alerted if the corrected coupon cannot be used due to event end date for a Send
    Given I am on "Bloomy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I should be able to see the bag view
    When I scan misprinted Coupon with an expired event end day "<string>" into the bag
    Then the message indicating the corrected coupon cannot be used due to event end date should be displayed
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Send @manual
  Scenario: Bloomy's - Misprinted coupon that will fail because it is not in the coupon process as a parameter for a Send
    Given I am on "Bloomy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I should be able to see the bag view
    When I scan a misprinted coupon which is not in the coupon process as a parameter "<string>" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take @manual
  Scenario: Bloomy's - Misprinted Coupon should be modified to the corrected coupon for Take
    Given I am on "Bloomy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    And I click on the bag icon
    Then I should be able to see the bag view
    When I scan misprinted Coupon "<string>" into the bag
    Then the coupon should be modified to be the corrected coupon
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take @manual
  Scenario: Bloomy's - Misprinted coupon that will fail because it is not in the coupon process as a parameter for a Take
    Given I am on "Bloomy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    And I click on the bag icon
    Then I should be able to see the bag view
    When I scan misprinted Coupon which is not in the coupon process as a parameter "<string>" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page
