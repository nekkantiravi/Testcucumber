# Author: Discovery QE
# Date Created: 02/16/2017

Feature: Verifying Shop 5 and Shop 9 coremetrics tags populated from Checkout page and Order confirmation page

  # Shop5 Tag Coremetrics Scenarios:

  @shop5_guest_master @snbc_coremetrics
  Scenario: Checkout Page - Domestic - Verify As a guest user, Shop 5 coremetrics tags are populated correctly for master product
    Given I visit the web site as a guest user in "domestic" mode
    Then I directly add an available and orderable product "1902537" to my bag
    Then I verify that landed on "shopping_bag" Page

  @shop5_guest_member @snbc_coremetrics
  Scenario: Checkout Page - Domestic - Verify As a guest user, Shop 5 coremetrics tags are populated correctly for member product
    Given I visit the web site as a guest user in "domestic" mode
    Then I directly add an available and orderable product "1870598" to my bag
    Then I verify that landed on "shopping_bag" Page

  @shop5_guest_Standard @snbc_coremetrics
  Scenario: Checkout Page - Domestic - Verify As a guest user, Shop 5 coremetrics tags are populated correctly for standard product
    Given I visit the web site as a guest user in "domestic" mode
    Then I directly add an available and orderable product "2112827" to my bag
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_master @snbc_coremetrics
  Scenario: Checkout Page - Domestic - Verify As a registered user, Shop 5 coremetrics tags are populated correctly for master product
    Given I visit the web site as a registered user in "domestic" mode
    Then I directly add an available and orderable product "1902537" to my bag
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_member @snbc_coremetrics
  Scenario: Checkout Page - Domestic - Verify As a registered user, Shop 5 coremetrics tags are populated correctly for member product
    Given I visit the web site as a registered user in "domestic" mode
    Then I directly add an available and orderable product "1870598" to my bag
    Then I verify that landed on "shopping_bag" Page

  @shop5_registered_Standard @snbc_coremetrics
  Scenario: Checkout Page - Domestic - Verify As a registered user, Shop 5 coremetrics tags are populated correctly for standard product
    Given I visit the web site as a registered user in "domestic" mode
    Then I directly add an available and orderable product "2112827" to my bag
    Then I verify that landed on "shopping_bag" Page

  @shop9_guest_domestic @snbc_coremetrics
  Scenario: Order Confirmation Page - Domestic - Verify As a guest user, Shop 9 coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "domestic" mode
    Then I directly add an available and orderable product "2112827" to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user
    Then I verify that landed on "order_confirmation" Page

  @shop9_guest_registry @snbc_coremetrics
  Scenario: Order Confirmation Page - Registry - Verify As a guest user, Shop 9 coremetrics tags are populated correctly
    Given I visit the web site as a guest user in "registry" mode
    Then I directly add an available and orderable product "834045" to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user
    Then I verify that landed on "order_confirmation" Page

  @shop9_registered_domestic @snbc_coremetrics
  Scenario: Order Confirmation Page - Domestic - Verify As a registered user, Shop 9 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "domestic" mode
    Then I directly add an available and orderable product "2112827" to my bag
    Then I checkout until I reach the order confirmation page as an "registered" user
    Then I verify that landed on "order_confirmation" Page

  @shop9_registered_registry @snbc_coremetrics
  Scenario: Order Confirmation Page - Registry - Verify As a registered user, Shop 9 coremetrics tags are populated correctly
    Given I visit the web site as a registered user in "registry" mode
    Then I directly add an available and orderable product "834045" to my bag
    Then I checkout until I reach the order confirmation page as an "registered" user
    Then I verify that landed on "order_confirmation" Page