Feature: Verify MCOM 603 paperless COM 603 1A MCOM  email content

  @mcom_com_603_1A_paperless @regression
  Scenario: Verify Email content for for template mcom_com_603_1A_paperless
    Given i trigger "mcom_com_603_1A_paperless" input through csp testemail
    And i have an enhanced payload sent to email provider
    When i navigate to view the email page
    Then i should see pre header:
      """
      Enjoy $10 off your purchase of $75 or more. Shop Macy's
      """
    Then i should see default categories:
      """
      FOR THE HOME,MEN,WOMEN,SHOES
      """
    And i should see Macys logo
    And i should see body text for 603_paperless:
      """
      THANKS FOR GOING PAPERLESS! We'll notify you every time you have a new statement ready for review on macys.com. As our thanks, please enjoy this great offer.

      """
    And i should see promo offers for 603_paperless:
      """
      TAKE $10 OFF Your next in store or online purchase of $75** or more. + FREE SHIPPING on your next purchase of $100 or more. US shipping, exclusions apply*
      """
    And i should see static promo legal disclaimer for 603_paperless:
      """
      * FOR SHIPPING OFFER(S): See Details & Exclusions ** TO GET EXTRA SAVINGS: Place qualifying items in shopping bag, then enter promo code X672FUD248 in the box labeled "HAVE A PROMO CODE?" and click "APPLY." You can enter the promo code in your shopping bag at any time before or during checkout. Only one promo code may be used per transaction. Excludes ALL: cosmetics/fragrances, Deals of the Day, Doorbusters/web busters, electrics/electronics, Everyday Values (EDV), furniture/mattresses, Last Act, Macy's Backstage, rugs, specials, super buys, Breville, Coach, Dallas Cowboys merchandise, Dyson, Fitbit, Frye, Hanky Panky, Jack Spade, Kate Spade, KitchenAid Pro Line, Le Creuset, Levi's, Locker Room by Lids, Marc Jacobs, Michele watches, Natori, New Era, Nike on Field, Sam Edelman, Samsung watches, Shun, Stuart Weitzman, The North Face, Theory, Tumi, Vitamix, Wacoal, Wolford, Wüsthof, athletic clothing, shoes & accessories; designer jewelry/watches, designer sportswear, gift cards, jewelry trunk shows, previous purchases, select licensed depts., services, special orders, special purchases, tech watches; PLUS, ONLINE ONLY: baby gear, kids' shoes, Allen Edmonds, Brahmin, Birkenstock, Hurley, Johnston & Murphy, Merrell, RVCA, Tommy Bahama, toys. Cannot be combined with any savings pass/coupon, extra discount or credit offer except opening a new Macy's account. Extra savings % applied to reduced prices. Extra savings end December 6, 2017.
      """
    And i should see "Shop in store:" text
    And i should see "Extra savings promo code:" text
    And i should see "Print savings pass" button
    And i should see the "SHOP MACY'S" button

  @optional
  Scenario: Verify optional fields empty scenario for template mcom_com_603_1A_paperless
    Given i trigger "mcom_com_603_1A_paperless_optional" input through csp testemail
    When i navigate to view the email page
    Then i should see Macys logo
