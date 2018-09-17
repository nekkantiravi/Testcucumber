# Author: IA-2017 QE Team
# Date Created: 12/05/2017
Feature: Tabbed view of online and BOPS items on Search pages

  @project_IA_2017 @domain_discovery @scenario1
  Scenario: Verify the default tabbed view of Online and BOPS items on searchResults page.
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    Then I should see tabbed view for online and BOPS items
    And I should see "All products" tab as active
    And I should see "Local store" tab as inactive

  @project_IA_2017 @domain_discovery @scenario2
  Scenario: Verify the product count displaying on tabbed view of Online and BOPS items on search results page.
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I should see tabbed view for online and BOPS items
    Then I should see product count on "All products" tab
    And I see the product count displaying on "All products" tab should be same as in service response
    And I should not see any product count on "Local store" tab

  @project_IA_2017 @domain_discovery @scenario3
  Scenario: Verify that "Free Pick Up In Store" facet section is not displaying on left navigation of searchResults page.
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I should see tabbed view for online and BOPS items
    Then I should not see "Free Pick Up In Store" facet section is not displaying on left navigation

  @project_IA_2017 @domain_discovery @scenario4
  Scenario: Verify "Local store" tab when customer location is known and product results are available on search results page
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I see tabbed view for online and BOPS items
    When I select "Local store" tab
    Then I should see the default store name beside the "Shopping at:" label
    And I should see the default store displayed on UI is same as from the service response
    And I should see the Change store link
    And I should see the store product count beside the Local store tab name
    And I should see the product count same as from the service response

  @project_IA_2017 @domain_discovery @scenario5
  Scenario: Verify when customer select different store in "Find Stores within" overlay when default store is displayed with product result in local store tab on search results page.
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I see tabbed view for online and BOPS items
    When I click on "Local store" tab
    And I click on "Change store" link
    And I see "Find Stores within" overlay
    When I select different store location
    And I see default store should be unselected
    When I click on Apply button
    Then I should see "Find Stores within" overlay is closed
    And I should see the newly selected store name displayed on search results page
    And I should see Change store link beside the store name on search results page

  @project_IA_2017 @domain_discovery @scenario6
  Scenario: Verify when customer click "Change location" link in "Find Stores within" overlay when default store is displayed with product result in local store tab on search results page.
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I see tabbed view for online and BOPS items
    When I click on "Local store" tab
    And I click on "Change store" link
    And I see "Find Stores within" overlay
    When I click on "change location" link
    And I see "Find a store" overlay
    And I close the "Find a store" overlay
    Then I should able to see the previou default store name on search results page

  @project_IA_2017 @domain_discovery @scenario7
  Scenario: Verify when customer selects different location in "Find a store" overlay when default store is displayed with product result in local store tab on search results page.
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I see tabbed view for online and BOPS items
    When I click on "Local store" tab
    And I click on "Change store" link
    And I see "Find Stores within" overlay
    When I click on "change location" link
    And I see "Find a store" overlay
    When I enter different zipcode
    And I click on "SAVE" button
    Then I should able to see the new store name on search results page
    And I  see BOPS products in the search results
    And I see product resuts are same from the service response

  @project_IA_2017 @domain_discovery @scenario8
  Scenario: Verify the store location tab after switch between "All products" tab and "Local store" tab when customer selects different location in "Find a store" overlay when default store is displayed with product result in local store tab on search results page.
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I see tabbed view for online and BOPS items
    When I click on "Local store" tab
    And I click on "Change store" link
    And I see "Find Stores within" overlay
    When I click on "change location" link
    And I see "Find a store" overlay
    When I enter different zipcode
    And I click on "SAVE" button
    And I see the new store name on search results page
    When  click on "All product" tab
    And I see the product results in the search results page
    When I click on "Local store" tab
    Then I should able to see the same store name on search results page
    And I should see "Change store" link

  @project_IA_2017 @domain_discovery @scenario9
  Scenario: Verify the default Local store tab when customer's location is already known but products are not available in that store on search results page
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I see tabbed view for online and BOPS items
    When I select "Local store" tab
    Then I should able to see the default store name beside the "Shopping at:" label
    And I should see the default store displayed on UI is same as from the service response
    And I should see "change store" link
    And I should see the message as below:
      | We found 0 results for dresses available for pick up at|
    And I should see "You can:" label
    And I should see below label text
    And I should see store product count as '0' on Local store tab

  @project_IA_2017 @domain_discovery @scenario10
  Scenario: Verify change store link when customer's location is already known but products are not available in that store on search results page
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I see tabbed view for online and BOPS items
    And I select "Local store" tab
    And I should see the message as below:
      | We found 0 results for dresses available for pick up at |
    When I click on "Change store" link
    And I see "Find Stores within" overlay
    When I select different store location
    And I see default store should be unselected
    When I click on Apply button
    Then I should see "Find Stores within" overlay is closed
    And I should see the newly selected store name displayed on search results page
    And I should see Change store link beside the store name on search results page
    And I see product results in the search results
    And I see product resuts are same from the service response

  @project_IA_2017 @domain_discovery @scenario11
  Scenario: Verify change location link on "Find stores within" overlay when customer's location is already known but products are not available in that store on search results page
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I see tabbed view for online and BOPS items
    And I select "Local store" tab
    And I should see the message as below:
      | We found 0 results for dresses available for pick up at: Store name |
    When I click on "Change store" link
    And I click on "Change location" link on "Find stores within" overlay
    And I see "Find a store" overlay
    When I enter different zipcode
    And I click on "SAVE" button
    Then I should able to see the previou default store name on search results page
    And I  see BOPS products in the search results
    And I see product resuts are same from the service response

  @project_IA_2017 @domain_discovery @scenario12
  Scenario: Verify "Change your local store" link when customer's location is already known but products are not available in that store on search results page
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I see tabbed view for online and BOPS items
    And I select "Local store" tab
    And I should see the message as below:
      | We found 0 results for dresses available for pick up at:Store name |
    When I click on "Change your local store" link
    And I see "Find stores within" overlay is displayed
    When I select different store location
    And I click on Apply button
    Then I should see "Find Stores within" overlay is closed
    And I should see the newly selected zipcode store name displayed on search results page
    And I should see the product results on the search results page

  @project_IA_2017 @domain_discovery @scenario13
  Scenario: Verify the store location tab after switch between "All products" tab and "Local store" tab when customer's location is not known on search results page.
    Given I am on Home Page
    When I enter "Dresses" keyword on serch text field
    And I navigate to searchResults page
    And I see tabbed view for online and BOPS items
    When I click on "Local store" tab
    And I  see "Find a Store" overlay
    When I enter the valid store zipcode
    And I click on save button
    Then I should able to see the selected store name beside "shipping at" label
    When  click on "All product" tab
    And I see the product results in the search results page
    When I click on "Local store" tab
    Then I should able to see the same store name on search results page
    And I should see "Change store" link
    And I should seee the product results