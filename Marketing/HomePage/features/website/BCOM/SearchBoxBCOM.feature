#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Home Page functionality in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery
  Scenario: Home Page - Verify Search Box in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify Search Box is displayed with search icon
    When I search "caps" for auto-completion
    Then I see auto-suggestions for "caps"
    When I click the search icon
    Then I verify I land on Search Results Page for "caps"
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_iship @backlog_discovery
  Scenario: Home Page - Verify Search Box in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I verify Search Box is displayed with search icon
    When I search "caps" for auto-completion
    Then I see auto-suggestions for "caps"
    When I click the search icon
    Then I verify I land on Search Results Page for "caps"
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery @use_browser
  Scenario: Home Page - Verify Search Box in REGISTRY Page
    Given I visit the web site as a guest user
    And I navigate to registry home page
    And I verify Search Box is displayed with search icon
    When I search "caps" for auto-completion
    Then I should not see autocomplete suggestions
    When I click the search icon
    Then I verify I land on Search Results Page for "caps"
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery @use_domain_qual
  Scenario: Home Page - Verify Search Box in REGISTRY mode
    Given I visit the web site as a registry user
    And I verify Search Box is displayed with search icon
    When I search "caps" for auto-completion
    Then I should not see autocomplete suggestions
    When I click the search icon
    Then I verify I land on Search Results Page for "caps"
    #Notes:
    #Verify SRP displays products and has the keyword "caps" on page as text
