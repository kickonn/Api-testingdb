package pages;

import appmanager.Assertions;
import appmanager.HelperBase;
import dao.MaintenanceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AdactinPage extends HelperBase {


    @Value("${txtUserName}")
    public String txtUserName;

    @Value("${txtPassword}")
    public String txtPassword;

    @Value("${txtLogin}")
    public String txtLogin;




    public void loginToApplication(String userName, String password) {
        try {
            enterText(txtUserName, userName, "UserName TextBox");
            enterText(txtPassword, password, "Password TextBox");
            clickOn(txtLogin, "Login Button");

//            ArrayList<String> win = new ArrayList<>(getWebDriver().getWindowHandles());
//            getWebDriver().switchTo().window(win.get(1));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterthehoteldaeatilsandsearch() {
        selectFromDropdownByValue(getWebElement("//select[@id='location']"), "Sydney", "location");
        selectFromDropdownByValue(getWebElement("//select[@id='hotels']"), "Hotel Creek", "Hotels");
        clickOn("//input[@id='Submit']", "search");
        sleep(3000);
        clickOn("//input[@type='radio'][1]", "select hotel");
    }

    public void selectTheHotel() {
        sleep(1000);
        clickOn("//input[@type='radio'][1]", "select hotel");
        sleep(1000);
        clickOn("//input[@id='continue']", "continue");

    }

    public void bookTheHotel() {

        enterText("//input[@id='first_name']", "demo", "first_name");
        enterText("//input[@id='last_name']", "tester", "last_name");
        enterText("//textarea[@id='address']", "India", "address");
        enterText("//input[@id='cc_num']", "0123456789123456", "card no");
        selectFromDropdownByValue(getWebElement("//select[@id='cc_type']"), "AMEX", "card type");
        selectFromDropdownByValue(getWebElement("//select[@id='cc_exp_month']"), "1", "month");
        selectFromDropdownByValue(getWebElement("//select[@id='cc_exp_year']"), "2023", "year");
        enterText("//input[@id='cc_cvv']", "123", "cvv");
        clickOn("//input[@id='book_now']", "book_now");
        sleep(10000);
        Assertions.getInstance().assertTrue(getWebElement("//td[@class='login_title']").getText().equals("Booking Confirmation "),"successfully validated the Booking Confirmation  page");
        sleep(3000);

    }
 public void validatesBookedItinerarypage() {

     Assertions.getInstance().assertTrue(getWebElement("//td[@class='login_title']").getText().equals("Booked Itinerary"),"successfully validated the Booked Itinerary page");
  sleep(3000);
    }


public void validatessearchhotelpage() {

     Assertions.getInstance().assertTrue(getWebElement("//td[@class='login_title']").getText().equals("Search Hotel"),"successfully validated the Search Hotel page");
    sleep(3000);
    }



}










