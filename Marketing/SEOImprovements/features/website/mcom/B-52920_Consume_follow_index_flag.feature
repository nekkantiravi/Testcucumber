# Author: SEO Improvements 2016
# Date Created: 6/29/2016
# Date Signed Off: TBD

##############################################################################################################################################################
# Story Titles: MCOM:NavApp:Tech: Consume follow/index flag from Discovery/Apollo services and MCOM::UI:Tech: Metadata: toggle to remove of noindex & nofollow
# Versionone story numbers:: B-52920 and B-51793
###############################################################################################################################################################

Feature: Based on keyword passed on from UI (either from DLP page or onsite search), meta tag with "INDEX, FOLLOW" or "NOINDEX, NOFOLLOW" value should be set on search results page.

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that robots meta tag should display as <META NAME="ROBOTS" CONTENT="INDEX, FOLLOW"> in view page source for whitelisted keywords
    Given I visit the web site as a guest user
    When I search for "zwelling"
    Then I should see "index, follow" attribute is appended in robots meta tag in view page source

    #Note: This scenario need to verify in Navapp, Shopapp and DLP pages search field.

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that robots meta tag should display as <META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW"> in view page source for blacklisted keywords
    Given I visit the web site as a guest user
    When I search for "jeans"
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source

    #Note: This scenario need to verify in Navapp, Shopapp and DLP pages search field.

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that robots meta tag should be ignored, when blacklisted keyword redirected to browse category
    Given I visit the web site as a guest user
    When I search for "dinnerware"
    Then I should see it is redirected to browse category
    And I should not see robots meta tag in view page source

    #Note: This scenario need to verify in Navapp, Shopapp and DLP pages search field.

  @wip @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that robots meta tag should be ignored, when whitelisted keyword redirected to browse category
    Given I visit the web site as a guest user
    When I search for "whitelisted keywords"
    Then I should see it is redirected to browse category
    And I should not see robots meta tag in view page source

    #Note: This scenario need to verify in Navapp, Shopapp and DLP pages search field.

###################################INTERNATIONAL MODE#######################################################

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that robots meta tag should display as <META NAME="ROBOTS" CONTENT="INDEX, FOLLOW"> in view page source for whitelisted keywords in International mode
    Given I visit the web site as a guest user in "iship" mode
    And I search for "zwelling"
    Then I should see "index, follow" attribute is appended in robots meta tag in view page source

    #Note: This scenario need to verify in Navapp, Shopapp and DLP pages search field.

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that robots meta tag should display as <META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW"> in view page source for blacklisted keywords in International mode
    Given I visit the web site as a guest user in "iship" mode
    And I search for "jeans"
    Then I should see "noindex, nofollow" attribute is appended in robots meta tag in view page source

    #Note: This scenario need to verify in Navapp, Shopapp and DLP pages search field.

  @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that robots meta tag should be ignored, when blacklisted keyword redirected to browse category in International mode
    Given I visit the web site as a guest user in "iship" mode
    And I search for "dinnerware"
    Then I should see it is redirected to browse category
    And I should not see robots meta tag in view page source

    #Note: This scenario need to verify in Navapp, Shopapp and DLP pages search field.

  @wip @domain_marketing @artifact_navapp @project_SEO_improvements @release_16K @priority_high @use_regression
  Scenario: Verify that robots meta tag should be ignored, when whitelisted keyword redirected to browse category in International mode
    Given I visit the web site as a guest user in "iship" mode
    And I search for "whitelisted keywords"
    Then I should see it is redirected to browse category
    And I should not see robots meta tag in view page source

    #Note: This scenario need to verify in Navapp, Shopapp and DLP pages search field.