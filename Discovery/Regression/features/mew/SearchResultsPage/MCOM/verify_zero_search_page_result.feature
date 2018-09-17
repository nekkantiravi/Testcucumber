Feature: As a product owner, I would like to ensure customer does not receive technical error when they search with 0 inputs in search bar. 

   @Mew_UFT @domain_discovery @release_17Z @project_UFT @domain_mew_discovery @use_mew_regression
	 Scenario: Verify technical error with zero input in search bar
     Given I visit the mobile web site as a registered user without add CC
     When I navigate the global navigation menu as follows:
      | Wallet |
     And I click on search go button
     Then I should see the "oc_my_wallet" Page