Feature: Verify Registry Random Categories

  @dsv_mew_sev1
  Scenario: To verify Registry Random Categories
    Given I visit the web site as a guest user
    When I navigate the global navigation menu as follows:
      | Wedding Registry      |
      | Gift Categories       |
      | Dining & Entertaining |
      | Dinnerware            |
    Then I should see category in Dinnerware view on browse page
    When I navigate to home page
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
      | Gift Categories  |
      | Kitchen          |
      | Bakeware         |
    Then I should see category in Bakeware view on browse page