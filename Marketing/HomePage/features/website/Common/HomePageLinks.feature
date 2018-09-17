Feature: Validate all Home Page links
###############################################################################################
# Story             : B-61920
# Author            : Casey Grimm
# Date              : 2016 12th October
###############################################################################################

  @use_dsv @artifact_navapp @domain_marketing @use_regression @mode_domestic @dsv_desktop_sev2
  Scenario: Verify all Home Page links lead to valid destinations
    Given I visit the web site as a guest user
    Then I verify that every link on the home page leads to a valid destination