#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify FOBS on Home Page in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery @use_bat @use_domain_qual
  Scenario: Home Page - Verify FOBs in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify FOBS are displayed and return a 200 OK
      | FOBS                   |
      | HOME                   |
      | BED & BATH             |
      | WOMEN                  |
      | MEN                    |
      | JUNIORS                |
      | KIDS                   |
      | BEAUTY                 |
      | SHOES                  |
      | HANDBAGS               |
      | JEWELRY                |
      | WATCHES                |
      | BRANDS                 |
    #Notes:
    #Verify links return a 200 OK on GET calls with http party

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_iship @backlog_discovery
  Scenario: Home Page - Verify FOBs in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I verify FOBS are displayed and return a 200 OK
      | FOBS                   |
      | HOME                   |
      | BED & BATH             |
      | WOMEN                  |
      | MEN                    |
      | JUNIORS                |
      | KIDS                   |
      | SHOES                  |
      | HANDBAGS & ACCESSORIES |
      | JEWELRY                |
      | WATCHES                |
      | BRANDS                 |
    #Notes:
    #Verify links return a 200 OK on GET calls with http party

#Testlink-MCOM-96575
  #Vone - RT-07346
  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify FOBs in REGISTRY mode
    Given I visit the web site as a registry user
    And I verify FOBS are displayed and return a 200 OK
      | FOBS                  |
      | STARTER IDEAS         |
      | WEDDING REGISTRY      |
      | DINING                |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | CLEANING & ORGANIZING |
      | BRANDS                |
      | WEDDING SHOP          |
    #Notes:
    #Verify links return a 200 OK on GET calls with http party

  #Testlink-MCOM-96575
  #Vone - RT-07346
  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify FOBs on REGISTRY Page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I verify FOBS are displayed and return a 200 OK
      | FOBS                  |
      | STARTER IDEAS         |
      | WEDDING REGISTRY      |
      | DINING                |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | CLEANING & ORGANIZING |
      | BRANDS                |
      | WEDDING SHOP          |
    #Notes:
    #Verify links return a 200 OK on GET calls with http party
