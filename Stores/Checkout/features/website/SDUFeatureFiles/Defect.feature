#Author: Stores Domain Checkout Team
    #Story: Defect Feature File - Checkout :: Various defect scenarios
    #Date Created: 08/11/2017
    #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-803
Feature: As an associate, I want to ensure that I am able to scroll through the bag with 3 or more items, that the suspend
  button is still enabled and clickable if I close the customer overlay, that the number of items in the bag in the right
  corner is within the bounds, that there isn't an arrow on take sales in the bag action menu, that there is only one
  about link in the hamburger menu, and that they're able to add 50 items and 50 different CRLs to the bag.

  @Macy's @Take
  Scenario: Macy's - As an associate, I want to ensure that I am able to scroll through the bag when there are 3 or more items in
  the bag for a take sale.
    Given I am on "Macy's Sales Trans"
    When I add 3 to quantity
    Then I select Take option
    When I add an item to the Checkout bag
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I scroll down to see items listed
    Then I should see 3 items in the bag
    And I remove all item from the Checkout bag
    When I call Cancel
    Then I am on "Macy's Sales Trans"

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - As an associate, I want to ensure that I am able to scroll through the bag when there are 3 or more items in
  the bag for a take sale.
    Given I am on "Bloomingdale's Sales Trans"
    When I add 3 to quantity
    And I select Take option
    And I add "20714001940" item to the Checkout bag
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I scroll down to see items listed
    Then I should see 3 items in the bag
    And I remove all item from the Checkout bag
    When I call Cancel
    Then I am on "Bloomingdale's Sales Trans"

  @Macy's @Send
  Scenario: Macy's - As an associate, I want to ensure that I am able to scroll through the bag when there are 3 or more items in
  the bag for a send.
    Given I am on "Macy's Sales Trans"
    When I add 3 to quantity
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I scroll down to see items listed
    Then I should see 3 items in the bag
    And I remove all item from the Checkout bag
    When I call Cancel
    Then I am on "Macy's Sales Trans"

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - As an associate, I want to ensure that I am able to scroll through the bag when there are 3 or more items in
  the bag for a send.
    Given I am on "Bloomingdale's Sales Trans"
    When I add 3 to quantity
    And I add "20714001940" item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    And I scroll down to see items listed
    Then I should see 3 items in the bag
    And I remove all item from the Checkout bag
    When I call Cancel
    Then I am on "Bloomingdale's Sales Trans"

  @Macy's @Send
  Scenario: Macy's - As an associate, I want to ensure that the suspend button is still enabled and clickable if they close the
  customer overlay without proceeding.
    Given I am on "Macy's Sales Trans"
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    And I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I close the customer name overlay
    Then I can view the suspend button
    When I press the suspend button
    And I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I am on "Macy's Sales Trans"

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - As an associate, I want to ensure that the suspend button is still enabled and clickable if they close the
  customer overlay without proceeding.
    Given I am on "Bloomingdale's Sales Trans"
    When I add "20714001940" item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    And I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I close the customer name overlay
    Then I can view the suspend button
    When I press the suspend button
    And I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I am on "Bloomingdale's Sales Trans"

  @UAT @Send
  Scenario: As an associate, I want to ensure that if I add 10 or more items to the bag, that the number of items is
  correctly showing in the bag action menu in the top right corner of the bag.
    Given I am on Sales Trans
    When I add 11 to quantity
    Then I add an item to the Checkout bag
    And I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    And I should see the bag total within the constraints of the bag action menu

  @Macy's @Take
  Scenario: Macy's - As an associate, I want to ensure that the arrow is not visible within the bag action menu when processing
  a take sale.
    Given I am on "Macy's Sales Trans"
    Then I select Take option
    When I add an item to the Checkout bag
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the bag action menu
    And I can verify that I don't see the send arrow
    When I call Cancel
    Then I am on "Macy's Sales Trans"

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - As an associate, I want to ensure that the arrow is not visible within the bag action menu when processing
  a take sale.
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    Then I add "20714001940" item to the Checkout bag
    And I Scan in a random crl
    And I can see the added to bag toast message
    Then the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can see the bag action menu
    And I can verify that I don't see the send arrow
    When I call Cancel
    Then I am on "Bloomingdale's Sales Trans"

  @Macy's
  Scenario: Macy's - As an associate, I want to ensure that there is only one about link within the hamburger menu.
    Given I am on "Macy's Sales Trans"
    And I click on Hamburger icon
    Then I See the About icon
    When I call Cancel
    Then I am on "Macy's Sales Trans"

  @Bloomingdale's
  Scenario: Bloomingdale's - As an associate, I want to ensure that there is only one about link within the hamburger menu.
    Given I am on "Bloomingdale's Sales Trans"
    And I click on Hamburger icon
    Then I See the About icon
    When I call Cancel
    Then I am on "Bloomingdale's Sales Trans"

  @Macy's @Take
  Scenario: Macy's- As an associate, I want to ensure that I am unable to see the cancel button when I'm on the print screen for a take
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    And The Authorization spinner closes
    And I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click on Hamburger icon
    Then I cannot see the Cancel Transaction Button
    When I click posttender print button
    Then I see the print confirmation toast message
    And I can see the sales trans landing page

  @Macy's @Send
  Scenario: Macy's- As an associate, I want to ensure that I am unable to see the cancel button when I'm on the print screen for a send
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    And The Authorization spinner closes
    And I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click on Hamburger icon
    Then I cannot see the Cancel Transaction Button
    When I click posttender print button
    Then I see the print confirmation toast message
    And I can see the sales trans landing page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's- As an associate, I want to ensure that I am unable to see the cancel button when I'm on the print screen for a take
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click on Hamburger icon
    Then I cannot see the Cancel Transaction Button
    When I click posttender print button
    Then I see the print confirmation toast message
    And I can see the sales trans landing page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's- As an associate, I want to ensure that I am unable to see the cancel button when I'm on the print screen for a send
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click on Hamburger icon
    Then I cannot see the Cancel Transaction Button
    When I click posttender print button
    Then I see the print confirmation toast message
    And I can see the sales trans landing page




