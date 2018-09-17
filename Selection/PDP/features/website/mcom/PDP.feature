#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: May 01,2017
#---------------------------------------------------

Feature: PDP Validation & Verification

  @use_regression @domain_selection @mcom_selection_pdp @akamai_waf
  Scenario: Verify basic components of member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    Then I verify basic components of "member" PDP in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp @akamai_waf
  Scenario: Verify basic components of master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I resize the dimension on window screen
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify basic components of "master" PDP in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B & Checkout on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify the productId being the same as added to bag
    And I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify add to bag on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify add to bag on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    Then I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag


  @use_regression @domain_selection @mcom_selection_pdp  @dsv_desktop_sev2 @ifs
  Scenario: Verify the zoomer on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    Then I verify the zoomer on member PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify zoomer & alternate images on member PDP Site mode
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
    Then I verify the zoomer and altimages on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify zoomer & alternate images on master PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "3409057" in site mode
    Then I verify the zoomer and altimages on master PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario Outline: Verify the GIFTNOW on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "<productId>" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the GIFTNOW on member PDP
    Examples:
      |productId|
      |77589    |


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify that webCollage is displayed on PDP
    Given I visit the web site as a guest user
    When I select a "eligible" product for "webCollage" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    Then I verify that "webCollage" is "displayed" on PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify that webCollage is not displayed on PDP
    Given I visit the web site as a guest user
    When I select a "non_eligible" product for "webCollage" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    Then I verify that "webCollage" is "not displayed" on PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify video functionality on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "eligible" product for "webCollage" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    Then I verify that "Video" is "loading" on PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify empty bag message on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify empty bag message on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify that quickbag count updates on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I verify quickbag count is updating with "0" items in bag
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I verify quickbag count is updating with "1" items in bag
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I verify quickbag count is updating with "2" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
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


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify quickbag on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I verify quickbag flyout on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify quickbag on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify quickbag flyout on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario Outline: Verify QuickBag product image navigation & color&quantity reset on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "<productId>" in site mode
    And I select "random" color on member PDP in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I navigate directly to PDP with PID "22804" in site mode
    And I click the "product image" on quick bag
    Then I verify navigating to "member" PDP "site" mode
    And I verify that color&quantity are reset to default values on member PDP site mode
    Examples:
      |productId|
      |77589    |


  @use_regression @domain_selection @mcom_selection_pdp
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


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag product image link navigates to PDP from My Account page
    Given I visit the web site as a registered user
    When I select a "member" product and navigate to PDP in "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I navigate to "MyAccount" in "site" mode
    And I click the "product image" on quick bag
    Then I verify navigating to "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Rendering QuickBag in legacy pages
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    And I navigate to international context page
    Then I verify quickbag flyout on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag checkout functionality on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag checkout functionality on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify QuickBag remove functionality on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I verify quickbag count is updating with "1" items in bag
    Then I select another "member" product to build product history
    And I click the "remove" on quick bag
    Then I verify quickbag count is updating with "0" items in bag


  @use_regression @domain_selection @mcom_selection_pdp
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


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the RVI panel on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "member" product to build product history
    And I scroll up&down for "RVI" element to load on PDP site mode
    Then I verify the RVI panel on "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the RVI panel on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select another "master" product to build product history
    And I scroll up&down for "RVI" element to load on PDP site mode
    Then I verify the RVI panel on "master" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify that Olapic section is available on member PDP Site mode
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
    Then I verify that Olapic section is available on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify that Olapic section is unavailable on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll down to view olapic panel
    Then I verify that Olapic section is unavailable on master PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Zero State Olapic on Radical PDP
    Given I visit the web site as a guest user
#    When I search for "Michael Kors Handbags"
#    And I select a random member product
    And I select a "ZeroState" product for "Olapic" and navigate to PDP
    When I scroll down to view olapic panel
    And I click on Olapic icon
    Then I click the add photo button
    When I close the photo uploader
    Then I click on view gallery


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Full State Olapic on Radical PDP
    Given I visit the web site as a guest user
