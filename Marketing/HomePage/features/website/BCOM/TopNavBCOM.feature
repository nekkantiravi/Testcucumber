#Author: Discovery QE
#Date Created: 06/12/2015


Feature: Verify Home Page functionality in DOMESTIC, ISHIP and RESGITRY mode

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery
  Scenario: Home Page - Verify Seasonal Action wrapper in DOMESTIC mode
    Given I visit the web site as a guest user
    And I click on the "Seasonal Action wrapper" link
    Then I verify Seasonal Action is displayed
    And I verify the Seasonal Image link is reachable
    When I click on the "Seasonal Action wrapper" link
    Then I verify that it is closed
    #Notes:
    #Verify that the image link returns a 200 OK using httpparty

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_iship @backlog_discovery
  Scenario: Home Page - Verify Seasonal Action wrapper in ISHIP mode
    Given I visit the web site as a guest user
    And I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    And I click on the "Seasonal Action wrapper" link
    Then I verify Seasonal Action is displayed
    And I verify the Seasonal Image link is reachable
    When I click on the "Seasonal Action wrapper" link
    Then I verify that it is closed
    #Notes:
    #Verify that the image link returns a 200 OK using httpparty

# QE Comments :: This scenario is not valid as this we dont have functionality to see seasonal adpool in registry mode
#  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @in_transition
#  Scenario: Home Page - Verify Seasonal Action wrapper in REGISTRY mode
#    Given I visit the web site as a registry user
#    When I click on the Seasonal Action wrapper
#    Then I verify Seasonal Action is displayed
#    And I verify the Seasonal Image link is reachable
#    When I click the Seasonal Action wrapper
#    Then I verify that it is closed
#    #Notes:
#    #Verify with business if this is expected behavior

#TestLink -BLCOM-56887
  #Vone - RT-06474
  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify Seasonal Action wrapper in REGISTRY Home Page
    Given I visit the web site as a guest user
    When I navigate to registry home page
    Then I verify Seasonal Action wrapper is not displayed
    When I click on the "back to bloomingdales" link
    Then I verify I land on the Home Page
    When I click on the "Seasonal Action wrapper" link
    Then I verify Seasonal Action is displayed
    #Notes:
    #Verify with business if this is expected behavior
    # Once we navigate to registry homepage seasonal action ON will display automatically
    # Once we move from registry to domestic auto seasonal action ON will not display. Hence
    # added click on seasonal action wrapper step

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_domestic @backlog_discovery
  Scenario: CategorySplashPage - Verify TOPNAV elements are displayed in DOMESTIC mode
    Given I visit the web site as a guest user
    Then I verify below topnav elements are displayed and return a 200 OK
      | STORES & EVENTS   |
      | COUNTRY FLAG      |
      | CURRENCY          |
      | MY ACCOUNT        |
      | WISH LIST         |
      | BROWN BAG:(0)     |
      | SHOPPING BAG ICON |

    #Notes:
    #Verify that the links returns a 200 OK using httpparty

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_registry @backlog_discovery
  Scenario: Home Page - Verify TOPNAV elements are displayed in REGISTRY mode
    Given I visit the web site as a registry user
    Then I verify below topnav elements are displayed and return a 200 OK
      | STORES & EVENTS   |
      | MY ACCOUNT        |
      | BROWN BAG:(0)     |
      | SHOPPING BAG ICON |
    #Notes:
    #Verify that the links returns a 200 OK using httpparty

  @use_regression @artifact_navapp @domain_marketing @priority_high @mode_iship @backlog_discovery
  Scenario: Home Page - Verify TOPNAV elements are displayed in ISHIP mode
    Given I visit the web site as a guest user
    When I navigate to international context page
    When I change country to "Australia"
    And I close the welcome mat if it's visible
    Then I verify below topnav elements are displayed and return a 200 OK
      | COUNTRY FLAG      |
      | CURRENCY          |
      | BROWN BAG:(0)     |
      | SHOPPING BAG ICON |
    And I verify below topnav elements are not displayed
      | STORES & EVENTS |
      | MY ACCOUNT      |
      | WISH LIST       |
    #Notes:
    #Verify that the image and links returns a 200 OK using httpparty



  #TBD - registered user scenarios for all modes