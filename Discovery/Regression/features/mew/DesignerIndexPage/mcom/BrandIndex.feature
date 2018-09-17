Feature: Brand index page verifications

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify user is able to navigate to brand index page in all modes
    Given I visit the mobile web site as a guest user in <mode_name> mode
    When I navigate to brand index page in <mode_name> mode using mobile website
    Then I should be redirected to designer page using mobile website
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify brands should be refined based on search keyword in brand index page
    Given I visit the mobile web site as a guest user in <mode_name> mode
    When I type "Brands" in mew search and click enter
    Then I should be redirected to designer page using mobile website
    And I type alphabet "j" in brand index search field
    Then I should see brands should be refined based on alphabet "j"
    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify page redirecting to brand index page while searching with brands keyword
    Given I visit the mobile web site as a guest user in <mode_name> mode
    When I type "Brands" in mew search and click enter
    Then I should be redirected to designer page using mobile website
    And I verify GN is expanded till "All Brands"
    Examples:
      | mode_name |
      | domestic  |
      | iship     |
      | registry  |