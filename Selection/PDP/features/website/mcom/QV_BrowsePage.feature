#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Aug.18,2017
#---------------------------------------------------

Feature: QuickView_BrowsePage Validation & Verification


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on Browse page Site mode
    Given I visit the web site as a guest user
    When I hover over "men" FOB tab on PDP
    And I select "Jeans" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page site mode
    Then I verify the basic elements on QuickView overlay in site mode


  @use_regression @domain_selection @mcom_selection_qv @wip
  Scenario: Verify basic elements of QuickView master product on Browse page Site mode
    Given I visit the web site as a guest user
    When I hover over "BED & BATH" FOB tab on PDP
    And I select "All Bath" link from FOB on PDP
    And I hover over a random master product image and click on QuickView button on "Browse" page site mode
    Then I verify the basic elements on QuickView overlay in site mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on Browse page iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I hover over "men" FOB tab on PDP
    And I select "Jeans" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page iship mode
    Then I verify the basic elements on QuickView overlay in iship mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on Browse page Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    When I hover over "Dining" FOB tab on PDP
    And I select "Barware" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page registry mode
    Then I verify the basic elements on QuickView overlay in registry mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on Browse page for E-Gift Cards
    Given I visit the web site as a guest user
    When I navigate to "GiftCard Page" in "site" mode
    And I select the "All Occasions" under "E-Gift Cards" subCategory on Gift Card page
    And I hover over a random product image and click on QuickView button on "Browse" page site mode
    Then I verify the basic elements on QuickView overlay in site mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify zoomer, altImages & all elements on QuickView overlay on Browse page Site mode
    Given I visit the web site as a guest user
    When I hover over "men" FOB tab on PDP
    And I select "Jeans" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page site mode
    Then I verify the all elements on QuickView overlay in site mode
    And I verify zoomer and altImages on QuickView overlay in site mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify zoomer, altImages & all elements on QuickView overlay on Browse page iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I hover over "men" FOB tab on PDP
    And I select "Jeans" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page iship mode
    Then I verify the all elements on QuickView overlay in iship mode
    And I verify zoomer and altImages on QuickView overlay in iship mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify zoomer, altImages & all elements on QuickView overlay on Browse page Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I hover over "Dining" FOB tab on PDP
    And I select "Barware" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page registry mode
    Then I verify the all elements on QuickView overlay in registry mode
    And I verify zoomer and altImages on QuickView overlay in registry mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2B functionality on Browse page Site mode
    Given I visit the web site as a guest user
    When I hover over "men" FOB tab on PDP
    And I select "Jeans" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page site mode
    And I select a random color on QuickView overlay in site mode
    And I select a random size on QuickView overlay in site mode
    Then I verify "in stock" message on QuickView overlay in site mode
    And I select a maximum quantity on QuickView overlay in site mode
    And I click "A2B" on QuickView overlay in site mode
    Then I verify "x items added to your bag." message on QuickView overlay in site mode
    And I click "Checkout" on QuickView overlay in site mode
    And I verify basic elements of shopping bag page in "site" mode
    Then I verify that the product is added to shopping bag


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2B functionality on Browse page iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I hover over "men" FOB tab on PDP
    And I select "Jeans" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page iship mode
    And I select a random color on QuickView overlay in iship mode
    And I select a random size on QuickView overlay in iship mode
    Then I verify "in stock" message on QuickView overlay in iship mode
    And I select a maximum quantity on QuickView overlay in iship mode
    And I click "A2B" on QuickView overlay in iship mode
    Then I verify "x items added to your bag." message on QuickView overlay in iship mode
    And I click "Checkout" on QuickView overlay in iship mode
    And I verify basic elements of shopping bag page in "iship" mode
    Then I verify that the product is added to shopping bag


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2B functionality on Browse page Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    When I hover over "Dining" FOB tab on PDP
    And I select "Barware" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page registry mode
#    And I select a random color on QuickView overlay in registry mode
#    And I select a random size on QuickView overlay in registry mode
    Then I verify "in stock" message on QuickView overlay in registry mode
    And I select a maximum quantity on QuickView overlay in registry mode
    And I click "A2B" on QuickView overlay in registry mode
    Then I verify "x items added to your bag." message on QuickView overlay in registry mode
    And I click "Checkout" on QuickView overlay in registry mode
    And I verify basic elements of shopping bag page in "site" mode
    Then I verify that the product is added to shopping bag


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify List signin page from QuickView on Browse page Site mode
    Given I visit the web site as a guest user
    When I hover over "women" FOB tab on PDP
    And I select "Jeans" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page site mode
    And I select a random size on QuickView overlay in site mode
    Then I verify "in stock" message on QuickView overlay in site mode
    And I click "A2L" on QuickView overlay in site mode
    Then I verify "Added to your guest List." message on QuickView overlay in site mode
    And I click "Signin link" on "A2L overlay" on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2L functionality on Browse page Site mode
    Given I visit the web site as a guest user
    When I hover over "women" FOB tab on PDP
    And I select "Jeans" link from FOB on PDP
    And I hover over a random product image and click on QuickView button on "Browse" page site mode
    And I select a random color on QuickView overlay in site mode
    And I select a random size on QuickView overlay in site mode
    Then I verify "in stock" message on QuickView overlay in site mode
    And I click "A2L" on QuickView overlay in site mode
    And I click "List link" on "A2L overlay" on member PDP site mode
    Then I verify navigation to "list" page in site mode
    And I click "product image" on "list" page in site mode
    Then I verify navigating to "member" PDP "site" mode

#Version One: B-88686
# Feature: As a product owner, I would like to ensure that when users add items to their list through QV that they are then able to add those items to their bag.
# Transition: UFT
  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify the Add TO Bag functionality in quick view overlay on domestic mode
    Given I visit the web site as a guest user
    When I navigate to the "Activewear" browse page under "WOMEN"
    And I select a random product in a quickview dialog
    And I click "ADD TO LIST" button on QuickView page
    Then I verify "Added to your guest List." message on QuickView overlay in site mode
    When I click "A2B" on QuickView overlay in site mode
    Then I verify "x items added to your bag." message on QuickView overlay in site mode

#Version One: B-86995
#Feature: As a product owner,I would like to ensure that the reviews we are now enabling on Chanel PDP's, also display
# on Chanel QV
#Author: UFT team
  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify the reviews are displayed in quick view on Chanel browse page
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    And I navigate to "WOMEN'S FRAGRANCE" browse page from cat splash page
    And I select "COCO MADEMOISELLE" facet listed on left nav
    When I select the chanel quick peek of 'member' product
    Then I verify the that QuickView is displayed for chanel product
    And I verify that the customer reviews are displayed in quick peek for chanel product