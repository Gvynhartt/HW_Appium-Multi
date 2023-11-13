package ru.netology.appium.multi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumTests {

    private AndroidDriver driver;
    enum Platform {Android} // потому что МЫ не буржуи
    Platform platform = Platform.Android;
    MainActivityPage mainActivityPage;
    AnotherActivityPage anotherActivityPage;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        URL remoteUrl = new URL("http://127.0.0.1:4723");
        // выпиливаем сгенерированный код для URL и пишем что попроще

        if (platform == Platform.Android) {
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("appium:deviceName", "pixel_2_api_33");
            desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
            desiredCapabilities.setCapability("appium:appActivity", ".MainActivity");
            desiredCapabilities.setCapability("appium:automationName", "UIAutomator2");
            desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
            desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
//            desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
//            desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);
            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        }
        mainActivityPage = new MainActivityPage(driver);
        anotherActivityPage = new AnotherActivityPage(driver);
    }

    @Test
    public void inputEmptyLineTest() {

        String emptyLine = "     "; // пять пробелов, которые далее будут введены в поле
        mainActivityPage.text2changeBtn.isDisplayed();
        String defaultText = mainActivityPage.text2changeBtn.getText(); // сохраняем отображаемый до всех манипуляций текст

        mainActivityPage.inputField.isDisplayed();
        mainActivityPage.inputField.click();
        mainActivityPage.inputField.sendKeys(emptyLine); // вводим строку из пробелов

        mainActivityPage.changeDisplTextBtn.isDisplayed();
        mainActivityPage.changeDisplTextBtn.click();

        String resultText = mainActivityPage.text2changeBtn.getText(); // сохраняем отображаемый тест для сличения
        Assertions.assertEquals(defaultText, resultText);
    }

    @Test
    public void openNonEmptyLineInAnotherActivity() {

        String sampleText = "Deep, dark fantasies..."; // сохраняем нашу тестовую строку
        mainActivityPage.text2changeBtn.isDisplayed();

        mainActivityPage.inputField.isDisplayed();
        mainActivityPage.inputField.click();
        mainActivityPage.inputField.sendKeys(sampleText); // вводим тестовую строку

        mainActivityPage.openInOtherActivityBtn.isDisplayed();
        mainActivityPage.openInOtherActivityBtn.click(); // переходим на второй экран

        anotherActivityPage.displTextFromMainAct.isDisplayed();
        anotherActivityPage.displTextFromMainAct.getText();

        String resultText = anotherActivityPage.displTextFromMainAct.getText(); // сохраняем тот текст, что там отображается, для сличения
        Assertions.assertEquals(sampleText, resultText);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
