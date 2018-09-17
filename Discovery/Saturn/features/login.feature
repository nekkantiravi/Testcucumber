Feature: Login and logout of Saturn as valid users

Scenario: Login and log out of Saturn as a valid Admin user.
  Given I login to Saturn as an "admin" user
    Then I log out of Saturn

Scenario: Login and log out of Saturn as a valid non-Admin user
  Given I login to Saturn as an "non_admin" user
  Then I log out of Saturn

