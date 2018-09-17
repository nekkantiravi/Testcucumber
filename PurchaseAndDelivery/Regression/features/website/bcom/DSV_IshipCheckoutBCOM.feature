Feature: Iship checkout

  @dsv_desktop_sev2 @use_regression @iship @domain_purchase_and_delivery @akamai_waf
  Scenario: Verify Quick view - Checkout
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I navigate to any category browse page
    And I select a random product in a quickview dialog
    And I add the item to the bag from quick view
    And I checkout on add to bag overlay
    And I add a "iship_eligible and orderable" product to my bag
    And I checkout on add to bag overlay
    Then I remove first item from shopping bag
    And I navigate to envoy checkout from shopping bag page
    # Notes:
    # things need to verify
    # add a random product to my bag using quick view: Hover the mouse on a product and click on Quick View button should open product quick view pop-up.
    # Click on add to bag button in quick view layer: Product should be added to bag and Pop-up should display "item added to your bag" then navigate to shopping bag page.
    # envoy checkout page (Border-free page) should load without any errors.
    # Test case description
    # Verify Quick view > Checkout
    # Test case steps
    # 1.Navigate to bloomingdales.com
    # 2.Click on Change country link.
    # 3.Select the country e.g. Australia and "click
    # on save & continue" button.
    # 4.Click on women > dresses.
    # 5.Hover the mouse on a product and click on Quick View button.
    # 6.Select Color, Size  Quantity and click on add to bag button.
    # 7.Click on Checkout in the pop-up.
    # 8.Click on Checkout in shopping bag.
    # Test case expected result
    # 1. bloomingdales.com home page should be displayed.
    # 2. It should navigate to shipping destination and preferred shopping currency change page.
    # 3. Iship hp should be displayed with country symbol.
    # 4.Women > dresses category browse page should be displayed.
    # 5.It should open product quick view pop-up.
    # 6.Product should be added in the bag and Pop-up should display "item added to your bag"
    # 7.It should navigate to shopping bag page.
    # 8.Checkout page (Borderfree page) should load without any errors

  @dsv_desktop_sev2 @use_regression @iship @domain_purchase_and_delivery @akamai_waf
  Scenario: Verify Multi facet - Checkout
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Australia"
    And I navigate to a category browse page that has facets
    And I select the first two color in the Color facet
    And I navigate to a random member product
    And I add "2" quantity to my bag from standard PDP Page
    And I checkout on add to bag overlay
    Then I make sure I am on the shopping bag page
    And I add a "iship_eligible and orderable" product to my bag
    And I checkout on add to bag overlay
    Then I remove first item from shopping bag
    Then I navigate to envoy checkout from shopping bag page
    # Notes:
    # things need to verify
    # add a product to my bag and checkout: this product should be multi facet product selected from browse page
    # Product should be added to the bag from PDP and Pop-up should display "item added to your bag"
    # Click on Checkout in the pop-up should navigate to shopping bag page.
    # envoy checkout page (Border-free page) should load without any errors.
    # Test case description
    # Verify Multi facet > Checkout
    # Test case steps
    # 1.Navigate to bloomingdales.com
    # 2.Click on Change country link.
    # 3.Select the country e.g. Australia and "click
    # on save & continue" button.
    # 4.Click on women > dresses.
    # 5.Select multi facet e.g. price, color, brand etc.
    # 6.Click on any product
    # 7.Select Color, Size  Quantity and click on add to bag button.
    # 8.Click on Checkout in the pop-up
    # 9.Click on Checkout in shopping bag.
    # Test case expected result
    # 1. bloomingdales.com home page should be displayed.
    # 2. It should navigate to shipping destination and preferred shopping currency change page.
    # 3. Iship hp should be displayed with country symbol.
    # 4.Women > dresses category browse page should be displayed.
    # 5.Products should be displayed accordingly.
    # 6.It should navigate to PDP.
    # 7.Product should be added in the bag and Pop-up should display "item added to your bag"
    # 8.It should navigate to shopping bag page.
    # 9.Checkout page (Borderfree page) should load without any errors

  @dsv_desktop_sev2 @use_regression @iship @domain_purchase_and_delivery
  Scenario: Verify Checkout
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Australia"
    And I add a "iship_eligible and orderable" product to my bag
    And I navigate to envoy checkout from shopping bag page

  @iship @domain_purchase_and_delivery @dsv_desktop_sev2
  Scenario: Verify Checkout for iship, currency cookie should be set automatically on bag page if shippingCountry is set
    Given I visit the web site as a guest user
    Then I clear all the cookies
    When I add the "shippingCountry" cookie value as "AU"
    And I add a "iship_eligible and orderable" product to my bag
    Then I verify that "currency" cookie is populated on the shopping bag page as "AUD"
    And I navigate to envoy checkout from shopping bag page
