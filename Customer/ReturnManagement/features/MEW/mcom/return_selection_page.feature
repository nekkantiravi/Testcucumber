### Project: MEW Returns
### EPIC: https://www14.v1host.com/Macyscom/Epic.mvc/Summary?oidToken=Epic:3243793
### Date updated: 23rd Oct 2017

Feature: Verify return selection page features on MEW


  @B-88080 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user able navigate to refund method pages by selecting continue button
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    Examples:
      | line_item_status |
      | delivered        |

  @B-88080 @B-88081 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify line items on return selection page by default
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    Then I should see unselected line item reason for return as default
    And I should see unselected line item quantity as one
    Examples:
      | line_item_status |
      | delivered        |

  @B-88082 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify cancel model display on selection page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    When I select "cancel" button on page
    Then I should see popup with yes or no buttons with below text
      | Cancel Return?                               |
      | Are you sure you want to cancel this return? |
    Examples:
      | line_item_status |
      | delivered        |

  @B-88082 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user is on return selection page upon selection no option on cancel model in selection page cancel popup
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    When I select "cancel" button on page
    And I tap on "no" button on cancel popup
    Then I should see cancel button popup is disappeared
    And I should be on "return selection" page
    Examples:
      | line_item_status |
      | delivered        |

  @B-88082 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user is on return selection page upon selecting yes option on cancel model in selection page cancel popup
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    When I select "cancel" button on page
    And I tap on "yes" button on cancel popup
    Then I should be navigated to order history page
    Examples:
      | line_item_status |
      | delivered        |

  @B-88083 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify error message on selection page by tapping on continue button without selecting any item
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select checkbox to return item
    When I select "continue" button on page
    Then I should see below error message
      | Please complete all fields to continue. |
    Examples:
      | line_item_status |
      | delivered        |

  @B-77281 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify reason for return and quantity fields are populating to default values upon deselecting line item to return
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select checkbox to return item
    When I select reason for return
    And I select checkbox to return item again
    Then I should see reason for return as default one
    Examples:
      | line_item_status |
      | delivered        |

  @B-95164 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario: Verify weather global error message and field errors are disappeared when reason for return is selected.
    Given I visit the mobile web site as a guest user
    And I associate "delivered" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "delivered" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select checkbox to return item
    When I select "continue" button on page
    Then I should see below error messages
      | global | Please complete all fields to continue. |
      | field  | Please select a reason above.           |
    When I select reason for return
    Then I should see global error message and field error disappeared
    When I select "continue" button on page
    Then I should navigate to "refund method" page


