Feature: Verify create profile functionality with few fields (MVP6)


  @mvp6 @use_regression @priority_high @domain_customer @release_16K @migrated_to_sdt
  Scenario: As a user, I should be able to sign in successfully, after successful profile creation
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should see welcome message
    When I sign out and sign back in
    And I navigate to my account page
    Then I should see "my account" page

  @mvp6 @use_regression @priority_high @domain_customer @release_16K @migrated_to_sdt
  Scenario: As a user, When I try to create a profile by using existing email id, I should receive existing email id error message
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should see welcome message
    When I sign out and navigate to Create Profile page
    And I enter an existing email id
    Then I should see existing email id error message
      | An account exists for this email address. Enter a different address or Sign In |
    When I select sign in link from email error message
    Then SignIn page should get loaded

  @mvp6 @use_regression @domain_customer @release_16K @migrated_to_sdt
  Scenario: Verify error messages for empty fields on Create Profile Page
    Given I visit the web site as a guest user
    When I create a new profile with missing first_name, last_name, email and primary_phone_number
    Then I should see inline error messages
      | field_name | mcom_err_msg                                                                                                                                                     |
      | first_name | Please enter your first name.                                                                                                                                    |
      | last_name  | Please enter your last name.                                                                                                                                     |
      | email_addr | Please enter your email address in this format: jane@company.com. Thank you.                                                                                     |
      | phone_numb | Your phone number must be entered in this format: 800-555-1212 and may not all be the same number. Do not use (parentheses) for the area code. Please try again. |

  @mvp6 @use_regression @domain_customer @release_16K @migrated_to_sdt
  Scenario: As a user, When I try to create a profile by providing invalid data, I should receive inline error messages
    Given I visit the web site as a guest user
    When I try to create a new account with invalid data
    Then I should see inline error messages
      | field_name | mcom_err_msg                                                                                                                                                     |
      | first_name | Sorry, first name may only contain letters and hyphens and cannot exceed 20 characters.                                                                          |
      | last_name  | Sorry, last name may only contain letters and hyphens and cannot exceed 30 characters.                                                                           |
      | email_addr | Please enter your email address in this format: jane@company.com. Thank you.                                                                                     |
      | dob        | Please enter a valid date of birth.                                                                                                                              |
      | phone_numb | Your phone number must be entered in this format: 800-555-1212 and may not all be the same number. Do not use (parentheses) for the area code. Please try again. |


  @mvp6 @use_regression @priority_high @domain_customer @release_16K @migrated_to_sdt
  Scenario: As a user, I should receive same phone number error message if i enter same digit for phone when creating new account
    Given I visit the web site as a guest user
    When I create a new account with all same digits for phone
    Then I should receive same phone number error message
      | Your phone number can't be all the same number and must be entered in this format: 800-555-1212. Please try again. |

  @mvp6 @use_regression @priority_high @domain_customer @release_16K @migrated_to_sdt
  Scenario: As a user, I should receive weak pwd error message if i give a simple pwd when creating new account
    Given I visit the web site as a guest user
    When I create a new profile with simple password
    Then I should receive "Sorry, but your password isn't strong enough. For your security, please make it more complex." a weak pwd error message
    When I update weak pwd with complex pwd
    Then I should see welcome message


  @mvp6 @use_regression @priority_high @domain_customer @release_16K @migrated_to_sdt
  Scenario: As a user, I should receive at least 13 years old error message if dob entered is less than 13
    Given I visit the web site as a guest user
    When I create a new profile and my age is less that 13 years
    Then I should receive "Please update the boxes highlighted in red." error message

  @mvp6 @use_regression @domain_customer @release_16K @migrated_to_sdt
  Scenario: As a user, I should be able to create new account by filling all valid details
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should see welcome message
    When I navigate to my profile page
    Then I should see date of birth auto-populated with valid data