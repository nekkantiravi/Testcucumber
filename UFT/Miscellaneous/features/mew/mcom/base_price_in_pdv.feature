Feature: As a product owner, I would like to ensure when navigating from Bag to PDV, and updating the quantity, the base price that is captured in the Coremetrics Shop 5 tag is accurate.

  @coremetrics @Mew_UFT @release_16B @domain_selection @project_UFT_COREMETRICS @use_coremterics
  Scenario: Verify shop tag Base Price for ATB and view bag page are the same for a sale product on domestic mode
    Given I visit the mobile web site as a guest user in domestic mode
    And I search using mobile website for "77589"
    Then I should be redirected to PDP page using mobile website
    And I add product to my bag from standard PDP Page using mobile site
    Then I should be redirected to ATB page using mobile website
    When I update product quantity to "3"
    And update product quantity to three
    And I visit the Product Detail View

  @coremetrics @Mew_UFT @release_16B @domain_selection @project_UFT_COREMETRICS @use_coremterics
  Scenario: Verify shop tag Base Price for ATB and view bag page are the same for a sale product on Iship mode
    Given I visit the mobile web home page
    And I change country to "Australia"
    And I close the welcome mat if it's visible
    And I entered "77589" in global search field and search
    Then I should see PDP displayed with product details
    When I store product price information
    And I tap on add to bag button
    Then I verify Mew Digital Analytics Base Price:
      | Tag Type (tid)  | Parameter Name    | Expected Value        |
      | 4               | Action Type (at)  |5                      |
      | 4               | Base Price (bp)   |  price of 1 product   |

    Then I tap 'update' link on My Bag page
    And update product quantity to three
    Then I verify Mew Digital Analytics Base Price:
      | Tag Type (tid)  | Parameter Name    | Expected Value         |
      | 4               | Action Type (at)  |5                       |
      | 4               | Base Price (bp)   |  price of 3 products   |

  @coremetrics @Mew_UFT @release_16B @domain_selection @project_UFT_COREMETRICS @use_coremterics
  Scenario: Verify shop tag Base Price for ATB and view bag page are the same for a sale product on registry mode
    Given I visit the mobile web home page
    And I navigate to the registry home page
    And I entered "77589" in global search field and search
    Then I should see PDP displayed with product details
    When I store product price information
    And I tap on add to bag button
    Then I verify Mew Digital Analytics Base Price:
      | Tag Type (tid)  | Parameter Name    | Expected Value        |
      | 4               | Action Type (at)  |5                      |
      | 4               | Base Price (bp)   |  price of 1 product   |

    Then I tap 'update' link on My Bag page
    And update product quantity to three
    Then I verify Mew Digital Analytics Base Price:
      | Tag Type (tid)  | Parameter Name    | Expected Value         |
      | 4               | Action Type (at)  |5                       |
      | 4               | Base Price (bp)   |  price of 3 products   |
