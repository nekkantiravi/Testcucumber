#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: May 01,2017
#---------------------------------------------------

Feature: PDPsiteModeLegacy&Hroku Validation & Verification

  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify basic components of member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    Then I verify basic components of "member" PDP in "site" mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify basic components of member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    Then I verify basic components of "member" PDP in "site" mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify basic components of master PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify basic components of "master" PDP in "site" mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify basic components of master PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify basic components of "master" PDP in "site" mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify add to bag on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify add to bag on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify add to bag on master PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify add to bag on master PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify the zoomer on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    Then I verify the zoomer on member PDP


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify the zoomer on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    Then I verify the zoomer on member PDP


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario Outline: Verify the GIFTNOW on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "<productId>" in site mode
    And I insert parameter to hit "QA" PDP "site" mode
    Then I verify the GIFTNOW on member PDP
    Examples:
      |productId|
      |77589    |


  @use_regression @domain_selection @pdp_heroku
  Scenario Outline: Verify the GIFTNOW on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "<productId>" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    Then I verify the GIFTNOW on member PDP
    Examples:
      |productId|
      |77589    |


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify that webCollage is displayed on PDP -legacy
    Given I visit the web site as a guest user
    When I select a "eligible" product for "webCollage" and navigate to PDP
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify that "webCollage" is "displayed" on PDP "site" mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify that webCollage is displayed on PDP -heroku
    Given I visit the web site as a guest user
    When I select a "eligible" product for "webCollage" and navigate to PDP
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify that "webCollage" is "displayed" on PDP "site" mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify video functionality on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "eligible" product for "webCollage" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    Then I verify that "Video" is "loading" on PDP "site" mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify video functionality on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "eligible" product for "webCollage" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    Then I verify that "Video" is "loading" on PDP "site" mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify empty bag message on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify empty bag message on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify empty bag message on master PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify empty bag message on master PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify that quickbag count updates on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify hitting "QA" PDP
    And I verify quickbag count is updating with "1" items in bag
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify hitting "QA" PDP
    And I verify quickbag count is updating with "2" items in bag


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify that quickbag count updates on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify hitting "heroku" PDP
    And I verify quickbag count is updating with "1" items in bag
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify hitting "heroku" PDP
    And I verify quickbag count is updating with "2" items in bag


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify that quickbag count updates on master PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify hitting "QA" PDP
    And I verify quickbag count is updating with "1" items in bag
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify hitting "QA" PDP
    And I verify quickbag count is updating with "2" items in bag


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify that quickbag count updates on master PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify hitting "heroku" PDP
    And I verify quickbag count is updating with "1" items in bag
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify hitting "heroku" PDP
    And I verify quickbag count is updating with "2" items in bag


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify quickbag on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify hitting "QA" PDP
    And I verify quickbag flyout on PDP


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify quickbag on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify hitting "heroku" PDP
    And I verify quickbag flyout on PDP


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify quickbag on master PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify hitting "QA" PDP
    And I verify quickbag flyout on PDP


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify quickbag on master PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify hitting "heroku" PDP
    And I verify quickbag flyout on PDP


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify the RVI panel on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "member" product to build product history
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll up&down for "RVI" element to load on PDP site mode
    Then I verify the RVI panel on "member" PDP "site" mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify the RVI panel on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "member" product to build product history
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll up&down for "RVI" element to load on PDP site mode
    Then I verify the RVI panel on "member" PDP "site" mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify the RVI panel on master PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "master" product to build product history
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the RVI panel on "master" PDP "site" mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify the RVI panel on master PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "77589" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    When I select a "master" product and navigate to PDP in "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the RVI panel on "master" PDP "site" mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify Full State Olapic on Radical PDP -legacy
    Given I visit the web site as a guest user
