@objects
    first_name                      id          firstName
    last_name                       id          lastName
    address_line_1                  id          shippingContact.address.addressLine1
    address_line_2                  id          addressLine2
    address_city                    id          city
    address_zip_code                id          postalCode
    address_state                   id          shippingContact.address.state
    phone_area_code                 id          areaCode
    phone_exchange_number           id          exchangeNbr
    phone_subscriber_number         id          subscriberNbr
    add_address_button              xpath       //button[@name='processShippingAddress']

= Add Address Page  =
    first_name:
        width  ~171 px
        height ~45 px

    last_name:
        width  ~171 px
        height ~45 px

    address_line_1:
        width  ~171 px
        height ~45 px

	address_line_2:
        width  ~171 px
        height ~45 px

	address_city:
        width  ~171 px
        height ~45 px

    address_state:
        width  ~171 px
        height ~45 px

    address_zip_code:
        width  ~78 px
        height ~45 px

    phone_area_code:
        width  ~50 px
        height ~45 px

    phone_exchange_number:
        width  ~46 px
        height ~45 px

    phone_subscriber_number:
        width  ~50 px
        height ~45 px

    add_address_button:
        width  ~136 px
        height ~38 px