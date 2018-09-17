Feature: BATs for Discovery

  @artifact_navapp @mcom @use_regression @priority_high @domain_discovery @use_bat
  Scenario:A user can search for products that match two keywords.
    Given I visit the web site as a guest user
    When I search for "long dress"
    Then all matching products should be returned