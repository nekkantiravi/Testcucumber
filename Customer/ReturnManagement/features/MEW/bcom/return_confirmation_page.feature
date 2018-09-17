# Project: Returns-MEW
## Story Link: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story%3A4013913&RoomContext=TeamRoom%3A3222872
## Story Link: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story%3A3270464
## Story Link: https://www14.v1host.com/Macyscom/story.mvc/Summary?oidToken=Story%3A4013969&RoomContext=TeamRoom%3A3222872
## Story Number: B-97240 , @B-97241 and B-77903

Feature:   As a mobile customer,after I leave the Return Summary page and am taken to the Return Confirmation Page,
  I would like see to a short text description informing me of the next steps
  I am to take in order to return the items and the email address to where the MRL was emailed,
  as well as a link to find the closest dropoff location (either UPS Store/Drop Box or Macy's store,
  depending on which return method the customer chose) and the option to email the return label to an
  alternate email address, as well as a 'continue shopping' button. 


  @B-97240 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify Return Confirmation page changes when user opt for original payment and Macys store as return method
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    And I select "submit" button on page
    Then I should navigate to "return confirmation" page
    And I should see "original card details" on confirmation page
    And I should see below print mailing label with "<return_method>"
      | Please print it out or have it available on your smart phone to speed up your return in store. |
    And I should see "store locator link" on confirmation page
    And I should see "QR code" on confirmation page
    And I should see "reservation number" on confirmation page
    And I should see "returned date" on confirmation page
    And I should see "estimated credit" on confirmation page
    And I should see "order number" on confirmation page
    And I should see 'continue shopping' button on page
    When I select "continue shopping" button on page
    Then I should navigate to "home" page
    Examples:
      | line_item_status | refund_method   | return_method |
      | delivered        | original tender | store drop    |

  @B-97240 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify Return Confirmation page changes when user opt for gift card and Macys store as return method
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    And I select "submit" button on page
    Then I should navigate to "return confirmation" page
    Then I should see "gift card mailing address" on confirmation page
    And I should see below print mailing label with "<return_method>"
      | Please print it out or have it available on your smart phone to speed up your return in store. |
    And I should see "QR code" on confirmation page
    And I should see "reservation number" on confirmation page
    And I should see "returned date" on confirmation page
    And I should see "estimated credit" on confirmation page
    And I should see "order number" on confirmation page
    And I should see 'continue shopping' button on page
    When I select "continue shopping" button on page
    Then I should navigate to "home" page
    Examples:
      | line_item_status               | refund_method | return_method |
      | shipping_and_billing_different | gift card     | store drop    |

  @B-97241 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify Return Confirmation page changes when user opt for original payment and UPS Dropoff as return method
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    And I select "submit" button on page
    Then I should navigate to "return confirmation" page
    Then I should see "original card details" on confirmation page
    And I should see below print mailing label with "<return_method>"
      | attach the return label to the outside of the package and drop it off at any UPS store, Drop Box |
    And I should see "UPS tracking number" on confirmation page
    And I should see "QR code" on confirmation page
    And I should see "reservation number" on confirmation page
    And I should see "returned date" on confirmation page
    And I should see "estimated credit" on confirmation page
    And I should see "order number" on confirmation page
    And I should see "customer email" on confirmation page
    And I should see "send to another email link" on confirmation page
    And I should see 'continue shopping' button on page
    When I select "continue shopping" button on page
    Then I should navigate to "home" page
    Examples:
      | line_item_status | refund_method   | return_method |
      | delivered        | original tender | ups drop box  |

  @B-97241 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify Return Confirmation page changes when user opt for gift card and UPS Dropoff as return method
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    And I select "submit" button on page
    Then I should navigate to "return confirmation" page
    Then I should see "gift card mailing address" on confirmation page
    And I should see below print mailing label with "<return_method>"
      | attach the return label to the outside of the package and drop it off at any UPS store, Drop Box |
    And I should see "UPS tracking number" on confirmation page
    And I should see "QR code" on confirmation page
    And I should see "reservation number" on confirmation page
    And I should see "returned date" on confirmation page
    And I should see "estimated credit" on confirmation page
    And I should see "order number" on confirmation page
    And I should see "customer email" on confirmation page
    And I should see "send to another email link" on confirmation page
    And I should see 'continue shopping' button on page
    When I select "continue shopping" button on page
    Then I should navigate to "home" page

    Examples:
      | line_item_status               | refund_method | return_method |
      | shipping_and_billing_different | gift card     | ups drop box  |

  @B-77903 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify Return Confirmation page changes when user opt for original payment and default pickup as return method
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    And I select "submit" button on page
    Then I should navigate to "return confirmation" page
    Then I should see "original card details" on confirmation page
    And I should see "pickup date" on confirmation page
    And I should see "pickup time" on confirmation page
    And I should see "special instructions" on confirmation page
    And I should see below print mailing label with "<return_method>"
      | A driver from our partner, Deliv, will arrive to pick up your package on the scheduled date and time. |
    And I should see "UPS tracking number" on confirmation page
    And I should see "QR code" on confirmation page
    And I should see "reservation number" on confirmation page
    And I should see "returned date" on confirmation page
    And I should see "estimated credit" on confirmation page
    And I should see "customer email" on confirmation page
    And I should see "send to another email link" on confirmation page
    And I should see "order number" on confirmation page
    And I should see 'continue shopping' button on page
    When I select "continue shopping" button on page
    Then I should navigate to "home" page
    Examples:
      | line_item_status            | refund_method   | return_method |
      | pickup_eligible_mixed_order | original tender | return pickup |

  @B-77903 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify Return Confirmation page changes when user opt for gift card and default pickup as return method
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    And I select "submit" button on page
    Then I should navigate to "return confirmation" page
    Then I should see "gift card mailing address" on confirmation page
    And I should see "pickup date" on confirmation page
    And I should see "pickup time" on confirmation page
    And I should see "special instructions" on confirmation page
    And I should see below print mailing label with "<return_method>"
      | A driver from our partner, Deliv, will arrive to pick up your package on the scheduled date and time. |
    And I should see "UPS tracking number" on confirmation page
    And I should see "QR code" on confirmation page
    And I should see "reservation number" on confirmation page
    And I should see "returned date" on confirmation page
    And I should see "estimated credit" on confirmation page
    And I should see "customer email" on confirmation page
    And I should see "send to another email link" on confirmation page
    And I should see "order number" on confirmation page
    And I should see 'continue shopping' button on page
    When I select "continue shopping" button on page
    Then I should navigate to "home" page
    Examples:
      | line_item_status                | refund_method | return_method |
      | rebuy_delivered_with_size_color | gift card     | return pickup |

  @B-77903 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify Return Confirmation page changes when user opt for original payment and alternate pickup as return method
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    And I select "submit" button on page
    Then I should navigate to "return confirmation" page
    And I should see "pickup date" on confirmation page
    And I should see "pickup time" on confirmation page
    And I should see "special instructions" on confirmation page
    And I should see below print mailing label with "<return_method>"
      | A driver from our partner, Deliv, will arrive to pick up your package on the scheduled date and time. |
    And I should see "UPS tracking number" on confirmation page
    And I should see "QR code" on confirmation page
    And I should see "reservation number" on confirmation page
    And I should see "returned date" on confirmation page
    And I should see "estimated credit" on confirmation page
    And I should see "order number" on confirmation page
    And I should see 'continue shopping' button on page
    When I select "continue shopping" button on page
    Then I should navigate to "home" page
    Examples:
      | line_item_status            | refund_method   | return_method |
      | pickup_eligible_mixed_order | original tender | return pickup |

  @B-77903 @release_17X @domain_customer_management @project_return_management @use_project
  Scenario Outline: Verify Return Confirmation page changes when user opt for gift card and alternate pickup as return method
    Given I visit the mobile web site as a guest user
    And I associate "<line_item_status>" order in db
    When I navigate to return review summary page using "<refund_method>" and "<return_method>" as return params with "<line_item_status>" order
    And I select "submit" button on page
    Then I should navigate to "return confirmation" page
    Then I should see "gift card mailing address" on confirmation page
    And I should see "pickup date" on confirmation page
    And I should see "pickup time" on confirmation page
    And I should see "special instructions" on confirmation page
    And I should see below print mailing label with "<return_method>"
      | A driver from our partner, Deliv, will arrive to pick up your package on the scheduled date and time. |
    And I should see "UPS tracking number" on confirmation page
    And I should see "QR code" on confirmation page
    And I should see "reservation number" on confirmation page
    And I should see "returned date" on confirmation page
    And I should see "estimated credit" on confirmation page
    And I should see "order number" on confirmation page
    And I should see 'continue shopping' button on page
    When I select "continue shopping" button on page
    Then I should navigate to "home" page
    Examples:
      | line_item_status                | refund_method | return_method |
      | rebuy_delivered_with_size_color | gift card     | return pickup |