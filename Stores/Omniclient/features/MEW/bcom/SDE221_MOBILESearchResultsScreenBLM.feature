# Author: Ovidiu Rucoi
# Story: SDE-221 - MOBILE: Search Results Screen
# Date Created: 07/21/2017
# Date Signed Off:

Feature: As an associate using the mobile application, I want to see customer and relationship information when
  searching for clients so that I can find and select the correct customer I am looking for.

  @domain_stores @omniclient @Story_SDE-221 @bcom @MEW
  Scenario Outline: First/Last Name Zip Search
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select FirstLastNameZip button from the Search All Clients page
    Then I should see the FirstLastNameZip search page
    When I input first name "<frst_nm>" on the FirstLastNameZip search page
    And I input last name "<lst_nm>" on the FirstLastNameZip search page
    And I input the zip code "<zip>" on the FirstLastNameZip search page
    And I click the Search button on the Search All Clients page
    Then I should see the number of search results found on the Results page
    And I should see the Affiliated with Me column on the Results page
    And I should see the affiliatedToMe check box if there is a relationship: "<aff_status>"
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    And I should not see the affiliatedToOthers check box

    Examples:
      | frst_nm | lst_nm | zip   | aff_status |
      | Karee   | Baree  | 44055 | yes        |
      | PAUL    | PAUL   | 44055 | no         |

  @domain_stores @omniclient @Story_SDE-221 @bcom @MEW
  Scenario Outline: Telephone Number Search
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Telephone button from the Search All Clients page
    Then I should see the Telephone Number search page
    When I input telephone number "<tel_nr>" on the Telephone Number search page
    And I click the Search button on the Search All Clients page
    Then I should see the number of search results found on the Results page
    And I should see the Affiliated with Me column on the Results page
    And I should see the affiliatedToMe check box if there is a relationship: "<aff_status>"
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    And I should not see the affiliatedToOthers check box

    Examples:
      | tel_nr     | aff_status |
      | 5038096333 | yes        |
      | 9519519511 | no         |

  @manual @domain_stores @omniclient @Story_SDE-221 @bcom @MEW
  Scenario: Credit Card Search - Client exists in DB
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe a credit card on the Credit Card search page
    Then I should see the number of search results found on the Results page
    And I should see the My Relationship column on the Results page
    And I should see the affiliatedToMe check box if there is a relationship: "<aff_status>"
    And I should see the Customer information on the Results page:
      | Name | AddressLine1 | AddressLine2 | City | State | Zip |
    And I should not see the affiliatedToOthers check box

  @manual @domain_stores @omniclient @Story_SDE-221 @bcom @MEW
  Scenario: Credit Card Search - Client does not exist in DB (operational)
    Given I launch the bloomingdales's omniclient page on mobile
    When I sign into OmniClient BLM mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select Credit Card button from the Search All Clients page
    Then I should see the Credit Card search page
    When I swipe a credit card on the Credit Card search page
    Then I should see the number of search results found on the Results page
    And I should see the My Relationship column on the Results page
    And I should see the affiliatedToMe check box if there is a relationship: "<aff_status>"
    And I should see the Customer information on the Results page:
      | Name |
    And I should not see the affiliatedToOthers check box