package com.exadel.automation;

import com.exadel.automation.pages.InboxPage;
import com.exadel.automation.pages.LoginPage;
import com.exadel.automation.pages.Page;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.exadel.automation.utils.ElementsUtil.isDisplayed;
import static io.qameta.allure.SeverityLevel.*;

public class GmailLoginTest extends TestBase {

    @Feature("GMAIL")
    @Story("GMAIL LOGIN")
    @Severity(BLOCKER)
    @Description("Verify login with correct username and password")
    @Test(description = "Verify login with correct username and password, TC-1")
    @TmsLink("1")
    public void testSuccessfulLogin() {
        Page page = new Page(driver);
        LoginPage loginPage = page.navigateToLoginPage(baseUrl);
        InboxPage inboxPage = loginPage.enterEmail(propertiesLoader.getUserEmail())
                .clickNextButton()
                .enterPassword(propertiesLoader.getUserPassword())
                .submitLogin(driver);
        Assert.assertTrue(isDisplayed(inboxPage.emailSection, 10), "Missing email section");
    }

    @Feature("GMAIL")
    @Story("GMAIL LOGIN")
    @Severity(CRITICAL)
    @Description("Verify login with nonexistent username")
    @TmsLink("2")
    @Test(description = "Verify login with nonexistent username, TC-2")
    public void testNonexistentUsernameLogin() {
        Page page = new Page(driver);
        LoginPage loginPage = page.navigateToLoginPage(baseUrl);
        loginPage.enterEmail("sdgffwgfsgfd@gmail.com")
                .clickNextButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isDisplayed(loginPage.getNonexistentUsernameError(), 5));
        softAssert.assertEquals(loginPage.getNonexistentUsernameError().getText(), "Couldn't find your Google Account");
        softAssert.assertAll();
    }

    @Feature("GMAIL")
    @Story("GMAIL LOGIN")
    @Severity(MINOR)
    @Description("Verify login with incorrect password")
    @TmsLink("3")
    @Test(description = "Verify login with incorrect password, TC-3")
    public void testIncorrectPasswordLogin() {
        Page page = new Page(driver);
        LoginPage loginPage = page.navigateToLoginPage(baseUrl);
        loginPage.enterEmail(propertiesLoader.getUserEmail())
                .clickNextButton()
                .enterPassword("Password1")
                .submitLogin(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isDisplayed(loginPage.getIncorrectPasswordError(), 5));
        softAssert.assertEquals(loginPage.getIncorrectPasswordError().getText(), "Wrong password. Try again or click Forgot password to reset it.");
        softAssert.assertAll();
    }

    @Feature("GMAIL")
    @Story("GMAIL LOGIN")
    @Severity(TRIVIAL)
    @Description("Verify 'Forgot email' button")
//    @Link(name = "TC-4", value = "4", type = "mylink")
    @TmsLink("4")
    @Issue("AD-12")
    @Test(description = "Verify 'Forgot email' button, TC-4")
    public void testForgotEmail() {
        Page page = new Page(driver);
        LoginPage loginPage = page.navigateToLoginPage(baseUrl);
        loginPage.clickForgotEmail();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isDisplayed(loginPage.getHeader(), 5));
        softAssert.assertEquals(loginPage.getHeader().getText(), "Find your wrong email");
        softAssert.assertAll();
    }

    @Feature("NEW FEATURE")
    @Story("NEW STORY")
    @Severity(TRIVIAL)
    @Description("Verify new story")
    @TmsLink("5")
    @Test(description = "Verify new story, TC-5")
    public void testBroken() throws Exception {
        throw new Exception("This test is broken");
    }
}
