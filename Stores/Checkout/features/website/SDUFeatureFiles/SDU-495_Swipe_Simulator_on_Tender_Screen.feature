     #Author: Stores Domain Checkout Team
     #Story: SDU-495 - Checkout :: Swipe Simulator on Tender Screen
     #Date Created: 05/22/2017
     #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-495
 Feature: As a PdM, I want to print a receipt with sample data, so that I understand the functional flow of the MVP transaction.

   @Macy's @Send
   Scenario: Macy's - An associate presses the Authorize button after completing Place order flow
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     When I swipe the "Macy's Prop" at the tendering screen
     Then I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the postTender screen
     When I click posttender print button
     Then I am on "Add Product" page

   @Macy's @Take
   Scenario: Macy's - An associate tries to Checkout a Take transaction
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     When I swipe the "Macy's Prop" at the tendering screen
     Then I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the postTender screen
     When I click posttender print button
     Then I am on "Add Product" page

   @Bloomingdale's @Send
   Scenario: Bloomingdale's - An associate presses the Authorize button after completing Place order flow
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     When I swipe the "Bloomingdale's Prop" at the tendering screen
     Then I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the postTender screen
     When I click posttender print button
     Then I am on "Add Product" page

   @Bloomingdale's @Take
   Scenario: Bloomingdale's - An associate tries to Checkout a Take transaction
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     When I swipe the "Bloomingdale's Prop" at the tendering screen
     Then I can see the signature view
     When I input my signature
     And I press the Confirm signature button
     Then I can see the postTender screen
     When I click posttender print button
     Then I am on "Add Product" page