Feature: Tests for search facets

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Special Offers facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Special Offers"
    And I select facet value "Clearance/Closeout"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Special Offers facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Special Offers"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Gender facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Gender"
    And I select facet value "Women"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Gender facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Gender"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Denim Style facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Denim Style"
    And I select facet value "Bootcut"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Denim Style facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Denim Style"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Brand facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Brand"
    And I select facet value "Melissa McCarthy Seven7"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Brand facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Brand"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Size facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Size"
    And I select facet value "Petite"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Size facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Rise facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Rise"
    And I select facet value "Mid Rise"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Rise facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Rise"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Color facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Color"
    And I select facet value "Yellow"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Color facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    When I click on filter link
    When I select facet name "Color"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Price facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Price"
    And I select facet value "$50-$100"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Price facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Price"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Customer Ratings facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Customer Ratings"
    And I select facet value "5 stars"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Customer Ratings facet on search page
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Customer Ratings"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in search page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Special Offers facet and Verify product persistence when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Special Offers"
    And I select facet value "Clearance/Closeout"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    And I navigate to a random PDP page on search results page
    When I click on back button on PDP page and navigate to search page
    Then I should see only products in search page with selected facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Special Offers facet Verify product persistence when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Special Offers"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    And I navigate to a random PDP page on search results page
    When I click on back button on PDP page and navigate to search page
    Then I should see only products in search page with selected multiple facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Size facet and Verify product persistence when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Size"
    And I select facet value "Petite"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    And I navigate to a random PDP page on search results page
    When I click on back button on PDP page and navigate to search page
    Then I should see only products in search page with selected facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Size facet on search page and Verify product persistence when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    And I navigate to a random PDP page on search results page
    When I click on back button on PDP page and navigate to search page
    Then I should see only products in search page with selected multiple facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Price facet on search page and Verify product persistence when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Price"
    And I select facet value "$50-$100"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    And I navigate to a random PDP page on search results page
    When I click on back button on PDP page and navigate to search page
    Then I should see only products in search page with selected facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Price facet on search page and Verify product persistence when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Price"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    And I navigate to a random PDP page on search results page
    When I click on back button on PDP page and navigate to search page
    Then I should see only products in search page with selected multiple facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects a single facet value within any available facet on search page and clicks clear all
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Red dresses" in mew search and click enter
    When I click on filter link
    When I select random facet on facet accordion model
    And I select random facet value on facet accordion model
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I click on filter link
    Then I should see the facet values selected
    When I click the clear all button
    When I click on Apply button
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within any avialble facet on search page and clicks clear all
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Red dresses" in mew search and click enter
    When I click on filter link
    When I select random facet on facet accordion model
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    When I click on filter link
    Then I should see the facet values selected
    When I click the clear all button
    When I click on Apply button
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Price facet on search page and verifies pagination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Price"
    And I select facet value "$50-$100"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    And I navigate to a random page other than first page
    Then I should see only products in search page with selected facet values
    When I remove the facet from the breadcrumb
    And current page number should be equal 1
    Then I should see the products without any facet values in search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Size facet on search page and verifies pagination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected multiple facet values
    And I navigate to a random page other than first page
    Then I should see only products in search page with selected multiple facet values
    When I remove the facet from the breadcrumb
    And current page number should be equal 1
    Then I should see only products in search page with selected multiple facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects multiple facet values within Size facet on search page and verifies clear all functionality
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on back button
    Then I should see the apply selection overlay with apply and cancel buttons
    When I click the cancel button
    Then I should see the products without any facet values in search page
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on back button
    Then I should see the apply selection overlay with apply and cancel buttons
    Then I click on Apply button on apply selection overlay
    Then I should see only products in search page with selected multiple facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - User selects facet value within Special Offers facet on search page and all facet values should update accordingly
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    When I click on filter link
    When I select facet name "Special Offers"
    And I select facet value "Clearance/Closeout"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in search page with selected facet values
    When I click on filter link
    Then I should see facet headers and values updated based on previous selection for search page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Size facet verifications
    Given I visit the mobile web site as a guest user in <mode> mode
    And I type "Red dresses" in mew search and click enter
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    And I deselect all selected size facet values from accordion model
    Then I should not see any size facet value as selected
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Size facet verifications after removing from breadcrumb
    Given I visit the mobile web site as a guest user in <mode> mode
    And I type "Red dresses" in mew search and click enter
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    And I click on apply button
    Then I should see only products in search page with selected multiple facet values
    And I refresh the page
    Then I should see only products in search page with selected multiple facet values
    When I remove the facet from the breadcrumb
    Then I should see only products in search page with selected multiple facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify user is able to navigate to a brand facet and search for a specific brand
    Given I visit the mobile web site as a guest user in <mode> mode
    And I type "Red dresses" in mew search and click enter
    And I click on filter link
    And I select facet name "Brand"
    And I type "Brand" into brand search text field on brand facet values screen
    Then I should see We found 0 results for "Brand" message in brand facet
    And I tap on X button in facet values selection screen
    When I type "a" into brand search text field on brand facet values screen
    Then I should see facet values refined with typed keyword "a"
    And I should see X button in the search input field
    When I tap on X button in facet values selection screen
    Then I should see empty search input field
    When I type "s" into brand search text field on brand facet values screen
    Then I should see facet values refined with typed keyword "s"
    And I select random facet value on facet accordion model
    And I click on Apply button
    Then I should see only products in search page with selected facet values
    When I remove the facet from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode     |
      | domestic |
      | iship    |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: SearchResultsPage - Verify user can able to select facet value from each available facets
    Given I visit the mobile web site as a guest user in <mode> mode
    When I type "Jeans" in mew search and click enter
    Then I should be on the search results page
    And I click on filter link
    And I select single facet value from each available facets
    And I click on Apply button
    Then I should be on the search results page
    And I should see only products in search page with selected multiple facet values
    And I refresh the page
    Then I should see only products in search page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in search page
    Examples:
      | mode     |
      | domestic |
      | iship    |