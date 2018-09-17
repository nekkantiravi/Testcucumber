Feature: As a QE engineer, I want to ensure that the menu collapse bug does not reappear

  @dsv_mew_sev2 @domain_discovery @domain_mew_discovery @use_mew_regression
  Scenario: Verify that the menu doesn't collapse WITH secure-m sign-in
    Given I visit the mobile web site as a registered user
    When I navigate to global navigation menu as follows:
      | Shop    |
      | Shoes   |
      | Women's Shoes |
      | Boots |
    Then I should see global navigation menu bar open on browse page

  @dsv_mew_sev2 @domain_discovery @domain_mew_discovery @use_mew_regression
  Scenario: Verify that the menu doesn't collapse WITHOUT secure-m sign-in
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate to global navigation menu as follows:
      | Shop    |
      | Shoes   |
      | Women's Shoes |
      | Boots |
    Then I should see global navigation menu bar open on browse page
