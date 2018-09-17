Feature: Search Results page auto completion scenarios in domestic, iship and registry modes

  @scenario1 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario Outline: Search Result page - Navigate to registry page and type a text in search box in domestic and iship modes
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "mic" in mew search box
    Then I should see "mic" in mew autocomplete suggestions

    Examples:
      | mode     |
      | domestic |
      | iship    |

  @scenario2 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario: Search Result page - Navigate to registry page and type a text in search box in REGISTRY mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | The Registry              |
      | Manage or Manage Registry |
    And I type "mic" in mew search box
    Then I should not see mew autocomplete suggestions