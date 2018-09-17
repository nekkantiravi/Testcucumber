Feature: Sample Feature test
  @tag
  Scenario: Sample scenario
    Given I visit the web site as a guest user
    And sample test: I click the logo
    And sample test: I navigate to sample page


  Scenario: Navigate to search result page
    Given I visit the web site as a guest user
    And I enter search keyword
        |Search_Type    |Keyword  |
        |Category Keyword  |Jeans    |
        |Category Keyword  |shirts    |
#        |Facet Keyword  |red dress  |
#        |Facet Keyword  |Clearance shoes|
#        |Facet Keyword  |cheap tablecloths|
#        |Brand Keyword  |elegant gowns    |
#        |Facet Keyword  |jeans women's    |
#        |Facet Keyword  |multi-color stoles|
#        |Brand Keyword  |calvin klein jeans|

    And Geberate Report

    #Then I should see search results page
#
