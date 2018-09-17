Feature: Verify softlock scenarios on sign in page

  @domain_ae @use_regression @1
  Scenario: verify softlock error message on myaccount sign in page
    Given I visit the web site as a guest user
    And I navigate to signin page of "SITE" mode
    And I sign in with softlocked user
    Then I should see the error as:
    """
    We value your security and privacy. To ensure your profile is secure, it's time to reset your password. Please follow this link to get started.
    """
    When I click the link in the error message
    Then I should navigate to forgot password page

  @domain_ae @use_regression
  Scenario: verify softlock error message on checkout sign in page
    Given I navigate directly to checkout sign in page
    And I sign in with softlocked user
    Then I should see the error as:
    """
    We value your security and privacy. To ensure your profile is secure, it's time to reset your password. Please follow this link to get started.
    """
    When I click the link in the error message
    Then I should navigate to forgot password page