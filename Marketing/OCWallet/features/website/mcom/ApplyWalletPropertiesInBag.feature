# Author: OC Wallet Team
# Date Created: 06/07/2017
# Date Signed Off: TBD


Feature: Apply Wallet Properties to a Purchase in Bag

  Mingle Link: http://mingle/projects/market/cards/252

  #######################################################################
  # Story Title: MCOM UI :: Signed in with Applicable Offers in Wallet
  # Mingle Link: http://mingle/projects/market/cards/1390
  #######################################################################`

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing @use_browser
  Scenario: Verify offer widget overlay is displaying as per comps when user having non applicable offers in wallet
    Given I visit the web site as a registered user with 1 stored future offers
    When I add an "available" product to my bag
    # Note: As part of above step add product which is not eligible for addffers in wallet
    Then I should see Shopping Bag Page
    And I verify message "You've got great offers in your wallet, but they don't apply to the items in your bag" in offer widget section
    # Note: Text is displaying in offer widget section
    When I open offer widget overlay by clicking on see my offers link
    Then I should see offer widget overlay is displaying as per comps
    # Note: As part of above step, we are verifying "These offers don't apply to the items in your bag:" text shown below heading
    # "offer name", "start and end dates", "details & exclusions link" are displaying for each offer

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify offer widget section is displaying as per comps when user having no offers in wallet
    Given I visit the web site as a registered user with no stored offers
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    And I should see "Add offers to your wallet now!" text offer widget section
    # Note: In above step we are verifying "Add offers to your wallet now!" and "find an offer" link are displaying
    When I should able to click find an offer in shopping bag page for signed in user
    Then I should redirect to deals & promotions page

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify user is able to click on details and exclusions link in offer widget overlay
    Given I visit the web site as a registered user with manually 1 stored offers in wallet
    # Note: As part of above step add more than one offer to wallet
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    When I open details and exclusions for offer on offer widget overlay
    When I select 'back to offers' in offer widget overlay
    Then I should return to widget again

   @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify user is navigated to deals and promotions page by clicking "find a different offer" link in offer widget overlay when items in bag are not eligible for any offers
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I add an "available" product to my bag
    # Note: As part of above step add product which is not eligible only for offers in wallet
    Then I should see Shopping Bag Page
    When I open offer widget overlay by clicking on see my offers link
    Then I should see 'find a different offer' button in non applicable offer widget overlay
    When I select 'find a different offer' button in non applicable offer widget overlay
    Then I should redirect to deals & promotions page

  @use_regression @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify user is able to close or cancel offer widget overlay
    Given I visit web site as registered user with Organic registration SUPC in wallet
    When I add an "available and supc_eligible" product to my bag
    # Note: As part of above step add product which is eligible only for offers in wallet
    When I navigate to shopping bag page from add to bag page
    Then I should see Shopping Bag Page
    And I adjust qty to make product eligible for SUPC
    When I open offer widget overlay by clicking on see my offers link
   # Then I should see Wallet Page
    And I select "Close" in offer widget overlay
    Then I should see widget overlay is closed

  @use_regression @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @feature_promotions
  Scenario:Verify that in bag appropriate error message is displayed when user tries to apply invalid or expired promo code manually
    Given I visit the web site as a registered user with manually 2 stored offers in wallet
    When I add an "available" product to my bag
    When I navigate to shopping bag page from add to bag page
    Then I should see Shopping Bag Page
    When I apply a "invalid or expired promo code" in shopping bag page
    Then I should see the promo code error in shopping bag:
      | MCOM | The promo code you entered is invalid. Please check the spelling, expiration date or excluded items. |

  @use_regression @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that in shopping bag appropriate error message is displayed when user tries to apply invalid SUPC manually
    Given I visit the web site as a registered user with manually 2 stored offers in wallet
    When I add an "available" product to my bag
    When I navigate to shopping bag page from add to bag page
    Then I should see Shopping Bag Page
    And I apply a "invalid SUPC" in shopping bag page
     # | promo_code  |
     # | X19G4RXG7K3 |
    Then I should see the promo code error in shopping bag:
      | MCOM | The promo code you entered is invalid. Please check the spelling, expiration date or excluded items. |

  @use_regression @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @feature_promotions
  Scenario:Verify that appropriate error message is displayed in bag when user tries to apply used or expired SUPC manually
    Given I visit the web site as a registered user with manually 2 stored offers in wallet
    When I add an "available" product to my bag
    When I navigate to shopping bag page from add to bag page
    Then I should see Shopping Bag Page
    When I apply a "used or expired SUPC" in shopping bag page
    Then I should see the promo code error in shopping bag:
      | MCOM | Promo Code: ?. We're sorry. We do not recognize the Promo Code you entered. Please try again. |

  @use_regression @14H @artifact_navapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that appropriate error message is displayed in bag page when user tries to apply promo code with empty data manually
    Given I visit the web site as a registered user with manually 2 stored offers in wallet
    When I add an "available" product to my bag
    When I navigate to shopping bag page from add to bag page
    Then I should see Shopping Bag Page
    When I leave promocode field empty and apply
    Then I should see the promo code error in shopping bag:
      | MCOM | You haven't entered a promo code yet. Please enter it now. |
      # Note: Verifying variations of link names to view saved offers is covering as part of #1715 story.

  #######################################################
  # Story Title: MCOM UI :: Signed out Customer in Bag
  # Mingle Link: http://mingle/projects/market/cards/1391
  #######################################################

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify user is able to navigate to deals and promotions page when clicks on find an offer link from wallet widget for non-signed in user
    Given I visit the web site as a guest user
    When I add an "available" product to my bag
    Then I should see wallet widget with 'sign in' and 'find an offer' links
    When I click find an offer in shopping bag page for guest user
    Then I should redirect to deals & promotions page

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify user is able to navigate back to the shopping bag when user signed in from shopping bag page by clicking sign in link from wallet widget
    Given I visit the web site as a non-signed in user
    When I add an "available" product to my bag
    Then I should see wallet widget with 'sign in' and 'find an offer' links
    When I click on 'sign in' link from wallet widget
    Then I should see Sign In Page
    When I sign in using existing username and password
    Then I should redirect to shopping bag page

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic
  @use_s4a_stable @domain_marketing
  Scenario:Verify that user is able to navigate back to the shopping bag when user created new profile by clicking sign in link from wallet widget
    Given I visit the web site as a guest user
    When I add an "available" product to my bag
    Then I should see wallet widget with 'sign in' and 'find an offer' links
    When I click on 'sign in' link from wallet widget
    Then I should see Sign In Page
    When I create a new profile
    Then I should see my account Page

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify user is able to navigate back to the shopping bag when user created new profile by clicking sign in link from wallet widget
    Given I visit the web site as a guest user
    When I add an "available" product to my bag
    Then I should see Shopping Bag Page
    And I should see wallet widget with 'sign in' and 'find an offer' links
    When I click on 'sign in' link from wallet widget
    Then I should see Sign In Page
    When I create a new profile
    Then I should see my account Page

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that wallet widget in shopping bag page when user has no applicable offers in his wallet
  # Pre-requisite: Customer should have some saved offers which are not applicable to his order
  # Note: We will verify the non applicable offers for selected product in  "shipping and payment" page
    Given I visit the web site as a non-signed in user having offers in wallet
    When I add an "available" product to my bag
    Then I should see wallet widget with 'sign in' and 'find an offer' links
    When I click on 'sign in' link from wallet widget
    When I sign in using existing username and password
    Then I verify message "You've got great offers in your wallet, but they don't apply to the items in your bag" in offer widget section

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that user able to see 'Add offer to wallet now!' on shopping bag page
    Given I visit the web site as a guest user
    When I add an "available" product to my bag
    Then I should see wallet widget with 'sign in' and 'find an offer' links
    When I click on 'sign in' link from wallet widget
    Then I should see Sign In Page
    When I sign in using existing username and password
    Then I should see "Add offers to your wallet now!" text in wallet widget as per comp

   ########################################################################################
   #Note: User is not required to Sign out Once user is Logged In for current scenario.
   #######################################################################################

  @use_regression @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @use_dsv @domain_marketing @use_domain_qual  @dsv_desktop_sev2
  Scenario: Verify that user able navigate to deals & promotions page when clicks on find an offer link from shopping bag page
    Given I visit the web site as a registered user with no stored offers
    When I add an "available" product to my bag
    When I navigate to shopping bag page from add to bag page
    And I should see "Apply an offer to see the savings!" text in offer widget section for signed in user

  #############################################################
  # Story Title: MCOM :: Shopping Bag: Dynamic Wallet Link Name
  # Mingle Link: http://mingle/projects/market/cards/1715
  #############################################################

  @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify guest user is able to see find an offer link in wallet widget of shopping bag page
    Given I visit the web site as a guest user
    And I add an "available and orderable" product to my bag
    Then I should see Shopping Bag Page
    And I should see 'find an offer' link in offer widget section for guest user

  @use_regression @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that find an offer link is displayed in shopping bag page for registered user when user have no offers in wallet
    Given I visit the web site as a registered user with no stored offers
    When I add an "available" product to my bag
    When I navigate to shopping bag page from add to bag page
    Then I should see Shopping Bag Page
    And I should see "Apply an offer to see the savings!" text in offer widget section for signed in user

  @use_regression @14H @artifact_navapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that see my offers link is displaying in shopping bag page when user having non applicable offers in wallet
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I add an "available and supc_not_eligible" product to my bag
    # Note: As part of above step add product which is not eligible for offers in wallet
    When I navigate to shopping bag page from add to bag page
    Then I should see Shopping Bag Page
    And I should see 'see my offers' link in offer widget section

