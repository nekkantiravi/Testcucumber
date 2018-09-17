package com.macys.sdt.projects.ArchitecturalEnhancement.SSL.steps.website;

import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.MainRunner;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.shared.steps.website.PageNavigation;
import com.macys.sdt.shared.steps.website.Registry;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DomainVerifcation extends StepUtils {
	
    @And("^I verify the domain call is in http/https$")
    public void I_verify_the_assets_domain_call_is_in(List<String> domains) throws Throwable {
		Wait.forPageReady();
		Har har = MainRunner.browsermobServer.getHar();
    	if(har != null && har.getLog() != null) {
			List<HarEntry> harEntries = har.getLog().getEntries();
			if(CollectionUtils.isEmpty(harEntries) ){
				System.out.println("No network calls identified..");
				return;
			}

			String url = RunConfig.getEnvOrExParam("website");
			String protocol = RunConfig.getEnvOrExParam("protocol");
			if(StringUtils.isBlank(protocol)){
				System.out.println("Ex param value of protocol is null or empty");
				return;
			}
			String urlHost = StringUtils.substring(url, 10);
			if(CollectionUtils.isNotEmpty(domains)) {
				for (String domain : domains) {
					String hostName = String.join("", domain, urlHost);    //Constructing the host name using domain
					int domainURLCount = 0;
					for (HarEntry harEntry : harEntries) {
						String requestURL = harEntry.getRequest().getUrl();

						if (requestURL.contains(hostName)) {    //Verify the host name whether is available in network call urls or not
							domainURLCount++;
							int status = (harEntry.getResponse() != null ) ? harEntry.getResponse().getStatus() : 0 ;
							if (!(StringUtils.startsWith(requestURL, protocol) && (status == 200))) {
								Assert.fail(domain.toUpperCase() + " call fails with requestURL : " + requestURL + " Status : " + status);
							}
						}
					}
					System.out.println(domain.toUpperCase() + " url count: " + domainURLCount);
				}
			}else{
				System.out.println("Domain values are null/empty");
			}
		}else{
			System.out.println("Har file is null");
		}
    }


	@And("^I verify the bright tag call is in https$")
	public void I_verify_the_bright_tag_call_is_in(List<String> brightTagURLs) throws Throwable {
		Wait.forPageReady();
		Har har = MainRunner.browsermobServer.getHar();
		if(har != null && har.getLog() != null) {
			List<HarEntry> harEntries = har.getLog().getEntries();
			System.out.println("Size :"+harEntries.size());
			if(CollectionUtils.isEmpty(harEntries) ){
				System.out.println("No network calls identified..");
				return;
			}

			if(CollectionUtils.isNotEmpty(brightTagURLs)) {
				for (String brightTagURL : brightTagURLs) {
					boolean isBrightTagURLAvalilable = false;
					boolean isBrightTagURLStatus = false;
					for (HarEntry harEntry : harEntries) {
						if (StringUtils.equalsIgnoreCase( harEntry.getRequest().getUrl(), brightTagURL) ) {
							isBrightTagURLAvalilable = true;
							if(harEntry.getResponse().getStatus() == 200){
								isBrightTagURLStatus = true;
								break;
							}
						}
					}
					if(!isBrightTagURLAvalilable){
						Assert.fail("Bright Tag call fails with brightTagURL : " + brightTagURL);
					}else if(!isBrightTagURLStatus){
						Assert.fail("Bright Tag call pass with brightTagURL : " + brightTagURL + ", but fails with Status : " + isBrightTagURLStatus);
					}
				}
			}else{
				System.out.println("No bright Tag urls are passed from feature file.");
			}
		}else{
			System.out.println("Har file is null");
		}

	}

	@Then("^I should be redirected to PDP page in \"(domestic|iship|registry)\" mode$")
	public void I_should_be_redirected_to_PDP_page_in_mode(String mode_name) throws Throwable {
		if(mode_name.equalsIgnoreCase("registry")) {
			new Registry().I_should_be_redirected_to_registry_PDP_page();
		}else{
			new PageNavigation().I_should_be_redirected_to_PDP_page();
		}
	}


	@Then("^I navigate to dynamic landing page")
	public void I_navigate_to_dynamic_landing_page() throws Throwable{
		List<WebElement> seo_links = Elements.findElements("designer_static.brands_name");
		if(CollectionUtils.isEmpty(seo_links) ){
			Assert.fail("Not navigated to dynamic landing page");
		}
		for (WebElement link : seo_links) {
			if (link != null && (link.getAttribute("href").contains((macys() ? "/featured/" : "/buy/")))) {
				Clicks.click(link);
				Wait.forPageReady();
				return;
			}
		}
		Assert.fail("Not navigated to dynamic landing page");
	}

}
