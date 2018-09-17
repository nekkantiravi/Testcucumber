# Author: Ovidiu Rucoi
# Story: SDE-247 - MOBILE: Existing Customer Add to Book Process - Add/Edit Customer Info
# Date Created: 08/09/2017
# Date Signed Off:

Feature: As an associate using the mobile application, I want to modify customer information when adding a customer
  to my book so that I can ensure I have the best contact information available.

  @domain_stores @omniclient @Story_SDE-247 @mcom @MEW
  Scenario: Add to Book - display Information Fields
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select FirstLastNameZip button from the Search All Clients page
    Then I should see the FirstLastNameZip search page
    When I input first name "PAULA" on the FirstLastNameZip search page
    And I input last name "SURACE" on the FirstLastNameZip search page
    And I input the zip code "06069" on the FirstLastNameZip search page
    And I click the Search button on the Search All Clients page
    Then I should see the Customer information on the Results page:
      | Name | AddressLine1 |
    When I click on the Client on the Results page
    And I select the Add to Book button
    And I should see the Name Information locked down and not editable
    And I should see the Required Information editable
      | Phone Nbr | Addr Line 1 | City | State | Zip |
    And I should see the Optional Information editable
      | Pref Name | Addr Line 2 |
    And I write the phone number in the Phone number field mobile"4404404404"
    When I tap on Save button from Add Client page
    Then I should see the following fields on the Add to Book page
      | Hint | Notes | Pref Contact Method |


  @manual @domain_stores @omniclient @Story_SDE-247 @mcom @MEW
  Scenario: Add to Book - Check DB for primary vs preferred info
    Given I launch the macy's omniclient page on mobile
    When I sign into OmniClient mobile application as Associate
    And I click on the Search All Clients button
    Then I should see the Search All Clients page
    When I select FirstLastNameZip button from the Search All Clients page
    Then I should see the FirstLastNameZip search page
    When I input first name "<frst_nm>" on the FirstLastNameZip search page
    And I input last name "<lst_nm>" on the FirstLastNameZip search page
    And I input the zip code "<zip>" on the FirstLastNameZip search page
    And I click the Search button on the Search All Clients page
    Then I should see the Customer information on the Results page:
      | Name | AddressLine1 |
    When I click on the Client on the Results page
    And I select the Add to Book button
    And I write the required Address "NEW ADDRESS"
    And I write the phone number in the Phone number field mobile"4445556666"
    And I select the Phone Type from Phone Type dropdown
    And I tap on Save button from Add Client page
    And I write a hint in the Hint field "new hint"
    And I select Preferred Contact method as phone from dropdown
    And I tap on Continue button from Add to Book page
#    And I input the signature omniclient
#    And I click on I Agree from signature box omniclient
    And I tap OK in the add confirmation popup
    And I tap Save button from Add to Book page
    And I tap OK in the Successfully Added client popup
    And I check the OCL database
    Then I should see the original address saved as the primary address and the updated address saved as the preferred address
    And I should see the original phone number saved as the primary phone number and the updated phone number saved as the secondary (preferred) number