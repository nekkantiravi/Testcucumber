Feature: Beauty appointment


  Scenario: As a beauty user I should be able to select a store
    Given I am on store locations offering all kinds of appointments
    When  I select a store offering beauty appointment and click continue
    Then  I should see beauty appointment

  @tag
  Scenario: As a beauty user I should be able to schedule appointment
    Given I have selected beauty appointment
    When  I enter my contact information and click Schedule it
    Then  I should see beauty appointment confirmation



