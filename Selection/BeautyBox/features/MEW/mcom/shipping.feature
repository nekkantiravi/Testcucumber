
Feature: Beauty Box Shipping address feature


  @wip
  Scenario Outline: As a customer I should be able to verify order
    Given I navigate to given "<URL>" url
    When I enter an existing email id
    And I should be able to add RegistredCheckout cookies
    Then I should verify order "<sub_total>" ,"<order_total>" and click on "<subscribe>" button

    Examples:
      |URL|sub_total|order_total|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|$12|$12.6|


  @wip1
  Scenario Outline: As a customer I should be able to edit and validate the shipping address
    Given I navigate to given "<URL>" url
    When I click on the change button and edit button
    When I enter an incorrect shipping address and save
    Then I should verify the shipping address global error

    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|

  @wip2
  Scenario Outline: As a customer I should be able to edit and validate the shipping address
    Given I navigate to given "<URL>" url
    When I click on the change button and edit button
    When I enter an incorrect zipcode in shipping address and save
    Then I should verify the shipping address warning error

    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|

  @tag
  Scenario Outline: As a customer I should be able to edit the shipping address
    Given I navigate to given "<URL>" url
    When I enter an existing email id
    #And I should be able to add RegistredCheckout cookies
    And I click on the change button and edit button
    Then I should verify the shipping address as below

    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|


    @tag
    Scenario Outline: As a signedIn customer I should be able to verify order summary
      Given I navigate to given "<URL>" url
      When I enter an existing email id
      Then I should verify order "<sub_total>" ,"<order_total>" and click on "<subscribe>" button

      Examples:
        |URL|sub_total|order_total|
        |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|$12|$12.6|


    @tag
     Scenario Outline: Error message if customer enters wrong password
     Given I navigate to given "<URL>" url
     When I enter wrong  password
     Then I should view "Your password is case sensitive; please make sure CAPS lock is turned off." error msg


      Examples:
        |URL|
        |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|


  @tag
  Scenario Outline: As a customer i should validate shipping fields
    Given I navigate to given "<URL>" url
    When I enter an existing email id
    And I click on the change button and edit button
    Then I should be able to count the address list and select any address
    Then I should verify the shipping address as below

    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|




  @wip
  Scenario Outline: As a customer i should validate shipping address
    Given I navigate to given "<URL>" url
    When I enter an existing email id
    And I click on the change button and edit buttons
    When I clear firstname and click save button
    Then I should view "Please enter a first name." error for first name
    And I click on the change button and edit buttons
    When I clear lastname and click save button
    Then I should view "Please enter a last name." error for first name


    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|


  @tag
  Scenario Outline: Verify error messages for empty fields on Create Profile Page
    Given I navigate to given "<URL>" url
    And I navigate to create profile page
    When I create a new profile with missing first_name, last_name, email and primary_phone_number
    Then I should see inline error messages
      | Please enter your first name.  |
      | Please enter your last name.   |
      | Please enter your email address in this format: jane@company.com. Thank you.  |
      | Your phone number must be entered in this format: 800-555-1212 and may not all be the same number. Do not use (parentheses) for the area code. Please try again.  |

  Examples:
  |URL|
  |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|


  @tag
  Scenario Outline: As a user, I should receive same phone number error message if i enter same digit for phone when creating new account
    Given I navigate to given "<URL>" url
    And I navigate to create profile page
    When I create a new account with all same digits for phone
    Then I should receive "Your phone number can't be all the same number and must be entered in this format: 800-555-1212. Please try again." same phone number error message


  Examples:
  |URL|
  |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|


  @tag
  Scenario Outline: As a user, I should receive weak pwd error message if i give a simple pwd when creating new account
    Given I navigate to given "<URL>" url
    And I navigate to create profile page
    And I create a new profile with simple password
    Then I should receive "Sorry, but your password isn't strong enough. For your security, please make it more complex." a weak pwd error message
    When I update weak pwd with complex pwd

  Examples:
  |URL|
  |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|

   @tag
  Scenario Outline: As a user, I should receive at least 13 years old error message if dob entered is less than 13
    Given I navigate to given "<URL>" url
    And I navigate to create profile page
    When I create a new profile and my age is less that 13 years
    Then I should receive "Please update the boxes highlighted in red." error message

    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|

  @tag
  Scenario Outline: As a user, When I try to create a profile by providing invalid data, I should receive inline error messages
    Given I navigate to given "<URL>" url
    And I navigate to create profile page
    When I try to create a new account with invalid data
    Then I should see invalid data inline error messages
      | Sorry, first name may only contain letters and hyphens and cannot exceed 20 characters.                                                                          |
      | Sorry, last name may only contain letters and hyphens and cannot exceed 30 characters.                                                                           |
      | Please enter your email address in this format: jane@company.com. Thank you.                                                                                     |
      | Please enter a valid date of birth.	                                                                                                                             |
      | Your phone number must be entered in this format: 800-555-1212 and may not all be the same number. Do not use (parentheses) for the area code. Please try again. |

    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|


  @tag3
  Scenario Outline: As a user, I create a new profile i should be able to view checkout page
    Given I navigate to given "<URL>" url
    And I navigate to create profile page
    And I create a new profile for BB
    Then I should verify order "<sub_total>" ,"<order_total>" and click on "<subscribe>" button

    Examples:
      |URL|sub_total|order_total|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|$12|$12.6|


  @trinath
  Scenario Outline: As a customer i should validate shippin
    Given I navigate to given "<URL>" url
    When I enter an existing email id
    And I click on the change button and edit buttons

    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|


  @tag2
  Scenario Outline: As a customer edit shiiping validation for
    Given I navigate to given "<URL>" url
    When I enter an existing email id
    And I click on the change button and edit buttons

    Examples:
      |URL|
      |mcom-beautydev.c4d.devops.fds.com/subscription/beautybox/checkout|



