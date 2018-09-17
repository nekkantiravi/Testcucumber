# Author: SEO Link Module
# Date Created: 10/11/2017
# Date Signed Off: TBD

#########################################################################################################################
# Stories Titles: QE ONLY ::MEW:: Verify OGNC redirects in Xapi
# Version One story numbers:: B-99809
#########################################################################################################################

Feature: As a SEO Developer I want to fix the URL redirects to .ognc links So that there is one less hop before it reaches the intended category URL.
################################## XAPI - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_xapi @use_manual
  Scenario Outline: Verify that Category URL should not contain index.ognc before they get redirected to their final URLs and Status code should be 200.
    Given I visit the mobile web site as a guest user
    When I navigate to "<category_url>" page
    Then I should not see index.ognc while redirecting to the destination url
     #Note: index.ognc should be display in network tab of browser
    And I should see status code display as 301 for category_url
    And I should see single redirect to category url
    And I should see status code display as 200 for destination url
    Examples:
      |category_url|
      |https://m.macys.com/shop/vacuums-steam-cleaners/calphalon?id=7749|
      |https://m.macys.com/shop/mgts-spring-2015/paradise-found?id=70343|
      |https://m.macys.com/shop/shoes/franco-sarto-wedges?id=63215|

  Note : Could not automate this story as we are checking 301 status in the Ayima tool.


