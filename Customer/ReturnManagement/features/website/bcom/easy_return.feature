Feature: Verify Easy Return

  @returns_regression @use_regression @mode_domestic @priority_high @artifact_shopapp @returns_mgt_2 @shopapp_4 @s4a_stable @14K @domain_customer @feature_easyreturn @use_domain_qual @migrated_to_sdt
  Scenario: Easy Returns URL redirects to Customer Service page for a Guest user
    Given I visit the web site as a guest user
    When I navigate to easy return using url
    Then I should be directed to customer service answer page

  @14K @use_regression @mode_domestic @credit-7387 @artifact_shopapp @returns_mgt_2 @domain_customer @priority_high @migrated_to_sdt
  Scenario Outline: URL validation from the Customer Help page to the Easy Returns page
    Given I visit the web site as a guest user
    When I navigate to following customer "<BCOM_URL>" page url
    Then I verify customer page url navigation with "<navigation_type>" page
    Examples:
      | navigation_type  | BCOM_URL                                                             |
      | app_answers_list | https://bloomingdales--tst.custhelp.com/app/answers/list/c/23        |
      | app_mail         | https://bloomingdales--tst.custhelp.com/app/answers/detail/a_id/434/ |
      | app_init_online  | https://bloomingdales--tst.custhelp.com/app/answers/detail/a_id/359/ |

  @returns_regression @credit-7536 @credit-7537 @release_16A @use_regression @mode_domestic @artifact_shopapp @domain_customer @feature_easyreturn_errorhandling @migrated_to_sdt
  Scenario: Invalid Email Address returns an error message on the Easy Returns page
    Given I visit the web site as a guest user
    When I navigate to easy return using url
    When I lookup with valid order number and incorrect emailaddress in EasyReturns page
      | order_type | email        |
      | submitted | sss@test.com |
    Then I verify error messages upto 1 attempts
      | We couldn't find an order with the information you provided. Please double check it and re-enter. |
    When I lookup with "submitted" order with gift return
    Then I should navigate to order details page

  @returns_regression @credit-7536 @credit-7537 @release_16A @use_regression @mode_domestic @artifact_shopapp @domain_customer @feature_easyreturn @migrated_to_sdt
  Scenario: Invalid Phone Number returns an error message on the Easy Returns page
    Given I visit the web site as a guest user
    When I navigate to easy return page
    When I lookup with valid order number and incorrect phonenumber in EasyReturns page
      |   order_number | phone_area_code | phone_exchange | phone_subscriber |
      |   submitted    | 123             | 123            | 1234             |
    And I should see error message on top
      | At this time we are unable to retrieve your order information. Please try again later or contact Customer Service at 1-800-777-0000                  |
    When I lookup with "submitted" order with gift return
    Then I should navigate to order details page

  @returns_regression @credit-7536 @credit-7537 @14K @use_regression @mode_domestic @artifact_shopapp @domain_customer @migrated_to_sdt
  Scenario: BOPS Order displays an error message correctly on the Easy Returns Page
    Given I visit the web site as a guest user
    When I navigate to easy return page
    And I navigate to order history page
    When I lookup with "bops_with_creditcard" order with gift return on OH page
    And I should see error message on top
      | Please contact Customer Service at 1-800-777-0000 to process the return. |

  @returns_regression @credit-7536 @credit-7537 @14K @use_regression @mode_domestic @artifact_shopapp @domain_customer @migrated_to_sdt
  Scenario: Gift Return is no by default on the Easy Return Page as a Guest user
    Given I visit the web site as a guest user
    When I navigate to easy return page
    When I lookup with "submitted" order
    Then I should see Gift Return Section
    Then I see No radio button is selected by default in Gift Return section
    And I should see email address or phone number text fields

  @returns_regression @credit-7536 @credit-7537 @14K @artifact_shopapp @domain_customer @use_regression @mode_domestic @returns_mgt_2 @migrated_to_sdt
  Scenario: Orders can be looked up through the Easy Returns Page
    Given I visit the web site as a guest user
    When I navigate to easy return page
    When I lookup with valid order number and valid emailaddress in EasyReturns page
      | order_number             | email         |
      | shipping and billing same| TDM@MACYS.COM |
    Then I should navigate to order details page

  @returns_regression @credit-7536 @credit-7537 @release_16A @use_regression @mode_domestic @artifact_shopapp @returns_mgt_3 @domain_customer @migrated_to_sdt
  Scenario: Invalid Order Numbers locks users out after four attempts on the Easy Returns page
    Given I visit the web site as a guest user
    When I navigate to easy return page
    When I lookup with incorrect order number and valid emailaddress in EasyReturns page
      | order_number   | email               |
      | incorrect_order| TEST@MACYS.COM      |
    Then I verify lockout error message after 4 attempts
      | We're still unable to locate your order. Please call our Customer Service Department at 1-800-777-0000, available 24 hours a day, 7 days a week.|

  @returns_regression @credit-7536 @credit-7537 @release_16A @use_regression @mode_domestic @artifact_shopapp @returns_mgt_3 @domain_customer @cm_dsv_high_10 @migrated_to_sdt
  Scenario: Invalid Email Address locks users out after four attempts on the Easy Returns page
    Given I visit the web site as a guest user
    When I navigate to easy return page
    When I lookup with valid order number and incorrect emailaddress in EasyReturns page
      | order_number              | email        |
      | shipping and billing same | sss@test.com |
    Then I verify lockout error message after 4 attempts
      | We're still unable to locate your order. Please call our Customer Service Department at 1-800-777-0000, available 24 hours a day, 7 days a week.|

  @returns_regression @credit-7536 @credit-7537 @release_16A @use_regression @mode_domestic @artifact_shopapp @returns_mgt_3 @domain_customer @migrated_to_sdt
  Scenario: Invalid Phone Number locks users out after four attempts on the Easy Returns page
    Given I visit the web site as a guest user
    When I navigate to easy return page
    When I lookup with valid order number and incorrect phonenumber in EasyReturns page
      | order_number              | phone_area_code | phone_exchange | phone_subscriber |
      | shipping and billing same | 123             | 123            | 1234             |
    Then I verify lockout error message after 4 attempts
      | We're still unable to locate your order. Please call our Customer Service Department at 1-800-777-0000, available 24 hours a day, 7 days a week.|

  @returns_regression @release_16E @domain_customer_management @return_management @priority_medium @mode_domestic @use_project @b-44643 @migrated_to_sdt
  Scenario: Order Number text field only accepts 20 alphanumeric characters on the Easy Returns page
    Given I visit the web site as a guest user
    And I navigate to easy return page
    And I verify order field is limitied to 20 characters
    And I should see order field only accepts alphanumeric characters

  @returns_regression @migrated_to_sdt
  Scenario: Error message validations with incorrect data on the Easy Returns page as a Guest user
    Given I visit the web site as a guest user
    When I navigate to easy return page
    When I lookup with "submitted" order
    Then I select gift option from Gift Return Section
    And I enter "123456" invalid shipped to zipcode
    Then I verify length of zipcode
    And I enter "ABCDE" invalid shipped to zipcode
    And I should see "Your ZIP Code must be 5-digits. Please try again." error message on top
    And I enter "12345" invalid shipped to zipcode
    And I should see "We couldn't find an order with the information you provided. Please double check it and re-enter." error message on top

  @returns_regression  @migrated_to_sdt
  Scenario: Error message validations using invalid data on the Easy Returns page as a Guest user
    Given I visit the web site as a guest user
    When I navigate to easy return page
    When I lookup with valid order number and incorrect emailaddress in EasyReturns page
      | order_number             | email        |
      | shipping and billing same| sss@test.com |
    Then I should see "We couldn't find an order with the information you provided. Please double check it and re-enter." error message on top
    When I navigate to easy return page
    When I lookup with valid order number and incorrect phonenumber in EasyReturns page
      | order_number             | phone_area_code | phone_exchange | phone_subscriber |
      | shipping and billing same| 123             | 123            | 1234             |
    Then I should see "We couldn't find an order with the information you provided. Please double check it and re-enter." error message on top
    When I navigate to easy return page
    When I lookup with incorrect order number and valid emailaddress in EasyReturns page
      | order_number   | email               |
      | incorrect_order| TEST@MACYS.COM      |
    Then I verify lockout error message after 4 attempts
      | We're still unable to locate your order. Please call our Customer Service Department at 1-800-777-0000, available 24 hours a day, 7 days a week.|
    When I lookup with "invalid" order
    And I select gift option from Gift Return Section
    And I enter "94903" invalid shipped to zipcode
    And I should see "We couldn't find an order with the information you provided. Please double check it and re-enter." error message on top