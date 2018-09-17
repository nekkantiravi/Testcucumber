Feature: Verify categories and ads on home page

  @scenario1 @xbrowser_mew @domain_marketing
  Scenario: Verify all categories have text and photo icons on home page
    Given I visit the mobile web site as a guest user
    Then I verify all categories have text and photo icons on home page

  @scenario2 @xbrowser_mew @domain_marketing
  Scenario: Verify bestsellers products are listed on home page, carousel is working and products are clickable
    Given I visit the mobile web site as a guest user
    Then I verify bestsellers products on home page using mobile website

  @scenario3 @xbrowser_mew @domain_marketing
  Scenario: Verify ads are clickable on home page
    Given I visit the mobile web site as a guest user
    Then I verify ads are clickable on home page

  @scenario4 @domain_marketing @xbrowser_mew
  Scenario: Footer appears on Home Page
    Given I visit the mobile web site as a guest user
    Then I should see the footer elements displayed at the bottom of the page
      | text              | element                |
      | Footer            | footer                 |
      | Signup            | goto_email_text_signup |
      | Privacy Practices | goto_privacy           |
      | Customer Service  | goto_contact_us        |
      | Desktop Version   | goto_full_site         |
      | Find A Store      | find_a_store           |
      | Pinterest         | goto_pinterest         |
      | Facebook          | goto_facebook          |
      | Twitter           | goto_twitter           |
      | Instagram         | goto_instagram         |
      | Youtube           | goto_youtube           |
      | Macys Blog        | goto_macys_blog        |
      | Copyright         | trademark              |

  @scenario5 @xbrowser_mew @domain_marketing
  Scenario: Verify user navigates to Find A Store Page from global navigation
    Given I visit the mobile web site as a guest user
    And I open the global navigation
    And I navigate on menu item "Stores"
    Then I should be navigated to Macys Store page
    And I verify the below search page elements
      | text          | element |
      | Find a Macy's Store | header  |

  @scenario6 @xbrowser_mew @domain_marketing
  Scenario: Verify "Deals and Promotions" button is clickable on home page
    Given I visit the mobile web site as a guest user
    When I click on "goto_deals_promotions" on "home" page
    Then I should be redirected to "deals_and_promotions" page

  @scenario7 @xbrowser_mew @domain_marketing
  Scenario: Verify "Find a Store" button is clickable on home page
    Given I visit the mobile web site as a guest user
    When I click on "goto_find_a_store" on "home" page
    Then I should be redirected to "stores" page

  @scenario8 @xbrowser_mew @domain_marketing
  Scenario: Verify Header and Footer appears on Home Page
    Given I visit the mobile web site as a guest user
    Then I should see following elements on "home" page:
    | header_container       |
    | header_image           |
    | my_bag                 |
    | global_nav_button      |
    | search_field           |
    | footer                 |
    | goto_sign_in_link      |
    | change_country_link    |
    | goto_email_text_signup |
    | goto_privacy           |
    | goto_contact_us        |
    | goto_full_site         |
    | find_a_store           |
    | goto_pinterest         |
    | goto_facebook          |
    | goto_twitter           |
    | goto_instagram         |
    | goto_youtube           |
    | goto_macys_blog        |
    | trademark              |

  @scenario9 @xbrowser_mew @domain_marketing
  Scenario: Verify user can navigate to My Bag page from Home page and items are viewable in bag
    Given I visit the mobile web site as a guest user
    When I add an "member_product" product to my bag using mobile website
    And I navigate back to "home" page using mobile website
    And I click on "my_bag" on "home" page
    Then I should be redirected to "shopping_bag" page
    And I should see following elements on "shopping_bag" page:
      | line_items  |
      | item_title  |
      | item_image  |
      | remove_item |

  @scenario10 @xbrowser_mew @domain_marketing
  Scenario: Verify Deals and Promotions page
    Given I visit the mobile web site as a guest user
    When I navigate the global navigation menu as follows:
      | Deals  |
    Then I should see "by category" sort option is selected on deals and promotions page
    And I should see following elements on "deals_and_promotions" page:
      | deals_and_promotions_items      |
      | category                        |
      | expiring_soon                   |
      | offer_category                  |
    When I click on "expiring_soon" on "deals_and_promotions" page
    Then I should see "expiring soon" sort option is selected on deals and promotions page
    And I should see following elements on "deals_and_promotions" page:
      | deals_and_promotions_items      |
      | category                        |
      | expiring_soon                   |
    When I click on "category" on "deals_and_promotions" page
    Then I should see "by category" sort option is selected on deals and promotions page
    And I click on "offer_heading" on "deals_and_promotions" page
    Then I should see following elements on "offer_details" panel:
      | header              |
      | offer_details_modal |
      | add_to_wallet or shop_now |
      | share_offer         |
      | details_exclusions  |
