# Author: OC Wallet Team
# Date Created: 04/17/2014
# Date Signed Off:


Feature: Manually Add Offers on Wallet page

  Mingle Link:http://mingle/projects/market/cards/265

  #############################################################
  # Story Title: MCOM UI :: Manually Add Offer from Wallet Page
  # Mingle Link: http://mingle/projects/market/cards/1290
  #############################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that user is able to add a valid online only offer promo code to wallet manually
    Given I visit the web site as a new registered user
    When I navigate to My Wallet page from My Account page
    And I added wallet eligible offer manually on wallet page
    Then I should see offer is added to wallet
    And I should see "Changes saved to your wallet." on top of the wallet page
    # Note: As part of above step we will check the offer is added in my offers section or not

  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that user is able to close the add an offer overlay by clicking outside of the overlay
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I click "outside of the overlay" after entering promo code in add offer overlay
    Then I should see that the offer is not saved

  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario Outline:Verify that user able to close the add an offer overlay by clicking on close or cancel
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I select "<close_action>" after entering promo code in add offer overlay
    Then I should see that the offer is not saved

  Examples:
    | close_action |
    | X            |
    | Cancel       |

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that offers list sequence when new offers added from wallet page and already stored offers have same expiration dates
  # Pre-requisite: User's wallet should have manually added and auto provisioned offers with same expiration dates
  # QE Comments: As part of this scenario, verifying the offers list sequence when adding offer from add an offer  overlay in wallet
   # Given I visit the web site as a registered user with manually 2 stored offers in wallet
    Given I visit the web site as a new registered user
    When I navigate to My Wallet page from My Account page
    Then I verify sorting order for list offers in wallet
  #      | sorting order will be as follows:                                                                                                                     |
  #      | If one Offer is SUPC,will take precedence over Public Offer(Promo Code)                                                                |
  #      | If both are SUPC, auto-provisioned will take precedence                                                                                |
  #      | If both Offers are Public Offers, the order will be by added first                                                                     |
  #      | Offers that are active that have the same expiration date as an inactive offer will get precedence based on the soonest effective date |


  @use_regression @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic
  @use_s4a_stable @domain_marketing
  Scenario:Verify the messaging on my wallet page under my offers & passes section
    Given I visit the web site as a registered user with manually 2 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see "Redeemed & expired offers will be automatically deleted." redeemed offer message at the bottom of page

  #################################################################
  # Story Title: MCOM UI:: Update Add Offer Overlay Button to "Add"
  # Mingle Link: http://mingle/projects/market/cards/1586
  #################################################################

  @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic
  @use_s4a_stable @domain_marketing
  Scenario:Verify the add button UI for on add offer overlay
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see 'Add' button on add offer overlay
    # Note: This is updation for story #1290

  #######################################################################################
  # Story Title: MCOM UI:: Wallet: Restrict Customer From Saving Registry Completion Code
  # Mingle Link: http://mingle/projects/market/cards/1660
  #######################################################################################

  @use_regression @14H @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that error message is displayed in the wallet page, when Customer tries to add a registry completion promo code to her Wallet
 #Prerequisite: Customer is signed in and has a valid registry completion promo code
    Given I visit the web site as a guest user
    When I create a new wedding registry with event date within 180 days
    And I capture the completion promo code from registry manager page
    When I navigate to My Wallet page from My Account page
    Then I should see "Changes saved to your wallet." success message after saving valid registry completion promo code on wallet page

  @use_regression @use_dsv @domain_marketing @project_oc_wallet @mode_domestic @use_browser  @dsv_desktop_sev2
  Scenario: Verify that user is able to add a valid online only offer promo code to wallet manually for DSV
    Given I visit the web site as a guest user
    When I create a new profile
    And I pick a promo code from deals and promotion page
    And I navigate to My Wallet page from My Account page
    And I add wallet eligible offer manually on wallet page
    Then I should see offer is added to wallet
    And I should see "Changes saved to your wallet." on top of the wallet page
    # Note: As part of above step we will check the offer is added in my offers section or not
