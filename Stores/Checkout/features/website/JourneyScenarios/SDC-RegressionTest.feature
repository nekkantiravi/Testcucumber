 #Author: Stores Domain Checkout Team
 #Story: Regression Test Suite
 #Date Created: 01/03/2017
 #Date Signed Off:

 @domain_stores @project_checkout @release_17
 Feature: I want to execute all regression test scenarios.

#   Remove item from bag
  @story_SDC-24 @regression
  Scenario: An associate wants to delete an item from the bag and view empty bag screen.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    And I close the CRL Overlay
    Then I can see the added to bag confirmation layer
    When I click the View Bag button and navigate to the bag
    Then I should be able to see totals information of all items added
    When I remove an item from the Checkout bag
    Then I can see Checkout empty bag view

   #Remove item from bag The issue is that each item has a number next to it now removebutton1 2 etc
  @story_SDC-24 @regression
  Scenario: An associate wants to delete an item from the bag and view the items is removed.
    Given I am on Sales Trans
    When I add 2 items to bag
    And I click on the bag icon
    Then I see the item added to the bag
    Then I should be able to see totals information of all items added
    When I remove all item from the Checkout bag
    Then I can see Checkout empty bag view
#
#   #Add to bag and check item details
#  @story_SDC-140 @regression
#  Scenario: An associate wants to add an item to a bag and can view item details.
#   Given I am on Sales Trans
#   When I add an item to the Checkout bag that has an associated fee
#   And I close the CRL Overlay
#   Then I can see the added to bag confirmation layer
#   When I click the View Bag button and navigate to the bag
#   Then I can see the item information
#    When I remove all item from the Checkout bag
#    Then I can see Checkout empty bag view
#
#   #Suspend from bag view
#   @story_SDC-35
#  Scenario: An associate suspends a bag and is returned to the home screen.
#    Given I am on Sales Trans
#   	And I add an item to the Checkout bag
#   	And I close the CRL Overlay
#   	Then I can see the added to bag confirmation layer
#   	When I click the View Bag button and navigate to the bag
#    Then I can view the suspend button
#    When I press the suspend button
#    Then I should see the customer name overlay
#    When I input the customers name
#    And I click customer name overlay suspend button
#    Then I see the suspended bag confirmation overlay
#    When I press OK on the suspended confirmation overlay
#    Then I am returned to New POS Sample webapp
#
#   #Suspend from header
#  @story_SDC-120 @regression
#  Scenario: An associate suspends a bag and is returned to the home screen.
#    Given I am on Sales Trans
#    When I add an item to the Checkout bag
#   And I close the CRL Overlay
#   Then I can see the added to bag confirmation layer
#    When I click the View Bag button and navigate to the bag
#    Then I can view the PDP Header
#    When I press the customer icon
#   Then I can see the customer bag
#    When I press the Header Suspend button
#    Then I should see the customer name overlay
#   When I input the customers name
#    And I click customer name overlay suspend button
#    Then I see the suspended bag confirmation overlay
#    When I press OK on the suspended confirmation overlay
#    Then I am returned to New POS Sample webapp
#
#  #Ellipsis menu - add another item
#  @story_SDC-114 @regression
#  Scenario: An associate navigates to the bag, taps on the ellipsis menu and taps on add another an item, verifies call URL to PDP with UPC.
#    Given I am on Sales Trans
#    When I add an item to the Checkout bag
#    And I close the CRL Overlay
#    Then I can see the added to bag confirmation layer
#    When I click the View Bag button and navigate to the bag
#    And I should see ellipsis menu on the item
#    When I tap ellipsis menu
#   Then I should see ellipsis drop-down menu
#   And I should see an option to Add another item
#   And I should see an option to Edit item
#    When I tap Add another item from ellipsis menu
#    Then I verify Add another item call URL with UPC
#    When I access Checkout bag screen
#    And I remove all item from the Checkout bag
#    Then I can see Checkout empty bag view
#
#   #Ellipsis menu - edit item
#  @story_SDC-115 @regression
#  Scenario: An associate navigates to the bag, taps on the ellipsis menu and taps on edit an item, verifies call URL to PDP with UPC.
#    Given I am on Sales Trans
#    When I add an item to the Checkout bag
#   And I close the CRL Overlay
#    Then I can see the added to bag confirmation layer
#   When I click the View Bag button and navigate to the bag
#   And I should see ellipsis menu on the item
#    When I tap ellipsis menu
#   Then I should see ellipsis drop-down menu
#   And I should see an option to Add another item
#   And I should see an option to Edit item
#   When I tap Edit item from ellipsis menu
#   Then I verify Edit item call URL with UPC
#   When I access Checkout bag screen
#   And I remove all item from the Checkout bag
#   Then I can see Checkout empty bag view
#
#	#check totals
#   @story_SDC-20
#  Scenario: An associate wants to view totals information of a customers bag.
#    Given I am on Sales Trans
#   	When I add an item to the Checkout bag
#    And I close the CRL Overlay
#    Then I can see the added to bag confirmation layer
#    When I click the View Bag button and navigate to the bag
#	  Then I should be able to see totals information of all items added
#	  When I remove all item from the Checkout bag
#    Then I can see Checkout empty bag view
#
#	#check totals
#  @story_SDC-20
#  Scenario: An associate wants to view totals information of a customers bag whenever a change is made.
#    Given I am on Sales Trans
#   	When I add 2 items to bag
#   	And I access Checkout bag screen
#    Then I should be able to see totals information of all items added
#    When I remove an item from the Checkout bag
#    Then I should be able to see totals information of all items added
#    And I should be able to see totals information is updated
#    When I remove all item from the Checkout bag
#    Then I can see Checkout empty bag view
#
#
#    # CRL Test Cases
#  @story_SDC-32 @regression
#  Scenario: On Product Discovery I add an item to the bag then input the CRL.
#    Given I am on Sales Trans
#   	When I add an item to the Checkout bag
#   	Then I see the CRL Overlay
#  	When I input a valid CRL
#  	Then I can see the added to bag confirmation layer
#  	When I click the View Bag button and navigate to the bag
#  	Then I can see the CRL was added to the item in the bag
#  	When I remove an item from the Checkout bag
#    Then I can see Checkout empty bag view
#
#  @story_SDC-32 @regression
# Scenario: On Product Disovery Skip CRL entry.
#   Given I am on Sales Trans
#   When I add an item to the Checkout bag
#   Then I see the CRL Overlay
#   When I close the CRL Overlay
#   Then I can see the added to bag confirmation layer
#   When I click the View Bag button and navigate to the bag
#   Then I can see the CRL was NOT added to the item in the bag
#   When I remove an item from the Checkout bag
#   Then I can see Checkout empty bag view
#
#   @story_SDC-32 @regression
#  Scenario: Invalid CRL from Product Discovery in a take sale
#    Given I am on Sales Trans
#   	When I add an item to the Checkout bag
#   	Then I see the CRL Overlay
#   	When I input an invalid CRL
#   	Then I can see the Invalid CRL error message
#   	When I close the CRL Overlay
#   	Then I can see the added to bag confirmation layer
#  	When I click the View Bag button and navigate to the bag
#  	And I remove an item from the Checkout bag
#    Then I can see Checkout empty bag view
#
#
#    @story_SDC-32 @regression
#  Scenario: Duplicate CRL from Product Discovery in a take sale
#    Given I am on Sales Trans
#    When I add an item to the Checkout bag
#   	Then I see the CRL Overlay
#   	When I input a valid CRL
#   	Then I can see the added to bag confirmation layer
#   	When I add a second item to the Checkout bag
#   	Then I see the CRL Overlay
#   	When I input a valid CRL
#   	Then I see the duplicate CRL error message
#   	When I Clear the field
#   	And I input a different CRL
#   	Then I can see the added to bag confirmation layer
#   	When I click the View Bag button and navigate to the bag
#   	Then I can see both CRLs in the bag view
#
##Customer Tooltips
#  @story_SDC-212
#  Scenario: An associate wants to see the tool tip & close the tool tip
#    Given I am on Sales Trans
#   	When I add an item to the Checkout bag
#   	And I close the CRL Overlay
#   	Then I can see the added to bag confirmation layer
#   	When I click the View Bag button and navigate to the bag
#   	Then I can see the checkout button
#   	When I press the checkout button
#   	Then I see the Customer Identification Screen
#    And  I can see the tool tips icon
#   	When I press the tool tips icon
#   	Then I can see the associate tool tips
#   	When I press the close button
#   	Then the associate tool tips closes

   	  #50 items limitation
#  @story_SDC-31 @regression
#  Scenario: An associate wants to be notified for the maximum number of items in a transaction.
#   Given I am on Sales Trans
#  	When I should be able to add "50" items to the bag
#   	When I add 51st item into the bag
#   	Then I should view the item limit error message
#    And I should be able to clear the error message
#    When I access Checkout bag screen
#    And I remove all item from the Checkout bag
#    Then I can see Checkout empty bag view
#

