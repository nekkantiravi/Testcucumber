# Author: UFT QE
# Date Created: 05/10/2017
# Story: B-63118

Feature: Verification of new legal copy in the footer on customer service pages


  @artifact_shopapp @domain_customer @priority_high @mode_domestic @project_UFT @release_17I
  Scenario Outline: Verify new legal notice text in the footer on Customer Service pages
    Given I visit the web site as a guest user
    When I navigate to "<customer_service_page>" footer link under customer service
    Then I verify the new legal notice text in the footer:
      | 2017 macys.com is a registered trademark. All rights reserved |
      | Macys.com, LLC, 680 Folsom St. San Francisco, CA 94107        |
    Examples:
      | customer_service_page |
      | customer service      |
      | para ayuda            |














