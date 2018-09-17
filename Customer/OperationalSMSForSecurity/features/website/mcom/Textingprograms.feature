###########################################################################
    # Story: B-75834 ::MCOM UI: Profile Creation: Display texting programs on profile creation page
    # Author: QE Team
    # ###########################################################################

Feature: As a customer I should be able to see texting programs when I opted for SMS alerts.

   # Pre-Requisite:: kill switch: operationSMSForSecurityEnabled=true

  @domain_customer @project_Operational_SMS_for_security @release_17H
  Scenario: Verify that user should see texting programs when he opted for SMS alerts
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I select text alert checkbox and enters mobile number
    Then I should see texting programs as below
      | promo_alerts_checkbox    |
      | security_alerts_checkbox |

  @domain_customer @project_Operational_SMS_for_security @release_17H
  Scenario Outline: Verify that user should create profile successfully by selecting atleast one of the text alerts
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    When I select "<alert_links>" checkbox
    And I select create profile button
    Then I should see user logged in to account successfully
    Examples:
      | alert_links              |
      | promo_alerts_checkbox    |
      | security_alerts_checkbox |

  @domain_customer @project_Operational_SMS_for_security @release_17H
  Scenario: Verify that user should see error message when trying to create profile without entering mobile number
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and don't enter mobile number
    And I select create profile button
    Then I should see phone number error message as below
    | Your phone number must be entered in this format: 800-555-1212 and may not all be the same number. Do not use (parentheses) for the area code. Please try again. |

  @domain_customer @project_Operational_SMS_for_security @release_17H
  Scenario: Verify error message when user doesn't opt any SMS Alerts
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And I select create profile button
    Then I should see the error message as below
    |Please select the text alerts you'd like to receive |

  @wip @domain_customer @project_Operational_SMS_for_security @release_
  Scenario: Verify that phone number reset occurs appropriately upon unchecking provide your mobile number field
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And again I Uncheck Mobile Number field
    Then entered mobile number should disappear and reset occurs

  @wip @domain_customer @project_Operational_SMS_for_security @release_
  Scenario Outline: Verify that reset occurs upon checking and unchecking phone number field
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    When I select "<alert_links>" checkbox
    And again I Uncheck Mobile Number field
    Then entered details should disappear in the mobile number field
    Examples:
      | alert_links              |
      | promo_alerts_checkbox    |
      | security_alerts_checkbox |





