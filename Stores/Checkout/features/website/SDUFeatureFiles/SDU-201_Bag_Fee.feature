    #Author: Dorin - Stores Domain Checkout Team
    #Story: SDU-201 - Checkout :: Bag Fee
    #Date Created: 07/26/2017
    # Date Signed Off:

    @domain_stores @project_checkout @release_17 @story_SDU-201
    Feature: As a business, I want to appropriately charge fees in stores that require them,
      so that I adhere to all laws and regulations set forth by the government..

      @Macy's @Take
      Scenario: Macy's - Take sale - User is redirected to bag if bag fee overlay is closed
        Given I am on "Macy's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        Then I should be able to see the bag view
        When I press the checkout button
        Then I can see the bag fee overlay
        When I close the bag fee overlay
        Then I should be able to see the bag view
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page

      @Macy's @Take
      Scenario: Macy's - Take sale - Subtotal and total calculations in bag view and tender screen take fees into account
        Given I am on "Macy's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I press the checkout button
        Then I can see the bag fee overlay
        When I add "2" bags
        Then I can see the mock tendering screen
        And I can see the Bag Fee
        And I should be able to see totals information is updated on Tendering
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I call Cancel
        Then I can see the sales trans landing page

      @Macy's @Take
      Scenario: Macy's - Take sale - An extra bag is added, the subtotals and totals are updated
        Given I am on "Macy's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I can see the bag fee overlay
        When I add "2" bags
        Then I can see the mock tendering screen
        And I can see the Bag Fee
        And I should be able to see totals information is updated on Tendering
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I press the checkout button
        Then I can see the bag fee overlay
        When I add an extra bag
        Then I can see the mock tendering screen
        And I should be able to see totals information is updated on Tendering
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page

      @Macy's @Take
      Scenario: Macy's - Take sale - A bag is removed, the subtotals and totals are updated
        Given I am on "Macy's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I press the checkout button
        Then I can see the bag fee overlay
        When I add "2" bags
        Then I can see the mock tendering screen
        And I can see the Bag Fee
        And I should be able to see totals information is updated on Tendering
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I press the checkout button
        Then I can see the bag fee overlay
        When I remove a bag
        Then I can see the mock tendering screen
        And I should be able to see totals information is updated on Tendering
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page

      @Macy's @Take
      Scenario Outline: Macy's - Bag text box validations - valid values
        Given I am on "Macy's Sales Trans"
      #When I checkout an item for a take sale
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I can see the bag fee overlay
        When I add "<Bag_Number>" bags
        Then I can see the mock tendering screen
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page


        Examples:
          | Bag_Number |
          | 99         |
          | 0          |

      @Macy's @Take
      Scenario Outline: Macy's - Bag text box validations - invalid values
        Given I am on "Macy's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I can see the bag fee overlay
        When I type "<Bag_Number>" bags
        Then I verify special character error displays with the Invalid "<Bag_Number>"
        And I see the red error rectangle
        When I close the overlay
        And I call Cancel
        Then I can see the sales trans landing page

        Examples:
          | Bag_Number |
          | -          |
          | /          |
          | h          |

      @Macy's @Send
      Scenario: Macy's - Send Sale - User is not prompted to add bags (bags are not required in send sales)
        Given I am on "Macy's Sales Trans"
        When I add 1 items to bag
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I do not see the bag fee overlay
        But I see the Shipping Method Overlay
        When I close the overlay
        And I call Cancel
        Then I can see the sales trans landing page

      @Macy's @Take
      Scenario Outline: Macy's - Bag text box validations - new invalid values
        Given I am on "Macy's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I can see the bag fee overlay
        When I type "<Bag_Number>" bags
        Then I see the correct value displayed "<Displayed_Value>"
        When I click Enter on bag overlay
        Then I can see the mock tendering screen
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page


        Examples: set
          | Bag_Number | Displayed_Value |
          | 01         | 1               |
          | 005        | 5               |
          | 023        | 23              |
          | 100        | 10              |

      @Bloomingdale's @Take
      Scenario: Bloomingdale's Take sale - User is redirected to bag if bag fee overlay is closed
        Given I am on "Bloomingdale's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        Then I should be able to see the bag view
        When I press the checkout button
        Then I can see the bag fee overlay
        When I close the bag fee overlay
        Then I should be able to see the bag view
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page

      @Bloomingdale's @Take
      Scenario: Bloomingdale's Take sale - Subtotal and total calculations in bag view and tender screen take fees into account
        Given I am on "Bloomingdale's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I press the checkout button
        Then I can see the bag fee overlay
        When I add "2" bags
        Then I can see the mock tendering screen
        And I can see the Bag Fee
        And I should be able to see totals information is updated on Tendering
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I call Cancel
        Then I can see the sales trans landing page

      @Bloomingdale's @Take
      Scenario: Bloomingdale's Take sale - An extra bag is added, the subtotals and totals are updated
        Given I am on "Bloomingdale's Sales Trans"
        When I select Take option
        And I add "91709543745" item to the Checkout bag
        And I add "20714001940" item to the Checkout bag
        And I close the CRL Overlay
        #Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I can see the bag fee overlay
        When I add "3" bags
        Then I can see the mock tendering screen
        And I can see the Bag Fee
        And I should be able to see totals information is updated on Tendering
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I press the checkout button
        Then I can see the bag fee overlay
        When I add an extra bag
        Then I can see the mock tendering screen
        And I should be able to see totals information is updated on Tendering
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page

      @Bloomingdale's @Take
      Scenario: Bloomingdale's - Take sale - A bag is removed, the subtotals and totals are updated
        Given I am on "Bloomingdale's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I press the checkout button
        Then I can see the bag fee overlay
        When I add "2" bags
        Then I can see the mock tendering screen
        And I can see the Bag Fee
        And I should be able to see totals information is updated on Tendering
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I press the checkout button
        Then I can see the bag fee overlay
        When I remove a bag
        Then I can see the mock tendering screen
        And I should be able to see totals information is updated on Tendering
        When I click on the bag icon
        Then I should be able to see subtotals information is updated
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page

      @Bloomingdale's @Take
      Scenario Outline: Bloomingdale's - Bag text box validations - valid values
        Given I am on "Bloomingdale's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I can see the bag fee overlay
        When I add "<Bag_Number>" bags
        Then I can see the mock tendering screen
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page


        Examples:
          | Bag_Number |
          | 99         |
          | 0          |

      @Bloomingdale's @Take
      Scenario Outline: Bloomingdale's - Bag text box validations - invalid values
        Given I am on "Bloomingdale's Sales Trans"
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I can see the bag fee overlay
        When I type "<Bag_Number>" bags
        Then I verify special character error displays with the Invalid "<Bag_Number>"
        And I see the red error rectangle
        When I close the overlay
        And I call Cancel
        Then I can see the sales trans landing page

        Examples:
          | Bag_Number |
          | -          |
          | /          |
          | h          |


      @Bloomingdale's @Take
      Scenario Outline: Bloomingdale's - Bag text box validations - new invalid values
        Given I am on "Bloomingdale's Sales Trans"
        When I add 1 items to bag
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I do not see the bag fee overlay
        But I see the Shipping Method Overlay
        When I close the overlay
        And I call Cancel
        Then I can see the sales trans landing page
        When I add an item to the Checkout bag for a Take Sale
        And I close the CRL Overlay
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I can see the bag fee overlay
        When I type "<Bag_Number>" bags
        Then I see the correct value displayed "<Displayed_Value>"
        When I click Enter on bag overlay
        Then I can see the mock tendering screen
        When I click on Hamburger icon
        And I click on Cancel Transaction button
        Then I am on "Add Product" page


        Examples:
          | Bag_Number | Displayed_Value |
          | 01         | 1               |
          | 005        | 5               |
          | 023        | 23              |
          | 100        | 10              |



      @Bloomingdale's @Send
      Scenario: Bloomingdale's - Send Sale - User is not prompted to add bags (bags are not required in send sales)
        Given I am on "Bloomingdale's Sales Trans"
        When I add 1 items to bag
        Then I can see the added to bag toast message
        And the toast message fades away after 2 seconds
        When I click on the bag icon
        And I press the checkout button
        Then I do not see the bag fee overlay
        But I see the Shipping Method Overlay
        When I close the overlay
        And I call Cancel
        Then I can see the sales trans landing page






