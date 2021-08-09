import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Scanner;

public class AutomationWeb  {
    private final Scanner userInput;

    public AutomationWeb(){
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver_win32\\chromedriver.exe");
        userInput=new Scanner(System.in);

    }
    public void loginUser(){
        String userName="";
        String userPassword="";
        System.out.println("enter your user name :)");
         userName=userInput.next();
        System.out.println("enter your password :)");
         userPassword=userInput.next();
        WebDriver driver=new ChromeDriver();
        driver.get(Def.LINK_ASHKELON_COLLEGE);
        driver.manage().window().maximize();
        List<WebElement> elementList=driver.findElements(By.className("top-header-menu"));
        WebElement menu= elementList.get(0);
        List<WebElement> meuItems=menu.findElements(By.tagName("li"));
        WebElement presoanlInfo=meuItems.get(20);
        presoanlInfo.click();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        WebElement userNameInput=driver.findElement(By.id("Ecom_User_ID"));
        WebElement userPasswordInput=driver.findElement(By.id("Ecom_Password"));
        if (userNameInput !=null && userPasswordInput !=null){
            userNameInput.sendKeys(userName);
            userPasswordInput.sendKeys(userPassword);
        }
        List<WebElement> login=driver.findElements(By.className("submit"));
        WebElement clickLogin=login.get(0);
        clickLogin.click();
        List<WebElement> secondMenu=driver.findElements(By.className("col-sm-6"));
        WebElement moodle=secondMenu.get(6);
        moodle.click();
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        chooseACourse(driver);
        logoff(driver);
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        driver.close();

    }

    public void chooseACourse(WebDriver driver){
        List<WebElement> namesCourses=driver.findElements(By.className("multiline"));
        System.out.println(namesCourses.size());
        for (int i = 0; i < namesCourses.size(); i++) {
            System.out.println(i + ": " + namesCourses.get(i).getText());

        }

        try {
            System.out.println("entre your course you want : ");
            WebElement courseUser= namesCourses.get(userInput.nextInt());
            courseUser.click();
        }catch (Exception e){
            System.out.println("you must enter only numbers");
            userInput.nextLine();
        }


    }

    public void logoff(WebDriver driver){
        List<WebElement> menuProfile=driver.findElements(By.className("action-menu-trigger"));
        WebElement profile=menuProfile.get(0);
        profile.click();
        List<WebElement> ietmMenu=driver.findElements(By.className("dropdown-item"));
        WebElement logoff=ietmMenu.get(5);
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        logoff.click();
    }

}
