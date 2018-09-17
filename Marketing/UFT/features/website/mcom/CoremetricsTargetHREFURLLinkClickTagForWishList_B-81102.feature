#Author: UFT team
#Date Created: 06/08/2017
#Date Signed Off:
#Version One: B-81102

Feature: As a product owner, I would like to ensure that Target HREF values of Link Click Tag are firing for wishlists.

  @artifact_shopapp @domain_marketing @release_17K @mode_domestic @project_UFT @project_UFT_COREMETRICS
  Scenario: Verify Target HREF URL under link click tag when we click on list link on wishlist overlay on PDP as a signed in user
    Given I visit the web site as a guest user
    And I sign in to my existing profile
    And I navigate to the "Polos" browse page under "Men"
    And I select a random member product
    Then I should be redirected to PDP page
    When I select product related attributes from PDP
    And I add the product to wishlist
    And I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a registered user

  @artifact_shopapp @domain_marketing @release_17K @mode_domestic @project_UFT @project_UFT_COREMETRICS
  Scenario: Verify Target HREF URL under link click tag when we click on list link on wishlist overlay on PDP as a guest user
    Given I visit the web site as a guest user
    And I navigate to the "Polos" browse page under "Men"
    And I select a random member product
    Then I should be redirected to PDP page
    When I select product related attributes from PDP
    And I add the product to wishlist
    And I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a guest user

  @artifact_shopapp @domain_marketing @release_17K @mode_registry @project_UFT @project_UFT_COREMETRICS
  Scenario: Verify Target HREF URL under link click tag when we click on list link on wishlist overlay on Registry PDP as a signed in user
    Given I visit the web site as a guest user
    And I sign in to my existing profile
    And I navigate to registry home page
    And I navigate to the "Electrics" browse page under "Kitchen"
    And I select a random member product
    Then I should be redirected to PDP page
    When I select product related attributes from PDP
    And I add the product to wishlist
    And I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a registered user

  @artifact_shopapp @domain_marketing @release_17K @mode_registry @project_UFT @project_UFT_COREMETRICS
  Scenario: Verify Target HREF URL under link click tag when we click on list link on wishlist overlay on Registry PDP as a guest user
    Given I visit the web site as a guest user
    And I navigate to registry home page
    And I navigate to the "Electrics" browse page under "Kitchen"
    And I select a random member product
    Then I should be redirected to PDP page
    When I select product related attributes from PDP
    And I add the product to wishlist
    And I select wishlist link on the wishlist overlay in PDP page
    Then I should see wishlist landing page as a guest user