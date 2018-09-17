# Author: Stores Domain Checkout Team
# Story: SDC-99 - Item Details in Bag view
# Date Created: 10/21/2016
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-99
Feature: As an associate, I want to view items and details in a bag, 
so that I can validate my customers purchase contents.

  Scenario: An associate wants to add an item to a bag and can view item details.
	  Given I am on "product discovery"
	  When I search for "672275307384" in home page
	  Then I can see the product card
	  And I press the Add to bag
	  Then I can see the added to bag confirmation layer
	  When I click the View Bag button and navigate to the bag
	  Then I can see the item information