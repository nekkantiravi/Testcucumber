#Author: UFT team
#Date Created: 09/25/2017
#Date Signed Off:
#Version One Card: B-89741

Feature: As a production Support developer, I would like to capture Order total in order table

  @release_17T @artifact_shopapp @medium @domain_purchase_and_delivery @project_UFT
  Scenario: Verify new column order total is added in order table to capture order total, user placed order as a guest
    Given I visit the web site as a guest user
    When I add an "available and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as a "guest" user
    Then I should see order total amount in order table is similar to order confirmation page value
    # Notes:
    # Check behavior for all tender types and with different card items
    # Check for Batch orders too

  @release_17T @artifact_shopapp @medium @domain_purchase_and_delivery @project_UFT
  Scenario: Verify new column order total is added in order table to capture order total, user placed order as a sign in user
    Given I visit the web site as a signed in user
    When I add an "available and orderable" product to my bag
    And I checkout until I reach the order confirmation page as a "signed in" user
    Then I should see order total amount in order table is similar to order confirmation page value
    # Notes:
    # Check behavior for all tender types and with different card items
    # Check for Batch orders too