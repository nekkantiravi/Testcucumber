package com.macys.sdt.projects.Marketing.StoresLocator.steps.MEW.bcom;

import com.macys.sdt.framework.model.addresses.ProfileAddress;
import com.macys.sdt.framework.interactions.*;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.DropDowns;
import com.macys.sdt.framework.interactions.TextBoxes;
import com.macys.sdt.framework.interactions.Wait;
import com.macys.sdt.framework.runner.RunConfig;
import com.macys.sdt.framework.utils.StepUtils;
import com.macys.sdt.framework.utils.*;
import com.macys.sdt.framework.utils.EnvironmentDetails;
import com.macys.sdt.framework.runner.WebDriverManager;
import com.macys.sdt.framework.utils.rest.utils.RESTOperations;
import com.macys.sdt.projects.Marketing.StoresLocator.utils.*;
import com.macys.sdt.shared.steps.MEW.GlobalNavigation;
import com.macys.sdt.shared.steps.MEW.GlobalNavigation.*;
import com.macys.sdt.shared.steps.MEW.PageNavigation;
import com.macys.sdt.shared.steps.MEW.ShopAndBrowse;
import com.macys.sdt.shared.utils.CommonUtils;
import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONString;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.Element;
import javax.ws.rs.core.Response;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sun.util.calendar.CalendarDate;

import javax.ws.rs.core.Response;

import static com.macys.sdt.framework.runner.RunConfig.url;
import static com.macys.sdt.framework.runner.WebDriverManager.getWebDriver;
import static com.macys.sdt.projects.Marketing.StoresLocator.utils.StoresLocatorService.getStoreBldgGroup;
import static com.macys.sdt.projects.Marketing.StoresLocator.utils.StoresLocatorService.getStoreBuildings;
// import static com.macys.sdt.shared.steps.MEW.GlobalNavigation.navigateToTopLevelMenuItemByCategory;
import static org.apache.commons.lang3.StringUtils.rightPad;
import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class StoresLocatorB extends StepUtils {

  private PageNavigation pageNavigation = new PageNavigation();
  private static ShopAndBrowse shopAndBrowse = new ShopAndBrowse();
  private static final Logger logger = LoggerFactory.getLogger(StoresLocatorB.class);
  private WebDriver webdriver = WebDriverManager.getWebDriver();
  private ProfileAddress profileAddress;
  private String legacyUrl;
  private String currentSearchAddress;
  private String serviceFilter;
  private List<WebElement> storesList;
  private List<SearchResultsStoreDetails> storesDtls;
  private int currentStore;
  private JSONArray storesData;
  private String storeName;
  private String storeNumber;
  private String storeLocation;
  private Random randomizer = new Random();

  private int currentEvent;
  private List<StoreEvents> storeEvents;

  private List<String> weekDays = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
  Actions actions = new Actions(getWebDriver());
  
}
