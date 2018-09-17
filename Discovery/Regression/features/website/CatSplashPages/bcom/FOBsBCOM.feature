#Author: Discovery QE
#Date Created: 10/1/2015


Feature: Verify FOB's functionality in catsplash page for SITE/ISHIP/REGISTRY modes

  #Vone - RT-06280
 #Testlink-BLCOM-69242
  @domain_discovery @mode_registry @use_regression @migrated_to_sdt @priority_high
 Scenario: CategorySplashPage - Verify FOBs navigation in REGISTRY mode
   Given I visit the web site as a guest user
   And I navigate to wedding registry by clicking registry link
   When I navigate to random category splash page
   And I verify that fobs are displayed and return a 200 OK
   #Notes: Click on each category and observe the navigation and redirect back to homepage

  #Testlink-BLCOM-69238
  @domain_discovery @mode_registry @use_regression @migrated_to_sdt @priority_high
   Scenario: CategorySplashPage - Verify FOB category URLs in REGISTRY mode
    Given I visit the web site as a guest user
    And I navigate to wedding registry by clicking registry link
    When I navigate to random category splash page
    Then I verify navigated URL is in "/shop/wedding-registry/" format
    When I navigate to random category browse page
    Then I verify navigated URL is in "/shop/wedding-registry/" format
    