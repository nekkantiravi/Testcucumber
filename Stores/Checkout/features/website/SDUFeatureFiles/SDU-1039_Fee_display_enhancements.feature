        #Author: Dorin - Stores Domain Checkout Team
        # Story: SDU-1039 - Checkout :: Fee display enhancements
        #Date Created: 09/20/2017
        #Date Signed Off:

        @domain_stores @project_checkout @release_17 @story_SDC-1039
        Feature: As a customer, I want the fees I incur during a transaction to display in a clear and meaningful way,
          so that I am easily informed of what I am being charged for and why.


          @Macy's @Take
          Scenario: Macy's Take sale - Taxes are viewable in bag, tendering and post tendering
            Given I am on "Macy's Sales Trans"
            When I checkout an item for a take sale
            Then I can see the mock tendering screen
            And I can see the product fees
            When I swipe the "Macy's Prop" at the tendering screen
            Then I can see the signature view
            When I input my signature
            And I press the Confirm signature button
            Then I can see the postTender screen
            And I can see the product fees
            When I click posttender print button
            Then I am on "Add Product" page

          @Macy's @Send
          Scenario: Macy's Send sale - Taxes are not displayed in bag, tendering and post tendering
            Given I am on "Macy's Sales Trans"
            When I checkout an item for a send sale
            Then I can see the mock tendering screen
            And I do not see the product fees
            When I swipe the "Macy's Prop" at the tendering screen
            Then I can see the signature view
            When I input my signature
            And I press the Confirm signature button
            Then I can see the postTender screen
            And I do not see the product fees
            When I click posttender print button
            Then I am on "Add Product" page

          @Bloomingdale's @Take
          Scenario: Bloomingdale's Take sale - Taxes are viewable in bag, tendering and post tendering
            Given I am on "Bloomingdale's Sales Trans"
            When I checkout an item for a take sale
            Then I can see the mock tendering screen
            And I do not see the product fees
            When I swipe the "Bloomingdale's Prop" at the tendering screen
            Then I can see the signature view
            When I input my signature
            And I press the Confirm signature button
            Then I can see the postTender screen
            And I do not see the product fees
            When I click posttender print button
            Then I am on "Add Product" page

          @Bloomingdale's @Send
          Scenario: Bloomingdale's Send sale - Taxes are not displayed in bag, tendering and post tendering
            Given I am on "Bloomingdale's Sales Trans"
            When I add 2 to quantity
            And I add "20714001940" item to the Checkout bag
            Then I can see the added to bag toast message
            And the toast message fades away after 2 seconds
            When I click on the bag icon
            Then I do not see the product fees
            When I press the checkout button
            Then I see the Shipping Method Overlay
            And I can see the Standard option selected
            When I click on Next Step button
            Then I see the Shipping information Overlay
            And I input the Shipping Information
            And I click on Next Step button
            Then I see the same as Shipping prompt
            When I click on Next Step button
            Then I see the Order Review Overlay
            When I tap on the Complete button
            Then I can see the mock tendering screen
            And I do not see the product fees
            When I swipe the "Bloomingdale's Prop" at the tendering screen
            Then I can see the signature view
            When I input my signature
            And I press the Confirm signature button
            Then I can see the postTender screen
            And I do not see the product fees
            When I click posttender print button
            Then I am on "Add Product" page