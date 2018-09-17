#Date Signed Off:
#Version One: B-84615

Feature: As a product owner, I would like to add a hyperlink directing users to international returns policy page.

  @artifact_shopapp @domain_customer @release_17N @mode_domestic @project_UFT @use_regression @run_this
  Scenario: Verify international returns policy hyper link on easy returns page
    Given I visit the web site as a guest user
    When I navigate to easy returns page
    Then I should see "International Returns" header and "International Returns Policy" link
    And "International Returns Policy" link should be navigated to Customer service International Returns Page