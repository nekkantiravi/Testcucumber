Feature: Execution Engine SDT Test

# Note: 1) For login, racf id has to be given at runtime in username and password variable
#       2) For subdomain other then sdt, subdomain has to be given at runtime in domain variable

  @EE_SDT @scenario1
  Scenario: Login to Execution Engine using RACF id
    Given I visit Execution Engine as a guest user
    When I goto desired subdomain
    And I login to subdomain using RACF id
    Then I should see RACF id on Welcome page
    And I verify subdomain

  @EE_SDT @scenario2
  Scenario: Select project, tag, feature, and scenario on Test Execution page
    Given I log in to desired subdomain
    When I select Test Execution on Welcome page
    And I select "SolutionDevelopment.ExecutionEngine" SDT Project if on SDT subdomain
    And I select "random" tag on Test Execution page
    And I select "random" feature on Test Execution page
    Then I select "random" scenario on Test Execution page

  @EE_SDT @scenario3
  Scenario: Verify all sections are loaded on Test Execution Page
    Given I log in to desired subdomain
    When I select Test Execution on Welcome page
    Then I verify following sections are loaded on Test Execution page:
      | Latest Job Builds    |
      | Queue                |
      | Meta Data            |
      | Error Categorization |
      | Parameters           |
      | Tags                 |

  @EE_SDT @scenario4
  Scenario: Verify all fields are loaded in meta data and parameters section on Test Execution page
    Given I log in to desired subdomain
    When I select Test Execution on Welcome page
    Then I verify meta data and parameters section on Test Execution page

  @EE_SDT @scenario5
  Scenario: Add and delete new Error Category on Test Execution page
    Given I log in to desired subdomain
    And I select Test Execution on Welcome page
    When I add "Test" Error Category on Test Execution page
    Then I should see the "Test" Error Category on Test Execution page
    When I delete "Test" Error Category on Test Execution page
    Then I should not see the "Test" Error Category on Test Execution page

  @EE_SDT @scenario6
  Scenario: Create, Load and Delete Project Template
    Given I log in to desired subdomain
    When I select Test Execution on Welcome page
    And I create a new project template
    And I refresh current page
    And I select Test Execution on Welcome page
    And I load "the created" project template
    Then I should see project template with pre-populated data
    And I should be able to delete the created project template

  @EE_SDT @scenario7
  Scenario: Navigate to Report page from Reports Tree page and verify all sections are loaded on Report page
    Given I log in to desired subdomain
    When I select Reports on Welcome page
    And I select a random report from Reports Tree page
    Then I verify following sections are loaded on Report page:
      | Report Info    |
      | Report Utils   |
      | Report Summary |
      | Report Data    |

  @EE_SDT @scenario8
  Scenario: Submit scenarios for execution by using existing project template and verify job is added to queue
    Given I log in to desired subdomain
    And I select Test Execution on Welcome page
    When I load "YC05RT4.Test capability to trigger execution" project template
    And I submit scenarios for execution on Test Execution page
    Then I should see "Test capability to trigger execution" in execution queue on Test Execution page

    # Note: 1) Prerequisite for Scenario 8: project template "YC05RT4.Test capability to trigger execution"
    #       2) IMP.: Make sure The template being loaded is not selecting Scenario 8 - to avoid execution loop

  @EE_SDT @scenario9
  Scenario: Create and delete disabled project - Create by selecting yes on submit execution overlay
    Given I log in to desired subdomain
    And I select Test Execution on Welcome page
    When I create "Test disabled Job" disabled project
    And I should see "Test disabled Job" in execution queue on Test Execution page
    When I delete "Test disabled Job" from the execution queue on Test Execution page
    Then I should not see "Test disabled Job" in execution queue on Test Execution page

    # Note: Prerequisite for Scenario 9: project template "YC05RT4.Test capability to trigger execution"

  @EE_SDT @scenario10
  Scenario: Create and delete Trigger - Create using flash icon
    Given I log in to desired subdomain
    And I select Test Execution on Welcome page
    When I create "Temp_Trigger" Trigger for "For_Trigger_Test" project
    Then I should see "Temp_Trigger" Trigger for "For_Trigger_Test" project
    When I delete "Temp_Trigger" Trigger
    Then I should not see "Temp_Trigger" Trigger for "For_Trigger_Test" project

    # Note: Prerequisite for Scenario 10: disabled project "For_Trigger_Test"

  @EE_SDT @scenario11
  Scenario: Report verification - Counts and Percentage - total, passed, failed and errors
    Given I log in to desired subdomain
    When I select Reports on Welcome page
    And I navigate to a report of "Test capability to trigger execution" project generated within last 2 days
    Then I should see 6 scenario passed out of 10 scenarios
    And I should see 4 scenario failed out of 10 scenarios
    And I should see "60" pass percentage
    And I should see "40" fail percentage
    And I should see 1 scenario failed due to "TBD" error
    And I should see 2 scenarios failed due to "ENV" error
    And I should see 1 scenario failed due to "Test" error
    And I should see 2 builds are generated in the report table
    And I verify the count of the scenarios in the report table:
      | Total | 10 |
      | P     | 6  |
      | F     | 4  |
      | ENV   | 2  |
      | Test  | 1  |
      | Data  | 0  |
      | TBD   | 1  |

    # Notes: Prerequisite for Scenario 11: report of "Test capability to trigger execution" project

  @EE_SDT @scenario11
  Scenario: Verify feature files are displayed as per channel type (mew or website) selected
    Given I log in to desired subdomain
    And I select Test Execution on Welcome page
    When I select "MEW" channel on Test Execution page
    And I select "SolutionDevelopment.DigitalAnalytics" SDT Project if on SDT subdomain
    And I select "random mew" tag on Test Execution page
    Then I should see "MEW" in feature files path
    When I select "Website" channel on Test Execution page
    And I select "SolutionDevelopment.DigitalAnalytics" SDT Project if on SDT subdomain
    And I select "random" tag on Test Execution page
    Then I should see "website" in feature files path
