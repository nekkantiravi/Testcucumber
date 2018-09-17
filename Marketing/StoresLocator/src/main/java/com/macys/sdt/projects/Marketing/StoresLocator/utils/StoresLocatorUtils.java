package com.macys.sdt.projects.Marketing.StoresLocator.utils;

import com.macys.sdt.framework.utils.*;
import java.util.*;
import com.macys.sdt.framework.runner.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class StoresLocatorUtils {
  private static EnvironmentDetails.AppDetails appDetails = EnvironmentDetails.otherApp("MSPOrder");

  public static String generateUrlFromPath(String path) {
    return "https://m." + appDetails.envName + ".fds.com/" + path;
  }

  public static Map<String, String> generateAuthHeaders() {
    Map<String, String > header = new HashMap<>();
    header.put("X-Macys-ClientId", "1913161491");
    return header;
  }
}
