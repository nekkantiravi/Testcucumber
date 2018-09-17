Feature: Verify Random Link on Cat Browse Pages

  @preview_desktop
  Scenario Outline: Verify Random Link on Cat Browse Pages
    Given I visit the web site as a guest user
    When I mouse over "<category>" category from top navigation
    And I select "<subcategory>" subcategory from flyout menu
    And  I navigate to sub categories from Left hand nav links
    Then I verify the response code for cat browse page

    Examples:
      | category | subcategory |
      | beauty   | BEAUTY      |
      | kids     | KIDS        |
      | women    | WOMEN       |
      | men      | MEN         |


  @preview_desktop
  Scenario: CategoryBrowse: Verify Title,Title Spelling,Product count and Filter by
    Given I am on Browse Page for "Dresses" under "WOMEN"
    And I verify the title of browse page
    And I verify the header text of browse page
    And I verify product count on browse page
    And I verify filter by is displaying at left nav at browse page