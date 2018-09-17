@objects
    add_credit_card                 id          wlt_addCardButton
    card_type                       id          cardType
    card_number                     id          cardNumber
    exp_month                       id          expMonth
    exp_year                        id          expYear
    first_name                      id          billingAddress.firstName
    last_name                       id          billingAddress.lastName
    address_line_1                  id          billingAddress.addressLine1
    address_line_2                  id          billingAddress.addressLine2
    address_city                    id          billingAddress.city
    address_zip_code                id          billingAddress.zipCode
    address_state                   id          billingAddress.state
    phone_area_code                 id          billingAddress.phone.areaCode
    phone_exchange_number           id          billingAddress.phone.exchangeNumber
    phone_subscriber_number         id          billingAddress.phone.subscriberNumber
    email                           id          billingAddress.email
    cancel_button                   xpath       //input[contains(@class, 'wlt_overlayCancelButton')]
    save_button                     xpath       //input[contains(@class, 'wlt_overlaySaveButton')]

= My Wallet Page  =
    add_credit_card:
        width  ~186 px
        height ~27 px

    card_type:
        width  ~217 px
        height ~40 px

    card_number:
        width  ~217 px
        height ~40 px

    exp_month:
        width  ~139 px
        height ~40 px

    exp_year:
        width  ~72 px
        height ~40 px

    first_name:
        width  ~217 px
        height ~40 px

    last_name:
        width  ~217 px
        height ~40 px

    address_line_1:
        width  ~217 px
        height ~40 px

	address_line_2:
        width  ~217 px
        height ~40 px

	address_city:
        width  ~217 px
        height ~40 px

    address_state:
        width  ~217 px
        height ~40 px

    address_zip_code:
        width  ~217 px
        height ~40 px

    phone_area_code:
        width  ~54 px
        height ~40 px

    phone_exchange_number:
        width  ~54 px
        height ~40 px

    phone_subscriber_number:
        width  ~72 px
        height ~40 px

    email:
        width  ~217 px
        height ~40 px

    cancel_button:
        width  ~79 px
        height ~26 px

    save_button:
        width  ~75 px
        height ~26 px