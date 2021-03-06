package com.ucsd.jira.automation.tests.web;

import com.pwc.core.framework.annotations.Issue;
import com.pwc.core.framework.listeners.Retry;
import com.ucsd.jira.automation.data.Constants;
import com.ucsd.jira.automation.data.enums.LeftMenu;
import com.ucsd.jira.automation.frameworksupport.Groups;
import com.ucsd.jira.automation.frameworksupport.JiraTestCase;
import org.testng.annotations.Test;

import static com.pwc.logging.service.LoggerService.FEATURE;
import static com.pwc.logging.service.LoggerService.GIVEN;
import static com.pwc.logging.service.LoggerService.SCENARIO;
import static com.pwc.logging.service.LoggerService.THEN;
import static com.pwc.logging.service.LoggerService.WHEN;


public class BasicTest extends JiraTestCase {

    @Override
    public void beforeMethod() {
    }

    @Override
    public void afterMethod() {
    }

    @Issue("STORY-1234")
    @Test(retryAnalyzer = Retry.class, groups = {Groups.ACCEPTANCE_TEST})
    public void testBasic() {

        FEATURE("Basic Jira Test");
        SCENARIO("User logs in and validates basic navigation functionality");

        GIVEN("I am a valid user");
        webElementVisible(Constants.TEST_HEADING);

        WHEN("I navigate with the left menu");
        webAction(LeftMenu.ISSUES);
        webElementVisible(combine(Constants.VARIABLE_BY_TEXT_SPAN, "Search issues"));
        redirect(Constants.HOME_URL);
// VS.
//        webAction(Constants.CLOSE_MENU_EXPAND_SPAN);

        THEN("The expected pages are displayed");
        webAction(LeftMenu.DASHBOARD);
        webElementVisible(combine(Constants.VARIABLE_BY_TEXT_SPAN, "System dashboard"));
        redirect(Constants.HOME_URL);
//        VS.
//        webAction(Constants.CLOSE_MENU_EXPAND_SPAN);

        webAction(LeftMenu.PROJECTS);
        webElementVisible(combine(Constants.VARIABLE_BY_TEXT_HEADING, "Projects"));
        webElementTextNotEquals(Constants.FIRST_PROJECTS_ANCHOR, "");

    }

}
