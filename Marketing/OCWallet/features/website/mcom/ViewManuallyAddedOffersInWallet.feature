# Author: Marketing Regression Team
# Date Created: 06/22/2017
# Date Signed Off:


Feature: View Manually Added Offers in Wallet

  Mingle Link: http://mingle/projects/market/cards/264

  #############################################################################################
  # Story Title: MCOM UI :: View Manually Provisioned Offers in Wallet - Functionality ONLY
  # Mingle Link: http://mingle/projects/market/cards/1287
  # AND
  # Story Title: MCOM UI :: View Manually Provisioned Offers in Wallet - Data Verification ONLY
  # Mingle Link: http://mingle/projects/market/cards/1435
  #############################################################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify user is able to see all the details for manually stored offers in my wallet
    Given I visit the web site as a registered user with manually 4 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see the offer details on my wallet page
    #  As part of the above step we need to verify the following details for all the manually stored offers:
    #  Name of offer (from STELLA), Special Redemption Code (from STELLA), Effective date stating "valid xx-xx-xx - xx-xx-xx" for ALL offers
    # (the first date is the day the offer will become effective (mm/dd/yy) and the 2nd date is the expiration date (mm-dd--yy)), Details and Exclusions (STELLA),
    # "x" to remove offer

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that user is able to see add offer or pass button and deals and promotions button on my wallet page
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see add offer or pass and deals and promotions button on my wallet page

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify user is able to open details and exclusions overlay for an offer
    Given I visit the web site as a registered user with manually 4 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see the Details and Exclusion text on opening details and exclusions overlay for any manually added offer

  @use_regression @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify the text on my wallet page when user do not have any stored offers
    Given I visit the web site as a registered user with no stored offers
    When I navigate to My Wallet page from My Account page
    Then I should see text "Add savings passes, deals, promos & more to save online & in-store!" in my offers & passes section list view

  @use_regression @14G @artifact_shopapp @priority_medium @project_oc_wallet @mode_domestic @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that customer's first name followed by Wallet is displayed on wallet page
    Given I visit the web site as a registered user with manually 4 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see customer's first name followed by Wallet on my wallet page
    # Note: Ex: Flow's wallet

  ##############################################################
  # Story Title: MCOM UI::Deals & Promo Button on My Wallet Page
  # Mingle Link: http://mingle/projects/market/cards/1437
  ##############################################################

  @use_regression @14G @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that user can navigate to Deals & promotion page when clicking on "deals & promotions" button
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    And I click on deals & promotions button on My Wallet page
    Then I should redirect to deals & promotions page

  ###############################################################
  # Story Title: MCOM UI :: View Offers in My Wallet as Grid View
  # Mingle Link: http://mingle/projects/market/cards/1430
  ###############################################################

  @14H @artifact_shopapp @priority_high @project_oc_wallet
  @use_s4a_stable @domain_marketing @mode_domestic
  Scenario:Verify that in grid view user is able to see all the details for manually stored offers in my wallet
    Given I visit the web site as a registered user with manually 4 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    And I select grid view option from view options
    Then I should see the grid offer details on my wallet page
    #  Note: As part of the above step we need to verify the following details for all the manually stored offers:
    #  Name of offer (from STELLA), Special Redemption Code (from STELLA), Effective date stating "valid xx-xx-xx - xx-xx-xx" for ALL offers
    # (the first date is the day the offer will become effective (mm/dd/yy) and the 2nd date is the expiration date (mm-dd--yy)), Details and Exclusions (STELLA),
    # "x" to remove offer

  @use_regression @14H @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @domain_marketing
  Scenario: Verify that in grid view user is able to open details and exclusions overlay for an offer
    Given I visit the web site as a registered user with manually 3 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    And I select grid view option from view options
    Then I should see the details and exclusion text on opening details and exclusions overlay for any manually added offer on grid view

    # Note: As part of above step, we are clicking on 'details and exclusions' link of an offer

  @use_regression @14H @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that offers list sequence in grid view if offers have different expiration dates
   # Pre-requisite: User's wallet should have manually added and auto provisioned offers with different expiration dates
    Given I visit the web site as a registered user with manually 4 stored offers with different expiration dates in wallet
    When I navigate to My Wallet page from My Account page
    And I select grid view option from view options
    Then I verify sorting order for grid offers in wallet
    # Note: As per above step, Offers sorting should be based on expiration date

  @use_regression @14H @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that offers list sequence in grid view if offers have same expiration dates
   # Pre-requisite: User's wallet should have manually added and auto provisioned offers with different expiration dates
    Given I visit the web site as a registered user with manually 2 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    And I select grid view option from view options
    Then I verify sorting order for grid offers in wallet
    # Note: As per above step, Offers sorting should be based on expiration date

  ###########################################################
  # Story Title: MCOM UI:: Grid View Option on My Wallet Page
  # Mingle Link: http://mingle/projects/market/cards/1436
  ###########################################################

  @use_regression @14H @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify that list view and grid view toggle functionality when offers are stored in my wallet
    Given I visit the web site as a registered user with manually 4 stored offers in wallet
    When I navigate to My Wallet page from My Account page
    Then I should see default view as the list view along with red color as the selected state
    And I should see the grid view icon with grey color as the unselected state
    When I select grid view option from view options
    Then I should see the list view icon turned to gray color as the unselected state and grid view icon turns red as the selected state
    # Note: In the above steps we will verify UI with creative comps
    # Note: We will verify add to wallet functionality.

  @use_regression @14H @artifact_shopapp @priority_high @project_oc_wallet @mode_domestic @use_s4a_stable @domain_marketing
  Scenario:Verify list view and grid view toggle functionality when no offers are stored in my wallet
    Given I visit the web site as a registered user with no stored offers
    When I navigate to My Wallet page from My Account page
    Then I should see default view as the list view along with red color as the selected state
    And I should see the grid view icon with grey color as the unselected state
    Then I should see text "Add savings passes, deals, promos & more to save online & in-store!" in my offers & passes section list view
    When I select grid view option from view options
    Then I should see text "Add savings passes, deals, promos & more to save online & in-store!" in my offers & passes section grid view