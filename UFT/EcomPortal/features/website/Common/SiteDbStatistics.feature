# Author: Kasun Alwis
# Date Created: 11/18/2013
# Date Signed Off:

Feature: Verify Site DB Statistics feature

  #Test Case ID: MCOM-66622 , BLCOM-65405
  @sst
  Scenario: Verify the graph in Site DB statistics, when user selects all required attributes(Statistics, Frequency,Type and Period)
    Given I login into site admin portal as a valid user
    When I select "Site DB Statistics" under Site DB Statistics section
      And I submit after selecting required attributes on Site DB Statistics page
    Then I should see the respective graph
    # Notes:
    # Breadcrumb should be displayed as "Home | Site DB Statistics"
    # Verify the followings exists on Site DB Statistics page
    # Daily, Weekly, Monthly, Yearly radio buttons exists
    # Bar Chart, Line Chart radio buttons exists
    # From and To date range selection panels
    # Select following options to get Shopping Order details
    # 'Statistics' => Shopping Order
    # 'Frequency' => Yearly
    # 'Type' => Bar Chart
    # 'Period' => From Year - 2003
    # 'Period' => To Year - current year
    # Click submit and verify report loaded successfully
