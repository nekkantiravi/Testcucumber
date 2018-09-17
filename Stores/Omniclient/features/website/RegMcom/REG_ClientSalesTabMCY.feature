# Author: Claudiu Chirila
# Date Created:
# Date Signed Off:

Feature: As an associate, I validate that all page elements from Sales tab are properly displayed

  @RegMcom @domain_stores @omniclient @website
  Scenario: By Division and My Selling Area tabs are displayed in Sales page view
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I enter "6784746548" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    When I click on the searched client "TEST1 TEST ACCOUNT"
    And I navigate to Sales tab from Client Profile view
    Then By Division tab in displayed in Sales view
    And the My Selling Area tab is displayed in Sales view
    And the following columns are displayed in By Division tab from Sales view
      | Division  |
      | Net Sales |
      | Visits    |
    When I click on My Selling Are tab from Sales view
    Then the following columns are displayed in My Selling Are tab from Sales view
      | Department |
      | Net Sales  |
      | Visits     |


  @RegMcom @domain_stores @omniclient @website
  Scenario: Sales By Area section title and piechart are displayed
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as associate
    And I enter "6784746548" in the "phone" textbox
    And I select the Search button in Omniclient home screen
    When I click on the searched client "TEST1 TEST ACCOUNT"
    And I navigate to Sales tab from Client Profile view
    Then the Sales By Area title is displayed in the left side of Sales tab
    And the piechart is displayed in the left side of Sales tab

