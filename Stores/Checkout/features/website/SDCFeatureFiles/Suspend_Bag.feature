 #Author: Stores Domain Checkout Team
 #Story: SDC-35 - Checkout :: Suspend a Bag
 #Date Created: 09/22/2016
 #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDC-35
Feature: As an associate, I want to suspend a bag to be retrieved at a later time,
  so that I can seamlessly complete a transaction for a customer regardless of which device
  I start the transaction on.

  @Macy's @Send
  Scenario: Macy's - An associate suspends a bag and is returned to the home screen.
    Given I am on "Macy's Sales Trans"
   	And I add an item to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see the sales trans landing page

  @Macy's @Send
  Scenario: Macy's - An associate attempts to suspend a bag and then presses cancel they are returned to the bag screen.
    Given I am on "Macy's Sales Trans"
   	And I add an item to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I press the cancel button
    Then I remain on the bag screen
    When I remove all item from the Checkout bag
    Then I can see the sales trans landing page
#
#  @Macy's @Send
#  Scenario: Macy's - An associate wants to suspend for the maximum number of items in a transaction.
#    Given I am on "Macy's Sales Trans"
#    When I add 50 items to bag
#    And I click on the bag icon
#    Then I can view the suspend button
#    When I press the suspend button
#    Then I should see the customer name overlay
#    When I input the customers name
#    And I click customer name overlay continue button
#    Then I see the suspended bag confirmation overlay
#    When I press OK on the suspended confirmation overlay
#    Then I can see the sales trans landing page



  @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate suspends a bag and is returned to the home screen.
    Given I am on "Bloomingdale's Sales Trans"
   	And I add an item to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see the sales trans landing page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate attempts to suspend a bag and then presses cancel they are returned to the bag screen.
    Given I am on "Bloomingdale's Sales Trans"
   	And I add an item to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I press the cancel button
    Then I remain on the bag screen
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view
    When I click the product icon
    Then I can see the sales trans landing page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate wants to suspend for the maximum number of items in a transaction.
    Given I am on "Bloomingdale's Sales Trans"
    When I add 50 items to bag
    And I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see the sales trans landing page


