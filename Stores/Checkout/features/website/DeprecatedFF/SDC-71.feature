# Author: Stores Domain Checkout Team
# Story: SDC-71 - Checkout :: Add item to bag from Consuming Application
# Date Created: 10/07/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-71
Feature: As an associate, I want to add an item to a bag from another application, 
so that I can help my customers purchase items.

  Scenario: An associate wants to add an item to a bag from a consuming application.
    Given I am on Sales Trans
   	When I add an item to the Checkout bag
   	Then I can see the added to bag toast message
	And the toast message fades away after 2 seconds
   	When I click on the bag icon
   	Then I see the item added to the bag
