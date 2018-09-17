Feature: Component tests for DLP page

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects facet value within Size facet on DLP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Size"
    And I select random facet value on facet accordion model
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in DLP page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in DLP page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects multiple facet values within Size facet on DLP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in DLP page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in DLP page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects facet value within Price facet on DLP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Price"
    And I select random facet value on facet accordion model
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in DLP page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in DLP page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects multiple facet values within Price facet on DLP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Price"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in DLP page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in DLP page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects multiple facet values within multiple facets on DLP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Special Offers"
    And I select multiple facets
    Then I should see the facet values selected
    When I select facet name "Color"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in DLP page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in DLP page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects facet value within Customer Ratings facet on DLP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Customer Ratings"
    And I select facet value "5 stars"
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in DLP page with selected facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in DLP page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects multiple facet values within Customer Ratings facet on DLP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Customer Ratings"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected multiple facet values
    When I refresh the page
    When I click on filter link
    Then I should see the facet values selected
    When I click on back button
    Then I should see only products in DLP page with selected multiple facet values
    When I remove the facets from the breadcrumb
    Then I should see the products without any facet values in DLP page
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects facet value within Size facet on DLP and Verify product persistence when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Size"
    And I select random facet value on facet accordion model
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected facet values
    And I navigate to a random PDP page on DLP page
    When I click on back button on PDP page and navigate back to DLP page
    Then I should see only products in DLP page with selected facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects multiple facet values within Size facet on DLP and Verify product persistence when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected multiple facet values
    And I navigate to a random PDP page on search results page
    When I click on back button on PDP page and navigate back to DLP page
    Then I should see only products in DLP page with selected multiple facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects multiple facet values within Price facet on DLP and Verify product persistence when navigating back from PDP
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Price"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected multiple facet values
    And I navigate to a random PDP page on search results page
    When I click on back button on PDP page and navigate back to DLP page
    Then I should see only products in DLP page with selected multiple facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects facet value within Size facet on DLP and verifies clear all functionality
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Size"
    And I select random facet value on facet accordion model
    Then I should see the facet values selected
    When I click on back button
    Then I should see the apply selection overlay with apply and cancel buttons
    When I click the cancel button
    Then I should see the products without any facet values in DLP page
    When I click on filter link
    And I select random facet value on facet accordion model
    Then I should see the facet values selected
    When I click on back button
    Then I should see the apply selection overlay with apply and cancel buttons
    Then I click on Apply button on apply selection overlay
    Then I should see only products in DLP page with selected facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects multiple facet values within Size facet on DLP and verifies clear all functionality
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on back button
    Then I should see the apply selection overlay with apply and cancel buttons
    When I click the cancel button
    Then I should see the products without any facet values in DLP page
    When I click on filter link
    And I select multiple facets
    Then I should see the facet values selected
    When I click on back button
    Then I should see the apply selection overlay with apply and cancel buttons
    Then I click on Apply button on apply selection overlay
    Then I should see only products in DLP page with selected multiple facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects facet value within Size facet on DLP and verifies pagination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Size"
    And I select random facet value on facet accordion model
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected facet values
    And I navigate to a random page other than first page
    Then I should see only products in DLP page with selected facet values
    When I remove the facet from the breadcrumb
    And current page number should be equal 1
    Then I should see the products without any facet values in DLP page
    Examples:
      | mode    |
      | domestic|
      | iship   |


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects multiple facet values within Size facet on DLP and verifies pagination
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected multiple facet values
    And I navigate to a random page other than first page
    Then I should see only products in DLP page with selected multiple facet values
    When I remove the facet from the breadcrumb
    And current page number should be equal 1
    Then I should see only products in DLP page with selected multiple facet values
    Examples:
      | mode    |
      | domestic|
      | iship   |

  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects facet value within Size facet on search page and clicks clear all
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Size"
    And I select random facet value on facet accordion model
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected facet values
    When I click on filter link
    Then I should see the facet values selected
    When I click the clear all button
    When I click on Apply button
    Then I should see the products without any facet values in DLP page
    Examples:
      | mode    |
      | domestic|
      | iship   |


  @domain_mew_discovery @use_mew_regression
  Scenario Outline: DynamicLandingPage - User selects multiple facet values within Size facet on search page and clicks clear all
    Given I visit the mobile web site as a guest user in <mode> mode
    When I navigate to "7 For All Mankind" dynamic landing page in <mode> using mobile website
    When I click on filter link
    When I select facet name "Size"
    And I select multiple facets
    Then I should see the facet values selected
    When I click on Apply button
    Then I should see only products in DLP page with selected multiple facet values
    When I click on filter link
    Then I should see the facet values selected
    When I click the clear all button
    When I click on Apply button
    Then I should see the products without any facet values in DLP page
    Examples:
      | mode    |
      | domestic|
      | iship   |
