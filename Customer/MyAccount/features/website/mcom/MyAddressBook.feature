# Author: Shatabdi Sheet
# Date Created: February 24th, 2013
# Date Signed Off: TBD

Feature: To verify add shipping address in My address book page


  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_high @use_regression @myaccount_5 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify in DB the two Shipping Addreses added from Address Book Page
    Given I visit the web site as a guest user
    When I create a new profile using services
    And I navigate to my address book page
    And user deletes one address
    Then address should get deleted
    And I add 2 shipping addresses to the address book page
    Then I verify address book details in DB


  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_high @use_regression @myaccount_5 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify user can add max 10 address on My Address Book Page
    Given I visit the web site as a guest user
    When I create a new profile using services
    Then I receive an error message when attempting to add more than 10 addresses

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify successful messaging when 1 address is added on My Address Book Page
    Given I visit the web site as a guest user
    When I create a new profile using services
    And I add 1 shipping address to the address book page
    Then I should see the message:
      | MCOM | Your entry has been successfully saved |
      | BCOM | The entry was successfully saved       |

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_low @use_regression @myaccount_5 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify only registered users can access My Address Book Page
    Given I visit the web site as a guest user
    When I create a new profile using services
    When I navigate to my address book page
    Then I should verify that address book page is displayed

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify successful messaging when updating existing address on My Address Book Page
    Given I visit the web site as a guest user
    When I create a new profile using services
    And I navigate to my address book page
    And I can update addresses in the address book
    Then I should see the message:
      | MCOM | Your entry has been successfully saved |
      | BCOM | The entry was successfully saved       |


  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify messaging when user makes a non primary address primary on My Address Book Page
    Given I visit the web site as a guest user
    When I create a new profile using services
    And I navigate to my address book page
    And I add 1 shipping address to the address book page
    And I click on make primary link
    Then I should see the message:
      | MCOM | Your entry has been successfully saved as default Shipping Address. |
      | BCOM | The entry was successfully saved as default shipping address.       |


  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5 @s4a_stable @domain_customer @saucelab_M @saucelab_M_F1 @migrated_to_sdt
  Scenario:Verify error messaging when user updates address with invalid Phone Number on My Address Book Page - Edit Already Saved Address
    Given I visit the web site as a guest user
    When I create a new profile using services
    And I navigate to my address book page
    And I update the address in my address book with invalid phone_area_code and phone_exchange and phone_subscriber fields
    Then I should see the error:
      | MCOM | We're sorry. The fields highlighted below must be completed before we can process your request. |
      | BCOM | Please enter a 10-digit phone number.                                                           |

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify address fields are empty after adding an address on My Address Book Page
    Given I visit the web site as a guest user
    When I create a new profile using services
    And I navigate to my address book page
    And I add 1 shipping address to the address book page
    Then I should verify the address field attributes should be empty

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify update existing address functionality on My Address Book Page
    Given I visit the web site as a guest user
    When I create a new profile using services
    And I navigate to my address book page
    And I can update addresses in the address book
    Then  I should verify the address field attributes should be empty

  @s4a_stable @use_launch_call @domain_customer @use_browser @ifs @use_regression @migrated_to_sdt
  Scenario:Verify generic error messaging when adding an address with empty mandatory fields on My Address Book Page
    Given I visit the web site as a registered user
    And I add an address to my address book with missing "first_name" fields
    Then I verify the error message:
      | First Name is a required field and may only contain letters and hyphens. Your entry may not exceed 20 characters. |


  @use_regression @14H @myaccount_5 @s4a_stable @domain_customer @artifact_shopapp @migrated_to_sdt
  Scenario:Verify error messaging when adding an address with invalid Address Line 2 on My Address Book Page
    Given I visit the web site as a registered user
    When I add an address to my address book with invalid "address_line_2" field
    Then I verify the error message:
      | The second line of your Street Address may only contain letters, numbers and #. Your entry must not exceed 35 characters, including spaces. |


  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @14H @myaccount_5  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify error messaging when adding an address with invalid mandatory fields on My Address Book Page
    Given I visit the web site as a registered user
    And I navigate to shipping address page
    Then I verify error message while adding address with invalid data or missing field on my address book page
      | field_name       | value  | mcom_error_message                                                                                                |
      | first_name       | !@#$   | First Name is a required field and may only contain letters and hyphens. Your entry may not exceed 20 characters. |
      | first_name       |        | First Name is a required field and may only contain letters and hyphens. Your entry may not exceed 20 characters. |
      | last_name        | !@#$   | Last Name is a required field and may only contain letters and hyphens                                            |
      | last_name        |        | Last Name is a required field and may only contain letters and hyphens                                            |
      | address_line_1   | !@#$   | Street Address is a required field and may only contain letters, numbers and #                                    |
      | address_line_1   |        | Street Address is a required field and may only contain letters, numbers and #                                    |
      | address_city     | !@#$   | City is a required field and may only contain letters, numbers and apostrophes                                    |
      | address_city     |        | City is a required field and may only contain letters, numbers and apostrophes                                    |
      | address_state    | Select | You did not select a State from the menu.                                                                         |
      | address_zip_code | !@#$%  | Your Zip Code must be 5 digits                                                                                    |
      | address_zip_code |        | Your Zip Code must be 5 digits                                                                                    |
      | address_zip_code | !@#$   | Your Zip Code must be 5 digits                                                                                    |


  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify messaging on a successful deletion of address on My Address Book Page
    Given I visit the web site as a registered user
    When I add another shipping address
    And user deletes one address
    Then I should see this message:
      | The entry has been deleted from your Address Book. |


  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_high @use_regression @myaccount_5  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify Delete functionality on My Address Book Page
    Given I visit the web site as a registered user
    And I navigate to shipping address page
    When user deletes one address
    Then I verify the deleted address should not be present in the address book page

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify user can Add multiple addresses and remove the same on My Address Book Page
    Given I visit the web site as a guest user
    When I create a new profile
    And I add 3 addresses to my address book
    Then I can remove all of the addresses

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5 @s4a_stable @domain_customer  @migrated_to_sdt
  Scenario:Verify Set As Primary is enabled for non primary address on My Address Book Page
    Given I visit the web site as a guest user
    When I create a new profile
    And I add 3 addresses to my address book
    Then any non-primary address should have an option to make it primary

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5  @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify Set As Primary is disabled for a primary address on My Address Book Page
    Given I visit the web site as a registered user
    And I add 1 addresses to my address book
    And I make second address as primary
    Then I should verify the primary address should not have an option to make primary

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @14H @myaccount_5 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify Phone Number is mandatory when adding an address on My Address Book Page
    Given I visit the web site as a registered user
    And I add an address to my address book with missing "phone_area_code and phone_exchange and phone_subscriber" fields
    Then I verify the error message:
      |Your Phone Number must be entered in this format: 800-555-1212 and may not all be the same number. Do not use (parentheses) for the area code. Please try again |

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_high @use_regression @myaccount_5 @s4a_stable @domain_customer @use_domain_qual @cm_dsv_high_10 @migrated_to_sdt
  Scenario:Verify Address Book functionality - Add, Edit, Remove and Make Primary
    Given I visit the web site as a registered user
    And I navigate to my address book and perform the add,edit,remove and make primary address

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @14H @myaccount_5 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify Phone Number is mandatory on My Address Book Page - Edit Already Saved Address
    Given I visit the web site as a registered user
    And I update the address in my address book with missing "phone_area_code and phone_exchange and phone_subscriber" fields
    Then I verify the error message:
      | Your Phone Number must be entered in this format: 800-555-1212 and may not all be the same number. Do not use (parentheses) for the area code. Please try again |


  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @14H @myaccount_5 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify error messaging when adding an address on My Address Book Page
    Given I visit the web site as a registered user
    When I add an address to my address book with invalid "phone_area_code and phone_exchange and phone_subscriber" fields
    Then I verify the error message:
      | We're sorry. The fields highlighted below must be completed before we can process your request. |

  @upi_88_mcom @upi_100_bcom @artifact_shopapp @priority_medium @use_regression @myaccount_5 @s4a_stable @domain_customer @migrated_to_sdt
  Scenario:Verify user can update existing address on My Address Book Page
    Given I visit the web site as a registered user
    Then I can update addresses in the address book
    And I should verify the updated address in the address book page


  @sstbacklog @use_regression @artifact_shopapp @myaccount_3 @domain_customer @migrated_to_sdt
  Scenario: Verify Address book functionality
    Given I visit the web site as a registered user
    Then I navigate to my address book and perform the add,edit,remove and make primary address