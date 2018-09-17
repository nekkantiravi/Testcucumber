
Feature: EGC balance
  #MCOM-91993
  @use_regression @wip @please_automate @domain_marketing
  Scenario: Rendering Gift card Balance Page when services are in Real mode
    Given I visit the web site as a registered user
    When I navigate to the Gift Card Balance page from My Account
    Then I should see basic attributes in Gift card balance page

  #MCOM-91993
  @use_regression @wip @please_automate @domain_marketing
  Scenario: Rendering Gift card Balance - Gift boxes and messages Page
    Given I visit the web site as a registered user
    When I navigate to the Gift Card Balance page from My Account
    And I navigate to the Gift boxes and messages tab in Gift Card Balance page
    Then I should see basic attributes of Gift boxes and messages tab

  @use_regression @wip @please_automate @domain_marketing
  Scenario: Rendering Gift card Balance - Terms and Conditions Page
    Given I visit the web site as a registered user
    When I navigate to the Gift Card Balance page from My Account
    And I navigate to the Terms and Conditions tab in Gift Card Balance page
    Then I should see basic attributes of Terms and Conditions tab