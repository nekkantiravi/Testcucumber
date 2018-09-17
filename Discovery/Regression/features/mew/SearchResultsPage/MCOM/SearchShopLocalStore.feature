Feature: Shoplocalstore functionality for search

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify that shop local store tab and All Items tab is displayed
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should see All Items tab displayed
    And I should see Free pick up tab displayed
    And I tap on Local Store tab
    And I should see zip code text box in local store model
    And I should see show stores within "25" miles option selected by default in drop down box below zip code field
    And I should see navigation icon beside zip code text box field in local store model
    And I should see search button in local store model
    And I select a store with prepopulated zipcode
    And I should see product count in Local Store Tab if store is preselected
    And I should see product count in All Items tab
    Examples:
      | keyword |
      | Electrics   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify that shop local store tab and All Items tab is displayed as registered user
    Given I visit the mobile web site as a registered user without add CC
    When I type "<keyword>" in mew search and click enter
    Then I should see All Items tab displayed
    And I should see Free pick up tab displayed
    And I tap on Local Store tab
    And I should see zip code text box in local store model
    And I should see show stores within "25" miles option selected by default in drop down box below zip code field
    And I should see navigation icon beside zip code text box field in local store model
    And I should see search button in local store model
    And I select a store with prepopulated zipcode
    And I should see product count in Local Store Tab if store is preselected
    And I should see product count in All Items tab
    Examples:
      | keyword |
      | Jeans   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify if Free pick up tab is greyed when searched category is not elligible for bops
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should see All Items tab displayed
    And I should see Free pick up tab greyed out
    Examples:
      | keyword |
      | Tables  |
      | Tops    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify if invalid search messaging is displayed for invalid search
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should see messaging for invalid search
    Examples:
      | keyword  |
      | vdsvdbfd |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify if user is able to change a store using zipcode
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should be on the search results page
    Then I should see All Items tab displayed
    And I should see Free pick up tab displayed
    And I tap on Local Store tab
    And I select a store with prepopulated zipcode
    And I click on the change store link
    And I select a nearest store using "22102" search
    Examples:
      | keyword |
      | Jeans   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify if user is able to change a store using city/state in search
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should see All Items tab displayed
    And I should see Free pick up tab displayed
    And I tap on Local Store tab
    And I select a store with prepopulated zipcode
    And I click on the change store link
    And I select a nearest store using "Fremont CA" search
    Examples:
      | keyword |
      | Jeans   |

  @domain_mew_discovery @use_mew_regression
  Scenario:Verify if user is able to navigate to PDP ans shopping bag page with store
    Given I visit the mobile web site as a registered user without add CC
    When I type "Electrics" in mew search and click enter
    Then I should see All Items tab displayed
    And I should see Free pick up tab displayed
    And I tap on Local Store tab
    And I select a store with prepopulated zipcode
    And I select a random member product using mobile website
    Then I verify Buy Online Pickup
    And I add product to my bag from PDP Page
    Then I verify BOPS section on checkout page

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify if the facets are applied on local store tab
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "Electrics"
    And I click on filter link
    And I select facet name "Brand"
    And I select facet value "Cuisinart"
    And I confirm selected facets using mobile website
    And I tap on Local Store tab
    And I select a nearest store using "22102" search
    Then I should see "Cuisinart" in breadcrumb
    When I click on X mark next to chip
    Then I should not see the chips displayed


  @domain_mew_discovery @use_mew_regression
  Scenario: Verify if the facets are applied and can be removed on local store tab
    Given I visit the mobile web site as a guest user
    When I search using mobile website for "Electrics"
    And I tap on Local Store tab
    And I select a nearest store using "22102" search
    And I select "Brand" facet on left nav using mobile website
    And I select random facet value on facet accordion model
    And I confirm selected facets using mobile website
    Then I should see selected facet value in breadcrumb
    When I click on X mark next to chip
    Then I should not see the chips displayed


  @Manual_Tag @domain_mew_discovery @use_mew_regression @manual
  Scenario: Verify that pick up option is selected by default in PDP page when Size color facet are preselected in local store tab
    Given I visit the mobile web site as a guest user
    And I search for "Dresses"
    Then I should be on the search results page
    And I tap on Local Store tab
    And I select "Macy's Stanford Shopping Center" store using "Fremont CA" search
    And I click on filter link
    And  I select facet name "Size"
    And I select 4,S sub facet in Women's Regular
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    And I select a random member product using mobile website
    Then I should see pick up in store selected by default with Size and color preselected

  @Manual_Tag @domain_mew_discovery @use_mew_regression @manual
  Scenario: Verify that pick up option is not  pre-selected by default in PDP page when product is added from All Items tab
    Given I visit the mobile web site as a guest user
    And I search for "Dresses"
    And I click on filter link
    And  I select facet name "Size"
    And I select 4,S sub facet in Women's Regular
    And I select facet name "Color"
    And I select facet value "Black"
    And I click on Apply button
    And I select a random member product using mobile website
    Then I should see pick up in store is not selected by default in PDP

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify the pagination for local store tab
    Given I visit the mobile web site as a guest user
    And I search for "Hand Bags"
    And I tap on Local Store tab
    And I select a nearest store using "Fremont CA" search
    Then I should be able to navigate using pagination functionality using mobile website
