#Author: Discovery QE
#Date Created: 06/12/2015

Feature: Verify each FOB CatSplash Popular Related Searches section

  #TestLink-BLCOM-80149 Vone - RT-06558
  @use_regression @artifact_navapp @domain_discovery @priority_high @use_dsv @dsv_category_25 @use_regression_3 @mode_domestic @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Popular Searches for HOME in domestic mode
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
  @use_regression @artifact_navapp @domain_discovery @priority_high @use_dsv @dsv_category_25 @s4a_stable @use_regression_3 @mode_domestic @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Popular Searches for WOMEN in domestic mode
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
  @use_regression @artifact_navapp @domain_discovery @priority_high @use_dsv @dsv_category_25 @s4a_stable @use_regression_3 @mode_domestic @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Popular Searches for MEN in domestic mode
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
  @use_regression @artifact_navapp @domain_discovery @priority_high @use_dsv @dsv_category_25 @use_regression_3 @mode_domestic @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Popular Searches for KIDS in domestic mode
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
  @use_regression @artifact_navapp @domain_discovery @priority_high @use_dsv @dsv_category_25 @use_regression_3 @mode_domestic @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Popular Searches for BEAUTY in domestic mode
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
  @use_regression @artifact_navapp @domain_discovery @priority_high @use_dsv @dsv_category_25 @use_regression_3 @mode_domestic @dsv_desktop_sev2
  Scenario: CategorySplashPage - Verify Popular Searches for SHOES in DOMESTIC Mode
  Given I am on CatSplash Page for "SHOES" on "domestic" mode
  Then I should see the Popular Searches at the bottom
  And I click on any keyword in the Popular Searches
  Then I should see the relevant page displayed
      # Notes:
      # Do full validation - Popular searches links should be present and clickable
