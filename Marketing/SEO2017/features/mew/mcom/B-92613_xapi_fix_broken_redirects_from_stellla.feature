# Author: SEO Link Module
# Date Created: 10/11/2017
# Date Signed Off: TBD

#########################################################################################################################
# Stories Titles: MCOM MEW XAPI: Fix broken redirects from Stella changes
# Version One story numbers:: B-92613
#########################################################################################################################

Feature: As a SEO Developer,I want search & browse pages to be redirected to parent categories as they are changed in Stella.
################################## XAPI - Site Mode ###################################################
  @domain_marketing @artifact_MeW2.0 @project_SEO_2017 @release_17U @priority_high @artifact_xapi @use_manual
  Scenario Outline: Verify that destination URL should have parent category and Status code should be 200.
    Given I visit the mobile web site as a guest user
    When I navigate to "<category_url>" page
    #Ex: https://m.macys.com/shop/womens-clothing/adrianna-papell?id=65286
    Then I should see parent category in the redirected destination url
     #Ex: https://m.macys.com/shop/petite-clothing/adrianna-papell?id=65286
    And I should see status code display as 301 for category url
    And I should see status code display as 200 for destination url

    Examples:
      |category_url|
      |https://m.macys.com/shop/womens-clothing/adrianna-papell?id=65286|
      |https://m.macys.com/shop/womens-clothing/alfani-clothing?id=19306|


  Note : Could not automate this story as we are checking 301 status in the Ayima tool.


