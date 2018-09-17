### Project: MEW Returns
### EPIC: https://www14.v1host.com/Macyscom/Epic.mvc/Summary?oidToken=Epic:3243793
### Date updated: 4th Dec 2017

Feature:  As a mobile customer,
  after I view the Return Selection page and am taken to the Refund Method Page,
  I would like to see text showing me which step I am on in the return flow,
  buttons to select the refund method (either gift card or refund to original form of tender with associated verbiage)
  and ‘continue’, 'back', and ‘cancel’ buttons.

  @B-77284 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify Original form of payment refund method and its verbiage in returns refund method page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    And I should see "original tender" verbiage as it was selected by default
    And I should see "original tender" verbiage as below
      | within 3–5 business days of receiving your return. It may take several days for your issuer to post activity to your account. |
    Examples:
      | line_item_status               |
      | shipping_and_billing_different |

  @B-77284 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user able to navigating to Return Method page by selecting continue button on Refund method page for bill-to/ship-to address matching of order
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When  I select "continue" button on page
    Then I should navigate to "return method" page
    Examples:
      | line_item_status                                 |
      | shipping_and_billing_address_same_for_creditcard |


  @B-77284 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user navigating to Return Method page by selecting continue for bill-to/ship-to address not matching of order
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    And I should see 'Gift Card' and 'Original Form of Payment' buttons are displayed
    And I should see "original tender" verbiage as it was selected by default
    When I select "continue" button on page
    Then I should navigate to "return method" page
    Examples:
      | line_item_status               |
      | shipping_and_billing_different |

  @B-77284 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user navigating to Return Method page by selecting continue for bill-to/ship-to address not matching of order by changing refund method option
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "gift card" as refund method
    Then I should see "gift card" verbiage as below
      | You have chosen a refund by Macy's Gift Card. |
    When I select "continue" button on page
    Then I should navigate to "return method" page
    Examples:
      | line_item_status               |
      | shipping_and_billing_different |

  @B-90718 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify cancel model display on refund method page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    And I select "cancel" button on page
    Then I should see popup with yes or no buttons with below text
      | Cancel Return?                               |
      | Are you sure you want to cancel this return? |
    Examples:
      | line_item_status |
      | return           |

  @B-90718 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: : Verify user is on refund method page upon selection no option on cancel model in refund method page cancel popup
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    And I select "cancel" button on page
    And I tap on "no" button on cancel popup
    Then I should see cancel button popup is disappeared
    And I should be on "refund method" page
    Examples:
      | line_item_status |
      | return           |

  @B-90718 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user is on refund method page upon selecting yes option on cancel model in refund method page cancel popup
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    And I select "cancel" button on page
    And I tap on "yes" button on cancel popup
    Then I should be navigated to order history page
    Examples:
      | line_item_status |
      | return           |

  @B-92647 @release_17X @domain_customer_management @project_return_management @use_project @execute
  Scenario Outline: Verify user able choose payment option for return items when shipping address and billing address is different
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    And I should see original payment and gift card options
    And I should see default payment option as original payment
    Examples:
      | line_item_status               |
      | shipping_and_billing_different |

  @B-92647 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user able choose payment option for return items when shipping address and billing address is same
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    And I should see original payment option only
    Examples:
      | line_item_status                                 |
      | shipping_and_billing_address_same_for_creditcard |

