Feature: Chanel page verification features.

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify that the following pages do not have a chanel banner
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate to "<page>" category browse page
    Then I should see main chenel banner
    And I should not see sub chanel banners
    Examples:
      | page         |
      | what's new   |
      | gifts        |

  @domain_mew_discovery @use_mew_regression
  Scenario: Chanel category should be in capitalized and black background and white letters when selected
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate to "chanel from nav" category browse page
    Then I should see the chanel category capitalized
    Then I should see the chanel in white words and in black background
    Then I should see the chanel sub categories capitalized
    Then I should see the chanel sub categories in white colors and in black background when selected

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: In chanel pages, we should only see sort by button and no filter by button
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate to "<page>" category browse page
    Then I should see main chenel banner
    Then I should see only sort button and not filter by
    Examples:
      | page               |
      | chanel from nav    |
      | gifts              |
      | women's fragrance  |
      | men's fragrance    |
      | makeup             |
      | skincare           |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: In chanel pages, we should not see bonus offers
    Given I visit the mobile web site as a guest user in domestic mode
    And I navigate to "<page>" category browse page
    Then I should not see any bonus or promotion offer
    Examples:
      | page               |
      | chanel from nav    |
      | gifts              |
      | women's fragrance  |
      | men's fragrance    |
      | makeup             |
      | skincare           |

  @domain_mew_discovery @use_mew_regression
  Scenario: In chanel pages, we should see bonus star ratings beneath the product thumbnails
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "chanel" in mew search and click enter
    Then I should be on the browse page
    Then I should see the start summary beneath the product thumbnails

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on chanel search results page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "chanel" in mew search and click enter
    Then I should be on the browse page
    And I select "<sort value>" in sort by drop down in mew
    Then I should see products sorted by "<sort value>" on browse page
    Examples:
      | sort value	         |
      | Price: Low to High   |
      | Price: High to Low   |
      | Featured Items       |
      | Customers' Top Rated |
      | Best Sellers         |
      | New Arrivals         |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify sort by functionality on chanel UI page
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "chanel" in mew search and click enter
    Then I should be on the browse page
    And I select "<sort value>" in sort by drop down in mew
    Then I should see products sorted by "<sort value>" on UI page
    Examples:
      | sort value	         |
      | Price: Low to High   |
      | Price: High to Low   |

  @domain_mew_discovery @use_new_regression
  Scenario Outline: Search for any chanel product should take to the default category.
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<search_text>" in mew search and click enter
    Then I should be on the browse page
    Then I should be in the chanel default category
    Examples:
      | search_text        |
      | bleu de chanel     |
      | Chanel No 5        |
      | Chanel Lipstick    |
      | chanel cc cream    |
      | Chanel             |

  @domain_mew_discovery @mew_foundation
  Scenario: Verify that the following pages do have a chanel banner
    Given I visit the mobile web home page
    And I navigate to "men's fragrance" category browse page
    Then I should see main chenel banner
    And I should see sub chanel banners