#    When I added a "available and olapic" product to my bag
    And I select a "FullState" product for "Olapic" and navigate to PDP
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I scroll down to view olapic panel
    And I click on Olapic next & previous arrows
    When I click on an Olapic image
    Then I verify redirecting to radical PDP after clicking Olapic product list


  @use_regression @domain_selection @mcom_pdp_sanity @mcom_selection_pdp  @dsv_desktop_sev2
  Scenario: Verify submitting product reviews as recommended product on member PDP Site mode
    Given I visit the web site as a registered user
    When I search for "jeans"
    And I select a random member product
    Then I click on the write a review button on PDP site mode
    And I fill out required fields as "recommended" and submit reviews
    Then I verify the confirmation message for writing a product review


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify submitting product reviews as not recommended product on member PDP Site mode
    Given I visit the web site as a registered user
    When I search for "jeans"
    And I select a random member product
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I click on the write a review button on PDP site mode
    And I fill out required fields as "not recommended" and submit reviews
    Then I verify the confirmation message for writing a product review


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: To verify guest user has to signin before writing a product review on PDP
    Given I visit the web site as a registered user
    When I click "profile" on "MyAccount" page in site mode
    And I search for "jeans"
    And I delete all cookies
    And I select a random member product
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click on the write a review button on PDP site mode
    Then I verify that guest user is navigated to "Account" signin page
    And I signin as the previous registered user
    And I fill out required fields as "not recommended" and submit reviews
    Then I verify the confirmation message for writing a product review


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: To verify that only one review for a particular product can be submitted -Registered user
    Given I visit the web site as a registered user
    When I search for "jeans"
    And I select a random member product
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I click on the write a review button on PDP site mode
    And I fill out required fields as "recommended" and submit reviews
    And I verify the confirmation message for writing a product review
    Then I verify that only one review for a particular product can be submitted


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: To verify that only one review for a particular product can be submitted -Guest user
    Given I visit the web site as a registered user
    When I click "profile" on "MyAccount" page in site mode
    And I search for "jeans"
    And I select a random member product
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click on the write a review button on PDP site mode
    And I fill out required fields as "recommended" and submit reviews
    Then I verify the confirmation message for writing a product review
    Then I visit the web site as a guest user
    And I navigate to PDP with "same" PID
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click on the write a review button on PDP site mode
    Then I verify that guest user is navigated to "Account" signin page
    And I signin as the previous registered user
    Then I verify the confirmation message for writing a product review


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: To verify write a product review on master PDP Site mode
    Given I visit the web site as a registered user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I click on the write a review button on PDP site mode
    And I fill out required fields as "recommended" and submit reviews
    Then I verify the confirmation message for writing a product review


  @use_regression @domain_selection @mcom_selection_pdp  @dsv_desktop_sev2
  Scenario: Verify Q&A functionality as a guest user on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on member PDP site mode
    Then I verify "Q&A section" in bottom tabs section on member PDP site mode
    And I click "Ask a new question button" in bottom tabs section on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Q&A functionality as a guest user on master PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "19942" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on master PDP site mode
    Then I verify "Q&A section" in bottom tabs section on member PDP site mode
    And I click "Ask a new question button" in bottom tabs section on master PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Q&A functionality as a registered user on member PDP Site mode
    Given I visit the web site as a registered user
    When I navigate directly to PDP with PID "22804" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on member PDP site mode
    And I click "Ask a new question button" in bottom tabs section on member PDP site mode
    Then I verify "Q&A" overlay on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Q&A functionality as a guest-to-registered user on member PDP Site mode
    Given I visit the web site as a registered user
    When I click "profile" on "MyAccount" page in site mode
    Then I close & reopen the browser
    And I visit the web site as a guest user
    And I navigate directly to PDP with PID "22804" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Product Q&A" in bottom tabs section on member PDP site mode
    And I click "Ask a new question button" in bottom tabs section on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page
    And I signin as the previous registered user
