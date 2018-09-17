#Mobile Lean Lab BCOM
#Created: 8/21/2017
#Author: Maria Ortega
#Version-one Story: B-89237

Feature: BCOM::Unavailable PDP

  Background:
    Given I visit any random unavailable pdp using mobile website
      |/shop/product/true-religion-jeans-stella-low-rise-skinny-in-lonestar?ID=769258|
      |/shop/product/aqua-lace-cami-dress?ID=1705763|
      |/shop/product/bcbgmaxazria-top-abrielle-peplum?ID=1182145|
      |/shop/product/paul-smith-naked-lady-bifold-wallet?ID=1498595|
      |/shop/product/ugg-lodge-lace-up-cold-weather-booties?ID=1398148|
      |/shop/product/mcm-tote-shopper-project-visetos-reversible?ID=1323507|
      |/shop/product/jonathan-adler-mykonos-beach-towel?ID=1054730|
      |/shop/product/woolrich-beaker-rex-rabbit-fur-trim-parka?ID=1450868|
      |/shop/product/alex-ani-monopoly-cat-expandable-wire-bangle?ID=1120662|
      |/shop/product/theory-tralsmin-suede-jacket?ID=1748021|
      |/shop/product/tory-burch-miller-cork-sandals?ID=1561033|
      |/shop/product/nike-dunk-sky-hi-wedge-sneakers?ID=1516475|

  Scenario: Verify that not available banner displays on unavailable items
    Then I should be able to see the not available banner

  Scenario: Verify that Product details accordion can be opened and it contains the product id
    When I click on the product accordion button
    Then I should be able to see product id

  Scenario: Verify that Reviews accordion can be expanded and Write a review button display
    When I click on the review accordion button
    Then I should be able to write a review

  Scenario: Verify that link "shop similar items" navigates to shop search results
    When I click on shop similar items button
    Then I should be navigated to search results page

  Scenario: Verify that if product image is present then brand name is displayed in shop all button
    When I see an image displayed on PDP
    Then I verify that shop all button contains the brand name
    And I verify that the button will take me to the search page