Feature: Relocate utag_data object to right before tag.js

  @Tealium
  Scenario Outline: Verify the utag_data variable as blank in below mentioned page.
    Given I visit the web site as a guest user
    When I add "<url>" to the current url
    Then I should see utag_data as blank.
    Examples:
      |url |
      | shop/womens-clothing?id=118 |
      | shop/womens-clothing/dresses?id=5449 |
      | shop/product/style-co.-sleeveless-a-line-swing-dress-only-at-macys?ID=2821269&CategoryID=5449|
