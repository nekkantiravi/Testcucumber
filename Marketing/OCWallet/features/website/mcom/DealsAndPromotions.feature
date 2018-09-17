# Author: OC Wallet Team
# Date Created: 05/19/2014
# Date Signed Off: TBD


Feature: Enhancements to Deals and Promotions Page
  # Mingle Link: http://mingle/projects/market/cards/248
  #######################################################################################
  # Story Title: Nav App :: MCOM UI :: Log in to My Wallet from Deals and Promotions page
  # Mingle Link: http://mingle/projects/market/cards/1235
  #######################################################################################

  @14G @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify the wallet page redirection functionality from Deals & Promotion page when user is not signed in
    Given I visit the web site as a non-signed in user
    When I navigate to deals and promotions page
    Then I should see the following details on deals and promotions page
      | title           | expected_message                                                                                   |
      | cards_image     | wallet icon on deals and promotions page                                                           |
      | customer_wallet | my wallet                                                                                          |
      | wallet_desc     | From payment info to savings passes, keep everything you need to shop in one easy-to-manage place! |
    When I click on get started in deals and promotions page
    Then I should see Sign In Page
    When I sign in using existing username and password
    Then I should see Wallet Page
    # Note: As part above scenario, verify the page re-directions and wallet details on Deals & Promotion page

  @14G @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
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
    When I create a new profile
    Then I should see my account Page
    # Note: As part above scenario, verify the page re-directions and wallet details on Deals & Promotion page

  ############################################################################################
  # Story Title: Nav App :: MCOM UI :: Manually add an offer from the Deals and Promotion Page
  # Mingle Link: http://mingle/projects/market/cards/1343
  ############################################################################################

  @use_bat_next @14G @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @use_dsv @domain_marketing @use_regression @saucelab_M @saucelab_M_F1 @use_domain_qual
  Scenario: Verify that manually added offers from deals and promotions page are displayed in user's Wallet for signed in user
    Given I visit the web site as a guest user
    When I create a new profile
    And I navigate to deals and promotions page
    And I click on 'add to wallet' button for any valid offer in deals and promotions page
    Then I should see the message as "Offer added - use it when you checkout" in tool tip
    And I should see added offer in my wallet page

  @14G @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @use_browser
  Scenario: Verify that signed in user is able to add all wallet eligible offers to wallet from deals and promotions page
    Given I visit the web site as a registered user
    When I navigate to deals and promotions page
    And I add all wallet eligible offers from deals and promotions page
    Then I should see the added offers in my wallet page

  @14G @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @use_regression
  Scenario: Verify that manually added offers from deals and promotions page are displayed in user's Wallet for not signed in user
    #Given I visit the web site as a non-signed in user
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    And I click on 'add to wallet' button for any offer in deals and promotions page
    Then I should see Sign In Page
    When I sign in using existing username and password
    Then I should see promotions page
 #   Then I should redirect to deals & promotions page
    When I click on 'add to wallet' button for any valid offer in deals and promotions page
    Then I should see the message as "Offer added - use it when you checkout" in tool tip
    Then I should see offer is added to wallet
   # And I should see added offer in my wallet page
    # Note:  for above step, We will navigate to wallet page and verify the offer is added to wallet page

  @14G @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @use_regression @ssf
  Scenario: Verify that manually added offers from deals and promotions page are displayed in user's Wallet for not registered user
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    And I click on 'add to wallet' button for any offer in deals and promotions page
    Then I should see Sign In Page
    When I create a new profile
    Then I should be navigated to My Account Page
    #Then I should see my account Page
    # Note:  for above step, We will navigate to wallet page and verify the offer is added to wallet page

  @14G @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify the tool tip message box in deals & promotions page when mouse over on any promotion which is already added to my wallet
  # Pre-requisite: User already added offer to wallet from D&P and "in my wallet" button is displayed for that offer
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I navigate to deals and promotions page
    Then I should see 'in my wallet' button disabled in deals and promotions page for already added offer

  ############################################################################
  # Story Title: MCOM UI :: Update Layout of Current Deals and Promotions page
  # Mingle Link: http://mingle/projects/market/cards/1358
  ############################################################################

  @14G @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify the functionality of details and exclusions for public offers on Deals and Promotion Page
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    And I click on details and exclusions link for any public offer
    Then I should see an overlay with the details and exclusions text
    # Note: The overlay UI should match with the comps attached to story in mingle
    # AK Note: Overlay story is: 10874

  ################################################################################
  # Story Title: Nav App :: MCOM UI :: Enhancements to Details and Exclusions link
  # Mingle Link: http://mingle/projects/shopping_experience_2/cards/1344
  ################################################################################

  @14G @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify the functionality of details and exclusions for offers in Deals and Promotion Page
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    And I click on details and exclusions link for any public offer
    Then I should see name of the offer along with the details and exclusions information on overlay
    When I select 'X' on details and exclusions overlay
    Then I should see the details and exclusions overlay is closed
    # Note: The overlay UI should match with the comps attached to story in mingle story#10480
    # Details and Exclusions text should be displayed as mentioned in STELLA or DB.
    # Note: Details and exclusions link will verify for the appropriate offer(public offer)

  #####################################################################
  # Story Title: Nav App::On D&P Page, if offer expiration date is greater than 2 years, suppress expiration date
  # Mingle Link: http://mingle/projects/market/cards/3024
  #####################################################################

  @14K @wip @project_oc_wallet @mode_domestic @artifact_navapp @priority_medium @domain_marketing
  Scenario Outline: Verify that expiry date is not displaying for offers which are having expiry date greater than two years for both guest and registered users
    Given I visit the web site as a <user_type> user
    When I navigate to deals and promotions page
    Then I should see expiry date is not display for offers which are having expiry date greater than two years

    Examples:
      | user_type  |
      | registered |
      | guest      |

  @14K @wip @project_oc_wallet @mode_domestic @artifact_navapp @priority_medium @domain_marketing
  Scenario Outline: Verify that expiry date is display for global offers which are having expiry date less than two years for both guest and registered users
    Given I visit the web site as a <user_type> user
    When I navigate to deals and promotions page
    Then I should see expiry date is display for offers which are having expiry date less than two years
    Examples:
      | user_type  |
      | registered |
      | guest      |

  #########################################################################################
  # Story Title: MCOM UI :: Improve understanding of how Wallet works on Deals & Promo page
  # Mingle Link: http://mingle/projects/market/cards/3610
  #########################################################################################

  @15A @project_oc_wallet @mode_domestic @artifact_navapp @priority_high @domain_marketing
  Scenario Outline: Verify that user is navigated to wallet campaign page by clicking on "see how it works" link in wallet section of deals and promoitons page
    Given I visit the web site as a <user_type> user
    When I navigate to deals and promotions page
    Then I should navigate to wallet campaign page by clicking 'see how it works' link in wallet section
    Examples:
      | user_type  |
      | registered |
      | guest      |
  # Note :: By clicking on "see how it works" link from deals and promotions page user should navigate to "http://www1.macys.com/m/campaign/splash/wallet/faq?cm_sp=macys_deals_and_promotions-_-my_wallet-_-how_it_works"

  ######################################################################################################
  # Story Title: MCOM:: Coremetrics: Add "see how it works" link to promote Wallet education on D&P page
  # Mingle Link: http://mingle/projects/market/cards/3620
  ######################################################################################################

  @15A @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
  Scenario: Verify that expiry date is not displaying for offers in wallet page which are having expiry date greater than two years
    Given I visit the web site as a registered user with no CC
    When I navigate to My Wallet page from My Account page
    And I added normal offers which are having more than two years on wallet page
    Then I should see expiry date is not displayed on wallet page for offers which are having expiry date greater than two years
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    And I should see expiry date is not displayed on shopping bag page for offers which are having expiry date greater than two years
    When I checkout until I reach the "shipping and payment" page as a "signed in" user
    Then I should see expiry date is not displayed on shipping and payment page for offers which are having expiry date greater than two years

  @15A @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
  Scenario: Verify that expiry date is not displaying for offers in wallet page which are having expiry date less than two years
    Given I visit the web site as a registered user with no CC
    When I navigate to My Wallet page from My Account page
    And I added normal offers which are having less than two years on wallet page
    Then I should see expiry date is displayed on wallet page for offers which are having expiry date less than two years
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    And I should see expiry date is displayed on shopping bag page for offers which are having expiry date less than two years
    When I checkout until I reach the "shipping and payment" page as a "signed in" user
    Then I should see expiry date is displayed on shipping and payment page for offers which are having expiry date less than two years

  @15A @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
  Scenario: Verify that expiry date is not displaying for SUPC offers in wallet page which are having expiry date greater than two years
    Given I visit the web site as a registered user with no CC
    When I navigate to My Wallet page from My Account page
    And I added SUPC offers which are having more than two years on wallet page
    Then I should see expiry date is not displayed on wallet page for offers which are having expiry date greater than two years
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    And I should see expiry date is not displayed on shopping bag page for offers which are having expiry date greater than two years
    When I checkout until I reach the "shipping and payment" page as a "signed in" user
    Then I should see expiry date is not displayed on shipping and payment page for offers which are having expiry date greater than two years

  @15A @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
  Scenario: Verify that expiry date is not displaying for SUPC offers in wallet page which are having expiry date less than two years
    Given I visit the web site as a registered user with no CC
    When I navigate to My Wallet page from My Account page
    And I added SUPC offers which are having less than two years on wallet page
    Then I should see expiry date is displayed on wallet page for offers which are having expiry date less than two years
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    And I should see expiry date is displayed on shopping bag page for offers which are having expiry date less than two years
    When I checkout until I reach the "shipping and payment" page as a "signed in" user
    Then I should see expiry date is displayed on shipping and payment page for offers which are having expiry date less than two years

  # Version One: B-39081
  @artifact_navapp @mode_domestic @release_16B @priority_medium @domain_marketing @feature_add_to_wallet @project_UFT @use_regression
  Scenario: Verify that user is able to add offers to Wallet from deals and promotions page for registered user
    Given I visit the web site as a registered user
    And I navigate to deals and promotions page
    When I click on 'add to wallet' button for any valid offer in deals and promotions page
    Then I should see the message as "Offer added - use it when you checkout" in tool tip
    And I should see offer is added to wallet
