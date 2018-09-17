# Author: SEO Link Module
# Date Created: 10/09/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM : QE Only : Ognc redirects in XAPI
# Version One story numbers:: B-95231
#########################################################################################################################

Feature: As a SEO Developer I want to fix the URL redirects to .ognc links So that there is one less hop before it reaches the intended category URL.
################################## XAPI - Site Mode ###################################################
  @domain_marketing @artifact_xapi @project_SEO_2017 @release_17U @priority_high @use_manual
  Scenario Outline: Verify that redirect Category URL should not display /catalog/index.ognc?id before they get redirected again to their final URLs and Status code should be 200.
    Given I visit the web site as a guest user
    When I navigate to "<category_url>" page
    Then I should not see index.ognc while redirecting the URL for parent category
     #Note: index.ognc should be display in network tab of browser
    And I should see status code display as 301
    And I should see single redirect to category url

    Examples:
      |category_url|
      |https://www.macys.com/shop/bed-bath?id=42806|
      |https://www.macys.com/shop/dining-entertaining?id=44626|
      |https://www.macys.com/shop/for-the-home/late-breaking-specials?id=69496|

    Note : Could not automate this story as we are checking 301 status in the Ayima tool.


