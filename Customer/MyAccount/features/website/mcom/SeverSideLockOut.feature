# Author: UPI BBQA team
# Date Created: 08/04/2014
# Date Signed Off:

Feature: Server Side Lockout MCOM

     #MCOM-77751
  @domain_customer @myaccount_7 @use_regression  @migrated_to_sdt
  Scenario: Verify Security Q&A overlay when signed in (existing user))
    Given I visit the web site as a guest user
    And I create a new profile
    And I navigate to my profile page
    And I update the valid address gender and security answer
    And I sign out from my current profile
    And I delete user password hint from DB
    When I sign in with previously credentials generated
    Then I should see security Q&A setup
    When I update the valid security answer
    Then I should see security Q&A updated in profile

  @domain_customer @use_regression @myaccount_7 @migrated_to_sdt
  Scenario: User Resets Password successfully from Registry SignIn Page
    Given I visit the web site as a registry user
    And I sign out from my current profile
    When I navigate to registry signin page
    And I sign in with invalid password on registry sign in page
    Then I go to the Registry Forgot Password page