#    When I added a "available and olapic" product to my bag
    And I select a "FullState" product for "Olapic" and navigate to PDP
    And I insert parameter to hit "QA" PDP "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I scroll down to view olapic panel
    And I click on Olapic next & previous arrows
    When I click on an Olapic image
    Then I verify redirecting to radical PDP after clicking Olapic product list


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify Full State Olapic on Radical PDP -heroku
    Given I visit the web site as a guest user
#    When I added a "available and olapic" product to my bag
    And I select a "FullState" product for "Olapic" and navigate to PDP
    And I insert parameter to hit "heroku" PDP "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I scroll down to view olapic panel
    And I click on Olapic next & previous arrows
    Then I click on an Olapic image


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify social icons on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22805" in site mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on member PDP site mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify social icons on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22805" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on member PDP site mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify social icons on master PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on master PDP site mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify social icons on master PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on master PDP site mode


  @domain_selection @use_regression @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify collection tab appears on page -legacy
    Given I visit the web site as a guest user
    When I select a "member" product for "CollectionCTA" and navigate to PDP
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "collectionCTA" link or tab on "member" PDP "site" mode
    And I click "productsCollectionLink" link or tab on "member" PDP "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify collection member products on master pdp


  @domain_selection @use_regression @pdp_heroku
  Scenario: Verify collection tab appears on page -heroku
    Given I visit the web site as a guest user
    When I select a "member" product for "CollectionCTA" and navigate to PDP
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "collectionCTA" link or tab on "member" PDP "site" mode
    And I click "productsCollectionLink" link or tab on "member" PDP "site" mode
    And I setup "legacy" cookies for "master" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify collection member products on master pdp


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify in stock message for product with no size on member PDP in Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I setup "sizeColor" cookies for "member" PDP "site" mode
    Then I verify the "in stock" message on member PDP in site mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify in stock message for product with no size on member PDP in Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I setup "sizeColor" cookies for "member" PDP "site" mode
    Then I verify the "in stock" message on member PDP in site mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify TrueFit functionality on PDP in Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "TrueFit" product and navigate to PDP in "site" mode
#    When I search for "jeans"
#    And I select a random member product
    And I setup "sizeColor" cookies for "member" PDP "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    Then I verify "TrueFit" functionality on member PDP site mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify TrueFit functionality on PDP in Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "TrueFit" product and navigate to PDP in "site" mode
#    When I search for "jeans"
#    And I select a random member product
    And I setup "sizeColor" cookies for "member" PDP "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    Then I verify "TrueFit" functionality on member PDP site mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify functionality of eligible BOPS product on member PDP -legacy
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    Then I verify functionality of eligible BOPS product on member PDP


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify functionality of eligible BOPS product on member PDP -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22805" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    Then I verify functionality of eligible BOPS product on member PDP


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify the FOB functionality on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    And I hover over "women" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "women" FOB tab on PDP


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify the FOB functionality on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    And I hover over "women" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "women" FOB tab on PDP


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify the basic elements on E-Gift Card PDP -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "4391437" in site mode
    And I insert parameter to hit "QA" PDP "site" mode
    Then I verify the basic elements of the "E-Gift Card" PDP
    And I fill out the required fields on "E-Gift Card" PDP by selecting "Arbitrary value of $60"
    And I click "A2B" button on "e-gift" PDP "site" mode
    And I click "Checkout" button on "e-gift" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify the basic elements on E-Gift Card PDP -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "4391437" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    Then I verify the basic elements of the "E-Gift Card" PDP
    And I fill out the required fields on "E-Gift Card" PDP by selecting "Arbitrary value of $120"
    And I click "A2B" button on "e-gift" PDP "site" mode
    And I click "Checkout" button on "e-gift" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify A2B fuctionality of E-Gift Cards -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "4391437" in site mode
    And I insert parameter to hit "QA" PDP "site" mode
    Then I verify the basic elements of the "E-Gift Card" PDP
    And I fill out the required fields on "E-Gift Card" PDP by selecting "Price Options"
    And I click "A2B" button on "e-gift" PDP "site" mode
    And I click "ContinueShopping" button on "e-gift" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify A2B fuctionality of E-Gift Cards -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "4391437" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    Then I verify the basic elements of the "E-Gift Card" PDP
    And I fill out the required fields on "E-Gift Card" PDP by selecting "Price Options"
    And I click "A2B" button on "e-gift" PDP "site" mode
    And I click "ContinueShopping" button on "e-gift" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify A2B error messages on E-Gift Cards PDP -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "4391437" in site mode
    And I insert parameter to hit "QA" PDP "site" mode
    Then I verify E-Gift Cards A2B error messages with invalid values for required fields


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify A2B error messages on E-Gift Cards PDP -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "4391437" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    Then I verify E-Gift Cards A2B error messages with invalid values for required fields


  @use_regression @domain_selection @pdp_legacy
  Scenario Outline: Verify free shipping message under Shipping & Returns tab on member PDP -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "<ProdID>" in site mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the "<ShippingMsg>" message under shipping & returns tab
    Examples:
      |ProdID  |ShippingMsg |
      |2864777 |This item qualifies for Free Shipping!|
      |1064073 |This item qualifies for Free Shipping with minimum purchase! Exclusions & Details|
