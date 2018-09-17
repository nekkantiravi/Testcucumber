Feature: Simplified Profile Creation

  @domain_customer @use_regression
  Scenario: As a user, When I try to create a profile by using existing email id, I should receive existing email id error message
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    And I navigate to create profile page
    And I create a new profile
    Then I should be navigated to My Account Page
    When I sign out from my current profile
    And I navigate to signin page of "SITE" mode
    And I navigate to create profile page
    When I enter an existing email id
    Then I should see "An account exists for this email address. Enter a different address or sign in" error message
    When I select sign in link from email error message
    Then I should see Sign In Page

  @domain_customer @use_regression
  Scenario: As a user, I should receive same phone number error message if i enter same digit for phone when creating new account
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    And I navigate to create profile page
    When I create a new account with all same digits for phone
    Then I should receive "Your phone number can't be all the same number and must be entered in this format: 800-555-1212. Please try again." same phone number error message

  @domain_customer @use_regression
  Scenario: As a user, I should receive weak pwd error message if i give a simple pwd when creating new account
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    And I navigate to create profile page
    And I create a new profile with simple password
    Then I should receive "Sorry, but your password isn't strong enough. For your security, please make it more complex." a weak pwd error message
    When I update weak pwd with complex pwd
    Then I should be navigated to My Account Page
    And I should see user logged in to account successfully

  @domain_customer @use_regression
  Scenario: Simplified profile creation
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    And I navigate to create profile page
    And I create a new profile
    Then I should be navigated to My Account Page
    And I should see user logged in to account successfully
    When I navigate to my profile page
    Then I should see date of birth auto-populated

  @domain_customer @use_regression
  Scenario: Verify error messages for empty fields on Create Profile Page
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    And I navigate to create profile page
    When I create a new profile with missing first_name, last_name, email and primary_phone_number
    Then I should see inline error messages
      | Please enter your first name.  |
      | Please enter your last name.   |
      | Please enter your email address in this format: jane@company.com. Thank you.  |
      | Your phone number must be entered in this format: 800-555-1212 and may not all be the same number. Do not use (parentheses) for the area code. Please try again.  |

  @domain_customer @use_regression
  Scenario: As a user, I should receive at least 13 years old error message if dob entered is less than 13
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    And I navigate to create profile page
    When I create a new profile and my age is less that 13 years
    Then I should receive "Please update the boxes highlighted in red." error message

  @domain_customer @use_regression
  Scenario: As a user, When I try to create a profile by providing invalid data, I should receive inline error messages
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    And I navigate to create profile page
    When I try to create a new account with invalid data
    Then I should see invalid data inline error messages
      | Sorry, first name may only contain letters and hyphens and cannot exceed 20 characters.                                                                          |
      | Sorry, last name may only contain letters and hyphens and cannot exceed 30 characters.                                                                           |
      | Please enter your email address in this format: jane@company.com. Thank you.                                                                                     |
      | Please enter a valid date of birth.	                                                                                                                             |
      | Your phone number must be entered in this format: 800-555-1212 and may not all be the same number. Do not use (parentheses) for the area code. Please try again. |

    
