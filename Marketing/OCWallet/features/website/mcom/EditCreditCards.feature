# Author: OC Wallet Team
# Date Created: 06/07/2017
# Date Signed Off: TBD


Feature: Edit Credit Cards in My Wallet

  Mingle Link: http://mingle/projects/market/cards/269

  #############################################################
  # Story Title: MCOM UI :: ShopApp :: Make Credit Card Primary
  # Mingle Link: http://mingle/projects/market/cards/1193
  #############################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that if first credit card is being added to user wallet then it is set as default card
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see set as default option is checked and disabled on add credit card overlay
    When I add a credit or debit card with following card type to my wallet:
      | card_type  |
      | MasterCard |
    Then I should see the added credit card is saved as default card

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that set as default option is unchecked in add credit card overlay if there is at least one credit or debit card in wallet
  # Pre-requisite: User's wallet should contain at least one credit card
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see set as default option is unchecked on add credit card overlay

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that "set as default" option is unchecked when editing a credit card which is not default also once saved verify the order of display on wallet page
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I select set as default option and save in edit credit card overlay
    # Note: As part of above step we will verify that set as default option is unchecked on edit credit card overlay
    Then I should see the saved default card at the top of the list of credit cards

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that if card has been saved as default and when edit credit card overlay for this card is accessed the set as default option is checked and disabled
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see set as default option is checked and disabled on edit credit card overlay for default card

  #######################################################
  # Story Title: MCOM UI :: ShopApp :: Edit Credit Cards
  # Mingle Link: http://mingle/projects/market/cards/1238
  #######################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that user is able to edit a credit card stored in my wallet by opening edit credit card overlay by using either pencil icon or credit card icon
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    And  I update credit card details
    Then I should see "Changes saved to your wallet." on top of the wallet page
    # Note: As part of above step we will open edit credit card overlay by using either pencil icon or credit card icon and then update card details and save
    And I should see updated credit card details on my wallet page
    # Note: As part of above step we will verify that the edited card details are pre-populated in edit credit card overlay
    And I should see card information is updated in the database

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that display of tool tip message when hovering the pencil icon
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see a tool tip with "edit or delete your card" message upon hovering the pencil icon for any store credit card

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario Outline:Verify that user is able to close or cancel out of editing a credit card on My Wallet page
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I can "<close_action>" out of "<open_action>" a credit card on My Wallet page

  Examples:
    | close_action | open_action |
    | close        | editing     |
    | cancel       | editing     |

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that updating one saved credit card with another saved credit card data from add credit card overlay i.e; card type and card number the card being updated should be deleted
  and changes should be updated for the existing credit card with same card type and card number
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I update credit card details with same card type and card number as another saved card
    Then I should see the credit card being updated is deleted
    And I should see existing saved credit card details with same card type and card number are updated

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic  @use_s4a_stable @domain_marketing
  Scenario:Verify use my shipping address field functionality in edit a credit or debit card overlay if there is a saved default address in address book
  # Pre-requisite: There should be a saved default address in user's address book
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see billing address fields except email are populated in edit card overlay with data from default address in address book when use my shipping address field is selected
    # Note: As part of above step we will verify that use my shipping address field is not selected by default
    # And also we will verify that the data in following fields is populated: First Name, Last Name, Address 1, Address 2, City, State, Zip Code, Phone Number
    And I should see billing address fields are still populated in edit card overlay with data from default address in address book when use my shipping address field is unchecked

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify use my shipping address field is not displayed in edit a credit or debit card overlay if there are no saved addresses in address book
  # Pre-requisite: There should be no saved address in user's address book
    Given I visit the web site as a registered user with no saved addresses
    When I navigate to My Wallet page from My Account page
    Then I should not see use my shipping address field in edit card overlay

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify masking of credit card number functionality in edit a credit or debit card overlay on My Wallet page
    Given I visit the web site as a registered user with 1 stored credit card
    When I navigate to My Wallet page from My Account page
    Then I should see the masking functionality for credit card number on edit credit card overlay
    # Note: As Part of above step we will verify masking functionality
    # Note: This masking behavior is the same when I am editing the first card or any subsequent card in my wallet

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the masking of credit card number functionality in edit a credit or debit card overlay on My Wallet page, when error message is displayed on the overlay
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see the masking functionality for credit card number when error message after saving card with invalid card
    # Note: This masking behavior is the same when I am editing the first card or any subsequent card in my wallet

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that previously entered credit card number is masked in edit a credit or debit card overlay on My Wallet page, when focusing the card number field
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see credit card number is masked when focused in credit card number field
    # Note: This masking behavior is the same when I am editing the first card or any subsequent card in my wallet

  #############################################################################
  # Story Title: MCOM UI :: ShopApp :: Credit Card Overlay - Minor Enhancements
  # Mingle Link: http://mingle/projects/market/cards/1427
  #############################################################################

  @use_regression @14H @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario Outline:Verify that user not able to enter alpha characters in credit card number field on Add/Edit credit card overlay
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see nothing is displyed after entering any alpha character in credit card number field on "<add_or_edit>" credit card overlay
    # Note: As part of above step we will verify that user able to enter only numbers in credit card number field and Same validations check points apply to the credit card overlay when the customer clicks the "save & close" button

  Examples:
    |add_or_edit|
    | Add       |
    | Edit      |

  @14H @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario Outline:Verify that user able to auto tabbed when adding or updating her phone number in the Add/Edit credit card overlay on wallet page
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I verify tabbing functionality in phone number field on "<add_or_edit>" credit card overlay
    # Note: Customer types in firs three numbers of her phone number whereby the overlay recognizes she has entered the appropriate amount of digits, and therefore auto-tabs her to the next field (2nd field out of 3) where she can enter the next set of numbers for her phone number.
    # The same functionality happens after the customer has entered her information in the 2nd field - she is auto-tabbed to the third/last field. Same validations check points apply to the credit card overlay when the customer clicks the "save & close" button

  Examples:
    | add_or_edit |
    | Add         |
    | Edit        |
