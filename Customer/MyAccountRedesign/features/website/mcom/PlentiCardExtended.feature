Feature: Verify Plenti Edge use cases

  @sa_lab
  Scenario: Verify expected plenti association error for associating more than 5 users
    Given I have same plenti account associated to more than 5 user accounts
    And I visit the web site as a registered user
    And I navigate to my account page
    And I add plenti using valid plenti card number
    Then I should see error message as:
    """
    This Plenti card is already linked to the maximum number of accounts.
    """