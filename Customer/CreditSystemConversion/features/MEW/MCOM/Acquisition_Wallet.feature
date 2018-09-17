#####################################################################################
# Story             : B-69157 : TC::BUS::UI::MEW::MCOM::Guest User Adds New Card to Wallet
# Author            : Credit Systems Conversion Carryover :: QE Team
#####################################################################################

Feature: As a guest user, I want to see a message prompting me to sign in/create an account when I return to macys.com Mobile (App, MEW) after completing a credit card application, receiving approval AND selecting add card to wallet, so that my card will be added to my wallet.

#Feature be wrapped in kill switch:  creditAddCardToWalletEnabled
# Pre-Requisite:: Customer applied for cobrand card and received a cobrand card online, and added it to her wallet via the Fusion acquisition flow.
  @use_manual @domain_customer @project_credit_service @artifact_shopapp @release_17D
 Scenario: verify that guest user should add his new card in wallet via the Fusion acquisition flow by signing into his account
   Given I visit the web site as a guest user
   And I completed Citi Fusion application process by selecting add card to wallet.
   When I return to .com site by clicking on return to macys
   Then I should see a overlay message instructing user to sign in or create an account on sign in page
   When I close the message and sign in to my account page
   Then I should see a new card added in Wallet page

  @use_manual @domain_customer @project_credit_service @artifact_shopapp @release_17D
  Scenario: verify that guest user should add his new card in wallet via the Fusion acquisition flow by creating a new account
    Given I visit the web site as a guest user
    And I completed Citi Fusion application process by selecting add card to wallet.
    When I return to .com site by clicking on return to macys
    Then I should see a overlay message instructing user to sign in or create an account on sign in page
    When I close the message and create a new account
    Then I should see a new card added in Wallet page

  @use_manual @domain_customer @project_credit_service @artifact_shopapp @release_17D
  Scenario: Verify that new card should not be added into wallet when user navigates away from sign-in page without signing in or creating an account
    Given I visit the web site as a guest user
    And I completed Citi Fusion application process by selecting add card to wallet.
    When I return to .com site by clicking on return to macys
    Then I should see a overlay message instructing user to sign in or create an account on sign in page
    When I navigate away from sign in page without signing in or creating an account
    Then I should not see new card added in Wallet page

  @use_manual @domain_customer @project_credit_service @artifact_shopapp @release_17D
  Scenario: Verify that add card to wallet details should NOT be retained when user navigates away from sign-in page without signing in or creating an account
    Given I visit the web site as a guest user
    And I completed Citi Fusion application process by selecting add card to wallet.
    When I return to .com site by clicking on return to macys
    Then I should see a overlay message instructing user to sign in or create an account on sign in page
    When I navigate away from sign in page without signing in or creating an account
    Then I should not retain add card to wallet details