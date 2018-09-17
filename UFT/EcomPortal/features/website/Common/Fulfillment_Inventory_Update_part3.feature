#Date Created: 06/24/2015
#Date Signed Off:
# Version One URL: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story:341361

Feature: As a member of the support team, I would like to update Fulfilment Inventory screen in MASS in order to easily monitor UPC availability through MASS portal.

  @sst
  Scenario: Verify that FCC/Eureka tab name is changed to Online Inventory
    Given I am on mass home page as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "Fulfillment inventory" page under Troubleshooting section
    Then I should see "Online Inventory" Tab instead of FCC/Eureka tab

  @sst
  Scenario: Verify that UPC is fltered out based on relevant app
    Given I am on mass home page as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "Fulfillment inventory" page under Troubleshooting section
    When I select "Online Inventory" tab
    Then I browse online inventory for single upc

  @sst
  Scenario: Verify that product is fltered out based on relevant app
    Given I am on mass home page as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "Fulfillment inventory" page under Troubleshooting section
    When I select "Online Inventory" tab
    Then I browse online inventory for single product

  @sst
  Scenario: Verify that UPCs are fltered out based on relevant app
    Given I am on mass home page as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "Fulfillment inventory" page under Troubleshooting section
    When I select "Online Inventory" tab
    Then I browse online inventory for multiple upcs

  @sst
  Scenario: Verify that new reason codes are added for backorderable and always avail rules
    Given I login into mass portal as a valid user
    When I navigate to the "Fulfillment inventory" page under Troubleshooting section
    And I select "fccCellA" from SDP URL
    When I select "Reference Data" tab
    Then I should see new code for back orderable and always avail rules
