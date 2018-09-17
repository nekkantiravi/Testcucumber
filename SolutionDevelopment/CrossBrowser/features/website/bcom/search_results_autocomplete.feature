Feature: Search Results page auto completion scenarios in domestic, iship and registry modes

  @scenario1 @domain_discovery @xbrowser @xbrowser_two
  Scenario Outline: Search Result page - Navigate to registry page and type a text in search box in domestic and iship modes
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I type "jeans" in search box
    Then I should see "jeans" in autocomplete suggestions

    Examples:
      | mode_name |
      | domestic  |
      | iship     |

  @scenario2 @domain_discovery @xbrowser @xbrowser_one
  Scenario: Search Result page - Navigate to registry page and type a text in search box in REGISTRY mode
    Given I visit the web site as a guest user in "registry" mode
    When I type "mic" in search box
    Then I should not see autocomplete suggestions