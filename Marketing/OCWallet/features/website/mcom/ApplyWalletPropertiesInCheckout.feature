# Author: OC Wallet Team
# Date Created: 04/22/2014
# Date Signed Off:


Feature: Apply Wallet Properties to a Purchase in Checkout

  Mingle Link: http://mingle/projects/market/cards/250

  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that appropriate error message is displayed when user tries to apply invalid promo code manually in checkout pages
    Given I visit the web site as a registered user with manually 2 stored offers in wallet
    When I add an "available and orderable" radical product to my bag
    And I checkout until I reach the "shipping and payment" page as a "signed in" user
    And I apply a "invalid promocode" in checkout page:
      | promo_code |
      | abcdef     |
    Then I should see the error:
      | MCOM | Please try a different promo code; the one you entered was invalid or expired.|

  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that appropriate error message is displayed when user tries to apply invalid SUPC manually in checkout pages
    Given I visit the web site as a registered user with manually 2 stored offers in wallet
    When I add an "available and orderable" radical product to my bag
    And I checkout until I reach the "shipping and payment" page as a "signed in" user
    And I apply a "invalid SUPC" in checkout page:
      | promo_code  |
      | X19G4RXG7K3 |
    Then I should see the error:
      | MCOM | Please try a different promo code; the one you entered was invalid or expired.|


  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that appropriate error message is displayed when user tries to apply used or expired SUPC manually in checkout pages
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I add an "available and orderable" radical product to my bag
    And I checkout until I reach the "shipping and payment" page as a "signed in" user
    And I apply a "used or expired SUPC" in checkout page:
      | promo_code   |
      | X19G4RXG7K3K |
    Then I should see the error:
      | MCOM | That promo code doesn't apply to the items in your shopping bag. Please choose a different code or remove to proceed with checkout. |

   @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that appropriate error message is displayed when user tries to apply promo code with empty data manually in checkout pages
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I add an "available and orderable" radical product to my bag
    And I checkout until I reach the "shipping and payment" page as a "signed in" user
    And I leave promocode field empty and apply
    Then I should see the error:
      | MCOM | Please enter a promo code. |

  ####################################################################
  # Story Title: MCOM UI :: Signed in with Applicable Offers in Wallet
  # Mingle Link: http://mingle/projects/market/cards/1225
  ####################################################################

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify guest user should not see offer widget overlay
    Given I visit the web site as a guest user
    And I add an "available and orderable" product to my bag
    When I checkout until I reach the "shipping" page as a "guest" user
    Then I should not see "see_my_offers" link in order summary section
    # Note: In above step we are checking guest user can apply through promo code section only

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing @use_regression @saucelab_M @saucelab_M_F1 @use_domain_qual
  Scenario:Verify is able to select different payment method other than default
    Given I visit the web site as a registered user with "third party and prop card" in wallet
    # Note: In above step add one third party card(as default) and one prop card to wallet
    And I add an "available" product to my bag
    And I checkout until I reach "shipping & payment" page as a "signed in" user
    Then I should see user is able to select other than default credit card from credit cards section

