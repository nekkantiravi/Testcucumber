#Author: Amol Ray
#Migrated by : Manjeet Ranga
#Date Created: 08/09/2017

Feature: Verify create profile functionality with few fields (MVP6)

  @use_regression @domain_customer @migrated_to_sdt
  Scenario: Verify My Account Page Title
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I validate the my account page title "My Account - Bloomingdale's"