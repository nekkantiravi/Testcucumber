# Author: SEO Improvements 2017
# Date Created: 6/12/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM/BCOM::MEW: Create killswitch to disable creation of links for hidden categories
# Versionone: B-81430
# Versionone: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story%3A3419081
#########################################################################################################################

Feature: As a developer I want to be able to disable this feature that would create URLS for available facets on categories or search pages.

  #########################################################################################################################
  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17N @manual
  Scenario Outline: Verify that kill switch 'CONFIG_SEO_CREATE_BROWSEHIDE_LINKS' should be displayed in ENV_CONFIG on category page
    Given I visit the mobile web site as a guest user on <mode>
    When I navigate to random browse page from site menu using mobile website
    Then I should see the kill switch 'CONFIG_SEO_CREATE_BROWSEHIDE_LINKS' in ENV_CONFIG

    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_marketing @artifact_navapp @project_SEO_Left_Nav @priority_high @release_17N @manual
  Scenario Outline: Verify that kill switch 'CONFIG_SEO_CREATE_BROWSEHIDE_LINKS' should be displayed in ENV_CONFIG on search page
    Given I visit the mobile web site as a guest user on <mode>
    When I search for "dresses"
    Then I should see the kill switch 'CONFIG_SEO_CREATE_BROWSEHIDE_LINKS' in ENV_CONFIG

    Examples:
      | mode     |
      | domestic |
      | iship    |