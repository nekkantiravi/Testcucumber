# Author: Ana Ghergut - Stores Domain Checkout Team
# Story: SDU-169 - Update Configuration View - Xenon Scanner
# Date Created: 12/05/2017
# Date Signed Off:

@domain_stores @project_checkout @manual @HALDesktop @release_1723 @story_SDU-169
Feature: As a developer, I should update the configuration view to show the status of the Xenon 1900GSR-2 Scanner

  Scenario: HALDesktop - The driver version of the scanner is displayed in the about menu
    Given I am on Hal Desktop
    When I connect the Xenon 1900GSR-2 Scanner to the HAL Desktop
    And I click on Hamburger icon
    And I click the About Icon
    And I open the device drop down
    Then I see the Scanner information
    And I see the driver version of the scanner
