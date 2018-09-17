# Author: Suma Dathrik
# Date  : May 26, 2014
# Date Signed Off:

Feature: Gift Returns - Order History page

  #As a customer, I should have the option to choose to validate and lookup order statuses as a gift recipient.

  #Mingle Story:  http://mingle/projects/customer_management/cards/6539
  #http://mingle/projects/customer_management/cards/6540


@returns_regression @14H @credit-6539 @credit-6540 @priority_medium @use_regression @mode_domestic @returns_mgt_4 @s4a_stable @domain_customer @feature_giftreturn_orderhistory @migrated_to_sdt
  Scenario: Email Address and Gift Returns div disappears when the Order Number is deleted on the Order History page as a Guest user
    Given I visit order history page as a guest user
    When I lookup with "submitted" order
    When I delete the order number as guest user
    And I see email address or phone number fields and gift return section is collapsed

  @returns_regression @14H @credit-6539 @credit-6540 @priority_medium @use_regression @mode_domestic @returns_mgt_4 @s4a_stable @domain_customer @feature_giftreturn_orderhistory @migrated_to_sdt
  Scenario: BOPS Gift order returns an error message on the Order History page as a Guest user
    Given I visit order history page as a guest user
    When I lookup "bops_with_creditcard" order with gift return
    And I should see error message on top
      | Please call our Customer Service Department at 1-800-BUY-MACY (1-800-289-6229) to process the return.|

  @returns_regression @14H @credit-6539 @credit-6540 @priority_medium @use_regression @mode_domestic @returns_mgt_4 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario: Gift radio button retains state when Order Number is deleted on the Order History page as a Signed-in user
    Given I visit order history page as a signed user
    When I lookup with "submitted" order
    Then I see No radio button is selected by default in Gift Return section
    When I delete the order number as guest user
    When I lookup with "submitted" order
    And I should see No radio button is selected
    Then I select gift option from Gift Return Section
    When I delete the order number as guest user
    When I lookup with "submitted" order
    And I should see Yes radio button is selected

  @returns_regression @release_16A @credit-6539 @credit-6540 @priority_medium @use_project @mode_domestic @returns_mgt_4 @domain_customer @migrated_to_sdt
  Scenario: Invalid Phone Number displays an error message on the Order History Page as a Signed-in user
    Given I visit order history page as a signed user
    When I lookup with valid order number and incorrect phonenumber in OH page
      |order_number   | phone_area_code | phone_exchange | phone_subscriber |
      |free_ship_order| 123             | 123            | 1234             |
    And I should see error message on top
      | We couldn't find an order with the information you provided. Please double check it and re-enter.|
    When I lookup with "submitted" order with gift return
    Then I should navigate to order details page

  @returns_regression @release-16A @use_project @mode_domestic @returns_mgt_4 @credit-6539 @credit-6540 @priority_medium @domain_customer @migrated_to_sdt
  Scenario: Invalid Email displays an error message on the Order History Page as a Signed-in user
    Given I visit order history page as a signed user
    When I lookup with valid order number and incorrect emailaddress in OH page
      |order_number    | email        |
      |free_ship_order | sss@test.com |
    And I should see error message on top
      | We couldn't find an order with the information you provided. Please double check it and re-enter.|
    When I lookup with "submitted" order with gift return
    Then I should navigate to order details page
