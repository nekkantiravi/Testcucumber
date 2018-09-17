Feature: Verify Iship functionality

  @dsv_desktop_sev2
  Scenario: Verify that user is signed out when signed in user in domestic mode is navigated to international Mode
    Given I visit the web site as a registered user in "domestic" mode
    When I navigate back to "home" page
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify the basic attributes of Iship Home page

    # Notes:
    # Verify user is signed out
    # Beauty category shouldn't be displayed.
    # Test case steps
    # 1. Select the country e.g. Australia and "click
  # on save & continue" button.
  # Test case expected result
  # 1a. Welcome mat should be displayed with country flag, name & regional language.
  # 1b. Iship hp should be displayed with country symbol.
  # 1c. User should be signed out and beauty category shouldn't be displayed.