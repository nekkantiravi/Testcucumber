Feature: Verify the canonical tag in all Navapp pages

  @mcom_ssl_canonical
  Scenario: Verify the canonical tags are SSL certified in home page
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify the canonical tag

  @mcom_ssl_canonical
  Scenario Outline: Verify the canonical tags are SSL certified in cat splash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to random category splash page
    Then I should see the "category splash" page
    And I verify the canonical tag
    Examples:
      |mode_name|
      |domestic|
      |iship|
      |registry|

  @mcom_ssl_canonical
  Scenario Outline: Verify the canonical tags are SSL certified in SubSplash page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I mouse over "Women" category from top navigation
    And I select "Activewear" subcategory from flyout menu
    Then I should be on "Women" subsplash page
    And I verify the canonical tag
    Examples:
      |mode_name|
      |domestic|
      |iship|


  @mcom_ssl_canonical
  Scenario Outline: Verify the canonical tags are SSL certified in PDP page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to a random product
    Then I should be redirected to PDP page in "<mode_name>" mode
    And I verify the canonical tag
    Examples:
      |mode_name|
      |domestic|
      |registry|


  @mcom_ssl_canonical
  Scenario Outline: Verify the canonical tags are SSL certified in category browse page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to browse page in "<mode_name>" mode
    Then I verify the canonical tag
    Examples:
      |mode_name|
      |domestic|
      |iship|
      |registry|


  @mcom_ssl_canonical
  Scenario Outline: Verify the canonical tags are SSL certified inn brand index page
    Given I visit the web site as a guest user in "<mode_name>" mode
    When I navigate to brand index page in "<mode_name>" mode
    Then I should be navigated to brand index page
    And I verify the canonical tag
    Examples:
      |mode_name|
      |domestic|
      |iship|