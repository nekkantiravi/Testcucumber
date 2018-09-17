Feature: As a product owner, I would like to ensure that CHANEL brand has requested to expose customer reviews on PDP

  @domain_selection @mew_regression @mew_review_regression
  Scenario: verify that the customer reviews for chanel brand are displayed on PDP
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "74714" in mew search box
    And I click on search go button at search field
    Then I should see rating and reviews on pdp

  @domain_selection @mew_regression @mew_review_regression
  Scenario: Verify that the user is able to write a review successfully for a member product
    Given I visit the mobile web site as a registered user
    When I search for "Men Jeans" global Search Input Field
    When I navigate to member PDP page that has multiple color/size combination
    Then I should see the write a review button and I click on it
    When I write a review on the review modal for the product
    Then the write review submission is complete

    @wip
  Scenario: Verify that the user is able to write a review successfully for a master product
    Given I visit the mobile web site as a registered user
    When I type "19942" in mew search box
    Then I should see the write a review button and I click on it
    When I write a review on the review modal for the product
    Then the write review submission is complete

  @domain_selection @mew_regression @mew_review_regression
  Scenario: verify that the customer reviews for flatware product are displayed on PDP
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "19942" in mew search box
    And I click on search go button at search field
    Then I should see rating and reviews on pdp
    When I click on reviews number next to stars
    Then I verify that reviews are displayed

    @domain_selection
  Scenario: verify that the customer reviews for flatware product are displayed on PDP
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "4584238" in mew search box
    And I click on search go button at search field
    Then verify media player