#    Bellow step is a temporary V/V until further clarifications
    Then I verify "Q&A Guest" overlay on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp  @dsv_desktop_sev2
  Scenario: Verify Pinterest link on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I click "Pinterest" icon on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Pinterest link on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I click "Pinterest" icon on master PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify EmailFriends link on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    Then I click "Email" icon on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify EmailFriends link on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I click "Email" icon on master PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp  @dsv_desktop_sev2 @ifs
  Scenario: Verify social icons on member PDP Site mode
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify social icons on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify social icons on master PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp  @dsv_desktop_sev2
  Scenario: Verify social icons on CHANEL PDP Site mode
    Given I visit the web site as a guest user
    When I mouse over "beauty" category from top navigation
    And I select "CHANEL" link from FOB on PDP
    And I select "skincare" link from left nav sub categories
    And I select a random "CHANEL" product
    Then I verify social icons on CHANEL PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Special Characters for radical PDP
    Given I visit the web site as a guest user
    When I select a "SpecialCharacter" product for "BrandNameLink" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the navigation to BrandName page


  @domain_selection @use_regression @mcom_selection_pdp
  Scenario: Verify ATB for Master product with Quotation in product's title
    Given I visit the web site as a guest user
    When I select a "master" product for "SpecialCharacter" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "master" PDP "site" mode
    And I click "ContinueShopping" button on "master" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag


  @domain_selection @use_regression @mcom_selection_pdp
  Scenario: Verify collection tab appears on page
    Given I visit the web site as a guest user
    When I select a "member" product for "CollectionCTA" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "collectionCTA" link or tab on "member" PDP "site" mode
    And I click "productsCollectionLink" link or tab on "member" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify collection member products on master pdp


  @domain_selection @use_regression @mcom_selection_pdp
  Scenario: Verify Suppressed Product Q&A tab in PDP
    Given I visit the web site as a guest user
    When I select a "member" product for "SuppressedQ&A" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify Q&A tab is not displayed on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the shopping bag Id is displaying in signin mode
    Given I visit the web site as a registered user
    When I navigate directly to "member" PDP and add a product to my bag in "site" mode
    And I click the "QBlink" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode
    And I verify shopping bag ID is displayed


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify shopping bag ID is removed after removing items
    Given I visit the web site as a guest user
    When I navigate directly to "member" PDP and add a product to my bag in "site" mode
    And I click the "QBlink" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode
    And I click the product "remove" link on shopping bag page
    Then I verify shopping bag ID is removed


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify shopping bag product image link navigates to PDP in Site mode
    Given I visit the web site as a guest user
    When I navigate directly to "member" PDP and add a product to my bag in "site" mode
    And I click the "QBlink" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode
    And I click the product "image" link on shopping bag page
    Then I verify navigating to "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario Outline: Verify shopping bag product image navigation & color&quantity reset on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "<productId>" in site mode
    And I select "random" color on member PDP in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    And I verify the productId being the same as added to bag
    And I click the product "image" link on shopping bag page
    Then I verify navigating to "member" PDP "site" mode
    And I verify that color&quantity are reset to default values on member PDP site mode
    Examples:
      |productId|
      |77589    |


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Empty bag & Removed item messages on shopping bag page in Site mode
    Given I visit the web site as a guest user
    When I click the "QBlink" on quick bag
    And I verify the "empty bag" message on shopping bag page in "site" mode
    When I navigate directly to "member" PDP and add a product to my bag in "site" mode
    And I click the "QBlink" on quick bag
    And I click the product "remove" link on shopping bag page
    Then I verify the "removed" message on shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify in stock message for product with no size on member PDP in Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I setup "sizeColor" cookies for "member" PDP "site" mode
    Then I verify the "in stock" message on member PDP in site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify in stock message for product with size on member PDP in Site mode
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
    And I setup "sizeColor" cookies for "member" PDP "site" mode
    And I select "random" size on member PDP in site mode
    Then I verify the "in stock" message on member PDP in site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify availibility message and A2B functionality on member PDP in Site mode
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
    And I setup "sizeColor" cookies for "member" PDP "site" mode
    Then I verify the "availibility" message on member PDP in site mode
    And I select "random" size on member PDP in site mode
    Then I verify the "in stock" message on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify availability message on member PDP site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "2651044" in site mode
    Then I verify the availability message as "in stock : Direct from vendor, usually ships within x business days." on member PDP in site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify TrueFit functionality on PDP in Site mode
    Given I visit the web site as a guest user
