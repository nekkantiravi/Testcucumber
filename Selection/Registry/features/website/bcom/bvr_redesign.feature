#BCOM Registry Lean Lab
#Author: Masha Malygina
#V-1 Story: B-83619

Feature: Verify the functionality of Bride View Registry (BVR)

  @B-83619 @BVR @registry @domain_selection @use_regression
  Scenario: Verify the initial state of BVR landing page header and footer
    Given I visit the web site as a registry user without checkout
    When I navigate to bvr page
    Then I should see the registry ID is present in the header
    And I should see the registrant and coregistrant names are present in the header
    And I should see the event date is present in the header
    And I should see that days's left until event is present in the header
    And I should see the barcode is present in the footer
    And I should not see Back to Registry Manager link in the header
    And I should see the shipping message is present in the footer:
    | Please review ship times to make sure you understand current availability.|
    | To expedite delivery, gifts containing more than one item may come in multiple shipments.|


  @B-83619 @BVR @registry @domain_selection @use_regression
  Scenario: Verify that user can navigate to Bride View Registry by clicking View My Items on Registry Manager
    Given I visit the web site as a registry user without checkout
    And I navigate to registry manager page
    When I click on View My Items in left panel of Registry Manager
    Then I should land on BVR

  @B-83619 @BVR @registry @domain_selection @use_regression
  Scenario: Verify that user can navigate to Bride View Registry by clicking Items Added on Registry Manager
    Given I visit the web site as a registry user without checkout
    And I navigate to registry manager page
    When I click on Items Added in left panel of Registry Manager
    Then I should land on BVR

  @B-83619 @BVR @registry @domain_selection @use_regression
  Scenario: Verify that user can navigate to Registry Manager page from BVR
    Given I visit the web site as a registry user without checkout
    When I navigate to bvr page
    When I click on Go Back to Registry Manager link in the BVR header
    Then I should land on Registry Manager

  @B-83619 @BVR @registry @domain_selection @use_regression
  Scenario: Verify user can prompt the BVR QP by clicking on Pencil icon in the lower left corner of the product image
    Given I visit the web site as a registry user without checkout
    And I navigate to "registrable and orderable" product PDP page
    And I add registry product to BVR page from standard PDP
    When I navigate to bvr page
    When I click on Pencil icon in the lower left corner of the product image
    Then I verify basic elements of the BVR QP

  @B-83619 @BVR @registry @domain_selection @use_regression
  Scenario: Verify user can prompt the BVR QP by clicking on Wants/Still Needs row below the product image
    Given I visit the web site as a registry user without checkout
    And I navigate to "registrable and orderable" product PDP page
    And I add registry product to BVR page from standard PDP
    When I navigate to bvr page
    When I click on Wants/Still Needs row below the product image
    Then I verify basic elements of the BVR QP

  @B-83619 @BVR @registry @domain_selection @use_regression
  Scenario: Verify that when user can add item to bag from BVR page
    Given I visit the web site as a registry user without checkout
    And I navigate to "registrable and orderable" product PDP page
    And I add registry product to BVR page from standard PDP
    And I navigate to bvr page
    When I add item to bag from BVR page
    Then I verify all attributes of registry ATB overlay
    And I click on "Checkout" button on registry ATB overlay
    And I should land on the bag page with the item present

  @B-83619 @BVR @registry @domain_selection @use_regression
  Scenario: Verify the ATB BVR BOPS is disabled when user clicks on Find In Store for the first time
    Given I visit the web site as a registry user without checkout
    And I navigate to "registrable and orderable" product PDP page
    And I add registry product to BVR page from standard PDP Page
    And I navigate to bvr page with local storage clear
    When I click on Find In Store link on BVR product thumbnail
    And I enter zip code into BVR BOPS overlay
    And I click search on BVR BOPS overlay
    Then I should see list of stores
    And I should see that none of the stores are selected
    And I should see that ATB is disabled

  @B-83619 @BVR @registry @domain_selection @use_regression
  Scenario: Verify BVR user can add item to bag from BVR BOPS overlay
    Given I visit the web site as a registry user without checkout
    And I navigate to "registrable and orderable" product PDP page
    And I add registry product to BVR page from standard PDP Page
    And I navigate to bvr page
    When I add item to bag from BVR BOPS overlay
    Then I verify all attributes of registry ATB overlay
    And I click on "Checkout" button on registry ATB overlay
    And I should land on the bag page with the item present
    And I should see that Pick Up In Store radio button is selected for that item


  @wip @BVR @registry @domain_selection
  Scenario: Verify user can close the BVR QP by clicking on "Save Changes" button
    Given I visit the website as a bvr user using rest services
    And I navigate to "registrable and orderable" product PDP page
    And I add registry product to BVR page from standard PDP
    When I navigate to bvr page
    And I click on Pencil icon in the lower left corner of the product image
    And I click on Save Changes button without editing any of the text boxes
    Then I verify BVR QP closes
    And I should see the overlay with the following message:
	| No changes were made to your registry. |