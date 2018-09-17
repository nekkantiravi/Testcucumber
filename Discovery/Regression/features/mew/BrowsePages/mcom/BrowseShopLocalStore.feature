Feature: Shoplocalstore functionality for browse

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify display of All Items and Free Pick up tabs on browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Jeans        |
    Then I should see All Items tab displayed
    And I should see Free pick up tab displayed
    And I tap on Local Store tab
    And I should see zip code text box in local store model
    And I should see show stores within "25" miles option selected by default in drop down box below zip code field
    And I should see navigation icon beside zip code text box field in local store model
    And I should see search button in local store model
    And I select a nearest store using "22102" search
    And I should see product count in Local Store Tab if store is preselected
    And I should see product count in All Items tab


  @domain_mew_discovery @use_mew_regression
  Scenario: Verify display of All Items and Free Pick up tabs on browse page for signed in user
    Given I visit the mobile web site as a registered user without add CC
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Jeans        |
    Then I should see All Items tab displayed
    And I should see Free pick up tab displayed
    And I tap on Local Store tab
    And I should see zip code text box in local store model
    And I should see show stores within "25" miles option selected by default in drop down box below zip code field
    And I should see navigation icon beside zip code text box field in local store model
    And I should see search button in local store model
    And I select a nearest store using "22102" search
    And I should see product count in Local Store Tab if store is preselected
    And I should see product count in All Items tab


  @domain_mew_discovery @use_mew_regression
  Scenario: Verify display of free pick up tab as greyed out on browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop                   |
      | Furniture & Mattresses |
      | Living Room            |
      | Couches & Sofas        |
    Then I should see All Items tab displayed
    And I should see Free pick up tab greyed out

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify if user is able to change a store using zipcode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Jeans        |
    Then I should see All Items tab displayed
    And I should see Free pick up tab displayed
    And I tap on Local Store tab
    And I select a store with prepopulated zipcode
    And I click on the change store link
    And I select a nearest store using "22102" search

  @domain_mew_discovery @use_mew_regression
  Scenario:Verify if user is able to change a store using city/state in search
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Jeans        |
    Then I should see All Items tab displayed
    And I should see Free pick up tab displayed
    And I tap on Local Store tab
    And I select a store with prepopulated zipcode
    And I click on the change store link
    And I select a nearest store using "Fremont CA" search


  @domain_mew_discovery @use_mew_regression
  Scenario:Verify if user is able to navigate to PDP and shopping bag page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | For The Home |
      | Kitchen      |
      | Electrics    |
    Then I should be on the browse page
    Then I should see All Items tab displayed
    And I should see Free pick up tab displayed
    And I tap on Local Store tab
    And I select a nearest store using "22102" search
    And I select a random member product using mobile website
    Then I verify Buy Online Pickup
    And I add product to my bag from PDP Page
    Then I verify BOPS section on checkout page

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify if the facets are applied on local store tab
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop         |
      | Men          |
      | All Clothing |
      | Jeans        |
    And I click on filter link
    And I select facet name "Brand"
    And I select facet value "Levi's"
    And I confirm selected facets using mobile website
    And I tap on Local Store tab
    And I select a nearest store using "22102" search
    Then I should see "Levi's" in breadcrumb
    When I click on X mark next to chip
    Then I should not see the chips displayed

  @Manual_Tag @domain_mew_discovery @use_mew_regression @manual
  Scenario: Verify that pick up option is selected by default in PDP page when Size color facet are preselected in local store tab
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I tap on Local Store tab
    And I select a nearest store using "Fremont CA" search
    And I select "Size" facet on left nav using mobile website
    And I select "4,S"  sub facet on left nav using mobile website
    And I select "Color" facet on left nav using mobile website
    And I select "Black" sub facet on left nav using mobile website
    And I click on random product
    Then I should see pick up in store selected by default with Size and color preselected

  @Manual_Tag @domain_mew_discovery @use_mew_regression @manual
  Scenario: Verify that pick up option is not  pre-selected by default in PDP page when product is added from All Items tab
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | women   |
      | Dresses |
    And I select "Size" facet on left nav using mobile website
    And I select "4,S"  sub facet on left nav using mobile website
    And I select "Color" facet on left nav using mobile website
    And I select "Black" sub facet on left nav using mobile website
    And I click on random product
    Then I should see pick up in store is not pre-selected by default

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify the pagination for local store tab
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop    |
      | Women   |
      | Dresses |
    And I tap on Local Store tab
    And I select a nearest store using "Fremont CA" search
    Then I should be able to navigate using pagination functionality using mobile website
