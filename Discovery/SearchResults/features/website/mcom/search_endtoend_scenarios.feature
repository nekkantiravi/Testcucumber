Feature: Verify Search

  Scenario: Response has more than X results
    Given I visit the web site as a guest user
    When I navigate to Search page with keyword then I should see
      |Keyword  |min_product_count|max_product_count|
      |Jeans    |1000             |2000             |
#      |shirts    |
#      |red dress  |
#      |Clearance shoes|
#    Then I should see minimum <min_product_count> and maximum <max_product_count>
#
#    Examples:
#    |keyword|min_product_count|max_product_count|
#    |Dresses|2000             |10000            |
#
#  Scenario Outline: Response has facets information
#    Given I visit the web site as a guest user
#    When I search
#
#    Examples:
#    |Keyword|

  Scenario: Products are not sorted by web ID
    Given I visit the web site as a guest user
    Then I call KWS service using keyword
      |keyword|
      |Dresses|
      |Jeans  |
#    And should fetch random 10 product ids from service response
#    And verify that product IDs are not sorted



