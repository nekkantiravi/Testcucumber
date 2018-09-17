    # Author: Dorin - Stores Domain Checkout Team
            # Story: SDU-762- Checkout :: Flag Item as Gift Receipt
            # Date Created: 9/21/2017

    @domain_stores @project_checkout @release_17 @story_SDU-762 @wip
    Feature: As a customer, I'd like the option to print a Gift Receipt,
      so that I can make sure my gift recipient can return the item if needed.

      @Macy's @Send
      Scenario: Macy's Send - An associate adds and removes the gift option to a product
        Given I am on "Macy's Sales Trans"
        When I add 1 items to bag
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        Then I should be able to see subtotal for the added items
        When I swipe the item from right to left
        And I select More menu option
        Then The "Add Gift Receipt" option is not available
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page

      @Macy's @Take
      Scenario: Macy's Take - An associate adds and removes the gift option to a product
        Given I am on "Macy's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        Then I should be able to see subtotal for the added items
        When I swipe the item from right to left
        Then I select More menu option
        When I select "Add Gift Receipt" from the More menu options
        Then The gift indicator is visible in the product area
        When I swipe the item from right to left
        Then I select More menu option
        When I select "Remove Gift Receipt" from the More menu options
        Then The gift indicator is not visible in the product area
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page

      @Bloomingdale's @Send
      Scenario: Bloomingdale's Send - An associate adds and removes the gift option to a product
        Given I am on "Bloomingdale's Sales Trans"
        When I add 1 items to bag
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        Then I should be able to see subtotal for the added items
        When I swipe the item from right to left
        And I select More menu option
        Then The "Add Gift Receipt" option is not available
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page

      @Bloomingdale's @Take
      Scenario: Bloomingdale's Take - An associate adds and removes the gift option to a product
        Given I am on "Bloomingdale's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        Then I should be able to see subtotal for the added items
        When I swipe the item from right to left
        Then I select More menu option
        When I select "Add Gift Receipt" from the More menu options
        Then The gift indicator is visible in the product area
        When I swipe the item from right to left
        Then I select More menu option
        When I select "Remove Gift Receipt" from the More menu options
        Then The gift indicator is not visible in the product area
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page