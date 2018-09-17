package com.macys.sdt.projects.Marketing.ChatBot.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.TextBoxes;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class ChatBotStepdefs {
    @Given("^I visit the messenger web site$")
    public void iVisitTheMessengerWebSite() throws Throwable {
        Navigate.visit("home");
    }

    @When("^I sign-in using my credentials$")
    public void iSignInUsingMyCredentials() throws Throwable {
        TextBoxes.typeTextbox("chatbot.email", "4086189910");
        TextBoxes.typeTextbox("chatbot.pass", "testchatbot!");
        Clicks.click("chatbot.login");
        Thread.sleep(2000);
    }

    @Then("^I see messenger signed-in page$")
    public void iShouldSeeMessengerSignedInPage() throws Throwable {

/*      if (Elements.elementPresent ("chatbot.convAction")){
            Clicks.click("chatbot.convAction");
            if (Elements.elementPresent ("chatbot.delChat")){
                Clicks.click("chatbot.delChat");
            } else {
                System.out.println("Did not find Delete Conversation option");
            }
        } else {
            System.out.println("Did not find Conversation Actions button");
        }
*/
        if (Elements.elementPresent ("chatbot.chatName")){
            System.out.println ("Found chat Mbot");
        } else {
            System.out.println ("Did not find chat Mbot");
        }
        Thread.sleep(2000);

        if (Elements.elementPresent ("chatbot.getStarted")){
            Clicks.click("chatbot.getStarted");
        } else {
            System.out.println("Did not find Get Started button");
        }
        Thread.sleep(4000);

    }

    @Then("^I see \"([^\"]*)\" button$")
    public void iSeeButton(String arg0) throws Throwable {
        if (Elements.elementPresent ("chatbot."+arg0)){
            System.out.println ("Found button: "+arg0);
        } else {
            System.out.println ("Did not find button: "+arg0);
        }
    /*    if (Elements.elementPresent ("chatbot.sendMsg")){
            System.out.println ("Found Send Message button");
            Clicks.click ("chatbot.sendMsg");
        } else {
            System.out.println ("Did not find Send Message button");
        }

    */
        Thread.sleep(2000);

    }

    @When("^I type \"([^\"]*)\" in the chat$")
    public void iTypeInTheChat(String arg0) throws Throwable {
        Thread.sleep(2000);
        if (Elements.elementPresent ("chatbot.enterText")){
            Clicks.click("chatbot.enterText");
            Thread.sleep(2000);
            TextBoxes.typeTextNEnter("chatbot.enterText", arg0);
            Thread.sleep(2000);
        /*    if (Elements.elementPresent ("chatbot.sendText")){
                Clicks.click("chatbot.sendText");
            } else {
                System.out.println("Did not find element to Send text");
            } */
        } else {
            System.out.println("Did not find element to enter text");
        }

    }

    @And("^I click on \"([^\"]*)\" button$")
    public void iClickOnButton(String arg0) throws Throwable {
        Thread.sleep(2000);
        System.out.println("Looking for element chatbot."+arg0);
        if (Elements.elementPresent ("chatbot."+arg0)){

            Clicks.click("chatbot."+arg0);
        } else {
            System.out.println("Did not find button: "+arg0);
        }
        Thread.sleep(2000);

    }


    @Then("^I see \"([^\"]*)\" text$")
    public void iSeeText(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Looking for element chatbot."+arg0);
        if (Elements.elementPresent ("chatbot."+arg0)){
            System.out.println("Found text: "+arg0);
        } else {
            System.out.println("Did not find text: "+arg0);
        }
        Thread.sleep(2000);

    }

    @And("^I search for Macy's bot in the search and select Macy's chat$")
    public void iSearchForMacySBotInTheSearch() throws Throwable {

        if (Elements.elementPresent ("chatbot.newMsg")){
            Clicks.click("chatbot.newMsg");
        } else {
            System.out.println("Did not find New message option");
        }
        Thread.sleep(2000);
        if (Elements.elementPresent ("chatbot.chatSelect")){
            TextBoxes.typeTextbox("chatbot.chatSelect","macysmsgbot");
        } else {
            System.out.println("Did not find chatSelect element");
        }

        Thread.sleep(2000);
        if (Elements.elementPresent ("chatbot.selectBot")){
            Clicks.click("chatbot.selectBot");
        } else {
            System.out.println("Did not find Bot");
        }
    }

    @Then("^I should see that call is initiated from device$")
    public void iShouldSeeThatCallIsInitiatedFromDevice() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @And("^I terminate the call$")
    public void iTerminateTheCall() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
