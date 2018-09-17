# Author: SST Regression Team
# Date Created: 11/21/2013
# Date Modified: 12/23/2013
# Date Signed Off:

Feature: Verify Order Information Details

  #Test Case ID: MCOM-57747 , BLCOM-57099
  @sst
  Scenario: Verify Order Information and Order Details page
    Given I visit the csu as a valid user
    When I navigate to the "Order Information" page in csu section
    And I search orders by custom date in Order Information page
    Then I should see order information details
      | Customer Name | Order Number | Time Order Placed | Customer IP Address | # Days Card Known in Site |
    When I select any order from the orders list
    Then I should see the order details of corresponding order
    # Notes:
    # To get order details Select month, date and year under 'Select date to retrieve list of orders:', then click on go button
    # Verify order information display for selected date
    # Click on the order number to view the detailed information for that particular Order
    # In order details page
    # - Verify all the information about Order details are displayed
    # - Verify Card Number should be encrypted and last 4 digits of Card Number should be displayed

  #Test Case ID: MCOM-71607 , BLCOM-67510
  @sst
  Scenario Outline: Verify Order lookup functionality to allow filter based on different Order Status
    Given I visit the csu as a valid user
    When I navigate to the "Order Information" page in csu section
    And I search orders by custom date and <order status> in Order Information page
    Then I should see order information details
      | Customer Name | Order Number | Time Order Placed | Customer IP Address | # Days Card Known in Site |

  Examples:
    | order status                                     |
    | All Orders                                       |
    | All Domestic Orders                              |
    | Domestic - Partial Filled Orders (BFPRT)         |
    | All International Orders                         |
    | International - Partial Filled Orders (IS_BFPRT) |
    # Notes:
    # verify given order status is exists in Select order status drop down