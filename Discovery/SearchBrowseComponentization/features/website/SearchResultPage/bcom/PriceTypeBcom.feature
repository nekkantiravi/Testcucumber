# Author: Discovery QE
# Date Created: 01/20/2017

Feature: Verifying Product Thumbnail details for each PriceType product in  Search Results Page

  # ******************************* PRICE TYPE ID = 0 PRICE TYPE = Ticket  **********************************************************
  # *******************************          WITH COLORWAY                 **********************************************************

  @scenario_PriceType0_MemberProduct_withcolorway @snbc_priceType @notAvailInRegistry
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for MEMBER product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      | iship     |
      | registry  |

  @scenario_PriceType0_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for MASTER product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType0_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType0_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType0_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for MASTER product without Colorway Pricing
    Given I am on SearchResultsPage for "nambé tri-corner" in <Site_Mode> mode
    When I select a "master" product "WithoutColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |


  @scenario_PriceType0_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=0) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 0 and PriceType = "Ticket"
    Then I verify that Product Thumbnail PriceTypeID = 0 and PriceType = "Ticket" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |


 # ******************************* PRICE TYPE ID = 11    PRICE TYPE = Markup       **********************************************************
 # *******************************               WITH COLORWAY                     **********************************************************


  @scenario_PriceType11_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "600TC Sateen Solid Standard Pillowcase" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType11_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for MASTER product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType11_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType11_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for MEMBER product without Colorway Pricing
    Given I am on SearchResultsPage for "grosgrain dinneware" in <Site_Mode> mode
    When I select a "member" product "WithoutColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType11_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType11_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=11) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 11 and PriceType = "Markup"
    Then I verify that Product Thumbnail PriceTypeID = 11 and PriceType = "Markup" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

# *******************************   PRICE TYPE ID = 9    PRICE TYPE = Revalue       **********************************************************
 # *******************************               WITH COLORWAY                      **********************************************************

  @scenario_PriceType9_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for MEMBER product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType9_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for MASTER product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType9_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType9_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType9_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType9_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=9) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 9 and PriceType = "Revalue"
    Then I verify that Product Thumbnail PriceTypeID = 9 and PriceType = "Revalue" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

