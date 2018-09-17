Feature: Footer Sign in

  @dsv_mew_sev2 @domain_marketing
  Scenario: Footer appears on Home Page
    Given I visit the mobile web site as a guest user
    Then I should see the footer elements displayed at the bottom of the page
      | text              | element                |
      | Footer            | footer                 |
      | Change Country    | change_country_link    |
      | Sign In Link      | goto_sign_in_link      |
      | Email Text Signup | goto_email_text_signup |
      | Footer links      | footer_bottom_links    |
      | Pinterest         | footer_social_links    |
      | Facebook          | footer_social_links    |
      | Twitter           | footer_social_links    |
      | Instagram         | footer_social_links    |
      | Become a Loyalist | become_loyallist       |
      | Promotions        | promotions_link        |
      | Footer container  | footer_container       |

