# Author: Stores Domain Checkout Team
# Story: SDC-325- Tech: Add no image SVG to Vanguard and modify bag to use it
# Date Created: 03/08/2017
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-325
Feature: I want to see the Image Not Found SVG in the bag

  Scenario: An associate wants to add an item to a bag that has no image.
	  Given I am on "product discovery"
	  When I search for "2100973" in home page
	  Then I can see the product card
	  Then I press the Add to bag
	  Then I can see the added to bag confirmation layer
	  When I click the View Bag button and navigate to the bag
	  Then I can see the No Image SVG