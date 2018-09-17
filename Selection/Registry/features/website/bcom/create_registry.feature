#BCOM Registry Lean Lab
#Author: Masha Malygina
#V-1 Story: B-95084

Feature: Verify functionality of new Edit Registry Profile redesign

  @B-95084 @registry @use_regression @domain_selection
  Scenario: Verify error messages for all required fields no input on Create Registry Form
    Given I visit the website as a guest user
    And I navigate to registry capture email page
    When I start to create a new registry from registry sign in page
    Then verify required validation error messages on Registry Create Form


  @B-95084 @registry @use_regression @domain_selection
  Scenario: Verify error messages on invalid input for all required fields on Create Registry Form
    Given I visit the website as a guest user
    And I navigate to registry capture email page
    When I start to create a new registry from registry sign in page
    Then verify invalid data error messages on Registry Create Form