# ******************************* PRICE TYPE ID = 1 PRICE TYPE = Promo Percent Off  **********************************************************
# *******************************               WITH COLORWAY                       **********************************************************

  @scenario_PriceType1_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "french garden dinnerware" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType1_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "French Garden Bread & Butter Plate" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType1_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType1_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType1_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType1_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=1) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 1 and PriceType = "Promo Percent Off"
    Then I verify that Product Thumbnail PriceTypeID = 1 and PriceType = "Promo Percent Off" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 3 PRICE TYPE = Promo Sale Price  **********************************************************
 # *******************************               WITH COLORWAY                       **********************************************************

  @scenario_PriceType3_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "haviland symphony" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType3_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "gold dessert plate" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType3_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType3_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType3_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType3_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=3) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 3 and PriceType = "Promo Sale Price"
    Then I verify that Product Thumbnail PriceTypeID = 3 and PriceType = "Promo Sale Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |


 # ******************************* PRICE TYPE ID = 4 PRICE TYPE = Promo Event Price  **********************************************************
 # *******************************               WITH COLORWAY                       **********************************************************

  @scenario_PriceType4A_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "haviland symphony" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType4A_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "gold dessert plate" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType4A_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType4A_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType4A_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType4A_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4A) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event Price" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 4 PRICE TYPE = Promo Event2 Price  **********************************************************
 # *******************************               WITH COLORWAY                        **********************************************************

  #@scenario_PriceType4B_MemberProduct_withcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "haviland symphony" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType4B_MasterProduct_withcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "gold dessert plate" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType4B_StandardProduct_withcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  #@scenario_PriceType4B_MemberProduct_withoutcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType4B_MasterProduct_withoutcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType4B_StandardProduct_withoutcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=4B) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 4 and PriceType = "Promo Event2 Price"
    Then I verify that Product Thumbnail PriceTypeID = 4 and PriceType = "Promo Event2 PriceType4" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 15 PRICE TYPE = LPOS **********************************************************
 # *******************************               WITH COLORWAY          **********************************************************

  @scenario_PriceType15_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "haviland symphony" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType15_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "gold dessert plate" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType15_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType15_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType15_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #Xl product not available in qa17 also
  #@scenario_PriceType15_StandardProduct_withoutcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=15) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 15 and PriceType = "LPOS"
    Then I verify that Product Thumbnail PriceTypeID = 15 and PriceType = "LPOS" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 20 PRICE TYPE = Special Savings **********************************************************
 # *******************************               WITH COLORWAY                 **********************************************************

  @scenario_PriceType20_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "bernardaud athena" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType20_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "athena service plate" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType20_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType20_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType20_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType20_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=20) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 20 and PriceType = "Special Savings"
    Then I verify that Product Thumbnail PriceTypeID = 20 and PriceType = "Special Savings" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 5 PRICE TYPE = Additional Off promo    **********************************************************
 # *******************************                    WITH COLORWAY                       **********************************************************

  #@scenario_PriceType5C_MemberProduct_withcolorway
  @ignore @snbc_priceType_new @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "french garden dinnerware" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5C_MasterProduct_withcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "French Garden Rim Soup Bowl" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5C_StandardProduct_withcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  #@scenario_PriceType5C_MemberProduct_withoutcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5C_MasterProduct_withoutcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType5C_StandardProduct_withoutcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=5C) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 5 and PriceType = "Additional Off promo"
    Then I verify that Product Thumbnail PriceTypeID = 5 and PriceType = "Additional Off promo" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 6 PRICE TYPE = Additional Off Perm    **********************************************************
 # *******************************                    WITH COLORWAY                      **********************************************************

  #@scenario_PriceType6_MemberProduct_withcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "Switch 3 Dinnerware" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType6_MasterProduct_withcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "Switch 3 Assorted Bread & Butter Plates" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType6_StandardProduct_withcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  #@scenario_PriceType6_MemberProduct_withoutcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType6_MasterProduct_withoutcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  #@scenario_PriceType6_StandardProduct_withoutcolorway
  @ignore @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=6) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 6 and PriceType = "Additional Off Perm"
    Then I verify that Product Thumbnail PriceTypeID = 6 and PriceType = "Additional Off Perm" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 7 PRICE TYPE = Permanent Markdown     **********************************************************
 # *******************************                    WITH COLORWAY                      **********************************************************

  @scenario_PriceType7_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for MEMBER product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Given I am on SearchResultsPage for "Switch 3 Dinnerware" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType7_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "Switch 3 Assorted Rim Soup Bowls" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType7_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType7_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType7_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType7_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=7) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 7 and PriceType = "Permanent Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 7 and PriceType = "Permanent Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 8 PRICE TYPE = Clearance Markdown    **********************************************************
 # *******************************                    WITH COLORWAY                      **********************************************************

  @scenario_PriceType8_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for MEMBER product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType8_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "audun dinnerwar" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType8_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType8_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType8_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType8_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=8) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 8 and PriceType = "Clearance Markdown"
    Then I verify that Product Thumbnail PriceTypeID = 8 and PriceType = "Clearance Markdown" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************   PRICE TYPE ID = 16 PRICE TYPE = Closeout             **********************************************************
 # *******************************                    WITH COLORWAY                       **********************************************************

  @scenario_PriceType16_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=16) in ALL modes for MEMBER product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType16_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=16) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "Audun Assorted Rim Soup Bowls" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType16_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=16) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType16_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=16) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType16_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=16) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType19_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=16) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 16 and PriceType = "Closeout"
    Then I verify that Product Thumbnail PriceTypeID = 16 and PriceType = "Closeout" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # ******************************* PRICE TYPE ID = 10 PRICE TYPE = Markdown Cancel    **********************************************************
 # *******************************                    WITH COLORWAY                   **********************************************************

  @scenario_PriceType10_MemberProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for MEMBER product with Colorway Pricing
    Given I am on SearchResultsPage for "constance by bernardaud" in <Site_Mode> mode
    When I select a "member" product "WithColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType10_MasterProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for MASTER product with Colorway Pricing
    Given I am on SearchResultsPage for "Constance Bread & Butter Plate" in <Site_Mode> mode
    When I select a "master" product "WithColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType10_StandardProduct_withcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for STANDARD product with Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

 # *******************************          WITHOUT COLORWAY                 **********************************************************

  @scenario_PriceType10_MemberProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for MEMBER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "member" product "WithoutColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType10_MasterProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for MASTER product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "master" product "WithoutColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

  @scenario_PriceType10_StandardProduct_withoutcolorway @snbc_priceType
  Scenario Outline:  SearchResultsPage - Verify Price Type=Ticket(ID=10) in ALL modes for STANDARD product without Colorway Pricing
    Given I am SearchResultsPage in <Site_Mode> mode for "standard" product "WithoutColorWay" pricing of PriceTypeID = 10 and PriceType = "Markdown Cancel"
    Then I verify that Product Thumbnail PriceTypeID = 10 and PriceType = "Markdown Cancel" is displayed
    Examples:
      | Site_Mode |
      | Domestic  |
      |  iship    |
      | registry  |

