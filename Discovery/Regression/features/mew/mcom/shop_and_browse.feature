Feature: Verify different display options on browse page

  @dsv_mew_sev1
  Scenario: Verify different display options on browse page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Shop           |
      | Men            |
      | All Clothing   |
      | Jeans          |
    When I select list view display options on browse page
    Then I should see products in list view on browse page
    When I select grid view display options on browse page
    Then I should see products in grid view on browse page
    When I select gallery view display options on browse page
    Then I should see products in gallery view on browse page
