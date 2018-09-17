#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Aug.18,2017
#---------------------------------------------------

Feature: QuickView_ProsPanel Validation & Verification


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on ZeroSearch ProsPanel Site mode
    Given I visit the web site as a guest user
    When I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in site mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page site mode
    Then I verify the basic elements on QuickView overlay in site mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on ZeroSearch ProsPanel iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in iship mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page iship mode
    Then I verify the basic elements on QuickView overlay in iship mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on ZeroSearch ProsPanel Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in registry mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page registry mode
    Then I verify the basic elements on QuickView overlay in registry mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify zoomer, altImages & all elements on QuickView overlay on ZeroSearch ProsPanel Site mode
    Given I visit the web site as a guest user
    When I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in site mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page site mode
    Then I verify the all elements on QuickView overlay in site mode
    And I verify zoomer and altImages on QuickView overlay in site mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify zoomer, altImages & all elements on QuickView overlay on ZeroSearch ProsPanel iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in iship mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page iship mode
    Then I verify the all elements on QuickView overlay in iship mode
    And I verify zoomer and altImages on QuickView overlay in iship mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify zoomer, altImages & all elements on QuickView overlay on ZeroSearch ProsPanel Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in registry mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page registry mode
    Then I verify the all elements on QuickView overlay in registry mode
    And I verify zoomer and altImages on QuickView overlay in registry mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2B functionality on ZeroSearch ProsPanel Site mode
    Given I visit the web site as a guest user
    When I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in site mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page site mode
    And I click "A2B" on QuickView overlay in site mode
    Then I verify "x items added to your bag." message on QuickView overlay in site mode
    And I click "Checkout" on QuickView overlay in site mode
    And I verify basic elements of shopping bag page in "site" mode
    Then I verify that the product is added to shopping bag


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2B functionality on ZeroSearch ProsPanel iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in iship mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page iship mode
    And I click "A2B" on QuickView overlay in iship mode
    Then I verify "x items added to your bag." message on QuickView overlay in iship mode
    And I click "Checkout" on QuickView overlay in iship mode
    And I verify basic elements of shopping bag page in "iship" mode
    Then I verify that the product is added to shopping bag


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2B functionality on ZeroSearch ProsPanel Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in registry mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page registry mode
    And I click "A2B" on QuickView overlay in registry mode
    Then I verify "x items added to your bag." message on QuickView overlay in registry mode
    And I click "Checkout" on QuickView overlay in registry mode
    And I verify basic elements of shopping bag page in "site" mode
    Then I verify that the product is added to shopping bag


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify List signin page from QuickView on ZeroSearch ProsPanel Site mode
    Given I visit the web site as a guest user
    When I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in site mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page site mode
    And I click "A2L" on QuickView overlay in site mode
    Then I verify "Added to your guest List." message on QuickView overlay in site mode
    And I click "Signin link" on "A2L overlay" on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2L functionality on ZeroSearch ProsPanel Site mode
    Given I visit the web site as a guest user
    When I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in site mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page site mode
    And I click "A2L" on QuickView overlay in site mode
    Then I verify "Added to your guest List." message on QuickView overlay in site mode
    And I click "List link" on "A2L overlay" on member PDP site mode
    Then I verify navigation to "list" page in site mode
    And I click "product image" on "list" page in site mode
    Then I verify navigating to "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2R functionality on ZeroSearch ProsPanel Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I search for "Jeharfgwegns"
#    And I delete all server & client side cookies on "SearchResult" page in registry mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ZSR" page registry mode
    And I click "A2R" on QuickView overlay in registry mode
    Then I verify that guest user is navigated to "Registry" signin page


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on ProsPanel in A2B page Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I hover over a random product image and click on QuickView button on "ProsHR in A2B" page site mode
    Then I verify the basic elements on QuickView overlay in site mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify A2B functionality of QuickView on ProsPanel in A2B page Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22805" in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I hover over a random product image and click on QuickView button on "ProsHR in A2B" page site mode
    And I click "A2B" on QuickView overlay in site mode
#    Then I verify "x items added to your bag." message on QuickView overlay in site mode
#    And I click "Checkout" on QuickView overlay in site mode
    Then I click "Checkout" button on "member" PDP "site" mode
    And I verify basic elements of shopping bag page in "site" mode
    Then I verify that the product is added to shopping bag


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify List signin page from QuickView on ProsPanel in A2B page Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I hover over a random product image and click on QuickView button on "ProsHR in A2B" page site mode
    And I click "A2L" on QuickView overlay in site mode
    And I click "Signin link" on "A2L overlay" on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2L functionality on ProsPanel in A2B page Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I hover over a random product image and click on QuickView button on "ProsHR in A2B" page site mode
    And I click "A2L" on QuickView overlay in site mode
    And I click "List link" on "A2L overlay" on member PDP site mode
    Then I verify navigation to "list" page in site mode
    And I click "product image" on "list" page in site mode
    Then I verify navigating to "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify Registry signin page from QuickView on ProsPanel in A2B page Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I hover over a random product image and click on QuickView button on "ProsHR in A2B" page site mode
    And I click "A2R Link" on QuickView overlay in site mode
    Then I verify that guest user is navigated to "Registry" signin page


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify basic elements of QuickView on ProsPanel in ShoppingBag page Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ShoppingBag" page site mode
    Then I verify the basic elements on QuickView overlay in site mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify A2B functionality of QuickView on ProsPanel in ShoppingBag page Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ShoppingBag" page site mode
    And I click "A2B" on QuickView overlay in site mode
#    Then I verify "x items added to your bag." message on QuickView overlay in site mode
#    And I click "Checkout" on QuickView overlay in site mode
    Then I click "Checkout" button on "member" PDP "site" mode
    And I verify basic elements of shopping bag page in "site" mode
    Then I verify that the product is added to shopping bag


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify List signin page from QuickView on ProsPanel in ShoppingBag page Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ShoppingBag" page site mode
    And I click "A2L" on QuickView overlay in site mode
    And I click "Signin link" on "A2L overlay" on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify QuickView A2L functionality on ProsPanel in ShoppingBag page Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ShoppingBag" page site mode
    And I click "A2L" on QuickView overlay in site mode
    And I click "List link" on "A2L overlay" on member PDP site mode
    Then I verify navigation to "list" page in site mode
    And I click "product image" on "list" page in site mode
    Then I verify navigating to "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_qv
  Scenario: Verify Registry signin page from QuickView on ProsPanel in ShoppingBag page Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    And I hover over a random product image and click on QuickView button on "ProsHR in ShoppingBag" page site mode
    And I click "A2R Link" on QuickView overlay in site mode
    Then I verify that guest user is navigated to "Registry" signin page
