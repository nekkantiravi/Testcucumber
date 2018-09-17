Feature: Verify Coach Pages in DOMESTIC, ISHIP and REGISTRY mode

#Testlink-BLCOM-84264,Vone-RT-06515
  @priority_high @artifact_navapp @domain_discovery @mode_domestic @snbc_comp
  Scenario: BrowsePage - Verify Coach Browse Page in DOMESTIC Mode
    Given I am on CategoryBrowsePage for "1004771" category id in Domestic mode
    And I clear existing class variables to avoid data issues
    Then I verify the basic attributes of COACH browse page
  # Notes:
  # Verification required:
  # left navigation catolary section is displayed top of the left side
  # SORT BY fucntion is displayed
  # Product thumbnails image should display properly
  # Facets should display on the left navigation