# Author: Discovery QE Team

Feature: MCOM: Implement Scrolled Pixel for PDP and ZSR panels

  @15E @priority_medium @use_regression @pros @mcom @domain_selection @snbc_comp
  Scenario Outline: Verify Scrolled Pixel request when ZSR-H panel is loaded in all mode
    Given I am on ZeroSearchResultPage for "lansdlanksd" in <shopping_mode> mode
    Then I verify that recommendation panel is displayed on ZSR page
    When I select left arrow on recommendation panel
    And I should see the "scrolled" informant for "ZSR Horizontal" panel in Dyces logs with the following parameters:
      | visitorId        |
      | context          |
      | customerId       |
      | responseType     |
      | deliveryId       |
      | productPositions |
      | headerId         |
      | controlGroupId   |
    Examples:
      | shopping_mode |
      | domestic      |
      | iship         |
      | registry      |