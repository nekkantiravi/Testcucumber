# Author: SEO Improvements 2017
# Date Created: 10/06/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: BCOM:MEW:Browse , Catsplash and PDP Page Source Elements with seo
# Versionone story numbers:: B-95354
#########################################################################################################################

Feature: As an SEO Manager, I would like to analyze all the references of "seo" in the DOM elements (Page Source code) for all Browse, CAT_Splash & PDP pages.
################################## Catsplash Page - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on Category Splash page on MeW non XAPI
    Given I visit the mobile web site as a guest user
    When  I navigate the global navigation menu as follows:
      | Women          |
    Then  I should see updated seo page elements as below
      | tag_cloud              |
      | tag_cloud_links        |
      | copy_block_container   |

################################## Browse Page - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on Category Browse page on MeW non XAPI
    Given I visit the mobile web site as a guest user
    When  I navigate to category browse page that has copy block
    Then  I should see updated seo page elements as below
      | tag_cloud              |
      | tag_cloud_links        |
      | copy_block_container   |

    ################################## PDP Page - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on PDP page on MeW non XAPI
    Given I visit the mobile web site as a guest user
    And   I navigate to "member_product and link_module" product PDP page
    Then  I should see updated seo page elements as below
      | tag_cloud              |
      | tag_cloud_links        |
      | seo_product            |
