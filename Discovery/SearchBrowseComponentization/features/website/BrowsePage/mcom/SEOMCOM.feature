# Author: DISCOVERY QE

Feature: Verify SEO tags on Browse Page - DOMESTIC, ISHIP and REGISTRY modes

  # @SEO_4431
  @use_regression @artifact_navapp @domain_discovery @priority_high @use_seo @snbc_comp
  Scenario Outline: CategorySplashPage - Domestic - Verify canonical tag
    Given I am on CategoryBrowsePage for "<category_id>" category id in Domestic mode
    Then I "should" see canonical tag pointing to the corresponding URL added in the header section
    Examples:
      | category_id |
      | 5449        |
      | 89          |

  @use_regression @artifact_navapp @domain_discovery @priority_high @use_seo @snbc_comp
  Scenario Outline: CategorySplashPage - Registry - Verify canonical tag
    Given I am on CategoryBrowsePage for "<category_id>" category id in Registry mode
    Then I "should" see canonical tag pointing to the corresponding URL added in the header section
    Examples:
      | category_id |
      | 70954       |
      | 7552        |

  @use_regression @artifact_navapp @domain_discovery @priority_high @use_seo @snbc_comp
  Scenario Outline: BrowsePage - Verify alternative tags are added for mobile URLs on desktop pages for guest user
    Given I am on CategoryBrowsePage for "<category_id>" category id in <Site_Mode> mode
    Then I "should" see <link rel="alternate" media="only screen and (max-width: 640px)" href="http://m.macys.com/..."> tag pointing to the corresponding URL added in the header section
    Examples:
      | Site_Mode | category_id |
      | domestic  | 26481       |
      | registry  | 31760       |
