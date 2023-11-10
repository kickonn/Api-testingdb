package pages;

import appmanager.Assertions;
import appmanager.HelperBase;
import dao.MaintenanceDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class databasePage extends HelperBase {
    @Autowired
    MaintenanceDAO maintenanceDAO;

    public static String firstname;
    public static String lastname;
    public static String email;


    public void navigatetoMyaccount(String username, String password) {
        clickOn("//span[.='My Account']", "My Account");
        sleep(1000);
        clickOn("//a[.='Login' and @class='dropdown-item']", "Login");
        enterText("//input[@name='email']", username, "username");
        enterText("//input[@name='password']", password, "password");
        clickOn("//button[.='Login']", "login");
        sleep(1000);
    }

    public void AccountDetails() {
        firstname = gettextValue(getWebElement("//input[@name='firstname']"));
        lastname = gettextValue(getWebElement("//input[@name='lastname']"));
        email = gettextValue(getWebElement("//input[@name='email']"));


    }

    public void databasevaliadtion(String tablename) {
        List<Map<String, Object>> dbdata = maintenanceDAO.getAllRecordsFromTable(tablename);
        String dbfirstname = dbdata.get(0).get("firstname").toString();
        String dblastname = dbdata.get(0).get("lastname").toString();
        String dbemail = dbdata.get(0).get("email").toString();
        Assertions.getInstance().assertEquals(firstname, dbfirstname, "successfully validated firstname in database " + dbfirstname);
        Assertions.getInstance().assertEquals(lastname, dblastname, "successfully validated firstname in database " + dblastname);
        Assertions.getInstance().assertEquals(email, dbemail, "successfully validated firstname in database " + dbemail);


    }

}


