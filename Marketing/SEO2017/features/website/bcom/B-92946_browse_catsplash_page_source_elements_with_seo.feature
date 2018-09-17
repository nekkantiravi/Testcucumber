# Author: SEO Improvements 2017
# Date Created: 10/06/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM:Desktop:Browse and SLP Page Source Elements with seo
# Versionone story numbers:: B-92946
#########################################################################################################################

Feature: As an SEO Manager, I would like to analyze all the references of "seo" in the DOM elements (Page Source code) for all Browse, CAT_Splash, Sub_Splash, SLP & PDP pages.
################################## Navapp - Site Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on Catsplash page on Desktop Navapp
    Given I visit the web site as a guest user
    When I navigate to "WOMEN" category page
    Then I should see seo page elements are updated as below
      | updated_mkt_content     |
      | tag_cloud_links         |
      | tag_cloud               |

  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on PDP page on Desktop Navapp
    Given I visit the web site as a guest user
    When I navigate to a random product
    Then I should see seo page elements are updated as below
      | updated_mkt_content     |
      | tag_cloud_links|
      | tag_cloud |

    ################################## XAPI - Site Mode ###################################################
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on Browse page on Desktop XAPI
    Given I visit the web site as a guest user
    When I navigate to Browse page type in SITE mode
    Then I should see seo page elements are updated as below
      | updated_mkt_content |
      | tag_cloud_links     |
      | tag_cloud           |
      | new_element_mkt_content_repositioned |
      | new_tag_cloud |