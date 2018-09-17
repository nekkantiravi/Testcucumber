Feature: Verify main ads are displayed or not on home page

  @domain_mew_discovery @use_mew_regression
  Scenario: Verify Main ads are displayed on HomePage
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify that main ads are displayed on mobile Home Page
    And I verify that ads are clickable and should not navigate to error page

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify featured categories on Home page are clickable and navigate to the correct pages
    Given I visit the mobile web site as a guest user
    When I navigate to featured "<category>" category page and verified using mobile website
    Examples:
      | category                |
      | Women                   |
      | Beauty                  |
      | Furniture & Mattresses  |
      | Men                     |
      | Jewelry & Watches       |
      | Gift Cards              |
      | Handbags & Accessories  |
      | Kids                    |
      | Shoes                   |
      | Plus Sizes & Petites    |
      | Registry                |
      | Home                    |
      | Juniors                 |
      | Gift Guide              |
      | Clearance               |
      | Active & Wellness       |