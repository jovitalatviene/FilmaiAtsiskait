package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Filmai {
    public static WebDriver browser;
    public static final int SECONDS_WAIT_TIME_FOR_ELEMENT = 2;
    public static void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        browser = new ChromeDriver(chromeOptions);
        browser.get("https://programavimoabc.lt/filmai.php");
    }
    public static void createMovie() {
        WebElement pavadinimas = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[2]"));
        pavadinimas.sendKeys("JovitaLatv");
        WebElement zanras = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[3]"));
        zanras.sendKeys("drama");
        WebElement aktoriai = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[4]"));
        aktoriai.sendKeys("geri");
        WebElement rezisierius = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[5]"));
        rezisierius.sendKeys("talentingas");
        WebElement trukme = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[6]"));
        trukme.sendKeys("2211");
        WebElement siusti = browser.findElement(By.xpath("//*[@id=\"form\"]/form/p[1]/button[1]"));
        siusti.click();
    }
    public static boolean isModalTitleContains(WebElement modalTitle, String keyword) {
        String title = modalTitle.getText();
        if (modalTitle.isEnabled() && modalTitle.isDisplayed() || title.contains(keyword)){
            System.out.println("Pavyko sėkmingai.");
            return true;
        }
        else {
            System.out.println("Klaida, nepavyko.");
            return false;
        }
    }
    public static void waitElement(By locator) {
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(SECONDS_WAIT_TIME_FOR_ELEMENT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void deleteMovie(){
 //       WebElement ieskoti = browser.findElement(By.xpath("//*[@id=\"form\"]/form/p[1]/button[3]"));
 //       ieskoti.click();
        WebElement movieId = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[1]"));
        movieId.sendKeys("168");
        WebElement delete = browser.findElement(By.xpath("//*[@id=\"form\"]/form/p[1]/button[4]"));
        delete.click();
        System.out.println("Filmas ištrintas sėkmingai");
    }
    public static void editMovie(){
        WebElement movieId = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[1]"));
        movieId.sendKeys("171");
        WebElement pavadinimas = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[2]"));
        pavadinimas.sendKeys("JovitosLatves");
        WebElement zanras = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[3]"));
        zanras.sendKeys("komedija");
        WebElement aktoriai = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[4]"));
        aktoriai.sendKeys("superduper");
        WebElement rezisierius = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[5]"));
        rezisierius.sendKeys("Gibson");
        WebElement trukme = browser.findElement(By.xpath("//*[@id=\"form\"]/form/input[6]"));
        trukme.sendKeys("150");
        WebElement edit = browser.findElement(By.xpath("//*[@id=\"form\"]/form/p[1]/button[2]"));
        edit.click();
    }
    public static void close(){browser.close();}
    public static void main(String[] args) {
        System.out.println("Filmų registracijos formos testavimas");

        setup();
        createMovie();
        isModalTitleContains(browser.findElement(By.xpath("/html/body/div[2]")), "Duomenys įrašyti sėkmingai");
        waitElement(By.xpath("//*[@id=\"form\"]/form/input[1]"));
        deleteMovie();
        editMovie();
        isModalTitleContains(browser.findElement(By.xpath("/html/body/div[2]")), "Įrašas paredaguotas sėkmingai");
        close();
    }
}