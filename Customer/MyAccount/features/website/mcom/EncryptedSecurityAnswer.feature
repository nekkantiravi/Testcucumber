# Author: UFT
# Date Created: 8/18/2016
# Date Signed Off:
# Version One: B-48655

Feature: As a product owner, I would like to update the Easy Returns page with new text

  @artifact_shopapp @priority_medium @domain_customer @project_UFT @use_regression @migrated_to_sdt
  Scenario: Verify Security Q&A overlay password filed type when signed in
    Given I visit the web site as a guest user
    And I create a new profile
    And I sign out from my current profile
    And I sign in with created profile and proceed till security question
    Then I should see security Q&A setup
    And I should see security answer field type as password on security Q&A overlay