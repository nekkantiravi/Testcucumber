Feature: Add link to customer preferences page on MEW 1.0 my account page below My Address Book and remove Preferred
  Store section

  # Pre-Requisite:: kill switch: preferencesEnabled=true

  Scenario: Verify if 'Preferred Store' section is removed from My Account page
    Given I visit the mobile web site as a registered user without add CC
    And I navigate to My Account mobile page
    Then I don't see 'Preferred Store' card

  Scenario: Verify if 'My Preferences' link redirects to correct page
    Given I visit the mobile web site as a registered user without add CC
    And I navigate to My Account mobile page
    When I tap on 'My Preferences' link on My Account page
    Then I'm redirected to /account/preferences/ page

    ####    Coremetrics scenarios
  Scenario: Verify CM tag when user clicks on 'My Preferences' link
    Given I visit the mobile web site as a registered user without add CC
    And I navigate to My Account mobile page
    When I tap on 'My Preferences' link on My Account page
    Then I'm navigated to url which has “cm_sp=my_account-_-loyallist-_-my_preferences” coremetrics tag appended