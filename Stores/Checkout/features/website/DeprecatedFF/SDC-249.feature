 #Author: Stores Domain Checkout Team
 #Story: SDC-249 - Checkout :: Checkout Button
 #Date Created: 01/15/2016
 #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDC-249
 Feature: As an associate, I want to check out customers on uPOS, so that I can quickly expedite transactions.

  Scenario: An associate Navigates to an empty bag. Checkout & suspend buttons do not display
    Given I am on Sales Trans
    When I click on the bag icon
    Then I can see Checkout empty bag view
    And I do not see the Checkout or Suspend button 

  Scenario: An associate add an item to the bag and navigates to the bag. The checkout button is available.
    Given I am on Sales Trans
   	When I add an item to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   	When I click on the bag icon
    Then I can view the suspend button
    And I can see the checkout button
