## Project: Returns-MEW
## EPIC: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story%3A3865289&RoomContext=TeamRoom%3A3222872
### Date updated: 23rd Oct 2017

Feature: As a mobile customer on the Return Method Page, I would like to see text showing me which step I am on in the return flow,
  buttons to select the return method (Macy’s store dropoff, UPS dropoff and Return Pickup) with associated verbiage, and ‘continue’, 'back',and ‘cancel’ buttons.
  After selecting the return method, the customer will be able to return to the previous page (ie. refund method page) by pressing the back arrow.

  @B-77471 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify Macy's Dropoff,UPS Dropoff and Return Pickup options are are displayed on Return Method Selection page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    And I should see all the return options on page
    And I should see "store drop" button as pre-selected by default
    #Note: Return options are "Macy's Dropoff, UPS Dropoff, Return Pickup"
    Examples:
      | line_item_status            |
      | pickup_eligible_mixed_order |

  @B-77471 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify the verbiage for Macy's Dropoff on Return Method Selection page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    And I should see "store drop" button as pre-selected by default
    And I should see "store drop" verbiage as below
      | Please print out your emailed return slip or have it available on your smart phone to speed up your return in store. |
    Examples:
      | line_item_status |
      | delivered        |

  @B-77471 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify the verbiage for 'UPS Dropoff' on Return Method Selection page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    And I should see "ups drop box" button on return method page
    When I select "ups drop box" as return method
    And I should see "ups drop box" verbiage as below
      | attach the return label to the outside of the package and drop it off at any UPS store, Drop Box, or Customer Center. |
    Examples:
      | line_item_status |
      | delivered        |

  @B-77882 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify associated verbiage for return pickup on return method page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    When I select "return pickup" as return method
    And I should see "return pickup" verbiage as below
      | We can pick up your return from the shipping address below.$6.95 fee applies.Signature required at pickup. Please note: At this time, pickups cannot be rescheduled or canceled. |
 #Note:   '$6.95 fee applies' text should be bold
    Examples:
      | line_item_status            |
      | pickup_eligible_mixed_order |

  @B-77882 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify customer pickup address and alternate address link for return pickup on return method page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    When I select "return pickup" as return method
    Then I should see shipping address as pickup address by default
    And I should see alternate address link
    Examples:
      | line_item_status            |
      | pickup_eligible_mixed_order |

  @B-77882 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify day,time and special instructions field are displayed for return pickup on return method page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    When I select "return pickup" as return method
    Then I should see "pickup day" field on return method page
    And I should see "pickup time" field on return method page
    And I should see "special instructions" field on return method page
    Examples:
      | line_item_status            |
      | pickup_eligible_mixed_order |

  @B-94361 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: verify that special instructions field is showing remaining characters count while entering into the special instructions field
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    When I select "return pickup" as return method
    And I should see "special instructions" field on return method page
    And I should see remaining characters as 200 below special instructions field
    When I should be able to enter "Test@123()" characters in special instructions field
    Then I should see remaining characters as 190 below special instructions field
    When I enter 200 characters in special instructions field
      | Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345Test@12345 |
    Then I should see remaining characters as 0 below special instructions field
    And I should not be able to enter few characters in special instructions field
    Examples:
      | line_item_status            |
      | pickup_eligible_mixed_order |

  @B-91613 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user navigating back to refund method page when user cancel his refund
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    And I select "cancel" button on page
    Then I should see popup with yes or no buttons with below text
      | Cancel Return?                               |
      | Are you sure you want to cancel this return? |
    When I tap on "yes" button on cancel popup
    Then I should be navigated to order history page
    Examples:
      | line_item_status |
      | delivered        |

  @B-91613 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify user on refund method page only when user select no to cancel his refund
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    And I select "cancel" button on page
    Then I should see popup with yes or no buttons with below text
      | Cancel Return?                               |
      | Are you sure you want to cancel this return? |
    When I tap on "no" button on cancel popup
    Then I should be on "return method" page
    Examples:
      | line_item_status |
      | delivered        |

  @B-91617 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify able to navigate back to refund method page by clicking on back arrow on return method page
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    When I tap on back on "return method" page
    Then I should navigate to "refund method" page
    Examples:
      | line_item_status |
      | delivered        |

  @B-92832 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify error message on refund method page while user tried to continue without selecting mandatory fields(Date and Time)
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    And I select "return pickup" as return method
    When I select "continue" button on page
    Then I should see below error message
      | Please complete all fields to continue. |
    Examples:
      | line_item_status            |
      | pickup_eligible_mixed_order |


  @B-96431 @release_17U @domain_customer_management @project_return_management @use_project
  Scenario Outline: Time and Date information should be persisted in pickup flow even when a different return method is selected
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    And I navigate to order history page on mobile
    When I expand order by tapping on "<line_item_status>" order container
    And I select return items button in mobile "OH" page
    Then I should navigate to "return selection" page
    And I select items "1" to return on page
    When I select "continue" button on page
    Then I should navigate to "refund method" page
    When I select "continue" button on page
    Then I should navigate to "return method" page
    And I select "return pickup" as return method
    And I select date "2018-01-02" in return pickup
    And I select time "9AM - 12PM" in return pickup
    And I should be able to enter "Test@123()" characters in special instructions field
    And I select "store drop" as return method
    And I select "return pickup" as return method
    Then I should see date selected as "Friday, December 29, 2017" in return pickup
    Then I should see time selected as "9AM - 12PM" in return pickup
    Then I shoudd see "Test@123()" in the special instruction field
    Examples:
      | line_item_status            |
      | pickup_eligible_mixed_order |