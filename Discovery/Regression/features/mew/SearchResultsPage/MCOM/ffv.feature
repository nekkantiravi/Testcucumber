#Author:Samatha
#Date: 3/14/2017
#Story#B-73091
#Sign off:Colin

Feature: Verifying the featured facet values functionality on MCOM-MEW search and browse pages in all modes

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the facet accordion values exposed when user clicks on See More button
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I should see “See More” button for 7 or more FFVs
    When I click on See More button
    Then I should see the accordion opened with the values exposed
    Examples:
      | keyword   |
      | bakeware  |
      | handbags  |
      | beauty    |
      | skin care |


  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the carousel display based on threshold count when user search on MCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should see FFV displayed as scrollable carousal
    When I select a random FFV
    Then I should see the FFV carousal based on the threshold product count
    Examples:
      | keyword |
      | Jackets |
      | Shirts  |
      | dresses |
      | jeans   |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the FFV selection in filter when user search on MCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    When I select a random FFV
    And  I select Filter
    And I click on the accordion displayed with selected count
    Then I should see the accordion opened with the values exposed
    Examples:
      | keyword |
      | Jeans   |
      | plates  |
      | watches |
      | dresses |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the selected FFV chip display when user search on MCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    When I click on X mark next to chip
    Then I should not see the chips displayed
    Examples:
      | keyword |
      | Jeans   |
      | watches |
      | shoes   |
      | shirts  |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the FFV title display when user browse on MCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the title updated dynamically

    Examples:
      | keyword    |
      | plates     |
      | watches    |
      | dresses    |
      | blue jeans |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the chip removal from filter when user search on MCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    And  I select Filter
    And I click on the accordion displayed with selected count
    And I click on clear link in the accordion
    And I click on apply button
    Then I should not see the chips displayed
    Examples:
      | keyword |
      | Jeans   |
      | watches |
      | plates  |
      | denim   |

    # ****** FFV Registry ***** #

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the facet accordion values exposed when user clicks on See More button in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I should see “See More” button for 7 or more FFVs
    And I click on See More button
    Then I should see the accordion opened with the values exposed
    Examples:
      | keyword   |
      | handbags  |
      | furniture |
      | electrics |


  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the carousel display based on threshold count when user search on MCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search and click enter
    Then I should see FFV displayed as scrollable carousal
    When I select a random FFV
    Then I should see the FFV carousal based on the threshold product count
    Examples:
      | keyword   |
      | handbags  |
      | furniture |
      | electrics |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the FFV selection in filter when user search on MCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    When I select a random FFV
    And  I select Filter
    And I click on the accordion displayed with selected count
    Then I should see the accordion opened with the values exposed
    Examples:
      | keyword   |
      | handbags  |
      | furniture |
      | electrics |


  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the selected FFV chip display when user search on MCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    When I click on X mark next to chip
    Then I should not see the chips displayed
    Examples:
      | keyword   |
      | handbags  |
      | furniture |
      | electrics |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the FFV title display when user browse on MCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the title updated dynamically
    Examples:
      | keyword   |
      | handbags  |
      | furniture |
      | electrics |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the chip removal from filter when user search on MCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    And  I select Filter
    And I click on the accordion displayed with selected count
    And I click on clear link in the accordion
    And I click on apply button
    Then I should not see the chips displayed
    Examples:
      | keyword   |
      | handbags  |
      | furniture |
      | electrics |

  #*********** FFV BROWSE ************#

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the facet accordion values exposed when user clicks on See More button on browse pages
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop                  |
      | Handbags & Sunglasses |
      | Sunglasses            |
    Then I should see the FFV title above the carousel
    And I should see “See More” button for 7 or more FFVs
    And I click on See More button
    Then I should see the accordion opened with the values exposed

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the carousel display based on threshold count when user browse on MCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop        |
      | Men         |
      | Accessories |
      | Watches     |
    Then I should see FFV displayed as scrollable carousal
    When I select a random FFV
    Then I should see the FFV carousal based on the threshold product count

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the FFV selection in filter when user browse on MCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Women |
      | Coats |
    Then I should see the FFV title above the carousel
    When I select a random FFV
    And  I select Filter
    And I click on the accordion displayed with selected count
    Then I should see the accordion opened with the values exposed

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the FFV title display when user browse on MCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop          |
      | Shoes         |
      | Women's Shoes |
      | Boots         |
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the title updated dynamically

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the selected FFV chip display when user browse on MCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop        |
      | Men         |
      | Accessories |
      | Watches     |
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    When I click on X mark next to chip
    Then I should not see the chips displayed

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the chip removal from filter when user browse on MCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      | Shop         |
      | For the Home |
      | Bed & Bath   |
      | Bedding      |
      | Comforters   |
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    And  I select Filter
    And I click on the accordion displayed with selected count
    And I click on clear link in the accordion
    And I click on apply button
    Then I should not see the chips displayed


  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the facet accordion values exposed when user clicks on See More button on browse pages in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories          |
      | Kitchen                  |
      | Cookware & Cookware Sets |
    Then I should see the FFV title above the carousel
    And I should see “See More” button for 7 or more FFVs
    And I click on See More button
    Then I should see the accordion opened with the values exposed

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the carousel display based on threshold count when user browse on MCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Bed & Bath      |
      | Bedding         |
      | Comforters      |
    Then I should see FFV displayed as scrollable carousal
    When I select a random FFV
    Then I should see the FFV carousal based on the threshold product count

  @regression @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the FFV selection in filter when user browse on MCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Bakeware        |
    Then I should see the FFV title above the carousel
    And I select a random FFV
    And  I select Filter
    And I click on the accordion displayed with selected count
    Then I should see the accordion opened with the values exposed

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the FFV title display when user browse on MCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories       |
      | Dining & Entertaining |
      | Dinnerware            |
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the title updated dynamically

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the selected FFV chip display when user browse on MCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Electrics       |
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    When I click on X mark next to chip
    Then I should not see the chips displayed

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the chip removal from filter when user browse on MCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      | Gift Categories |
      | Kitchen         |
      | Bakeware        |
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    And  I select Filter
    And I click on the accordion displayed with selected count
    And I click on clear link in the accordion
    And I click on apply button
    Then I should not see the chips displayed


   # **** FFV Iship ****#
  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the facet accordion values exposed when user clicks on See More button  in Iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I should see “See More” button for 7 or more FFVs
    When I click on See More button
    Then I should see the accordion opened with the values exposed
    Examples:
      | keyword  |
      | handbags |
      | beauty   |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the carousel display based on threshold count when user search on MCOM-MEW in  iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "<keyword>" in mew search and click enter
    Then I should see FFV displayed as scrollable carousal
    When I select a random FFV
    Then I should see the FFV carousal based on the threshold product count
    Examples:
      | keyword |
      | Shirts  |
      | dresses |
      | jeans   |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the FFV selection in filter when user search on MCOM-MEW in  iship mode

    Given I visit the mobile web site as a guest user in iship mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    When I select a random FFV
    And  I select Filter
    And I click on the accordion displayed with selected count
    Then I should see the accordion opened with the values exposed
    Examples:
      | keyword     |
      | Red dresses |
      | Jeans       |
      | watches     |


  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the selected FFV chip display when user search on MCOM-MEW in  iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    When I click on X mark next to chip
    Then I should not see the chips displayed
    Examples:
      | keyword |
      | Jeans   |
      | watches |
      | shoes   |


  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the FFV title display when user browse on MCOM-MEW in  iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the title updated dynamically
    Examples:
      | keyword    |
      | watches    |
      | dresses    |
      | blue jeans |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario Outline: Verify the chip removal from filter when user search on MCOM-MEW in  iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I type "<keyword>" in mew search and click enter
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    And  I select Filter
    And I click on the accordion displayed with selected count
    And I click on clear link in the accordion
    And I click on apply button
    Then I should not see the chips displayed

    Examples:
      | keyword |
      | Jeans   |
      | watches |
      | plates  |

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the facet accordion values exposed when user clicks on See More button on browse pages in  iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Men   |
      | Shoes |
      | Boots |
    Then I should see the FFV title above the carousel
    And I should see “See More” button for 7 or more FFVs
    And I click on See More button
    Then I should see the accordion opened with the values exposed

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the carousel display based on threshold count when user browse on MCOM-MEW in  iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the global navigation menu as follows:
      | Shop        |
      | Men         |
      | Accessories |
      | Watches     |
    Then I should see FFV displayed as scrollable carousal
    When I select a random FFV
    Then I should see the FFV carousal based on the threshold product count

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the FFV selection in filter when user browse on MCOM-MEW in  iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the global navigation menu as follows:
      | Shop  |
      | Women |
      | Coats |
    Then I should see the FFV title above the carousel
    When I select a random FFV
    And  I select Filter
    And I click on the accordion displayed with selected count
    Then I should see the accordion opened with the values exposed

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the FFV title display when user browse on MCOM-MEW in  iship mode

    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the global navigation menu as follows:
      | Shop          |
      | Shoes         |
      | Women's Shoes |
      | Boots         |
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the title updated dynamically

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the selected FFV chip display when user browse on MCOM-MEW in  iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the global navigation menu as follows:
      | Shop        |
      | Men         |
      | Accessories |
      | Watches     |
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    When I click on X mark next to chip
    Then I should not see the chips displayed

  @regression @artifact_navapp @feature_Core_search_improvements @domain_mew_discovery @use_mew_regression
  Scenario: Verify the chip removal from filter when user browse on MCOM-MEW in  iship mode
    Given I visit the mobile web site as a guest user in iship mode
    When I navigate the global navigation menu as follows:
      | Shop         |
      | For the Home |
      | Bed & Bath   |
      | Bedding      |
      | Comforters   |
    Then I should see the FFV title above the carousel
    And I select a random FFV
    Then I should see the chip displayed
    And  I select Filter
    And I click on the accordion displayed with selected count
    And I click on clear link in the accordion
    And I click on apply button
    Then I should not see the chips displayed