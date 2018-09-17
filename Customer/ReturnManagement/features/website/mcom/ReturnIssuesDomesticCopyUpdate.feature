#Author: UFT team
#Date Created: 07/06/2017
#Date Signed Off:
#Version One: B-83877

Feature: Return issues domestic copy update

  @artifact_shopapp @domain_customer @release_17N @mode_domestic @project_UFT
  Scenario: Verify new error message copy when user tries to return order as signed in user, order is actually placed as a guest
    Given I visit the web site as a signed in user
    When I navigate to order details page from footer
    And I select "intransit" order as a "guest" user
    And I lookup with order number and emailaddress in OH page
    And I select return items button on "OD" page
    Then I should see "Since you placed the order when you weren't signed in, we must have you sign out to process your return. Please sign out, then look up your order again to retrieve your order details or return the item(s)." return error message


