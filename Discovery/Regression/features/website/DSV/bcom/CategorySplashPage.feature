#Author: Discovery QE

Feature: Verify SubSplashPage functionality for DSV

############################### DOMESTIC MODE ##########################################################

  @domain_discovery @priority_high @mode_domestic @use_regression @migrated_to_sdt @dsv_desktop_sev2
  Scenario: CatSplashPage - Verify JSESSIONID cookie in DOMESTIC mode
    Given I visit the web site as a guest user
    And I navigate to "WOMEN" category page
    Then I verify that "JSESSIONID" cookie is not displayed