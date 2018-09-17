

#Author:Swathi Tatavarthy
#Date: 3/31/2017
#Story#B-73626
#Sign off:

Feature: Verify the featured facet values functionality in search results and browse pages on BCOM-MEW

  @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify Refine Results and Sortby functionality in search result page on BCOM-MEW

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    And I should see the Refine Results link
    When I tap on the Refine Results link
    And I should see "Sort By” is still in a dropdown menu with “featured” as the default value
    When I tap on the "Sort By” drop down
    And I should see the OS keyboard pop up where they can scroll to select which way we want to sort
    Then I should see sort by functionality with below options using mobile website:
      | Featured Items     |
      | Newest             |
      | Best Sellers       |
      | Price: Low to High |
      | Price: High to Low |

    Examples:
      |keyword|
      |Jeans|
      |Shoes|
      |Handbags|
      |fragrance|

  @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify filter,apply and clear all  functionality in Refine Results modal in search result page on BCOM-MEW

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    And I should see the Refine Results link
    When I tap on the Refine Results link
    Then I should see the below addition of two exposed filters that used to show on the results page
      | Designer  |
      | Item Type |
    And I should see the Refine Results modal with all filter:
      | Department |
      | Item Type  |
      | Designer   |
      | Color      |
      | Size       |
    When I select a facet
    Then I should see the “apply” button and “clear all” appears within each facet category
    And I click apply for the filter selected
    And I verify the applied filter in search results page
    And I should see “Clear All” clears the whole section and whatever “Shop For” they selected on the page
    When I click on the 'X' button next to the “Refine Results” title
    Then I should exit from the Refine Results

    Examples:
      |keyword|
      |Shoes  |
      |Flats  |
      |bloomingdales|
      |blue jeans   |

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify FFV breadcrumbs functionality in search result page on BCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    And I should see the searchquery in the result lable
    #Results: <search query> <from>-<to> of <amount of results>
    Then I should see the Search query is in bold

    Examples:
      |keyword|
      |Jeans|
      |Handbags|
      |beauty  |
      |Jewlery |


  @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify FFV load and create boxes functionality when user searches on BCOM-MEW

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    And I should see the name of the Facet value for each box
    And I should see the number of results for the respective facets value
    And I should see the FFV's does not allow for multiple select per level

    Examples:
      |keyword|
      |Jeans|
      |Shoes|
      |Handbags|
      |jackets |

  @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the autocomplete suggestions

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search box
    Then I should see the autocomplete suggestions
    And I click on any autocomplete suggestion
    Then I should see facets are loaded

    Examples:
      |keyword|
      |Jeans|
      |Shoes|
      |Shirts|
      |bracelets|

  @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the recent searches text in recent search panel

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    And I type one character in mew search box
    Then I should see the "Recent searches" title in the panel
    And I should see the recent searches in recent search container

    Examples:
      |keyword|
      |jeans|
      |shoes|
      |shirts|
      |rings |

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the title of featured facet values in search result page on BCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    And I should see facets are loaded
    And I should see the title be Shop By: chips Above Shop by Title
    And I should see the title should be in normal
    When I select a random ffv
    Then I should see the title should be updated dynamically

    Examples:
      |keyword|
      |shirts|
      |shoes|
      |bloomingdales|
      |Gucci Shoes  |


  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the BOX Selection in search_results page on BCOM_MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    And I should see facets are loaded
    And I should see the name of the Facet value for each box
    And I should see the number of results for the respective facets value
    When I select a random ffv
    Then I should see FFV carousal should be displayed based on the threshold product count

    Examples:
      |keyword|
      |jackets|
      |beauty|
      |shirts|
      |watches|

  @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the facet Selection in Refine results modal on BCOM_MEW

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    And I should see facets are loaded
    And I select a random ffv
    Then I should see the breadcrumb displayed
    When I tap on the Refine Results link
    Then I should see the facet is updated in Refine filter modal

    Examples:
      |keyword|
      |blazers|
      |shoes|
      |shirts|
      |Michael Kors|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify  FFV carousal see more button functionality in search result page on BCOM-MEW

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    And I should see “See More” button for 13 FFVs

    Examples:
      |keyword|
      |Rings|
      |Shoes|
      |Handbags|
      |skin care|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the chip functionality when we select any FFV

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    When I select a random ffv
    Then I should see the breadcrumb displayed
    And I should see the value/s show as selected with a check mark next to them

    Examples:
      |keyword|
      |jeans|
      |beauty|
      |shirts|
      |skirts|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the Chip functionality when user filters by regular facets in search results page

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    And I tap on the Refine Results link
    When I select a facet
    And I click apply for the filter selected
    Then I verify the applied filter in search results page

    Examples:
      |keyword|
      |blue jeans|
      |shoes|
      |shorts|
      |sneakers|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the Chip functionality when user un-selects it in search results page

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    When I select a random ffv
    Then I should see the breadcrumb displayed
    And I should see the value/s show as selected with a check mark next to them
    When I click on the X icon to unselect
    Then I should not see the chip displayed

    Examples:
      |keyword|
      |jeans|
      |shoes|
      |shirts|
      |fragrance|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the SeeMore functionality when user clicks on SeeMore button in search results page

    Given I visit the mobile web site as a guest user in domestic mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    When I select a random ffv
    Then I should see the title be Shop By: chips Above Shop by Title
    And I click on SeeMore button
    Then I should see corresponding accordion modal opened by default

    Examples:
      |keyword|
      |jeans|
      |shoes|
      |handbags|
      |necklaces|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the BOX Selection in registry search pages on BCOM_MEW
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded

     Examples:
      |keyword|
      |plates|
      |electrics|
      |bakeware |

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the SeeMore functionality when user clicks on SeeMore button in registry search pages
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    And I should see the title be Shop By: chips Above Shop by Title
    When I click on SeeMore button
    Then I should see corresponding accordion modal opened by default

     Examples:
      |keyword|
      |lenox|
      |plates|
      |furniture|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the chip functionality when we select any FFV in registry search pages
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    When I select a random ffv
    Then I should see the chip displayed for the selected FFV
    And I should see the value/s show as selected with a check mark next to them

    Examples:
      |keyword|
      |fragrance|
      |electrics|
      |cookware|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the Chip functionality when user filters by regular facets in registry search pages
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    And I tap on the Refine Results link
    When I select a facet
    And I click apply for the filter selected
    Then I verify the applied filter in browse page

    Examples:
      |keyword|
      |lenox|
      |beauty|
      |plates|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the Chip functionality when user un-selects it in registry search pages

    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    When I select a random ffv
    Then I should see the chip displayed for the selected FFV
    And I should see the value/s show as selected with a check mark next to them
    When I click on the X icon to unselect
    Then I should not see the chip displayed

    Examples:
      |keyword|
      |lenox|
      |electrics|
      |bakeware|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the title of featured facet values in registry search pages on BCOM-MEW
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search
    And I should see facets are loaded
    And I should see the title should be in normal
    And I should see the title be Shop By: chips Above Shop by Title
    When I select a random ffv
    Then I should see the number of results for the respective facets value
    And I should see the title should be updated dynamically

    Examples:
     |keyword|
     |lenox|
     |bloomingdales|
     |plates|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify the BOX Selection in registry search pages on BCOM_MEW
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search
    And I should see facets are loaded
    And I should see the name of the Facet value for each box
    And I should see the number of results for the respective facets value
    When I select a random ffv
    Then I should see FFV carousal should be displayed based on the threshold product count

     Examples:
      |keyword|
      |lenox|
      |plates|
      |comforters|

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario Outline: Verify  FFV carousal see more button functionality in search result page on BCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I type "<keyword>" in mew search
    Then I should see facets are loaded
    And I should see “See More” button for 13 FFVs

    Examples:
      |keyword|
      |lenox|
      |plates|
      |furniture|


      #*********** FFV BROWSE ************#


  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the BOX Selection in browse pages on BCOM_MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Men|
      |Clothing|
      |Jeans   |
    Then I should see facets are loaded

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the SeeMore functionality when user clicks on SeeMore button in browse pages
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Beauty|
      |Skin care  |
    Then I should see facets are loaded
    And I should see the title be Shop By: chips Above Shop by Title
    When I click on SeeMore button
    Then I should see corresponding accordion modal opened by default

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the chip functionality when we select any FFV in browse page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Women|
      |Coats|
    Then I should see facets are loaded
    When I select a random ffv
    Then I should see the chip displayed for the selected FFV
    And I should see the value/s show as selected with a check mark next to them

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the Chip functionality when user filters by regular facets in browse page
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Men|
      |Clothing|
      |Jeans   |
    Then I should see facets are loaded
    And I tap on the Refine Results link
    When I select a facet
    And I click apply for the filter selected
    Then I verify the applied filter in browse page

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the Chip functionality when user un-selects it in browse page

    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Beauty|
      |Fragrance   |
    Then I should see facets are loaded
    When I select a random ffv
    Then I should see the chip displayed for the selected FFV
    And I should see the value/s show as selected with a check mark next to them
    When I click on the X icon to unselect
    Then I should not see the chip displayed

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the title of featured facet values in browse page on BCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Home|
      |Kitchen|
      |Bakeware   |
    And I should see facets are loaded
    And I should see the title should be in normal
    And I should see the title be Shop By: chips Above Shop by Title
    When I select a random ffv
    Then I should see the number of results for the respective facets value
    And I should see the title should be updated dynamically

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the BOX Selection in browse page on BCOM_MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Handbags|
      |Totes   |
    And I should see facets are loaded
    And I should see the name of the Facet value for each box
    And I should see the number of results for the respective facets value
    When I select a random ffv
    Then I should see FFV carousal should be displayed based on the threshold product count

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify  FFV carousal see more button functionality in browse result page on BCOM-MEW
    Given I visit the mobile web site as a guest user in domestic mode
    When I navigate the global navigation menu as follows:
      |Beauty|
      |Skin care  |
    Then I should see facets are loaded
    And I should see “See More” button for 13 FFVs

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify  FFV carousal see more button functionality in browse result page on BCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      |Add Gifts to Registry|
      |Dining & Entertaining|
      |Dinnerware           |
    Then I should see facets are loaded
    And I should see “See More” button for 13 FFVs

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the BOX Selection in browse pages on BCOM_MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      |Add Gifts to Registry|
      |Kitchen|
      |Cookware|
    Then I should see facets are loaded

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the SeeMore functionality when user clicks on SeeMore button in browse pages in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      |Add Gifts to Registry|
      |Kitchen|
      |Bakeware|
    Then I should see facets are loaded
    And I should see the title be Shop By: chips Above Shop by Title
    When I click on SeeMore button
    Then I should see corresponding accordion modal opened by default

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the chip functionality when we select any FFV in browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      |Add Gifts to Registry|
      |Home Decor|
    Then I should see facets are loaded
    When I select a random ffv
    Then I should see the chip displayed for the selected FFV
    And I should see the value/s show as selected with a check mark next to them

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the Chip functionality when user filters by regular facets in browse page in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      |Add Gifts to Registry|
      |Luggage|
    Then I should see facets are loaded
    And I tap on the Refine Results link
    When I select a facet
    And I click apply for the filter selected
    Then I verify the applied filter in browse page

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the Chip functionality when user un-selects it in browse page in registry mode

    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      |Add Gifts to Registry|
      |Luggage |
      |Luggage |
      |Luggage Collections|
    Then I should see facets are loaded
    When I select a random ffv
    Then I should see the chip displayed for the selected FFV
    And I should see the value/s show as selected with a check mark next to them
    When I click on the X icon to unselect
    Then I should not see the chip displayed

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the title of featured facet values in browse page on BCOM-MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      |Add Gifts to Registry|
      |Bed & Bath           |
      |Bedding              |
    And I should see facets are loaded
    And I should see the title should be in normal
    And I should see the title be Shop By: chips Above Shop by Title
    When I select a random ffv
    Then I should see the number of results for the respective facets value
    And I should see the title should be updated dynamically

  @regression @domain_discovery @artifact_navapp @feature_Core_search_improvements
  Scenario: Verify the BOX Selection in browse page on BCOM_MEW in registry mode
    Given I visit the mobile web site as a guest user in registry mode
    When I navigate the global navigation menu as follows:
      |Add Gifts to Registry|
      |Kitchen|
      |Electrics|
    And I should see facets are loaded
    And I should see the name of the Facet value for each box
    And I should see the number of results for the respective facets value
    When I select a random ffv
    Then I should see FFV carousal should be displayed based on the threshold product count

