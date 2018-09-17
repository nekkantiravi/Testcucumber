#---------------------------------------------------
# Brand         : MCOM Responsive
# Author        : Ejay Landicho
# Date Created	: Dec.13,2017
#---------------------------------------------------

Feature: Adding to List from Bag for Responsive List Page

  @responsive_list @domain_selection @project_mcom @t
  Scenario Outline: As a user, I should be able to add a product from bag to my list
    Given I visit the web site as a <user_type> user
    And I set cookie for SSC to see responsive experience
    When I select a "member" product and navigate to PDP in "site" mode
    And I add product to my bag from standard PDP Page
    When I  click on continue checkout button on shoppping bag page
    Then I should see Shopping Bag Page
    And I move my item from bag to list
    And I select wishlist link in header
    And I click on default list
    And I verify the basic components on the page for "<user_page>" user
    And I should see "1" products in the responsive list page
    Examples:
      |user_type  | user_page |
      |guest      | Guest     |
      |registered | SignedIn  |