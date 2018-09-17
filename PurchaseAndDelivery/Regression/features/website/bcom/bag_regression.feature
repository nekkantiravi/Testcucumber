Feature: Bag Regression

  ################### Promo Lab Scenarios #######################

  Scenario Outline: Guest User - Over Threshold
    Given I visit the website as a guest user using rest services
    And I make sure I'm "<threshold_status>" with "<product_qty>" for "<promoType>" promotion with "<trigger_type>" triggers
    When I access the bag page
    Then I should see the expected messages at the line item level
    Examples:
      | threshold_status | product_qty                | promoType           | trigger_type |
      | Over Threshold   | One Product                | Promotional Pricing | Quantity     |
      | Over Threshold   | Different Products         | Promotional Pricing | Quantity     |
      | Over Threshold   | One Product Different UPCs | Promotional Pricing | Quantity     |
      | Over Threshold   | One Product                | Promotional Pricing | Subtotal     |
      | Over Threshold   | Different Products         | Promotional Pricing | Subtotal     |
      | Over Threshold   | One Product Different UPCs | Promotional Pricing | Subtotal     |

  Scenario Outline: Signed In - Over Threshold
    Given I visit the website as a registered user using rest services
    And I make sure I'm "<threshold_status>" with "<product_qty>" for "<promoType>" promotion with "<trigger_type>" triggers
    When I access the bag page
    Then I should see the expected messages at the line item level
    Examples:
      | threshold_status | product_qty                | promoType           | trigger_type |
      | Over Threshold   | One Product                | Promotional Pricing | Quantity     |
      | Over Threshold   | Different Products         | Promotional Pricing | Quantity     |
      | Over Threshold   | One Product Different UPCs | Promotional Pricing | Quantity     |
      | Over Threshold   | One Product                | Promotional Pricing | Subtotal     |
      | Over Threshold   | Different Products         | Promotional Pricing | Subtotal     |
      | Over Threshold   | One Product Different UPCs | Promotional Pricing | Subtotal     |

