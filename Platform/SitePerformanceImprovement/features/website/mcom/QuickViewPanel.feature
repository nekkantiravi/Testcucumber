Feature: Verify the Quick View accross all Navapp pages

  @domain_Other @artifact_navapp @project_siteperformance @feature_QV
  Scenario Outline: Verify the Quick View dialog in search pages for domestic mode
    Given I visit the web site as a guest user in "domestic" mode
    When I search for "<Search_cat>"
    And I select a random quick view
    Then I verify that product name of quick view with grid thumbnail
    And I verify that price of quick view with grid thumbnail
    And I verify that promotion of quick view with grid thumbnail
    And I verify the basic attributes of quickview in "domestic" mode

    Examples:
      |Search_cat|
      |dinning    |
      |apple watches|
      |dresses|
      |red mug|
      |fuchsia handbag|

  @domain_Other @artifact_navapp @project_siteperformance @feature_QV
  Scenario Outline: Verify the Quick View dialog in search pages for iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I search for "<Search_cat>"
    And I select a random quick view
    Then I verify that product name of quick view with grid thumbnail
    And I verify that price of quick view with grid thumbnail
    And I verify the basic attributes of quickview in "iship" mode

    Examples:
      |Search_cat|
      |jeans|

  @test @domain_Other @artifact_navapp @project_siteperformance @feature_QV
  Scenario Outline: Verify the Quick View dialog in search pages for registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I search for "<Search_cat>"
    And I select a random quick view
    Then I verify that product name of quick view with grid thumbnail
    And I verify that price of quick view with grid thumbnail
    And I verify the basic attributes of quickview in "registry" mode

    Examples:
      |Search_cat|
      |Electrics|

  @domain_Other @artifact_navapp @project_siteperformance @feature_QV
  Scenario: Verify the Quick View dialog in browse pages for domestic mode
    Given I visit the web site as a guest user
    When I navigate to browse page in "domestic" mode
    And I select a random quick view
    Then I verify that product name of quick view with grid thumbnail
    And I verify that price of quick view with grid thumbnail
    And I verify the basic attributes of quickview in "domestic" mode

  @domain_Other @artifact_navapp @project_siteperformance @feature_QV
  Scenario: Verify the Quick View dialog in browse pages for iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate to browse page in "iship" mode
    And I select a random quick view
    Then I verify that product name of quick view with grid thumbnail
    And I verify that price of quick view with grid thumbnail
    And I verify the basic attributes of quickview in "iship" mode

  @domain_Other @artifact_navapp @project_siteperformance @feature_QV
  Scenario: Verify the Quick View dialog in browse pages for registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I navigate to browse page in "registry" mode
    And I select a random quick view
    Then I verify that product name of quick view with grid thumbnail
    And I verify that price of quick view with grid thumbnail
    And I verify the basic attributes of quickview in "registry" mode
