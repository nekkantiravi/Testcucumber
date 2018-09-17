package com.macys.sdt.projects.Discovery.Regression.utils.config.MEW;

import com.macys.sdt.framework.utils.StepUtils;
import org.openqa.selenium.JavascriptExecutor;
import com.macys.sdt.framework.interactions.Navigate;

/**
 * Created by yh03392 on 9/5/17.
 */
public class LocalStoreUtils extends StepUtils{
    public static JavascriptExecutor js;

  /*
   Returns product count from the local storage and it is displayed in the Free pick up and All Items tab
  */

    public static String getItemFromLocalStorage(String key)

    {
        return (String) Navigate.execJavascript(String.format("return window.localStorage.getItem('%s');", key));

    }


}

