#Author: Amol Ray
#Migrated by :
#Date Created: 08/31/2017

Feature: Create profile while guest checkout

  Background:
    Given I visit the mobile web site as a guest user
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the payment page as a "guest" user

  @domain_customer,@project_salab,@priority_high @use_regression,@option2
  Scenario: Verify create profile checkbox on RC checkout payment info page
    Given I am on checkout payment page I should see the create profile checkbox
    When I checked create profile checkbox I should see the create profile form

  @domain_customer,@project_salab,@priority_high @use_regression,@option2
  Scenario: Verify create profile option inline error on checkout payment info page
    Given I am on checkout payment page I should see the create profile checkbox
    When I checked create profile checkbox I should see the create profile form
    Then I select continue button on guest payment page
    And I verify inline error message while creating profile on checkout payment info page
      | error_pwd   | This is required |

  @domain_customer,@project_salab,@priority_high @use_regression,@option2
  Scenario: Verify the email exist error on RC page
    Given I am on checkout payment page I should see the create profile checkbox
    When I checked create profile checkbox I should see the create profile form
    And I update email with already exist mail Id
      | error_email  | An account exists for this email address. Please enter a different email or uncheck the box to continue as a guest.|

  @domain_customer,@project_salab,@priority_high @use_regression,@option2
  Scenario: Create profile on checkout payment info page
    Given I am on checkout payment page I should see the create profile checkbox
    When I checked create profile checkbox I should see the create profile form
    When I enter payment details on payment page
    And I enter all required data on create profile form
    Then I select continue button on guest payment page
    When I place an order
    Then I should see order confirmation section displayed with order details

  @domain_customer,@project_salab,@priority_high @use_regression,@option2
  Scenario: Create profile on checkout payment info page with CC server side error
    Given I am on checkout payment page I should see the create profile checkbox
    When I checked create profile checkbox I should see the create profile form
    Then I modify the payment info with wrong data
    And I enter all required data on create profile form
    Then I select continue button on guest payment page
    And I validated profile created on RC payment page
      |create_profile_msg | Your account has been created.|

  @domain_customer,@project_salab,@priority_high @use_regression,@option4
  Scenario: Validate create profile marketing banner and form on order confirmation page
    And I fill in payment information on guest payment page
    And I select continue button on guest payment page
    When I place an order
    Then I should see order confirmation section displayed with order details
    And I see the create profile marketing banner and create profile form

  @domain_customer,@project_salab,@priority_high @use_regression,@option4
  Scenario: Create profile on order confirmation page
    And I fill in payment information on guest payment page
    And I select continue button on guest payment page
    When I place an order
    Then I should see order confirmation section displayed with order details
    And I enter all required data on order confirmation create profile form
    Then I click on create profile button
      |create_profile_msg | Your account has been created.|


