#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: May 01,2017
#---------------------------------------------------

Feature: PDP_iShipMode Validation & Verification


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify basic components of member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I verify basic components of "member" PDP in "iship" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify basic components of master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I verify basic components of "master" PDP in "iship" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B & Checkout on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I select a "member" product and navigate to PDP in "iship" mode
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "Checkout" button on "member" PDP "iship" mode
    Then I verify the productId being the same as added to bag
    And I verify basic elements of shopping bag page in "iship" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify add to bag on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "ContinueShopping" button on "member" PDP "iship" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify add to bag on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "iship" mode
    And I click "ContinueShopping" button on "master" PDP "iship" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify zoomer & alternate images on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "jeans"
    And I select a random member product
    Then I verify the zoomer and altimages on member PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify zoomer & alternate images on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I select a "master" product and navigate to PDP in "iship" mode
    Then I verify the zoomer and altimages on master PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify empty bag message and that quickbag count updates on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I verify quickbag count is updating with "0" items in bag
    Then I navigate directly to "member" PDP and add a product to my bag in "iship" mode
    And I verify quickbag count is updating with "1" items in bag
    And I navigate directly to "member" PDP and add a product to my bag in "iship" mode
    Then I verify quickbag count is updating with "2" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify empty bag message and that quickbag count updates on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    And I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "iship" mode
    And I click "ContinueShopping" button on "master" PDP "iship" mode
    And I verify quickbag count is updating with "1" items in bag
    And I click "A2B" button on "master" PDP "iship" mode
    And I click "ContinueShopping" button on "master" PDP "iship" mode
    Then I verify quickbag count is updating with "2" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify quickbag on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I navigate directly to "member" PDP and add a product to my bag in "iship" mode
    Then I verify quickbag flyout on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify quickbag on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    And I navigate directly to "master" PDP and add a product to my bag in "iship" mode
    Then I verify quickbag flyout on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag product image navigation & color&quantity reset on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I navigate directly to PDP with PID "5747" in iship mode
    And I select "random" color on member PDP in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "ContinueShopping" button on "member" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I navigate directly to PDP with PID "22804" in iship mode
    And I click the "product image" on quick bag
    Then I verify navigating to "member" PDP "iship" mode
    And I verify that color&quantity are reset to default values on member PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag product image link navigates to PDP from master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
#    When I select a "master" product and navigate to PDP in "iship" mode
    When I navigate directly to PDP with PID "3704030" in iship mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "iship" mode
    And I click "ContinueShopping" button on "master" PDP "iship" mode
    Then I select another "master" product to build product history
    And I click the "product image" on quick bag
    Then I verify navigating to "member" PDP "iship" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag checkout functionality on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "ContinueShopping" button on "member" PDP "iship" mode
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "iship" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag checkout functionality on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I select a "master" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "iship" mode
    And I click "ContinueShopping" button on "master" PDP "iship" mode
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "iship" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag remove functionality on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "ContinueShopping" button on "member" PDP "iship" mode
    And I verify quickbag count is updating with "1" items in bag
    Then I select another "member" product to build product history
    And I click the "remove" on quick bag
    Then I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag remove functionality on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I select a "master" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "iship" mode
    And I click "ContinueShopping" button on "master" PDP "iship" mode
    And I verify quickbag count is updating with "1" items in bag
    Then I select another "master" product to build product history
    And I click the "remove" on quick bag
    Then I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the RVI panel on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I select a "member" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "member" product to build product history
    And I scroll up&down for "RVI" element to load on PDP site mode
    Then I verify the RVI panel on "member" PDP "iShip" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the RVI panel on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I select a "master" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "master" product to build product history
    And I scroll up&down for "RVI" element to load on PDP site mode
    Then I verify the RVI panel on "master" PDP "iShip" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify social icons on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    When I select a "member" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on member PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify social icons on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I select a "master" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on master PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario Outline: Verify shopping bag product image navigation & color&quantity reset on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I navigate directly to PDP with PID "<productId>" in iship mode
    And I select "random" color on member PDP in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "Checkout" button on "member" PDP "iship" mode
    And I click the product "image" link on shopping bag page
    Then I verify navigating to "member" PDP "iship" mode
    And I verify that color&quantity are reset to default values on member PDP iship mode
    Examples:
      |productId|
      |5747     |


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Empty bag & Removed item messages on shopping bag page iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I click the "QBlink" on quick bag
    And I verify the "empty bag" message on shopping bag page in "iship" mode
    When I navigate directly to "member" PDP and add a product to my bag in "iship" mode
    And I click the "QBlink" on quick bag
    Then I verify basic elements of shopping bag page in "iship" mode
    And I click the product "remove" link on shopping bag page
    Then I verify the "removed" message on shopping bag page in "iship" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify product unavailable message on shopping bag page iShip mode
    Given I visit the web site as a guest user
    When I select a "eligible" product for "webCollage" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "iship" mode
    Then I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
