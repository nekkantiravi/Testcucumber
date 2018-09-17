#---------------------------------------------------
# Brand         : MCOM
# Author        : Bruce Shad
# Date Created	: Aug.09,2017
#---------------------------------------------------

Feature: QuickLook Validation & Verification

  @use_regression @domain_selection @mcom_selection_pdp @mcom_selection_ql
  Scenario: Verify basic elements of QuickLook on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" site mode
    Then I verify the basic elements of QuickLook overlay on "PDP" site mode
    And I click ProductDetails button on QuickLook overlay on "Master PDP" site mode
    Then I verify navigation to member PDP in site mode


  @use_regression @domain_selection @mcom_selection_pdp @mcom_selection_ql
  Scenario: Verify basic elements of QuickLook on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    Then I select a "master" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" iship mode
    Then I verify the basic elements of QuickLook overlay on "PDP" iship mode
    And I click ProductDetails button on QuickLook overlay on "Master PDP" iship mode
    Then I verify navigation to member PDP in iship mode


  @use_regression @domain_selection @mcom_selection_pdp @mcom_selection_ql
  Scenario: Verify basic elements of QuickLook on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    Then I select a "master" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" registry mode
    Then I verify the basic elements of QuickLook overlay on "PDP" registry mode
    And I click ProductDetails button on QuickLook overlay on "Master PDP" registry mode
    Then I verify navigation to member PDP in registry mode


  @use_regression @domain_selection @mcom_selection_pdp @mcom_selection_ql
  Scenario: Verify A2B functionality for QuickLook on master PDP Site mode
    Given I visit the web site as a guest user
    When I select a "master" product and navigate to PDP in "site" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" site mode
    And I click A2B button on QuickLook overlay on "Master PDP" site mode
    And I click Checkout button on QuickLook overlay on "Master PDP" site mode
    Then I verify basic elements of shopping bag page in "site" mode
#    And I verify the productId being the same as added to bag


  @use_regression @domain_selection @mcom_selection_pdp @mcom_selection_ql
  Scenario: Verify A2B functionality for QuickLook on master PDP iShip mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    And I change country to "Sweden"
    And I close the welcome mat if it's visible
    And I select a "master" product and navigate to PDP in "iship" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" iship mode
    And I click A2B button on QuickLook overlay on "Master PDP" iship mode
    And I click Checkout button on QuickLook overlay on "Master PDP" iship mode
    Then I verify basic elements of shopping bag page in "iship" mode


  @use_regression @domain_selection @mcom_selection_pdp @mcom_selection_ql
  Scenario: Verify A2B functionality for QuickLook on master PDP Registry mode
    Given I visit the web site as a guest user
    When I navigate to registry mode
    And I select a "master" product and navigate to PDP in "registry" mode
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" registry mode
    And I click A2B button on QuickLook overlay on "Master PDP" registry mode
    Then I verify that guest user is navigated to "Registry" signin page

#Author: UFT team
# Feature: As a product owner, I would like to fix an issue where member items image-link is disabled and not working as
# expected, When clicking on corresponding member image from master PDP.
#Version One:B-101778
  @use_regression @domain_selection @mcom_selection_pdp @mcom_selection_ql
  Scenario: Verify QV is displayed when clicking on quickview button on member image in master PDP page for BigTicket items
    Given I visit the web site as a guest user
    When I navigate to "BT_collection_master and orderable" product PDP page
    And I scroll upDown for lazyLoad elements to load on PDP
    And I hover over a random product thumbnail and click on the QuickLook button on "PDP" site mode
    Then I verify the basic elements of QuickLook overlay on "PDP" site mode
    And I click ProductDetails button on QuickLook overlay on "Master PDP" site mode
    Then I verify navigation to member PDP in site mode
