 #Author: Stores Domain Checkout Team
 #Story: SDC-207 - Checkout :: Identify Customer through Checkout Process
 #Date Created: 01/10/2016
 #Date Signed Off:

 @domain_stores @project_checkout @release_16 @story_SDC-207
 Feature: As a customer, I want to identify myself in store, so that I can earn loyalty points, use coupons, etc.

  Scenario: An associate suspends a bag and is returned to the home screen.
    Given I am on Sales Trans
   	When I add an item to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   	When I click the View Bag button and navigate to the bag
    Then I can see the checkout button
    When I press the checkout button
    Then I see the Customer Identification Screen 
    And I can see the tool tips icon
    And I can see the Find Customer button
    And I can see the Customer Lookup Field 