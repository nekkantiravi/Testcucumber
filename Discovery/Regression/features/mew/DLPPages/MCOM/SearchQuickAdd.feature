Feature: As a user I should be able to use quick add feature in DLP pages to add a product to the bag

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - Go to Category DLP page and verify quick add buttons
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    Then I should be on DLP page
    Then I should see quick add button for every member product on DLP page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the DLP page
    And I should see the quick add overlay with all elements as per the response on <mode> mode
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - Go to DLP page and verify quick add overlay to close after adding item to bag
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    Then I should be on DLP page
    Then I should see quick add button for every member product on DLP page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the DLP page
    And I should see the quick add overlay with all elements as per the response on <mode> mode
    When I tap on add to bag button in quick add overlay
    Then I should not see the quick add overlay on the browse page
    And I should see product is added to bag
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DLP - Verify  page is navigating to PDP when user selects see product details link on quick add
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    Then I should be on DLP page
    Then I should see quick add button for every member product on DLP page
    When I tap on the quick add button on a random product
    Then I should see the quick add overlay on the DLP page
    When I tap on see product details link
    Then I should be on PDP page
    Examples:
      | mode     |
      | domestic |
      | iship    |
