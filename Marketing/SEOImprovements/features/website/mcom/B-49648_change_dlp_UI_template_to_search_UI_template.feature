# Author: SEO Improvements 2016
# Date Created: 6/06/2016
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM::NavApp:Tech: Change DLP UI template to on-site search UI template.
# Versionone story number:: B-49648
#########################################################################################################################

Feature:  Business want to have on-site search and DLP pages same UI template.

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that DLP pages should have the same UI template as that of on-site search.
    Given I visit the web site as a guest user
    And I navigate to brand index page in "domestic" mode
    When I select "clarks" brand in all brands page
    Then I should see "We found 'n' results for xxxx" message of product count




