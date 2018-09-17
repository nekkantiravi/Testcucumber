# Author: SST Regression
# Date Created: 11/14/2013
# Date Modified: 06/21/2013
# Date Signed Off:

Feature: Verify the features in Reports section

  #Test Case ID: MCOM-66586 , MCOM-66587 , BLCOM-65402 , BLCOM-65403
  @sst
  Scenario: Verify job list display for given date range in view jobs page
    Given I login into site admin portal as a valid user
    When I navigate to "View Jobs" page under Reports section
    And I submit the job by selecting the Date Range along with required fields
      | Jobs Group  | Jobs range                 |
      | ShowAllJobs | Incremental IA Persistence |
    Then I should see the Job list Report on View Jobs page
      | Job Id | Job Name | Job Start Time | Job End Time | SLA Duration | SLA Missed | Job Status | Job Notes |
    # Notes:
    # Breadcrumb should be displayed as "Home | View Jobs"
    # Verify the followings exists on View Jobs page
    # Current Date and Date Range with Radio Button Selective options.
    # Jobs List box should display with available jobs.
    # Start Date and End Date with Calendar Pop-ups.
    # Job Listing and Chart Options with Radio Button Selective options.
    # Submit and Reset Buttons.
    # Select following options to get job list details
    # Date Range radio button
    # 'Jobs Group' => ShowAllJobs
    # 'Jobs' => Incremental IA Persistence
    # 'Start date' => 1 year before current date
    # 'End date' => current date
    # Click submit and verify results display under following columns
    # Job Id | Job Name | Job Start Time | Job End Time | SLA Duration | SLA Missed | Job Status | Job Notes

  #Test Case ID: MCOM-66586 , BLCOM-65393
  @sst
  Scenario: Verify order statistics page
    Given I login into site admin portal as a valid user
    When I navigate to "Order Statistics" page under Reports section
    And I select submit button
    Then I should see Order Statistics table
    # Notes:
    # Breadcrumb should be displayed as "Home | Order Statistics"
    # Verify the followings exists on Order Statistics page
    # "Today" , "Yesterday" and "Year to Date" buttons
    # Click "Yesterday" and verify the results under following columns
    # Today | Orders | COMP RT | COMP BATCH | UNRESOLVED | NOT PROCESSED YET | REAL TIME % | MODE
