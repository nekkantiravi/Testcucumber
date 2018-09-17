# Author: SEO Link Module
# Date Created: 10/09/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM:: Link Module : SLP URL Redirection. Ignore externalHostUrl with browse category
# Version One story numbers::   B-91675
#########################################################################################################################

Feature:As a MCOM SEO Business Manager, I would like to ignore the External Host URL replacement when it is different than SLP format.

  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17T @priority_high @use_manual
  Scenario Outline: Verify that user should navigate to SLP page when External host URL contains the SLP
    Given I visit the web site as a guest user
    When I navigate to “<page>" page
    Then I should see the seo tag cloud
    When I hover on tag cloud link which has external host URL as SLP
  #Ex :<externalHostUrl>/shop/b/adidas-shirts?id=75686</externalHostUrl>
    Then I should see link format as SLP
    When I click on SLP link in link module
    Then I should see that target URL navigates to SLP page
  # We should not see any 301 re-directs here
    Examples:
      | page            |
      | Category Splash |
      | Browse          |
      | Sub splash      |
      | Member PDP      |
      | Master PDP      |
      | SLP             |

  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17T @priority_high @use_manual
  Scenario Outline: Verify that user should navigate to DLP page when external host URL contains the browse
   Given I visit the web site as a guest user
    When I navigate to “<page>" page
    Then I should see the seo tag cloud
    When I hover on tag cloud link which has external URL contains browse
    #Ex :<externalHostUrl>/shop/makeup-and-perfume/bare-minerals?id=54541</externalHostUrl>
    Then I should see link format as DLP
    When I click on DLP link in link module
    Then I should see that target URL navigates to DLP page
    # We should not see any 301 re-directs here
    Examples:
      | page            |
      | Category Splash |
      | Browse          |
      | Sub splash      |
      | Member PDP      |
      | Master PDP      |
      | SLP             |
