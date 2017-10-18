package ge.shitbot.bot.drivers.bookies;

import ge.shitbot.bot.drivers.BookieDriver;
import ge.shitbot.bot.drivers.BookieDriverGeneral;
import ge.shitbot.bot.exceptions.UnknownOddTypeException;
import ge.shitbot.bot.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by giga on 9/13/17.
 */
public class CrocoBetDriver extends BookieDriverGeneral implements BookieDriver {

    String baseUrl = "https://www.crocobet.com";
    String user = "ggat";
    String password = "pathfinder48";

    public CrocoBetDriver(WebDriver webDriver) {
        super(webDriver);
    }

    protected void goToMainPage() {

        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
    }

    protected void goToAccountPage() {

        if(!isLoggedIn()) {
            login();
        }

        WebElement accountLink = presenceOfElementLocated(By.xpath("//*[@id=\"navbar\"]/div/a[contains(@href, '/userPanel/deposit')]"));

        accountLink.click();
    }

    protected void login() {

        goToMainPage();

        //Input user name
        webDriver.findElement(By.xpath("//*[@id=\"navbar\"]/form[contains(@name, 'loginForm')]/div/div/input[contains(@name, \"login\")]")).sendKeys(user);
        webDriver.findElement(By.xpath("//*[@id=\"navbar\"]/form[contains(@name, 'loginForm')]/div/div//input[contains(@name, 'password') and contains(@type, \"password\")]")).sendKeys(password);
        webDriver.findElement(By.xpath("//*[@id=\"navbar\"]/form[contains(@name, 'loginForm')]//button[@type='submit']")).click();

    }

    public boolean isLoggedIn() {

        goToMainPage();

        try{
            webDriver.findElement(By.xpath("//*[@id=\"navbar\"]/div/button[@type='button' and contains(@class, 'logout-btn')]")).isDisplayed();
        } catch (Throwable e) {
            return false;
        }

        return true;
    }

    public void createBet(String category, String subCategory, String teamOneName, String teamTwoName, String oddType, Double amount, Double oddConfirmation) throws UnknownOddTypeException {

        //FIXME: If team names are too short or empty it will match lot of odd rows, most probably first row will be selected
        //FIXME: Currently we choose odds using td index which may change in future.
        //TODO: Add event date confirmation
        //TODO: Add odd confirmation.*/

        Poin oddTypeIndex = getOddTypeIndex(oddType);

        if(!isLoggedIn()) {
            login();
        }

        //nav სპორტი
        //NOTE: string(.) is used to get entire child nodes as string.
        presenceOfElementLocated(By.xpath("/html/body/div/div[contains(@data-ng-include, 'navigation')]/div/nav/ul/li/a[@href='sports']")).click();

        //aside ფეხბურთი
        presenceOfElementLocated(By.xpath("/html/body/div/div[contains(@class, 'main-content')]//div[contains(@class, 'sport-categories-box')]/div/ul[contains(@class, 'sport-list')]/li[contains(concat(' ', @class, ' '), 'sport-1')]")).click();


        //TODO: Here we should go deeper and ten return up. cause there may be tow ესპანეთი.
        //aside ფეხბურთი
        /*WebElement webElement = */presenceOfElementLocated(By.xpath("/html/body/div/div[contains(@class, 'main-content')]//div[contains(@class, 'sport-categories-box')]/div/div/ul[havingClass('sport-list') and havingClass('subcategory')]//span[havingClass('categoryName') and contains(string(.), '"+category+"')]/ancestor::li[1]")).click();
        /*WebElement subCategoryMenu = */presenceOfElementLocated(By.xpath("//*[contains(@id, 'categoryId_') and not(contains(@style, 'display: none'))]/li/span[contains(string(.), '"+ subCategory +"')]/ancestor::li[1]")).click();

        windowScroll(0L, 1000L);

        //Specific event row
        presenceOfElementLocated(By.xpath("//*[@id=\"sport-content\"]//div[contains(@class, 'country-level')]//div[contains(@class, 'panel-body')]//div[contains(@class, 'league-level')]//span[contains(string(.), '"+ category +"') and contains(string(.), '"+ subCategory +"') ]/ancestor::div[contains(@class, 'league-level')]/following-sibling::div//div[contains(@class, 'panel-body')]/ul/li[contains(@class, 'single-event')]//li[contains(@class, 'period-item')]/div[contains(concat(' ', normalize-space(@class), ' '), 'event' ) and contains(concat(' ', normalize-space(@class), ' '), 'name' ) and contains(string(.), '" + teamOneName + "') and contains(string(.), '" + teamTwoName + "')]/following-sibling::div[havingClass('buttons-holder') and havingClass('main-holder')]/div["+ oddTypeIndex.x +"]/button["+ oddTypeIndex.y +"]")).click();


        WebElement stakeInput = presenceOfElementLocated(By.xpath("//*[@id=\"betslip-stake\"]"));

        stakeInput.clear();

        stakeInput.sendKeys(presentDouble(amount));

    }

    private static class Poin{
        int x;
        int y;

        public Poin(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Poin t(int x, int y) {
            return new Poin(x, y);
        }
    }

    //Non-zero based index
    protected Poin getOddTypeIndex(String oddType) throws UnknownOddTypeException {

        HashMap<String, Poin> index = new HashMap<>();

        //String[] arr = {"1", "", "2", "1X", "", "X2", "", "", "", "", "Yes", "No"};
        index.put("1", Poin.t(1,1));
        index.put("X", Poin.t(1,2));
        index.put("2", Poin.t(1,3));
        index.put("1X", Poin.t(2,1));
        index.put("12", Poin.t(2,2));
        index.put("X2", Poin.t(2,3));
        index.put("Yes", Poin.t(4,1));
        index.put("No", Poin.t(4,2));

        if( !index.containsKey(oddType) ) {
            throw new UnknownOddTypeException("Odd type [" + oddType + "]");
        }

        return index.get(oddType);
    }

    public Long getBalance() {

        goToAccountPage();

        WebElement balanceElement = presenceOfElementLocated(By.xpath("/html/body/div/div//div[@data-balance]//li/span[@class='total']"));

        String rawBalance = balanceElement.getText().trim().replaceAll("GEL", "").replaceAll(",", ".").trim();

        return Math.round(Double.parseDouble(rawBalance) * 100);
    }
}
