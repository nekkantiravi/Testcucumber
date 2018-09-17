Feature: Verify that Polaris footer to contain a responsive minimal view

  @domain_discovery @priority_medium @mode_domestic @please_automate
  Scenario: HomePage - Verify Minimal footer view functionality in DOMESTIC mode
    Given I navigate directly to polaris responsive footer page
    Then I should see polaris footer section
    And I should see "1px" footer divider with "#CCC" font color
    And I should see "#fff" as footer background color
    And I should see "20px" padding between divider and first line of footer text
    And I should see footer font text is horizontally centered
    And I should see footer text with "Avenir LTD Std (85 Heavy)" font, "#999" font color and "10px" font size
    And I should see footer text "15px" line height
    And I should see below text links in first line:
    |Terms of Use      |
    |Privacy Practices |
    |Interest Based Ads|
    |Product Recall    |
    And I should see below text in second line:
    |© 2017 Bloomingdale's. 1000 Third Avenue New York, NY 10022. |
    And I should see "10px" padding between links
    And I should see "30px" padding between last line of footer text and end of the screen


  @domain_discovery @priority_medium @mode_domestic @please_automate
  Scenario: HomePage - Verify Minimized footer functionality in Domestic mode
    Given I navigate directly to polaris responsive footer page
    When I resize window to "990px"
    Then I should see all content is displayed at max width of "960px"
    When I resize window to "900px"
    Then I should see content is moved to new line with "15Px" padding in left and right
    When I resize window to "320px"
    Then I should see content is moved to new line with "15Px" padding in left and right
    When I resize window to "300px"
    Then I should not be allowed to resize window to less than "320px"

  @domain_discovery @priority_medium @mode_domestic @please_automate
  Scenario: HomePage - Verify link navigation functionality
    Given I navigate directly to polaris responsive footer page
    When I click on "footerLink" from footer section
    Then I should be navigated to respective page

  @domain_discovery @priority_medium @mode_domestic @please_automate
  Scenario: HomePage - Verify coremetrics tags upon footer links navigation
    Given I visit the web site as a guest user
    When I add a random product to my bag and checkout
    Then I should see polaris header in checkout pages
    And I should see coremetrics tags with attribute 37 fired on checkout pages when polaris header is shown

