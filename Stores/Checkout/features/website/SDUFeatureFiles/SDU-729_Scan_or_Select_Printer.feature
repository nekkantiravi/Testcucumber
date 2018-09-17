#Author: Stores Domain Checkout Team
    #Story: SDU-729 - Checkout :: Scan/Select Printer
    #Date Created: 06/28/2017
    #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-729
Feature: As an associate, I want the option to scan a printer, or choose last,
  so that I can find the best place to print my customer's receipt.

Place Order Scenarios:
  @Macy's @Send
  Scenario: Macy's - Place Order flow - Assign printer to device
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    And I can see the add printer button and print button
    When I click posttender print button
    Then I can see the sales trans landing page

#  @manual
#  Scenario: Assign printer to device
#    When I click the add printer button
#    Then I can see the scan printer barcode screen
#    And I scan the barcode on the printer
#    Then a receipt is printed
#    And I see the save this printer message

#  Scenario: Place Order flow - Use previously assigned printer
#    Given I am on Sales Trans
#    When I checkout an item for a send sale
#    Then I can see the mock tendering screen
#    And I can see the receipt icon
#    When I click on the receipt icon
#    Then I can see the receipt information on a send
#    And I can see the find printer button
#    When I click on the find printer button
#
#  @manual
#  Scenario: Print a receipt
#    Then a receipt is printed
#
#  Scenario: Place Order flow - Printer previously assigned but change to another printer
#    Given I am on Sales Trans
#    When I checkout an item for a send sale
#    Then I can see the mock tendering screen
#    And I can see the receipt icon
#    When I click on the receipt icon
#    Then I can see the receipt information on a send
#    And I can see the find printer button
#    When I click on the find printer button
#
#  @manual
#  Scenario: Assign a different printer to device
#    Then I can see the scan printer barcode screen
#    And I can see the use this printer button
#    When I scan the barcode on the printer
#    Then a receipt is printed
#    And I see the save this printer message
#
#
##Take Sale Scenarios:
  @Macy's @Take
  Scenario: Macy's - Take Sale flow - Assign printer to device
    Given I am on "Macy's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I swipe the "Macy's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    And I can see the add printer button and print button
    When I click posttender print button
    Then I can see the sales trans landing page


#  @manual
#  Scenario: Assign printer to device
#    And I can see the add printer button
#    When I click the add printer button
#    Then I can see the scan printer barcode screen
#    And I scan the barcode on the printer
#    Then a receipt is printed
#    And I see the save this printer message
#
#
#
#  Scenario: Take Sale flow - Use previously assigned printer
#    Given I am on Sales Trans
#    When I add an item to the Checkout bag for a Take Sale
#    Then I see the CRL Overlay
#    When I close the CRL Overlay
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    When I click on the bag icon
#    Then I can see the bag header title
#    When I press the checkout button
#    Then I can see the mock tendering screen
#    And I can see the receipt icon
#    When I click on the receipt icon
#    Then I can see the receipt information on a send
#    And I can see the find printer button
#    When I click on the find printer button
#
#  @manual
#  Scenario: Print a receipt
#    Then a receipt is printed
#
#  Scenario: Take Sale flow - Printer previously assigned but change to another printer
#    Given I am on Sales Trans
#    When I add an item to the Checkout bag for a Take Sale
#    Then I see the CRL Overlay
#    When I close the CRL Overlay
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    When I click on the bag icon
#    Then I can see the bag header title
#    When I press the checkout button
#    Then I can see the mock tendering screen
#    And I can see the receipt icon
#    When I click on the receipt icon
#    Then I can see the receipt information on a send
#    And I can see the find printer button
#    When I click on the find printer button
#
#  @manual
#  Scenario: Assign a different printer to device
#    Then I can see the scan printer barcode screen
#    And I can see the use this printer button
#    When I scan the barcode on the printer
#    Then a receipt is printed
#    And I see the save this printer message

  #Place Order Scenarios:
  @Bloomingdale's @Send
  Scenario: Bloomingdale's - Place Order flow - Assign printer to device
    Given I am on "Bloomingdale's's Sales Trans"
    When I checkout an item for a send sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    And I can see the add printer button and print button
    When I click posttender print button
    Then I can see the sales trans landing page


