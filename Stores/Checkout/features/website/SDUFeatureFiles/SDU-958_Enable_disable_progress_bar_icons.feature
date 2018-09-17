#Author: Stores Domain Checkout Team
    #Story: SDU-958 - Checkout :: Enable/Allow quantity parameter for add to bag functionality
    #Date Created: 08/11/2017
    #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-958
Feature: As an associate, I want to be able to move more than a quantity of one for a specific UPC to the bag at one time.

  @Macy's @Send
  Scenario: Macy's - As an associate, I want to be able to move more than a quantity of one for a specific UPC
  to the bag at one time for a send.
    Given I am on "Macy's Sales Trans"
    When I add 3 to quantity
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should see 3 items in the bag
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's @Take
  Scenario:Macy's - As an associate, I want to be able to move more than a quantity of one for a specific UPC
  to the bag at one time for a take.
    Given I am on "Macy's Sales Trans"
    When I add 3 to quantity
   Then I select Take option
    When I add an item to the Checkout bag
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should see 3 items in the bag
    When I call Cancel
    Then I can see the sales trans landing page

  @Macy's
    Scenario: Macy's - As an associate, I should see an error message when inputing 0 items to the quantity field
  and attempting to add to bag.
      Given I am on "Macy's Sales Trans"
      When I add 0 to quantity
      Then I select Take option
      When I add an item to the Checkout bag
      Then I should see the unable to add item popup


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - As an associate, I want to be able to move more than a quantity of one
  for a specific UPC to the bag at one time for a send.
    Given I am on "Bloomingdale's Sales Trans"
    When I add 3 to quantity
    And I add "20714001940" item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should see 3 items in the bag
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - As an associate, I want to be able to move more than a quantity of one
  for a specific UPC to the bag at one time for a take.
    Given I am on "Bloomingdale's Sales Trans"
    When I add 3 to quantity
    And I select Take option
    And I add "20714001940" item to the Checkout bag
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should see 3 items in the bag
    When I call Cancel
    Then I can see the sales trans landing page

  @Bloomingdale's
  Scenario: Bloomingdale's - As an associate, I should see an error message
  when inputing 0 items to the quantity field and attempting to add to bag.
    Given I am on "Bloomingdale's Sales Trans"
    When I add 0 to quantity
    And I select Take option
    And I add "20714001940" item to the Checkout bag
    Then I should see the unable to add item popup





