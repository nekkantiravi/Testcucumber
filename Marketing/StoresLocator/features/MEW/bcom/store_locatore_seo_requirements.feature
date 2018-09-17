@v-1-24537 @seo @store_locator @domain_marketing
Feature: As a member of the marketing team, I want' Bloomingdales' store pages to rank higlhy in search results so users can easily find accurate info.
  Stories included:
  24537 :: BM2 :: Store Locator :: SEO requirements


  @mew_regression
  Scenario: Verify that store details page markup is correct
    Given I am on stores page
    And I navigate to any Store Details page
    Then I should see a title tag that is formatted as follows:
      | <store name> - <city>, <state> |
    And I should see a meta description tag that is formatted as follows:
      | Shop <store name> in <city>, <state>. Find updated store hours, services, events and more |
    And I should see a canonical tag with and href value matching all parts except that 'www1.' is replaced with 'm.'


    @mew_regression
  Scenario: Verify that store locator landing page markup is correct
    Given I visit the mobile web home page
    And I am on stores page
    Then I should see a title tag with the following text:
      | Bloomingdale's Store Locator |
    And I should see a meta description tag with the following text:
      | Locate & shop the nearest Bloomingdale's store. Find updated store hours, services, events and more. |
