Feature: Verify BCOM email content for fedfil_vgc template

  @blcom_fil_272_18A_fedfil_vgc @regression
  Scenario: Verify Email content for template blcom_fil_272_18A_fedfil_vgc
    Given i trigger "blcom_fil_272_18A_fedfil_vgc" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see preheader:
      """
      Clearly you have very stylish friends.
      """
    And i should see bloomingdales logo
    And i should see freeshipping image
    Then i should see header:
      """
      YOU'VE RECEIVED AN E-GIFT CARD
      """
    And i should see gift message for fedfil_vgc
    And i should see button as "shop now"
    And i should see button as "get barcode"
    And i should see gift card number
    And i should see the gift card text:
      """
      Save this email! You'll need your E-Gift Card number to shop on bloomingdales.com.
      """
