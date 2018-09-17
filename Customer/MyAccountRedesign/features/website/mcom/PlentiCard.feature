Feature: Plenti feature on Redesigned MyAccount dashboard

  Background:
    Given I visit the web site as a signed-in user
    And I add ResponsiveMyAccount experiment cookie
    And I navigate to my account page

  @sa_lab @use_regression @domain_customer
  Scenario: Verify plenti card elements for user without plenti account
    When I refresh current page
    Then I should see plenti card elements
    And I should see default message as:
    """
    If you have a Plenti account, link it
    using your phone or Plenti numbers.
    """
    And I should see join reward program message:
    """
    Join our rewards program.
    Learn More
    """

  @sa_lab @use_regression @domain_customer
  Scenario: Verify add plenti using valid card number
    When I refresh current page
    And I add plenti using valid plenti card number
    Then I should see card info in the plenti widget

  @sa_lab @use_regression @domain_customer
  Scenario: Verify add plenti using valid phone number
    When I refresh current page
    And I add plenti using valid phone number
    Then I should see card info in the plenti widget

  @sa_lab @use_regression @domain_customer
  Scenario Outline: Verify error message for adding plenti using invalid card number
    When I refresh current page
    And I add plenti using invalid card <number>
    Then I should see plenti error message as <errormessage>
    Examples:
      | number           | errormessage                          |
      | 1234567890123456 | No Plenti # found - please try again! |
      | 41523434         | Plenti # must be 16 numbers           |

  @sa_lab
  @mvp8 @use_regression @domain_customer
  Scenario Outline: Verify error message for adding plenti using invalid phone number
    When I refresh current page
    And I add plenti using invalid phone <number>
    Then I should see plenti error message as <errormessage>
    Examples:
      | number     | errormessage                          |
      | 2141413211 | No Plenti # found - please try again! |
      | 324234325  | Phone Number must be 10 numbers.      |

  @sa_lab @use_regression @domain_customer
  Scenario: Verify disable add plenti by phone number for more than 5 attempts
    When I refresh current page
    And I add plenti 6 times using invalid phone number:
    """
    0000000000
    """
    Then I should see error message as:
    """
    We’re sorry, we did not find a Plenti # associated to the phone number you entered. Please call Plenti Customer Service at 1-855-PLENTI1.
    """
    And I should see add plenti button disabled

  @sa_lab @use_regression @domain_customer
  Scenario: Verify disable add plenti by card number for more than 5 attempts
    When I refresh current page
    And I add plenti 6 times using invalid phone number:
    """
    111111111111111
    """
    Then I should see error message as:
    """
    We’re sorry, we did not find a Plenti # associated to the phone number you entered. Please call Plenti Customer Service at 1-855-PLENTI1.
    """
    And I should see add plenti button disabled