#    And I should see added offer in my wallet page

  # Version One: B-39081
  @artifact_navapp @mode_domestic @release_16B @priority_medium @domain_marketing @feature_add_to_wallet @project_UFT @use_regression
  Scenario: Verify that guest user is navigated to sign in page when he selects add to wallet in deals and promotions page
    Given I visit the web site as a guest user
    And I navigate to deals and promotions page
    When I click on 'add to wallet' button for any valid offer in deals and promotions page
    Then I should see Sign In Page

  # Version One: B-46723
  @artifact_navapp @release_16E @priority_medium @domain_marketing @project_UFT @mode_domestic @use_regression
  Scenario: Verify he tag is not populated in bright tag data dictionary object on DP Page
    Given I visit the web site as a guest user
    When I navigate to deals and promotions page
    Then I should not see hE tag in bright tag data dictionary object

  @artifact_navapp @mode_domestic @release_17A @priority_medium @domain_marketing @project_UFT @use_regression
  Scenario: Verify the updated text is displayed in Wallet ad on Deals & Promotions page for signed in user
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to deals and promotions page
    Then I should see the following details on deals and promotions page
      | title           | expected_message                                                                                            |
      | cards_image     | wallet icon with cards on deals and promotions page                                                         |
      | customer_wallet | first name of customer                                                                                      |
      | wallet_desc     | If you haven't already, add your credit card to your wallet to check out faster & get new offers instantly! |
      | find_out_more   | button to find out more about My Wallet                                                                     |
    When I click on go to my wallet in deals and promotions page
    Then I should see Wallet Page
    # Note: As part above scenario, verify the page re-directions and wallet details on Deals & Promotion page
