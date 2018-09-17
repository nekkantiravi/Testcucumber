# Author: SST Regression Team
# Date Created: 11/21/2013
# Date Modified: 12/23/2013
# Date Signed Off:

Feature: Verify Order statistics details

  @sst
  Scenario: Verify Order statistics details in Order Statistics Information page
    Given I visit the csu as a valid user
    When I navigate to the "Order Statistics" page in csu section
    And I should see Order Statistics Information page after selecting search button
      | Largest Order | Top 10 Products by Volume | Top 10 Products by Price | Top 5 Billto States by Order Volume | Top 5 Billto States by Order Price |
    # Notes:
    # Verify following details are displayed in Order statistics page
    #-Total Number Of orders,count
    #-Largest Order
    #-Top 10 Products by Volume
    #-Top 10 Products by Price
    #-Top 5 Billto States by Order Volume
    #-Top 5 Billto States by Order Price