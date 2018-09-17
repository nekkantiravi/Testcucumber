@use_domestic
@use_regression
@domain_marketing
@project_plenti
@plenti_redeem
@priority_high
Feature: Plenti : Redeem

@use_regression
Scenario: Plenti - Redeem Plenti User
Given I visit the web site as a Plenti user with a "fully_enrolled" status
#When I add an "available and orderable" radical product to my bag
  When I add an "available" product to my bag
Then I validate Plenti redeem functionality through checkout as a "signed in" user

  #Test requires the following:
  # Orderable Product
  # Test will also verify LTY lookup services by verifying UI display of USL panels on shopping bag, payment page, order confirmation, and account summary

@use_regression
Scenario: Plenti - Redeem Guest User
Given I visit the web site as a guest user
#When I add an "available and orderable" radical product to my bag
  When I add an "available" product to my bag
 And I add "fully_enrolled" id on shopping bag page
  Then I validate Plenti redeem functionality through checkout as a "guest" user