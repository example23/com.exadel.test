package com.exadel.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OnlinerTest extends TestBase{


    @Test
    public void ExadelHT (){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://catalog.onliner.by");
        driver.findElement(By.xpath("//div[@id='container']/div/div[2]/div/div/div/ul/li[2]/span[2]")).click();
        driver.findElement(By.xpath("//div[@id='container']/div/div[2]/div/div/div/div[2]/div/div/div/div/div/div")).click();
        driver.findElement(By.xpath("//div[@id='container']/div/div[2]/div/div/div/div[2]/div/div/div/div/div/div[2]/div/a/span/span[2]")).click();
        WebElement image = driver.findElement(By.xpath("//*[@id=\"schema-products\"]/div[1]/div/div[2]/div[3]/div/div/div[3]/div[1]/div[2]/a/img"));
        String src = image.getAttribute("data-bind");
        logger.info(src);
      //Средствами вэб-драйвера перейти в Каталог->Электроника->Мобильные телефоны
      //Найти вэб-елемент 3 го изображения телефона

        WebElement price = driver.findElement(By.xpath("//*[@id=\"schema-products\"]/div[1]/div/div[2]/div[1]/div/div[1]/div[1]/a/span"));
        String src1 = price.getText();
        logger.info(src1);
      //Средствами вэб-драйвера забрать с UI цену 1-го телефона

        driver.get("https://catalog.onliner.by/mobile");
        Select select = new Select(driver.findElement(By.xpath("//*[@id=\"schema-filter\"]/div[1]/div[8]/div[3]/div/div[1]/select")));
        select.selectByVisibleText("1\"");
        new Select(driver.findElement(By.xpath("//*[@id=\"schema-filter\"]/div[1]/div[8]/div[3]/div/div[2]/select"))).selectByVisibleText("1.77\"");
      //Выбрать все телефоныс диагонально 1-1.77



    }


}




