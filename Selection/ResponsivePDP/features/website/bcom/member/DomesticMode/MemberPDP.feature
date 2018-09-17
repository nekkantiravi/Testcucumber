#---------------------------------------------------
# Brand         : BCOM
# Author        : Sankeerth Kakarna
# Date Created	: Dec.1,2017
#---------------------------------------------------

Feature: MemberDomesticPDP Validation & Verification

  Scenario Outline: Verify basic stuff
    Given I navigate directly to member PDP that is "available" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    Then I verify that I can open all accordions on member PDP on site mode on <device>
    And I verify that I can close all accordions on member PDP on site mode on <device>
    Examples:
      |device  |
      |desktop |
      |tablet  |
      |mobile  |

  Scenario Outline: Verify the elements of Product details accordion
    Given I navigate directly to member PDP that is "available" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    Then I verify that "product-details" accordion is open on member PDP site mode on <device>
    And I verify the elements of ProductDetails accordion with Xapi on member PDP site mode on <device>
    Then I verify the navigation of all links under ProductDetails section on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify the elements of Shipping&Returns accordion
    Given I navigate directly to member PDP that is "carpet" on site mode on <device>
    And I click on "shipping-returns" accordion on member PDP site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    Then I verify the elements of shipping-returns accordion with Xapi on member PDP site mode on <device>
    Then I verify the navigation of all links under shipping-returns section on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify social share icons on member PDP
    Given I navigate directly to member PDP that is "available" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    Then I verify the social share icons on member PDP on site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify that the color label text is updated once a color swatch is selected
    Given I navigate directly to member PDP that is "available and colors" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    Then I select random color on member PDP on site mode on <device>
    Then I verify that the color label text updates to the color selected on member PDP on site mode on <device>
    Then I verify that color swatches are displayed according to priceToColors in xapi on member PDP on site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify that no color swatches display if the item only has one color option(one UPC). We only display the color label.
    Given I navigate directly to member PDP that is "available and single_color" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    When I verify that the product has only one color and color swatches are not displayed on member PDP on site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify that when selected a color swatch, the price value matches the price in xapi
    Given I navigate directly to member PDP that is "available and colors" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    When I select all color swatches from all tiers on member PDP on site mode on <device>
    Then I verify product prices with xapi on member PDP on site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify that for a selected size, available colors diaplayed matches list of available colors in xapi
    Given I navigate directly to member PDP that is "available and sizes and colors" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    When I select all sizes on member PDP on site mode on <device>
    Then I verify that the available colors displayed matches the available colors in xapi on member PDP on site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify the functionality of back to top button
    Given I navigate directly to member PDP that is "available" on site mode on <device>
    When I scroll to bottom of the page
    And I select backtotop button
    Then I verify that I am navigated to the top of the page
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify beauty products
    Given I navigate directly to member PDP that is "available and beauty" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    Then I verify that the order of colors in the color picker dropdown match the order of colors in xapi on member PDP on site mode on <device>
    Then I verify that the color selected from color picker dropdown matches the color swatch selected on member PDP on site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify the zoomer & altimages on Member PDP Site mode
    Given I visit the bcom home page on <device> as a guest user
    Given I navigate directly to member PDP that is "available and altImages" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    And I verify altimages on pdp on member PDP site mode on <device>
    And I verify altimages on imageOverlay on member PDP site mode on <device>
    And I verify zoomer on imageOverlay on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify the Promo banners on Member PDP Site mode
    Given I navigate directly to member PDP that is "available and promos" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    Then I verify promo banner and promo overlay on member PDP site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |

  Scenario Outline: Verify availability message & functionality on Member PDP in Site mode
    Given I navigate directly to member PDP that is "available and checkout" on site mode on <device>
    Then I verify the basic elements on member PDP on site mode on <device> with Xapi
    Then I verify the "Available. Usually ships within x business days." message on member PDP on site mode on <device>
    Then I verify product prices with xapi on member PDP on site mode on <device>
    Then I select random quantity on member PDP on site mode on <device>
    And I click A2B button on member PDP on site mode on <device>
    Then I verify product details on AddToBag drawer as the "member" product being added in site mode on <device>
    Examples:
      |device  |
      |desktop |
      |mobile  |
      |tablet  |
