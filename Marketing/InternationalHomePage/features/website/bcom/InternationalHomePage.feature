Feature: International Home Page

  @bcom_international_home_page @Marketing_CBT
  Scenario: As a customer I want to verify whether the seasonal ad is present in iship mode and if it gets expanded when clicked
    When I visit the web site as a guest user in "iship" mode
    And I close the welcome mat if it's visible
    Then I should see the seasonal promo in the header
    When I click on the seasonal promo in the header
    Then the seasonal promo should be expanded

  @bcom_international_home_page @Marketing_CBT
  Scenario: As as customer I want to verify whether the country flag is present and whether the currency is correct in iship mode
    When I visit the web site as a guest user in "domestic" mode
    And I navigate to international context page
    And I change country to "Canada"
    And I close the welcome mat if it's visible
    Then I should see the correct iship currency for Canada in the header
    And I should see the correct country flag for Canada

