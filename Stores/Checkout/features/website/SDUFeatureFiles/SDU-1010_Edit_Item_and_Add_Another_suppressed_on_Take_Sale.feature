  #Author: Stores Domain Checkout Team
  #Story: SDU-1010- Checkout :: Suppress 'Edit Item' and 'Add Another' on take sale
  #Date Created: 6/7/2017
  #Date Signed Off:

  @domain_stores @project_checkout @release_17 @story_SDU-1010
  Feature: As an associate, I want options to modify or delete items, so that I can make sure my customer's bag is up-to-
    date and accurate.

    @Macy's @Take
    Scenario: Macy's Take Sale buttons do not display
      Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag for a Take Sale
      And I close the CRL Overlay
      And I click on the bag icon
      Then I should be able to see the bag view
      When I swipe the item from right to left
      Then I can see the slide out menu
      And I can see the slide out menu options
      When I click on the more button
      Then I do not see the add item or edit item buttons
      But I see the change price button
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

    @Macy's @Send
    Scenario: Macy's Send buttons do display
      Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag
      Then I can see the added to bag toast message
      When I click on the bag icon
      Then I should be able to see the bag view
      When I swipe the item from right to left
      Then I can see the slide out menu
      And I can see the slide out menu options
      When I click on the more button
      Then I can see the options 'Edit Item','Add Another' and 'Price Change'
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

    @Bloomingdale's @Take
    Scenario: Bloomingdale's Take Sale buttons do not display
      Given I am on "Bloomingdale's Sales Trans"
      When I select Take option
      And I add "20714001940" item to the Checkout bag
      And I close the CRL Overlay
      And I click on the bag icon
      Then I should be able to see the bag view
      When I swipe the item from right to left
      Then I can see the slide out menu
      And I can see the slide out menu options
      When I click on the more button
      Then I do not see the add item or edit item buttons
      But I see the change price button
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page

    @Bloomingdale's @Send
    Scenario: Bloomingdale's Send buttons do display
      Given I am on "Bloomingdale's Sales Trans"
      And I add "20714001940" item to the Checkout bag
      Then I can see the added to bag toast message
      When I click on the bag icon
      Then I should be able to see the bag view
      When I swipe the item from right to left
      Then I can see the slide out menu
      And I can see the slide out menu options
      When I click on the more button
      Then I can see the options 'Edit Item','Add Another' and 'Price Change'
      When I click on Hamburger icon
      And I click on Cancel Transaction button
      Then I am on "Add Product" page