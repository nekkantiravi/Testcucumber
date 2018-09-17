#Author: Stores Domain Checkout Team
   #Story: SDU-Showcase Checkout ::Showcase
   #Date Created: 8/10/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @showcase
Feature: As an associate I want to showcase my team's work

############################# Macy's Smoke Tests ##############################

  @Macy's @Take
    Scenario: Macy's - Swipe and Remove an Item
      Given I am on "Macy's Sales Trans"
      When I add an item to the Checkout bag
      Then I can see the added to bag toast message
      And the toast message fades away after 2 seconds
      When I click on the bag icon
      Then I should be able to see the bag view
      And I can see the item information on Sales Trans
      When I swipe the item from right to left
      Then I can see the slide out menu
      And I can see the slide out menu options
      When I click the remove button
      Then I can see Checkout empty bag view

    @Macy's @Take
    Scenario: Macy's - Scan an item into the bag/Scan CRL
      Given I am on "Macy's Sales Trans"
      And I click on the bag icon
      Then I can see Checkout empty bag view
      When I scan UPC "91709543745" into the bag
      Then I see the CRL Overlay
      And I can see the Input field and add button were removed
      When I scan "39282726" in to the CRL Overlay
      Then The CRL overlay closes
      Then I can see the added to bag toast message
      And the toast message fades away after 2 seconds
      And I can see the "39282726" CRL in the bag
      Then I can see the item information on Sales Trans
      When I click on Hamburger icon
      Then I see the Cancel Transaction button
      And I click on Cancel Transaction button
      Then I can see the sales trans landing page

  @Macy's @Take
  Scenario: Macy's - Take sale tender with Prop
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click posttender print button
    Then I see the print confirmation toast message
    Then I can see the sales trans landing page


  @Macy's @Send
  Scenario: Macy's Send sale Tender with a prop
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    When I click posttender print button
    Then I see the print confirmation toast message
    Then I can see the sales trans landing page

  @Macy's @Send
  Scenario: Macy's - Suspend
    Given I am on "Macy's Sales Trans"
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see the sales trans landing page
#
#  @Macy's @Send
#  Scenario: Macy's - Verify Datacollect Open and Close
#    Given I am on "Macy's Sales Trans"
#    When I connect to the Database


    ################ Bloomingdale's Smoke Tests ####################


  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Swipe and Remove an Item
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I click the remove button
    Then I can see Checkout empty bag view

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Scan an item into the bag/Scan CRL
    Given I am on "Bloomingdale's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "39282726" in to the CRL Overlay
    Then The CRL overlay closes
    And I can see the "39282726" CRL in the bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    Then I can see the item information on Sales Trans
    When I click on Hamburger icon
    Then I see the Cancel Transaction button
    And I click on Cancel Transaction button
    Then I can see the sales trans landing page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Take sale tender with Prop
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can verify the authorize button was removed
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click posttender print button
    Then I can see the sales trans landing page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's- Send sale Tender with a Prop
    Given I am on "Bloomingdale's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    When I click posttender print button
    Then I can see the sales trans landing page

  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Suspend
    Given I am on "Bloomingdale's Sales Trans"
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see the sales trans landing page

    #
