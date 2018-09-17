# Author: SEO Optimization 2017
# Date Created: 12/20/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: MCOM MEW: Remove all Link Modules and related coding from Chanel Category, PDP and SLP pages
# Versionone story numbers::B-99053
########################################################################################################################################################
Feature: As an SEO Business Manager, I would like to Remove all Link Module/Tag Clouds appearance and coding from all Chanel pages (Category, Browse,Sub Splash and PDP pages )
   ###################################################### Chanel Browse Page - site mode ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17Y @priority_high @artifact_xapi
  Scenario: Verify that link module section should not be displayed for Chanel Browse Page in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    And I navigate to "women's fragrance" category browse page
    Then I should not see link module section displayed on UI
    And I should not see link module section displayed on view source
      |  tag_cloud        |
      |  new_tag_cloud    |
      |  tag_cloud_links  |

      ######################################################### Chanel SubSplash Page - site mode ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17Y @priority_high @artifact_xapi
  Scenario: Verify that link module section should not be displayed for Chanel Sub Splash Page in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    And I navigate to "chanel from nav" category browse page
    Then I should not see link module section displayed on UI
    And I should not see link module section displayed on view source
      |  tag_cloud        |
      |  new_tag_cloud    |
      |  tag_cloud_links  |

    ######################################################### Chanel PDP Page - site mode ########################################################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17Y @priority_high @artifact_wssg
  Scenario: Verify that link module section should not be displayed for Chanel PDP Page in DOMESTIC mode on MEW
    Given I visit the mobile web site as a guest user
    And I navigate to "women's fragrance" category browse page
    When I select a random product from chanel page
    Then I should not see link module section displayed on UI
    And I should not see link module section displayed on view source
      |  tag_cloud        |
      |  new_tag_cloud    |
      |  tag_cloud_links  |
