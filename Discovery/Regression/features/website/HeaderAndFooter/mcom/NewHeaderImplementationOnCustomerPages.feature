#Author: Discovery QE
#Date Created: 17/08/2017

Feature: Verify new Header Redesign functionality on Customer Pages

  #Adding @wip as responsive sing-in page won't have Header FOB menu & Flyouts for this scenario validation
  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign @xbrowser_hfr @wip
  Scenario: Verify new header redesign functionality on create profile page in Domestic mode
    Given I visit the web site as a guest user
    When I navigate to signin page of "SITE" mode
    And I navigate to create profile page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality on My Account page in Domestic mode
    Given I visit the web site as a registered user
    When I navigate to my account page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality on my address book page in Domestic mode
    Given I visit the web site as a registered user
    When I navigate to my address book page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality on update profile page in Domestic mode
    Given I visit the web site as a registered user
    When I navigate to my profile page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality in CreditGateway page in Domestic mode
    Given I visit the web site as a registered user
    When I hover on My Account element on link rail
    And I verify the My Account Pages "MY MACY'S CREDIT CARD" are rendered properly in "Domestic" Mode
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality in CreditBenefits page in Domestic mode
    Given I visit the web site as a registered user
    When I hover on My Account element on link rail
    And I verify the My Account Pages "MY MACY'S CREDIT CARD" are rendered properly in "Domestic" Mode
    And I navigate to credit benefits page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality in Apply & Learn More page in domestic mode
    Given I visit the web site as a registered user
    When I hover on My Account element on link rail
    And I verify the My Account Pages "MY MACY'S CREDIT CARD" are rendered properly in "Domestic" Mode
    And I navigate to Apply & Learn More page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality in Order history page in domestic mode
    Given I visit the web site as a registered user
    And Navigate to "HnF Clean" viewtype in new header footer experience
    When I hover on My Account element on link rail
    And I verify the My Account Pages "MY ORDER HISTORY" are rendered properly in "domestic mode" Mode
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality in order details page
    Given I visit the web site as a guest user
    When I verify the My Account Pages "MY ORDER HISTORY" are rendered properly in "domestic mode" Mode
    And I select "submitted" order as a "guest" user
    Then I navigate to order details page as a "guest" user
    And I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality in Return selection page
    Given I visit the web site as a guest user
    When I verify the My Account Pages "MY ORDER HISTORY" are rendered properly in "domestic mode" Mode
    And I select "submitted" order as a "guest" user
    Then I navigate to order details page as a "guest" user
    When I select return items button in "OD" page
    Then I should navigate to return selection page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality in Return submit page
    Given I visit the web site as a guest user
    When I verify the My Account Pages "MY ORDER HISTORY" are rendered properly in "domestic mode" Mode
    And I select "submitted" order as a "guest" user
    Then I navigate to order details page as a "guest" user
    When I select return items button in "OD" page
    And I select items and continue to submit page
    Then I verify New Header Redesign implementation for clean in "domestic" mode

  @use_regression @domain_discovery @artifact_nodejs @project_HNFRedesign @release_17J @feature_hnf_redesign
  Scenario: Verify new header redesign functionality in Return confirmation page
    Given I visit the web site as a guest user
    When I verify the My Account Pages "MY ORDER HISTORY" are rendered properly in "domestic mode" Mode
    And I select "submitted" order as a "guest" user
    Then I navigate to order details page as a "guest" user
    When I select return items button in "OD" page
    And I select items and continue to submit page
    Then I navigate to confirmation page
    And I verify New Header Redesign implementation for clean in "domestic" mode