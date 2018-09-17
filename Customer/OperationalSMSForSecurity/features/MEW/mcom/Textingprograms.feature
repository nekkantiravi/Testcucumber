###########################################################################
    # Story: B-75931 ::MCOM UI MEW2: Display texting programs on profile creation page.
    # Author: QE Team
    # ###########################################################################

Feature: As a customer I should be able to see texting programs when I opted for SMS alerts.

   # Pre-Requisite:: kill switch: operationSMSForSecurityEnabled=true

  @domain_customer @project_Operational_SMS_for_security @release_17L
  Scenario: Verify that user should see text programs when he opted for SMS alerts
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I select sms alerts checkbox and enters mobile number
    Then I should see text programs as below
      | promo_alerts_checkbox    |
      | security_alerts_checkbox |

  @domain_customer @project_Operational_SMS_for_security @release_17L
  Scenario Outline: Verify that user should create profile successfully by selecting atleast one of the text alerts
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I enter user details for mew
    And I select sms alerts checkbox and enters mobile number
    When I selects "<text_alerts>" checkbox
    And I select create account button
    Then I should see user logged in to account successfully
    Examples:
      | text_alerts              |
      | promo_alerts_checkbox    |
      | security_alerts_checkbox |

  @domain_customer @project_Operational_SMS_for_security @release_17L
  Scenario: Verify that user should see error message when trying to create profile without entering mobile number
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I enter user details for mew
    And I select sms alerts checkbox and don't enter mobile number
    And I select create account button
    Then I should see phone number error message as below
      | Please enter all 10 digits in your phone number. |

  @wip @domain_customer @project_Operational_SMS_for_security @release_17L
  Scenario: Verify error message when user doesn't opt for any SMS Alerts
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I enter user details for mew
    And I select sms alerts checkbox and enters mobile number
    And I select create account button
    Then I should see the error message as below
      | Please select the text alerts you'd like to receive |

  @wip @domain_customer @project_Operational_SMS_for_security @release_17L
  Scenario: Verify that phone number reset occurs appropriately upon unchecking provide your mobile number field
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I enter user details for mew
    And I select sms alerts checkbox and enters mobile number
    And again I Uncheck sms alerts checkbox
    Then entered mobile number should disappear and reset occurs

  @wip @domain_customer @project_Operational_SMS_for_security @release_17L
  Scenario Outline: Verify that reset occurs upon checking and unchecking phone number field
    Given I visit the mobile web site as a guest user
    When I navigate to the sign-in page
    And I navigate to the create profile page
    And I enter user details for mew
    And I select sms alerts checkbox and enters mobile number
    When I selects "<text_alerts>" checkbox
    And again I Uncheck sms alerts checkbox
    Then entered details should disappear in the mobile number field
    Examples:
      | text_alerts              |
      | promo_alerts_checkbox    |
      | security_alerts_checkbox |

