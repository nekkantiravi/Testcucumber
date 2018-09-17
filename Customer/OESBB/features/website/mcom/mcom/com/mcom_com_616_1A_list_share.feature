Feature: Verify MCOM 616 list share email content

  @regression @mcom_com_616_1A_list_share @mcom_com_616
  Scenario: Verify Email content for tempalte mcom_com_616_1A_source_payload
    Given i trigger "mcom_com_616_1A_list_share" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Check out what's on their list. Macy's List
      """
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see mail body text for list share:
      """
      see what <shipmentFirstNameFromEp> added to a macy's list!
      """
    And i should see button as "SEE THE COMPLETE LIST"
    And i should see item name
    And i should see item image
    And i should see item image url valid
    And i should see product name url valid

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_com_616_1A_source_payload
    Given i trigger "mcom_com_616_1A_list_share_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo
