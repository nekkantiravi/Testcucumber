package com.macys.sdt.projects.Selection.PROS.pagefactory.loader.decorator;

import com.google.common.base.CaseFormat;
import com.macys.sdt.framework.interactions.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static ru.yandex.qatools.htmlelements.utils.HtmlElementUtils.*;

/**
 * credit: atepliashin
 */
public class JsonHtmlElementFieldAnnotationsHandler extends Annotations {

    private static final PrintStream stdErrSuppressor;
    private static final Logger logger = Logger.getLogger(Thread.currentThread().getClass().getName());

    static {
        stdErrSuppressor = new PrintStream(new OutputStream() {
            public void write(int b) {
                // redirect stderr to /dev/null
            }
        });
    }

    public JsonHtmlElementFieldAnnotationsHandler(Field field) {
        super(field);
    }

    @Override
    public By buildBy() {

        if (isHtmlElement(getField()) || isTypifiedElement(getField())) {
            return buildByFromHtmlElementAnnotations();
        }
        if (isHtmlElementList(getField()) || isTypifiedElementList(getField())) {
            return buildByFromHtmlElementListAnnotations();
        }

        return buildWebElementBy();
    }

    private By buildByFromFindAnnotations() {
        if (getField().isAnnotationPresent(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBys.class)) {
            com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBys findBys = getField().getAnnotation(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBys.class);
            return new ByChained(findBysToBys(findBys.value()));
        }
        if (getField().isAnnotationPresent(FindBys.class)) {
            FindBys findBys = getField().getAnnotation(FindBys.class);
            return buildByFromFindBys(findBys);
        }
        if (getField().isAnnotationPresent(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindAll.class)) {
            com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindAll findAll = getField().getAnnotation(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindAll.class);
            return new ByAll(findBysToBys(findAll.value()));
        }
        if (getField().isAnnotationPresent(FindAll.class)) {
            FindAll findBys = getField().getAnnotation(FindAll.class);
            return buildBysFromFindByOneOf(findBys);
        }
        if (getField().isAnnotationPresent(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy.class)) {
            com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy findBy = getField().getAnnotation(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy.class);
            return jsonPathToBy(findBy.jsonPath());
        }
        if (getField().isAnnotationPresent(FindBy.class)) {
            FindBy findBy = getField().getAnnotation(FindBy.class);
            return buildByFromFindBy(findBy);
        }

        // this block allows to map fields to selectors from json implicitly
        // it makes sense only for web elements. for html elements it's needed to exclude the case when it has its own
        // selector to find it.

        // example:
        // @FindBy(jsonPath = "some_page")
        // public class SomePage extends Page { WebElement someField; }
        //
        // will allow to extract selector by path "some_page.some_field"

        Class<?> declaringClass = getField().getDeclaringClass();
        if (declaringClass.isAnnotationPresent(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy.class)
                && (isWebElement(getField()) || isWebElementList(getField()))) {
            String jsonPath = declaringClass.getAnnotation(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy.class).jsonPath();
            if ((!isHtmlElement(getField()) && !isHtmlElementList(getField())) || !jsonPath.matches(".+\\..+")) {
                return jsonPathToBy(String.format("%s.%s", jsonPath, CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, getField().getName())));
            }
        }

        return null;
    }

    private By[] findBysToBys(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy[] findByArray) {
        List<By> bys = new ArrayList<>();
        for (com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy findBy : findByArray) {
            String jsonPath = findBy.jsonPath();
            By by = jsonPathToBy(jsonPath);
            if (by == null) {
                logger.warning(String.format("Cannot determine how to locate element %s by json path %s", getField(), jsonPath));
                continue;
            }
            bys.add(by);
        }
        if (bys.isEmpty()) {
            throw new IllegalArgumentException("Cannot determine how to locate element " + getField());
        }
        return bys.toArray(new By[bys.size()]);
    }

    private By buildByFromHtmlElementAnnotations() {
        assertValidAnnotations();

        By result = buildByFromFindAnnotations();
        if (result != null) {
            return result;
        }

        Class<?> fieldClass = getField().getType();
        while (fieldClass != Object.class) {
            if (fieldClass.isAnnotationPresent(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy.class)) {
                return jsonPathToBy(fieldClass.getAnnotation(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy.class).jsonPath());
            }
            if (fieldClass.isAnnotationPresent(FindBy.class)) {
                return buildByFromFindBy(fieldClass.getAnnotation(FindBy.class));
            }
            fieldClass = fieldClass.getSuperclass();
        }

        return buildByFromDefault();
    }

    private By buildByFromHtmlElementListAnnotations() {
        assertValidAnnotations();

        By result = buildByFromFindAnnotations();
        if (result != null) {
            return result;
        }

        Class<?> listParameterClass = getGenericParameterClass(getField());
        while (listParameterClass != Object.class) {
            if (listParameterClass.isAnnotationPresent(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy.class)) {
                return jsonPathToBy(listParameterClass.getAnnotation(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy.class).jsonPath());
            }
            if (listParameterClass.isAnnotationPresent(FindBy.class)) {
                return buildByFromFindBy(listParameterClass.getAnnotation(FindBy.class));
            }
            listParameterClass = listParameterClass.getSuperclass();
        }

        return buildByFromDefault();
    }

    private By buildWebElementBy() {
        assertValidAnnotations();

        By by = buildByFromFindAnnotations();

        if (by == null) {
            by = buildByFromDefault();
        }

        if (by == null) {
            throw new IllegalArgumentException("Cannot determine how to locate element " + getField());
        } else {
            return by;
        }
    }

    protected By buildByFromDefault() {
        logger.warning(String.format("The locator for %s will be created using defaults", getField()));
        return super.buildByFromDefault();
    }

    private By jsonPathToBy(String jsonPath) {
        PrintStream originalErrorOutput = System.err;
        System.setErr(stdErrSuppressor);
        By by = Elements.element(jsonPath);
        System.setErr(originalErrorOutput);
        return by;
    }

    @Override
    protected void assertValidAnnotations() {
        FindBys findBys = getField().getAnnotation(FindBys.class);
        com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBys jsonFindBys = getField().getAnnotation(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBys.class);
        FindAll findAll = getField().getAnnotation(FindAll.class);
        com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindAll jsonFindAll = getField().getAnnotation(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindAll.class);
        FindBy findBy = getField().getAnnotation(FindBy.class);
        com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy jsonFindBy = getField().getAnnotation(com.macys.sdt.projects.Selection.PROS.pagefactory.annotation.FindBy.class);
        if ((findBys != null && findBy != null) || (jsonFindBys != null && jsonFindBy != null)) {
            throw new IllegalArgumentException("If you use a \'@FindBys\' annotation, you must not also use a \'@FindBy\' annotation");
        } else if (findAll != null && findBy != null || (jsonFindAll != null && jsonFindBy != null)) {
            throw new IllegalArgumentException("If you use a \'@FindAll\' annotation, you must not also use a \'@FindBy\' annotation");
        } else if ((findAll != null && findBys != null) || (jsonFindAll != null && jsonFindBys != null)) {
            throw new IllegalArgumentException("If you use a \'@FindAll\' annotation, you must not also use a \'@FindBys\' annotation");
        }
    }
}
