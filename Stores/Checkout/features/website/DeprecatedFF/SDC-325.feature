# Author: Stores Domain Checkout Team
# Story: SDC-325- Tech: Add no image SVG to Vanguard and modify bag to use it
# Date Created: 03/08/2017
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-325
Feature: I want to see the Image Not Found SVG in the bag

  Scenario: An associate wants to add an item to a bag that has no image.
    Given I am on Sales Trans
   	When I add an item to the Checkout bag
#	  And I see the CRL Overlay
#   	And I close the CRL Overlay
   	Then I can see the added to bag toast message
	And the toast message fades away after 2 seconds
   	When I click on the bag icon
   	Then I can see the No Image SVG
   	

