Feature: As an SA, I am creating this story to handle legacy Store's URLs
  Stories included:
  B-02838 : Tech/QE : MCOM/BCOM Stores Legacy URL interpretation

  # replaces manual regression scenario 10
  @v1-b-02838 @regression @use_mew @domain_marketing
  Scenario Outline: Verify that URL patterns are handled correctly
    Given I visit the mobile web home page
    When I deeplink to an iPhone "<Desktop_URL>" URL
    Then I should see the corresponding iPhone "<APP_URL>" routed to MEW
    Examples:
      | Desktop_URL                                                 | APP_URL                          |
      | store/index.ognc                                            | shop/store/search                |
      | store/event/index.ognc                                      | shop/store/search                |
      | store/locator/index.ognc                                    | shop/store/search                |
      | store/catalog/index.ognc                                    | circularhub.com                  |
      # This was already commented out!
      # | campaign/social?campaign_id=61&channel_id=1                 | shop/coupons-deals               |

