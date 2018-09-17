Feature: Verify my profile functionality
  @dsv_desktop_sev2 @use_regression @domain_customer
  Scenario: My Profile - Rendering Create Profile Page for a guest user
    Given I visit the web site as a guest user
    And I navigate to my account page
    When I navigate to create profile page
    Then I verify the basic attributes of the create profile page

  @dsv_desktop_sev2 @use_regression @domain_customer
  Scenario: Verify error messages when Create Profile mandatory fields are left blank
    Given I visit the web site as a guest user
    And I navigate to my account page
    When I navigate to create profile page
    And I verify error message while creating profile with missing fields
      | first_name        | Please enter first name.     |
      | last_name         | Please enter last name.      |
      | email             | Please enter an email address. |
      #| security_question | Please select a security question from the drop down box before proceeding.|
      #| security_answer   | The answer to the selected security question must be between 2 and 20 characters. Please try again.|
      | dob               | The Birth Date you entered is invalid.|

