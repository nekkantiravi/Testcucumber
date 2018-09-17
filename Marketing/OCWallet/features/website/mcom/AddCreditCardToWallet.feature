Feature: Add Credit Cards to My Wallet


  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @scenario_1
  Scenario: Verify that user is able to add credit cards to my wallet
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to My Wallet page from My Account page
    And I add a credit card with all possible card_type to my wallet
    | card_type               |
    | Macy's                  |
    | Macy's American Express |
      # Note: Macys's American Express card text should include (R) symbol
    | American Express        |
    | Visa                    |
    | MasterCard              |
    | Discover                |
    | Employee Card           |
      # Note: As part of above step we will open the add overlay, enter following details in overlay and save it:
      # Card Type, Card Number, Expiration Date for non-prop cards and Billing address
      # And also verify that overlay is closed and user lands on My Wallet page
      # Also save as default functionality we will be covering as part of Story #10661

    And I should see the added cards in the list of my credit or debit cards
    And I should see the added cards information is saved to database

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @scenario_2
  Scenario:Verify that expiration date fields are disabled for prop cards in credit card overlay
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see expiration date fields are disabled for the following prop card type in add credit card overlay:
      | card_type     |
      | Macy's        |
      | Employee Card |

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @use_domain_qual
  Scenario:Verify that add a new card button is disabled after 10 credit cards are added to wallet
    Given I visit the web site as a registered user with 10 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see add a new card button is disabled

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario Outline:Verify that user is able to close or cancel out of adding a credit card on My Wallet page
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I can "<close_action>" out of "<open_action>" a credit card on My Wallet page

  Examples:
    | close_action | open_action |
    | close        | adding      |
    | cancel       | adding      |

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that use my shipping address field functionality in add credit card overlay if there is a saved default address in address book
  # Pre-requisite: There should be a saved default address in user's address book
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see billing address fields except email are populated in add card overlay with data from default address in address book when use my shipping address field is selected
    # Note: As part of above step we will verify that the data in following fields is populated: First Name, Last Name, Address 1, Address 2, City, State, Zip Code, Phone Number

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that use my shipping address field is not displayed in add credit card overlay if there are no saved addresses in address book
  # Pre-requisite: There should be no saved address in user's address book
    Given I visit the web site as a registered user with no saved addresses
    When I navigate to My Wallet page from My Account page
    Then I should not see use my shipping address field in add card overlay

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @use_domain_qual
  Scenario:Verify that existing saved credit card details should be updated if user tries to add a new credit card with same card type and card number
    Given I visit the web site as a registered user with 1 stored credit card
    When I navigate to My Wallet page from My Account page
    And I add a Visa credit card to my wallet with same card type and card number as existing saved card
    Then I should see new credit card is not added to wallet
    And I should see existing saved credit card details with same card type and card number are updated

   @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the credit card validations in add credit card overlay on my wallet page with invalid data
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see error message in add credit card overlay while saving credit card details with invalid data in following fields:
      | field                     | expected_error_message                                                        |
      | first_name and last_name  | Please remove any special characters.                                         |
      | card_number               | Please enter a valid credit card number.                                      |
      | card_type and card_number | Hmm...the card number and card type do not match.                             |
      | expiration_date           | Please enter a valid expiration date.                                         |
      | first_name                | Please remove any special characters.                                         |
      | last_name                 | Please remove any special characters.                                         |
      | address_line_1            | Please remove any special characters.                                         |
      | address_line_1_with_co    | The address can't contain c/o - please try again.                             |
      | address_line_2            | Please remove any special characters.                                         |
      | address_city              | Please remove any special characters.                                         |
      | address_zip_code          | Please enter a valid 5-digit zip code.                                        |
      | phone_subscriber          | Please enter a 10-digit phone number.                                         |
      | email                     | Please enter a contact email address in the following format: jane@email.com. |

  @use_regression @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the masking of credit card number functionality in add credit card overlay on My Wallet page
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see the masking functionality for credit card number on add credit card overlay
    # Note: This masking behavior is the same when I am adding the first card to my wallet and when I add a subsequent card to my wallet

  @use_regression @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic  @use_s4a_stable @domain_marketing
  Scenario:Verify the masking of credit card number functionality in add credit card overlay on My Wallet page, when error message is displayed on the overlay
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see the masking functionality for credit card number when error message after saving card with invalid card on add credit card overlay
    # Note: This masking behavior is the same when I am adding the first card to my wallet and when I add a subsequent card to my wallet

  ##########################################################
  # Story Title: MCOM UI :: Success Messaging on Wallet Page
  # Mingle Link: http://mingle/projects/market/cards/1186
  ##########################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @use_domain_qual
  Scenario:Verify the success message display when user adds a credit card on my wallet page
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add a credit card with following card type to my wallet:
      | card_type |
      | Macy's    |
    Then I should see "Changes saved to your wallet." on top of the wallet page

  @use_regression @use_bat_next @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @use_domain_qual
  Scenario: Verify the success message display when user adds an offer on my wallet page
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I add an offer to my wallet
    Then I should see "Changes saved to your wallet." on top of the wallet page

  ########################################################################
  # Story Title: MCOM UI:: Wallet Page: Trademark Symbols For Credit Cards
  # Mingle Link: http://mingle/projects/market/cards/1758
  ########################################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that add user is able to see trade mark symbol after each credit card name in wallet page
    Given I visit the web site as a registered user with 6 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see trade mark symbol after each credit card name in wallet page
    # Note: Ex: Visa(registered service mark)

  Scenario:Generate SUPC
    Given I generate SUPC
