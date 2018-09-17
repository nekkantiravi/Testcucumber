# Author: Anamaria Oanea - Stores Domain Checkout Team
# Story: SDU-744 - Receipt - Customer information
# Date Created: 07/26/2017
# Date Signed Off:

@domain_stores @project_checkout @release_1714 @story_SDU-744
Feature: As a customer, I want my shipping and billing information to print on my Place Order receipt,
  so I have a record of where my merchandise will be shipped and who it is billed to.
  The Customer information section of the receipt prints below Tender information and includes
  Shipping Information (Shipping Customer's name, address, and phone number)
  Billing Information( Billing Customer's name and phone number)
 @manual
  Scenario: Customer info section includes Shipping info and Billing Info
    Given I am on Sales Trans
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can see the receipt icon
    When I click on the receipt icon
    Then I can see the receipt information on a send
#     This step will contain the customer info section elements
  @manual
  Scenario: Take order - no customer info section on receipt
    Given I am on Sales Trans
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    And I can see the receipt icon
    When I click on the receipt icon
    Then I can see the receipt information on a take
    And I can't see the customer info section on take receipt

  @manual
  Scenario: Billing info differs from shipping info
    Given I am on Sales Trans
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can see the receipt icon
    When I click on the receipt icon
    Then I can see the receipt information on a send
    And I can see the Billing info differs from Shipping info

  @manual
  Scenario: Customer info section prints below tender info
    Given I am on Sales Trans
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    And I can see the receipt icon
    When I click on the receipt icon
    Then I can see the receipt information on a send
    And I can see the customer info section under tender info

