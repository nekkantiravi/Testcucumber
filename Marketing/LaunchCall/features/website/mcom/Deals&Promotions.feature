Feature: Validate multiple scenarios during launch call

  @use_regression @domain_marketing @launch_call
  Scenario: Verify the wallet page redirection functionality from Deals & Promotion page when user does not have a profile
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I should see the following details on deals and promotions page
      | title           | expected_message                                                                                   |
      | cards_image     | wallet icon on deals and promotions page                                                           |
      | customer_wallet | my wallet                                                                                          |
      | wallet_desc     | From payment info to savings passes, keep everything you need to shop in one easy-to-manage place! |
    When I click on get started in deals and promotions page
    Then I should see Sign In Page

  @use_regression @domain_marketing @launch_call
  Scenario: Verify the updated text is displayed in Wallet ad on Deals & Promotions page for signed in user
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to deals and promotions page
    Then I should see the following details on deals and promotions page
      | title           | expected_message                                                                                            |
      | cards_image     | wallet icon with cards on deals and promotions page                                                         |
      | customer_wallet | first name of customer                                                                                      |
      | wallet_desc     | If you haven't already, add your credit card to your wallet to check out faster & get new offers instantly! |
      | find_out_more   | button to find out more about My Wallet                                                                     |
    When I click on 'add to wallet' button for any valid offer in deals and promotions page
    Then I should see the message as "Offer added - use it when you checkout" in tool tip
    And I should see offer is added to wallet
    When I navigate to deals and promotions page
    When I click on go to my wallet in deals and promotions page
    Then I should see Wallet Page