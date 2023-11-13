package ru.netology.appium.multi;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class AnotherActivityPage {

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/text")
    public WebElement displTextFromMainAct;

    private AppiumDriver driver;

    public AnotherActivityPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }
}
