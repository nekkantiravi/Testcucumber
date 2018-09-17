# Author: Ovidiu Rucoi
# Story: SDE-294 - one click exposure of PRIMARY Customer Data
# Date Created: 11/14/2017
# Date Signed Off:

Feature: As a Clienteling User I want to have quick access to my Client's PRIMARY ADDRESS and PRIMARY PHONE information

  @domain_stores @omniclient @story_SDE-294 @website @bcom
  Scenario: one click exposure of PRIMARY Customer Data -  Selling Associate
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I add a new BLM Client: "PRIMFNAME" "PRIMLNAME" "PRIMADDR" "PRIMCITY" "12345" "TESTHINT" "4445556666" "AL" "primemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    And click on ADD button from Address section BLM
    And we add additional address "PREFADDR" "PREFCITY" "54321" "AL" BLM
    And I change the preferred address by selecting the radio button from the new added address BLM
    And we click on ADD button from Phones section BLM
    And we add additional phone number "2223334444" BLM
    And I change the preferred number by selecting the radio button from the new added number BLM
    And we click on ADD button from Emails section BLM
    And we add additional email "prefemail@blm.com" BLM
    And I change the preferred email by selecting the radio button from the new added email BLM
    And we click on SAVE button from Customer Info tab BLM
    Then the following information should be displayed in Preferred Information section BLM:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
      | Preferred Email        |
    And the Additional Information section is displayed with the following sections BLM:
      | Address(es) |
      | Phone(s)    |
      | Email(s)    |
    And I should see the Expose Primary Data link on the Manage Client tab
    When I click the Expose Primary Data button from the Manage Client tab
    Then I should see the expanded Expose Primary Data section on the Manage Client tab
    And the following information should be displayed in the newly exposed Primary Info section:
      | Name                 | PRIMFNAME PRIMLNAME               |
      | Primary address      | PRIMADDR, PRIMCITY, AL, US, 12345 |
      | Primary phone number | (444) 555-6666 - HOME             |
      | Primary Email        | PRIMEMAIL@MACYS.COM               |
    When I click the Expose Primary Data button from the Manage Client tab
    Then I should not see the Expose Primary Data section on the Manage Client tab

  @domain_stores @omniclient @story_SDE-294 @website @bcom
  Scenario: one click exposure of PRIMARY Customer Data -  Corporate Admin
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as corporate admin
    And I add a new BLM Client: "PRIMFNAME" "PRIMLNAME" "PRIMADDR" "PRIMCITY" "12345" "TESTHINT" "4445556666" "AL" "primemail@macys.com"
    And I click yes button on the credit card required attention popup
    And I navigate to Manage Customer tab
    And I click on the Edit button from the CUSTOMER INFO section BLM
    And click on ADD button from Address section BLM
    And we add additional address "PREFADDR" "PREFCITY" "54321" "AL" BLM
    And I change the preferred address by selecting the radio button from the new added address BLM
    And we click on ADD button from Phones section BLM
    And we add additional phone number "2223334444" BLM
    And I change the preferred number by selecting the radio button from the new added number BLM
    And we click on ADD button from Emails section BLM
    And we add additional email "prefemail@blm.com" BLM
    And I change the preferred email by selecting the radio button from the new added email BLM
    And we click on SAVE button from Customer Info tab BLM
    Then the following information should be displayed in Preferred Information section BLM:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
      | Preferred Email        |
    And the Additional Information section is displayed with the following sections BLM:
      | Address(es) |
      | Phone(s)    |
      | Email(s)    |
    And I should see the Expose Primary Data link on the Manage Client tab
    When I click the Expose Primary Data button from the Manage Client tab
    Then I should see the expanded Expose Primary Data section on the Manage Client tab
    And the following information should be displayed in the newly exposed Primary Info section:
      | Name                 | PRIMFNAME PRIMLNAME               |
      | Primary address      | PRIMADDR, PRIMCITY, AL, US, 12345 |
      | Primary phone number | (444) 555-6666 - HOME             |
      | Primary Email        | PRIMEMAIL@MACYS.COM              |
    When I click the Expose Primary Data button from the Manage Client tab
    Then I should not see the Expose Primary Data section on the Manage Client tab
