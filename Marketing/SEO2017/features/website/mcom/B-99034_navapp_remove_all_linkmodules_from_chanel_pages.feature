# Author: SEO Optimization 2017
# Date Created: 12/21/2017
# Date Signed Off: TBD
#########################################################################################################################
# Story Titles: MCOM Desktop: Remove all Link Module Coding from Chanel Category, PDP and SLP pages
# Versionone story numbers::B-99034
########################################################################################################################################################
Feature: Remove all Link Module/Tag Cloud coding from all Chanel pages (Browse, CatSplash, SubSplash,PDP and SLP pages)
###################################################### Chanel Browse Page - site mode ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that link module should not be displayed for Chanel Browse Page in DOMESTIC mode
    Given I visit the web site as a guest user
    And I navigate to "WOMEN'S FRAGRANCE" browse page from cat splash page
    Then I should not see link module displayed on UI
    And I should not see link module displayed on view source
      |  tag_cloud        |
      |  new_tag_cloud    |
      |  tag_cloud_links  |

 ######################################################### Chanel SubSplash Page - site mode ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that link module should not be displayed for Chanel Sub Splash Page in DOMESTIC
    Given I visit the web site as a guest user
    When I navigate to the "CHANEL" sub splash page under "BEAUTY"
    Then I should not see link module displayed on UI
    And I should not see link module displayed on view source
      |  tag_cloud        |
      |  new_tag_cloud    |
      |  tag_cloud_links  |

    ######################################################### Chanel PDP Page - site mode ########################################################################################
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17Y @priority_high
  Scenario: Verify that link module should not be displayed for Chanel PDP Page in DOMESTIC mode
    Given I visit the web site as a guest user
    When I search for "CHANEL MEN"
    When I select the random product from chanel page
    Then I should not see link module displayed on UI
    And I should not see link module displayed on view source
      |  tag_cloud        |
      |  new_tag_cloud    |
      |  tag_cloud_links  |