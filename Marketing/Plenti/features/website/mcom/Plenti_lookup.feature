@use_domestic
@use_regression
@domain_marketing
@project_plenti
@plenti_lookup
Feature: Plenti : Lookup

  @priority_high  @use_bat
  Scenario: Plenti - Successful lookups from my account page
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I perform all valid Plenti lookups on the "my_account" page

  #this test is tagged as high priority because it shows all account status lookups as successful on the myaccount page
  #this verifies that the environment has successful profile creation & lookup service behavior.

  @priority_high
  Scenario: Plenti - Successful lookups from shopping bag page as a guest user
    Given I visit the web site as a guest user
   # When I add an "available and orderable" radical product to my bag
    When I add an "available" product to my bag
   # And I should be redirected to shopping bag page
    When I navigate to shopping bag page from add to bag page
    Then I perform all valid Plenti lookups on the "shopping_bag" page

  @priority_medium
  Scenario: Plenti - Successful lookups from shopping bag page as a registered user
    Given I visit the web site as a registered user
    When I add an "available and orderable" radical product to my bag
    And I should be redirected to shopping bag page
    Then I perform all valid Plenti lookups on the page

  @priority_low
  Scenario: Plenti - Successful lookups from shopping bag page as a Plenti user
    Given I visit the web site as a Plenti user with a "fully_enrolled_usl" status
    When I add an "available and orderable" radical product to my bag
    And I should be redirected to shopping bag page
    Then I perform all valid Plenti lookups on the page

  @priority_high
  Scenario: Plenti - Successful lookups from payment page as a guest user
    Given I visit the web site as a guest user
   # When I add an "available and orderable" radical product to my bag
    When I add an "available" product to my bag
    And I checkout until I reach the "responsive_payment_guest_section" page as a "guest" user
    Then I perform all valid Plenti lookups on the "payment" page

  #this test is tagged as high priority because it shows successful lookup on the responsive payment page
  #this verifies that lookup services are correctly working including prop/cobrand lookup which is only available on payment pages.

  @priority_medium
  Scenario: Plenti - Successful lookups from payment page as a registered user
    Given I visit the web site as a registered user
    When I add an "available and orderable" radical product to my bag
    And I add a "virtual_gift_card" product to my bag with "USL Test" as message
    And I checkout until I reach the "responsive shipping and payment" page as a "signed in" user
    Then I perform all valid Plenti lookups on the page

  @priority_high
  Scenario: Plenti - Successful lookups from payment page as a Plenti user
   # Given I visit the web site as a Plenti user with a "fully_enrolled" status
    Given I visit the web site as a registered user
   # When I add an "available and orderable" radical product to my bag
    When I add an "available" product to my bag
    And I checkout until I reach "shipping & payment" page as a "signed in" user
    Then I perform all valid Plenti lookups on the "payment" page

  #this test is tagged as high priority because it shows successful lookup on the old payment page
  #this verifies that lookup services are correctly working including prop/cobrand lookup which is only available on payment pages.
  #also verifies correct profile linking behavior from
