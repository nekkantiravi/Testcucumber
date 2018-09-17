Feature: Verify Registry Categories

  @dsv_mew_sev1
  Scenario Outline: To verify Registry Categories
    Given I visit the mobile web site as a guest user
    When I navigate the registry from global navigate menu
    Then I login with manage registry user on mobile website
    Then I navigate to registry categories using mobile website
    When I navigate to "<category>" category page and verified using mobile website
    Examples:
      | category                  |
      | Dining & Entertaining     |
      | Kitchen                   |
      | Bed & Bath                |
      | Home Decor                |
      | Luggage                   |
      | Home Care & Tech          |
      | Sale                      |