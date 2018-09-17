#Author: Claudiu Chirila
#Story: SDE-111 - MOBILE: List/Event Details Page
#Date Created: 06/12/2017
#Date Signed Off:

Feature:As an associate using the mobile clientele application, I want to see Lists/Event details so that I can see the list of customers that need outreach, monitor status,
  and be able to close To Dos.

  @manual @domain_stores @omniclient @story_SDE-118 @mcom @MEW
  Scenario: All information regarding of the newly created TO DO are properly displayed for both SM and SA
#  •Selling Managers will see a list of clients with the following information: Client name and phone number, Selling Associate assigned to the To Do, and the Status
#  •Selling Associates will see a list of clients with the following information: Client name and phone number, Last Visit Date, and the Status

    # The To DO first needs to be manually created wia the Web app,
    # since the Mobile app does not have support for creating To Dos

    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as My Shop Selling Manager
    And I navigate to My Macys To Dos mobile page
    And I click on the newly created To Do mobile Macys
    And I click the chevron to expand the description mobile Macys
    Then List title will be display as the screen header Macys
    And Due by date and description is displayed at the top of the page Macys
    And following columns are displayed mobile Macys :
      | Client name |
      | SA          |
      | Status      |
    When I select the switch user input box
    And I enter "FIFTY O" credentials
    And I select the "FIFTY ONE" credentials from the dropdown
    Then I should see switch user overlay
    When click the Switch button
    Then I should be switched into the selected "associate"
    When I navigate to My Macys To Dos mobile page
    And I click on the newly created To Do mobile Macys
    Then List title will be display as the screen header mobile Macys
    And following columns are displayed mobile Macys:
      | Client name |
      | Last Visit  |
      | Status      |


