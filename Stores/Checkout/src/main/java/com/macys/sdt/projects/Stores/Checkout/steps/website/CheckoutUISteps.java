package com.macys.sdt.projects.Stores.Checkout.steps.website;

import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.projects.Stores.Checkout.utils.GalenCheckLayout;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.util.List;

public class CheckoutUISteps extends StepUtils {

	private GalenCheckLayout testElement = new GalenCheckLayout();

	@Then("^I should see SampleApp page UI as expected$")
	public void i_should_see_SampleApp_page_UI_as_expected() throws Throwable {

		List<String> tags = null;

	}

	@And("^the checkout button is centered$")
	public void theCheckoutButtonIsCentered() throws Throwable {
		Wait.secondsUntilElementPresent("bag_screen.checkout_button", 25);
		Elements.elementShouldBePresent("bag_screen.checkout_button");
		List<String> tags = null;
		String specPath = "Stores/Checkout/features/lib/sdu_556.gspec";
		testElement.checkLayout(specPath, tags);
	}

	@And("^I see the continue_cancel buttons aligned$")
	public void iSeeTheContinue_cancelButtonsAligned() throws Throwable {
		List<String> tags = null;
		String specPath = "Stores/Checkout/features/lib/CustomerOverlayValidation.gspec";
		testElement.checkLayout(specPath, tags);
	}
}