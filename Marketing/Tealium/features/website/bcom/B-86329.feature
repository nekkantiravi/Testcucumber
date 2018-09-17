Feature: Verify the data layer attributes for Order confirmation page

  @Tealium_proxy @Datalayer
  Scenario: Verify the data layer attributes on order confirmation page
    Given I visit the web site as a guest user
    When I add an "available and member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "guest" user
    Then I verify the network call
      | chkout/rcplaceorder |
    Then I verify the data attributes for tealium tags on order confirmation page from utagData for "chkout/rcplaceorder"
      | pagetype              |
      | orderid               |
      | ordercurrency         |
      | ordersubtotal         |
      | ordershipping         |
      | ordertax              |
      | orderdiscount         |
      | orderpromocode        |
      | ordertotal            |
      | ordertotalitems       |
      | paymenttype           |
      | productname           |
      | productcategory_id    |
      | productcategory_name  |
      | productcolor          |
      | productsize           |
      | productprice          |
      | productquantity       |
      | productsubtotal       |
      | productupc            |
      | productregistryflag   |
      | productregistrynumber |


  @Tealium_proxy @Datalayer
  Scenario: Verify the order confirmation details attributes on order confirmation page
    Given I visit the web site as a registered user
    When I add an "available and member_product and orderable" product to my bag
    Then I checkout until I reach the order confirmation page as an "signedin" user
    Then I verify the network call
      | chkout/placeorder |
    Then I verify the data attributes for tealium tags on order confirmation page from utagData for "chkout/rcplaceorder"
      | pagetype              |
      | orderid               |
      | ordercurrency         |
      | ordersubtotal         |
      | ordershipping         |
      | ordertax              |
      | orderdiscount         |
      | orderpromocode        |
      | ordertotal            |
      | ordertotalitems       |
      | paymenttype           |
      | productname           |
      | productcategory_id    |
      | productcategory_name  |
      | productcolor          |
      | productsize           |
      | productprice          |
      | productquantity       |
      | productsubtotal       |
      | productupc            |
      | productregistryflag   |
      | productregistrynumber |
    Then I verify the data attributes for tealium tags on order confirmation page from utagData for "chkout/rcplaceorder"
      | orderCurrency |
      | orderDiscount |
      | orderId       |
