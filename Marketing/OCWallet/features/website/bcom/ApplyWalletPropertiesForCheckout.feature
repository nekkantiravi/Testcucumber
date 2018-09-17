# Author: OC Wallet Team
# Date Created: 06/02/2014
# Date Signed Off: TBD


Feature: Apply Wallet Propreties to a Purchase in Checkout

  Mingle Link: http://mingle/projects/market/cards/250

  #######################################################
  # Story Title: MCOM UI :: Signed in with Applied Offer
  # Mingle Link: http://mingle/projects/market/cards/1327
  #######################################################

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that ELIGIBLE OFFERS section is not displaying in order review page.
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I add an "available and orderable" product to my bag
    Then I checkout to reach the "order review" page as a "signed in" user
    And I should not see ELIGIBLE OFFERS section on order review page

  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario Outline: Verify that appropriate error message is displayed when user tries to apply promo code with empty data manually in checkout pages
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I add an "available and orderable" product to my bag
    And I checkout to reach the "<page>" page as a "signed in" user
    And I leave promocode field empty and apply
    Then I should see the error:
      | BCOM | Please enter a valid promotion code. |

  Examples:
    | page                 |
    | shipping and payment |
    | order review         |

  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing @feature_promotions
  Scenario Outline: Verify that appropriate error message is displayed when user tries to apply invalid SUPC manually in checkout pages
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I add an "available and orderable" product to my bag
    And I checkout to reach the "<page>" page as a "signed in" user
    And I apply a "invalid SUPC" in checkout page:
      | promo_code  |
      | X19G4RXG7K3 |
    Then I should see the error:
      | BCOM | The promotion code X19G4RXG7K3 is invalid or expired. Please enter a valid promotion code or remove to proceed with checkout. |

  Examples:
    | page                 |
    | shipping and payment |
    | order review         |

  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing @feature_promotions
  Scenario Outline: Verify that appropriate error message is displayed when user tries to apply invalid or expired promo code manually in checkout pages
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I add an "available and orderable" product to my bag
    And I checkout to reach the "<page>" page as a "signed in" user
    And I apply a "invalid promocode" in checkout page:
      | promo_code |
      | abcdef     |
    Then I should see the error:
      | BCOM | The promotion code ABCDEF is invalid or expired. Please enter a valid promotion code or remove to proceed with checkout. |

  Examples:
    | page                 |
    | shipping and payment |
    | order review         |

  #############################################################
  # Story Title: BCOM:: View and apply reward cards in Checkout
  # Mingle Link: http://mingle/projects/market/cards/1317
  #############################################################

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that the message is being displayed in the checkout pages when there is no reward cards in your bwallet
    Given I visit the web site as a registered user
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "less_than_2500_points"
    When I add an "available and orderable" product to my bag
    Then I checkout to reach the "shipping and payment" page as a "signed in" user
    And I should see "There are no Reward Cards available in your bWallet." message in reward cards section
    And I should able to click on bwallet
    Then I should see Wallet Page
    # Note: In the above step we are checking, in the message bwallet text is displaying as link or not

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @coremetrics @Marketing_CBT
  Scenario: Verify that the reward cards are being displayed in payment option section when there is reward cards in my bwallet
    Given I visit the web site as a registered user
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "rewards"
    When I navigate to My Wallet page from My Account page
    When I add an "available" product to my bag
    And I ensure to be on "shopping bag" page
    # updated the step name to avoid duplicate step issue
    And I checkout to reach the "shipping & payment" page as a "signed in" user
    Then I should see reward cards in payment option section
    # Note: We will verify reward cards are being displayed in payment option section and they should be unchecked by default
    # Note: In the above step we will verify "Reward Card title" , "Expiration date", "EXPIRING SOON" text, Card balance

   @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that the reward cards section is not being displayed in payment option section in shipping & payment page when the user don't have a Loyallist Account added to my profile
    Given I visit the web site as a registered user
    When I add an "available" product to my bag
    And I checkout to reach the "shipping and payment" page as a "signed in" user
    Then I should not see the reward cards in payment option section

################################################################################################
# Story Title: BCOM: In shipping&payment page, reward cards number is displaying with CID number
# Mingle Link: http://mingle/projects/market/cards/1911
################################################################################################

@14H @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that reward cards saved in wallet display without CID number in checkout shipping & payment page
    Given I visit the web site as a registered user
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "rewards"
    And I add an "available and orderable" product to my bag
    And I checkout to reach the "shipping and payment" page as a "signed in" user
    Then I should see the reward cards stored in wallet are displayed in reward cards section without CID number

  #############################################################################
  # Story Title: BCOM:: Display Reward Cards applied from Wallet on Review page
  # Mingle Link: http://mingle/projects/market/cards/1906
  #############################################################################

  @use_regression @14H @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that reward cards saved in wallet selected during checkout are displayed on order review page
    Given I visit the web site as a registered user with no stored credit cards
    When I navigate to Loyallist Account Association page from My Account
    And I can associate my account by loyallist number using "rewards"
    Then I navigate to My Wallet page from My Account page
    And I capture the reward cards from wallet page
    And I add an "available and orderable" product to my bag
    And I ensure to be on "shopping bag" page
    And I checkout to reach the "shipping & payment" page as a "signed in" user
    Then I should see the reward cards stored in wallet are displayed in reward cards section
    When I select a reward card
    Then I should see the reward card amount is deducted from order total
#    When I continue checking out to reach the "responsive order review" page
#    Then I should see the selected reward card in PAYMENT METHOD section