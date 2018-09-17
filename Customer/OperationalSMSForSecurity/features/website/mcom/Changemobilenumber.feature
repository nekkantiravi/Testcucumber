###########################################################################
    # Story: B-83197 ::MSP Preference : Publish Message to OES in case customer update the Mobile no
    # Author: QE Team
    # ###########################################################################

Feature: Publish message when user updates mobile number

  @use_manual @domain_customer @project_Operational_SMS_for_security @release_17P
  Scenario: when old number is optin(security alert is Enabled) and customer change number and opt in(i.e on pref page security alert is in selected state)
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And I select both text alerts
    And I select create profile button
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see security alert status as pending
#  End user should reply back to the message(optin) which he received, So that Security alert status will change from Pending to Enabled
    When I refresh the page
    Then I should see security alert status as Enabled
    And I click on edit link
    And I change mobile number and selects security alert

#  Expected: trigger COM 617 1D type S(change mobile number email and SMS to old number) and COM 617 S3D type O(security optout message to old number), COM 617 S2D type O(security optin message to new number)

  @use_manual @domain_customer @project_Operational_SMS_for_security @release_17P
  Scenario: when old number is optin(security alert is Enabled) and customer change number and does not opt in(i.e on pref page security alert is not in selected state)
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And I select both text alerts
    And I select create profile button
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see security alert status as pending
  #  End user should reply back to the message(optin) which he received, So that Security alert status will change from Pending to Enabled
    When I refresh the page
    Then I should see security alert status as Enabled
    And I click on edit link
    And I change mobile number and deselects security alert

#  Expected: trigger COM 617 1D type S(change mobile number email and SMS to old number) and COM 617 S3D type O(security optout message to old number)

  @use_manual @domain_customer @project_Operational_SMS_for_security @release_17P
  Scenario: when old number is pending(security alert is pending) and customer change number and opt in(i.e on pref page security alert is in selected state)
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And I select both text alerts
    And I select create profile button
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see security alert status as pending
    And I click on edit link
    And I change mobile number and selects security alert

#  Expected:trigger COM 617 1D type E(change mobile number email) and COM 617 S2D type O(security optin message)

  @use_manual @domain_customer @project_Operational_SMS_for_security @release_17P
  Scenario: when old number is pending(security alert is pending) and customer change number and does not opt in(i.e on pref page security alert is deselected)
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And I select both text alerts
    And I select create profile button
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see security alert status as pending
    And I click on edit link
    And I change mobile number and deselects security alert

#  Expected: trigger COM 617 1D type E(change mobile number email)

  @use_manual @domain_customer @project_Operational_SMS_for_security @release_17P
  Scenario: when old number is optout(security alert is disabled) and customer change number and opt in(i.e on pref page security alert is in selected state)
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And I select "promo_alerts_checkbox" checkbox
    And I select create profile button
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see security alert status as disabled
    And I click on edit link
    And I change mobile number and selects security alert

#  Expected: trigger COM 617 1D type E(change mobile number email) and COM 617 S2D type O(security optin message)

  @use_manual @domain_customer @project_Operational_SMS_for_security @release_17P
  Scenario: when old number is optout(security alert is disabled) and customer change number and does not opt in(i.e on pref page security alert is deselected)
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And I select "promo_alerts_checkbox" checkbox
    And I select create profile button
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see security alert status as disabled
    And I click on edit link
    And I change mobile number and deselects security alert

#  Expected: trigger COM 617 1D type E(change mobile number email)

  #  Optout scenarios
  @use_manual @domain_customer @project_Operational_SMS_for_security @release_17P
  Scenario: When old number is optin(Security alert status is Enabled), user optout security alert(i.e making disabled)
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And I select both text alerts
    And I select create profile button
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see security alert status as pending
  #  End user should reply back to the message(optin) which he received, So that Security alert status will change from Pending to Enabled
    When I refresh the page
    Then I should see security alert status as Enabled
    When I click on edit link
    And I deselects security alert
    Then I should see security alert status as disabled
#  Expected: Trigger COM 617 S3D type O(security optout message to old number)

  @use_manual @domain_customer @project_Operational_SMS_for_security @release_17P
  Scenario: When old number is optin(Security alert status is Enabled), Customer selected Maybe later radio button
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And I select both text alerts
    And I select create profile button
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see security alert status as pending
  #  End user should reply back to the message(optin) which he received, So that Security alert status will change from Pending to Enabled
    When I refresh the page
    Then I should see security alert status as Enabled
    When I click on edit link
    And I select May be later radio button
    Then I should see security alert status as disabled
#  Expected: Trigger COM 617 S3D type O(security optout message to old number)