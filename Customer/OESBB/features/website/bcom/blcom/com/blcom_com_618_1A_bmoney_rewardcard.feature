Feature: Verify BCOM 618 bmoney rewardcard email content

  @blcom_com_618_1A_bmoney_rewardcard @regression
  Scenario: Verify Email content for template blcom_com_618_1A_bmoney_rewardcard_template
    Given i trigger "blcom_com_618_1A_bmoney_rewardcard" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
    And i should see preheader:
      """
      Go ahead, splurge a little.
      """
    And i should see static contents in bmoney rewardcard:
      """
      Print your Promotional Gift Card and give it to a store associate at checkout. No printer? Present the card in your bWallet from your mobile device.
      """
    And i should see static contents in bmoney rewardcard:
      """
      Your Promotional Gift Card has been automatically added to your bWallet, so you can easily apply it at checkout during the redemption period above.
      """
    And i should see redeem information in rewardcard:
      """
      CONGRATULATIONS! YOU'VE EARNED $<rewardCardAmt> Redeem it from: <rewardCardEffectiveDate> to <rewardCardExpirationDate>
      """
    And i should see "USE IT IN-STORE" label in bmoney rewardcard
    And i should see "USE IT ONLINE" label in bmoney rewardcard
    And i should see " GET MY GIFT CARD" label in bmoney rewardcard
    And i should see "GO TO MY bWALLET" label in bmoney rewardcard
    
    
    @blcom_com_618_1A_bmoney_rewardcard_optional 
  Scenario: Verify Email content for template blcom_com_618_1A_bmoney_rewardcard_template_optional
    Given i trigger "blcom_com_618_1A_bmoney_rewardcard_optional" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    And i should see bloomingdales logo
    And i should see freeshipping image
