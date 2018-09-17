# Author: UFT QE
# Date Created: 10/12/2017
# Version One: B-93158

Feature: As a product owner, I would like to fix an error that is occurring when customers are toggling through "may we suggest"
         on lists


  @artifact_shopapp @domain_selection @priority_high @mode_domestic @project_UFT @release_17T
  Scenario: Verify error page is not displated while clicking on arrow icons in May We Suggest recommendation panel on list page as a guest user
    Given I visit the web site as a guest user
    When I select wishlist link in header
    Then I should see wishlist landing page as a guest user
    When I shuffle between left and right arrow icons in 'May We Suggest' recommendation panel
    Then I should see wishlist landing page as a guest user

  @artifact_shopapp @domain_selection @priority_high @mode_domestic @project_UFT @release_17T
  Scenario: Verify error page is not displated while clicking on arrow icons in May We Suggest recommendation panel on list page as a sign in user
    Given I visit the web site as a registered user
    When I select wishlist link in header
    Then I should see wishlist landing page as a registered user
    When I shuffle between left and right arrow icons in 'May We Suggest' recommendation panel
    Then I should see wishlist landing page as a registered user