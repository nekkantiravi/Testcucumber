Feature: Verify categories functionality from global navigation

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify all categories and their response code from global navigation in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify following "<category>" from global navigation
    And I verify child categories of below parent "<category>" are visible
    Examples:
      | category              |
      | DESIGNERS             |
      | EDITORIAL             |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | BEAUTY                |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | GIFTS                 |
      | SALE                  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify all categories and their response code from global navigation in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    Then I verify following "<category>" from global navigation
    And I verify child categories of below parent "<category>" are visible
    Examples:
      | category              |
      | DESIGNERS             |
      | EDITORIAL             |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | SALE                  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify below options are visible on regstry global navigation
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "<option>" under registry menu from registry home page
    And I verify child categories of below parent "<option>" are visible
    And I randomly navigate to any registry browse page by clicking on child categories of below "<option>"
    Examples:
      | option                |
      | Add Gifts to Registry |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify below categories and their response code from global navigation in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify following "<category>" from global navigation
    Examples:
      | category        |
      | PROMOTIONS      |
      | MY ACCOUNT      |
      | MY bWALLET      |
      | MY LOYALLIST    |
      | THE REGISTRY    |
      | WISH LIST       |
      | RECENT ACTIVITY |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Navigate to random browse page from global navigation in domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I randomly navigate to any browse page by clicking on child categories of below "<category>"
    Examples:
      | category              |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | BEAUTY                |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | SALE                  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Navigate to random browse page from global navigation in iship mode
    Given I visit the mobile web site as a guest user in iship mode
    Then I randomly navigate to any browse page by clicking on child categories of below "<category>"
    Examples:
      | category              |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | SALE                  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify service and UI data comparison for domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify child categories of below parent "<category>" from backend service call
    Examples:
      | category              |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | BEAUTY                |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | SALE                  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify service and UI data comparison for iship mode
    Given I visit the mobile web site as a guest user in iship mode
    Then I verify child categories of below parent "<category>" from backend service call
    Examples:
      | category              |
      | WOMEN                 |
      | SHOES                 |
      | HANDBAGS              |
      | JEWELRY & ACCESSORIES |
      | MEN                   |
      | KIDS                  |
      | HOME                  |
      | SALE                  |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify below options are visible on regstry global navigation
    Given I visit the mobile web site as a guest user in registry mode
    Then I verify the below "Add Gifts to Registry" under registry menu from registry home page
    And I verify child categories of below parent "Add Gifts to Registry" are visible
    And I also verify the UI data with service response data for "<category>" in "registry" global navigation
    Examples:
      | category              |
      | Dining & Entertaining |
      | Kitchen               |
      | Home Decor            |
      | Bed & Bath            |
      | Luggage               |
      | Home Care & Tech      |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify deeplink scenario for domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    Then I navigate to "browse" page by url "<url>"
    And I verify the catid or product id falls under right GN tree for "<url>"
    Examples:
      | url                                                  |
      | /shop/kids/boys-shoes?id=1003143                     |
      | /shop/beauty/estee-lauder-skin-care-products?id=7657 |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify deeplink scenario for iship mode
    Given I visit the mobile web site as a guest user in iship mode
    Then I navigate to "browse" page by url "<url>"
    And I verify the catid or product id falls under right GN tree for "<url>"
    Examples:
      | url                                                   |
      | /shop/kids/boys-shoes?id=1003143                      |
      | /shop/womens-designer-shoes/mid-calf-boots?id=1005935 |

  #B-95024
  @domain_mew_discovery @use_mew_regression
  Scenario: Global nav should no longer have menu items for "Registry Manager" and "Registry Dashboard"
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | The Registry    |
      | Manage Registry |
    Then I verify the following child categories for Registry are not visible:
      | Registry Manager   |
      | Registry Dashboard |
    Then I verify the below "View Registry" under registry menu from registry home page
