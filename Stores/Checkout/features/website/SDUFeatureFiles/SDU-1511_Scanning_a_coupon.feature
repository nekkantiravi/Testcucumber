#Author: Stores Domain Checkout Team
           #Story: SDU-1511- Checkout :: Scanning a Coupon
           #Date Created: 11/15/2017
           #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-1511
Feature:  As an associate, I want the ability to scan a physical coupon, so that I can efficiently service my customer.

  @Macy's @Send
  Scenario: Macy's - Associate can scan a coupon from Send
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I should be able to see the bag view
    When I scan Coupon "00000000002200050007" into the bag
    Then the coupon added message should be displayed
    And the coupon number should be displayed
    And the coupon message should be static
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  #@Macy's @Send
 # Scenario: Macy's - A toast message should display when something other than a UPC or coupon was scanned.
 # Given I am on "Macy's Sales Trans"
 # When I add an item to the Checkout bag
 # Then I can see the added to bag toast message
 # When I click on the bag icon
 # Then I should be able to see the bag view
 # When I scan something other than a UPC or coupon "123456" into the bag
 # Then I see the unable to scan toast message
 # And the toast message fades away after 3 seconds
 # When I click on Hamburger icon
 # And I click on Cancel Transaction button
 # Then I am on "Add Product" page

  @manual
  Scenario: Macy's - Associate can only scan a coupon within the bag view for Send
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I scan Coupon "<string>" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I swipe the "Prop Card" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I scan Coupon "<string>" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I click the product icon
    Then I can see the sales trans landing page
    When I scan Coupon "<string>" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds

  @Macy's @Take
  Scenario: Macy's - Associate can scan a coupon from Take
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    And I click on the bag icon
    Then I should be able to see the bag view
    When I scan Coupon "00000000002200050007" into the bag
    Then the coupon added message should be displayed
    And the coupon number should be displayed
    And the coupon message should be static
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

 # @Macy's @Take
 # Scenario: Macy's - A toast message should display when something other than a UPC or coupon was scanned.
 # Given I am on "Macy's Sales Trans"
 # When I add an item to the Checkout bag for a Take Sale
 # And I close the CRL Overlay
 # And I click on the bag icon
 # Then I should be able to see the bag view
 # When I scan something other than a UPC or coupon "123456" into the bag
 # Then I see the unable to scan toast message
 # And the toast message fades away after 3 seconds
 # When I click on Hamburger icon
 # And I click on Cancel Transaction button
 # Then I am on "Add Product" page

  @manual
  Scenario: Macy's - Associate can only scan a coupon within the bag view for Take
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I scan Coupon "00000000002200050007" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I swipe the "Prop Card" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I scan Coupon "00000000002200050007" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I click the product icon
    Then I can see the sales trans landing page
    When I scan Coupon "00000000002200050007" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Associate can scan a coupon from Send
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I should be able to see the bag view
    When I scan Coupon "00000000002200050007" into the bag
    Then the coupon added message should be displayed
    And the coupon number should be displayed
    And the coupon message should be static
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

 # @Bloomingdale's @Send
 # Scenario: Bloomingdale's - A toast message should display when something other than a UPC or coupon was scanned.
 # Given I am on "Bloomingdale's Sales Trans"
 # When I add an item to the Checkout bag
 # Then I can see the added to bag toast message
 # When I click on the bag icon
 # Then I should be able to see the bag view
 # When I scan something other than a UPC or coupon "123456" into the bag
 # Then I see the unable to scan toast message
 # And the toast message fades away after 3 seconds
 # When I click on Hamburger icon
 # And I click on Cancel Transaction button
 # Then I am on "Add Product" page

  @manual
  Scenario: Bloomingdale's - Associate can only scan a coupon within the bag view for Send
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I scan Coupon "00000000002200050007" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I swipe the "Prop Card" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I scan Coupon "00000000002200050007" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I click the product icon
    Then I can see the sales trans landing page
    When I scan Coupon "00000000002200050007" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Associate can scan a coupon from Take
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    And I close the CRL Overlay
    And I click on the bag icon
    Then I should be able to see the bag view
    When I scan Coupon "00000000002200050007" into the bag
    Then the coupon added message should be displayed
    And the coupon number should be displayed
    And the coupon message should be static
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

 # @Bloomingdale's @Take
 # Scenario: Bloomingdale's - A toast message should display when something other than a UPC or coupon was scanned.
 # Given I am on "Bloomingdale's Sales Trans"
 # When I add an item to the Checkout bag for a Take Sale
 # And I close the CRL Overlay
 # And I click on the bag icon
 # Then I should be able to see the bag view
 # When I scan something other than a UPC or coupon "123456" into the bag
 # Then I see the unable to scan toast message
 # And the toast message fades away after 3 seconds
 # When I click on Hamburger icon
 # And I click on Cancel Transaction button
 # Then I am on "Add Product" page

  @manual
  Scenario: Bloomingdale's - Associate can only scan a coupon within the bag view for Take
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I scan Coupon "00000000002200050007" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I swipe the "Prop Card" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I scan Coupon "00000000002200050007" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds
    When I click the product icon
    Then I can see the sales trans landing page
    When I scan Coupon "00000000002200050007" into the bag
    Then I see the unable to scan toast message
    And the toast message fades away after 3 seconds