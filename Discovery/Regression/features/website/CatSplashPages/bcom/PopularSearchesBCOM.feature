#Author: Discovery QE
#Date Created: 06/12/2015

Feature: Verify each FOB CatSplash Popular Related Searches section

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for DESIGNERS in DOMESTIC mode
    Given I am on CatSplash Page for "DESIGNERS" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for SHOES in DOMESTIC mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for WOMEN in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for HANDBAGS in DOMESTIC mode
    Given I am on CatSplash Page for "HANDBAGS" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for MEN in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for GIFTS in DOMESTIC mode
    Given I am on CatSplash Page for "GIFTS" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for JEWELRY & ACCESSORIES in DOMESTIC mode
    Given I am on CatSplash Page for "JEWELRY & ACCESSORIES" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for BEAUTY in DOMESTIC mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for KIDS in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for HOME in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for SALE in DOMESTIC mode
    Given I am on CatSplash Page for "SALE" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Related Searches section for EDITORIAL in DOMESTIC mode
    Given I am on CatSplash Page for "EDITORIAL" on "domestic" mode
    Then I verify Popular Related Searches section and links return 200 OK
    # Notes:
    # Should display content and all links should return 200 OK via http party

  #TestLink-BLCOM-80149 Vone - RT-06558
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Searches for HOME in DOMESTIC mode
    Given I am on CatSplash Page for "HOME" on "domestic" mode
    Then I should see the Popular Searches at the bottom
    And I click on any keyword in the Popular Searches
    Then I should see the relevant page displayed
    # Notes:
    # Do full validation - Popular searches links should be present and clickable
    # TBD
    # Verify the landing page URL is in following format http://<environment>/buy/<keyword>
    # Links of products or categories should be displayed properly

  #TestLink-BLCOM-80149 Vone - RT-06558
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Searches for WOMEN in DOMESTIC mode
    Given I am on CatSplash Page for "WOMEN" on "domestic" mode
    Then I should see the Popular Searches at the bottom
    And I click on any keyword in the Popular Searches
    Then I should see the relevant page displayed
    # Notes:
    # Do full validation - Popular searches links should be present and clickable
  # TBD
    # Verify the landing page URL is in following format http://<environment>/buy/<keyword>
    # Links of products or categories should be displayed properly

  #TestLink-BLCOM-80149 Vone - RT-06558
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Searches for MEN in DOMESTIC mode
    Given I am on CatSplash Page for "MEN" on "domestic" mode
    Then I should see the Popular Searches at the bottom
    And I click on any keyword in the Popular Searches
    Then I should see the relevant page displayed
    # Notes:
    # Do full validation - Popular searches links should be present and clickable
    # TBD
    # Verify the landing page URL is in following format http://<environment>/buy/<keyword>
    # Links of products or categories should be displayed properly

  #TestLink-BLCOM-80149 Vone - RT-06558
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Searches for KIDS in DOMESTIC mode
    Given I am on CatSplash Page for "KIDS" on "domestic" mode
    Then I should see the Popular Searches at the bottom
    And I click on any keyword in the Popular Searches
    Then I should see the relevant page displayed
    # Notes:
    # Do full validation - Popular searches links should be present and clickable
  # TBD
    # Verify the landing page URL is in following format http://<environment>/buy/<keyword>
    # Links of products or categories should be displayed properly

  #TestLink-BLCOM-80149 Vone - RT-06558
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Searches for BEAUTY in DOMESTIC mode
    Given I am on CatSplash Page for "BEAUTY" on "domestic" mode
    Then I should see the Popular Searches at the bottom
    And I click on any keyword in the Popular Searches
    Then I should see the relevant page displayed
    # Notes:
    # Do full validation - Popular searches links should be present and clickable
  # TBD
    # Verify the landing page URL is in following format http://<environment>/buy/<keyword>
    # Links of products or categories should be displayed properly

  #TestLink-BLCOM-80149 Vone - RT-06558
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario: CategorySplashPage - Verify Popular Searches for SHOES in DOMESITC Mode
    Given I am on CatSplash Page for "SHOES" on "domestic" mode
    Then I should see the Popular Searches at the bottom
    And I click on any keyword in the Popular Searches
    Then I should see the relevant page displayed
    # Notes:
    # Do full validation - Popular searches links should be present and clickable

  #Testlink-BLCOM-75408,Testlink-BLCOM-84280,Testlink-BLCOM-84275
  # Vone - RT-06301 - RT-06516
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario Outline: CategorySplashPage - Verify tag cloud in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to category splash page
    Then I verify that SEO tag clouds are displayed
    And I verify the page for keyword which has "<options>" from popular searches
  Examples:
    | options  |
    | /buy URL |
      # Notes
      # Verify Popular Searches in splash page:
      # Navigate to BRANDSEARCH PAGE by clicking on popular search link
      # Verify in BRANDSEARCH
      # appended as "/shop/{searchphrase}"
      # relevant product displayed in the "site" page
      # facets, Pagination, and sort by in the page
      # Verify all three different types of links - /buy, /shop, cms/slp/2/
  # TBD
    # Verify the landing page URL is in following format http://<environment>/buy/<keyword>
    # Links of products or categories should be displayed properly

  #TBD - need the same scenarios for ISHIP & REGISTRY mode

  #Testlink-BLCOM-84275
  @domain_discovery @mode_domestic @use_regression @migrated_to_sdt @priority_high
  Scenario Outline: CategorySplashPage - Verify tag cloud in DOMESTIC mode
    Given I visit the web site as a guest user
    When I navigate to wedding registry by clicking registry link
    And I navigate to random category splash page
    Then I verify that SEO tag clouds are displayed
    And I verify the page for keyword which has "<options>" from popular searches
  Examples:
    | options  |
    | /buy URL |
      # Notes
      # Verify Popular Searches in splash page:
      # Navigate to BRANDSEARCH PAGE by clicking on - popular search link
      # Verify in BRANDSEARCH
      # appended as "/shop/{searchphrase}"
      # relevant product displayed in the "site" page
      # facets, Pagination, and sort by in the page
      # Verify all three different types of links - /buy, /shop, cms/slp/2/
