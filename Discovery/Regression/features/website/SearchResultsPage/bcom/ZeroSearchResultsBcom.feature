# Author: Discovery QE

Feature: Verify Search Zero Results Page contents in DOMESTIC Registy and Iship mode

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt @use_regression
  Scenario: SearchResultsPage - Domestic - Verify the BCOM  zero results page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "fsfafap lafasfd"
    Then I modify the url to get in to snbc experiment
    Then I verify that the message "We couldn't find a match for fsfafap lafasfd." in zero results page

  @domain_discovery @priority_medium @mode_domestic @snbc_comp @migrated_to_sdt @use_regression
  Scenario: SearchResultsPage - Domestic - Verify the BCOM  zero results page with special character in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "xtyz&asdf"
    Then I modify the url to get in to snbc experiment
    Then I verify that the message "We couldn't find a match for xtyz&asdf." in zero results page

  @domain_discovery @priority_medium @mode_iship @snbc_comp @migrated_to_sdt @use_regression
  Scenario Outline: SearchResultsPage - Iship - Verify the BCOM International zero results page in ISHIP mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "<Country>"
    And I close the welcome mat if it's visible
    When I search for "fsagwgwf"
    Then I modify the url to get in to snbc experiment
    Then I verify that the message "We couldn't find a match for fsagwgwf." in zero results page
    Examples:
      | Country        |
      | India          |
      | Canada         |
      | United Kingdom |

  @domain_discovery @priority_medium @mode_iship @snbc_comp @migrated_to_sdt @use_regression
  Scenario Outline: SearchResultsPage - Iship - Verify the BCOM International zero results page having special character in ISHIP mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "<Country>"
    And I close the welcome mat if it's visible
    When I search for "sdkkl&kijrw&"
    Then I modify the url to get in to snbc experiment
    Then I verify that the message "We couldn't find a match for sdkkl&kijrw&." in zero results page
    Examples:
      | Country        |
      | India          |
      | Canada         |
      | United Kingdom |

  @domain_discovery @priority_medium @mode_registry @snbc_comp @migrated_to_sdt @use_regression
  Scenario: SearchResultsPage - Registry - Verify the BCOM International zero results page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I search for "stamina"
    Then I modify the url to get in to snbc experiment
    Then I verify that the message "We couldn't find a match for stamina." in zero results page

  @domain_discovery @priority_medium @mode_registry @snbc_comp @migrated_to_sdt @use_regression
  Scenario: SearchResultsPage - Registry - Verify the BCOM International zero results page having special character in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I search for "xtyz&asdf"
    Then I modify the url to get in to snbc experiment
    Then I verify that the message "We couldn't find a match for xtyz&asdf." in zero results page