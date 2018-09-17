#Domain: Discovery

Feature: Excluded category Ids / Excluded URls should land on Navapp Page not on DiscoveryPages Stack

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET1 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify below excluded categories are routed to 'navapp' server in 'domestic' context:
      | 58049 |
      | 61916 |
      | 62138 |
      | 62139 |
      | 62574 |
      | 62575 |
      | 62576 |
      | 62577 |
      | 62578 |
      | 62579 |
      | 62580 |
      | 62581 |
      | 62582 |
      | 62583 |
      | 62584 |
      | 62585 |
      | 62586 |
      | 62587 |
      | 62588 |
      | 62589 |

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET2 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify below excluded categories are routed to 'navapp' server in 'domestic' context:
      | 146446 |
      | 30668  |
      | 58049  |
      | 59291  |
      | 61916  |
      | 62138  |
      | 62139  |
      | 62574  |
      | 62575  |
      | 62576  |
      | 62577  |
      | 62578  |
      | 62579  |
      | 62580  |
      | 62581  |
      | 62582  |
      | 62583  |
      | 62584  |
      | 62585  |

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET3 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify below excluded categories are routed to 'navapp' server in 'domestic' context:
      | 62586 |
      | 62587 |
      | 62588 |
      | 62589 |
      | 62594 |
      | 62595 |
      | 62596 |
      | 62597 |
      | 62598 |
      | 62599 |
      | 62600 |
      | 62601 |
      | 62602 |
      | 62603 |
      | 62604 |
      | 62605 |
      | 62606 |
      | 62608 |
      | 62613 |
      | 62990 |

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET4 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify below excluded categories are routed to 'navapp' server in 'domestic' context:
      | 62991 |
      | 62992 |
      | 62993 |
      | 63218 |
      | 63220 |
      | 63221 |
      | 63222 |
      | 63223 |
      | 63224 |
      | 63225 |
      | 63254 |
      | 63255 |
      | 63256 |
      | 63257 |
      | 63258 |
      | 63259 |
      | 63260 |
      | 63417 |
      | 64019 |
      | 65571 |
      | 65571 |

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET5 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify below excluded categories are routed to 'navapp' server in 'domestic' context:
      | 65571  |
      | 67994  |
      | 68033  |
      | 71077  |
      | 90342  |
      | 121442 |
      | 121443 |
      | 140054 |
      | 146242 |

  @goes_to_navapp_atpresent @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET6 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I verify below excluded categories are routed to 'navapp' server in 'domestic' context:
      | 14602  |
      | 67726  |
      | 141858 |
      | 66029  |

  @artifact_navapp @release_17S @priority_medium @domain_discovery @project_UFT @mode_domestic @snbc_comp @migrated_to_sdt @discovery_daily_run
  Scenario: CategoryBrowsePage - Domestic|Iship|Registry - Verify exculded categories SET7 list is routing to navapp
    Given I visit the web site as a guest user in "domestic" mode
    Then I navigate to specific excluded urls
      | /shop/b/                                   |
      | /shop/s/                                   |
      | /shop/product/                             |
      | /shop/registry/wedding/product/            |
      | /shop/wedding-registry/product/            |
      | /shop/wishlist/                            |
      | /shop/coupons-deals/                       |
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

