Feature: Verify BCOM 616 wishlistShare Email content

  @regression
  @blcom_com_616_1A_wishlistshare
  Scenario: Verify email content for template blcom_com_616_1A_wishlistShare
    Given i trigger "blcom_com_616_1A_wishlistshare" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see bloomingdales logo
    And i should see freeshipping image
    And i should see singleline header:
      """
      <shipmentname>'S WISHLIST
      """
    And i should see singleline body text:
      """
      A note from <shipmentname>,
      """
    Then i should see static contents:
      """
      A note from OES: thoughts on the below? I figured id give her 2 or 3 View Wish Link
      """
    And i should see "View Wish Link" link
    And i should see item name
    And i should see button as "VIEW ALL"
    And i should see item image url valid
    And i should see product name url valid
    
    
    @optional
    Scenario: Verify optional fields empty scenario for template blcom_com_616_1A_wishlistshare_optional
    Given i trigger "blcom_com_616_1A_wishlistshare_optional" input through csp testemail
    When i navigate to view the email page
    And i should see freeshipping image
 
