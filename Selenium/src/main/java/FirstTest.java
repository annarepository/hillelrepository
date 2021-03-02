import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FirstTest {
    @Test
    public void checkUrl() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get("https://next.privat24.ua/money-transfer/card");

        By commentText = By.xpath(".//div[@data-qa-node='comment']");
        By receiverAmount = By.xpath(".//div[@data-qa-node='receiver-amount']");
        By payerCard = By.xpath(".//span[@data-qa-node='payer-card']");
        By receiverCard = By.xpath(".//span[@data-qa-node='receiver-card']");
        By cardNumberFrom = By.xpath(".//input[@data-qa-node='numberdebitSource']");

        driver.findElement(cardNumberFrom).sendKeys("4552331448138217");
        //в примере ниже мы не создаем переменную, а сразу работаем с элементом по его XPATH локатору
        driver.findElement(By.xpath(".//input[@data-qa-node='expiredebitSource']")).sendKeys("0524");
        driver.findElement(By.xpath(".//input[@data-qa-node='cvvdebitSource']")).sendKeys("111");
        driver.findElement(By.xpath(".//input[@data-qa-node='firstNamedebitSource']")).sendKeys("Shayne");
        driver.findElement(By.xpath(".//input[@data-qa-node='lastNamedebitSource']")).sendKeys("McConnell");
        driver.findElement(By.xpath(".//input[@data-qa-node='numberreceiver']")).sendKeys("4567739561253907");
        driver.findElement(By.xpath(".//input[@data-qa-node='firstNamereceiver']")).sendKeys("Ruth");
        driver.findElement(By.xpath(".//input[@data-qa-node='lastNamereceiver']")).sendKeys("Montalvo");
        driver.findElement(By.xpath(".//input[@data-qa-node='amount']")).clear();
        driver.findElement(By.xpath(".//input[@data-qa-node='amount']")).sendKeys("300");
        driver.findElement(By.xpath(".//span[@data-qa-node='toggle-comment']")).click();
        driver.findElement(By.xpath(".//textarea[@data-qa-node='comment']")).sendKeys("На текилу");
        driver.findElement(By.xpath(".//button[contains(text(), 'Переказати')]")).click();

        Assert.assertEquals("На текилу", driver.findElement(commentText).getText());
        Assert.assertEquals("300 UAH", driver.findElement(receiverAmount).getText());
        Assert.assertEquals("* 8217", driver.findElement(payerCard).getText());
        Assert.assertEquals("* 3907", driver.findElement(receiverCard).getText());
    }

    @Test
    public void  chekRefillMobile() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get("https://next.privat24.ua/mobile");

        driver.findElement(By.xpath(".//input[@data-qa-node='phone-number']")).sendKeys("732089820");
        driver.findElement(By.xpath(".//input[@data-qa-node='amount']")).sendKeys("250");
        driver.findElement(By.xpath(".//input[@data-qa-node='numberdebitSource']")).sendKeys("4552331448138217");
        driver.findElement(By.xpath(".//input[@data-qa-node='expiredebitSource']")).sendKeys("05/24");
        driver.findElement(By.xpath(".//input[@data-qa-node='cvvdebitSource']")).sendKeys("926");
        driver.findElement(By.xpath(".//button[@data-qa-node='submit']")).click();

        By details = By.xpath(".//span[@data-qa-node='details']");
        By card = By.xpath(".//td[@data-qa-node='card']");
        By nameB = By.xpath(".//span[@data-qa-node='nameB']");
        By amount = By.xpath(".//span[@data-qa-node='amount']");
        By currency = By.xpath(".//small[@data-qa-node='currency']");


        Assert.assertEquals("Поповнення телефону. На номер +380732089820", driver.findElement(details).getText());
        Assert.assertEquals("4552 **** **** 8217", driver.findElement(card).getText());
        Assert.assertEquals("Lifecell Ukraine", driver.findElement(nameB).getText());
        Assert.assertEquals("250", driver.findElement(amount).getText());
        Assert.assertEquals(" UAH", driver.findElement(currency).getText());
    }
}
