package tests;

import appmanager.HelperBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import pages.databasePage;

public class database {
    @Autowired
    databasePage DataBasePage;
    HelperBase helperBase = new HelperBase();

    @Given("user login into the opencart application")
    public void userLoginIntoTheOpencartApplication() {
        helperBase.checkLogInUser();
    }

    @When("user navigates dashboard screen and clicks on my account")
    public void userNavigatesDashboardScreenAndClicksOnMyAccount() {

    }


    @Then("user validates stores the account details like first name,last name and email")
    public void userStoresTheAccountDetailsLikeFirstNameLastNameAndEmail() {
        DataBasePage.AccountDetails();
    }

    @Then("user clicks on edit account")
    public void userClicksOnEditAccount() {
        DataBasePage.clickOn("//a[.='Edit Account' and @class='list-group-item']", "Edit Account");

    }


    @Then("user validates the user account details in the DB:\"(.+?)\"")
    public void userValidatesTheUserAccountDetailsInTheDB(String arg0) {
        DataBasePage.databasevaliadtion(arg0);
    }

    @When("user navigates dashboard screen and User logs into the application with \"(.+?)\" and \"(.+?)\"")
    public void userNavigatesDashboardScreenAndUserLogsIntoTheApplicationWithAnd(String arg0, String arg1) {
        DataBasePage.navigatetoMyaccount(arg0,arg1);
    }
}
