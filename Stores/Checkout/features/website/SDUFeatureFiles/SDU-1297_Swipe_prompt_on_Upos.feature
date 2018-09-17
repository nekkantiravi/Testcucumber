 #Author:Ana - Stores Domain Checkout Team
       #Story: SDU-1297: Swipe prompt on uPOS
       #Date Created: 11/24/2017
       #Date Signed Off:

 @domain_stores @project_checkout @release_1722 @story_SDU-1297
 Feature: As an associate, I want to be informed when the customer is required to provide payment at the CRU, so I can best assist my customer.

   @Macy's @Take
   Scenario: Macy's - The swipe message is displayed on the tendering screen - for a take sale
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I click on Hamburger icon
     Then I cancel the transaction

   @Macy's @Send
   Scenario: Macy's - The swipe message is displayed on the tendering screen - for a send sale
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I click on Hamburger icon
     Then I cancel the transaction

   @Macy's @Take
   Scenario: Macy's - The swipe message is no longer displayed after swiping a card- for a take sale
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I swipe the "Macy's Prop" at the tendering screen
     Then I see the authorization spinner
     When I can see the Authorize button
     Then I do not see the swipe prompt message
     And I can see the signature view
     When I input my signature
     And I can see Accept signature button is active
     Then I press the Confirm signature button
     And I can see the find printer button
     Then I click on the find printer button

   @Macy's @Send
   Scenario: Macy's - The swipe message is no longer displayed after swiping a card- for a send sale
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I swipe the "Macy's Prop" at the tendering screen
     Then I see the authorization spinner
     When I can see the Authorize button
     Then I do not see the swipe prompt message
     And I can see the signature view
     When I input my signature
     And I can see Accept signature button is active
     Then I press the Confirm signature button
     And I can see the find printer button
     Then I click on the find printer button

   @Macy's @Take
   Scenario: Macy's - I am on Add Product page when I cancel the transaction after the swipe prompt message is displayed - for a take sale
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page

   @Macy's @Take
   Scenario: Macy's - I can navigate back to bag page after the swipe prompt message is displayed- for a take sale
     Given I am on "Macy's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I click on the bag icon
     Then I am on "Bag" page
     When I click on Hamburger icon
     Then I cancel the transaction

   @Bloomingdale's @Take
   Scenario: Bloomindale's - The swipe message is displayed on the tendering screen - for a take sale
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I click on Hamburger icon
     Then I cancel the transaction

   @Bloomingdale's @Send
   Scenario: Bloomindale's - The swipe message is displayed on the tendering screen - for a send sale
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I click on Hamburger icon
     Then I cancel the transaction

   @Bloomingdale's @Take
   Scenario: Bloomingdale's - The swipe message is no longer displayed after swiping a card- for a take sale
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I swipe the "Bloomingdale's Prop" at the tendering screen
     Then I see the authorization spinner
     When I can see the Authorize button
     Then I do not see the swipe prompt message
     And I can see the signature view
     When I input my signature
     And I can see Accept signature button is active
     Then I press the Confirm signature button
     And I can see the find printer button
     Then I click on the find printer button

   @Bloomingdale's @Send
   Scenario: Bloomindale's - The swipe message is no longer displayed after swiping a card- for a send sale
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a send sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I swipe the "Bloomingdale's Prop" at the tendering screen
     Then I see the authorization spinner
     When I can see the Authorize button
     Then I do not see the swipe prompt message
     And I can see the signature view
     When I input my signature
     And I can see Accept signature button is active
     Then I press the Confirm signature button
     And I can see the find printer button
     Then I click on the find printer button

   @Bloomingdale's @Take
   Scenario: Bloomindale's - I am on Add Product page when I cancel the transaction after the swipe prompt message is displayed - for a take sale
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I am on "Add Product" page

   @Bloomingdale's @Take
   Scenario: Bloomindale's - I can navigate back to bag page after the swipe prompt message is displayed- for a take sale
     Given I am on "Bloomingdale's Sales Trans"
     When I checkout an item for a take sale
     Then I can see the mock tendering screen
     And I see the swipe prompt message
     When I click on the bag icon
     Then I am on "Bag" page
     When I click on Hamburger icon
     Then I cancel the transaction