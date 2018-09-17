#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: May 01,2017
#---------------------------------------------------

Feature: PDP_RegistryMode Validation & Verification


  @use_regression @domain_selection @mcom_pdp_sanity @mcom_selection_pdp
  Scenario: Verify basic components of member PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify basic components of "member" PDP in "registry" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B & Checkout on member PDP Registry mode
    Given I visit the web site as a guest user
    And I select a "member" product and navigate to PDP in "registry" mode
    And I click "A2B" button on "member" PDP "registry" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify the productId being the same as added to bag
    And I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify basic components of master PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify basic components of "master" PDP in "registry" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify add to bag on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate directly to "member" PDP and add a product to my bag in "registry" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify add to bag on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate directly to "master" PDP and add a product to my bag in "registry" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @mcom_pdp_sanity @mcom_selection_pdp
  Scenario: Verify zoomer & alternate images on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I navigate directly to PDP with PID "77589" in registry mode
    Then I verify the zoomer and altimages on member PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify zoomer & alternate images on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "3409057" in registry mode
    Then I verify the zoomer and altimages on master PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify empty bag message on the quickbag flyout on member PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "registry" mode
    And I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify empty bag message on the quickbag flyout on master PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "registry" mode
    And I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify quickbag flyout member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate directly to "member" PDP and add a product to my bag in "registry" mode
    Then I verify quickbag flyout on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify quickbag flyout on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate directly to "master" PDP and add a product to my bag in "registry" mode
    Then I verify quickbag flyout on PDP


  @use_regression @domain_selection @mcom_pdp_sanity @mcom_selection_pdp
  Scenario Outline: Verify QuickBag product image navigation & color&quantity reset on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I navigate directly to PDP with PID "<productId>" in registry mode
    And I select "random" color on member PDP in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "registry" mode
    And I click "ContinueShopping" button on "member" PDP "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I navigate directly to PDP with PID "22804" in registry mode
    And I click the "product image" on quick bag
    Then I verify navigating to "member" PDP "site" mode
    And I verify that color&quantity are reset to default values on member PDP site mode
    Examples:
      |productId|
      |77589    |


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag product image link navigates to PDP from master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate directly to "master" PDP and add a product to my bag in "registry" mode
    And I click the "product image" on quick bag
    Then I verify navigating to "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag checkout functionality on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate directly to "member" PDP and add a product to my bag in "registry" mode
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag checkout functionality on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate directly to "master" PDP and add a product to my bag in "registry" mode
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_pdp_sanity @mcom_selection_pdp
  Scenario Outline: Verify shopping bag product image navigation & color&quantity reset on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I navigate directly to PDP with PID "<productId>" in registry mode
    And I select "random" color on member PDP in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "registry" mode
    And I click "Checkout" button on "member" PDP "registry" mode
    And I click the product "image" link on shopping bag page
    Then I verify navigating to "member" PDP "site" mode
    And I verify that color&quantity are reset to default values on member PDP site mode
    Examples:
      |productId|
      |77589    |


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag remove functionality on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate directly to "member" PDP and add a product to my bag in "registry" mode
    And I verify quickbag count is updating with "1" items in bag
    And I click the "remove" on quick bag
    Then I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag remove functionality on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate directly to "master" PDP and add a product to my bag in "registry" mode
    And I verify quickbag count is updating with "1" items in bag
    And I click the "remove" on quick bag
    Then I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the RVI panel on member PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I navigate directly to PDP with PID "5747" in registry mode
    And I scroll up&down for "RVI" element to load on PDP site mode
    Then I verify the RVI panel on "member" PDP "Registry" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the RVI panel on master PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "master" product to build product history
    And I scroll up&down for "RVI" element to load on PDP site mode
    Then I verify the RVI panel on "master" PDP "Registry" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify social icons on member PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    #    bellow step works for Registry too, as elements are same as member PDP site mode
    Then I verify social icons on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify social icons on master PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "registry" mode
    #    bellow step works for Registry too, as elements are same as master PDP site mode
    Then I verify social icons on master PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify video functionality on member PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "video" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify that "Video" is "loading" on PDP "registry" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the FOB functionality on member PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "Dining" FOB tab on PDP
    And I select "Bar & Wine Accessories" link from FOB on PDP
    Then I verify the "Dining" FOB tab on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the FOB functionality on master PDP Registry mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "Home Decor" FOB tab on PDP
    And I select "Bowls & Vases" link from FOB on PDP
    Then I verify the "Home Decor" FOB tab on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify write review overlay on member PDP Registry mode
    Given I visit the web site as a registered user
    When I select a "member" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click on the write a review button on PDP registry mode
    Then I verify that write review overlay is displayed in registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify write review overlay on master PDP Registry mode
    Given I visit the web site as a registered user
    When I select a "master" product and navigate to PDP in "registry" mode
    And I click on the write a review button on PDP registry mode
    Then I verify that write review overlay is displayed in registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify guest user navigating to signin page for writing product reviews on PDP
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "registry" mode
    And I click on the write a review button on PDP registry mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS vertical recommendation panels on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I select a "member" product for "PROS" and navigate to PDP
    And I resize the dimension on window screen
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the vertical recommendation panel on member PDP
    And I select a random product from vertical recommendation panel on member PDP registry mode
    Then I verify navigation to the corresponding PDP registry mode


  @use_regression @domain_selection @mcom_pdp_sanity @mcom_selection_pdp
  Scenario: Verify PROS vertical recommendation panels on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I select a "master" product for "PROS" and navigate to PDP
    And I resize the dimension on window screen
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the vertical recommendation panel on master PDP
    And I select a random product from vertical recommendation panel on master PDP registry mode
    Then I verify navigation to the corresponding PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS horizontal recommendation panels on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I select a "member" product for "PROS" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the horizontal recommendation panel on member PDP
    And I select a random product from horizontal recommendation panel on member PDP registry mode
    Then I verify navigation to the corresponding PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS horizontal recommendation panels on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I select a "master" product for "PROS" and navigate to PDP
    Then I verify the horizontal recommendation panel on master PDP
    And I select a random product from horizontal recommendation panel on master PDP registry mode
    Then I verify navigation to the corresponding PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Add2Registry button on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I navigate directly to PDP with PID "77589" in registry mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2R" button on "member" PDP "registry" mode
    Then I verify that guest user is navigated to "Registry" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the links under Shipping & Returns tab on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I navigate directly to PDP with PID "22805" in registry mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the links under shipping & returns tab on member PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the links under Shipping & Returns tab on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I navigate directly to PDP with PID "19942" in registry mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Shipping & Returns" in bottom tabs section on master PDP registry mode
    Then I verify the links under shipping & returns tab on master PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Q&A functionality as a guest user on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I navigate directly to PDP with PID "22804" in registry mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on member PDP registry mode
    Then I verify "Q&A section" in bottom tabs section on member PDP registry mode
    And I click "Ask a new question button" in bottom tabs section on member PDP registry mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Q&A functionality as a guest user on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I navigate directly to PDP with PID "19942" in registry mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on master PDP registry mode
    Then I verify "Q&A section" in bottom tabs section on member PDP registry mode
    And I click "Ask a new question button" in bottom tabs section on master PDP registry mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify that Olapic section is available on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I search for "jeans"
    And I select a random member product
    Then I verify that Olapic section is available on member PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify that Olapic section is unavailable on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I select a "master" product and navigate to PDP in "registry" mode
    Then I verify that Olapic section is unavailable on master PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify special offers for Free Shipping on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I navigate directly to PDP with PID "22805" in registry mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify "Free Shipping" offer under SpecialOffers tab on member PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify special offers for any promotions on member PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I search for "jeans"
    And I select a random member product
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify "Any" offer under SpecialOffers tab on member PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify product name is same between Browse page & PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    When I hover over "Dining" FOB tab on PDP
    And I select "Barware" link from FOB on PDP
    And I select a random member product and save its name details on Browse page
    Then I verify that product name details are same on PDP registry mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify product name is same between Search page & PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I search for "Jeans"
    And I select a random member product and save its name details on Search page
    Then I verify that product name details are same on PDP registry mode

#Version One: B-88025
# Feature: As a product owner, I would like to ensure that registrants are able to add items from Hotel Collection Turkish Bath Set to their registry
# Transition: UFT
  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify that the user should be able to add Hotel Collection bath towels to registry
    Given I visit the website as a bvr user in site mode
    And I navigate to registry manager page
    When I navigate directly to PDP with PID "1502641" in registry mode
    And I click "A2R" button on "member" PDP "registry" mode
    Then I should see add to registry dialog on PDP

#Version One: B-90038
#Feature: As a product owner, I would like to ensure that when the customer clicks on the "add to registry" button,
#  the currency symbol is displayed
#Author: UFT team
  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the currency symbol is displayed when the user clicks on add to registry button
    Given I visit the web site as a registry user
    When I navigate directly to PDP with PID "1310" in registry mode
    And I click "A2R" button on "member" PDP "registry" mode
    Then I should see add to registry dialog on PDP
    And I should see currency symbol is displayed on add to registry dialog
    When I navigate back to "home" page
    And I navigate directly to PDP with PID "1310" in site mode
    And I click "A2R" button on "member" PDP "site" mode
    Then I should see add to registry dialog on PDP
    And I should see currency symbol is displayed on add to registry dialog