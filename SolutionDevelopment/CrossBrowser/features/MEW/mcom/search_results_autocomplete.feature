Feature: Search Results page auto completion scenarios in domestic, iship and registry modes

  @scenario1 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario: Search Result page - User types text in search box in DOMESTIC mode
    Given I visit the mobile web site as a guest user
    When I type "mic" in mew search box
    Then I should see "mic" in mew autocomplete suggestions

  @scenario2 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario: Search Result page - Navigate to registry page and type a text in search box in REGISTRY mode
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Wedding Registry |
    And I type "mic" in mew search box
    Then I should not see mew autocomplete suggestions

  @scenario3 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario: Search Result page - User types text in search box in ISHIP mode
    Given I visit the mobile web site as a guest user
    When I navigate to change country page using mobile website
    And I change country to "Australia" using mobile website
    And I close the welcome mat if it's visible using mobile website
    And I type "mic" in mew search box
    Then I should see "mic" in mew autocomplete suggestions

  @scenario4 @xbrowser_tablet @domain_discovery @xbrowser_mew
  Scenario: Shopping Bag - Verify Recommendation Panel not displayed for empty shopping bag
    Given I visit the mobile web site as a guest user
    When I navigate to shopping bag page using mobile website
    Then I verify recommendation panel is not displayed on shopping bag page
