Feature: PDP MCOM scenarios

   @dsv_desktop_sev2 @wip
  Scenario: Verify vertical scrolling functionality of vertical recommendation panel on PDP page (SITE)
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate PDP page that has recommendations in "vertical" panel in "site" mode
    Then I should see "vertical" recommendation panel on pdp page
    And I should see recommendation products in vertical panel
    And I should see scrolling for recommendation panel

   @dsv_desktop_sev2 @wip
  Scenario: Verify product thumbnail functionality in vertical recommendation panel on PDP page (SITE)
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate PDP page that has recommendations in "vertical" panel in "site" mode
    Then I should see "vertical" recommendation panel on pdp page
    And I select a random product from vertical recommendation panel on member PDP site mode
    Then I verify navigation to the corresponding PDP site mode

   @dsv_desktop_sev2 @wip
  Scenario: Verify product thumbnail functionality in horizontal recommendation panel on PDP page (SITE)
    Given I visit the web site as a guest user in "domestic" mode
    When I navigate PDP page that has recommendations in "horizontal" panel in "site" mode
    Then I should see "horizontal" recommendation panel on pdp page
    Then I should be navigated to the corresponding PDP page from recommendations section

   @dsv_desktop_sev2 @wip
  Scenario: Verify product description functionality in horizontal recommendation panel on PDP page (SITE)
    Given I visit the web site as a guest user in "domestic" mode
    When I select a "member" product for "PROS" and navigate to PDP
    Then I should see "horizontal" recommendation panel on pdp page
    And I select a random product from horizontal recommendation panel on member PDP site mode
    Then I verify navigation to the corresponding PDP site mode


   @dsv_desktop_sev2
  Scenario: Verify currency in category browse page and PDP Page in Domestic mode
    Given  I visit the web site as a guest user
    When I select "MEN" subcategory from flyout menu
    And I select "Dress Shirts" category from left nav
    Then I verify currency in category and PDP page in Domestic Mode

   @dsv_desktop_sev2
  Scenario:Verify Product QA links and QA tab in PDP
    Given I visit the web site as a guest user
    When I select a "member" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the basic attributes of Q&A TAB in PDP Page

   @dsv_desktop_sev2
  Scenario: Verify update order of Copy & Bullet points on PDP
    Given I visit the web site as a guest user
    When I navigate to a product having "with_warranty and orderable" attributes
    Then I verify the update order of Copy & Bullet points on PDP

   @dsv_desktop_sev2
  Scenario: Verify product thumbnail functionality in vertical recommendation panel on PDP page in ISHIP mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to international context page
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    When I navigate PDP page that has recommendations in "vertical" panel in "iship" mode
    Then I should see "vertical" recommendation panel on pdp page
    And I select a random product from vertical recommendation panel on member PDP iship mode
    Then I verify navigation to the corresponding PDP iship mode