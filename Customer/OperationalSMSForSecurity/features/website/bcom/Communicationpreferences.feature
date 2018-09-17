###########################################################################
    # Story: B-77063 ::BCOM UI:: My Preferences - summary and detail view of Text Preferences
    # Author: QE Team
    # ###########################################################################

Feature: On Preferences page, a customer has a summary view of what Text Programs they are opted into
  and not opted into.

# Pre-Requisite:: kill switch: smsSecurityPreferencesEnabled=true

  @domain_customer @project_Operational_SMS_for_security @release_17Q
  Scenario: Verify text program status on preferences page when user creates a profile by selecting both promo and security
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
    Then I should see text Alerts status as below with mobile number
      | Bloomingdale's Alerts | Security Alerts |
      | Enabled               | Pending         |

  @domain_customer @project_Operational_SMS_for_security @release_17Q
  Scenario: Verify text program status on preferences page when user creates a profile by selecting only promo alert
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
    Then I should see text Alerts status as below with mobile number
      | Bloomingdale's Alerts | Security Alerts |
      | Enabled               | Disabled        |

  @domain_customer @project_Operational_SMS_for_security @release_17Q
  Scenario: Verify text program status on preferences page when user creates a profile by selecting only security alert
    Given I visit the web site as a guest user
    When I click on "my account" link in the header
    And I navigate to create profile page
    And I enter profile details
    And I select text alert checkbox and enters mobile number
    And I select "security_alerts_checkbox" checkbox
    And I select create profile button
    Then I should see user logged in to account successfully
    When I navigate to Preferences page directly from My Account page
    And I navigate to Notifications preferences sub page
    Then I should see text Alerts status as below with mobile number
      | Bloomingdale's Alerts | Security Alerts |
      | Disabled              | Pending         |

  @wip @domain_customer @project_Operational_SMS_for_security @release_17Q
  Scenario: Verify warning(yellow) message on preferences page when user creates a profile
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
    And I click on 'Edit' option for Text Preference
#      Then I should see warning message as below


#    @wip @domain_customer @project_Operational_SMS_for_security @release_17Q
#    Scenario: Verify information(blue) message on preferences page when user selects promo on edit summary page

  #    @wip @domain_customer @project_Operational_SMS_for_security @release_17Q
  Scenario: Verify text program status on preferences page when user doesn't like to receive texts
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
    And I click on 'Edit' option for Text Preference
    Then I select 'I'd prefer not to receive texts at this time' radio button and save
    Then I should see text Alerts status as below without mobile number
      | Bloomingdale's Alerts | Security Alerts  |
      | Disabled              | Disabled         |


    Scenario: Verify text program status on preferences page when user chooses to unsubscribe promo alert on preferences page
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
      And I click on 'Edit' option for Text Preference
      Then I select Bloomingdale's Alerts checkbox to unsubscribe and save








