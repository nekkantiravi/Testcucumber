package com.macys.sdt.projects.Selection.Gamification.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
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

public class OlapicWidget extends StepUtils {

	public static String brandSearch = null;
	public static int filterCount;
	public static String promotionalProdId = null;
	public static String promotionCode = null;

	@When("^I scroll down the PDP page to view the olapic widget$")
	public static void I_scroll_down_the_PDP_page_to_view_the_olapic_widget() throws Throwable {
		scrollToLazyLoadElement("olapic.bbolapic_carousel");
		Wait.secondsUntilElementPresent("olapic.add_photo_button", 10);
		Assert.assertTrue("Able to scroll down the PDP page successfully to view the olapic", true);
	}

	@And("^I click on shop and return$")
	public static void iClickOnShop() throws Throwable {
		Elements.elementPresent("olapic.olapic_pdp_shippingnreturn");
		Clicks.click("olapic.olapic_pdp_shippingnreturn");
	}

	@Then("^I click on add photo button$")
	public static void I_see_the_olapic_widget_on_radical_pdp() throws Throwable {

		boolean pbElement = Elements.elementPresent("olapic.add_photo_button");
		if (pbElement) {
			try {
				Clicks.click("olapic.add_photo_button");
				Assert.assertTrue("Able to click on Add a Photo button successfully", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Able to click on Add a Photo button successfully" + e);
			}
		} else {
			Assert.fail("Add a Photo button is not visible on olapic widget");
		}
	}
	/*
	 * @When("^I click on upload photo button to see the uploaders$") public
	 * static void iClickOnPhotoUploader() throws Throwable {
	 * 
	 * secondsUntilElementPresent("olapic.ola_upload_photos_iframe", 5);
	 * switchToFrame("olapic.ola_upload_photos_iframe");
	 * elementPresent("olapic.ola_upload_photos"); Assert.assertTrue(
	 * "Able to identify upload Photo on olapic iframe successfully", true);
	 * switchToFrame("default"); }
	 */

	@When("^I click on close button to close the photo uploader$")
	public static void I_click_on_close_button_to_close_the_photo_uploader() throws Throwable {

		if (Elements.elementPresent("olapic.ola_uploadphoto_close")) {
			try {
				Clicks.click("olapic.ola_uploadphoto_close");
				Assert.assertTrue("Olapic photo uploader screen has been closed successfully", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Able to click on Add a Photo button successfully" + e);
			}
		} else {
			Assert.fail("Not able to find the photouploader screen close element");
		}
	}

	@Then("^I click on view gallery to view the macyslove$")
	public static void I_click_on_view_gallery_to_view_the_macyslove() throws Throwable {

		Wait.secondsUntilElementPresent("olapic.view_gallery_button", 5);
		if (Elements.elementPresent("olapic.view_gallery_button")) {
			try {
				Clicks.click("olapic.view_gallery_button");
				Assert.assertTrue("Able to click on Olapic View Gallery button successfully", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Unable to find Olapic View Gallery button" + e);
			}
			try {
				Wait.secondsUntilElementPresent("olapic.view_gallery_Wall", 20);
				scrollToLazyLoadElement("olapic.view_gallery_Wall");
				Assert.assertTrue("Viewing photo gallery successfully", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Unable to scrolldown macyslove gallery" + e);
			}
		}
	}

	@When("^I added an? \"(.*?)\" product to my bag(?: that is not(?: an?)? \"(.*?)\")?(?: and \"?(.*?)\"? ?checkout)?$")
	public void I_added_a_product_to_my_bag(String productTrue, String productFalse, String checkout) throws Throwable {
		/*
		 * System.out.println("Product true" + productTrue); System.out.println(
		 * "oroduct false" + productFalse);
		 */
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

	@And("^I click on next and previou arrows on olapic to scroll the images$")
	public static void iClickOnNextAndPreviouArrowsOnOlapicToScrollTheImages() throws Throwable {
		Elements.elementPresent("olapic.olapic_pdp_shippingnreturn");
		Clicks.click("olapic.olapic_pdp_shippingnreturn");
		if (Elements.elementPresent("olapic.olapic_pdp_fullstate_rightarr")) {
			try {
				for (int i = 0; i < 2; i++) {
					Clicks.click("olapic.olapic_pdp_fullstate_rightarr");
					Thread.sleep(2000);
				}
				Assert.assertTrue("Able to click on full sate olpaic next arrow/image", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Doesn't find olapic which has arrows:" + e);
			}
		} else {
			Assert.fail("Doesn't find the full state olpaic next arrow/image");
		}
		if (Elements.elementPresent("olapic.olapic_pdp_fullstate_leftarr")) {
			try {
				for (int j = 0; j < 2; j++) {
					Clicks.click("olapic.olapic_pdp_fullstate_leftarr");
					Thread.sleep(2000);
				}
				Assert.assertTrue("Able to click on full sate olpaic previous arrow/image", true);
			} catch (NoSuchElementException e) {
				Assert.fail("Doesn't find olapic which has arrows:" + e);
			}
		} else {
			Assert.fail("Doesn't find the full state olpaic previous arrow/image");
		}
	}

	@When("^I click on one of the olapic image$")
	public static void iClickOnOneOfTheOlapicImage() throws Throwable {
		Wait.untilElementPresent("olapic.olapic_image_click");
		Assert.assertTrue("Error-Env: Unable to identify the star rating element on my review page",
				Elements.elementPresent("olapic.olapic_image_click"));
		Clicks.clickRandomElement("olapic.olapic_image_click",WebElement::isDisplayed);
		
	}
	
	@Then("^I click on product list to see the page redirection to radical PDP$")
	public static void iClickOnProductListToSeeThePageRedirectionToRadicalPdp() throws Throwable {
		Wait.untilElementPresent("olapic.olapic_productlist");
		Assert.assertTrue("Error-Env: Unable to identify the star rating element on my review page",
				Elements.elementPresent("olapic.olapic_productlist"));
		Clicks.clickRandomElement("olapic.olapic_productlist");
		
		Wait.untilElementPresent("olapic.olapic_pdp_addtobag");
		if(Elements.elementPresent("olapic.olapic_pdp_addtobag")){
			Assert.assertTrue("Able to see addtobag on PDP", true);
		}else if(Elements.elementPresent("olapic.olapic_pdp_addtobag_noprod")){
			Assert.assertTrue("This product is currently unavailable", true);
		}else{
			Assert.fail("Doesn't display PDP page");
		}
	}
}
