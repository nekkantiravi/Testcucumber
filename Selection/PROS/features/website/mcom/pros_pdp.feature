Feature: Verify recommendations on PDP pages

# site
  @dsv_desktop_sev2 @use_regression @pros @domain_selection
  Scenario: Verify vertical scrolling functionality of vertical recommendation panel on PDP page (SITE)
    Given I visit the web site as a guest user in "domestic" mode
    When I pros user navigate PDP page that has recommendations in "vertical" panel in "site" mode
    Then I should see "vertical" recommendation panel on pdp page
    And I should see recommendation products in vertical panel
    And I should see scrolling for recommendation panel

  @dsv_desktop_sev2 @use_regression @pros @domain_selection
  Scenario: Verify product thumbnail functionality in vertical recommendation panel on PDP page (SITE)
    Given I visit the web site as a guest user in "domestic" mode
    When I pros user navigate PDP page that has recommendations in "vertical" panel in "site" mode
    Then I should see "vertical" recommendation panel on pdp page
    And I select a random product from vertical recommendation panel on member PDP site mode
    Then I verify navigation to the corresponding PDP site mode

  @dsv_desktop_sev2 @use_regression @pros @domain_selection
  Scenario: Verify product thumbnail functionality in horizontal recommendation panel on PDP page (SITE)
    Given I visit the web site as a guest user in "domestic" mode
    When I pros user navigate PDP page that has recommendations in "horizontal" panel in "site" mode
    Then I should see "horizontal" recommendation panel on pdp page
    Then I should be navigated to the corresponding PDP page from recommendations section

  @dsv_desktop_sev2 @use_regression @pros @domain_selection
  Scenario: Verify product description functionality in horizontal recommendation panel on PDP page (SITE)
    Given I visit the web site as a guest user in "domestic" mode
    When I select a "member" product and navigate to PDP
    Then I should see "horizontal" recommendation panel on pdp page
    And I select a random product from horizontal recommendation panel on member PDP site mode
    Then I verify navigation to the corresponding PDP site mode

#  iShip
  @dsv_desktop_sev2 @use_regression @pros @domain_selection
  Scenario: Verify PROS vertical recommendation panels on member PDP page in iShip mode
    Given I visit the web site as a guest user in "domestic" mode
    And I navigate to international context page
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    When I pros user navigate PDP page that has recommendations in "vertical" panel in "iship" mode
    Then I should see "vertical" recommendation panel on pdp page
    And I select a random product from vertical recommendation panel on member PDP iship mode
    Then I verify navigation to the corresponding PDP iship mode

  @dsv_desktop_sev2 @pros @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS vertical recommendation panels on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    When I select a "master" product and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the vertical recommendation panel on master PDP
    And I select a random product from vertical recommendation panel on master PDP iship mode
    Then I verify navigation to the corresponding PDP iship mode

  @dsv_desktop_sev2 @pros @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS horizontal recommendation panels on member PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    When I select a "member" product and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the horizontal recommendation panel on member PDP
    And I select a random product from horizontal recommendation panel on member PDP iship mode
    Then I verify navigation to the corresponding PDP iship mode

  @dsv_desktop_sev2 @pros @use_regression @domain_selection @mcom_selection_pdp
  Scenario: Verify PROS horizontal recommendation panels on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    When I select a "master" product and navigate to PDP
    And I scroll upDown for lazyLoad elements to load on PDP
    Then I verify the horizontal recommendation panel on master PDP
    And I select a random product from horizontal recommendation panel on master PDP iship mode
    Then I verify navigation to the corresponding PDP iship mode

#  registry
  @dsv_desktop_sev2 @use_regression @pros @domain_selection
  Scenario: Verify product description functionality in vertical recommendation panel on PDP page in Registry mode
    Given I visit the web site as a guest user in "registry" mode
    When I pros user navigate PDP page that has recommendations in "vertical" panel in "registry" mode
    Then I should see "vertical" recommendation panel on pdp page
    And I select a random product from vertical recommendation panel on member PDP registry mode
    Then I verify navigation to the corresponding PDP registry mode
