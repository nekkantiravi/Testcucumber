Feature: Verify Credit Card Apply now Page

  @dsv_desktop_sev1
  Scenario: Verify navigation of apply now link from Learn More & Apply footer link as a guest user
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the "apply & learn more" page from footer
    When I select "apply_now" link on apply and learn more page
    Then I should redirect to "apply now" page
