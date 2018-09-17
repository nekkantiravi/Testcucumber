#Date Created: 08/4/2015
#Date Signed Off:
# Version One URL: https://www14.v1host.com/Macyscom/Search.mvc/Advanced?q=B-26266

Feature: As a member of the support team, I would like to ensure the Fulfilment Inventory screen in MASS has a tab for displaying orders placed for the UPC
  given in input box within the time period interval provided as input.

  @sst
  Scenario: Verify that new tab is displayed for UPC orders
    Given I am on mass home page as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "Fulfillment inventory" page under Troubleshooting section
    Then I should see a new tab for UPC orders in fulfillment inventory page

  @sst
  Scenario: Verify that order details are displayed for a given UPC
    Given I am on mass home page as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "Fulfillment inventory" page under Troubleshooting section
    And I select "UPC Order" tab
    And I search with valid UPC details
    Then I should see that the order details are displayed for selected UPC
    And I should also see the order count displayed on the page matches with DB

  @sst
  Scenario Outline: Verify that system display proper error when Invalid entries are provided
    Given I am on mass home page as a valid user
    When I select "fccCellA" from SDP URL
    And I navigate to the "Fulfillment inventory" page under Troubleshooting section
    And I select "UPC Order" tab
    And I search with "<UPC>" upc and "<Time_Period>" time
    Then I should see that appropriate "<Error_Message>" is displayed

    Examples:
      | UPC     | Time_Period        | Error_Message                              |
      | invalid | valid              | Must be a long number                      |
      | valid   | invalid_start_date | Start date must not be in future           |
      | valid   | invalid_end_date   | End date must not be in future             |
      | valid   | start_date_greater | Start date must not be later than End date |

#  Note: Invalid time may include future dates or incorrect To and From dates