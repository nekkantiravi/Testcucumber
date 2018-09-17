#Domain: Discovery

Feature: Excluded category Ids / Excluded URls should land on Navapp Page not on DiscoveryPages Stack

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET1 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify below excluded categories are routed to 'navapp' server in 'domestic' context:
      | 2910 |
      | 2916 |
      | 2917 |
      | 2921 |
      | 3376 |
      | 3864 |
      | 3865 |
      | 3866 |
      | 3948 |
      | 3977 |
      | 16958 |
      | 16961 |
      | 1002096 |
      | 1000926 |
      | 1001019  |
      | 1001020  |
      | 1001021  |
      | 1001022  |
      | 1001023  |
      | 1001064  |

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET2 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify below excluded categories are routed to 'navapp' server in 'domestic' context:
      | 1001070  |
      | 1001071  |
      | 1001072  |
      | 1001073  |
      | 1001074  |
      | 1001075  |
      | 1001076  |
      | 1001077  |
      | 1001078  |
      | 1001102  |
      | 1001103  |
      | 1001104  |
      | 1001105  |
      | 1001106  |
      | 1001107  |
      | 1001108  |
      | 1001109  |
      | 1001110  |
      | 1001151  |
      | 1001152  |

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET3 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify below excluded categories are routed to 'navapp' server in 'domestic' context:
      | 1001153 |
      | 1001154 |
      | 1001155 |
      | 1001279 |
      | 1001280 |
      | 1001281 |
      | 1001282 |
      | 1001283 |
      | 1001284 |
      | 1001285 |
      | 1001286 |
      | 1001288 |
      | 1001308 |
      | 1001734 |
      | 1002095 |
      | 1002098 |
      | 1004757 |
      | 1004760 |
      | 1005320 |
      | 1022159 |

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET4 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify below excluded categories are routed to 'navapp' server in 'domestic' context:
      | 1026161 |
      | 1039659 |
      | 13668   |
      | 1006041 |
      | 1001337 |
      | 1001338 |
      | 1001339 |
      | 1001340 |
      | 1001341 |
      | 1001342 |
      | 1001343 |
      | 1001344 |
      | 1001351 |
      | 1005423 |

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET7 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I navigate to specific excluded urls
      | /shop/b/                                   |
      | /shop/s/                                   |
      | /buy/                                      |
      | /shop/product/                             |
      | /shop/registry/wedding/product/            |
      | /shop/wedding-registry/product/            |
      | /shop/wishlist/                            |
      | /shop/coupons-deals/                       |
      | /shop/feedback/                            |
      | /shop/coupons-sales-promotions/            |
      | /shop/captchaGenerator/                    |
      | /shop/emailToFriend/                       |
      | /shop/search/product/thumbnail/canvasids/  |
      | /shop/catalog/product/thumbnail/canvasids/ |
      | /shop/search/facetedmeta/                  |
      | /shop/catalog/                             |
      | /shop/international/                       |
      | /shop/semantic/                  `         |
      | /shop/media/                               |
      | /shop/all-brands/                          |
      | /shop/wedding-registry/all-brands/         |
      | /shop/storeavailability/                   |
      | /shop/store/                               |
      | /shop/tabletop-builder/                    |
      | /shop/gift/                                |
      | /shop/topnav/                              |
      | /shop/flyout/                              |
      | /shop/gift/                                |
      | /shop/all-designers                        |
