Feature: Verify MCOM 272 virtual gift card receipt email content

  @mcom_fil_272_18B_virtual_gift_card_receipt @regression
  Scenario: Verify Email content for for template mcom_fil_272_18B_virtual_gift_card_receipt
    Given i trigger "mcom_fil_272_18B_virtual_gift_card_receipt" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Hurray! You've received a Macy's E-Gift Card. View Account
      """
    And i should see body text for virtual gift card receipt:
      """
      Surprise! <billingAddr.firstname> sent you an E-Gift Card. Message from <billingAddr.firstname>: The Best Roomie EVER!To help get your face MACtified!Your favorite Roommate! How to redeem:Redeem your card in store or online.In Store: click on in store to print out the gift card information page with the bar code and present it to any Macy's Associate at checkout.Online: click on online to retrieve your E-Gift Card and CID numbers. Enter this information during checkout in the APPLY GIFT CARD section.Your Macy's E-Gift Card never expires.If you have any questions, please call our Gift Card hotline at 1-800-511-2752 7AM to 10PM (MT), Monday through Friday, or contact us online. Please have your Macy's Gift Card number available for all inquiries.
      """
     And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see card amount:
    """
    Card amount: $<ordertotal>
    """    
    And i should see Macys logo
    And i should see one "redeem in store" button
    

  @optional
  Scenario: Verify optional fields for mcom_fil_272_18B_virtual_gift_card_receipt
    Given i trigger "mcom_fil_272_18B_virtual_gift_card_receipt_optional" input through csp testemail
    When i navigate to view the email page
    And i should see Macys logo