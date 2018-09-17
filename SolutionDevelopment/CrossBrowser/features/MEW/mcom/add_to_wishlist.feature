Feature: Chanel product - Verify the functionality for add to list

  @scenario1 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario Outline: As a user, I should add CHANEL product to wishlist from PDP Page
    Given I visit the mobile web site as a <user_type> user without add CC
    When I search for "chanel beauty"
    And I select a random member product using mobile website
    And I select product related attributes from PDP using mobile website
    And I click Add to Wish List button on PDP using mobile website
    And I click on view list in ATW overlay from PDP using mobile website
    Then I add product to my bag from wishlist page using mobile website and checkout

    Examples:
      | user_type  |
      | guest      |
      | registered |

  @scenario2 @xbrowser_tablet @domain_selection @xbrowser_mew
  Scenario Outline: Verify that the list appears for both Guest and Signed in user
    Given I visit the mobile web site as a <user_type> user without add CC
    When I navigate to "available and orderable and member_product" product PDP page
    And I select product related attributes from PDP using mobile website
    And I click Add to Wish List button on PDP using mobile website
    And I navigate back to "home" page using mobile website
    And I navigate the global navigation menu as follows:
      | Lists |
    Then I should see following elements on "wish_list" page:
      | wishlist_title  |
      | manage_list     |
      | add_to_bag_btn  |
      | item_links      |
      | list_item_count |

    Examples:
      | user_type  |
      | guest      |
      | registered |
