# Author: Ovidiu Rucoi
# Story: To Dos Print
# Date Created: 10/18/2017
# Date Signed Off:

Feature: As an associate, I want to be able to print Todos

  @RegMcom @domain_stores @omniclient @website
  Scenario: Print from Homepage ToDos section
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should see the ToDos section on the Macys Homepage
    When I click the HERE button under the ToDos section on the Macys Homepage
    Then I should see the list of ToDos under the ToDos section on the Macys Homepage
    When I select the first ToDo from the ToDos section on the Macys Homepage
    And I click the Print button from the ToDos section on the Macys Homepage
    Then I should see the ToDos Print Overlay
    When I close the Print Overlay window
    And I select all ToDos from the ToDos section on the Macys Homepage
    And I click the Print button from the ToDos section on the Macys Homepage
    Then I should see the ToDos Print Overlay

  @RegMcom @domain_stores @omniclient @website
  Scenario: Print from Homepage Clients section
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    Then I should see the Clients section on the Macys Homepage
    When I select the first Client from the Clients section on the Macys Homepage
    And I click the Print button from the Clients section on the Macys Homepage
    Then I should see the Client Card Print Overlay
    When I close the Print Overlay window
    And I select all Clients from the Clients section on Homepage
    And I click the Print button from the Clients section on the Macys Homepage
    Then I should see the Client Card Print Overlay

  @RegMcom @domain_stores @omniclient @website
  Scenario: Print from My Todos tab
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I navigate to ALL TO DOS page
    Then I should see the MY TO DOS page
    When I select the first Todo from the My ToDos tab
    And I click the Print button from the My ToDos tab
    Then I should see the ToDos Print Overlay
    When I close the Print Overlay window
    And I select all ToDos from the My ToDos tab
    And I click the Print button from the My ToDos tab
    Then I should see the ToDos Print Overlay


  @RegMcom @domain_stores @omniclient @website
  Scenario: Print from My Clients tab
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I click on My Clients from top navigation bar
    Then I should see the Macys My Clients page
    When I select the first Client from the Macys My Clients page
    And I click the Print button from the Macys My Clients page
    Then I should see the Client Card Print Overlay
    When I close the Print Overlay window
    And I select all Clients from the Macys My Clients page
    And I click the Print button from the Macys My Clients page
    Then I should see the Client Card Print Overlay

  @RegMcom @domain_stores @omniclient @website
  Scenario: Print from Client Profile
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I select a Macys client from OCL website Homepage
    Then I should see the Client Profile website page
    When I click the Print button from the Client Profile page
    Then I should see the Client Card Print Overlay