#  @Macy's @Send
#  Scenario: Macy's - Verify Datacollect Open and Close
#    Given I am on "Macy's Sales Trans"
#    When I connect to the Database


    ########### Macy's Integrated APP ################

  @MacysIntegrated
  Scenario: Macy's - Swipe and Remove an Item
    Given I am on "Macy's Integrated Environment"
    When I add "51153819872" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I click the remove button
    Then I can see Checkout empty bag view

  @MacysIntegrated
  Scenario: Macy's - Scan an item into the bag/Scan CRL
    Given I am on "Macy's Integrated Environment"
    When I click the bag menu
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    When I scan "39282726" in to the CRL Overlay
    Then The CRL overlay closes
    And I can see the "39282726" CRL in the bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    Then I can see the item information on Sales Trans
    When I click on Hamburger icon
    Then I see the Cancel Transaction button
    And I click on Cancel Transaction button
    Then I can see the landing page

  @MacysIntegrated
  Scenario: Macy's - Take sale tender with Prop
    Given I am on "Macy's Integrated Environment"
    When I add "51153819872" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I press the checkout button
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click posttender print button
    Then I see the print confirmation toast message
    And I can see the landing page


  @MacysIntegrated
  Scenario: Macy's Send sale Tender with a prop
    Given I am on "Macy's Integrated Environment"
    When I add "51153819872" into PD for a "Send" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I press checkout and fill out shipping information to get to posttender screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    When I click posttender print button
    Then I see the print confirmation toast message
    Then I can see the landing page

  @MacysIntegrated
  Scenario: Macy's Take - An associate adds and removes the gift option to a product
    Given I am on "Macy's Integrated Environment"
    When I add "51153819872" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
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
    Then I can see the landing page

  @MacysIntegrated
  Scenario: Macy's - Take a percentage off on a Take sale
    Given I am on "Macy's Integrated Environment"
    When I add "51153819872" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check that Only one of the three options can be used
    Then I complete percent change and overlay closes
    And Price of the item is updated in bag
    And I should be able to see subtotals information is updated
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the landing page

  @MacysIntegrated
  Scenario: Macy's - Take a dollar off on a Take sale
    Given I am on "Macy's Integrated Environment"
    When I add "51153819872" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check that Only one of the three options can be used
    Then I complete dollar change and overlay closes
    And Price of the item is updated in bag
    And I should be able to see subtotals information is updated
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the landing page


  @MacysIntegrated
  Scenario: Macy's - Change Price Using Price Option
    Given I am on "Macy's Integrated Environment"
    When I add "51153819872" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    When I type a new price value
    Then Complete the price change and check default symbol
    And I see the original price of the item has a strikethrough
    And I see the actual price of the item also displays on Sales Trans
    And Price of the item is updated in bag
    And I should be able to see subtotals information is updated
    And Subtotal reflects the new price of item
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the landing page

  @MacysIntegrated
  Scenario: Macy's - Verify Datacollect Open and Close
    Given I am on "Macy's Integrated Environment"
    When I verify the "Open" record for "Store337IntegrationCID"
    When I verify the "Close" record for "Store337IntegrationCID"




 ############### Bloomingdale's Itegrated APP ################

  @BloomIntegrated
  Scenario: Macy's - Swipe and Remove an Item
    Given I am on "Bloomingdale's Integrated Environment"
    When I add "4003666135358" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I swipe the item from right to left
    Then I can see the slide out menu
    And I can see the slide out menu options
    When I click the remove button
    Then I can see Checkout empty bag view

  @BloomIntegrated
  Scenario: Macy's - Scan an item into the bag/Scan CRL
    Given I am on "Bloomingdale's Integrated Environment"
    When I click the bag menu
    Then I can see Checkout empty bag view
    When I scan UPC "91709455154" into the bag
    Then I see the CRL Overlay
    When I scan "39282726" in to the CRL Overlay
    Then The CRL overlay closes
    And I can see the "39282726" CRL in the bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    Then I can see the item information on Sales Trans
    When I click on Hamburger icon
    Then I see the Cancel Transaction button
    And I click on Cancel Transaction button
    Then I can see the landing page

  @BloomIntegrated
  Scenario: Macy's - Take sale tender with Prop
    Given I am on "Bloomingdale's Integrated Environment"
    When I add "4003666135358" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I press the checkout button
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    When I click posttender print button
    Then I see the print confirmation toast message
    Then I can see the landing page

  @BloomIntegrated
  Scenario: Macy's Send sale Tender with a prop
    Given I am on "Bloomingdale's Integrated Environment"
    When I add "91709516701" into PD for a "Send" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I press checkout and fill out shipping information to get to posttender screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    When I click posttender print button
    Then I see the print confirmation toast message
    Then I can see the landing page

  @BloomIntegrated
  Scenario: Macy's Take - An associate adds and removes the gift option to a product
    Given I am on "Bloomingdale's Integrated Environment"
    When I add "4003666135358" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
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
    Then I can see the landing page

  @BloomIntegrated
  Scenario: Macy's - Take a percentage off on a Take sale
    Given I am on "Bloomingdale's Integrated Environment"
    When I add "4003666135358" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check that Only one of the three options can be used
    Then I complete percent change and overlay closes
    And Price of the item is updated in bag
    And I should be able to see subtotals information is updated
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the landing page

  @BloomIntegrated
  Scenario: Macy's - Take a dollar off on a Take sale
    Given I am on "Bloomingdale's Integrated Environment"
    When I add "4003666135358" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    And Check that Only one of the three options can be used
    Then I complete dollar change and overlay closes
    And Price of the item is updated in bag
    And I should be able to see subtotals information is updated
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the landing page

  @BloomIntegrated
  Scenario: Macy's - Change Price Using Price Option
    Given I am on "Bloomingdale's Integrated Environment"
    When I add "4003666135358" into PD for a "Take" sale
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag action button
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    When I swipe the item from right to left
    Then I select More menu option
    When I select "Change Price" from the More menu options
    Then The change price overlay displays properly
    When I type a new price value
    Then Complete the price change and check default symbol
    And I see the original price of the item has a strikethrough
    And I see the actual price of the item also displays on Sales Trans
    And Price of the item is updated in bag
    And I should be able to see subtotals information is updated
    And Subtotal reflects the new price of item
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I can see the landing page


    #### Implement the changes

  @BloomIntegrated
  Scenario: Macy's - Verify Datacollect Open and Close
    Given I am on "Macy's Integrated Environment"
    When I verify the "Open" record for "Store001IntegrationCID"
    When I verify the "Close" record for "Store001IntegrationCID"