#      |22805   |This item is assigned a shipping surcharge fee of $4.00 based on size/weight and/or special shipping requirements|


  @use_regression @domain_selection @pdp_heroku
  Scenario Outline: Verify free shipping message under Shipping & Returns tab on member PDP -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "<ProdID>" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the "<ShippingMsg>" message under shipping & returns tab
    Examples:
      |ProdID  |ShippingMsg |
      |2864777 |This item qualifies for Free Shipping!|
      |1064073 |This item qualifies for Free Shipping with minimum purchase! Exclusions & Details|
#      |22805   |This item is assigned a shipping surcharge fee of $4.00 based on size/weight and/or special shipping requirements|


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify the links under Shipping & Returns tab on member PDP -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22805" in site mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the links under shipping & returns tab on member PDP site mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify the links under Shipping & Returns tab on member PDP -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22805" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the links under shipping & returns tab on member PDP site mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify the links under Shipping & Returns tab on master PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "19942" in site mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Shipping & Returns" in bottom tabs section on master PDP site mode
    Then I verify the links under shipping & returns tab on master PDP site mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify the links under Shipping & Returns tab on master PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "19942" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Shipping & Returns" in bottom tabs section on master PDP site mode
    Then I verify the links under shipping & returns tab on master PDP site mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify quantity, sizes and colors features on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "2970058" in site mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I select "random" color on member PDP in site mode
    And I select "random" size on member PDP in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify quantity, sizes and colors features on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "2970058" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I select "random" color on member PDP in site mode
    And I select "random" size on member PDP in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify submitting product reviews as recommended product on member PDP Site mode -legacy
    Given I visit the web site as a registered user
    When I search for "jeans"
    And I select a random member product
    And I insert parameter to hit "QA" PDP "site" mode
    Then I click on the write a review button on PDP site mode
    And I fill out required fields as "recommended" and submit reviews
    Then I verify the confirmation message for writing a product review


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify submitting product reviews as recommended product on member PDP Site mode -heroku
    Given I visit the web site as a registered user
    When I search for "jeans"
    And I select a random member product
    And I insert parameter to hit "heroku" PDP "site" mode
    Then I click on the write a review button on PDP site mode
    And I fill out required fields as "recommended" and submit reviews
    Then I verify the confirmation message for writing a product review


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify Q&A section elements on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on member PDP site mode
    Then I verify "Q&A section" in bottom tabs section on member PDP site mode
    And I click "Ask a new question button" in bottom tabs section on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify Q&A section elements on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on member PDP site mode
    Then I verify "Q&A section" in bottom tabs section on member PDP site mode
    And I click "Ask a new question button" in bottom tabs section on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify that Olapic section is available on member PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
    And I insert parameter to hit "QA" PDP "site" mode
    Then I verify that Olapic section is available on member PDP site mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify that Olapic section is available on member PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
    And I insert parameter to hit "heroku" PDP "site" mode
    Then I verify that Olapic section is available on member PDP site mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify basic elements of QuickLook on master PDP Site mode -legacy
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" site mode
    Then I verify the basic elements of QuickLook overlay on "PDP" site mode
    And I click ProductDetails button on QuickLook overlay on "Master PDP" site mode
    Then I verify navigation to member PDP in site mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify basic elements of QuickLook on master PDP Site mode -heroku
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" site mode
    Then I verify the basic elements of QuickLook overlay on "PDP" site mode
    And I click ProductDetails button on QuickLook overlay on "Master PDP" site mode
    Then I verify navigation to member PDP in site mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify A2B functionality for BigTicket items on member PDP -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1658726" in site mode
    And I setup "2630" cookie for "BigTicket" on member PDP site mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I enter zipcode "94587" for a BigTicket item and click the submit button on member PDP
    Then I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode
    And I verify the productId being the same as added to bag


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify A2B functionality for BigTicket items on member PDP -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1658726" in site mode
    And I setup "2630" cookie for "BigTicket" on member PDP site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I enter zipcode "94587" for a BigTicket item and click the submit button on member PDP
    Then I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode
    And I verify the productId being the same as added to bag


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify A2B functionality for BigTicket items on master PDP -legacy
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1434000" in site mode
    And I setup "2630" cookie for "BigTicket" on master PDP site mode
    And I insert parameter to hit "QA" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I enter zipcode "94587" for a BigTicket item and click the submit button on master PDP
    Then I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    And I verify quickbag count is updating with "1" items in bag
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify A2B functionality for BigTicket items on master PDP -heroku
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1434000" in site mode
    And I setup "2630" cookie for "BigTicket" on master PDP site mode
    And I insert parameter to hit "heroku" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I enter zipcode "94587" for a BigTicket item and click the submit button on master PDP
    Then I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    And I verify quickbag count is updating with "1" items in bag
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


