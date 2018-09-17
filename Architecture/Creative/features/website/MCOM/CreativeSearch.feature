Feature: Validate search functionality

  @creative @creative1
  Scenario Outline: Search functionality validation
    Given I visit the web site as a guest user
    When I search for "<search_keyword>"
    Then I should be in Search Landing page
    And I validate the search url
    And I should see "<keyword>" in search landing page
    Examples:
      | search_keyword  | keyword      |
      | Coach           | Coach        |
      | Dresses         | Dresses      |
      | Jeans           | Jeans        |
      | Levis           | Levis        |
      | Ralph Lauren    | Ralph Lauren |
      | Furniture       | Furniture    |
      | Shirts          | Shirts       |
      | Sneakers        | Sneakers     |









