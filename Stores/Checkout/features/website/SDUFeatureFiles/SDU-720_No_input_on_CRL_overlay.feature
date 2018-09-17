#Author: Stores Domain Checkout Team
   #Story: SDU-720 - Remove Input on CRL Overlay
   #Date Created: 06/28/2017
   #Date Signed Off:


@domain_stores @project_checkout @release_17 @story_SDU-720
Feature:  As a Product Owner, I don't want to allow input for CRL's,
  so that I can create a clean and efficient application.

  @Macy's @Take
  Scenario: Macy's - CRL Input Field and Add button were removed
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Take
  Scenario: Macy's - Scan into CRL Overlay on PDP
    Given I am on "product discovery"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "39282726" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I can see the "39282726" CRL in the bag
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Take
  Scenario: Macy's - Scan Hex into CRL Overlay on PDP
    Given I am on "product discovery"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "0x0C224807006202" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    When I click on the bag icon
    Then I can see the "0524807006202" CRL in the bag
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Take
  Scenario: Macy's - Scan an invalid CRL
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "392" in to the CRL Overlay
    Then  I can see the Invalid CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Take
  Scenario: Macy's - Scan an invalid CRL
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "392789292929" in to the CRL Overlay
    Then  I can see the Invalid CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Take
  Scenario: Macy's - Scan a duplicate CRL
    Given I am on "Macy's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "37839203" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "37839203" in to the CRL Overlay
    Then I see the duplicate CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Take
  Scenario: Macy's - Scan item into the bag and scanning a CRL into the CRL Overlay while in bag view
    Given I am on "Macy's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "39282726" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    And I can see the "39282726" CRL in the bag
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Take
  Scenario: Macy's - Scan item into the bag and scanning a Hex CRL into the CRL Overlay while in bag view
    Given I am on "Macy's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "0x0C224807006202" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    And I can see the "0524807006202" CRL in the bag
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Take
  Scenario: Macy's - Scan an invalid CRL in bag view
    Given I am on "Macy's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "392" in to the CRL Overlay
    Then  I can see the Invalid CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Take
  Scenario: Macy's - Scan an invalid CRL in bag view
    Given I am on "Macy's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "392789292929" in to the CRL Overlay
    Then  I can see the Invalid CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Macy's @Take
  Scenario: Macy's - Scan a duplicate CRL in bag view
    Given I am on "Macy's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "37839203" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "37839203" in to the CRL Overlay
    Then I see the duplicate CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - CRL Input Field and Add button were removed
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Scan an invalid CRL
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "392" in to the CRL Overlay
    Then  I can see the Invalid CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Scan an invalid CRL
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "392789292929" in to the CRL Overlay
    Then  I can see the Invalid CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Scan a duplicate CRL
    Given I am on "Bloomingdale's Sales Trans"
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "37839203" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    When I add an item to the Checkout bag for a Take Sale
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "37839203" in to the CRL Overlay
    Then I see the duplicate CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Scan item into the bag and scanning a CRL into the CRL Overlay while in bag view
    Given I am on "Bloomingdale's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "39282726" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    And I can see the "39282726" CRL in the bag
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Scan item into the bag and scanning a Hex CRL into the CRL Overlay while in bag view
    Given I am on "Bloomingdale's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "0x0C224807006202" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    And I can see the "0524807006202" CRL in the bag
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Scan an invalid CRL in bag view
    Given I am on "Bloomingdale's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "392" in to the CRL Overlay
    Then  I can see the Invalid CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Scan an invalid CRL in bag view
    Given I am on "Bloomingdale's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "392789292929" in to the CRL Overlay
    Then  I can see the Invalid CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Scan a duplicate CRL in bag view
    Given I am on "Bloomingdale's Sales Trans"
    And I click on the bag icon
    Then I can see Checkout empty bag view
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "37839203" in to the CRL Overlay
    Then The CRL overlay closes
    Then I can see the added to bag toast message
    When I scan UPC "91709543745" into the bag
    Then I see the CRL Overlay
    And I can see the Input field and add button were removed
    When I scan "37839203" in to the CRL Overlay
    Then I see the duplicate CRL error message
    And I close the CRL Overlay
    Then I can see the added to bag toast message
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page
