# Author: SEO Link Module
# Date Created: 10/09/2017
# Date Signed Off: TBD

#########################################################################################################################
# Story Titles: MCOM::Navapp::Reverse css of Linkmodule on PDP pages
# Version One story numbers::   B-97566
##########################################################################################################################

Feature:As a SEO Developer I want to position the popular searches (link module/tagcloud) component high up in the DOM Treeso that the bots can get to the tag cloud content faster. 
################################## PDP page - Site Mode ###################################################
  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17W @priority_high
  Scenario: Verfiy that link module component should be moved up in the view source page for Desktop
    Given I visit the web site as a guest user
    And I navigate to "master_product and link_module" product PDP page
    And I should see link module higher up in the page view source

  @domain_marketing @artifact_navapp @project_SEO_2017 @release_17W @priority_high
  Scenario: Verfiy that link module component should be moved up in the view source page for Desktop
    Given I visit the web site as a guest user
    And I navigate to "member_product and link_module" product PDP page
    And I should see link module higher up in the page view source