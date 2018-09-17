#Mobile Lean Lab BCOM
#Created: 8/21/2017
#Author: Maria Ortega
#Version-one Story: B-89237

Feature: BCOM::Fit Predictor
  Scenario: I verify that I am able to see the Fit Predictor in the PDP
    Given I goto Mobile Home page
    When I navigate the global navigation menu as follows:
      | MEN      |
      | Clothing |
      | Polos    |
    And I click on any random PDP
    Then I will verify that Fit Predictor is visible on PDP