#  @manual
#  Scenario: Assign printer to device
#    When I click the add printer button
#    Then I can see the scan printer barcode screen
#    And I scan the barcode on the printer
#    Then a receipt is printed
#    And I see the save this printer message

#  Scenario: Place Order flow - Use previously assigned printer
#    Given I am on Sales Trans
#    When I checkout an item for a send sale
#    Then I can see the mock tendering screen
#    And I can see the receipt icon
#    When I click on the receipt icon
#    Then I can see the receipt information on a send
#    And I can see the find printer button
#    When I click on the find printer button
#
#  @manual
#  Scenario: Print a receipt
#    Then a receipt is printed
#
#  Scenario: Place Order flow - Printer previously assigned but change to another printer
#    Given I am on Sales Trans
#    When I checkout an item for a send sale
#    Then I can see the mock tendering screen
#    And I can see the receipt icon
#    When I click on the receipt icon
#    Then I can see the receipt information on a send
#    And I can see the find printer button
#    When I click on the find printer button
#
#  @manual
#  Scenario: Assign a different printer to device
#    Then I can see the scan printer barcode screen
#    And I can see the use this printer button
#    When I scan the barcode on the printer
#    Then a receipt is printed
#    And I see the save this printer message
#
#
##Take Sale Scenarios:
  @Bloomingdale's @Take
  Scenario: Bloomingdale's - Take Sale flow - Assign printer to device
    Given I am on "Bloomingdale's's Sales Trans"
    When I checkout an item for a take sale
    Then I can see the mock tendering screen
    When I swipe the "Bloomingdale's Prop" at the tendering screen
    Then I can see the signature view
    When I input my signature
    And I press the Confirm signature button
    Then I can see the postTender screen
    And I can see the add printer button and print button
   When I click posttender print button
  Then I can see the sales trans landing page

#  @manual
#  Scenario: Assign printer to device
#    And I can see the add printer button
#    When I click the add printer button
#    Then I can see the scan printer barcode screen
#    And I scan the barcode on the printer
#    Then a receipt is printed
#    And I see the save this printer message
#
#
#
#  Scenario: Take Sale flow - Use previously assigned printer
#    Given I am on Sales Trans
#    When I add an item to the Checkout bag for a Take Sale
#    Then I see the CRL Overlay
#    When I close the CRL Overlay
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    When I click on the bag icon
#    Then I can see the bag header title
#    When I press the checkout button
#    Then I can see the mock tendering screen
#    And I can see the receipt icon
#    When I click on the receipt icon
#    Then I can see the receipt information on a send
#    And I can see the find printer button
#    When I click on the find printer button
#
#  @manual
#  Scenario: Print a receipt
#    Then a receipt is printed
#
#  Scenario: Take Sale flow - Printer previously assigned but change to another printer
#    Given I am on Sales Trans
#    When I add an item to the Checkout bag for a Take Sale
#    Then I see the CRL Overlay
#    When I close the CRL Overlay
#    Then I can see the added to bag toast message
#    And the toast message fades away after 2 seconds
#    When I click on the bag icon
#    Then I can see the bag header title
#    When I press the checkout button
#    Then I can see the mock tendering screen
#    And I can see the receipt icon
#    When I click on the receipt icon
#    Then I can see the receipt information on a send
#    And I can see the find printer button
#    When I click on the find printer button
#
#  @manual
#  Scenario: Assign a different printer to device
#    Then I can see the scan printer barcode screen
#    And I can see the use this printer button
#    When I scan the barcode on the printer
#    Then a receipt is printed
#    And I see the save this printer message