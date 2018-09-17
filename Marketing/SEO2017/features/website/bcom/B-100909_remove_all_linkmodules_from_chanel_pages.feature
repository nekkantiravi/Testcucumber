# Author: SEO Improvements 2017
# Date Created: 12/21/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM Desktop: Remove all Link Module Coding from Chanel Category, PDP and DLP pages
# Versionone story numbers:: B-99039
#########################################################################################################################
Feature: Test to see if there are any Link Modules or related coding on BCOM Chanel pages and if found, remove all Link Module/Tag Clouds appearance and coding from all Chanel pages (Browse,PDP pages)
########### ###################################################### CHANEL Category Browse Page ########################################################################################
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that link module should not be displayed on Chanel Category Browse Page
    Given I search for "chanel makeup" in Domestic mode
    Then I should not see link module displayed on UI
    And I should not see link module displayed on view source
      |  tag_cloud        |
      |  new_tag_cloud    |
      |  tag_cloud_links  |

########### ###################################################### CHANEL PDP Page ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that link module should not be displayed on Chanel PDP Page
    Given I search for "chanel makeup" in Domestic mode
    When I select one random product from chanel page
    Then I should not see link module displayed on UI
    And I should not see link module displayed on view source
      |  tag_cloud        |
      |  new_tag_cloud    |
      |  tag_cloud_links  |