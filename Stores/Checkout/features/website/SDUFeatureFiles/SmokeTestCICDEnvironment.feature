#Author: Stores Domain Checkout Team
   #Story: SDU-Showcase Checkout ::Showcase
   #Date Created: 8/10/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @showcase
Feature: As an associate I want to showcase my team's work

##### Macy's Smoke Tests #####

  @Macy's @Take
  Scenario: Macy's - Swipe and Remove an Item
    Given I am on "product discovery"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I click the remove button
    Then I can see Checkout empty bag view

  @Macy's @Take
  Scenario: Macy's - Scan an item into the bag/Scan CRL
    Given I am on "product discovery"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "39282726" in to the CRL Overlay
    Then The CRL overlay closes
    And I can see the "39282726" CRL in the bag
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page

  @Macy's @Take
  Scenario: Macy's - Take sale tender with Prop
    Given I am on "product discovery"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click posttender print button
    Then I can see the sales trans landing page

  @Macy's @Send
  Scenario: Macy's - Send sale Tender with a prop
    Given I am on "product discovery"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    When I click posttender print button
    Then I can see the sales trans landing page

#    ##### Bloomingdale's Smoke Tests #####
#
#  @Bloomingdale's @Take
#  Scenario: Bloomingdale's - Swipe and remove
#    Given I am on "Bloomingdale's Sales Trans"
#    When I add an item to the Checkout bag
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    When I click on the bag icon
#    Then I should be able to see the bag view
#    When I swipe the item from right to left
#    Then I can see the slide out menu
#    And I can see the slide out menu options
#    When I click the remove button
#    Then I can see Checkout empty bag view
#
#  @Bloomingdale's @Take
#  Scenario: Bloomingdale's - Scan item into the bag and scan a CRL
#    Given I am on "Bloomingdale's Sales Trans"
#    And I click on the bag icon
#    Then I can see Checkout empty bag view
#    When I scan UPC "91709543745" into the bag
#    Then I see the CRL Overlay
#    And I can see the Input field and add button were removed
#    When I scan "39282726" in to the CRL Overlay
#    Then The CRL overlay closes
#    And I can see the "39282726" CRL in the bag
#    When I click on Hamburger icon
#    And I click on Cancel Transaction button
#    Then I can see the sales trans landing page
#
#  @Bloomingdale's @Take
#  Scenario: Bloomingdale's - Take tender with Bloom Prop
#    Given I am on "Bloomingdale's Sales Trans"
#    When I checkout an item for a take sale
#    Then I can see the mock tendering screen
#    And I can verify the authorize button was removed
#    When I swipe the "Bloomingdale's Prop" at the tendering screen
#    Then I see the authorization spinner
#    When The Authorization spinner closes
#    Then I can see the signature view
#    When I input my signature
#    And I press the Confirm signature button
#    Then I can see the postTender screen
#    When I click posttender print button
#    Then I can see the sales trans landing page
#
#  @Bloomingdale's @Send
#  Scenario: Bloomingdale's - Send Sale Tender with Bloom Prop
#    Given I am on "Bloomingdale's Sales Trans"
#    When I checkout an item for a send sale
#    Then I can see the mock tendering screen
#    When I swipe the "Bloomingdale's Prop" at the tendering screen
#    Then I can see the signature view
#    When I input my signature
#    And I press the Confirm signature button
#    When I click posttender print button
#    Then I can see the sales trans landing page
#
#
#
#