#    When I select a "TrueFit" product and navigate to PDP in "site" mode
    When I search for "jeans"
    And I select a random member product
    And I setup "sizeColor" cookies for "member" PDP "site" mode
    Then I verify "TrueFit" functionality on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify functionality of eligible BOPS product on member PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "147374" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify functionality of eligible BOPS product on member PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify functionality of eligible BOPS product w/ size on member PDP
    Given I visit the web site as a guest user
    When I select a "eligible" product for "BOPS" and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    And I select "random" size on member PDP in site mode
    Then I verify functionality of eligible BOPS product on member PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify special offers for Free Shipping on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "147374" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify "Free Shipping" offer under SpecialOffers tab on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B for Free Shipping product on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "147374" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify "Free Shipping" offer under SpecialOffers tab on member PDP site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify the "Free Shipping" product added to bag on QB & Shoppingbag page


  @use_regression @domain_selection @mcom_selection_pdp  @dsv_desktop_sev2
  Scenario: Verify special offers for GWP product on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1355960" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify "GWP" offer under SpecialOffers tab on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B for GWP product on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "353083" in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    Then I verify the "GWP" on A2B page
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the "GWP" product added to bag on QB & Shoppingbag page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify product Type feature on member PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1101386" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify "Type" functionality on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify BigTicket items on member PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1658726" in site mode
    And I setup "2630" cookie for "BigTicket" on member PDP site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the basic elements of the "BigTicket" PDP
    And I verify the zoomer and altimages on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify BigTicket items on master PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1434000" in site mode
    And I setup "2630" cookie for "BigTicket" on master PDP site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the basic elements of the "BigTicket" PDP
    And I verify the zoomer and altimages on master PDP site mode


  @use_regression @domain_selection @mcom_pdp_sanity @mcom_selection_pdp
  Scenario: Verify A2B functionality for BigTicket items on member PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1658726" in site mode
    And I setup "2630" cookie for "BigTicket" on member PDP site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I enter zipcode "94587" for a BigTicket item and click the submit button on member PDP
    Then I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I verify quickbag count is updating with "1" items in bag
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode
    And I verify the productId being the same as added to bag


  @use_regression @domain_selection @mcom_pdp_sanity @mcom_selection_pdp
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


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B & availibility error for BigTicket items on member PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1658726" in site mode
    And I setup "2630" cookie for "BigTicket" on member PDP site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I enter zipcode "" for a BigTicket item and click the submit button on member PDP
    And I enter zipcode "9999" for a BigTicket item and click the submit button on member PDP
    And I click "A2B" button on "member" PDP "site" mode
    Then I verify the error message "Please enter a valid zip code" on member PDP site mode
    And I enter zipcode "99999" for a BigTicket item and click the submit button on member PDP
    And I click "A2B" button on "member" PDP "site" mode
    Then I verify the error message "Item availability unknown. Please call." on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
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


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the Mattresses on member PDP
    Given I visit the web site as a guest user
    And I hover over "BED & BATH" FOB tab on PDP
    And I select "Mattresses" link from FOB on PDP
    And I select the subCategory link "California King Mattresses" under Category header "Mattresses Size"
    And I select a random member product
#    And I setup "2630" cookie for "BigTicket" on member PDP site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select a Type on member PDP site mode
    Then I verify the basic elements of the "BigTicket" PDP
    And I verify the zoomer and altimages on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the A2B functionality for Mattresses on member PDP
    Given I visit the web site as a guest user
    And I hover over "BED & BATH" FOB tab on PDP
    And I select "Mattresses" link from FOB on PDP
    And I select the subCategory link "California King Mattresses" under Category header "Mattresses Size"
    And I select a random member product
#    And I setup "2630" cookie for "BigTicket" on member PDP site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select a Type on member PDP site mode
    And I enter zipcode "94587" for a BigTicket item and click the submit button on member PDP
    Then I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode
    And I verify the productId being the same as added to bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the FOB functionality on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
