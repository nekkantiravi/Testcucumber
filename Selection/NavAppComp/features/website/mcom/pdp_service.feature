Feature: Test for pdp-service

  @sanity1 @regression @domain_selection @project_MCOM
  Scenario: Verify Review Count and Review Title are returned as expected for a valid product
    Given I visit the web site as a guest user
    And I search for "kitchenaid mixers"
    And I navigate to PDP with random member product
    Then I verify common product info
    Then I verify Buy Online Pickup In Store


  @sanity2 @regression @domain_selection @project_MCOM
  Scenario: Add to Bag/Add to List Necklace product
    Given I visit the web site as a guest user
    And I search for "Pendant Necklace"
    And I navigate to PDP with random member product
    Then I verify common product info
    Then I verify price is present
    Then I verify Add to List functionality
    Then I verify Add to Bag functionality

  @sanity3 @regression
  Scenario: Givenchy Necklace, Rose Gold-Tone Crystal Fireball Pendant Necklace product with registered user (792565)
    Given I visit the web site as a registered user
    And I look at Pendant Necklace product
    Then I verify common product info
    Then I verify Add to List functionality with registered user
    Then I verify Add to Bag functionality with registered user

  @sanity4 @regression @domain_selection @project_MCOM
  Scenario Outline: Calvin Klein Open-Back Halter Gown Colorway product (2643306)
    Given I visit the web site as a guest user
    And I look at colorway Dress product
    Then I verify price for colorway swatches "<Color>"
    Then I verify original price "<original_price>"
    Examples:
    |Color            |    original_price   |
    |Black          |    $199.00         |
  #  | Eggplant            | $149.00            |
  #  |Blush            | $149.00            |
   # |French Coral     | $149.00            |
  #  |Midnight         | $149.00            |
  #  |Mint             | $149.00            |
  #  |Dusty Rose       | $149.00            |
   # |Lilac            | $149.00            |
  #  |Silver/Grey      | $149.00            |
  #  |Doe              |  $149.00          |

  @sanity5 @regression @domain_selection @project_MCOM
  Scenario: Verify Size section of dress product
    Given I visit the web site as a guest user
    And I search for "dress"
    And I navigate to PDP with random member product
    Then I verify common product info
    Then I verify sizes section
    Then I verify email a friend icon

  @regression @domain_selection @project_MCOM
  Scenario: Verify Size section of dress product
    Given I visit the web site as a guest user
    And I search for "dress"
    And I navigate to PDP with random member product
    Then I verify common product info
    Then I verify select size message

  @sanity6 @regression @domain_selection @project_MCOM
  Scenario: Qty verified&Bag Elements passing correctly:qty,descr,color,price,totalPrice
    Given I visit the web site as a guest user
    And I search for "michael kors bags"
    And I navigate to PDP with random member product
    Then I verify Quantity drop down box
    When I select random value from the Quantity list
    Then I verify product Quantity gets updated
    Then I verify Bag Elements were passed in correctly

  @sanity7 @regression
  Scenario: Logged In: Qty verified&Bag Elements passing correctly:qty,descr,color,price,totalPrice (2380773)
    Given I visit the web site as a registered user
    #And I look at iPhone6 Wristlet product
    And I search for "michael kors bags"
    And I navigate to PDP with random member product
    Then I verify Quantity drop down box
    When I select random value from the Quantity list
    Then I verify product Quantity gets updated
    Then I verify Bag Elements were passed in correctly

  @sanity8 @regression
  Scenario: Verify add to List with registered user
    Given I visit the web site as a registered user
    And I search for "fry pan"
    And I navigate to PDP with random member product
    Then I verify Quantity drop down box
    When I select random value from the Quantity list
    Then I verify product Quantity gets updated
    Then I verify List functionality with registered user

  @sanity9 @regression @domain_selection @project_MCOM
  Scenario: Verify Product Details and Shipping & Returns section is available as expected for a valid product
    Given I visit the web site as a guest user
    And I search for "michael kors bags"
    And I navigate to PDP with random member product
    Then I should see Product Details and Shipping & Returns sections
    And The Product Details and shipping returns section should be in default state
    When I click on Product Details and shipping returns headers
    Then Product details and shipping returns section should change its default state

  @sanity @regression
  Scenario: Verify add to bag Electronic Gift Card Product (666269)
    Given I visit the web site as a guest user
    And I look at Electronic Gift Card Product
    Then I verify Add to Bag functionality for Electronic Gift Card Product
    #Then I verify quickMyBagLink contains Electronic Gift Card
    Then I verify email sample link
    Then I verify pdp tab area
    Then I verify shipping link pdp tab area
    Then I verify return link pdp tab area

  @sanity10 @regression @domain_selection @project_MCOM
  Scenario: Verify big ticket product (use random product from array)
    Given I visit the web site as a guest user
    And I look at Mattress product with big ticket function
    Then I verify common product info
    Then I verify click to call functionality
    #Then I verify Get alerts for this item  #disabled experiment

  @sanity @wip
  Scenario: Verify product with gift with purchase attribute
    Given I look at product with gift with purchase
    Then I verify common product info
    Then I verify gift with purchase text and links

  @sanity11 @regression @domain_selection @project_MCOM
  Scenario: verify pinterest link and functionality
    Given I visit the web site as a guest user
    And I search for "Pendant Necklace"
    And I navigate to PDP with random member product
    Then I verify pinterest popup url
    #Then I should see pick a board pop up with product description

  @sanity @working
  Scenario: Verify product with AVAILABILITY: backorder
    Given I look at product with availability backorder
    Then I verify common product info
    Then I verify backorder status appears on the page

  @sanity @wip
  Scenario: I verify QandA for iPhone6 Wristlet product  (2380773)
    Given I look at iPhone6 Wristlet product
    Then I verify QandA elements for iPhone6 Wristlet product

  @bat1 @wip
  Scenario: load page from mock json Givenchy Necklace, Rose Gold-Tone Crystal Fireball Pendant Necklace product (792565)
    Given I look at Pendant Necklace product mock
    Then I verify common product info

  @bat @wip
  Scenario: load page from mock json for iPhone6 Wristlet product (2380773)
    Given I look at iPhone6 Wristlet product mock
    Then I verify common product info

  @sanity12 @regression @domain_selection @project_MCOM
  Scenario: Verify quickbag showsup 0 items in your bag from big ticket product PDP Page as a guest user
    Given I visit the web site as a guest user
    And I look at Mattress product with big ticket function
    Then I verify common product info
    Then I should see "0 items in your bag. Shop great deals now!" on quick bag overlay

  @navappcomp_master @regression @domain_selection @project_MCOM
  Scenario: Verify quickbag showsup from master product PDP Page as a guest user
    Given I visit the web site as a guest user
    And I look at Pendant Necklace product
    When I click on add to bag button
    And I search for "hotel collections"
    And I navigate to PDP of the first master product
    Then I verify common master product info
    Then I should see Pendant Necklace product description hovering quick bag header

  @sanity13 @regression @domain_selection @project_MCOM
  Scenario: Verify quickbag showsup from big ticket product PDP Page (use random product from array) as a guest user
    Given I visit the web site as a guest user
    And I look at Pendant Necklace product
    When I click on add to bag button
    And I look at Mattress product with big ticket function
    Then I verify common product info
    Then I should see Pendant Necklace product description hovering quick bag header

  @sanity14 @regression
  Scenario: Verify products details in PDP are displayed based on ISHIP
    Given I visit the web site as a guest user
    And I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I search for "dress"
    And I navigate to PDP with random member product
    Then I should see price with CAD Currency

  @regression @domain_selection @project_MCOM
  Scenario: CHANEL COCO NOIR Solid Parfum product (1669016)
    Given I look at chanel product
    Then I verify common product info for CHANEL product
    Then I verify Add to List functionality for chanel product with "guest" user
    Then I verify Add to Bag functionality for chanel product
    Then I verify Add to Registry functionality for chanel product with "guest" user

  @sanity16 @regression @domain_selection @project_MCOM
  Scenario: Verify COACH product as a guest user (319737)
    Given I visit the web site as a guest user
    And I look at COACH Willow Floral Mercer product
    Then I verify common product info
    Then I verify price is present
    Then I verify Add to List functionality
    Then I verify Add to Bag functionality


  @sanity17 @regression
  Scenario: Verify COACH product as a registered user (319737)
    Given I visit the web site as a registered user
    And I look at COACH Willow Floral Mercer product
    Then I verify common product info
    Then I verify Add to List functionality with registered user
    Then I verify Add to Bag functionality with registered user

  @regression
  Scenario: CHANEL COCO NOIR Solid Parfum product registered user (1669016)
    Given I visit the web site as a registered user
    Given I look at chanel product
    Then I verify common product info for CHANEL product
    Then I verify Add to List functionality for chanel product with "registred" user
    Then I verify Add to Bag functionality for chanel product
    Then I verify Add to Registry functionality for chanel product with "registred" user

  @navappcomp_master @regression @domain_selection @project_MCOM
  Scenario: Verify quickbag showsup from master product PDP Page as a regestered user
    Given I visit the web site as a guest user
    And I look at Pendant Necklace product
    When I click on add to bag button
    And I search for "hotel collections"
    And I navigate to PDP of the first master product
    Then I verify common master product info
    Then I should see Pendant Necklace product description hovering quick bag header

  @regression @domain_selection @project_MCOM
  Scenario: Search for product
    Given I visit the web site as a guest user
    And I search for "dress"
    And I navigate to PDP with random member product
    And I search for "hotel collections"
    Then I verify search results "hotel collections" are displayed

  @navappcomp_master @regression @domain_selection @project_MCOM
  Scenario: Verify quickbag showsup 0 items in your bag from master product PDP Page as a guest user
    Given I visit the web site as a guest user
    And I search for "hotel collections"
    And I navigate to PDP of the first master product
    Then I verify common master product info
    Then I should see "0 items in your bag. Shop great deals now!" on quick bag overlay

  @regression
  Scenario: Verify CAD Colorway Price in PDP are displayed based on ISHIP
    Given I visit the web site as a guest user
    And I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I navigate to colorway product PDP page
    Then I should see colorway price with CAD Currency

  @regression @domain_selection @project_MCOM
  Scenario: Verify add to registry are not available on ISHIP PDP
    Given I visit the web site as a guest user
    And I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I search for "dress"
    And I navigate to PDP with random member product
    Then I should not see add to registy button

  @regression @domain_selection @project_MCOM
  Scenario: Verify add to list are not available on ISHIP PDP
    Given I visit the web site as a guest user
    And I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I search for "dress"
    And I navigate to PDP with random member product
    Then I should not see add to list button

  @regression
  Scenario: Verify add to list are not available on ISHIP PDP
    Given I visit the web site as a guest user
    And I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I search for "dress"
    And I navigate to PDP with random member product
    Then I should not see egift link

  @regression @domain_selection @project_MCOM
  Scenario: Verify add to and and quick bag ISHIP PDP
    Given I visit the web site as a guest user
    And I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I search for "dress"
    And I navigate to PDP with random member product
    #And I click on add to bag button
    And I verify Add to Bag functionality
    And I search for "jeans"
    And I navigate to PDP with random member product
    Then I should see CAD price on quick bag overlay

  @regression @domain_selection @project_MCOM
  Scenario: Verify Vertical pros price and availability text of ISHIP PDP
    Given I visit the web site as a guest user
    And I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I search for "dress"
    And I navigate to PDP with random member product
    Then I should not see availability text
    #And I should see pros price with CAD price info

  @regression @domain_selection @project_MCOM
  Scenario: Verify Special offers link and special offer details on ISHIP PDP
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I navigate to special offer product PDP page
    And I select special offer details link
    Then I should see special offer section expands
