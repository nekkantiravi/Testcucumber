Feature: DSV scenarios for my account page

  @dsv_desktop_sev2 @use_regression @domain_customer
  Scenario: My Account - my preferred store Section - select a store
    And I create a new profile
    And I navigate to my account page
    When I click on 'My Preferences' option from left navigation menu
    Then I should see "My Preferences" as page title
    When I click on 'Add a Store' option of the Preferred Store
    Then I should be navigated to Preferred store page
    When I enter the ZIP Code into text field
    And I click on the 'Search' button
    And I select the other store
    Then I should be navigated to summary state of Preferred store
    And I should see 'Change Store' option on the summary state of Preferred store

  @dsv_desktop_sev2 @use_regression @domain_customer @akamai_waf
  Scenario: My Account - Rendering My Account Page as a newly registered user
    Given I visit the web site as a guest user
    And I create a new profile
    When I navigate to my account page
    Then I verify that My Account dashboard is rendered properly
    Then I verify the My Account Pages are rendered properly
      | my profile                    |
      | my preferences                |
      | My Address Book               |
      | My Wallet                     |
      | My Perks                      |
      | Order Status                  |
      | My Bloomingdale's Credit Card |
      | Wish List                     |
      | My Points                     |
      | FAQs                          |

  @dsv_desktop_sev2 @use_regression @domain_customer
  Scenario: Verify Change store on my account
    And I create a new profile
    And I navigate to my account page
    When I click on 'My Preferences' option from left navigation menu
    Then I should see "My Preferences" as page title
    When I click on 'Add a Store' option of the Preferred Store
    Then I should be navigated to Preferred store page
    When I enter the ZIP Code into text field
    And I click on the 'Search' button
    And I select the other store
    Then I should be navigated to summary state of Preferred store
    And I should verify that the selected store details are displayed in the my preferred store Section on My Account Page

  @dsv_desktop_sev2
  Scenario: Verify Create/Update address with Zip code starting from "0" - My Wallet
    Given I visit the web site as a guest user
    And I create a new profile
    When I navigate to My Wallet page from My Account page
    And I click on add a credit card on wallet page
    Then I add a credit card from my bwallet page with Zip code starting with "0"
    When I click on edit or remove a card link
    Then I should not see zip code trimmed

    # Notes:
    # Test case description
    # BCOM: Verify Create/Update address with Zip code starting from "0".
    # My wallet -  Zip code should not be trimmed.
    # Test case steps (BCOM)
    # 1. Navigate to bloomingdales.com
    # 2. Create profile and goto My wallet from my account page
    # 3. Add a new address - 583 Newark Ave, Jersey City, NJ   07306



