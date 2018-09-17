  #BCOM Registry Lean Lab
  #Author: Mirna Silva
  #V-1 Story: B-95077

  Feature: Verify that on BVR page we have some products recommendation for categories that were not added to the registry

    @B-95077 @BVR @registry @domain_selection @use_regression
  Scenario: Verify that user can see Empty Category Recommendation section displayed
    Given I visit the website as a bvr user using rest services
    And I navigate to "registrable and orderable" product PDP page
    And I add registry product to BVR page from standard PDP
    When I navigate to bvr page
    Then I should see Empty Category Recommendation displayed

    @B-95077 @BVR @registry @domain_selection @use_regression
  Scenario: Verify that Empty Category Recommendation is displayed only when view is sorted by Categories
    Given I visit the website as a bvr user using rest services
    And I navigate to "registrable and orderable" product PDP page
    And I add registry product to BVR page from standard PDP
    When I navigate to bvr page
    Then I sort by "Price High to Low"
    And I should not see Empty Category Recommendation displayed

    @B-95077 @BVR @registry @domain_selection @use_regression
  Scenario: Verify that user can add product from Empty Category Recommendation panel to registry
    Given I visit the website as a bvr user using rest services
    And I navigate to "registrable and orderable" product PDP page
    And I add registry product to BVR page from standard PDP
    When I navigate to bvr page
    And I add recommended product to registry
    Then I should see added product
    And I should see the recommended items with same category as product recently added
    When I refresh the page
    Then I should not see the recommended items with same category as product recently added

    @B-95077 @BVR @registry @domain_selection @use_regression
  Scenario: Verify that user can navigate to PDP by clicking on product thumbnail
    Given I visit the website as a bvr user using rest services
    And I navigate to "registrable and orderable" product PDP page
    And I add registry product to BVR page from standard PDP
    When I navigate to bvr page
    Then I should see Empty Category Recommendation displayed
    When I click on product thumbnail from recommendation section
    Then I should be redirected to PDP page