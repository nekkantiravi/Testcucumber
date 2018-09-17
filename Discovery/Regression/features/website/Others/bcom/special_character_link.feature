
@dsv_desktop_sev2
Feature: To validate all special character links in flyouts
  Scenario Outline: To validate all special character links in flyouts
    Given I visit the web site as a guest user
    And I mouse over "<FOB>" category from top navigation
    And I validate all special character links in all flyouts
   Examples:
    | FOB                  |
    | WOMEN                |
    | SHOES                |
    | HANDBAGS             |
    | JEWELRY & ACCESSORIES|
    | MEN                  |
    | BEAUTY               |
    | KIDS                 |
    | HOME                 |
    | SALE                 |
    | DESIGNERS            |
    | WHAT'S NEW           |
    | GIFTS                |
    | THE REGISTRY         |



