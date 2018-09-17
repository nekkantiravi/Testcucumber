#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Jan.02,2018
#---------------------------------------------------

Feature: MemberPDP_FOBsCategories Validation & Verification Scenarios

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_fob
  Scenario: Verify the FOB functionality on Member PDP Site mode on desktop
    Given I visit the home page on desktop as a guest user
    When I navigate directly to "member" PDP site mode on desktop
    And I hover over "women" FOB tabs on PDP site mode on desktop
    And I select "Dresses" link from FOB on PDP site mode on desktop
    Then I verify the "women" FOB link landing page from PDP site mode on desktop

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_fob
  Scenario: Verify the FOB functionality on Member PDP Site mode on desktop
    Given I visit the home page on desktop as a guest user
    When I navigate directly to "member" PDP site mode on desktop
    And I hover over "men" FOB tabs on PDP site mode on desktop
    And I select "jeans" link from FOB on PDP site mode on desktop
    Then I verify the "men" FOB link landing page from PDP site mode on desktop

  @domain_selection @use_regression @mcom_domestic_pdp @member_domestic_pdp @member_pdp_fob @wip
  Scenario Outline: Verify the FOB functionality on Member PDP Site mode on desktop
    Given I visit the home page on desktop as a guest user
    When I navigate directly to "member" PDP site mode on desktop
    And I hover over "<category>" FOB tabs on PDP site mode on desktop
    And I select "<subcategory>" link from FOB on PDP site mode on desktop
    And I select a random member product
    Then I verify the basic elements on member PDP site mode on desktop
    And I verify the product category on member PDP site mode on desktop
    And I click the A2B button on member PDP site mode on desktop
    Then I verify the AddToBag drawer as the "member" product being added in site mode on desktop
    Examples:
      |category   |subcategory                |
      |Women      |Leggings                   |
      |Men        |Jeans                      |
      |Women      |Trendy Plus Size           |
      |Men        |Sneakers                   |
      |Women      |All Petites                |
      |Men        |Hats, Gloves & Scarves     |
      |Women      |Graphic Tees & Sweatshirts |
      |Men        |Golf Shop                  |
      |Women      |Cashmere Shop              |
      |Men        |Outdoor & Camping          |
      |Women      |Hats, Gloves & Scarves     |
      |Men        |Tech Shop                  |
      |Women      |Graphic Tees & Sweatshirts |
#      |Home       |
#      |Bed & Bath |
#      |Shoes      |
#      |Handbags   |
#      |Beauty     |
#      |Kids       |
#      |Juniors    |
#      |Jewelry    |
#      |Watches    |
#      |Active     |
#      |Brands     |
