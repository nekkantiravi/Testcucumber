Feature: Sign In Modal


  @mingle-21730 @sign_in @use_regression @domain_customer @use_new_regression @domain_mew_foundation @mew_foundation @securem_pipeline @migrated_to_sdt
  Scenario:  Verify items on 'Sign In' modal
    Given I visit the mobile web home page
    And I am on the footer secure-m sign in page
    Then login modal is opened with header sign in
    Then login modal page items are displayed:
      | Item                                    |
      | Email Address                           |
      | Password                                |
      | Passwords are case sensitive            |
      | SIGN IN                                 |
      | Forgot Your Password?                   |
      | CREATE AN ACCOUNT                       |
      | Secure site. Learn More                 |
      | Privacy Practices                       |



  @mingle-21730 @sign_in @use_regression @domain_customer @use_new_regression @domain_mew_foundation @mew_foundation @securem_pipeline @migrated_to_sdt @run_this
  Scenario: Verify I can log in
    Given I visit the mobile web home page
    And I am on the footer secure-m sign in page
    And I enter a valid email and password
    And I touch modal sign in Button
    Then I should see home page
    Then I should be logged in




