###############################################################################################

  Feature: WDS :: MCOM:: BCOM :: ListBrands Migration from Hessian to REST

################################## Site Mode ########################################################

  @artifact_navapp @domain_marketing @release_15A @use_regression @feature_c2 @mode_domestic @use_domain_qual
  Scenario: Category page - Verify list brands are rendering in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category page
      | MCOM | Brands    |
      | BCOM | Designers |
    Then I should see list of brands
    And I click on facet

  @artifact_navapp @domain_marketing @release_15A @use_regression @feature_c2 @mode_domestic
  Scenario: Category page - Verify list brands are rendering when navigated from flyout menu in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category page from flyout menu
      | MCOM | Brands    |
      | BCOM | Designers |
    Then I should see list of brands
    And  I click on facet

################################## International Mode ########################################################

  @artifact_navapp @domain_marketing @release_15A @use_regression @feature_c2 @mode_domestic
  Scenario: Category page - Verify list brands are rendering in ISHIP mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to category page
      | MCOM | Brands    |
      | BCOM | Designers |
    Then I should see list of brands
    And I click on facet

  @artifact_navapp @domain_marketing @release_15A @use_regression @feature_c2 @mode_iship @use_domain_qual
  Scenario: Category page - Verify list brands are rendering from flyout menu in ISHIP mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    And I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate to category page from flyout menu
      | MCOM | Brands    |
      | BCOM | Designers |
    Then I should see list of brands
    And  I click on facet
