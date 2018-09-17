#Author: Stores Domain Checkout Team
   #Story: SDU-595- Checkout :: Item-Level Menu
   #Date Created: 6/7/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-595
Feature: As an associate, I want options to modify or delete items, so that I can make sure my customer's bag is up-to-
  date and accurate.

  @Macy's @Send
  Scenario: Macy's - Associate is able to swipe left to reveal the slide out menu
  and is able to swipe right to hide the menu.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I slide the item from left to right
    Then I can no longer see the slide out menu
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's @Send
  Scenario: Macy's - Associate can close the more menu by taping the more button again
  and is able to swipe right to hide the menu.
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I click on the more button
    Then I can see the options 'Edit Item','Add Another' and 'Price Change'
    When I click on the more button
    Then I can no longer see the options list
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's @Send
  Scenario: Macy's - Associate is able to swipe left to reveal the slide out menu
  and is able to select the 'more' option and then Add Another
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I click on the more button
    Then I can see the options 'Edit Item','Add Another' and 'Price Change'
    When I tap Add another item button
    Then I should see 2 items in the bag
  And I can see the item information on Sales Trans
  And I call Cancel
  Then I can see the sales trans landing page

@Macy's @Send
    Scenario: Macy's - Associate is able to swipe left to reveal the slide out menu
  and is able to select the 'more' option and then Edit item
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I click on the more button
    Then I can see the options 'Edit Item','Add Another' and 'Price Change'
    When I tap Edit item button
    Then I verify Edit item call URL with UPC
    When I navigate back to the bag
    And I call Cancel
    Then I can see the sales trans landing page

  @Macy's @Send
  Scenario: Macy's - Associate is able to swipe left to reveal the slide out menu and is able to select the 'remove' option.
    Given I am on "Macy's Sales Trans"
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

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Associate is able to swipe left to reveal the slide out menu
  and is able to swipe right to hide the menu.
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I slide the item from left to right
    Then I can no longer see the slide out menu
    When I call Cancel
    Then I can see the sales trans landing page

    #to be enabled when the latest app is deployed on 22, where the "more" menu logo is the circle with 3 dots
#  @Bloomingdale's @Send
#  Scenario: Bloomingdale's - Associate can close the more menu by taping the more button again
#  and is able to swipe right to hide the menu.
#    Given I am on "Bloomingdale's Sales Trans"
#    When I add an item to the Checkout bag
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    When I click on the bag icon
#    Then I should be able to see the bag view
#    When I swipe the item from right to left
#    Then I can see the slide out menu
#    And I can see the slide out menu options
#    When I click on the more button
#    Then I can no longer see the options list
#    When I call Cancel
#    Then I can see the sales trans landing page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Associate is able to swipe left to reveal the slide out menu
  and is able to select the 'more' option and then Add Another
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I click on the more button
    Then I can see the options 'Edit Item','Add Another' and 'Price Change'
    When I tap Add another item button
    Then I should see 2 items in the bag
    And I can see the item information on Sales Trans
    And I call Cancel
    Then I can see the sales trans landing page


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Associate is able to swipe left to reveal the slide out menu
  and is able to select the 'more' option and then Edit item
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I click on the more button
    Then I can see the options 'Edit Item','Add Another' and 'Price Change'
    When I tap Edit item button
    Then I verify Edit item call URL with UPC
    When I navigate back to the bag
    And I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Associate is able to swipe left to reveal the slide out menu
  and is able to select the 'remove' option.
    Given I am on "Bloomingdale's Sales Trans"
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