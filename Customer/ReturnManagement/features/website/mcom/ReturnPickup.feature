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

  @returns_regression @15D @domain_customer @story-6639 @priority_medium @use_regression @returns_mgt_6 @mode_domestic @migrated_to_sdt
  Scenario: Pick up section message are correct for eligible orders when selecting a default address
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_location_chicago" order as a "guest" user
    And I click on "yes" button to schedule return pickup
    And I select "other" address for return pickup
    Then I should see option to validate zipcode
    And  I "should not" see schedule your pickup section now
    When I enter "valid_zipcode" for return pickup
    Then  I "should" see schedule your pickup section now
    And I should see address fields to update pickup address with success message:
      | Great—that ZIP Code's eligible! Just enter the street address & select a day & time. |

  @returns_regression @15D @domain_customer @story-6639 @priority_medium @story-6899 @use_regression @returns_mgt_6 @mode_domestic @migrated_to_sdt
  Scenario: Users can schedule a return pick up using the default address
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_location_chicago" order as a "guest" user
    And I click on "yes" button to schedule return pickup
    Then I should see default address as pickup address
    When I continue to submit page using "default" address by entering valid pickup information
    Then I should see pick up information on "submit page" as specified in selection page
    And I verify return credit section
    When I navigate to return confirmation page
    Then I should see pick up information on "confirmation page" as specified in selection page

  @returns_regression @15D @domain_customer @story-6639 @priority_medium @story-6899 @use_regression @returns_mgt_6 @mode_domestic @migrated_to_sdt
  Scenario Outline: Users can schedule a return pick up using the other addresses
    Given I visit order history page as a guest user
    When I navigate to return selection page using "<order_type>" order as a "guest" user
    And I click on "yes" button to schedule return pickup
    Then I should see default address as pickup address
    When I continue to submit page using "other" address by entering valid pickup information
    Then I should see pick up information on "submit page" as specified in selection page
    And I verify return credit section
    When I navigate to return confirmation page
    Then I should see pick up information on "confirmation page" as specified in selection page
    Examples:
      |order_type                          |
      |pickup_eligible_location_los_angeles|
      |pickup_eligible_mixed_order         |
      |pickup_eligible_location_chicago    |

  @returns_regression @15D @domain_customer @story-6639 @story-6899 @story-6900 @priority_medium @use_regression @returns_mgt_6 @mode_domestic @migrated_to_sdt
  Scenario Outline: Return pick up elements are correct on the Submit and Confirmation page
    Given I visit order history page as a guest user
    When I navigate to "<page_name>" using "<order_type>" order as a guest user using "<pickup_address>" address
    Then I should see pick up information on "<page_name>" as specified in selection page
    And I verify return credit section
    And I should see pickup note on return page:
      | PLEASE NOTE: At this time, pickups cannot be rescheduled or cancelled. If we miss you, our pickup partner Deliv will be in touch.|
    And I should see pickup_instruction "<title>" on page as mentioned in title
    And I should see pickup_instruction "<instruction_one>" on page as mentioned in instruction_one
    And I should see pickup_instruction "two" on page as below:
      | 2) PACK the item(s) you'd like to return with your confirmation page. Attach your return label & seal the package-pick up drivers can't take unpackaged, loose items. |
    And I should see pickup_instruction "three" on page as below:
      |3) SHIP-a driver from our partner Deliv will arrive to pickup your sealed package. A signature is required at pickup time.|
    And I should see label on "<page_name>" as below:
      | Don't Forget to Add to Your Calendar! |
    Examples:
      |page_name            | pickup_address|order_type                         | title                                 |      instruction_one  |
      |submit page          |   default     |pickup_eligible_location_chicago   | How to Prepare for Your Pickup:       | 1) PRINT your confirmation page and shipping label at the next step. |
      |confirmation page    |   other       |pickup_eligible_in_san_francisco   | Follow these steps for your pickup:   | 1) PRINT your return label.                                       |

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
      | Great—that ZIP Code's eligible! Just enter the street address & select a day & time. |

  @returns_regression @15D @domain_customer @story-6639 @story-6899 @in_transition @migrated_to_sdt
  Scenario: Pick-ups can be scheduled for gift orders by Guest users using the non-default address
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_location_chicago" order as a "guest" user using gift lookup
    And I click on "yes" button to schedule return pickup
    Then I should see default address as pickup address
    When I continue to submit page using "other" address by entering valid pickup information
    Then I should see pick up information on "return_submit" as specified in selection page
    And I verify return credit section
    When I navigate to return confirmation page
    Then I should see pick up information on "return_confirmation" as specified in selection page
    And I see deeplink is clicked in email

  @returns_regression @15D @domain_customer @story-6639 @priority_medium @6902 @use_regression @returns_mgt_6 @mode_domestic @migrated_to_sdt
  Scenario: Pick-up section displays an error when a Guest user enters an invalid zip code
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_in_san_francisco" order as a "guest" user
    And I click on "yes" button to schedule return pickup
    And I select "other" address for return pickup
    And I enter "invalid_zipcode" for return pickup
    Then I "should not" see schedule your pickup section now
    And I should see error message as zipcode not valid:
      | Sorry, pickup is not available for this ZIP Code. Try another ZIP Code or select your original shipping address. |

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
    Then I should see pick address as default address by default with success message
      | Return pickup is available for this address. Select a day & time below to schedule! |
    And I should see time and date options to schedule pickup
    And I should see fields below should not exceed character length specified
      | field_name           | charecter_lenght |
      | phone_number         | 10               |
      | special_instructions | 50               |

  @returns_regression @15D @domain_customer @story-6639 @priority_medium @use_regression @returns_mgt_6 @mode_domestic @migrated_to_sdt
  Scenario: Invalid Zipcde returns an error message on the return selection page
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_in_san_francisco" order as a "guest" user
    And I click on "yes" button to schedule return pickup
    And I select "other" address for return pickup
    And I enter "invalid_zipcode" for return pickup
    Then I "should not" see schedule your pickup section now
    And I should see error message as zipcode not valid:
      | Sorry, pickup is not available for this ZIP Code. Try another ZIP Code or select your original shipping address. |

  @returns_regression @15D @domain_customer @story-6639 @priority_medium @use_regression @returns_mgt_6 @mode_domestic @migrated_to_sdt
  Scenario: Pick up section elements are correct for eligible orders when selecting a default address
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_location_los_angeles" order as a "guest" user
    And I click on "yes" button to schedule return pickup
    And I select "other" address for return pickup
    Then I should see option to validate zipcode
    And  I "should not" see schedule your pickup section now
    When I enter "valid_zipcode" for return pickup
    Then  I "should" see schedule your pickup section now
    And I should see fields below should not exceed character length specified
      | field_name           | character_length |
      | pickup_zipcode       | 5                |
      | phone_number         | 10               |
      | address_line_one     | 35               |
      | address_line_two     | 35               |
      | special_instructions | 50               |


  @returns_regression @migrated_to_sdt
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
      | Great—that ZIP Code's eligible! Just enter the street address & select a day & time.|
    And I should see fields below should not exceed character length specified
      | field_name           | character_length |
      | pickup_zipcode       | 5                |
      | phone_number         | 10               |
      | address_line_one     | 35               |
      | address_line_two     | 35               |
      | special_instructions | 50               |

  @15D @domain_customer @story-6639 @priority_medium @use_regression @returns_mgt_6 @mode_domestic @returns_regression @migrated_to_sdt
  Scenario:  Pick up section elements and message are correct for eligible orders when selecting a default address
    Given I visit order history page as a guest user
    When I navigate to return selection page using "pickup_eligible_location_chicago" order as a "guest" user
    And I click on "yes" button to schedule return pickup
    And I select "other" address for return pickup
    Then I should see option to validate zipcode
    And  I "should not" see schedule your pickup section now
    When I enter "valid_zipcode" for return pickup
    Then  I "should" see schedule your pickup section now
    And I should see address fields to update pickup address with success message:
      | Great—that ZIP Code's eligible! Just enter the street address & select a day & time.|
    And I should see fields below should not exceed character length specified
      | field_name           | character_length |
      | pickup_zipcode       | 5                |
      | phone_number         | 10               |
      | address_line_one     | 35               |
      | address_line_two     | 35               |
      | special_instructions | 50               |

