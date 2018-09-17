#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Aug.18,2017
#---------------------------------------------------

Feature: QuickView_SearchPage Validation & Verification


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on Search page Site mode
    Given I visit the web site as a guest user
    When I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page site mode
    Then I verify the basic elements on QuickView overlay in site mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on Search page iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page iship mode
    Then I verify the basic elements on QuickView overlay in iship mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on Search page Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page registry mode
    Then I verify the basic elements on QuickView overlay in registry mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify zoomer, altImages & all elements on QuickView overlay on Search page Site mode
    Given I visit the web site as a guest user
    When I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page site mode
    Then I verify the all elements on QuickView overlay in site mode
    And I verify zoomer and altImages on QuickView overlay in site mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify zoomer, altImages & all elements on QuickView overlay on Search page iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page iship mode
    Then I verify the all elements on QuickView overlay in iship mode
    And I verify zoomer and altImages on QuickView overlay in iship mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify zoomer, altImages & all elements on QuickView overlay on Search page Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page registry mode
    Then I verify the all elements on QuickView overlay in registry mode
    And I verify zoomer and altImages on QuickView overlay in registry mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2B functionality on Search page Site mode
    Given I visit the web site as a guest user
    When I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page site mode
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
  Scenario: Verify QuickView A2B functionality on Search page iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page iship mode
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
  Scenario: Verify QuickView A2B functionality on Search page Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page registry mode
    And I select a random color on QuickView overlay in registry mode
    And I select a random size on QuickView overlay in registry mode
    Then I verify "in stock" message on QuickView overlay in registry mode
    And I select a maximum quantity on QuickView overlay in registry mode
    And I click "A2B" on QuickView overlay in registry mode
    Then I verify "x items added to your bag." message on QuickView overlay in registry mode
    And I click "Checkout" on QuickView overlay in registry mode
    And I verify basic elements of shopping bag page in "site" mode
    Then I verify that the product is added to shopping bag


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify List signin page from QuickView on Search page Site mode
    Given I visit the web site as a guest user
    When I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page site mode
    And I select a random size on QuickView overlay in site mode
    Then I verify "in stock" message on QuickView overlay in site mode
    And I click "A2L" on QuickView overlay in site mode
    Then I verify "Added to your guest List." message on QuickView overlay in site mode
    And I click "Signin link" on "A2L overlay" on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2L functionality on Search page Site mode
    Given I visit the web site as a guest user
    When I search for "Jeans"
    And I hover over a random product image and click on QuickView button on "Search" page site mode
    And I select a random color on QuickView overlay in site mode
    And I select a random size on QuickView overlay in site mode
    Then I verify "in stock" message on QuickView overlay in site mode
    And I click "A2L" on QuickView overlay in site mode
    And I click "List link" on "A2L overlay" on member PDP site mode
    Then I verify navigation to "list" page in site mode
    And I click "product image" on "list" page in site mode
    Then I verify navigating to "member" PDP "site" mode
