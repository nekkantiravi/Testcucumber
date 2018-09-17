Feature: BATs for Selection

  @artifact_navapp_2 @selection_launch @artifact_navapp @mcom_iship @domain_purchase_and_delivery @sel_mcom_bat @iship_2 @use_bat @use_domain_qual @use_s4a_stable @project_MCOM @sanity_MCOM @domain_selection @use_regression @project_iship
  Scenario: Verify country flag in iship mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I navigate to product ID 77589
    Then I verify the flag for "Australia"