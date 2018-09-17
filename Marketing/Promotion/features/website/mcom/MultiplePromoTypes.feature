Feature: Promotion Scenarios with multiple promo types

  @domain_marketing @use_regression @Marketing_CBT
  Scenario: Verify applied promotion details in shopping bag and checkout page
    Given I visit the web site as a registered user
    And I verify multiple promotion combination in shopping bag
    | Promotions                                                 |
    | GWP + PWP                                                  |
    | GWP + Promocode($)                                         |
    | GWP + Qualified To Quantity                                |
    | GWP + Lowest Price                                         |
    | Gwp + Flat Fee                                             |
    | "GWP:Main item NOT available and Gift item available"      |
    | "GWP:Main item NOT available and Gift item NOT available"  |
    | PWP + Promocode(%)                                         |
    | PWP + Qualified To Quantity                                |
    | PWP + Lowest Price                                         |
    | Pwp + Flat Fee                                             |
    | Pwp + Free Shipping                                        |
    | Qualified To Quantity + Lowest Price                       |
    | Qualified To Quantity + Free Shipping                      |
    | Dollar Off + Lowest Price                                  |
    | Percent Off +Lowest Price                                  |
