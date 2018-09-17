 #Author: Stores Domain Checkout Team
      #Story: SDU-493 - Checkout :: Main Menu/Landing Page
      #Date Created: 06/01/2017
      #Date Signed Off:

 @domain_stores @project_checkout @release_17 @story_SDU-493
 Feature: Story Phrase: As an associate, I want on offline capable landing page, so that I can access functions even when
   I am offline.

   @manual @Macy's
   Scenario: An associate logs in and sees the landing page
     Given I launch the offline landing page
     Then I can see the landing page
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I can see the sales trans landing page

   @manual @Bloomingdale's
   Scenario: An associate logs in and sees the landing page
     Given I launch the offline landing page
     Then I can see the landing page
     And I click on Hamburger icon
     And I click on Cancel Transaction button
     Then I can see the sales trans landing page