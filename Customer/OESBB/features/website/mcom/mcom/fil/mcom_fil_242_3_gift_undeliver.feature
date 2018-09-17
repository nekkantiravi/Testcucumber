Feature: Verify MCOM 242 email content

  @mcom_fil_242_3_gift_undeliver @regression
  Scenario: Verify email content for tempalte mcom_fil_242_3_gift_undeliver
    Given i trigger "mcom_fil_242_3_gift_undeliver" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    #Then i sould see subject as
    Then i should see static contents:
      """
      Your E-Gift Card couldn't be delivered to the email address you provided. We've refunded your account for the amount below. To order a new card, <link>shop E-Gift Cards<link>. Thanks for shopping at Macy's.
      """
    And i should see preheader:
      """
      We tried to deliver your Macy's E-Gift Card.<link>Shop E-Gift Cards<link>
      """
    And i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see order number
    And i should see order capture date
    And i should see firstname
    And i should see credit card type
    And i should see refund amount
    And i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product type
    And i should see product image
    And i should see product image url valid
    And i should see product name url valid
    And i should see button as "SHOP E-GIFT CARDS"
    
  @optional
  Scenario: Verify optional fields empty scenario for template mcom_fil_242_3_gift_undeliver
    Given i trigger "mcom_fil_242_3_gift_undeliver_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo