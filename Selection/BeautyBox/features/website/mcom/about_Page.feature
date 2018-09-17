Feature: Beauty Box about page Feature File

  @about
  Scenario: As a logged in user I want to see about page to validate beautybox video
  Given I visit the web site as a guest user
  And I launch beautybox about page url
  Then I should see video on the about subscribe page and I play the video
  And I click on video to pause the video


  @15 @tag1 @abouthowitworksa @beauty
  Scenario: As a logged in user I want to see how it works on beauty box monthly subscription page
  Given I visit the web site as a guest user
  And I launch beautybox about page url
  Then I see how it works on monthly subscription page
  And I see join and its description
  And I see get your box and its description
  And I see discover and its description

  @15 @tag1 @about @WIP
  Scenario: As a logged in user I want to see all months banners romance text and  monthly boxes
  Given I visit the web site as a guest user
  And I launch beautybox about page url
  Then I see ourboxes section and its dropdown background image and monthly text


  @16 @tag @abouttopbanner
  Scenario: As a logged in user I want to see top and bottom banners
  Given I visit the web site as a guest user
  And I launch beautybox about page url
  Then I see top banner on about page
  And I see bottom banner on about page