   #Author: Stores Domain Checkout Team
   #Story: SDU-535- Checkout :: Reciept Screen- Mobile
   #Date Created: 6/28/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-535
Feature: As an associate, I want to see a review of the items that the customer purchased, so that I can initiate the
         completion of the transaction (and get ready to print a receipt).

  @Macy's @Send
    Scenario: Macy's - Associate is able to see a snapshot of the customer's receipt
  which serves as a confirmation that the transaction has processed and is now ready for receipt printing.
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    And The Authorization spinner closes
    And I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the find printer button
    And I can see the receipt information on a send
    When I click posttender print button
    Then I am on "Add Product" page

  @Macy's @Take
  Scenario: Macy's - Associate is able to see a snapshot of the customer's receipt while performing a take sale
  which serves as a confirmation that the transaction has processed and is now ready for receipt printing.
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    And The Authorization spinner closes
    And I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the find printer button
    And I can see the receipt information on a take
    When I click posttender print button
    Then I am on "Add Product" page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Associate is able to see a snapshot of the customer's receipt
  which serves as a confirmation that the transaction has processed and is now ready for receipt printing.
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I see the authorization spinner
    And The Authorization spinner closes
    And I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the find printer button
    And I can see the receipt information on a send
    When I click posttender print button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Associate is able to see a snapshot of the customer's receipt while performing a take sale
  which serves as a confirmation that the transaction has processed and is now ready for receipt printing.
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I see the authorization spinner
    And The Authorization spinner closes
    And I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the find printer button
    And I can see the receipt information on a take
    When I click posttender print button
    Then I am on "Add Product" page