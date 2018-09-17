#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify FOBS on Home Page in DOMESTIC, ISHIP and REGISTRY mode

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery @use_domain_qual
  Scenario: Home Page - Verify FOBs in DOMESTIC mode
    Given I visit the web site as a guest user
    And I verify FOBS are displayed and return 200 OK
      | DESIGNERS             |
      | WHAT'S NEW            |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | BEAUTY                |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | GIFTS                 |
      | THE REGISTRY          |
      | SALE                  |
    #Notes:
    #Verify links return a 200 OK on GET calls with http party

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_iship @backlog_discovery
  Scenario: Home Page - Verify FOBs in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I verify FOBS are displayed and return 200 OK
      | DESIGNERS             |
      | WHAT'S NEW            |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | THE REGISTRY          |
      | SALE                  |
    #Notes:
    #Verify links return a 200 OK on GET calls with http party

  #Vone - RT-06280
  #Testlink-BLCOM-69242
  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify FOBs - REGISTRY mode
    Given I visit the web site as a registry user
    And I verify FOBS are displayed and return 200 OK
      | GETTING STARTED       |
      | BRANDS                |
      | DINING & ENTERTAINING|
      | KITCHEN              |
      | BED & BATH           |
      | HOME DECOR           |
      | LUGGAGE              |
      | HOME CARE & TECH     |
    #Notes:
    #Verify links return a 200 OK on GET calls with http party

   #Vone - RT-06280 - RT-06327
  #Testlink-BLCOM-69242,#Testlink-BLCOM-84154
  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify FOBs on REGISTRY Page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    And I verify FOBS are displayed and return 200 OK
      | GETTING STARTED       |
      | BRANDS                |
      | DINING & ENTERTAINING |
      | KITCHEN               |
      | BED & BATH            |
      | HOME DECOR            |
      | LUGGAGE               |
      | HOME CARE & TECH      |
      | SALE                  |
    #Notes:
    #Verify links return a 200 OK on GET calls with http party

  #Vone - RT-06277 - RT-06270
  #Testlink-BLCOM-76303, Testlink-BLCOM-64732
  @backlog_discovery @priority_low @artifact_navapp @domain_marketing @mode_domestic
  Scenario: Home Page - Verification for no 404 Errors and no 2nd call requests in domestic mode
    Given I visit the web site as a guest user
    Then I should not see 404 error code in homepage
#    And I should not see second call request ---- TBD
# For 2nd call verification we need all network call details. We need to do R&D on it for now commenting this step

    #Vone - RT-06277 - RT-06270
  #Testlink-BLCOM-76303, Testlink-BLCOM-64732
  @backlog_discovery @priority_low @artifact_navapp @domain_marketing @mode_iship
  Scenario: Home Page - Verification for no 404 Errors and no 2nd call requests in Iship mode
    Given I visit the web site as a guest user
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I should not see 404 error code in homepage
#    And I should not see second call request --- TBD
# For 2nd call verification we need all network call details. We need to do R&D on it for now commenting this step
