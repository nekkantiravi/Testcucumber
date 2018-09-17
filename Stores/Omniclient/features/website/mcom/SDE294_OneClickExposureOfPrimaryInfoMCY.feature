# Author: Ovidiu Rucoi
# Story: SDE-294 - one click exposure of PRIMARY Customer Data
# Date Created: 11/14/2017
# Date Signed Off:

Feature: As a Clienteling User I want to have quick access to my Client's PRIMARY ADDRESS and PRIMARY PHONE information

  @domain_stores @omniclient @story_SDE-294 @website @mcom
  Scenario: one click exposure of PRIMARY Customer Data -  Selling Associate
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I add a new Macys Client: "PRIMFNAME" "PRIMLNAM" "PRIMADDR" "PRIMCITY" "12345" "TEST HINT" "1111111111" "AL"
    And I navigate to Manage Client tab
    And I click on the Edit button from the CLIENT INFO section MACYS
    And click on ADD button from Address section
    And we add additional address "PREFADDR" "PRECITY" "54321" "AL" MACYS
    And I change the preferred address by selecting the radio button from the new added address
    And we click on ADD button from Phones section
    And we add additional phone number "2223334444" MACYS
    And I change the preferred number by selecting the radio button from the new added phone
    And we click on SAVE button from Client Info tab MACYS
    Then the following information should be displayed in Preferred Information section:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
    And the Additional Information section is displayed with the following sections MACYs:
      | Address(es) |
      | Phone(s)    |
    And I should see the Expose Primary Data link on the Manage Client tab
    When I click the Expose Primary Data button from the Manage Client tab
    Then I should see the expanded Expose Primary Data section on the Manage Client tab
    And the following information should be displayed in the newly exposed Primary Info section:
      | Name                 | PRIMFNAME PRIMLNAM                              |
      | Primary address      | PRIMADDR, ADDRESSLINE2, PRIMCITY, AL, US, 12345 |
      | Primary phone number | (111) 111-1111 - MOBILE                         |
    When I click the Expose Primary Data button from the Manage Client tab
    Then I should not see the Expose Primary Data section on the Manage Client tab

  @domain_stores @omniclient @story_SDE-294 @website @mcom
  Scenario: one click exposure of PRIMARY Customer Data -  Corporate Admin
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as corporate admin
    And I add a new Macys Client: "PRIMFNAME" "PRIMLNAM" "PRIMADDR" "PRIMCITY" "12345" "TEST HINT" "1111111111" "AL"
    And I navigate to Manage Client tab
    And I click on the Edit button from the CLIENT INFO section MACYS
    And click on ADD button from Address section
    And we add additional address "PREFADDR" "PRECITY" "54321" "AL" MACYS
    And I change the preferred address by selecting the radio button from the new added address
    And we click on ADD button from Phones section
    And we add additional phone number "2223334444" MACYS
    And I change the preferred number by selecting the radio button from the new added phone
    And we click on SAVE button from Client Info tab MACYS
    And the following information should be displayed in Preferred Information section:
      | Name                   |
      | Preferred address      |
      | Preferred phone number |
    And the Additional Information section is displayed with the following sections MACYs:
      | Address(es) |
      | Phone(s)    |
    And I should see the Expose Primary Data link on the Manage Client tab
    When I click the Expose Primary Data button from the Manage Client tab
    Then I should see the expanded Expose Primary Data section on the Manage Client tab
    And the following information should be displayed in the newly exposed Primary Info section:
      | Name                 | PRIMFNAME PRIMLNAM                              |
      | Primary address      | PRIMADDR, ADDRESSLINE2, PRIMCITY, AL, US, 12345 |
      | Primary phone number | (111) 111-1111 - MOBILE                         |
    When I click the Expose Primary Data button from the Manage Client tab
    Then I should not see the Expose Primary Data section on the Manage Client tab
