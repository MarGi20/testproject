import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstSeleniumProject { //    Это основной класс,
    // который использует Selenium WebDriver для взаимодействия с браузером (в данном случае — Chrome).

    WebDriver driver;

    // Переменная driver типа WebDriver
    // используется для управления браузером.
    // Именно через неё происходит взаимодействие с веб-страницами.

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver(); // Запускаем браузер Сhrome
        driver.get("https://www.google.com"); // открывает нужную нам страницу
        driver.navigate().to("https://www.google.com"); //показывает навигацию перемещения
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
      //  driver.manage().window().setPosition(new Point(2500, 0));
      //  driver.manage().window().maximize(); //разворачивает на весь экран

    }
    @Test
public void firstTest(){
        System.out.println("Google hi!!!");

    }

    @AfterMethod(enabled = false)
    //параметр enabled = false означает,
    // что этот метод отключён и выполняться не будет.
    // Если бы он был включён, то метод driver.quit() закрыл бы браузер по завершении теста.
    public void tearDown(){
        driver.quit(); // завершает работу драйвера
        driver.close(); // завершает работу одну вкладку

    }

}