#    Then I verify the error message "Product Unavailable" on member PDP iship mode
    And I click the "QBlink" on quick bag
    And I verify basic elements of shopping bag page in "iship" mode
    Then I verify the "unavailable" message on shopping bag page in "iship" mode
#    And I verify the "unavailable international" message on shopping bag page in "iship" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the FOB functionality on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "women" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "women" FOB tab on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the FOB functionality on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "men" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "men" FOB tab on PDP


  @use_regression @domain_selection @mcom_selection_pdp  @dsv_desktop_sev2
  Scenario: Verify PROS vertical recommendation panels on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    When I select a "member" product for "PROS" and navigate to PDP
    And I resize the dimension on window screen
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the vertical recommendation panel on member PDP
    And I select a random product from vertical recommendation panel on member PDP iship mode
    Then I verify navigation to the corresponding PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS vertical recommendation panels on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    When I select a "master" product for "PROS" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the vertical recommendation panel on master PDP
    And I select a random product from vertical recommendation panel on master PDP iship mode
    Then I verify navigation to the corresponding PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS horizontal recommendation panels on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    When I select a "member" product for "PROS" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the horizontal recommendation panel on member PDP
    And I select a random product from horizontal recommendation panel on member PDP iship mode
    Then I verify navigation to the corresponding PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp  @dsv_desktop_sev2
  Scenario: Verify PROS horizontal recommendation panels on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    When I select a "master" product for "PROS" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the horizontal recommendation panel on master PDP
    And I select a random product from horizontal recommendation panel on master PDP iship mode
    Then I verify navigation to the corresponding PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B select size error message on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    Then I navigate directly to PDP with PID "2970058" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "member" PDP "site" mode
    Then I verify the error message "Please select a size." on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B limit reached error message on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    Then I navigate directly to PDP with PID "22804" in iship mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "ContinueShopping" button on "member" PDP "iship" mode
    And I select "minimum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "iship" mode
    Then I verify the error message "You've reached the limit for this item." on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify alphanumeric orders on member PDP
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I navigate directly to PDP with PID "4085662" in iship mode
#    When I search for "jeans"
#    And I select a random member product
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify sizes are displayed in alphanumeric orders on member PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the links under Shipping & Returns tab on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I navigate directly to PDP with PID "22805" in iship mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the links under shipping & returns tab on member PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the links under Shipping & Returns tab on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I navigate directly to PDP with PID "19942" in iship mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Shipping & Returns" in bottom tabs section on master PDP iship mode
    Then I verify the links under shipping & returns tab on master PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify special offers for Free Shipping on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I navigate directly to PDP with PID "22805" in iship mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify "Free Shipping" offer under SpecialOffers tab on member PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify special offers for any promotions on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "jeans"
    And I select a random member product
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify "Any" offer under SpecialOffers tab on member PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario Outline: Verify alphanumeric sizes on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I navigate directly to PDP with PID "<product_id>" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify sizes are displayed in alphanumeric orders on member PDP
    Examples:
      |product_id |
      |4085662    |
#      |700371     |


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Q&A functionality as a guest user on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I navigate directly to PDP with PID "22804" in iship mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on member PDP iship mode
    Then I verify "Q&A section" in bottom tabs section on member PDP iship mode
    And I click "Ask a new question button" in bottom tabs section on member PDP iship mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Q&A functionality as a guest user on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I navigate directly to PDP with PID "19942" in iship mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on master PDP iship mode
    Then I verify "Q&A section" in bottom tabs section on master PDP iship mode
    And I click "Ask a new question button" in bottom tabs section on master PDP iship mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify that Olapic section is available on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "jeans"
    And I select a random member product
    Then I verify that Olapic section is available on member PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify that Olapic section is unavailable on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I select a "master" product and navigate to PDP in "iship" mode
    Then I verify that Olapic section is unavailable on master PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify product name is same between Browse page & PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I hover over "men" FOB tab on PDP
    And I select "jeans" link from FOB on PDP
    And I select a random member product and save its name details on Browse page
    Then I verify that product name details are same on PDP iship mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify product name is same between Search page & PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I search for "Jeans"
    And I select a random member product and save its name details on Search page
    Then I verify that product name details are same on PDP iship mode

#Version One: B-100199
#Feature: As a product owner, I would like to make sure Link in class ="brandNameLink" has to be updated
# to href="/shop/featured"
#Author: UFT team
  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the updated brand name link on PDP in iship mode
    Given I visit the web site as a guest user in "iship" mode
    When I navigate directly to PDP with PID "2351352" in site mode
    Then I should see updated redirection URL link on PDP Page
    When I select brand name link
    Then I should redirect to the updated URL from PDP page