#Author: Stores Domain Checkout Team
   #Story: SDU-340- Checkout :: Open PDP from within bag
   #Date Created: 05/19/2017
   #Date Signed Off:

    @domain_stores @project_checkout @release_17 @story_SDU-340
    Feature: As an associate, I want to open the PDP for a product from within Bag,
    so that I can share additional information about a product with my Customer.

    @Macy's @send
    Scenario: Macy's - associate views PDP card within the bag and closes the PDP Card
      Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag
      Then I can see the added to bag toast message
      And the toast message fades away after 2 seconds
      When I click on the bag icon
      Then I should be able to see the bag view
      When I click the item image
      Then I see the redirection to PDP
      When I navigate back to the bag
      And I call Cancel
      Then I can see the sales trans landing page


    @Bloomingdale's @send
    Scenario: Bloomingdale's - associate views PDP card within the bag and closes the PDP Card
      Given I am on "Bloomingdale's Sales Trans"
      When I add an item to the Checkout bag
      Then I can see the added to bag toast message
      And the toast message fades away after 2 seconds
      When I click on the bag icon
      Then I should be able to see the bag view
      When I click the item image
      Then I see the redirection to PDP
      When I navigate back to the bag
      And I call Cancel
      Then I can see the sales trans landing page








