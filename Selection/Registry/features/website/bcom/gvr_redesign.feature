#BCOM Registry Lean Lab
#Author: Masha Malygina
#V-1 Story: B-83619

Feature: Verify the functionality of Guest View Registry (GVR)

  @B-83619 @GVR @registry @domain_selection @use_regression
  Scenario: Verify the initial state of GVR landing page header and footer                                                                                                                                                                                                                al state of GVR landing page header and footer
    Given I visit the web site as a gvr user without checkout
    And I navigate to GVR page by url
    Then I should see the registry ID is present in the header
    And I should see the registrant and coregistrant names are present in the header
    And I should see the event date is present in the header
    And I should see that days's left until event is present in the header
    And I should see the barcode is present in the footer
    And I should not see Back to Registry Manager link in the header
    And I should not see the shipping message is present in the footer:
      | Please review ship times to make sure you understand current availability.|
      | To expedite delivery, gifts containing more than one item may come in multiple shipments.|


  @B-83619 @GVR @registry @domain_selection @use_regression
  Scenario: Verify user can prompt the GVR QP by clicking on the icon in the lower left corner of the product image
    Given I visit the website as a guest user
    And I navigate to GVR page with products added by url
    And I open GVR QP of an item
    Then I verify basic elements of the GVR QP

  @B-83619 @GVR @registry @domain_selection @use_regression
  Scenario: Verify that when registry has no items, the corresponding message on GVR is displayed and the link to Gift Cards browse page is present
    Given I visit the web site as a gvr user without checkout
    When I navigate to GVR page by url
    And I verify GVR has no items
    Then I should see the message in the empty GVR page header:
      """
      There are no items in this registry yet.
      """
    And I should see Shop Gift Cards link
    And I should see Little Brown Card bag icon
    And I should land on Gift Card browse page when clicking on either Shop Gift Cards link or Little Brown Card bag icon

  @B-83619 @GVR @registry @domain_selection @use_regression
  Scenario: Verify that when user can add item to bag from GVR page
    Given I visit the website as a guest user
    And I navigate to GVR page with products added by url
    When I add item to bag from GVR page
    Then I verify all attributes of registry ATB overlay
    And I click on "Checkout" button on registry ATB overlay
    And I should land on the bag page with the item present

  @B-83619 @GVR @registry @domain_selection @use_regression
  Scenario: Verify that when user can add item to bag from GVR QP
    Given I visit the website as a guest user
    Given I navigate to GVR page with products added by url
    And I open GVR QP of an item
    When I add item to bag by clicking on ATB button on registry GVR QP
    And I verify all attributes of registry ATB overlay
    And I click on "Checkout" button on registry ATB overlay
    Then I should land on the bag page with the item present

  @B-83619 @GVR @registry @domain_selection @use_regression
  Scenario: Verify the ATB GVR BOPS is disabled when user clicks on Find In Store for the first time
    Given I visit the website as a guest user
    And I navigate to GVR page with products added by url
    When I click on Find In Store link on GVR product thumbnail
    And I enter zip code into GVR BOPS overlay
    And I click search on GVR BOPS overlay
    Then I should see list of stores
    And I should see that none of the stores are selected
    And I should see that ATB is disabled

  @B-83619 @GVR @registry @domain_selection @use_regression
  Scenario: Verify GVR user can add item to bag from GVR BOPS overlay
    Given I visit the website as a guest user
    And I navigate to GVR page with products added by url
    When I add item to bag from GVR BOPS overlay
    Then I verify all attributes of registry ATB overlay
    And I click on "Checkout" button on registry ATB overlay
    And I should land on the bag page with the item present
    And I should see that Pick Up In Store radio button is selected for that item

