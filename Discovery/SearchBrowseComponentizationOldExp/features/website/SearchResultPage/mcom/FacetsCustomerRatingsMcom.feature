#Author: Discovery QE

Feature: Verifying Customer Ratings Facets in SLP

############################### ALL MODES ##########################################################
  @artifact_navapp @domain_discovery @priority_high @mode_domestic @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the ratings in product thumbnail matches the first facet selection
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    When I select the first rating in the Customer Ratings facet
    Then I verify that the products are displayed with the selected rating
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @add_missing_scope @project_snb
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the ratings in product thumbnail matches the facet selection
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    When I select the first two ratings in the Customer Ratings facet
    Then I verify that the products are displayed with the selected rating
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify deselecting of ratings from breadcrumb
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    Then I verify that the product count is displayed
    When I select the first rating in the Customer Ratings facet
    Then I verify that the products are displayed with the selected rating
    When I remove the selected facet from the breadcrumb
    Then I verify that all of the products are displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify ratings choice is retained when paginated
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    When I select the first rating in the Customer Ratings facet
    And I navigate to the last page
    Then I verify that the products are displayed with the selected rating
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting a rating from overlay displays all the products
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    Then I verify that the product count is displayed
    When I select the first rating in the Customer Ratings facet
    Then I verify that the product count is updated
    When I remove the selected facet from the breadcrumb
    Then I verify that the product count returns to original
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    Then I verify that the product count is displayed
    When I select the first two ratings in the Customer Ratings facet
    And I click on clear all button
    Then I verify that all of the products are displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify clear all and product count
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    Then I verify that the product count is displayed
    When I select the first two ratings in the Customer Ratings facet
    Then I verify that the product count is updated
    When I click on clear all button
    Then I verify that the product count returns to original
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that deselecting the rating one by one from breadcrumbs displays products accordingly
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    Then I verify that the product count is displayed
    When I select 3 rating in the "Customer Ratings" facet
    Then I verify that products are filtered as per selected facet value
    And I verify that the product count is updated
    When I remove first rating facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove second rating facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
    When I remove last rating facet from the breadcrumb
    Then I verify that the product count is updated
    And I verify that the product thumbnails are updated
  #Note : Verify that the product assortment is getting changed after the deselection of each rating
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that clear all button is updating the product assortment back to original
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    Then I verify that the product count is displayed
    When I select 3 rating in the "Customer Ratings" facet
    Then I verify that products are filtered as per selected facet value
    When I click on clear all button
    And I verify that the product count is updated
    Then I verify that all of the products are displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that product counts in overlay and results match
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    Then I select the first rating in the Customer Ratings facet
    And I verify that product count for the selected CUSTRATINGS in the overlay and results match
  #Note : The product count can be off by 2
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the quick peek is showing the selected rating
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    When I select the first rating in the Customer Ratings facet
    And I select the quick peek of random product
    Then I verify that the product rating in quick peek
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |

  @artifact_navapp @domain_discovery @priority_high @mode_domestic @project_snb @add_missing_scope
  Scenario Outline: SearchResultsPage - Domestic|Iship|Registry - Verify that the PDP is showing the selected rating
    Given I am on SearchResultsPage for "plates" in <Site_Mode> mode
    When I select the first rating in the Customer Ratings facet
    And I select random product from thumbnail grid
    Then I verify that the product rating in PDP
    Examples:
      | Site_Mode |
      | Domestic  |
      | Registry  |
      | Iship     |