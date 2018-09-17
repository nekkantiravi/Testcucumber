# Author: Shatabdi Sheet
# Date Created: February 24th, 2013
# Date Signed Off: TBD

Feature: To verify add shipping address in My address book page


  @wip @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_high @use_regression @myaccount_5 @s4a_stable @domain_customer
  Scenario:Verify an address deleted from My Address Book Page is NOT displayed on Shipping & Payment Page
    Given I visit the web site as a guest user
    When I create a new profile using services
    And I add 1 shipping address to the address book page
    And I navigate to my address book page
    And user deletes one address
    Then address should get deleted
    When I add a "available and orderable" product to my bag
    And I checkout until I reach the shipping page as a "signed in" user
    Then I should not see the deleted address in shipping & payment page

  @wip @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_high @use_regression  @myaccount_6 @s4a_stable @domain_customer @use_domain_qual
  Scenario:Verify address used during Create Profile is displayed on Shipping Payment Page
    Given I visit the web site as a guest user
    When I create a new profile
    And  I add shipping address and it should be dispalyed in my address book
    And I add a "available_sdd and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then the create profile address should be displayed in shipping payment page

  @wip @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5  @s4a_stable @domain_customer
  Scenario:Verify the primary address from My Address Book Page is displayed on Shipping & Payment Page
    Given I visit the web site as a registered user
    And I add 1 addresses to my address book
    And I add shipping address and it should be dispalyed in my address book
    And I make second address as primary
    Then I should verify the primary address should not have an option to make primary
    When I add a "available_sdd and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then the default address should displayed in the shipping and payment page