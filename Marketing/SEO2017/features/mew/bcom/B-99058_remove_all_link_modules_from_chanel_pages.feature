# Author: SEO Improvements 2017
# Date Created: 12/20/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM MEW: Remove all Link Modules and related coding from Chanel Category, PDP and SLP pages
# Versionone story numbers:: B-99058
#########################################################################################################################
Feature: Test to see if there are any Link Modules or related coding on BCOM Chanel MEW pages and if found, remove all Link Module/Tag Clouds appearance and coding from all Chanel pages (Browse,PDP pages)
########### ###################################################### CHANEL Category Browse Page ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17Y @priority_high @artifact_wssg
  Scenario: Verify that link module section should not be displayed for Chanel Browse Page in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | BEAUTY   |
      | Featured Brands |
      | CHANEL |
      | WOMEN'S FRAGRANCE|
    Then I should not see link module section displayed on UI
    And I should not see link module section displayed on view source
      |  tag_cloud        |
      |  new_element_only_tag_cloud  |
      |  tag_cloud_links  |

########### ###################################################### CHANEL PDP Page ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17Y @priority_high @artifact_wssg
  Scenario: Verify that link module section should not be displayed for Chanel PDP Page in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    And I navigate the global navigation menu as follows:
      | BEAUTY   |
      | Featured Brands |
      | CHANEL |
      | WOMEN'S FRAGRANCE|
    When I select a random product from chanel page
    Then I should not see link module section displayed on UI
    And I should not see link module section displayed on view source
      |  tag_cloud        |
      |  new_element_only_tag_cloud  |
      |  tag_cloud_links  |

