Feature: Verify quick bag functionality

Scenario: Verify add view functionality in quickbag for normal product

Given I visit the web site as a guest user
When I search for "jeans"
Then I should see "slp" pages
When I select a random product in slp page
Then I should see "PDP" pages
When I add the product to bag
	And I click on Macys logo
Then I should see the added product item details in quickbag
