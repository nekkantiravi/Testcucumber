#Author: Loyalty Bonus Offers Visibility Team
#Date Created: May 11, 2015
#Date Modified:

#VersionOne Story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-18242
#VersionOne Story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-22346
#VersionOne Story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-14685
#VersionOne Story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-20980
#VersionOne Story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-16983
#VersionOne Story link: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-27234

Feature: As a bloomingdales.com customer, I verify the global Loyallist offer information on the PDP page.

  @project_lbov @mode_iship @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product which is eligible for loyallist global offer should not be displayed in international mode in pdp page
    Given I visit the web site as a guest user
    When I change country to "a random country"
    And I navigate directly to random product PDP with options in iship mode:
      | dml_product            | true         |
      | iship_eligible         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I should not see loyallist global offer main callout on pdp page
    And I should not see loyallist offer information in bonus offer section of the PDP
    Examples:
      | promo_type               |
      | Loyalty_Offer_Fixed      |
   #   | Loyalty_Offer_Multiplier |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verifying the offer information for product eligible for loyallist global offer in domestic mode, when user changes to international mode it should not be displayed in pdp page
    Given I visit the web site as a guest user
    When I navigate directly to random product PDP with options in domestic mode:
      | dml_product            | true         |
      | iship_eligible         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I should see loyallist global offer main callout on pdp page
    When I change country to "a random country"
    Then I should not see loyallist global offer main callout on pdp page
    Examples:
      | promo_type               |
  #    | Loyalty_Offer_Fixed      |
      | Loyalty_Offer_Multiplier |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product which is eligible for loyallist global offer when user navigate to a PDP page
    Given I visit the web site as a <user>
    When I navigate directly to random product PDP with options in domestic mode:
      | dml_product            | true         |
      | <product_type>         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I verify loyallist global offer main callout on pdp page
    When I select loyallist global offer main callout on pdp page
    Then I should see "OFFERS" tab selected on PDP page
    And I verify loyallist global offer in bonus offers section on pdp page
    Examples:
      | user            | promo_type               | product_type     |
      | guest user      | Loyalty_Offer_Fixed      | master_product   |
   #   | registered user | Loyalty_Offer_Multiplier | member_product   |
   #   | loyallist       | Loyalty_Offer_Fixed      | standard_product |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product which is eligible for loyallist global offer when user navigate to a registry PDP page
    Given I visit the web site as a <user>
    When I navigate directly to random product PDP with options in registry mode:
      | dml_product            | true         |
      | <product_type>         | true         |
      | registrable            | true         |
      | loyallist_global_offer | <promo_type> |
    Then I verify loyallist global offer main callout on pdp page
    When I select loyallist global offer main callout on pdp page
    Then I should see "OFFERS" tab selected on PDP page
    And I verify loyallist global offer in bonus offers section on pdp page
    Examples:
      | user            | promo_type               | product_type     |
    #  | guest user      | Loyalty_Offer_Fixed      | standard_product |
      | registered user | Loyalty_Offer_Multiplier | member_product   |
    #  | loyallist       | Loyalty_Offer_Fixed      | master_product   |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product which is eligible for more than 1 loyallist global offer ending on different days when user navigate to a PDP page
    Given I visit the web site as a <user>
    When I navigate directly to random product PDP with options in domestic mode:
      | dml_product            | true                             |
      | <product_type>         | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_diff_day |
    Then I verify loyallist global offer main callout on pdp page
    When I select loyallist global offer main callout on pdp page
    Then I should see "OFFERS" tab selected on PDP page
    And I verify loyallist global offer in bonus offers section on pdp page
    Examples:
      | user            | product_type     |
     # | guest user      | member_product   |
     # | registered user | master_product   |
      | loyallist       | standard_product |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product which is eligible for more than 1 loyallist global offer ending on different days when user navigate to a registry PDP page
    Given I visit the web site as a <user>
    When I navigate directly to random product PDP with options in registry mode:
      | dml_product            | true                             |
      | <product_type>         | true                             |
      | registrable            | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_diff_day |
    Then I verify loyallist global offer main callout on pdp page
    When I select loyallist global offer main callout on pdp page
    Then I should see "OFFERS" tab selected on PDP page
    And I verify loyallist global offer in bonus offers section on pdp page
    Examples:
      | user            | product_type     |
     # | guest user      | master_product   |
      | registered user | standard_product |
     # | loyallist       | standard_product |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product eligible for Loyalty Offer Fixed and Loyalty Offer Multiplier on Browse page PDP page
    Given I visit the web site as a <user>
    When I navigate directly to random product PDP with options in domestic mode:
      | dml_product            | true                             |
      | <product_type>         | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_same_day |
    Then I verify loyallist global offer main callout on pdp page
    When I select loyallist global offer main callout on pdp page
    And I should see "OFFERS" tab selected on PDP page
    Then I verify loyallist global offer in bonus offers section on pdp page
    Examples:
      | user            | product_type     |
    #  | guest user      | standard_product |
    #  | registered user | master_product   |
      | loyallist       | member_product   |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product eligible for Loyalty Offer Fixed and Loyalty Offer Multiplier on registry PDP page
    Given I visit the web site as a <user>
    When I navigate directly to random product PDP with options in registry mode:
      | dml_product            | true                             |
      | <product_type>         | true                             |
      | registrable            | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_same_day |
    Then I verify loyallist global offer main callout on pdp page
    When I select loyallist global offer main callout on pdp page
    And I should see "OFFERS" tab selected on PDP page
    Then I verify loyallist global offer in bonus offers section on pdp page
    Examples:
      | user            | product_type     |
      | guest user      | master_product   |
     # | registered user | member_product   |
     # | loyallist       | member_product   |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product eligible for Loyalty Offer Fixed and Loyalty Offer Multiplier on Browse page PDP page
    Given I visit the web site as a <user>
    When I navigate directly to random product PDP with options in domestic mode:
      | dml_product            | true                                             |
      | <product_type>         | true                                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed and Loyalty_Offer_Multiplier |
    Then I verify loyallist global offer main callout on pdp page
    When I select loyallist global offer main callout on pdp page
    And I should see "OFFERS" tab selected on PDP page
    Then I verify loyallist global offer in bonus offers section on pdp page
    Examples:
      | user            | product_type     |
      #| guest user      | member_product   |
      | registered user | master_product   |
      #| loyallist       | standard_product |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product eligible for Loyalty Offer Fixed and Loyalty Offer Multiplier on registry PDP page
    Given I visit the web site as a <user>
    When I navigate directly to random product PDP with options in registry mode:
      | dml_product            | true                                             |
      | <product_type>         | true                                             |
      | registrable            | true                                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed and Loyalty_Offer_Multiplier |
    Then I verify loyallist global offer main callout on pdp page
    When I select loyallist global offer main callout on pdp page
    And I should see "OFFERS" tab selected on PDP page
    Then I verify loyallist global offer in bonus offers section on pdp page
    Examples:
      | user            | product_type     |
      #| guest user      | member_product   |
      #| registered user | member_product   |
      | loyallist       | master_product   |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product eligible for combination of promotion on pdp page
    Given I visit the web site as a <user>
    When I navigate directly to random product PDP with options in domestic mode:
      | dml_product    | true                |
      | <product_type> | true                |
      | with_promotion | <promo_combination> |
    Then I verify loyallist global offer main callout on pdp page
    And I verify the display of special promotion
    And I should see loyallist offers below the GWPs in bonus offers section
    Examples:
      | user            | product_type     | promo_combination                   |
     # | guest user      | standard_product | Loyalty_Offer_Multiplier and PWP    |
      | registered user | member_product   | Loyalty_Offer_Fixed and Bundled_GWP |
     # | loyallist       | master_product   | Loyalty_Offer_Fixed and Bundled_GWP |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a product eligible for combination of promotion on registry pdp page
    Given I visit the web site as a <user>
    When I navigate directly to random product PDP with options in registry mode:
      | dml_product    | true                |
      | <product_type> | true                |
      | registrable    | true                |
      | with_promotion | <promo_combination> |
    Then I verify loyallist global offer main callout on pdp page
    And I verify the display of special promotion
    And I should see loyallist offers below the GWPs in bonus offers section
    Examples:
      | user            | product_type     | promo_combination                   |
      | guest user      | member_product   | Loyalty_Offer_Multiplier and PWP    |
      #| loyallist       | master_product   | Loyalty_Offer_Fixed and Bundled_GWP |
      #| registered user | standard_product | Loyalty_Offer_Multiplier and PWP    |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a master producxt which is having member product eligible for a loyallist global offer in domestic mode
    Given I visit the web site as a <user>
    When I navigate to master PDP having member product with options in domestic mode:
      | dml_product            | true         |
      | member_product         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I verify loyallist global offer callout for member product on master PDP
    When I select loyallist global offer callout for member product on master PDP
    Then I should be navigated to PDP page of the member product
    Examples:
      | user            | promo_type               |
     # | guest user      | Loyalty_Offer_Fixed      |
      | registered user | Loyalty_Offer_Multiplier |
     # | loyallist       | Loyalty_Offer_Fixed      |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a master producxt which is having member product eligible for a loyallist global offer in registry mode
    Given I visit the web site as a <user>
    When I navigate to master PDP having member product with options in registry mode:
      | dml_product            | true         |
      | member_product         | true         |
      | registrable            | true         |
      | loyallist_global_offer | <promo_type> |
    Then I verify loyallist global offer callout for member product on master PDP
    When I select loyallist global offer callout for member product on master PDP
    Then I should be navigated to PDP page of the member product
    Examples:
      | user            | promo_type               |
      #| guest user      | Loyalty_Offer_Multiplier |
      #| registered user | Loyalty_Offer_Multiplier |
      | loyallist       | Loyalty_Offer_Multiplier |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a master product which is having member product eligible for loyallist offer fixed and multiplier in domestic mode
    Given I visit the web site as a <user>
    When I navigate to master PDP having member product with options in domestic mode:
      | dml_product            | true                                             |
      | member_product         | true                                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed and Loyalty_Offer_Multiplier |
    Then I verify loyallist global offer callout for member product on master PDP
    When I select loyallist global offer callout for member product on master PDP
    Then I should be navigated to PDP page of the member product
    Examples:
      | user            |
      #| guest user      |
      #| registered user |
      | loyallist       |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a master product which is having member product eligible for loyallist offer fixed and multiplier in registry mode
    Given I visit the web site as a <user>
    When I navigate to master PDP having member product with options in registry mode:
      | dml_product            | true                                             |
      | member_product         | true                                             |
      | registrable            | true                                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed and Loyalty_Offer_Multiplier |
    Then I verify loyallist global offer callout for member product on master PDP
    When I select loyallist global offer callout for member product on master PDP
    Then I should be navigated to PDP page of the member product
    Examples:
      | user            |
      #| guest user      |
      | registered user |
     # | loyallist       |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a master product which is having member product eligible for more than 1 loyallist global offer ending on the same day in domestic mode
    Given I visit the web site as a <user>
    When I navigate to master PDP having member product with options in domestic mode:
      | dml_product            | true                             |
      | member_product         | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_same_day |
    Then I verify loyallist global offer callout for member product on master PDP
    When I select loyallist global offer callout for member product on master PDP
    Then I should be navigated to PDP page of the member product
    Examples:
      | user            |
      #| guest user      |
      #| registered user |
      | loyallist       |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a master product which is having member product eligible for more than 1 loyallist global offer ending on the same day in registry mode
    Given I visit the web site as a <user>
    When I navigate to master PDP having member product with options in registry mode:
      | dml_product            | true                             |
      | member_product         | true                             |
      | registrable            | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_same_day |
    Then I verify loyallist global offer callout for member product on master PDP
    When I select loyallist global offer callout for member product on master PDP
    Then I should be navigated to PDP page of the member product
    Examples:
      | user            |
      | guest user      |
      #| registered user |
      #| loyallist       |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information for a master product which is having member product eligible for more than 1 loyallist global offer ending on different days in domestic mode
    Given I visit the web site as a <user>
    When I navigate to master PDP having member product with options in domestic mode:
      | dml_product            | true                             |
      | member_product         | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed exp_diff_day |
    Then I verify loyallist global offer callout for member product on master PDP
    When I select loyallist global offer callout for member product on master PDP
    Then I should be navigated to PDP page of the member product
    Examples:
      | user            |
      #| guest user      |
      | registered user |
      #| loyallist       |

  @project_lbov @mode_registry @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify the offer information on master product pdp which is having member product eligible for more than one promotion in registry mode
    Given I visit the web site as a <user>
    When I navigate to master PDP having member product with options in registry mode:
      | dml_product     | true                                |
      | member_product  | true                                |
      | registrable     | true                                |
      | with_promotion  | Loyalty_Offer_Fixed and Bundled_GWP |
    Then I verify loyallist global offer callout for member product on master PDP
    When I select loyallist global offer callout for member product on master PDP
    Then I should be navigated to PDP page of the member product
    Examples:
      | user            |
     # | guest user      |
     # | registered user |
      | loyallist       |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario: Verify loyallist global offer with missing loyallist flag attribute on PDP page
    Given I visit the web site as a guest user
    When I navigate directly to random product PDP with options in domestic mode:
      | dml_product            | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed without_flag |
    Then I should not see loyallist global offer main callout on pdp page
    And I should not see loyallist offer information in bonus offer section of the PDP

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario: Verify loyallist global offer with missing offer name attribute on PDP page
    Given I visit the web site as a guest user
    When I navigate directly to random product PDP with options in domestic mode:
      | dml_product            | true                             |
      | loyallist_global_offer | Loyalty_Offer_Fixed without_name |
    Then I should not see loyallist global offer main callout on pdp page
    Then I should see loyallist offer information in bonus offer section of the PDP

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration
  Scenario Outline: Verify loyallist global offer with missing required attribute on PDP page
    Given I visit the web site as a guest user
    When I navigate directly to random product PDP with options in domestic mode:
      | dml_product            | true                     |
      | loyallist_global_offer | <loyallist_global_offer> |
    Then I should see loyallist global offer main callout on pdp page
    And I should not see loyallist offer information in bonus offer section of the PDP
    Examples:
      | loyallist_global_offer                  |
      | Loyalty_Offer_Fixed without_description |
    #| Loyalty_Offer_Multiplier without_title  |
    #| Loyalty_Offer_Fixed without_info_excl   |

  @project_lbov @mode_domestic @priority_high @domain_marketing @release_15I @artifact_navapp @use_iteration @coremetrics
  Scenario Outline: Verify coremetrics tag for loyallist global offer main callout on pdp
    Given I visit the web site as a <user>
    When I search for a product with options:
      | dml_product            | true         |
      | <product_type>         | true         |
      | loyallist_global_offer | <promo_type> |
    Then I should see loyallist global offer main callout on pdp page
    When I select loyallist global offer main callout on pdp page
    And I save the PDP page data to variables:
      | Variable       | Description (parameter to use the variable) |
      | {product_id}   | Attribute 29 (Explore) (e_a29)              |
      | {category_id}  | Element Category (ecat)                     |
    Then I verify Digital Analytics tags:
      | Tag Type (tid) | Parameter Name                  | Expected Value              |
      | 15             | Element ID (eid)                | "PDP_Loyallist_Bonus_Offer" |
      | 15             | Element Category (ecat)         | {category_id}               |
      | 15             | Attribute 29 (Explore) (e_a29)  | {product_id}                |
    Examples:
      | user            | promo_type               | product_type   |
      | guest user      | Loyalty_Offer_Fixed      | member_product |
   # | registered user | Loyalty_Offer_Multiplier | member_product |
   # | loyallist       | Loyalty_Offer_Fixed      | master_product |
