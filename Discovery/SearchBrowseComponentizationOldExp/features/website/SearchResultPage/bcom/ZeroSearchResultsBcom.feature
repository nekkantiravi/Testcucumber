# Author: Discovery QE

Feature: Verify Search Zero Results Page contents in DOMESTIC Registy and Iship mode

  @please_automate @add_missing_scope @artifact_navapp @domain_discovery @priority_medium @mode_domestic @please_automate
  Scenario: SearchResultsPage - Domestic - Verify the BCOM  zero results page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "stamina"
    Then I verify that the message "we couldn't find a match for stamina" in zero results page

  @please_automate @add_missing_scope @artifact_navapp @domain_discovery @priority_medium @mode_domestic @please_automate
  Scenario: SearchResultsPage - Domestic - Verify the BCOM  zero results page with special character in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "xtyz&asdf"
    Then I verify that the message "we couldn't find a match for xtyz&asdf" in zero results page

  @please_automate @add_missing_scope @artifact_navapp @domain_discovery @priority_medium @mode_iship @please_automate
  Scenario Outline: SearchResultsPage - Iship - Verify the BCOM International zero results page in ISHIP mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "<Country>"
    And I close the welcome mat if it's visible
    When I search for "stamina"
    And I verify that the message "we couldn't find a match for stamina" in zero results page
    Examples:
     | Country        |
     | India          |
     | Canada         |
     | United Kingdom |

  @please_automate @add_missing_scope @artifact_navapp @domain_discovery @priority_medium @mode_iship @please_automate
  Scenario Outline: SearchResultsPage - Iship - Verify the BCOM International zero results page having special character in ISHIP mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "<Country>"
    And I close the welcome mat if it's visible
    When I search for "sdkkl&kijrw&"
    And I verify that the message "we couldn't find a match for sdkkl&kijrw&" in zero results page
    Examples:
      | Country        |
      | India          |
      | Canada         |
      | United Kingdom |

  @please_automate @add_missing_scope @artifact_navapp @domain_discovery @priority_medium @mode_registry @please_automate
  Scenario: SearchResultsPage - Registry - Verify the BCOM International zero results page in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I search for "stamina"
    Then I verify that the message "we couldn't find a match for stamina" in zero results page

  @please_automate @add_missing_scope @artifact_navapp @domain_discovery @priority_medium @mode_registry @please_automate
  Scenario: SearchResultsPage - Registry - Verify the BCOM International zero results page having special character in REGISTRY mode
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I search for "xtyz&asdf"
    And I verify that the message "we couldn't find a match for xtyz&asdf" in zero results page