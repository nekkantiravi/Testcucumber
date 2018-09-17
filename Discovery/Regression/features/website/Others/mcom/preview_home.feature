Feature: Verify the features in every category or sub category

  @preview_desktop @wip
  Scenario: HomePage: Verify FOBs and test Flyout Menu
    Given I visit the web site as a guest user
    When I navigate to all category pages and I verify left nav red link is clickable
      |WOMEN|
      |MEN|
      |HOME|
      |BED & BATH|
      |SHOES|
      |HANDBAGS|
      |BEAUTY|
      |KIDS|
      |JUNIORS|
      |JEWELRY|
      |WATCHES|
      |BRANDS |

  @preview_desktop
  Scenario: Verify the GNA link on Home page -Free Shipping
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify GNA link is displayed on homepage and contains "Free Shipping" text
    When I click on free shipping exclusion link
    Then I should be redirected to free ship popup

  @preview_desktop
  Scenario: Verify the GNA link on Home page -The Edit
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify GNA link is displayed on homepage and contains "The Edit" text
    When I click on check it out link
    Then I should be redirected to Macys The Edit page
