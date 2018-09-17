Feature: As a product owner, I would like to add new ribbon overlays available for promotions on DynamicLanding page

  @priority_low @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify overlay bottom text for product thumbnails having Quick_Ship and Ext_Widths_And_Sizes stella attributes
    Given I visit the web site as a guest user
    And I navigate to brand index page in "domestic" mode
    When I select "<brand_name>" brand in all brands page
    Then I verify that text "<thumbnail_overlay_bottom_text>" for corresponding product is displayed on dlp page
    Examples:
      | brand_name | thumbnail_overlay_bottom_text |
      | clarks     | Extended Widths               |

  @priority_medium @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @feature_dlp_page
  Scenario Outline: DynamicLandingPage - Domestic - Verify Badge text is not suppressed for Tom Ford Tory Burch brands
    Given I visit the web site as a guest user
    And I navigate to brand index page in "domestic" mode
    When I navigate to "<brand_name>" dlp page
    Then I should see "batch text" is displayed for the "specific beauty" brand
    Examples:
      | brand_name   |
      | Tom Ford     |
      | Tory Burch   |