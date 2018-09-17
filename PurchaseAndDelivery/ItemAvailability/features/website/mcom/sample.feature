Feature: Sample feature file

  @sample_tag
  Scenario: Visit sample web page & use shared steps to test it
    When I navigate to sample page
    When I search for "nike women"
    Then I should be in Search Landing page
    And I select a random member product
    Then I should be redirected to PDP page
    When I add product to my bag from standard PDP Page
    Then I should be redirected to ATB page