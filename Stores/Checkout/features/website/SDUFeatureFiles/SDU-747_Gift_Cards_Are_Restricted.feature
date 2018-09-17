#Author: Stores Domain Checkout Team
   #Story: SDU-747 - Restrict Gift Cards
   #Date Created: 08/03/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-747
Feature:  As an associate, I want to know when I cannot ring a UPC on uPOS for Take sales,
  so that I can redirect my transaction to the old system immediately,
  instead of figuring it out later into the transaction.

  @Macy's @Take
  Scenario Outline: Macy's - Try to add a Gift Card in a Take Sale
    Given I am on "Macy's Sales Trans"
    When I select Take option
    And I add "<Gift Card>" item to the Checkout bag
    Then I can see the Restricted Item error
    And I close the Restricted Item error
    When I click on the bag icon
    Then I can see Checkout empty bag view

    Examples:
      |Gift Card                   |
#Macy's Gift Card
      | 656103389173105            |

  @Macy's
  Scenario Outline: Macy's - Try to add a Gift Card directly in the bag
    Given I am on "Macy's Sales Trans"
    When I click on the bag icon
    Then I scan UPC "<Gift Card>" into the bag
    And I can see the Restricted Item error
    When I close the Restricted Item error
    Then I can see Checkout empty bag view

    Examples:
      |Gift Card                   |
#Macy's Gift Card
      | 656103389173105            |

  @Bloomingdale's @Take
  Scenario Outline: Bloomingdale's - Try to add a Gift Card in a Take Sale
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    And I add "<Gift Card>" item to the Checkout bag
    Then I can see the Restricted Item error
    And I close the Restricted Item error
    When I click on the bag icon
    Then I can see Checkout empty bag view

    Examples:
      |Gift Card                   |
#Bloomingdale's Gift Card
      | 817255057633929            |

  @Bloomingdale's
  Scenario Outline: Bloomingdale's - Try to add a Gift Card directly in the bag
    Given I am on "Bloomingdale's Sales Trans"
    When I click on the bag icon
    Then I scan UPC "<Gift Card>" into the bag
    And I can see the Restricted Item error
    When I close the Restricted Item error
    Then I can see Checkout empty bag view

    Examples:
      |Gift Card                   |
#Bloomingdale's Gift Card
      | 817255057633929            |

