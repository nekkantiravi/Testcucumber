# Author: Stores Domain Checkout Team
# Story: SDC-29 - Checkout :: Messages display in under 1 second
# Date Created: 12/01/2016
# Date Signed Off:

@manual @domain_stores @project_checkout @release_17 @story_SDC-29
Feature: As an associate, I want messages to display in under one second, 
so that I can help make customers make purchases as quickly and efficiently as possible.

  Scenario: An associate suspends a bag and is returned to the home screen.
    Given I am on Sales Trans
   	And I add an item to the Checkout bag
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   	And I can verify the overlay message displays within one second
   	When I Press the close button 
   	Then I can verify the overlay closes within 1 second
   	When I access Checkout bag screen 
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    And I can verify the overlay message displays within one second
    When I input the customers name
    And I click customer name overlay suspend button
    Then I can verify the overlay closes within one second
    And I can verify the overlay message displays within one second
    When I press OK on the suspended confirmation overlay
    Then I can verify the overlay closes within one second
    Then I am returned to New POS Sample webapp
