Feature: Loyalty UI
  #For tests related to UI messaging, errors, display, and link navigation

  @use_regression @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Verify My Loyallist Footer Link Navigation For Guest User
    Given I visit the web site as a guest user
    Then I click the loyallist footer link as a guest user

  @use_regression @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Verify My Loyallist Footer Link Navigation For Registered User
    Given I visit the web site as a registered user without loyalty
    Then I click the loyallist footer link as a registered user

  @use_regression @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Verify My Loyallist Footer Link Navigation For Loyallist
    Given I visit the web site as a loyallist
    Then I click the loyallist footer link as a loyallist

  #BLCOM-81328
  @use_regression @uft @s4a_stable @domain_marketing @loyalty_ui
  Scenario: Verify Loyalty Enrollment Confirmation Page link Apply Now for Guest User
    Given I visit the web site as a guest user
    When I enroll into the Loyalty program by creating a new profile
    Then I verify the loyalty enrollment confirmation page link goto_apply_now

  @use_regression @uft @s4a_stable @domain_marketing @loyalty_ui
  Scenario: Verify Loyalty Enrollment Confirmation Page link Continue Shopping for Guest User
    Given I visit the web site as a guest user
    When I enroll into the Loyalty program by creating a new profile
    Then I verify the loyalty enrollment confirmation page link continue_shopping

  #BLCOM-81328
  @use_regression @uft @s4a_stable @domain_marketing @loyalty_ui @use_domain_qual
  Scenario: Verify Loyalty Enrollment Confirmation Page link Apply Now for Registered User
    Given I visit the web site as a registered user without loyalty
    When I enroll into the Loyalty program using existing profile
    Then I verify the loyalty enrollment confirmation page link goto_apply_now

  @use_regression @uft @s4a_stable @domain_marketing @loyalty_ui @use_domain_qual
  Scenario: Verify Loyalty Enrollment Confirmation link Continue Shopping for Registered User
    Given I visit the web site as a registered user without loyalty
    When I enroll into the Loyalty program using existing profile
    Then I verify the loyalty enrollment confirmation page link continue_shopping

  @use_regression @uft @priority_medium @prod @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Navigate to loyalty top of the list page and verify benefits
    Given I visit the web site as a guest user
    When I navigate to the loyalty top of the list page
    Then I can verify the benefits for being top of the list

  @use_regression @uft @priority_medium @prod @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Navigate to loyalty member benefits page and verify navigation from links
    Given I visit the web site as a guest user
    When I navigate to the loyalty member benefits page
    Then I verify the My Perks page and the links
#      | info_exclusions      |
#      | terms_and_conditions |
#      | top_of_the_list      |
#      | apply_now            |

  @use_regression @priority_high @prod @uft @sst_launch_call_domestic @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Verify Loyalty Landing Page and Link Navigation
    Given I visit the web site as a guest user
    When I navigate to the loyalty landing page
    Then I verify the loyalty landing page and the links
#      | info_exclusions     |
#      | become_a_loyallist  |
#      | manage_my_loyallist |
#      | apply_now           |

  @use_regression @14G @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Verify Loyalty Account Summary Page Guest User
    Given I visit the web site as a guest user
    Then I attempt to navigate to the loyalty account summary page as a guest user

  @use_regression @s4a_stable @domain_marketing @priority_high @loyalty_ui
  Scenario:Verify Successful Sign In Behavior From Loyalty Landing Page
    Given I visit the web site as a registered user without loyalty
    When I sign out from profile
    And I navigate to the loyalty landing page
    Then I sign in from the loyalty landing page

  @s4a_stable @domain_marketing @loyalty_ui @deprecated_15GA
  Scenario:Verify Failed Sign In Behavior From Loyalty Landing Page
    Given I visit the web site as a registered user without loyalty
    And I sign out from profile
    And I navigate to the loyalty landing page
    Then I attempt to sign in from the loyalty landing page

