# Author: Stores Domain Checkout Team
# Story: SDU-418 - Checkout :: Update buttons to have hover over and pressed states
# Date Created: 5/4/2017
# Date Signed Off:

Feature: As an associate, when I press one of the black buttons the button's color changes to indicate an action.

  @domain_stores @project_checkout @release_1708 @story_SDU-418 @wip
  Scenario: Associates should see the button's color change from black to a lighter color (hex #68696b), indicating an
  action.

    Given I launch the macy's store domain CSG
    Then I should see the color changing button
    When I click and hold the color changing button
    Then I should see the button background color change from black to hex #68696b