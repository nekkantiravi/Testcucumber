Feature: Verify Credit Card Apply now Page


  Scenario: Verify navigation of apply now link from Learn More & Apply now page as a guest user
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate to the "apply & learn more" page from footer
    And I select "apply_now" link on apply and learn more page
    Then I should redirect to "apply now" page
