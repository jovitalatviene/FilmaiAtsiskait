import org.example.Filmai;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.example.Filmai.browser;

public class FilmaiTest {
    @BeforeMethod
    public static void setup() {
        Filmai.setup();
    }
    @Test (priority = 1)
    public void createMoviePositive(){
        Filmai.createMovie();
        boolean resultActual = Filmai.isModalTitleContains(browser.findElement(By.xpath("/html/body/div[2]")), "Duomenys įrašyti sėkmingai");
        Assert.assertTrue(resultActual);
    }
    @Test (priority = 2)
    public void createMovieNegative(){
        Filmai.createMovie();
        boolean resultActual =! Filmai.isModalTitleContains(browser.findElement(By.xpath("/html/body/div[2]")), "Duomenys įrašyti sėkmingai");
        Assert.assertFalse(resultActual);
    }
    @Test (priority = 3)
    public void deleteMovie() {
        Filmai.waitElement(By.xpath("//*[@id=\"form\"]/form/input[1]"));
        Filmai.deleteMovie();
    }
    @Test (priority = 4)
    public void editMoviePositive() {
        Filmai.editMovie();
        boolean resulActual = Filmai.isModalTitleContains(browser.findElement(By.xpath("/html/body/div[2]")), "Įrašas paredaguotas sėkmingai");
        Assert.assertTrue(resulActual);
    }
    @Test (priority = 5)
    public void editMovieNegative() {
        Filmai.editMovie();
        boolean resulActual =! Filmai.isModalTitleContains(browser.findElement(By.xpath("/html/body/div[2]")), "Įrašas paredaguotas sėkmingai");
        Assert.assertFalse(resulActual);
    }
    @AfterMethod
    public static void close() {Filmai.close();}
}
