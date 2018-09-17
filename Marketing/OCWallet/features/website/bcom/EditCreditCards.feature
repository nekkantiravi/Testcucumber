# Author: OC Wallet Team
# Date Created: 06/06/2017
# Date Signed Off: TBD


Feature: Edit Credit Cards in My Wallet

  Mingle Link: http://mingle/projects/market/cards/269

  ###############################################################
  # Story Title: BCOM UI :: ShopApp :: Edit Credit and Debit Card
  # Mingle Link: http://mingle/projects/market/cards/1188
  ###############################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @use_domain_qual
  Scenario:Verify user is able to edit debit or credit cards
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    And  I update credit card details
    Then I should see updated credit card details on my wallet page
    # Note: As part of above step we will verify that the edited card details are pre-populated in edit credit card overlay
    And I should see card information is updated in the database

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario Outline:Verify that user is able to close or cancel out of editing a credit or debit card on My Wallet page
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I can "<close_action>" out of "<open_action>" a credit card on My Wallet page

    Examples:
      | close_action | open_action |
      | close        | editing     |
      | cancel       | editing     |

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that updating one saved credit card with another saved credit card data i.e; card type and card number the card being updated should be deleted
  and changes should be updated for the existing credit card with same card type and card number
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I update credit card details with same card type and card number as another saved card
    Then I should see the credit card being updated is deleted
    And I should see existing saved credit card details with same card type and card number are updated

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that use my shipping address field functionality in edit a credit or debit card overlay if there is a saved default address in address book
  # Pre-requisite: There should be a saved default address in user's address book
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see billing address fields except email are populated in edit card overlay with data from default address in address book when use my shipping address field is selected
    # Note: As part of above step we will verify that use my shipping address field is not selected by default
    # And also we will verify that the data in following fields is populated: First Name, Last Name, Address 1, Address 2, City, State, Zip Code, Phone Number

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that use my shipping address field is not displayed in edit a credit or debit card overlay if there are no saved addresses in address book
  # Pre-requisite: There should be no saved address in user's address book
    Given I visit the web site as a registered user with no saved addresses and with stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should not see use my shipping address field in edit card overlay

  #######################################################
  # Story Title: BCOM UI:: Making Credit Card Default
  # Mingle Link: http://mingle/projects/market/cards/1244
  #######################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @use_domain_qual
  Scenario:Verify that if first credit card is being added to wallet then it is set as default card
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see set as default option is checked and disabled on add credit card overlay
    When I add a credit or debit card with following card type to my wallet:
      | card_type  |
      | MasterCard |
    Then I should see the added credit card is saved as default card

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that set as default option is unchecked in add a credit or debit card overlay if there is at least one credit or debit card in wallet
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see set as default option is unchecked on add credit card overlay

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that set as default option is unchecked when editing a credit card which is not default also once saved verify the order of display on wallet page
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I select set as default option and save in edit credit card overlay
    # Note: As part of above step we will verify that set as default option is unchecked on edit credit card overlay
    Then I should see the saved default card at the top of the list of credit cards

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that if card has been saved as default and when edit a credit or debit card overlay for this card is accessed the set as default option is checked and disabled
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    Then I should see set as default option is checked and disabled on edit credit card overlay for default card

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that user is able to select set as default option while adding a new credit card and save the information
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I add a credit card to my wallet by selecting set as default option
    Then I should see the newly added credit card is saved as default card
