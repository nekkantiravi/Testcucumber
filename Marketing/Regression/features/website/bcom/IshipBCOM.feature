Feature: Verify Iship functionality

  @dsv_desktop_sev2 @use_regression @iship @domain_marketing
  Scenario: Verify Home Page in Iship Mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Australia" and stay on the current page
    Then the welcome message should include "Australia"
    And I close the welcome mat if it's visible
    Then I verify the basic attributes of Iship Home page
    # Notes:
    # 1a. Welcome mat should be displayed with country flag, name & regional language.
    # 1b. Iship hp should be displayed with country symbol.
    # 1c. User should be signed out and beauty category shouldn't be displayed.
