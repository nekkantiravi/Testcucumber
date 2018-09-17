###########################################################################
    # Story: B-85785 ::MCOM UI:Display complete your profile overlay
    # Author: QE Team
    # ###########################################################################

  Feature: Prompt a customer to enter their Mobile # and subscribe to Security text message alerts when they login
    to their account.

  # Pre-Requisite:: kill switch: accountAutoCompletionForSecurityEnabled=true

    @domain_customer @project_Operational_SMS_for_security @release_17P
    Scenario: Verify that user should get account completion overlay when he signed in for third time
      Given I visit the web site as a guest user
      When I click on "my account" link in the header
      And I navigate to create profile page
      And I enter profile details
      And I select create profile button
      Then I should see user logged in to account successfully
      When I sign out from my current web site profile
      And I sign in with my existing profile
      Then I should see security Q&A overlay
      And  I sign out from my current web site profile
      When I sign in with my existing profile
      Then I should see account completion overlay

    @domain_customer @project_Operational_SMS_for_security @release_17P
    Scenario: Verify that account completion overlay should be displayed for the user who has subscribed only Promo alert
      Given I visit the web site as a guest user
      When I click on "my account" link in the header
      And I navigate to create profile page
      And I enter profile details
      And I select text alert checkbox and enters mobile number
      When I select "promo_alerts_checkbox" checkbox
      And I select create profile button
      Then I should see user logged in to account successfully
      When I sign out from my current web site profile
      And I sign in with my existing profile
      Then I should see security Q&A overlay
      And  I sign out from my current web site profile
      When I sign in with my existing profile
      Then I should see account completion overlay

    @domain_customer @project_Operational_SMS_for_security @release_17P
      Scenario Outline: Verify that user can perform actions on account completion overlay
      Given I visit the web site as a guest user
      When I click on "my account" link in the header
      And I navigate to create profile page
      And I enter profile details
      And I select text alert checkbox and enters mobile number
      When I select "promo_alerts_checkbox" checkbox
      And I select create profile button
      Then I should see user logged in to account successfully
      When I sign out from my current web site profile
      And I sign in with my existing profile
      Then I should see security Q&A overlay
      And  I sign out from my current web site profile
      When I sign in with my existing profile
      Then I should see account completion overlay
      When I click "<action_button>" on account completion overlay
      Then I should land on home page
      Examples:
      | action_button                  |
      | submit_sms_form                |
      | complete_profile_cancel_button |
      | close_overlay                  |





