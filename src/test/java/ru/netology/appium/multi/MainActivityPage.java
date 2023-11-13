package ru.netology.appium.multi;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MainActivityPage {

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    public WebElement text2changeBtn;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    public WebElement inputField;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    public WebElement changeDisplTextBtn;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonActivity")
    public WebElement openInOtherActivityBtn;

    private AppiumDriver driver;

    public MainActivityPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }
}
