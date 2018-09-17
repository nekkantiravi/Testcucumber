# Author: Stores Domain Checkout Team
# Story: SDC-38 - Checkout :: Associate Authorization to Ring Transactions
# Date Created: 01/12/2017
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-38
Feature: As an associate, I want to be authorized to ring transactions at POS, so that I can perform sales and tasks for my customers.

  @Macy's @Send
  Scenario: Macy's - An associate navigates to the bag screen before adding an item.
    Given I am on "Macy's Sales Trans"
    When I set the unathorized manager level
    And I click the product icon
    When I click on the bag icon
    And I can see the unathorized to ring error message
    When I close the error message
    Then I should be able to see the bag view

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - An associate navigates to the bag screen before adding an item.
    Given I am on "Bloomingdale's Sales Trans"
    When I set the unathorized manager level
    And I click the product icon
    When I click on the bag icon
    And I can see the unathorized to ring error message
    When I close the error message
    Then I should be able to see the bag view


 # @Macy's @Send
  #Scenario: Macy's - An associate navigates to the bag after adding an item.
    #Given I am on "Macy's Sales Trans"
   # When I set the unathorized manager level
   # And I add an item to the Checkout bag
    #Then I can see the added to bag toast message
   # And the toast message fades away after 2 seconds
    #When I click on the bag icon
   # Then I can see the unathorized to ring error message
   # When I close the error message
    #Then I should be able to see the bag view
   # And I see the disabled suspend and checkout buttons


