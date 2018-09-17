package com.macys.sdt.projects.PurchaseAndDelivery.BopsCxImprovements.steps.website;

import com.google.common.base.CaseFormat;
import com.google.common.reflect.ClassPath;
import com.macys.sdt.framework.interactions.Clicks;
import com.macys.sdt.framework.interactions.Elements;
import com.macys.sdt.framework.interactions.Wait;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static com.macys.sdt.framework.utils.StepUtils.macys;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

/**
 * Created by atepliashin on 9/23/16.
 */
public class GenericSteps {

    /**
     * Allows dynamically to call page object static methods if page object class is present and method is declared.
     * otherwise will generate selector and use it for default action - click();
     *
     * @param action page object static method or selector described for page
     * @param page   page object class that's used to find the action. If class is not found, used as a selector part
     */
    @When("^I am going to (.*) (?:on|in) (.*) (?:page|window|dialog|overlay)$")
    public void doAction(String action, String page) {
        String pageClass = stepMatchToCase(CaseFormat.UPPER_CAMEL, page);
        String methodToCall = stepMatchToCase(CaseFormat.LOWER_CAMEL, action);
        String packagePrefix = String.format("com.macys.sdt.shared.actions.website.%scom.pages", macys() ? "m" : "b");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Set<ClassPath.ClassInfo> classes;
        try {
            classes = ClassPath.from(loader).getTopLevelClassesRecursive(packagePrefix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Optional<ClassPath.ClassInfo> classInfoOptional = classes.stream().
                filter(classInfo -> classInfo.getSimpleName().equals(pageClass)).
                findFirst();
        if (!classInfoOptional.isPresent()) {
            System.out.println(String.format(
                    "No page class %s present in the package %s, default action will be performed",
                    pageClass, packagePrefix));
            defaultAction(action, page);
        } else {
            ClassPath.ClassInfo classInfo = classInfoOptional.get();
            try {
                classInfo.load().getMethod(methodToCall).invoke(null);
            } catch (NoSuchMethodException e) {
                System.out.println(String.format(
                        "Method %s not declared in %s, default action will be performed instead",
                        methodToCall, classInfo.getName()));
                defaultAction(action, page);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Then("^I should(n't)? see \"(.*)\" is present (?:in|on) (.*) (?:page|window|dialog|overlay)$")
    public void iSeeElement(String negate, String element, String page) throws Throwable {
        String selector = combineSelector(element, page);
        if (negate != null) {
            assertFalse(Elements.elementPresent(selector));
        } else {
            Elements.elementShouldBePresent(selector);
        }
    }

    @Then("I verify \"(.*)\" contains (?:message|text) \"(.*)\" (?:in|on) (.*) (?:page|window|dialog|overlay)$")
    public void iSeeExpectedText(String element, String expectedText, String page) {
        String selector = combineSelector(element, page);
        Wait.untilElementPresent(selector);
        assertThat(Elements.getText(selector), containsString(expectedText));
    }

    private void defaultAction(String element, String page) {
        By locator = Elements.element(combineSelector(element, page));
        Wait.untilElementPresent(locator);
        Clicks.click(locator);
    }

    private String combineSelector(String element, String page) {
        return stepMatchToCase(CaseFormat.LOWER_UNDERSCORE, page) + "." +
                stepMatchToCase(CaseFormat.LOWER_UNDERSCORE, element);
    }

    private String stepMatchToCase(CaseFormat format, String string) {
        return CaseFormat.LOWER_HYPHEN.to(format, string.toLowerCase().replaceAll("\\s", "-"));
    }
}
