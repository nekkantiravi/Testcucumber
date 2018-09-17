#Author: Loyalty Bonus Offers Visibility Team
#Date Created: May 11, 2015
#Date Modified:

#VersionOne story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=+B-14684
#VersionOne story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=+B-18241
#VersionOne story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=+B-15320
#VersionOne story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=+B-20315

Feature: As a bloomindales.com customer I can see what products are eligible for a Loyallist global offer on browse and search pages.

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product which is eligible for loyallist global offer in browse page
    Given I visit the web site as a <user>
    When I navigate to browse page having product with options in domestic mode:
      | dml_product            | true         |
      | <product_type>         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I verify loyallist global offer thumbnail call out for the eligible product
    Examples:
      | user            | promo_type               | product_type     |
      | guest user      | Loyalty_Offer_Fixed      | master_product   |
    #  | registered user | Loyalty_Offer_Multiplier | standard_product |
    #  | loyallist       | Loyalty_Offer_Fixed      | standard_product |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product which is eligible for loyallist global offer in search results page
    Given I visit the web site as a <user>
    When I search product by name with options in domestic mode:
      | dml_product            | true         |
      | <product_type>         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I verify loyallist global offer thumbnail call out for the eligible product
    Examples:
      | user            | promo_type               | product_type     |
    #  | guest user      | Loyalty_Offer_Fixed      | standard_product |
      | registered user | Loyalty_Offer_Fixed      | standard_product |
    #  | loyallist       | Loyalty_Offer_Multiplier | master_product   |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product eligible for Loyalty Offer Fixed and Loyalty Offer Multiplier on Browse page
    Given I visit the web site as a <user>
    When I navigate to browse page having product with options in domestic mode:
      | dml_product            | true                                             |
      | <product_type>         | true                                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed and Loyalty_Offer_Multiplier |
    Then I verify loyallist global offer thumbnail call out for the eligible product
    Examples:
      | user            | product_type     |
    #  | guest user      | standard_product |
    #  | registered user | master_product   |
      | loyallist       | standard_product |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product eligible for more then one Loyalty Offer Fixed ending on the same day on Browse page
    Given I visit the web site as a <user>
    When I navigate to browse page having product with options in domestic mode:
      | dml_product            | true                             |
      | <product_type>         | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_same_day |
    Then I verify loyallist global offer thumbnail call out for the eligible product
    Examples:
      | user            | product_type     |
      | guest user      | standard_product |
   #   | registered user | master_product   |
   #   | loyallist       | standard_product |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product eligible for more then one Loyalty Offer Fixed ending on different days on Browse page
    Given I visit the web site as a <user>
    When I navigate to browse page having product with options in domestic mode:
      | dml_product            | true                             |
      | <product_type>         | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_diff_day |
    Then I verify loyallist global offer thumbnail call out for the eligible product
    Examples:
      | user            | product_type     |
    #  | guest user      | standard_product |
      | registered user | master_product   |
    #  | loyallist       | standard_product |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product eligible for Loyalty Offer Fixed and Loyalty Offer Multiplier on search results page
    Given I visit the web site as a <user>
    When I search product by name with options in domestic mode:
      | dml_product            | true                                             |
      | <product_type>         | true                                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed and Loyalty_Offer_Multiplier |
    Then I verify loyallist global offer thumbnail call out for the eligible product
    Examples:
      | user            | product_type     |
    #  | guest user      | standard_product |
    #  | registered user | master_product   |
      | loyallist       | standard_product |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product eligible for more then one Loyalty Offer Fixed ending on the same day on search results page
    Given I visit the web site as a <user>
    When I search product by name with options in domestic mode:
      | dml_product            | true                             |
      | <product_type>         | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_same_day |
    Then I verify loyallist global offer thumbnail call out for the eligible product
    Examples:
      | user            | product_type     |
      | guest user      | standard_product |
    #  | registered user | master_product   |
    #  | loyallist       | standard_product |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product eligible for more then one Loyalty Offer Fixed ending on different days on search results page
    Given I visit the web site as a <user>
    When I search product by name with options in domestic mode:
      | dml_product            | true                             |
      | <product_type>         | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_diff_day |
    Then I verify loyallist global offer thumbnail call out for the eligible product
    Examples:
      | user            | product_type     |
     # | guest user      | standard_product |
      | registered user | standard_product |
     # | loyallist       | standard_product |

  @project_lbov @mode_iship @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify that loyallist promotional callout under the product thumbnail which is eligible for loyalty promotion in domestic mode should not be displayed in category browse page in international mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I navigate to browse page having product with options in iship mode:
      | dml_product            | true         |
      | iship_eligible         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I verify loyallist global offer thumbnail call out is not displayed for the eligible product
    Examples:
      | promo_type               |
      | Loyalty_Offer_Fixed      |
    #  | Loyalty_Offer_Multiplier |

  @project_lbov @mode_iship @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify that loyallist promotional callout under the product thumbnail which is eligible for loyalty promotion in domestic mode should not be displayed in search results page in international mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    And I search product by name with options in iship mode:
      | dml_product            | true         |
      | iship_eligible         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I verify loyallist global offer thumbnail call out is not displayed for the eligible product
    Examples:
      | promo_type               |
   #   | Loyalty_Offer_Fixed      |
      | Loyalty_Offer_Multiplier |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product which is eligible for loyallist global offer in registry browse page
    Given I visit the web site as a guest user
    When I navigate to browse page having product with options in registry mode:
      | dml_product            | true         |
      | registrable            | true         |
      | <product_type>         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I verify loyallist global offer thumbnail call out for the eligible product
    Examples:
      | promo_type                                       | product_type     |
      | Loyalty_Offer_Fixed                              | master_product   |
    #  | Loyalty_Offer_Multiplier                         | standard_product |
    #  | Loyalty_Offer_Fixed and Loyalty_Offer_Multiplier | master_product   |
    #  | Loyalty_Offer_Fixed exp_same_day                 | master_product   |
    #  | Loyalty_Offer_Fixed exp_diff_day                 | standard_product |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product which is eligible for loyallist global offer in registry search results page
    Given I visit the web site as a guest user
    When I navigate to registry page
    And I search product by name with options in registry mode:
      | dml_product            | true         |
      | registrable            | true         |
      | <product_type>         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I verify loyallist global offer thumbnail call out for the eligible product
    Examples:
      | promo_type                                       | product_type     |
     # | Loyalty_Offer_Fixed                              | master_product   |
     # | Loyalty_Offer_Multiplier                         | standard_product |
     # | Loyalty_Offer_Fixed and Loyalty_Offer_Multiplier | master_product   |
     # | Loyalty_Offer_Fixed exp_same_day                 | master_product   |
      | Loyalty_Offer_Fixed exp_diff_day                 | standard_product |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15K @artifact_navapp @use_iteration
  Scenario: Verify category refinement by products eligible for a Loyallist global offer as a guest user in site mode
    Given I visit the web site as a guest user
    When I navigate to browse category having product with options in domestic mode:
      | dml_product            | true       |
      | loyallist_global_offer | true       |
      | with_facet             | Promotions |
    And I select "Loyallist Points Offers" item from "PROMOTIONS" facet on left nav
    Then I should see only loyallist global offer eligible products

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15K @artifact_navapp @use_iteration
  Scenario: Verify category refinement by products eligible for a Loyallist global offer as a guest user in registry mode
    Given I visit the web site as a guest user
    When I navigate to browse category having product with options in registry mode:
      | dml_product            | true       |
      | loyallist_global_offer | true       |
      | registrable            | true       |
      | with_facet             | Promotions |
    And I select "Loyallist Points Offers" item from "PROMOTIONS" facet on left nav
    Then I should see only loyallist global offer eligible products

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15K @artifact_navapp @use_iteration
  Scenario: Verify refinement by products eligible for a Loyallist global offer as a guest user in site mode on search result page
    Given I visit the web site as a guest user
    When I search product by name with options in domestic mode:
      | dml_product            | true |
      | loyallist_global_offer | true |
    And I select "Loyallist Points Offers" item from "PROMOTIONS" facet on left nav
    Then I should see only loyallist global offer eligible products

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15K @artifact_navapp @use_iteration
  Scenario: Verify refinement by products eligible for a Loyallist global offer as a guest user in registry mode on search result page
    Given I visit the web site as a guest user
    When I navigate to registry page
    And I search product by name with options in registry mode:
      | dml_product            | true |
      | registrable            | true |
      | loyallist_global_offer | true |
    And I select "Loyallist Points Offers" item from "PROMOTIONS" facet on left nav
    Then I should see only loyallist global offer eligible products

  @project_lbov @mode_iship @priority_high @domain_marketing @release_15K @artifact_navapp @use_iteration
  Scenario: Verify category refinement by products eligible for a Loyallist global offer as a guest user in iship mode
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I navigate to browse category having product with options in iship mode:
      | dml_product            | true       |
      | iship_eligible         | true       |
      | loyallist_global_offer | true       |
      | with_facet             | Promotions |
    Then I should not see "Loyallist Points Offers" under "PROMOTIONS" facet

  @project_lbov @mode_iship @priority_high @domain_marketing @release_15K @artifact_navapp @use_iteration
  Scenario: Verify refinement by products eligible for a Loyallist global offer as a guest user in iship mode on search result page
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I search product by name with options in iship mode:
      | dml_product            | true       |
      | iship_eligible         | true       |
      | loyallist_global_offer | true       |
    Then I should not see "Loyallist Points Offers" under "PROMOTIONS" facet

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product which is eligible for loyallist global offer and other promotions in browse page
    Given I visit the web site as a <user>
    When I navigate to browse page having product with options in domestic mode:
      | dml_product      | true                |
      | <product_type>   | true                |
      | with_promotion   | <promo_combination> |
    Then I verify promotion sequencing on product thumbnail
    Examples:
      | user            | promo_combination                                                | product_type     |
      | guest user      | Loyalty_Offer_Multiplier and PWP                                 | standard_product |
  #  | guest user      | Percent_Off_Order and Loyalty_Offer_Multiplier and Threshold_GWP | standard_product |
  #  | registered user | Promotional_Pricing and Loyalty_Offer_Fixed                      | master_product   |
  #  | loyallist       | Loyalty_Offer_Fixed and Bundled_GWP                              | master_product   |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the thumbnail call out for a product which is eligible for loyallist global offer and other promotions in search results page
    Given I visit the web site as a <user>
    When I search product by name with options in domestic mode:
      | dml_product      | true                |
      | <product_type>   | true                |
      | with_promotion   | <promo_combination> |
    Then I verify promotion sequencing on product thumbnail
    Examples:
      | user            | promo_combination                                                  | product_type     |
     # | guest user      | Dollar_Off_Order and Loyalty_Offer_Multiplier                      | standard_product |
     # | guest user      | Loyalty_Offer_Fixed and Loyalty_Offer_Multiplier and Free_Shipping | master_product   |
     # | guest user      | Percent_Off_Order and Loyalty_Offer_Multiplier and Threshold_GWP   | master_product   |
      | registered user | Loyalty_Offer_Fixed and Bundled_GWP                                | standard_product |
   # | loyallist       | Loyalty_Offer_Fixed and Loyalty_Offer_Multiplier and Free_Shipping | standard_product |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario: Verify the thumbnail call out is not displayed for loyallist global offer with missing loyallist flag attribute on browse page
    Given I visit the web site as a guest user
    When I search product by name with options in domestic mode:
      | dml_product            | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed without_flag |
    Then I verify loyallist global offer thumbnail call out is not displayed for the eligible product

  @project_lbov @mode_domestic @priority_medium @domain_marketing @release_15I @artifact_navapp @use_manual
  Scenario: Verify the thumbnail call out flex text for loyallist global offer eligible product
    Given I visit the web site as a guest user
    When I navigate to browse page having product with loyallist global offer
    Then I verify loyallist global offer thumbnail call out text color and font