#    The below two test cases on Manage my loyallist functionlaity are obsolete from 17N
#  @use_regression @uft @priority_medium @prod @s4a_stable @domain_marketing @loyalty_ui
#  Scenario:Verify the new member benefits page, user navigated from footer as a signed in user
#    Given I visit the web site as a registered user
#    When I click the loyallist footer link as a registered user
#    And I click manage my loyallist from a loyalty page as a registered user
#    Then I should see member benefits in left navigation of account association page
#    When I navigate to the member benefits link in account association page
#    Then I verify that the "Member Benefits" page is rendered
#
#  @use_regression @priority_high @prod @uft @s4a_stable @domain_marketing @loyalty_ui
#  Scenario:Verify manage my loyallist functionality for not signed in user, where the user is loyallist member and have a bloomingdale's profile
#    Given I visit the web site as a loyallist
#    When I sign out from profile
#    And I navigate to loyallist landing page from footer link
#    Then I should navigate to sign in page after clicking manage my loyallist nav point in top nav
#    When I sign in using existing profile
#    Then I should be navigated to Loyalty account summary page

  @use_regression @priority_medium @prod @uft @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Verify sign in and enroll option, where the user is loyallist member and have a bloomingdale's profile
    Given I visit the web site as a loyallist
    When I sign out from profile
    And I navigate to loyallist landing page from footer link
    And I signed in with existing loyallist profile from sign in & enroll option
    Then I should be navigated to Loyalty account summary page
    And I should see "You are already enrolled in Bloomingdale's Loyallist program. You can view your rewards below." text in account summary page

  @use_regression @uft @use_launch_call @s4a_stable @domain_marketing @loyalty_ui @use_domain_qual
  Scenario:Verify create profile and enroll button functionality on become a loyallist page
    Given I visit the web site as a guest user
    When I navigate to loyallist landing page from footer link
    And I open create profile and enroll option from new loyallist landing page
    Then I verify that the "Loyallist Enrollment Page" page is rendered
    And I should see question mark after phone number present in the loyalty enrollment page
    And I should see link for terms and conditions text present in the loyalty enrollment page

  @use_regression @domain_marketing @loyalty_ui @use_domain_qual
  Scenario: Verification of loyalty offers on Sales & Promotions page
    Given I visit the web site as a registered user without loyalty
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "reward"
    And I navigate to loyalty bonus offers page
    Then I should be in Sales & promotions page
    And I should see loyalty offers


  @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Navigate to Loyalty FAQ and Terms Pages and Verify Links For Guest User
    Given I visit the web site as a guest user
    When I navigate to the loyalty terms and conditions page as a guest user
    Then I verify the "become_a_loyallist and manage_my_loyallist" links as a guest user
    When I navigate to the loyalty FAQ page as a guest user
    Then I verify the "become_a_loyallist and manage_my_loyallist" links as a guest user

  @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Navigate to Loyalty FAQ and Terms Pages and Verify Links For registered user
    Given I visit the web site as a registered user without loyalty
    When I navigate to the loyalty terms and conditions page as a registered user
    Then I verify the "become_a_loyallist and manage_my_loyallist" links as a registered user
    When I navigate to the loyalty FAQ page as a registered user
    Then I verify the "become_a_loyallist and manage_my_loyallist" links as a registered user

  @s4a_stable @domain_marketing @loyalty_ui
  Scenario:Navigate to Loyalty FAQ and Terms Pages and Verify Links For loyallist
    Given I visit the web site as a loyallist
    When I navigate to the loyalty terms and conditions page as a loyallist
    Then I verify the "become_a_loyallist and manage_my_loyallist" links as a loyallist
    When I navigate to the loyalty FAQ page as a loyallist
    Then I verify the "become_a_loyallist and manage_my_loyallist" links as a loyallist

  @domain_marketing @loyalty_errors @wip @please_automate
  Scenario: Verify errors on loyalty association page
    Given I visit the web site as a registered user without loyalty
    And TODO needs a new step to add a compatible Citi Card To Profile
    When I navigate to the loyalty account summary page
    And I close the loyalty auto association dialog
    Then I can verify all the errors on the loyalty association page
    # Notes:
    # Auto association works only with new profiles

  @use_regression @uft @priority_medium @artifact_legacy @domain_marketing @loyalty_errors @wip @please_automate
  Scenario: Verify errors on loyalty auto association page
    Given I visit the web site as a registered user
    And  TODO needs a new step to add a compatible Citi Card To Profile
    Then I verify all errors for auto associate from the enrollment page

  @use_regression @uft @priority_medium @artifact_legacy @domain_marketing @loyalty_errors @wip @please_automate
  Scenario: Verify errors on loyalty auto association dialog
    Given I visit the web site as a registered user
    And TODO needs a new step to add a compatible Citi Card To Profile
    Then I verify all errors for auto associate from the account summary page

  @use_regression @domain_marketing @loyalty_errors
  Scenario: Verify Account Management Edit Error Messages
    Given I visit the web site as a loyallist
    And I navigate to the loyalty account summary page
    Then I can verify the loyalty edit account field error messages:
      | field                 | invalid_data                 | error                    |
      | edit_first_name       |                              | This field is required.  |
      | edit_first_name       | $#@#!!!                      | This field is not valid. |
      | edit_last_name        |                              | This field is required.  |
      | edit_last_name        | $#@#!!!                      | This field is not valid. |
      | edit_address_line1    |                              | This field is required.  |
      | edit_address_line1    | BAD!ADRESSCHAR#$             | This field is not valid. |
      | edit_address_line2    | BAD!ADRESSCHAR#$             | This field is not valid. |
      | edit_address_city     |                              | This field is required.  |
      | edit_address_city     | &^!@!#                       | This field is not valid. |
      | edit_address_state    | State                        | This field is required.  |
      | edit_address_zip      |                              | This field is required.  |
      | edit_address_zip      | ABSD1                        | This field is not valid. |
      | edit_address_phone_part1  |                              | This field is required.  |
      | edit_address_phone_part1  | AB1                          | This field is not valid. |
      | edit_address_email        | BADEMAIL!#$&@SPECIALCHAR.COM | This field is not valid. |
  #  #TODO NEED COVERAGE:
  @wip @domain_marketing @loyalty_errors
  Scenario: Verify loyalty enrollment error messages using an existing profile
    Given I visit the web site as a registered user
    And I navigate to the loyalty enrollment page as a registered user
    Then I can verify all the loyalty enrollment error messages as a registered user

  @wip @domain_marketing @loyalty_errors
  Scenario: Verify loyalty enrollment error messages as a new user
    Given I visit the web site as a guest user
    And I navigate to the loyalty enrollment page as a guest user
    Then I can verify all the loyalty enrollment error messages as a guest user


