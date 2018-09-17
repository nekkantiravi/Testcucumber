###############################################
# Program:  <Program name>
# Project:  Credit & Customer Service (RENAMED)=> Customer Management
# Story: MCOM:   https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=15115
# Story: MCOM:   https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=15113
# Story: MCOM:   https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=15116
# Story: MCOM:   https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=15114
# Story: MCOM:   https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=14889
# Story: MCOM:   https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=14881
# Author: Chandrasekhar Chandragiri
# Date  : April 9th, 2015
# Last Updated: 23rd April 2015
# Reviewer: Stephen Goetz
###############################################


Feature: As a customer, I would like a more efficient way to replace items that I am returning
  so I dont have to hunt around your site.

  @release_15F @domain_customer_management @project_return_management @priority_medium @mode_domestic @use_regression @returns_mgt_6 @artifact_shopapp @feature_add_quick_view_mcom
  Scenario: Call to action is displayed on the Return Confirmation page for Single line items upon guest users selecting to print the shipping label
    Given I visit order history page as a guest user
    And I navigate to selection page using "single_line_item_and_upc_available_in_db_with_fcc_response" order as a "guest" user
    When I select "1" line item reason as "Size did not fit" in reason for return
    And I navigate to confirmation page by "without" pickup details
    #And I navigates to the order details page
    And I select "print mailing label" from the "confirmation" page
    Then I should see call to action as find the right size option under reason for return
    When I select call to action on page
    Then I should see quick view panel

  @release_15F @domain_customer_management @project_return_management @priority_medium @mode_domestic @use_regression @returns_mgt_6 @artifact_shopapp @feature_add_quick_view_mcom
  Scenario: Call to action is displayed on the Return Confirmation page for Multiple line items upon guest users selecting to print the shipping label
    Given I visit order history page as a guest user
    And I navigate to selection page using "multiple_line_items_and_upc_available_in_db_with_fcc_response" order as a "guest" user
    When I select "2" line item reason as "Size did not fit" in reason for return
    And I navigate to confirmation page by "without" pickup details
    And I navigates to the order details page
    And I select "print mailing label" from the "confirmation" page
    Then I should see call to action as find the right size option under reason for return
    When I select call to action on page
    Then I should see quick view panel

  @returns_regression @release_15F @domain_customer_management @project_return_management @priority_medium @mode_domestic @use_regression @returns_mgt_6 @artifact_shopapp @feature_add_quick_view_mcom @migrated_to_sdt
  Scenario: Call to action navigates to add to bag page on the Order Details page for Single line items upon guest users selecting to item to the bag
    Given I visit order history page as a guest user
    And I navigate to selection page using "submitted" order as a "guest" user
    When I select "1" line item reason as "Size did not fit" in reason for return
    And I navigate to confirmation page by "without" pickup details
    And I navigates to the order details page
    And I select "print mailing label" from the "order details" page
    Then I should see call to action as find the right size option under reason for return
    When I should be able to add items to bag on header

  @returns_regression @release_15F @domain_customer_management @project_return_management @priority_medium @mode_domestic @use_regression @returns_mgt_6 @artifact_shopapp @feature_add_quick_view_mcom @migrated_to_sdt
  Scenario: Call to action navigates to add to bag page on the Return Confirmation page for Single line items upon guest users selecting to item to the bag
    Given I visit order history page as a guest user
    And I navigate to selection page using "single_line_item_and_upc_available_in_db_with_fcc_response" order as a "guest" user
    When I select "1" line item reason as "Size did not fit" in reason for return
    And I navigate to confirmation page by "without" pickup details
    And I select "print mailing label" from the "confirmation" page
    Then I should see call to action as find the right size option under reason for return
    And I should be able to add items to bag on header

  @returns_regression @release_15F @domain_customer_management @project_return_management @priority_medium @mode_domestic @use_regression @returns_mgt_6 @artifact_shopapp @feature_add_quick_view_mcom @migrated_to_sdt
  Scenario: Call to action navigates to add to bag page on the Order Details page for Multiple line items upon guest users selecting to item to the bag
    Given I visit order history page as a guest user
    And I navigate to selection page using "single_line_item_and_upc_available_in_db_with_fcc_response" order as a "guest" user
    When I select "2" line item reason as "Size did not fit" in reason for return
    And I navigate to confirmation page by "without" pickup details
    And I navigates to the order details page
    And I select "print mailing label" from the "order details" page
    Then I should see call to action as find the right size option under reason for return
    And I should be able to add items to bag on header

  @returns_regression @release_15F @domain_customer_management @project_return_management @priority_medium @mode_domestic @use_regression @returns_mgt_6 @artifact_shopapp @feature_add_quick_view_mcom @migrated_to_sdt
  Scenario: Call to action navigates to add to bag page on the Return Confirmation page for Multiple line items upon guest users selecting to item to the bag
    Given I visit order history page as a guest user
    And I navigate to selection page using "multiple_line_items_and_upc_available_in_db_with_fcc_response" order as a "guest" user
    When I select "2" line item reason as "Size did not fit" in reason for return
    And I navigate to confirmation page by "without" pickup details
    And I select "print mailing label" from the "confirmation" page
    Then I should see call to action as find the right size option under reason for return
    And I should be able to add items to bag on header



