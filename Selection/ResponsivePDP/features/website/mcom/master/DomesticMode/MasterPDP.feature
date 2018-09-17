#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Oct.13,2017
#---------------------------------------------------

Feature: MasterPDP Validation & Verification Scenarios

  @domain_selection @use_regression @master_domestic_pdp @mcom_domestic_pdp @wip
  Scenario: Verify basic components of master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I resize the dimension on window screen
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify basic components of "master" PDP in "site" mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify add to bag on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify zoomer & alternate images on master PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "3409057" in site mode
    Then I verify the zoomer and altimages on master PDP site mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify empty bag message on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I verify quickbag count is updating with "0" items in bag

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify that quickbag count updates on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    And I verify quickbag count is updating with "1" items in bag
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    And I verify quickbag count is updating with "2" items in bag

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify quickbag on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify quickbag flyout on PDP

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify QuickBag product image link navigates to PDP from master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
#    And I setup "legacy" cookies for "master" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I select another "master" product to build product history
    And I click the "product image" on quick bag
    Then I verify navigating to "member" PDP "site" mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify QuickBag checkout functionality on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify QuickBag remove functionality on master PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "3704030" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    And I verify quickbag count is updating with "1" items in bag
    Then I select a "master" product and navigate to PDP in "site" mode
    And I click the "remove" on quick bag
    Then I verify quickbag count is updating with "0" items in bag

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify social icons on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on master PDP site mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify the RVI panel on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "master" product to build product history
    And I scroll up&down for "RVI" element to load on PDP site mode
    Then I verify the RVI panel on "master" PDP "site" mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify that Olapic section is unavailable on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll down to view olapic panel
    Then I verify that Olapic section is unavailable on master PDP site mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: To verify write a product review on master PDP Site mode
    Given I visit the web site as a registered user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I click on the write a review button on PDP site mode
    And I fill out required fields as "recommended" and submit reviews
    Then I verify the confirmation message for writing a product review

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify Q&A functionality as a guest user on master PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "19942" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on master PDP site mode
    Then I verify "Q&A section" in bottom tabs section on member PDP site mode
    And I click "Ask a new question button" in bottom tabs section on master PDP site mode
    Then I verify that guest user is navigated to "Account" signin page

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify Pinterest link on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I click "Pinterest" icon on master PDP site mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify EmailFriends link on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I click "Email" icon on master PDP site mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify ATB for Master product with Quotation in product's title
    Given I visit the web site as a guest user
    When I select a "master" product for "SpecialCharacter" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify BigTicket items on master PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1434000" in site mode
    And I setup "2630" cookie for "BigTicket" on master PDP site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the basic elements of the "BigTicket" PDP
    And I verify the zoomer and altimages on master PDP site mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify A2B functionality for BigTicket items on master PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1434000" in site mode
    And I setup "2630" cookie for "BigTicket" on master PDP site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I enter zipcode "94587" for a BigTicket item and click the submit button on master PDP
    Then I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    And I verify quickbag count is updating with "1" items in bag
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify A2B & availibility error for BigTicket items on master PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1434000" in site mode
    And I setup "2630" cookie for "BigTicket" on master PDP site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I enter zipcode "" for a BigTicket item and click the submit button on master PDP
    And I enter zipcode "9999" for a BigTicket item and click the submit button on master PDP
    And I click "A2B" button on "master" PDP "site" mode
    Then I verify the error message "Please enter a valid zip code" on master PDP site mode
    And I enter zipcode "99999" for a BigTicket item and click the submit button on master PDP
    And I click "A2B" button on "master" PDP "site" mode
    Then I verify the error message "Item availability unknown. Please call." on master PDP site mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify the FOB functionality on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "men" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "men" FOB tab on PDP

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify PROS vertical recommendation panels on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product for "PROS" and navigate to PDP
    And I resize the dimension on window screen
    Then I verify the vertical recommendation panel on master PDP
    And I select a random product from vertical recommendation panel on master PDP site mode
    Then I verify navigation to the corresponding PDP site mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify PROS horizontal recommendation panels on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product for "PROS" and navigate to PDP
    And I resize the dimension on window screen
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the horizontal recommendation panel on master PDP
    And I select a random product from horizontal recommendation panel on master PDP site mode
    Then I verify navigation to the corresponding PDP site mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify the links under Shipping & Returns tab on master PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "19942" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Shipping & Returns" in bottom tabs section on master PDP site mode
    Then I verify the links under shipping & returns tab on master PDP site mode

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify A2B functionality for QuickLook on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" site mode
    And I click A2B button on QuickLook overlay on "Master PDP" site mode
    And I click Checkout button on QuickLook overlay on "Master PDP" site mode
    Then I verify basic elements of shopping bag page in "site" mode
#    And I verify the productId being the same as added to bag

  @domain_selection @use_regression @mcom_responsive_pdp @mcom_master_pdp @mcom_domestic_pdp @wip
  Scenario: Verify ProductDetails link navigates from QuickLook to member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" site mode
    Then I verify the basic elements of QuickLook overlay on "PDP" site mode
    And I click ProductDetails button on QuickLook overlay on "Master PDP" site mode
    Then I verify navigation to member PDP in site mode
