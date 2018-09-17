Feature: As a customer,I would like to schedule a pickup for return items so I dont have to go to a store or UPS location.

  @returns_regression @15D @domain_customer @story-6639 @priority_medium @6902 @use_regression @returns_mgt_6 @mode_domestic @feature_returnpickup @migrated_to_sdt
  Scenario: Pick-up section is not displayed for non-eligible orders
    Given I visit order history page as a guest user
    When I navigate to return selection page using "submitted" order as a "guest" user
    Then I "should not" see pickup section on return selection page

  @returns_regression @15D @domain_customer @story-6639 @priority_medium @bcom @6902 @use_regression @returns_mgt_6 @mode_domestic @migrated_to_sdt
  Scenario: Pick-up section is not displayed for BOPS orders
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_bops" order as a "guest" user
    Then I "should not" see pickup section on return selection page

  @15D @domain_customer @story-6639 @priority_medium @6902 @use_regression @returns_mgt_6 @mode_domestic @migrated_to_sdt
  Scenario: Pick-up section is displayed for mixed orders
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_mixed_order" order as a "guest" user
    Then I "should" see pickup section on return selection page


  @returns_regression @15D @domain_customer @story-6639 @priority_medium @6902 @use_regression @returns_mgt_6 @mode_domestic @feature_returnpickup @cm_dsv_high_10 @migrated_to_sdt
  Scenario: Pick-up elements are correct when a Guest user selects a non-default address
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_location_chicago" order as a "guest" user
    And I click on "yes" button to schedule return pickup
    And I select "other" address for return pickup
    Then I should see option to validate zipcode
    And  I "should not" see schedule your pickup section now
    When I enter "valid_zipcode" for return pickup
    Then  I "should" see schedule your pickup section now
    And I should see address fields to update pickup address with success message:
      | The selected address is eligible for our pickup service.|

  @returns_regression @15D @domain_customer @story-6639 @story-6899 @in_transition  @migrated_to_sdt
  Scenario: Pick-ups can be scheduled for gift orders by Guest users using the non-default address
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_location_chicago" order as a "guest" user using gift lookup
    And I click on "yes" button to schedule return pickup
    Then I should see default address as pickup address
    When I continue to submit page using "other" address by entering valid pickup information
    Then I should see pick up information on "submit page" as specified in selection page
    And I verify return credit section
    When I navigate to return confirmation page
    Then I should see pick up information on "confirmation page" as specified in selection page
    And I see deeplink is clicked in email

  @returns_regression @15G @domain_customer_management @story-18419 @priority_medium @story-18416 @project_returns_management @use_iteration @mode_domestic @feature_add_quickview @defect_13664 @migrated_to_sdt
  Scenario: Pick-up FAQs can be clicked on
    Given I visit order history page as a guest user
    When I navigate to selection page using "pickup_eligible_location_chicago" order as a "guest" user
    Then I should see label as how_it_works
    And I click on visit_our_FAQ_ page

  @returns_regression @migrated_to_sdt
  Scenario: Pick-up section validations when Guest user selects the default address
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_location_los_angeles" order as a "guest" user
    Then I "should" see pickup section on return selection page
    When I click on "yes" button to schedule return pickup
    Then I should see pick address as default address by default with success message:
      | This address is eligible for our pickup service.|
    And I should see time and date options to schedule pickup
    And I should see fields below should not exceed character length specified
      | field_name           | charecter_lenght |
      | phone_number         | 10               |
      | special_instructions | 50               |

  @returns_regression @15D @domain_customer @story-6639 @priority_medium @6902 @use_regression @returns_mgt_6 @mode_domestic @migrated_to_sdt
  Scenario: Pick-up section displays an error when a Guest user enters an invalid zip code
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_in_san_francisco" order as a "guest" user
    And I click on "yes" button to schedule return pickup
    And I select "other" address for return pickup
    And I enter "invalid_zipcode" for return pickup
    Then I "should not" see schedule your pickup section now
    And I should see error message as zipcode not valid:
      | Our returns pickup service is not yet available for this ZIP Code,although it is available for your default shipping address. |

  @returns_regression @migrated_to_sdt @returns_regression
  Scenario: Pick-up section validations when Guest user selects the non-default address
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_location_chicago" order as a "guest" user
    And I click on "yes" button to schedule return pickup
    And I select "other" address for return pickup
    Then I should see option to validate zipcode
    And  I "should not" see schedule your pickup section now
    When I enter "valid_zipcode" for return pickup
    Then  I "should" see schedule your pickup section now
    And I should see address fields to update pickup address with success message:
      | The selected address is elegible for our pickup service. |
    And I should see fields below should not exceed character length specified
      | field_name           | character_length |
      | pickup_zipcode       | 5                |
      | phone_number         | 10               |
      | address_line_one     | 35               |
      | address_line_two     | 35               |
      | special_instructions | 50               |

