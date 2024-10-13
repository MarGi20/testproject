import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class FindElementsTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://ilcarro.web.app");
        driver.manage().window().setPosition(new Point(2500, 0)); // Подвинуть окно браузера на 2500 пикселей вправо, чтобы он запускался на втором мониторе
        driver.manage().window().maximize(); // На весь экран развернуть браузер
        // Неявное ожидание локатора. Если элемент появится до истечения времени, Selenium сразу продолжит выполнение, не дожидаясь окончания таймера.
        // Устанавливает глобальное ожидание на все элементы, которые вы пытаетесь найти с помощью методов findElement или findElements
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //************************************************* Ищем локатор элемента по тэгу
    @Test
    public void testFindElementsTagName() {
        //? Локатор с тегом <h1>
        //* driver.findElement(By.tagName("h1")); // Создадим локальную переменную
        WebElement h1 = driver.findElement(By.tagName("h1"));
        //! System.out.println(h1); // получаем ерунду
        System.out.println(h1.getText());

        //? Локатор с тегом <а> это картинка в этом случае логотип сайта
        WebElement a = driver.findElement(By.tagName("a"));
        //! System.out.println(a.getText()); // Не получится распечатать текст картинки
        System.out.println(a.getSize()); // Узнаём разрешение картинки в состоянии рендера

        //? Массив элементов с тегом <а>
        List<WebElement> elements_a = driver.findElements(By.tagName("a"));
        //! System.out.println(elements_a); //  Распечатает нам массив
        System.out.println(elements_a.size()); // Узнаём размер массива 31 элемента на странице с тегом <а>
        System.out.println(elements_a.get(2).getText()); // Узнаём текст элемента в массиве под индексом 2

        for (WebElement element : elements_a) { // for-each, для перебора всех элементов в списке elements_a
            System.out.println(element.getAttribute("href"));  // Для вывода атрибута href
            System.out.println(element.getText());  // Для вывода текста элемента
        }
    }

    /**
     * Стратегия поиска по id - это самая успешная стратегия.
     */
    //************************************************* Ищем локатор элемента по id
    @Test
    public void testFindElementsByLocator() {
        //? By.id
        // #city
        driver.findElement(By.id("city"));
        driver.findElement(By.cssSelector("#city"));
        driver.findElement(By.cssSelector("input#city"));

        //? Bt.cssSelector
        // ng-reflect-name="city"
        driver.findElement(By.cssSelector("[ng-reflect-name='city']"));

        //? By.className
        // .telephone
        WebElement telephone1 = driver.findElement(By.className("telephone"));
        WebElement telephone2 = driver.findElement(By.cssSelector(".telephone"));
        System.out.println(telephone1.getText() + telephone2.getText()); // Распечатаем телефон

        //? By.linkText
        // a[ng-reflect-router-link='let-car-work']
        //! далее по xPath, потому что стандартные CSS-селекторы не поддерживают поиск по тексту внутри элемента.
        // //a[text()=' Let the car work ']
        // //a[.=' Let the car work ']
        // //a[contains(text(), ' Let the car work ']
        // (//a[contains(text(), 'work')])[2]
        WebElement letTheCarWork = driver.findElement(By.linkText("Let the car work"));
        System.out.println(letTheCarWork.getText()); // Let the car work

        WebElement search = driver.findElement(By.linkText("Search"));
        System.out.println(search.getText()); // Search

        //? By.partialLinkText
        //* для поиска ссылки (<a>) по части её текстового содержания
        //! для поиска элемента по содержимому только XPath, потому что стандартные CSS-селекторы не поддерживают поиск по тексту внутри элемента.
        WebElement partialLinkText = driver.findElement(By.partialLinkText("work"));
        System.out.println(partialLinkText.getText());
    }

    @Test
    public void testFindElementsByCssSelector() {
        //? [attr="value"]
        // input[id='city']
        // input[id^='ci']
        // input[id$='ty']
        driver.findElement(By.cssSelector("input[id='city']"));// Полное соответствие
        driver.findElement(By.cssSelector("input[id^='ci']")); // Начинается на
        driver.findElement(By.cssSelector("input[id$='ty']")); // Заканчивается на
        driver.findElement(By.cssSelector("#city"));
        WebElement city = driver.findElement(By.cssSelector("[ng-reflect-name='city']"));
        System.out.println(city.getAttribute("type"));
        System.out.println(city.getAttribute("id"));
        System.out.println(city.getAttribute("formcontrolname"));
        System.out.println(city.getAttribute("ng-reflect-label"));
        System.out.println(city.getAttribute("class"));
        System.out.println(city.getAttribute("autocomplete"));
    }

    @Test
    public void findElementByXPath(){
        //h1
       WebElement h1 = driver.findElement(By.xpath("//h1"));
       System.out.println(h1.getText());

       WebElement city= driver.findElement(By.xpath("//*[@id='city']"));
        System.out.println();

    }

    @Test
    public void siblingTests(){
        //a[text()=' Let the car work ']/preceding::*
        //a[text()=' Let the car work ']/preceding::link[2]
        //a[text()=' Let the car work ']/following::*
        //a[text()=' Let the car work ']/preceding-sibling::*[1]
        driver.findElement(By.xpath("//a[text()=' Let the car work ']/preceding::*"));
        driver.findElement(By.xpath("//a[text()=' Let the car work ']/preceding::link[2]"));
        driver.findElement(By.xpath("//a[text()=' Let the car work ']/following::*"));
        driver.findElement(By.xpath("//a[text()=' Let the car work ']/preceding-sibling::*[1]"));
    }

    @Test
    public void siblingTests2() {
        driver.get("https://ticket-service-69443.firebaseapp.com/");
        driver.findElement(By.xpath("//*[@class='mt-3' and text()='Berlin City Hall | Events and Tickets']"));
        driver.findElement(By.xpath("//span[@class='mt-3' and text()='Berlin City Hall | Events and Tickets']"));
        driver.findElement(By.xpath("//span[contains(@class,'mt-') and contains(text(),'Berlin City Hall | Events and Tickets')]"));
    }

    @AfterMethod(enabled = true)
    public void tearDown() {
        driver.quit();
    }
}


