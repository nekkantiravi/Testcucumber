Feature: Secure-m Sign in page error checking

  @sign_in @use_regression @domain_customer @use_new_regression @domain_mew_foundation @mew_foundation @securem_pipeline @migrated_to_sdt
  Scenario: Invalid email addresses on secure-m sign in page
    Given I visit the mobile web home page
    When I navigate the global navigation menu as follows:
      | Wallet|
    Then I should see secure-m sign in page
    And I should see a password error message for the following usernames for secure-m
      | username                              | error_message                                                    |
      | me@                                   | Please enter your email address in this format: jane@company.com |
      | @example.com                          | Please enter your email address in this format: jane@company.com |
      | me@example..com                       | Please enter your email address in this format: jane@company.com |
      | me\@example.com                       | Please enter your email address in this format: jane@company.com |
      | Abc.example.com                       | Please enter your email address in this format: jane@company.com |
      | A@b@c@example.com                     | Please enter your email address in this format: jane@company.com |
      | a"b(c)d,e:f;g<h>i[j\k]l@example.com   | Please enter your email address in this format: jane@company.com |
      | just"not"right@example.com            | Please enter your email address in this format: jane@company.com |
      | this is"not\allowed@example.com       | Please enter your email address in this format: jane@company.com |
      | this\ still\"not\\allowed@example.com | Please enter your email address in this format: jane@company.com |

  @sign_in @use_regression @domain_customer @use_new_regression @domain_mew_foundation @mew_foundation @securem_pipeline @migrated_to_sdt
  Scenario: Invalid Password on the secure-m sign in page
    Given I visit the mobile web home page
    When I navigate the global navigation menu as follows:
      | Wallet|
    Then I should see secure-m sign in page
    And I should see a password error message for the following passwords for secure-m
      | password           | error_message                                                                                                   |
      | aa aaa             | Your password must be between 7-16 characters, and cannot include . , - \| \ / = _ or spaces. Please try again. |
      | 123456789012345678 |  |

  @sign_in @use_regression @domain_customer @use_new_regression @domain_mew_foundation @mew_foundation @securem_pipeline @migrated_to_sdt
  Scenario: Four unsuccessful attempts to sign in on secure-m sign in page
    Given I visit the mobile web home page
    When I navigate the global navigation menu as follows:
      | Wallet|
    Then I should see secure-m sign in page
    When I fail to login 4 times
    Then forgot password overlay is presented


  @sign_in @use_regression @domain_customer @use_new_regression @domain_mew_foundation @mew_foundation @securem_pipeline @migrated_to_sdt @run_this
  Scenario: Email/Password combination not found on secure-m sign in page
    Given I visit the mobile web home page
    When I navigate the global navigation menu as follows:
      | Wallet|
    Then I should see secure-m sign in page
    When I login from modal with no profile
    Then an alert for incorrect user with the message:
      |That email address/password combination is not in our records.|
    When I touch secure-m "forgot password" Button
    Then I'm on the forgot password page