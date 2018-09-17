#Author: Traci Morris
#Story: SDE-5 - QE Test - View Created Events
#Date Created:
#Date Signed Off:

Feature: As a District Manager, I want the ability to view Corporate Marketing Events that were created by 
Corporate Administrators for any associates within my district so that I can monitor and coach customer outreach.

@manual @domain_stores @omniclient @Story_SDE-5 @Manual
  Scenario: Get count of To Dos on District Managers dashboard
    Given I launch the macy's omniclient page
    When I sign into Omniclient application as "District Manager"
    Then I will see the dashboard
    And I will verify the count of To Dos

@manual @domain_stores @omniclient @Story_SDE-5 @Manual
Scenario: Create a Marketing Event as a Corporate Administrator
  Given I launch the macy's omniclient page
  When I sign into Omniclient application as "Corporate Administrator"
  And I select Create Event tab
  And I select Create Event link
  And I Create a Marketing Event
  Then I will see a confirmation overlay
  When I close the overlay
  Then I verify that my Event is displayed

@manual @domain_stores @omniclient @Story_SDE-5 @Manual
Scenario: Verify the count of open Events will display on the dashboard
  Given I launch the macy's omniclient page
  When I sign into Omniclient application as "District Manager"
  Then I will see the dashboard
  And I will see the count of Open events
  And I will verify that the To Dos count has been incremented by one

@manual @domain_stores @omniclient @Story_SDE-5 @Manual
Scenario: Verify the event title, # of uncalled customers and due dates are displayed on the dashboard
  Given I launch the macy's omniclient page
  When I sign into Omniclient application as "District Manager"
  Then I will see the dashboard
  And I will see the Event titles
  And I will see the number of uncalled customers
  And I will see the due dates

@manual @domain_stores @omniclient @Story_SDE-5 @Manual
Scenario: Verify the event information can also be seen in the All To Dos tab including title, number of uncalled
customers and due by date
  Given I launch the macy's omniclient page
  When I sign into Omniclient application as "District Manager"
  Then I will see the dashboard
  And I select All To Dos tab
  Then I will verify the My Macys To Dos page is displayed
  And I will see the Event titles
  And I will see the number of uncalled customers
  And I will see the due dates