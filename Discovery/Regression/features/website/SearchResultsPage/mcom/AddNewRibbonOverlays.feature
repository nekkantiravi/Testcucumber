#=========================
# Author: UFT
# Date Created: 3/28/2016
# Date Signed Off: TBD
# Version One: B-43172
#=========================

Feature: As a product owner, I would like to add new ribbon overlays available for use

############################### DOMESTIC MODE ##########################################################

  @priority_low @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_search_results_page @comp_snbc @xbrowser_search
  Scenario Outline: SearchResultsPage - Domestic - Verify browse ribbon overlays on search results page
    Given I am on SearchResultsPage for "<search_keyword>" in Domestic mode
    Then I verify that text "<thumbnail_overlay_bottom_text>" for corresponding product is displayed on search results page
    Examples:
      | search_keyword    | thumbnail_overlay_bottom_text |
      | Certified Diamond | Certified Diamond             |
      | Power Reclining   | Power Reclining               |
      | Smart Watch       | Smart Watch                   |
      | Cushion Firm      | cushion firm                  |
      | Tummy Control     | tummy control                 |

  ############################### ISHIP MODE ##########################################################

  @priority_low @domain_discovery @mode_iship @use_regression @migrated_to_sdt @feature_search_results_page @comp_snbc
  Scenario Outline: SearchResultsPage - Iship - Verify browse ribbon overlays on search results page
    Given I am on SearchResultsPage for "<search_keyword>" in Iship mode
    Then I verify that text "<thumbnail_overlay_bottom_text>" for corresponding product is displayed on search results page
    Examples:
      | search_keyword    | thumbnail_overlay_bottom_text |
      | Certified Diamond | Certified Diamond             |
      | Smart Watch       | Smart Watch                   |
      | Tummy Control     | tummy control                 |

  ############################### REGISTRY MODE ##########################################################

  @priority_low @domain_discovery @mode_registry @use_regression @migrated_to_sdt @feature_search_results_page @comp_snbc
  Scenario Outline: SearchResultsPage - Registry - Verify browse ribbon overlays on search results page
    Given I am on SearchResultsPage for "<search_keyword>" in Registry mode
    Then I verify that text "<thumbnail_overlay_bottom_text>" for corresponding product is displayed on search results page
    Examples:
      | search_keyword | thumbnail_overlay_bottom_text |
      | Smart Watch    | Smart Watch                   |

  @priority_low @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_search_results_page @comp_snbc
  Scenario Outline: SearchResultsPage - Domestic - Verify overlay bottom text for product thumbnails having Quick_Ship and Ext_Widths_And_Sizes stella attributes
    Given I am on SearchResultsPage for "<search_keyword>" in Domestic mode
    Then I verify that text "<thumbnail_overlay_bottom_text>" for corresponding product is displayed on search results page
    Examples:
      | search_keyword | thumbnail_overlay_bottom_text |
      | Quick Ship     | Quick Ship                    |
      | clarks         | Ext Sizes & Widths            |

  @priority_medium @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_search_results_page @B-78193
  Scenario Outline: SearchResultsPage - Domestic - Verify Badge text is not suppressed for Tom Ford, Dior and Tory Burch brands
    Given I visit the web site as a guest user
    When I search for "<beauty_brand>"
    Then I should see "batch text" is displayed for the "specific beauty" brand
    Examples:
      | beauty_brand |
      | ford tom     |
      | tory burch   |

  @priority_low @domain_discovery @mode_registry @use_regression @migrated_to_sdt @feature_search_results_page @comp_snbc
  Scenario Outline: SearchResultsPage - Domestic - Verify the "more colors available" bar under the product thumbnail
    Given I am on SearchResultsPage for "spencer border duvet" in <Site_Mode> mode
    When I select a product with below options:
      | SUPPRESS_COLOR_SWATCHES | Y  |
      | color_swatches_count    | >1 |
    Then I verify that "more colors available" ribbon overlay text is displayed below product thumbnail
    And I verify that color swatches are not displayed below product thumbnail
    Examples:
      | Site_Mode |
      | domestic  |
      | registry  |

  @priority_low @domain_discovery @mode_registry @use_regression @migrated_to_sdt @feature_search_results_page @comp_snbc
  Scenario Outline: SearchResultsPage - Domestic - Verify more color swatches under the product thumbnail
    Given I am on SearchResultsPage for "spencer" in <Site_Mode> mode
    When I select a product with below options:
      | SUPPRESS_COLOR_SWATCHES | N  |
      | color_swatches_count    | >1 |
    And I verify that color swatches are displayed below product thumbnail
    Examples:
      | Site_Mode |
      | domestic  |
      | registry  |