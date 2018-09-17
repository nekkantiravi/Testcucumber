Feature: Verify softlock scenarios on sign in pages

  @domain_ae @use_regression
  Scenario: verify softlock error message on myaccount sign in page
    Given I visit the mobile web site as a guest user
    And I navigate to mew2.0 sign in modal
    And I sign in with softlocked user from sign in modal
    Then I should see the error as:
    """
    We value your security and privacy. To ensure your profile is secure, it's time to reset your password. Please follow this link to get started.
    """
    When I click the link in the error message
    Then I should navigate to forgot password page

  @domain_ae @use_regression
  Scenario: verify softlock error message on secure-m sign in page
    Given I navigate directly to secure-m sign in page
    And I sign in with softlocked user from sign in modal
    Then I should see the error as:
    """
    We value your security and privacy. To ensure your profile is secure, it's time to reset your password. Please follow this link to get started.
    """
    When I click the link in the error message
    Then I should navigate to forgot password page