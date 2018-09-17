Feature: Beauty Box foxy Feature Files

   @dsv @dsv_desktop_sev2 @zeus
  Scenario: As a guest user validate the CTA on about page
    Given I visit the web site as a guest user
    And I launch beautybox about page url
    Then I see the cta status on about page

  @dsv @dsv_desktop_sev2 @zeus
  Scenario Outline: As a guest user I want to see PTP full size products when user navigates from about page
  Given I visit the web site as a guest user
  And I launch beautybox about page url
  Then I validate "<shopTheProduct>" ptp monthly products "<ptpPrice>"

  Examples:
  |shopTheProduct|ptpPrice|
  |SHOP FULL SIZE PRODUCTS|productsDesc|

  @precondition
  Scenario Outline: Reset back the original data after executing automation
    When I visit postgresql database to insert or delete "<records>" as a precondition
    And I visit the database to insert "<availabilty>" max availabilty of next "<months>" months

    Examples:
      |records|availabilty|months|
      |postexecution_insertdata.sql|500|6|
