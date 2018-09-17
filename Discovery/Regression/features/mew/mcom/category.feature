Feature: Verify categories functionality

  @preview_mew
  Scenario: Verify category spelling and link for Shop Menu
    Given I visit the mobile web site as a guest user in domestic mode
    Then I verify following categories or options from global navigation
      | Women                    |
      | Mens                     |
      | Kids & Baby              |
      | Shoes                    |
      | For The Home             |
      | Beauty                   |
      | Handbags & Sunglasses    |
      | Jewelry & Watches        |
      | Lingerie & Shapewear     |
      | Plus & Petite            |
      | Juniors & Guys           |
      | Furniture & Mattresses   |
      | Gift Guides & Gift Cards |
      | Wedding Registry         |
      | Active & Wellness        |