###############################################################
# Feature: PDPiShipModeLegacy&Hroku Validation & Verification

  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify basic components of member PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I verify basic components of "member" PDP in "iship" mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify basic components of member PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I navigate directly to PDP with PID "5747" in iship mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I verify basic components of "member" PDP in "iship" mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify basic components of master PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I verify basic components of "master" PDP in "iship" mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify basic components of master PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I navigate directly to PDP with PID "657579" in iship mode
#    Then I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I verify basic components of "master" PDP in "iship" mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify add to bag on member PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "ContinueShopping" button on "member" PDP "iship" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify add to bag on member PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "ContinueShopping" button on "member" PDP "iship" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify add to bag on master PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "iship" mode
    And I click "ContinueShopping" button on "master" PDP "iship" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify add to bag on master PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "iship" mode
    And I click "ContinueShopping" button on "master" PDP "iship" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify QuickBag remove functionality on member PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "ContinueShopping" button on "member" PDP "iship" mode
    And I verify quickbag count is updating with "1" items in bag
    Then I select another "member" product to build product history
    And I insert parameter to hit "QA" PDP "iship" mode
    And I click the "remove" on quick bag
    Then I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify QuickBag remove functionality on member PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I click "A2B" button on "member" PDP "iship" mode
    And I click "ContinueShopping" button on "member" PDP "iship" mode
    And I verify quickbag count is updating with "1" items in bag
    Then I select another "member" product to build product history
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I click the "remove" on quick bag
    Then I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify QuickBag remove functionality on master PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "iship" mode
    And I click "ContinueShopping" button on "master" PDP "iship" mode
    And I verify quickbag count is updating with "1" items in bag
    Then I select another "master" product to build product history
    And I insert parameter to hit "QA" PDP "iship" mode
    And I click the "remove" on quick bag
    Then I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify QuickBag remove functionality on master PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "iship" mode
    And I click "ContinueShopping" button on "master" PDP "iship" mode
    And I verify quickbag count is updating with "1" items in bag
    Then I select another "master" product to build product history
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I click the "remove" on quick bag
    Then I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify the RVI panel on member PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I select a "member" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "member" product to build product history
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the RVI panel on "member" PDP "iShip" mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify the RVI panel on member PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
#    When I select a "member" product and navigate to PDP in "iship" mode
    Then I navigate directly to PDP with PID "5747" in iship mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "member" product to build product history
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the RVI panel on "member" PDP "iShip" mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify the RVI panel on master PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "master" product to build product history
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the RVI panel on "master" PDP "iShip" mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify the RVI panel on master PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    When I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "master" product to build product history
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the RVI panel on "master" PDP "iShip" mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify social icons on member PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    When I select a "member" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on member PDP iship mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify social icons on member PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    When I select a "member" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on member PDP iship mode


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify social icons on master PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on master PDP iship mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify social icons on master PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    When I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on master PDP iship mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify the FOB functionality on member PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    Then I select a "member" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "women" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "women" FOB tab on PDP


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify the FOB functionality on member PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
#    Then I select a "member" product and navigate to PDP in "iship" mode
    Then I navigate directly to PDP with PID "5747" in iship mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "women" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "women" FOB tab on PDP


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify the FOB functionality on master PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "men" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "men" FOB tab on PDP


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_heroku
  Scenario: Verify the FOB functionality on master PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
