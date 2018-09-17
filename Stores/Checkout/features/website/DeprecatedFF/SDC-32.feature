# Author: Stores Domain Checkout Team
# Story: SDC-32- Add CRL to Item 
# Date Created: 03/08/2017
# Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDC-32
Feature:  As an associate, I want to attach a CRL to an item, so that my customer can easily 
return that item, if she chooses.

  Scenario: On Product Discovery I add an item to the bag then input the CRL.
    Given I am on Sales Trans
    When I select Take option
   	And I add an item to the Checkout bag
   	Then I see the CRL Overlay
    When I scan "123456789" in to the CRL Overlay
  	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
  	Then I can see the CRL was added to the item in the bag
      # ** Need to update when we have a Class/ID for CRL
    And I swipe the item from right to left
  	When I remove an item from the Checkout bag
    Then I can see Checkout empty bag view

 Scenario: On Product Disovery Skip CRL entry.
   Given I am on Sales Trans
    When I select Take option
   And I add an item to the Checkout bag
   Then I see the CRL Overlay
   When I close the CRL Overlay
   Then I can see the added to bag toast message
   And the toast message fades away after 2 seconds
   When I click on the bag icon
   Then I can see the CRL was NOT added to the item in the bag
     # ** Need to update when we have a Class/ID for CRL
   And I swipe the item from right to left
   When I remove an item from the Checkout bag
   Then I can see Checkout empty bag view

  Scenario: Invalid CRL from Product Discovery in a take sale
    Given I am on Sales Trans
     When I select Take option
   	And I add an item to the Checkout bag
   	Then I see the CRL Overlay
    When I scan "invalid213" in to the CRL Overlay
   	Then I can see the Invalid CRL error message
   	When I close the CRL Overlay 
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
 	When I click on the bag icon
    And I swipe the item from right to left
    And I remove an item from the Checkout bag
    Then I can see Checkout empty bag view
   
  
  Scenario: Duplicate CRL from Product Discovery in a take sale
    Given I am on Sales Trans
      When I select Take option
    And I add an item to the Checkout bag
   	Then I see the CRL Overlay
    When I scan "123456789" in to the CRL Overlay
   	Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
   	When I add a second item to the Checkout bag
   	Then I see the CRL Overlay
    When I scan "123456789" in to the CRL Overlay
   	Then I see the duplicate CRL error message
   	When I Clear the field
   	And I scan "987654321" in to the CRL Overlay
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
   	Then I can see both CRLs in the bag view
   # ** Need to update when we have a Class/ID for CRL

 # Scenario: Place Order CRL prompting does not occur
  #  Given I am on Sales Trans
  # 	When I add an item to the Checkout bag that is a Place Order
  # 	Then I do not see the CRL Overlay
  # 	And  I can see the added to bag toast message
  #     And the toast message fades away after 2 seconds
  # 	When I click the View Bag button and navigate to the bag
  # 	Then I can see the item information
  # 	And do not see a CRL

  ################This section is manual scenarios scanning into the bag ###########


  #Scenario: On the Bag screen I add an item to the bag then scan the CRL.
    #Given I am on the bag screen
   	#When I add an item to the Checkout bag for a take sale
   	#Then I see the CRL Overlay
   	#When I scan a valid CRL
   	#Then I can see the item added to the bag
   	#And I can see the CRL was added to the item in the bag

  #Scenario: On Product Disovery Skip CRL entry, Checkout & enter CRL
     #Given I am on the bag screen
   	 #When I add an item to the Checkout bag for a take sale
   	 #Then I see the CRL Overlay
   	 #When I close the CRl Overlay
   	 #Then I can see the item added to the bag
   	 #And I can see the CRL was NOT added to the item in the bag


  #Scenario: Invalid CRL from Product Discovery in a take sale
    #Given I am on the bag screen
   	#When I add an item to the Checkout bag for a take sale
   	#Then I see the CRL Overlay
   	#When I scan an invalid CRL
   	#Then I can see the Invalid CRL error message
   	#When I close the CRL Overlay
   	#Then I am on the bag screen


  #Scenario: Duplicate CRL from Product Discovery in a take sale
    #Given I am on the bag screen
    #When I add an item to the Checkout bag for a take sale
   	#Then I see the CRL Overlay
   	#When I scan a valid CRL
   	#Then I can see the added to bag toast message
    #And the toast message fades away after 2 seconds
   	#When I add an item to the Checkout bag for a take sale
   	#Then I see the CRL Overlay
   	#When I scan a valid CRL
   	#Then I see the duplicate CRL error message
   	#When I Clear the field
   	#And  I scan a different CRL
   	#Then I can see 2 CRL numbers within the bag

  #Scenario: Place Order CRL prompting does not occur
    #Given I am on the bag screen
   	#When I add an item to the Checkout bag that is a Place Order
   	#Then I do not see the CRL Overlay
   	#And I can see the item information
   	#And do not see a CRL