################   Macy's Desktop Scenarios #################

  @MacysDesktop
  Scenario: Macy's - Swipe and Remove an Item
    Given I am on "Macy's Desktop"
    When I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I should be able to see the bag view
    And I can see the item information on Sales Trans
    And I can see the slide out menu options
    When I click the remove button
    Then I can see Checkout empty bag view

  @MacysDesktop
  Scenario: Macy's - Scan an item into the bag/Scan CRL
    Given I am on "Macy's Desktop"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "39282726" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    And I can see the "39282726" CRL in the bag
    Then I can see the item information on Sales Trans
    When I click on Hamburger icon
    Then I see the Cancel Transaction button
    And I click on Cancel Transaction button
    Then I can see Checkout empty bag view

  @MacysDesktop
  Scenario: Macy's - Take sale tender with Prop Under the $30 Threshold
    Given I am on "Macy's Desktop"
    When I select Take option
    And I add an item to the Checkout bag that costs less than "$30 for Macy's"
    And I close the CRL Overlay
    And I click on the bag icon
    Then I can see the checkout button
    When I press the checkout button
    And I add "0" bags
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I see the authorization spinner
    When The Authorization spinner closes
    Then I can see the postTender screen
    And I see the print confirmation toast message
    Then I can see Checkout empty bag view


  @MacysDesktop
  Scenario: Macy's Send sale Tender with a prop Under the $30 Threshold
    Given I am on "Macy's Desktop"
    And I add an item to the Checkout bag that costs less than "$30 for Macy's"
    And I click on the bag icon
    Then I can see the checkout button
    When I press the checkout button
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I can see the postTender screen
    And I see the print confirmation toast message
    Then I can see Checkout empty bag view

  @MacysDesktop
  Scenario: Macy's - Suspend
    Given I am on "Macy's Desktop"
    And I add an item to the Checkout bag
    Then I can see the added to bag toast message
    And the toast message fades away after 2 seconds
    When I click on the bag icon
    Then I can view the suspend button
    When I press the suspend button
    Then I should see the customer name overlay
    When I input the customers name
    And I click customer name overlay continue button
    Then I see the suspended bag confirmation overlay
    When I press OK on the suspended confirmation overlay
    Then I can see Checkout empty bag view

#  @MacysDesktop
#  Scenario: Macy's - Verify Datacollect Open and Close
#    Given I am on "Macy's Desktop"
#    When I connect to the Database


