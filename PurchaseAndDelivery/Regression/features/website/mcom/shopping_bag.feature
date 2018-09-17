Feature: shopping bag

  @dsv_desktop_sev1
  Scenario: Remove Item from Bag
    Given I visit the web site as a guest user
    When I add an "available and orderable and prod_available" product to my bag
    And I click checkout button on add to bag overlay
    And I remove an item from the bag
    Then I can see empty bag view

  @dsv_desktop_sev1
  Scenario: Verify Shopping bag update quantity
    Given I visit the website as a guest user
    When I add an "available and orderable and prod_available" product to my bag
    And I click checkout button on add to bag overlay
    When I update shopping bag items with quantity 2
    Then I should see updated total in shopping bag page for updated quantity 2

  @dsv_desktop_sev1
  Scenario: BOPs - Change Store
    Given I visit the web site as a guest user
    And I navigate to the "Dress Shirts" browse page under "MEN"
    Then I verify that "Free Pick Up In Store" facet is listed on left nav
    When I select zipcode link in pick-up in-store facet section
    Then I verify that bops overlay is displayed
    When I search for zipcode "94102" in bops change store dialog
    And I close the bops change store dialog
    Then I verify store location is 94102