Feature: As a macys.com customer, I need to be able to filter search and browse results by color,
  and have the filtered results accurately display products that match the color selected

  @clp @p1 @v1-b-09140 @15jc @1522 @domain_mew_discovery @wip
  Scenario Outline: Verify the features to be displayed for the member product with same price for all colors in search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I click on filter link
    And I select facet name "Color"
    And I select facet value "White"
    When I click on Apply button
    Then I should be on the search results page
    And I navigate to a random PDP page on search results page
    And I should be on PDP page
    And I should see the color "White" as one of the colors
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @clp @p1 @v1-b-09140 @15jc @1522 @domain_mew_discovery @wip
  Scenario Outline: Verify the features to be displayed for the member product priced at the color level in search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I click on filter link
    And I select facet name "Color"
    And I select facet value "Blue"
    When I click on Apply button
    Then I should be on the search results page
    And I navigate to a random PDP page on search results page
    And I should be on PDP page
    And I should see the color "Blue" as one of the colors
    Examples:
      | mode     |
      | domestic |
      | iship    |