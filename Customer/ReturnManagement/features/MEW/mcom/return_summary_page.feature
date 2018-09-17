# Project: Returns-MEW
# EPIC: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story%3A3270419&RoomContext=TeamRoom%3A3222872
# Date created: 7th Nov 2017
# Story: B-77901

Feature: As a mobile customer, after I complete the Return Method page and am taken to the Return Summary Page, I would like to see the items being returned, the unit price, the quantity of each, the subtotal, the merchandise total, taxes, any fees deducted from refund, and the total estimated credit.

  @B-77901 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify short product description for each item and verbiage(eg.Step 4 of 4 Confirm Return) on the top of the Return summary Page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    Then I should see "summary page" page title verbiage as below
      | Step 4 of 4: Submit Return |
    And I should see short description for each item
    Examples:
      | line_item_status            | refund_method | return_method |
      | pickup_eligible_mixed_order | gift card     | ups drop box  |

  @B-77901 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify the merchandise total, tax, any applicable fees deducted from the credit (ie. Return pickup fee), the total estimated credit, refund payment type and return method are displayed.
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    Then I should be on "return summary" page
    And I should see "merchandise total" on summary page
    And I should see "tax" on summary page
    And I should see "return delivery fee" on summary page
    And I should see "total estimated credit" on summary page
    And I should see refund payment type as "macys gift card" on summary page
    And I should see return method as "macys store dropoff" on summary page
    Examples:
      | line_item_status            | refund_method | return_method |
      | pickup_eligible_mixed_order | gift card     | ups drop box  |

  @B-77901 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user navigating back to order details page when user cancel his return.
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    Then I should be on "return summary" page
    And I select "cancel" button on page
    And I tap on "yes" button on cancel popup
    Then I should be navigated to order history page
    Examples:
      | line_item_status            | refund_method   | return_method |
      | pickup_eligible_mixed_order | original tender | ups drop box  |

  @B-77901 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user on return summary page only when user select no to cancel his return.
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    Then I should be on "return summary" page
    And I select "cancel" button on page
    And I tap on "no" button on cancel popup
    Then I should see cancel button popup is disappeared
    And I should be on "return summary" page
    Examples:
      | line_item_status            | refund_method   | return_method |
      | pickup_eligible_mixed_order | original tender | ups drop box  |

  @B-77901 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user able navigating to return confirmation page after clicked on 'continue' button on rerurn summary page.
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    Then I should be on "return summary" page
    When I select "submit" button on page
    Then I should navigate to "return confirmation" page
    Examples:
      | line_item_status            | refund_method | return_method |
      | pickup_eligible_mixed_order | gift card     | ups drop box  |

  @B-77901 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user able to navigating back return method page after clicking on 'back' arrow on return summary page.
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    Then I should be on "return summary" page
    When I tap on back on "return summary" page
    Then I should navigate to "return method" page
    Examples:
      | line_item_status            | refund_method | return_method |
      | pickup_eligible_mixed_order | gift card     | ups drop box  |
