# Author: SEO Improvements 2017
# Date Created: 10/06/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM:Desktop:Browse and SLP Page Source Elements with seo
# Versionone story numbers:: B-92947
#########################################################################################################################

Feature: As an SEO Manager, I would like to analyze all the references of "seo" in the DOM elements (Page Source code) for all Browse, CAT_Splash, Sub_Splash, SLP & PDP pages.
################################## XAPI - Site Mode ###################################################
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on Catsplash page on Desktop XAPI
    Given I visit the web site as a guest user
    When I navigate to random category splash page
    Then I should see seo page elements are updated as below
      | new_tag_cloud                  |
      | new_element_mkt_content        |
      | new_element_mkt_content_repositioned|

  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on Subsplash page on Desktop XAPI
    Given I visit the web site as a guest user
    When I navigate to the "MICHAEL Michael Kors" sub splash page under "HANDBAGS"
    Then I should see seo page elements are updated as below
      | new_tag_cloud                  |
      | new_element_mkt_content        |
      | new_element_mkt_content_repositioned|

  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on Browse page on Desktop XAPI
    Given I visit the web site as a guest user
    When I navigate to Browse page type in SITE mode
    Then I should see seo page elements are updated as below
      | new_tag_cloud                  |
      | new_element_mkt_content        |
      | new_element_mkt_content_repositioned|

  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on SLP page on Desktop XAPI
    Given I visit the web site as a guest user
    When I navigate the "Static_Browse" SLP category which is under "71233" parent category
    Then I should see seo page elements are updated as below
      | new_tag_cloud                  |
      | new_element_mkt_content        |

    ################################## Navapp  - Site Mode ##################################################
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17U @priority_high
  Scenario: Verify the updated seo page elements on PDP page on Desktop Navapp (non-XAPI)
    Given I visit the web site as a guest user
    And  I navigate to "master_product and link_module" product PDP page
    Then I should see updated seo page elements as below in pdp page
      | new_pdp_mkt_section      |
      | new_product_mkt_data     |
      | new_product_mkt_container|