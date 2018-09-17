package com.macys.sdt.projects.Selection.PDP.steps.website.mcom;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Navigate;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.db.models.PromotionService;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class StepDefOlapic extends StepUtils {

	private static Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());
	public static String promotionalProdId = null;
	public static String promotionCode = null;


	@When("^I scroll down to view olapic panel")
	public static void I_scroll_down_to_view_olapic_panel() throws Throwable {
		scrollToLazyLoadElement("olapic.bbolapic_carousel");
		Wait.secondsUntilElementPresent("olapic.add_photo_button", 10);
		Assert.assertTrue("Scrolling down PDP successfully!", true);
	}

	@And("^I click on Olapic icon$")
	public static void I_click_on_Olapic_icon() throws Throwable {
		Elements.elementPresent("pdp.olapicIcon");
		Clicks.click("pdp.olapicIcon");
	}

	@Then("^I click the add photo button$")
	public static void I_click_the_add_photo_button() throws Throwable {

		boolean pbElement = Elements.elementPresent("olapic.add_photo_button");
		if (pbElement) {
			try {
				Clicks.click("olapic.add_photo_button");
				Assert.assertTrue("Able to Add a Photo successfully!", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Failed to Add a Photo successfully!" + e);
			}
		} else {
			Assert.fail("Add a Photo button is not visible on olapic panel!");
		}
	}

	@When("^I close the photo uploader$")
	public static void I_close_the_photo_uploader() throws Throwable {

		if (Elements.elementPresent("olapic.ola_uploadphoto_close")) {
			try {
				Clicks.click("olapic.ola_uploadphoto_close");
				Assert.assertTrue("Photo uploader closed successfully!", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Failed to click close Photo uploader button successfully!" + e);
			}
		} else {
			Assert.fail("Unable to find the photouploader screen close element!");
		}
	}

	@Then("^I click on view gallery$")
	public static void I_click_on_view_gallery() throws Throwable {

		Wait.secondsUntilElementPresent("olapic.view_gallery_button", 5);
		if (Elements.elementPresent("olapic.view_gallery_button")) {
			try {
				Clicks.click("olapic.view_gallery_button");
				Assert.assertTrue("Able to View Gallery successfully!", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Olapic View Gallery button not visible!" + e);
			}
			try {
				Wait.secondsUntilElementPresent("olapic.view_gallery_Wall", 20);
				scrollToLazyLoadElement("olapic.view_gallery_Wall");
				Assert.assertTrue("Able to view photo gallery successfully!", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Not able to scroll down macyslove gallery!" + e);
			}
		}
	}

	@When("^I added an? \"(.*?)\" product to my bag(?: that is not(?: an?)? \"(.*?)\")?(?: and \"?(.*?)\"? ?checkout)?$")
	public void I_added_a_product_to_my_bag(String productTrue, String productFalse, String checkout) throws Throwable {

		if (prodEnv()) {
			CommonUtils.navigateToRandomProduct(productTrue, productFalse);
		} else {
			// All the none production related promotional products need to be
			// get from siteDB with their respective attributes
			switch (productTrue) {
				case "orderable and promo_code_eligible":
					String[] productsWithPromoCode = new PromotionService().getPromoCodeForPromotionalProducts();
					if (productsWithPromoCode == null)
						Assert.fail("ERROR-DATA: Promo-Code eligible promotions are not available in database!!");
					promotionalProdId = productsWithPromoCode[1];
					promotionCode = productsWithPromoCode[0];
					CommonUtils.navigateDirectlyToProduct(promotionalProdId);
					break;
				case "orderable and pwp":
					// TODO
					break;
				default:
					CommonUtils.navigateToRandomProduct(productTrue, productFalse);
			}
		}
	}

	@And("^I click on Olapic next & previous arrows$")
	public static void I_click_on_Olapic_next_previous_arrows() throws Throwable {
		Elements.elementPresent("pdp.olapicIcon");
		Clicks.click("pdp.olapicIcon");
		Wait.secondsUntilElementPresent("pdp.olapicRightArrow", 20);
		if (Elements.elementPresent("pdp.olapicRightArrow")) {
			try {
				for (int i = 0; i < 2; i++) {
					Clicks.click("pdp.olapicRightArrow");
					Thread.sleep(2000);
				}
				Assert.assertTrue("Successfully clicked the Olpaic next arrow!", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Olapic arrows not visible:" + e);
			}
		} else {
			Assert.fail("Olapic next arrow not visible!");
		}
		if (Elements.elementPresent("pdp.olapicLeftArrow")) {
			try {
				for (int j = 0; j < 2; j++) {
					Clicks.click("pdp.olapicLeftArrow");
					Thread.sleep(2000);
				}
				Assert.assertTrue("Successfully clicked the Olpaic previous arrow!", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Olapic arrows not visible:" + e);
			}
		} else {
			Assert.fail("Olapic previous arrow not visible!");
		}
	}

	@When("^I click on an Olapic image$")
	public static void I_click_on_an_Olapic_image() throws Throwable {
		Wait.untilElementPresent("olapic.olapic_image_click");
		Assert.assertTrue("Unable to find a Olapic image!",
				Elements.elementPresent("olapic.olapic_image_click"));
		Clicks.clickRandomElement("olapic.olapic_image_click",WebElement::isDisplayed);
	}

	@Then("^I verify that Olapic section is (available|unavailable) on (member|master) PDP (site|iship|registry) mode$")
	public static void verify_availibility_of_Olapic_section(String arg, String pg, String mode) throws Throwable {

		try {
			Wait.forPageReady();
			Wait.secondsUntilElementPresent("pdp.productTitle", 20);
		} catch (Exception e) {
			logger.warning(String.format(pg + " PDP not loading properly as productTitle is not displayed in " + mode + " mode!\n"));
			e.printStackTrace();
		}
		Navigate.execJavascript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
		Navigate.execJavascript("window.scrollTo(document.body.scrollHeight, 0)");
		
		switch(arg) {
			case "available": {
				if(pg.equalsIgnoreCase("member")) {
					Wait.secondsUntilElementPresent("olapic.olapicSection", 15);
					Assert.assertTrue("Olapic section not displayed/loaded!", Elements.elementPresent("olapic.olapicSection"));
				}
				else
					Assert.assertFalse("Olapic section is displayed/loaded!", Elements.elementPresent("olapic.olapicSection"));
				break;
			}
			case "unavailable": {
				if(pg.equalsIgnoreCase("master"))
					Assert.assertFalse("Olapic section is displayed/loaded!", Elements.elementPresent("olapic.olapicSection"));
				else
					Assert.assertTrue("Olapic section not displayed/loaded!", Elements.elementPresent("olapic.olapicSection"));
				break;
			}
		}
		Thread.sleep(2000);
	}

	@Then("^I verify redirecting to radical PDP after clicking Olapic product list$")
	public static void I_verify_landing_on_radical_PDP_after_clicking_Olapic_product_list() throws Throwable {
		Wait.untilElementPresent("olapic.olapic_productlist");
		Assert.assertTrue("Unable to see Olapic product list!",
				Elements.elementPresent("olapic.olapic_productlist"));
		Clicks.clickRandomElement("olapic.olapic_productlist");

		Wait.untilElementPresent("pdp.add_to_bag");
		if(Elements.elementPresent("pdp.add_to_bag")){
			Assert.assertTrue("Able to see addtobag button on PDP", true);
		}else if(Elements.elementPresent("pdp.productUnavailable")){
			Assert.assertTrue("This product is currently unavailable", true);
		}else{
			Assert.fail("PDP not loading properlly!");
		}
	}


}
