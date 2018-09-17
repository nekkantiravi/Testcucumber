Feature: Wallet Page Validations

  @use_regression @domain_marketing @launch_call
  Scenario: Validate all the important links on Wallet Page
  Given I visit the web site as a signed in user
    When I navigate to My Wallet page from My Account page
    Then I validate all links return valid response

  @use_regression @domain_marketing @launch_call
  Scenario: Verify that user is able to add credit card to my wallet
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to My Wallet page from My Account page
    And I add a credit card with all possible card_type to my wallet
      | card_type               |
      | Macy's                  |
    And I should see the added cards in the list of my credit or debit cards

  @use_regression @domain_marketing @launch_call
  Scenario: Verify that user is able to add a valid online only offer promo code to wallet manually
    Given I visit the web site as a new registered user
    When I navigate to My Wallet page from My Account page
    And I added wallet eligible offer manually on wallet page
    Then I should see offer is added to wallet
    And I should see "Changes saved to your wallet." on top of the wallet page
    # Note: As part of above step we will check the offer is added in my offers section or not

  @use_regression @domain_marketing @launch_call
  Scenario:Verify the user is able to successfully delete a credit card from my wallet
    Given I visit the web site as a registered user with 2 stored credit cards
    When I navigate to My Wallet page from My Account page
    And I delete any credit card
    Then I should see "Changes saved to your wallet." on top of the wallet page
    And I should see credit card is deleted from my wallet page

  @use_regression @domain_marketing @launch_call
  Scenario:Verify that user is able to edit a credit card stored in my wallet by opening edit credit card overlay by using either pencil icon or credit card icon
    Given I visit the web site as a registered user with 1 stored credit cards
    When I navigate to My Wallet page from My Account page
    And  I update credit card details
    Then I should see "Changes saved to your wallet." on top of the wallet page
# Note: As part of above step we will open edit credit card overlay by using either pencil icon or credit card icon and then update card details and save
    And I should see updated credit card details on my wallet page
# Note: As part of above step we will verify that the edited card details are pre-populated in edit credit card overlay

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify user is able to see all the details for manually stored offers in my wallet
    Given I visit the web site as a registered user with manually 4 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see the offer details on my wallet page
    Then I should see add offer or pass and deals and promotions button on my wallet page
    And I select grid view option from view options
    Then I should see the grid offer details on my wallet page
