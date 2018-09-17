#Author: Stores Domain Checkout Team
   #Story: SDU-200 - Block Recalled Merchandise from being Added to Bag
   #Date Created: 07/6/2017
   #Date Signed Off:

    @domain_stores @project_checkout @release_17 @story_SDU-200
Feature:  As an associate, I want to know if merchandise is recalled, so that I can't
  sell it to my customer and I know to take the proper steps to remove it from the
  selling floor.

  @Macy's @Take
  Scenario: Macy's - Add a Recalled Item in a Take Sale
    Given I am on "Macy's Sales Trans"
    When I select Take option
    And I add an item to the Checkout bag that "is recall item"
    Then I can see the Recall error
    And I close the Recall error
    When I click on the bag icon
    Then I can see Checkout empty bag view

  @Macy's @Send
  Scenario: Macy's - Add a Recalled Item directly in the bag
    Given I am on "Macy's Sales Trans"
    When I click on the bag icon
    Then I scan UPC "0768549130595" into the bag
    And I can see the Recall error
    When I close the Recall error
    Then I can see Checkout empty bag view

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Add a Recalled Item in a Take Sale
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    And I add an item to the Checkout bag that "is recall item"
    Then I can see the Recall error
    And I close the Recall error
    When I click on the bag icon
    Then I can see Checkout empty bag view


  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Add a Recalled Item directly in the bag
    Given I am on "Bloomingdale's Sales Trans"
    When I click on the bag icon
    Then I scan UPC "0768549130595" into the bag
    And I can see the Recall error
    When I close the Recall error
    Then I can see Checkout empty bag view
