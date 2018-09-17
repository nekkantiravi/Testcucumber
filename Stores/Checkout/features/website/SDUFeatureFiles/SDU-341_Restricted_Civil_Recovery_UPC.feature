#Author: Stores Domain Checkout Team
   #Story: SDU-341 - Restricted Civil Recovery UPC
   #Date Created: 08/03/2017
   #Date Signed Off:

@domain_stores @project_checkout @release_17 @story_SDU-341
Feature:  As an associate, I want to know when I cannot ring a UPC on uPOS for Take sales,
  so that I can redirect my transaction to the old system immediately,
  instead of figuring it out later into the transaction.

  @Macy's @take
  Scenario Outline: Macy's - Try to add a Civil Recovery Item in a Take Sale
    Given I am on "Macy's Sales Trans"
    When I select Take option
    And I add "<UPC>" item to the Checkout bag
    Then I can see the Restricted Item error
    And I close the Restricted Item error
    When I click on the bag icon
    Then I can see Checkout empty bag view


    Examples:
      |UPC                    |
#External Civil Demand (Dept/Class 13/10)
      | 400822291158           |
#Internal Restitution (Dept/Class 13/11)
      | 400822291165           |
#Court Ordered Restitution (Dept/Class 13/26)
      | 400822291172           |


  @Macy's @send
  Scenario Outline: Macy's - Try to add a Civil Recovery Item directly in the bag
    Given I am on "Macy's Sales Trans"
    When I click on the bag icon
    Then I scan UPC "<UPC>" into the bag
    And I can see the Restricted Item error
    When I close the Restricted Item error
    Then I can see Checkout empty bag view


    Examples:
      |UPC                    |
#External Civil Demand (Dept/Class 13/10)
      | 400822291158           |
#Internal Restitution (Dept/Class 13/11)
      | 400822291165           |
#Court Ordered Restitution (Dept/Class 13/26)
      | 400822291172           |

  @Bloomingdale's @take
  Scenario Outline: Bloomingdale's - Try to add a Civil Recovery Item in a Take Sale
    Given I am on "Bloomingdale's Sales Trans"
    When I select Take option
    And I add "<UPC>" item to the Checkout bag
    Then I can see the Restricted Item error
    And I close the Restricted Item error
    When I click on the bag icon
    Then I can see Checkout empty bag view

    Examples:
      |UPC                    |
#External Civil Demand (Dept/Class 13/10)
      | 400822291158           |
#Internal Restitution (Dept/Class 13/11)
      | 400822291165           |
#Court Ordered Restitution (Dept/Class 13/26)
      | 400822291172           |


  @Bloomingdale's @send
  Scenario Outline: Bloomingdale's - Try to add a Civil Recovery Item directly in the bag
    Given I am on "Bloomingdale's Sales Trans"
    When I click on the bag icon
    Then I scan UPC "<UPC>" into the bag
    And I can see the Restricted Item error
    When I close the Restricted Item error
    Then I can see Checkout empty bag view


    Examples:
      |UPC                    |
#External Civil Demand (Dept/Class 13/10)
      | 400822291158           |
#Internal Restitution (Dept/Class 13/11)
      | 400822291165           |
#Court Ordered Restitution (Dept/Class 13/26)
      | 400822291172           |