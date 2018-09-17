###############################################
# Program:  <Program name>
# Project:  Tux Mensweare House
# Story:
# Author:   Team
# Date  :   March 03,2017
# Reviewer:
###############################################
Feature: Verification of Shopping bag features for MensWearHouse

  @scenario1 @use_wip @domain_customer @project_MCOM
  Scenario: Verify product name on shopping bag page for tuxedo items
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see tux items in shopping bag

  @scenario2 @use_wip @domain_customer @project_MCOM
  Scenario: Verify paypal button on shopping bag page when tux item present
    Given I visit the web site as a guest user
    And I create a new profile
    And I add a tuxedo product to bag
    When I navigate to shopping bag page
    Then I verify tux item added to bag
    And I "should not" paypal button in shopping bag page

  @scenario3 @use_wip @domain_customer @project_MCOM
  Scenario: Verify customer rental damage waiver fee field and help icon text message on shopping bag page
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on shopping bag page:
      | A $10 mandatory and nonrefundable Rental Damage Waiver Fee per look is automatically added to your rental. The fee covers the cost of minor rental repairs, as well as the costs associated with the rental's inspection, quality control and processing. If we determine that a rental is damaged beyond repair, or if a rental is not returned, you will also be charged the full replacement cost of the item(s). |

  @scenario4 @use_wip @domain_customer @project_MCOM  @passed
  Scenario: Verify user should be able to see relevant information about tuxedo reservation on shopping bag page
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should not see below buttons on shopping bag page for tuxedo product
      | .checkout_with_paypal |
      | .bops_shipping        |
      | .wish_list            |

  @scenario5 @use_wip @domain_customer @project_MCOM
  Scenario: Verify individual and total price of tuxedo item in the bag
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I verify individual and total price of tuxedo item in the shopping bag

  @scenario6 @use_wip @domain_customer @project_MCOM
  Scenario: Verify customer should be able to remove tux item from shopping bag
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I remove tux item from the shopping bag

  @scenario7 @use_wip @domain_customer @project_MCOM
  Scenario: Verify Error message for tux item in shopping bag page in I ship mode
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    When I navigate to international context page
    And I change country to "Australia"
    When I navigate to shopping bag page
    Then I verify below error message for tux item on shopping bag page in iship mode
      |We're sorry; the highlighted items are not available for shipping to Australia. Please remove the item(s) from your shopping bag to proceed.|

  @scenario8 @domain_customer @project_MCOM @use_wip @tmw_core_metrics
  Scenario: Verify links in tuxedo item on shopping bag page
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I verify below links for tuxedo item on shopping bag page
      |  item_names     |
      |  edit_this_look |
      |  product_image  |

  @scenario9 @use_wip @domain_customer @project_MCOM
  Scenario: Verify the bag changes when tux items in saved bag added to current bag
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    When I sign out from my current profile
    And I clear all the cookies
    And I goto Home page
    And I sign in to my existing profile
    And I navigate to shopping bag page
    Then I should see tux item in saved bag
    When I select add to current bag
    Then I should see tux item added to current bag

  @scenario10 @use_wip @domain_customer @project_MCOM
  Scenario: Verify product name, description and itemized list for tuxedo item on shopping bag page for tuxedo items
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I verify name and description for tuxedo item in shopping bag

  @scenraio11 @use_wip @domain_customer @project_MCOM
  Scenario: Verify charge time info for Tuxedo item
    Given I visit the web site as a registered user
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should be able to see "Your account will be billed when your rental items ship." as charge time info
    And I should verify tuxedo information

  @scenraio12 @use_wip @domain_customer @project_MCOM
  Scenario: Verify paypal button in shopping bag when tux items in saved bag added to current bag
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    When I sign out from my current profile
    And I clear all the cookies
    When I sign in to my existing profile
    And I add a random product to bag
    And I navigate to shopping bag page
    Then I "should see" paypal button in shopping bag page

  @scenario13 @use_wip @domain_customer @project_MCOM
  Scenario: Verify Estimated shipping value as free on shopping bag page for tux only item
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see shipping value as free on "order_total" section on "shopping_bag" page

  @scenario14 @use_wip @domain_customer @project_MCOM
  Scenario: Verify tux quantity when we have tux only item in shopping bag page
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see quantity Qty as one for the tux item on "shopping bag" page

  @scenario15 @use_wip @domain_customer @project_MCOM
  Scenario: Verify move to list for tux+macys item
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should not see move to list for "tux" item

  @scenario16 @use_wip @domain_customer @project_MCOM
  Scenario: Verify Estimated shipping value as free on shopping bag page for tux+macys item
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see shipping value as free on "order_total" section on "shopping_bag" page

  @scenario17 @use_wip @domain_customer @project_MCOM
  Scenario: Verify tux related changes with respect to bops item
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    When I add an "available_bops and orderable" product to my bag and select checkout
    And I select pick up option for bops item after selecting available store
    Then I verify tux item added to bag
    And I should see shipping value as free on "order_total" section on "shopping_bag" page
    And I should verify tuxedo information
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on shopping bag page:
      | A $10 mandatory and nonrefundable Rental Damage Waiver Fee per look is automatically added to your rental. The fee covers the cost of minor rental repairs, as well as the costs associated with the rental's inspection, quality control and processing. If we determine that a rental is damaged beyond repair, or if a rental is not returned, you will also be charged the full replacement cost of the item(s). |
    And I "should not" paypal button in shopping bag page

  @scenario18 @use_wip @domain_customer @project_MCOM
  Scenario: Verify paypal button on shopping bag page when tux item+ macys item present
    Given I visit the web site as a guest user
    And I create a new profile
    And I add a tuxedo product to bag
    And I add a random product to bag
    When I navigate to shopping bag page
    Then I verify tux item added to bag
    And I "should not" paypal button in shopping bag page

  @scenario19 @use_wip @domain_customer @project_MCOM
  Scenario: Verify by applying promocode in shoppingbag page for only tux item in bag
    Given I visit the web site as a guest user
    And I create a new profile
    And I add a tuxedo product to bag
    When I navigate to shopping bag page
    And I apply valid promo code "VALPAK10" using website
    Then I should see promo error message on the top of bag page
    And I should see promo inline error message

  @scenario20 @use_wip @domain_customer @project_MCOM
  Scenario: Verify by applying promocode in shoppingbag page for only tux+macys item in bag
    Given I visit the web site as a guest user
    And I create a new profile
    And I add a tuxedo product to bag
    And I directly add an available and orderable product "1310" to my bag
    And I apply valid promo code "VALPAK10" using website
    Then I should see promocode is successfully applied

  @scenario21 @use_wip @domain_customer @project_MCOM
  Scenario: verify GWP promotions for Tux item+macys item
    Given I visit the web site as a guest user
    And I create a new profile
    And I add a tuxedo product to bag
    And I navigate to PDP page of an "GWP" product
    And I verify the product details of an "GWP" product in PDP page
    When I navigate to shopping bag page
    Then I verify the basic attributes of shopping bag page for "GWP" product

  @scenario22 @use_wip @domain_customer @project_MCOM
  Scenario: verify PWP promotions for Tux item+macys item
    Given I visit the web site as a guest user
    And I create a new profile
    And I add a tuxedo product to bag
    And I navigate to PDP page of an "PWP" product
    And I add a random product to bag
    When I navigate to shopping bag page
    Then I verify the basic attributes of shopping bag page for "PWP" product

  @scenario23 @use_wip @domain_customer @project_MCOM
  Scenario: Verify MB Money changes in shopping bag only Tux item in bag in earn period
    Given I visit the web site as a guest user in mMoney earn period
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I should see MB Money earn logo in shopping bag page

  @scenario24 @use_wip @domain_customer @project_MCOM
  Scenario: Verify MB Money changes in shopping bag Macys  and Tux item in bag in earn period
    Given I visit the web site as a guest user in mMoney earn period
    And I create a new profile
    And I add a tuxedo product to bag
    And I add an "available and orderable" product to my bag
    When I navigate to shopping bag page
    Then I should see MB Money earn logo in shopping bag page

  @scenario25 @use_wip @domain_customer @project_MCOM
  Scenario: Verify MB Money changes in shopping bag only Tux item in bag in redeem period
    Given I visit the web site as a guest user in mMoney burn period
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I should see MB Money burn logo in shopping bag page

  @scenario26 @use_wip @domain_customer @project_MCOM
  Scenario: Verify MB Money changes in shopping bag Macys  and Tux item in bag in redeem period
    Given I visit the web site as a guest user in mMoney burn period
    And I create a new profile
    And I add a tuxedo product to bag
    And I add an "available and orderable" product to my bag
    When I navigate to shopping bag page
    Then I should see MB Money burn logo in shopping bag page

  @scenario27 @use_wip @domain_customer @project_MCOM
  Scenario Outline: Verify PROS panel in shopping bag page with respect to tux item
    Given I visit the web site as a guest user in "<root>" mode
    And I create a new profile
    And I add a tuxedo product to bag
    When I navigate PDP page have PROS panel with DCA cookie as DAL
    And I navigate to shopping bag page
    Then I should see "vertical" recommendation panel on shopping_bag page
    Examples:
      |root        |
      |domestic    |
      |iship    |

  @scenario28 @use_wip @domain_customer @project_MCOM
  Scenario Outline: Verify PROS panel in shopping bag page with respect to tux and macys items in bag
    Given I visit the web site as a guest user in "<root>" mode
    And I create a new profile
    And I add a tuxedo product to bag
    And I add an "available and orderable" product to my bag
    When I navigate PDP page have PROS panel with DCA cookie as DAL
    And I navigate to shopping bag page
    Then I should see "vertical" recommendation panel on shopping_bag page
    Examples:
      |root        |
      |domestic    |
      |iship    |

  @scenario29 @use_wip @domain_customer @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify the Recently viewed items in Shopping bag page for only tux item in bag
    Given I visit the web site as a guest user in "<mode_name>" mode
    And I create a new profile
    And I add a tuxedo product to bag
    When I search for "<Search_cat>"
    When I visit "8" random items
    When I navigate to shopping bag page
    Then I verify 6 Recent products displayed at one time in RVI panel
    And I should see the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I should move towards next set of products
    When I click on "left" arrow key inside RVI
    Then I should move towards previous set of products
    When I click "edit" button in rvi panel
    And I remove and verify the item is removed from RVI panel
    Examples:
      |mode_name|Search_cat|
      |domestic |Jeans     |
      |iship    |Hobos     |

  @scenario30 @use_wip @domain_customer @artifact_navapp @project_siteperformance @feature_rvi
  Scenario Outline: Verify the Recently viewed items in Shopping bag page for macys and tux item in bag
    Given I visit the web site as a guest user in "<mode_name>" mode
    And I create a new profile
    And I add a tuxedo product to bag
    And I add an "available and orderable" product to my bag
    When I search for "<Search_cat>"
    When I visit "8" random items
    When I navigate to shopping bag page
    Then I verify 6 Recent products displayed at one time in RVI panel
    And I should see the navigation arrow buttons
    When I click on "right" arrow key inside RVI
    Then I should move towards next set of products
    When I click on "left" arrow key inside RVI
    Then I should move towards previous set of products
    When I click "edit" button in rvi panel
    And I remove and verify the item is removed from RVI panel
    Examples:
      |mode_name|Search_cat|
      |domestic |Jeans     |
      |iship    |Hobos     |

  @scenario31 @use_wip @domain_customer @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify items in this look for tux item in shopping bag
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see items in this look for tux item

  @scenario32 @use_wip @domain_customer @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify items in this look for tux +macys item in shopping bag
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see items in this look for tux item

  @scenario33 @use_wip @domain_customer @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the error message for tuxedo item when event date is less than 15 days on shopping bag page
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I change even date to current date
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see below error message for tuxedo item placed less than 15 days before event list on shopping bag page
      |We're sorry, but rental orders must be placed online at least 15 days prior to the event date. You may still have time if you book in store (rush fees may apply). For information on rush orders or to make a change to your reservation details, please call 1-844-MCYS-TUX.| |

  @scenario34 @use_wip @domain_customer @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify the error message for tuxedo item when event date is less than 15 days on shopping bag page for mixed bag
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I change even date to current date
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see below error message for tuxedo item placed less than 15 days before event list on shopping bag page
      |We're sorry, but rental orders must be placed online at least 15 days prior to the event date. You may still have time if you book in store (rush fees may apply). For information on rush orders or to make a change to your reservation details, please call 1-844-MCYS-TUX.| |

  @scenario35 @use_wip @domain_customer @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify changes in shopping bag when TuxedoRentalEnabled kill switch is disabled for tux item
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item is not displaying in shopping bag page
    And I verify rental damage wavier fee not displayed in shopping bag page

  @scenario36 @use_wip @domain_customer @artifact_navapp @project_siteperformance @feature_rvi
  Scenario: Verify changes in shopping bag when TuxedoRentalEnabled kill switch is disabled for mixed bag
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    Then I verify tux item is not displaying in shopping bag page
    And I verify rental damage wavier fee not displayed in shopping bag page

  @scenario37 @use_wip @domain_customer @project_MCOM
  Scenario: Verify pickup option not displayed for tuxedo items
    Given I visit the web site as a guest user
    And I create a new profile
    When I add a tuxedo product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see tux items in shopping bag
    And I should not see pick up option for tux item in shopping bag page

  @scenario38 @use_wip @domain_customer @project_MCOM
  Scenario: Verify customer rental damage waiver fee field and help icon text message on shopping bag page for mixed bag
    Given I visit the web site as a guest user
    And I create a new profile
    Then I should be navigated to My Account Page
    When I add a tuxedo product to bag
    And I add a random product to bag
    And I navigate to shopping bag page
    Then I verify tux item added to bag
    And I should see "rental damage waiver fee" in the order total section of tuxedo reservation on shopping bag page:
      | A $10 mandatory and nonrefundable Rental Damage Waiver Fee per look is automatically added to your rental. The fee covers the cost of minor rental repairs, as well as the costs associated with the rental's inspection, quality control and processing. If we determine that a rental is damaged beyond repair, or if a rental is not returned, you will also be charged the full replacement cost of the item(s). |
