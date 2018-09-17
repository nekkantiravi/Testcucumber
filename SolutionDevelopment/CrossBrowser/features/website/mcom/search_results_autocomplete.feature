Feature: Search Results page auto completion scenarios in domestic, iship and registry modes

  @scenario1 @domain_discovery @xbrowser @xbrowser_one
  Scenario: Search Result page - User types text in search box in DOMESTIC mode
    Given I visit the web site as a guest user
    When I type "mic" in search box
    Then I should see "mic" in autocomplete suggestions

  @scenario2 @domain_discovery @xbrowser @xbrowser_one
  Scenario: Search Result page - Navigate to registry page and type a text in search box in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I type "mic" in search box
    Then I should not see autocomplete suggestions

  @scenario3 @domain_discovery @xbrowser @xbrowser_one
  Scenario: Search Result page - User types text in search box in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Australia"
    And I close the welcome mat if it's visible
    And I refresh current page
    And I type "mic" in search box
    Then I should see "mic" in autocomplete suggestions

  @scenario4 @domain_discovery @xbrowser @xbrowser_one
  Scenario: Shopping Bag - Verify Recommendation Panel not displayed for empty shopping bag
    Given I visit the web site as a guest user
    When I navigate to empty shopping bag page
    Then I verify recommendation panel is not displayed