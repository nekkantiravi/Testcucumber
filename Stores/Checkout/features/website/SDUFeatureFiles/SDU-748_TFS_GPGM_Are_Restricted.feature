#Author: Stores Domain Checkout Team
   #Story: SDU-748 - Restrict TFS/GPGM
   #Date Created: 08/03/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-748
Feature:  As an associate, I want to know when I cannot ring a UPC on uPOS for Take sales,
  so that I can redirect my transaction to the old system immediately,
  instead of figuring it out later into the transaction.

  @Macy's @Take
  Scenario Outline: Macy's - Try to add a Give Back UPC in a Take Sale
    Given I am on "Macy's Sales Trans"
    When I select Take option
    And I add "<UPC>" item to the Checkout bag
    Then I can see the Restricted Item error
    And I close the Restricted Item error
    When I click on the bag icon
    Then I can see Checkout empty bag view

    Examples:
      |UPC                   |
#Macy's Thanks for Sharing (TFS)
      | 431036703236         |

  @Macy's
  Scenario Outline: Macy's - Try to add a Give Back UPC directly in the bag
    Given I am on "Macy's Sales Trans"
    When I click on the bag icon
    Then I scan UPC "<UPC>" into the bag
    And I can see the Restricted Item error
    When I close the Restricted Item error
    Then I can see Checkout empty bag view

    Examples:
      |UPC                   |
#Macy's Thanks for Sharing (TFS)
      | 431036703236         |

  @bloomingdale's @Take
  Scenario Outline: Bloomingdale's - Try to add a Give Back UPC in a Take Sale
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    And I add "<UPC>" item to the Checkout bag
    Then I can see the Restricted Item error
    And I close the Restricted Item error
    When I click on the bag icon
    Then I can see Checkout empty bag view

    Examples:
      |UPC                   |
#Bloomingdale's Give Pink/Get More (GPGM)
      | 400013067678         |

  @bloomingdale's
  Scenario Outline: Bloomingdale's - Try to add a Give Back UPC directly in the bag
    Given I am on "Bloomingdale's Sales Trans"
    When I click on the bag icon
    Then I scan UPC "<UPC>" into the bag
    And I can see the Restricted Item error
    When I close the Restricted Item error
    Then I can see Checkout empty bag view

    Examples:
      |UPC                   |
#Bloomingdale's Give Pink/Get More (GPGM)
      | 400013067678         |
