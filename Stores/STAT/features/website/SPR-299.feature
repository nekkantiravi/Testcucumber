# Author: STAT Team
# Story: SPR-299 PR - Modify - PendingReturnService - Enforce Order#/Res# Format
# Date Created: 11/30/2017
# Date Signed Off:

@STAT @pending_returns @SPR-299 @manual
Feature: As an application I want to enforce the Order Number and Reservation Number formatting on the
  Post/Create Pending Return Service so I can ensure the accuracy of information


  Scenario: QE to verify successful response when order number is numeric and < 30 characters
    Given I am in postman
    When I POST a pending returns order number that is < 30 characters
    Then I should see the Post returned successful with a status code of 200


  Scenario: QE to verify successful response when order number is numeric and = 30 characters
    Given I am in postman
    When I POST a pending returns order number that is = 30 characters
    Then I should see the Post returned successful with a status code of 200


  Scenario: QE to verify successful response when order number is numeric and > 30 characters
    Given I am in postman
    When I POST a pending returns order number that is > 30 characters
    Then I should see the Post returned unsuccessful with a status code of 400

  Scenario: QE to verify successful response when order number is non-numeric and < 30 characters
    Given I am in postman
    When I POST a pending returns order number that is non-numeric < 30 characters
    Then I should see the Post returned unsuccessful with a status code of 404

  Scenario: QE to verify successful response when order number is non-numeric and = 30 characters
    Given I am in postman
    When I POST a pending returns order number that is non-numeric = 30 characters
    Then I should see the Post returned unsuccessful with a status code of 404

  Scenario: QE to verify successful response when order number is non-numeric and > 30 characters
    Given I am in postman
    When I POST a pending returns order number that is non-numeric > 30 characters
    Then I should see the Post returned unsuccessful with a status code of 404

  Scenario: QE to verify successful response when order number is non-numeric and include special characters
    Given I am in postman
    When I POST a pending returns order number that is non-numeric and include special characters
    Then I should see the Post returned unsuccessful with a status code of 404

  Scenario: QE to verify successful response when order number is non-numeric and include a space
    Given I am in postman
    When I POST a pending returns order number that is non-numeric and include space
    Then I should see the Post returned unsuccessful with a status code of 404

  Scenario: QE to verify successful response when reservation number is numeric and < 100 characters
    Given I am in postman
    When I POST a pending returns reservation number is numeric and < 100 characters
    Then I should see the Post returned successful with a status code of 200

  Scenario: QE to verify successful response when reservation number is numeric and = 100 characters
    Given I am in postman
    When I POST a pending returns reservation number is numeric and = 100 characters
    Then I should see the Post returned successful with a status code of 200

  Scenario: QE to verify successful response when reservation number is numeric and > 100 characters
    Given I am in postman
    When I POST a pending returns reservation number is numeric and > 100 characters
    Then I should see the Post returned unsuccessful with a status code of 404

  Scenario: QE to verify successful response when reservation number is non-numeric and < 100 characters
    Given I am in postman
    When I POST a pending returns reservation number is non-numeric and < 100 characters
    Then I should see the Post returned unsuccessful with a status code of 404

  Scenario: QE to verify successful response when reservation number is non-numeric and = 100 characters
    Given I am in postman
    When I POST a pending returns reservation number is non-numeric and = 100 characters
    Then I should see the Post returned unsuccessful with a status code of 404

  Scenario: QE to verify successful response when reservation number is non-numeric and > 100 characters
    Given I am in postman
    When I POST a pending returns reservation number is non-numeric and > 100 characters
    Then I should see the Post returned unsuccessful with a status code of 404

  Scenario: QE to verify successful response when reservation number is non-numeric and include special characters
    Given I am in postman
    When I POST a pending returns order number that is non-numeric and include special characters
    Then I should see the Post returned unsuccessful with a status code of 404

  Scenario: QE to verify successful response when reservation number is non-numeric and include a space
    Given I am in postman
    When I POST a pending returns order number that is non-numeric and include a space
    Then I should see the Post returned unsuccessful with a status code of 404


