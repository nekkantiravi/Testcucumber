# Author: Kasun Alwis
# Date Created: 11/18/2013
# Date Signed Off:

Feature: Verify  enhancements in site admin page

  #Test Case ID: MCOM-71600 , BLCOM-67489
  @sst
  Scenario: Verify the Left navigation Menu Options in site admin page
    Given I login into site admin portal as a valid user
    Then I should see given menu items in left panel
      | Run Jobs                 |
      | System Information       |
      | Site DB Statistics       |
      | Misc Maintenance         |
      | Reports                  |
      | CSU                      |

  #Test Case ID: MCOM-72767 , BLCOM-67667
  @sst
  Scenario Outline: Verify Job groupings for Site Admin in View Jobs page
    Given I login into site admin portal as a valid user
    When I navigate to "View Jobs" page under Reports section
    When I select "<Jobs Group>" from Jobs Group dropdown
    Then I verify the sub jobs list values based on the "<Jobs Group>" selection in Jobs Group

  Examples:
    | Jobs Group        |
    | CAPS Jobs         |
    | IA Jobs           |
    | Batch Engine Jobs |
    | Recycle Jobs      |
  # Notes:
  # Verify following exists on Jobs Group drop down
  # following SQL statements to find the sub-jobs listed under grouped jobs.
  # Job Grpup 1 : select * from JOB_GROUP_MAPPING where JOB_GROUP_ID = 1
  # Job Grpup 2 : select * from JOB_GROUP_MAPPING where JOB_GROUP_ID = 2
  # Job Grpup 3 : select * from JOB_GROUP_MAPPING where JOB_GROUP_ID = 3
  # Job Grpup 4 : select * from JOB_GROUP_MAPPING where JOB_GROUP_ID = 4
  # SQL query to retrive job group names and their assigned job names
  # SELECT jg.JOB_GROUP_ID, jg.JOB_GROUP_NAME, j.JOB_ID , j.JOB_NAME
  # FROM JOB_GROUP_MAPPING jgm LEFT JOIN JOB j on jgm.JOB_ID = j.JOB_ID
  # LEFT JOIN JOB_GROUP jg ON jgm.JOB_GROUP_ID = jg.JOB_GROUP_ID
  # ORDER BY jg.JOB_GROUP_ID