#    And I setup "headerFooter" cookies for "member" PDP "site" mode
    And I hover over "women" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "women" FOB tab on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the FOB functionality on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "men" FOB tab on PDP
    And I select "activewear" link from FOB on PDP
    Then I verify the "men" FOB tab on PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify user can add multiple items to the bag by navigating through the FOB
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over "women" FOB tab on PDP
    And I select "jeans" link from FOB on PDP
    And I select a random member product
    And I select "random" size on member PDP in site mode
    Then I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag
    And I hover over "men" FOB tab on PDP
    And I select "jeans" link from FOB on PDP
    And I select a random member product
    And I select "random" size on member PDP in site mode
    Then I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I verify quickbag count is updating with "2" items in bag
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify basic elements on CHANEL PDP
    Given I visit the web site as a guest user
    When I hover over "beauty" FOB tab on PDP
    And I select "CHANEL" link from FOB on PDP
    And I select "GIFTS" link from left nav sub categories
    And I select a random "CHANEL" product
    Then I verify the basic elements of the "CHANEL" PDP


  @use_regression @domain_selection @mcom_pdp_sanity @mcom_selection_pdp
  Scenario: Verify A2B functionality on CHANEL PDP
    Given I visit the web site as a guest user
    When I hover over "beauty" FOB tab on PDP
    And I select "CHANEL" link from FOB on PDP
    And I select "skincare" link from left nav sub categories
    And I select a random "CHANEL" product
    Then I verify the basic elements of the "CHANEL" PDP
    And I click "A2B" button on "CHANEL" PDP "site" mode
    And I click "ContinueShopping" button on "CHANEL" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the layout of the Gift Card splash page
    Given I visit the web site as a guest user
    When I navigate to "GiftCard Page" in "site" mode
    Then I verify the layout of gift cards page


  @use_regression @domain_selection @mcom_selection_pdp  @dsv_desktop_sev2
  Scenario: Verify the basic elements on E-Gift Card PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "4391437" in site mode
    Then I verify the basic elements of the "E-Gift Card" PDP
    And I fill out the required fields on "E-Gift Card" PDP by selecting "Arbitrary value of $60"
    And I click "A2B" button on "e-gift" PDP "site" mode
    And I click "Checkout" button on "e-gift" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode
    And I verify the productId being the same as added to bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B fuctionality of E-Gift Cards
    Given I visit the web site as a guest user
    When I navigate to "GiftCard Page" in "site" mode
    And I select the "All Occasions" under "E-Gift Cards" subCategory on Gift Card page
    And I select a random "E-Gift Card" product
    Then I verify the basic elements of the "E-Gift Card" PDP
    And I fill out the required fields on "E-Gift Card" PDP by selecting "Price Options"
    And I click "A2B" button on "e-gift" PDP "site" mode
    And I click "ContinueShopping" button on "e-gift" PDP "site" mode
    Then I verify quickbag count is updating with "1" items in bag
    And I click the "checkout" on quick bag
    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B error messages on E-Gift Cards PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "4391437" in site mode
    Then I verify E-Gift Cards A2B error messages with invalid values for required fields


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the basic elements on Gift Card page
    Given I visit the web site as a guest user
    When I navigate to "GiftCard Page" in "site" mode
    And I select the "All Occasions" under "Gift Cards" subCategory on Gift Card page
#    And I select a random "Gift Card" product
#    Then I verify the basic elements of the "Gift Card" PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B fuctionality of Gift Cards
    Given I visit the web site as a guest user
    When I navigate to "GiftCard Page" in "site" mode
    And I select the "Baby" under "Gift Cards" subCategory on Gift Card page
