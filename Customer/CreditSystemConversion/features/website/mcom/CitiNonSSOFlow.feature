Feature: User can apply and activate card through online

  @domain_customer @artifact_shopapp
  Scenario Outline: Verify Apply Card Functionality
    Given I visit the web site as a guest user
    When I navigate to the "credit services" page from footer
    And I clicked on Apply Now button
    Then I should redirect to "<fusion_page>" page
    Then I Enter Customer details "<user_type>" and apply for card in "<fusion_page>"
    Examples:
      | user_type     | fusion_page       |
      | approval_data | fusion_apply_card |

