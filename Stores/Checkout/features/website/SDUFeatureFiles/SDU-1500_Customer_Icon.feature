#Author: Stores Domain Checkout Team
    #Story: SDU-1500 - Checkout :: Customer Icon in Header
    #Date Created: 11/29/2017
    #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-1500
Feature:As an associate, I want a way to access customer information so I can help her.


  @Macy's
  Scenario: Macy's - Check if UI elements are properly displayed when customer icon is tapped
    Given I am on "Macy's Sales Trans"
    When Customer icon is displayed to the left of bag in the header
    And User taps customer icon in the header
    And I am on "Customer" page
    And 'Find Customer' is properly displayed
    And 'Swipe customer's card to identify best offers.' is properly displayed
    When I click on the bag icon
    Then I am on "Bag" page
    When User taps customer icon in the header
    Then I am on "Customer" page


  @Macy's
  Scenario: Macy's - Check if progress bar is still displayed when the Find Customer page is displaying
    Given I am on "Macy's Sales Trans"
    When Customer icon is displayed to the left of bag in the header
    And User taps customer icon in the header
    Then I am on "Customer" page
    And 'Find Customer' is properly displayed
    And Progress bar is not displayed

  @Macy's @manual
  Scenario: Macy's - Check if progress bar is still displayed when the Find Customer page is displaying and after refresh the page
    Given I am on "Macy's Sales Trans"
    When Customer icon is displayed to the left of bag in the header
    And User taps customer icon in the header
    Then I am on "Customer" page
    And 'Find Customer' is properly displayed
    And Progress bar is not displayed
    When I refresh the page
    And I close the overlay
    Then Progress bar is not displayed