#    And I select a random "Gift Card" product wip
#    And I click "A2B" button on "member" PDP "site" mode
#    And I click "ContinueShopping" button on "member" PDP "site" mode
#    Then I verify quickbag count is updating with "1" items in bag
#    And I click the "checkout" on quick bag
#    Then I verify basic elements of shopping bag page in "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS vertical recommendation panels on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product for "PROS" and navigate to PDP
    And I resize the dimension on window screen
    Then I verify the vertical recommendation panel on member PDP
    And I select a random product from vertical recommendation panel on member PDP site mode
    Then I verify navigation to the corresponding PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS vertical recommendation panels on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product for "PROS" and navigate to PDP
    And I resize the dimension on window screen
    Then I verify the vertical recommendation panel on master PDP
    And I select a random product from vertical recommendation panel on master PDP site mode
    Then I verify navigation to the corresponding PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS horizontal recommendation panels on member PDP Site mode
    Given I visit the web site as a guest user
    When I select a "member" product for "PROS" and navigate to PDP
    And I resize the dimension on window screen
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the horizontal recommendation panel on member PDP
    And I select a random product from horizontal recommendation panel on member PDP site mode
    Then I verify navigation to the corresponding PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS horizontal recommendation panels on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product for "PROS" and navigate to PDP
    And I resize the dimension on window screen
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the horizontal recommendation panel on master PDP
    And I select a random product from horizontal recommendation panel on master PDP site mode
    Then I verify navigation to the corresponding PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario Outline: Verify free shipping message under Shipping & Returns tab on member PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "<ProdID>" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the "<ShippingMsg>" message under shipping & returns tab
    Examples:
      |ProdID  |ShippingMsg |
      |2864777 |This item qualifies for Free Shipping!|
      |1064073 |This item qualifies for Free Shipping with minimum purchase! Exclusions & Details|
#      |22805   |This item is assigned a shipping surcharge fee of $4.00 based on size/weight and/or special shipping requirements|


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the links under Shipping & Returns tab on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22805" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the links under shipping & returns tab on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the links under Shipping & Returns tab on master PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "19942" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Shipping & Returns" in bottom tabs section on master PDP site mode
    Then I verify the links under shipping & returns tab on master PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify quantity, sizes and colors features on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "2351345" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I select "random" color on member PDP in site mode
    And I select "random" size on member PDP in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "Checkout" button on "member" PDP "site" mode
    Then I verify basic elements of shopping bag page in "site" mode
    And I verify the productId being the same as added to bag


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B, Add2List & Add2Registry select size error message on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1386760" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2B" button on "member" PDP "site" mode
    Then I verify the error message "Please select a size." on member PDP site mode
    And I click "A2L" button on "member" PDP "site" mode
    Then I verify the error message "Please select a size." on member PDP site mode
    And I click "A2R" button on "member" PDP "site" mode
    Then I verify the error message "Please select a size." on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B color&size combination unavailable error message on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "2970058" in site mode
    And I select a random "unavailable" color & size on member PDP in site mode
    And I select "default" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    Then I verify the error message "Sorry, the selected color and size combination is unavailable." on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B limit reached error message on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "22804" in site mode
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    And I select "minimum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    Then I verify the error message "You've reached the limit for this item." on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify A2B limit reached error message for product with size&color on member PDP Site mode
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
#    When I navigate directly to PDP with PID "2970058" in site mode
    Then I select the first available size
    And I select "maximum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    And I click "ContinueShopping" button on "member" PDP "site" mode
    Then I select the first available size
    And I select "minimum" quantity on member PDP in site mode
    And I click "A2B" button on "member" PDP "site" mode
    Then I verify the error message "You've reached the limit for this item. Please select another size/color." on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario Outline: Verify alphanumeric ascending order on member PDP
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "<productID>" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify sizes are displayed in alphanumeric orders on member PDP
    Examples:
      |productID|
      |4085662  |
      |4811007  |
      |1320297  |
