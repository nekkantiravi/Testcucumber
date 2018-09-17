# Author: Chirila Claudiu
# Story:
# Date Created: 10/24/2017
# Date Signed Off:

Feature: Customer's contact history,status,date,and title are properly displayed.


  @RegMcom @domain_stores @omniclient @website
  Scenario: Customer's contact history,status,date,and title columns are displayed in History Contact tab

    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I click on My Book radio button
    And I type the name of a customer "KONSTANCE " in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I click on Contact History tab from Client Profile page
    Then the following columns are displayed in Contact History view
      | Status            |
      | Date              |
      | Title             |
      | Selling Associate |

  @RegBcom @domain_stores @omniclient @website
  Scenario: Mark all todos as completed
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I check the My To DOs count
    And I navigate to MY TASKS page
    Then I mark all TO DOs as completed TO DOS tab BLM
#      | EMAIL          |
#      | MAIL           |
#      | TEXT           |
#      | IN PERSON      |
#      | PHONE LEFT MSG |
#      | NO ACTION      |
      | PHONE COMPLETE |


  @RegMcom @domain_stores @omniclient @website
  Scenario: After creating a ToDo we check that the SA name is displayed, title is truncated after 20 characters, status
  is displayed as COMPLETED and the DATE field was the proper formatting
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I navigate to MY TASKS page
    And I click on the Create To Do button on MY TO DOS page
    And I enter a title "More than 20 characters in the title" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I input client name "KONSTANCE BACKET" in the input filed from MY TO DOS page
    And I select the full name "KONSTANCE BACKET" from client dropdown MY TO DOS page
    And I enter a description "KONSTANCE This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button
    And I navigate to MY TASKS page
    And I mark all TO DOs as completed TO DOS tab BLM
#      | EMAIL          |
#      | MAIL           |
#      | TEXT           |
#      | IN PERSON      |
#      | PHONE LEFT MSG |
#      | NO ACTION      |
  | PHONE COMPLETE |

    And I click on My Book radio button
    And I type the name of a customer "KONSTANCE" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I click on Contact History tab from Client Profile page
    Then the newly completed TO DO is displayed first in list CONTACT HISTORY "MORE THAN 20 CHARACTERS IN THE TITLE"
    And the STATUS of the newly completed TO DO is displayed as COMPLETED
    And the DATE is displayed in the proper format MY TO DOS page
    And the TITLE is truncated to allow maximum twenty characters MY TO DOS page
    And the NAME of the SA "THREE, EIGHTY" that created the TO DO is displayed MY TO DOS page

  @RegMcom @domain_stores @omniclient @website
  Scenario: Maximum 6 completed TODOs are displayed in CONTACT HISTORY view
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I navigate to MY TASKS page

    And I click on the Create To Do button on MY TO DOS page
    And I enter a title "MORE THAN 20 CHARACTERS IN THE TITLE" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I input client name "KONSTANCE BACKET" in the input filed from MY TO DOS page
    And I select the full name "KONSTANCE BACKET" from client dropdown MY TO DOS page
    And I enter a description "KONSTANCE This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button

    And I click on the Create To Do button on MY TO DOS page
    And I enter a title "MORE THAN 20 CHARACTERS IN THE TITLE" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I input client name "KONSTANCE BACKET" in the input filed from MY TO DOS page
    And I select the full name "KONSTANCE BACKET" from client dropdown MY TO DOS page
    And I enter a description "KONSTANCE This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button

    And I click on the Create To Do button on MY TO DOS page
    And I enter a title "MORE THAN 20 CHARACTERS IN THE TITLE" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I input client name "KONSTANCE BACKET" in the input filed from MY TO DOS page
    And I select the full name "KONSTANCE BACKET" from client dropdown MY TO DOS page
    And I enter a description "KONSTANCE This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button

    And I click on the Create To Do button on MY TO DOS page
    And I enter a title "MORE THAN 20 CHARACTERS IN THE TITLE" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I input client name "KONSTANCE BACKET" in the input filed from MY TO DOS page
    And I select the full name "KONSTANCE BACKET" from client dropdown MY TO DOS page
    And I enter a description "KONSTANCE This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button

    And I click on the Create To Do button on MY TO DOS page
    And I enter a title "MORE THAN 20 CHARACTERS IN THE TITLE" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I input client name "KONSTANCE BACKET" in the input filed from MY TO DOS page
    And I select the full name "KONSTANCE BACKET" from client dropdown MY TO DOS page
    And I enter a description "KONSTANCE This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button

    And I click on the Create To Do button on MY TO DOS page
    And I enter a title "MORE THAN 20 CHARACTERS IN THE TITLE" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I input client name "KONSTANCE BACKET" in the input filed from MY TO DOS page
    And I select the full name "KONSTANCE BACKET" from client dropdown MY TO DOS page
    And I enter a description "KONSTANCE This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button

    And I click on the Create To Do button on MY TO DOS page
    And I enter a title "MORE THAN 20 CHARACTERS IN THE TITLE" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I input client name "KONSTANCE BACKET" in the input filed from MY TO DOS page
    And I select the full name "KONSTANCE BACKET" from client dropdown MY TO DOS page
    And I enter a description "KONSTANCE This is a regression test" in the input field on MY TO DOS page
    And I click on the TO DO Save button

    And I navigate to MY TASKS page
    And I mark all TO DOs as completed TO DOS tab BLM
#      | EMAIL          |
#      | MAIL           |
#      | TEXT           |
#      | IN PERSON      |
#      | PHONE LEFT MSG |
#      | NO ACTION      |
      | PHONE COMPLETE |

    And I click on My Book radio button
    And I type the name of a customer "KONSTANCE" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I click on Contact History tab from Client Profile page
    Then maximum six TODOs are displayed in CONTACT HISTORY view

  @RegMcom @domain_stores @omniclient @website
  Scenario: To Dos are sorted from  newest to oldest by default in Contact History tab
    Given I launch the bloomingdales's omniclient page
    When I sign into Omniclient BLM application as associate
    And I navigate to MY TASKS page
    And I click on the Create To Do button on MY TO DOS page
    And I enter a title "NEWEST TO DO IN LIST" in the input field on MY TO DOS page
    And I select a valid date in Create To Dos View on MY TO DOS page
    And I input client name "KONSTANCE BACKE" in the input filed from MY TO DOS page
    And I select the full name "KONSTANCE BACKET" from client dropdown MY TO DOS page
    And I enter a description "Testing TODO list sorting" in the input field on MY TO DOS page
    And I click on the TO DO Save button
    And I navigate to MY TASKS page
    And I mark all TO DOs as completed TO DOS tab BLM
#      | EMAIL          |
#      | MAIL           |
#      | TEXT           |
#      | IN PERSON      |
#      | PHONE LEFT MSG |
#      | NO ACTION      |
      | PHONE COMPLETE |
    And I click on My Book radio button
    And I type the name of a customer "KONSTANCE" in the search box
    And I click on the omniclient search button
    And I click on the searched client from the customers results list
    And I click on Contact History tab from Client Profile page
    Then the newly completed TO DO is displayed first in list CONTACT HISTORY "NEWEST TO DO IN LIST"