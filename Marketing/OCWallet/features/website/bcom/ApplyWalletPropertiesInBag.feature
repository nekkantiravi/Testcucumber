# Author: OC Wallet Team
# Date Created: 07/03/2014
# Date Signed Off: TBD


Feature: Apply Wallet Properties to a Purchase in Bag

  Mingle Link: http://mingle/projects/market/cards/252

  ###########################################################
  # Story Title: BCOM:: Message Available Reward Cards in Bag
  # Mingle Link: http://mingle/projects/market/cards/1399
  ###########################################################

  @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify reward cards header and message when user has unexpired reward card balance as greater than zero
    #Pre-requisite: User should have associated loyallist  account and user should have ">0" balance.
    Given I visit the web site as a registered user
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "rewards"
    And I capture the reward card balance from wallet page
    And I add an "available" product to my bag
    Then I should see Reward cards header and "Your Reward Card balance of $XX.XX can be applied at checkout." message

  @use_regression @14H @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify reward cards header and message is not displaying when user does not have unexpired reward cards or has zero reward card balance
    # Pre-requisite: User should have associated loyallist  account and user should have "0" balance.
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "less_than_2500_points"
    And I add an "available" product to my bag
    And I ensure to be on "shopping bag" page
    Then I should not see Reward cards header and message on shopping bag page

  @use_regression @14H @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify reward cards header and message is not displaying when user does not have a loyallist account associated
    # Pre-requisite: User should not have associated loyallist  account
    Given I visit the web site as a registered user with no stored credit cards
    And I add an "available" product to my bag
    And I ensure to be on "shopping bag" page
    Then I should not see Reward cards header and message on shopping bag page

  #######################################################
  # Story Title: BCOM:: Apply Offers to a Purchase in Bag
  # Mingle Link: http://mingle/projects/market/cards/1400
  #######################################################

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that ELIGIBLE OFFERS section is displaying in shopping bag page when the user having only future offers in wallet
    Given I visit the web site as a registered user with 1 stored future offers
     # Note: Only future offers should be added
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    And I should see ELIGIBLE OFFERS section is displaying with "There are no valid offers available in your bWallet. Any offers that you saved previously may have expired." message

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that ELIGIBLE OFFERS section is displaying in shopping bag page when the user dont have offers in wallet
    Given I visit the web site as a registered user
   # Note: Only future offers should be added
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    And I should see ELIGIBLE OFFERS section is displaying with "There are no valid offers available in your bWallet. Any offers that you saved previously may have expired." message

  @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @feature_promotions
  Scenario:Verify that appropriate error message is displayed when user tries to apply invalid promo code or SUPC manually in shopping bag pages
    Given I visit the web site as a registered user
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    When I apply invalid/expired/not-valid promo code or SUPC in shopping bag page
    Then I should see the error:
      | BCOM | We're sorry, but we do not recognize the promotion Code you entered. Please check and re-enter the Promotion Code. If you need assistance, please call Customer service at 1-800-777-0000. |

  ########################################################
  # Story Title: BCOM:: Info / Exclusions Overlay in Bag
  # Mingle Link: http://mingle/projects/market/cards/1411
  #######################################################

  @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that info/Exclusions overlay is displaying for all offers
    Given I visit the web site as a registered user with manually 4 stored offers in wallet
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    And I should be able to click/tap on info/exclusions link for all offers to see offer details
    # Note: In the above step, verify the offer Exclusions text is displayed as per comps and
    # verify offer name and text are supplied from offer name and Legal Disclaimer fields in DB/Stella
    # Note: In the above step, both and online and instore codes are displayed as per comp

  @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario Outline: Verify that user can close info/Exclusions overlay in bag by clicking close button or clicking off overlay
    Given I visit the web site as a registered user with manually 1 stored offers in wallet
    And I add an "available" product to my bag
    Then I open info/exclusions overlay by clicking info/exlcusions link
    When I close info/exclusions overlay by selecting "<close_action>" on overlay
    Then I should see offer info and exclusions overlay is closed

  Examples:
    | close_action                 |
    | close                        |
    | outside of the overlay       |
    | click 'escape' from keyboard |
  # Note: Verify the info/exclusions overlay is closing while clicking on close(X) and click anywhere outside of the overlay

  @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing @use_browser
  Scenario: Verify in bag more than one code are displaying as comma separated in info/Exclusions overlay
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I saved omnichannel offer having more than one promo code in wallet
    # Note: As part of above step add offer to user having more than codes supplied from Stella.
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    When I open info/exclusions overlay by clicking info/exlcusions link
    Then I should see all codes of offer are displaying with comma separated
    # Note: As part of above step, verify that codes displaying are getting wrapped if there is more codes supplied from DB/Stella.

  @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify IN STORE CODE text is not displaying in info/exclusions overlay in bag when offer is not having instore code
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I saved online only offer promo code in wallet
    And I add an "available" product to my bag
    Then I open info/exclusions overlay by clicking info/exlcusions link
    And I should not see instore code in overlay
    And I should see online codes are displaying in overlay

  ##########################################################
  # Story Title: BCOM:: Purchase summary in Bag Style Update
  # Mingle Link: http://mingle/projects/market/cards/1860
  ##########################################################

  @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario Outline:Verify the UI for Purchase summary section in shopping bag page is updated for both guest and signed in user when offer is not applied
    Given I visit the web site as a <user_type> user
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    And I should see "HAVE A PROMO CODE?" text for promo code
    # Note: Customer should see 'HAVE A PROMO CODE?' text for promo code not applied scenario

  Examples:
    | user_type  |
    | guest      |
    | registered |

  ########################################################################
  # Story Title: BCOM:: "Sign In" copy, link and flow off guest bag
  # Mingle Link: http://mingle/projects/market/cards/2280
  #######################################################################

  @14I @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify sign in link is displaying for guest user in shopping bag page if bag contains items.
    Given I visit the web site as a guest user
    When I add an "available" product to my bag
    Then I should see "SIGN IN to see your offers and Reward Card balance." copy on the shopping bag page

  @use_regression @14I @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @coremetrics @use_domain_qual
  Scenario:Verify that user is able to sign in from wallet widget section from shopping bag
    Given I visit the web site as a guest user
    When I add an "available" product to my bag
    When I navigate to shopping bag page from add to bag page
    And I click on 'sign in' link from widget offer section in the shopping bag
    Then I should see Sign In Page
    When I sign in using existing username and password
    Then I should see Shopping Bag Page

  @use_regression @14I @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that user is able to navigate to My Account Page from shopping bag after signing up
    Given I visit the web site as a guest user
    When I add an "available" product to my bag
    When I navigate to shopping bag page from add to bag page
    And I click on 'sign in' link from widget offer section in the shopping bag
    Then I should see Sign In Page
    When I create a new profile
    Then I should see my account Page

