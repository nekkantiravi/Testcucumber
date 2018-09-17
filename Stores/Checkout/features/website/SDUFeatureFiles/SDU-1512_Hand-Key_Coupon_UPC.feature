#Author: Stores Domain Checkout Team
    #Story: SDU-1664 - Checkout ::  Hand Key a Coupon
    #Date Created: 10/01/2018
    #Date Signed Off:

@domain_stores @project_checkout @release_26 @story_SDU-1512
Feature: As an associate, I want the ability to hand key a physical coupon, so that I can continue to service my customer when a barcode is unable to be scanned.

  @Macy's @Send
  Scenario Outline: Macy's - A toast message should display when a UPC or coupon was scanned.
    Given I am on "Macy's Sales Trans"
    When I click on the bag icon
    Then I hand key a "<UPC>"
    And I see the CRL Overlay
    And I close the CRL Overlay
    Then I hand key a "<COUPON>"
    Then the coupon added message should be displayed
    And the coupon number should be displayed
    When I click on Hamburger icon
    And I click on Cancel Transaction button
    Then I am on "Add Product" page

    Examples:
      | UPC         | COUPON               |
      | 20714001940 | 00000000000300010006 |


  @Macy's @Send
  Scenario Outline: Macy's - When associate tries to hand key a promo code, the invalid UPC error message is displayed
    Given I am on "Macy's Sales Trans"
    When I click on the bag icon
    Then I hand key a "<PROMO_CODE>"
    And I see the swipe prompt message
    And I see the invalid UPC error message


    Examples:
      | PROMO_CODE   |
      | PROMO20      |
      | PROMO3253236 |
 