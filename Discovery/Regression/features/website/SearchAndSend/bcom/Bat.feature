Feature: Changing additional filters

#http://11.120.103.7/projects/unified_navigation/cards/11441

  @use_regression_sns @domain_discovery @artifact_navapp_sns @use_bat_pos
  Scenario: SearchAndSend - Verifying pos home page and search with keyword
    Given I am on SNS POS simulator page
    When I search for "shoes" as keyword in SNS POS
    Then I should see page title "Find Merchandise - Search Results" in SNS POS "search results" page
    And I should see products on POS search results page
