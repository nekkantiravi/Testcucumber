#Author: UFT Team
#Date Created: 01/27/2016
#Date Signed Off:
#Version One: B-37703

Feature: As a producer, I would like to ensure that clicking the GNA in iship does not display error message

  @use_regression @domain_discovery @priority_medium @mode_iship
  Scenario: HomePage - Verify error message is not displayed when we click GNA on Iship home page
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "a random country"
    And I close the welcome mat if it's visible
    Then I verify the display of GNA

  @use_regression @domain_discovery @priority_medium @mode_iship
  Scenario: CategorySplashPage - Verify error message is not displayed when we click GNA on Iship splash page
    Given I am on CatSplash Page for "SHOES" on "iship" mode
    Then I verify the display of GNA

  @use_regression @domain_discovery @priority_medium @mode_iship
  Scenario: BrowsePage - Verify error message is not displayed when we click GNA on Iship browse page
    Given I am on CategoryBrowsePage for "Dress Shirts" under "MEN" in iship mode
    Then I verify the display of GNA

  @use_regression @domain_discovery @priority_medium @mode_iship
  Scenario: DLPPage - Verify error message is not displayed when we click GNA on Iship browse page
    Given I am on DynamicLandingPage in "iship" mode
    Then I verify the display of GNA

  @use_regression @domain_discovery @priority_medium @mode_iship
  Scenario: SearchResultsPage - Verify error message is not displayed when we click GNA on Iship browse page
    Given I am on SearchResultsPage for "100%" in iship mode
    Then I verify the display of GNA

  @use_regression @domain_discovery @priority_medium @mode_iship
  Scenario: ZeroResultsPage - Verify error message is not displayed when we click GNA on Iship browse page
    Given I am on SearchResultsPage for "fsdsdkkka" in iship mode
    Then I verify the display of GNA