#    Then I select a "master" product and navigate to PDP in "iship" mode
    Then I navigate directly to PDP with PID "657579" in iship mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "men" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "men" FOB tab on PDP


  @use_regression @domain_selection @mcom_pdp_sanity @pdp_legacy
  Scenario: Verify basic elements of QuickLook on master PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" iship mode
    Then I verify the basic elements of QuickLook overlay on "PDP" iship mode
    And I click ProductDetails button on QuickLook overlay on "Master PDP" iship mode
    Then I verify navigation to member PDP in iship mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify basic elements of QuickLook on master PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" iship mode
    Then I verify the basic elements of QuickLook overlay on "PDP" iship mode
    And I click ProductDetails button on QuickLook overlay on "Master PDP" iship mode
    Then I verify navigation to member PDP in iship mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify the links under Shipping & Returns tab on member PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I navigate directly to PDP with PID "22805" in iship mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the links under shipping & returns tab on member PDP iship mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify the links under Shipping & Returns tab on member PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I navigate directly to PDP with PID "22805" in iship mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the links under shipping & returns tab on member PDP iship mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify the links under Shipping & Returns tab on master PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I navigate directly to PDP with PID "19942" in iship mode
    And I insert parameter to hit "QA" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Shipping & Returns" in bottom tabs section on master PDP iship mode
    Then I verify the links under shipping & returns tab on master PDP iship mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify the links under Shipping & Returns tab on master PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I navigate directly to PDP with PID "19942" in iship mode
    And I insert parameter to hit "heroku" PDP "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Shipping & Returns" in bottom tabs section on master PDP iship mode
    Then I verify the links under shipping & returns tab on master PDP iship mode


  @use_regression @domain_selection @pdp_legacy
  Scenario: Verify that Olapic section is available on member PDP iShip mode -legacy
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "jeans"
    And I select a random member product
    And I insert parameter to hit "QA" PDP "iship" mode
    Then I verify that Olapic section is available on member PDP iship mode


  @use_regression @domain_selection @pdp_heroku
  Scenario: Verify that Olapic section is available on member PDP iShip mode -heroku
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I search for "jeans"
    And I select a random member product
    And I insert parameter to hit "heroku" PDP "iship" mode
    Then I verify that Olapic section is available on member PDP iship mode
