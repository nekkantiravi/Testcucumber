Feature: MoreLikeThis functionality check.

  @mlt
  Scenario Outline: As a user, When I click on More Like This link for a product, it should show me similar products or any of the expected message
    Given I visit the mobile web site as a guest user
    When I click on "<applicable_fob>" sub category under "Shop"
    And I navigate to any sub category that lists products
    When I click on More Like This link reached through "browse"
    Then I should be shown matching products if any or one of the expected messages
    Examples:
  | applicable_fob        |
  | Women                 |
  | men                   |
  | shoes                 |
  | kids & baby           |
  | handbags & sunglasses |

  @mlt
  Scenario Outline: Validate <More Like This> functionality upto 3 levels of navigation
    Given I visit the mobile web site as a guest user
    When I click on "<applicable_fob>" sub category under "Shop"
    And I navigate to any sub category that lists products
    Then I look out for a More Like This link that has matching products upto "3" level and validate
    Examples:
  | applicable_fob        |
  | Women                 |
  | men                   |
  | shoes                 |
  | kids & baby           |
  | handbags & sunglasses |

  @mlt
  Scenario Outline: Validate <More Like This> functionality for Search
    Given I visit the mobile web site as a guest user
    When I search for "<Applicable_search_terms>"
    And I click on More Like This link reached through "search"
    Then I should be shown matching products if any or one of the expected messages
    Examples:
      | Applicable_search_terms         |
      | Jeans                           |
      | tops                            |
      | men's shoes                     |
      | Leggings                        |
      | handbags                        |
      | sunglasses                      |
      | shirts                          |
      | sweaters                        |
      | car seat                        |
      | stroller                        |
      | bodysuit                        |
      | sneakers                        |
      | flats                           |
      | sandals                         |
      | socks                           |
      | belts                           |
      | scarf                           |
      | maternity                       |
      | skirt                           |
      | polos                           |

  @mlt
  Scenario Outline: Validation of <More Like This> on deep/far result pages
    Given I visit the mobile web site as a guest user
    When I click on "<applicable_fob>" sub category under "Shop"
    And I navigate to any sub category that lists products
    And I navigate to a far results page
    When I click on More Like This link reached through "browse"
    Then I should be shown matching products if any or one of the expected messages
    Examples:
      | applicable_fob        |
      | Women                 |
      | men                   |
      | shoes                 |
      | kids & baby           |
      | handbags & sunglasses |

  @mlt
  Scenario Outline: Validation of existing functionality on <More Like This> pages
    Given I visit the mobile web site as a guest user
    When I click on "<applicable_fob>" sub category under "Shop"
    And I navigate to any sub category that lists products
    Then I should be able to validate existing functionality on MLT pages
    Examples:
      | applicable_fob        |
      | Women                 |
      | men                   |
      | shoes                 |
      | kids & baby           |
      | handbags & sunglasses |

    @mlt
  Scenario Outline: Validation of <More Like This> functionality for browse in iship mode
    Given I visit the mobile web site as a guest user
    When I navigate to change country page using mobile website
    And I change country to "India" using mobile website
    When I click on "<applicable_fob>" sub category under "Shop"
    And I navigate to any sub category that lists products
    Then I should be able to validate existing functionality on MLT pages in iship mode
    Examples:
      | applicable_fob        |
      | Women                 |
      | men                   |
      | shoes                 |
      | kids & baby           |
      | handbags & sunglasses |
