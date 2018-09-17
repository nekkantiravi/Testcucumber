# Author: OC Wallet Team
# Date Created: 04/24/2014
# Date Signed Off:


Feature: Remove Offers stored in My Wallet

  Mingle Link: http://mingle/projects/market/cards/249

  ###########################################################
  # Story Title: MCOM UI :: Remove Offers stored in My Wallet
  # Mingle Link: http://mingle/projects/market/cards/1314
  ###########################################################

  @14G @wip @use_manual @artifact_shopapp @priority_low @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that UI of offer delete confirmation overlay on my wallet page matches with the comps
    Given I visit the web site as a registered user with manually stored offers in wallet
    When I navigate to My Wallet page from My Account page
    And I click on the 'X' next to the offer
    Then I should see UI of delete confirmation overlay of offers is matches with the comps

  #################################################################################
  # Story Title: MCOM UI :: Confirm Display for Removing Offers stored in My Wallet
  # Mingle Link: http://mingle/projects/market/cards/1316
  #################################################################################

  @14G @wip @use_manual @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that user is able to successfully remove the star rewards promotions from my wallet
    Given I visit the web site as a registered user with stored star rewards offers in wallet
    When I navigate to My Wallet page from My Account page
    And I select on the 'x' icon for any star reward offer from the offer list
    Then  I should see "Are you sure you want to delete this Macy's Star Pass?" message on delete confirmation overlay
    And I should see the additional message "Note: Macy's Star Passes can't be re-added once deleted" on delete confirmation overlay
    When I select "yes,delete" button on delete confirmation overlay
    Then I should not see the deleted offer in the offer list
    And I should see "Changes saved to your wallet" message on top in wallet page

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that user is able to successfully remove the non star rewards promotions from my wallet
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    And I remove any non star reward offer from the offer list
    # Note: As part of above step we will also verify the message "Are you sure you want to delete <offer name>?" on delete confirmation overlay displaying properly or not
    Then I should not see the deleted offer in the offer list
    And I should see "Changes saved to your wallet." on top of the wallet page

  @14G @wip @use_manual @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario Outline: Verify that no and cancel options for star rewards offers on delete promotion confirmation overlay
    Given I visit the web site as a registered user with stored star rewards offers in wallet
    When I navigate to My Wallet page from My Account page
    And I select on the 'X' icon for any star reward offer from the offer list
    Then  I should see the "Are you sure you want to delete this Macy's Star Pass?" message on delete confirmation overlay
    And I should see the additional message " Note: Macy's Star Passes can't be re-added once deleted" on delete confirmation overlay
    When I select "<cancel_options>" button on delete confirmation overlay
    Then I should see the selected offer is not deleted from the offer list in wallet page

  Examples:
    | cancel_options |
    | no             |
    | cancel         |

  @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario Outline: Verify the no and cancel options for non star rewards offers on delete promotion confirmation overlay
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    And I cancel out of removing any non star reward offer from the offer list by selecting "<cancel_option>" button on delete confirmation overlay
    # Note: As part of above step we will also verify the message "Are you sure you want to delete <offer name>?" on delete confirmation overlay displaying properly or not
    Then I should see the selected offer is not deleted from the offer list in wallet page

  Examples:
    | cancel_option |
    | no            |
    | cancel        |

  @use_regression @use_dsv @domain_marketing @project_oc_wallet @mode_domestic @priority_medium
  Scenario: Verify that user is able to successfully remove the non star rewards promotions from my wallet for DSV
    Given I visit the web site as a guest user
    When I create a new profile
    And I pick a promo code from deals and promotion page
    And I navigate to My Wallet page from My Account page
    And I add wallet eligible offer manually on wallet page
    Then I should see offer is added to wallet
    When I remove any non star reward offer from the offer list
    # Note: As part of above step we will also verify the message "Are you sure you want to delete <offer name>?" on delete confirmation overlay displaying properly or not
    Then I should not see the deleted offer in the offer list
    And I should see "Changes saved to your wallet." on top of the wallet page