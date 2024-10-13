import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

    public class DemoWebShopTest {

        WebDriver driver;



            @BeforeMethod
            public void setUp() {
                WebDriver  driver = new ChromeDriver();
                driver.get("https://demowebshop.tricentis.com/");
            }
            @Test

            public void testFindElementsTagName () {


                // 1. Поиск логотипа

                WebElement logo = driver.findElement(By.xpath("//div[@class='header-logo']/a/img"));

                // 2. Поиск поля для поиска товаров
                WebElement searchField = driver.findElement(By.xpath("//input[@id='small-searchterms']"));

                // 3. Поиск кнопки поиска
                WebElement searchButton = driver.findElement(By.xpath("//input[@value='Search']"));

                // 4. Поиск ссылки на регистрацию
                WebElement registerLink = driver.findElement(By.xpath("//a[@href='/register']"));

                // 5. Поиск ссылки на логин
                WebElement loginLink = driver.findElement(By.xpath("//a[@href='/login']"));

                // 6. Поиск главного меню (верхние категории)
                List<WebElement> mainMenuItems = driver.findElements(By.xpath("//ul[@class='top-menu']//a"));

                // 7. Поиск блока с новыми товарами
                WebElement newProductsBlock = driver.findElement(By.xpath("//div[@class='block block-category-navigation']"));

                // 8. Поиск кнопки "Add to cart" для первого товара на главной странице
                WebElement firstProductAddToCartButton = driver.findElement(By.xpath("//input[@value='add to cart'])[1]"));

                //9. Поиск ссылки на раздел Books
                WebElement booksLink = driver.findElement(By.xpath("//a[@hrefs='/books']"));

                //10. Поиск футера
                WebElement footer = driver.findElement(By.xpath("//div[@class='footer-upper']"));

        }
        @AfterMethod(enabled = true)
        public void tearDown() {
            driver.quit();
        }
    }

