Feature: Sign In Modal error checking


  @mingle-23944 @sign_in @component_test @p1 @use_regression @domain_customer @use_new_regression @domain_mew_foundation @mew_foundation @securem_pipeline @migrated_to_sdt @run_this
  Scenario: Invalid email address on sign in modal
    Given I am on the footer secure-m sign in page
    Then modal messages appear for the following usernames
      | username                              | error_message                                                       |
      | me@                                   | Your email address must be entered in this format: jane@company.com |
      | @example.com                          | Your email address must be entered in this format: jane@company.com |
      | me@example..com                       | Your email address must be entered in this format: jane@company.com |
      | me\@example.com                       | Your email address must be entered in this format: jane@company.com |
      | Abc.example.com                       | Your email address must be entered in this format: jane@company.com |
      | A@b@c@example.com                     | Your email address must be entered in this format: jane@company.com |
      | a"b(c)d,e:f;g<h>i[j\k]l@example.com   | Your email address must be entered in this format: jane@company.com |
      | just"not"right@example.com            | Your email address must be entered in this format: jane@company.com |
      | this is"not\allowed@example.com       | Your email address must be entered in this format: jane@company.com |
      | this\ still\"not\\allowed@example.com | Your email address must be entered in this format: jane@company.com |


  @mingle-23944 @sign_in @component_test @p1 @use_regression @domain_customer @use_new_regression @domain_mew_foundation @mew_foundation @securem_pipeline
  Scenario: Invalid password on sign in modal
    Given I am on the footer secure-m sign in page
    Then no modal messages appear for the following passwords
      | password          | error_message                                                                                                             |
      | aa aaa            | Your password must be between 5-16 alphanumeric characters, and cannot include spaces. Example: jane47. Please try again. |
      | AAA@AA            | Your password must be between 5-16 alphanumeric characters, and cannot include spaces. Example: jane47. Please try again. |
      | aaaaaaaaaaaaaaaa1 | Your password must be between 5-16 alphanumeric characters, and cannot include spaces. Example: jane47. Please try again. |


  @mingle-23944 @sign_in @component_test @p1 @use_regression @domain_customer @use_new_regression @domain_mew_foundation @mew_foundation @securem_pipeline
  Scenario: Four unsuccessful attempts to sign in on modal
    Given I am on the footer secure-m sign in page
    And I fail to login 4 times
    Then forgot password overlay is presented
    # interaction for overlay is covered in story details #21731


  @mingle-23944 @sign_in @component_test @p1 @use_regression @domain_customer @use_new_regression @domain_mew_foundation @mew_foundation @securem_pipeline
  Scenario: Email/Password combination not found on sign in modal
    Given I am on the footer secure-m sign in page
    And I login from modal with no profile
    Then an alert for incorrect user with the message:
      |That email address/password combination is not in our records.|
#    Then I touch modal "Forgot Password" Button
#    Then I'm on the forgot password page
    # interaction for overlay is covered in story details #21731