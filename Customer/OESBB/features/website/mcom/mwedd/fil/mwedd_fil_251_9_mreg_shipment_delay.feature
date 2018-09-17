Feature: Verify email content for mwedd_fil_251_9_mreg_shipment_delay template

  @mwedd_fil_251_9_mreg_shipement_delay @regression
  Scenario: Verify Email content for for template mwedd_fil_251_9_mreg_shipment_delay
    Given i trigger "mwedd_fil_251_9_mreg_shipment_delay" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      A Macy's registry gift order is delayed. View Registry
      """
    And i should see static contents:
      """
      We're sorry, but due to unexpected vendor delays, a registry gift ordered by your guest is delayed. The new estimated ship date is below. We apologize for the inconvenience.Want to make changes? Visit your registry.
      """
    Then i should see order number:
      """
      Order #: <ordernumber>	
      """
    Then i should see order capture date:
      """
      Order date: <orderdate>	
      """
    Then i should see firstname
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see button as "view my registry"
    Then i should see product name
    And i should see product quantity
    And i should see product color
    And i should see product size
    And i should see ship date
    And i should see shipment type
    And i should see product type
    And i should see product price
    And i should see estimated ship date
    And i should see product name url valid

  @optional
  Scenario: Verify optional fields empty scenario for template mwedd_fil_251_9_mreg_shipment_delay
    Given i trigger "mwedd_fil_251_9_mreg_shipment_delay_optional" input through csp testemail
    And i wait for enhanced payload to be sent to email provider
    When i navigate to view the email page
    Then i should see default categories:
      """
      FOR THE HOME FOB,MEN FOB,WOMEN FOB,SHOES FOB
      """