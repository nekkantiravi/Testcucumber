# Author: Stores Domain Checkout Team
# Story: SDC-220 - Checkout :: Integrate with waitscreen spinner
# Date Created: 12/28/2016
# Date Signed Off:

@manual @domain_stores @project_checkout @release_17 @story_SDC-220
Feature: As an associate, I want to see a waiting symbol or spinner,
  so that I know when the system is processing.

  Scenario: An associate adds an item and sees an waiting symbol (loading spinner) to indicate the system is processing.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see "waiting spinner" displayed on center of screen
    And I should be able to perform other actions while spinner is displayed
    And I should see spinner disappear once data has been retrieved / loaded

  Scenario: An associate removes an item and sees an waiting symbol (loading spinner) to indicate the system is processing.
    Given I am on Sales Trans
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click the View Bag button and navigate to the bag
    And I remove an item from the Checkout bag
    Then I can see "waiting spinner" displayed on center of screen
    And I should be able to perform other actions while spinner is displayed
    And I should see spinner disappear once data has been retrieved / loaded
