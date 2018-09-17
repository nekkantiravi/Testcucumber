# Author: OC Wallet Team
# Date Created: 07/13/2017
# Date Signed Off:


Feature: UI :: Foundational Development

  Mingle Link: http://mingle/projects/market/cards/266

  ###############################################################################################################
  # Story Title: MCOM: Error messages in add offers overlay are not displaying as expected for special characters
  # Mingle Link: http://mingle/projects/market/cards/3023
  ###############################################################################################################

  @use_regression @14K @project_oc_wallet @mode_domestic @artifact_shopapp @priority_medium @domain_marketing
  Scenario: Verify that the error message on add offers overlay on wallet page when user enters any special character
    Given I visit the web site as a registered user
    When I navigate to My Wallet page from My Account page
    Then I should see error message "Please remove any special characters." on add offers overlay when entering the special characters

  @use_regression @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
  Scenario: Verify that offers count and See Saved Offers link in My wallet section on My account page when user having more than one offer stored in wallet
    Given I visit the web site as a registered user with manually 2 stored offers in wallet
    When I navigate to 'My Account' page
    Then I should see the number of offers in my bWallet
    And I should see 'See Saved Offers' link is taking me to my wallet page
    # User will navigate to My Wallet page by clicking on 'see them now' link

  # 2017-07-12 YC03673: Invalid Scenario as of 17M. There is no different in the content of Wallet section even if there is 1 offer or 3 offers
#  @use_regression @14K @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
#  Scenario: Verify that offers count and see it now link in My wallet section on My account page when user having only 1 offer stored in wallet
#    Given I visit the web site as a registered user with manually 1 stored offers in wallet
#    When I navigate to 'My Account' page
#    And I should see the number of offers in my bWallet
#    Then I should see "great news! there is 1 offer in your wallet." message with "See It Now" link on My wallet section
#    And I should see 'see it now' link is taking me to my wallet page
#    # User will navigate to My Wallet page by clicking on 'see it now' link

  # 2017-07-12 YC03673: Invalid Scenario as of 17M with Responsive My Account. Checkit Out link and text below will no more be part of Wallet section of Responsive My Account page
#  @14K @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
#  Scenario: Verify that user is navigated to wallet page by clicking "check it out" link in My wallet section on My account page when user having offers stored in wallet
#    Given I visit the web site as a registered user with manually 2 stored offers in wallet
#    When I navigate to 'My Account' page
#    Then I should see "my wallet is a great new way to store & manage your Macy's offers & payment options online." wallet message with "check it out" link on My wallet section
#    And I should see 'check it out' link is taking me to my wallet page
#    # User will navigate to My Wallet page by clicking on 'check it out' link

  # 2017-07-12 YC03673: Invalid Scenario as of 17M with Responsive My Account.
#  @use_regression @14K @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
#  Scenario: Verify that user is navigated to deals and promotions page by clicking "deals & promotions" button in My wallet section on My account page when user having offers stored in wallet
#    Given I visit the web site as a registered user with manually 1 stored offers in wallet
#    When I navigate to 'My Account' page
#    Then I should see My wallet section is displaying "with" offers count
#    And I should see 'deals & promotions' link is taking me to deals & promotions page
    # User will navigate to deals and promotions page by clicking "deals & promotions" button in My wallet section

  # 2017-07-12 YC03673: Invalid Scenario as of 17M with Responsive My Account.
#  @use_regression @use_bat_next @14K @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
#  Scenario: Verify that user is navigated to deals and promotions page by clicking "deals & promotions" button in My wallet section on My account page when user not having offers stored in wallet
#    Given I visit the web site as a registered user
#    When I navigate to 'My Account' page
#    Then I should see My wallet section is displaying "without" offers count
#    And I should see 'deals & promotions' link is taking me to deals & promotions page
    # User will navigate to deals and promotions page by clicking "deals & promotions" button in My wallet section

  # 2017-07-12 YC03673: Invalid Scenario as of 17M with Responsive My Account.
#  @14K @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
#  Scenario: Verify that user is navigated to wallet page by clicking "check it out" link in My wallet section on My account page when user not having offers stored in wallet
#    Given I visit the web site as a registered user
#    When I navigate to 'My Account' page
#    Then I should see My wallet section is displaying "without" offers count
#    And I should see "say hello to a great new way to store & manage your Macy's offers & payment options online." wallet message with "check it out" link on My wallet section
#    And I should see 'check it out' link is taking me to my wallet page
    # User will navigate to My Wallet page by clicking on 'check it out' link

  @deprecated_15GA @14K @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
  Scenario: Verify that user is navigated to my profile page by clicking "Add it to your profile" link in credit card section on My account page when user not having prop card
    Given I visit the web site as a registered user
    When I navigate to 'My Account' page
    Then I should see credit card section is displaying without prop card
    And I should see 'Add it to your profile' link is taking me to my profile page
    # User will navigate to my profile page by clicking "Add it to your profile" link in credit card section on My Account

  @deprecated_15GA @14K @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
  Scenario: Verify that user is navigated to credit card application page by clicking "apply now" link in credit card section on My account page when user not having prop card
    Given I visit the web site as a registered user
    When I navigate to 'My Account' page
    Then I should see credit card section is displaying without prop card
    And I should see 'apply now' link is taking me to credit card application page
    # User will navigate to credit card application page by clicking "apply now" link in credit card section on My Account

  @deprecated_15GA @14K @project_oc_wallet @mode_domestic @artifact_shopapp @priority_high @domain_marketing
  Scenario: Verify that user is navigated to macys credit card page by clicking "find out more" button in credit card section on My account page when user not having prop card
    Given I visit the web site as a registered user
    When I navigate to 'My Account' page
    Then I should see credit card section is displaying without prop card
    And I should see 'find out more' button is taking me to macys credit card page
    # User will navigate to macys credit card page by clicking "find out more" button in credit card section on My Account