#    |2970058  |


  @use_regression @domain_selection @mcom_selection_pdp  @dsv_desktop_sev2
  Scenario: Verify alphanumeric sizes are in ascending order on member PDP Site mode
    Given I visit the web site as a guest user
    When I search for "jeans"
    And I select a random member product
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify sizes are displayed in alphanumeric orders on member PDP


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Add2Registry functionality on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "77589" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2R" button on "member" PDP "site" mode
    Then I verify that guest user is navigated to "Registry" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Add2List Signin on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "77589" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2L" button on "member" PDP "site" mode
    And I click "Signin link" on "A2L overlay" on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify navigation to member PDP by clicking on product image on WishList page
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "77589" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2L" button on "member" PDP "site" mode
    And I click "List link" on "A2L overlay" on member PDP site mode
    Then I verify navigation to "list" page in site mode
    And I click "product image" on "list" page in site mode
    Then I verify navigating to "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Add2List functionality for any list on member PDP Site mode
    Given I visit the web site as a registered user
    When I navigate directly to PDP with PID "77589" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2L" button on "member" PDP "site" mode
    And I click "List link" on "A2L overlay" on member PDP site mode
    Then I verify navigation to "list" page in site mode
    And I click "product image" on "list" page in site mode
    Then I verify navigating to "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Add2List functionality for default list on member PDP Site mode
    Given I visit the web site as a registered user
    When I navigate directly to PDP with PID "77589" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2L Dropdown" button on "member" PDP "site" mode
    And I click "DefaultList link" on "A2L overlay" on member PDP site mode
    Then I verify navigation to "list" page in site mode
    And I click "product image" on "list" page in site mode
    Then I verify navigating to "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Add2List functionality for a new list on member PDP Site mode
    Given I visit the web site as a registered user
    When I navigate directly to PDP with PID "77589" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "A2L Dropdown" button on "member" PDP "site" mode
    And I click "CreateList link" on "A2L overlay" on member PDP site mode
    Then I verify navigation to "list" page in site mode
    And I click "product image" on "list" page in site mode
    Then I verify navigating to "member" PDP "site" mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Add2List on member PDP with user session expired
    Given I visit the web site as a registered user
    When I navigate directly to PDP with PID "77589" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I force session expiration
    And I click "A2L Dropdown" button on "member" PDP "site" mode
    Then I verify the "A2L session expired" message on member PDP in site mode
    And I click "Signin link" on "A2L session expired popup" on member PDP site mode
    Then I verify that guest user is navigated to "Account" signin page


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify Savings based on actual sales pop up
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "656182" in site mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I click "Savings based on offering prices, not actual sales" link or tab on "member" PDP "site" mode
    Then I verify "Pricing Policy" overlay on member PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify SponsoredItems section on member PDP Site mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "4451329" in site mode
    And I scroll up&down for "Sponsored" element to load on PDP site mode
    Then I verify the "SponsoredItems" section on member PDP site mode
    And I select a random product from "SponsoredItems" on member PDP site mode
    Then I verify navigation to the corresponding PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify product name is same between Browse page & PDP Site mode
    Given I visit the web site as a guest user
    When I hover over "men" FOB tab on PDP
    And I select "jeans" link from FOB on PDP
    And I select a random member product and save its name details on Browse page
    Then I verify that product name details are same on PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify product name is same between Search page & PDP Site mode
    Given I visit the web site as a guest user
    When I search for "Jeans"
    And I select a random member product and save its name details on Search page
    Then I verify that product name details are same on PDP site mode


  @use_regression @domain_selection @mcom_selection_pdp @wip
  Scenario: Verify product price & currency symbol is same between Search page & PDP Site mode
    Given I visit the web site as a guest user
    When I search for "Jeans"
    And I select a random member product and save its price details on Search page
    Then I verify that product price details are same on PDP site mode

# Version One: B-100199
# Feature: As a product owner, I would like to make sure Link in class ="brandNameLink" has to be updated
# to href="/shop/featured"
# Author: UFT team
  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the updated brand name link on PDP in domestic mode
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1103208" in site mode
    Then I should see updated redirection URL link on PDP Page
    When I select brand name link
    Then I should redirect to the updated URL from PDP page

# Version One: B-100358
# Feature: As a product owner, I would like to have the hyperlink removed advising how to request the warranty.
# Author: UFT team
  @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify the updated information in warranty overlay on PDP page
    Given I visit the web site as a guest user
    When I navigate directly to PDP with PID "1473888" in site mode
    And I click on Request Warranty information bullet text link on PDP page
    Then I should see below text in warranty overlay on PDP page
      | If your item is covered by a warranty, please email us to receive a copy of the warranty details. |
    When I click on contact us link displayed in warranty overlay
    Then I should verify the warranty overlay is closed
    And I navigates to customer service contact page