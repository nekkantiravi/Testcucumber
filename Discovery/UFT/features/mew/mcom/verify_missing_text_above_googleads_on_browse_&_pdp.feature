#=========================
# Author: UFT
# Date Created: 19/01/2018
# Date Signed Off: TBD
# Version One: B-102414 
#=========================


  Feature: As a product owner, I would like to verify google AdSense Ads text is not missing on browse and pdp pages

  @Mew_UFT @release_17ZA @domain_discovery @project_UFT
    Scenario: verify that the google AdSense Ads text is not missing on search page
    Given I visit the mobile web home page
    When I search using mobile website for "jeans"
    Then I should be in Search Landing page using mobile website
    Then I should see "ADS FROM UNAFFILIATED SITES" text above googleads

  @Mew_UFT @release_17ZA @domain_discovery @project_UFT
    Scenario: verify that the google AdSense Ads text is not missing on browse page
    Given I visit the mobile web home page
    When I navigate the global navigation menu as follows:
      | Shop      |
      | Women     |
      | Dresses   |
    Then I should see "ADS FROM UNAFFILIATED SITES" text above googleads