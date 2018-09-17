# Author: UFT QE
# Date Created: 2016/11/18
# Story: B-65326

Feature: Verification of new legal copy in the footer


  @artifact_navapp @domain_marketing @priority_high @mode_domestic @project_UFT @release_17I
  Scenario: Verify new legal notice text in the footer on Home page
    Given I visit the web site as a guest user
    And I verify the new legal notice text in the footer:
      | Macys.com, LLC, 680 Folsom St. San Francisco, CA 94107        |
      | 2017 macys.com is a registered trademark. All rights reserved |

  @artifact_navapp @domain_marketing @priority_high @mode_domestic @project_UFT @release_17I
  Scenario: Verify new legal notice text in the footer on Search results page
    Given I visit the web site as a guest user
    When I search for "dresses"
    Then I verify the new legal notice text in the footer:
      | Macys.com, LLC, 680 Folsom St. San Francisco, CA 94107        |
      | 2017 macys.com is a registered trademark. All rights reserved |

  @artifact_navapp @domain_marketing @priority_high @mode_domestic @project_UFT @release_17I
  Scenario: Verify new legal notice text in the footer on Browse page
    Given I visit the web site as a guest user
    When I navigate to browse page in "domestic" mode
    Then I verify the new legal notice text in the footer:
      | Macys.com, LLC, 680 Folsom St. San Francisco, CA 94107        |
      | 2017 macys.com is a registered trademark. All rights reserved |

  @artifact_shopapp @domain_marketing @priority_high @mode_registry @project_UFT @release_17I
  Scenario: Verify new legal notice text in the footer on registry home page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I verify the new legal notice text in the footer:
      | Macys.com, LLC, 680 Folsom St. San Francisco, CA 94107        |
      | 2017 macys.com is a registered trademark. All rights reserved |

  @artifact_shopapp @domain_marketing @priority_high @mode_registry @project_UFT @release_17I
  Scenario: Verify new legal notice text in the footer on registry manager page
    Given I visit the web site as a guest user
    When I create a new wedding registry with event date as past date which is less than 185 days and event type as "WEDDING" option
    Then I should be navigated to the registry manager page
    And I verify the new legal notice text in the footer:
      | Macys.com, LLC, 680 Folsom St. San Francisco, CA 94107        |
      | 2017 macys.com is a registered trademark. All rights reserved |